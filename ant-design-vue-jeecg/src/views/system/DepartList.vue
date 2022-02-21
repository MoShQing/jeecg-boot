<template>
  <a-card :bordered="false">
    <Row style="font-size: large; font-weight: bold; ">表2： 《国家学生体质健康标准》单项指标评分表（男生）</Row>
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
                title: '引体向上',
                width: 60,
                align:'center',
                dataIndex: 'ytxs_1',
                key: 'ytxs_1',
              }
            ]
          },
          {
            title: '大三 大四',
            children: [
              {
                title: '引体向上',
                width: 60,
                align:'center',
                dataIndex: 'ytxs_2',
                key: 'ytxs_2',
              }
            ]
          },
          {
            title: '大一 大二',
            children: [
              {
                title: '1000米',
                width: 60,
                align:'center',
                dataIndex: 'otm_1',
                key: 'otm_1',
              }
            ]
          },
          {
            title: '大三 大四',
            children: [
              {
                title: '1000米',
                width: 60,
                align:'center',
                dataIndex: 'otm_2',
                key: 'otm_2',
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
          fhl_1: 5040,
          fhl_2: 5140,
          tqq_1: 24.9,
          tqq_2: 25.1,
          ldty_1: 273,
          ldty_2: 275,
          ytxs_1: 19,
          ytxs_2: 20,
          otm_1: '3分17秒',
          otm_2: '3分15秒',
          ftm_1: 6.7,
          ftm_2: 6.6
        },
          {
            dj: '优秀',
            fzxm:'95',
            fhl_1: 4920,
            fhl_2: 5020,
            tqq_1: 23.1,
            tqq_2: 23.3,
            ldty_1: 268,
            ldty_2: 270,
            ytxs_1: 18,
            ytxs_2: 19,
            otm_1: '3分22秒',
            otm_2: '3分20秒',
            ftm_1: 6.8,
            ftm_2: 6.7
          },
          {
            dj: '优秀',
            fzxm:'90',
            fhl_1: 4800,
            fhl_2: 4900,
            tqq_1: 21.3,
            tqq_2: 21.5,
            ldty_1: 263,
            ldty_2: 265,
            ytxs_1: 17,
            ytxs_2: 18,
            otm_1: '3分27秒',
            otm_2: '3分25秒',
            ftm_1: 6.9,
            ftm_2: 6.8
          },
          {
            dj: '良好',
            fzxm:'85',
            fhl_1: 4550,
            fhl_2: 4650,
            tqq_1: 19.5,
            tqq_2: 19.9,
            ldty_1: 256,
            ldty_2: 258,
            ytxs_1: 16,
            ytxs_2: 17,
            otm_1: '3分34秒',
            otm_2: '3分32秒',
            ftm_1: 7.0,
            ftm_2: 6.9
          },
          {
            dj: '良好',
            fzxm:'80',
            fhl_1: 4300,
            fhl_2: 4400,
            tqq_1: 17.7,
            tqq_2: 18.2,
            ldty_1: 248,
            ldty_2: 250,
            ytxs_1: 15,
            ytxs_2: 16,
            otm_1: '3分42秒',
            otm_2: '3分40秒',
            ftm_1: 7.1,
            ftm_2: 7.0
          },
          {
            dj: '及格',
            fzxm:'78',
            fhl_1: 4180,
            fhl_2: 4280,
            tqq_1: 16.3,
            tqq_2: 16.8,
            ldty_1: 244,
            ldty_2: 246,
            ytxs_1: '',
            ytxs_2:'',
            otm_1: '3分47秒',
            otm_2: '3分45秒',
            ftm_1: 7.3,
            ftm_2: 7.2
          },
          {
            dj: '及格',
            fzxm:'76',
            fhl_1: 4060,
            fhl_2: 4160,
            tqq_1: 14.9,
            tqq_2: 15.4,
            ldty_1: 240,
            ldty_2: 242,
            ytxs_1: 14,
            ytxs_2: 15,
            otm_1: '3分52秒',
            otm_2: '3分50秒',
            ftm_1: 7.5,
            ftm_2: 7.4
          },
          {
            dj: '及格',
            fzxm:'74',
            fhl_1: 3940,
            fhl_2: 4040,
            tqq_1: 13.5,
            tqq_2: 14.0,
            ldty_1:236,
            ldty_2: 238,
            ytxs_1: '',
            ytxs_2: '',
            otm_1: '3分57秒',
            otm_2: '3分55秒',
            ftm_1: 7.7,
            ftm_2: 7.6
          },
          {
            dj: '及格',
            fzxm:'72',
            fhl_1: 3820,
            fhl_2: 3920,
            tqq_1: 12.1,
            tqq_2: 12.6,
            ldty_1: 232,
            ldty_2: 234,
            ytxs_1: 13,
            ytxs_2: 14,
            otm_1: '4分02秒',
            otm_2: '4分00秒',
            ftm_1: 7.9,
            ftm_2: 7.8
          },
          {
            dj: '及格',
            fzxm:'70',
            fhl_1: 3700,
            fhl_2: 3800,
            tqq_1: 10.7,
            tqq_2: 11.2,
            ldty_1: 228,
            ldty_2: 230,
            ytxs_1: '',
            ytxs_2: '',
            otm_1: '4分07秒',
            otm_2: '4分05秒',
            ftm_1: 8.1,
            ftm_2: 8.0
          },
          {
            dj: '及格',
            fzxm:'68',
            fhl_1: 3580,
            fhl_2: 3680,
            tqq_1: 9.3,
            tqq_2: 9.8,
            ldty_1: 224,
            ldty_2: 226,
            ytxs_1: 12,
            ytxs_2: 13,
            otm_1: '4分12秒',
            otm_2: '4分10秒',
            ftm_1: 8.3,
            ftm_2: 8.2
          },
          {
            dj: '及格',
            fzxm:'66',
            fhl_1: 3460,
            fhl_2: 3560,
            tqq_1: 7.9,
            tqq_2: 8.4,
            ldty_1: 220,
            ldty_2: 222,
            ytxs_1: '',
            ytxs_2: '',
            otm_1: '4分17秒',
            otm_2: '4分15秒',
            ftm_1: 8.5,
            ftm_2: 8.4
          },
          {
            dj: '及格',
            fzxm:'64',
            fhl_1: 3340,
            fhl_2: 3440,
            tqq_1: 6.5,
            tqq_2: 7.0,
            ldty_1: 216,
            ldty_2: 218,
            ytxs_1: 11,
            ytxs_2: 12,
            otm_1: '4分22秒',
            otm_2: '4分20秒',
            ftm_1: 8.7,
            ftm_2: 8.6
          },
          {
            dj: '及格',
            fzxm:'62',
            fhl_1: 3220,
            fhl_2: 3320,
            tqq_1: 5.1,
            tqq_2: 5.6,
            ldty_1: 212,
            ldty_2: 214,
            ytxs_1: '',
            ytxs_2: '',
            otm_1: '4分27秒',
            otm_2: '4分25秒',
            ftm_1: 8.9,
            ftm_2: 8.8
          },
          {
            dj: '及格',
            fzxm:'60',
            fhl_1: 3100,
            fhl_2: 3200,
            tqq_1: 3.7,
            tqq_2: 4.2,
            ldty_1: 208,
            ldty_2: 210,
            ytxs_1: 10,
            ytxs_2: 11,
            otm_1: '4分32秒',
            otm_2: '4分30秒',
            ftm_1: 9.1,
            ftm_2: 9.0
          },
          {
            dj: '不及格',
            fzxm:'50',
            fhl_1: 2940,
            fhl_2: 3030,
            tqq_1: 2.7,
            tqq_2: 3.2,
            ldty_1: 203,
            ldty_2: 205,
            ytxs_1: 9,
            ytxs_2: 10,
            otm_1: '4分52秒',
            otm_2: '4分50秒',
            ftm_1: 9.3,
            ftm_2: 9.2
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
            ytxs_1: 22,
            ytxs_2: 23,
            otm_1: '4分54秒',
            otm_2: '4分52秒',
            ftm_1: 10.7,
            ftm_2: 10.6
          },
          {
            dj: '不及格',
            fzxm:'30',
            fhl_1: 2620,
            fhl_2: 2690,
            tqq_1: 0.7,
            tqq_2: 1.2,
            ldty_1: 193,
            ldty_2: 195,
            ytxs_1: 7,
            ytxs_2: 8,
            otm_1: '5分32秒',
            otm_2: '5分30秒',
            ftm_1: 9.7,
            ftm_2: 9.6
          },
          {
            dj: '不及格',
            fzxm:'20',
            fhl_1: 2460,
            fhl_2: 2520,
            tqq_1: -0.3,
            tqq_2: 0.2,
            ldty_1: 188,
            ldty_2: 190,
            ytxs_1: 6,
            ytxs_2: 7,
            otm_1: '5分52秒',
            otm_2: '5分50秒',
            ftm_1: 9.9,
            ftm_2: 9.8
          },
          {
            dj: '不及格',
            fzxm:'10',
            fhl_1: 2300,
            fhl_2: 2350,
            tqq_1: -1.3,
            tqq_2: -0.8,
            ldty_1: 183,
            ldty_2: 185,
            ytxs_1: 5,
            ytxs_2: 6,
            otm_1: '6分12秒',
            otm_2: '6分10秒',
            ftm_1: 10.1,
            ftm_2: 10.0
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
