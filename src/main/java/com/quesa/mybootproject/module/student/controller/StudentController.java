package com.quesa.mybootproject.module.student.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.quesa.mybootproject.module.student.service.StudentService;
import com.quesa.mybootproject.module.student.entity.Student;

/**
 * 控制层
 * Created by quesa
 */
@RestController
@Api(value = "", description = "")
@RequestMapping(value = "/v2/user/student", method = RequestMethod.POST)

public class StudentController extends BaseController {
    @Autowired
    StudentService studentService;

    /**
     * <p>
     * 保存对象-如果对象存在则修改原数据，否则插入一条新数据
     * </p>
     */
    @ApiOperation(value = "新增或更新", notes = "/v2/user/student/save?data={\"method\":\"save\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"name\":\"姓名\",\"sex\":\"性别\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/save")
    @AccessRequired
    public void save() {
        Student student = new Student();
        //获取前端数据
        student.setId(RequestUtil.getParameterToString("id"));//
        student.setName(RequestUtil.getParameterToString("name"));//姓名
        student.setSex(RequestUtil.getParameterToInteger("sex"));//性别
        //与数据库交互
        Integer isOk = studentService.save(student);
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
    @ApiOperation(value = "新增", notes = "/v2/user/student/insert?data={\"method\":\"insert\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"name\":\"姓名\",\"sex\":\"性别\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/insert")
    @AccessRequired
    public void insert() {
        Student student = new Student();
        //获取前端数据
        student.setId(RequestUtil.getParameterToString("id"));//
        student.setName(RequestUtil.getParameterToString("name"));//姓名
        student.setSex(RequestUtil.getParameterToInteger("sex"));//性别
        student.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        //与数据库交互
        Integer isOk = studentService.insert(student);
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
    @ApiOperation(value = "根据ID更新", notes = "/v2/user/student/update?data={\"method\":\"update\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"name\":\"姓名\",\"sex\":\"性别\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/update")
    @AccessRequired
    public void update() {
        Student student = new Student();
//获取前端数据

        student.setId(RequestUtil.getParameterToString("id"));//
        student.setName(RequestUtil.getParameterToString("name"));//姓名
        student.setSex(RequestUtil.getParameterToInteger("sex"));//性别
        student.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
//与数据库交互
        Integer isOk = studentService.update(student);
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
    @ApiOperation(value = "根据ID更新", notes = "/v2/user/student/update_by_id?data={\"method\":\"update_by_id\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"name\":\"姓名\",\"sex\":\"性别\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/update_by_id")
    @AccessRequired
    public void updateById() {
        Student student = new Student();
//获取前端数据

        student.setId(RequestUtil.getParameterToString("id"));//
        student.setName(RequestUtil.getParameterToString("name"));//姓名
        student.setSex(RequestUtil.getParameterToInteger("sex"));//性别
        student.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
//与数据库交互
        Integer isOk = studentService.update(student);
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
    @ApiOperation(value = "根据ID查找返回单个对象", notes = "/v2/user/student/update_by_ids?data={\"method\":\"update_by_ids\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"name\":\"姓名\",\"sex\":\"性别\",\"ct\":\"创建时间\",\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String", defaultValue = "{}")
    @RequestMapping("/update_by_ids")
    public void updateByIds() {
//获取前端数据
        Student student = new Student();

        student.setId(RequestUtil.getParameterToString("id"));//
        student.setName(RequestUtil.getParameterToString("name"));//姓名
        student.setSex(RequestUtil.getParameterToInteger("sex"));//性别
        student.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        List<String> ids = (List<String>) JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        Integer isOk = studentService.updateByIds(student, ids);
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
     * 根据多条件查找返回单个对象
     * </P>
     */
    @ApiOperation(value = "根据多条件查找返回单个对象", notes = "/v2/user/student/get?data={\"method\":\"get\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"name\":\"姓名\",\"sex\":\"性别\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String",
            defaultValue = "{}")
    @RequestMapping("/get")
    public void get() {
        Student student = new Student();

        student.setId(RequestUtil.getParameterToString("id"));//
        student.setName(RequestUtil.getParameterToString("name"));//姓名
        student.setSex(RequestUtil.getParameterToInteger("sex"));//性别
        student.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        //与数据库交互
        student = studentService.get(student);
        //向页面输出数据
        if (student != null && !StringUtil.isEmpty(student.getId())) {
            ResponseUtil.writeJSON(student.toJSONObjcet());
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);
        }
        return;
    }

    /**
     * <p>
     * 根据ID查找返回单个对象
     * </P>
     */
    @ApiOperation(value = "根据多条件查找返回单个对象", notes = "/v2/user/student/get?data={\"method\":\"get\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"name\":\"姓名\",\"sex\":\"性别\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType = "String",
            defaultValue = "{}")
    @RequestMapping("/get_by_id")
    public void getById() {
        String id = RequestUtil.getParameterToString("id", "未获取到有效标识");
        //与数据库交互
        Student student = studentService.getById(id);
        //向页面输出数据
        if (student != null && !StringUtil.isEmpty(student.getId())) {
            ResponseUtil.writeJSON(student.toJSONObjcet());
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
    @ApiOperation(value = "查询（根据ID 批量查询）", notes = "/v2/user/student/get_by_ids?data={\"method\":\"get_by_ids\",\"userid\":\"\",\"token\":\"\",\"params\":{\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
            "String", defaultValue = "{}")
    @RequestMapping("/get_by_ids")
    public void getListByIds() {
        Page<Student> page = new Page<Student>();
        //获取前端数据
        List<String> ids = (List<String>) JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        List<Student> studentList = studentService.getListByIds(ids);
        //向页面输出数据
        page.setList(studentList);
        ResponseUtil.writeJSON(page.toJSONObject());
        return;
    }

    /**
     * <p>
     * 根据条件查询全部记录（并翻页）
     * </p>
     */
    @ApiOperation(value = "根据多条件查找并翻页", notes = "/v2/user/student/get_list?data={\"method\":\"get_list\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"name\":\"姓名\",\"sex\":\"性别\",\"ct\":\"创建时间\",\"pageNo\":\"1\",\"pageSize\":\"5\",\"sortFields\":\"\",\"sortType\":\"\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
            "String", defaultValue = "{\"method\":\"get_list\",\"params\":{\"pageNo\":\"1\",\"pageSize\":\"5\"}}")
    @RequestMapping("/get_list")
    public void getList() {
        Student student = new Student();
        Page<Student> page = new Page<Student>();//获取前端数据

        student.setId(RequestUtil.getParameterToString("id"));//
        student.setName(RequestUtil.getParameterToString("name"));//姓名
        student.setSex(RequestUtil.getParameterToInteger("sex"));//性别
        student.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        student.setSortFields(StringUtil.fieldToSql(RequestUtil.getParameterToString("sortFields")));//排序
        student.setSortType(RequestUtil.getParameterToString("sortType"));//升序or降序
        page.setPageNo(RequestUtil.getParameterToInteger("pageNo") == null ? 1 :
                RequestUtil.getParameterToInteger("pageNo"));
        page.setPageSize(RequestUtil.getParameterToInteger("pageSize") == null ? Page.DEFAULT_PAGE_SIZE :
                RequestUtil.getParameterToInteger("pageSize"));
        page.setTotalRecord(RequestUtil.getParameterToInteger("totalRecord") == null ? -1 :
                RequestUtil.getParameterToInteger("totalRecord"));
        //与数据库交互
        page = studentService.getListPage(page, student);
        // 向页面输出数据信息
        ResponseUtil.writeJSON(page.toJSONObject());
        return;
    }

    /**
     * <p>
     * 根据 id删除记录-逻辑删除
     * </p>
     */
    @ApiOperation(value = "根据 id删除记录-逻辑删除", notes = "/v2/user/student/delete_by_id?data={\"method\":\"delete_by_id\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"name\":\"姓名\",\"sex\":\"性别\",\"ct\":\"创建时间\"}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
            "String", defaultValue = "{}")
    @RequestMapping("/delete_by_id")
    @AccessRequired
    public void deleteById() {
        Student student = new Student();
        //获取前端数据
        student.setId(RequestUtil.getParameterToString("id", "id不能为空"));//模板ID
        //与数据库交互
        Integer isOk = studentService.deleteLogicById(student);
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
     * 根据 ids批量删除记录-逻辑删除
     * </p>
     */
    @ApiOperation(value = "根据 ids批量删除记录-逻辑删除", notes = "/v2/user/student/delete_by_ids?data={\"method\":\"delete_by_ids\",\"userid\":\"\",\"token\":\"\",\"params\":{\"id\":\"\",\"name\":\"姓名\",\"sex\":\"性别\",\"ct\":\"创建时间\",\"ids\":[\"id1\",\"id2\"]}}")
    @ApiImplicitParam(name = "data", value = "JSON对象传递参数", required = true, paramType = "body", dataType =
            "String", defaultValue = "{}")
    @RequestMapping("/delete_by_ids")
    @AccessRequired
    public void deleteByIds() {
        //获取前端数据
        Student student = new Student();

        student.setId(RequestUtil.getParameterToString("id"));//
        student.setName(RequestUtil.getParameterToString("name"));//姓名
        student.setSex(RequestUtil.getParameterToInteger("sex"));//性别
        student.setCt(RequestUtil.getParameterToDate("ct"));//创建时间;
        student.setId(RequestUtil.getParameterToString("ids", "ids不能为空!"));//模板ID
        List<String> ids = (List<String>) JSON.parse(RequestUtil.getParameterToString("ids", "ids不能为空"));
        //与数据库交互
        Integer isOk = studentService.deleteLogicByIds(student, ids);
        //向页面输出数据
        if (isOk >= 1) {
            ResponseUtil.writeJSON(Constant.SYSTEM_SUCCESS);//更新成功
        } else {
            ResponseUtil.writeJSON(Constant.SYSTEM_ADD_DATA_ERROR);//更新失败
        }
        return;
    }

}