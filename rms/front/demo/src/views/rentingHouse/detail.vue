<template>     
    <el-row :gutter="1" class="detail-container">
        <el-col :span="24">
            <el-col :span="7">
                <el-card :body-style="{ padding: '0px'}">
                <img  v-bind:src="RentingHouse.images" class="image">
                <div style="padding: 14px;">
                    <span>{{ RentingHouse.community }}</span>      
                    <span>{{ RentingHouse.landlord }}</span>   
                    <span>{{ RentingHouse.phoneNumber }}</span>          
                </div>
                </el-card>
            </el-col>
            <el-col :span="15" style="margin-left: 4%;">
                <el-form ref="form" :model="RentingHouse" label-width="80px" size="mini">
                <el-form-item label="小区名称" >
                    <el-col :span="4">
                        <el-input v-model="RentingHouse.community" :disabled="flag"></el-input>
                    </el-col>   
                </el-form-item>
                <el-form-item label="房源板块" :offset='5'>
                    <el-col :span="3">
                    <el-input v-model="RentingHouse.provienceId" :disabled="true"></el-input>
                    </el-col>  
                    <el-col :span="1">-</el-col>
                    <el-col :span="3">
                    <el-input v-model="RentingHouse.cityId" :disabled="true"></el-input>
                    </el-col> 
                    <el-col :span="1">-</el-col>
                    <el-col :span="3">
                    <el-input v-model="RentingHouse.countryId" :disabled="true"></el-input>
                    </el-col>  
                </el-form-item>
                <el-form-item label="详细地址" >
                    <el-col :span="12">
                    <el-input v-model="RentingHouse.address" :disabled="flag"></el-input>
                    </el-col>   
                </el-form-item>
                <el-form-item label="户型">
                    <el-radio-group v-model="RentingHouse.houseStyle" size="medium" :disabled="flag">
                    <el-radio border label="1">一房</el-radio>
                    <el-radio border label="2">二房</el-radio>
                    <el-radio border label="3">三房</el-radio>
                    <el-radio border label="4">四房</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="面积" >
                <el-col :span="6">
                    <el-input v-model="RentingHouse.area" placeholder="单位为平方米" :disabled="flag"></el-input>
                </el-col>   
                </el-form-item>
                <el-form-item label="租金" >
                <el-col :span="6">
                    <el-input v-model="RentingHouse.rent" placeholder="单位为元/月" :disabled="flag"></el-input>
                </el-col>     
                </el-form-item>
                <el-form-item label="出租类型">
                    <el-radio-group v-model="RentingHouse.rentingStyle" size="medium" :disabled="flag">
                    <el-radio border label="0">整租</el-radio>
                    <el-radio border label="1">合租</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="租赁情况">
                    <el-radio-group v-model="RentingHouse.rantingState" size="medium" :disabled="flag">
                    <el-radio border label="0">已租</el-radio>
                    <el-radio border label="1">未租</el-radio>
                    <el-radio border label="2">停用</el-radio>
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
            <el-col :span="1">
                <span>haha</span>
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
            RentingHouse: {},
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
          console.log('详细页面')
          console.log(this.value)
            self.$backend.request(self.$api.rentingHouse.queryRentingHouse, {rentingHouseId: this.value})
                .then((res) => {
                    self.RentingHouse = res.data
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
                self.$backend.request(self.$api.rentingHouse.updateRentingHouse, self.RentingHouse)
					.then((res) => {
						self.$message.success('保存成功！')
                        self.flag = true
                        self.$store.commit('resource.area.setIsChange', Math.random())
					})
            }
        },
        // 取消
        onCancel () {
             this.flag = true        
        },
        checkData (RentingHouse) {
            if (!RentingHouse) {
                return false
            }
            if (!RentingHouse.community) {
                this.$message.error('小区名称不能为空！')
                return false
            }
            if (!RentingHouse.houseStyle) {
                this.$message.error('户型不能为空！')
                return false
            }
            if (!RentingHouse.rentingStyle) {
                this.$message.error('出租类型不能为空！')
                return false
            }
            if (RentingHouse.rentingState) {
                this.$message.error('租赁情况不能为空！')
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

