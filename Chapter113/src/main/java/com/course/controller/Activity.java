package com.course.controller;


import org.mybatis.dao.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sh_activity
 *
 * @mbg.generated do_not_delete_during_merge Tue May 15 11:14:15 CST 2018
 */
@Table("bs_activity")
public class Activity implements Serializable {
    private Integer id;

    private Integer sort;

    private String activityName;

    private String activityImg;

    private String activityUrl;

    private Date startTime;

    private Integer dayNum;

    private Integer status;

    private Date createTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 1L;

    /**
     *
     *
     * @return sh_activity.id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 排序号
     *
     * @return sh_activity.sort 排序号
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序号
     *
     * @param sort 排序号
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 活动名称
     *
     * @return sh_activity.activity_name 活动名称
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * 活动名称
     *
     * @param activityName 活动名称
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    /**
     * 活动图片
     *
     * @return sh_activity.activity_img 活动图片
     */
    public String getActivityImg() {
        return activityImg;
    }

    /**
     * 活动图片
     *
     * @param activityImg 活动图片
     */
    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg == null ? null : activityImg.trim();
    }

    /**
     * 活动链接
     *
     * @return sh_activity.activity_url 活动链接
     */
    public String getActivityUrl() {
        return activityUrl;
    }

    /**
     * 活动链接
     *
     * @param activityUrl 活动链接
     */
    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl == null ? null : activityUrl.trim();
    }

    /**
     * 生效时间
     *
     * @return sh_activity.start_time 生效时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 生效时间
     *
     * @param startTime 生效时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 有效期天数。天数为0时长期有效
     *
     * @return sh_activity.day_num 有效期天数。天数为0时长期有效
     */
    public Integer getDayNum() {
        return dayNum;
    }

    /**
     * 有效期天数。天数为0时长期有效
     *
     * @param dayNum 有效期天数。天数为0时长期有效
     */
    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    /**
     * 0在线、1已下线、2暂停、3删除
     *
     * @return sh_activity.status 0在线、1已下线、2暂停、3删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0在线、1已下线、2暂停、3删除
     *
     * @param status 0在线、1已下线、2暂停、3删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     *
     * @return sh_activity.create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *
     * @return sh_activity.modified_time
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     *
     *
     * @param modifiedTime
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }


}

