/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.entity;

import java.math.BigDecimal;

import com.nmxpsoft.base.commons.utilities.PropertyUtilities;

/**
 * 员工的实体类，关联的表名为EEMS_STAFF。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public class Staff implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String staffId; // 员工编号

  protected java.lang.String name; // 员工名称
  
  protected java.lang.String sex; // 员工性别

  protected java.lang.String account; // 员工账号
  
  protected BigDecimal baseAmount; // 员工账号

  protected java.lang.String password; // 员工密码

  protected java.lang.String phonen; // 员工电话

  protected java.lang.String address; // 员工地址

  protected java.lang.String wechatn; // 员工微信

  protected java.lang.String qqn; // 员工QQ

  protected java.lang.Long createTimestamps; // 创建时间

  /**
   * 返回员工对象的员工编号的值。
   * @return 员工对象的员工编号的值
   */
  public final java.lang.String getStaffId() {
    return this.staffId;
  }

  /**
   * 设置员工对象的员工编号的值。
   *
   * @param staffId 
   *            员工编号的值
   */
  public final void setStaffId(java.lang.String staffId) {
    this.staffId = staffId;
  }
  

  public BigDecimal getBaseAmount() {
    return baseAmount;
  }

  public void setBaseAmount(BigDecimal baseAmount) {
    this.baseAmount = baseAmount;
  }

  /**
   * 返回员工对象的员工名称的值。
   * @return 员工对象的员工名称的值
   */
  public final java.lang.String getName() {
    return this.name;
  }

  /**
   * 设置员工对象的员工名称的值。
   *
   * @param name 
   *            员工名称的值
   */
  public final void setName(java.lang.String name) {
    this.name = name;
  }
  
  public java.lang.String getSex() {
    return sex;
  }

  public void setSex(java.lang.String sex) {
    this.sex = sex;
  }

  /**
   * 返回员工对象的员工账号的值。
   * @return 员工对象的员工账号的值
   */
  public final java.lang.String getAccount() {
    return this.account;
  }

  /**
   * 设置员工对象的员工账号的值。
   *
   * @param account 
   *            员工账号的值
   */
  public final void setAccount(java.lang.String account) {
    this.account = account;
  }

  /**
   * 返回员工对象的员工密码的值。
   * @return 员工对象的员工密码的值
   */
  public final java.lang.String getPassword() {
    return this.password;
  }

  /**
   * 设置员工对象的员工密码的值。
   *
   * @param password 
   *            员工密码的值
   */
  public final void setPassword(java.lang.String password) {
    this.password = password;
  }

  /**
   * 返回员工对象的员工电话的值。
   * @return 员工对象的员工电话的值
   */
  public final java.lang.String getPhonen() {
    return this.phonen;
  }

  /**
   * 设置员工对象的员工电话的值。
   *
   * @param phonen 
   *            员工电话的值
   */
  public final void setPhonen(java.lang.String phonen) {
    this.phonen = phonen;
  }

  /**
   * 返回员工对象的员工地址的值。
   * @return 员工对象的员工地址的值
   */
  public final java.lang.String getAddress() {
    return this.address;
  }

  /**
   * 设置员工对象的员工地址的值。
   *
   * @param address 
   *            员工地址的值
   */
  public final void setAddress(java.lang.String address) {
    this.address = address;
  }

  /**
   * 返回员工对象的员工微信的值。
   * @return 员工对象的员工微信的值
   */
  public final java.lang.String getWechatn() {
    return this.wechatn;
  }

  /**
   * 设置员工对象的员工微信的值。
   *
   * @param wechatn 
   *            员工微信的值
   */
  public final void setWechatn(java.lang.String wechatn) {
    this.wechatn = wechatn;
  }

  /**
   * 返回员工对象的员工QQ的值。
   * @return 员工对象的员工QQ的值
   */
  public final java.lang.String getQqn() {
    return this.qqn;
  }

  /**
   * 设置员工对象的员工QQ的值。
   *
   * @param qqn 
   *            员工QQ的值
   */
  public final void setQqn(java.lang.String qqn) {
    this.qqn = qqn;
  }

  /**
   * 返回员工对象的创建时间的值。
   * @return 员工对象的创建时间的值
   */
  public final java.lang.Long getCreateTimestamps() {
    return this.createTimestamps;
  }

  /**
   * 设置员工对象的创建时间的值。
   *
   * @param createTimestamps 
   *            创建时间的值
   */
  public final void setCreateTimestamps(java.lang.Long createTimestamps) {
    this.createTimestamps = createTimestamps;
  }



  @Override
  public String toString() {
    return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
  }

  @Override
  public boolean equals(Object object) {
    return org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(this, object);
  }

  @Override
  public int hashCode() {
    return org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode(this);
  }

  /**
   * 判断当前员工对象本身是否为空，只要对象的属性全部为null是返回true，否则返回 false。
   * @return true代表当前对象所有属性全部为空，，false代表当前对象并非所有属性都为空
   */
  public boolean selfIsNull() {
    if(this.staffId != null ) { return false; }
    if(this.name != null ) { return false; }
    if(this.sex != null ) { return false; }
    if(this.account != null ) { return false; }
    if(this.baseAmount != null ) { return false; }
    if(this.password != null ) { return false; }
    if(this.phonen != null ) { return false; }
    if(this.address != null ) { return false; }
    if(this.wechatn != null ) { return false; }
    if(this.qqn != null ) { return false; }
    if(this.createTimestamps != null ) { return false; }
    return true;
  }

  /**
   * 检查员工对象与目标员工对象的逻辑版本是否一致。
   *
   * @param entity
   *            要对比的员工对象。
   * @return true代表两个对象的版本一致，false代表两个对象的版本不一致
   */
  public boolean checkVersion(Staff entity){
    if (this == entity) return true;
    if (entity == null || getClass() != entity.getClass()) return false;
    return true;
  }

  /**
   * 将当前员工对象当中的非空属性，逐一克隆到目标员工当中。
   *
   * @param entity
   *            将被克隆到的目标员工对象。
   */
  public void cloneThis(Staff entity ) {
    if (!PropertyUtilities.asNullValue(this.staffId)) 
      entity.staffId = this.staffId;
    if (!PropertyUtilities.asNullValue(this.name)) 
      entity.name = this.name;
    if (!PropertyUtilities.asNullValue(this.sex)) 
      entity.sex = this.sex;
    if (!PropertyUtilities.asNullValue(this.account)) 
      entity.account = this.account;
    if (this.baseAmount != null) 
      entity.baseAmount = this.baseAmount;
    if (!PropertyUtilities.asNullValue(this.password)) 
      entity.password = this.password;
    if (!PropertyUtilities.asNullValue(this.phonen)) 
      entity.phonen = this.phonen;
    if (!PropertyUtilities.asNullValue(this.address)) 
      entity.address = this.address;
    if (!PropertyUtilities.asNullValue(this.wechatn)) 
      entity.wechatn = this.wechatn;
    if (!PropertyUtilities.asNullValue(this.qqn)) 
      entity.qqn = this.qqn;
    if (this.createTimestamps != null) 
      entity.createTimestamps = this.createTimestamps;
  }

  /**
   * 将当前员工对象当中的非空属性，逐一克隆到到目标员工对象集合当中。
   *
   * @param entities
   *            将被克隆到的目标员工对象集合。
   */
  public void cloneThisToCollection(java.util.Collection<Staff> entities ) {
    for(Staff entity : entities) {
      cloneThis(entity);
    }
  }

  /**
   * 将当前员工对象的主键的值设置为null。
   */
  public void clearPrimaryKeyValue() {
    this.staffId = null;
  }
}