<template>
  <div class="shopping-cart-wrap">

    <h3 class="shopping-cart-tit">

      结算中心  共{{ count }}门课程结算
    </h3>
    <div class="row">
      <el-table
        ref="multipleTable"
        :data="account_list"
        tooltip-effect="dark"
        style="width: 100%"
      >
        <el-table-column
          type=""
          width="55">
        </el-table-column>
        <el-table-column
          label="课程"
          width="555">
          <template slot-scope="scope">
            <img :src="scope.row.course_img" alt="">
            <a href="javascript:void(0);">{{ scope.row.name}}</a>
          </template>
        </el-table-column>

        <el-table-column
          prop="address"
          label="原价"
          show-overflow-tooltip>
          <template slot-scope='scope'>
            ¥{{scope.row.default_price}}
          </template>
        </el-table-column>
        <el-table-column
          prop="address"
          label="课程优惠券"
          show-overflow-tooltip>
          <template slot-scope='scope'>

            <select id="" @change="change_coupon(scope.row.id)" placeholder = '请选择优惠券'>
              <option value="coupon" @change="change_coupon">请选择优惠券</option>
              <option  v-for='(item,index) in scope.row.coupon_list' :key='item.pk' :value="item.pk">{{
                item.coupon_type}}
              </option>
            </select>
          </template>
        </el-table-column>

        <el-table-column
          prop="default_price"
          label="折后价"
          show-overflow-tooltip>
          <template slot-scope='scope'>
            ¥{{scope.row.rebate_price}}
          </template>
        </el-table-column>


      </el-table>
    </div>
    <div class="ttt">

      <div class="box">
             <h3>使用通用优惠券</h3>
              <el-select v-model = 'coupons_val' clearable placeholder="请选择" @change = 'change_coupon'>
              <el-option
                v-for="item in global_coupons"
                :key="item.name"
                :label="item.coupon_type"
                :value="item.pk">
              </el-option>
            </el-select>
            <el-checkbox v-model="checked" @change = 'change_coupon'>使用我的贝理</el-checkbox>
      </div>
      <div class="pay_money">
        <span class="title">课程总价格:</span>
        <span class="total">{{total_price}}</span>
      </div>
    </div>
    <div class="ooo">
      <div class="pay_way">
        <span>请选择支付方式:</span>
        <img src="../../assets/pay.png" alt="" width="250" style="vertical-align: middle">
      </div>
      <div class="pay">

        <h2>应付款:{{total_price}}</h2>
        <el-button type="success" size="100px" @click="pay">支付</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Cart',
    data(){
      return {
        account_list: [],
        count: 0,
        global_coupons: [],
        coupons_val:'',
        checked:false,
        obj:{},
        total_price:0,
        course_list:[]

      }
    },
    methods: {
//      支付
      pay(){
        this.account_list.forEach((item,index)=>{
          let obj_account = {}
          console.log("===>",item)
          obj_account['course_id'] = item.id;
          obj_account['default_price_policy_id'] = item.default_price_policy_id;
          obj_account['coupon_record_id'] = parseInt(this.obj[item.id])
          this.course_list.push(obj_account)

        })

        let params = {
            "is_beli":this.checked,
            "course_list":this.course_list,
             "global_coupon_id":this.coupons_val,
             "pay_money":this.total_price
       };
        this.$http.payment(params)
          .then(res=>{
          console.log(res)
          if(res.code === 1000){
          location.href = res.data
        }
        })
      },
//      优惠策略 计算总价格
      change_coupon(course_id){


            if (event.target.value && this.coupons_val==='' && event.target.value !== '0'){
              this.obj[course_id] = parseInt(event.target.value)
            }
            if (this.coupons_val && event.target.value ===0){
                 this.obj["global_coupon_id"] = parseInt(this.coupons_val)

            }
            if(event.target.value && this.coupons_val){

              this.obj[course_id] = parseInt(event.target.value)
            }


            for(let key in  this.obj){
              if (this.obj[key] == 'coupon'){
                delete this.obj[key]
              }
            }

            var params = {
              is_beli:JSON.stringify(this.checked),
              choose_coupons:this.obj
            };
            this.$http.total_price(params)
              .then(res=>{
              console.log(res)
              for(let key in res.data){
                this.account_list.forEach((item,index)=>{
                  if (item.id == key){
                    item.rebate_price = res.data[key]
                  }

                })
              }
              this.total_price=res.data.total_price
            })

      },
      get_account_list(){
        this.$http.account_list()
          .then(res => {
           console.log(res.data)
        this.account_list = res.data.account_course_list
        this.count = res.data.total;



        this.total_price = res.data.total_price;


        this.global_coupons = res.data.global_coupons
      })
      }


    },
    created(){
      this.get_account_list()

    }


  }
  ;
</script>

<style lang="css" scoped>
  .shopping-cart-wrap {
    width: 100%;
  }

  .shopping-cart-wrap h3, .row {
    width: 1200px;
    margin: 0 auto;
  }

  .shopping-cart-wrap h3 {
    padding: 50px 0;
  }

  .el-table .warning-row {
    background: #22C8C5;
  }

  .cell img {
    vertical-align: middle;
    width: 170px;
  }

  .cell a {
    color: #000;
    margin-left: 30px;
  }

  select {
    border: 0;
    outline: none;
    font-size: 12px;
    color: #666;
    line-height: 18px;
    width: 117px;
    height: 28px;
    padding-left: 16px;
    border: 1px solid #d9d9d9;
    border-radius: 4px;
  }

  .total {
    width: 1200px;
    margin: 0 auto;
    /*display: flex;*/
    /*justify-content:flex-end;*/

  }

  .shopping-cart-wrap .total button {
    float: right;
    margin-top: 20px;
  }

  .shopping-cart-wrap .total h3 {
    padding: 0;
    float: right;
    width: 100px;
    height: 30px;
    margin-top: 30px;
  }
  .ttt{
    width: 1200px;
    margin: 0 auto;
    display: flex;
  }
  .ttt .box{
    width: 80%;
  }
  .ttt .pay_money{
    width: 20%;
    padding-top: 120px;
  }
  .pay_money .title{
    /*width: 100px;*/
    height: 40px;
    line-height: 40px;
    font-weight: bold;
    color: darkgrey;
    /*margin-right: 100px;*/
  }
  .ooo{
     width: 1200px;
    margin: 0 auto;
    display: flex;
    margin-top: 80px;
    cursor: pointer;
    justify-content: space-between;
    padding-bottom: 100px;
  }
  .pay_way{
  }
  .pay{
    display: flex;
    margin-right: 100px;
  }
  .pay h2{
    margin-top: 20px;
    margin-right: 20px;
  }


</style>
