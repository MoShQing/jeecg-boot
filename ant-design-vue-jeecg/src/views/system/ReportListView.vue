<template>
  <a-card :bordered="false">

    <!-- 左侧面板 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
<!--        <a-row :gutter="12">
          <a-col :md="7" :sm="8">
            <a-form-item label="学生姓名" :labelCol="{span: 6}" :wrapperCol="{span: 14, offset: 1}">
              <a-input placeholder="请输入学生姓名" v-model="queryParam.dictName"></a-input>
            </a-form-item>
          </a-col>
          &lt;!&ndash;<a-col :md="7" :sm="8">
            <a-form-item label="字典编号" :labelCol="{span: 6}" :wrapperCol="{span: 14, offset: 1}">
              <a-input placeholder="请输入字典编号" v-model="queryParam.dictCode"></a-input>
            </a-form-item>
          </a-col>&ndash;&gt;
          <a-col :md="7" :sm="8">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>
        </a-row>-->
      </a-form>

      <div class="table-operator" style="border-top: 5px">
        <!--<a-button @click="handleAdd" type="primary" icon="plus">添加</a-button>
        <a-button type="primary" icon="download" @click="handleExportXls('字典信息')">导出</a-button>
        <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
          <a-button type="primary" icon="import">导入</a-button>
        </a-upload>

        <a-button type="primary" icon="hdd" @click="openDeleteList">回收站</a-button>-->
      </div>

      <a-table
        ref="table"
        rowKey="id"
        size="middle"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        @change="handleTableChange">
        <span slot="action" slot-scope="text, record">
          <a @click="handleView(record)">
            <a-icon type="edit"/>
            单项指标查看
          </a>
          <a-divider type="vertical"/>
          <a @click="viewDictItem(record)">
            <a-icon type="setting"/>
            体重指数查看
          </a>
        </span>
      </a-table>

    </div>
    <report-modal ref="modalForm" @ok="modalFormOk"></report-modal>
    <dict-item-list ref="dictItemList"></dict-item-list>
    <dict-delete-list ref="dictDeleteList"></dict-delete-list>
  </a-card>
</template>

<script>
  import { filterObj } from '@/utils/util';
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ReportModal from './modules/ReportModal'
  import DictItemList from './DictItemList'
  import DictDeleteList from './DictDeleteList'

  export default {
    name: "DictList",
    mixins:[JeecgListMixin],
    components: {ReportModal, DictItemList,DictDeleteList},
    data() {
      return {
        description: '这是数据字典页面',
        visible: false,
        // 查询条件
        queryParam: {
          dictCode: '',
          dictName: "A",
        },
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 120,
            align: "center",
            customRender: function (t, r, index) {
              return parseInt(index) + 1;
            }
          },
          {
            title: '学生姓名',
            align: "left",
            dataIndex: 'dictName',
          },
          {
            title: '性别',
            align: "left",
            dataIndex: 'sex',
            customRender: function (text) {
              if (text == 1) {
                return "禁用";
              } else if (text == 0) {
                return "启用";
              } else {
                return text;
              }
            }
          },
          {
            title: '肺活量',
            align: "left",
            dataIndex: 'description1',
          },
          {
            title: '体前屈',
            align: "left",
            dataIndex: 'description2',
          },
          {
            title: '立定跳远',
            align: "left",
            dataIndex: 'description3',
          },
          {
            title: '引体向上',
            align: "left",
            dataIndex: 'description4',
            customRender: function (text, row, index) {
              if (row.sex == '男') {
                return text;
              } else {
                return '--';
              }
            }
          },
          {
            title: '1000米',
            align: "left",
            dataIndex: 'description5',
            customRender: function (text, row, index) {
              if (row.sex == '男') {
                return text;
              } else {
                return '--';
              }
            }
          },
          {
            title: '仰卧起坐',
            align: "left",
            dataIndex: 'description6',
            customRender: function (text, row, index) {
              if (row.sex == '女') {
                return text;
              } else {
                return '--';
              }
            }
          },
          {
            title: '800米',
            align: "left",
            dataIndex: 'description7',
            customRender: function (text, row, index) {
              if (row.sex == '女') {
                return text;
              } else {
                return '--';
              }
            }
          },
          {
            title: '50米',
            align: "left",
            dataIndex: 'description8',
          },
          {
            title: '体重指数',
            align: "left",
            dataIndex: 'description9',
          },
          {
            title: '操作',
            dataIndex: 'action',
            align: "center",
            scopedSlots: {customRender: 'action'},
          }
        ],
        dict: "",
        labelCol: {
          xs: {span: 8},
          sm: {span: 5},
        },
        wrapperCol: {
          xs: {span: 16},
          sm: {span: 19},
        },
        url: {
          list: "/sys/report/list",
          delete: "/sys/report/delete",
          exportXlsUrl: "sys/report/exportXls",
          importExcelUrl: "sys/report/importExcel",
        },
      }
    },
    computed: {
      importExcelUrl: function () {
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      push() {
        this.$router.push({
          path: '/isystem/dict',
          name: 'isystem-dict'
        });
      },

      getQueryParams() {
        var param = Object.assign({}, this.queryParam, this.isorter);
        param.field = this.getQueryField();
        param.pageNo = this.ipagination.current;
        param.pageSize = this.ipagination.pageSize;
        return filterObj(param);
      },
      //取消选择
      cancelDict() {
        this.dict = "";
        this.visible = false;
        this.loadData();
      },
      //编辑字典数据
      editDictItem(record) {
        this.$refs.dictItemList.edit(record);
      },
      viewDictItem(record) {
        this.$refs.dictItemList.view(record);
        this.$refs.dictItemList.disableSubmit = true;
      },

      // 重置字典类型搜索框的内容
      searchReset() {
        var that = this;
        that.queryParam.dictName = "";
        that.queryParam.dictCode = "";
        that.loadData(this.ipagination.current);
      },

      openDeleteList(){
        this.$refs.dictDeleteList.show()
      }
    },
    watch: {
      openKeys(val) {
        console.log('openKeys', val)
      },
    },
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>