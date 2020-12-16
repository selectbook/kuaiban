<template>
  <!-- element-ui -->
  <el-header style='width:100%;height:80px;' >
    <div class="header">
      <router-link to="/" class="nav-left" tag="div">
        <el-image style="margin-left:10px; margin-top:10px;width: 60px; height: 60px" src="../../../static/images/logo.jpg"></el-image>
      </router-link>
      <div class="nav-center">
        <ul>
          <li v-for = '(list) in headerList' :key = 'list.id'>
            <router-link :to = 'list.path'>
              {{list.title}}
            </router-link>
          </li>
          <li>
            <el-dropdown>
                          <span class="el-dropdown-link nav-title">
                            职业考试<i class="el-icon-arrow-down el-icon--right"></i>
                          </span>
              <el-dropdown-menu slot="dropdown">
                <router-link :to = "{name:'VocationalEx'}">
                  <el-dropdown-item class="">
                    1+X考试
                  </el-dropdown-item>
                </router-link>
                <router-link :to = "{name:'VocationalSkillsEx'}">
                  <el-dropdown-item class="">
                    职业技能考试
                  </el-dropdown-item>
                </router-link>
              </el-dropdown-menu>
            </el-dropdown>
          </li>
          <li>
            <router-link :to = "{name:'QX'}">
              关于我们
            </router-link>
          </li>
        </ul>
      </div>

      <!-- <el-dropdown> -->

      <div class="nav-right " v-if = 'userInfo.token' @mouseenter = 'isShow = true' @mouseleave ='isShow = false'>
        <span class="user" @click='user'>{{userInfo.username}}</span>
        <!-- <img :src="userInfo.head_img" alt=""> -->
        <ul class="my_account" v-show = 'isShow'>
          <li @click = 'goPerson'>
           我的
          </li>
          <li @click="dialogVisible=true">
            修改密码
          </li>
          <li @click = 'logout'>
            退出
          </li>
        </ul>
      </div>

      <div class="nav-right" v-else>
        <span @click = 'loginHandler'>登录</span>
      </div>
    </div>
    <el-dialog title="修改密码" :visible.sync="dialogVisible" width="480px">
      <el-form ref="editForm" :model="editForm" label-width="80px">
        <el-form-item label="密码">
          <el-input v-model="editForm.password" maxlength="20"></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="editForm.conPassword" maxlength="20"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editFormButton">确 定</el-button>
      </div>
    </el-dialog>
  </el-header>
</template>

<script>
  export default {
    name: 'LuffyHeader',
    data(){
      return {
        dialogVisible:false,
        headerList:[
          {id:'1',path:'/index',title:'首页'},
          {id:'2',path:'/robot',title:'机器人工程技术'},
          {id:'3',path:'/IOT',title:'物联网工程技术'},
          {id:'4',path:'/blockchain',title:'区块链工程技术'},
          {id:'5',path:'/AI',title:'人工智能工程技术'},
          {id:'6',path:'/AIData',title:'AI数据科学'},
          // {id:'7',name:'QX',title:'关于我们'},
        ],
        isShow:false,
        editForm:{
          password:'',
          conPassword:''
        }
      }
    },
    methods:{
      editFormButton(){
        if(this.editForm.password.length<6){
          this.$message.error("密码长度不小于6位");
          return
        }
        if(this.editForm.password!==this.editForm.conPassword){
          this.$message.error("两次输入密码不一致！！");
          return
        }
        let params={
          userID:Number(localStorage.getItem('userid')),
          password: this.editForm.password
        }
        this.$http.updatePass(params).then(res => {
          if (res.data.code === 0) {
            this.$message('密码重置成功,请重新登录');
            this.dialogVisible = false;
            setTimeout(()=>{
              localStorage.clear();
              this.$store.dispatch('accountLogout');
              this.$router.push({
                name:'Login'
              })
            },1000)
          }else{
            this.$message(res.data.msg);
          }
        }).catch(err => {
          this.$message("报名失败！，请检查信息是否正确！");
        });
      },
      user(){
        this.$router.push({
          // 个人主页跳转暂时取消，乐 2020.04.13
          // name:"user"
        })
      },
      goPerson(){
        this.$router.push({
          path:'/user'
        })
      },
      loginHandler(){
        this.$router.push({
          name:'Login'
        })
      },
      logout(){
        localStorage.clear();
        this.$store.dispatch('accountLogout');
        this.isShow = false;
        this.$router.push({
          name:'Home'
        })
      },
    },
    computed:{
      userInfo(){
        return this.$store.state.userInfo;
      }
    }
  };
</script>

<style lang="css" scoped>
  .nav-title {
    color: #4a4a4a;
    font-size: 16px;
    font-family: PingFangSC-Lishg;
    width: 100%;
    height: 60px;
    display: inline-block;
  }
  .el-header{
    border-bottom: #c9c9c9;
    box-shadow: 0 0.5px 0.5px 0 #c9c9c9;
  }
  .header{
    min-width:1200px;
    max-width: 1500px;
    height: 80px;
    line-height: 80px;
    margin: 0 auto;
    font-size: 16px;
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
  }
  .nav-left{
    float: left;height: 80px;;
  }
  .nav-center{
    float: left;
    margin-left:3%;
    width: calc(97% - 200px);
  }
  .nav-center ul{
    overflow: hidden;
    height: 80px;
    line-height: 80px;
    display: flex;justify-content: space-between
  }
  .nav-center  ul li{
    height: 80px;
    text-align: center;
  }
  .nav-center ul li a{
    color: #4a4a4a;
    width: 100%;
    height:80px;
    box-sizing: border-box;
    display: inline-block;
  }
  .nav-center ul li a:hover{
    color: #B3B3B3;
  }
  .nav-center ul li a.is-active{
    color: #4a4a4a;
    border-bottom: 4px solid #ffc210;
  }
  .nav-right{
    float: right;
    position: relative;
    z-index: 100;
  }
  .nav-right span{
    cursor: pointer;
  }
  .nav-right .user{
    margin-left: 15px;
    color: #409EFF;
  }
  .nav-right img{
    width: 26px;
    height: 26px;
    border-radius: 50%;
    display: inline-block;
    vertical-align: middle;
    margin-left: 15px;
  }
  .nav-right  ul{
    position: absolute;
    width: 221px;
    z-index: 100;
    font-size: 12px;
    top: 80px;
    background: #fff;
    border-top: 2px solid #d0d0d0;
    box-shadow: 0 2px 4px 0 #e8e8e8;
  }
  .nav-right ul li{
    height: 40px;
    color: #4a4a4a;
    padding-left: 30px;
    padding-right: 20px;
    font-size: 12px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    cursor: pointer;
    transition: all .2s linear;
  }
  .nav-right ul li span.msg{
    margin-left: -80px;
    color: red;
  }
  .nav-right ul li span.count{
    margin-left: -100px;
    color: red;
  }
  .expand-enter-active {
    transition: all 3s ease;
    height: 50px;
    overflow: hidden;
  }
  .expand-leave-active{
    transition: all 3s ease;
    height: 0px;
    overflow: hidden;
  }
  .expand-enter, .expand-leave {
    height: 0;
    opacity: 0;
  }
</style>
