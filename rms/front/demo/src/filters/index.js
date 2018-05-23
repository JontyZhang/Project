import Vue from 'vue'
import { DateUtils, StringUtils } from 'utils/common'

let filters  = {
	/**
	 * 数字千位符
 	 * 1000 -> 1,000 
	 * @param {*} value 
	 */
	thousands (value) {
		return (value || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,')
	},
	/**
	 * 日期格式化
	 */
	date (value) {
		if (!StringUtils.isEmpty(value)) {
			return DateUtils.dateFormat2(value, false)
		}
		return ''
	},
	/**
	 * 日期时间格式化
	 */
	datetime (value) {
		if (!StringUtils.isEmpty(value)) {
			return DateUtils.dateFormat2(value, true)
		}
		return ''
	}
}

// 注册为全局的filter
for (let i in filters) {
	Vue.filter(i, filters[i])
}

export default filters