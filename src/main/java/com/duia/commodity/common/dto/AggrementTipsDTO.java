package com.duia.commodity.common.dto;

import java.util.List;

// 协议提示
public class AggrementTipsDTO {

    private List comName;
    private String content;

    public AggrementTipsDTO() {
    }

    public AggrementTipsDTO(List comName, String content) {
        this.comName = comName;
        this.content = content;
    }
    public AggrementTipsDTO(List comName) {
        this.comName = comName;
    }

    public List getComName() {
        return comName;
    }

    public void setComName(List comName) {
        this.comName = comName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
