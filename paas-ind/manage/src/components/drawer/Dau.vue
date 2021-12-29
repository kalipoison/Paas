<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>仪表盘</el-breadcrumb-item>
      <el-breadcrumb-item>访问量</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
        <h4>总访问量{{totalQuery}}</h4>
        <!-- 2.为Echarts准备一个Dom -->
        <div id="main" style="width: 100%;height:400px;"></div>
    </el-card>
  </div>
</template>

<script>
// 1.导入echarts
import * as echarts from 'echarts'
import _ from 'lodash'

export default {
    data () {
        return {
            totalQuery: "",
            // 需要合并的数据
            options: {
                title: {
                text: '访问量'
                },
                tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                    backgroundColor: '#E9EEF3'
                    }
                }
                },
                grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
                },
                xAxis: [
                {
                    boundaryGap: false
                }
                ],
                yAxis: [
                {
                    type: 'value'
                }
                ]
            }
        }
    },
    created () {
        this.getTotalQuery();
        
    },
    mounted () {
        this.drawLine('main');
    },
    methods: {
        async getTotalQuery () {
            const { data: res } = await this.$http.get('/auth/dau/total')
            this.totalQuery = res;
        },
        async drawLine(id) {
            const { data: res } = await this.$http.get('/auth/dau/month')
            console.info('res', res);
            this.charts = echarts.init(document.getElementById(id))
            this.charts.setOption({
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['访问量']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },

                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                data: ["1","2","3","4","5","6","7","8","9","10","11","12"]
                
                },
                yAxis: {
                    type: 'value'
                },

                series: [{
                    name: '近七日收益',
                    type: 'line',
                    stack: '总量',
                    data: res
                }]
            })
        }
    }
}
</script>

<style lang="less" scoped>

</style>
