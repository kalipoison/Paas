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
            {path: '/sys/node', component: Node},
            {path: '/sys/namespace', component: Namespace},
            {path: '/sys/deployment', component: Deployment},
            {path: '/sys/pod', component: Pod},
            {path: '/sys/dau', component: Dau},
            {path: '/sys/service', component: Service},
            {path: '/sys/terminal', component: Terms},
        ]
    }
]

const router = new VueRouter({
    mode : 'history',
    routes
})

export default router