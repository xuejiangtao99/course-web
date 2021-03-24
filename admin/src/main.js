import Vue from 'vue'
import App from './App.vue'
import router from './router.js'
import axios from 'axios'

Vue.prototype.$ajax = axios;

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
