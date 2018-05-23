/**
    文件：src/main.vue
    作者：吴彬
    时间：2017-5-20
    描述：应用程序入口
*/
import Vue from 'vue'
import store from './store'
import router from './router'
import 'filters/index'
import api from 'conf/api/index'
import backend from 'utils/backend'
import utils from 'utils/common/index'

// 饿了么UI组件
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
Vue.use(ElementUI)

let echarts = require('echarts')
import laydate from 'lib/laydate/laydate.js'
require('imports?define=>false&exports=>false!lib/laydate/theme/default/laydate.css')

// import TydicGaUI from 'tydic-ga-ui'
// Vue.use(TydicGaUI)

import App from './app'
Object.defineProperty(Vue.prototype, '$api', {
	get () {
		return api
	}
})

Object.defineProperty(Vue.prototype, '$laydate', {
	get () {
		return laydate
	}
})

Object.defineProperty(Vue.prototype, '$backend', {
	get () {
		return backend
	}
})
Object.defineProperty(Vue.prototype, '$echarts', {
	get () {
		return echarts
	}
})
Object.defineProperty(Vue.prototype, '$utils', {
	get () {
		return utils
	}
})

Vue.directive('focus', {
	inserted: function (el) {
		el.focus()
	}
})

new Vue({
	el: '#app',
	store,
	router,
	render: createElement => {
		return createElement(App)
	}
})