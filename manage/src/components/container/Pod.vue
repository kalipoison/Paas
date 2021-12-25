<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>容器管理</el-breadcrumb-item>
      <el-breadcrumb-item>Pod列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 搜索 添加 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入namespace" v-model="queryInfo.query" clearable @clear="getPodList">
            <el-button slot="append" icon="el-icon-search" @click="getPodList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加Pod</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addYamlDialogVisible = true">Yaml格式自定义Pod</el-button>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- Pod列表区域 -->
      <el-table :data="podlist" border stripe>
        <!-- stripe: 斑马条纹 border：边框-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="podName" label="Name"></el-table-column>
        <el-table-column prop="namespace" label="namespace"></el-table-column>
        <el-table-column prop="labelsApp" label="label"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="restartPolicy" label="重启策略"></el-table-column>
        <el-table-column prop="serviceAccount" label="serviceAccount"></el-table-column>
        <el-table-column prop="status" label="status"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="查看Pod信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="warning"
                icon="el-icon-document"
                size="mini"
                content="查看Pod信息"
                circle
                @click="infoPod(scope.row)"
              ></el-button>
            </el-tooltip>
            <el-tooltip
              class="item"
              effect="dark"
              content="更多Pod信息"
              :enterable="false"
              placement="top"
            >
              <el-button
                type="success"
                icon="el-icon-more"
                size="mini"
                content="更多Pod信息"
                circle
                @click="InfoYamlPod(scope.row)"
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
                @click="removePod(scope.row)"
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

    <!-- 添加Pod的对话框 -->
    <el-dialog title="新增Pod" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="addPodForm"
        ref="addPodFormRef"
        label-width="100px"
      >
        <el-form-item label="apiVersion" prop="apiVersion">
          <el-input v-model="addPodForm.apiVersion"></el-input>
        </el-form-item>
        <el-form-item label="属性名" prop="metadataName">
          <el-input v-model="addPodForm.metadataName"></el-input>
        </el-form-item>
        <el-form-item label="命名空间" prop="namespace">
          <el-input v-model="addPodForm.namespace"></el-input>
        </el-form-item>
        <el-form-item label="标签app" prop="metadataLabelsApp">
          <el-input v-model="addPodForm.metadataLabelsApp"></el-input>
        </el-form-item>
        <el-form-item label="容器名" prop="specConatinersName">
          <el-input v-model="addPodForm.specConatinersName"></el-input>
        </el-form-item>
        <el-form-item label="容器镜像" prop="specContainersImage">
          <el-input v-model="addPodForm.specContainersImage"></el-input>
        </el-form-item>
        <el-form-item label="容器开放端口" prop="containerPort">
          <el-input v-model="addPodForm.containerPort"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addPod">确 定</el-button>
      </span>
    </el-dialog>

    <!-- Yaml 方式添加Pod的对话框 -->
    <el-dialog title="新增Pod" :visible.sync="addYamlDialogVisible" width="50%" @close="addDialogClosed">
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

    <!-- 查看Pod信息的对话框 -->
    <el-dialog title="Pod信息" :visible.sync="infoDialogVisible" width="50%" @close="infoDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="infoPodObj"
        ref="addPodFormRef"
        label-width="100px"
      >
        <el-form-item label="apiVersion" prop="apiVersion">
          <el-input v-model="infoPodObj.apiVersion" disabled></el-input>
        </el-form-item>
        <el-form-item label="kind" prop="kind">
          <el-input v-model="infoPodObj.kind" disabled></el-input>
        </el-form-item>
        <el-form-item label="podName" prop="metadataName">
          <el-input v-model="infoPodObj.metadataName" disabled></el-input>
        </el-form-item>
        <el-form-item label="LabelsApp" prop="metadataLabelsApp">
          <el-input v-model="infoPodObj.metadataLabelsApp" disabled></el-input>
        </el-form-item>
        <el-form-item label="容器具体信息">

        </el-form-item>
        <el-table :data="infoPodObj.kubePodContainerBOList">
          <el-table-column label="#" type="index"></el-table-column>
          <el-table-column label="镜像名" prop="image"></el-table-column>
          <el-table-column label="镜像" prop="name"></el-table-column>
          <el-table-column label="端口" prop="ports"></el-table-column>
          <el-table-column label="CPU资源使用上限" prop="limitsCPU"></el-table-column>
          <el-table-column label="最少需要的CPU资源" prop="requestsCPU"></el-table-column>
          <el-table-column label="内存资源使用上限" prop="limitsMemory"></el-table-column>
          <el-table-column label="最少需要内存" prop="requestsMemory"></el-table-column>
        </el-table>

      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="infoDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 更多Pod信息的对话框 -->
    <el-dialog title="Pod详细信息" :visible.sync="infoYamlDialogVisible" width="50%" @close="infoYamlDialogClosed">
      <!-- 内容主体 -->
      <el-form
        :model="infoYamlPodForm"
        ref="infoYamlPodFormRef"
        label-width="100px"
      >
          <pre>{{infoYamlPodForm.info}}</pre>
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
      podlist: [],
      totle: 0,
      // 添加Pod对话框
      addDialogVisible: false,
      // Pod添加
      addPodForm: {
        apiVersion: '',
        namespace: '',
        metadataName: '',
        metadataLabelsApp: '',
        specConatinersName: '',
        specContainersImage: '',
        containerPort: '',
      },
      // Yaml添加Pod对话框
      addYamlDialogVisible: false,
      // Yaml 添加Pod
      addYamlPodForm: {
        podYamlInfo: '',
      },
      // Pod信息对话框
      infoDialogVisible: false,
      // Pod信息
      infoPodObj: {
        apiVersion: '',
        kind: '',
        metadataLabelsApp: '',
        metadataName: '',
        kubePodContainerBOList: []
      },
      // Pod 更多信息对话框
      infoYamlDialogVisible: false,
      // Pod 更多信息
      infoYamlPodForm: {
        info: '',
      },
    }
  },
  created () {
    this.getPodList()
  },
  methods: {
    async getPodList () {
        const { data: res } = await this.$http.get('/auth/pod', {
          params : {
            namespace : this.queryInfo.query,
          }
        })
        console.info('res', res);
        if (!res.success) {
            return this.$message.error(res.message)
        }
        this.podlist = res.data;
        this.totle = res.count;
        console.info("userlist", this.podlist, this.totle);
    },
    // 监听 pagesize改变的事件
    handleSizeChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getPodList()
    },
    // 监听 页码值 改变事件
    handleCurrentChange (newSize) {
      // console.log(newSize)
      this.queryInfo.pagenum = newSize
      this.getPodList()
    },
    // 监听 添加 Pod 对话框的关闭事件
    addDialogClosed () {
      this.$refs.addPodFormRef.resetFields()
    },
    // 添加 Pod
    addPod () {
      // 提交请求前，表单预验证
      this.$refs.addPodFormRef.validate(async valid => {
        // 表单预校验失败
        if (!valid) return
        const { data: res } = await this.$http.post('/auth/pod', {
          ...this.addPodForm,
          containerPort : parseInt(this.addPodForm.containerPort)
        })
        console.info('addPod', res)
        if (!res.success) {
          return this.$message.error(res.message)
        }
        this.$message.success(res.data)
        // 隐藏添加Pod对话框
        this.addDialogVisible = false
        this.getPodList()
      })
    },
    // 添加pod yaml方式
    async addPodYaml () {
      const { data: res } = await this.$http.post('/auth/pod/createByYaml', {
        podInfo : this.addYamlPodForm.podYamlInfo
      })
      console.info('addPodYaml', this.addYamlPodForm)
      addYamlDialogVisible = false

    },

    // 删除Pod
    async removePod (podInfo) {
      console.info("podInfo", podInfo)
      const confirmResult = await this.$confirm(
        '此操作将永久删除该Pod, 是否继续?',
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
      const { data: res } = await this.$http.delete('/auth/pod', {
        params: {
          namespace : podInfo.namespace,
          podName : podInfo.podName
        }
      })
      console.info('pod', res);
      if (!res.success) return this.$message.error('删除Pod失败！')
      this.$message.success('删除Pod成功！')
      this.getPodList()
    },
    // 监听修改用户对话框的关闭事件
    infoDialogClosed () {
      this.$refs.infoPodFormRef.resetFields()
    },
    // Pod信息
    async infoPod (podInfo) {
      this.infoDialogVisible = true
      const { data: res } = await this.$http.get('/auth/pod/detail', {
        params : {
          namespace : podInfo.namespace,
          podName : podInfo.podName
        }
      })
      if (!res.success) {
        this.$message.error(res.message)
      }
      this.infoPodObj = res.data
      console.info('infoPodObj', this.infoPodObj)
    },
    // 监听 pod 详细信息yaml格式 对话框的关闭事件
    infoDialogClosed () {
      this.$refs.infoYamlPodFormRef.resetFields()
    },
    // Pod全部信息yaml格式
    async InfoYamlPod (podInfo) {
      const { data: res } = await this.$http.get('/auth/pod/detailYml', {
        params : {
          namespace : podInfo.namespace,
          podName : podInfo.podName
        }
      })
      this.infoYamlDialogVisible = true;
      this.infoYamlPodForm.info = res.data;
    },

    infoYamlDialogClosed () {
      this.infoYamlPodForm.info = '';
    },

    change (e) {
      this.$forceUpdate()
    },
  }
}
</script>

<style lang="less" scoped>
</style>
