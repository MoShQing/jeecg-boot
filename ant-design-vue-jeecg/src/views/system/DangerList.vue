<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <!--    <div class="table-page-search-wrapper">
          <a-form layout="inline" @keyup.enter.native="searchQuery">
            <a-row :gutter="24">

              <a-col :md="6" :sm="12">
                <a-form-item label="账号">
                  &lt;!&ndash;<a-input placeholder="请输入账号查询" v-model="queryParam.username"></a-input>&ndash;&gt;
                  <j-input placeholder="输入账号模糊查询" v-model="queryParam.username"></j-input>
                </a-form-item>
              </a-col>

              <a-col :md="6" :sm="8">
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
              </template>

              <a-col :md="6" :sm="8">
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
    <div class="table-operator" style="border-top: 5px">
      <a-button @click="handleAdd" type="primary" icon="plus" v-has="'danger:add'">新增工艺</a-button>
    </div>

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
        <a-avatar shape="square" :src="getAvatarView(record.avatar)" icon="danger"/>
      </div>
    </template>

    <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical"/>

          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
          </a-popconfirm>

        </span>
    </a-table>
    <!-- table区域-end -->

    <danger-modal ref="modalForm" @ok="modalFormOk"></danger-modal>

  </a-card>
</template>

<script>
import DangerModal from './modules/DangerModal'
import {JeecgListMixin} from '@/mixins/JeecgListMixin'
import JInput from '@/components/jeecg/JInput'

export default {
  name: "DangerList",
  mixins: [JeecgListMixin],
  components: {
    DangerModal,
    JInput
  },
  data() {
    return {
      description: '这是重点危险品工艺页面',
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
          title: '重点危险品工艺名称',
          align: "center",
          dataIndex: 'dangerName',
          width: 120
        },
        {
          title: '备注',
          align: "center",
          width: 100,
          dataIndex: 'remark',
        }
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
        list: "/management/danger/list",
        delete: "/management/danger/delete",
        deleteBatch: "/management/danger/deleteBatch",
        exportXlsUrl: "/management/danger/exportXls",
        importExcelUrl: "management/danger/importExcel",
      },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
  methods: {
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