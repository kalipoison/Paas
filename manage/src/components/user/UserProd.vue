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
          <el-input placeholder="请输入 完整用户名/邮箱" v-model="queryInfo.query" clearable @clear="getUserProdlist">
            <el-button slot="append" icon="el-icon-search" @click="getUserProdlist"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="loadAddDialogVisible">添加 用户产品</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- userProd 列表区域 -->
      <el-table :data="userProdList" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="username" label="用户名/邮箱"></el-table-column>
        <el-table-column prop="productName" label="产品名"></el-table-column>
        <el-table-column prop="containerName" label="容器名"></el-table-column>
        <el-table-column prop="image" label="镜像"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="expireTime" label="到期时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="编辑 用户产品 信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="warning"
                icon="el-icon-edit"
                size="mini"
                content="编辑 用户产品 信息"
                circle
                @click="showEditDialog(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除 用户产品"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                content="删除 用户产品"
                circle
                @click="removeUserProd(scope.row)"
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

    <!-- 添加 用户产品 的对话框 -->
    <el-dialog title="新增 用户产品" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addUserProdForm"
        ref="addUserProdFormRef"
        label-width="100px"
      >
        <el-form-item label="用户名/邮箱" prop="username">
          <el-input v-model="addUserProdForm.username" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="容器名" prop="containerName">
          <el-input v-model="addUserProdForm.containerName" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="镜像" prop="image">
          <el-input v-model="addUserProdForm.image" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="产品" prop="prodId">
          <el-select v-model="addUserProdForm.prodId" placeholder="请选择">
            <el-option
              v-for="prod in productList"
              :key="prod.prodId"
              :label="prod.prodName"
              :value="prod.prodId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="有效时长" prop="validateDay">
          <el-input v-model="addUserProdForm.validateDay" @input="change($event)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUserProdValue">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑 用户产品 的对话框 -->
    <el-dialog title="编辑 用户产品" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="editUserProdForm"
        ref="editUserProdFormRef"
        label-width="100px"
      >
        <el-form-item label="用户名/邮箱" prop="username" >
          <el-input v-model="editUserProdForm.username" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="容器名" prop="containerName" >
          <el-input v-model="editUserProdForm.containerName" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="镜像" prop="image" >
          <el-input v-model="editUserProdForm.image" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="产品" prop="prodId">
          <el-select v-model="editUserProdForm.prodId" placeholder="请选择">
            <el-option
              v-for="prod in productList"
              :key="prod.prodId"
              :label="prod.prodName"
              :value="prod.prodId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="延迟/天" prop="validateDay" >
          <el-input v-model="editUserProdForm.validateDay" @input="change($event)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUserProdValue">确 定</el-button>
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
      userProdList: [],
      productList: [],
      totle: 0,
      // 添加 notify 对话框
      addDialogVisible: false,
      // notify 添加
      addUserProdForm: {
        username: '',
        validateDay: '',
        image: '',
        containerName: '',
        prodId: '',
      },
      // 编辑 notify 对话框
      editDialogVisible: false,
      // 编辑 notify
      editUserProdForm: {
        userProdId: '',
        prodId: '',
        username: '',
        productName: '',
        image: '',
        containerName: '',
        validateDay: '',
        expireTime: '',
      },
    }
  },
  created () {
    this.getUserProdlist()
  },
  methods: {
    async getUserProdlist () {
        const { data: res } = await this.$http.get('/auth/userProd', {
          params : {
            username : this.queryInfo.query,
          }
        })
        console.info('getUserProdlist', res)
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.userProdList = res.data;
        this.totle = res.count;
        console.info("userlist", this.userProdList);
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getUserProdlist()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getUserProdlist()
    },
    // 监听 添加 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addUserProdFormRef.resetFields()
    },
    // 获取 productList
    async getProductList() {
      const { data: res } = await this.$http.get('/auth/product')
      this.productList = res.data
      console.info('get productList', res)
    },

    // 添加 notify 前加载信息
    loadAddDialogVisible () {
      this.getProductList()
      this.addDialogVisible = true
    },
    // 添加 UserProd
    addUserProdValue () {
      console.info('addUserProdValue this.addUserProdForm', this.addUserProdForm)
      // 提交请求前，表单预验证
      this.$refs.addUserProdFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/userProd', {
          ...this.addUserProdForm,
        })
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success("创建 Notify 成功")
        console.info('Notify', res)
        // 隐藏添加对话框
        this.addDialogVisible = false
        this.getUserProdlist()
      })
    },
    // 展开 编辑 UserProd 对话框
    async showEditDialog (serviceInfo) {
      this.getProductList()
      this.editDialogVisible = true
      const { data: res } = await this.$http.get('/auth/userProd/detail', {
        params : {
          id: serviceInfo.userProdId
        }
      })
      if (!res.success) {
        this.$message.error(res.message)
      }
      console.info('userProd detail' , res)
      this.editUserProdForm = res.data
    },
    // 更新 UserProd 信息
    async editUserProdValue (serviceInfo) {
      console.info('update user prod', this.editUserProdForm)
      const { data: res } = await this.$http.put('/auth/userProd', {
        userProdId: this.editUserProdForm.userProdId,
        prodId: this.editUserProdForm.prodId,
        image: this.editUserProdForm.image,
        prodId: this.editUserProdForm.prodId,
        containerName: this.editUserProdForm.containerName,
        expired: this.editUserProdForm.expireTime,
        validateDay: this.editUserProdForm.validateDay,
      })
      this.editDialogVisible = false
      this.getUserProdlist()
    },
    // 删除 userProd
    async removeUserProd (serviceInfo) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该 用户产品 , 是否继续?',
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
      console.info('delete 用户产品', serviceInfo);
      const { data: res } = await this.$http.delete('/auth/userProd', {
        params: {
          id : serviceInfo.userProdId
        }
      })
      console.info('res', res)
      if (!res.success) return this.$message.error('删除 用户产品 失败！')
      this.$message.success('删除 用户产品 成功！')
      this.getUserProdlist()
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed () {
      this.$refs.editUserProdFormRef.resetFields()
    },
    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
