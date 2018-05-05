package com.liuxiaobin.eems.entity;

import com.nmxpsoft.base.commons.utilities.PropertyUtilities;

/**
 * 奖惩记录的实体类，关联的表名为EEMS_JC_RECORDING。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public class JcRecording implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String recordingId; // 记录编号

  protected java.lang.String staffId; // 员工编号

  protected java.lang.String jcType; // 奖惩类型

  protected java.math.BigDecimal amount; // 奖惩金额

  protected java.lang.Long createTimestamps; // 创建时间

  // 虚拟列
  protected java.lang.String staffName; // 员工名称

  protected java.lang.String staffAccount; // 员工账号

  /**
   * 返回奖惩记录对象的记录编号的值。
   * @return 奖惩记录对象的记录编号的值
   */
  public final java.lang.String getRecordingId() {
    return this.recordingId;
  }

  /**
   * 设置奖惩记录对象的记录编号的值。
   *
   * @param recordingId 
   *            记录编号的值
   */
  public final void setRecordingId(java.lang.String recordingId) {
    this.recordingId = recordingId;
  }

  /**
   * 返回奖惩记录对象的员工编号的值。
   * @return 奖惩记录对象的员工编号的值
   */
  public final java.lang.String getStaffId() {
    return this.staffId;
  }

  /**
   * 设置奖惩记录对象的员工编号的值。
   *
   * @param staffId 
   *            员工编号的值
   */
  public final void setStaffId(java.lang.String staffId) {
    this.staffId = staffId;
  }

  /**
   * 返回奖惩记录对象的奖惩类型的值。
   * @return 奖惩记录对象的奖惩类型的值
   */
  public final java.lang.String getJcType() {
    return this.jcType;
  }

  /**
   * 设置奖惩记录对象的奖惩类型的值。
   *
   * @param jcType 
   *            奖惩类型的值
   */
  public final void setJcType(java.lang.String jcType) {
    this.jcType = jcType;
  }

  /**
   * 返回奖惩记录对象的奖惩金额的值。
   * @return 奖惩记录对象的奖惩金额的值
   */
  public final java.math.BigDecimal getAmount() {
    return this.amount;
  }

  /**
   * 设置奖惩记录对象的奖惩金额的值。
   *
   * @param amount 
   *            奖惩金额的值
   */
  public final void setAmount(java.math.BigDecimal amount) {
    this.amount = amount;
  }

  /**
   * 返回奖惩记录对象的创建时间的值。
   * @return 奖惩记录对象的创建时间的值
   */
  public final java.lang.Long getCreateTimestamps() {
    return this.createTimestamps;
  }

  /**
   * 设置奖惩记录对象的创建时间的值。
   *
   * @param createTimestamps 
   *            创建时间的值
   */
  public final void setCreateTimestamps(java.lang.Long createTimestamps) {
    this.createTimestamps = createTimestamps;
  }

  /**
   * 返回奖惩记录对象的员工名称的值。
   * @return 奖惩记录对象的员工名称的值
   */
  public final java.lang.String getStaffName() {
    return this.staffName;
  }

  /**
   * 设置奖惩记录对象的员工名称的值。
   *
   * @param staffName 
   *            员工名称的值
   */
  public final void setStaffName(java.lang.String staffName) {
    this.staffName = staffName;
  }

  /**
   * 返回奖惩记录对象的员工账号的值。
   * @return 奖惩记录对象的员工账号的值
   */
  public final java.lang.String getStaffAccount() {
    return this.staffAccount;
  }

  /**
   * 设置奖惩记录对象的员工账号的值。
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
   * 判断当前奖惩记录对象本身是否为空，只要对象的属性全部为null是返回true，否则返回 false。
   * @return true代表当前对象所有属性全部为空，，false代表当前对象并非所有属性都为空
   */
  public boolean selfIsNull() {
    if(this.recordingId != null ) { return false; }
    if(this.staffId != null ) { return false; }
    if(this.jcType != null ) { return false; }
    if(this.amount != null ) { return false; }
    if(this.createTimestamps != null ) { return false; }
    return true;
  }

  /**
   * 检查奖惩记录对象与目标奖惩记录对象的逻辑版本是否一致。
   *
   * @param entity
   *            要对比的奖惩记录对象。
   * @return true代表两个对象的版本一致，false代表两个对象的版本不一致
   */
  public boolean checkVersion(JcRecording entity){
    if (this == entity) return true;
    if (entity == null || getClass() != entity.getClass()) return false;
    return true;
  }

  /**
   * 将当前奖惩记录对象当中的非空属性，逐一克隆到目标奖惩记录当中。
   *
   * @param entity
   *            将被克隆到的目标奖惩记录对象。
   */
  public void cloneThis(JcRecording entity ) {
    if (!PropertyUtilities.asNullValue(this.recordingId)) 
      entity.recordingId = this.recordingId;
    if (!PropertyUtilities.asNullValue(this.staffId)) 
      entity.staffId = this.staffId;
    if (!PropertyUtilities.asNullValue(this.jcType)) 
      entity.jcType = this.jcType;
    if (this.amount != null) 
      entity.amount = this.amount;
    if (this.createTimestamps != null) 
      entity.createTimestamps = this.createTimestamps;
  }

  /**
   * 将当前奖惩记录对象当中的非空属性，逐一克隆到到目标奖惩记录对象集合当中。
   *
   * @param entities
   *            将被克隆到的目标奖惩记录对象集合。
   */
  public void cloneThisToCollection(java.util.Collection<JcRecording> entities ) {
    for(JcRecording entity : entities) {
      cloneThis(entity);
    }
  }

  /**
   * 将当前奖惩记录对象的主键的值设置为null。
   */
  public void clearPrimaryKeyValue() {
    this.recordingId = null;
  }
}