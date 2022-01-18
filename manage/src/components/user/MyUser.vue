<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入 用户名/邮箱" v-model="queryInfo.query" clearable @clear="getMyUserlist">
            <el-button slot="append" icon="el-icon-search" @click="getMyUserlist"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="loadAddDialogVisible">添加 用户</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- 用户 列表区域 -->
      <el-table :data="myUserList" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="email" label="用户名/邮箱"></el-table-column>
        <el-table-column prop="statusContent" label="状态"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="memberContent" label="是否会员"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="编辑 用户 信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="warning"
                icon="el-icon-edit"
                size="mini"
                content="编辑 用户 信息"
                circle
                @click="showEditDialog(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除 用户"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                content="删除 用户"
                circle
                @click="removeMyUser(scope.row)"
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

    <!-- 添加 User 的对话框 -->
    <el-dialog title="新增 User" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addMyUserForm"
        ref="addMyUserFormRef"
        label-width="100px"
      >
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addMyUserForm.email" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addMyUserForm.password" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="addMyUserForm.status" placeholder="请选择">
            <el-option
              label="禁用"
              value="1">
            </el-option>
            <el-option
              label="启用"
              value="0">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否会员" prop="member">
          <el-select v-model="addMyUserForm.member" placeholder="请选择">
            <el-option
              label="是"
              value="1">
            </el-option>
            <el-option
              label="否"
              value="0">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addMyUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑 User 的对话框 -->
    <el-dialog title="编辑 用户" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="editMyUserForm"
        ref="editMyUserFormRef"
        label-width="100px"
      >
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editMyUserForm.email" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="editMyUserForm.password" @input="change($event)" placeholder="为空，则不修改密码"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="editMyUserForm.status" placeholder="请选择">
            <el-option
              label="禁用"
              value="1">
            </el-option>
            <el-option
              label="启用"
              value="0">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否会员" prop="member">
          <el-select v-model="editMyUserForm.member" placeholder="请选择">
            <el-option
              label="是"
              value="1">
            </el-option>
            <el-option
              label="否"
              value="0">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editMyUserValue">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data () {
    return {
      // 获取 MyUser 列表查询参数对象
      queryInfo: {
        query: '',
        // 当前页数
        pagenum: 1,
        // 每页显示多少数据
        pagesize: 5
      },
      myUserList: [],
      prodProplist: [],
      totle: 0,
      // 添加 MyUser 对话框
      addDialogVisible: false,
      // MyUser 添加
      addMyUserForm: {
        email : '',
        password : '',
        status : '',
        member : '',
      },
      // 编辑 MyUser 对话框
      editDialogVisible: false,
      // 编辑 MyUser
      editMyUserForm: {
        userId : '',
        email : '',
        password : '',
        status : '',
        member : '',
      },
    }
  },
  created () {
    this.getMyUserlist()
  },
  methods: {
    async getMyUserlist () {
        const { data: res } = await this.$http.get('/auth/myUser', {
          params : {
            email : this.queryInfo.query,
          }
        })
        console.info('getMyUserlist', res)
        if (!res.success) {
            return this.$message.error(res.message)
        }
        res.data.forEach(e => {
          e.statusContent = e.status == 1 ? '正常' : '禁用'
          e.memberContent = e.member == 1 ? '是' : '否'
        });
        this.myUserList = res.data;
        this.totle = res.count;
        console.info("getMyUserlist", this.myUserList);
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getMyUserlist()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getMyUserlist()
    },
    // 监听 添加 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addMyUserFormRef.resetFields()
    },
    // 添加 myUser 前加载信息
    loadAddDialogVisible () {
      this.addDialogVisible = true
    },
    // 添加 myUser
    addMyUser () {
      console.info('addMyUser this.addMyUserForm', this.addMyUserForm)
      // 提交请求前，表单预验证
      this.$refs.addMyUserFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/myUser', {
          ...this.addMyUserForm,
        })
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success("创建 用户 成功")
        console.info('MyUser', res)
        // 隐藏添加对话框
        this.addDialogVisible = false
        this.getMyUserlist()
      })
    },
    // 展开 编辑 MyUser 对话框
    async showEditDialog (serviceInfo) {
      this.editDialogVisible = true
      const { data: res } = await this.$http.get('/auth/myUser/detail', {
        params : {
          id: serviceInfo.userId
        }
      })
      if (!res.success) {
        this.$message.error(res.message)
      }
      console.info('MyUser detail' , res)
      res.data.password = ''
      this.editMyUserForm = res.data
    },
    // 更新 MyUser 信息
    async editMyUserValue (serviceInfo) {
      const { data: res } = await this.$http.put('/auth/myUser', {
        userId : this.editMyUserForm.userId,
        password : this.editMyUserForm.password,
        status : this.editMyUserForm.status,
        member : this.editMyUserForm.member
      })
      if (!res.success) {
        return this.$message.error(res.message)
      }
      this.$message.success("更新 用户 成功")
      this.editDialogVisible = false
      this.getMyUserlist()
    },
    // 删除 MyUser
    async removeMyUser (serviceInfo) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该 用户 , 是否继续?',
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
      console.info('delete 用户', serviceInfo);
      const { data: res } = await this.$http.delete('/auth/myUser', {
        params: {
          id : serviceInfo.userId
        }
      })
      console.info('res', res)
      if (!res.success) return this.$message.error('删除 用户 失败！')
      this.$message.success('删除 用户 成功！')
      this.getMyUserlist()
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed () {
      this.$refs.editMyUserFormRef.resetFields()
    },
    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
