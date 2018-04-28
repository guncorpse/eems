/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.controller;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.User;
import com.liuxiaobin.eems.search.UserSearch;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nmxpsoft.web.controller.springmvc.BaseController;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.liuxiaobin.eems.service.IUserService;
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

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.UserService")
  private IUserService userService;


  @RequestMapping(value = { "single" }, method = { RequestMethod.POST })
  @ResponseBody
  public ResponseRange<User> save(CommonParameters commonParameters, @RequestBody User user) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserController.save ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter user is : " + user);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      userService.saveUser(user);
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
  public ResponseRange<User> batchSave(CommonParameters commonParameters, @RequestBody List<User> users) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserController.batchSave ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter users is : " + users);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(users)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " users " );
      }
      userService.batchSaveUser(users);
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
  public ResponseRange<User> update(CommonParameters commonParameters, @RequestBody User user) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserController.update ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter user is : " + user);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      userService.updateUser(user);
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
  public ResponseRange<User> batchUpdate(CommonParameters commonParameters, @RequestBody List<User> users) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserController.batchUpdate ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter users is : " + users);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(users)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " users " );
      }
      userService.batchUpdateUser(users);
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
  public ResponseRange<User> remove(CommonParameters commonParameters, @RequestBody User user) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserController.remove ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter user is : " + user);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      userService.removeUser(user);
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
  public ResponseRange<User> batchRemove(CommonParameters commonParameters, @RequestBody List<User> users) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserController.batchRemove ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter users is : " + users);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(users)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " users " );
      }
      userService.batchRemoveUser(users);
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
  public ResponseRange<User> getByPrimaryKey(CommonParameters commonParameters, @PathVariable java.lang.String userId) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserController.getByPrimaryKey ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter userId is : " + userId);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.asNullValue(userId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " userId " );
      }
      responseRange.setData(userService.getUserByPrimaryKey(userId));
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
  public ResponseRange<User> get(CommonParameters commonParameters, UserSearch userSearch) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserController.get ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter userSearch is : " + userSearch);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      if (userSearch == null || userSearch.selfIsNull()) {
        if (commonParameters.isPageSerach()) {
          responseRange.setData(userService.paginationGetAllUser(commonParameters.getPageSerachParameters()));
        } else {
          responseRange.setData(userService.getAllUser());
        }
      } else {
        if (commonParameters.isPageSerach()) {
          responseRange.setData(userService.paginationSearchUser(userSearch, commonParameters.getPageSerachParameters()));
        } else {
          responseRange.setData(userService.searchUser(userSearch));
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
  public ResponseRange<User> batchImport(CommonParameters commonParameters, User user, @RequestParam CommonsMultipartFile importFile) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserController.batchImport ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter user is : " + user);
      log.debug("parameter importFile is : " + importFile);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      java.io.InputStream inputXML = new java.io.BufferedInputStream(getClass().getResourceAsStream("/template/read/User.xml"));
      org.jxls.reader.XLSReader mainReader = org.jxls.reader.ReaderBuilder.buildFromXML(inputXML);
      java.io.InputStream inputXLS = new java.io.BufferedInputStream(importFile.getInputStream());
      java.util.List<User> userList = new java.util.LinkedList<>();
      java.util.Map<String, Object> beans = new java.util.HashMap<>();
      beans.put("userList", userList);
      org.jxls.reader.XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
      if (readStatus.isStatusOK()) {
        if (user != null && !user.selfIsNull()) {
          user.cloneThisToCollection(userList);
        }
        userService.batchSaveUser(userList);
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
  public org.springframework.http.ResponseEntity<byte[]> export(CommonParameters commonParameters, UserSearch userSearch) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UserController.export ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter userSearch is : " + userSearch);
    }
    org.springframework.http.ResponseEntity<byte[]> response = null;
    try {
      List<PageRange<User>> pageRangeList = new java.util.LinkedList<>();
      List<String> sheetNames = new java.util.LinkedList<>();
      if (userSearch == null || userSearch.selfIsNull()) {
        PageSerachParameters page = new PageSerachParameters();
        page.setPageSize(60000l);
        long pageIndex = 1;
        PageRange<User> pageRange = null;
        do {
          page.setPageNumber(pageIndex);
          pageRange = userService.paginationGetAllUser(page);
          pageRangeList.add(pageRange);
          sheetNames.add("第" + pageIndex + "页");
          pageIndex++;
        } while (pageRange.getPageCount() >= pageIndex);
      } else {
        PageSerachParameters page = new PageSerachParameters();
        page.setPageSize(60000l);
        long pageIndex = 1;
        PageRange<User> pageRange = null;
        do {
          page.setPageNumber(pageIndex);
          pageRange = userService.paginationSearchUser(userSearch, commonParameters.getPageSerachParameters());
          pageRangeList.add(pageRange);
          sheetNames.add("第" + pageIndex + "页");
          pageIndex++;
        } while (pageRange.getPageCount() >= pageIndex);
      }
      java.io.InputStream is = this.getClass().getResourceAsStream("/template/write/User.xls");
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
