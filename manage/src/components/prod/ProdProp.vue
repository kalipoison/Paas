<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品</el-breadcrumb-item>
      <el-breadcrumb-item>商品属性</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入商品属性名" v-model="queryInfo.query" clearable @clear="getProdProplist">
            <el-button slot="append" icon="el-icon-search" @click="getProdProplist"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加 商品属性</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- 商品 列表区域 -->
      <el-table :data="prodProplist" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="propName" label="商品属性名称"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="编辑 商品属性 信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="warning"
                icon="el-icon-edit"
                size="mini"
                content="编辑 商品属性 信息"
                circle
                @click="showEditDialog(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除 商品属性"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                content="删除 商品属性"
                circle
                @click="removeProdProp(scope.row)"
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

    <!-- 添加 商品属性 的对话框 -->
    <el-dialog title="新增 商品属性" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addProdPropForm"
        ref="addProdPropFormRef"
        label-width="100px"
      >
        <el-form-item label="商品名称" prop="prodName">
          <el-input v-model="addProdPropForm.propName" @input="change($event)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addProdProp">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑 商品属性 的对话框 -->
    <el-dialog title="编辑 商品属性" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="editProductForm"
        ref="editProductFormRef"
        label-width="100px"
      >
        <el-form-item label="商品属性名" prop="propName">
          <el-input v-model="editProductForm.propName" @input="change($event)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editProduct">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data () {
    return {
      // 获取Pod列表查询参数对象
      queryInfo: {
        query: '',
        // 当前页数
        pagenum: 1,
        // 每页显示多少数据
        pagesize: 5
      },
      prodProplist: [],
      totle: 0,
      // 添加 ProdProp 对话框
      addDialogVisible: false,
      // ProdProp 添加
      addProdPropForm: {
        propName: '',
      },
      // 编辑 ProdProp 对话框
      editDialogVisible: false,
      // 编辑 ProdProp
      editProductForm: {
        propId: '',
        propName: '',
      },
    }
  },
  created () {
    this.getProdProplist()
  },
  methods: {
    async getProdProplist () {
        const { data: res } = await this.$http.get('/auth/prodProp', {
          params : {
            propName : this.queryInfo.query,
          }
        })
        console.info('getProdProplist', res)
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.prodProplist = res.data;
        this.totle = res.count;
        console.info("userlist", this.prodProplist);
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getProdProplist()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getProdProplist()
    },
    // 监听 添加 Product 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addProdPropFormRef.resetFields()
    },
    // 添加 ProdProp
    addProdProp () {
      // 提交请求前，表单预验证
      this.$refs.addProdPropFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/prodProp', {
          ...this.addProdPropForm,
        })
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success("创建 商品属性 成功")
        // 隐藏添加对话框
        this.addDialogVisible = false
        this.getProdProplist()
      })
    },
    // 展开 编辑 商品属性 对话框
    async showEditDialog (serviceInfo) {
      this.editDialogVisible = true
      const { data: res } = await this.$http.get('/auth/prodProp/detail', {
        params : {
          propId: serviceInfo.propId
        }
      })
      if (!res.success) {
        this.$message.error(res.message)
      }
      console.info('showEditDialog', res.data)
      this.editProductForm = res.data
    },
    // 更新 商品属性 信息
    async editProduct (serviceInfo) {
      const { data: res } = await this.$http.put('/auth/prodProp', {
        propId: this.editProductForm.propId,
        propName: this.editProductForm.propName,
      })
      this.editDialogVisible = false
      this.getProdProplist()
    },
    // 删除 Product
    async removeProdProp (serviceInfo) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该 商品属性名 , 是否继续?',
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
      console.info('delete 商品属性名', serviceInfo);
      const { data: res } = await this.$http.delete('/auth/prodProp', {
        params: {
          propId : serviceInfo.propId
        }
      })
      console.info('res', res)
      if (!res.success) return this.$message.error('删除 商品属性名 失败！')
      this.$message.success('删除 商品属性名 成功！')
      this.getProdProplist()
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed () {
      this.$refs.editProductFormRef.resetFields()
    },
    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
