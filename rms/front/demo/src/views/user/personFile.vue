<template> 
   <el-row :gutter="1" class="detail-container">
      <el-col>
          <el-form>
              <el-form-item label="名字" >
                  <el-col :span="4">
                      <el-input v-model="user.userName" :disabled="flag"></el-input>
                  </el-col>   
              </el-form-item>
              <el-form-item label="性别" >
                  <el-col :span="4">
                      <el-input v-model="user.userSex" :disabled="flag"></el-input>
                  </el-col>   
              </el-form-item>
              <el-form-item label="电话号码" >
                  <el-col :span="4">
                      <el-input v-model="user.phoneNum" :disabled="flag"></el-input>
                  </el-col>   
              </el-form-item>
              <el-form-item label="身份证号码" >
                  <el-col :span="4">
                      <el-input v-model="user.userCID" :disabled="flag"></el-input>
                  </el-col>   
              </el-form-item>    
              <el-form-item>
                  <el-col :span="24">
                      <el-col :span="3">
                          <el-button type="primary" @click="onChange">更改</el-button>
                      </el-col>
                      <el-col :span="3"  v-if="!flag">
                        <el-button @click="onCancel">取消</el-button>
                      </el-col>
                  </el-col>  
              </el-form-item>          
          </el-form>
      </el-col>
  </el-row>
</template>

<script>
export default {
	data () {
		return {
      user: {

      },
      flag: true // 是否可编辑
		}
    },
    mounted () {
      this.onSearch()
    },
    methods: {
      onSearch () {
        let self = this 
        self.$backend.request(self.$api.user.queryUserByBass).then((res) => {
          self.user = res.data 
        })
      },
      onChange () {
         this.flag = false
      },
      onCancel () {
        this.flag = true   
      }
    }   
}
</script>

