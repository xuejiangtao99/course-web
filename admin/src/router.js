import  Vue from 'vue'
import VueRouter from 'vue-router';
import Login from './views/Login';
import Admin from './views/Admin';
import Welcome from './views/admin/Welcome'


Vue.use(VueRouter)


export default new VueRouter({

    mode:'history',
    base:process.env.BASE_URL,
    routes:[{
        path: "*",
        redirect: "/login",
    }, {
        path: "/login",
        component: Login
    },{
        path:'/admin',
        component:Admin,
        children:[{
            path:'welcome',
            component:Welcome
        }]
    }]
})