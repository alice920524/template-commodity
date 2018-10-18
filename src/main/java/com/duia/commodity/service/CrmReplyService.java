package com.duia.commodity.service;
import com.duia.commodity.model.CrmReply;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.PayOrder;


/**
 * Created by CodeGenerator on 2018/07/02.
 */
public interface CrmReplyService extends Service<CrmReply> {

    void saveCrmReply(PayOrder payOrder);
}
