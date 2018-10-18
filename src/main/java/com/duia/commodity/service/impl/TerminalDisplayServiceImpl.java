package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.TerminalDisplayMapper;
import com.duia.commodity.model.TerminalDisplay;
import com.duia.commodity.service.TerminalDisplayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/09/07.
 */
@Service
@Transactional
public class TerminalDisplayServiceImpl extends AbstractService<TerminalDisplay> implements TerminalDisplayService {
    @Resource
    private TerminalDisplayMapper terminalDisplayMapper;

    @Override
    public boolean terminalCheck(Long relationId, Integer relationType, Integer appType) {
        TerminalDisplay query = new TerminalDisplay();
        query.setRelationId(relationId);
        query.setRelationType(relationType);
        query.setTerminal(appType);

        List list = terminalDisplayMapper.select(query);

        return (list !=null && !list.isEmpty());
    }
}
