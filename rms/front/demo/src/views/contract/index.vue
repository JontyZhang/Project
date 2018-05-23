<!-- 
	文件：views/contract/index
	作者：
	时间：
	描述：合同首页
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
	            title: '合同管理',
	            closable: false,
	            isClosed: true,
	            component: resolve => require(['./list'], resolve),
	            model: this.$refs.tabs
	        })
    }
}
</script>