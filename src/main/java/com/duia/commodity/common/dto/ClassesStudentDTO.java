package com.duia.commodity.common.dto;

import java.util.Date;

/**
 * Created by 李恒名 on 2017/7/15.
 */
public class ClassesStudentDTO {
    private Long id;//用户id
    private String name;//昵称
    private String imageUrl;//头像
    private Date buyTime;//购买时间
    private String content;//评论内容
    private Integer star;//星级
    private String replyContent;//老师回复内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
