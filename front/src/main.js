import Vue from 'vue'
import App from './App.vue'
import router from './router/index'

// element UI
import ElementUI from'element-ui'
import'element-ui/lib/theme-chalk/index.css';//引入element-ui的样式文件

// axios
import axios from 'axios'
import qs from 'qs'

// store
import store from './store'

//通过这个命令使所有的element-ui标签(<el-*></el-*>)可被解析
Vue.use(ElementUI)
Vue.prototype.$message = ElementUI.Message

// 设置超时时间和跨域是否允许携带凭证
// axios.defaults.timeout = 10000;
axios.defaults.withCredentials = false;

// 配置请求的根路径
axios.defaults.baseURL = 'http://127.0.0.1:8082/api/'
// 请求在到达服务器之前，先会调用use中的这个回调函数来添加请求头信息
axios.interceptors.request.use((config) => {
  // console.info("config", config);
  // 为请求头对象，添加token验证的Authorization字段
  const token = window.sessionStorage.getItem('token');
  config.headers.Authorization = token || {};
  config.headers.loginType = 'client';
  console.info('config', config);
  if (config.method === 'post' || config.method === 'put') {
    config.data = qs.stringify(config.data);
  }

  // 在最后必须 return config
  return config;
})
axios.interceptors.response.use((response) => {
  if (!response.success) {
    return response;
  }
  return response;
})

Vue.prototype.$http = axios

Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
