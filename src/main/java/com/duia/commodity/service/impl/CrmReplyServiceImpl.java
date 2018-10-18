package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.CrmReplyMapper;
import com.duia.commodity.model.AuthorityUsers;
import com.duia.commodity.model.CrmReply;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.AuthorityUsersService;
import com.duia.commodity.service.CrmReplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2018/07/02.
 */
@Service
@Transactional
public class CrmReplyServiceImpl extends AbstractService<CrmReply> implements CrmReplyService {
    @Resource
    private CrmReplyMapper crmReplyMapper;
    @Resource
    private AuthorityUsersService authorityUsersService;

    @Override
    public void saveCrmReply(PayOrder payOrder) {
        if (StringUtils.isNotEmpty(payOrder.getRemark()) && null != payOrder.getCreator()) {
            CrmReply crmReply = new CrmReply();
            crmReply.setObjId(payOrder.getId().intValue());
            crmReply.setType(1);
            crmReply.setContent(payOrder.getRemark());
            crmReply.setCreateTime(new Date());
            Long authUserId = payOrder.getCreator();

            AuthorityUsers authorityUsers = authorityUsersService.findById(authUserId);

            crmReply.setUserId(authorityUsers.getId());
            crmReply.setUserPosition(authorityUsers.getPosition());
            crmReply.setUserEmail(authorityUsers.getEmail());
            crmReply.setUserName(authorityUsers.getUsername());
            save(crmReply);
        }
    }
}
