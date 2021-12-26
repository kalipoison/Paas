<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>容器管理</el-breadcrumb-item>
      <el-breadcrumb-item>Delpyment列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入namespace" v-model="queryInfo.query" clearable @clear="getDeploymentList">
            <el-button slot="append" icon="el-icon-search" @click="getDeploymentList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加Deployment</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addYamlDialogVisible = true">Yaml格式自定义Deployment</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- Deployment 列表区域 -->
      <el-table :data="deploymentlist" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="name" label="deploymentName"></el-table-column>
        <el-table-column prop="namespace" label="namspace"></el-table-column>
        <el-table-column prop="matchLabels" label="label"></el-table-column>
        <el-table-column prop="replicas" label="replicas"></el-table-column>
        <el-table-column prop="restartPolicy" label="restartPolicy"></el-table-column>
        <el-table-column prop="unavilableReplicas" label="unavilableReplicas"></el-table-column>
        <el-table-column prop="avilableReplicas" label="avilableReplicas"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="编辑 Delpyment 信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="warning"
                icon="el-icon-edit"
                size="mini"
                content="编辑 Delpyment 信息"
                circle
                @click="showEditDialog(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="更多Deployment信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="success"
                icon="el-icon-more"
                size="mini"
                content="更多Deployment信息"
                circle
                @click="InfoYamlDeployment(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除Pod"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                content="删除Pod"
                circle
                @click="removeDeployment(scope.row)"
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

    <!-- 添加 Deployment 的对话框 -->
    <el-dialog title="新增 Deployment" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addDeploymentForm"
        ref="addDeploymentFormRef"
        label-width="100px"
      >
        <el-form-item label="apiVersion" prop="apiVersion">
          <el-input v-model="addDeploymentForm.apiVersion" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="deployment名" prop="metadataName">
          <el-input v-model="addDeploymentForm.metadataName" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="namespace" prop="namespace">
          <el-input v-model="addDeploymentForm.namespace" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="标签选择器" prop="matchLabelsApp">
          <el-input v-model="addDeploymentForm.matchLabelsApp" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="副本数" prop="replicas">
          <el-input v-model="addDeploymentForm.replicas" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="模板容器名" prop="templateSpecConatinersName">
          <el-input v-model="addDeploymentForm.templateSpecConatinersName" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="模板容器镜像" prop="templateSpecContainersImage">
          <el-input v-model="addDeploymentForm.templateSpecContainersImage" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="模板容器开放端口" prop="templateContainerPort">
          <el-input v-model="addDeploymentForm.templateContainerPort" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="模板CPU资源上限" prop="limitCPU">
          <el-input v-model="addDeploymentForm.limitCPU" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="模板最少CPU资源" prop="requestCPU">
          <el-input v-model="addDeploymentForm.requestCPU" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="模板内存资源上限" prop="limitMemory">
          <el-input v-model="addDeploymentForm.limitMemory" @input="change($event)"></el-input>
        </el-form-item>
        <el-form-item label="模板最少内存资源" prop="requestMemory">
          <el-input v-model="addDeploymentForm.requestMemory" @input="change($event)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addDeployment">确 定</el-button>
      </span>
    </el-dialog>

    <!-- Yaml 方式添加Pod的对话框 -->
    <el-dialog title="新增 Deployment(Yaml 格式)" :visible.sync="addYamlDialogVisible" width="50%" @close="infoYamlDialogClosed">
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

    <!-- 编辑 Deployment 的对话框 -->
    <el-dialog title="编辑 Deployment" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="editDeploymentForm"
        ref="editDeploymentFormRef"
        label-width="100px"
      >
        <el-form-item label="Deployment" prop="metadataName">
          <el-input v-model="editDeploymentForm.metadataName" disabled></el-input>
        </el-form-item>
        <el-form-item label="namespace" prop="namespace">
          <el-input v-model="editDeploymentForm.namespace" disabled></el-input>
        </el-form-item>
        <el-form-item label="apiVersion" prop="apiVersion">
          <el-input v-model="editDeploymentForm.apiVersion"></el-input>
        </el-form-item>
        <el-form-item label="标签选择器" prop="matchLabelsApp">
          <el-input v-model="editDeploymentForm.matchLabelsApp"></el-input>
        </el-form-item>
        <el-form-item label="副本数" prop="replicas">
          <el-input v-model="editDeploymentForm.replicas"></el-input>
        </el-form-item>
        <el-form-item label="模板容器名" prop="templateSpecConatinersName">
          <el-input v-model="editDeploymentForm.templateSpecConatinersName"></el-input>
        </el-form-item>
        <el-form-item label="模板容器镜像" prop="templateSpecContainersImage">
          <el-input v-model="editDeploymentForm.templateSpecContainersImage"></el-input>
        </el-form-item>
        <el-form-item label="模板容器开放端口" prop="templateContainerPort">
          <el-input v-model="editDeploymentForm.templateContainerPort"></el-input>
        </el-form-item>
        <el-form-item label="模板CPU资源上限" prop="limitCPU">
          <el-input v-model="editDeploymentForm.limitCPU"></el-input>
        </el-form-item>
        <el-form-item label="模板最少CPU资源" prop="requestCPU">
          <el-input v-model="editDeploymentForm.requestCPU"></el-input>
        </el-form-item>
        <el-form-item label="模板内存资源上限" prop="limitMemory">
          <el-input v-model="editDeploymentForm.limitMemory"></el-input>
        </el-form-item>
        <el-form-item label="模板最少内存资源" prop="requestMemory">
          <el-input v-model="editDeploymentForm.requestMemory"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editDeployment">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 更多 Deployment 信息的对话框 -->
    <el-dialog title="Deployment 详细信息" :visible.sync="infoYamlDialogVisible" width="50%" @close="infoYamlDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="InfoYamlDeploymentForm"
        ref="InfoYamlDeploymentFormRef"
        label-width="100px"
      >
          <pre>{{InfoYamlDeploymentForm.info}}</pre>
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
      deploymentlist: [],
      totle: 0,
      // 添加 Deployment 对话框
      addDialogVisible: false,
      // Deployment 添加
      addDeploymentForm: {
        apiVersion: '',
        namespace: '',
        metadataName: '',
        metadataLabelsApp: '',
        specConatinersName: '',
        specContainersImage: '',
        containerPort: '',
      },
      // Yaml添加 Deployment 对话框
      addYamlDialogVisible: false,
      // Yaml 添加 Deployment
      addYamlPodForm: {
        podYamlInfo: '',
      },
      // 编辑 Deployment 对话框
      editDialogVisible: false,
      // 编辑 Deployment
      editDeploymentForm: {
        apiVersion: '',
        namespace: '',
        metadataName: '',
        metadataLabelsApp: '',
        specConatinersName: '',
        specContainersImage: '',
        containerPort: '',
      },
      // Deployment 更多信息对话框
      infoYamlDialogVisible: false,
      // Deployment 更多信息
      InfoYamlDeploymentForm: {
        info: '',
      },
    }
  },
  created () {
    this.getDeploymentList()
  },
  methods: {
    async getDeploymentList () {
        const { data: res } = await this.$http.get('/auth/deployment', {
          params : {
            namespace : this.queryInfo.query,
          }
        })
        console.info('res', res);
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.deploymentlist = res.data;
        this.totle = res.count;
        console.info("userlist", this.deploymentlist, this.totle);
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getDeploymentList()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getDeploymentList()
    },
    // 监听 添加 Pod 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addDeploymentFormRef.resetFields()
    },
    // 添加 Pod
    addDeployment () {
      // 提交请求前，表单预验证
      this.$refs.addDeploymentFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/deployment', {
          ...this.addDeploymentForm,
        })
        console.info('addDeployment', res)
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success(res.data)
        // 隐藏添加Pod对话框
        this.addDialogVisible = false
        this.getDeploymentList()
      })
    },
    // 添加pod yaml方式
    async addPodYaml () {
      const { data: res } = await this.$http.post('/auth/pod/createByYaml', {
        podInfo : this.addYamlPodForm.podYamlInfo
      })
      console.info('addPodYaml', this.addYamlPodForm)
      this.addYamlDialogVisible = false

    },
    // 展开 编辑deployment 对话框
    async showEditDialog (deploymentInfo) {
      this.editDialogVisible = true
      console.info('showEditDialog deploymentInfo', deploymentInfo)
      const { data: res } = await this.$http.get('/auth/deployment/detail', {
        params : {
          namespace : deploymentInfo.namespace,
          deploymentName : deploymentInfo.name
        }
      })
      if (!res.success) {
        this.$message.error(res.message)
      }
      this.editDeploymentForm = res.data
    },
    // 更新 deployment信息
    async editDeployment (deploymentInfo) {
      console.info('deploymnetInfo edit', deploymentInfo)
      const { data: res } = await this.$http.put('/auth/deployment', {
        ...this.editDeploymentForm,
      })
      console.info('editDeployment', res)
      this.editDialogVisible = false
      this.getDeploymentList()
    },
    // 删除Pod
    async removeDeployment (deploymentInfo) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该 Deployment , 是否继续?',
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
      const { data: res } = await this.$http.delete('/auth/deployment', {
        params: {
          namespace : deploymentInfo.namespace,
          deploymentName : deploymentInfo.name
        }
      })
      if (!res.success) return this.$message.error('删除 Deployment 失败！')
      this.$message.success('删除 Deployment 成功！')
      this.getDeploymentList()
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed () {
      this.$refs.editDeploymentFormRef.resetFields()
    },
    // 监听 deployment 详细信息yaml格式 对话框的关闭事件
    infoDialogClosed () {
      this.$refs.InfoYamlDeploymentFormRef.resetFields()
    },
    // deployment 全部信息yaml格式
    async InfoYamlDeployment (deploymentInfo) {
      console.info('deployment', deploymentInfo)
      const { data: res } = await this.$http.get('/auth/deployment/detailYml', {
        params : {
          namespace : deploymentInfo.namespace,
          deploymentName : deploymentInfo.name
        }
      })
      console.info('deployment res', res)
      if (!res.success) return this.$message.error(res.message)
      this.infoYamlDialogVisible = true;
      this.InfoYamlDeploymentForm.info = res.data;
    },

    infoYamlDialogClosed () {
      this.InfoYamlDeploymentForm.info = '';
    },

    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
