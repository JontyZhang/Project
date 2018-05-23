const LOGIN_USER = 'LOGIN_USER'

export default {
	setLoginUser (user) {
		if (typeof (user) === 'object') {
			window.sessionStorage.setItem(LOGIN_USER, JSON.stringify(user))	
		} else {
			window.sessionStorage.setItem(LOGIN_USER, '')
		}
	},
	getLoginUser () {
		let jsonstr = window.sessionStorage.getItem(LOGIN_USER)
		if (jsonstr && jsonstr !== '') {
			try {
				return JSON.parse(jsonstr)
			} catch (e) {
				console.error(e)
				return null
			}
		}
		return null
	}
}
