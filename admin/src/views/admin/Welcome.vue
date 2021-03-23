<template>
  <div>
    <h2>您好，欢迎进入登录欢迎页面</h2>
  </div>
</template>


<script>
export default {
  name:'welcome',
  mounted() {
    this.$parent.activeSidebar('welcome-sidebar')
  }
}
</script>