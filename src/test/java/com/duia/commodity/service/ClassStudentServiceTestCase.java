package com.duia.commodity.service;

import com.duia.commodity.Tester;
import com.duia.commodity.common.dto.ClassesStudentDTO;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 李恒名 on 2017/7/15.
 */
public class ClassStudentServiceTestCase extends Tester {
    @Resource
    private ClassStudentService studentService;
    @Test
    public void test1(){
        final long classId = 1868;
        List<ClassesStudentDTO> studentList = studentService.findByClassId(classId);
        Assert.assertNotNull(studentList);
    }
}
