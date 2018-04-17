/*
 * Copyright (c) 2017, 2026, Nmxpsoft and Nmgzhigang and/or its affiliates. All rights reserved.
 * Nmxpsoft and  Nmgzhigang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxiaobin.eems.commons.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.nmxpsoft.base.commons.exception.NmXpsoftBaseException;
import com.nmxpsoft.base.commons.exception.ErrorMessageUtilities;

/**
 * 异常类。
 *
 * @author liuxiaobin
 * @version 0.0.1
 */
public class EemsException extends NmXpsoftBaseException {

  private static final long serialVersionUID = 1L;

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(EemsException.class);

  private final static Properties errorMessages = new Properties();
  private static volatile boolean isLoadMessage = false;

  /**
   * 加载异常资源文件：ErrorCode.properties，默认编码为GBK
   */
  private static synchronized void reload() {
    if (!isLoadMessage) {
      isLoadMessage = true;
      InputStream is = EemsException.class.getResourceAsStream("ErrorCode.properties");
        if (is != null) {
          try {
            errorMessages.load(is);
            isLoadMessage = true;
          } catch (IOException ioe) {
            if (log.isErrorEnabled()) {
            log.error("ErrorCode.properties load is error !!!", ioe);
          }
        }
      }
    }
  }

  protected EemsException(int errCode, String errMessage, Exception innerException) {
    super(errCode, errMessage, innerException);
  }

  public static EemsException getException(int errCode) {
    return getException(null, errCode);
  }

  public static EemsException getException(int errCode, String... lstPattern) {
    return getException(null, errCode, lstPattern);
  }

  public static EemsException getException(Exception innerException, int errCode, String... lstPattern) {
    String errMessage = codeToMessage(errCode, lstPattern);
    return new EemsException(errCode, errMessage, innerException);
  }

  private static String codeToMessage(int errCode, String... lstPattern) {
    reload();
    String errCodeStr = Integer.toString(errCode);
    String errorMessage = ErrorMessageUtilities.codeToMessage(errorMessages, errCode, lstPattern);
    if (errorMessage == null || errorMessage.equals(errCodeStr)) {
      errorMessage = NmXpsoftBaseException.itselfCodeToMessage(errCode, lstPattern);
    }
    return errorMessage;
  }
}