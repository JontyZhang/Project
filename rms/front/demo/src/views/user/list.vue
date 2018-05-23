<template>
    <el-row>
      <!--搜索区域-->
        <el-col :span="24" class="table-search">
            <el-col :span="8" class="table-input">
              <el-input v-model="searchInfo.userName" placeholder="请输入用户名字"></el-input>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" @click="onSearch(1)">查询</el-button>
              <el-button type="primary"  @click="onReset">重置</el-button>
              <el-button type="danger"  @click="handleDelete">删除</el-button>
            </el-col>
        </el-col>
        <!--列表区域-->
        <el-col :span="24">
            <el-table :data="userList" border class="el-table-jt" @selection-change="handleChange">
            <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column type="index" label="序号" width="70">
              </el-table-column>
              <el-table-column prop="userName" label="用户名字" width="220">
              </el-table-column> 
              <el-table-column prop="userSex" label="性别" width="120">
              </el-table-column>              
              <el-table-column prop="phoneNum" label="电话" width="150">
              </el-table-column> 
              <el-table-column prop="userCID" label="身份证号码" width="200">
              </el-table-column>              
              <el-table-column prop="updateTime" label="更新时间" width="200">
              </el-table-column> 
              <el-table-column label="角色" >
                <template scope = 'scope'>
                    <span v-for="(item,index) in scope.row.roleList">
			        {{item.roleName}}
                    </span>
                </template>
              </el-table-column> 
              <el-table-column label="操作">
                  <template scope="scope">
                      <el-button type="primary" size="small" @click="onShowDetail(scope.row)">查看详情</el-button>
                    <el-button type="primary" size="small" @click="onShowDetail(scope.row)">分配角色</el-button>
                  </template>
             </el-table-column>   
             </el-table>
        </el-col>
        <!--分页按钮-->
        <el-col :span="24" class="el-table-page">
            <!-- 组件 分页-->
              <el-pagination
                @current-change="onSearch"
                :current-page="searchInfo.pageNum"
                :page-size="searchInfo.pageSize"
                layout="total, prev, pager, next, jumper"
                :total="total">
              </el-pagination>
          </el-col>
          <el-col :span="24" class="el-addUser">
              <addUser v-show="add"></addUser>
        </el-col>
    </el-row>
</template>
<script>
import addUser from './addUser'
import { tabsUtils } from 'components/common'
import { mapGetters } from 'vuex'
  export default {
     components: {
        addUser,
        tabsUtils
    },
    props: {
        // tabs 组件实例
        value: Object
    },
    watch: {
        isChange () {
            if (this.isChange) {
                this.onSearch(null)
            }
        }
    },
    data () {
      return {
         checkedList: [], // 勾选中的内容
         userList: [], // 用户列表数据
         user: '', // 删除的User
         total: 0, // 总记录数
         searchInfo: {
             pageNum: 1, // 当前页
             pageSize: 10, // 每页多少条记录
             userName: '' // 社区
        },
         add: false // 是否弹出新增房源框
      }
    },
    mounted () {
	    this.onSearch(1)
	  },
    methods: {
     /**
		 * 查询
		 */
     onSearch (index) {
	    let self = this
        self.allChecked = false
        if (index) {
                self.searchInfo.pageNum = index
            }
		  	self.pageNum = index
		  	self.searchInfo.pageSize = 10
		  	self.$backend.request(self.$api.user.queryUserBatch, this.searchInfo)
			  	.then((res) => {
			  	    self.total = res.data.total
              // self.searchInfo.pageNum = res.data.pageNum
              self.userList = res.data.list
			})
      },
      /**
		  * 重置
		  */
        onReset () {
            this.searchInfo = {}
            this.onSearch(1)
        },
        /** 
         * 复选框状态改变
        */
        handleChange (val) {
            this.checkedList = val
        },
      /**
		   * 新增用户
		   */
        onAdd () {
            this.add = true
            console.log(this.add)
        },
      /**
       *查看用户详情
       */
        onShowDetail (item) {
            tabsUtils.open(this.value, {
              id: item.userId,
              title: item.userName + '信息详情',
              isClosed: true,
              component: resolve => require(['./detail'], resolve),
              model: item.userId
          })
        },
      /**
       *删除
       */
        handleDelete () {
            let self = this
			if (self.checkedList.length > 0) {
				self.$backend.request(self.$api.user.deleteUser, self.checkedList)
					.then((response) => {
						self.$message.success('删除成功')
						self.onSearch()
						this.allChecked = false 
					})
				self.dialogVisible = false
			} else {
				self.dialogVisible = false
				return
			}
        }
    }
  }
</script>
<style>
.el-addUser{
    margin-left: 25%
}
</style>
