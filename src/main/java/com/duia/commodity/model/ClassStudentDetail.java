package com.duia.commodity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "class_student_detail")
public class ClassStudentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String name;

    private Date birthdate;

    private Integer sex;

    private String province;

    private String city;

    private String degree;

    private String profession;

    private String level;

    private String isexam;

    @Column(name = "exam_date")
    private Date examDate;

    private String istrain;

    @Column(name = "train_org_name")
    private String trainOrgName;

    @Column(name = "train_price")
    private Double trainPrice;

    private String mobile;

    @Column(name = "qq_no")
    private String qqNo;

    @Column(name = "qq_name")
    private String qqName;

    private String email;

    @Column(name = "marital_status")
    private String maritalStatus;

    private String child;

    private String income;

    private String salary;

    @Column(name = "net_year")
    private String netYear;

    @Column(name = "shop_time")
    private String shopTime;

    @Column(name = "net_time")
    private String netTime;

    @Column(name = "shop_payment")
    private String shopPayment;

    @Column(name = "edu_payment")
    private String eduPayment;

    private String reason;

    @Column(name = "yx_account")
    private String yxAccount;

    @Column(name = "yx_token")
    private String yxToken;

    @Column(name = "yx_open")
    private Integer yxOpen;

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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return degree
     */
    public String getDegree() {
        return degree;
    }

    /**
     * @param degree
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * @return profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return isexam
     */
    public String getIsexam() {
        return isexam;
    }

    /**
     * @param isexam
     */
    public void setIsexam(String isexam) {
        this.isexam = isexam;
    }

    /**
     * @return exam_date
     */
    public Date getExamDate() {
        return examDate;
    }

    /**
     * @param examDate
     */
    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    /**
     * @return istrain
     */
    public String getIstrain() {
        return istrain;
    }

    /**
     * @param istrain
     */
    public void setIstrain(String istrain) {
        this.istrain = istrain;
    }

    /**
     * @return train_org_name
     */
    public String getTrainOrgName() {
        return trainOrgName;
    }

    /**
     * @param trainOrgName
     */
    public void setTrainOrgName(String trainOrgName) {
        this.trainOrgName = trainOrgName;
    }

    /**
     * @return train_price
     */
    public Double getTrainPrice() {
        return trainPrice;
    }

    /**
     * @param trainPrice
     */
    public void setTrainPrice(Double trainPrice) {
        this.trainPrice = trainPrice;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return qq_no
     */
    public String getQqNo() {
        return qqNo;
    }

    /**
     * @param qqNo
     */
    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    /**
     * @return qq_name
     */
    public String getQqName() {
        return qqName;
    }

    /**
     * @param qqName
     */
    public void setQqName(String qqName) {
        this.qqName = qqName;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return marital_status
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return child
     */
    public String getChild() {
        return child;
    }

    /**
     * @param child
     */
    public void setChild(String child) {
        this.child = child;
    }

    /**
     * @return income
     */
    public String getIncome() {
        return income;
    }

    /**
     * @param income
     */
    public void setIncome(String income) {
        this.income = income;
    }

    /**
     * @return salary
     */
    public String getSalary() {
        return salary;
    }

    /**
     * @param salary
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

    /**
     * @return net_year
     */
    public String getNetYear() {
        return netYear;
    }

    /**
     * @param netYear
     */
    public void setNetYear(String netYear) {
        this.netYear = netYear;
    }

    /**
     * @return shop_time
     */
    public String getShopTime() {
        return shopTime;
    }

    /**
     * @param shopTime
     */
    public void setShopTime(String shopTime) {
        this.shopTime = shopTime;
    }

    /**
     * @return net_time
     */
    public String getNetTime() {
        return netTime;
    }

    /**
     * @param netTime
     */
    public void setNetTime(String netTime) {
        this.netTime = netTime;
    }

    /**
     * @return shop_payment
     */
    public String getShopPayment() {
        return shopPayment;
    }

    /**
     * @param shopPayment
     */
    public void setShopPayment(String shopPayment) {
        this.shopPayment = shopPayment;
    }

    /**
     * @return edu_payment
     */
    public String getEduPayment() {
        return eduPayment;
    }

    /**
     * @param eduPayment
     */
    public void setEduPayment(String eduPayment) {
        this.eduPayment = eduPayment;
    }

    /**
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return yx_account
     */
    public String getYxAccount() {
        return yxAccount;
    }

    /**
     * @param yxAccount
     */
    public void setYxAccount(String yxAccount) {
        this.yxAccount = yxAccount;
    }

    /**
     * @return yx_token
     */
    public String getYxToken() {
        return yxToken;
    }

    /**
     * @param yxToken
     */
    public void setYxToken(String yxToken) {
        this.yxToken = yxToken;
    }

    /**
     * @return yx_open
     */
    public Integer getYxOpen() {
        return yxOpen;
    }

    /**
     * @param yxOpen
     */
    public void setYxOpen(Integer yxOpen) {
        this.yxOpen = yxOpen;
    }
}