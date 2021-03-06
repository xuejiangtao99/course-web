import  Vue from 'vue'
import VueRouter from 'vue-router';
import Login from './views/Login';
import Admin from './views/Admin';
import Welcome from './views/admin/Welcome'
import Chapter from './views/admin/Chapter'
import Section from "@/views/admin/Section";
import Course from "@/views/admin/Course";
import User from "@/views/admin/User";


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
        path:"",
        redirect:'/login'
    },{
        path:'/',
        name:'admin',
        component:Admin,
        meta:{
            loginRequire:true
        },
        children:[{
            path:'welcome',
            name:'welcome',
            component:Welcome
        },{
            path:"business/chapter",
            name:'business/chapter',
            component:Chapter
        },{
            path:"business/section",
            name:"business/section",
            component:Section
        },{
            path:"business/course",
            name:"business/course",
            component:Course
        },{
            path:"system/user",
            name:"system/user",
            component:User
        }]
    }]
})