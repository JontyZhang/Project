<!--
    文件：src/views/default/403.vue
    作者：吴彬
    时间：2017-5-25
    描述：权限不足提示
-->
<template>
  <section class="not-found">
    <section>您没有权限访问当前页面</section>
		<router-link :to="{ path: '/' }">回首页</router-link>
  </section>
</template>

<style lang="scss">
.not-found {
	text-align: center;
	margin-top: 10px;
	
	section {
	  font-size: 3em;
	  color: #80BD01;
	  text-shadow: 2px 2px 5px rgba(0, 0, 0, .6);
	}
	
	p {
	  color: lighten(#000, 35%);
	}
}
</style>