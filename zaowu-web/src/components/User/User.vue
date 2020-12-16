<template>
  <div>
    <el-container style="margin-left: 150px; margin-right:150px">
      <el-container>
        <el-aside width="200px" style="margin-right:50px">
          <el-col>
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b">
              <el-menu-item index="1" @click="user">
                <i class="el-icon-setting"></i>
                <span slot="title">账号管理</span>
              </el-menu-item>
              <el-submenu index="2">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <span>学习情况</span>
                </template>
                  <el-menu-item index="2-1" @click="studyHour">学习时长</el-menu-item>
                  <el-menu-item index="2-2" @click="assessment">学习评估</el-menu-item>
                  <el-submenu index="2-3">
                    <span slot="title">课程成绩</span>
                    <el-menu-item index="2-3-1" @click="gradeExam">理论成绩</el-menu-item>
                    <el-menu-item index="2-3-2" @click="gradeExperiment">实验成绩</el-menu-item>
                  </el-submenu>
              </el-submenu>
              <el-menu-item index="3" @click="myCert">
                <i class="el-icon-document"></i>
                <span slot="title">报名信息</span>
              </el-menu-item>
              <el-submenu index="4">
                <template slot="title">
                  <i class="el-icon-location"></i>
                  <span>智能分析</span>
                </template>
                    <el-menu-item index="4-1" @click="analyzeReport">大数据智能报表</el-menu-item>
                    <el-menu-item index="4-2" @click="analyzeTalent">地区/行业人才分析</el-menu-item>
              </el-submenu>
            </el-menu>
          </el-col>
        </el-aside>
          <el-main style="margin-top: 50px" >
            <template>
              <el-row class="demo-avatar demo-basic" type="flex" justify="center">
                <el-col :span="12">
                  <div class="demo-basic--circle" align="center">
                    <div class="block"><el-avatar :size="150" :src="avatarURL"></el-avatar></div>
                  </div>
                  <div align="center" style="margin-top: 10px">
                  <span style="font-family:'Helvetica Neue'; font-size: 30px" >{{form.userName}}</span>
                  </div>
                </el-col>
              </el-row>
                <el-divider></el-divider>
            </template>
            <el-row type="flex" justify="center" class="row-bg">
              <el-form ref="form" :model="form">
                <el-form-item label="姓名">
                  <el-input v-model="form.userName"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                  <el-input v-model="form.sex==='0'?'男':'女'"></el-input>
                </el-form-item>
                <el-form-item label="身份证号">
                  <el-input v-model="form.idcard"></el-input>
                </el-form-item>
                <el-form-item label="绑定手机">
                  <el-input v-model="form.phonenumber"></el-input>
                </el-form-item>
                <el-form-item label="绑定邮箱">
                  <el-input v-model="form.email"></el-input>
                </el-form-item>
              </el-form>
            </el-row>
            <!--<el-row type="flex" justify="center">
             <el-button type="primary" icon="el-icon-edit" round>修改资料</el-button>  
            </el-row>-->
          </el-main>
      </el-container>
    </el-container>

  </div>
</template>
<script>
// import avater from 'vue-avatar-component'
  export default {
    name: "User",
    data() {
      return {
        form: {
          userName:localStorage.getItem('username')
        },
        avatarURL:'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
      }
    },
    mounted(){
      this.getUser()
    },
    methods: {
      getUser(){
        let params={
          userName:localStorage.getItem('username')
        }
        this.$http.userInfo(params).then(res => {
          if (res.data.code === 0) {
            this.form = res.data.data
          }else{
            this.$message(res.data.msg);
          }
        }).catch(err => {
          this.$message("报名失败！，请检查信息是否正确！");
        });
      },
      user(){
        this.$router.push({
          name:"user"
        })
      },
      studyHour(){
        this.$router.push({
          name:"studyHour"
        })
      },
      assessment(){
        this.$router.push({
          name:"assessment"
        })
      },
      gradeExam(){
        this.$router.push({
          name:"gradeExam"
        })
      },
      gradeExperiment(){
        this.$router.push({
          name:"gradeExperiment"
        })
      },
      myCert(){
        this.$router.push({
          name:"myCert"
        })
      },
      analyzeReport(){
        this.$router.push({
          name:"analyzeReport"
        })
      },
      analyzeTalent(){
        this.$router.push({
          name:"analyzeTalent"
        })
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
    }
  }
</script>
<style scoped>
  .box>ul {
    display: flex;
    justify-content: center;
  }
  .order_num p{
    font-size:16px;
    font-weight: 200;
    color: #7f7f7f;
  }

  .order_info>ul {
    display: flex;
    justify-content: center;
  }

  .order_info {
    width: 1200px;
    margin: 0 auto
  }

  .order_info > ul {
    width: 100%;
    height: 60px;
    line-height: 60px;
    display: flex;
    justify-content: space-between;
    font-weight: 800;
    font-size: 14px;
    color: #909399;;
  }

  .order_info > ul > li:first-child {
    width: 400px;

  }


  .item ul li {
    margin-top: 40px;
  }
  .price{
    color: red;
  }
  .original{
    text-decoration: line-through;
  }
  .item{
    text-align: center;
  }
  #first{
    text-align: left;
  }
</style>
