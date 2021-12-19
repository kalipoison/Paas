<template>
    <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>系统管理</el-breadcrumb-item>
        <el-breadcrumb-item>权限列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
        <el-table :data="logList" border stripe>
            <el-table-column type="index" label="#"></el-table-column>
            <el-table-column label="操作者" prop="username"></el-table-column>
            <el-table-column label="操作名称" prop="operation"></el-table-column>
            <el-table-column label="方法" prop="method"></el-table-column>
            <el-table-column label="参数" prop="params"></el-table-column>
            <el-table-column label="方法响应时间" prop="time"></el-table-column>
            <el-table-column label="IP" prop="ip"></el-table-column>
            <el-table-column label="创建时间" prop="createDate"></el-table-column>
        </el-table>
            <!-- 分页区域 -->
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="queryInfo.pagenum"
            :page-sizes="[2, 5, 10, 15]"
            :page-size="queryInfo.pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totle"
        ></el-pagination>
        </el-card>
    </div>
</template>

<script>
export default {
  data () {
    return {
        // 权限列表
        logList: [],
        queryInfo: {
            query: '',
            // 当前页数
            pagenum: 1,
            // 每页显示多少数据
            pagesize: 5
        },
        totle: 0,
    }
  },
    created () {
        this.getLogList()
    },
    methods: {
        async getLogList () {
            const { data: res } = await this.$http.get('/auth/log',{
                current: this.queryInfo.pagenum,
                size: this.queryInfo.pagesize,
                username: this.queryInfo.query,
            });
            this.logList = res.data;
            this.totle = res.count;
            console.info('this.logList', this.logList);
        },
        handleSizeChange (newSize) {
        this.queryInfo.pagesize = newSize
        this.getLogList()
        },
        handleCurrentChange (pagenum) {
            this.queryInfo.pagenum = pagenum
            this.getLogList()
        },
    }
}
</script>

<style lang='less' scoped>
</style>
