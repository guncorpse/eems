/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.search;

import java.io.Serializable;
import com.liuxiaobin.eems.entity.FcsUser;

/**
 * 用户的查询条件类。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public final class FcsUserSearch implements Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String nickname; // eq用户昵称的参数值

  protected java.lang.String account; // eq用户账号的参数值

  protected java.lang.String password; // eq用户密码的参数值

  /**
   * 返回用户对象的eq用户昵称的参数值的值。
   * @return 用户对象的eq用户昵称的参数值的值
   */
  public final java.lang.String getNickname() {
    return this.nickname;
  }

  /**
   * 设置用户对象的eq用户昵称的参数值的值。
   *
   * @param nickname 
   *            eq用户昵称的参数值的值
   */
  public final void setNickname(java.lang.String nickname) {
    this.nickname = nickname;
  }
    
  /**
   * 返回用户对象的eq用户账号的参数值的值。
   * @return 用户对象的eq用户账号的参数值的值
   */
  public final java.lang.String getAccount() {
    return this.account;
  }

  /**
   * 设置用户对象的eq用户账号的参数值的值。
   *
   * @param account 
   *            eq用户账号的参数值的值
   */
  public final void setAccount(java.lang.String account) {
    this.account = account;
  }
    
  /**
   * 返回用户对象的eq用户密码的参数值的值。
   * @return 用户对象的eq用户密码的参数值的值
   */
  public final java.lang.String getPassword() {
    return this.password;
  }

  /**
   * 设置用户对象的eq用户密码的参数值的值。
   *
   * @param password 
   *            eq用户密码的参数值的值
   */
  public final void setPassword(java.lang.String password) {
    this.password = password;
  }
    

  /**
   * 判断当前用户对象本身是否为空，只要对象的属性全部为null是返回true，否则返回 false。
   * @return true代表当前对象所有属性全部为空，，false代表当前对象并非所有属性都为空
   */
  public boolean selfIsNull() {
    if(this.nickname != null ) { return false; }
    if(this.account != null ) { return false; }
    if(this.password != null ) { return false; }
    return true;
  }

  @Override
  public String toString() {
    return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
  }

  public void setEntity(FcsUser entity) {
    this.nickname = entity.getNickname();
    this.account = entity.getAccount();
    this.password = entity.getPassword();
  }
}