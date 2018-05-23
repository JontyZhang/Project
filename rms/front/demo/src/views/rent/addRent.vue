<template>
    <el-dialog
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
         <el-col :span="24" class="form-title">
             <p style="color:#0e0f0f;font-size:16px;">新增定金单</p>
         </el-col>
         <el-col :span="24" class="form-main">
            <el-form ref="rent" :model="rent" label-width="80px" size="mini">
            <el-form-item label="客户名" >
                <el-col :span="12">
                <el-input v-model="rent.tenantName"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item size="large">
                <el-button type="primary" class="form-btn-final" @click="onSave">立即创建</el-button>
                <el-button @click.native="onCancel">取消</el-button>
            </el-form-item>
            </el-form>
        </el-col>
    </el-dialog>
</template>

<script>
export default{
    data () {
        return {
            rent: {
                tenantName: ''
            },
            flag: false,
            centerDialogVisible: false  // 控制弹出框
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
            self.$confirm('是否确认添加新角色？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then(() => {
                if (self.checkData(self.role)) {
                        self.$backend.request(self.$api.role.addRole, self.role)
                            .then((response) => {
                                self.$message.success('保存成功！')                               
                                self.centerDialogVisible = false
                                this.$parent.$parent.onSearch(1)
                            })
                    }
            })
        },
        /** 
         * 取消
        */
        onCancel () {
            this.centerDialogVisible = false
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