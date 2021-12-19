<template>
  <el-container>
    <el-header>
      <div>
        <img src="../assets/logo.png" width="50px" height="50px" alt />
        <span>通用后台管理系统</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <el-container>
      <el-aside :width="isCollapse?'64px':'200px'">
        <el-menu 
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#4a7bcf"
          unique-opened
          :default-active="activePath"
          :collapse="isCollapse"
          :collapse-transition="false"
          router
        >
          <el-menu-item index="home">
            <i class="el-icon-menu"></i>
            <span slot="title">首页</span>
          </el-menu-item>
          <!-- 一级菜单 -->
          <el-submenu 
            v-for="item in menuList"
            :index="item.menuId + ''"
            :key="item.menuId"
          >
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>{{item.name}}</span>
            </template>
            <el-menu-item 
              v-for="subItem in item.list"
              :index="subItem.url + ''"
              :key="subItem.menuId"          
            >
              <template slot="title">
                <!-- 图标 -->
                <i class="el-icon-menu"></i>
                <!-- 文本 -->
                <span>{{subItem.name}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>


<script>
export default {
  data(){
    return {
      menuList : [],
      // 是否折叠侧边栏
      isCollapse : false,
      // 被激活的连接地址
      activePath : '',
    }
  },
  created() {
    this.getMenuList();
  },
  methods : {
    async getMenuList () {
      const { data: res } = await this.$http.get('/auth/menu/nav', {
        params: {
            username : window.sessionStorage.getItem('username'),
        }
      })
      this.menuList = res.data.menuList;
    },
    logout () {
      // 清空token
      window.sessionStorage.clear()
      // 页面重定向到登录页面
      this.$router.push('/login')
    },
    toggleCollapse () {
      this.isCollapse = !this.isCollapse
    },
    saveNavState(activePath) {
      window.sessionStorage.setItem('activePath', activePath)
    },
    backHome() {
      this.$router.push('/home')
    },
  }
};
</script>

<style lang="less" scoped>
.el-container {
  height: 100%;
}
.el-header {
  background-color: #373f41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;
  > div {
    display: flex;
    align-items: center;
    img {
      height: 40px;
    }
    span {
      margin-left: 15px;
    }
  }
}

// .el-aside {
//   background-color: #333744;

//   .el-menu {
//     border: none;
//   }
// }

.el-aside {
  background-color: rgb(84, 92, 100);
  .el-menu {
    height: 100vh;
    border-right: none;
  }
}
.el-icon {
  margin-right: 10px;
}
.toggle-button {
  background-color: rgb(84, 92, 100);
  color: #fff;
  text-align: center;
  font-size: 10px;
  height: 24px;
  line-height: 24px;
  letter-spacing: 0.1em;
  cursor: pointer;
}
</style>