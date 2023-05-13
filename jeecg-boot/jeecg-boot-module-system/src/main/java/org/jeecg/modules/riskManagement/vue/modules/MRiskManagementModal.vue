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
          label="风险分析对象名称">
          <a-input placeholder="请输入风险分析对象名称" v-decorator="['riskName', validatorRules.riskName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="责任部门">
          <a-input placeholder="请输入责任部门" v-decorator="['chargeDepart', validatorRules.chargeDepart ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="责任人">
          <a-input placeholder="请输入责任人" v-decorator="['chargeMan', validatorRules.chargeMan ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="责任人手机号">
          <a-input placeholder="请输入责任人手机号" v-decorator="['chargePhone', validatorRules.chargePhone ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="企业编码">
          <a-input placeholder="请输入企业编码" v-decorator="['enterpriseCode', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="创建时间">
          <a-input-number v-decorator="[ 'createTimeStr', validatorRules.createTimeStr ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="安全风险分析单元">
          <a-input placeholder="请输入安全风险分析单元" v-decorator="['riskUnit', validatorRules.riskUnit ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="危险源">
          <a-input placeholder="请输入危险源" v-decorator="['riskSource', validatorRules.riskSource ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="安全风险">
          <a-input placeholder="请输入安全风险" v-decorator="['securityRisks', validatorRules.securityRisks ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="诱发事故类型">
          <a-input placeholder="请输入诱发事故类型" v-decorator="['type', validatorRules.type ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否为重点管控风险">
          <a-input placeholder="请输入是否为重点管控风险" v-decorator="['importantFlag', validatorRules.importantFlag ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="详细位置">
          <a-input placeholder="请输入详细位置" v-decorator="['locationDetail', validatorRules.locationDetail ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="伤亡/财产损失预测">
          <a-input placeholder="请输入伤亡/财产损失预测" v-decorator="['prediction', validatorRules.prediction ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="管控层级">
          <a-input placeholder="请输入管控层级" v-decorator="['managementLevel', validatorRules.managementLevel ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="危险因素">
          <a-input placeholder="请输入危险因素" v-decorator="['riskFactor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="应急处置主要措施">
          <a-input placeholder="请输入应急处置主要措施" v-decorator="['scheme', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="分线等级">
          <a-input placeholder="请输入分线等级" v-decorator="['riskLevel', validatorRules.riskLevel ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="辨识时间">
          <a-input-number v-decorator="[ 'identificationTime', validatorRules.identificationTime ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="管控措施分类">
          <a-input placeholder="请输入管控措施分类" v-decorator="['managementMethodType', validatorRules.managementMethodType ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="岗位责任人">
          <a-input placeholder="请输入岗位责任人" v-decorator="['jobManager', validatorRules.jobManager ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="具体管控措施">
          <a-input placeholder="请输入具体管控措施" v-decorator="['schemeDetail', validatorRules.schemeDetail ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="隐患排查内容">
          <a-input placeholder="请输入隐患排查内容" v-decorator="['debugContent', validatorRules.debugContent ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="隐患描述">
          <a-input placeholder="请输入隐患描述" v-decorator="['riskDetail', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="记录状态">
          <a-input placeholder="请输入记录状态" v-decorator="['status', validatorRules.status ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="delFlag">
          <a-input placeholder="请输入delFlag" v-decorator="['delFlag', {}]" />
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
    name: "MRiskManagementModal",
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
        riskName:{rules: [{ required: true, message: '请输入风险分析对象名称!' }]},
        chargeDepart:{rules: [{ required: true, message: '请输入责任部门!' }]},
        chargeMan:{rules: [{ required: true, message: '请输入责任人!' }]},
        chargePhone:{rules: [{ required: true, message: '请输入责任人手机号!' }]},
        createTimeStr:{rules: [{ required: true, message: '请输入创建时间!' }]},
        riskUnit:{rules: [{ required: true, message: '请输入安全风险分析单元!' }]},
        riskSource:{rules: [{ required: true, message: '请输入危险源!' }]},
        securityRisks:{rules: [{ required: true, message: '请输入安全风险!' }]},
        type:{rules: [{ required: true, message: '请输入诱发事故类型!' }]},
        importantFlag:{rules: [{ required: true, message: '请输入是否为重点管控风险!' }]},
        locationDetail:{rules: [{ required: true, message: '请输入详细位置!' }]},
        prediction:{rules: [{ required: true, message: '请输入伤亡/财产损失预测!' }]},
        managementLevel:{rules: [{ required: true, message: '请输入管控层级!' }]},
        riskLevel:{rules: [{ required: true, message: '请输入分线等级!' }]},
        identificationTime:{rules: [{ required: true, message: '请输入辨识时间!' }]},
        managementMethodType:{rules: [{ required: true, message: '请输入管控措施分类!' }]},
        jobManager:{rules: [{ required: true, message: '请输入岗位责任人!' }]},
        schemeDetail:{rules: [{ required: true, message: '请输入具体管控措施!' }]},
        debugContent:{rules: [{ required: true, message: '请输入隐患排查内容!' }]},
        status:{rules: [{ required: true, message: '请输入记录状态!' }]},
        },
        url: {
          add: "/riskManagement/mRiskManagement/add",
          edit: "/riskManagement/mRiskManagement/edit",
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
          this.form.setFieldsValue(pick(this.model,'riskName','chargeDepart','chargeMan','chargePhone','enterpriseCode','createTimeStr','riskUnit','riskSource','securityRisks','type','importantFlag','locationDetail','prediction','managementLevel','riskFactor','scheme','riskLevel','identificationTime','managementMethodType','jobManager','schemeDetail','debugContent','riskDetail','status','delFlag'))
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