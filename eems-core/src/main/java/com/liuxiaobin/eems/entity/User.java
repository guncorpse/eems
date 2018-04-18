/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.entity;

import com.nmxpsoft.base.commons.utilities.PropertyUtilities;

/**
 * 用户的实体类，关联的表名为FCS_USER。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public class User implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String userId; // 用户编号

  protected java.lang.String nickname; // 用户昵称

  protected java.lang.String account; // 用户账号

  protected java.lang.String password; // 用户密码

  protected java.lang.Long createTimestamps; // 创建时间

  /**
   * 返回用户对象的用户编号的值。
   * @return 用户对象的用户编号的值
   */
  public final java.lang.String getUserId() {
    return this.userId;
  }

  /**
   * 设置用户对象的用户编号的值。
   *
   * @param userId 
   *            用户编号的值
   */
  public final void setUserId(java.lang.String userId) {
    this.userId = userId;
  }

  /**
   * 返回用户对象的用户昵称的值。
   * @return 用户对象的用户昵称的值
   */
  public final java.lang.String getNickname() {
    return this.nickname;
  }

  /**
   * 设置用户对象的用户昵称的值。
   *
   * @param nickname 
   *            用户昵称的值
   */
  public final void setNickname(java.lang.String nickname) {
    this.nickname = nickname;
  }

  /**
   * 返回用户对象的用户账号的值。
   * @return 用户对象的用户账号的值
   */
  public final java.lang.String getAccount() {
    return this.account;
  }

  /**
   * 设置用户对象的用户账号的值。
   *
   * @param account 
   *            用户账号的值
   */
  public final void setAccount(java.lang.String account) {
    this.account = account;
  }

  /**
   * 返回用户对象的用户密码的值。
   * @return 用户对象的用户密码的值
   */
  public final java.lang.String getPassword() {
    return this.password;
  }

  /**
   * 设置用户对象的用户密码的值。
   *
   * @param password 
   *            用户密码的值
   */
  public final void setPassword(java.lang.String password) {
    this.password = password;
  }

  /**
   * 返回用户对象的创建时间的值。
   * @return 用户对象的创建时间的值
   */
  public final java.lang.Long getCreateTimestamps() {
    return this.createTimestamps;
  }

  /**
   * 设置用户对象的创建时间的值。
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
   * 判断当前用户对象本身是否为空，只要对象的属性全部为null是返回true，否则返回 false。
   * @return true代表当前对象所有属性全部为空，，false代表当前对象并非所有属性都为空
   */
  public boolean selfIsNull() {
    if(this.userId != null ) { return false; }
    if(this.nickname != null ) { return false; }
    if(this.account != null ) { return false; }
    if(this.password != null ) { return false; }
    if(this.createTimestamps != null ) { return false; }
    return true;
  }

  /**
   * 检查用户对象与目标用户对象的逻辑版本是否一致。
   *
   * @param entity
   *            要对比的用户对象。
   * @return true代表两个对象的版本一致，false代表两个对象的版本不一致
   */
  public boolean checkVersion(User entity){
    if (this == entity) return true;
    if (entity == null || getClass() != entity.getClass()) return false;
    return true;
  }

  /**
   * 将当前用户对象当中的非空属性，逐一克隆到目标用户当中。
   *
   * @param entity
   *            将被克隆到的目标用户对象。
   */
  public void cloneThis(User entity ) {
    if (!PropertyUtilities.asNullValue(this.userId)) 
      entity.userId = this.userId;
    if (!PropertyUtilities.asNullValue(this.nickname)) 
      entity.nickname = this.nickname;
    if (!PropertyUtilities.asNullValue(this.account)) 
      entity.account = this.account;
    if (!PropertyUtilities.asNullValue(this.password)) 
      entity.password = this.password;
    if (this.createTimestamps != null) 
      entity.createTimestamps = this.createTimestamps;
  }

  /**
   * 将当前用户对象当中的非空属性，逐一克隆到到目标用户对象集合当中。
   *
   * @param entities
   *            将被克隆到的目标用户对象集合。
   */
  public void cloneThisToCollection(java.util.Collection<User> entities ) {
    for(User entity : entities) {
      cloneThis(entity);
    }
  }

  /**
   * 将当前用户对象的主键的值设置为null。
   */
  public void clearPrimaryKeyValue() {
    this.userId = null;
  }
}