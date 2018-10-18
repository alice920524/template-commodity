package com.duia.commodity.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "users_dep_grp_rel")
public class UsersDepGrpRel {
    /**
     * 主键(自增长)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 部门id
     */
    @Column(name = "dep_id")
    private Integer depId;

    /**
     * 组id
     */
    @Column(name = "grp_id")
    private Integer grpId;

    /**
     * 用户ID
     */
    @Column(name = "auth_user_id")
    private Long authUserId;

    /**
     * 状态(0-删除 1-正常)
     */
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user")
    private Integer createUser;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user")
    private Integer updateUser;

    /**
     * 人员级别id[0:无 ;其他表示对应级别id]
     */
    @Column(name = "level_id")
    private Integer levelId;

    /**
     * CRM客户端锁定状态(0:被锁定1:未锁定)
     */
    @Column(name = "crm_user_lock")
    private Boolean crmUserLock;

    /**
     * 获取主键(自增长)
     *
     * @return id - 主键(自增长)
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键(自增长)
     *
     * @param id 主键(自增长)
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取部门id
     *
     * @return dep_id - 部门id
     */
    public Integer getDepId() {
        return depId;
    }

    /**
     * 设置部门id
     *
     * @param depId 部门id
     */
    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    /**
     * 获取组id
     *
     * @return grp_id - 组id
     */
    public Integer getGrpId() {
        return grpId;
    }

    /**
     * 设置组id
     *
     * @param grpId 组id
     */
    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    /**
     * 获取用户ID
     *
     * @return auth_user_id - 用户ID
     */
    public Long getAuthUserId() {
        return authUserId;
    }

    /**
     * 设置用户ID
     *
     * @param authUserId 用户ID
     */
    public void setAuthUserId(Long authUserId) {
        this.authUserId = authUserId;
    }

    /**
     * 获取状态(0-删除 1-正常)
     *
     * @return status - 状态(0-删除 1-正常)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态(0-删除 1-正常)
     *
     * @param status 状态(0-删除 1-正常)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return create_user
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return update_user
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取人员级别id[0:无 ;其他表示对应级别id]
     *
     * @return level_id - 人员级别id[0:无 ;其他表示对应级别id]
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * 设置人员级别id[0:无 ;其他表示对应级别id]
     *
     * @param levelId 人员级别id[0:无 ;其他表示对应级别id]
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * 获取CRM客户端锁定状态(0:被锁定1:未锁定)
     *
     * @return crm_user_lock - CRM客户端锁定状态(0:被锁定1:未锁定)
     */
    public Boolean getCrmUserLock() {
        return crmUserLock;
    }

    /**
     * 设置CRM客户端锁定状态(0:被锁定1:未锁定)
     *
     * @param crmUserLock CRM客户端锁定状态(0:被锁定1:未锁定)
     */
    public void setCrmUserLock(Boolean crmUserLock) {
        this.crmUserLock = crmUserLock;
    }
}