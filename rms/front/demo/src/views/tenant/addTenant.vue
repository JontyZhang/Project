<!-- 
    文件：src/views/contract/addContract.vue
    作者：
    时间：
    描述：新增合同页面
-->
<template>
<div v-if="showflag">
    <el-row class="form-container">
         <el-col :span="24" class="form-title">
             <p style="color:#0e0f0f;font-size:16px;">新增合同</p>
         </el-col>
         <el-col :span="24" class="form-main">
            <el-form ref="tenant" :model="tenant" label-width="80px" size="mini">
            <el-form-item label="角色名" >
                <el-col :span="12">
                <el-input v-model="tenant.contractStyle"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="角色名" >
                <el-col :span="12">
                <el-input v-model="tenant.contractStyle"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="角色名" >
                <el-col :span="12">
                <el-input v-model="tenant.contractStyle"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="角色名" >
                <el-col :span="12">
                <el-input v-model="tenant.contractStyle"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="角色名" >
                <el-col :span="12">
                <el-input v-model="tenant.contractStyle"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="角色名" >
                <el-col :span="12">
                <el-input v-model="tenant.contractStyle"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item size="large">
                <el-button type="primary" class="form-btn-final" @click="onSave">立即创建</el-button>
                <el-button @click.native="onCancel">取消</el-button>
            </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</div>
</template>

<script>
export default{
    data () {
        return {
            tenant: {
                tenantName: '', // 客户名
                tenantSex: '',
                tenantPhoneNum: '',
                tenantCID: '',
                landlordId: '',
                rentingHouseId: '',
                contractId: '',
                remark: ''
            },
            flag: false,
            showflag: false  // 控制弹出框
        }
    },
    mounted () {
        
	},
    methods: {
        /** 
         * 创建保存
        */
        onSave () {
           // debugger
            let self = this
            self.$confirm('是否确认添加新客户？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then(() => {
                if (self.checkData(self.role)) {
                        self.$backend.request(self.$api.tenant.addTenant, self.addTenant)
                            .then((response) => {
                                self.$message.success('保存成功！')
                               // this.$parent.onSearch(1)
                                this.showflag = false
                            })
                    }
            })
        },
        /** 
         * 取消
        */
        onCancel () {
            this.showflag = false
        },
        /**
         * 数据校验
         */
        checkData (role) {
            return  true
        },
        handleChange (value) {
            console.log(value)
        }
    }
}
</script>