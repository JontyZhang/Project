<!-- 
	文件：views/imformation/index
	作者：
	时间：
	描述：首页
 -->
<template>  
    <el-row>                                                  
            <!--主体-->
            <el-col class="imformation-main">
                <house-list ref="houseList"></house-list>
            </el-col>
    </el-row>
</template>

<script>
import houseList from './list'
export default {
    components: {
        houseList
    },
	data () {
        return  {  
        }
	},
    mounted () {
      //  this.$refs.houseList.onSearch(1)
      //  this.$refs.houseList.onSearchCity()
      this.getDates()
	},
	methods: {
      handleSelect (key, keyPath) {
        console.log(key, keyPath)
      },
      // 获取List内容
      getDates () {
        this.$parent.clear = false
        // debugger
        this.$refs.houseList.onSearch(1)
        this.$refs.houseList.onSearchCity()         
      }
	}
}
</script>
<style>
.el-menu{
    background-color: white
}
</style>

