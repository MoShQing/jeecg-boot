<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="风险分析对象名称">
              <a-input placeholder="请输入风险分析对象名称" v-model="queryParam.riskName"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="责任部门">
              <a-input placeholder="请输入责任部门" v-model="queryParam.chargeDepart"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="责任人">
              <a-input placeholder="请输入责任人" v-model="queryParam.chargeMan"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="责任人手机号">
              <a-input placeholder="请输入责任人手机号" v-model="queryParam.chargePhone"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="企业编码">
              <a-input placeholder="请输入企业编码" v-model="queryParam.enterpriseCode"></a-input>
            </a-form-item>
          </a-col>
          </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('安全风险管控清单')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <mRiskManagement-modal ref="modalForm" @ok="modalFormOk"></mRiskManagement-modal>
  </a-card>
</template>

<script>
  import MRiskManagementModal from './modules/MRiskManagementModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "MRiskManagementList",
    mixins:[JeecgListMixin],
    components: {
      MRiskManagementModal
    },
    data () {
      return {
        description: '安全风险管控清单管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '风险分析对象名称',
            align:"center",
            dataIndex: 'riskName'
           },
		   {
            title: '责任部门',
            align:"center",
            dataIndex: 'chargeDepart'
           },
		   {
            title: '责任人',
            align:"center",
            dataIndex: 'chargeMan'
           },
		   {
            title: '责任人手机号',
            align:"center",
            dataIndex: 'chargePhone'
           },
		   {
            title: '企业编码',
            align:"center",
            dataIndex: 'enterpriseCode'
           },
		   {
            title: '创建时间',
            align:"center",
            dataIndex: 'createTimeStr'
           },
		   {
            title: '安全风险分析单元',
            align:"center",
            dataIndex: 'riskUnit'
           },
		   {
            title: '危险源',
            align:"center",
            dataIndex: 'riskSource'
           },
		   {
            title: '安全风险',
            align:"center",
            dataIndex: 'securityRisks'
           },
		   {
            title: '诱发事故类型',
            align:"center",
            dataIndex: 'type'
           },
		   {
            title: '是否为重点管控风险',
            align:"center",
            dataIndex: 'importantFlag'
           },
		   {
            title: '详细位置',
            align:"center",
            dataIndex: 'locationDetail'
           },
		   {
            title: '伤亡/财产损失预测',
            align:"center",
            dataIndex: 'prediction'
           },
		   {
            title: '管控层级',
            align:"center",
            dataIndex: 'managementLevel'
           },
		   {
            title: '危险因素',
            align:"center",
            dataIndex: 'riskFactor'
           },
		   {
            title: '应急处置主要措施',
            align:"center",
            dataIndex: 'scheme'
           },
		   {
            title: '分线等级',
            align:"center",
            dataIndex: 'riskLevel'
           },
		   {
            title: '辨识时间',
            align:"center",
            dataIndex: 'identificationTime'
           },
		   {
            title: '管控措施分类',
            align:"center",
            dataIndex: 'managementMethodType'
           },
		   {
            title: '岗位责任人',
            align:"center",
            dataIndex: 'jobManager'
           },
		   {
            title: '具体管控措施',
            align:"center",
            dataIndex: 'schemeDetail'
           },
		   {
            title: '隐患排查内容',
            align:"center",
            dataIndex: 'debugContent'
           },
		   {
            title: '隐患描述',
            align:"center",
            dataIndex: 'riskDetail'
           },
		   {
            title: '记录状态',
            align:"center",
            dataIndex: 'status'
           },
		   {
            title: 'delFlag',
            align:"center",
            dataIndex: 'delFlag'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/riskManagement/mRiskManagement/list",
          delete: "/riskManagement/mRiskManagement/delete",
          deleteBatch: "/riskManagement/mRiskManagement/deleteBatch",
          exportXlsUrl: "riskManagement/mRiskManagement/exportXls",
          importExcelUrl: "riskManagement/mRiskManagement/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>