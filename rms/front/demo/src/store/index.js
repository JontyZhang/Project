import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

import systemUser from './modules/system/user'

import button from './button'

import { state, getters, mutations, actions } from './global'

let store = new Vuex.Store({
	actions,
	state,
	getters,
	mutations,
	modules: {
		systemUser,
		button
	}
})

export default store