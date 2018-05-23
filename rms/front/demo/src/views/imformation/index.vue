<!-- 
	文件：views/imformation/index
	作者：
	时间：
	描述：首页
 -->
<template>  
    <el-row class="imformation-container">
            <!-- 头-->
            <el-col :span="24" class="imformation-header">
                    <el-col :span="8"  >
                        <img src="~assets/img/logo.jpg" class="Logo-img-style">                   
                    </el-col>
                    <el-col :span="4" class="title-style"><span>Yike</span></el-col>
                    <el-col :span="8" class="imformation-header-menu">                   
                        <el-menu  mode="horizontal" :default-active="$route.path" router>
                            <el-menu-item index="/imformation"  @click="showHome">首页</el-menu-item>
                            <el-menu-item index="/mainList" @click="clearHome">找房</el-menu-item>
                            <el-menu-item index="login">控制台</el-menu-item>                               
                        </el-menu>
                   </el-col>                                             
            </el-col> 
            <el-col :span="24" v-if="clear">
                <img src="~assets/img/xx.jpg">     
            </el-col> 
            <el-col :span="24" v-else style="height:1200px;">
                 <router-view></router-view>    
            </el-col>         
    </el-row>
</template>

<script>
import mainList from './mainList'
export default {
    components: {
        mainList
    },
	data () {
        return  {
         activeIndex: '1',
         activeIndex2: '1',
         clear: true   
        }
	},
    mounted () {
        this.$router.push({name: 'imformation'})
	},
	methods: {
        clearHome () {
            this.clear = false 
            // this.$refs.mainList.getDates()
        },
        showHome () {
            this.clear = true
        }
	}
}
</script>
<style>
.el-menu{
    background-color: white
}
.imformation-foot{
     text-align:center;
}
</style>

