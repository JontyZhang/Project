<!-- 
	文件：views/default/addUser
	作者：zjt
	时间：2017-03-19
	描述：新增用户
 -->
 <template>
  <div v-show="centerDialogVisible">
    <el-dialog
        title="注册"
        :visible.sync="centerDialogVisible"
        :fullscreen="true"
        :before-close="handleClose">
        <el-col :span="24" class="dialog-main-new">
            <el-form :model="user" ref="user"  label-width="80px" size="mini">
            <el-form-item label="姓名" >
                <el-col :span="12">
                <el-input v-model="user.userName"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="密码" >
                <el-col :span="12">
                <el-input  type="password" v-model="user.userPassword"></el-input>
                </el-col>   
            </el-form-item>
             <el-form-item label="手机号码" >
                <el-col :span="12">
                <el-input type="number" v-model="user.phoneNum"></el-input>
                </el-col>   
            </el-form-item>
            <el-form-item label="性别">
                <el-radio-group v-model="user.userSex" size="medium">
                <el-radio border label="0">男</el-radio>
                <el-radio border label="1">女</el-radio>
                </el-radio-group>
            </el-form-item>
            </el-form>
        </el-col>
            <span slot="footer" class="dialog-footer-new">
                <el-button @click="onCancel">取 消</el-button>
                <el-button type="primary" @click="addUser">确 定</el-button>
            </span>
    </el-dialog>
 </div>
 </template>
 <script>
 import CryptoJS from 'crypto-js'
  export default{
      data () {
          return {
              centerDialogVisible: false,
          user: {
                  userName: '',             
                  userPassword: '',
                  phoneNum: '',
                  userSex: ''
                  }
          }
      },
      methods: {
          // 注册用户
        addUser () {
            let self = this
            if (self.checkMsg(self.user)) {
                let password = CryptoJS.MD5(this.user.userPassword).toString()
                self.user.userPassword =  password.toString(CryptoJS.enc.Utf8).toUpperCase()
                this.$backend.request(self.$api.user.addUser, self.user).then((result) => {
                    if (result.success) {
                        this.$message.success('添加成功')
                        this.init()
                    }
                })
            }
        },
        init () {
            this.$refs['user'].resetFields()
            this.user = {
                  userName: '',             
                  userPassword: '',
                  phoneNum: '',
                  userSex: ''
            }
            this.centerDialogVisible = false
        },
        checkMsg (user) {
            if (!user) {
                return false
            }
            if (!user.userName) {
                this.$message.error('用户姓名不能为空')
                return false 
            }
            if (!user.password) {
                this.$message.error('用户密码不能为空')
                return false 
            }
            return  true
        },
        onCancel () { 
         // debugger
            this.centerDialogVisible = false    
            console.log(this.$refs['user']) 
            this.init()        
        }
      }
  }
 </script>
 <style>
 .dialog-main-new{
     padding-left: 20%;
 }
.dialog-footer-new{
    margin-right: 45%;
 }
 </style>
 