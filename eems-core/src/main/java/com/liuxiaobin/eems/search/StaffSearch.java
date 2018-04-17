/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.search;

import java.io.Serializable;
import com.liuxiaobin.eems.entity.Staff;

/**
 * 员工的查询条件类。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public final class StaffSearch implements Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String name; // eq员工名称的参数值

  protected java.lang.String account; // eq员工账号的参数值

  protected java.lang.String password; // eq员工密码的参数值

  protected java.lang.String phonen; // eq员工电话的参数值

  protected java.lang.String address; // eq员工地址的参数值

  protected java.lang.String wechatn; // eq员工微信的参数值

  protected java.lang.String qqn; // eq员工QQ的参数值

  /**
   * 返回员工对象的eq员工名称的参数值的值。
   * @return 员工对象的eq员工名称的参数值的值
   */
  public final java.lang.String getName() {
    return this.name;
  }

  /**
   * 设置员工对象的eq员工名称的参数值的值。
   *
   * @param name 
   *            eq员工名称的参数值的值
   */
  public final void setName(java.lang.String name) {
    this.name = name;
  }
    
  /**
   * 返回员工对象的eq员工账号的参数值的值。
   * @return 员工对象的eq员工账号的参数值的值
   */
  public final java.lang.String getAccount() {
    return this.account;
  }

  /**
   * 设置员工对象的eq员工账号的参数值的值。
   *
   * @param account 
   *            eq员工账号的参数值的值
   */
  public final void setAccount(java.lang.String account) {
    this.account = account;
  }
    
  /**
   * 返回员工对象的eq员工密码的参数值的值。
   * @return 员工对象的eq员工密码的参数值的值
   */
  public final java.lang.String getPassword() {
    return this.password;
  }

  /**
   * 设置员工对象的eq员工密码的参数值的值。
   *
   * @param password 
   *            eq员工密码的参数值的值
   */
  public final void setPassword(java.lang.String password) {
    this.password = password;
  }
    
  /**
   * 返回员工对象的eq员工电话的参数值的值。
   * @return 员工对象的eq员工电话的参数值的值
   */
  public final java.lang.String getPhonen() {
    return this.phonen;
  }

  /**
   * 设置员工对象的eq员工电话的参数值的值。
   *
   * @param phonen 
   *            eq员工电话的参数值的值
   */
  public final void setPhonen(java.lang.String phonen) {
    this.phonen = phonen;
  }
    
  /**
   * 返回员工对象的eq员工地址的参数值的值。
   * @return 员工对象的eq员工地址的参数值的值
   */
  public final java.lang.String getAddress() {
    return this.address;
  }

  /**
   * 设置员工对象的eq员工地址的参数值的值。
   *
   * @param address 
   *            eq员工地址的参数值的值
   */
  public final void setAddress(java.lang.String address) {
    this.address = address;
  }
    
  /**
   * 返回员工对象的eq员工微信的参数值的值。
   * @return 员工对象的eq员工微信的参数值的值
   */
  public final java.lang.String getWechatn() {
    return this.wechatn;
  }

  /**
   * 设置员工对象的eq员工微信的参数值的值。
   *
   * @param wechatn 
   *            eq员工微信的参数值的值
   */
  public final void setWechatn(java.lang.String wechatn) {
    this.wechatn = wechatn;
  }
    
  /**
   * 返回员工对象的eq员工QQ的参数值的值。
   * @return 员工对象的eq员工QQ的参数值的值
   */
  public final java.lang.String getQqn() {
    return this.qqn;
  }

  /**
   * 设置员工对象的eq员工QQ的参数值的值。
   *
   * @param qqn 
   *            eq员工QQ的参数值的值
   */
  public final void setQqn(java.lang.String qqn) {
    this.qqn = qqn;
  }
    

  /**
   * 判断当前员工对象本身是否为空，只要对象的属性全部为null是返回true，否则返回 false。
   * @return true代表当前对象所有属性全部为空，，false代表当前对象并非所有属性都为空
   */
  public boolean selfIsNull() {
    if(this.name != null ) { return false; }
    if(this.account != null ) { return false; }
    if(this.password != null ) { return false; }
    if(this.phonen != null ) { return false; }
    if(this.address != null ) { return false; }
    if(this.wechatn != null ) { return false; }
    if(this.qqn != null ) { return false; }
    return true;
  }

  @Override
  public String toString() {
    return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
  }

  public void setEntity(Staff entity) {
    this.name = entity.getName();
    this.account = entity.getAccount();
    this.password = entity.getPassword();
    this.phonen = entity.getPhonen();
    this.address = entity.getAddress();
    this.wechatn = entity.getWechatn();
    this.qqn = entity.getQqn();
  }
}