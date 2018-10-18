package com.duia.commodity.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "crm_reply")
public class CrmReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 回复人id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 回复人name
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 回复人邮箱
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 回复人身份
     */
    @Column(name = "user_position")
    private Integer userPosition;

    /**
     * 关联id
     */
    @Column(name = "obj_id")
    private Integer objId;

    /**
     * 关联类型1：交接（objid对应订单）2：对学员（学员id）
     */
    private Integer type;

    /**
     * 回复内容
     */
    private String content;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 获取回复人id
     *
     * @return user_id - 回复人id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置回复人id
     *
     * @param userId 回复人id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取回复人name
     *
     * @return user_name - 回复人name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置回复人name
     *
     * @param userName 回复人name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取回复人邮箱
     *
     * @return user_email - 回复人邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置回复人邮箱
     *
     * @param userEmail 回复人邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取回复人身份
     *
     * @return user_position - 回复人身份
     */
    public Integer getUserPosition() {
        return userPosition;
    }

    /**
     * 设置回复人身份
     *
     * @param userPosition 回复人身份
     */
    public void setUserPosition(Integer userPosition) {
        this.userPosition = userPosition;
    }

    /**
     * 获取关联id
     *
     * @return obj_id - 关联id
     */
    public Integer getObjId() {
        return objId;
    }

    /**
     * 设置关联id
     *
     * @param objId 关联id
     */
    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    /**
     * 获取关联类型1：交接（objid对应订单）2：对学员（学员id）
     *
     * @return type - 关联类型1：交接（objid对应订单）2：对学员（学员id）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置关联类型1：交接（objid对应订单）2：对学员（学员id）
     *
     * @param type 关联类型1：交接（objid对应订单）2：对学员（学员id）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取回复内容
     *
     * @return content - 回复内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置回复内容
     *
     * @param content 回复内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}