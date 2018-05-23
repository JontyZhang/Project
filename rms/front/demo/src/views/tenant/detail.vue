<template>     
    <el-row :gutter="1" class="detail-container">
        <el-col :span="24">
            <el-col :span="15" style="margin-left: 4%;">
                <el-form ref="form" :model="tenantDetails" label-width="80px" size="mini">
                <el-form-item label="租户姓名" >
                <el-col :span="6">
                    <el-input v-model="tenantDetails.tenantName"  :disabled="flag"></el-input>
                </el-col>   
                </el-form-item>
                <el-form-item label="性别">
                <el-col :span="6">
                <el-select v-model="tenantDetails.tenantSex" :disabled="flag">
                    <el-option label="男" value="0"></el-option>
                     <el-option label="女" value="1"></el-option>
                </el-select>
                </el-col>   
                </el-form-item>
                <el-form-item label="联系电话" >
                <el-col :span="15">
                <el-input v-model="tenantDetails.tenantPhoneNum"  :disabled="flag"></el-input>
                </el-col>   
                </el-form-item>
                <el-form-item label="身份证号码" >
                <el-col :span="15">
                <el-input v-model="tenantDetails.tenantCID"  :disabled="flag"></el-input>
                </el-col>   
                </el-form-item>
                <el-form-item label="备注">
                    <el-col :span="15"> <el-input v-model="tenantDetails.remark"  type="textarea"></el-input></el-col>
                </el-form-item>
                <el-form-item size="large">
                    <el-col :span="24">
                        <el-col :span="3">
                            <el-button type="primary" class="form-btn-final" @click="changFlag">更改</el-button>
                        </el-col>
                        <el-col :span="6" v-if="!flag">
                             <el-button @click="onCancel">取消</el-button>
                        <el-button type="primary" class="form-btn-final" @click="onSave">保存</el-button>
                        </el-col>
                    </el-col>                
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
            tenantDetails: {

            },
            flag: true // 是否可编辑
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
          debugger
          console.log(this.value)
            self.$backend.request(self.$api.tenant.queryTenant, {tenantId: this.value})
                .then((res) => {
                    self.tenantDetails = res.data
                })
        },
        // 更改
        changFlag () {
            this.flag = false
        },
        // 保存
        onSave () {
            let self = this
            self.Rents.propertyFee = self.rentsDetails.propertyFee
            self.Rents.houseRent = self.rentsDetails.houseRent
            self.Rents.waterFee = self.rentsDetails.waterFee
            self.Rents.electricFee = self.rentsDetails.electricFee
            self.Rents.netFee = self.rentsDetails.netFee
            self.Rents.gasFee = self.rentsDetails.gasFee
            self.Rents.rentState = self.rentsDetails.rentState
            debugger
            if (self.checkData(self.Rents)) {
                self.$backend.request(self.$api.tenant.updateTenant, self.tenantDetails)
					.then((res) => {
						self.$message.success('保存成功！')
                        self.flag = true
					})
            }
        },
        // 取消
        onCancel () {
             this.flag = true        
        },
        checkData (rentsDetails) {
            if (!rentsDetails) {
                return false
            }
            return true
        }
    }
}
</script>
<style>
.detail-container{
    padding-top: 1%;
    padding-left: 2%;
}
  .image {
    width: 100%;
    display: block;
    height: 50%;
  }
</style>

