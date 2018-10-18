package com.duia.commodity.common.dto;

import java.util.List;

public class FQPeriodDTO {

    private Integer fq;
    private List<FQShuJuDTO> shuju;

    public Integer getFq() {
        return fq;
    }

    public void setFq(Integer fq) {
        this.fq = fq;
    }

    public List<FQShuJuDTO> getShuju() {
        return shuju;
    }

    public void setShuju(List<FQShuJuDTO> shuju) {
        this.shuju = shuju;
    }
}
