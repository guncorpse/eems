package com.liuxiaobin.eems.entity;

import com.nmxpsoft.base.commons.utilities.PropertyUtilities;

/**
 * 工资的实体类，关联的表名为EEMS_WAGE。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public class Wage implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String wageId; // 记录编号

  protected java.lang.String staffId; // 员工编号

  protected java.math.BigDecimal baseAmount; // 基本金额

  protected java.lang.Integer jCount; // 奖励次数

  protected java.lang.Integer cCount; // 惩罚次数

  protected java.math.BigDecimal jAmount; // 奖励金额

  protected java.math.BigDecimal cAmount; // 惩罚金额

  protected java.math.BigDecimal finallyAmount; // 最终金额

  protected java.lang.Long createTimestamps; // 创建时间

  // 虚拟列
  protected java.lang.String staffName; // 员工名称

  protected java.lang.String staffAccount; // 员工账号

  /**
   * 返回工资对象的记录编号的值。
   * @return 工资对象的记录编号的值
   */
  public final java.lang.String getWageId() {
    return this.wageId;
  }

  /**
   * 设置工资对象的记录编号的值。
   *
   * @param wageId 
   *            记录编号的值
   */
  public final void setWageId(java.lang.String wageId) {
    this.wageId = wageId;
  }

  /**
   * 返回工资对象的员工编号的值。
   * @return 工资对象的员工编号的值
   */
  public final java.lang.String getStaffId() {
    return this.staffId;
  }

  /**
   * 设置工资对象的员工编号的值。
   *
   * @param staffId 
   *            员工编号的值
   */
  public final void setStaffId(java.lang.String staffId) {
    this.staffId = staffId;
  }

  /**
   * 返回工资对象的基本金额的值。
   * @return 工资对象的基本金额的值
   */
  public final java.math.BigDecimal getBaseAmount() {
    return this.baseAmount;
  }

  /**
   * 设置工资对象的基本金额的值。
   *
   * @param baseAmount 
   *            基本金额的值
   */
  public final void setBaseAmount(java.math.BigDecimal baseAmount) {
    this.baseAmount = baseAmount;
  }

  /**
   * 返回工资对象的奖励次数的值。
   * @return 工资对象的奖励次数的值
   */
  public final java.lang.Integer getJCount() {
    return this.jCount;
  }

  /**
   * 设置工资对象的奖励次数的值。
   *
   * @param jCount 
   *            奖励次数的值
   */
  public final void setJCount(java.lang.Integer jCount) {
    this.jCount = jCount;
  }

  /**
   * 返回工资对象的惩罚次数的值。
   * @return 工资对象的惩罚次数的值
   */
  public final java.lang.Integer getCCount() {
    return this.cCount;
  }

  /**
   * 设置工资对象的惩罚次数的值。
   *
   * @param cCount 
   *            惩罚次数的值
   */
  public final void setCCount(java.lang.Integer cCount) {
    this.cCount = cCount;
  }

  /**
   * 返回工资对象的奖励金额的值。
   * @return 工资对象的奖励金额的值
   */
  public final java.math.BigDecimal getJAmount() {
    return this.jAmount;
  }

  /**
   * 设置工资对象的奖励金额的值。
   *
   * @param jAmount 
   *            奖励金额的值
   */
  public final void setJAmount(java.math.BigDecimal jAmount) {
    this.jAmount = jAmount;
  }

  /**
   * 返回工资对象的惩罚金额的值。
   * @return 工资对象的惩罚金额的值
   */
  public final java.math.BigDecimal getCAmount() {
    return this.cAmount;
  }

  /**
   * 设置工资对象的惩罚金额的值。
   *
   * @param cAmount 
   *            惩罚金额的值
   */
  public final void setCAmount(java.math.BigDecimal cAmount) {
    this.cAmount = cAmount;
  }

  /**
   * 返回工资对象的最终金额的值。
   * @return 工资对象的最终金额的值
   */
  public final java.math.BigDecimal getFinallyAmount() {
    return this.finallyAmount;
  }

  /**
   * 设置工资对象的最终金额的值。
   *
   * @param finallyAmount 
   *            最终金额的值
   */
  public final void setFinallyAmount(java.math.BigDecimal finallyAmount) {
    this.finallyAmount = finallyAmount;
  }

  /**
   * 返回工资对象的创建时间的值。
   * @return 工资对象的创建时间的值
   */
  public final java.lang.Long getCreateTimestamps() {
    return this.createTimestamps;
  }

  /**
   * 设置工资对象的创建时间的值。
   *
   * @param createTimestamps 
   *            创建时间的值
   */
  public final void setCreateTimestamps(java.lang.Long createTimestamps) {
    this.createTimestamps = createTimestamps;
  }

  /**
   * 返回工资对象的员工名称的值。
   * @return 工资对象的员工名称的值
   */
  public final java.lang.String getStaffName() {
    return this.staffName;
  }

  /**
   * 设置工资对象的员工名称的值。
   *
   * @param staffName 
   *            员工名称的值
   */
  public final void setStaffName(java.lang.String staffName) {
    this.staffName = staffName;
  }

  /**
   * 返回工资对象的员工账号的值。
   * @return 工资对象的员工账号的值
   */
  public final java.lang.String getStaffAccount() {
    return this.staffAccount;
  }

  /**
   * 设置工资对象的员工账号的值。
   *
   * @param staffAccount 
   *            员工账号的值
   */
  public final void setStaffAccount(java.lang.String staffAccount) {
    this.staffAccount = staffAccount;
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
   * 判断当前工资对象本身是否为空，只要对象的属性全部为null是返回true，否则返回 false。
   * @return true代表当前对象所有属性全部为空，，false代表当前对象并非所有属性都为空
   */
  public boolean selfIsNull() {
    if(this.wageId != null ) { return false; }
    if(this.staffId != null ) { return false; }
    if(this.baseAmount != null ) { return false; }
    if(this.jCount != null ) { return false; }
    if(this.cCount != null ) { return false; }
    if(this.jAmount != null ) { return false; }
    if(this.cAmount != null ) { return false; }
    if(this.finallyAmount != null ) { return false; }
    if(this.createTimestamps != null ) { return false; }
    return true;
  }

  /**
   * 检查工资对象与目标工资对象的逻辑版本是否一致。
   *
   * @param entity
   *            要对比的工资对象。
   * @return true代表两个对象的版本一致，false代表两个对象的版本不一致
   */
  public boolean checkVersion(Wage entity){
    if (this == entity) return true;
    if (entity == null || getClass() != entity.getClass()) return false;
    return true;
  }

  /**
   * 将当前工资对象当中的非空属性，逐一克隆到目标工资当中。
   *
   * @param entity
   *            将被克隆到的目标工资对象。
   */
  public void cloneThis(Wage entity ) {
    if (!PropertyUtilities.asNullValue(this.wageId)) 
      entity.wageId = this.wageId;
    if (!PropertyUtilities.asNullValue(this.staffId)) 
      entity.staffId = this.staffId;
    if (this.baseAmount != null) 
      entity.baseAmount = this.baseAmount;
    if (this.jCount != null) 
      entity.jCount = this.jCount;
    if (this.cCount != null) 
      entity.cCount = this.cCount;
    if (this.jAmount != null) 
      entity.jAmount = this.jAmount;
    if (this.cAmount != null) 
      entity.cAmount = this.cAmount;
    if (this.finallyAmount != null) 
      entity.finallyAmount = this.finallyAmount;
    if (this.createTimestamps != null) 
      entity.createTimestamps = this.createTimestamps;
  }

  /**
   * 将当前工资对象当中的非空属性，逐一克隆到到目标工资对象集合当中。
   *
   * @param entities
   *            将被克隆到的目标工资对象集合。
   */
  public void cloneThisToCollection(java.util.Collection<Wage> entities ) {
    for(Wage entity : entities) {
      cloneThis(entity);
    }
  }

  /**
   * 将当前工资对象的主键的值设置为null。
   */
  public void clearPrimaryKeyValue() {
    this.wageId = null;
  }
}