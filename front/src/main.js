import Vue from 'vue'
import App from './App.vue'
import router from './router/index'

// element UI
import ElementUI from'element-ui'
import'element-ui/lib/theme-chalk/index.css';//引入element-ui的样式文件

//通过这个命令使所有的element-ui标签(<el-*></el-*>)可被解析
Vue.use(ElementUI)
Vue.prototype.$message = ElementUI.Message

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
