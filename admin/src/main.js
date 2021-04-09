import Vue from 'vue'
import App from './App.vue'
import router from './router.js'
import axios from 'axios'
import filter from "@/filter/filter";

Vue.prototype.$ajax = axios;

//解决前后端分离每一次Ajax请求的时候，对应的sessionId 不一致
axios.defaults.withCredentials = true

axios.interceptors.request.use(function (config) {
    console.log("请求" + JSON.stringify(config))
    //后端拦截,先在请求添加token
    let loginUser = JSON.parse(sessionStorage.getItem(SESSION_KEY_LOGIN_USER));
    if(Tool.isNotEmpty(loginUser)){
        config.headers.token = loginUser.token
    }
    return config;
}, error => {
    return Promise.reject(error);
})

axios.interceptors.response.use(function (response) {
    console.log("返回结果" + response)
    return response;
}, error => {
})

Vue.config.productionTip = false


//登录路由拦截
router.beforeEach((to, from, next)=>{
    //判断是否给对meta.loginRequire属性监控拦截，返回值为true/false（即在router.js定义的loginRequire属性值）
    if(to.matched.some(function (item){
        return item.meta.loginRequire
    })){
        let loginUser = JSON.parse(sessionStorage.getItem(SESSION_KEY_LOGIN_USER));
        if(Tool.isEmpty(loginUser)){
            next("/login")
        }else{
            next()
        }
    }else{
        next()
    }
})

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')

console.log(process.env.VUE_APP_SERVER)

//全局过滤器
Object.keys(filter).forEach(key => {
    Vue.filter(key, filter[key]);
})