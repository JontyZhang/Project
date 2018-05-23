<!--
    文件：src/views/user/index.vue
    作者：
    时间：2017-08-30
    描述：人员管理首页
-->

<template>
	<section>
		<tabs ref="tabs"></tabs>
	</section>
</template>


<script>
import list from './list'
import { tabs, tabsUtils } from 'components/common'
export default {
	components: {
		list,
		tabs
	},
	data () {
		return {
		}
	},
    mounted () {
    	tabsUtils.open(this.$refs.tabs, {
	            id: new Date().getTime(),
	            title: '人员管理',
	            closable: false,
	            isClosed: true,
	            component: resolve => require(['./list'], resolve),
	            model: this.$refs.tabs
	        })
    }
}
</script>