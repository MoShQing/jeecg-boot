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
          label="危化品名称">
          <a-input placeholder="请输入危化品名称" v-decorator="['chemicalName', validatorRules.chemicalName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="CAS号">
          <a-input placeholder="请输入CAS号" v-decorator="['cas', validatorRules.cas ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否重点监管危险化学品">
<!--          <a-input placeholder="请输入是否重点监管危险化学品" v-decorator="['importantFlag', validatorRules.importantFlag ]" />-->
          <a-select v-decorator="[ 'importantFlag', {rules: [{ required: true, message: '请输入是否重点监管危险化学品'}]} ]" placeholder="请输入是否重点监管危险化学品">
            <a-select-option :value="0">是</a-select-option>
            <a-select-option :value="1">否</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="化学品别名">
          <a-input placeholder="请输入化学品别名" v-decorator="['alias', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="英文名称">
          <a-input placeholder="请输入英文名称" v-decorator="['chemicalNameEn', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="危险性类别">
          <a-input placeholder="请输入危险性类别" v-decorator="['dangerType', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="存储地点">
          <a-input placeholder="请输入存储地点" v-decorator="['storageLocation', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="存储方式">
          <a-input placeholder="请输入存储方式" v-decorator="['storageMethod', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="创建人">
          <a-input placeholder="请输入创建人" v-decorator="['createBy', {}]" />
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
    name: "MChemicalModal",
    data () {
      return {
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 8 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        chemicalName:{rules: [{ required: true, message: '请输入危化品名称!' }]},
        cas:{rules: [{ required: true, message: '请输入cas!' }]},
        importantFlag:{rules: [{ required: true, message: '请输入是否重点监管危险化学品!' }]},
        },
        url: {
          add: "/chemical/mChemical/add",
          edit: "/chemical/mChemical/edit",
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
          this.form.setFieldsValue(pick(this.model,'chemicalName','cas','importantFlag','alias','chemicalNameEn','dangerType','storageLocation','storageMethod','delFlag'))
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