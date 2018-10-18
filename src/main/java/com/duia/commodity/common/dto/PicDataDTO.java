package com.duia.commodity.common.dto;

public class PicDataDTO {
    private String smallImg;
    private String bigImg;
    private Integer bigImgWidth;
    private Integer bigImgHeight;

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public String getBigImg() {
        return bigImg;
    }

    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    public Integer getBigImgWidth() {
        return bigImgWidth;
    }

    public void setBigImgWidth(Integer bigImgWidth) {
        this.bigImgWidth = bigImgWidth;
    }

    public Integer getBigImgHeight() {
        return bigImgHeight;
    }

    public void setBigImgHeight(Integer bigImgHeight) {
        this.bigImgHeight = bigImgHeight;
    }
}
