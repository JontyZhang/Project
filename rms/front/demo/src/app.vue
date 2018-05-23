<!--
    文件：src/app.vue
    作者：吴彬
    时间：2017-5-20
    描述：主界面
-->
<template>
<div id="app" v-loading="loading" element-loading-text="玩命加载中">
	<!-- http拦截器 -->
	<http-interceptor></http-interceptor>
	
	<transition name="fade">
		<router-view></router-view>
	</transition>
</div>
</template>

<script>
import { mapGetters } from 'vuex'
import { httpInterceptor } from 'components/common'

export default {
	components: {
		httpInterceptor
	},
	computed: {
		...mapGetters({
			loading: 'global.loading'
		})
	}
}
</script>

<style lang="scss">
@import url('assets/css/index.css');
</style>