<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    :closable="false"
    :maskClosable="false"
    :keyboard="false"
    @ok="handleOk">

    <template slot="footer">
      <a-button @click="handleOk">保存</a-button>
    </template>

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="企业名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'enterpriseName', validatorRules.enterpriseName]" placeholder="请输入企业名称"></a-input>
        </a-form-item>
        <a-form-item label="统一社会信用代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'creditCode', validatorRules.creditCode]" placeholder="请输入统一社会信用代码"></a-input>
        </a-form-item>
        <a-form-item label="法定代表人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'legalPerson', validatorRules.legalPerson]" placeholder="请输入法定代表人"></a-input>
        </a-form-item>
        <a-form-item label="单位联系方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'enterprisePhone', validatorRules.enterprisePhone]" placeholder="请输入单位联系方式"></a-input>
        </a-form-item>
        <a-form-item label="注册地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'address', validatorRules.address]" placeholder="请输入注册地址"></a-input>
        </a-form-item>
        <a-form-item label="企业编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'enterpriseCode', validatorRules.enterpriseCode]" placeholder="请输入企业编码"></a-input>
        </a-form-item>
        <a-form-item label="从业人数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'staffCount', validatorRules.staffCount]" placeholder="请输入从业人数"></a-input>
        </a-form-item>
        <a-form-item label="单位负责人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'charger', validatorRules.charger]" placeholder="请输入单位负责人"></a-input>
        </a-form-item>
        <a-form-item label="负责人手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'chargerPhone', validatorRules.chargerPhone]" placeholder="请输入负责人手机号"></a-input>
        </a-form-item>
        <a-form-item label="安全负责人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'safetyManager', validatorRules.safetyManager]" placeholder="请输入安全负责人"></a-input>
        </a-form-item>
        <a-form-item label="安全负责人手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'safetyPhone', validatorRules.safetyPhone]" placeholder="请输入安全负责人手机号"></a-input>
        </a-form-item>
        <a-form-item label="单位规模" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'companySize', validatorRules.companySize]" placeholder="请输入单位规模"></a-input>
        </a-form-item>
        <a-form-item label="成立时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择成立时间" v-decorator="[ 'establishedTime', validatorRules.establishedTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="营业状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'businessStatus', validatorRules.businessStatus]" placeholder="请输入营业状态"></a-input>
        </a-form-item>
        <a-form-item label="安全生产标准化等级" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'level', validatorRules.level]" placeholder="请输入安全生产标准化等级"></a-input>
        </a-form-item>
        <a-form-item label="公司类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'companyType', validatorRules.companyType]" placeholder="请输入公司类型"></a-input>
        </a-form-item>
        <a-form-item label="行业类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'industryType', validatorRules.industryType]" placeholder="请输入行业类型"></a-input>
        </a-form-item>
        <a-form-item label="监管行业类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'manageType', validatorRules.manageType]" placeholder="请输入监管行业类型"></a-input>
        </a-form-item>
        <a-form-item label="监管副行业类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'manageSecondType', validatorRules.manageSecondType]" placeholder="请输入监管副行业类型"></a-input>
        </a-form-item>
        <a-form-item label="行政区划分" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'region', validatorRules.region]" placeholder="请输入行政区划分"></a-input>
        </a-form-item>
        <a-form-item label="经营范围" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'businessScope', validatorRules.businessScope]" placeholder="请输入经营范围"></a-input>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: "MmEnterpriseModal",
    components: { 
      JDate,
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

        confirmLoading: false,
        validatorRules:{
        enterpriseName:{rules: [{ required: true, message: '请输入企业名称!' }]},
        creditCode:{rules: [{ required: true, message: '请输入统一社会信用代码!' }]},
        legalPerson:{},
        enterprisePhone:{},
        address:{},
        enterpriseCode:{rules: [{ required: true, message: '请输入企业编码!' }]},
        staffCount:{},
        charger:{rules: [{ required: true, message: '请输入单位负责人!' }]},
        chargerPhone:{rules: [{ required: true, message: '请输入负责人手机号!' }]},
        safetyManager:{rules: [{ required: true, message: '请输入安全负责人!' }]},
        safetyPhone:{rules: [{ required: true, message: '请输入安全负责人手机号!' }]},
        companySize:{},
        establishedTime:{},
        businessStatus:{},
        level:{rules: [{ required: true, message: '请输入安全生产标准化等级!' }]},
        companyType:{},
        industryType:{},
        manageType:{},
        manageSecondType:{},
        region:{rules: [{ required: true, message: '请输入行政区划分!' }]},
        businessScope:{},
        },
        url: {
          add: "/enterprise/mmEnterprise/add",
          edit: "/enterprise/mmEnterprise/edit",
        }
     
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
          this.form.setFieldsValue(pick(this.model,'enterpriseName','creditCode','legalPerson','enterprisePhone','address','enterpriseCode','staffCount','charger','chargerPhone','safetyManager','safetyPhone','companySize','establishedTime','businessStatus','level','companyType','industryType','manageType','manageSecondType','region','businessScope'))
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
        this.form.setFieldsValue(pick(row,'enterpriseName','creditCode','legalPerson','enterprisePhone','address','enterpriseCode','staffCount','charger','chargerPhone','safetyManager','safetyPhone','companySize','establishedTime','businessStatus','level','companyType','industryType','manageType','manageSecondType','region','businessScope'))
      },

      
    }
  }
</script>