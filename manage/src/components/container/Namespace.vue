<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>容器管理</el-breadcrumb-item>
      <el-breadcrumb-item>namespace管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加命名空间</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- namespace列表区域 -->
      <el-table :data="namespacelist" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="name" label="namespace"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="status" label="状态"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="查看namespace详细信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="success"
                icon="el-icon-more"
                size="mini"
                content="查看namespace详细信息"
                circle
                @click="InfoYamlNamespace(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除namespace"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                content="删除namespace"
                circle
                @click="removeNamespace(scope.row)"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
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

    <!-- 添加namespace的对话框 -->
    <el-dialog title="添加namspace" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addNamespaceForm"
        ref="addNamespaceFormRef"
        label-width="100px"
      >
        <el-form-item label="namespace" prop="namespace">
          <el-input v-model="addNamespaceForm.namespace"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addNamespace">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 更多Pod信息的对话框 -->
    <el-dialog title="namespace详细信息" :visible.sync="infoYamlDialogVisible" width="50%" @close="infoYamlDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="InfoYamlNamespaceForm"
        ref="InfoYamlNamespaceFormRef"
        label-width="100px"
      >
          <pre>{{InfoYamlNamespaceForm.info}}</pre>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="infoYamlDialogVisible = false">取 消</el-button> -->
        <el-button type="primary" @click="infoYamlDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data () {
    return {
      // 获取namespace列表查询参数对象
      queryInfo: {
        query: '',
        // 当前页数
        pagenum: 1,
        // 每页显示多少数据
        pagesize: 5
      },
      namespacelist: [],
      totle: 0,
      // 添加 namespace 对话框
      addDialogVisible: false,
      // 添加 namespace 
      addNamespaceForm: {
        namespace : '',
      },
      // 查看 namespace 对话框
      infoYamlDialogVisible: false,
      // namespace详情 yaml格式
      InfoYamlNamespaceForm: {
        info: ''
      }
    }
  },
  created () {
    this.getNamespaceList()
  },
  methods: {
    async getNamespaceList () {
        const { data: res } = await this.$http.get('/auth/namespace')
        console.info('res', res);
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.namespacelist = res.data;
        this.totle = res.count;
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getNamespaceList()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getNamespaceList()
    },
    // 监听 添加 namespace 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addNamespaceFormRef.resetFields()
    },
    // 添加 namespace
    addNamespace () {
      // 提交请求前，表单预验证
      this.$refs.addNamespaceFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/namespace', {
          ...this.addNamespaceForm
        })
        console.info('addNamespaceForm', res, this.addNamespaceForm);
        if (!res.success) {
          return this.$message.error('添加namespace失败！')
        }
        this.$message.success('添加namespace成功！')
        // 隐藏添加namespace对话框
        this.addDialogVisible = false
        this.getNamespaceList()
      })
    },
    // 删除 namespace
    async removeNamespace (namespaceInfo) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该namespace, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除')
      }
      console.info('delete namespace', namespaceInfo)
      const { data: res } = await this.$http.delete('/auth/namespace', {
        params: {
          namespace : namespaceInfo.name,
        }
      })
      if (!res.success) return this.$message.error('删除namespace失败！')
      this.$message.success('删除namespace成功！')
      this.getNamespaceList()
    },
    async infoYamlDialogClosed() {
      this.$refs.InfoYamlNamespaceFormRef.resetFields()
    },
    async InfoYamlNamespace (namespaceInfo) {
      const { data: res } = await this.$http.get('/auth/namespace/detail', {
        params : {
          namespace: namespaceInfo.name,
        }
      })
      if (!res.success) return this.$message.error(res.message)
      this.InfoYamlNamespaceForm.info = res.data
      this.infoYamlDialogVisible = true
    }
  }
}
</script>

<style lang="less" scoped>
</style>
