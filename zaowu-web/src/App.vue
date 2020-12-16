<template>
  <div id="app">
    <!-- 导航区域 -->
    <LuffyHeader />
    <!-- <transition name="fade"> -->
    <router-view />
    <!-- </transition> -->

      <el-divider></el-divider>
      <footer style="text-align:center">
        <p>机器人工程 | 物联网工程技术 | 区块链工程技术 | 人工智能工程技术</p>
        <p>版权所有：深圳中科启秀智慧教育科技有限公司 | 备案：粤ICP备20013639号</p>
        <p>地址：深圳市南山区西丽深圳大学城学苑大道1068号 联系我们：0755-86533594</p>
      </footer>
  </div>
</template>

<script>
import LuffyHeader from "@/components/Common/LuffyHeader";
export default {
  name: "App",
  data() {
    return {};
  },
  components: {
    LuffyHeader
  }
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
</style>
