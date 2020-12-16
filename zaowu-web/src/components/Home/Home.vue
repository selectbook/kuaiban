<template>
  <div>
    <el-carousel indicator-position="outside" height="500px">
      <el-carousel-item v-for="item in lunboImgs" :key="item.id">
        <img :src="item.imgSrc" alt="" />
      </el-carousel-item>
    </el-carousel>

    <div class="course-class">
      <el-row :gutter="20">
        <el-col :span="11" :offset="7">
          <el-divider content-position="center"
            ><span class="course-title">机器人工程技术</span></el-divider
          >
        </el-col>
      </el-row>
      <el-row :gutter="20" style="margin-top:50px; margin-top:80px">
        <el-col :span="7" :offset="2">
          <el-image
            style="margin-left: 30px"
            src="../../../static/images/Robot.gif"
          ></el-image>
        </el-col>
        <el-col :span="11" :offset="2">
          <p class="course-descript">
            此课程体系通过一系列的基于工业机器人的应用系统设计实例，传授机器人应用技术专业领域的新理论、新技术和关键技能通过一系列的基于工业机器人的应用系统设计实例，传授机器人应用技术专业领域的新理论、新技术和关键技能。
            </p>
          <el-card class="box-card" shadow="never">
            <div slot="header" class="clearfix">
              <span class="outline">培训大纲</span>
              <el-button style="float: right; padding: 8px 0; font-size:16px" type="text" @click="jump('Robot')"
                >加入学习</el-button
              >
            </div>
            <div v-for="item in RBOutline" :key="item" class="text item">
              {{ item }}
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div class="course-class">
      <el-row :gutter="20">
        <el-col :span="11" :offset="7">
          <el-divider content-position="center"
            ><span style="font-size:30px; font-family:'PingFang SC'"
              >物联网工程技术</span
            ></el-divider
          >
        </el-col>
      </el-row>
      <el-row :gutter="20" style="margin-top:50px">
        <el-col :span="7" :offset="2">
          <el-image
            style="margin-left: 30px;margin-top:80px"
            src="../../../static/images/IOT.gif"
          ></el-image>
        </el-col>

        <el-col :span="11" :offset="2">
          <p class="course-descript">
            此课程体系是面向物联网工程技术人员的职业教育课程，课程深入行业，与行业对接，与企业共建，以企业技术应用为重点，瞄准物联网产业中职业岗位，围绕如何培养职业岗位技能，依托中科深圳先进技术研究院师资力量进行教研课程研发，解析最新科学技术和最新应用场景。
          </p>
          <el-card class="box-card" shadow="never">
            <div slot="header" class="clearfix">
              <span class="outline">培训大纲</span>
              <el-button style="float: right; padding: 8px 0; font-size:16px" type="text" @click="jump('IOT')"
                >加入学习</el-button
              >
            </div>
            <div v-for="item in IOTOutline" :key="item" class="text item">
              {{ item }}
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div class="course-class">
      <el-row :gutter="20">
        <el-col :span="11" :offset="7">
          <el-divider content-position="center"
            ><span style="font-size:30px; font-family:'PingFang SC'"
              >区块链工程技术</span
            ></el-divider
          >
        </el-col>
      </el-row>
      <el-row :gutter="20" style="margin-top:50px; align:middle;">
        <el-col :span="7" :offset="2">
          <el-image
            style="margin-left: 30px; margin-top:80px"
            src="../../../static/images/BC.gif"
          ></el-image>
        </el-col>
        <el-col :span="11" :offset="2">
          <p class="course-descript">
            此课程体系全面的讲解了区块链技术的前世今生，从区块链底层结构到“区块链+”的实际应用场景，循序渐进的带领学员全面、深入地学习区块链技术。
          </p>
          <el-card class="box-card" shadow="never">
            <div slot="header" class="clearfix">
              <span class="outline">培训大纲</span>
              <el-button style="float: right; padding: 8px 0; font-size:16px" type="text" @click="jump('BC')"
                >加入学习</el-button
              >
            </div>
            <div v-for="item in BCOutline" :key="item" class="text item">
              {{ item }}
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div class="course-class">
      <el-row :gutter="20">
        <el-col :span="11" :offset="7">
          <el-divider content-position="center"
            ><span style="font-size:30px; font-family:'PingFang SC'"
              >人工智能工程技术</span
            ></el-divider
          >
        </el-col>
      </el-row>
      <el-row :gutter="20" style="margin-top:50px; ">
        <el-col :span="7" :offset="2">
          <el-image
            style="margin-left: 30px;margin-top:80px"
            src="../../../static/images/AI.gif"
          ></el-image>
        </el-col>
        <el-col :span="11" :offset="2">
          <p class="course-descript">
            此课程体系强调实践性，强调动手操作，重点介绍机器学习和深度学习最常用的Python开源框架，完成本系列培训之后即可上手使用这些工具快速完成常见机器学习和深度学习算法模型的实现。
          </p>
          <el-card class="box-card" shadow="never">
            <div slot="header" class="clearfix">
              <span class="outline">培训大纲</span>
              <el-button style="float: right; padding: 8px 0; font-size:16px" type="text" @click="jump('AI')"
                >加入学习</el-button
              >
            </div>
            <div v-for="item in AIOutline" :key="item" class="text item">
              {{ item }}
            </div>
          </el-card>
        </el-col>
        </el-row>
    </div>
  </div>

