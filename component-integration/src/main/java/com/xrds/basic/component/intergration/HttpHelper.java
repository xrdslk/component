package com.xrds.basic.component.intergration;

import org.apache.http.client.HttpClient;

import com.kunpu.frameworks.commons.http.HttpContactAble;

public class HttpHelper extends HttpContactAble {

  @Override
  protected HttpClient buildHttpClient() throws Exception {
    return null;
  }

}
