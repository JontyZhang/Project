<!-- 
    文件：src/views/rentingHouse/list.vue
    作者：
    时间：
    描述：留言列表页面
-->
<template>
    <el-row>
      <!--搜索区域-->
        <el-col :span="24" class="table-search">
            <el-col :span="8" class="table-input">
              <el-input v-model="searchInfo.touristNum" placeholder="请输入留言号码"></el-input>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" @click="onSearch(1)">查询</el-button>
              <el-button type="primary"  @click="onReset">重置</el-button>
              <el-button type="danger"  @click="handleDelete">删除</el-button>
            </el-col>
        </el-col>
        <!--列表区域-->
        <el-col :span="24">
            <el-table :data="MsgList" border class="el-table-jt" @selection-change="handleChange">
              <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column type="index" label="序号" width="70">
              </el-table-column>
              <el-table-column prop="msg" label="留言内容" width="150">
              </el-table-column>
              <el-table-column prop="touristNum" label="联系电话" width="150">
              </el-table-column>   
              <el-table-column prop="createTime" label="更新时间">
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
    </el-row>
</template>

<script>
import { tabsUtils } from 'components/common'
import { mapGetters } from 'vuex'
  export default {
     components: {
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
         MsgList: [], // 留言列表数据
         total: 0, // 总记录数
         checkedList: [], // 选中列表
         searchInfo: {
             pageNum: 1, // 当前页
             pageSize: 10 // 每页多少条记录
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
        debugger
        self.allChecked = false
        if (index) {
                self.searchInfo.pageNum = index
            }
		  	self.pageNum = index
		  	self.searchInfo.pageSize = 10
		  	self.$backend.request(self.$api.MsgManager.queryMsgBatch, this.searchInfo)
			  	.then((res) => {
			  	 self.total = res.data.total
                // self.searchInfo.pageNum = res.data.pageNum
                self.MsgList = res.data.list
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
       *查看留言详情
       */
        onShowDetail (item) {
            tabsUtils.open(this.value, {
              id: item.id,
              title: '留言详情',
              isClosed: true,
              component: resolve => require(['./detail'], resolve),
              model: item.id
          })
        },
      /**
       *删除
       */
        handleDelete () {
            let self = this
			if (self.checkedList.length > 0) {
				self.$backend.request(self.$api.MsgManager.deleteMsgBatch, self.checkedList)
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