</template>

<script>
export default {
  name: "Home",
  data() {
    return {lunboImgs: [
        {
          id: 1,
          imgSrc:
            "../../../static/images/cover1.gif"
        },
        {
          id: 2,
          imgSrc:
            "../../../static/images/cover2.gif"
        },
        {
          id: 3,
          imgSrc:
            "../../../static/images/cover3.gif"
        }
      ],
      AIOutline: [],
      BCOutline: [],
      IOTOutline: [],
      RBOutline: []
    };
  },
  methods: {
    jump(data){
      if(data==='IOT'){
        this.$router.push({
          path:'/IOT'
        });
      }
      if(data==='AI'){
        this.$router.push({
          path:'/AI'
        });
      }
      if(data==='BC'){
        this.$router.push({
          path:'/blockchain'
        });
      }
      if(data==='Robot'){
        this.$router.push({
          path:'/robot'
        });
      }
    },
    getAIArr(data) {
      for (var i = 0; i < data.length; i++) {
        if (i === 0) {
          this.AIOutline.push(data[i].basicKnowledge);
        } else {
          // 判断当前元素与上一个元素是否相同
          if (data[i].basicKnowledge === data[i - 1].basicKnowledge) {
          } else {
            this.AIOutline.push(data[i].basicKnowledge);
          }
        }
      }
    },
    getBCArr(data) {
      for (var i = 0; i < data.length; i++) {
        if (i === 0) {
          this.BCOutline.push(data[i].basicKnowledge);
        } else {
          // 判断当前元素与上一个元素是否相同
          if (data[i].basicKnowledge === data[i - 1].basicKnowledge) {
          } else {
            this.BCOutline.push(data[i].basicKnowledge);
          }
        }
      }
    },
    getIOTArr(data) {
      for (var i = 0; i < data.length; i++) {
        if (i === 0) {
          this.IOTOutline.push(data[i].basicKnowledge);
        } else {
          // 判断当前元素与上一个元素是否相同
          if (data[i].basicKnowledge === data[i - 1].basicKnowledge) {
          } else {
            this.IOTOutline.push(data[i].basicKnowledge);
          }
        }
      }
    },
    getRBArr(data) {
      for (var i = 0; i < data.length; i++) {
        if (i === 0) {
          this.RBOutline.push(data[i].basicKnowledge);
        } else {
          // 判断当前元素与上一个元素是否相同
          if (data[i].basicKnowledge === data[i - 1].basicKnowledge) {
          } else {
            this.RBOutline.push(data[i].basicKnowledge);
          }
        }
      }
    },
    // 获取培训大纲
    getAIOutline() {
      this.$http
        .getOutline("AI")
        .then(res => {
          if (!res.code) {
            this.getAIArr(res.data.data);
          }
        })
        .catch(err => {});
    },
    getIOTOutline() {
      this.$http
        .getOutline("IOT")
        .then(res => {
          if (!res.code) {
            this.getIOTArr(res.data.data);
          }
        })
        .catch(err => {});
    },
    getBCOutline() {
      this.$http
        .getOutline("BC")
        .then(res => {
          if (!res.code) {
            this.getBCArr(res.data.data);
          }
        })
        .catch(err => {});
    },
    getRBOutline() {
      this.$http
        .getOutline("Robot")
        .then(res => {
          if (!res.code) {
            this.getRBArr(res.data.data);
          }
        })
        .catch(err => {});
    }
  },
  // 生命周期  在created方法发起ajax请求
  created() {
    this.getAIOutline();
    this.getIOTOutline();
    this.getRBOutline();
    this.getBCOutline();

  }
};
</script>


