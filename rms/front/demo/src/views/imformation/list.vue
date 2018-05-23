<template>
  <el-row style="width:1600px; margin-left:150px;margin-right:200px;">
    <!-- 搜索区域-->
    <el-col :span="24" class="QVQ">
        <el-col :span="1">
            <span class="span_s">区域:</span>        
        </el-col>
        <el-col :span="1" class="condition-QVQ">
            <span class="span_a" @click="clearCondition1">不限</span>        
        </el-col>
        <el-col :span="1" v-for="(city, i) in cityList" class="condition-QVQ"> 
            <span  @click="getCountry(i)">{{ city.label }}</span>
        </el-col>
    </el-col>
    <el-col :span="24" v-if="sel" class="QVQ">
        <el-col :span="1" v-for="label in labels.children" class="condition-QVQ">
            <span  @click="getCity(label)">{{ label.label }}</span>
        </el-col>
    </el-col>
    <el-col :span="24" class="QVQ">
        <el-col :span="1">
            <span class="span_s">户型:</span>
        </el-col>
        <el-col :span="1" class="condition-QVQ">
            <span class="span_a"  @click="clearCondition2">不限</span>        
        </el-col>
        <el-col :span="1" v-for="item in houseStyleList" class="condition-QVQ"> 
            <span @click="getHouseStyle(item)">{{ item.label }}</span>
        </el-col>
    </el-col>
    <el-col :span="24" class="QVQ">
        <el-col :span="1">
            <span class="span_s">方式:</span>
        </el-col>
        <el-col :span="1" class="condition-QVQ">
            <span class="span_a"  @click="clearCondition3">不限</span>        
        </el-col>
        <el-col :span="1" v-for="item in rentingStyleList" class="condition-QVQ"> 
            <span @click="getRentStyle(item)">{{ item.label }}</span>
        </el-col>
    </el-col>
    <el-col :span="24" class="QVQ">
        <el-col :span="1">
            <span class="span_s">月租:</span>
        </el-col>
        <el-col :span="1" class="condition-QVQ">
            <span class="span_a"  @click="clearCondition4">不限</span>        
        </el-col>
         <el-col :span="2" v-for="item in rentList" class="condition-QVQ-m"> 
            <span @click="getRent(item)">{{ item.label }}</span>
        </el-col>
    </el-col> 
    <el-col class="O-O">
    <el-col :span="6" v-for="item in rentingHouseList" class="cardList" :offset="1">
        <el-card :body-style="{ padding: '0px',width:'500px',height:'500px'}">
        <img  v-bind:src="item.images" class="image"  @click="onShowDetails(item.rentingHouseId)">
        <div style="margin-left:20px;margin-top:10px"><span>{{item.address}}</span></div>    
        <div style="margin-left:20px"><span>{{item.rent}}/月</span></div>  
        <div style="padding: 14px;">          
            <span>{{ item.community }}</span>
            <div class="bottom clearfix">
            <time class="time">{{ currentDate }}</time>
            <el-button type="info" class="button" @click="onShowDetails(item.rentingHouseId)"><i class="el-icon-arrow-right"></i></el-button>
            </div>
        </div>
        </el-card>
    </el-col>
    </el-col>
     <!--分页按钮-->
    <el-col :span="24" class="el-table-page" v-if="rentingHouseList.length != 0">
        <!-- 组件 分页-->
            <el-pagination
            @current-change="onSearch"
            :current-page="searchInfo.pageNum"
            :page-size="searchInfo.pageSize"
            layout="total, prev, pager, next, jumper"
            :total="total">
            </el-pagination>
        </el-col>
    <el-col  v-if="rentingHouseList.length == 0" style="margin-left:30%;">
        <img src="~assets/img/noData.jpg">
    </el-col>
    <el-col>
        <detail ref="detail"></detail>
    </el-col>
