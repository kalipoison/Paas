<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品</el-breadcrumb-item>
      <el-breadcrumb-item>订单列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入 订单号" v-model="queryInfo.query" clearable @clear="getOrderlist">
            <el-button slot="append" icon="el-icon-search" @click="getOrderlist"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="loadAddDialogVisible">添加 订单</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- 订单 列表区域 -->
      <el-table :data="orderList" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="orderNumber" label="订单号"></el-table-column>
        <el-table-column prop="productName" label="产品名"></el-table-column>
        <el-table-column prop="email" label="用户名/邮箱"></el-table-column>
        <el-table-column prop="isPay" label="是否支付"></el-table-column>
        <el-table-column prop="status" label="订单状态"></el-table-column>
        <el-table-column prop="createTime" label="createTime"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="订单 详情"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="success"
                icon="el-icon-more"
                size="mini"
                content="订单 详情"
                circle
                @click="detailOrder(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="编辑 订单 信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="warning"
                icon="el-icon-edit"
                size="mini"
                content="编辑 订单 信息"
                circle
                @click="showEditDialog(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除 订单"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                content="删除 订单"
                circle
                @click="removeOrder(scope.row)"
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

    <!-- 添加 订单 的对话框 -->
    <el-dialog title="新增 订单" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addOrderForm"
        ref="addOrderFormRef"
        label-width="100px"
      >
        <el-form-item label="用户名" prop="email">
          <el-input v-model="addOrderForm.email" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="产品名" prop="email">
          <el-select v-model="addOrderForm.prodId" placeholder="请选择">
            <el-option
              v-for="product in this.productList"
              :key="product.prodId"
              :label="product.prodName"
              :value="product.prodId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addOrderValue">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 更多 Order 信息的对话框 -->
    <el-dialog title="订单 详情信息" :visible.sync="detailDialogVisible" width="50%" @close="detailDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="detailOrderForm"
        ref="detailOrderFormRef"
        label-width="100px"
      >
        <el-form-item label="订单号" prop="orderNumber">
          <el-input v-model="detailOrderForm.orderNumber" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="产品名" prop="productName">
          <el-input v-model="detailOrderForm.productName" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="email">
          <el-input v-model="detailOrderForm.email" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="总值" prop="total">
          <el-input v-model="detailOrderForm.total" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="优惠总额" prop="reduceAmount">
          <el-input v-model="detailOrderForm.reduceAmount" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="实际总值" prop="actualTotal">
          <el-input v-model="detailOrderForm.actualTotal" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="订单状态" prop="status">
          <el-input v-model="detailOrderForm.status" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="订购时间" prop="createTime">
          <el-input v-model="detailOrderForm.createTime" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="订单更新时间" prop="updateTime">
          <el-input v-model="detailOrderForm.updateTime" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="付款时间" prop="payTime">
          <el-input v-model="detailOrderForm.payTime" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="完成时间" prop="finallyTime">
          <el-input v-model="detailOrderForm.finallyTime" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="是否已经支付" prop="isPayed">
          <el-input v-model="detailOrderForm.isPayed" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户订单删除状态" prop="refundSts">
          <el-input v-model="detailOrderForm.refundSts" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="订单关闭原因" prop="closeType">
          <el-input v-model="detailOrderForm.closeType" @input="change($event)" disabled></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="detailDialogVisible = false">取 消</el-button> -->
        <el-button type="primary" @click="detailDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>


    <!-- 编辑 Order 的对话框 -->
    <el-dialog title="编辑 订单" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="editOrderForm"
        ref="editOrderFormRef"
        label-width="100px"
      >
        <el-form-item label="订单号" prop="orderNumber">
          <el-input v-model="editOrderForm.orderNumber" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="产品名" prop="productName">
          <el-input v-model="editOrderForm.productName" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="email">
          <el-input v-model="editOrderForm.email" @input="change($event)" disabled></el-input>
        </el-form-item>
        <el-form-item label="产品名" prop="email">
          <el-select v-model="editOrderForm.prodId" placeholder="请选择">
            <el-option
              v-for="product in this.productList"
              :key="product.prodId"
              :label="product.prodName"
              :value="product.prodId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="实际总值" prop="actualTotal">
          <el-input v-model="editOrderForm.actualTotal" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="订单状态" prop="status">
          <el-select v-model="editOrderForm.status" placeholder="请选择">
            <el-option
              key="0"
              label="失败"
              value="0">
            </el-option>
            <el-option
              key="1"
              label="成功"
              value="1">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否已经支付" prop="isPayed">
          <el-select v-model="editOrderForm.isPayed" placeholder="请选择">
            <el-option
              key="0"
              label="否"
              value="0">
            </el-option>
            <el-option
              key="1"
              label="是"
              value="1">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editOrderValue">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data () {
    return {
      // 获取 Order 列表查询参数对象
      queryInfo: {
        query: '',
        // 当前页数
        pagenum: 1,
        // 每页显示多少数据
        pagesize: 5
      },
      orderList: [],
      productList: [],
      totle: 0,
      // 添加 Order 对话框
      addDialogVisible: false,
      // Order 添加
      addOrderForm: {
        email: '',
        prodId: '',
      },
      // 编辑 Order 对话框
      editDialogVisible: false,
      // 编辑 Order
      editOrderForm: {
        orderId : '',
        orderNumber: '',
        productName: '',
        email: '',
        prodId: '',
        actualTotal: '',
        status: '',
        userId: '',
        isPayed: '',
      },
      // Order 更多信息对话框
      detailDialogVisible: false,
      // Order 更多信息
      detailOrderForm: {
        actualTotal: '',
        cancelTime: '',
        closeType: '',
        createTime: '',
        deleteStatus: '',
        email: '',
        finallyTime: '',
        isPayed: '',
        orderId: '',
        orderNumber: '',
        payTime: '',
        payType: '',
        prodId: '',
        productName: '',
        reduceAmount: '',
        refundSts: '',
        status: '',
        total: '',
        updateTime: '',
        userId: '',
      },
    }
  },
  created () {
    this.getOrderlist()
  },
  methods: {
    async getOrderlist () {
        console.info('queryInfo', this.queryInfo)
        const { data: res } = await this.$http.get('/auth/order', {
          params : {
            orderNumber : this.queryInfo.query,
          }
        });
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.orderList = res.data;
        this.totle = res.count;
        console.info("userlist", this.orderList);
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getOrderlist()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getOrderlist()
    },
    // 监听 添加 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addOrderFormRef.resetFields()
    },
    async getProductList() {
      const { data: res } = await this.$http.get('/auth/product')
      console.info('getProductList', res)
      this.productList = res.data
    },
    // 添加 Order 前加载信息
    loadAddDialogVisible () {
      this.getProductList()
      this.addDialogVisible = true
    },
    // 添加 order
    addOrderValue () {
      console.info('addOrderValue this.addOrderForm', this.addOrderForm)
      // 提交请求前，表单预验证
      this.$refs.addOrderFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/order', {
          ...this.addOrderForm,
        })
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success("创建 订单 成功")
        console.info('创建 订单 res', res)
        // 隐藏添加对话框
        this.addDialogVisible = false
        this.getOrderlist()
      })
    },
    // 展开 编辑  对话框
    async showEditDialog (serviceInfo) {
      this.getProductList()
      this.editDialogVisible = true
      const { data: res } = await this.$http.get('/auth/order/detail', {
        params : {
          id : serviceInfo.orderId,
        }
      })
      if (!res.success) {
        this.$message.error(res.message)
      }
      console.info('edit order detail' , res)
      this.editOrderForm = res.data
    },
    // 更新 Order 信息
    async editOrderValue (serviceInfo) {
      const { data: res } = await this.$http.put('/auth/order', {
        orderId : this.editOrderForm.orderId,
        orderNumber: this.editOrderForm.orderNumber,
        email: this.editOrderForm.email,
        prodId: this.editOrderForm.prodId,
        actualTotal: this.editOrderForm.actualTotal,
        userId: this.editOrderForm.userId,
        status: this.editOrderForm.status,
        isPayed: this.editOrderForm.isPayed,
      })
      this.editDialogVisible = false
      this.getOrderlist()
      if (!res.success) return this.$message.error(res.message)
      this.$message.success(res.data)
    },
    // 展开 详情 对话框
    async detailOrder (serviceInfo) {
      console.info('detailOrder serviceInfo', serviceInfo)
      const { data: res } = await this.$http.get('/auth/order/detail', {
        params : {
          id : serviceInfo.orderId,
        }
      })
      if (!res.success) return this.$message.error(res.message)
      console.info('detailOrder res' , res)
      var data = res.data;
      data.reduceAmount = data.reduceAmount === null || data.reduceAmount === '' ? 0 : data.reduceAmount;
      data.isPayed = data.isPayed === 1 ? '已经支付' : '没有支付过'
      if (data.deleteStatus == 1) {
        data.deleteStatus = '回收站'
      } else if (data.deleteStatus == 2) {
        data.deleteStatus = '永久删除'
      } else {
        data.deleteStatus = '没有删除'
      }
      this.detailOrderForm = data
      this.detailDialogVisible = true;
    },
    // 关闭 详情 对话框 
    detailDialogClosed () {
      this.$refs.detailOrderFormRef.resetFields()
    },


    // 删除 Order
    async removeOrder (serviceInfo) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该 订单 , 是否继续?',
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
      console.info('delete order', serviceInfo);
      const { data: res } = await this.$http.delete('/auth/order', {
        params: {
          id : serviceInfo.orderId
        }
      })
      console.info('res', res)
      if (!res.success) return this.$message.error('删除 订单 失败！')
      this.$message.success('删除 订单 成功！')
      this.getOrderlist()
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed () {
      this.$refs.editOrderFormRef.resetFields()
    },
    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
