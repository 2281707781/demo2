package com.springmvc.entity;

import jxl.write.DateTime;

import java.util.Date;

/**
 * @author ypl
 * @date 2020/6/7 - 20:55
 **/
public class Alermlog {
    //编号
    private int id;
    //产生时间
    private Date createtime;
    //内容
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Alermlog{" +
                "id=" + id +
                ", createtime=" + createtime +
                ", comment='" + comment + '\'' +
                '}';
    }
}