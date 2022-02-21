let obj = {
  "success": true, "message": "操作成功！", "code": 0, "result": {
    "records": [{
      "createBy": "admin",
      "dictCode": "0",
      "createTime": "2021-02-22 19:26:54",
      "updateBy": "admin",
      "description": null,
      "dictName": "2022上学期",
      "updateTime": "2019-03-30 18:14:44",
      "id": "d6e1152968b02d69ff358c75b48a6ee1",
      "type": 0,
      "delFlag": 0
    }, {
      "createBy": "admin",
      "dictCode": "1",
      "createTime": "2020-12-18 23:24:32",
      "updateBy": "admin",
      "description": null,
      "dictName": "2021下学期",
      "updateTime": "2019-04-01 15:27:06",
      "id": "ac2f7c0c5c5775fcea7e2387bcb22f01",
      "type": 1,
      "delFlag": 0
    }, {
      "createBy": "admin",
      "dictCode": "1",
      "createTime": "2020-09-26 19:21:14",
      "updateBy": "admin",
      "description": "已存档数据，不可编辑",
      "dictName": "2021上学期",
      "updateTime": "2019-04-26 19:21:23",
      "id": "68168534ff5065a152bfab275c2136f8",
      "type": 0,
      "delFlag": 0
    }, {
      "createBy": "admin",
      "dictCode": "1",
      "createTime": "2020-06-18 23:18:42",
      "updateBy": "admin",
      "description": null,
      "dictName": "2020下学期",
      "updateTime": "2019-03-30 19:33:52",
      "id": "4c753b5293304e7a445fd2741b46529d",
      "type": 1,
      "delFlag": 0
    }, {
      "createBy": "admin",
      "dictCode": "1",
      "createTime": "2019-09-29 21:07:39",
      "updateBy": "admin",
      "description": "",
      "dictName": "2020上学期",
      "updateTime": "2019-09-29 21:08:26",
      "id": "1178295274528845826",
      "type": null,
      "delFlag": 0
    }]
    , "total": 5, "size": 10, "current": 1, "orders": [], "searchCount": true, "pages": 3
  }, "timestamp": 1645429576403
}
let url = 'get|/jeecg-boot/sys/dict/list';

export default {

  'get|/jeecg-boot/sys/dict/list': option => {
    console.log('请求url:' + url + "，结果：" + JSON.stringify(obj));
    return obj
  },

  'put|/jeecg-boot/sys/dict/edit': option => {
    let arr = obj.result.records;

    let newObj = JSON.parse(option.body);
    arr.splice(arr.findIndex(val=>val.dictName==newObj.dictName), 1, newObj)
    console.log('请求url:' + url + "，结果：" + JSON.stringify(obj));
    return obj
  },

  'post|/jeecg-boot/sys/dict/add': option => {
    let arr = obj.result.records;

    let newObj = JSON.parse(option.body);
    arr.unshift(newObj);
    // arr.splice(arr.findIndex(val=>val.dictName==newObj.dictName), 1, newObj)
    console.log('请求url:' + url + "，结果：" + JSON.stringify(obj));
    return obj
  }
}