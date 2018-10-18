package com.duia.commodity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "book_resource")
public class BookResource {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * sku
     */
    private Integer sku;

    /**
     * 资源包名称
     */
    private String title;

    /**
     * 资源包视频
     */
    @Column(name = "video_id")
    private String videoId;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改者
     */
    private Long updator;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 删除标识位[0:可用,1:删除]
     */
    private Integer deleted;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取sku
     *
     * @return sku - sku
     */
    public Integer getSku() {
        return sku;
    }

    /**
     * 设置sku
     *
     * @param sku sku
     */
    public void setSku(Integer sku) {
        this.sku = sku;
    }

    /**
     * 获取资源包名称
     *
     * @return title - 资源包名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置资源包名称
     *
     * @param title 资源包名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取资源包视频
     *
     * @return video_id - 资源包视频
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 设置资源包视频
     *
     * @param videoId 资源包视频
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(Long creator) {
        this.creator = creator;
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
     * 获取最后修改者
     *
     * @return updator - 最后修改者
     */
    public Long getUpdator() {
        return updator;
    }

    /**
     * 设置最后修改者
     *
     * @param updator 最后修改者
     */
    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    /**
     * 获取最后修改时间
     *
     * @return update_time - 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param updateTime 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取删除标识位[0:可用,1:删除]
     *
     * @return deleted - 删除标识位[0:可用,1:删除]
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识位[0:可用,1:删除]
     *
     * @param deleted 删除标识位[0:可用,1:删除]
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}