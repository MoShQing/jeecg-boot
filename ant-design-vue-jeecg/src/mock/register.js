let obj = {"success":true,"message":"注册成功","code":200};
let url = 'post|/jeecg-boot/sys/user/register';

export default {

  'post|/jeecg-boot/sys/user/register':  option => {
    console.log('请求url:'+url+"，结果："+JSON.stringify(obj));
    return obj
  }
}