<template>
  <div id="app" v-cloak>
    <iframe v-if="flag" :id="id" :src="url" frameborder="0" width="100%" height="800px" scrolling="auto"></iframe>
    <mm-enterprise-modal v-cloak v-show="!flag" ref="modalForm" @ok="modalFormOk"></mm-enterprise-modal>
  </div>
</template>

<script>
  import PageLayout from '../page/PageLayout'
  import RouteView from './RouteView'
  import MmEnterpriseModal from '@/views/system/modules/MmEnterpriseModal'
  import Vue from "vue";
  import {ACCESS_TOKEN} from "@/store/mutation-types";
  import {getAction} from "@api/manage";

  export default {

    name: "IframePageContent",
    components: {MmEnterpriseModal},
    inject:['closeCurrent'],
    data () {
      return {
        url: "http://120.194.68.73:9999/#/fromUrl?topage=bigscreennew&buildid=206320&showmenu=true&token=22D9BF4F773D73927DEB2A666CE1FA81",
        id:"",
        flag: false,
        list: '/enterprise/mmEnterprise/list'
      }
    },
    created () {
      this.goUrl()
      this.$nextTick(()=>{
        this.loadData(this)
      })
    },
    updated () {
      this.goUrl()
    },
    watch: {
      $route(to, from) {
        this.goUrl();
      }
    },
    methods: {
      modalFormOk(){
        window.location.href="/";
      },
      loadData(that) {
        if(!this.list){
          that.$message.error("请设置list属性!")
          return
        }

        that.$refs.modalForm.add();
        that.$refs.modalForm.title = "新增";
        that.$refs.modalForm.disableSubmit = false;

        var params = {};//查询条件

        let v_token = Vue.ls.get(ACCESS_TOKEN);
        params.token = v_token;

        that.loading = true;
        getAction(this.list, params).then((res) => {
          if (res.success) {
            if (res.result.total > 0) {
              that.flag = true
            }
          }
          if(res.code===510){
            that.$message.warning(res.message)
          }
          that.loading = false;
        })
      },
      goUrl () {
        let url = this.$route.meta.url
        let id = this.$route.path
        this.id = id
        //url = "http://www.baidu.com"
        console.log("------url------"+url)
        if (url !== null && url !== undefined) {
          this.url = url;
          /*update_begin author:wuxianquan date:20190908 for:判断打开方式，新窗口打开时this.$route.meta.internalOrExternal==true */
          if(this.$route.meta.internalOrExternal != undefined && this.$route.meta.internalOrExternal==true){
            this.closeCurrent();
            window.open(this.url);
          }
          /*update_end author:wuxianquan date:20190908 for:判断打开方式，新窗口打开时this.$route.meta.internalOrExternal==true */

        }
      }
    }
  }
</script>

<style>
[v-cloak]{
  display:none !important;
}
</style>