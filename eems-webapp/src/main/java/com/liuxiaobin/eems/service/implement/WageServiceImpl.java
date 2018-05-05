package com.liuxiaobin.eems.service.implement;

import java.util.Collection;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.Wage;
import com.liuxiaobin.eems.persistent.IWagePersistent;
import com.liuxiaobin.eems.search.WageSearch;
import com.liuxiaobin.eems.service.IWageService;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.nmxpsoft.base.commons.utilities.StringUtilities;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;

/**
 * 该类是以下对象操作的业务具休实现。
 * 工资
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Service("com.liuxiaobin.eems.WageService")
@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = java.lang.Exception.class)
public class WageServiceImpl implements IWageService {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(WageServiceImpl.class);

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.WagePersistent")
  private IWagePersistent wagePersistent;

  @org.springframework.beans.factory.annotation.Autowired
  protected org.springframework.context.ApplicationEventPublisher publisher;
  // WagePersistentImpl
  /**
   * {@inheritDoc}
   */
  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveWage(Wage wage) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.saveWage ");
      log.debug("parameter wage is : " + wage);
    }
    try {
      if (wage == null || wage.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
      }
      wage.clearPrimaryKeyValue();
      wage.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
      if (PropertyUtilities.asNullValue(wage.getWageId())) {
        wage.setWageId(StringUtilities.getUUID());
      }
      wagePersistent.saveWage(wage);
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
  public void batchSaveWage(Collection<Wage> wages) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.batchSaveWage ");
      log.debug("parameter wages is : " + wages);
    }
    try {
      if (PropertyUtilities.isEmpty(wages)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wages " );
      }
      for(Wage wage : wages) {
        if(PropertyUtilities.isEmpty(wage) || wage.selfIsNull()) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
        }
        wage.clearPrimaryKeyValue();
        wage.setCreateTimestamps(PropertyUtilities.currentTimeMillis());
        if (PropertyUtilities.asNullValue(wage.getWageId())) {
          wage.setWageId(StringUtilities.getUUID());
        }
      }
      wagePersistent.batchSaveWage(wages);
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
  public void updateWage(Wage wage) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.updateWage ");
      log.debug("parameter wage is : " + wage);
    }
    try {
      if (wage == null || wage.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
      }
      if (PropertyUtilities.asNullValue(wage.getWageId())) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wageId " );
      }
      Wage wageOld = wagePersistent.getWageByPrimaryKey(wage.getWageId());
      if (wageOld == null) {
        throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      wagePersistent.updateWage(wage);
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
  public void batchUpdateWage(Collection<Wage> wages) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.batchUpdateWage ");
      log.debug("parameter wages is : " + wages);
    }
    try {
      if (PropertyUtilities.isEmpty(wages)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wages " );
      }
      for(Wage wage : wages) {
        if(PropertyUtilities.isEmpty(wage) || wage.selfIsNull()) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
        }
        if (PropertyUtilities.asNullValue(wage.getWageId())) {
          throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wageId " );
        }
        Wage wageOld = wagePersistent.getWageByPrimaryKey(wage.getWageId());
        if (wageOld == null) {
          throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
      }
      wagePersistent.batchUpdateWage(wages);
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
  public void removeWage(Wage wage) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.removeWage ");
      log.debug("parameter wage is : " + wage);
    }
    try {
      if (wage == null || wage.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
      }
      java.util.Set<Wage> deleteWages = new java.util.LinkedHashSet<>();
      if (!PropertyUtilities.asNullValue(wage.getWageId())) {
        Wage wageOld = wagePersistent.getWageByPrimaryKey(wage.getWageId());
        if (wageOld == null ) {
          throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        deleteWages.add(wageOld);
      } else {
        WageSearch wageSearch = new WageSearch();
        wageSearch.setEntity(wage);
        deleteWages.addAll(wagePersistent.searchWage(wageSearch));
      }
      if (PropertyUtilities.isNotEmpty(deleteWages)) {
        wagePersistent.batchRemoveWage(deleteWages);
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
  public void batchRemoveWage(Collection<Wage> wages) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.batchRemoveWage ");
      log.debug("parameter wages is : " + wages);
    }
    try {
      if (PropertyUtilities.isEmpty(wages)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wages " );
      }
      java.util.Set<Wage> deleteWages = new java.util.LinkedHashSet<>();
      for(Wage wage : wages) {
        if (!PropertyUtilities.asNullValue(wage.getWageId())) {
          Wage wageOld = wagePersistent.getWageByPrimaryKey(wage.getWageId());
          if (wageOld == null ) {
            throw EemsException.getException(EemsException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          deleteWages.add(wageOld);
        } else {
          WageSearch wageSearch = new WageSearch();
          wageSearch.setEntity(wage);
          deleteWages.addAll(wagePersistent.searchWage(wageSearch));
        }
      }
      if (PropertyUtilities.isNotEmpty(deleteWages)) {
        wagePersistent.batchRemoveWage(deleteWages);
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
  public Wage getWageByPrimaryKey(java.lang.String wageId) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.getWageByPrimaryKey ");
      log.debug("parameter wageId is : " + wageId);
    }
    try {
      if (PropertyUtilities.asNullValue(wageId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wageId " );
      }
      return wagePersistent.getWageByPrimaryKey(wageId);
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
  public Long getCountWage(WageSearch wageSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.getCountWage ");
      log.debug("parameter wageSearch is : " + wageSearch);
    }
    try {
      return wagePersistent.getCountWage(wageSearch);
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
  public Collection<Wage> getAllWage() throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.getAllWage ");
    }
    try {
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

  /**
   * {@inheritDoc}
   */
  @Override
  public PageRange<Wage> paginationGetAllWage(PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.paginationGetAllWage ");
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      return wagePersistent.paginationGetAllWage(page);
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
  public Collection<Wage> searchWage(WageSearch wageSearch) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.searchWage ");
      log.debug("parameter wageSearch is : " + wageSearch);
    }
    try {
      return wagePersistent.searchWage(wageSearch);
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
  public PageRange<Wage> paginationSearchWage(WageSearch wageSearch, PageSerachParameters page) throws EemsException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageService.paginationSearchWage ");
      log.debug("parameter wageSearch is : " + wageSearch);
      log.debug("parameter page is : " + page);
    }
    try {
      if (page == null) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " page " );
      }
      return wagePersistent.paginationSearchWage(wageSearch, page);
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