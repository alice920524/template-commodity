package com.duia.commodity.service;

import com.duia.commodity.Tester;
import com.duia.commodity.common.dto.ChapterDTO;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 李恒名 on 2017/7/15.
 */
public class ClassScheduleChapterServiceTestCase extends Tester {
    @Resource
    private ClassScheduleChapterService chapterService;
    @Test
    public void test1(){
        final long classId = 2856;
        List<ChapterDTO> chapterList = chapterService.findByClassId(classId);
        Assert.assertNotNull(chapterList);
    }

}
