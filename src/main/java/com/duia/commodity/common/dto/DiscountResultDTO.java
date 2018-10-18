package com.duia.commodity.common.dto;

import java.util.List;

public class DiscountResultDTO {
   private List<DiscountDTO> list;
   private int availableNum;
   private int unAvailableNum;

    public DiscountResultDTO() {
    }

    public DiscountResultDTO(List<DiscountDTO> list, int availableNum, int unAvailableNum) {
        this.list = list;
        this.availableNum = availableNum;
        this.unAvailableNum = unAvailableNum;
    }

    public List<DiscountDTO> getList() {
        return list;
    }

    public void setList(List<DiscountDTO> list) {
        this.list = list;
    }

    public int getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
    }

    public int getUnAvailableNum() {
        return unAvailableNum;
    }

    public void setUnAvailableNum(int unAvailableNum) {
        this.unAvailableNum = unAvailableNum;
    }
}
