import Vue from 'vue'
import Router from 'vue-router'
// @ 绝对路径 检索到 .....src/

// 如果我们Router当做局部模块使用 一定要Vue.use(Router)
// 以后在组件中  可以通过this.$router  获取Router实例化对象
// 路由信息对象 this.$routes
import Home from '@/components/Home/Home'
import QX from '@/components/Home/QX'
import Course from '@/components/Course/Course'
import LightCourse from '@/components/LightCourse/LightCourse'
import Micro from '@/components/Micro/Micro'
import CourseDetail from '@/components/Course/CourseDetail'

import CourseSubject from '@/components/Course/Subject'

import Login from '@/components/Login/Login'
import Cart from '@/components/Cart/Cart'
import Account from '@/components/Cart/Account'
import MyOrder from '@/components/Order/MyOrder'
import PaySuccess from '@/components/Order/PaySuccess'
import User from '@/components/User/User'
import StudyHour from '@/components/User/StudyHour'
import Assessment from '@/components/User/Assessment'
import GradeExam from '@/components/User/GradeExam'
import GradeExperiment from '@/components/User/GradeExperiment'
import MyCert from '@/components/User/MyCert'
import AnalyzeReport from '@/components/User/AnalyzeReport'
import AnalyzeTalent from '@/components/User/AnalyzeTalent'
import Subject from '@/components/Course/Subject'

import CourseAI from '@/components/Course/CourseAI'
import CourseBC from '@/components/Course/CourseBC'
import CourseIOT from '@/components/Course/CourseIOT'
import CourseRobot from '@/components/Course/CourseRobot'
import AIData from '@/components/Course/AIData'
import VocationalEx from '@/components/Course/VocationalEx'
import VocationalSkillsEx from '@/components/Course/VocationalSkillsEx'
Vue.use(Router)

// 配置路由规则
export default new Router({
   linkActiveClass:'is-active',
   mode: 'history',//改成history模式
  routes: [
    // {
    //   path: '/',
    //   redirect:'/home'
    //   // component: HelloWorld
    // },
    {
      path: '/aboutAs',
      name:'QX',
      component:QX
    },
    {
    	path:"/",
    	name:'Home',
      redirect:'/index',
    	component:Home
    },
    {
      path:"/index",
      name:'Home',
      component:Home
    },
    {
    	path:"/courses",
    	name:'Course',
    	component:Course
    },
    {
    	path:"/home/light-course",
    	name:'LightCourse',
    	component:LightCourse
    },
    {
    	path:"/micro",
    	name:'Micro',
    	component:Micro
    },
    // 课程详情
    {
      path:"/course/detail/web/:detailId",
      name:'course.detail',
      component:CourseDetail
    },
    // 课程详情(gai)
    {
      path:"/course/subject:detailId",
      name:'subject',
      component:CourseSubject,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:'/login',
      name:'Login',
      component:Login
    },
    // 购物车的页面
    {
      path:"/purchase/shopping_cart",
      name:'purchase.shop',
      component:Cart,
      meta: {
        requiresAuth: true
      }
    },
    {
     path:'/buy',
      name:'account',
      component:Account,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:'/my/order',
      name:'my_order',
      component:MyOrder,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:'/order/pay_success',
      name:'pay_success',
      component:PaySuccess,
      meta: {
        requiresAuth: true
      }
    },
    // 乐 2020.03.15
    {
      path:'/blockchain',
      name:'CourseBC',
      component:CourseBC,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:'/robot',
      name:'CourseRobot',
      component:CourseRobot,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:'/AI',
      name:'CourseAI',
      component:CourseAI,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:'/AIData',
      name:'AIData',
      component:AIData,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:'/VocationalEx',
      name:'VocationalEx',
      component:VocationalEx,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:'/VocationalSkillsEx',
      name:'VocationalSkillsEx',
      component: VocationalSkillsEx,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:'/IOT',
      name:'CourseIOT',
      component:CourseIOT,
      meta: {
        requiresAuth: true
      }
    },
    // 账号管理页 
    {
      path:'/user',
      name:'user',
      component:User,
      meta:{
        requiresAuth:true
      }
    }, 
    // 学习时长页
    {
      path:'/studyHour',
      name:'studyHour',
      component:StudyHour,
      meta:{
        requiresAuth:true
      }
    }, 
    // 学习评估页
    {
      path:'/assessment',
      name:'assessment',
      component:Assessment,
      meta:{
        requiresAuth:true
      }
    },
    // 笔试成绩页
    {
      path:'/gradeExam',
      name:'gradeExam',
      component:GradeExam,
      meta:{
        requiresAuth:true
      }
    },
    // 实验成绩页 
    {
      path:'/gradeExperiment',
      name:'gradeExperiment',
      component:GradeExperiment,
      meta:{
        requiresAuth:true
      }
    }, 
    // 我的证页面
    {
      path:'/myCert',
      name:'myCert',
      component:MyCert,
      meta:{
        requiresAuth:true
      }
    },
    // 智能报表页
    {
      path:'/analyzeReport',
      name:'analyzeReport',
      component:AnalyzeReport,
      meta:{
        requiresAuth:true
      }
    },
    // 人才分析页
    {
      path:'/analyzeTalent',
      name:'analyzeTalent',
      component:AnalyzeTalent,
      meta:{
        requiresAuth:true
      }
    },
    // 课程页
    {
      path:'/subject',
      name:'subject',
      component:Subject,
      meta:{
        requiresAuth:true
      }
    }
  ]
})
