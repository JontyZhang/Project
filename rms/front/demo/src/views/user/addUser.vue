<template>
    <el-row class="form-container">
         <el-col :span="24" class="form-title">
             <p style="color:#0e0f0f;font-size:16px;">新增用户</p>
         </el-col>
         <el-col :span="24" class="form-main">
            <el-form ref="form" :model="user" label-width="80px" size="mini">
            <el-form-item label="用户名" >
                <el-col :span="12">
                <el-input v-model="user.name"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="性别">
            <el-radio-group v-model="user.sex">
                    <el-radio :label="3">男</el-radio>
                    <el-radio :label="6">女</el-radio>
                    <el-radio :label="9">不明</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="活动性质">
                <el-checkbox-group v-model="user.type">
                <el-checkbox-button label="美食/餐厅线上活动" name="type"></el-checkbox-button>
                <el-checkbox-button label="地推活动" name="type"></el-checkbox-button>
                <el-checkbox-button label="线下主题活动" name="type"></el-checkbox-button>
                </el-checkbox-group>
            </el-form-item>
            <el-form-item label="租赁情况">
                <el-radio-group v-model="user.resource" size="medium">
                <el-radio border label="已租"></el-radio>
                <el-radio border label="未租"></el-radio>
                <el-radio border label="停用"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item size="large">
                <el-button type="primary" class="form-btn-final" @click="onSave">立即创建</el-button>
                <el-button @click.native="onCancel">取消</el-button>
            </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<script>
export default{
    data () {
        return {
            user: {
                name: '',
                sex: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            }
        }
    },
    mounted () {
        
	},
    methods: {
        /** 
         * 创建保存
        */
        onSave () {

        },
        /** 
         * 取消
        */
        onCancel () {
             console.log(this.$parent.add)
             this.$parent.add = false
        },
        /**
         * 数据校验
         */
        checkData (user) {
            return true
        },
        handleChange (value) {
            console.log(value)
        }
    }
}
</script>