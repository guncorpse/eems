/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.search;

import java.io.Serializable;
import com.liuxiaobin.eems.entity.JcRecording;

/**
 * 奖惩记录的查询条件类。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public final class JcRecordingSearch implements Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String staffId; // eq员工编号的参数值

  protected java.lang.String jcType; // eq奖惩类型的参数值

  protected java.math.BigDecimal amount; // eq奖惩金额的参数值

  /**
   * 返回奖惩记录对象的eq员工编号的参数值的值。
   * @return 奖惩记录对象的eq员工编号的参数值的值
   */
  public final java.lang.String getStaffId() {
    return this.staffId;
  }

  /**
   * 设置奖惩记录对象的eq员工编号的参数值的值。
   *
   * @param staffId 
   *            eq员工编号的参数值的值
   */
  public final void setStaffId(java.lang.String staffId) {
    this.staffId = staffId;
  }
    
  /**
   * 返回奖惩记录对象的eq奖惩类型的参数值的值。
   * @return 奖惩记录对象的eq奖惩类型的参数值的值
   */
  public final java.lang.String getJcType() {
    return this.jcType;
  }

  /**
   * 设置奖惩记录对象的eq奖惩类型的参数值的值。
   *
   * @param jcType 
   *            eq奖惩类型的参数值的值
   */
  public final void setJcType(java.lang.String jcType) {
    this.jcType = jcType;
  }
    
  /**
   * 返回奖惩记录对象的eq奖惩金额的参数值的值。
   * @return 奖惩记录对象的eq奖惩金额的参数值的值
   */
  public final java.math.BigDecimal getAmount() {
    return this.amount;
  }

  /**
   * 设置奖惩记录对象的eq奖惩金额的参数值的值。
   *
   * @param amount 
   *            eq奖惩金额的参数值的值
   */
  public final void setAmount(java.math.BigDecimal amount) {
    this.amount = amount;
  }
    

  /**
   * 判断当前奖惩记录对象本身是否为空，只要对象的属性全部为null是返回true，否则返回 false。
   * @return true代表当前对象所有属性全部为空，，false代表当前对象并非所有属性都为空
   */
  public boolean selfIsNull() {
    if(this.staffId != null ) { return false; }
    if(this.jcType != null ) { return false; }
    if(this.amount != null ) { return false; }
    return true;
  }

  @Override
  public String toString() {
    return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
  }

  public void setEntity(JcRecording entity) {
    this.staffId = entity.getStaffId();
    this.jcType = entity.getJcType();
    this.amount = entity.getAmount();
  }
}