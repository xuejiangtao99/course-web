<template>
  <div>
      <p>
        <button @click="list(1)" class="btn btn-white btn-default btn-round">
          <i class="ace-icon fa fa-refresh"></i>
          刷新
        </button>

        <button @click="add()" class="btn btn-white btn-default btn-round" style="margin-left: 20px">
          <i class="ace-icon fa fa-edit"></i>
          新增
        </button>
      </p>
      <table id="simple-table" class="table  table-bordered table-hover">

    <thead>
    <tr>
      <th>id</th>
      <th>课程id</th>
      <th>章节名称</th>
      <th>操作</th>
    </tr>
    </thead>

    <tbody>
    <tr v-for="chapter in chapters">
      <td>{{chapter.id}}</td>
      <td>{{chapter.courseId}}</td>
      <td>{{chapter.name}}</td>
      <td>
        <div class="hidden-sm hidden-xs btn-group">
          <button class="btn btn-xs btn-info" @click="edit(chapter)">
            <i class="ace-icon fa fa-pencil bigger-120"></i>
          </button>
          <button class="btn btn-xs btn-danger" @click="deleteById(chapter.id)">
            <i class="ace-icon fa fa-trash-o bigger-120"></i>
          </button>
        </div>
      </td>
    </tr>
    </tbody>
  </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:item-count="itemCount"></pagination>

      <div class="modal fade" id="editModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document" >
          <div class="modal-content">
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">{{editTitle}}</h4>
          </div>
            <div class="modal-body">
              <form class="form-horizontal">
                <div class="form-group">
                  <label class="col-sm-2 control-label">名称</label>
                  <div class="col-sm-10">
                    <input v-model="chapter.name"  class="form-control"  placeholder="请输入大章名称">
                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-2 control-label">课程ID</label>
                  <div class="col-sm-10">
                    <input v-model="chapter.courseId" class="form-control"  placeholder="课程ID">
                  </div>
                </div>
              </form>
            </div>
            <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" @click="saveOrUpdate()">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div>
  </div>
</template>

<script>
  import Pagination from '@/components/pagination'
  export default {
    name:'chapter',
    components: {Pagination},
    mounted() {
      let _this = this;
      _this.list(1);
    },
    data:function (){
      return {
        chapters:[],
        editTitle:'新增',
        chapter:{},
        pageDto:{page:1,size:1},
        //todo 后续更改动态
        itemCount:5
      }
    },
    methods:{
      list(page){
        let _this =this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER+"/business/admin/queryCharacterList",{
          page:page,
          size:_this.$refs.pagination.size})
            .then((response)=>{
          console.log(response.data)
              let data = response.data;
              if(data.success){
                Loading.hide();
                 _this.chapters = data.content.list;
              }
              _this.$refs.pagination.render(page,data.content.total)
        })
      },
      add:function (){
        this.chapter = {}
        $('#editModal').modal("show")
        // $('#editModal').modal({backdrop:'static'}) //点击空白地方禁止关闭
      },

      edit:function (chapter){
        let _this = this
        _this.chapter = $.extend({},chapter)
        _this.editTitle = "修改"
        $('#editModal').modal("show")
      },

      deleteById(id) {
        let _this = this
        Confirm.show("删除后则不可恢复",function (){
          Loading.show()
          _this.$ajax.delete(process.env.VUE_APP_SERVER+"/business/admin/deleteById/"+id)
              .then(response=>{
                if(response.data.success){
                  Toast.success("删除成功")
                  Loading.hide()
                  _this.list(1)
                }
              })
        })
      },

      saveOrUpdate(){
        let _this = this
        if(
            !Validator.require(_this.chapter.name,"名称") ||
            !Validator.require(_this.chapter.courseId,"课程Id") ||
            !Validator.length(_this.chapters.courseId,1,8)
        ){

          return ;
        }
        _this.$ajax.post(process.env.VUE_APP_SERVER+"/business/admin/save",_this.chapter)
            .then((response)=>{
              Loading.show()
              if(response.data.success){
                $('#editModal').modal('hide')
                Toast.success("保存成功")
                Loading.hide()
                _this.list(1)
              }else {
                console.log(response.data)
                Toast.warning(response.data.msg)
              }
            })
      }
    }
  }
</script>