/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.Wage;
import com.liuxiaobin.eems.search.WageSearch;
import com.liuxiaobin.eems.service.IWageService;
import com.nmxpsoft.base.commons.utilities.PropertyUtilities;
import com.nmxpsoft.base.commons.vo.CommonParameters;
import com.nmxpsoft.base.commons.vo.PageRange;
import com.nmxpsoft.base.commons.vo.PageSerachParameters;
import com.nmxpsoft.base.commons.vo.ResponseRange;
import com.nmxpsoft.web.controller.springmvc.BaseController;

/**
 * 该类是工资控制器。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "wage" })
public class WageController extends BaseController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(WageController.class);

  @javax.annotation.Resource(name = "com.liuxiaobin.eems.WageService")
  private IWageService wageService;


  @RequestMapping(value = { "single" }, method = { RequestMethod.POST })
  @ResponseBody
  public ResponseRange<Wage> save(CommonParameters commonParameters, @RequestBody Wage wage) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageController.save ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter wage is : " + wage);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      if (wage == null || wage.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
      }
      wageService.saveWage(wage);
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
  public ResponseRange<Wage> batchSave(CommonParameters commonParameters, @RequestBody List<Wage> wages) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageController.batchSave ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter wages is : " + wages);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(wages)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wages " );
      }
      wageService.batchSaveWage(wages);
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
  public ResponseRange<Wage> update(CommonParameters commonParameters, @RequestBody Wage wage) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageController.update ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter wage is : " + wage);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      if (wage == null || wage.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
      }
      wageService.updateWage(wage);
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
  public ResponseRange<Wage> batchUpdate(CommonParameters commonParameters, @RequestBody List<Wage> wages) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageController.batchUpdate ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter wages is : " + wages);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(wages)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wages " );
      }
      wageService.batchUpdateWage(wages);
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
  public ResponseRange<Wage> remove(CommonParameters commonParameters, @RequestBody Wage wage) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageController.remove ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter wage is : " + wage);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      if (wage == null || wage.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wage " );
      }
      wageService.removeWage(wage);
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
  public ResponseRange<Wage> batchRemove(CommonParameters commonParameters, @RequestBody List<Wage> wages) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageController.batchRemove ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter wages is : " + wages);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.isEmpty(wages)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wages " );
      }
      wageService.batchRemoveWage(wages);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

  @RequestMapping(value = { "/{wageId}" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseRange<Wage> getByPrimaryKey(CommonParameters commonParameters, @PathVariable java.lang.String wageId) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageController.getByPrimaryKey ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter wageId is : " + wageId);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      if (PropertyUtilities.asNullValue(wageId)) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " wageId " );
      }
      responseRange.setData(wageService.getWageByPrimaryKey(wageId));
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
  public ResponseRange<Wage> get(CommonParameters commonParameters, WageSearch wageSearch) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageController.get ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter wageSearch is : " + wageSearch);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      if (wageSearch == null || wageSearch.selfIsNull()) {
        if (commonParameters.isPageSerach()) {
          responseRange.setData(wageService.paginationGetAllWage(commonParameters.getPageSerachParameters()));
        } else {
          responseRange.setData(wageService.getAllWage());
        }
      } else {
        if (commonParameters.isPageSerach()) {
          responseRange.setData(wageService.paginationSearchWage(wageSearch, commonParameters.getPageSerachParameters()));
        } else {
          responseRange.setData(wageService.searchWage(wageSearch));
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
  public ResponseRange<Wage> batchImport(CommonParameters commonParameters, Wage wage, @RequestParam CommonsMultipartFile importFile) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageController.batchImport ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter wage is : " + wage);
      log.debug("parameter importFile is : " + importFile);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      java.io.InputStream inputXML = new java.io.BufferedInputStream(getClass().getResourceAsStream("/template/read/Wage.xml"));
      org.jxls.reader.XLSReader mainReader = org.jxls.reader.ReaderBuilder.buildFromXML(inputXML);
      java.io.InputStream inputXLS = new java.io.BufferedInputStream(importFile.getInputStream());
      java.util.List<Wage> wageList = new java.util.LinkedList<>();
      java.util.Map<String, Object> beans = new java.util.HashMap<>();
      beans.put("wageList", wageList);
      org.jxls.reader.XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
      if (readStatus.isStatusOK()) {
        if (wage != null && !wage.selfIsNull()) {
          wage.cloneThisToCollection(wageList);
        }
        wageService.batchSaveWage(wageList);
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
  public org.springframework.http.ResponseEntity<byte[]> export(CommonParameters commonParameters, WageSearch wageSearch) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call WageController.export ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter wageSearch is : " + wageSearch);
    }
    org.springframework.http.ResponseEntity<byte[]> response = null;
    try {
      PageRange<Wage> pageRange = new PageRange<>();
      List<String> sheetNames = new java.util.LinkedList<>();
      if (wageSearch == null || wageSearch.selfIsNull()) {
        PageSerachParameters page = new PageSerachParameters();
        page.setPageSize(60000l);
        long pageIndex = 1;
        page.setPageNumber(pageIndex);
        pageRange = wageService.paginationGetAllWage(page);
      } else {
        PageSerachParameters page = new PageSerachParameters();
        page.setPageSize(60000l);
        long pageIndex = 1;
        page.setPageNumber(pageIndex);
        pageRange = wageService.paginationSearchWage(wageSearch, commonParameters.getPageSerachParameters());
      }
      java.io.InputStream is = this.getClass().getResourceAsStream("/template/write/Wage.xls");
      org.jxls.common.Context context = new org.jxls.common.Context();
      context.putVar("pageRangeSet", pageRange);
      context.putVar("sheetNames", sheetNames);
      java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
      JxlsHelper jxlsHelper = JxlsHelper.getInstance();
      Transformer transformer  = jxlsHelper.createTransformer(is, os);
      JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
      Map<String, Object> funcs = new HashMap<String, Object>();
      funcs.put("utils", new WageController()); //添加自定义功能
      evaluator.getJexlEngine().setFunctions(funcs);
      jxlsHelper.processTemplate(context, transformer);
      org.springframework.http.HttpHeaders headers= new org.springframework.http.HttpHeaders();
      byte[] by= os.toByteArray(); 
      headers.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment",java.net.URLEncoder.encode("工资报表.xls", "UTF-8"));
      headers.setContentLength(by.length);
      response= new org.springframework.http.ResponseEntity<byte[]>(by, headers, org.springframework.http.HttpStatus.OK);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
    }
    return response;
  }
  
  public String stampToDate(Long s){
    String res;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date(s);
    res = simpleDateFormat.format(date);
    return res;
}

}