<style lang="css" scoped>
img {
  width: 100%;
}
.course {
  width: 100%;
  height: 1000px;
  background: #f3f3f3;
}
.coursebox {
  padding: 24px 0;
  font-size: 16px;
  color: #666;
  letter-spacing: 0.41px;
  font-family: PingFangSC-Regular;
  overflow: hidden;
}
.coursebox li {
  padding: 8px;
}
.coursebox li.active {
  border-radius: 5%;
  border: 1px solid orange;
  color: darkorange;
}
ul li {
  float: left;
  margin-right: 24px;
  cursor: pointer;
}

ul li.active {
  color: #00b4e4;
}
.courseList {
  width: 100%;
  height: auto;
  overflow: hidden;
}
.detail {
  float: left;
  width: 248px;
  height: auto;
  margin-right: 16px;
  margin-bottom: 30px;
  position: relative;
  padding: 0 20px;
  background: #fff;
  box-shadow: 0 2px 6px 0 #e8e8e8;
  transition: all 0.2s linear;
  cursor: pointer;
}
.detail:hover {
  box-shadow: 0 8px 15px rgba(0, 0, 0, 0.15);
  transform: translate3d(0, -3px, 0);
}
.detail:nth-of-type(4n) {
  margin-right: 0;
}
.head {
  width: 100%;
  height: 144px;
}
.detail .head img {
  width: 100%;
  height: 144px;
  position: absolute;
  left: 0;
  top: 0;
}
.detail .head b {
  width: 100%;
  height: 144px;
  position: absolute;
  left: 0;
  top: 0;
  opacity: 0.9;
  background: #56cbc4;
}
.detail .head p {
  position: absolute;
  width: 248px;
  height: 144px;
  left: 0;
  top: 0;
  text-align: center;
  font-family: PingFangSC-Medium;
  font-size: 24px;
  color: #fff;
  overflow: hidden;
  display: flex;
  align-items: center;
  padding: 0 20px;
  justify-content: space-around;
}
.content {
  width: 250px;
  height: 118px;
  padding-top: 30px;
}
.content p {
  width: 100%;
  height: 40px;
  font-size: 14px;
  color: #666;
  cursor: pointer;
  letter-spacing: 0.6px;
  margin-bottom: 20px;
  font-family: PingFangSC-Regular;
  overflow: hidden;
}
.content-detail {
  width: 5%;
  height: 10px;
  line-height: 10px;
  /* position: relative; */
}
.content-detail .span3 {
  position: absolute;
  right: 0;
}
.content-detail .span3 .s {
  text-decoration: line-through;
}
.content-detail .span4 {
  /*margin-left: 100px;*/
  position: absolute;
  right: 0;
  color: #fc0107;
}
.content-detail .span3 .t {
  color: #000;
  margin-left: 5px;
  text-decoration: none !important;
  color: #fc0107;
}
.course-title {
  font-size: 30px;
  font-family: "PingFang SC";
  margin-bottom: 50px;
}
.course-class {
  margin-top: 5%;
  margin-bottom: 3%;
}
.course-descript {
  line-height: 2;
  font-size: 20px;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  text-indent: 40px;
}

.outline{
  margin-left: 40%;
  font-size: 24px;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  color:#303133;
}
  .text {
  text-align: center;
  margin-left: 3%;
  font-size: 16px;
  font-family:'Times New Roman', Times, serif;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 500px;
    margin-top:30px;
  }
</style>
