let demoList = [{
  id: 1,
  name: 'zs',
  age: '23',
  job: '前端工程师'
},{
  id: 2,
  name: 'ww',
  age: '24',
  job: '后端工程师'
}]

export default {
  'get|/jeecg-boot/sys/user/list':  option => {
    return {"success":true,"message":"操作成功！","code":0,"result":{"records":[{"birthday":"2018-12-05","sex_dictText":"男","sex":1,"activitiSync":"1","telephone":null,"updateTime":"2019-11-21 16:39:35","avatar":"user/20190119/logo-2_1547868176839.png","status_dictText":"正常","delFlag":"0","realname":"管理员22","workNo":"111","createBy":null,"post":"","phone":"18566666661","createTime":"2038-06-21 17:54:10","updateBy":"admin","orgCode":"A01","id":"e9ca23d68d884d4ebb19d07889727dae","email":"11@qq.com","username":"admin","status":1},{"birthday":"2019-04-01","sex_dictText":null,"sex":null,"activitiSync":"1","telephone":null,"updateTime":"2019-04-10 22:00:22","avatar":"user/20190401/20180607175028Fn1Lq7zw_1554118444672.png","status_dictText":"正常","delFlag":"0","realname":"张小红","workNo":null,"createBy":"admin","post":null,"phone":null,"createTime":"2023-10-01 19:34:10","updateBy":"admin","orgCode":null,"id":"f0019fdebedb443c98dcb17d88222c38","email":null,"username":"zhagnxiao","status":1},{"birthday":null,"sex_dictText":"女","sex":2,"activitiSync":"1","telephone":null,"updateTime":"2019-04-09 15:47:36","avatar":"user/20190220/e1fe9925bc315c60addea1b98eb1cb1349547719_1550656892940.jpg","status_dictText":"正常","delFlag":"0","realname":"jeecg","workNo":null,"createBy":"admin","post":null,"phone":null,"createTime":"2019-02-13 16:02:36","updateBy":"admin","orgCode":"A02A01","id":"a75d45a015c44384a04449ee80dc3503","email":null,"username":"jeecg","status":1},{"birthday":"2019-02-09","sex_dictText":"男","sex":1,"activitiSync":"1","telephone":null,"updateTime":"2019-03-23 15:05:50","avatar":"user/20190314/ly-plate-e_1552531617500.png","status_dictText":"正常","delFlag":"0","realname":"23232","workNo":null,"createBy":"admin","post":null,"phone":"18611782222","createTime":"2019-01-26 18:01:10","updateBy":"admin","orgCode":null,"id":"42d153bffeea74f72a9c1697874fa4a7","email":"zhangdaiscott@163.com","username":"test22","status":1}],"total":4,"size":10,"current":1,"orders":[],"searchCount":true,"pages":1},"timestamp":1645411864143};
  }
}