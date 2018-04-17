/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.controller;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.Staff;
import com.liuxiaobin.eems.search.StaffSearch;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nmxpsoft.web.controller.springmvc.BaseController;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.liuxiaobin.eems.service.IStaffService;
import com.nmxpsoft.base.commons.vo.CommonParameters;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nmxpsoft.base.commons.vo.ResponseRange;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.nmxpsoft.base.commons.vo.PageRange;

/**
 * 该类是员工控制器。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "staff" })
public class StaffController extends BaseController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(StaffController.class);

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.StaffService")
  private IStaffService staffService;


  @RequestMapping(value = { "single" }, method = { RequestMethod.POST })
  @ResponseBody
  public ResponseRange<Staff> save(CommonParameters commonParameters, @RequestBody Staff staff) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffController.save ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter staff is : " + staff);
    }
    ResponseRange<Staff> responseRange = new ResponseRange<>();
    try {
      if (staff == null || staff.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
      }
      staffService.saveStaff(staff);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(method = { RequestMethod.POST })
  @ResponseBody
  public ResponseRange<Staff> batchSave(CommonParameters commonParameters, @RequestBody List<Staff> staffs) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffController.batchSave ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter staffs is : " + staffs);
    }
    ResponseRange<Staff> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(staffs)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffs " );
      }
      staffService.batchSaveStaff(staffs);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(value = { "single" }, method = { RequestMethod.PUT })
  @ResponseBody
  public ResponseRange<Staff> update(CommonParameters commonParameters, @RequestBody Staff staff) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffController.update ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter staff is : " + staff);
    }
    ResponseRange<Staff> responseRange = new ResponseRange<>();
    try {
      if (staff == null || staff.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
      }
      staffService.updateStaff(staff);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(method = { RequestMethod.PUT })
  @ResponseBody
  public ResponseRange<Staff> batchUpdate(CommonParameters commonParameters, @RequestBody List<Staff> staffs) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffController.batchUpdate ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter staffs is : " + staffs);
    }
    ResponseRange<Staff> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(staffs)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffs " );
      }
      staffService.batchUpdateStaff(staffs);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(value = { "single" }, method = { RequestMethod.DELETE })
  @ResponseBody
  public ResponseRange<Staff> remove(CommonParameters commonParameters, @RequestBody Staff staff) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffController.remove ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter staff is : " + staff);
    }
    ResponseRange<Staff> responseRange = new ResponseRange<>();
    try {
      if (staff == null || staff.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staff " );
      }
      staffService.removeStaff(staff);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(method = { RequestMethod.DELETE })
  @ResponseBody
  public ResponseRange<Staff> batchRemove(CommonParameters commonParameters, @RequestBody List<Staff> staffs) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffController.batchRemove ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter staffs is : " + staffs);
    }
    ResponseRange<Staff> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(staffs)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffs " );
      }
      staffService.batchRemoveStaff(staffs);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(value = { "/{staffId}" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseRange<Staff> getByPrimaryKey(CommonParameters commonParameters, @PathVariable java.lang.String staffId) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffController.getByPrimaryKey ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter staffId is : " + staffId);
    }
    ResponseRange<Staff> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.asNullValue(staffId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " staffId " );
      }
      responseRange.setData(staffService.getStaffByPrimaryKey(staffId));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(method = { RequestMethod.GET })
  @ResponseBody
  public ResponseRange<Staff> get(CommonParameters commonParameters, StaffSearch staffSearch) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffController.get ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter staffSearch is : " + staffSearch);
    }
    ResponseRange<Staff> responseRange = new ResponseRange<>();
    try {
      if (staffSearch == null || staffSearch.selfIsNull()) {
        if (commonParameters.isPageSerach()) {
          responseRange.setData(staffService.paginationGetAllStaff(commonParameters.getPageSerachParameters()));
        } else {
          responseRange.setData(staffService.getAllStaff());
        }
      } else {
        if (commonParameters.isPageSerach()) {
          responseRange.setData(staffService.paginationSearchStaff(staffSearch, commonParameters.getPageSerachParameters()));
        } else {
          responseRange.setData(staffService.searchStaff(staffSearch));
        }
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(value = { "import" }, method = { RequestMethod.POST })
  @ResponseBody
  public ResponseRange<Staff> batchImport(CommonParameters commonParameters, Staff staff, @RequestParam CommonsMultipartFile importFile) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffController.batchImport ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter staff is : " + staff);
      log.debug("parameter importFile is : " + importFile);
    }
    ResponseRange<Staff> responseRange = new ResponseRange<>();
    try {
      java.io.InputStream inputXML = new java.io.BufferedInputStream(getClass().getResourceAsStream("/template/read/Staff.xml"));
      org.jxls.reader.XLSReader mainReader = org.jxls.reader.ReaderBuilder.buildFromXML(inputXML);
      java.io.InputStream inputXLS = new java.io.BufferedInputStream(importFile.getInputStream());
      java.util.List<Staff> staffList = new java.util.LinkedList<>();
      java.util.Map<String, Object> beans = new java.util.HashMap<>();
      beans.put("staffList", staffList);
      org.jxls.reader.XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
      if (readStatus.isStatusOK()) {
        if (staff != null && !staff.selfIsNull()) {
          staff.cloneThisToCollection(staffList);
        }
        staffService.batchSaveStaff(staffList);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(value = { "export" }, method = { RequestMethod.GET })
  @ResponseBody
  public org.springframework.http.ResponseEntity<byte[]> export(CommonParameters commonParameters, StaffSearch staffSearch) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call StaffController.export ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter staffSearch is : " + staffSearch);
    }
    org.springframework.http.ResponseEntity<byte[]> response = null;
    try {
      List<PageRange<Staff>> pageRangeList = new java.util.LinkedList<>();
      List<String> sheetNames = new java.util.LinkedList<>();
      if (staffSearch == null || staffSearch.selfIsNull()) {
        PageSerachParameters page = new PageSerachParameters();
        page.setPageSize(60000l);
        long pageIndex = 1;
        PageRange<Staff> pageRange = null;
        do {
          page.setPageNumber(pageIndex);
          pageRange = staffService.paginationGetAllStaff(page);
          pageRangeList.add(pageRange);
          sheetNames.add("第" + pageIndex + "页");
          pageIndex++;
        } while (pageRange.getPageCount() >= pageIndex);
      } else {
        PageSerachParameters page = new PageSerachParameters();
        page.setPageSize(60000l);
        long pageIndex = 1;
        PageRange<Staff> pageRange = null;
        do {
          page.setPageNumber(pageIndex);
          pageRange = staffService.paginationSearchStaff(staffSearch, commonParameters.getPageSerachParameters());
          pageRangeList.add(pageRange);
          sheetNames.add("第" + pageIndex + "页");
          pageIndex++;
        } while (pageRange.getPageCount() >= pageIndex);
      }
      java.io.InputStream is = this.getClass().getResourceAsStream("/template/write/Staff.xls");
      org.jxls.common.Context context = new org.jxls.common.Context();
      context.putVar("pageRangeSet", pageRangeList);
      context.putVar("sheetNames", sheetNames);
      java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
      org.jxls.util.JxlsHelper.getInstance().processTemplate(is,os, context);
      org.springframework.http.HttpHeaders headers= new org.springframework.http.HttpHeaders();
      byte[] by= os.toByteArray(); 
      headers.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment",java.net.URLEncoder.encode("员工.xls", "UTF-8"));
      headers.setContentLength(by.length);
      response= new org.springframework.http.ResponseEntity<byte[]>(by, headers, org.springframework.http.HttpStatus.OK);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
    }
    return response;
  }

}
