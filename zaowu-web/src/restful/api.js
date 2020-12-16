
import Axios  from 'axios'
import Vue from 'vue'
import qs from 'qs'
let  vm = new Vue();
// 设置公共的url
Axios.defaults.baseURL = 'https://www.luffycity.com/api/v1/';

Axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    if (localStorage.getItem('token')) {
    	// Axios.defaults.headers.common['Authorization'] = localStorage.getItem('access_token');
    	// console.log(config.headers);
    	config.headers.Authorization = localStorage.getItem('token')
    }
    // 更改加载的样式
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });


// 分类列表api
export const categoryList =  ()=> {
	return Axios.get('http://116.62.102.70:8000/course/category/').then(res=>res.data);


};
export const  courseList = (categoryId)=>{
  return Axios.get(`http://116.62.102.70:8000/courses/?category_id=${categoryId}`).then(res=>res.data);
};
// 课程概述，暂时没用。
export const coursedetail = (courseid)=>{
	return Axios.get(`http://116.62.102.70:8000/coursedetail/${courseid}/`).then(res=>res);
};

//获取课程视频列表
export const getVedio = (courseId) =>{
  return Axios.get(`http://116.62.102.70:8000/coursechapter?course=${courseId}`).then(res=>res);
};

//获取课程大纲
export const getOutline = (courseName) =>{
  return Axios.get(`http://116.62.102.70:8000/knowledge?category=${courseName}`).then(res=>res);
};

//获取课程介绍
export const getIntro = (courseName) =>{
  return Axios.get(`http://116.62.102.70:8000/description?category=${courseName}`).then(res=>res);
};

//获取课程流程
export const getFlow = (courseName) =>{
  return Axios.get(`http://116.62.102.70:8000/categoryoutline?category=${courseName}`).then(res=>res);
};

//获取课程评论
export const getComments = (courseId) =>{
  return Axios.get(`http://116.62.102.70:8000/comment?course=${courseId}`).then(res=>res);
};

//发表课程评论
export const postComments = (params) =>{
  return Axios.post(`http://116.62.102.70:8000/comment`,params).then(res=>res);
};

//获取视频链接
export const getChapter = (courseId) =>{
  return Axios.get(`http://116.62.102.70:8000/coursechapter?course=${courseId}`).then(res=>res);
};

// geetest接口
export const geetest = ()=>{
	return Axios.get(`http://116.62.102.70:8000/api/captcha_check/`).then(res=>res.data);
};

// 登录
export const  userLogin = (params)=>{
	// 这个参数至少有五个字段 username password  验证的三个字段
	// return Axios.post('http://116.62.102.70:8000/login/',params).then(res=>res);
	return Axios.post('http://39.101.161.152:8081/api/auth/login',params).then(res=>res);
};

//注册
export const  userRegister = (params)=>{
	// 这个参数至少有五个字段 username password  验证的三个字段
	return Axios.post('http://39.101.161.152:8081/api/auth/regist',params).then(res=>res);
};

// 加入购物车的接口
export const shoppingCart = (params)=>{
	return Axios.post('http://116.62.102.70:8000/shoppingcar/',params).then(res=>res.data);
};
// 购物车的数据
export const shoppingcar = ()=>{
	return Axios.get(`http://116.62.102.70:8000/shoppingcar/`).then(res=>res.data);
};
// 退出
export const logout = ()=>{
	return Axios.delete(`http://116.62.102.70:8000/logout/`).then(res=>res.data);

};
//移除课程
export const removeCourse = (params)=>{
	return Axios.delete(`http://116.62.102.70:8000/shoppingcar/`,{data:params}).then(res=>res);
};
//添加计算
export const account = (params)=>{

  return Axios.post(`http://116.62.102.70:8000/account/`,params).then(res=>res);
};
//结算列表
export const account_list = ()=>{

  return Axios.get(`http://116.62.102.70:8000/account/`).then(res=>res.data);
};

//总价格计算
export const total_price = (params)=>{
  return Axios.put(`http://116.62.102.70:8000/account/`,params).then(res=>res.data);
};
//支付
export const payment = (params)=>{

  return Axios.post(`http://116.62.102.70:8000/payment/`,params).then(res=>res.data);
};

//我的订单
export const myOrderList = ()=>{
   return Axios.get(`http://116.62.102.70:8000/myorder/`).then(res=>res.data);

};
//去支付
export const go_pay = (order_number,final_price)=>{
  return Axios.get(`http://116.62.102.70:8000/get_pay_url/?order_number=${order_number}&final_price=${final_price}`).then(res=>res.data);
};

// 报名
export const  examRegist = (params)=>{
	return Axios.post('http://39.101.161.152:8081/api/auth/examRegist',params).then(res=>res);
};
// 报名信息
export const  examRegistInfo = (params)=>{
  return Axios.post('http://39.101.161.152:8081/api/auth/examRegistInfo',params).then(res=>res);
};
// 用户信息
export const  userInfo = (params)=>{
  return Axios.post('http://39.101.161.152:8081/api/auth/userInfo',params).then(res=>res);
};
// 修改密码
export const  updatePass = (params)=>{
  return Axios.post('http://39.101.161.152:8081/api/auth/updatePass',params).then(res=>res);
};
