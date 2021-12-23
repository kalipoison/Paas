<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>菜单</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 添加菜单按钮 -->
      <el-row>
        <el-col>
          <el-button type="primary" @click="AddRoleDialogVisible=true">添加菜单</el-button>
        </el-col>
      </el-row>
      <!-- 菜单列表 -->
      <!-- <el-table 
        :data="rolesList" 
        border 
        stripe 
        default-expand-all 
        :tree-props="{children: 'list', hasChildren: 'list'}"> -->
      <el-table
        :data="rolesList"
        style="width: 100%;margin-bottom: 20px;"
        row-key="menuId"
        border
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <!-- 索引列 -->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="菜单名称" prop="name"></el-table-column>
        <el-table-column label="url" prop="url"></el-table-column>
        <el-table-column label="权限" prop="perms"></el-table-column>
        <el-table-column label="类型" prop="type"></el-table-column>
        <el-table-column label="操作" width="300px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)">编辑</el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeRoleById(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 添加菜单对话框 -->
    <el-dialog title="添加菜单" :visible.sync="AddRoleDialogVisible" width="40%" @close="addRoleDialogClosed">
      <el-form
        :model="addMenuForm"
        ref="addMenuFormRef"
        :rules="addMenuFormRules"
        label-width="100px"
      >
        <el-form-item label="菜单名称" prop="menuName">
          <el-input v-model="addMenuForm.menuName"></el-input>
        </el-form-item>
        <el-form-item label="菜单描述" prop="roleDesc">
          <el-input v-model="addMenuForm.roleDesc"></el-input>
        </el-form-item>
        <el-form-item label="url" prop="roleDesc">
          <el-input v-model="addMenuForm.roleDesc"></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="roleDesc">
          <el-input v-model="addMenuForm.roleDesc"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="AddRoleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRoles">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑菜单对话框 -->
    <el-dialog title="编辑菜单" :visible.sync="editRoleDialogVisible" width="40%" @close="addRoleDialogClosed">
      <el-form
        :model="editRoleForm"
        ref="editRoleFormRef"
        :rules="editRoleFormRules"
        label-width="100px"
      >
        <el-form-item label="菜单名称" prop="roleName">
          <el-input v-model="editRoleForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="菜单描述" prop="roleDesc">
          <el-input v-model="editRoleForm.roleDesc"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editRoleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editRoles">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 所有菜单列表
      rolesList: [],
      //  树形控件的属性绑定对象
      treeProps: {
        label: 'authName',
        children: 'children'
      },
      //   添加用户对话框
      AddRoleDialogVisible: false,
      // 添加菜单表单
      addMenuForm: {},
      //   添加菜单表单验证
      addMenuFormRules: {
        roleName: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' }
        ],
        roleDesc: [
          { required: true, message: '请输入菜单描述', trigger: 'blur' }
        ]
      },
      //   编辑菜单信息
      editRoleForm: {},
      editRoleDialogVisible: false,
      editRoleFormRules: {
        roleName: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' }
        ],
        roleDesc: [
          { required: true, message: '请输入菜单描述', trigger: 'blur' }
        ]
      },
      //   当前即将分配权限的Id
      roleId: 0
    }
  },
  created () {
    this.getMenuList()
  },
  methods: {
    async getMenuList () {
      const { data: res } = await this.$http.get('/auth/menu')
      console.info("menu res", res);
      if (!res.success) {
        return this.$message.error('获取菜单列表失败！')
      }
        res.data.forEach(element => {
          element.children = element.list
          return element;
        });
      this.rolesList = res.data
    },
    // 根据ID删除对应的权限
    async removeRightById (role, rightId) {
      // 弹框提示 删除
      const confirmResult = await this.$confirm(
        '此操作将永久删除该权限, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)
      // 点击确定 返回值为：confirm
      // 点击取消 返回值为： cancel
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消权限删除')
      }
      const { data: res } = await this.$http.delete(
        `roles/${role.id}/rights/${rightId}`
      )
      if (res.meta.status !== 200) {
        return this.$message.error('删除权限失败！')
      }
      role.children = res.data
      //   不建议使用
      // this.getRolesList()
    },
    // 添加角色对话框的关闭
    addRoleDialogClosed () {
      this.$refs.addRoleFormRef.resetFields()
    },
    // 添加角色
    addRoles () {
      this.$refs.addRoleFormRef.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.post('roles', this.addRoleForm)
        if (res.meta.status !== 201) {
          this.$message.error('添加角色失败！')
        }
        this.$message.success('添加角色成功！')
        this.AddRoleDialogVisible = false
        this.getRolesList()
      })
    },
    // 删除角色
    async removeRoleById (id) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该角色, 是否继续?',
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
      const { data: res } = await this.$http.delete('roles/' + id)
      if (res.meta.status !== 200) return this.$message.error('删除角色失败！')
      this.$message.success('删除用户成功！')
      this.getRolesList()
    },
    // 编辑角色
    async showEditDialog (id) {
      const { data: res } = await this.$http.get('roles/' + id)
      if (res.meta.status !== 200) return this.$message.error('查询角色信息失败！')
      this.editRoleForm = res.data
      this.editRoleDialogVisible = true
    },
    editRoles () {
      this.$refs.editRoleFormRef.validate(async valid => {
        // console.log(valid)
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.put(
          'roles/' + this.editRoleForm.roleId,
          {
            roleName: this.editRoleForm.roleName,
            roleDesc: this.editRoleForm.roleDesc
          }
        )
        if (res.meta.status !== 200) {
          this.$message.error('更新角色信息失败！')
        }
        // 隐藏编辑角色对话框
        this.editRoleDialogVisible = false
        this.$message.success('更新角色信息成功！')
        this.getRolesList()
      })
    },
  }
}
</script>

<style lang="less" scoped>
.el-tag {
  margin: 7px;
}
.bdtop {
  border-top: 1px solid #eee;
}
.bdbottom {
  border-bottom: 1px solid #eee;
}
.vcenter {
  display: flex;
  align-items: center;
}
</style>
