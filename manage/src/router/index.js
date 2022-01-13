// import Vue from 'vue'
import VueRouter from 'vue-router'
import Vue from 'vue'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import SysUser from '../components/sys/SysUser.vue'
import Role from '../components/sys/Role.vue'
import Menu from '../components/sys/Menu.vue'
import Log from '../components/sys/Log.vue'
import Node from '../components/container/Node.vue'
import Namespace from '../components/container/Namespace.vue'
import Deployment from '../components/container/Deployment.vue'
import Pod from '../components/container/Pod.vue'
import Dau from '../components/drawer/Dau.vue'
import Service from '../components/container/Service.vue'
import Terms from '../components/term/Terms.vue'
import Product from '../components/prod/Product.vue'
import ProdProp from '@/components/prod/ProdProp'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/login',
    }, {
        path: '/login',
        component: Login
    }, {
        path: '/home',
        component: Home,
        children: [
            {path: '/sys/user', component: SysUser},
            {path: '/sys/role', component: Role},
            {path: '/sys/menu', component: Menu},
            {path: '/sys/log', component: Log},
            {path: '/container/node', component: Node},
            {path: '/container/namespace', component: Namespace},
            {path: '/container/deployment', component: Deployment},
            {path: '/container/pod', component: Pod},
            {path: '/container/service', component: Service},
            {path: '/dau/request', component: Dau},
            {path: '/terminal/terminal', component: Terms},
            {path: '/prod/product', component: Product},
            {path: '/prod/prodProp', component: ProdProp},
        ]
    }
]

const router = new VueRouter({
    mode : 'history',
    routes
})

export default router