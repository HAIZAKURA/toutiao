(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6f17cd6d"],{"3cfe":function(t,e,a){},"6d5c":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"comm-mgr"},[a("div",{staticClass:"title"},[a("span",{staticClass:"title-text"},[t._v("评 论 管 理")]),a("span",{staticClass:"title-text-small"},[t._v("Comment Management")]),a("div",{staticClass:"title-tooltip"},[a("el-input",{attrs:{placeholder:"请输入主题ID"},model:{value:t.tid,callback:function(e){t.tid=e},expression:"tid"}},[a("template",{slot:"prepend"},[t._v("主题ID")]),a("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:function(e){return t.getCommList(1)}},slot:"append"})],2)],1)]),a("el-divider"),a("div",{ref:"commTable",staticClass:"comm"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.commLoading,expression:"commLoading"}],staticStyle:{width:"100%"},attrs:{data:t.commList,"max-height":t.commListHeight}},[a("el-table-column",{attrs:{prop:"cid",label:"ID","min-width":"30",sortable:""}}),a("el-table-column",{attrs:{prop:"ccont",label:"内容","min-width":"80"}}),a("el-table-column",{attrs:{prop:"ctime",label:"时间","min-width":"50",sortable:"",formatter:t.dateFormat}}),a("el-table-column",{attrs:{prop:"uname",label:"用户","min-width":"30",sortable:""}}),a("el-table-column",{attrs:{label:"操作","min-width":"20"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini",type:"danger",icon:"el-icon-delete"},on:{click:function(a){return t.handleDelete(e.row.cid)}}})]}}])})],1)],1),a("div",{ref:"commPag",staticClass:"comm-pagination"},[a("el-pagination",{attrs:{layout:"total, prev, pager, next","current-page":t.query.pageIndex,"page-size":t.query.pageSize,total:t.pageTotal},on:{"current-change":t.handleCurrentChange}})],1)],1)},i=[],o=a("7f45"),s=a.n(o),l={name:"CommMgr",data:function(){return{tid:"",commList:[],commListHeight:.7*window.innerHeight,commLoading:!1,pageTotal:0,query:{pageIndex:1,pageSize:10}}},methods:{handleDelete:function(t){var e=this;this.$confirm("确认删除评论（ID："+t+"）?","警告",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){e.deleteComm(t)}))},handleCurrentChange:function(t){this.query.pageIndex=t,this.getCommList(this.query.pageIndex)},dateFormat:function(t,e){var a=t[e.property];return void 0===a?"":s()(a).tz("Asia/Shanghai").format("YYYY-MM-DD HH:mm:ss")},getCommList:function(t){var e=this;this.commLoading=!0,this.$axios.get("/api/tcomm/"+this.tid,{params:{page:t}}).then((function(t){1===t.data.code&&(e.commList=t.data.data.list,e.pageTotal=t.data.data.total,e.commLoading=!1)}))},deleteComm:function(t){var e=this;this.$axios.delete("/api/comm/"+t).then((function(t){1===t.data.code?e.$message({message:"删除成功",type:"success"}):e.$message({message:"系统错误或权限不足",type:"error"}),e.getCommList(e.query.pageIndex)})).catch((function(){e.$message({message:"网络错误",type:"error"})}))}}},c=l,m=(a("b045"),a("2877")),r=Object(m["a"])(c,n,i,!1,null,"5923ebb4",null);e["default"]=r.exports},b045:function(t,e,a){"use strict";var n=a("3cfe"),i=a.n(n);i.a}}]);
//# sourceMappingURL=chunk-6f17cd6d.36eb6768.js.map