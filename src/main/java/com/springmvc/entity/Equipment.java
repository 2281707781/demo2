package com.springmvc.entity;


public class Equipment {
  //消防栓编号
  private String id;
  //设备名称
  private String devicename;
  //设备型号
  private String version;
  //设备类型
  private String devicetype;
  //NBIOT/GPRS号码
  private String nbiot_Gprs;
  //位置
  private String positions;
  //设防
  private long state;
  //维修人员
  private String serviceman1;
  //维修人员
  private String serviceman2;
  //设备地址
  private String address;
  //备注
  private String comment;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getDevicename() {
    return devicename;
  }

  public void setDevicename(String devicename) {
    this.devicename = devicename;
  }


  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  public String getDevicetype() {
    return devicetype;
  }

  public void setDevicetype(String devicetype) {
    this.devicetype = devicetype;
  }


  public String getNbiot_Gprs() {
    return nbiot_Gprs;
  }

  public void setNbiot_Gprs(String nbiot_Gprs) {
    this.nbiot_Gprs = nbiot_Gprs;
  }


  public String getPositions() {
    return positions;
  }

  public void setPositions(String positions) {
    this.positions = positions;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public String getServiceman1() {
    return serviceman1;
  }

  public void setServiceman1(String serviceman1) {
    this.serviceman1 = serviceman1;
  }


  public String getServiceman2() {
    return serviceman2;
  }

  public void setServiceman2(String serviceman2) {
    this.serviceman2 = serviceman2;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "Equipment{" +
            "id='" + id + '\'' +
            ", devicename='" + devicename + '\'' +
            ", version='" + version + '\'' +
            ", devicetype='" + devicetype + '\'' +
            ", nbiot_Gprs='" + nbiot_Gprs + '\'' +
            ", positions='" + positions + '\'' +
            ", state=" + state +
            ", serviceman1='" + serviceman1 + '\'' +
            ", serviceman2='" + serviceman2 + '\'' +
            ", address='" + address + '\'' +
            ", comment='" + comment + '\'' +
            '}';
  }
}
