<template>
    <el-row>         
        <el-col>
            <el-col :span="24" class="form-main">
                <el-col>
                    <h3>用户信息</h3>
                </el-col>
            <el-form ref="form" :model="user" label-width="100px" size="mini">
            <el-form-item label="姓名" >
                <el-col :span="4">
                <el-input v-model="user.userName" :disabled="flag"></el-input>
                </el-col>   
            </el-form-item>
             <el-form-item label="性别" >
                <el-col :span="3">
                <el-input v-model="user.userSex" :disabled="flag"></el-input>
                </el-col>   
            </el-form-item>
             <el-form-item label="电话号码" >
                <el-col :span="3">
                <el-input v-model="user.phoneNum" :disabled="flag"></el-input>
                </el-col>   
            </el-form-item>
             <el-form-item label="身份证号码" >
                <el-col :span="3">
                <el-input v-model="user.userCID" :disabled="flag"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item size="large">
                <el-button type="primary" class="form-btn-final" @click="onChange">更改</el-button>
                <el-button @click="onCancel">取消</el-button>
            </el-form-item>
            </el-form>
        </el-col>
        </el-col>
    </el-row>
</template>

<script>
import basedata from 'conf/basedata'

export default {
    props: {
        value: String
    },
    watch: {
        value () {
            this.init()
        }
    },
    data () {
        return {
            user: {},
            flag: true
        }
    },
    mounted () {
        this.init()
    },
    methods: {
        /*
		 * 初始化数据
		 */
        init () {
        let self = this
          console.log(this.value)
            self.$backend.request(self.$api.user.queryUser, {userId: this.value})
                .then((res) => {
                    self.user = res.data
                })
        },
        onChange () {
            this.flag = false
        },
        // 取消
        onCancel () {

        }
    }
}
</script>
