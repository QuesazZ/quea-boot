package com.quesa.mybootproject.module.score.controller;
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
import com.quesa.mybootproject.module.score.service.ScoreService;
import com.quesa.mybootproject.module.score.entity.Score;
/**
* 控制层
* Created by quesa
*/
@RestController
@Api(value = "",description = "")
@RequestMapping(value = "/v2/score/score",method = RequestMethod.POST)

public class ScoreController extends BaseController {
    @Autowired
    ScoreService scoreService;

    /**
    * <p>
    * 保存对象-如果对象存在则修改原数据，否则插入一条新数据
    * </p>
    */
    @ApiOperation(value = "新增或更新", notes = "/v2/score/score/save?data={\"method\":\"save\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"stuId\":\"学生id\",\"score\":\"分数\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/save")
    @AccessRequired
    public void save() {
        Score score = new Score();
        //获取前端数据
        
score.setId(RequestUtil.getParameterToString("id"));//
score.setStuId(RequestUtil.getParameterToString("stuId"));//学生id
score.setScore(RequestUtil.getParameterToDouble("score",2));//分数
score.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        //与数据库交互
        Integer isOk = scoreService.save(score);
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
    @ApiOperation(value = "新增", notes = "/v2/score/score/insert?data={\"method\":\"insert\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"stuId\":\"学生id\",\"score\":\"分数\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/insert")
    @AccessRequired
    public void insert() {
        Score score = new Score();
        //获取前端数据
        
score.setId(RequestUtil.getParameterToString("id"));//
score.setStuId(RequestUtil.getParameterToString("stuId"));//学生id
score.setScore(RequestUtil.getParameterToDouble("score",2));//分数
score.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        //与数据库交互
        Integer isOk = scoreService.insert(score);
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
    @ApiOperation(value = "根据ID更新", notes = "/v2/score/score/update?data={\"method\":\"update\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"stuId\":\"学生id\",\"score\":\"分数\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/update")
    @AccessRequired
    public void update() {
        Score score = new Score();
        //获取前端数据
        
score.setId(RequestUtil.getParameterToString("id"));//
score.setStuId(RequestUtil.getParameterToString("stuId"));//学生id
score.setScore(RequestUtil.getParameterToDouble("score",2));//分数
score.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        //与数据库交互
        Integer isOk = scoreService.update(score);
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
    @ApiOperation(value = "根据ID更新", notes = "/v2/score/score/update_by_id?data={\"method\":\"update_by_id\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"stuId\":\"学生id\",\"score\":\"分数\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/update_by_id")
    @AccessRequired
    public void updateById() {
        Score score = new Score();
        //获取前端数据
        
score.setId(RequestUtil.getParameterToString("id"));//
score.setStuId(RequestUtil.getParameterToString("stuId"));//学生id
score.setScore(RequestUtil.getParameterToDouble("score",2));//分数
score.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        //与数据库交互
        Integer isOk = scoreService.update(score);
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
    @ApiOperation(value = "根据ID查找返回单个对象", notes = "/v2/score/score/update_by_ids?data={\"method\":\"update_by_ids\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"stuId\":\"学生id\",\"score\":\"分数\",\"ct\":\"创建时间\",\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/update_by_ids")
    public void updateByIds() {
        //获取前端数据
        Score score = new Score();
        
score.setId(RequestUtil.getParameterToString("id"));//
score.setStuId(RequestUtil.getParameterToString("stuId"));//学生id
score.setScore(RequestUtil.getParameterToDouble("score",2));//分数
score.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        List<String> ids = (List<String>)JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        Integer isOk = scoreService.updateByIds(score,ids);
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
    @ApiOperation(value = "根据多条件查找返回单个对象", notes = "/v2/score/score/get?data={\"method\":\"get\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"stuId\":\"学生id\",\"score\":\"分数\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType ="String",
    defaultValue = "{}")
    @RequestMapping("/get")
    public void get() {
        Score score = new Score();
        
score.setId(RequestUtil.getParameterToString("id"));//
score.setStuId(RequestUtil.getParameterToString("stuId"));//学生id
score.setScore(RequestUtil.getParameterToDouble("score",2));//分数
score.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        //与数据库交互
        score = scoreService.get(score);
        //向页面输出数据
        if (score != null && !StringUtil.isEmpty(score.getId())) {
            ResponseUtil.writeJSON(score.toJSONObjcet());
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
    @ApiOperation(value = "根据多条件查找返回单个对象", notes = "/v2/score/score/get?data={\"method\":\"get\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"stuId\":\"学生id\",\"score\":\"分数\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType ="String",
    defaultValue = "{}")
    @RequestMapping("/get_by_id")
    public void getById() {
    String id = RequestUtil.getParameterToString("id","未获取到有效标识");
        //与数据库交互
        Score score = scoreService.getById(id);
        //向页面输出数据
        if (score != null && !StringUtil.isEmpty(score.getId())) {
            ResponseUtil.writeJSON(score.toJSONObjcet());
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
    @ApiOperation(value = "查询（根据ID 批量查询）", notes = "/v2/score/score/get_by_ids?data={\"method\":\"get_by_ids\",\"userid\":\"\",\"token\":\"\",\"params\":{\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
    "String", defaultValue = "{}")
    @RequestMapping("/get_by_ids")
    public void getListByIds() {
        Page<Score> page = new Page<Score>();
        //获取前端数据
        List<String> ids = (List<String>)JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        List<Score> scoreList = scoreService.getListByIds(ids);
        //向页面输出数据
        page.setList(scoreList);
        ResponseUtil.writeJSON(page.toJSONObject());
        return;
    }

    /**
    *<p>
    * 根据条件查询全部记录（并翻页）
    *</p>
    */
    @ApiOperation(value = "根据多条件查找并翻页", notes = "/v2/score/score/get_list?data={\"method\":\"get_list\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"stuId\":\"学生id\",\"score\":\"分数\",\"ct\":\"创建时间\",\"pageNo\":\"1\",\"pageSize\":\"5\",\"sortFields\":\"\",\"sortType\":\"\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
    "String", defaultValue = "{\"method\":\"get_list\",\"params\":{\"pageNo\":\"1\",\"pageSize\":\"5\"}}")
    @RequestMapping("/get_list")
    public void getList() {
        Score score = new Score();
        Page<Score> page = new Page<Score>();//获取前端数据
        
score.setId(RequestUtil.getParameterToString("id"));//
score.setStuId(RequestUtil.getParameterToString("stuId"));//学生id
score.setScore(RequestUtil.getParameterToDouble("score",2));//分数
score.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        score.setSortFields(StringUtil.fieldToSql(RequestUtil.getParameterToString("sortFields")));//排序
        score.setSortType(RequestUtil.getParameterToString("sortType"));//升序or降序
        page.setPageNo(RequestUtil.getParameterToInteger("pageNo") == null ? 1 :
        RequestUtil.getParameterToInteger("pageNo"));
        page.setPageSize(RequestUtil.getParameterToInteger("pageSize") == null ? Page.DEFAULT_PAGE_SIZE :
        RequestUtil.getParameterToInteger("pageSize"));
        page.setTotalRecord(RequestUtil.getParameterToInteger("totalRecord") == null ? -1 :
        RequestUtil.getParameterToInteger("totalRecord"));
        //与数据库交互
        page = scoreService.getListPage(page,score);
        // 向页面输出数据信息
        ResponseUtil.writeJSON(page.toJSONObject());
        return;
    }

    /**
    *<p>
    * 根据 id删除记录-逻辑删除
    *</p>
    */
    @ApiOperation(value = "根据 id删除记录-逻辑删除", notes = "/v2/score/score/delete_by_id?data={\"method\":\"delete_by_id\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"stuId\":\"学生id\",\"score\":\"分数\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
    "String", defaultValue = "{}")
    @RequestMapping("/delete_by_id")
    @AccessRequired
    public void deleteById() {
        Score score = new Score();
        //获取前端数据
        score.setId(RequestUtil.getParameterToString("id", "id不能为空"));//模板ID
        //与数据库交互
        Integer isOk = scoreService.deleteLogicById(score);
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
    @ApiOperation(value = "根据 ids批量删除记录-逻辑删除", notes = "/v2/score/score/delete_by_ids?data={\"method\":\"delete_by_ids\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"stuId\":\"学生id\",\"score\":\"分数\",\"ct\":\"创建时间\",\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
    "String", defaultValue = "{}")
    @RequestMapping("/delete_by_ids")
    @AccessRequired
    public void deleteByIds() {
        //获取前端数据
        Score score = new Score();
        
score.setId(RequestUtil.getParameterToString("id"));//
score.setStuId(RequestUtil.getParameterToString("stuId"));//学生id
score.setScore(RequestUtil.getParameterToDouble("score",2));//分数
score.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        score.setId(RequestUtil.getParameterToString("ids","ids不能为空!"));//模板ID
        List<String> ids = (List<String>)JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        Integer isOk = scoreService.deleteLogicByIds(score,ids);
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
    /**@ApiOperation(value = "根据 id删除记录-实际删除", notes = "/v2/score/score/deletePracticalById?data={\"method\":\"deletePracticalById\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"id\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body",
    dataType = "String", defaultValue = "{}")
    @RequestMapping("/deletePracticalById")
    @AccessRequired
    public void deletePracticalById() {
        //获取前端数据
        String id = RequestUtil.getParameterToString("id", "id不能为空");
        //与数据库交互
        Integer isOk = scoreService.deleteById(id);
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
    /**@ApiOperation(value = "根据 ids批量删除记录-实际删除", notes = "/v2/score/score/deletePracticalByIds?data={\"method\":\"deletePracticalByIds\",\"userid\":\"\",\"token\":\"\",\"params\":{\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body",
    dataType = "String", defaultValue = "{}")
    @RequestMapping("/deletePracticalByIds")
    @AccessRequired
    public void deletePracticalByIds() {
        //获取前端数据
        List<String> ids = (ListString>)JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        Integer isOk = scoreService.deleteByIds(ids);
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_DELETE_DATA_ERROR);
        }
        return;
    }*/

}