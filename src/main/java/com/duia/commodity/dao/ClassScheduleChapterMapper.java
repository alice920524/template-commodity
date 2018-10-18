package com.duia.commodity.dao;

import com.duia.commodity.common.dto.ChapterDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.ClassScheduleChapter;

import java.util.List;

public interface ClassScheduleChapterMapper extends Mapper<ClassScheduleChapter> {
    List<ChapterDTO> selectByClassId(Long classId);
}