package com.duia.commodity.common.dto;

/**
 * Created by duia on 2018/7/9.
 */
public class BookCommidityDTO {
    private Integer id;//商品id
    private Integer sku;
    private Double price;//商品价格
    private String title;//商品名称
    private String subtitle;//商品副标题
    private String coverUrl;//商品封面

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public String toString() {
        return "BookCommidityDTO{" +
                "id=" + id +
                ", sku=" + sku +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                '}';
    }
}

