package com.duia.commodity.common.dto;

import com.duia.commodity.model.CommodityPromotion;
import com.duia.enums.Status;
import com.duia.enums.StudyPackage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CommodityBaseDTO {
    // 商品ID
    private Long comId;
    // 商品名称
    private String name;
    // 班型ID
    private Long classTypeId;
    // web封面图
    private String webImg;
    // App封面图
    private String appImg;
    // 销售价
    private Double costPrice;
    // 一类价格
    private Double firstPrice;
    // 二类价格
    private Double secondPrice;
    // 原价
    private Double realpayPrice;
    // 学习包价格
    private Double studyPackPrice;
    // 学习包 1:没有  2:有
    private Integer hasStudyPack;
    // 教材类型[0:普通,1:区分初高中]
    private Integer bookType;
    // 教材选中类型[0:普通,1:初中,2:高中]
    private Integer bookTypeChecked;
    // 保险
    private InsuranceDTO insurance;
    // 协议 icon
    private List<Integer> agreements;
    //活动标记
    private Integer promotionStatus;
    // 活动ID
    private Long promotionId;
    //保险配置状态
    private Integer insuranceStatus;
    // 班型类型[0:系统班,1:专题课]
    private Integer courseType;
    // 质保期类型[0:普通,1:1类,2:2类]
    private Integer guaType;
    //多类型质保期[0:普通,1:多类型]
    private Integer guaMul;
    // 零元购标识
    private Integer zeroStatus;


    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Long classTypeId) {
        this.classTypeId = classTypeId;
    }

    public String getWebImg() {
        return webImg;
    }

    public void setWebImg(String webImg) {
        this.webImg = webImg;
    }

    public String getAppImg() {
        return appImg;
    }

    public void setAppImg(String appImg) {
        this.appImg = appImg;
    }

    public InsuranceDTO getInsurance() {
        return insurance;
    }

    public void setInsurance(InsuranceDTO insurance) {
        this.insurance = insurance;
    }

    public List<Integer> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<Integer> agreements) {
        this.agreements = agreements;
    }

    public Integer getHasStudyPack() {
        return hasStudyPack;
    }

    public void setHasStudyPack(Integer hasStudyPack) {
        this.hasStudyPack = hasStudyPack;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice, Double studyPackPrice) {
        setCostPrice(new BigDecimal(null == costPrice ? 0 : costPrice).add(new BigDecimal(null == studyPackPrice ? 0 : studyPackPrice)).doubleValue());
    }

    public void setSecondPrice(Double secondPrice, Double studyPackPrice) {
        setCostPrice(new BigDecimal(null == secondPrice ? 0 : secondPrice).add(new BigDecimal(null == studyPackPrice ? 0 : studyPackPrice)).doubleValue());
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getRealpayPrice() {
        return realpayPrice;
    }


    public void setRealpayPrice(Double realpayPrice, Double studyPackPrice) {

        setRealpayPrice(new BigDecimal(null == realpayPrice ? 0 : realpayPrice).add(new BigDecimal(null == studyPackPrice ? 0 : studyPackPrice)).doubleValue());
    }

    public void setRealpayPrice(Double realpayPrice) {
        this.realpayPrice = realpayPrice;
    }

    public Double getStudyPackPrice() {
        return studyPackPrice;
    }

    public void setStudyPackPrice(Double studyPackPrice) {
        this.studyPackPrice = studyPackPrice;
    }

    /**
     * @Description:是否含有学习包
     * @Date: 17:35 2018/5/15
     */
    public boolean hasStudyPack() {
        Integer hasStudyPack = getHasStudyPack();
        if (null == hasStudyPack) {
            return false;
        }
        return StudyPackage.VALID.getState() == getHasStudyPack();
    }
    /**
     * @Description:设置商品保险选中状态
     * @Date: 12:50 2018/5/15
     */
    public void setInsuranceChecked(Integer checked) {
        if (null != insurance) {
            insurance.setChecked(checked);
        }
    }
    /**
     * 保险价格(有保险返回保险价格，反正返回0，没有null情况)
     * */
    public Double getInsurancePrice() {
        InsuranceDTO insuranceDTO = getInsurance();
        if (null == insuranceDTO) {
            return 0.0;
        }

        if (Objects.equals(1,insuranceDTO.getChecked())) {
            if (null == insuranceDTO.getPrice() || "".equals(insuranceDTO.getPrice())) {
                return 0.0;
            }
            return insuranceDTO.getPrice();
        }
        return 0.0;
    }

    public Integer getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(Integer promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public void setPromotion(CommodityPromotion commodityPromotion) {
        if (null != commodityPromotion && commodityPromotion.getId() != null) {
            setPromotionStatus(commodityPromotion.getType());
            setPromotionId(commodityPromotion.getId());

            Double studyPackPrice = this.getStudyPackPrice();
            if (null == studyPackPrice) {
                studyPackPrice = 0.0;
            }
            Double insurancePrice = this.getInsurancePrice();
            if (null == insurancePrice) {
                insurancePrice = 0.0;
            }
            setCostPrice(new BigDecimal(commodityPromotion.getCostPrice()).add(new BigDecimal(studyPackPrice)).add(new BigDecimal(insurancePrice)).doubleValue());
        } else {
            setPromotionStatus(0);
        }
    }

    public boolean insuranceStatus() {
        if (null == insuranceStatus) {
            return false;
        }
        return insuranceStatus.equals(Status.VALID.getState());
    }

    public void setInsuranceStatus(Integer insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Integer getGuaType() {
        return guaType;
    }

    public void setGuaType(Integer guaType) {
        this.guaType = guaType;
    }

    public Integer getGuaMul() {
        return guaMul;
    }

    public void setGuaMul(Integer guaMul) {
        this.guaMul = guaMul;
    }

    public Double getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Double firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Double getSecondPrice() {
        return secondPrice;
    }

    public void setSecondPrice(Double secondPrice) {
        this.secondPrice = secondPrice;
    }

    public Integer getZeroStatus() {
        return zeroStatus;
    }

    public void setZeroStatus(Integer zeroStatus) {
        this.zeroStatus = zeroStatus;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public Integer getBookTypeChecked() {
        return bookTypeChecked;
    }

    public void setBookTypeChecked(Integer bookTypeChecked) {
        this.bookTypeChecked = bookTypeChecked;
    }
}
