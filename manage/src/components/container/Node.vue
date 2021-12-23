<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>容器管理</el-breadcrumb-item>
      <el-breadcrumb-item>Node管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="10">
          <el-input placeholder="请输入node名称" v-model="queryInfo.query" clearable @clear="getNodeList">
            <el-button slot="append" icon="el-icon-search" @click="getNodeList"></el-button>
          </el-input>
        </el-col>
      </el-row>
      <!-- 用户列表区域 -->
      <el-table :data="nodelist" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="nodeName" label="node名称"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="kernelVersion" label="内核版本"></el-table-column>
        <el-table-column prop="kubeProxyVersion" label="kubeProxy版本"></el-table-column>
        <el-table-column prop="kubeletVersion" label="kubelet版本"></el-table-column>
      </el-table>
      <!-- 分页区域 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 5, 10, 15]"
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
      // 获取用户列表查询参数对象
      queryInfo: {
        query: '',
        // 当前页数
        pagenum: 1,
        // 每页显示多少数据
        pagesize: 5
      },
      nodelist: [],
      totle: 0,
    }
  },
  created () {
    this.getNodeList()
  },
  methods: {
    async getNodeList () {
        const { data: res } = await this.$http.get('/auth/node')
        if (!res.success) {
            return this.$message.error('获取node列表失败！')
        }
        this.nodelist = res.data;
        this.totle = res.count;
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getNodeList()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getNodeList()
    },
    
  }
}
</script>

<style lang="less" scoped>
</style>
