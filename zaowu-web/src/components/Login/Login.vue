<template>
  <div class="box">
    <!-- <img src="https://www.luffycity.com/static/img/Loginbg.3377d0c.jpg" alt=""> -->
    <div class="login">
      <!-- <div class="login-title">
        <img src="https://www.luffycity.com/static/img/Logotitle.1ba5466.png" alt="">
        <p>帮助有志向的年轻人通过努力学习获得体面的工作和生活!</p>
      </div> -->
      <div class="login_box">
        <div class="title">
          <span>登录</span>
          <!-- <span>短信登录</span> -->
        </div>
        <div class="inp">
          <input v-model = 'username' type="text"  placeholder="注册手机号码" class="user">
          <input v-model = 'password' type="password" name="" class="pwd" placeholder="密码">
          <!-- <div id="geetest"></div> -->
          <div class="rember">
            <p>
              <input type="checkbox" class="no" name="a"></input>
              <span>记住密码</span>
            </p>
            <p>忘记密码</p>
          </div>
          <button class="login_btn" @click = 'loginHandler'>登录</button>
          <p class="go_login" >没有账号？ <span @click="drawer = true">立即注册</span></p>
          <el-drawer
            :visible.sync="drawer"
            :direction="direction"
            :before-close="handleClose">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="ruleForm">
              <el-form-item label="姓名" prop="name">
                <el-input v-model.number="ruleForm.name"></el-input>
              </el-form-item>
              <el-form-item label="年龄" prop="age">
                <el-input v-model.number="ruleForm.age"></el-input>
              </el-form-item>
              <el-form-item label="联系方式" prop="mobile">
                <el-input v-model.number="ruleForm.mobile"></el-input>
              </el-form-item>
              <el-form-item label="电子邮箱" prop="email">
                <el-input v-model="ruleForm.email"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="pass">
                <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')" :loading="loading">{{loading ? '注册中...' : '提交注册'}}</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-drawer>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Login",
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
          return callback(new Error("手机号不能为空"));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error("请输入正确的手机号"));
          } else {
            callback();
          }
        }, 1000);
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
      var validatePass = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请输入密码"));
        } else {
          if (this.ruleForm.checkPass !== "") {
            this.$refs.ruleForm.validateField("checkPass");
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请再次输入密码"));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error("两次输入密码不一致!"));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          pass: "",
          checkPass: "",
          age: "",
          mobile: "",
          email: "",
          name: ""
        },
        rules: {
          pass: [{ validator: validatePass, trigger: "blur" }],
          checkPass: [{ validator: validatePass2, trigger: "blur" }],
          age: [{ validator: checkAge, trigger: "blur" }],
          mobile: [{ validator: checkMobile, trigger: "blur" }],
          name: [{ validator: checkName, trigger: "blur" }],
          email: [{ validator: checkEmail, trigger: "blur" }]
        },
        loading: false,
        timer: null,
        drawer: false,
        direction: "rtl",
        username: "",
        password: "",
        validateResult: {} //验证成功之后返回的结果，它用于服务端sdk的二次验证
      };
    },

    methods: {
      submitForm(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            this.loading = true;
            this.timer = setTimeout(() => {
              let params = {
                phonenumber: this.ruleForm.mobile,
                phone: this.ruleForm.mobile,
                email: this.ruleForm.email,
                password: this.ruleForm.pass,
                repassword: this.ruleForm.pass,
                username: this.ruleForm.name,
                age: this.ruleForm.age

              };
              this.$http.userRegister(params).then(res => {
                if (res.data.code == 0) {
                  this.$message("注册成功！");
                  this.drawer = false;
                } else if (res.data.code == 11) {
                  this.$message("注册失败，该账户已存在！");
                } else {
                  this.$message("注册失败！");
                }
              })
                .catch(err => {
                  this.$message("注册失败，请检查信息是否正确！");
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
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      handleClose(done) {
        this.$confirm("放弃注册？").then(_ => {
          done();
        }).catch(_ => {});
      },
      loginHandler() {
        if(!this.username){
          this.$message.warning('请输入用户名！')
          return
        }
        if(!this.password){
          this.$message.warning('请输入密码！')
          return
        }
        let params = {
          phone: this.username,
          password: this.password,
          // geetest_challenge: this.validateResult.geetest_challenge,
          // geetest_validate: this.validateResult.geetest_validate,
          // geetest_seccode: this.validateResult.geetest_seccode
        };
        this.$http.userLogin(params).then(res => {
          if (res.data.code === 0) {
            this.$router.go(-1);
            localStorage.setItem("token", res.data.data.token);
            localStorage.setItem("userid", res.data.data.userId);
            localStorage.setItem("username", res.data.data.username);
            localStorage.setItem("head_img", res.data.data.head_img);
            // localStorage.setItem('shop_cart_num',res.data.shop_cart_num);
            let user = {
              token: localStorage.getItem("token"),
              userid: localStorage.getItem("userid"),
              username: localStorage.getItem("username"),
              head_img: localStorage.getItem("head_img")
            };

            // dispacth action的行为
            this.$store.dispatch("getUserInfo", user);
          } else {
            this.$message(res.data.msg);
          }
        })
          .catch(err => {
            console.log(err);
          });
      },
      // getGeetest() {
      //   this.$http
      //     .geetest()
      //     .then(res => {
      //       let data = res.data;
      //       var _this = this;
      //       //请检测data的数据结构， 保证data.gt, data.challenge, data.success有值
      //       initGeetest(
      //         {
      //           // 以下配置参数来自服务端 SDK
      //           gt: data.gt,
      //           challenge: data.challenge,
      //           offline: !data.success,
      //           new_captcha: true,
      //           product: "popup",
      //           width: "100%"
      //         },
      //         captchaObj => {
      //           var result = captchaObj.getValidate();

      //           // 这里可以调用验证实例 captchaObj 的实例方法
      //           captchaObj.appendTo("#geetest"); //将验证按钮插入到宿主页面中captchaBox元素内
      //           captchaObj
      //             .onReady(() => {
      //               //your code
      //             })
      //             .onSuccess(() => {
      //               var result = captchaObj.getValidate();
      //               this.validateResult = result;
      //             })
      //             .onError(() => {});
      //         }
      //       );
      //     })
      //     .catch(err => {
      //       console.log(err);
      //     });
      // }
    },
    created() {
      // this.getGeetest();
    }
  };
</script>

<style lang="css" scoped>
  .ruleForm {
    width: 90%;
  }
  .box {
    width: 100%;
    position: relative;
  }
  .box img {
    width: 100%;
  }
  .box .login {

    width: 500px;
    height: 400px;
    margin: 50px auto
  }
  .login .login-title {
    width: 100%;
    text-align: center;
  }
  .login-title img {
    width: 190px;
    height: auto;
  }
  .login-title p {
    font-family: PingFangSC-Regular;
    font-size: 18px;
    color: #fff;
    letter-spacing: 0.29px;
    padding-top: 10px;
    padding-bottom: 50px;
  }
  .login_box {
    width: 400px;
    height: auto;
    background: #fff;
    box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.5);
    border-radius: 4px;
    margin: 0 auto;
    padding-bottom: 40px;
  }
  .login_box .title {
    font-size: 20px;
    color: #9b9b9b;
    letter-spacing: 0.32px;
    border-bottom: 1px solid #e6e6e6;
    display: flex;
    justify-content: space-around;
    padding: 50px 60px 0 60px;
    margin-bottom: 20px;
    cursor: pointer;
  }
  .login_box .title span:nth-of-type(1) {
    color: #4a4a4a;
    border-bottom: 2px solid #84cc39;
  }

  .inp {
    width: 350px;
    margin: 0 auto;
  }
  .inp input {
    border: 0;
    outline: 0;
    width: 100%;
    height: 45px;
    border-radius: 4px;
    border: 1px solid #d9d9d9;
    text-indent: 20px;
    font-size: 14px;
    background: #fff !important;
  }
  .inp input.user {
    margin-bottom: 16px;
  }
  .inp .rember {
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;
    margin-top: 10px;
  }
  .inp .rember p:first-of-type {
    font-size: 12px;
    color: #4a4a4a;
    letter-spacing: 0.19px;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-align: center;
    align-items: center;
    /*position: relative;*/
  }
  .inp .rember p:nth-of-type(2) {
    font-size: 14px;
    color: #9b9b9b;
    letter-spacing: 0.19px;
    cursor: pointer;
  }

  .inp .rember input {
    outline: 0;
    width: 16px;
    height:16px;
    margin-right: 5px;
    border-radius: 4px;
    border: 1px solid #d9d9d9;
    text-indent: 20px;
    font-size: 14px;
    background: #fff !important;
  }

  .inp .rember p span {
    display: inline-block;
    font-size: 12px;
    width: 100px;
    /*position: absolute;*/
    /*left: 20px;*/
  }
  #geetest {
    margin-top: 20px;
  }
  .login_btn {
    width: 100%;
    height: 45px;
    background: #84cc39;
    border-radius: 5px;
    font-size: 16px;
    color: #fff;
    letter-spacing: 0.26px;
    margin-top: 30px;
  }
  .inp .go_login {
    text-align: center;
    font-size: 14px;
    color: #9b9b9b;
    letter-spacing: 0.26px;
    padding-top: 20px;
  }
  .inp .go_login span {
    color: #84cc39;
    cursor: pointer;
  }
</style>
