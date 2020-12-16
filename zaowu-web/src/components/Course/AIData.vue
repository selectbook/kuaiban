<template>
  <div class="course">
    <div class="container clearfix">
      <el-tabs :tab-position="'left'" style="height:800px auto">
        <el-tab-pane class="tab" label="课程介绍">
          <ul
            class="infinite-list"
            v-infinite-scroll="load"
            style="overflow:auto"
          >
            <div v-for="item in intro" :key="item.id" class="intro">
              <p class="introTitle">{{ item.title }}</p>
              <p class="introDes">{{ item.description }}</p>
              <p class="introDes">{{ item.description1 }}</p>
              <p class="introDes">{{ item.description2 }}</p>
              <p class="introDes">{{ item.description3 }}</p>
              <p class="introDes">{{ item.description4 }}</p>
              <p class="introDes">{{ item.description5 }}</p>
              <p class="introDes">{{ item.description6 }}</p>
              <img v-if="item.image" :src="item.image" alt="" class="introImg" />
              <p class="introDes">{{ item.description7 }}</p>
              <img v-if="item.image2" :src="item.image2" alt="" class="introImg" />
              <p class="introDes">{{ item.description8 }}</p>
            </div>
          </ul>
          <div class="intro">
          <p class="introTitle">考核安排</p>
          <el-steps :active="5" align-center style="margin-top:3%">
            <el-step title="报名缴费"></el-step>
            <el-step title="课程学习"></el-step>
            <el-step title="结业考试"></el-step>
            <el-step title="证书发放"></el-step>
          </el-steps>
          </div>
        </el-tab-pane>
        <el-tab-pane class="tab" label="培训大纲">
          <ul
            class="infinite-list"
            v-infinite-scroll="load"
            style="overflow:auto"
          >
            <el-table
              border
              :data="tableOutline"
              :span-method="cellMerge"
              style="width: 100%; overflow:auto"
            >
              <el-table-column
                prop="basicKnowledge"
                label="基础知识模块"
                width="400"
              >
              </el-table-column>
              <el-table-column prop="relatedKnowledge" label="相关知识">
              </el-table-column>
            </el-table>
          </ul>
        </el-tab-pane>
        <el-tab-pane class="tab" label="立即报名">
          <div class="courseList">
          <div style="margin: 20px; margin-left:50px"></div>
          <el-form
            :label-position="labelPosition"
            label-width="80px"
            :model="applyForm"
            :rules="rules"
            ref="applyForm"
          >
            <el-form-item label="姓名" prop="name">
              <el-input  placeholder="请输入姓名" v-model="applyForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-select style="width: 100%" v-model="applyForm.sex" placeholder="请选择性别">
                <el-option
                  v-for="item in sexOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            

            <el-form-item label="年龄" prop="age">
              <el-input placeholder="请输入年龄" v-model.number="applyForm.age"></el-input>
            </el-form-item>
            <el-form-item label="学历" prop="degree">
              <el-select style="width: 100%" v-model="applyForm.degree" placeholder="请选择学历">
                <el-option
                  v-for="item in degreeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            
            <el-form-item label="联系手机" prop="tel">
              <el-input placeholder="请输入手机号" v-model.number="applyForm.tel"></el-input>
            </el-form-item>
            <el-form-item label="微信" prop="wechat">
              <el-input placeholder="请输入微信号" v-model="applyForm.wechat"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button
                style="margin-left:45%"
                type="primary"
                @click="apply('applyForm')"
                :loading="loading"
                >{{ loading ? "提交中..." : "提交报名申请" }}</el-button
              >
              <div class="submit-text">
                <div style="margin-top: 20px;">信息保护申明:您提供的相关信息仅用于中科启秀培训教育业务，绝不对外泄露。</div>
              </div>

            </el-form-item>


          </el-form>
           
          </div>
          
          <!-- <ul
            class="infinite-list"
            v-infinite-scroll="load"
            style="overflow:auto"
          >
            <div v-for="item in signUpList" :key="item.id" class="intro">
                <el-row class="input-con">
                    <el-col :span="2"><div class="input-title">{{item.label}}:</div></el-col>
                    <el-col :span="22">
                        <el-input v-model="item.value" :type="item.type" :clearable="true" :placeholder=" '请输入' + (item.label) "></el-input>
                    </el-col>
                </el-row>
            </div>
            <div class="submit-btn">
                <el-button type="primary"  @click="submitInfo()">提交报名资料</el-button>
                <div style="margin-top: 20px;">信息保护申明:您提供的相关信息仅用于中科启秀培训教育业务，绝不对外泄露。</div>
            </div>
          </ul> -->
        </el-tab-pane>
       
        <el-tab-pane class="tab" label="课程学习">
          <div class="courseList">
            <div class="linkPage tetx1">
              <p>亲爱的同学,</p>
              <p>本次培训为中科AI数据科学学院与纽约数据科学学院合作服务，</p>
              <p>请前往美国纽约科学学院在线平台学习。</p>
            </div>

            <div class="submit-btn">
                <el-button type="primary"  >立即前往</el-button>
                <div style="margin-top: 20px;" class="tetx1">点击链接如遇到任何问题，可随时联系我的客服询问哦。</div>
            </div>
          </div>
        </el-tab-pane>
        <!-- <el-tab-pane class="tab" label="考核安排">
          <light-timeline :items="exam"></light-timeline>
        </el-tab-pane> -->
      </el-tabs>
    </div>
  </div>
