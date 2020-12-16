import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

let store = new Vuex.Store({
	// 三大将
	state:{
		userInfo:{}
	},
	// 修改state的唯一方法 是提交mutations
	mutations:{
		getUserInfo(state,user){
			state.userInfo = user;
			console.log(state.userInfo);
		},
		accountLogout(state){
			state.userInfo = {}
		}
	},
	actions:{
		getUserInfo({commit},user){
			commit('getUserInfo',user);
		},
		accountLogout({commit}){
			commit('accountLogout')
		}
	}
});
export default store;