let demoList = [
  {
    "success": true, "message": "查询成功", "code": 200, "result": {
      "allAuth": [{
        "action": "online:goGenerateCode",
        "describe": "代码生成按钮",
        "type": "1",
        "status": "1"
      }, {"action": "user:form:phone", "describe": "手机号禁用", "type": "2", "status": "1"}, {
        "action": "user:add",
        "describe": "添加用户按钮",
        "type": "1",
        "status": "1"
      }],
      "auth": [],
      "menu": [{
        "redirect": null,
        "path": "/dashboard/analysis",
        "component": "dashboard/Analysis",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "home", "title": "首页"},
        "name": "dashboard-analysis",
        "id": "9502685863ab87f0ad1134142788a385"
      }, {
        "redirect": null,
        "path": "/isystem",
        "component": "layouts/RouteView",
        "route": "1",
        "children": [{
          "path": "/isystem/depart",
          "component": "system/DepartList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "单项指标评分表（男生）"},
          "name": "isystem-depart",
          "id": "45c966826eeff4c99b8f8ebfe74511fc"
        }, {
          "path": "/isystem/user",
          "component": "system/UserList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "单项指标评分表（女生）"},
          "name": "isystem-user",
          "id": "3f915b2769fc80648e92d04e84ca059d"
        }, {
          "path": "/isystem/role",
          "component": "system/RoleList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "体重指数（BMI）"},
          "name": "isystem-role",
          "id": "e8af452d8948ea49d37c934f5100ae6a"
        }, {
          "path": "/isystem/dict",
          "component": "system/DictList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "学期管理"},
          "name": "isystem-dict",
          "id": "f1cb187abf927c88b89470d08615f5ac"
        }],
        "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "setting", "title": "系统管理1"},
        "name": "isystem",
        "id": "d7d6e2e4e2934f2c9385a623fd98c6f3"
      }, {
        "redirect": null,
        "path": "/dashboard3",
        "component": "layouts/RouteView",
        "route": "1",
        "children": [{
          "path": "5f22d2592b01c9e964efe70040162b83",
          "component": "layouts/IframePageView",
          "route": "1",
          "meta": {
            "keepAlive": false,
            "internalOrExternal": false,
            "title": "SQL监控",
            "url": "{{ window._CONFIG['domianURL'] }}/druid/"
          },
          "name": "{{ window._CONFIG['domianURL'] }}-druid-",
          "id": "aedbf679b5773c1f25e9f7b10111da73"
        }, {
          "path": "/isystem/log",
          "component": "system/LogList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "日志管理"},
          "name": "isystem-log",
          "id": "58857ff846e61794c69208e9d3a85466"
        }, {
          "path": "10b80437abc6eee8c3e3daabb5e9f59e",
          "component": "layouts/IframePageView",
          "route": "1",
          "meta": {
            "keepAlive": false,
            "internalOrExternal": false,
            "title": "在线文档",
            "url": "{{ window._CONFIG['domianURL'] }}/doc.html"
          },
          "name": "{{ window._CONFIG['domianURL'] }}-doc.html",
          "id": "2dbbafa22cda07fa5d169d741b81fe12"
        }, {
          "path": "/isystem/QuartzJobList",
          "component": "system/QuartzJobList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "定时任务"},
          "name": "isystem-QuartzJobList",
          "id": "b1cb0a3fedf7ed0e4653cb5a229837ee"
        }],
        "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "dashboard", "title": "系统监控"},
        "name": "dashboard3",
        "id": "08e6b9dc3c04489c8e1ff2ce6f105aa4"
      }, {
        "redirect": null,
        "path": "/jeecg",
        "component": "layouts/RouteView",
        "route": "1",
        "children": [{
          "path": "/jeecg/jeecgDemoList",
          "component": "jeecg/JeecgDemoList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "单表模型示例"},
          "name": "DemoList",
          "id": "4148ec82b6acd69f470bea75fe41c357"
        }, {
          "path": "/jeecg/tablist/JeecgOrderDMainList",
          "component": "jeecg/tablist/JeecgOrderDMainList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "一对多Tab示例"},
          "name": "jeecg-tablist-JeecgOrderDMainList",
          "id": "6ad53fd1b220989a8b71ff482d683a5a"
        }, {
          "path": "/jeecg/JeecgOrderMainList",
          "component": "jeecg/JeecgOrderMainList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "一对多示例"},
          "name": "jeecg-JeecgOrderMainList",
          "id": "fb367426764077dcf94640c843733985"
        }, {
          "path": "/jeecg/PrintDemo",
          "component": "jeecg/PrintDemo",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "打印测试"},
          "name": "jeecg-PrintDemo",
          "id": "e6bfd1fcabfd7942fdd05f076d1dad38"
        }, {
          "path": "bfa89e563d9509fbc5c6503dd50faf2e",
          "component": "layouts/IframePageView",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "百度", "url": "http://www.baidu.com"},
          "name": "http@--www.baidu.com",
          "id": "a400e4f4d54f79bf5ce160ae432231af"
        }, {
          "path": "/jeecg/helloworld",
          "component": "jeecg/helloworld",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "helloworld"},
          "name": "jeecg-helloworld",
          "id": "339329ed54cf255e1f9392e84f136901"
        }, {
          "path": "/jeecg/InterfaceTest",
          "component": "jeecg/InterfaceTest",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "数据回执模拟"},
          "name": "jeecg-InterfaceTest",
          "id": "c6cf95444d80435eb37b2f9db3971ae6"
        }],
        "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "qrcode", "title": "常见案例"},
        "name": "jeecg",
        "id": "2a470fc0c3954d9dbb61de6d80846549"
      }, {
        "redirect": null,
        "path": "/exception",
        "component": "layouts/RouteView",
        "route": "1",
        "children": [{
          "path": "/exception/403",
          "component": "exception/403",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "403"},
          "name": "exception-403",
          "id": "65a8f489f25a345836b7f44b1181197a"
        }, {
          "path": "/exception/404",
          "component": "exception/404",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "404"},
          "name": "exception-404",
          "id": "d2bbf9ebca5a8fa2e227af97d2da7548"
        }, {
          "path": "/exception/500",
          "component": "exception/500",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "500"},
          "name": "exception-500",
          "id": "b4dfc7d5dd9e8d5b6dd6d4579b1aa559"
        }],
        "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "warning", "title": "异常页"},
        "name": "exception",
        "id": "c65321e57b7949b7a975313220de0422"
      }, {
        "redirect": "/list/query-list",
        "path": "/list",
        "component": "layouts/PageView",
        "route": "1",
        "children": [{
          "path": "/list/query-list",
          "component": "list/TableList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "查询表格"},
          "name": "list-query-list",
          "id": "418964ba087b90a84897b62474496b93"
        }, {
          "path": "/list/edit-table",
          "component": "list/TableInnerEditList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "内联编辑表格"},
          "name": "list-edit-table",
          "id": "ae4fed059f67086fd52a73d913cf473d"
        }, {
          "path": "/list/user-list",
          "component": "list/UserList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "用户列表"},
          "name": "list-user-list",
          "id": "05b3c82ddb2536a4a5ee1a4c46b5abef"
        }, {
          "path": "/list/role-list",
          "component": "list/RoleList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "角色列表"},
          "name": "list-role-list",
          "id": "4f84f9400e5e92c95f05b554724c2b58"
        }, {
          "path": "/list/permission-list",
          "component": "list/PermissionList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "权限列表"},
          "name": "list-permission-list",
          "id": "73678f9daa45ed17a3674131b03432fb"
        }, {
          "path": "/list/basic-list",
          "component": "list/StandardList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "标准列表"},
          "name": "list-basic-list",
          "id": "f23d9bfff4d9aa6b68569ba2cff38415"
        }, {
          "path": "/list/card",
          "component": "list/CardList",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "卡片列表"},
          "name": "list-card",
          "id": "7ac9eb9ccbde2f7a033cd4944272bf1e"
        }, {
          "path": "/list/search",
          "component": "list/search/SearchLayout",
          "route": "1",
          "children": [{
            "path": "/list/search/application",
            "component": "list/TableList",
            "route": "1",
            "meta": {"keepAlive": false, "internalOrExternal": false, "title": "搜索列表（应用）"},
            "name": "list-search-application",
            "id": "200006f0edf145a2b50eacca07585451"
          }, {
            "path": "/list/search/article",
            "component": "list/TableList",
            "route": "1",
            "meta": {"keepAlive": false, "internalOrExternal": false, "title": "搜索列表（文章）"},
            "name": "list-search-article",
            "id": "078f9558cdeab239aecb2bda1a8ed0d1"
          }, {
            "path": "/list/search/project",
            "component": "list/TableList",
            "route": "1",
            "meta": {"keepAlive": false, "internalOrExternal": false, "title": "搜索列表（项目）"},
            "name": "list-search-project",
            "id": "de13e0f6328c069748de7399fcc1dbbd"
          }],
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "搜索列表"},
          "name": "list-search",
          "id": "fb07ca05a3e13674dbf6d3245956da2e"
        }],
        "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "table", "title": "列表页"},
        "name": "list",
        "id": "540a2936940846cb98114ffb0d145cb8"
      }, {
        "redirect": null,
        "path": "/form",
        "component": "layouts/PageView",
        "route": "1",
        "children": [{
          "path": "/form/base-form",
          "component": "form/BasicForm",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "基础表单"},
          "name": "form-base-form",
          "id": "277bfabef7d76e89b33062b16a9a5020"
        }, {
          "path": "/form/step-form",
          "component": "form/stepForm/StepForm",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "分步表单"},
          "name": "form-step-form",
          "id": "6531cf3421b1265aeeeabaab5e176e6d"
        }, {
          "path": "/form/advanced-form",
          "component": "form/advancedForm/AdvancedForm",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "高级表单"},
          "name": "form-advanced-form",
          "id": "e5973686ed495c379d829ea8b2881fc6"
        }],
        "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "form", "title": "表单页"},
        "name": "form",
        "id": "e3c13679c73a4f829bcff2aba8fd68b1"
      }, {
        "redirect": null,
        "path": "/account",
        "component": "layouts/RouteView",
        "route": "1",
        "children": [{
          "path": "/account/center",
          "component": "account/center/Index",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "个人中心"},
          "name": "account-center",
          "id": "d86f58e7ab516d3bc6bfb1fe10585f97"
        }, {
          "path": "/account/settings/base",
          "component": "account/settings/Index",
          "route": "1",
          "children": [{
            "path": "/account/settings/custom",
            "component": "account/settings/Custom",
            "route": "1",
            "meta": {"keepAlive": false, "internalOrExternal": false, "title": "个性化设置"},
            "name": "account-settings-custom",
            "id": "882a73768cfd7f78f3a37584f7299656"
          }, {
            "path": "/account/settings/binding",
            "component": "account/settings/Binding",
            "route": "1",
            "meta": {"keepAlive": false, "internalOrExternal": false, "title": "账户绑定"},
            "name": "account-settings-binding",
            "id": "4f66409ef3bbd69c1d80469d6e2a885e"
          }, {
            "path": "/account/settings/base",
            "component": "account/settings/BaseSetting",
            "route": "1",
            "hidden": true,
            "meta": {"keepAlive": false, "internalOrExternal": false, "title": "基本设置"},
            "name": "account-settings-base",
            "id": "1367a93f2c410b169faa7abcbad2f77c"
          }, {
            "path": "/account/settings/notification",
            "component": "account/settings/Notification",
            "route": "1",
            "meta": {"keepAlive": false, "internalOrExternal": false, "title": "新消息通知"},
            "name": "account-settings-notification",
            "id": "fedfbf4420536cacc0218557d263dfea"
          }, {
            "path": "/account/settings/security",
            "component": "account/settings/Security",
            "route": "1",
            "meta": {"keepAlive": false, "internalOrExternal": false, "title": "安全设置"},
            "name": "account-settings-security",
            "id": "ec8d607d0156e198b11853760319c646"
          }],
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "个人设置"},
          "name": "account-settings-base",
          "id": "6e73eb3c26099c191bf03852ee1310a1",
          "alwaysShow": true
        }, {
          "path": "/dashboard/workplace",
          "component": "dashboard/Workplace",
          "route": "1",
          "meta": {"keepAlive": false, "internalOrExternal": false, "title": "工作台"},
          "name": "dashboard-workplace",
          "id": "8fb8172747a78756c11916216b8b8066"
        }],
        "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "user", "title": "个人页"},
        "name": "account",
        "id": "717f6bee46f44a3897eca9abd6e2ec44"
      }]
    }, "timestamp": 1645415388121
  }
]