</template>

<script>
import aidata_bg from '../../assets/aidata_bg.png'
import aidata2_bg from '../../assets/aidata2_bg.png'
export default {
  name: "Course",
  data() {
    var checkName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("姓名不能为空"));
      }
      setTimeout(() => {
        callback();
      }, 1000);
    };
    var checkEmail = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("邮箱不能为空"));
      }
      setTimeout(() => {
        callback();
      }, 1000);
    };
    var checkMobile = (rule, value, callback) => {
      if (!value) {
          return callback(new Error('手机号不能为空'));
        } else {
          const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
          console.log(reg.test(value));
          if (reg.test(value)) {
            callback();
          } else {
            return callback(new Error('请输入正确的手机号'));
          }
        }
    };
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("年龄不能为空"));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error("请输入数字值"));
        } else {
          callback();
        }
      }, 1000);
    };
    var checkSex = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("性别不能为空"));
      }
      setTimeout(() => {
        callback();
      }, 1000);
    };
    var checkWechat = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("微信不能为空"));
      }
      setTimeout(() => {
        callback();
      }, 1000);
    };
    return {
      sexOptions: [
        { id: 0, name: '男', value: '男'},
        { id: 1, name: '女', value: '女'},
      ],
      degreeOptions: [
          { id: 0, name: '大专',value: '大专'},
          { id: 1, name: '本科',value: '本科'},
          { id: 2, name: '硕士',value: '硕士'},
      ],
      tableOutline: [],
      tableFlow: [
        {
          course: "12987122",
          outline: "王小虎1",
          period: "11"
        },
        {
          course: "12987122",
          outline: "王小虎1",
          period: "11"
        },
        {
          course: "12987124",
          outline: "王小虎2",
          period: "11"
        },
        {
          course: "12987125",
          outline: "王小虎3",
          period: "11"
        },
        {
          course: "12987126",
          outline: "王小虎4",
          period: "11"
        }
      ],
      categoryList: [], // 分类列表
      currentIndex: 0, //分类列表选中
      categoryId: 0, //获取所有的课程列表的id
      labelPosition: "right",
      applyForm: {
        name: "",
        sex: "",
        age: "",
        degree: "",
        tel: "",
        wechat: ""
      },
      loading: false,
      rules: {
        name: [{ validator: checkName, trigger: "blur" }],
        age: [{ validator: checkAge, trigger: "blur" }],
        tel: [{ validator: checkMobile, trigger: "blur" }],
        email: [{ validator: checkEmail, trigger: "blur" }],
        sex: [{ validator: checkSex, trigger: "blur" }],
        wechat: [{ validator: checkWechat, trigger: "blur" }]
      },
      courseDetail: [
        {
          id: 1,
          coursedetail_id: 1,
          name: "python21天",
          course_img: "../../../static/images/mac1.jpeg",
          brief: "Python是一种跨平台的计算机程序设计语言。",
          bgColor: "#56CBC4",
          level: "初级",
          people_but: "",
          is_free: true,
          origin_price: "¥200",
          price: "¥0"
        },
        {
          id: 2,
          coursedetail_id: 2,
          name: "java21天",
          course_img: "../../../static/images/mac2.jpeg",
          brief: "Java是一种跨平台的计算机程序设计语言。",
          bgColor: "#4169E1",
          level: "初级",
          people_but: "",
          is_free: true,
          origin_price: "¥100",
          price: "¥0"
        },
        {
          id: 3,
          coursedetail_id: 3,
          name: "go21天",
          course_img: "../../../static/images/mac3.jpg",
          brief: "go是一种跨平台的计算机程序设计语言。",
          bgColor: "#9370DB",
          level: "中级",
          people_but: "",
          is_free: false,
          origin_price: "¥200",
          price: "¥100"
        },
        {
          id: 4,
          coursedetail_id: 4,
          name: "linux21天",
          course_img: "../../../static/images/mac3.jpg",
          brief: "linux简介。",
          bgColor: "#9370DB",
          level: "中级",
          people_but: "",
          is_free: false,
          origin_price: "¥200",
          price: "¥100"
        }
      ], //课程列表详情数据
      bgColors: [
        "#4AB2BF",
        "#1895C6",
        "#4C87E0",
        "#A361D9",
        "#F7AE6A",
        "#FF14A0",
        "#61F0E1",
        "#6282A7",
        "#27998E",
        "#3C74CC",
        "#A463DA",
        "#F0A257",
        "#DD4B7A",
        "#59C6BD",
        "#617FA1",
        "#1B92C3",
        "#30A297",
        "#3B73CB",
        "#9E57CA",
        "#A463DA",
        "#1895C6",
        "#A361D9",
        "#FF14A0"
      ],
      spanArr: [],
      spanArr2: [],
      count: 0,
      intro: [],
      signUpList: [
          {
            id: 0,
            label: '姓名',
            key: 'title',
            type: 'string',
            value: ''
          },
          {
            id: 1,
            label: '性别',
            key: 'sex',
            type: 'enum',
            value: ''
          },
          {
            id: 2,
            label: '年龄',
            key: 'age',
            type: 'number',
            value: ''
          },
          {
            id: 3,
            label: '学历',
            key: 'edu',
            type: 'enum',
            value: ''  
          },
          {
            id: 4,
            label: '手机号',
            key: 'phone',
            type: 'number',
            value: '' 
          },
          {
            id: 5,
            label: '微信',
            key: 'wechat',
            type: 'string',
            value: ''
          },
      ]
    };
  },
  methods: {
    apply(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.timer = setTimeout(() => {
            let params = {
              	age: this.applyForm.age,
                degree: this.applyForm.degree,
                phonenumber: this.applyForm.tel,
                sex: this.applyForm.sex,
                userName: this.applyForm.name,
                wechat: this.applyForm.wechat,
                registType: 'AI数据科学'
            };
            this.$http.examRegist(params)
              .then(res => {
                if (res.data.code == 0) {
                  this.$message("报名成功!");
                  this.$router.push({
                               name:"Home"
                      });
                  this.drawer = false;
                } else if (res.data.code == 11) {
                  this.$message("报名失败！");
                } else {
                  this.$message("报名失败！");
                }
              })
              .catch(err => {
                this.$message("报名失败！，请检查信息是否正确！");
              });
            setTimeout(() => {
              this.loading = false;
            }, 400);
          }, 2000);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    submitInfo() {
        console.log('this===',this.signUpList)
    },
    load() {
      this.count += 2;
    },
    getSpanArr(data) {
      for (var i = 0; i < data.length; i++) {
        if (i === 0) {
          this.spanArr.push(1);
          this.pos = 0;
        } else {
          // 判断当前元素与上一个元素是否相同
          if (data[i].basicKnowledge === data[i - 1].basicKnowledge) {
            this.spanArr[this.pos] += 1;
            this.spanArr.push(0);
          } else {
            this.spanArr.push(1);
            this.pos = i;
          }
        }
      }
    },
    cellMerge({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 0) {
        const _row = this.spanArr[rowIndex];
        const _col = _row > 0 ? 1 : 0;
        return {
          rowspan: _row,
          colspan: _col
        };
      }
    },
    getSpanArr2(data) {
      for (var i = 0; i < data.length; i++) {
        if (i === 0) {
          this.spanArr2.push(1);
          this.pos = 0;
        } else {
          // 判断当前元素与上一个元素是否相同
          if (data[i].course === data[i - 1].course) {
            this.spanArr2[this.pos] += 1;
            this.spanArr2.push(0);
          } else {
            this.spanArr2.push(1);
            this.pos = i;
          }
        }
      }
    },
    cellMerge2({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 0) {
        const _row = this.spanArr2[rowIndex];
        const _col = _row > 0 ? 1 : 0;
        return {
          rowspan: _row,
          colspan: _col
        };
      }
    },
    // 课程详情 事件操作
    detailHandler(id) {
      this.$router.push({
        name: "subject",
        params: {
          detailId: id
        }
      });
    },
    getOutline() {
    //   this.$http
    //     .getOutline("AI")
    //     .then(res => {
    //       if (!res.code) {
    //           console.log('getOutline==',res.data)
    //         this.tableOutline = res.data.data;
    //         this.getSpanArr(this.tableOutline);
    //       }
    //     })
    //     .catch(err => {});
                                               
        const outLineList = [
            {
                basicKnowledge: "入门基础",
                relatedKnowledge: "数据科学入门基础"
            },
            {
                basicKnowledge: "入门基础",
                relatedKnowledge: "统计学入门基础"
            },
           
            {
                basicKnowledge: "入门基础",
                relatedKnowledge: "专业数据分析师嘉宾分享"
            },
           
            {
                basicKnowledge: "数据库与SQL",
                relatedKnowledge: "Linux"
            },
            {
                basicKnowledge: "数据库与SQL",
                relatedKnowledge: "Git与github"
            },
            {
                basicKnowledge: "数据库与SQL",
                relatedKnowledge: "数据库与SQL"
            },
            {
                basicKnowledge: "数据分析与Python",
                relatedKnowledge: "什么是数据分析与python"
            },
            {
                basicKnowledge: "数据分析与Python",
                relatedKnowledge: "数据结构"
            },
            {
                basicKnowledge: "数据分析与Python",
                relatedKnowledge: "高阶Python"
            },

        ]
        this.tableOutline = outLineList
        this.getSpanArr(this.tableOutline)
    },
    getIntro() {
    //   this.$http
    //     .getIntro("AI")
    //     .then(res => {
    //       if (!res.code) {
    //         this.intro = res.data.data;
    //         console.log('this.intro==',this.intro)
    //       }
    //     })
    //     .catch(err => {});
        const introList = [
            {
                description:"简单定义为：数据科学是从数据中提取有用知识的一系列技能和技术。这些技能通常用德鲁·康威（Drew Conway）创造的维恩图（或它的变体）来表示：",
                image: aidata_bg,
                image2: aidata2_bg,
                title: "什么是数据科学",
                description7: '三个圆圈分别代表三个不同的领域：编程领域（语言知识、语言库、设计模式、体系结构等）；数学（代数、微积分等）和统计学领域；数据领域(特定领域的知识：医疗、金融、工业等)。这些领域共同构成了定义中的技能和技术。它们包括获取数据、数据清理、数据分析、创建假设、算法、机器学习、优化、结果可视化等等。数据科学汇集了这些领域和技能，支持和改进了从原始数据中提取见解和知识的过程。',
                description8: '什么是“有用的知识”？就是可以具有某种价值、可以回答或解决现实世界中问题的知识。数据科学也可以定义为：研究应用数据处理和分析方面的进展，为我们提供解决方法和答案的领域。'
            },
            {
                description:"面向国家战略性新兴产业发展需求，培养具有扎实人工智能工程领域的专业知识，具备较强的创新实践能力，良好的团队协作沟通能力和自主学习能力，能够在人工智能工程领域从事软硬件产品开发，人工智能应用系统设计、开发及维护以及科学研究等工作的高素质复合型工程技术人才。",
                title: "培训目的"
            },
            {
              description1: '1.应届毕业生',
              description2: '2.在职转行人士',
              description3: '3.职业技能提升',
              description4: '4.职场新手',
              description5: '5.专项技能学习',
              description6: '6.业务相关需求',
              title: "培训对象"
            },
        ]
        this.intro = introList
    },
    // 获取全部的课程列表
    courseList() {
      this.$http
        .courseList("AI")
        .then(res => {
          if (!res.code) {
            console.log('courseList==',res.data)
            this.categoryList = res.data;
            // this.courseDetail = res.data;
            this.courseDetail.forEach((item, index) => {
              this.bgColors.forEach((bgColor, i) => {
                if (i === index) {
                  item.bgColor = bgColor;
                }
              });
            });
          }
        })
        .catch(err => {});
    }
  },

  // 生命周期  在created方法发起ajax请求
  created() {
    // 返回一个Axios实例化对象

    // 获取课程列表
    // this.courseList();

    this.getOutline();
    this.getSpanArr2(this.tableFlow);
    this.getIntro();
  }
};
</script>

