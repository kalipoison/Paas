import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Login from '../components/Login.vue'
import First from '../components/home/First.vue'
import Price from '../components/home/Price.vue'
import Service from '../components/home/Service.vue'

Vue.use(VueRouter)

const routes = [{
    path : '/',
    component : Home,
    children: [
        {path : '/' , component: First},
        {path : '/price' , component: Price},
        {path : '/service' , component: Service},
    ]
}, {
    path : '/login',
    component : Login
}
]

const router = new VueRouter({
    // mode : 'history',
    routes
})

export default router