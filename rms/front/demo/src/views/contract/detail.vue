<template>     
    <el-row :gutter="1" class="detail-container">
        <el-col :span="24">
            <el-col :span="15" style="margin-left: 4%;">
                <el-form ref="form" :model="RentingHouse" label-width="80px" size="mini">
                <el-form-item label="合同编号" >
                    <el-col :span="4">
                        <el-input v-model="Contract.contractNo" :disabled="flag"></el-input>
                    </el-col>   
                </el-form-item>
                <el-form-item label="客户" >
                <el-col :span="6">
                    <el-input v-model="Contract.tenantName" :disabled="unSet"></el-input>
                </el-col>     
                </el-form-item>
                <el-form-item label="客户电话" >
                <el-col :span="6">
                    <el-input v-model="Contract.tenantTel"  :disabled="unSet"></el-input>
                </el-col>     
                </el-form-item>                                
                <el-form-item label="租金" >
                <el-col :span="6">
                    <el-input v-model="Contract.rent" placeholder="单位为元/月" :disabled="flag"></el-input>
                </el-col>     
                </el-form-item>
                <el-form-item label="出租类型">
                    <el-radio-group v-model="Contract.contractStyle" size="medium" :disabled="flag">
                    <el-radio border label="0">整租</el-radio>
                    <el-radio border label="1">合租</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="合同状态">
                    <el-radio-group v-model="Contract.contractState" size="medium" :disabled="flag">
                    <el-radio border label="0">有效</el-radio>
                    <el-radio border label="1">无效</el-radio>
                </el-radio-group>
                </el-form-item>
                <el-form-item size="large">
                    <el-col :span="24">
                        <el-col :span="2">
                            <el-button type="primary"  @click="1">押金</el-button>
                        </el-col>
                        <el-col :span="2">
                            <el-button type="primary"  @click="2">杂费</el-button>
                        </el-col>
                        <el-col :span="2">
                            <el-button type="primary"  @click="print">打印</el-button>
                        </el-col>                        
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
            Contract: {
                contractId: '' // 合同ID
            },
            unSet: true, // 是否可编辑 不允许
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
          self.Contract.contractId = this.value
            self.$backend.request(self.$api.contract.queryContractByContractId, self.Contract)
                .then((res) => {
                    self.Contract = res.data
                })
        },
        // 更改
        changFlag () {
            this.flag = false
        },
        // 保存
        onSave () {
            let self = this
            if (self.checkData(self.RentingHouse)) {
                self.$backend.request(self.$api.contract.updateContract, self.Contract)
					.then((res) => {
						self.$message.success('保存成功！')
                        self.flag = true
					})
            }
        },
        print () {
            debugger
            let self = this
             self.$confirm('是否确认打印合同？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then(() => {
                if (self.Contract) {
                        self.$backend.request(self.$api.contract.createContactWord, self.Contract)
                            .then((response) => {
                                self.$message.success('生成合同成功！')
                                self.$parent.$parent.onSearch(1)
                                this.centerDialogVisible = false
                                this.init()
                            })
                    }
            })
        },
        // 取消
        onCancel () {
             this.flag = true        
        },
        checkData (contract) {

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

