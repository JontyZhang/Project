<!--
    文件：src/views/default/error.vue
    作者：吴彬
    时间：2017-5-25
    描述：错误页面
-->
<template>
  <section class="error-page">
    <section>糟糕，发生了一点点意外</section>
    <p>{{ error.msg }}</p>
    <router-link :to="retryRoute">重试</router-link>
  </section>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
    computed: {
        ...mapGetters({
            error: 'global.error'
        })
    },
    data () {
      return {
        retryRoute: null
      }
    },
    created () {
      if (typeof (this.$route.query.redirect) !== 'undefined') {
          let redirect = decodeURIComponent(this.$route.query.redirect)
          this.retryRoute = { path: redirect }
      } else {
          this.retryRoute = { path: '/' }
      }
    }
}
</script>

<style lang="scss">
.error-page {
	text-align: center;
	margin-top: 10px;
	
	section {
	  font-size: 2em;
	  color: #f90;
	  text-shadow: 2px 2px 5px rgba(0, 0, 0, .6);
	}
	
	p {
	  color: lighten(#000, 35%);
      font-size: 22px;
	}
}
</style>