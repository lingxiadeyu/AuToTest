package com.course.controller;


import org.mybatis.dao.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table gc_goods
 *
 * @mbg.generated do_not_delete_during_merge Tue Sep 11 09:44:26 CST 2018
 */
@Table("gc_goods")
public class Goods implements Serializable {
    private Long id;

    private String name;

    private String description;

    private String images;

    private String instruction;

    private Integer instructionMode;

    private String attributes;

    private String detail;

    private Long categoryId;

    private Long brandId;

    private Long spuId;

    private Short businessLineId;

    private Short goodsStatus;

    private Integer channelStatus;

    private Date createdTime;

    private Date modifiedTime;

    private Short status;

    private static final long serialVersionUID = 1L;

    /**
     *
     *
     * @return gc_goods.id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商品名称
     *
     * @return gc_goods.name 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 商品描述
     *
     * @return gc_goods.description 商品描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 商品描述
     *
     * @param description 商品描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 商品图片；第一张为主图，其他为轮播图
     *
     * @return gc_goods.images 商品图片；第一张为主图，其他为轮播图
     */
    public String getImages() {
        return images;
    }

    /**
     * 商品图片；第一张为主图，其他为轮播图
     *
     * @param images 商品图片；第一张为主图，其他为轮播图
     */
    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    /**
     * 使用说明
     *
     * @return gc_goods.instruction 使用说明
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * 使用说明
     *
     * @param instruction 使用说明
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction == null ? null : instruction.trim();
    }

    /**
     * 商品说明 模式 0图片模式 1文本模式
     *
     * @return gc_goods.instruction_mode 商品说明 模式 0图片模式 1文本模式
     */
    public Integer getInstructionMode() {
        return instructionMode;
    }

    /**
     * 商品说明 模式 0图片模式 1文本模式
     *
     * @param instructionMode 商品说明 模式 0图片模式 1文本模式
     */
    public void setInstructionMode(Integer instructionMode) {
        this.instructionMode = instructionMode;
    }

    /**
     *
     *
     * @return gc_goods.attributes
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     *
     *
     * @param attributes
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes == null ? null : attributes.trim();
    }

    /**
     * 商品详情
     *
     * @return gc_goods.detail 商品详情
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 商品详情
     *
     * @param detail 商品详情
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     * 类目id
     *
     * @return gc_goods.category_id 类目id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 类目id
     *
     * @param categoryId 类目id
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 品牌id
     *
     * @return gc_goods.brand_id 品牌id
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 品牌id
     *
     * @param brandId 品牌id
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * spu id
     *
     * @return gc_goods.spu_id spu id
     */
    public Long getSpuId() {
        return spuId;
    }

    /**
     * spu id
     *
     * @param spuId spu id
     */
    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    /**
     * 业务线
     *
     * @return gc_goods.business_line_id 业务线
     */
    public Short getBusinessLineId() {
        return businessLineId;
    }

    /**
     * 业务线
     *
     * @param businessLineId 业务线
     */
    public void setBusinessLineId(Short businessLineId) {
        this.businessLineId = businessLineId;
    }

    /**
     * 商品上下架状态 0上架 1下架
     *
     * @return gc_goods.goods_status 商品上下架状态 0上架 1下架
     */
    public Short getGoodsStatus() {
        return goodsStatus;
    }

    /**
     * 商品上下架状态 0上架 1下架
     *
     * @param goodsStatus 商品上下架状态 0上架 1下架
     */
    public void setGoodsStatus(Short goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    /**
     * 渠道标志：0启动渠道关联表，1表示渠道不限
     *
     * @return gc_goods.channel_status 渠道标志：0启动渠道关联表，1表示渠道不限
     */
    public Integer getChannelStatus() {
        return channelStatus;
    }

    /**
     * 渠道标志：0启动渠道关联表，1表示渠道不限
     *
     * @param channelStatus 渠道标志：0启动渠道关联表，1表示渠道不限
     */
    public void setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
    }

    /**
     * 创建时间
     *
     * @return gc_goods.created_time 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 修改时间
     *
     * @return gc_goods.modified_time 修改时间
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 修改时间
     *
     * @param modifiedTime 修改时间
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * 0-正常；1-软删除
     *
     * @return gc_goods.status 0-正常；1-软删除
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 0-正常；1-软删除
     *
     * @param status 0-正常；1-软删除
     */
    public void setStatus(Short status) {
        this.status = status;
    }


}

