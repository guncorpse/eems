package com.liuxiaobin.eems.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuxiaobin.eems.commons.exception.EemsException;
import com.liuxiaobin.eems.entity.JcRecording;
import com.liuxiaobin.eems.entity.User;
import com.liuxiaobin.eems.entity.Wage;
import com.liuxiaobin.eems.service.IBusinessService;
import com.liuxiaobin.eems.service.IJcRecordingService;
import com.liuxiaobin.eems.service.IWageService;
import com.nmxpsoft.base.commons.vo.CommonParameters;
import com.nmxpsoft.base.commons.vo.ResponseRange;
import com.nmxpsoft.web.controller.springmvc.BaseController;

/**
 * 该类是业务控制器。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "business" })
public class BusinessController extends BaseController {
  
  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(BusinessController.class);
  
  @javax.annotation.Resource(name = "com.liuxiaobin.eems.BusinessService")
  private IBusinessService businessService;
  
  @javax.annotation.Resource(name = "com.liuxiaobin.eems.WageService")
  private IWageService wageService;
  
  @javax.annotation.Resource(name = "com.liuxiaobin.eems.JcRecordingService")
  private IJcRecordingService jcRecordingService;
  
  @RequestMapping(value = { "login" }, method = { RequestMethod.POST })
  @ResponseBody
  public ResponseRange<User> login(CommonParameters commonParameters, @RequestBody User user) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call BusinessController.login ");
      log.debug("parameter commonParameters is : " + commonParameters);
      log.debug("parameter user is : " + user);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      if (user == null || user.selfIsNull()) {
        throw EemsException.getException(EemsException.FW_PARAMETER_IS_NULL_ERROR, " user " );
      }
      user = businessService.login(user);
      super.getSession().setAttribute("user", user);
      responseRange.setData(user);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }
  
  @RequestMapping(value = { "clear_wage" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseRange<Wage> clearWage(CommonParameters commonParameters) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call BusinessController.clearWage ");
      log.debug("parameter commonParameters is : " + commonParameters);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      responseRange.setData(businessService.clearWages());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }
  
  @RequestMapping(value = { "clean_wage" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseRange<Wage> cleanWage(CommonParameters commonParameters) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call BusinessController.cleanWage ");
      log.debug("parameter commonParameters is : " + commonParameters);
    }
    ResponseRange<Wage> responseRange = new ResponseRange<>();
    try {
      Collection<Wage> wages = wageService.getAllWage();
      if(wages.isEmpty()) {
        throw EemsException.getException(EemsException.FW_ERROR,"工资记录为空，无需清空");
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
  
  @RequestMapping(value = { "clean_jc" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseRange<JcRecording> cleanJc(CommonParameters commonParameters) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call BusinessController.cleanJc ");
      log.debug("parameter commonParameters is : " + commonParameters);
    }
    ResponseRange<JcRecording> responseRange = new ResponseRange<>();
    try {
      Collection<JcRecording> recordings = jcRecordingService.getAllJcRecording();
      if(recordings.isEmpty()) {
        throw EemsException.getException(EemsException.FW_ERROR,"奖惩记录为空，无需清空");
      }
      jcRecordingService.batchRemoveJcRecording(recordings);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }
  
  @RequestMapping(value = { "get_user" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseRange<User> getUser(CommonParameters commonParameters) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call BusinessController.getUser ");
      log.debug("parameter commonParameters is : " + commonParameters);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      User user = (User)super.getSession().getAttribute("user");
      if(user == null) {
        throw EemsException.getException(EemsException.FW_ERROR,"没有检测到用户信息,请登录");
      }
      responseRange.setData(user);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }
  
  @RequestMapping(value = { "logout" }, method = { RequestMethod.GET })
  @ResponseBody
  public ResponseRange<User> logout(CommonParameters commonParameters) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call BusinessController.logout ");
      log.debug("parameter commonParameters is : " + commonParameters);
    }
    ResponseRange<User> responseRange = new ResponseRange<>();
    try {
      User user = (User)super.getSession().getAttribute("user");
      if(user == null) {
        throw EemsException.getException(EemsException.FW_ERROR,"没有检测到用户信息,无需注销");
      }
      super.getSession().removeAttribute("user");
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responseRange.setException(e);
    }
    return responseRange;
  }

}
