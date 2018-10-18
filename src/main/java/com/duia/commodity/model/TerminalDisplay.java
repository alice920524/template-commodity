package com.duia.commodity.model;

import javax.persistence.*;

@Table(name = "terminal_display")
public class TerminalDisplay {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关联id
     */
    @Column(name = "relation_id")
    private Long relationId;

    /**
     * 关联类型[0:班型,1:套餐,2:组合,3:加价购,4:分享购,5:图书商品,6:图书分享购,7:抢购]
     */
    @Column(name = "relation_type")
    private Integer relationType;

    /**
     * 隶属系列[0:非隶属,1:随身学,2:对啊帮,3:题库]
     */
    private Integer series;

    /**
     * 显示终端[读取appType]
     */
    private Integer terminal;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取关联id
     *
     * @return relation_id - 关联id
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 设置关联id
     *
     * @param relationId 关联id
     */
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取关联类型[0:班型,1:套餐,2:组合,3:加价购,4:分享购,5:图书商品,6:图书分享购,7:抢购]
     *
     * @return relation_type - 关联类型[0:班型,1:套餐,2:组合,3:加价购,4:分享购,5:图书商品,6:图书分享购,7:抢购]
     */
    public Integer getRelationType() {
        return relationType;
    }

    /**
     * 设置关联类型[0:班型,1:套餐,2:组合,3:加价购,4:分享购,5:图书商品,6:图书分享购,7:抢购]
     *
     * @param relationType 关联类型[0:班型,1:套餐,2:组合,3:加价购,4:分享购,5:图书商品,6:图书分享购,7:抢购]
     */
    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    /**
     * 获取隶属系列[0:非隶属,1:随身学,2:对啊帮,3:题库]
     *
     * @return series - 隶属系列[0:非隶属,1:随身学,2:对啊帮,3:题库]
     */
    public Integer getSeries() {
        return series;
    }

    /**
     * 设置隶属系列[0:非隶属,1:随身学,2:对啊帮,3:题库]
     *
     * @param series 隶属系列[0:非隶属,1:随身学,2:对啊帮,3:题库]
     */
    public void setSeries(Integer series) {
        this.series = series;
    }

    /**
     * 获取显示终端[读取appType]
     *
     * @return terminal - 显示终端[读取appType]
     */
    public Integer getTerminal() {
        return terminal;
    }

    /**
     * 设置显示终端[读取appType]
     *
     * @param terminal 显示终端[读取appType]
     */
    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }
}