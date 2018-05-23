<template>
    <el-dialog
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
         <el-col :span="24" class="form-title">
             <p style="color:#0e0f0f;font-size:16px;">新增字典</p>
         </el-col>
         <el-col :span="24" class="form-main">
            <el-form ref="dictionaryEntity" :model="dictionaryEntity" label-width="80px" size="mini">
            <el-form-item label="typeCode" >
                <el-col :span="12">
                <el-input v-model="dictionaryEntity.typeCode" placeholder="建议用英文"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="code" >
                <el-col :span="12">
                <el-input v-model="dictionaryEntity.code" placeholder="字典code"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="value" >
                <el-col :span="12">
                <el-input v-model="dictionaryEntity.value" placeholder="字典中文翻译"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="备注" >
                <el-col :span="12">
                <el-input v-model="dictionaryEntity.remark" placeholder="备注"></el-input>
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
            dictionaryEntity: {
                dictionaryId: '',
                typeCode: '',
                code: '',
                value: '',
                remark: ''
            },
            centerDialogVisible: false // 是否展示
        }
    },
    mounted () {
        
	},
    methods: {
        init () {
            this.$refs['dictionaryEntity'].resetFields()
            this.dictionaryEntity = {
                  typeCode: '',             
                  code: '',
                  value: '',
                  remark: ''
            }
            this.centerDialogVisible = false
        },
        /** 
         * 创建保存
        */
        onSave () {
            let self = this
            this.$backend.request(this.$api.dictionaryManager.addDictionaryContent, this.dictionaryEntity).then((result) => {
                if (result.success) {
						self.$message.success('保存成功！')                       
                        this.init()
                        this.$parent.$parent.onSearch(1)
                } else {
                        self.$message.error('保存失败！请检查是否重复插入')
                        this.init()
                        this.$parent.$parent.onSearch(1)
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
        checkData (user) {
            return true
        },
        handleChange (value) {
            console.log(value)
        }
    }
}
</script>