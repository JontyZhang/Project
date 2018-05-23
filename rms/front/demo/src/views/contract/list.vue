<!-- 
    文件：src/views/contract/list.vue
    作者：
    时间：
    描述：合同列表页面
-->
<template>
    <el-row>
      <!--搜索区域-->
        <el-col :span="24" class="table-search">
            <el-col :span="8" class="table-input">
              <el-input v-model="searchInfo.tenantName" placeholder="请输入客户姓名"></el-input>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" @click="onSearch(1)">查询</el-button>
              <el-button type="primary"  @click="onReset">重置</el-button>
              <el-button type="danger"  @click="handleDelete">删除</el-button>
            </el-col>
        </el-col>
        <!--列表区域-->
        <el-col :span="24">
            <el-table :data="contractList" border class="el-table-jt" @selection-change="handleChange">
              <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column type="index" label="序号" width="70">
              </el-table-column>
              <el-table-column prop="tenantName" label="客户姓名" width="220">
              </el-table-column>
              <el-table-column prop="tenantSex" label="客户性别" width="120">
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
          <el-col :span="24" class="el-addContract">
              <addContract ref="addContract"></addContract>
        </el-col>
    </el-row>
</template>

<script>
import addContract from './addContract'
import { tabsUtils } from 'components/common'
import { mapGetters } from 'vuex'
  export default {
     components: {
        addContract,
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
         contractList: [], // 合同列表数据
         total: 0, // 总记录数
         checkedList: [], // 选中列表
         searchInfo: {
             pageNum: 1, // 当前页
             pageSize: 10, // 每页多少条记录
             tenantName: '' // 客户名称
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
		  	self.$backend.request(self.$api.contract.queryContractBatch, this.searchInfo)
			  	.then((res) => {
			  	 self.total = res.data.total
                // self.searchInfo.pageNum = res.data.pageNum
                self.contractList = res.data.list
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
		   * 新增客户
		   */
        onAdd () {
           this.$refs.addContract.centerDialogVisible = true
        },

      /**
       *查看客户详情
       */
        onShowDetail (item) {
            tabsUtils.open(this.value, {
              id: item.contractId,
              title: '合同详情',
              isClosed: true,
              component: resolve => require(['./detail'], resolve),
              model: item.contractId
          })
        },
      /**
       *删除
       */
        handleDelete () {
            let self = this
			if (self.checkedList.length > 0) {
				self.$backend.request(self.$api.contract.queryContractByContractId, self.checkedList)
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
.el-addContract{
    margin-left: 25%
}
</style>
