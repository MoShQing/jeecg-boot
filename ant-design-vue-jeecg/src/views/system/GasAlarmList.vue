<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :md="6" :sm="12">
            <a-form-item label="查询条件">
              <a-input placeholder="请输入查询条件" v-model="queryParam.username"></a-input>
            </a-form-item>
          </a-col>

          <!--<a-col :md="6" :sm="8">
            <a-form-item label="性别">
              <a-select v-model="queryParam.sex" placeholder="请选择性别查询">
                <a-select-option value="">请选择性别查询</a-select-option>
                <a-select-option value="1">男性</a-select-option>
                <a-select-option value="2">女性</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>


          <template v-if="toggleSearchStatus">
            <a-col :md="6" :sm="8">
              <a-form-item label="邮箱">
                <a-input placeholder="请输入邮箱查询" v-model="queryParam.email"></a-input>
              </a-form-item>
            </a-col>

            <a-col :md="6" :sm="8">
              <a-form-item label="手机号码">
                <a-input placeholder="请输入手机号码查询" v-model="queryParam.phone"></a-input>
              </a-form-item>
            </a-col>

            <a-col :md="6" :sm="8">
              <a-form-item label="状态">
                <a-select v-model="queryParam.status" placeholder="请选择用户状态查询">
                  <a-select-option value="">请选择用户状态</a-select-option>
                  <a-select-option value="1">正常</a-select-option>
                  <a-select-option value="2">解冻</a-select-option>
                </a-select>

              </a-form-item>
            </a-col>
          </template>-->

          <a-col :md="6" :sm="8">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
<!--              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>-->
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <!--<div class="table-operator" style="border-top: 5px">
      <a-button @click="handleAdd" type="primary" icon="plus" v-has="'user:add'">新增工艺</a-button>
            <a-button type="primary" icon="download" @click="handleExportXls('用户信息')">导出</a-button>
            <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
              <a-button type="primary" icon="import">导入</a-button>
            </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay" @click="handleMenuClick">
          <a-menu-item key="1">
            <a-icon type="delete" @click="batchDel"/>
            删除
          </a-menu-item>
          <a-menu-item key="2">
            <a-icon type="lock" @click="batchFrozen('2')"/>
            冻结
          </a-menu-item>
          <a-menu-item key="3">
            <a-icon type="unlock" @click="batchFrozen('1')"/>
            解冻
          </a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px">
          批量操作
          <a-icon type="down"/>
        </a-button>
      </a-dropdown>
    </div>-->

    <!-- table区域-begin -->
    <!--    <div>
          <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
            <i class="anticon anticon-info-circle ant-alert-icon"></i>已选择&nbsp;<a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项&nbsp;&nbsp;
            <a style="margin-left: 24px" @click="onClearSelected">清空</a>
          </div>-->

    <a-table
      ref="table"
      bordered
      size="middle"
      rowKey="id"
      :columns="columns"
      :dataSource="dataSource"
      :pagination="ipagination"
      :loading="loading"
    @change="handleTableChange">

    <template slot="avatarslot" slot-scope="text, record, index">
      <div class="anty-img-wrap">
        <a-avatar shape="square" :src="getAvatarView(record.avatar)" icon="user"/>
      </div>
    </template>

    <span slot="action" slot-scope="text, record">
          <a @click="send(record)">发送</a>

<!--          <a-divider type="vertical"/>

          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
          </a-popconfirm>-->
        </span>


    </a-table>
    <!-- table区域-end -->

    <user-modal ref="modalForm" @ok="modalFormOk"></user-modal>

    <password-modal ref="passwordmodal" @ok="passwordModalOk"></password-modal>

    <sys-user-agent-modal ref="sysUserAgentModal"></sys-user-agent-modal>

    <a-modal
      title="详情"
      width="40%"
      centered
      :keyboard="false"
      :visible="visible"
      @cancel="closeModal"
      @ok="sendMess"
      cancelText="取消"
      okText="发送">
        <a-form :form="form">

          <a-form-item label="ip" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input v-model="form.ip"/>
          </a-form-item>

          <a-form-item label="port" :labelCol="labelCol" :wrapperCol="wrapperCol" >
            <a-input v-model="form.port"/>
          </a-form-item>

          <a-form-item label="mess" :labelCol="labelCol" :wrapperCol="wrapperCol" >
            <a-input v-model="form.mess"/>
          </a-form-item>

        </a-form>
    </a-modal>
  </a-card>
