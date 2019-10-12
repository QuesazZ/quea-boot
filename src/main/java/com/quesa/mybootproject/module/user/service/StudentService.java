package com.quesa.mybootproject.module.user.service;

import com.quesa.mybootproject.module.user.entity.Student;
import com.quesa.mybootproject.module.user.dao.StudentMapper;
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
