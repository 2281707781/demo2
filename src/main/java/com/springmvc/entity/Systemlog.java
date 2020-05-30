package com.springmvc.entity;

import java.util.Date;

public class Systemlog {
    //日志编号
    private String id;
    //生成时间
    private Date createtime;
    //用户编号
    private String userid;
    //操作名称
    private String logname;
    //操作内容
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUserid(String userid) {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}