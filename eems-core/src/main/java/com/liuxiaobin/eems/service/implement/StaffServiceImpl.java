/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.service.implement;

import java.util.Collection;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.Staff;
import com.liuxiaobin.eems.persistent.IJcRecordingPersistent;
import com.liuxiaobin.eems.persistent.IStaffPersistent;
import com.liuxiaobin.eems.persistent.IWagePersistent;
import com.liuxiaobin.eems.search.JcRecordingSearch;
import com.liuxiaobin.eems.search.StaffSearch;
import com.liuxiaobin.eems.search.WageSearch;
import com.liuxiaobin.eems.service.IJcRecordingService;
import com.liuxiaobin.eems.service.IStaffService;
import com.liuxiaobin.eems.service.IWageService;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.nmxpsoft.base.commons.utilities.StringUtilities;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;

/**
 * 该类是以下对象操作的业务具休实现。
 * 员工
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Service("com.liuxiaobin.eems.StaffService")
@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = java.lang.Exception.class)
public class StaffServiceImpl implements IStaffService {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(StaffServiceImpl.class);

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.StaffPersistent")
  private IStaffPersistent staffPersistent;

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.JcRecordingPersistent")
  private IJcRecordingPersistent jcRecordingPersistent;

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.WagePersistent")
  private IWagePersistent wagePersistent;

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.JcRecordingService")
  private IJcRecordingService jcRecordingService;

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.WageService")
  private IWageService wageService;

  @org.springframework.beans.factory.annotation.Autowired
  protected org.springframework.context.ApplicationEventPublisher publisher;
  // StaffPersistentImpl
  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveStaff(Staff staff) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.saveStaff ");
      log.debug("parameter staff is : " + staff);
    }
    try {
      if (staff == null || staff.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
      }
      staff.clearPrimaryKeyValue();
      staff.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
      if (PropertyUtilities.asNullValue(staff.getStaffId())) {
        staff.setStaffId(StringUtilities.getUUID());
      }
      if (!staffPersistent.isUnique(staff)) {
        throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { staffPersistent.getNotUniqueErrorMessage(staff)});
      }
      staffPersistent.saveStaff(staff);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchSaveStaff(Collection<Staff> staffs) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.batchSaveStaff ");
      log.debug("parameter staffs is : " + staffs);
    }
    try {
      if (PropertyUtilities.isEmpty(staffs)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffs " );
      }
      for(Staff staff : staffs) {
        if(PropertyUtilities.isEmpty(staff) || staff.selfIsNull()) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
        }
        staff.clearPrimaryKeyValue();
        staff.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
        if (PropertyUtilities.asNullValue(staff.getStaffId())) {
          staff.setStaffId(StringUtilities.getUUID());
        }
        if (!staffPersistent.isUnique(staff)) {
          throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { staffPersistent.getNotUniqueErrorMessage(staff)});
        }
      }
      staffPersistent.batchSaveStaff(staffs);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void updateStaff(Staff staff) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.updateStaff ");
      log.debug("parameter staff is : " + staff);
    }
    try {
      if (staff == null || staff.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
      }
      if (PropertyUtilities.asNullValue(staff.getStaffId())) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffId " );
      }
      Staff staffOld = staffPersistent.getStaffByPrimaryKey(staff.getStaffId());
      if (staffOld == null) {
        throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      if (!staffPersistent.isUnique(staff)) {
        throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { staffPersistent.getNotUniqueErrorMessage(staff)});
      }
      staffPersistent.updateStaff(staff);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchUpdateStaff(Collection<Staff> staffs) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.batchUpdateStaff ");
      log.debug("parameter staffs is : " + staffs);
    }
    try {
      if (PropertyUtilities.isEmpty(staffs)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffs " );
      }
      staffPersistent.isUnique(staffs);
      for(Staff staff : staffs) {
        if(PropertyUtilities.isEmpty(staff) || staff.selfIsNull()) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
        }
        if (PropertyUtilities.asNullValue(staff.getStaffId())) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffId " );
        }
        Staff staffOld = staffPersistent.getStaffByPrimaryKey(staff.getStaffId());
        if (staffOld == null) {
          throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        if (!staffPersistent.isUnique(staff)) {
          throw EemsException.getException(EemsException.FW_DATA_CONTENTION_ERROR, new String[] { staffPersistent.getNotUniqueErrorMessage(staff)});
        }
      }
      staffPersistent.batchUpdateStaff(staffs);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void removeStaff(Staff staff) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.removeStaff ");
      log.debug("parameter staff is : " + staff);
    }
    try {
      if (staff == null || staff.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
      }
      java.util.Set<Staff> deleteStaffs = new java.util.LinkedHashSet<>();
      if (!PropertyUtilities.asNullValue(staff.getStaffId())) {
        Staff staffOld = staffPersistent.getStaffByPrimaryKey(staff.getStaffId());
        if (staffOld == null ) {
          throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        deleteStaffs.add(staffOld);
      } else {
        StaffSearch staffSearch = new StaffSearch();
        staffSearch.setEntity(staff);
        deleteStaffs.addAll(staffPersistent.searchStaff(staffSearch));
      }
      if (PropertyUtilities.isNotEmpty(deleteStaffs)) {
        for(Staff deleteStaff : deleteStaffs) {
          JcRecordingSearch cascadeDeleteJcRecordingSearch = new JcRecordingSearch();
          cascadeDeleteJcRecordingSearch.setStaffId(deleteStaff.getStaffId());
          Long count0 = jcRecordingPersistent.getCountJcRecording(cascadeDeleteJcRecordingSearch);
          if (count0 > 0) {
            throw EemsException.getException(EemsException.FW_DATA_IS_USE_ERROR, "奖惩记录");
          }
          WageSearch cascadeDeleteWageSearch = new WageSearch();
          cascadeDeleteWageSearch.setStaffId(deleteStaff.getStaffId());
          Long count1 = wagePersistent.getCountWage(cascadeDeleteWageSearch);
          if (count1 > 0) {
            throw EemsException.getException(EemsException.FW_DATA_IS_USE_ERROR, "工资");
          }
        }
        staffPersistent.batchRemoveStaff(deleteStaffs);
      }
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchRemoveStaff(Collection<Staff> staffs) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.batchRemoveStaff ");
      log.debug("parameter staffs is : " + staffs);
    }
    try {
      if (PropertyUtilities.isEmpty(staffs)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffs " );
      }
      java.util.Set<Staff> deleteStaffs = new java.util.LinkedHashSet<>();
      for(Staff staff : staffs) {
        if (!PropertyUtilities.asNullValue(staff.getStaffId())) {
          Staff staffOld = staffPersistent.getStaffByPrimaryKey(staff.getStaffId());
          if (staffOld == null ) {
            throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          deleteStaffs.add(staffOld);
        } else {
          StaffSearch staffSearch = new StaffSearch();
          staffSearch.setEntity(staff);
          deleteStaffs.addAll(staffPersistent.searchStaff(staffSearch));
        }
      }
      if (PropertyUtilities.isNotEmpty(deleteStaffs)) {
        for(Staff deleteStaff : deleteStaffs) {
          JcRecordingSearch cascadeDeleteJcRecordingSearch = new JcRecordingSearch();
          cascadeDeleteJcRecordingSearch.setStaffId(deleteStaff.getStaffId());
          Long count0 = jcRecordingPersistent.getCountJcRecording(cascadeDeleteJcRecordingSearch);
          if (count0 > 0) {
            throw EemsException.getException(EemsException.FW_DATA_IS_USE_ERROR, "奖惩记录");
          }
          WageSearch cascadeDeleteWageSearch = new WageSearch();
          cascadeDeleteWageSearch.setStaffId(deleteStaff.getStaffId());
          Long count1 = wagePersistent.getCountWage(cascadeDeleteWageSearch);
          if (count1 > 0) {
            throw EemsException.getException(EemsException.FW_DATA_IS_USE_ERROR, "工资");
          }
        }
        staffPersistent.batchRemoveStaff(deleteStaffs);
      }
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Staff getStaffByPrimaryKey(java.lang.String staffId) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.getStaffByPrimaryKey ");
      log.debug("parameter staffId is : " + staffId);
    }
    try {
      if (PropertyUtilities.asNullValue(staffId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffId " );
      }
      return staffPersistent.getStaffByPrimaryKey(staffId);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long getCountStaff(StaffSearch staffSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.getCountStaff ");
      log.debug("parameter staffSearch is : " + staffSearch);
    }
    try {
      return staffPersistent.getCountStaff(staffSearch);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Staff> getAllStaff() throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.getAllStaff ");
    }
    try {
      return staffPersistent.getAllStaff();
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PageRange<Staff> paginationGetAllStaff(PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.paginationGetAllStaff ");
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      return staffPersistent.paginationGetAllStaff(page);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Staff> searchStaff(StaffSearch staffSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.searchStaff ");
      log.debug("parameter staffSearch is : " + staffSearch);
    }
    try {
      return staffPersistent.searchStaff(staffSearch);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PageRange<Staff> paginationSearchStaff(StaffSearch staffSearch, PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffService.paginationSearchStaff ");
      log.debug("parameter staffSearch is : " + staffSearch);
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      return staffPersistent.paginationSearchStaff(staffSearch, page);
    } catch (EemsException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw EemsException.getException(e, EemsException.FW_ERROR, e.getMessage());
    }
  }

}