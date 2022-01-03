<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>容器管理</el-breadcrumb-item>
      <el-breadcrumb-item>Service列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入namespace" v-model="queryInfo.query" clearable @clear="getServicelist">
            <el-button slot="append" icon="el-icon-search" @click="getServicelist"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加 Service</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addYamlDialogVisible = true">Yaml 格式自定义 Service</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- Service 列表区域 -->
      <el-table :data="servicelist" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="metadataName" label="serviceName"></el-table-column>
        <el-table-column prop="namespace" label="namspace"></el-table-column>
        <el-table-column prop="clusterIP" label="clusterIP"></el-table-column>
        <el-table-column prop="externalIP" label="externalIP"></el-table-column>
        <el-table-column prop="port" label="port"></el-table-column>
        <el-table-column prop="protocal" label="protocal"></el-table-column>
        <el-table-column prop="targetPort" label="targetPort"></el-table-column>
        <el-table-column prop="specSelectorLabelsApp" label="specSelectorLabelsApp"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="编辑 Service 信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="warning"
                icon="el-icon-edit"
                size="mini"
                content="编辑 Service 信息"
                circle
                @click="showEditDialog(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="更多 Service 信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="success"
                icon="el-icon-more"
                size="mini"
                content="更多 Service 信息"
                circle
                @click="InfoYamlService(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除 Service"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                content="删除 Service"
                circle
                @click="removeService(scope.row)"
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

    <!-- 添加 Service 的对话框 -->
    <el-dialog title="新增 Service" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addServiceForm"
        ref="addServiceFormRef"
        label-width="100px"
      >
        <el-form-item label="apiVersion" prop="apiVersion">
          <el-input v-model="addServiceForm.apiVersion" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="namespace" prop="namespace">
          <el-input v-model="addServiceForm.namespace" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="serviceName" prop="metadataName">
          <el-input v-model="addServiceForm.metadataName" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="specSelectorApp" prop="specSelectorApp">
          <el-input v-model="addServiceForm.specSelectorApp" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="Protocol" prop="specProtocol">
          <el-input v-model="addServiceForm.specProtocol" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="Port" prop="specPort">
          <el-input v-model="addServiceForm.specPort" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="externalIP" prop="externalIP">
          <el-input v-model="addServiceForm.externalIP" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="type" prop="type">
          <el-input v-model="addServiceForm.type" @input="change($event)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addService">确 定</el-button>
      </span>
    </el-dialog>

    <!-- Yaml 方式添加Pod的对话框 -->
    <el-dialog title="新增 Service (Yaml 格式)" :visible.sync="addYamlDialogVisible" width="50%" @close="infoYamlDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addYamlPodForm"
        ref="addYamlPodFormRef"
        label-width="100px"
      >

        <el-input type="textarea" v-model="addYamlPodForm.podYamlInfo" placeholder="" :autosize="{ minRows: 7}" @input="change($event)">
        </el-input>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addYamlDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addPodYaml()">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑 Service 的对话框 -->
    <el-dialog title="编辑 Service" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="editServiceForm"
        ref="editServiceFormRef"
        label-width="100px"
      >
        <el-form-item label="Service" prop="metadataName">
          <el-input v-model="editServiceForm.metadataName" disabled></el-input>
        </el-form-item>
        <el-form-item label="namespace" prop="namespace">
          <el-input v-model="editServiceForm.namespace" disabled></el-input>
        </el-form-item>
        <el-form-item label="apiVersion" prop="apiVersion">
          <el-input v-model="editServiceForm.apiVersion"></el-input>
        </el-form-item>
        <el-form-item label="SelectorApp" prop="specSelectorApp">
          <el-input v-model="editServiceForm.specSelectorApp"></el-input>
        </el-form-item>
        <el-form-item label="Protocol" prop="specProtocol">
          <el-input v-model="editServiceForm.specProtocol"></el-input>
        </el-form-item>
        <el-form-item label="Port" prop="specPort">
          <el-input v-model="editServiceForm.specPort"></el-input>
        </el-form-item>
        <el-form-item label="TargetPort" prop="specTargetPort">
          <el-input v-model="editServiceForm.specTargetPort"></el-input>
        </el-form-item>
        <el-form-item label="externalIP" prop="externalIP">
          <el-input v-model="editServiceForm.externalIP"></el-input>
        </el-form-item>
        <el-form-item label="type" prop="type">
          <el-input v-model="editServiceForm.type"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editService">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 更多 Service 信息的对话框 -->
    <el-dialog title="Service 详细信息" :visible.sync="infoYamlDialogVisible" width="50%" @close="infoYamlDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="InfoYamlServiceForm"
        ref="InfoYamlServiceFormRef"
        label-width="100px"
      >
          <pre>{{InfoYamlServiceForm.info}}</pre>
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
      // 获取Pod列表查询参数对象
      queryInfo: {
        query: '',
        // 当前页数
        pagenum: 1,
        // 每页显示多少数据
        pagesize: 5
      },
      servicelist: [],
      totle: 0,
      // 添加 Service 对话框
      addDialogVisible: false,
      // Service 添加
      addServiceForm: {
        apiVersion: '',
        namespace: '',
        metadataName: '',
        specSelectorApp: '',
        specProtocol: '',
        specPort: '',
        specTargetPort: '',
      },
      // Yaml添加 Service 对话框
      addYamlDialogVisible: false,
      // Yaml 添加 Service
      addYamlPodForm: {
        podYamlInfo: '',
      },
      // 编辑 Service 对话框
      editDialogVisible: false,
      // 编辑 Service
      editServiceForm: {
        apiVersion: '',
        namespace: '',
        metadataName: '',
        specSelectorApp: '',
        specProtocol: '',
        specPort: '',
        specTargetPort: '',
        externalIP: '',
        type: ''
      },
      // Service 更多信息对话框
      infoYamlDialogVisible: false,
      // Service 更多信息
      InfoYamlServiceForm: {
        info: '',
      },
    }
  },
  created () {
    this.getServicelist()
  },
  methods: {
    async getServicelist () {
        const { data: res } = await this.$http.get('/auth/service', {
          params : {
            namespace : this.queryInfo.query,
          }
        })
        console.info('add service', res)
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.servicelist = res.data;
        this.totle = res.count;
        console.info("userlist", this.servicelist, this.totle);
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getServicelist()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getServicelist()
    },
    // 监听 添加 Pod 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addServiceFormRef.resetFields()
    },
    // 添加 Service
    addService () {
      // 提交请求前，表单预验证
      this.$refs.addServiceFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/service', {
          ...this.addServiceForm,
        })
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success("创建service成功")
        console.info('addService', res)
        // 隐藏添加Pod对话框
        this.addDialogVisible = false
        this.getServicelist()
      })
    },
    // 添加Service yaml方式
    async addPodYaml () {
      const { data: res } = await this.$http.post('/auth/pod/createByYaml', {
        podInfo : this.addYamlPodForm.podYamlInfo
      })
      console.info('addPodYaml', this.addYamlPodForm)
      this.addYamlDialogVisible = false

    },
    // 展开 编辑 Service 对话框
    async showEditDialog (serviceInfo) {
      this.editDialogVisible = true
      console.info('showEditDialog serviceInfo', serviceInfo)
      const { data: res } = await this.$http.get('/auth/service/detail', {
        params : {
          namespace : serviceInfo.namespace,
          serviceName : serviceInfo.metadataName
        }
      })
      if (!res.success) {
        this.$message.error(res.message)
      }
      this.editServiceForm = res.data
    },
    // 更新 Service 信息
    async editService (serviceInfo) {
      console.info('serviceInfo edit', serviceInfo)
      const { data: res } = await this.$http.put('/auth/service', {
        ...this.editServiceForm,
      })
      console.info('editService', res)
      this.editDialogVisible = false
      this.getServicelist()
    },
    // 删除Pod
    async removeService (serviceInfo) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该 Service , 是否继续?',
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
      console.info('delete service', serviceInfo);
      const { data: res } = await this.$http.delete('/auth/service', {
        params: {
          namespace : serviceInfo.namespace,
          serviceName : serviceInfo.metadataName
        }
      })
      console.info('res', res)
      if (!res.success) return this.$message.error('删除 Service 失败！')
      this.$message.success('删除 Service 成功！')
      this.getServicelist()
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed () {
      this.$refs.editServiceFormRef.resetFields()
    },
    // 监听 Service 详细信息yaml格式 对话框的关闭事件
    infoDialogClosed () {
      this.$refs.InfoYamlServiceFormRef.resetFields()
    },
    // Service 全部信息yaml格式
    async InfoYamlService (serviceInfo) {
      console.info('service', serviceInfo)
      const { data: res } = await this.$http.get('/auth/service/detailYml', {
        params : {
          namespace : serviceInfo.namespace,
          serviceName : serviceInfo.metadataName
        }
      })
      console.info('service res', res)
      if (!res.success) return this.$message.error(res.message)
      this.infoYamlDialogVisible = true;
      this.InfoYamlServiceForm.info = res.data;
    },

    infoYamlDialogClosed () {
      this.InfoYamlServiceForm.info = '';
    },

    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
