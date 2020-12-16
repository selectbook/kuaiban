package com.catelf.qzpoint.streaming

import java.lang
import java.sql.ResultSet
import java.util.Random

import com.catelf.qzpoint.util.{DataSourceUtil, QueryCallback, SqlProxy}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}


import scala.collection.mutable

object RegisterStreaming {
  private val groupid = "register_group_test1"

  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "hdfs")
    val conf = new SparkConf().setAppName(this.getClass.getSimpleName)
//      .setMaster("local[2]")
      .set("spark.streaming.kafka.maxRatePerPartition", "50")
      .set("spark.streaming.stopGracefullyOnShutdown", "true")
    val ssc = new StreamingContext(conf, Seconds(3))
    val topics = Array("register_topic")
    val kafkaMap: Map[String, Object] = Map[String, Object](
      "bootstrap.servers" -> "cdh1.macro.com:9092,cdh2.macro.com:9092,cdh3.macro.com:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> groupid,
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: lang.Boolean)
    )
    ssc.checkpoint("hdfs://cdh1.macro.com:8020/user/catelf/sparkstreaming/checkpoint")
    //查询mysql中是否有偏移量
    val sqlProxy = new SqlProxy()
    val offsetMap = new mutable.HashMap[TopicPartition, Long]()
    val client = DataSourceUtil.getConnection
    try {
      sqlProxy.executeQuery(client, "select * from `offset_manager` where groupid=?", Array(groupid), new QueryCallback {
        override def process(rs: ResultSet): Unit = {
          while (rs.next()) {
            val model = new TopicPartition(rs.getString(2), rs.getInt(3))
            val offset = rs.getLong(4)
            offsetMap.put(model, offset)
          }
          rs.close() //关闭游标
        }
      })
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      sqlProxy.shutdown(client)
    }
    //设置kafka消费数据的参数  判断本地是否有偏移量  有则根据偏移量继续消费 无则重新消费
    val stream: InputDStream[ConsumerRecord[String, String]] = if (offsetMap.isEmpty) {
      KafkaUtils.createDirectStream(
        ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](topics, kafkaMap))
    } else {
      KafkaUtils.createDirectStream(
        ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](topics, kafkaMap, offsetMap))
    }
    val resultDStream = stream.filter(item => item.value().split("\t").length == 3).
      mapPartitions(partitions => {
        partitions.map(item => {
          val line = item.value()
          val arr = line.split("\t")
          val app_name = arr(1) match {
            case "1" => "PC"
            case "2" => "APP"
            case _ => "Other"
          }
          (app_name, 1)
        })
      })
    resultDStream.cache()
    //    resultDStream.reduceByKeyAndWindow((x: Int, y: Int) => x + y, Seconds(60), Seconds(6)).print()
    val updateFunc = (values: Seq[Int], state: Option[Int]) => {
      val currentCount = values.sum //本批次求和
      val previousCount = state.getOrElse(0) //历史数据
      Some(currentCount + previousCount)
    }
    resultDStream.updateStateByKey(updateFunc).print()


    //        val dsStream = stream.filter(item => item.value().split("\t").length == 3)
    //          .mapPartitions(partitions =>
    //            partitions.map(item => {
    //              val rand = new Random()
    //              val line = item.value()
    //              val arr = line.split("\t")
    //              val app_id = arr(1)
    //              (rand.nextInt(3) + "_" + app_id, 1)
    //            }))
    //        dsStream.print()
    //        val a = dsStream.reduceByKey(_ + _)
    //        a.print()
    //        a.map(item => {
    //          val appid = item._1.split("_")(1)
    //          (appid, item._2)
    //        }).reduceByKey(_ + _).print()

    //处理完 业务逻辑后 手动提交offset维护到本地 mysql中
    stream.foreachRDD(rdd => {
      val sqlProxy = new SqlProxy()
      val client = DataSourceUtil.getConnection
      try {
        val offsetRanges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        for (or <- offsetRanges) {
          sqlProxy.executeUpdate(client, "replace into `offset_manager` (groupid,topic,`partition`,untilOffset) values(?,?,?,?)",
            Array(groupid, or.topic, or.partition.toString, or.untilOffset))
        }
      } catch {
        case e: Exception => e.printStackTrace()
      } finally {
        sqlProxy.shutdown(client)
      }
    })
    ssc.start()
    ssc.awaitTermination()
  }

}
