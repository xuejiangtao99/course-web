import Vue from 'vue'
import App from './App.vue'
import router from './router.js'
import axios from 'axios'
import filter from "@/filter/filter";

Vue.prototype.$ajax = axios;

//解决前后端分离每一次Ajax请求的时候，对应的sessionId 不一致
axios.defaults.withCredentials = true

axios.interceptors.request.use(function (config){
      console.log("请求"+config)
  return config;
},error => {})

axios.interceptors.response.use(function (response){
  console.log("返回结果"+response)
  return response;
},error => {})

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

console.log(process.env.VUE_APP_SERVER)

//全局过滤器
Object.keys(filter).forEach(key=>{
  Vue.filter(key,filter[key]);
})
