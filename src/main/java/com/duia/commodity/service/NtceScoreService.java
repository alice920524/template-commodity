package com.duia.commodity.service;

import org.jsoup.nodes.Document;

import java.util.HashMap;

/**
 * Created by zhenghui on 2018/3/30.
 */
public interface NtceScoreService {

    String getServerPath();

    HashMap query(Document doc, HashMap resultMap);
}
