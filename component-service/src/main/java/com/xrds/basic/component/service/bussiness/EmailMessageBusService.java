package com.xrds.basic.component.service.bussiness;

import com.kunpu.frameworks.commons.exception.business.BusinessException;

/**
 * 
 * 
 * @author liukai
 * @version $Id: EmailMessageBusService.java, v 0.1 2017年11月26日 下午2:04:15 liukai Exp $
 */
public interface EmailMessageBusService extends MessageBusService {


  /**
   * 根据邮件发送规则配置发送并保存通知 定制的邮件发送保存接口，根据邮件配置项定义
   * 
   * @param noticeLevel 级别参数分 ，高：H，中：M，低：L；通过DefaultLevel枚举类获取 定时处理发送异常的消息 高级别每1分钟处理一次 中级别10分钟处理一次
   *        低级别两个小时处理一次
   * @param configKey 邮件模板配置项
   * @param content 邮件内容 （注，邮件内容必须为符合模板的map转化后的json字符串）
   * @param noticeLevel 邮件发送级别
   * @return
   * @throws BusinessException
   */
  public boolean sendAndSaveMessageByConfig(String noticeLevel, String configKey, String content)
      throws BusinessException;

  /**
   * 根据邮件发送规则配置发送并保存通知 定制的邮件发送保存接口，根据邮件配置项定义
   * 
   * @param noticeLevel 级别参数分 ，高：H，中：M，低：L；通过DefaultLevel枚举类获取 定时处理发送异常的消息 高级别每1分钟处理一次 中级别10分钟处理一次
   *        低级别两个小时处理一次
   * @param configKey 邮件模板配置项
   * @param content 异常邮件内容 （注，邮件内容必须为符合模板的map转化后的json字符串）
   * @param noticeLevel 邮件发送级别
   * @return
   * @throws BusinessException
   */
  public boolean sendAndSaveExceptionMessageByConfig(String noticeLevel, String configKey,
      Throwable content) throws BusinessException;

  /**
   * 默认邮件发送接口 邮件模板为email_common_template 内容格式为$content
   * 
   * @param noticeLevel 级别参数分 ，高：H，中：M，低：L；通过DefaultLevel枚举类获取 定时处理发送异常的消息 高级别每1分钟处理一次 中级别10分钟处理一次
   *        低级别两个小时处理一次
   * @param content
   * @return
   * @throws BusinessException
   */
  public boolean sendAndSaveMessageByConfig(String noticeLevel, String content)
      throws BusinessException;

  /**
   * 默认邮件发送接口 邮件模板为email_common_template 内容格式为$content
   * 
   * @param noticeLevel 级别参数分 ，高：H，中：M，低：L；通过DefaultLevel枚举类获取 定时处理发送异常的消息 高级别每1分钟处理一次 中级别10分钟处理一次
   *        低级别两个小时处理一次
   * @param content 异常邮件内容
   * @return
   * @throws BusinessException
   */
  public boolean sendAndSaveExceptionMessageByConfig(String noticeLevel, Throwable content)
      throws BusinessException;


  /**
   * 
   * 根据邮件发送规则配置发送通知
   * 
   * @param configKey 邮件模板配置项
   * @param content 邮件内容 （注，邮件内容必须为符合模板的map转化后的json字符串）
   * @throws HssException
   */
  public boolean sendMessageByConfig(String configKey, String content);

  /**
   * 默认邮件发送接口 邮件模板为email_common_template 内容格式为$content
   * 
   * @param content
   * @return
   */
  public boolean sendMessageByConfig(String content);



}
