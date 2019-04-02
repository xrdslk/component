package com.xrds.basic.component.web.filter;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

import com.dianping.cat.message.Event;
import com.dianping.cat.message.internal.AbstractMessage;
import com.kunpu.frameworks.cat.util.CatUtil;


public class CatDispatcherServlet extends DispatcherServlet {
  /**  */
  private static final long serialVersionUID = -6118649385080363853L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    if (!CatUtil.isCatEnable()) {
      super.service(request, response);
      return;
    }
    // String uriString = request.getRequestURI();
    // if ("/".equals(request.getRequestURI())) {
    super.service(request, response);
    return;
    // }


    // try {
    // Transaction transaction = Cat.newTransaction("PigeonCall", request.getRequestURI());
    // Event crossServerEvent = Cat.newEvent("PigeonCall.server", request.getRemoteAddr());
    // Event crossPortEvent = Cat.newEvent("PigeonCall.port", request.getRemoteHost() + "");
    // Event protocolEvent = Cat.newEvent("PigeonCall.user", request.getRemoteUser());
    // protocolEvent.setStatus("0");
    // crossServerEvent.setStatus("0");
    // crossPortEvent.setStatus("0");
    // completeEvent(protocolEvent);
    // completeEvent(crossPortEvent);
    // completeEvent(crossServerEvent);
    // transaction.addChild(protocolEvent);
    // transaction.addChild(crossPortEvent);
    // transaction.addChild(crossServerEvent);
    // super.service(request, response);
    // transaction.setStatus(Transaction.SUCCESS);
    // } catch (Exception e) {
    // // transaction.setStatus(e);
    // e.printStackTrace();
    // } finally {
    // // transaction.complete();
    // }
  }

  private void completeEvent(Event event) {
    AbstractMessage message = (AbstractMessage) event;
    message.setCompleted(true);
  }
}
