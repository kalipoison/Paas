<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>兑换券</el-breadcrumb-item>
      <el-breadcrumb-item>兑换券列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入商品名" v-model="queryInfo.query" clearable @clear="getVoucherlist">
            <el-button slot="append" icon="el-icon-search" @click="getVoucherlist"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="loadAddDialogVisible">添加 Voucher</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- Voucher 列表区域 -->
      <el-table :data="voucherList" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="voucherCode" label="兑换码"></el-table-column>
        <el-table-column prop="username" label="创建者"></el-table-column>
        <el-table-column prop="status" label="状态"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="expireTime" label="到期时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="删除 Voucher"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                content="删除 Voucher"
                circle
                @click="removeVoucher(scope.row)"
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

    <!-- 添加 Voucher 的对话框 -->
    <el-dialog title="新增 Voucher" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addVoucherForm"
        ref="addVoucherFormRef"
        label-width="100px"
      >
        <el-form-item label="创建兑换券张数" prop="num">
          <el-input v-model="addVoucherForm.num" @input="change($event)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addVoucherValue">确 定</el-button>
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
      voucherList: [],
      totle: 0,
      // 添加 SKU 对话框
      addDialogVisible: false,
      // SKU 添加
      addVoucherForm: {
        num: '',
      },
    }
  },
  created () {
    this.getVoucherlist()
  },
  methods: {
    async getVoucherlist () {
        const { data: res } = await this.$http.get('/auth/voucher', {
          params : {
            productName : this.queryInfo.query,
          }
        })
        console.info('getVoucherlist', res)
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.voucherList = res.data;
        this.totle = res.count;
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getVoucherlist()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getVoucherlist()
    },
    // 监听 添加 Voucher 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addVoucherFormRef.resetFields()
    },
    // 添加 Voucher 前加载 预先数据
    loadAddDialogVisible () {
      this.addDialogVisible = true
    },
    // 添加 Voucher
    addVoucherValue () {
      console.info('addVoucherValue this.addVoucherForm', this.addVoucherForm)
      // 提交请求前，表单预验证
      this.$refs.addVoucherFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/voucher', {
          num : this.addVoucherForm.num,
          username : window.sessionStorage.getItem('username'),
        })
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success("创建 voucher 成功")
        console.info('voucher', res)
        // 隐藏添加对话框
        this.addDialogVisible = false
        this.getVoucherlist()
      })
    },
    // 删除 Voucher
    async removeVoucher (serviceInfo) {
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
      const { data: res } = await this.$http.delete('/auth/voucher', {
        params: {
          id : serviceInfo.voucherId
        }
      })
      console.info('res', res)
      if (!res.success) return this.$message.error('删除 Voucher 失败！')
      this.$message.success('删除 Voucher 成功！')
      this.getVoucherlist()
    },
    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
