<template>
  <div class="course">
    <div class="container clearfix">
      <el-tabs :tab-position="'left'" style="height:800px auto">
        <el-tab-pane class="tab" label="了解1+X">
          <ul
            class="infinite-list"
            v-infinite-scroll="load"
            style="overflow:auto"
          >
            <div v-for="item in intro" :key="item.id" class="intro">
              <p class="introTitle">{{ item.title }}</p>
              <p class="introDes">{{ item.description1 }}</p>
              <img :src="item.image" alt="" class="introImg" />
              <p class="introDes">{{ item.description2 }}</p><hr/>
              <p class="introDes">{{ item.description3 }}</p><hr/>
              <p class="introDes">{{ item.description4 }}</p><hr/>
              <p class="introDes">{{ item.description5 }}</p>
            </div>
          </ul>
        </el-tab-pane>
        <el-tab-pane class="tab" label="关于考试">
          <ul
            class="infinite-list"
            v-infinite-scroll="load"
            style="overflow:auto"
          >
            <div class="intro">
              <div class="intro">
                <p class="introTitle">考试流程</p>
                <el-steps :active="5" align-center style="margin-top:3%">
                  <el-step title="高职院校学生"></el-step>
                  <el-step title="考试专业选择"></el-step>
                  <el-step title="官网报名"></el-step>
                  <el-step title="获得证书"></el-step>
                </el-steps>
                <img :src="aboutEx.image" alt="" class="introImg" />
                <p class="introTitle">{{ aboutEx.subTitle1 }}</p>
                <p class="introDes">{{ aboutEx.subDescription11 }}</p>
                <p class="introDes">{{ aboutEx.subDescription12 }}</p>
                <p class="introTitle">{{ aboutEx.subTitle2 }}</p>
                <p class="introDes">{{ aboutEx.subDescription21 }}</p>
                <p class="introDes">{{ aboutEx.subDescription22 }}</p>
              </div>
            </div>
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
             <el-form-item label="身份证号" prop="cardId">
              <el-input placeholder="请输入身份证号" v-model.number="applyForm.cardId"></el-input>
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
             <el-form-item label="考试科目" prop="exSubject">
              <el-select style="width: 100%" v-model="applyForm.exSubject" placeholder="请选择考试科目">
                <el-option
                  v-for="item in exSubjectOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
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
                <div class="qrcode">
                  <div class="qrcode-div">
                    <img src="../../assets/collectionCode.jpeg" alt="" class="qrcode-img" />
                    <h2 style="color:#409EFF ">扫码缴费</h2>
                    <h3>缴费后,立即扫描二维码联系客服,获取"考试账户"</h3>
                  </div>
                  <div class="qrcode-div">
                    <img src="../../assets/service.jpeg" alt="" class="qrcode-img" />
                    <h2 style="color:#409EFF ">联系客服获取"考试账户</h2>
                    <h3>获取考号后立即前往"在线考试"</h3>
                  </div>
                </div>
              </div>
              
            </el-form-item>
          </el-form>
          </div>
        </el-tab-pane>   
        <el-tab-pane class="tab dl-container" label="在线考试">
          <div class="dl-app">
           <img src="../../assets/onlie_ex.png" alt="" class="dl-img" />
            <el-button type="primary"  @click="openDlPage()">立即下载</el-button>
            <p class="dl-text">点击下载考试客户端</p>
            <p class="dl-text">安装并登录</p>
            <p class="dl-text">开始考试</p>            
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
import x1_bg from '../../assets/x1_bg.png'
import about_ex from '../../assets/about_ex.png'
import onlie_ex from '../../assets/onlie_ex.png'
import collectionCode from '../../assets/collectionCode.jpeg'
import service from '../../assets/service.jpeg'
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
    var checkCardId = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("身份证号不能为空"));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error("请输入数字值"));
        } else {
          callback();
        }
      }, 1000);
    };
    var checkExSubject = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("考试科目不能为空"));
      }
      setTimeout(() => {
        callback();
      }, 1000);
    };


    return {
      labelPosition: "right",
      applyForm: {
        name: "",
        sex: "",
        age: "",
        degree: "",
        tel: "",
        wechat: "",
        cardId: "",
        exSubject: ""
      },
      loading: false,
      rules: {
        name: [{ validator: checkName, trigger: "blur" }],
        age: [{ validator: checkAge, trigger: "blur" }],
        tel: [{ validator: checkMobile, trigger: "blur" }],
        email: [{ validator: checkEmail, trigger: "blur" }],
        sex: [{ validator: checkSex, trigger: "blur" }],
        wechat: [{ validator: checkWechat, trigger: "blur" }],
        cardId: [{ validator: checkCardId, trigger: "blur" }],
        exSubject: [{ validator: checkExSubject, trigger: "blur" }],
      },
      sexOptions: [
        { id: 0, name: '男', value: '男'},
        { id: 1, name: '女', value: '女'},
      ],
      degreeOptions: [
          { id: 0, name: '大专',value: '大专'},
          { id: 1, name: '本科',value: '本科'},
          { id: 2, name: '硕士',value: '硕士'},
      ],
      exSubjectOptions: [
          { id: 0, name: '虚拟现实应用开发职业技能等级证书',value: '虚拟现实应用开发职业技能等级证书'},
          { id: 1, name: '工业机器人操作与运维职业技能等级证书',value: '工业机器人操作与运维职业技能等级证书'},
          { id: 2, name: '虚拟现实应用开发职业技能等级证书',value: '虚拟现实应用开发职业技能等级证书'},
          { id: 3, name: '制造执行系统实施与应用',value: '制造执行系统实施与应用'},
          { id: 4, name: '大数据工程化处理与应用',value: '大数据工程化处理与应用'},
          { id: 5, name: '互联网软件测试',value: '互联网软件测试'},
          { id: 6, name: '人工智能前端设备应用',value: '人工智能前端设备应用'},
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
            value: '',
            list: [
              { id: 0, name: '男', value: '男'},
              { id: 1, name: '女', value: '女'},
            ]
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
            label: '身份证号',
            key: 'ID',
            type: 'number',
            value: ''
          },
          {
            id: 4,
            label: '学历',
            key: 'edu',
            type: 'enum',
            value: '',
            list: [
              { id: 0, name: '大专',value: '大专'},
              { id: 1, name: '本科',value: '本科'},
              { id: 2, name: '硕士',value: '硕士'},
            ]
          },
          {
            id: 5,
            label: '手机号',
            key: 'phone',
            type: 'number',
            value: '' 
          },
          {
            id: 6,
            label: '微信',
            key: 'wechat',
            type: 'string',
            value: ''
          },
          {
            id: 7,
            label: '考试科目',
            key: 'classes',
            type: 'enum',
            value: '',
            list: [
              { id: 0, name: '虚拟现实应用开发职业技能等级证书',value: '虚拟现实应用开发职业技能等级证书'},
              { id: 1, name: '工业机器人操作与运维职业技能等级证书',value: '工业机器人操作与运维职业技能等级证书'},
              { id: 2, name: '虚拟现实应用开发职业技能等级证书',value: '虚拟现实应用开发职业技能等级证书'},
              { id: 3, name: '制造执行系统实施与应用',value: '制造执行系统实施与应用'},
              { id: 4, name: '大数据工程化处理与应用',value: '大数据工程化处理与应用'},
              { id: 5, name: '互联网软件测试',value: '互联网软件测试'},
              { id: 6, name: '人工智能前端设备应用',value: '人工智能前端设备应用'},
            ]
          },
      ],
      aboutEx: {
        title: '1+X证书用途',
        subTitle1: '1.探索职业教育基础：',
        subDescription11: '探索实施1+X证书制度，是近年来我国深化职业教育体制改革的一大亮点。其中，“1”为学历证书，“X”为若干职业技能等级证书。',
        subDescription12: '这项制度的探索，旨在打通学历教育和职业培训之间的区隔，为进一步加快培养复合型技术技能人才、拓展就业创业本领奠定制度基础。',
        subTitle2: '2.学生综合能力的体现：',
        subDescription21: '从某种意义上讲，职业教育就是教育和社会实际相结合的有效载体。鼓励学生在获得学历证书的同时，积极取得多类职业技能等级证书，一方面有利于学生个人综合能力的提升，这是人才培养的首要目标；',
        subDescription22: '另一方面，在创业就业过程中，学生可选的渠道更宽、可用的资源更广，亦契合当下社会的现实需要。',
        image: about_ex
      }
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
                idcard: this.applyForm.cardId,
                subject: this.applyForm.exSubject,
                registType: '1+X考试 '
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

    openDlPage() {
      window.open('https://pintia.cn/download-oms-client?tab=downloads', "_blank");
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
                basicKnowledge: "人工智能入门",
                relatedKnowledge: "人工智能概论"
            },
            {
                basicKnowledge: "人工智能入门",
                relatedKnowledge: "人工智能/机器学习和深度学习的概念及关系"
            },
            {
                basicKnowledge: "人工智能机器视觉系统集成开发实战",
                relatedKnowledge: "AI检测模块的调用及测试用"
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
                image: x1_bg,
                description1:"1+x证书是学历证书+若干职业技能等级证书制度（简称1+X证书制度），1+X证书制度鼓励学生在获得学历证书的同时，积极取得多类职业技能等级证书，拓展就业创业本领，缓解结构性就业矛盾。",
                description2: "“1”是学历证书，是指学习者在学制系统内实施学历教育的学校或者其他教育机构中完成了学制系统内一定教育阶段学习任务后获得的文凭；“X”为若干职业技能等级证书。学历证书+若干职业技能等级证书制度（简称1+X证书制度），就是学生在获得学历证书的同时，取得多类职业技能等级证书。职教界内外最为关注的，实际就是这个“X”。在实施“1+X证书制度”时，无疑须处理好学历证书“1”与职业技能等级证书“X”的关系。“1”是基础，“X”是“1”的补充、强化和拓展。学历证书和职业技能等级证书不是两个并行的证书体系，而是两种证书的相互衔接和相互融通。",
                description3: "书证相互衔接融通是“1+X证书制度”的精髓所在，这种衔接融通主要体现在：职业技能等级标准与各个层次职业教育的专业教学标准相互对接。这种对接是由学历证书与职业技能等级证书的关系决定的。不同等级的职业技能标准应与不同教育阶段学历职业教育的培养目标和专业核心课程的学习目标相对应，保持培养目标和教学要求的一致性。",
                description4: "“X”证书的培训内容与专业人才培养方案的课程内容相互融合。X证书的职业技能培训不是要独立于专业教学之外再设计一套培养培训体系和课程体系，而是要将其培训内容有机融入学历教育专业人才培养方案。专业课程能涵盖X证书职业技能培训内容的，就不再单独另设X证书培训；专业课程未涵盖的培训内容，则通过职业技能培训模块加以补充、强化和拓展。“X”证书培训过程与学历教育专业教学过程统筹组织、同步实施。由于X证书培训内容与学历教育的专业课程有机融合，因此，“X”证书培训和专业教学可以统筹安排教学内容、实践场所、组织形式、教学时间、安排师资，从而实现X证书培训与专业教学过程的一体化。",
                description5: "“X”证书的职业技能考核与学历教育专业课程考试统筹安排，同步考试与评价。职业技能等级标准与专业教学标准的对应、X证书培训内容与学历教育专业课程的融合、培训过程与专业教学过程的统筹安排，为实现X证书职业技能考核与学历教育专业课程考试的统筹安排、同步考试评价奠定了基础。学历证书与职业技能等级证书体现的学习成果相互转换。获得学历证书的学生在参加相应的职业技能等级证书考试时，可免试部分内容，获得职业技能等级证书的学生，可按规定兑换学历教育的学分，免修相应课程或模块。学历证书与职业技能等级证书的互通互换，为构建国家资历框架奠定了基础。",
                title: "什么是1+X"
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
.submit-text {
    width: 100%;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    margin: 10px 0 10px 0;
}
.dl-container {
  padding-top: 100px;
  padding-bottom: 100px;
}
.dl-text {
  font-size: 36px;
}
.qrcode {
  width: 100%;
  justify-content: space-between;
  display: flex;
  flex-direction: row;

}
.qrcode-div {
  display: flex;
  align-items: center;
  flex-direction: column;
}
.qrcode-img {
  height: 200px;
  width: 200px;
}
.dl-img {
  height: 400px;
  width: 400px;
}
.dl-app {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

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
