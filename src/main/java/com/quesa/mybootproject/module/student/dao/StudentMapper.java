package com.quesa.mybootproject.module.student.dao;

import com.quesa.mybootproject.module.student.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.quesa.mybootproject.common.persistence.BaseDao;

/**
 * 
 * -数据库操作接口层
 * 
 **/
@Mapper
@Repository
public interface StudentMapper extends BaseDao<Student> {


}
