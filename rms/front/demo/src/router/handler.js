import store from '../store'
import { basedata } from 'conf/basedata'

export default function (router) {
    router.beforeEach((to, from, next) => {
        try {
            beforeRouteEnter(to, from, next)
        } catch (e) {
            console.error(e)
        }
    })
}

// 是否已经加载
let isLoad = true

// 过滤的路由name
let filters = [ 'login', 'mainList', 'error', '500', '400', 'imformation' ]

// 过滤的路由map
let filterMap = new Map()
filters.forEach(item => {
	filterMap.set(item, true)
})

/**
 * 路由跳转之前
 */
function beforeRouteEnter (to, from, next) {
	document.body.scrollTop = 0
	// 适应第三方应用跳转到某个路由
	if (!isLoad) {
		isLoad = true
		if (typeof (to.query.redirect) !== 'undefined') {
			let redirect = decodeURIComponent(to.query.redirect)
			next(redirect)
			return
		}
	}
	// 某些过滤的请求，包括login,error等
	if (isFilter(to.name)) {
		// debugger
		next()
		return
	}
	if (!getLoginUser()) {
		 redirect('imformation', next, to)
	} else {
		doNext(next, to)
		/* if (basedata.isLoad()) {
			doNext(next, to)
		} else {
			loadBaseData(next, to)
		} */
	}
}

/**
 * 执行跳转
 * @param {*} next 
 */
function doNext (next, to) {
	// 如果跳转到admin做权限控制
	if (/^\/admin/.test(to.path)) {
		let loginUser = getLoginUser()	
		if (loginUser.yhid !== 'admin') {
			redirect('403', next, to)
			return
		}
	}
	next()
}

/**
 * 跳转到某个页面
 */
function redirect (name, next, to) {
	if (typeof (to.query.redirect) === 'undefined') {
		to.query.redirect = encodeURIComponent(to.path) 
	}
	next({ name: name, query: to.query })
}

/**
 * 设置loading
 */
function setLoading (flag) {
	store.dispatch('global.setLoading', flag)
}

/**
 * 获取登陆用户
 */
function getLoginUser () {
	return store.getters['global.user']
}

/**
 * 是否在过滤范围内
 */
function isFilter (name) {
	return filterMap.get(name)
}

/**
 * 加载基础数据
 */
function loadBaseData (next, to) {
	setLoading(true)
	// 请求基础数据
	basedata.request().then(result => {
		setLoading(false)
		doNext(next, to)
	}, error => {
		setLoading(false)
		if (error.code && Number(error.code) === 120000) {
			redirect('login', next, to)
		} else {
			if (error.stack) {
				error.msg = error.message
			}
			if (error.code === 120000) {
				redirect('login', next, to)
			} else {
				store.dispatch('global.setError', error)
				redirect('error', next, to)
			}
		}
	})
}