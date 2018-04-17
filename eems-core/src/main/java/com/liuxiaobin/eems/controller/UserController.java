/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.controller;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.FcsUser;
import com.liuxiaobin.eems.search.FcsUserSearch;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nmxpsoft.web.controller.springmvc.BaseController;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.liuxiaobin.eems.service.IFcsUserService;
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
 * 该类是用户控制器。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "user" })
public class UserController extends BaseController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(UserController.class);

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.FcsUserService")
  private IFcsUserService fcsUserService;


  @RequestMapping(value = { "single" }, method = { RequestMethod.POST })
  @ResponseBody
  public ResponseRange<FcsUser> save(CommonParameters commonParameters, @RequestBody FcsUser fcsUser) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserController.save ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter fcsUser is : " + fcsUser);
    }
    ResponseRange<FcsUser> responseRange = new ResponseRange<>();
    try {
      if (fcsUser == null || fcsUser.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUser " );
      }
      fcsUserService.saveFcsUser(fcsUser);
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
  public ResponseRange<FcsUser> batchSave(CommonParameters commonParameters, @RequestBody List<FcsUser> fcsUsers) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserController.batchSave ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter fcsUsers is : " + fcsUsers);
    }
    ResponseRange<FcsUser> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(fcsUsers)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUsers " );
      }
      fcsUserService.batchSaveFcsUser(fcsUsers);
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
  public ResponseRange<FcsUser> update(CommonParameters commonParameters, @RequestBody FcsUser fcsUser) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserController.update ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter fcsUser is : " + fcsUser);
    }
    ResponseRange<FcsUser> responseRange = new ResponseRange<>();
    try {
      if (fcsUser == null || fcsUser.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUser " );
      }
      fcsUserService.updateFcsUser(fcsUser);
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
  public ResponseRange<FcsUser> batchUpdate(CommonParameters commonParameters, @RequestBody List<FcsUser> fcsUsers) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserController.batchUpdate ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter fcsUsers is : " + fcsUsers);
    }
    ResponseRange<FcsUser> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(fcsUsers)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUsers " );
      }
      fcsUserService.batchUpdateFcsUser(fcsUsers);
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
  public ResponseRange<FcsUser> remove(CommonParameters commonParameters, @RequestBody FcsUser fcsUser) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserController.remove ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter fcsUser is : " + fcsUser);
    }
    ResponseRange<FcsUser> responseRange = new ResponseRange<>();
    try {
      if (fcsUser == null || fcsUser.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUser " );
      }
      fcsUserService.removeFcsUser(fcsUser);
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
  public ResponseRange<FcsUser> batchRemove(CommonParameters commonParameters, @RequestBody List<FcsUser> fcsUsers) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserController.batchRemove ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter fcsUsers is : " + fcsUsers);
    }
    ResponseRange<FcsUser> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(fcsUsers)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " fcsUsers " );
      }
      fcsUserService.batchRemoveFcsUser(fcsUsers);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(value = { "/{userId}" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseRange<FcsUser> getByPrimaryKey(CommonParameters commonParameters, @PathVariable java.lang.String userId) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserController.getByPrimaryKey ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter userId is : " + userId);
    }
    ResponseRange<FcsUser> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.asNullValue(userId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " userId " );
      }
      responseRange.setData(fcsUserService.getFcsUserByPrimaryKey(userId));
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
  public ResponseRange<FcsUser> get(CommonParameters commonParameters, FcsUserSearch fcsUserSearch) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserController.get ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter fcsUserSearch is : " + fcsUserSearch);
    }
    ResponseRange<FcsUser> responseRange = new ResponseRange<>();
    try {
      if (fcsUserSearch == null || fcsUserSearch.selfIsNull()) {
        if (commonParameters.isPageSerach()) {
          responseRange.setData(fcsUserService.paginationGetAllFcsUser(commonParameters.getPageSerachParameters()));
        } else {
          responseRange.setData(fcsUserService.getAllFcsUser());
        }
      } else {
        if (commonParameters.isPageSerach()) {
          responseRange.setData(fcsUserService.paginationSearchFcsUser(fcsUserSearch, commonParameters.getPageSerachParameters()));
        } else {
          responseRange.setData(fcsUserService.searchFcsUser(fcsUserSearch));
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
  public ResponseRange<FcsUser> batchImport(CommonParameters commonParameters, FcsUser fcsUser, @RequestParam CommonsMultipartFile importFile) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserController.batchImport ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter fcsUser is : " + fcsUser);
      log.debug("parameter importFile is : " + importFile);
    }
    ResponseRange<FcsUser> responseRange = new ResponseRange<>();
    try {
      java.io.InputStream inputXML = new java.io.BufferedInputStream(getClass().getResourceAsStream("/template/read/FcsUser.xml"));
      org.jxls.reader.XLSReader mainReader = org.jxls.reader.ReaderBuilder.buildFromXML(inputXML);
      java.io.InputStream inputXLS = new java.io.BufferedInputStream(importFile.getInputStream());
      java.util.List<FcsUser> fcsUserList = new java.util.LinkedList<>();
      java.util.Map<String, Object> beans = new java.util.HashMap<>();
      beans.put("fcsUserList", fcsUserList);
      org.jxls.reader.XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
      if (readStatus.isStatusOK()) {
        if (fcsUser != null && !fcsUser.selfIsNull()) {
          fcsUser.cloneThisToCollection(fcsUserList);
        }
        fcsUserService.batchSaveFcsUser(fcsUserList);
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
  public org.springframework.http.ResponseEntity<byte[]> export(CommonParameters commonParameters, FcsUserSearch fcsUserSearch) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call FcsUserController.export ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter fcsUserSearch is : " + fcsUserSearch);
    }
    org.springframework.http.ResponseEntity<byte[]> response = null;
    try {
      List<PageRange<FcsUser>> pageRangeList = new java.util.LinkedList<>();
      List<String> sheetNames = new java.util.LinkedList<>();
      if (fcsUserSearch == null || fcsUserSearch.selfIsNull()) {
        PageSerachParameters page = new PageSerachParameters();
        page.setPageSize(60000l);
        long pageIndex = 1;
        PageRange<FcsUser> pageRange = null;
        do {
          page.setPageNumber(pageIndex);
          pageRange = fcsUserService.paginationGetAllFcsUser(page);
          pageRangeList.add(pageRange);
          sheetNames.add("第" + pageIndex + "页");
          pageIndex++;
        } while (pageRange.getPageCount() >= pageIndex);
      } else {
        PageSerachParameters page = new PageSerachParameters();
        page.setPageSize(60000l);
        long pageIndex = 1;
        PageRange<FcsUser> pageRange = null;
        do {
          page.setPageNumber(pageIndex);
          pageRange = fcsUserService.paginationSearchFcsUser(fcsUserSearch, commonParameters.getPageSerachParameters());
          pageRangeList.add(pageRange);
          sheetNames.add("第" + pageIndex + "页");
          pageIndex++;
        } while (pageRange.getPageCount() >= pageIndex);
      }
      java.io.InputStream is = this.getClass().getResourceAsStream("/template/write/FcsUser.xls");
      org.jxls.common.Context context = new org.jxls.common.Context();
      context.putVar("pageRangeSet", pageRangeList);
      context.putVar("sheetNames", sheetNames);
      java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
      org.jxls.util.JxlsHelper.getInstance().processTemplate(is,os, context);
      org.springframework.http.HttpHeaders headers= new org.springframework.http.HttpHeaders();
      byte[] by= os.toByteArray(); 
      headers.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment",java.net.URLEncoder.encode("用户.xls", "UTF-8"));
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
