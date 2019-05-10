package com.course.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ElementDetail")
public class ElementDetail {

    @Id
    @GeneratedValue
    private int id;

    private String page_name;//页面名称
    private String element_name;//元素名称
    private Integer indexs;//index值
    private String by_name;
    private String by_resourceid;
    private String by_classname;
    private String by_xpath;
    private String contextdesc;
    private Integer xStartPoint;
    private Integer xEndPoint;
    private Integer yStartPoint;
    private Integer yEndPoint;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public String getElement_name() {
        return element_name;
    }

    public void setElement_name(String element_name) {
        this.element_name = element_name;
    }

    public Integer getIndexs() {
        return indexs;
    }

    public void setIndexs(Integer indexs) {
        this.indexs = indexs;
    }

    public String getBy_name() {
        return by_name;
    }

    public void setBy_name(String by_name) {
        this.by_name = by_name;
    }

    public String getBy_resourceid() {
        return by_resourceid;
    }

    public void setBy_resourceid(String by_resourceid) {
        this.by_resourceid = by_resourceid;
    }

    public String getBy_classname() {
        return by_classname;
    }

    public void setBy_classname(String by_classname) {
        this.by_classname = by_classname;
    }

    public String getBy_xpath() {
        return by_xpath;
    }

    public void setBy_xpath(String by_xpath) {
        this.by_xpath = by_xpath;
    }

    public String getContextdesc() {
        return contextdesc;
    }

    public void setContextdesc(String contextdesc) {
        this.contextdesc = contextdesc;
    }

    public Integer getxStartPoint() {
        return xStartPoint;
    }

    public void setxStartPoint(Integer xStartPoint) {
        this.xStartPoint = xStartPoint;
    }

    public Integer getxEndPoint() {
        return xEndPoint;
    }

    public void setxEndPoint(Integer xEndPoint) {
        this.xEndPoint = xEndPoint;
    }

    public Integer getyStartPoint() {
        return yStartPoint;
    }

    public void setyStartPoint(Integer yStartPoint) {
        this.yStartPoint = yStartPoint;
    }

    public Integer getyEndPoint() {
        return yEndPoint;
    }

    public void setyEndPoint(Integer yEndPoint) {
        this.yEndPoint = yEndPoint;
    }
}
