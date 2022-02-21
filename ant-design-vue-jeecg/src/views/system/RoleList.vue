<template>
  <a-card :bordered="false">
    <Row style="font-size: large; font-weight: bold; ">表3： 《国家学生体质健康标准》体重指数（BMI）单项评分表（单位：千克/米）</Row>
    <Br/>
    <a-table
      :columns="columns2"  :data-source="statisticData" bordered size="small"
      :pagination=false
      :rowKey="(record,index)=>{return index}"></a-table>
  </a-card>
</template>

<script>
  import UserModal from './modules/UserModal'
  import PasswordModal from './modules/PasswordModal'
  import {putAction} from '@/api/manage';
  import {frozenBatch} from '@/api/api'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'
  import SysUserAgentModal from "./modules/SysUserAgentModal";
  import JInput from '@/components/jeecg/JInput'

  const temp = {}; // 当前重复的值,支持多列
  const mergeCellKey = (text, array, columns) => {
    let i = 0;
    if (text !== temp[columns]) {
      temp[columns] = text;
      array.forEach((item) => {
        if (item.key === temp[columns]) {
          i += 1;
        }
      });
    }
    return i;
  };
  const renderContent = (value, row, index) => {
    const obj = {
      children: value,
      attrs: {},
    };
    return obj;
  };

  export default {
    name: "UserList",
    mixins: [JeecgListMixin],
    components: {
      SysUserAgentModal,
      UserModal,
      PasswordModal,
      JInput
    },
    data() {
      let that = this;
      return {
        description: '单项评分表',
        columns2: [
          {
            title: '等级',
            dataIndex: 'dj',
            align:'center',
          },
          {
            title: '单项得分',
            customRender: ( value, row, index ) => {
              let obj = {
                children: value,
                attrs: {}
              }
              if (index === 1) {
                obj = {
                  children: value,
                  attrs: { rowSpan: 2 }
                }
              }

              if (index === 2) {
                obj.attrs.colSpan = 0
              }
              return obj
            },
            align:'center',
            dataIndex: 'dxdf',
            key: 'dxdf',
          },
          {
            title: '高三（女）',
            align:'center',
            width: 100,
            dataIndex: 'gsnv',
            key: 'gsnv',
          },
          {
            title: '高三（男）',
            align:'center',
            width: 100,
            dataIndex: 'gsnan',
            key: 'gsnan',
          },
          {
            title: '大学（女）',
            align:'center',
            width: 100,
            dataIndex: 'dxnv',
            key: 'dxnv',
          },
          {
            title: '大学（男）',
            align:'center',
            width: 100,
            dataIndex: 'dxnan',
            key: 'dxnan',
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
        statisticData: [{
          dj: '正常',
          dxdf:'100',
          gsnv: '17.1~23.3',
          gsnan: '17.3~23.8',
          dxnv: '17.2~23.9',
          dxnan: '17.9~23.9',
        },
          {
            dj: '低体重',
            dxdf:'80',
            gsnv: '<=17.0',
            gsnan: '<=17.2',
            dxnv: '<=17.1',
            dxnan: '<=17.8',
          },{
            dj: '超重',
            dxdf:'80',
            gsnv: '23.4~25.7',
            gsnan: '23.9~27.3',
            dxnv: '24.0~27.9',
            dxnan: '24.0~27.9',
          },{
            dj: '肥胖',
            dxdf:'60',
            gsnv: '>=25.8',
            gsnan: '>=27.4',
            dxnv: '>=28.0',
            dxnan: '>=28.0',
          },],
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
