<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="风险分布图名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'picName', validatorRules.picName]" placeholder="请输入风险分布图名称"></a-input>
        </a-form-item>
        <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'createBy', validatorRules.createBy]" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="风险区域" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'riskArea', validatorRules.riskArea]" placeholder="请输入风险区域"></a-input>
        </a-form-item>
        <a-form-item label="风险分布图" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-upload
            listType="picture-card"
            class="avatar-uploader"
            :showUploadList="false"
            :action="uploadAction"
            :data="{'isup':1}"
            :headers="headers"
            :beforeUpload="beforeUpload"
            @change="handleChange"
          >
            <img v-if="model.picPath" :src="getAvatarView(model.picPath)" alt="头像" style="height:104px;max-width:300px"/>
            <div v-else>
              <a-icon :type="uploadLoading ? 'loading' : 'plus'" />
              <div class="ant-upload-text">上传</div>
            </div>
          </a-upload>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import Vue from "vue";
  import {ACCESS_TOKEN} from "@/store/mutation-types";

  export default {
    name: "MmRiskLevelModal",
    components: { 
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        headers: {

        },

        confirmLoading: false,
        validatorRules:{
        picName:{rules: [{ required: true, message: '请输入风险分布图名称!' }]},
        picPath:{rules: [{ required: true, message: '请输入风险分布图!' }]},
        riskArea:{rules: [{ required: true, message: '请输入风险区域!' }]},
        createBy:{rules: [{ required: true, message: '请输入创建人!' }]},
        },
        url: {
          add: "/riskLevel/mmRiskLevel/add",
          edit: "/riskLevel/mmRiskLevel/edit",
          fileUpload: '/tice/sys/common/upload',
          imgerver: 'http://localhost:3000/tice/sys/common/view',
        },

        uploadLoading:false,
     
      }
    },
    created () {
      this.headers["X-Access-Token"] = Vue.ls.get(ACCESS_TOKEN)
    },
    methods: {
      uploadAction:function () {
        return this.url.fileUpload;
      },
      beforeUpload: function(file){
        var fileType = file.type;
        if(fileType.indexOf('image')<0){
          this.$message.warning('请上传图片');
          return false;
        }
        //TODO 验证文件大小
      },
      handleChange (info) {
        this.picUrl = "";
        if (info.file.status === 'uploading') {
          this.uploadLoading = true;
          return
        }
        if (info.file.status === 'done') {
          var response = info.file.response;
          this.uploadLoading = false;
          console.log(response);
          if(response.success){
            this.model.avatar = response.message;
            this.model.picPath = response.message;
          }else{
            this.$message.warning(response.message);
          }
        }
      },
      getAvatarView(picPath){
        return this.url.imgerver +"/"+ picPath;
      },

      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'picName','picPath','riskArea','createBy'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'picName','picPath','riskArea'))
      },

      
    }
  }
</script>