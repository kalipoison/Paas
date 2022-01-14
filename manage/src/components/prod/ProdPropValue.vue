<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品</el-breadcrumb-item>
      <el-breadcrumb-item>商品属性值列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入商品属性值名" v-model="queryInfo.query" clearable @clear="getProdPropValuelist">
            <el-button slot="append" icon="el-icon-search" @click="getProdPropValuelist"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="loadAddDialogVisible">添加 商品</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- 商品 列表区域 -->
      <el-table :data="prodPropValueList" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="propName" label="商品属性名称"></el-table-column>
        <el-table-column prop="propValue" label="商品属性值名称"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="编辑 商品属性值 信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="warning"
                icon="el-icon-edit"
                size="mini"
                content="编辑 商品属性值 信息"
                circle
                @click="showEditDialog(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除 商品属性值"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                content="删除 商品属性值"
                circle
                @click="removeProdPropValue(scope.row)"
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

    <!-- 添加 ProdPropValue 的对话框 -->
    <el-dialog title="新增 ProdPropValue" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addProdPropValueForm"
        ref="addProdPropValueFormRef"
        label-width="100px"
      >
        <el-form-item label="商品属性名称" prop="propId">
          <el-select v-model="addProdPropValueForm.propId" placeholder="请选择">
            <el-option
              v-for="prodProp in this.prodProplist"
              :key="prodProp.propId"
              :label="prodProp.propName"
              :value="prodProp.propId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品属性值" prop="propValue">
          <el-input v-model="addProdPropValueForm.propValue" @input="change($event)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addProdPropValue">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑 ProdPropValue 的对话框 -->
    <el-dialog title="编辑 ProdPropValue" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="editProdPropValueForm"
        ref="editProdPropValueFormRef"
        label-width="100px"
      >
        <el-form-item label="商品属性名称" prop="propId">
          <el-select v-model="editProdPropValueForm.propId" placeholder="请选择">
            <el-option
              v-for="prodProp in this.prodProplist"
              :key="prodProp.propId"
              :label="prodProp.propName"
              :value="prodProp.propId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品属性值" prop="propValue">
          <el-input v-model="editProdPropValueForm.propValue" @input="change($event)"></el-input>
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
      prodPropValueList: [],
      prodProplist: [],
      totle: 0,
      // 添加 ProdPropValue 对话框
      addDialogVisible: false,
      // ProdPropValue 添加
      addProdPropValueForm: {
        propName: '',
        propValue: '',
      },
      // 编辑 Product 对话框
      editDialogVisible: false,
      // 编辑 Product
      editProdPropValueForm: {
        propId: '',
        propValue: '',
        valueId: '',
      },
    }
  },
  created () {
    this.getProdPropValuelist()
  },
  methods: {
    async getProdPropValuelist () {
        const { data: res } = await this.$http.get('/auth/prodPropValue', {
          params : {
            prodName : this.queryInfo.query,
          }
        })
        console.info('getProdPropValuelist', res)
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.prodPropValueList = res.data;
        this.totle = res.count;
        console.info("userlist", this.prodPropValueList);
    },
    async getProdProplist () {
      const { data: res } = await this.$http.get('/auth/prodProp')
      console.info('getProdProplist', res)
      if (!res.success) {
          return this.$message.error(res.message)
      }
      this.prodProplist = res.data;
      this.totle = res.count;
      console.info("getProdProplist", this.prodProplist);
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getProdPropValuelist()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getProdPropValuelist()
    },
    // 监听 添加 Product 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addProdPropValueFormRef.resetFields()
    },
    // 添加 ProdPropValue 前加载 ProdProp信息
    loadAddDialogVisible () {
      console.info('loadAddDiagload')
      this.getProdProplist()
      this.addDialogVisible = true
    },
    // 添加 ProdPropValue
    addProdPropValue () {
      console.info('addProdPropValue this.addProdPropValueForm', this.addProdPropValueForm)
      // 提交请求前，表单预验证
      this.$refs.addProdPropValueFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/prodPropValue', {
          ...this.addProdPropValueForm,
        })
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success("创建 商品属性 成功")
        console.info('商品属性', res)
        // 隐藏添加对话框
        this.addDialogVisible = false
        this.getProdPropValuelist()
      })
    },
    // 展开 编辑 ProdPropValue 对话框
    async showEditDialog (serviceInfo) {
      this.getProdProplist()
      this.editDialogVisible = true
      const { data: res } = await this.$http.get('/auth/prodPropValue/detail', {
        params : {
          valueId: serviceInfo.valueId
        }
      })
      if (!res.success) {
        this.$message.error(res.message)
      }
      this.editProdPropValueForm = res.data
    },
    // 更新 ProdPropValue 信息
    async editProduct (serviceInfo) {
      console.info('edit ProdPropValue', this.editProdPropValueForm.propValue)
      const { data: res } = await this.$http.put('/auth/prodPropValue', {
        propId: this.editProdPropValueForm.propId,
        propValue: this.editProdPropValueForm.propValue,
        valueId: this.editProdPropValueForm.valueId,
      })
      this.editDialogVisible = false
      this.getProdPropValuelist()
    },
    // 删除 ProdPropValue
    async removeProdPropValue (serviceInfo) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该 商品属性值 , 是否继续?',
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
      console.info('delete 商品属性值', serviceInfo);
      const { data: res } = await this.$http.delete('/auth/prodPropValue', {
        params: {
          valueId : serviceInfo.valueId
        }
      })
      console.info('res', res)
      if (!res.success) return this.$message.error('删除 商品属性值 失败！')
      this.$message.success('删除 商品属性值 成功！')
      this.getProdPropValuelist()
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed () {
      this.$refs.editProdPropValueFormRef.resetFields()
    },
    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
