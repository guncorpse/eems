package com.liuxiaobin.eems.service.implement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.JcRecording;
import com.liuxiaobin.eems.entity.Staff;
import com.liuxiaobin.eems.entity.User;
import com.liuxiaobin.eems.entity.Wage;
import com.liuxiaobin.eems.persistent.IJcRecordingPersistent;
import com.liuxiaobin.eems.persistent.IStaffPersistent;
import com.liuxiaobin.eems.persistent.IUserPersistent;
import com.liuxiaobin.eems.persistent.IWagePersistent;
import com.liuxiaobin.eems.search.JcRecordingSearch;
import com.liuxiaobin.eems.search.UserSearch;
import com.liuxiaobin.eems.search.WageSearch;
import com.liuxiaobin.eems.service.IBusinessService;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.nmxpsoft.base.commons.utilities.StringUtilities;

/**
 * 该类是以下对象操作的业务具休实现。
 * 奖惩记录
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Service("com.liuxiaobin.eems.BusinessService")
@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = java.lang.Exception.class)
public class BusinessServiceImpl implements IBusinessService {
  
  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(BusinessServiceImpl.class);
  
  @javax.annotation.Resource(name = "com.liuxiaobin.eems.JcRecordingPersistent")
  private IJcRecordingPersistent jcRecordingPersistent;
  
  @javax.annotation.Resource(name = "com.liuxiaobin.eems.StaffPersistent")
  private IStaffPersistent staffPersistent;
  
  @javax.annotation.Resource(name = "com.liuxiaobin.eems.UserPersistent")
  private IUserPersistent userPersistent;
  
  @javax.annotation.Resource(name = "com.liuxiaobin.eems.WagePersistent")
  private IWagePersistent wagePersistent;

  @Override
  public User login(User user) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call BusinessService.login ");
      log.debug("parameter user is : " + user);
    }
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      UserSearch userSearch = new UserSearch();
      userSearch.setAccount(user.getAccount());
      userSearch.setPassword(user.getPassword());
      
      Collection<User> users = userPersistent.searchUser(userSearch);
      if(users.isEmpty()) {
        throw EemsException.getException(EemsException.FW_ERROR, " 用户名或密码错误 " );
      }
      return users.iterator().next();
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

  private Wage calculationWage(Staff staff) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call BusinessService.calculationWage ");
      log.debug("parameter staff is : " + staff);
    }
    try {
      if (staff == null || staff.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
      }
      
      Wage wage = new Wage();
      wage.setBaseAmount(staff.getBaseAmount());
      wage.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
      wage.setCAmount(new BigDecimal(0));
      wage.setCCount(0);
      wage.setFinallyAmount(new BigDecimal(0));
      wage.setJAmount(new BigDecimal(0));
      wage.setJCount(0);
      wage.setStaffId(staff.getStaffId());
      wage.setWageId(StringUtilities.getUUID());
      
      JcRecordingSearch jcRecordingSearch = new JcRecordingSearch();
      jcRecordingSearch.setStaffId(staff.getStaffId());
      Collection<JcRecording> jcRecordings = jcRecordingPersistent.searchJcRecording(jcRecordingSearch);
      if(jcRecordings.isEmpty()) {
        wage.setFinallyAmount(staff.getBaseAmount());
        return wage;
      }
      
      for(JcRecording j : jcRecordings) {
        if(j.getJcType().equals("j")) {
          wage.setJCount(wage.getJCount() + 1);
          wage.setJAmount(wage.getJAmount().add(j.getAmount()));
        }else {
          wage.setCCount(wage.getCCount() + 1);
          wage.setCAmount(wage.getCAmount().add(j.getAmount()));
        }
      }
      wage.setFinallyAmount(wage.getBaseAmount().add(wage.getJAmount()).subtract(wage.getCAmount()));
      return wage;
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

  private Collection<Wage> calculationWages(Collection<Staff> staffs) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call BusinessService.calculationWages ");
      log.debug("parameter staffs is : " + staffs);
    }
    Collection<Wage> wages = new ArrayList<>();
    try {
      for (Staff staff : staffs) {
        wages.add(this.calculationWage(staff));
      }
      if(wagePersistent.getCountWage(new WageSearch()) > 0) {
        wagePersistent.batchRemoveWage(wagePersistent.getAllWage());
      }
      wagePersistent.batchSaveWage(wages);
      return wagePersistent.getAllWage();
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

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public Collection<Wage> clearWages() throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call BusinessService.clearWages ");
    }
    try {
      return this.calculationWages(staffPersistent.getAllStaff());
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