</template>

<script>
import UserModal from './modules/UserModal'
import PasswordModal from './modules/PasswordModal'
import {sendMess} from '@/api/api';
import {frozenBatch} from '@/api/api'
import {JeecgListMixin} from '@/mixins/JeecgListMixin'
import SysUserAgentModal from "./modules/SysUserAgentModal";
import JInput from '@/components/jeecg/JInput'

export default {
  name: "GasAlarmList",
  mixins: [JeecgListMixin],
  components: {
    SysUserAgentModal,
    UserModal,
    PasswordModal,
    JInput
  },
  data() {
    return {
      visible: false,
      labelCol: {
        xs: { span: 24 },
        sm: { span: 5 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
      form:{
        ip:'58.240.210.114',
        port:'10123',
        mess:'',
      },

      description: '这是用户管理页面',
      queryParam: {},
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
          title: '设备名称',
          align: "center",
          dataIndex: 'username',
          width: 120
        },
        {
          title: '创建时间',
          align: "center",
          width: 150,
          dataIndex: 'createTime',
          sorter: true
        },
        ,{
          title: '操作',
          dataIndex: 'action',
          scopedSlots: {customRender: 'action'},
          align: "center",
          width: 170
        }

      ],
      url: {
        imgerver: window._CONFIG['domianURL'] + "/sys/common/view",
        syncUser: "/process/extActProcess/doSyncUser",
        list: "/sys/user/list",
        delete: "/sys/user/delete",
        deleteBatch: "/sys/user/deleteBatch",
        exportXlsUrl: "/sys/user/exportXls",
        importExcelUrl: "sys/user/importExcel",
      },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
  methods: {
    closeModal() {
      this.visible = false
    },
    send: function (record) {
      this.visible = true
    },
    sendMess: function() {
      let that = this
      console.log(that.form)
      sendMess(that.form).then((res) => {
        if (res.success) {
          that.$message.success(res.message);
        } else {
          that.$message.warning(res.message);
        }
      });
    },
    getAvatarView: function (avatar) {
      return this.url.imgerver + "/" + avatar;
    },

    batchFrozen: function (status) {
      if (this.selectedRowKeys.length <= 0) {
        this.$message.warning('请选择一条记录！');
        return false;
      } else {
        let ids = "";
        let that = this;
        let isAdmin = false;
        that.selectionRows.forEach(function (row) {
          if (row.username == 'admin') {
            isAdmin = true;
          }
        });
        if (isAdmin) {
          that.$message.warning('管理员账号不允许此操作,请重新选择！');
          return;
        }
        that.selectedRowKeys.forEach(function (val) {
          ids += val + ",";
        });
        that.$confirm({
          title: "确认操作",
          content: "是否" + (status == 1 ? "解冻" : "冻结") + "选中账号?",
          onOk: function () {
            frozenBatch({ids: ids, status: status}).then((res) => {
              if (res.success) {
                that.$message.success(res.message);
                that.loadData();
                that.onClearSelected();
              } else {
                that.$message.warning(res.message);
              }
            });
          }
        });
      }
    },
    handleMenuClick(e) {
      if (e.key == 1) {
        this.batchDel();
      } else if (e.key == 2) {
        this.batchFrozen(2);
      } else if (e.key == 3) {
        this.batchFrozen(1);
      }
    },
    handleFrozen: function (id, status, username) {
      let that = this;
      //TODO 后台校验管理员角色
      if ('admin' == username) {
        that.$message.warning('管理员账号不允许此操作！');
        return;
      }
      frozenBatch({ids: id, status: status}).then((res) => {
        if (res.success) {
          that.$message.success(res.message);
          that.loadData();
        } else {
          that.$message.warning(res.message);
        }
      });
    },
    handleChangePassword(username) {
      this.$refs.passwordmodal.show(username);
    },
    handleAgentSettings(username){
      this.$refs.sysUserAgentModal.agentSettings(username);
      this.$refs.sysUserAgentModal.title = "用户代理人设置";
    },
    passwordModalOk() {
      //TODO 密码修改完成 不需要刷新页面，可以把datasource中的数据更新一下
    }
  }

}
</script>
<style scoped>
@import '~@assets/less/common.less'
</style>