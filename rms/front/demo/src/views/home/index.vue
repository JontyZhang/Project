<!-- 
	文件：views/home/index
	作者：
	时间：2017-05-27
	描述：首页
 -->
<template>
	<el-row class="container">
		<!-- 头-->
		 <el-col :span="24" class="header-container">
			 <el-col :span="20" class="logo" :class="collapsed?'logo-collapse-width':'logo-width'">
				 <span>rms管理系统</span>
			 </el-col>
			<el-col :span="4" class="userinfo">
				<el-dropdown trigger="hover">
					<span class="el-dropdown-link userinfo-inner"><img :src="this.sysUserAvatar" />管理员</span>
					<el-dropdown-menu slot="dropdown">
						<!--<el-dropdown-item>我的消息</el-dropdown-item>
						<el-dropdown-item>设置</el-dropdown-item> -->
						<el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</el-col>
		 </el-col>
		 <!-- 主体-->
		 <el-col :span="24" class="main-container" type="flex" justify="center">
			 <!-- 左边导航菜单-->
			 <el-col :span="3" class="aside-container" type="flex" justify="center">
				 <el-menu :default-active="$route.path" router>
					 <el-menu-item index="/introduce"><i class="el-icon-message"></i>简述</el-menu-item>
					 <el-menu-item index="/rentingHouse"><i class="el-icon-message"></i>房屋管理</el-menu-item>
					  <el-menu-item index="/contract"><i class="el-icon-message"></i>合同管理</el-menu-item>
					<el-submenu index="1">
						<template slot="title"><i class="el-icon-message"></i>统计管理</template>
							<el-menu-item-group>
								<el-menu-item index="/payments">收支流水</el-menu-item>
								<el-menu-item index="/rentdetail">流水明细</el-menu-item>
							</el-menu-item-group>
					</el-submenu>
					<el-submenu index="2">
						<template slot="title"><i class="el-icon-message"></i>放租管理</template>
							<el-menu-item-group>
								<el-menu-item index="/tenant">客户管理</el-menu-item>
								<el-menu-item index="/rent">房租管理</el-menu-item>								
								<el-menu-item index="/deposit">押金管理</el-menu-item>
							</el-menu-item-group>
					</el-submenu>
					<el-submenu index="3">
						<template slot="title"><i class="el-icon-message"></i>求租管理</template>
							<el-menu-item-group>
								<el-menu-item index="/msgManager">求租留言管理</el-menu-item>
							</el-menu-item-group>
					</el-submenu>
					<el-submenu index="4"> <!--加v-ifv-if="showBtn" -->
						<template slot="title"><i class="el-icon-message"></i>管理员管理</template>
							<el-menu-item-group>
								<el-menu-item index="/role">角色管理</el-menu-item>
								<el-menu-item index="/premission">权限管理</el-menu-item>
								<el-menu-item index="/userManager">用户管理</el-menu-item>
								<el-menu-item index="/dictionaryManager">字典管理</el-menu-item>
							</el-menu-item-group>
					</el-submenu>
					<el-submenu index="5">
						<template slot="title"><i class="el-icon-message"></i>系统管理</template>
							<el-menu-item-group>
								<el-menu-item index="/personFile">个人信息修改</el-menu-item>
								<el-menu-item index="/help">帮助中心</el-menu-item>
							</el-menu-item-group>
					</el-submenu>
				</el-menu>
			 </el-col>
			 <!-- 主体-->
			 <el-col :span="21" class="content-container">
				 <router-view></router-view>
			 </el-col>		
		 </el-col>
	</el-row>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
	...mapGetters({
		showBtn: 'button.button'
	}),
	components: {
    },
	data () {
		return {
		}
	},
    mounted () {
	},
	methods: {
		/**
		 * 收缩
		 */
		 onGetData () {
			let self = this
			console.log(this.user.userId)
			self.$backend.request(self.$api.user.queryUser, {userId: this.user.userId})
				.then((res) => {
					console.log(res.data)
				    self.user = res.data
				})
		},
		/**
		 *退出登陆
		 */
		 logout () {
			 // 清空前台用户信息
			let self = this
			self.$backend.request(self.$api.loginController.loginOut)
			  this.$router.push({path: '/imformation'})
		 }
	}
}
</script>

