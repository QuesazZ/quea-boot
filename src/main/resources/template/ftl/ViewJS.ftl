/**
* 获取页面数据
*/
var viewData = {
${entityData}
pageNo:1, // 当前页, 默认为第1页
pageSize:10, // 每页记录数
totalRecord:-1 // 总记录数, 默认为-1, 表示需要查询
}
/**
* 初始化页面数据
*/
function initViewData(){
${setParams}
}
/**
* 添加
* @param obj 当前点击对象
*/
function save${EntityName}(obj){
initViewData();
var sendData = viewData;
//非空判断，在checkField($("#表单对象Id"))中传入表单对象，默认不做非空判断
if(checkField()){
sendSave${EntityName}(sendData);
}
}
/**
* 删除
* @param obj 当前点击对象
*/
function del${EntityName}(obj){
initViewData();
var sendData = viewData;
//非空判断，在checkField($("#表单对象Id"))中传入表单对象，默认不做非空判断
if(checkField()){
sendDel${EntityName}(sendData);
}
}
/**
* 修改
* @param obj 当前点击对象
*/
function update${EntityName}(obj){
initViewData();
var sendData = viewData;
//非空判断，在checkField($("#表单对象Id"))中传入表单对象，默认不做非空判断
if(checkField()){
sendUpdate${EntityName}(sendData);
}
}
/**
* 更具ID查询对象
* @param obj 当前点击对象
*/
function get${EntityName}ById(obj){
initViewData();
var sendData = viewData;
//非空判断，在checkField($("#表单对象Id"))中传入表单对象，默认不做非空判断
if(checkField()){
sendFind${EntityName}ById(sendData);
}
}
/**
* 查询集合
* @param obj 当前点击对象
*/
function get${EntityName}List(obj){
initViewData();
var sendData = viewData;
//非空判断，在checkField($("#表单对象Id"))中传入表单对象，默认不做非空判断
if(checkField()){
sendFind${EntityName}List(sendData);
}
}