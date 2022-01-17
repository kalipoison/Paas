<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>通知</el-breadcrumb-item>
      <el-breadcrumb-item>通知栏信息列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入通知内容" v-model="queryInfo.query" clearable @clear="getNotifylist">
            <el-button slot="append" icon="el-icon-search" @click="getNotifylist"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="loadAddDialogVisible">添加 通知</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- notify 列表区域 -->
      <el-table :data="notifyList" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="createTime" label="通知发布时间"></el-table-column>
        <el-table-column prop="notifyContent" label="通知内容"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="编辑 notify 信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="warning"
                icon="el-icon-edit"
                size="mini"
                content="编辑 notify 信息"
                circle
                @click="showEditDialog(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除 notify"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                content="删除 notify"
                circle
                @click="removeNotify(scope.row)"
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

    <!-- 添加 Notify 的对话框 -->
    <el-dialog title="新增 Notify" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addNotifyForm"
        ref="addNotifyFormRef"
        label-width="100px"
      >
        <el-form-item label="通知内容" prop="notifyContent">
          <el-input type="textarea"  v-model="addNotifyForm.notifyContent" :autosize="{ minRows: 7}" @input="change($event)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addNotifyValue">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑 ProdPropValue 的对话框 -->
    <el-dialog title="编辑 ProdPropValue" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="editNotifyForm"
        ref="editNotifyFormRef"
        label-width="100px"
      >
        <el-form-item label="商品属性值" prop="notifyContent">
          <el-input v-model="editNotifyForm.notifyContent" @input="change($event)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editNotifyValue">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data () {
    return {
      // 获取 Notify 列表查询参数对象
      queryInfo: {
        query: '',
        // 当前页数
        pagenum: 1,
        // 每页显示多少数据
        pagesize: 5
      },
      notifyList: [],
      prodProplist: [],
      totle: 0,
      // 添加 notify 对话框
      addDialogVisible: false,
      // notify 添加
      addNotifyForm: {
        notifyContent: '',
      },
      // 编辑 notify 对话框
      editDialogVisible: false,
      // 编辑 notify
      editNotifyForm: {
        notifyContent: '',
        notifyId: '',
      },
    }
  },
  created () {
    this.getNotifylist()
  },
  methods: {
    async getNotifylist () {
        const { data: res } = await this.$http.get('/auth/notify', {
          params : {
            notifyContent : this.queryInfo.query,
          }
        })
        console.info('getNotifylist', res)
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.notifyList = res.data;
        this.totle = res.count;
        console.info("userlist", this.notifyList);
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getNotifylist()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getNotifylist()
    },
    // 监听 添加 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addNotifyFormRef.resetFields()
    },
    // 添加 notify 前加载信息
    loadAddDialogVisible () {
      this.addDialogVisible = true
    },
    // 添加 notify
    addNotifyValue () {
      console.info('addNotifyValue this.addNotifyForm', this.addNotifyForm)
      // 提交请求前，表单预验证
      this.$refs.addNotifyFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/notify', {
          ...this.addNotifyForm,
        })
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success("创建 Notify 成功")
        console.info('Notify', res)
        // 隐藏添加对话框
        this.addDialogVisible = false
        this.getNotifylist()
      })
    },
    // 展开 编辑 notify 对话框
    async showEditDialog (serviceInfo) {
      this.editDialogVisible = true
      const { data: res } = await this.$http.get('/auth/notify/detail', {
        params : {
          id: serviceInfo.notifyId
        }
      })
      if (!res.success) {
        this.$message.error(res.message)
      }
      console.info('notify detail' , res)
      this.editNotifyForm = res.data
    },
    // 更新 notify 信息
    async editNotifyValue (serviceInfo) {
      const { data: res } = await this.$http.put('/auth/notify', {
        notifyId: this.editNotifyForm.notifyId,
        notifyContent: this.editNotifyForm.notifyContent,
      })
      this.editDialogVisible = false
      this.getNotifylist()
    },
    // 删除 notify
    async removeNotify (serviceInfo) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该 notify , 是否继续?',
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
      console.info('delete notify', serviceInfo);
      const { data: res } = await this.$http.delete('/auth/notify', {
        params: {
          id : serviceInfo.notifyId
        }
      })
      console.info('res', res)
      if (!res.success) return this.$message.error('删除 notify 失败！')
      this.$message.success('删除 notify 成功！')
      this.getNotifylist()
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed () {
      this.$refs.editNotifyFormRef.resetFields()
    },
    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
