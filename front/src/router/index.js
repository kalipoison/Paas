import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Login from '../components/Login.vue'
import First from '../components/home/First.vue'
import Price from '../components/home/Price.vue'
import Service from '../components/home/Service.vue'
import Person from '../components/person/Person.vue'
import PersonHome from '../components/person/PersonHome.vue'
import PersonShop from '../components/person/PersonShop.vue'
import PersonTool from '../components/person/PersonTool.vue'
import PersonNotify from '../components/person/PersonNotify.vue'

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
}, {
    path : '/console',
    component : Person,
    children: [
        {path : '/' , component: PersonHome},
        {path : '/console/shop' , component: PersonShop},
        {path : '/console/tool' , component: PersonTool},
        {path : '/console/notify' , component: PersonNotify},
    ]
}
]

const router = new VueRouter({
    mode : 'history',
    routes
})

export default router