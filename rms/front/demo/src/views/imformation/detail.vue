<template>
  <el-row>
      <el-col>
        <el-dialog
            title="公寓详情"
            :visible.sync="centerDialogVisible"
            :fullscreen = "true"
            :close-on-press-escape = "true" 
            width="100%"
            heigh
            center>
            <el-col :span="24">
                <!--房子基本信息-->
                <el-col :span="16"  class="baseMsg-container">
                    <el-col>
                    <el-col  class="d-titile">
                       <span class="d-titile">[{{rentingHouseDetail.community}}]</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>{{rentingHouseDetail.title}}</span>
                    </el-col>
                    <el-col :span="24" class="baseMsg">
                        <el-col :span="12">
                             <span>面积：{{rentingHouseDetail.area}}</span>
                        </el-col>
                        <el-col :span="12">
                            <span>价格:  {{rentingHouseDetail.rent}}</span>
                        </el-col>
                    </el-col>
                    <el-col :span="24" class="baseMsg">
                        <el-col :span="12">
                            <span>朝向:{{rentingHouseDetail.houseFace}}</span>
                        </el-col>
                        <el-col :span="12">
                            <span>房型:{{rentingHouseDetail.houseStyle}}</span>
                        </el-col>
                    </el-col>
                    <el-col :span="24" class="baseMsg">
                        <el-col :span="12">
                            <el-radio-group v-model="rentingHouseDetail.rentingStyle" size="medium" :disabled="flag">
                            <el-radio border label="0">整租</el-radio>
                            <el-radio border label="1">合租</el-radio>
                </el-radio-group>
                        </el-col>
                        <el-col :span="12">
                            <span>小区:{{rentingHouseDetail.community}}</span>
                    </el-col>
                     <el-col :span="24" style="margin-top:5%">
                         <el-col :span="16">
                            <span>详细地址:{{rentingHouseDetail.address}}</span>
                         </el-col>   
                    </el-col>
                    </el-col>
                     <!--房间描述-->
                     <el-col :span="24" style="margin-top:5%">
                         <el-col>
                            <span class="d-titile">房间描述</span>
                         </el-col>
                         <el-col>
                            <span>{{rentingHouseDetail.remark}}</span>
                         </el-col>                       
                    </el-col>
                    <!--中国好舍友-->
                    <el-col>
                        
                    </el-col>
                    </el-col>
                </el-col>
                <!--房东信息-->
                <el-col :span="8">
                    <!--头像-->
                    <el-col :span="24" class="photoQAQ">
                        <el-col  :span="24"  v-if="rentingHouseDetail.user.userSex === '1'">
                            <img src="~assets/img/male.png">
                        </el-col>
                        <el-col :span="24" v-else-if="rentingHouseDetail.user.userSex === '0'">
                            <img src="~assets/img/female.png">
                        </el-col>
                    </el-col>
                    <el-col :span="24" class="telQAQ">
                        <span style="font-color:green;font-size:15px;font-weight:bold ;">{{rentingHouseDetail.user.userName}}</span>
                    </el-col>
                     <!--电话-->
                    <el-col :span="24" class="telQAQ">
                        <span style="color:green;font-size:15px;font-weight:bold ;">{{rentingHouseDetail.user.phoneNum}}</span>
                    </el-col>
                    <el-col :span="24" style="padding-left:35%">
                        <el-button @click="onMsg">联系</el-button>
                    </el-col>
                    <el-col  :span="24" style="padding-left:5%">
                        <img style ="width:300px;height:250px ;" v-bind:src="rentingHouseDetail.images">
                    </el-col>
                </el-col>
            </el-col>
            <!--
            <span slot="footer" class="dialog-footer">
                <el-button @click="centerDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
            </span>
            -->
        </el-dialog>         
      </el-col>
       <msg ref="msg"></msg>
  </el-row>
</template>
<script>
import msg from './msg'
export default {
    components: {
        msg
    },
    data () {
        return {
                rentingHouseId: '',
                centerDialogVisible: false, // 是否打开对话框
                rentingHouseDetail: {
                    user: {
                        userSex: ''
                    }
                },
                detailSearch: {
                    rentingHouseId: '',
                    user: {
                        userSex: ''
                    }
                },
                flag: true
        }
    },
    mounted () {
    },
    methods: {
        onMsg () {
            debugger
            console.log(this.rentingHouseDetail.rentingHouseId)
            this.$refs.msg.centerDialogVisible = true
            this.$refs.msg.msgDetail.rentingHouseId = this.rentingHouseDetail.rentingHouseId
        }
    }
}
</script>
<style>
.baseMsg-container{
     padding: 5% 5% 5% 3%;
     border:1px solid black;
}
.baseMsg{
    padding: 5%;
}
.d-titile{
    font-weight:bold ;
    font-size:20px;
    padding-bottom: 4%;
    border-bottom: 1px solid red;
}
.photoQAQ{
    margin-left: 18%;
    margin-top: -10%;
}
.telQAQ{
    margin-top: 3%;
    margin-bottom: 3%;
    padding-left: 35%
}
</style>

