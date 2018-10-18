package com.duia.commodity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pic_data")
public class PicData {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关联id
     */
    @Column(name = "relation_id")
    private Integer relationId;

    /**
     * 关联类型[0:班型评论]
     */
    @Column(name = "relation_type")
    private Integer relationType;

    /**
     * 图片名称
     */
    private String title;

    /**
     * 小图
     */
    @Column(name = "small_img")
    private String smallImg;

    /**
     * 大图
     */
    @Column(name = "big_img")
    private String bigImg;

    /**
     * 大图宽度
     */
    @Column(name = "big_img_width")
    private Integer bigImgWidth;

    /**
     * 大图高度
     */
    @Column(name = "big_img_height")
    private Integer bigImgHeight;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 操作人
     */
    private Integer creator;

    /**
     * 操作时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 操作人
     */
    private Integer updator;

    /**
     * 删除状态[0:未删除,1:已删除]
     */
    private Integer deleted;

    /**
     * 获取PK
     *
     * @return id - PK
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置PK
     *
     * @param id PK
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取关联id
     *
     * @return relation_id - 关联id
     */
    public Integer getRelationId() {
        return relationId;
    }

    /**
     * 设置关联id
     *
     * @param relationId 关联id
     */
    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取关联类型[0:班型评论]
     *
     * @return relation_type - 关联类型[0:班型评论]
     */
    public Integer getRelationType() {
        return relationType;
    }

    /**
     * 设置关联类型[0:班型评论]
     *
     * @param relationType 关联类型[0:班型评论]
     */
    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    /**
     * 获取图片名称
     *
     * @return title - 图片名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置图片名称
     *
     * @param title 图片名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取小图
     *
     * @return small_img - 小图
     */
    public String getSmallImg() {
        return smallImg;
    }

    /**
     * 设置小图
     *
     * @param smallImg 小图
     */
    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    /**
     * 获取大图
     *
     * @return big_img - 大图
     */
    public String getBigImg() {
        return bigImg;
    }

    /**
     * 设置大图
     *
     * @param bigImg 大图
     */
    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    /**
     * 获取大图宽度
     *
     * @return big_img_width - 大图宽度
     */
    public Integer getBigImgWidth() {
        return bigImgWidth;
    }

    /**
     * 设置大图宽度
     *
     * @param bigImgWidth 大图宽度
     */
    public void setBigImgWidth(Integer bigImgWidth) {
        this.bigImgWidth = bigImgWidth;
    }

    /**
     * 获取大图高度
     *
     * @return big_img_height - 大图高度
     */
    public Integer getBigImgHeight() {
        return bigImgHeight;
    }

    /**
     * 设置大图高度
     *
     * @param bigImgHeight 大图高度
     */
    public void setBigImgHeight(Integer bigImgHeight) {
        this.bigImgHeight = bigImgHeight;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取操作人
     *
     * @return creator - 操作人
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * 设置操作人
     *
     * @param creator 操作人
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * 获取操作时间
     *
     * @return update_time - 操作时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置操作时间
     *
     * @param updateTime 操作时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取操作人
     *
     * @return updator - 操作人
     */
    public Integer getUpdator() {
        return updator;
    }

    /**
     * 设置操作人
     *
     * @param updator 操作人
     */
    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    /**
     * 获取删除状态[0:未删除,1:已删除]
     *
     * @return deleted - 删除状态[0:未删除,1:已删除]
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除状态[0:未删除,1:已删除]
     *
     * @param deleted 删除状态[0:未删除,1:已删除]
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}