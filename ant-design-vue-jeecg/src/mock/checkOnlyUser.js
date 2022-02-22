let arr = ['w','teacher','manager'];
let obj = {"success":true,"message":"","code":200,"result":true}
let obj_err = {"success":false,"message":"用户账号已存在","code":200,"result":true}

let url = 'get|/tice/sys/user/checkOnlyUser';

import {getQueryString} from '@/utils/util'
export default {

  'get|/tice/sys/user/checkOnlyUser':  option => {
    let username = getQueryString(option.url, "username");

    if(arr.indexOf(username)<0) {
      console.log('请求url:'+url+"，结果："+JSON.stringify(obj));
      return obj
    }else {
      console.log('请求url:'+url+"，结果："+JSON.stringify(obj_err));
      return obj_err
    }
  }
}
