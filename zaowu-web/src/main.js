// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// elementUI 导入
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//light-timeline
import LightTimeline from 'vue-light-timeline';
Vue.use(LightTimeline);
// 调用插件
Vue.use(ElementUI);
import '../static/global/global.css'
import '../static/global/gt.js'
import 'animate.css'
// 导入axios

import * as api from './restful/api'
Vue.prototype.$http = api;
// 添加请求拦截器


// store的引入
import store from '../src/store'


//调用video控件，2020.03.15 乐
import VideoPlayer from 'vue-video-player'
import 'vue-video-player/src/custom-theme.css'
import 'video.js/dist/video-js.css'
Vue.use(VideoPlayer)

// 全局守卫 
// -- 本地调试需求，暂时关闭，乐 2020.03.14
router.beforeEach((to, from, next) => {
  // console.log(to)
  if (to.meta.requiresAuth) { //表示用户访问该组件需要登录
    if (!localStorage.getItem('token')) {
      next({
        name: 'Login'
      })
    } else {
      let user = {
        token: localStorage.getItem('token'),
        username: localStorage.getItem('username'),
        head_img: localStorage.getItem('head_img'),
      };

      store.dispatch('getUserInfo', user);
      next()
    }
  } else {
    //放行
    next()
  }

  if (localStorage.getItem('token')) {
    // 用户登录过
    let user = {
      token: localStorage.getItem('token'),
      username: localStorage.getItem('username'),
      head_img: localStorage.getItem('head_img'),
    };

    store.dispatch('getUserInfo', user);
  }

  // ...
  //  next();

})


Vue.config.productionTip = false

/* eslint-disable no-new */
let vm = new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

export const validater = {

/**
   * 电话号码校验
   */
  telephoneNumber(rule, value, callback) {
    if (value && (!(/^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/).test(value))) {
      callback(new Error('电话号码不符合规范'))
    } else {
      callback()
    }
  },
  
  /**
   * 邮箱校验
   */
  emailValue(rule, value, callback) {
    let temp = /^[\w.\-]+@(?:[a-z0-9]+(?:-[a-z0-9]+)*\.)+[a-z]{2,3}$/
    let tempOne = /^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/
    if (value && (!(temp).test(value))) {
      callback(new Error('邮箱格式不符合规范'))
    } else {
      callback()
    }
  }
}