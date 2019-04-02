package com.xrds.basic.component.dal.config;

import java.util.Properties;

import org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer;

public class JDBCPlaceholderConfigurer extends PreferencesPlaceholderConfigurer {
  @Override
  protected String resolvePlaceholder(String placeholder, Properties props) {
    if ("datasource.password".equals(placeholder)) {
      String encryptedPassword = props.getProperty(placeholder);
      return encryptedPassword;
    }
    return super.resolvePlaceholder(placeholder, props);
  }

}
