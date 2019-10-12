package ${package};
import com.alibaba.fastjson.JSON;
import com.quesa.mybootproject.common.controller.AccessRequired;
import com.quesa.mybootproject.common.controller.BaseController;
import com.quesa.mybootproject.common.controller.RequestUtil;
import com.quesa.mybootproject.common.controller.ResponseUtil;
import com.quesa.mybootproject.common.page.Page;
import com.quesa.mybootproject.common.util.StringUtil;
import com.quesa.mybootproject.common.constants.Constant;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ${servicePage}.${ServiceName};
import ${entityPage}.${EntityName};
/**
* ${tableComment}控制层
* Created by quesa
*/
@RestController
@Api(value = "${tableComment}",description = "${tableComment}")
@RequestMapping(value = "/v2/${modulePage}/${moduleName}",method = RequestMethod.POST)

public class ${EntityName}Controller extends BaseController {
    @Autowired
    ${ServiceName} ${serviceName};

    /**
    * <p>
    * 保存对象-如果对象存在则修改原数据，否则插入一条新数据
    * </p>
    */
    @ApiOperation(value = "新增或更新", notes = "/v2/${modulePage}/${moduleName}/save?data={\"method\":\"save\",\"userid\":\"\",\"token\":\"\",\"params\":{${apiParams}}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/save")
    @AccessRequired
    public void save() {
        ${EntityName} ${entityName} = new ${EntityName}();
        //获取前端数据
        ${setParameter};
        //与数据库交互
        Integer isOk = ${serviceName}.save(${entityName});
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);//保存成功
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_ADD_DATA_ERROR);//保存失败
        }
        return;
    }

    /**
    * <p>
    * 新增一条数据
    * </p>
    */
    @ApiOperation(value = "新增", notes = "/v2/${modulePage}/${moduleName}/insert?data={\"method\":\"insert\",\"userid\":\"\",\"token\":\"\",\"params\":{${apiParams}}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/insert")
    @AccessRequired
    public void insert() {
        ${EntityName} ${entityName} = new ${EntityName}();
        //获取前端数据
        ${setParameter};
        //与数据库交互
        Integer isOk = ${serviceName}.insert(${entityName});
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);//新增成功
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_ADD_DATA_ERROR);//新增失败
        }
        return;
    }

    /**
    * <p>
    * 根据ID更新一条数据
    * </p>
    */
    @ApiOperation(value = "根据ID更新", notes = "/v2/${modulePage}/${moduleName}/update?data={\"method\":\"update\",\"userid\":\"\",\"token\":\"\",\"params\":{${apiParams}}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/update")
    @AccessRequired
    public void update() {
        ${EntityName} ${entityName} = new ${EntityName}();
        //获取前端数据
        ${setParameter};
        //与数据库交互
        Integer isOk = ${serviceName}.update(${entityName});
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);//更新成功
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_ADD_DATA_ERROR);//更新失败
        }
        return;
    }

    /**
    * <p>
    * 根据ID更新一条数据
    * </p>
    */
    @ApiOperation(value = "根据ID更新", notes = "/v2/${modulePage}/${moduleName}/update_by_id?data={\"method\":\"update_by_id\",\"userid\":\"\",\"token\":\"\",\"params\":{${apiParams}}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/update_by_id")
    @AccessRequired
    public void updateById() {
        ${EntityName} ${entityName} = new ${EntityName}();
        //获取前端数据
        ${setParameter};
        //与数据库交互
        Integer isOk = ${serviceName}.update(${entityName});
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);//更新成功
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_ADD_DATA_ERROR);//更新失败
        }
        return;
    }

    /**
    * <p>
    * 根据Ids批量更新数据
    * </p>
    */
    @ApiOperation(value = "根据ID查找返回单个对象", notes = "/v2/${modulePage}/${moduleName}/update_by_ids?data={\"method\":\"update_by_ids\",\"userid\":\"\",\"token\":\"\",\"params\":{${apiParams},\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/update_by_ids")
    public void updateByIds() {
        //获取前端数据
        ${EntityName} ${entityName} = new ${EntityName}();
        ${setParameter};
        List<String> ids = (List<String>)JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        Integer isOk = ${serviceName}.updateByIds(${entityName},ids);
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);//更新成功
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_ADD_DATA_ERROR);//更新失败
        }
        return;
    }

    /**
    *<P>
    * 根据多条件查找返回单个对象
    *</P>
    */
    @ApiOperation(value = "根据多条件查找返回单个对象", notes = "/v2/${modulePage}/${moduleName}/get?data={\"method\":\"get\",\"userid\":\"\",\"token\":\"\",\"params\":{${apiParams}}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType ="String",
    defaultValue = "{}")
    @RequestMapping("/get")
    public void get() {
        ${EntityName} ${entityName} = new ${EntityName}();
        ${setParameter};
        //与数据库交互
        ${entityName} = ${serviceName}.get(${entityName});
        //向页面输出数据
        if (${entityName} != null && !StringUtil.isEmpty(${entityName}.getId())) {
            ResponseUtil.writeJSON(${entityName}.toJSONObjcet());
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);
        }
        return;
    }

    /**
    *<P>
    * 根据ID查找返回单个对象
    *</P>
    */
    @ApiOperation(value = "根据多条件查找返回单个对象", notes = "/v2/${modulePage}/${moduleName}/get?data={\"method\":\"get\",\"userid\":\"\",\"token\":\"\",\"params\":{${apiParams}}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType ="String",
    defaultValue = "{}")
    @RequestMapping("/get_by_id")
    public void getById() {
    String id = RequestUtil.getParameterToString("id","未获取到有效标识");
        //与数据库交互
        ${EntityName} ${entityName} = ${serviceName}.getById(id);
        //向页面输出数据
        if (${entityName} != null && !StringUtil.isEmpty(${entityName}.getId())) {
            ResponseUtil.writeJSON(${entityName}.toJSONObjcet());
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);
        }
        return;
    }

    /**
    * <p>
    * 查询（根据ID 批量查询）
    * </p>
    */
    @ApiOperation(value = "查询（根据ID 批量查询）", notes = "/v2/${modulePage}/${moduleName}/get_by_ids?data={\"method\":\"get_by_ids\",\"userid\":\"\",\"token\":\"\",\"params\":{\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
    "String", defaultValue = "{}")
    @RequestMapping("/get_by_ids")
    public void getListByIds() {
        Page<${EntityName}> page = new Page<${EntityName}>();
        //获取前端数据
        List<String> ids = (List<String>)JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        List<${EntityName}> ${entityName}List = ${serviceName}.getListByIds(ids);
        //向页面输出数据
        page.setList(${entityName}List);
        ResponseUtil.writeJSON(page.toJSONObject());
        return;
    }

    /**
    *<p>
    * 根据条件查询全部记录（并翻页）
    *</p>
    */
    @ApiOperation(value = "根据多条件查找并翻页", notes = "/v2/${modulePage}/${moduleName}/get_list?data={\"method\":\"get_list\",\"userid\":\"\",\"token\":\"\",\"params\":{${apiParams},\"pageNo\":\"1\",\"pageSize\":\"5\",\"sortFields\":\"\",\"sortType\":\"\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
    "String", defaultValue = "{\"method\":\"get_list\",\"params\":{\"pageNo\":\"1\",\"pageSize\":\"5\"}}")
    @RequestMapping("/get_list")
    public void getList() {
        ${EntityName} ${entityName} = new ${EntityName}();
        Page<${EntityName}> page = new Page<${EntityName}>();//获取前端数据
        ${setParameter};
        ${entityName}.setSortFields(StringUtil.fieldToSql(RequestUtil.getParameterToString("sortFields")));//排序
        ${entityName}.setSortType(RequestUtil.getParameterToString("sortType"));//升序or降序
        page.setPageNo(RequestUtil.getParameterToInteger("pageNo") == null ? 1 :
        RequestUtil.getParameterToInteger("pageNo"));
        page.setPageSize(RequestUtil.getParameterToInteger("pageSize") == null ? Page.DEFAULT_PAGE_SIZE :
        RequestUtil.getParameterToInteger("pageSize"));
        page.setTotalRecord(RequestUtil.getParameterToInteger("totalRecord") == null ? -1 :
        RequestUtil.getParameterToInteger("totalRecord"));
        //与数据库交互
        page = ${serviceName}.getListPage(page,${entityName});
        // 向页面输出数据信息
        ResponseUtil.writeJSON(page.toJSONObject());
        return;
    }

    /**
    *<p>
    * 根据 id删除记录-逻辑删除
    *</p>
    */
    @ApiOperation(value = "根据 id删除记录-逻辑删除", notes = "/v2/${modulePage}/${moduleName}/delete_by_id?data={\"method\":\"delete_by_id\",\"userid\":\"\",\"token\":\"\",\"params\":{${apiParams}}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
    "String", defaultValue = "{}")
    @RequestMapping("/delete_by_id")
    @AccessRequired
    public void deleteById() {
        ${EntityName} ${entityName} = new ${EntityName}();
        //获取前端数据
        ${entityName}.setId(RequestUtil.getParameterToString("id", "id不能为空"));//模板ID
        //与数据库交互
        Integer isOk = ${serviceName}.deleteLogicById(${entityName});
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);//更新成功
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_ADD_DATA_ERROR);//更新失败
        }
        return;
    }

    /**
    *<p>
    * 根据 ids批量删除记录-逻辑删除
    *</p>
    */
    @ApiOperation(value = "根据 ids批量删除记录-逻辑删除", notes = "/v2/${modulePage}/${moduleName}/delete_by_ids?data={\"method\":\"delete_by_ids\",\"userid\":\"\",\"token\":\"\",\"params\":{${apiParams},\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
    "String", defaultValue = "{}")
    @RequestMapping("/delete_by_ids")
    @AccessRequired
    public void deleteByIds() {
        //获取前端数据
        ${EntityName} ${entityName} = new ${EntityName}();
        ${setParameter};
        ${entityName}.setId(RequestUtil.getParameterToString("ids","ids不能为空!"));//模板ID
        List<String> ids = (List<String>)JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        Integer isOk = ${serviceName}.deleteLogicByIds(${entityName},ids);
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);//更新成功
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_ADD_DATA_ERROR);//更新失败
        }
        return;
    }

    /**
    *<P>
    * 根据 id删除记录-实际删除
    *</P>
    */
    /**@ApiOperation(value = "根据 id删除记录-实际删除", notes = "/v2/${modulePage}/${moduleName}/deletePracticalById?data={\"method\":\"deletePracticalById\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"id\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body",
    dataType = "String", defaultValue = "{}")
    @RequestMapping("/deletePracticalById")
    @AccessRequired
    public void deletePracticalById() {
        //获取前端数据
        String id = RequestUtil.getParameterToString("id", "id不能为空");
        //与数据库交互
        Integer isOk = ${serviceName}.deleteById(id);
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_DELETE_DATA_ERROR);
        }
        return;
    }*/

    /**
    *<p>
    * 根据 ids批量删除记录-实际删除
    *</p>
    */
    /**@ApiOperation(value = "根据 ids批量删除记录-实际删除", notes = "/v2/${modulePage}/${moduleName}/deletePracticalByIds?data={\"method\":\"deletePracticalByIds\",\"userid\":\"\",\"token\":\"\",\"params\":{\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body",
    dataType = "String", defaultValue = "{}")
    @RequestMapping("/deletePracticalByIds")
    @AccessRequired
    public void deletePracticalByIds() {
        //获取前端数据
        List<String> ids = (ListString>)JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        Integer isOk = ${serviceName}.deleteByIds(ids);
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_DELETE_DATA_ERROR);
        }
        return;
    }*/

}