</el-row>
</template>
<script>
import detail from './detail'
export default {
    components: {
        detail
    },
    data () {
        return {
             currentDate: new Date(), // 当前时间
             rentingHouseList: [], // 房屋列表数据
             houseList: [],
             City: {
                 orgPid: ''
             },
             cityList: '',
             searchInfo: {
                pageNum: 1, // 当前页
                pageSize: 10, // 每页多少条记录
                provienceId: '',
                countryId: '',
                cityId: '',
                houseStyle: '',
                rentingStyle: '',
                rent: ''
             },
             detailSearch: {
                 rentingHouseId: ''
             },
             rentingHouseDetail: {
             },
             houseStyleList: [
                 {
                     label: '一房',
                     value: '1'
                 },
                 {
                     label: '两房',
                     value: '2'
                 },
                 {
                     label: '三方',
                     value: '3'
                 },
                 {
                     label: '四房',
                     value: '4'
                 }
             ], // 房屋类型
             rentingStyleList: [
                 {
                     label: '合租',
                     value: '1'
                 },
                 {
                     label: '整租',
                     value: '0'
                 }
             ], // 出租类型
             rentList: [
                 {
                     label: '    ≤1000',
                     value: '0-999'
                 },
                 {
                     label: '1000-2000',
                     value: '1000-2000'
                 },
                 {
                     label: '2000-3000',
                     value: '2000-3000'
                 },
                 {
                     label: '3000-4000',
                     value: '3000-4000'
                 },
                {
                     label: '4000-5000',
                     value: '4000-5000'
                 },
                {
                     label: '5000-10000',
                     value: '5000-10000'
                 },
                 {
                     label: '≥10000',
                     value: '10000-1000000'
                 }
             ], // 租金范围
             labels: [],
             sel: false
        }
    },
    mounted () {
      //  debugger
        // this.onSearch(1)
        // this.onSearchCity()
    },
    methods: {
    // 查询
     onSearch (index) {
		let self = this
        if (index) {
                self.searchInfo.pageNum = index
            }
		  	self.pageNum = index
		  	self.searchInfo.pageSize = 6
		  	self.$backend.request(self.$api.RentingHouseMsgController.queryRentingHousesBatchFront, self.searchInfo)
			  	.then((res) => {
			  	    self.total = res.data.total
              // self.searchInfo.pageNum = res.data.pageNum
              self.rentingHouseList = res.data.list
			})
      },
      // 查询出广东省所有城市
      onSearchCity () {
          let self = this 
          this.City.orgPid = 500001
          self.$backend.request(self.$api.cityManager.queryAllCityForFront)
                .then((res) => {
                    self.cityList = res.data
                })
      },
      // 获取country
      getCountry (i) {
          this.labels = this.cityList[i]
          this.searchInfo.countryId = this.cityList[i].value
          this.sel = !this.sel
          this.searchInfo.cityId = ''
          this.onSearch(1)
      },
      // 获取city
      getCity (label) {
         // debugger
          this.searchInfo.cityId = label.value
          this.searchInfo.countryId = ''
          this.onSearch(1)
      },
      // 获取户型
      getHouseStyle (item) {
         // debugger
          this.searchInfo.houseStyle = item.value
          this.onSearch(1)
      },
      // 获取出租方式
      getRentStyle (item) {
          this.searchInfo.rentingStyle = item.value
          this.onSearch(1)
      },
      // 获取租金
      getRent (item) {
          this.searchInfo.rent = item.value
          this.onSearch(1)
      },
      clearCondition1 () {
          this.searchInfo.countryId = ''
          this.searchInfo.cityId = ''
          this.onSearch(1)
      },
      clearCondition2 () {
          this.searchInfo.houseStyle = ''
          this.onSearch(1)
      },
      clearCondition3 () {
          this.searchInfo.rentingStyle = ''
          this.onSearch(1)
      },
      clearCondition4 () {
          this.searchInfo.rent = ''
          this.onSearch(1)
      },
      // 查看房子详情
      onShowDetails (rentingHouseId) {
          this.detailSearch.rentingHouseId = rentingHouseId
          this.$refs.detail.rentingHouseId = rentingHouseId
          this.onGetMsgDetail()
          this.$refs.detail.rentingHouseDetail = this.rentingHouseDetail
          this.$refs.detail.centerDialogVisible = true 
      },
      onGetMsgDetail () {   
        let self = this 
        self.$backend.request(self.$api.RentingHouseMsgController.queryMsgDetail, self.detailSearch)
            .then((res) => {
                self.rentingHouseDetail = res.data
        })
    }
    }
}
</script>
<style>
.span_s{
    color:red;
}
.span_a{
    color: blue;
}
.O-O{
    padding: 0%;
}
.cardList{
    padding-top:2%; 
    padding-left: 5%;
    padding-bottom:3%; 
    cursor:pointer;
}
.QVQ{
    margin-left:150px;
    padding-top:20px;
}
.condition-QVQ{
  cursor:pointer;
}
.condition-QVQ-m{
    cursor:pointer;
    width: 120px;
}
  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
    height: 80%;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  } 
</style>