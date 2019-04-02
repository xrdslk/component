package com.xrds.basic.test.druid;

import org.junit.Test;

import com.alibaba.druid.filter.config.ConfigTools;
import com.xrds.basic.test.BaseTest;



public class DruidTester extends BaseTest {
  private ConfigTools configTools;

  public ConfigTools getConfigTools() {
    return configTools;
  }

  public void setConfigTools(ConfigTools configTools) {
    this.configTools = configTools;
  }

  @SuppressWarnings("static-access")
  @Test
  public void test() {
    try {
      System.out.println(configTools.encrypt("java@lh"));
    } catch (Exception e) {}
  }
}
