<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品</el-breadcrumb-item>
      <el-breadcrumb-item>Sku列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入商品名" v-model="queryInfo.query" clearable @clear="getSkulist">
            <el-button slot="append" icon="el-icon-search" @click="getSkulist"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="loadAddDialogVisible">添加 Sku</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- 商品 列表区域 -->
      <el-table :data="skuList" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="productName" label="商品名称"></el-table-column>
        <el-table-column prop="propName" label="商品属性名称"></el-table-column>
        <el-table-column prop="propValue" label="商品属性值名称"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
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
                @click="removeSKU(scope.row)"
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

    <!-- 添加 Sku 的对话框 -->
    <el-dialog title="新增 Sku" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addSkuForm"
        ref="addSkuFormRef"
        label-width="100px"
      >
        <el-form-item label="商品名称" prop="propId">
          <el-select v-model="addSkuForm.prodId" placeholder="请选择">
            <el-option
              v-for="prod in this.productlist"
              :key="prod.prodId"
              :label="prod.prodName"
              :value="prod.prodId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品属性" prop="propId">
          <el-select v-model="addSkuForm.valueId" placeholder="请选择">
            <el-option
              v-for="prodProp in this.prodPropValuelist"
              :key="prodProp.valueId"
              :label="prodProp.propName + ' : '+ prodProp.propValue"
              :value="prodProp.valueId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addSkuValue">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data () {
    return {
      // 获取 SKU 列表查询参数对象
      queryInfo: {
        query: '',
        // 当前页数
        pagenum: 1,
        // 每页显示多少数据
        pagesize: 5
      },
      skuList: [],
      prodPropValuelist: [],
      productlist: [],
      totle: 0,
      // 添加 SKU 对话框
      addDialogVisible: false,
      // SKU 添加
      addSkuForm: {
        prodId: '',
        valueId: '',
      },
    }
  },
  created () {
    this.getSkulist()
  },
  methods: {
    async getSkulist () {
        const { data: res } = await this.$http.get('/auth/sku', {
          params : {
            productName : this.queryInfo.query,
          }
        })
        console.info('getSkulist', res)
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.skuList = res.data;
        this.totle = res.count;
    },
    async getProdPropValuelist () {
      const { data: res } = await this.$http.get('/auth/prodPropValue')
      if (!res.success) {
          return this.$message.error(res.message)
      }
      this.prodPropValuelist = res.data;
    },
    async getProductlist () {
      const { data: res } = await this.$http.get('/auth/product')
      if (!res.success) {
          return this.$message.error(res.message)
      }
      this.productlist = res.data
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getSkulist()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getSkulist()
    },
    // 监听 添加 Product 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addSkuFormRef.resetFields()
    },
    // 添加 Sku 前加载 ProdProp信息
    loadAddDialogVisible () {
      this.getProdPropValuelist()
      this.getProductlist()
      this.addDialogVisible = true
    },
    // 添加 Sku
    addSkuValue () {
      console.info('addSkuValue this.addSkuForm', this.addSkuForm)
      // 提交请求前，表单预验证
      this.$refs.addSkuFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/sku', {
          ...this.addSkuForm,
        })
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success("创建 商品属性 成功")
        console.info('商品属性', res)
        // 隐藏添加对话框
        this.addDialogVisible = false
        this.getSkulist()
      })
    },
    // 删除 Sku
    async removeSKU (serviceInfo) {
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
      const { data: res } = await this.$http.delete('/auth/sku', {
        params: {
          id : serviceInfo.skuId
        }
      })
      console.info('res', res)
      if (!res.success) return this.$message.error('删除 SKU 失败！')
      this.$message.success('删除 SKU 成功！')
      this.getSkulist()
    },
    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
