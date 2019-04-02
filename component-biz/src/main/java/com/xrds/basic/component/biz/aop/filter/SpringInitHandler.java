package com.xrds.basic.component.biz.aop.filter;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.kunpu.frameworks.cat.util.CatUtil;
import com.kunpu.frameworks.commons.exception.CommonException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;

/**
 * 
 * @author yuyangzhi
 * @version $Id: SpringInitHandler.java, v 0.1 2018年6月28日 上午11:57:43 yuyangzhi Exp $
 */
@Component("springInitHandler")
public class SpringInitHandler extends PlaceholderConfigurerSupport
    implements
      ApplicationContextAware {

  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_BIZ.getLogName());

  private static ApplicationContext applicationContext;


  /**
   * @see org.springcipwork.context.ApplicationContextAware#setApplicationContext(org.springcipwork.context.ApplicationContext)
   */
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    // CatUtil.setCatEnable(ApplicationContextConfigUtil.getBoolean("cat.switch", false));
    CatUtil.setCatEnable(true);
    // 日志规范相关
    // LogbackConfigListener.getInstance();
    this.applicationContext = applicationContext;
  }



  @Override
  protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
      throws BeansException {}

  public static Object getBean(String beanName) throws CommonException {
    try {
      if (applicationContext != null && StringUtils.isNotBlank(StringUtils.trim(beanName))) {
        return applicationContext.getBean(beanName);
      }
    } catch (Exception e) {
      LOGGER.error("get spring bean " + beanName + " exception", e);
      // throw new CommonException(CipErrorCode.ERROR_409, e);
    }
    return null;
  }


}
