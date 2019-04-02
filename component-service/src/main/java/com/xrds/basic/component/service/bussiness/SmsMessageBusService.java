package com.xrds.basic.component.service.bussiness;


/**
 * 
 * 
 * @author liukai
 * @version $Id: SmsMessageBusService.java, v 0.1 2017年11月26日 下午2:04:24 liukai Exp $
 */
public interface SmsMessageBusService extends MessageBusService {

  /**
   * 根据短信发送规则配置发送并保存通知
   * 
   * @param configKey 短信模板配置项
   * @param content 短信内容
   * @param eventId
   * @param phoneNum @throws HssException
   * @param platformChannel
   */
  public boolean sendAndSaveMessageByConfig(String configKey, String content, String eventId,
      String[] phoneNum, String platformChannel);

  /**
   * 根据短信发送规则配置发送并保存通知
   * 
   * @param noticeLevel 级别参数分 ，高：H，中：M，低：L；通过DefaultLevel枚举类获取 定时处理发送异常的消息 高级别每1分钟处理一次 中级别10分钟处理一次
   *        低级别两个小时处理一次
   * @param configKey 短信模板配置项
   * @param content 短信内容
   * @param eventId
   * @param phoneNum @return
   * @param platformChannel
   */
  public boolean sendAndSaveMessageByConfigLevel(String noticeLevel, String configKey,
      String content, String eventId, String[] phoneNum, String platformChannel);

  /**
   * 根据短信发送规则配置发送并保存通知
   * 
   * @param noticeLevel 级别参数分 ，高：H，中：M，低：L；通过DefaultLevel枚举类获取 定时处理发送异常的消息 高级别每1分钟处理一次 中级别10分钟处理一次
   *        低级别两个小时处理一次
   * @param configKey 短信模板配置项
   * @param content 短信内容
   * @param phone 手机号码
   * @return
   */
  public boolean sendAndSaveMessageByPhone(String noticeLevel, String configKey, String[] phone,
      String content);

}
