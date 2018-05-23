<template>     
    <el-row :gutter="1" class="detail-container">
        <el-col :span="24">
            <el-col :span="15" style="margin-left: 4%;">
                <el-form ref="form" :model="Msg" label-width="80px" size="mini">
                <el-form-item label="留言编号" >
                    <el-col :span="24">
                        <el-input v-model="Msg.id" :disabled="flag"></el-input>
                    </el-col>   
                </el-form-item>
                <el-form-item label="联系电话" >
                <el-col :span="24">
                    <el-input v-model="Msg.touristNum" :disabled="flag"></el-input>
                </el-col>     
                </el-form-item>
                <el-form-item label="内容" >
                <el-col :span="24">
                    <el-input v-model="Msg.msg"  :disabled="flag"></el-input>
                </el-col>     
                </el-form-item>   
                <el-form-item label="状态" >
                <el-col :span="24">
                   <el-select v-model="Msg.msgState" :disabled="flag">
                      <el-option label="有效" value="0"></el-option>
                     <el-option label="无效" value="1"></el-option>
                   </el-select>
                </el-col>     
                </el-form-item>      
                <el-form-item label="申请时间" >
                <el-col :span="24">
                    <el-input v-model="Msg.createTime"  :disabled="flag"></el-input>
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
            Msg: {
                id: '' // 合同ID
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
          self.Msg.id = this.value
            self.$backend.request(self.$api.MsgManager.queryMsgDetail, this.Msg)
                .then((res) => {
                    self.Msg = res.data
                })
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

