<template>     
    <el-row :gutter="1" class="detail-container">
        <el-col :span="24">
            <el-col :span="15" style="margin-left: 4%;">
                <el-form ref="form" :model="rentsDetails" label-width="80px" size="mini">
                <el-form-item label="租户" >
                <el-col :span="6">
                    <el-input v-model="rentsDetails.tenant.tenantName"  :disabled="flag"></el-input>
                </el-col>   
                </el-form-item>
                <el-form-item label="房租" >
                <el-col :span="6">
                <el-input v-model="rentsDetails.houseRent"  :disabled="flag"></el-input>
                </el-col>   
                </el-form-item>
                <el-form-item label="水费" >
                <el-col :span="6">
                <el-input v-model="rentsDetails.waterFee"  :disabled="flag"></el-input>
                </el-col>   
                </el-form-item>
                <el-form-item label="电费" >
                <el-col :span="6">
                <el-input v-model="rentsDetails.electricFee"  :disabled="flag"></el-input>
                </el-col>   
                </el-form-item>
                <el-form-item label="网费" >
                <el-col :span="6">
                <el-input v-model="rentsDetails.netFee"  :disabled="flag"></el-input>
                </el-col>   
                </el-form-item>
                <el-form-item label="燃气费" >
                <el-col :span="6">
                <el-input v-model="rentsDetails.gasFee" :disabled="flag"></el-input>
                </el-col>   
                </el-form-item>
                <el-form-item label="物业管理费" >
                <el-col :span="6">
                <el-input v-model="rentsDetails.propertyFee" :disabled="flag"></el-input>
                </el-col>  
                </el-form-item>   
                 <el-form-item label="租金状态">
                <el-radio-group v-model="rentsDetails.rentsState" size="medium" :disabled="flag">
                <el-radio border label="0">生效</el-radio>
                <el-radio border label="1">无效</el-radio>
                </el-radio-group>
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
            rentsDetails: {
                tenant: {
                    tenantName: ''
                }
            },
            Rents: {
             propertyFee: '',
             houseRent: '',
             waterFee: '',
             electricFee: '',
             netFee: '',
             gasFee: '',
             rentState: ''
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
          console.log(this.value)
            self.$backend.request(self.$api.Rent.queryRentById, {rentId: this.value})
                .then((res) => {
                    self.rentsDetails = res.data
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
                self.$backend.request(self.$api.Rent.updateRents, self.Rents)
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

