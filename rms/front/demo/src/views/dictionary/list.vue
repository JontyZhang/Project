<!-- 
    文件：src/views/rentingHouse/list.vue
    作者：
    时间：
    描述：租房列表页面
-->

<template>
    <el-row>
      <!--搜索区域-->
        <el-col :span="24" class="table-search">
            <el-col :span="6" class="table-input">
              <el-input v-model="searchInfo.phoneNumber" placeholder="请输入字典值"></el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="primary" @click="onSearch(1)">查询</el-button>
              <el-button type="danger" @click="handleDelete">批量删除</el-button>
              <el-button type="primary"  @click="onReset">重置</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="primary"  @click="onAdd">新增字典</el-button>
            </el-col>
        </el-col>
        <!--列表区域-->
        <el-col :span="24">
            <el-table :data="dictionaryList" border class="el-table-jt"  @selection-change="handleChange">
              <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column type="index" label="序号" width="70">
              </el-table-column>
              <el-table-column prop="dictionaryId" label="字典id" width="600">
              </el-table-column>
              <el-table-column prop="typeCode" label="typeCode" width="180">
              </el-table-column>
              <el-table-column prop="code" label="code" width="140">
              </el-table-column>
              <el-table-column prop="value" label="value" width="100">
              </el-table-column>
              <el-table-column prop="remark" label="备注" >
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
          <el-col :span="24" class="el-addDictionary">
              <addDictionary ref="dictionary"></addDictionary>
        </el-col>
    </el-row>
</template>

<script>
import addDictionary from './addDictionary'
import { tabsUtils } from 'components/common'
import { mapGetters } from 'vuex'
  export default {
     components: {
        addDictionary,
        tabsUtils
    },
    props: {
        // tabs 组件实例
        value: Object
    },
    data () {
      return {
         dictionaryList: [], // 字典列表数据
         total: 0, // 总记录数
         checkedList: [], // 选中列表
         searchInfo: {
             pageNum: 1, // 当前页
             pageSize: 10, // 每页多少条记录
             code: '' // 社区
        }
      }
    },
    mounted () {
        this.onSearch(1)
	  },
    methods: {
     // 查询
     onSearch (index) {
		let self = this
        self.allChecked = false
        if (index) {
                self.searchInfo.pageNum = index
            }
		  	self.pageNum = index
		  	self.searchInfo.pageSize = 10
		  	self.$backend.request(self.$api.dictionaryManager.queryDictionaryBatch, this.searchInfo)
			  	.then((res) => {
			  	    self.total = res.data.total
              // self.searchInfo.pageNum = res.data.pageNum
                    self.dictionaryList = res.data.list
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
		   * 新增字典
		   */
        onAdd () {
            this.$refs.dictionary.centerDialogVisible = true
            // console.log(this.add)
        },
        // 删除
        handleDelete () {
            let self = this
			if (self.checkedList.length > 0) {
				self.$backend.request(self.$api.rentingHouse.deleteRentingHouse, self.checkedList)
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
    .el-addDictionary{
        margin-left: 25%
    }
</style>
