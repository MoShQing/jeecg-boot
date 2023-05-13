<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="危化源名称">
          <a-input placeholder="请输入危化源名称" v-decorator="['dangersSourceName', validatorRules.dangersSourceName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="注册编号">
          <a-input placeholder="请输入注册编号" v-decorator="['regNo', validatorRules.regNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="活动区域">
          <a-input placeholder="请输入活动区域" v-decorator="['area', validatorRules.area ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="危险类型">
          <a-input placeholder="请输入危险类型" v-decorator="['type', validatorRules.type ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="等级">
          <a-input placeholder="请输入等级" v-decorator="['level', validatorRules.level ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="责任人">
          <a-input placeholder="请输入责任人" v-decorator="['charger', validatorRules.charger ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="目前状态">
          <a-input placeholder="请输入目前状态" v-decorator="['status', validatorRules.status ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="确认依据">
          <a-input placeholder="请输入确认依据" v-decorator="['basis', validatorRules.basis ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="可能造成后果">
          <a-input placeholder="请输入可能造成后果" v-decorator="['result', validatorRules.result ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="控制措施">
          <a-input placeholder="请输入控制措施" v-decorator="['measure', validatorRules.measure ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="周围环境">
          <a-input placeholder="请输入周围环境" v-decorator="['enviroment', validatorRules.enviroment ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="0">
          <a-input placeholder="请输入0" v-decorator="['delFlag', {}]" />
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
    name: "MDangersSourceModal",
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
        dangersSourceName:{rules: [{ required: true, message: '请输入危化源名称!' }]},
        regNo:{rules: [{ required: true, message: '请输入注册编号!' }]},
        area:{rules: [{ required: true, message: '请输入活动区域!' }]},
        type:{rules: [{ required: true, message: '请输入危险类型!' }]},
        level:{rules: [{ required: true, message: '请输入等级!' }]},
        charger:{rules: [{ required: true, message: '请输入责任人!' }]},
        status:{rules: [{ required: true, message: '请输入目前状态!' }]},
        basis:{rules: [{ required: true, message: '请输入确认依据!' }]},
        result:{rules: [{ required: true, message: '请输入可能造成后果!' }]},
        measure:{rules: [{ required: true, message: '请输入控制措施!' }]},
        enviroment:{rules: [{ required: true, message: '请输入周围环境!' }]},
        },
        url: {
          add: "/dangerssource/mDangersSource/add",
          edit: "/dangerssource/mDangersSource/edit",
        },
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'dangersSourceName','regNo','area','type','level','charger','status','basis','result','measure','enviroment','delFlag'))
		  //时间格式化
        });

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
        this.close()
      },


    }
  }
</script>

<style lang="less" scoped>

</style>