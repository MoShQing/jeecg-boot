<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :md="5" :sm="8">
            <a-form-item label="报警类别">
              <a-select v-model="queryParam.alarmType" :allowClear="true" v-decorator="[ 'queryParam.alarmType']" placeholder="请选择报警类型">
                <a-select-option :value="20">烟雾</a-select-option>
                <a-select-option :value="21">火灾</a-select-option>
                <a-select-option :value="29">人员离岗</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="摄像头ID">
              <a-input placeholder="请输入摄像头ID" v-model="queryParam.cameraId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="11" :sm="12">
<!--            <a-form-item label="报警时间段">
              <a-range-picker
                v-model="queryParam.alarmTime"
                name="queryParam.alarmTime"
                style="width: 100%"
                v-decorator="[
                  'buildTime',
                  {rules: [{ required: true, message: '请选择起止日期' }]}
                ]" />
            </a-form-item>-->

            <a-form-item label="报警时间段" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}">
              <j-date v-model="queryParam.createTime_begin" :showTime="true" date-format="YYYY-MM-DD" style="width:45%" placeholder="请选择开始时间" ></j-date>
              <span style="width: 10px;">~</span>
              <j-date v-model="queryParam.createTime_end" :showTime="true" date-format="YYYY-MM-DD" style="width:45%" placeholder="请选择结束时间"></j-date>
            </a-form-item>
          </a-col>

          <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
            <a-col :md="6" :sm="24">
              <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
                <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
                  <!--              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>-->
              </span>
            </a-col>
          </span>
        </a-row>
      </a-form>
    </div>

    <!-- table区域-begin -->
    <div>
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

<!--        <template v-slot:bodyCell="{ column, record, index}">
          <template v-if="column.dataIndex === 'srcPicData'">
            <img :src="record.srcPicData" />
          </template>
        </template>-->

        <span slot="srcPicData" slot-scope="text, record">
          <img @mouseenter="handleView(record)" width="68" height="68" :src="'data:image/png;base64,'+record.srcPicData">
<!--          <img src="~@/assets/avatar2.jpg" class="logo" alt="logo">-->
        </span>

<!--        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />

          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
            <a>删除</a>
          </a-popconfirm>
        </span>-->

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <mAlarm-modal ref="modalForm" @ok="modalFormOk"></mAlarm-modal>
  </a-card>
</template>

<script>
  import MAlarmModal from './modules/AlarmModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import moment from "moment"
  import JDate from "@comp/jeecg/JDate";

  export default {
    name: "MAlarmList",
    mixins:[JeecgListMixin],
    components: {
      JDate,
      MAlarmModal
    },
    data () {
      return {
        ipagination:{
          pageSize: 2,
        },
        description: '报警数据管理页面',
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
            title: '报警类别',
            align:"center",
            dataIndex: 'alarmType',
            customRender(t, r, index) {
              if (r.alarmType == "20") {
                return "烟雾"
              }else if (r.alarmType == "21") {
                return "火灾"
              } else if (r.alarmType == "29") {
                return "人员离岗"
              }
              return ""
            }
           },
		   {
            title: '摄像头ID',
            align:"center",
            dataIndex: 'cameraId'
           },
		   {
            title: '摄像头名称',
            align:"center",
            dataIndex: 'cameraName'
           },
		   {
            title: '报警图片',
            align:"center",
            dataIndex: 'srcPicData',
            key: 'srcPicData',
            scopedSlots: { customRender: 'srcPicData' },
           },
          {
            title: '报警时间',
            align:"center",
            dataIndex: 'createTime',
            customRender: (time) => moment(time).format('YYYYMMDD')
          }
        ],
		url: {
          list: "/alarm/mAlarm/list",
          delete: "/alarm/mAlarm/delete",
          deleteBatch: "/alarm/mAlarm/deleteBatch",
          exportXlsUrl: "alarm/mAlarm/exportXls",
          importExcelUrl: "alarm/mAlarm/importExcel",
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