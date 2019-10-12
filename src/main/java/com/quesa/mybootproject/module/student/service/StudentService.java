package com.quesa.mybootproject.module.student.service;

import com.quesa.mybootproject.module.student.entity.Student;
import com.quesa.mybootproject.module.student.dao.StudentMapper;
import org.springframework.stereotype.Service;
import com.quesa.mybootproject.common.service.BaseService;
/**
 * 
 * -业务逻辑处理层
 * 
 **/
@Service
public class StudentService extends BaseService<StudentMapper,Student> {


}
