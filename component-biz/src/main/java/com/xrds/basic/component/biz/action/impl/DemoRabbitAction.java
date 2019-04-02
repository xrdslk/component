package com.xrds.basic.component.biz.action.impl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.kunpu.middleware.rabbit.enums.AckedType;
import com.rabbitmq.client.Channel;

/**
 * 接收MQ通知Demo
 * 
 * @author yuyangzhi
 * @version $Id: BankCardQueryAction.java, v 0.1 2017年6月22日 下午5:08:09 yuyangzhi Exp $
 */
public class DemoRabbitAction implements ChannelAwareMessageListener {

  public static final LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_BIZ
      .getLogName());

  @Override
  public void onMessage(Message message, Channel channel) throws Exception {
    AckedType action = AckedType.RETRY;
    long deliveryTag = message.getMessageProperties().getDeliveryTag();
    try {
      // 接收消息队列通知
      LOGGER.info("consumer receive bindCard message-------->:{}", message);
      // 接收到通知之后请求更新绑卡状态action
      System.out.println(message);
      // 如果成功完成则action=Action.ACCEPT
      action = AckedType.ACCEPT;
    } catch (Exception e) {
      // 根据异常种类决定是ACCEPT、RETRY还是 REJECT
      action = AckedType.REJECT;
    } finally {
      // 成功:自动删除消息
      if (action == AckedType.ACCEPT) {
        channel.basicAck(deliveryTag, false);

        // 失败重试:一直到有某个客户端可以正常消费这个消息
      } else if (action == AckedType.RETRY) {
        channel.basicNack(deliveryTag, false, true);

        // 丢弃消息:如果存在死信队列则发送到死信队列等待人工处理，否则直接丢弃
      } else {
        channel.basicNack(deliveryTag, false, false);
      }
    }
  }

}
