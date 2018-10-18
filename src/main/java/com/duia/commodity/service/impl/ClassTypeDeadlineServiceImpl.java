package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.ClassTypeDeadlineMapper;
import com.duia.commodity.model.ClassTypeDeadline;
import com.duia.commodity.service.ClassTypeDeadlineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/11/07.
 */
@Service
@Transactional
public class ClassTypeDeadlineServiceImpl extends AbstractService<ClassTypeDeadline> implements ClassTypeDeadlineService {
    @Resource
    private ClassTypeDeadlineMapper classTypeDeadlineMapper;

//    @Override
//    public ClassTypeDeadline findByClassTypeId(Long classTypeId) {
//        String useDate = new SimpleDateFormat("yyyyMM").format(new Date());
//        return classTypeDeadlineMapper.selectByClassTypeIdAndUseDate(classTypeId, Integer.parseInt(useDate));
//    }

    @Override
    public ClassTypeDeadline findByClassTypeId(Long classTypeId, Integer guaType) {
        Condition condition = new Condition(ClassTypeDeadline.class);
        condition.createCriteria()
                .andEqualTo("useDate", new SimpleDateFormat("yyyyMM").format(new Date()))
                .andEqualTo("classTypeId", classTypeId)
                .andEqualTo("guaType", guaType);
        List<ClassTypeDeadline> classTypeDeadlineList = this.findByCondition(condition);
        if (null == classTypeDeadlineList || classTypeDeadlineList.isEmpty()) {
            return new ClassTypeDeadline();
        }
        return classTypeDeadlineList.get(0);
    }
}
