<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
<!--    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="危化源名称">
              <a-input placeholder="请输入危化源名称" v-model="queryParam.dangersSourceName"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="注册编号">
              <a-input placeholder="请输入注册编号" v-model="queryParam.regNo"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :md="6" :sm="8">
              <a-form-item label="活动区域">
                <a-input placeholder="请输入活动区域" v-model="queryParam.area"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="危险类型">
                <a-input placeholder="请输入危险类型" v-model="queryParam.type"></a-input>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="等级">
                <a-input placeholder="请输入等级" v-model="queryParam.level"></a-input>
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
<!--      <a-button type="primary" icon="download" @click="handleExportXls('重大危险源')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>-->
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
    <mDangersSource-modal ref="modalForm" @ok="modalFormOk"></mDangersSource-modal>
  </a-card>
</template>

<script>
import MDangersSourceModal from './modules/MDangersSourceModal'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'

export default {
  name: "MDangersSourceList",
  mixins:[JeecgListMixin],
  components: {
    MDangersSourceModal
  },
  data () {
    return {
      description: '重大危险源管理页面',
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
          title: '危化源名称',
          align:"center",
          dataIndex: 'dangersSourceName'
        },
        {
          title: '注册编号',
          align:"center",
          dataIndex: 'regNo'
        },
        {
          title: '活动区域',
          align:"center",
          dataIndex: 'area'
        },
        {
          title: '危险类型',
          align:"center",
          dataIndex: 'type',
          customRender: (text, record, index) => {
            let re = "";
            if (text === 0) {
              re = "一般";
            } else if (text === 1) {
              re = "重大";
            }
            return re;
          }
        },
        {
          title: '等级',
          align:"center",
          dataIndex: 'level',
          customRender: (text, record, index) => {
            let re = "";
            if (text === 0) {
              re = "无";
            } else if (text === 1) {
              re = "Ⅳ级";
            } else if (text === 2) {
              re = "Ⅲ级";
            } else if (text === 3) {
              re = "Ⅱ级";
            } else if (text === 4) {
              re = "Ⅰ级";
            }
            return re;
          }
        },
        {
          title: '责任人',
          align:"center",
          dataIndex: 'charger'
        },
        {
          title: '目前状态',
          align:"center",
          dataIndex: 'status',
          customRender: (text, record, index) => {
            let re = "";
            if (text === 0) {
              re = "受控";
            } else if (text === 1) {
              re = "非受控";
            }
            return re;
          }
        },
        {
          title: '确认依据',
          align:"center",
          dataIndex: 'basis'
        },
        {
          title: '可能造成后果',
          align:"center",
          dataIndex: 'result'
        },
        {
          title: '控制措施',
          align:"center",
          dataIndex: 'measure'
        },
        {
          title: '周围环境',
          align:"center",
          dataIndex: 'enviroment'
        },
        {
          title: '操作',
          dataIndex: 'action',
          align:"center",
          scopedSlots: { customRender: 'action' },
        }
      ],
      url: {
        list: "/dangerssource/mDangersSource/list",
        delete: "/dangerssource/mDangersSource/delete",
        deleteBatch: "/dangerssource/mDangersSource/deleteBatch",
        exportXlsUrl: "dangerssource/mDangersSource/exportXls",
        importExcelUrl: "dangerssource/mDangersSource/importExcel",
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