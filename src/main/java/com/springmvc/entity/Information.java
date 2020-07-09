package com.springmvc.entity;

import java.util.Date;

public class Information {
    //编号
    private Integer id;
    //数据接收时间
    private Date datecreatetime;
    //消防栓编号
    private String equipmentid;
    //水压
    private Integer pressure;
    //倾斜角度
    private Double inclination;
    //阀开状态
    private int state;
    //流量
    private int flowmeter;
    //水温
    private Double temperature;
    //电量
    private Integer battery;

    private String contain1;

    private String contain2;

    private String contain3;

    private Equipment equipment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }

    public String getEquipmentid() {
        return equipmentid;
    }

    public void setEquipmentid(String equipmentid) {
        this.equipmentid = equipmentid;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Double getInclination() {
        return inclination;
    }

    public void setInclination(Double inclination) {
        this.inclination = inclination;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getFlowmeter() {
        return flowmeter;
    }

    public void setFlowmeter(int flowmeter) {
        this.flowmeter = flowmeter;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public String getContain1() {
        return contain1;
    }

    public void setContain1(String contain1) {
        this.contain1 = contain1;
    }

    public String getContain2() {
        return contain2;
    }

    public void setContain2(String contain2) {
        this.contain2 = contain2;
    }

    public String getContain3() {
        return contain3;
    }

    public void setContain3(String contain3) {
        this.contain3 = contain3;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", datecreatetime=" + datecreatetime +
                ", equipmentid='" + equipmentid + '\'' +
                ", pressure=" + pressure +
                ", inclination=" + inclination +
                ", state=" + state +
                ", flowmeter=" + flowmeter +
                ", temperature=" + temperature +
                ", battery=" + battery +
                ", contain1='" + contain1 + '\'' +
                ", contain2='" + contain2 + '\'' +
                ", contain3='" + contain3 + '\'' +
                ", equipment=" + equipment +
                '}';
    }
}