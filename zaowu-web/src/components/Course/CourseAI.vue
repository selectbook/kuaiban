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
              <img :src="item.image" alt="" class="introImg" />
            </div>
          </ul>
          <div class="intro">
            <p class="introTitle">考核安排</p>
            <div class="courseList">
            <el-steps :active="5" align-center style="margin-top:3%;margin-bottom:3%; background: #f3f3f3" class="intro">
              <el-step title="理论考试"></el-step>
              <el-step title="实操考试"></el-step>
              <el-step title="综合评审"></el-step>
              <el-step title="证书发放"></el-step>
            </el-steps>
            </div>
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
        <!-- <el-tab-pane class="tab" label="培训流程">
          <el-table
            border
            :data="tableFlow"
            :span-method="cellMerge2"
            style="width: 100%; margin-top: 20px; overflow:auto"
          >
            <el-table-column prop="course" label="课程模块" width="400">
            </el-table-column>
            <el-table-column prop="outline" label="课程大纲"> </el-table-column>
            <el-table-column prop="period" label="学时安排"> </el-table-column>
          </el-table>
        </el-tab-pane> -->
        <el-tab-pane class="tab" label="报名课程">
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
              <el-input v-model="applyForm.name"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
              <el-input v-model.number="applyForm.age"></el-input>
            </el-form-item>
            <el-form-item label="联系手机" prop="tel">
              <el-input v-model.number="applyForm.tel"></el-input>
            </el-form-item>
            <el-form-item label="联系邮箱" prop="email">
              <el-input v-model="applyForm.email"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button
                style="margin-left:45%"
                type="primary"
                @click="apply('applyForm')"
                :loading="loading"
                >{{ loading ? "提交中..." : "提交报名申请" }}</el-button
              >
            </el-form-item>
          </el-form>
          </div>
        </el-tab-pane>
        <el-tab-pane class="tab" label="课程学习">
          <div class="courseList">
            <div
              class="detail"
              v-for="course in courseDetail"
              :key="course.id"
              @click="detailHandler(course.id)"
            >
              <div class="head">
                <img :src="course.course_img" alt="" class="backImg" />
                <b class="mask" :style="{ background: course.bgColor }"></b>
                <p>{{ course.name }}</p>
              </div>
              <div class="content">
                <p>{{ course.brief }}</p>
                <div class="content-detail">
                  <div>
                    <img
                      src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iMTFweCIgaGVpZ2h0PSIxMnB4IiB2aWV3Qm94PSIwIDAgMTEgMTIiIHZlcnNpb249IjEuMSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayI+CiAgICA8IS0tIEdlbmVyYXRvcjogU2tldGNoIDQ4LjIgKDQ3MzI3KSAtIGh0dHA6Ly93d3cuYm9oZW1pYW5jb2RpbmcuY29tL3NrZXRjaCAtLT4KICAgIDx0aXRsZT5TaGFwZTwvdGl0bGU+CiAgICA8ZGVzYz5DcmVhdGVkIHdpdGggU2tldGNoLjwvZGVzYz4KICAgIDxkZWZzPjwvZGVmcz4KICAgIDxnIGlkPSJSZWN0YW5nbGUtMiIgc3Ryb2tlPSJub25lIiBzdHJva2Utd2lkdGg9IjEiIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTE3LjAwMDAwMCwgLTkxLjAwMDAwMCkiPgogICAgICAgIDxnIGlkPSLkuKrkurrkuK3lv4MiIHRyYW5zZm9ybT0idHJhbnNsYXRlKDE3LjAwMDAwMCwgOTEuMDAwMDAwKSIgZmlsbD0iIzlCOUI5QiIgZmlsbC1ydWxlPSJub256ZXJvIj4KICAgICAgICAgICAgPHBhdGggZD0iTTYuNzU4MjMwODEsNi42MjU2MzEwOCBMNC4yNDEzMzM5Miw2LjYyNTYzMTA4IEMxLjkwMjMxNTc1LDYuNjI1NjMxMDggMCw4LjQxNDM0Nzg0IDAsMTAuNjEzNjY4OSBMMCwxMC44NTAyOTE3IEMwLDEyIDEuODcxODkwNjUsMTIgNC4yNDEzMzM5MiwxMiBMNi43NTgyNDUzMiwxMiBDOS4wMzQ0NTQ2MSwxMiAxMSwxMiAxMSwxMC44NTAyOTE3IEwxMSwxMC42MTM2Njg5IEMxMSw4LjQxNDc3MDM2IDkuMDk3MTkwOTUsNi42MjU2MzEwOCA2Ljc1ODIzMDgxLDYuNjI1NjMxMDggWiBNNS4zNzQwNDEyNiw2LjMyMTI0MjAyIEM3LjIyNjIxNDM2LDYuMzIxMjQyMDIgOC43MzMxOTI0Nyw0LjkwMzU2Mjg4IDguNzMzMTkyNDcsMy4xNjA2Mjc4MiBDOC43MzMxOTI0NywxLjQxNzc3NDU0IDcuMjI2MjE0MzYsMCA1LjM3NDA0MTI2LDAgQzMuNTIxOTI2MiwwIDIuMDE0ODAzLDEuNDE4MDc0MzkgMi4wMTQ4MDMsMy4xNjA2OTU5NyBDMi4wMTQ4MDMsNC45MDMxODEyNSAzLjUyMTkyNjIsNi4zMjEyNDIwMiA1LjM3NDA0MTI2LDYuMzIxMjQyMDIgWiIgaWQ9IlNoYXBlIj48L3BhdGg+CiAgICAgICAgPC9nPgogICAgPC9nPgo8L3N2Zz4K"
                      alt=""
                    />
                    <span>{{ course.people_but }}</span>
                    <span>{{ course.level }}</span>
                    <span class="span3" v-if="course.is_free">
                      <span class="s">{{ course.origin_price }}</span>
                      <span class="t">会员免费</span>
                    </span>
                    <span class="span4" v-else>{{ course.price }}</span>
                  </div>
                </div>
              </div>
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
    return {
      loading: false,
      timer: null,
      labelPosition: "right",
      applyForm: {
        name: "",
        age: "",
        tel: "",
        email: ""
      },
      rules: {
        name: [{ validator: checkName, trigger: "blur" }],
        age: [{ validator: checkAge, trigger: "blur" }],
        tel: [{ validator: checkMobile, trigger: "blur" }
        ],
        email: [{ validator: checkEmail, trigger: "blur" }]
      },
      tableFlow: [
      ],
      categoryList: [], // 分类列表
      currentIndex: 0, //分类列表选中
      categoryId: 0, //获取所有的课程列表的id
      courseDetail: [
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
      intro: []
    };
  },
  methods: {
    apply(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.$message("申请成功！");
          this.loading = false;
        }
        //   this.timer = setTimeout(() => {
        //     let params = {
        //       mobile: this.ruleForm.mobile,
        //       email: this.ruleForm.email,
        //       password: this.ruleForm.pass,
        //       name: this.ruleForm.name,
        //       age: this.ruleForm.age
        //     };
        //     this.$http
        //       .userRegister(params)
        //       .then(res => {
        //         if (res.data.code == 1000) {
        //           this.$message("注册成功！");
        //           this.drawer = false;
        //         } else if (res.data.code == 500) {
        //           this.$message("注册失败，该账户已存在！");
        //         } else {
        //           this.$message("注册失败！");
        //         }
        //       })
        //       .catch(err => {
        //         this.$message("注册失败，请检查信息是否正确！");
        //       });
        //     setTimeout(() => {
        //       this.loading = false;
        //     }, 400);
        //   }, 2000);
        // } else {
        //   console.log("error submit!!");
        //   return false;
        // }
      });
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
      this.$http
        .getOutline("AI")
        .then(res => {
          if (!res.code) {
            this.tableOutline = res.data.data;
            this.getSpanArr(this.tableOutline);
          }
        })
        .catch(err => {});
    }, // 获取介绍
    getIntro() {
      this.$http
        .getIntro("AI")
        .then(res => {
          if (!res.code) {
            this.intro = res.data.data;
          }
        })
        .catch(err => {});
    },
    // 获取全部的课程列表
    courseList() {
      this.$http
        .courseList("AI")
        .then(res => {
          if (!res.code) {
            this.categoryList = res.data;
            this.courseDetail = res.data;
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
    this.courseList();

    this.getOutline();
    this.getSpanArr2(this.tableFlow);
    this.getIntro();
  }
};
</script>

<style lang="css" scoped>
.intro {
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
  width: 100%;
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
