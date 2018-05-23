<template>
  <el-row>
      <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="20%"
        center>
        <el-col :span="24" class="form-title">
         <p style="color:#0e0f0f;font-size:16px;">留言</p>
         </el-col>
         <el-col :span="24" class="form-main">
            <el-form ref="form" :model="msgDetail" label-width="80px" size="mini">
            <el-form-item label="信息详情" >
                <el-col :span="12">
                <el-input v-model="msgDetail.msg" type="textarea"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="电话号码" >
                <el-col :span="12">
                <el-input v-model="msgDetail.touristNum"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item size="large">
                <el-button type="primary" class="form-btn-final" @click="onSave">立即创建</el-button>
                <el-button @click.native="onCancel">取消</el-button>
            </el-form-item>
            </el-form>
        </el-col>
      </el-dialog>
  </el-row>
</template>
<script>
export default {
        data () {
        return {
             centerDialogVisible: false, // 是否打开对话框
             msgDetail: {
                 rentingHouseId: ''
             }
        }
    },
    methods: {
        onSave () {
            let self = this
             self.$confirm('是否确认留言？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then(() => {
                if (self.msgDetail) {
                        self.$backend.request(self.$api.RentingHouseMsgController.setMsg, self.msgDetail)
                            .then((response) => {
                                self.$message.success('保存成功！')
                                // self.$parent.$parent.onSearch(1)
                                this.centerDialogVisible = false
                                this.init()
                            })
                    }
            })
        },
        init () {
            this.msgDetail.msg = ''
            this.msgDetail.touristNum = ''
        },
        onCancel () {
            this.centerDialogVisible = false
        }
    }
}
</script>
