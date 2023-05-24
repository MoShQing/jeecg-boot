<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
<!--      <a-button type="primary" icon="download" @click="handleExportXls('安全风险四色分布图')">导出</a-button>
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
        :rowSelection="{fixed:true,selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        :scroll="tableScroll"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="图片不存在" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>

        <template slot="picPath" slot-scope="text, record, index">
          <img @click="openPic(text)" :src="getAvatarView(text)" alt="头像" style="height:104px;max-width:300px"/>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />

          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
            <a>删除</a>
          </a-popconfirm>
        </span>

      </a-table>
    </div>

    <mmRiskLevel-modal ref="modalForm" @ok="modalFormOk"></mmRiskLevel-modal>
    <a-modal
      title="详情"
      :visible="visible"
      :footer="null"
      width="100%"
      centered
      @cancel="closePic"
    >
      <img width="100%" height="100%" :src="picPath">
    </a-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import MmRiskLevelModal from './modules/MmRiskLevelModal'

  export default {
    name: "MmRiskLevelList",
    mixins:[JeecgListMixin],
    components: {
      MmRiskLevelModal,
    },
    data () {
      return {
        description: '安全风险四色分布图管理页面',
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
            title:'风险分布图名称',
            align:"center",
            dataIndex: 'picName'
          },
          {
            title:'风险分布图',
            align:"center",
            dataIndex: 'picPath',
            scopedSlots: {customRender: 'picPath'}
          },
          {
            title:'风险区域',
            align:"center",
            dataIndex: 'riskArea'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/riskLevel/mmRiskLevel/list",
          delete: "/riskLevel/mmRiskLevel/delete",
          deleteBatch: "/riskLevel/mmRiskLevel/deleteBatch",
          exportXlsUrl: "/riskLevel/mmRiskLevel/exportXls",
          importExcelUrl: "riskLevel/mmRiskLevel/importExcel",
          imgerver: 'http://localhost:3000/tice/sys/common/view',
        },
        dictOptions:{
        },
        tableScroll:{x :3*147+50},
        visible: false,
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      closePic() {
        this.visible = false
      },
      openPic(picPath) {
        this.visible = true
        this.picPath = this.getAvatarView(picPath);
      },

      getAvatarView(picPath){
        return this.url.imgerver +"/"+ picPath;
      },
      initDictConfig(){
      }
       
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>