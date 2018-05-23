<!-- 
    文件：src/views/rentingHouse/list.vue
    作者：
    时间：
    描述：租金列表页面
-->
<template>
    <el-row>
      <!--搜索区域-->
        <el-col :span="24" class="table-search">
            <el-col :span="8" class="table-input">
              <el-input v-model="searchInfo.phoneNumber" placeholder="请输入房租大小"></el-input>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" @click="onSearch(1)">查询</el-button>
              <el-button type="primary"  @click="onReset">重置</el-button>
              <el-button type="danger"  @click="handleDelete">删除</el-button>
            </el-col>
        </el-col>
        <!--列表区域-->
        <el-col :span="24">
            <el-table :data="rentList" border class="el-table-jt" @selection-change="handleChange">
              <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column type="index" label="序号" width="70">
              </el-table-column>
              <el-table-column prop="tenant.tenantName" label="租户" width="220">
              </el-table-column>
              <el-table-column prop="houseRent" label="房租" width="120">
              </el-table-column>  
              <el-table-column prop="waterFee" label="水费" width="120">
              </el-table-column>   
              <el-table-column prop="electricFee" label="电费" width="120">
              </el-table-column>   
              <el-table-column prop="netFee" label="网费" width="120">
              </el-table-column>   
              <el-table-column prop="gasFee" label="燃气费" width="120">
              </el-table-column>  
              <el-table-column prop="propertyFee" label="物业管理费" width="120">
              </el-table-column>    
              <el-table-column prop="updateTime" label="更新时间">
              </el-table-column>
              <el-table-column label="操作">
                  <template scope="scope">
                    <el-button type="primary" size="small" @click="onShowDetail(scope.row)">查看详情</el-button>
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
          <el-col :span="24" class="el-addRole">
              <addRent ref="addRent"></addRent>
        </el-col>
    </el-row>
</template>

<script>
import addRent from './addRent'
import { tabsUtils } from 'components/common'
import { mapGetters } from 'vuex'
  export default {
     components: {
        addRent,
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
         rentList: [], // 房租列表数据
         total: 0, // 总记录数
         checkedList: [], // 选中列表
         searchInfo: {
             pageNum: 1, // 当前页
             pageSize: 10, // 每页多少条记录
             roleName: '' // 权限名称
        }
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
		  	self.$backend.request(self.$api.Rent.queryRentBatch, this.searchInfo)
			  	.then((res) => {
			  	 self.total = res.data.total
                // self.searchInfo.pageNum = res.data.pageNum
                self.rentList = res.data.list
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
		   * 新增
		   */
        onAdd () {
           this.$refs.addRent.centerDialogVisible = true
        },
      /**
       *查看租金详情
       */
        onShowDetail (item) {
            tabsUtils.open(this.value, {
              id: item.rentId,
              title: '租金详情',
              isClosed: true,
              component: resolve => require(['./detail'], resolve),
              model: item.rentId
          })
        },
      /**
       *删除
       */
        handleDelete () {
            let self = this
			if (self.checkedList.length > 0) {
				self.$backend.request(self.$api.Rent.deleteRents, self.checkedList)
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
.el-addRole{
    margin-left: 25%
}
</style>
