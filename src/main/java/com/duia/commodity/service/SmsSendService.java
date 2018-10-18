package com.duia.commodity.service;

/**
 * Created by 李恒名 on 2017/7/26.
 */
public interface SmsSendService {

     void send(String mobile, String templateId,String[] datas);

}
