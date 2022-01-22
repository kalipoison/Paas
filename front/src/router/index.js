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
import PersonSecurity from '../components/person/PersonSecurity.vue'
import PersonGift from '../components/person/PersonGift.vue'
import PersonBuy from '../components/person/PersonBuy.vue'
import PersonPay from '../components/person/PersonPay.vue'
import Terms from '../components/term/Terms.vue'

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
            {path : '/console/security' , component: PersonSecurity},
            {path : '/console/giftcard' , component: PersonGift},
            {path : '/console/PersonBuy' , component: PersonBuy},
            {path : '/console/PersonPay' , component: PersonPay},
        ]
    }, {
        path : '/terminal',
        component : Terms
    }
]

const router = new VueRouter({
    mode : 'history',
    routes
})

export default router