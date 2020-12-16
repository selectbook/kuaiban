<template>
  <div class="box">
       <img src="../../../static/images/pay.jpg" alt="" height="100">
       <h3>订单{{$route.query.order_num}}支付成功!</h3>
       <router-link :to="{name:'Home'}">返回首页</router-link>
  </div>
</template>
<script>
  export default {
    name: "PaySuccess",
    data() {
      return {}
    },

    created(){

    },
  }
</script>
<style scoped>
  .box{
    width: 1200px;
    line-height: 100px;
    border-radius: 5px;
    margin: 0px auto;
    text-align: center;
    background-color: white;

  }

  .box img{
    margin-top: 30px;
  }

</style>
