/**
 * 
 */
package com.xrds.basic.component.common.util.log;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {
    Connection.class, Integer.class})})
public class SQLLogInterceptor implements Interceptor {
  protected LoggerAdapter log;

  public SQLLogInterceptor() {
    this.log = LoggerAdapterFactory.getLogger(CommonLogType.SYS_DAL.getLogName());
  }

  public Object intercept(Invocation invocation) throws Throwable {
    try {
      StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
      BaseStatementHandler delegate =
          (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
      MappedStatement mappedStatement =
          (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");

      if (delegate != null) {
        BoundSql boundSql = delegate.getBoundSql();
        Object parameterObject = boundSql.getParameterObject();

        StringBuffer sb = new StringBuffer(100);

        sb.append("sql:====>\r\n");
        sb.append(removeBreakingWhitespace(boundSql.getSql()));
        sb.append("\r\n");

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
          Configuration configuration = mappedStatement.getConfiguration();
          TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
          MetaObject metaObject =
              (parameterObject == null) ? null : configuration.newMetaObject(parameterObject);
          for (int i = 0; i < parameterMappings.size(); ++i) {
            ParameterMapping parameterMapping = (ParameterMapping) parameterMappings.get(i);
            if (parameterMapping.getMode() != ParameterMode.OUT) {
              Object value;
              String propertyName = parameterMapping.getProperty();
              PropertyTokenizer prop = new PropertyTokenizer(propertyName);
              if (parameterObject == null)
                value = null;
              else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass()))
                value = parameterObject;
              else if (boundSql.hasAdditionalParameter(propertyName))
                value = boundSql.getAdditionalParameter(propertyName);
              else if ((propertyName.startsWith("__frch_"))
                  && (boundSql.hasAdditionalParameter(prop.getName()))) {
                value = boundSql.getAdditionalParameter(prop.getName());
                if (value != null)
                  value =
                      configuration.newMetaObject(value).getValue(
                          propertyName.substring(prop.getName().length()));
              } else {
                value = (metaObject == null) ? null : metaObject.getValue(propertyName);
              }

              sb.append("[");
              sb.append(propertyName);
              sb.append(":");
              sb.append(value);
              sb.append("]");
            }
          }
        }
        sb.append("==================================");
        this.log.info(sb.toString());
      } else {
        this.log.warn("检测是否重复配置SQLLogInterceptor");
      }
    } catch (Exception e) {
      this.log.error("未知异常!" + e.getMessage(), e);
    }

    return invocation.proceed();
  }

  protected String removeBreakingWhitespace(String original) {
    StringTokenizer whitespaceStripper = new StringTokenizer(original);
    StringBuilder builder = new StringBuilder();
    for (; whitespaceStripper.hasMoreTokens(); builder.append(" "))
      builder.append(whitespaceStripper.nextToken());

    return builder.toString();
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  public void setProperties(Properties arg0) {}

}
