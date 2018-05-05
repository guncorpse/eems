package com.liuxiaobin.eems.search;

import java.io.Serializable;

import com.liuxiaobin.eems.entity.Wage;

/**
 * 工资的查询条件类。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public final class WageSearch implements Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String staffId; // eq员工编号的参数值

  protected java.math.BigDecimal baseAmount; // eq基本金额的参数值

  protected java.lang.Integer jCount; // eq奖励次数的参数值

  protected java.lang.Integer cCount; // eq惩罚次数的参数值

  protected java.math.BigDecimal jAmount; // eq奖励金额的参数值

  protected java.math.BigDecimal cAmount; // eq惩罚金额的参数值

  protected java.math.BigDecimal finallyAmount; // eq最终金额的参数值

  /**
   * 返回工资对象的eq员工编号的参数值的值。
   * @return 工资对象的eq员工编号的参数值的值
   */
  public final java.lang.String getStaffId() {
    return this.staffId;
  }

  /**
   * 设置工资对象的eq员工编号的参数值的值。
   *
   * @param staffId 
   *            eq员工编号的参数值的值
   */
  public final void setStaffId(java.lang.String staffId) {
    this.staffId = staffId;
  }
    
  /**
   * 返回工资对象的eq基本金额的参数值的值。
   * @return 工资对象的eq基本金额的参数值的值
   */
  public final java.math.BigDecimal getBaseAmount() {
    return this.baseAmount;
  }

  /**
   * 设置工资对象的eq基本金额的参数值的值。
   *
   * @param baseAmount 
   *            eq基本金额的参数值的值
   */
  public final void setBaseAmount(java.math.BigDecimal baseAmount) {
    this.baseAmount = baseAmount;
  }
    
  /**
   * 返回工资对象的eq奖励次数的参数值的值。
   * @return 工资对象的eq奖励次数的参数值的值
   */
  public final java.lang.Integer getJCount() {
    return this.jCount;
  }

  /**
   * 设置工资对象的eq奖励次数的参数值的值。
   *
   * @param jCount 
   *            eq奖励次数的参数值的值
   */
  public final void setJCount(java.lang.Integer jCount) {
    this.jCount = jCount;
  }
    
  /**
   * 返回工资对象的eq惩罚次数的参数值的值。
   * @return 工资对象的eq惩罚次数的参数值的值
   */
  public final java.lang.Integer getCCount() {
    return this.cCount;
  }

  /**
   * 设置工资对象的eq惩罚次数的参数值的值。
   *
   * @param cCount 
   *            eq惩罚次数的参数值的值
   */
  public final void setCCount(java.lang.Integer cCount) {
    this.cCount = cCount;
  }
    
  /**
   * 返回工资对象的eq奖励金额的参数值的值。
   * @return 工资对象的eq奖励金额的参数值的值
   */
  public final java.math.BigDecimal getJAmount() {
    return this.jAmount;
  }

  /**
   * 设置工资对象的eq奖励金额的参数值的值。
   *
   * @param jAmount 
   *            eq奖励金额的参数值的值
   */
  public final void setJAmount(java.math.BigDecimal jAmount) {
    this.jAmount = jAmount;
  }
    
  /**
   * 返回工资对象的eq惩罚金额的参数值的值。
   * @return 工资对象的eq惩罚金额的参数值的值
   */
  public final java.math.BigDecimal getCAmount() {
    return this.cAmount;
  }

  /**
   * 设置工资对象的eq惩罚金额的参数值的值。
   *
   * @param cAmount 
   *            eq惩罚金额的参数值的值
   */
  public final void setCAmount(java.math.BigDecimal cAmount) {
    this.cAmount = cAmount;
  }
    
  /**
   * 返回工资对象的eq最终金额的参数值的值。
   * @return 工资对象的eq最终金额的参数值的值
   */
  public final java.math.BigDecimal getFinallyAmount() {
    return this.finallyAmount;
  }

  /**
   * 设置工资对象的eq最终金额的参数值的值。
   *
   * @param finallyAmount 
   *            eq最终金额的参数值的值
   */
  public final void setFinallyAmount(java.math.BigDecimal finallyAmount) {
    this.finallyAmount = finallyAmount;
  }
    

  /**
   * 判断当前工资对象本身是否为空，只要对象的属性全部为null是返回true，否则返回 false。
   * @return true代表当前对象所有属性全部为空，，false代表当前对象并非所有属性都为空
   */
  public boolean selfIsNull() {
    if(this.staffId != null ) { return false; }
    if(this.baseAmount != null ) { return false; }
    if(this.jCount != null ) { return false; }
    if(this.cCount != null ) { return false; }
    if(this.jAmount != null ) { return false; }
    if(this.cAmount != null ) { return false; }
    if(this.finallyAmount != null ) { return false; }
    return true;
  }

  @Override
  public String toString() {
    return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
  }

  public void setEntity(Wage entity) {
    this.staffId = entity.getStaffId();
    this.baseAmount = entity.getBaseAmount();
    this.jCount = entity.getJCount();
    this.cCount = entity.getCCount();
    this.jAmount = entity.getJAmount();
    this.cAmount = entity.getCAmount();
    this.finallyAmount = entity.getFinallyAmount();
  }
}