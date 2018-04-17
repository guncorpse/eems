/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.controller;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.JcRecording;
import com.liuxiaobin.eems.search.JcRecordingSearch;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nmxpsoft.web.controller.springmvc.BaseController;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.liuxiaobin.eems.service.IJcRecordingService;
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
 * 该类是奖惩记录控制器。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "jc_recording" })
public class JcRecordingController extends BaseController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(JcRecordingController.class);

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.JcRecordingService")
  private IJcRecordingService jcRecordingService;


  @RequestMapping(value = { "single" }, method = { RequestMethod.POST })
  @ResponseBody
  public ResponseRange<JcRecording> save(CommonParameters commonParameters, @RequestBody JcRecording jcRecording) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingController.save ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter jcRecording is : " + jcRecording);
    }
    ResponseRange<JcRecording> responseRange = new ResponseRange<>();
    try {
      if (jcRecording == null || jcRecording.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
      }
      jcRecordingService.saveJcRecording(jcRecording);
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
  public ResponseRange<JcRecording> batchSave(CommonParameters commonParameters, @RequestBody List<JcRecording> jcRecordings) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingController.batchSave ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter jcRecordings is : " + jcRecordings);
    }
    ResponseRange<JcRecording> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(jcRecordings)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecordings " );
      }
      jcRecordingService.batchSaveJcRecording(jcRecordings);
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
  public ResponseRange<JcRecording> update(CommonParameters commonParameters, @RequestBody JcRecording jcRecording) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingController.update ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter jcRecording is : " + jcRecording);
    }
    ResponseRange<JcRecording> responseRange = new ResponseRange<>();
    try {
      if (jcRecording == null || jcRecording.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
      }
      jcRecordingService.updateJcRecording(jcRecording);
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
  public ResponseRange<JcRecording> batchUpdate(CommonParameters commonParameters, @RequestBody List<JcRecording> jcRecordings) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingController.batchUpdate ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter jcRecordings is : " + jcRecordings);
    }
    ResponseRange<JcRecording> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(jcRecordings)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecordings " );
      }
      jcRecordingService.batchUpdateJcRecording(jcRecordings);
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
  public ResponseRange<JcRecording> remove(CommonParameters commonParameters, @RequestBody JcRecording jcRecording) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingController.remove ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter jcRecording is : " + jcRecording);
    }
    ResponseRange<JcRecording> responseRange = new ResponseRange<>();
    try {
      if (jcRecording == null || jcRecording.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecording " );
      }
      jcRecordingService.removeJcRecording(jcRecording);
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
  public ResponseRange<JcRecording> batchRemove(CommonParameters commonParameters, @RequestBody List<JcRecording> jcRecordings) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingController.batchRemove ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter jcRecordings is : " + jcRecordings);
    }
    ResponseRange<JcRecording> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(jcRecordings)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " jcRecordings " );
      }
      jcRecordingService.batchRemoveJcRecording(jcRecordings);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(value = { "/{recordingId}" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseRange<JcRecording> getByPrimaryKey(CommonParameters commonParameters, @PathVariable java.lang.String recordingId) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingController.getByPrimaryKey ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter recordingId is : " + recordingId);
    }
    ResponseRange<JcRecording> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.asNullValue(recordingId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " recordingId " );
      }
      responseRange.setData(jcRecordingService.getJcRecordingByPrimaryKey(recordingId));
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
  public ResponseRange<JcRecording> get(CommonParameters commonParameters, JcRecordingSearch jcRecordingSearch) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingController.get ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter jcRecordingSearch is : " + jcRecordingSearch);
    }
    ResponseRange<JcRecording> responseRange = new ResponseRange<>();
    try {
      if (jcRecordingSearch == null || jcRecordingSearch.selfIsNull()) {
        if (commonParameters.isPageSerach()) {
          responseRange.setData(jcRecordingService.paginationGetAllJcRecording(commonParameters.getPageSerachParameters()));
        } else {
          responseRange.setData(jcRecordingService.getAllJcRecording());
        }
      } else {
        if (commonParameters.isPageSerach()) {
          responseRange.setData(jcRecordingService.paginationSearchJcRecording(jcRecordingSearch, commonParameters.getPageSerachParameters()));
        } else {
          responseRange.setData(jcRecordingService.searchJcRecording(jcRecordingSearch));
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
  public ResponseRange<JcRecording> batchImport(CommonParameters commonParameters, JcRecording jcRecording, @RequestParam CommonsMultipartFile importFile) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingController.batchImport ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter jcRecording is : " + jcRecording);
      log.debug("parameter importFile is : " + importFile);
    }
    ResponseRange<JcRecording> responseRange = new ResponseRange<>();
    try {
      java.io.InputStream inputXML = new java.io.BufferedInputStream(getClass().getResourceAsStream("/template/read/JcRecording.xml"));
      org.jxls.reader.XLSReader mainReader = org.jxls.reader.ReaderBuilder.buildFromXML(inputXML);
      java.io.InputStream inputXLS = new java.io.BufferedInputStream(importFile.getInputStream());
      java.util.List<JcRecording> jcRecordingList = new java.util.LinkedList<>();
      java.util.Map<String, Object> beans = new java.util.HashMap<>();
      beans.put("jcRecordingList", jcRecordingList);
      org.jxls.reader.XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
      if (readStatus.isStatusOK()) {
        if (jcRecording != null && !jcRecording.selfIsNull()) {
          jcRecording.cloneThisToCollection(jcRecordingList);
        }
        jcRecordingService.batchSaveJcRecording(jcRecordingList);
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
  public org.springframework.http.ResponseEntity<byte[]> export(CommonParameters commonParameters, JcRecordingSearch jcRecordingSearch) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call JcRecordingController.export ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter jcRecordingSearch is : " + jcRecordingSearch);
    }
    org.springframework.http.ResponseEntity<byte[]> response = null;
    try {
      List<PageRange<JcRecording>> pageRangeList = new java.util.LinkedList<>();
      List<String> sheetNames = new java.util.LinkedList<>();
      if (jcRecordingSearch == null || jcRecordingSearch.selfIsNull()) {
        PageSerachParameters page = new PageSerachParameters();
        page.setPageSize(60000l);
        long pageIndex = 1;
        PageRange<JcRecording> pageRange = null;
        do {
          page.setPageNumber(pageIndex);
          pageRange = jcRecordingService.paginationGetAllJcRecording(page);
          pageRangeList.add(pageRange);
          sheetNames.add("第" + pageIndex + "页");
          pageIndex++;
        } while (pageRange.getPageCount() >= pageIndex);
      } else {
        PageSerachParameters page = new PageSerachParameters();
        page.setPageSize(60000l);
        long pageIndex = 1;
        PageRange<JcRecording> pageRange = null;
        do {
          page.setPageNumber(pageIndex);
          pageRange = jcRecordingService.paginationSearchJcRecording(jcRecordingSearch, commonParameters.getPageSerachParameters());
          pageRangeList.add(pageRange);
          sheetNames.add("第" + pageIndex + "页");
          pageIndex++;
        } while (pageRange.getPageCount() >= pageIndex);
      }
      java.io.InputStream is = this.getClass().getResourceAsStream("/template/write/JcRecording.xls");
      org.jxls.common.Context context = new org.jxls.common.Context();
      context.putVar("pageRangeSet", pageRangeList);
      context.putVar("sheetNames", sheetNames);
      java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
      org.jxls.util.JxlsHelper.getInstance().processTemplate(is,os, context);
      org.springframework.http.HttpHeaders headers= new org.springframework.http.HttpHeaders();
      byte[] by= os.toByteArray(); 
      headers.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment",java.net.URLEncoder.encode("奖惩记录.xls", "UTF-8"));
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
