<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
<!--    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="chemicalName">
              <a-input placeholder="请输入chemicalName" v-model="queryParam.chemicalName"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="cas">
              <a-input placeholder="请输入cas" v-model="queryParam.cas"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :md="6" :sm="8">
              <a-form-item label="importantFlag">
                <a-input placeholder="请输入importantFlag" v-model="queryParam.importantFlag"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="alias">
                <a-input placeholder="请输入alias" v-model="queryParam.alias"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="chemicalNameEn">
                <a-input placeholder="请输入chemicalNameEn" v-model="queryParam.chemicalNameEn"></a-input>
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
    </div>-->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
<!--      <a-button type="primary" icon="download" @click="handleExportXls('重点监管化学品')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>-->
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
<!--      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>-->

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />

          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
            <a>删除</a>
          </a-popconfirm>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <mChemical-modal ref="modalForm" @ok="modalFormOk"></mChemical-modal>
  </a-card>
</template>

<script>
import MChemicalModal from './modules/MChemicalModal'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'

export default {
  name: "MChemicalList",
  mixins:[JeecgListMixin],
  components: {
    MChemicalModal
  },
  data () {
    return {
      description: '重点监管化学品管理页面',
      // 表头
      columns: [
        {
          title: '序号',
          dataIndex: '',
          key:'rowIndex',
          width:60,
          align:"center",
          customRender:function (t,r,index) {
            return parseInt(index)+1;
          }
        },
        {
          title: '危化品名称',
          align:"center",
          dataIndex: 'chemicalName'
        },
        {
          title: 'CAS号',
          align:"center",
          dataIndex: 'cas'
        },
        {
          title: '是否重点监管危险化学品',
          align:"center",
          dataIndex: 'importantFlag',
          customRender: (text, record, index) => {
            let re = "";
            if (text === 0) {
              re = "是";
            } else if (text === 1) {
              re = "否";
            }
            return re;
          }
        },
        {
          title: '危险性类别',
          align:"center",
          dataIndex: 'dangerType'
        },
        {
          title: '存放地点',
          align:"center",
          dataIndex: 'storageLocation'
        },
        {
          title: '创建时间',
          align:"center",
          dataIndex: 'createTime'
        },
        {
          title: '修改时间',
          align:"center",
          dataIndex: 'updateTime'
        },
        {
          title: '创建人',
          align:"center",
          dataIndex: 'createBy'
        },
        {
          title: '操作',
          dataIndex: 'action',
          align:"center",
          scopedSlots: { customRender: 'action' },
        }
      ],
      url: {
        list: "/chemical/mChemical/list",
        delete: "/chemical/mChemical/delete",
        deleteBatch: "/chemical/mChemical/deleteBatch",
        exportXlsUrl: "chemical/mChemical/exportXls",
        importExcelUrl: "chemical/mChemical/importExcel",
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