const map = new Map();
map.set("0",{
  "success": true, "message": "查询成功", "code": 200, "result": {
    "allAuth": [{
      "action": "online:goGenerateCode",
      "describe": "代码生成按钮",
      "type": "1",
      "status": "1"
    }, {"action": "user:form:phone", "describe": "手机号禁用", "type": "2", "status": "1"}, {
      "action": "user:add",
      "describe": "添加用户按钮",
      "type": "1",
      "status": "1"
    }],
    "auth": [],
    "menu": [{
      "redirect": null,
      "path": "/dashboard/analysis",
      "component": "dashboard/Analysis",
      "route": "1",
      "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "home", "title": "首页"},
      "name": "dashboard-analysis",
      "id": "9502685863ab87f0ad1134142788a385"
    }, {
      "redirect": null,
      "path": "/isystem",
      "component": "layouts/RouteView",
      "route": "1",
      "children": [{
        "path": "/isystem/depart",
        "component": "system/DepartList",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "单项指标评分表（男生）"},
        "name": "isystem-depart",
        "id": "45c966826eeff4c99b8f8ebfe74511fc"
      }, {
        "path": "/isystem/user",
        "component": "system/UserList",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "单项指标评分表（女生）"},
        "name": "isystem-user",
        "id": "3f915b2769fc80648e92d04e84ca059d"
      }, {
        "path": "/isystem/role",
        "component": "system/RoleList",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "体重指数（BMI）"},
        "name": "isystem-role",
        "id": "e8af452d8948ea49d37c934f5100ae6a"
      }, {
        "path": "/isystem/reportview",
        "component": "system/ReportListView",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "成绩查看"},
        "name": "isystem-reportview",
        "id": "f1cb187abf927c88b89470d08615f5ac"
      }],
      "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "setting", "title": "学生菜单"},
      "name": "isystem",
      "id": "d7d6e2e4e2934f2c9385a623fd98c6f3"
    }]
  }, "timestamp": 1645415388121
});
map.set("1",{
  "success": true, "message": "查询成功", "code": 200, "result": {
    "allAuth": [{
      "action": "online:goGenerateCode",
      "describe": "代码生成按钮",
      "type": "1",
      "status": "1"
    }, {"action": "user:form:phone", "describe": "手机号禁用", "type": "2", "status": "1"}, {
      "action": "user:add",
      "describe": "添加用户按钮",
      "type": "1",
      "status": "1"
    }],
    "auth": [],
    "menu": [{
      "redirect": null,
      "path": "/dashboard/analysis",
      "component": "dashboard/Analysis",
      "route": "1",
      "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "home", "title": "首页"},
      "name": "dashboard-analysis",
      "id": "9502685863ab87f0ad1134142788a385"
    }, {
      "redirect": null,
      "path": "/isystem",
      "component": "layouts/RouteView",
      "route": "1",
      "children": [{
        "path": "/isystem/depart",
        "component": "system/DepartList",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "单项指标评分表（男生）"},
        "name": "isystem-depart",
        "id": "45c966826eeff4c99b8f8ebfe74511fc"
      }, {
        "path": "/isystem/user",
        "component": "system/UserList",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "单项指标评分表（女生）"},
        "name": "isystem-user",
        "id": "3f915b2769fc80648e92d04e84ca059d"
      }, {
        "path": "/isystem/role",
        "component": "system/RoleList",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "体重指数（BMI）"},
        "name": "isystem-role",
        "id": "e8af452d8948ea49d37c934f5100ae6a"
      }, {
        "path": "/isystem/dict",
        "component": "system/DictList",
        "route": "1",
        "hidden": true,
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "学期管理"},
        "name": "isystem-dict",
        "id": "f1cb187abf927c88b89470d08615f5ac"
      }, {
        "path": "/isystem/dict2",
        "component": "system/DictList2",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "成绩管理"},
        "name": "isystem-dict2",
        "id": "f1cb187abf927c88b89470d08615f5ac1"
      }, {
        "path": "/isystem/report",
        "component": "system/ReportList",
        "route": "1",
        "hidden": true,
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "学生成绩管理"},
        "name": "isystem-report",
        "id": "f1cb187abf927c88b89470d08615f5ac2"
      }],
      "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "setting", "title": "教师菜单"},
      "name": "isystem",
      "id": "d7d6e2e4e2934f2c9385a623fd98c6f3"
    }]
  }, "timestamp": 1645415388121
});
map.set("2",{
  "success": true, "message": "查询成功", "code": 200, "result": {
    "allAuth": [{
      "action": "online:goGenerateCode",
      "describe": "代码生成按钮",
      "type": "1",
      "status": "1"
    }, {"action": "user:form:phone", "describe": "手机号禁用", "type": "2", "status": "1"}, {
      "action": "user:add",
      "describe": "添加用户按钮",
      "type": "1",
      "status": "1"
    }],
    "auth": [],
    "menu": [{
      "redirect": null,
      "path": "/dashboard/analysis",
      "component": "dashboard/Analysis",
      "route": "1",
      "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "home", "title": "首页"},
      "name": "dashboard-analysis",
      "id": "9502685863ab87f0ad1134142788a385"
    }, {
      "redirect": null,
      "path": "/isystem",
      "component": "layouts/RouteView",
      "route": "1",
      "children": [{
        "path": "/isystem/depart",
        "component": "system/DepartList",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "单项指标评分表（男生）"},
        "name": "isystem-depart",
        "id": "45c966826eeff4c99b8f8ebfe74511fc"
      }, {
        "path": "/isystem/user",
        "component": "system/UserList",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "单项指标评分表（女生）"},
        "name": "isystem-user",
        "id": "3f915b2769fc80648e92d04e84ca059d"
      }, {
        "path": "/isystem/role",
        "component": "system/RoleList",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "体重指数（BMI）"},
        "name": "isystem-role",
        "id": "e8af452d8948ea49d37c934f5100ae6a"
      }, {
        "path": "/isystem/dict",
        "component": "system/DictList",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "学期管理"},
        "name": "isystem-dict",
        "id": "f1cb187abf927c88b89470d08615f5ac"
      }, {
        "path": "/isystem/reportview",
        "component": "system/ReportListView",
        "route": "1",
        "meta": {"keepAlive": false, "internalOrExternal": false, "title": "成绩查看"},
        "name": "isystem-reportview",
        "id": "f1cb187abf927c88b89470d08615f5ac"
      }],
      "meta": {"keepAlive": false, "internalOrExternal": false, "icon": "setting", "title": "管理员菜单"},
      "name": "isystem",
      "id": "d7d6e2e4e2934f2c9385a623fd98c6f3"
    }]
  }, "timestamp": 1645415388121
});


let url = '/tice/sys/permission/getUserPermissionByToken';

import {getQueryString} from '@/utils/util'
export default {

  'get|/tice/sys/permission/getUserPermissionByToken': option => {
    let token = getQueryString(option.url,"token");
    console.log('获取到参数为：'+token);
    let permission = map.get(token);
    console.log('请求url:' + url + "，结果：" + JSON.stringify(permission));
    return permission
  }

}