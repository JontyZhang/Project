<template>
    <el-dialog
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
       <el-col :span="24" class="form-title">
             <p >新增合同</p>
         </el-col>
         <el-col :span="24" style="margin-top:5%;margin-left: 9%">
            <el-col :span="24" style="margin-bottom:3%;">
                <span>房间信息</span>
            </el-col>
            <el-col :span="10">
                <span>详细地址:</span>
                <el-input v-model="rentingHouse.address" size="medium" :disabled="true" style="margin-top:2%;margin-left: 0%;display:block; width:320px;"></el-input>
            </el-col>
            <el-col :span="2">   </el-col>
            <el-col :span="10">
                <span>小区:</span>
                <el-input v-model="rentingHouse.community" size="medium" :disabled="true" style="margin-top:2%;margin-left: 0%; display:block;width:320px;"></el-input>
            </el-col>
         </el-col>
         <el-col :span="24" style="margin-top:5%;margin-left:9%;">
             <el-col :span="24" style="margin-bottom:2%;">
                 <span>基本信息</span> 
             </el-col>
             <el-col :span="10" style="">
                 <span>承租人姓名：</span>
                  <el-input v-model="contract.tenantName" style="margin-top:2%;margin-left: 0%;display:block; width:320px;"></el-input>
             </el-col> 
                 <el-col :span="10">
                 <span>承租人电话号：</span>
                 <el-input v-model="contract.tenantTel" style="margin-top:2%;margin-left: 0%;display:block; width:320px;"></el-input>
             </el-col>                
         </el-col>
         <el-col :span="24" style="margin-top:1%;margin-left:9%;">
             <el-col :span="10">
                 <span>承租人身份证号：</span>
                 <el-input v-model="contract.tenantCID" style="margin-top:2%;margin-left: 0%;display:block; width:320px;"></el-input>
             </el-col>               
         </el-col>
         <el-col :span="24" style="margin-top:5%;margin-left:9%;">
             <el-col :span="10" style="">
                 <span>出租人姓名：</span>
                  <el-input v-model="contract.landlordName" style="margin-top:2%;margin-left: 0%;display:block; width:320px;"></el-input>
             </el-col> 
                 <el-col :span="10">
                 <span>出租人电话号：</span>
                 <el-input v-model="contract.landlordTel" style="margin-top:2%;margin-left: 0%;display:block; width:320px;"></el-input>
             </el-col>                
         </el-col>
         <el-col :span="24" style="margin-top:1%;margin-left:9%;">
             <el-col :span="10">
                 <span>出租人身份证号：</span>
                 <el-input v-model="contract.landlordCID" style="margin-top:2%;margin-left: 0%;display:block; width:320px;"></el-input>
             </el-col>               
         </el-col>
         <el-col :span="24" style="margin-top:5%">
             <span style="margin-left: 9%">租赁信息</span>
             <span style="color:#ff5153;">（合同生效后，将不可修改租赁信息）</span>
         </el-col>
         <el-col :span="24" class="form-main">
            <el-form ref="contract" :model="contract" label-width="100px" size="mini">
                <el-form-item label="租赁时间">
                    <el-col :span="24">
                     <el-col :span="10">
                        <el-form-item prop="rentFromDate">
                                <el-date-picker
                                v-model="contract.rentFromDate"
                                type="date"
                                placeholder="开始日期"
                                format="yyyy 年 MM 月 dd 日"
                                value-format="timestamp">
                                </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="1">-</el-col>
                    <el-col :span="10">
                        <el-form-item prop="rentToDate">
                                <el-date-picker
                                v-model="contract.rentToDate"
                                type="date"
                                :picker-options="pickerOptions"
                                placeholder="结束日期"
                                format="yyyy 年 MM 月 dd 日"
                                value-format="timestamp">
                                </el-date-picker>
                        </el-form-item>                       
                    </el-col>         
                </el-col>
            </el-form-item>
            <el-form-item label="租金" >
                <el-col :span="24">
                    <el-col :span="6">
                        <el-input v-model="contract.rent" placeholder="元/月"></el-input>
                     </el-col> 
                     <el-col :span="6"><span style="margin-left: 50%;">总租金</span></el-col>  
                    <el-col :span="8">
                        <el-input v-model="totalPrice" :disabled="true" ></el-input>
                     </el-col>
                </el-col>
            </el-form-item>
            <el-form-item label="收租方式">
                <el-select v-model="contract.payStyle" placeholder="请选收租方式">
                <el-option label="提前日收租" value="0"></el-option>
                <el-option label="固定日收租" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="出租方式">
                <el-select v-model="contract.contractStyle" placeholder="请选收租方式">
                <el-option label="整租" value="0"></el-option>
                <el-option label="合租" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="合同状态">
                <el-select v-model="contract.contractState" placeholder="请选合同状态">
                <el-option label="有效" value="0"></el-option>
                <el-option label="无效" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="备注" >
                <el-col :span="6">
                <el-input v-model="contract.remark" type="textarea"></el-input>
                </el-col>   
            </el-form-item>
         <el-col :span="24" style="margin-top:5%;margin-buttom:5%">
             <span  style="margin-left:0%;">押金信息</span>
         </el-col>
            <el-form-item label="押金单状态">
                <el-select v-model="contract.deposit.depositState" placeholder="请选押金单状态">
                <el-option label="有效" value="0"></el-option>
                <el-option label="无效" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="押金">
                <el-col :span="6">
                    <el-input v-model="contract.deposit.deposit" type="number"></el-input>
                </el-col>
            </el-form-item> 
            <el-form-item label="备注">
                <el-col :span="12">
                <el-input v-model="contract.deposit.remark" type="textarea"></el-input>
                </el-col>   
            </el-form-item>
                <span style="margin-left:0%;">租金详情信息</span>
            <el-form-item label="租金状态">
                <el-select v-model="contract.rents.rentsState" placeholder="请选租金状态">
                <el-option label="有效" value="0"></el-option>
                <el-option label="无效" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-col :span="6">
                    <el-checkbox label="电费" name="type" :border="true"  v-model="test1"></el-checkbox>
                </el-col>
                <el-col :span="6">
                    <el-input v-model="contract.rents.electricFee" type="number" v-show="test1"></el-input>
                </el-col>
            </el-form-item>
            <el-form-item>
                <el-col :span="6">
                    <el-checkbox label="水费" name="type" :border="true" v-model="test2"></el-checkbox>
                </el-col>
                <el-col :span="6">
                    <el-input v-model="contract.rents.waterFee" type="number"  v-show="test2"></el-input>
                </el-col>
            </el-form-item> 
            <el-form-item>
                <el-col :span="6">
                    <el-checkbox label="网费" name="type" :border="true" v-model="test3"></el-checkbox>
                </el-col>
                <el-col :span="6">
                    <el-input v-model="contract.rents.netFee" type="number" v-show="test3"></el-input>
                </el-col>
            </el-form-item>  
            <el-form-item>
                <el-col :span="6">
                    <el-checkbox label="燃气费用" name="type" :border="true" v-model="test4"></el-checkbox>
                </el-col>
                <el-col :span="6">
                    <el-input v-model="contract.rents.gasFee" type="number" v-show="test4"></el-input>
                </el-col>
            </el-form-item>  
            <el-form-item>
                <el-col :span="6">
                    <el-checkbox label="物业管理费" name="type" :border="true"  v-model="test5"></el-checkbox>
                </el-col>
                <el-col :span="6">
                    <el-input v-model="contract.rents.propertyFee" type="number" v-show="test5"></el-input>
                </el-col>
            </el-form-item>  
            <el-form-item label="备注">
                <el-col :span="12">
                <el-input v-model="contract.rents.remark" type="textarea"></el-input>
                </el-col>   
            </el-form-item>
             <el-form-item size="large">
                <el-button type="primary" class="form-btn-final" @click="onSave">立即生成</el-button>
                <el-button @click="onCancel">取消</el-button>
            </el-form-item>
            </el-form>
        </el-col>
