<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    :footer="null">

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="报警类别">
          {{getTypeText(model.alarmType)}}
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="摄像头ID">
          {{model.cameraId}}
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="摄像头名称">
          {{model.cameraName}}
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="报警时间">
          {{model.createTime}}
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="报警图片">
          <img v-show="model.srcPicData" width="100%" height="100%" :src="'data:image/png;base64,'+model.srcPicData">
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "MAlarmModal",
    data () {
      return {
        title:"操作",
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

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        alarmType:{rules: [{ required: true, message: '请输入报警类别!' }]},
        cameraId:{rules: [{ required: true, message: '请输入摄像头ID!' }]},
        cameraName:{rules: [{ required: true, message: '请输入摄像头名称!' }]},
        srcPicData:{rules: [{ required: true, message: '请输入报警图片!' }]},
        },
        url: {
          add: "/alarm/mAlarm/add",
          edit: "/alarm/mAlarm/edit",
        },
      }
    },
    created () {
    },
    methods: {
      getTypeText(alarmType) {
        if (alarmType == "20") {
          return "烟雾"
        }else if (alarmType == "21") {
          return "火灾"
        } else if (alarmType == "29") {
          return "人员离岗"
        }
        return ""
      },
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'alarmType','cameraId','cameraName','srcPicData','delFlag'))
		  //时间格式化
        });

      },
      close () {
        const that = this;
        that.$emit('close');
        that.visible = false;
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
            //时间格式化
            
            console.log(formData)
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
        const that = this;
        that.visible = false;
        let formData = that.model
        formData.status = 1
        httpAction(that.url.edit,formData,'put').then((res)=>{
          if(res.success){
            that.$message.success(res.message);
          }else{
            that.$message.warning(res.message);
          }
        })

        this.close()
      },


    }
  }
</script>

<style lang="less" scoped>

</style>