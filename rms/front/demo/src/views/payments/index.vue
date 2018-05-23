<template>
  <el-row :gutter="10">
      <el-col :span="24">
          <el-col :span="12">
              <div id="myChart" :style="{width: '600px', height: '500px'}"></div>
          </el-col>
          <el-col :span="12">
               <div id="myChart2" :style="{width: '600px', height: '500px'}"></div>
          </el-col>        
      </el-col>
  </el-row>
</template>
<script>
// 引入基本模板
var echarts = require('echarts/lib/echarts')
// 引入柱状图
require('echarts/lib/chart/pie')
// 引入提示框和标题组件
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
export default{
    data () {
        return {

        }
    },
    mounted () {
        this.creatE()
    },
    methods: {
        creatE () {
             let myChart = echarts.init(document.getElementById('myChart'))
             let myChart2 = echarts.init(document.getElementById('myChart2'))
            // 绘制图表
             myChart.setOption({
                title: {text: '租金收入'},
                tooltip: {},
                xAxis: {
                data: ['茂名', '云浮', '深圳', '佛山', '东莞', '广州']
            },
              yAxis: {},
             series: [{
                name: '销量',
                type: 'bar',
                data: [3000, 6000, 30000, 8000, 12000, 16000]
            }]
            })
            // 绘制图表2
            myChart2.setOption({
                title: {text: '费用占比'},
                series: {
                type: 'pie',
                data: [
                    {name: '押金', value: 1212},
                    {name: '租金', value: 2323},
                    {name: '杂费', value: 1919}
                ]
             }
            })
        }
    }
}
</script>
