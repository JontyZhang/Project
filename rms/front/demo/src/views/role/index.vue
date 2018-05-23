<!-- 
	文件：views/role/index
	作者：
	时间：
	描述：首页
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
	            title: '角色管理',
	            closable: false,
	            isClosed: true,
	            component: resolve => require(['./list'], resolve),
	            model: this.$refs.tabs
	        })
    }
}
</script>