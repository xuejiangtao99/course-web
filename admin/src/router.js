import  Vue from 'vue'
import VueRouter from 'vue-router';
import Login from './views/Login';
import Admin from './views/Admin';
import Welcome from './views/admin/Welcome'
import Chapter from './views/admin/Chapter'


Vue.use(VueRouter)


export default new VueRouter({

    mode:'history',
    base:process.env.BASE_URL,
    routes:[{
        path: "*",
        redirect: "/login",
    }, {
        path: "/login",
        name:'login',
        component: Login
    },{
        path:'/',
        name:'admin',
        component:Admin,
        children:[{
            path:'welcome',
            name:'welcome',
            component:Welcome
        },{
            path:"business/chapter",
            name:'business/chapter',
            component:Chapter
        }]
    }]
})