<style lang="css" scoped>
.tetx1 {
  font-size: 16px;
  line-height: 2;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.linkPage {
  display: flex;
  flex-direction: column;
  align-items: center;
    margin-top: 100px;
}
.submit-text {
    width: 100%;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    margin: 10px 0 10px 0;
}
.submit-btn {
    width: 100%;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    margin: 100px 0 100px 0;
}
.input-con{
    margin-top: 20px;
}
.input-title{
    height: 40px;
    margin-right: 10px;
    display: flex;
    align-items: center;
    justify-content: flex-end;
}
.intro{
  margin-left: 8%;
  margin-right: 8%;
}
.introTitle {
  margin-bottom: 1%;
  font-size: 32px;
  line-height: 2;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.introDes {
  font-size: 16px;
  line-height: 2;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  text-indent: 32px;
  margin-left: 5%;
}
.introImg {
  margin-top: 5%;
  margin-bottom: 5%;
  width:100%;
  display: table-cell;
  position: relative;
}
.course {
  width: 100%;
  height: 800px auto;
  margin-top: 1px;
  /* background: #f3f3f3; */
}
.tab {
  width: 100%;
  height: 1000px auto;
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
  margin-left: 0%;
  overflow: hidden;
}
.detail {
  float: left;
  width: 248px;
  height: auto;
  margin-right: 16px;
  margin-bottom: 30px;
  margin-top: 5%;
  margin-left: 4%;
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
  width: 248px;

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
  width: 100%;
  height: 40px;
  line-height: 40px;
  position: relative;
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
</style>
