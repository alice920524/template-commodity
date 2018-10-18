package com.duia.commodity.service;
import com.duia.commodity.model.TerminalDisplay;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2018/09/07.
 */
public interface TerminalDisplayService extends Service<TerminalDisplay> {

    /**
     * 终端校验
     * */
    boolean terminalCheck(Long relationId, Integer relationType, Integer appType);

}
