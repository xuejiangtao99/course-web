<template>
  <div class="main-container">
    <div class="main-content">
      <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
          <div class="login-container">
            <div class="center">
              <h1>
                <i class="ace-icon fa fa-leaf green"></i>
                <span class="red">Ace</span>
                <span class="white" id="id-text2">Application</span>
              </h1>
              <h4 class="blue" id="id-company-text">&copy; Company Name</h4>
            </div>

            <div class="space-6"></div>

            <div class="position-relative">
              <div id="login-box" class="login-box visible widget-box no-border">
                <div class="widget-body">
                  <div class="widget-main">
                    <h4 class="header blue lighter bigger">
                      <i class="ace-icon fa fa-coffee green"></i>
                      控制台登录
                    </h4>

                    <div class="space-6"></div>

                    <form>
                      <fieldset>
                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名" v-model="user.loginName"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                        </label>

                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" v-model="user.password"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                        </label>

                        <div class="space"></div>

                        <div class="clearfix">
                          <label class="inline" style="float: left">
                            <input type="checkbox" class="ace" v-model="remember"/>
                            <span class="lbl" style="font-size: 10px">记住我</span>
                          </label>

                          <button type="button" class="width-35 pull-right btn btn-sm btn-primary"
                                  @click="login()"
                          >
                            <i class="ace-icon fa fa-key"></i>
                            <span class="bigger-110">登录</span>
                          </button>
                        </div>

                        <div class="space-4"></div>
                      </fieldset>
                    </form>
                    <div class="space-6"></div>
                  </div>
                </div>
              </div>
            </div><!-- /.position-relative -->

          </div>
        </div><!-- /.col -->
      </div><!-- /.row -->
    </div><!-- /.main-content -->
  </div>
</template>

<script>
export default {
  name: 'login',
  props: {
    msg: String
  },
  data() {
    return {
      user: {},
      remember: true
    }
  },
  mounted() {
    $('body').attr('class', 'login-layout light-login');

    let rememberUser = JSON.parse(localStorage.getItem(LOCAL_KEY_USER_REMEMBER));
    if (rememberUser != null) {
      let _this = this
      _this.user = rememberUser
    }
  },
  methods: {
    checkboxClick: function () {
      let _this = this
      if (_this.remember) {
          _this.remember = false
      }else{
        _this.remember = true
      }
    },
    login: function () {
      let _this = this
      let md5 = hex_md5(_this.user.password)
      let rememberUser = JSON.parse(localStorage.getItem(LOCAL_KEY_USER_REMEMBER)) || {}
      if(md5 !== rememberUser.md5){
        _this.user.password = hex_md5(_this.user.password + KEY)
      }
      Loading.show()
      _this.$ajax.post(process.env.VUE_APP_SERVER + "/system/admin/login", _this.user).then((respond) => {
        let resp = respond.data
        Loading.hide()
        if (resp.success) {
          sessionStorage.setItem(SESSION_KEY_LOGIN_USER, JSON.stringify(resp.content))
          _this.$router.push("/welcome")
          let loginUser = resp.content
          if (_this.remember) {
            md5 = hex_md5(_this.user.password)
            localStorage.setItem(LOCAL_KEY_USER_REMEMBER, JSON.stringify({
              loginName: loginUser.loginName,
              password: _this.user.password,
              md5:md5
            }));
          } else {
            localStorage.setItem(LOCAL_KEY_USER_REMEMBER, null);
          }
        } else {
          Toast.warning(resp.msg)
        }
      })
    }
  }
}
</script>
<style scoped>

</style>
