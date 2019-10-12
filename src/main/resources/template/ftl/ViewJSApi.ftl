/**
* 添加
* @param sendData 发送请求参数
*/
function sendSave${EntityName}(sendData){
$.send({
url:"/${entityName}/save",
data:sendData,
success:function (data) {
if(data.status.code == 200){
alert("添加数据成功！");
console.log(JSON.stringify(data));
}else{//添加失败
layer.msg("添加数据失败！");
}
}
})
}
/**
* 删除
* @param sendData 发送请求参数
*/
function sendDel${EntityName}(sendData){
$.send({
url:"/${entityName}/delete",
data:sendData,
success:function (data) {
if(data.status.code == 200){
alert("删除数据成功！");
console.log(JSON.stringify(data));
}else{//删除失败
layer.msg("删除数据失败！");
}
}
})
}
/**
* 修改
* @param sendData 发送请求参数
*/
function sendUpdate${EntityName}(sendData){
$.send({
url:"/${entityName}/update",
data:sendData,
success:function (data) {
if(data.status.code == 200){
alert("修改成功！");
console.log(JSON.stringify(data));
}else{//修改数据失败
layer.msg("修改数据失败！");
}
}
})
}
/**
* 更具ID查询对象
* @param sendData 发送请求参数
*/
function sendFind${EntityName}ById(sendData){
$.send({
url:"/${entityName}/getById",
data:sendData,
success:function (data) {
if(data.status.code == 200){
alert("查询成功！");
console.log(JSON.stringify(data));
}else{//没有获取到数据
layer.msg("没有获取到想要数据！");
}
}
})
}
/**
* 查询集合
* @param sendData 发送请求参数
*/
function sendFind${EntityName}List(sendData){
$.send({
url:"/${entityName}/getListPage",
data:sendData,
success:function (data) {
if(data.status.code == 200) {
var userList = "";
console.log(JSON.stringify(data));
if (data.result.list.length > 0) {
$.each(data.result.list, function (n, value) {
console.log(value);

});
$("#${entityName}List").html(userList);
}
layui.laypage({
cont: '${entityName}Page', //容器。值支持id名、原生dom对象，jquery对象,
pages: data.result.totalPage, //通过后台拿到的总页数
curr: data.result.pageNo || 1, //当前页
// skip: false, //是否开启跳页
skin: '#02CAB0',
first: 1, //将首页显示为数字1,。若不显示，设置false即可
last: data.result.totalPage, //将尾页显示为总页数。若不显示，设置false即可
prev: '上一页', //若不显示，设置false即可
next: '下一页', //若不显示，设置false即可
jump: function (obj, first) {
if (!first) {  //一定要加此判断，否则初始时会无限刷新
layer.msg('第 ' + obj.curr + '页');
sendData.pageNo = obj.curr;
sendFind${EntityName}List(sendData)
}
}
});
}else{//没有获取到数据
layer.msg("没有数据！");
}
}
})
}