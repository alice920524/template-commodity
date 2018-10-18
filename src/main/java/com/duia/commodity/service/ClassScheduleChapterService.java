package com.duia.commodity.service;

import com.duia.commodity.common.dto.ChapterDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.ClassScheduleChapter;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/13.
 */
public interface ClassScheduleChapterService extends Service<ClassScheduleChapter> {

    /**
     * 通过班级ID获取课表下的章、节
     *
     * @param classId 班级ID
     */
    List<ChapterDTO> findByClassId(Long classId);

}