</el-dialog>
</template>
<script>
export default{
    data () {
        let that = this
        return {
            contract: {
                contractStyle: '', // 客户名
                landlordName: '',
                landlordTel: '',
                landlordCID: '',
                tenantName: '',
                tenantTel: '',
                tenantCID: '',
                rent: '',
                rentIdL: '',
                rentFromDate: '',
                rentToDate: '',
                rentDate: '',
                payStyle: '',
                payDate: '',
                contractState: '', // 合同状态
                remark: '', // 备注
                rentingHouseId: '', // 房子Id
                deposit: {
                     deposit: '',
                     remark: '',
                     depositState: ''
                 },
                rents: {
                    houseRent: '',
                    waterFee: '',
                    electricFee: '',
                    netFee: '',
                    gasFee: '',
                    propertyFee: '',
                    remark: '',
                    rentsState: ''
                }
            },
            test1: false,
            test2: false,
            test3: false,
            test4: false,
            test5: false,
            rentingHouse: {},
            centerDialogVisible: false,  // 控制弹出框
            pickerOption: {
                disableDate (time) {
                    return time.getTime() > new Date(that.contract.rentFromDate).getTime()
                }
            } // 控制结束时间比开始时间晚    
        }
    },
    mounted () {
	},
    computed: {
       totalPrice () {
            this.contract.rents.houseRent = (Math.floor((this.contract.rentToDate - this.contract.rentFromDate) / 1000 / 60 / 60 / 24)) * (this.contract.rent) / 30
            return this.contract.rents.houseRent
       }
    },
    methods: {
        /** 
         * 创建保存
        */
        onSave () {
            debugger
            let self = this
            self.$confirm('是否确认添加新合同？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then(() => {
                if (self.checkData(self.contract)) {
                        self.$backend.request(self.$api.contract.addContract, self.contract)
                            .then((response) => {
                                self.$message.success('保存成功！')
                                self.$parent.$parent.onSearch(1)
                                this.centerDialogVisible = false
                                this.init()
                            })
                    }
            })
        },
        init () {
            this.$refs['contract'].resetFields()
            this.contract = {
                contractStyle: '', // 客户名
                landlordName: '',
                landlordTel: '',
                landlordCID: '',
                tenantName: '',
                tenantTel: '',
                tenantCID: '',
                rent: '',
                rentIdL: '',
                rentFromDate: '',
                rentToDate: '',
                rentDate: '',
                payStyle: '',
                payDate: '',
                contractState: '', // 合同状态
                remark: '', // 备注
                rentingHouseId: '' // 房子Id
            }
            this.contract.deposit = {
                deposit: '',
                remark: '',
                depositState: ''
            }
            this.contract.rents = {
                houseRent: '',
                waterFee: '',
                electricFee: '',
                netFee: '',
                gasFee: '',
                propertyFee: '',
                remark: '',
                rentsState: ''            
            }
            this.test1 = ''
            this.test2 = ''
            this.test3 = ''
            this.test4 = ''
            this.test5 = ''
        },
        /** 
         * 取消
        */
        onCancel () {
            this.centerDialogVisible = false
            this.init()
        },
        /**
         * 数据校验
         */
        checkData (contract) {
            return  true
        }
    }
}
</script>