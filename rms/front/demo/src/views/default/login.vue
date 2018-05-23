<!--
    文件：src/views/default/login.vue
    作者：zjt
    时间：2017-5-25
    描述：用户登陆
-->
<template>
<div v-loading="loading" element-loading-text="正在登陆">
    <div class="rms-login-title">rms管理系统</div>
    <div class="rms-login-table">
        <el-form v-on:submit.prevent>
            <el-input type="text" class="rms-login-input" placeholder="姓名" v-model="user.name"/>
            <el-input type="Password" class="rms-login-input" placeholder="密码" v-model="user.password" />
            <el-col :span="24">
                <el-col :span="6" class="rms-login-input"><el-button size="small" round @click="onAdd">加入我们</el-button></el-col>
            </el-col>           
            <el-button class="rms-login-button" @click="onLogin()">登陆</el-button>
        </el-form>
        <el-col>
            <add-User ref="addUser"></add-User>
        </el-col>
    </div>
</div>
</template>

<script>
import api from 'conf/api'
import backend from 'utils/backend'
import CryptoJS from 'crypto-js'
import { mapActions } from 'vuex'
import addUser from './addUser'

export default {
    components: {
        addUser
    },
    data () {
        return {
            loading: false,
            // 登陆输入参数
            user: {
                name: '',
                password: ''
            }
        }
    },
    methods: {
        ...mapActions({
            setUser: 'global.setUser',
            setButton: 'button.button'
        }),
        onLogin () {
            let self = this
            if (self.checkMsg(self.user)) {
                // 将密码进行md5加密
                let password = CryptoJS.MD5(this.user.password).toString()
                console.log('登录用户:' + JSON.stringify(loginInput))
                let loginInput = { 
                    userName: this.user.name,
                    userPassword: password.toString(CryptoJS.enc.Utf8).toUpperCase()
                } 
                this.loading = true
                self.$backend.request(self.$api.loginController.login, loginInput).then((result) => {
                    let user = result.data
                    if (user ===  undefined || JSON.stringify(user) === {}) {
                        this.$router.push({ path: 'login' })
                        this.$alert('用户信息有误')
                    } else {
                        this.setUser(user)
                        this.setButton(result.data)
                        this.redirect()
                    }              
                }, response => {
                    this.loading = false
                })
                // this.setUser(this.user)
                // this.loading = false
                // this.redirect()
            }            
        },
        redirect () {
            if (typeof (this.$route.query.redirect) !== 'undefined') {
                let redirect = decodeURIComponent(this.$route.query.redirect)
                this.$router.push({ path: redirect })
            } else {
                this.$router.push({ path: '/' })
            }
        },
      // 新增
		onAdd () {
             this.$refs.addUser.centerDialogVisible = true                 
        },
        checkMsg (user) {
            if (!user) {
                return false
            }
            if (!user.name) {
                this.$message.error('用户名不能为空！')
                return false
            }
            if (!user.password) {
                this.$message.error('用户密码不能为空')
                return false
            }
            return true 
        } 
    }
}
</script>
