<template>
  <a-card :bordered="false">
    <Row style="font-size: large; font-weight: bold; ">表1： 《国家学生体质健康标准》单项指标评分表（女生）</Row>
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
            customRender: ( value, row, index ) => {
              let obj = {
                children: '',
                attrs: {}
              }
              if (index === 0) {
                obj = {
                  children: value,
                  attrs: { rowSpan: 3 }
                }
              }

              if (index === 3) {
                obj = {
                  children: value,
                  attrs: { rowSpan: 2 }
                }
              }

              if (index === 5) {
                obj = {
                  children: value,
                  attrs: { rowSpan: 10 }
                }
              }

              if (index === 15) {
                obj = {
                  children: value,
                  attrs: { rowSpan: 5 }
                }
              }
              if ([1, 2, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16,17,18,19,20].indexOf(index) !== -1) {
                obj.attrs.colSpan = 0
              }
              return obj
            },
          },
          {
            title: '年级',
            align:'center',
            customRender: renderContent,
            children: [
              {
                title: '分值/项目',
                align:'center',
                dataIndex: 'fzxm',
                key: 'fzxm',
              }
            ]
          },
          {
            title: '大一 大二',
            children: [
              {
                title: '肺活量',
                align:'center',
                width: 60,
                dataIndex: 'fhl_1',
                key: 'fhl_1',
              }
            ]
          },
          {
            title: '大三 大四',
            children: [
              {
                title: '肺活量',
                align:'center',
                width: 60,
                dataIndex: 'fhl_2',
                key: 'fhl_2',
              }
            ]
          },
          {
            title: '大一 大二',
            children: [
              {
                title: '体前屈',
                align:'center',
                width: 60,
                dataIndex: 'tqq_1',
                key: 'tqq_1',
              }
            ]
          },
          {
            title: '大三 大四',
            children: [
              {
                title: '体前屈',
                align:'center',
                width: 60,
                dataIndex: 'tqq_2',
                key: 'tqq_2',
              }
            ]
          },
          {
            title: '大一 大二',
            children: [
              {
                title: '立定跳远',
                width: 60,
                align:'center',
                dataIndex: 'ldty_1',
                key: 'ldty_1',
              }
            ]
          },
          {
            title: '大三 大四',
            children: [
              {
                title: '立定跳远',
                width: 60,
                align:'center',
                dataIndex: 'ldty_2',
                key: 'ldty_2',
              }
            ]
          },
          {
            title: '大一 大二',
            children: [
              {
                title: '仰卧起坐',
                width: 60,
                align:'center',
                dataIndex: 'ywqz_1',
                key: 'ywqz_1',
              }
            ]
          },
          {
            title: '大三 大四',
            children: [
              {
                title: '仰卧起坐',
                width: 60,
                align:'center',
                dataIndex: 'ywqz_2',
                key: 'ywqz_2',
              }
            ]
          },
          {
            title: '大一 大二',
            children: [
              {
                title: '800米',
                width: 60,
                align:'center',
                dataIndex: 'ehm_1',
                key: 'ehm_1',
              }
            ]
          },
          {
            title: '大三 大四',
            children: [
              {
                title: '800米',
                width: 60,
                align:'center',
                dataIndex: 'ehm_2',
                key: 'ehm_2',
              }
            ]
          },
          {
            title: '大一 大二',
            children: [
              {
                title: '50米',
                width: 60,
                align:'center',
                dataIndex: 'ftm_1',
                key: 'ftm_1',
              }
            ]
          },
          {
            title: '大三 大四',
            children: [
              {
                title: '50米',
                width: 60,
                align:'center',
                dataIndex: 'ftm_2',
                key: 'ftm_2',
              }
            ]
          },
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
          dj: '优秀',
          fzxm:'100',
          fhl_1: 3400,
          fhl_2: 3450,
          tqq_1: 25.8,
          tqq_2: 26.3,
          ldty_1: 207,
          ldty_2: 208,
          ywqz_1: 56,
          ywqz_2: 57,
          ehm_1: '3分18秒',
          ehm_2: '3分16秒',
          ftm_1: 7.5,
          ftm_2: 7.4
        },
          {
            dj: '优秀',
            fzxm:'95',
            fhl_1: 3350,
            fhl_2: 3400,
            tqq_1: 24.0,
            tqq_2: 24.4,
            ldty_1: 201,
            ldty_2: 202,
            ywqz_1: 54,
            ywqz_2: 55,
            ehm_1: '3分24秒',
            ehm_2: '3分22秒',
            ftm_1: 7.6,
            ftm_2: 7.5
          },
          {
            dj: '优秀',
            fzxm:'90',
            fhl_1: 3300,
            fhl_2: 3350,
            tqq_1: 22.2,
            tqq_2: 22.4,
            ldty_1: 195,
            ldty_2: 196,
            ywqz_1: 52,
            ywqz_2: 53,
            ehm_1: '3分30秒',
            ehm_2: '3分28秒',
            ftm_1: 7.7,
            ftm_2: 7.6
          },
          {
            dj: '良好',
            fzxm:'85',
            fhl_1: 3150,
            fhl_2: 3200,
            tqq_1: 20.6,
            tqq_2: 21.0,
            ldty_1: 188,
            ldty_2: 189,
            ywqz_1: 49,
            ywqz_2: 50,
            ehm_1: '3分37秒',
            ehm_2: '3分35秒',
            ftm_1: 8.0,
            ftm_2: 7.9
          },
          {
            dj: '良好',
            fzxm:'80',
            fhl_1: 3000,
            fhl_2: 3050,
            tqq_1: 19.0,
            tqq_2: 19.5,
            ldty_1: 181,
            ldty_2: 182,
            ywqz_1: 46,
            ywqz_2: 47,
            ehm_1: '3分44秒',
            ehm_2: '3分42秒',
            ftm_1: 8.3,
            ftm_2: 8.2
          },
          {
            dj: '及格',
            fzxm:'78',
            fhl_1: 2900,
            fhl_2: 2950,
            tqq_1: 17.7,
            tqq_2: 18.2,
            ldty_1: 178,
            ldty_2: 179,
            ywqz_1: 44,
            ywqz_2:45,
            ehm_1: '3分49秒',
            ehm_2: '3分47秒',
            ftm_1: 8.5,
            ftm_2: 8.4
          },
          {
            dj: '及格',
            fzxm:'76',
            fhl_1: 2800,
            fhl_2: 2850,
            tqq_1: 16.4,
            tqq_2: 16.9,
            ldty_1: 175,
            ldty_2: 176,
            ywqz_1: 42,
            ywqz_2: 43,
            ehm_1: '3分54秒',
            ehm_2: '3分52秒',
            ftm_1: 8.7,
            ftm_2: 8.6
          },
          {
            dj: '及格',
            fzxm:'74',
            fhl_1: 2700,
            fhl_2: 2750,
            tqq_1: 15.1,
            tqq_2: 15.6,
            ldty_1:172,
            ldty_2: 173,
            ywqz_1: 40,
            ywqz_2: 41,
            ehm_1: '3分59秒',
            ehm_2: '3分57秒',
            ftm_1: 8.9,
            ftm_2: 8.8
          },
          {
            dj: '及格',
            fzxm:'72',
            fhl_1: 2600,
            fhl_2: 2650,
            tqq_1: 13.8,
            tqq_2: 14.3,
            ldty_1: 169,
            ldty_2: 170,
            ywqz_1: 38,
            ywqz_2: 39,
            ehm_1: '4分04秒',
            ehm_2: '4分02秒',
            ftm_1: 9.1,
            ftm_2: 9.0
          },
          {
            dj: '及格',
            fzxm:'70',
            fhl_1: 2500,
            fhl_2: 2550,
            tqq_1: 12.5,
            tqq_2: 13.0,
            ldty_1: 166,
            ldty_2: 167,
            ywqz_1: 36,
            ywqz_2: 37,
            ehm_1: '4分09秒',
            ehm_2: '4分07秒',
            ftm_1: 9.3,
            ftm_2: 9.2
          },
          {
            dj: '及格',
            fzxm:'68',
            fhl_1: 2400,
            fhl_2: 2450,
            tqq_1: 11.2,
            tqq_2: 11.7,
            ldty_1: 163,
            ldty_2: 164,
            ywqz_1: 34,
            ywqz_2: 35,
            ehm_1: '4分14秒',
            ehm_2: '4分12秒',
            ftm_1: 9.5,
            ftm_2: 9.4
          },
          {
            dj: '及格',
            fzxm:'66',
            fhl_1: 2300,
            fhl_2: 2350,
            tqq_1: 9.9,
            tqq_2: 10.4,
            ldty_1: 160,
            ldty_2: 161,
            ywqz_1: 32,
            ywqz_2: 33,
            ehm_1: '4分19秒',
            ehm_2: '4分17秒',
            ftm_1: 9.7,
            ftm_2: 9.6
          },
          {
            dj: '及格',
            fzxm:'64',
            fhl_1: 2200,
            fhl_2: 2250,
            tqq_1: 8.6,
            tqq_2: 9.1,
            ldty_1: 157,
            ldty_2: 158,
            ywqz_1: 30,
            ywqz_2: 31,
            ehm_1: '4分24秒',
            ehm_2: '4分22秒',
            ftm_1: 9.9,
            ftm_2: 9.8
          },
          {
            dj: '及格',
            fzxm:'62',
            fhl_1: 2100,
            fhl_2: 2150,
            tqq_1: 7.3,
            tqq_2: 7.8,
            ldty_1: 154,
            ldty_2: 155,
            ywqz_1: 28,
            ywqz_2: 29,
            ehm_1: '4分29秒',
            ehm_2: '4分27秒',
            ftm_1: 10.1,
            ftm_2: 10.0
          },
          {
            dj: '及格',
            fzxm:'60',
            fhl_1: 2000,
            fhl_2: 2050,
            tqq_1: 6.0,
            tqq_2: 6.5,
            ldty_1: 151,
            ldty_2: 152,
            ywqz_1: 26,
            ywqz_2: 27,
            ehm_1: '4分34秒',
            ehm_2: '4分32秒',
            ftm_1: 10.3,
            ftm_2: 10.2
          },
          {
            dj: '不及格',
            fzxm:'50',
            fhl_1: 1960,
            fhl_2: 2010,
            tqq_1: 5.2,
            tqq_2: 5.7,
            ldty_1: 146,
            ldty_2: 147,
            ywqz_1: 24,
            ywqz_2: 25,
            ehm_1: '4分44秒',
            ehm_2: '4分42秒',
            ftm_1: 10.5,
            ftm_2: 10.4
          },
          {
            dj: '不及格',
            fzxm:'40',
            fhl_1: 1920,
            fhl_2: 1970,
            tqq_1: 4.4,
            tqq_2: 4.9,
            ldty_1: 141,
            ldty_2: 142,
            ywqz_1: 22,
            ywqz_2: 23,
            ehm_1: '4分54秒',
            ehm_2: '4分52秒',
            ftm_1: 10.7,
            ftm_2: 10.6
          },
          {
            dj: '不及格',
            fzxm:'30',
            fhl_1: 1880,
            fhl_2: 1930,
            tqq_1: 3.6,
            tqq_2: 4.1,
            ldty_1: 136,
            ldty_2: 137,
            ywqz_1: 20,
            ywqz_2: 21,
            ehm_1: '5分04秒',
            ehm_2: '5分02秒',
            ftm_1: 10.9,
            ftm_2: 10.8
          },
          {
            dj: '不及格',
            fzxm:'20',
            fhl_1: 1840,
            fhl_2: 1890,
            tqq_1: 2.8,
            tqq_2: 3.3,
            ldty_1: 131,
            ldty_2: 132,
            ywqz_1: 18,
            ywqz_2: 19,
            ehm_1: '5分14秒',
            ehm_2: '5分12秒',
            ftm_1: 11.1,
            ftm_2: 11.0
          },
          {
            dj: '不及格',
            fzxm:'10',
            fhl_1: 1800,
            fhl_2: 1850,
            tqq_1: 2.0,
            tqq_2: 2.5,
            ldty_1: 126,
            ldty_2: 127,
            ywqz_1: 16,
            ywqz_2: 17,
            ehm_1: '5分24秒',
            ehm_2: '5分22秒',
            ftm_1: 11.3,
            ftm_2: 11.2
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
