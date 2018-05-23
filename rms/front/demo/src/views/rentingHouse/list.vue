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
              <el-input v-model="searchInfo.phoneNumber" placeholder="请输入电话号码或者面积大小"></el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="primary" @click="onSearch(1)">查询</el-button>
              <el-button type="danger" @click="handleDelete">批量删除</el-button>
              <el-button type="primary"  @click="onReset">重置</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="primary"  @click="onAdd">新增房源</el-button>
            </el-col>
        </el-col>
        <!--列表区域-->
        <el-col :span="24">
            <el-table :data="rentingHouseList" border class="el-table-jt"  @selection-change="handleChange">
              <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column type="index" label="序号" width="70">
              </el-table-column>
              <el-table-column prop="address" label="地址" width="220">
              </el-table-column>
              <el-table-column prop="community" label="小区" width="120">
              </el-table-column>
              <el-table-column prop="phoneNumber" label="电话号码" width="140">
              </el-table-column>
              <el-table-column prop="area" label="面积(m²)" width="100">
              </el-table-column> 
              <el-table-column prop="landlord" label="房东"  width="100">
              </el-table-column>        
              <el-table-column prop="updateTime" label="更新时间" width="200">
              </el-table-column> 
              <el-table-column label="操作">
                  <template scope="scope">
                    <el-button type="primary" size="small" @click="onShowDetail(scope.row)">查看详情</el-button>
                    <el-button type="primary" size="small" @click="onAddContract(scope.row)">签订合同</el-button>
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
          <el-col>
              <span>
			   {{this.add}}
              </span>
          </el-col>
              <addRentingHouse ref="renthouse"></addRentingHouse>
              <addContract ref="addContract"></addContract>
    </el-row>
</template>

<script>
import addRentingHouse from './addRentingHouse'
import addContract from '../contract/addContract'
import { tabsUtils } from 'components/common'
import { mapGetters } from 'vuex'
  export default {
     components: {
        addRentingHouse,
        tabsUtils,
        addContract
    },
    props: {
        // tabs 组件实例
        value: Object
    },
    data () {
      return {
         rentingHouseList: [], // 房屋列表数据
         total: 0, // 总记录数
         checkedList: [], // 选中列表
         searchInfo: {
             pageNum: 1, // 当前页
             pageSize: 10, // 每页多少条记录
             phoneNumber: '' // 社区
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
		  	self.$backend.request(self.$api.rentingHouse.queryRentingHousesBatch, this.searchInfo)
			  	.then((res) => {
			  	    self.total = res.data.total
              // self.searchInfo.pageNum = res.data.pageNum
              self.rentingHouseList = res.data.list
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
		   * 新增房源
		   */
        onAdd () {
            this.$refs.renthouse.centerDialogVisible = true
            // console.log(this.add)
        },
        // 签约
        onAddContract (item) {
            this.$refs.addContract.centerDialogVisible = true
            this.$refs.addContract.contract.rentingHouseId = item.rentingHouseId
            this.$refs.addContract.rentingHouse = item
            console.log(item)
            console.log(this.$refs.addContract.rentingHouse)
        },
      /**
       *查看房源详情
       */
        onShowDetail (item) {
            tabsUtils.open(this.value, {
              id: item.rentingHouseId,
              title: item.community + '详情',
              isClosed: true,
              component: resolve => require(['./detail'], resolve),
              model: item.rentingHouseId
          })
        },
        // 删除
        handleDelete () {
            let self = this
             self.$confirm('是否删除选中列表？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then(() => {
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
        })
        }
    }
  }
</script>
<style>
.el-addRentingHouse{
    margin-left: 25%
}
</style>
