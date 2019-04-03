package com.xrds.basic.component.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kunpu.frameworks.commons.code.CommonErrorCode;
import com.kunpu.frameworks.commons.exception.business.BusinessRuntimeException;

/**
 * 对象/Map互转
 * 
 * @author fuli
 * @version 0.1
 * @date 2017年12月18日
 */
public interface MapTransformable {
  default public Map<String, Object> toMap(Object target) throws BusinessRuntimeException {
    if (target == null) return null;
    Map<String, Object> map = new HashMap<>();
    try {
      for (Field field : getFields(target.getClass())) {
        field.setAccessible(true);
        map.put(field.getName(), field.get(target));
      }
    } catch (Exception e) {
      throw new BusinessRuntimeException(CommonErrorCode.COMMON_999.getCode(), "对象转换Map异常", e);
    }
    return map;
  }

  default public <T> T toObject(Map<String, Object> map, Class<T> clazz)
      throws BusinessRuntimeException {
    if (clazz == null) return null;
    T t = null;
    try {
      t = clazz.newInstance();
      for (Field field : getFields(clazz)) {
        field.setAccessible(true);
        field.set(t, map.get(field.getName()));
      }
    } catch (Exception e) {
      throw new BusinessRuntimeException(CommonErrorCode.COMMON_999.getCode(), "Map转换对象异常", e);
    }
    return t;
  }

  default List<Field> getFields(Class<?> clazz) {
    List<Field> fieldList = new ArrayList<>();
    while (clazz != null && clazz != Object.class) {
      fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()).stream().filter((field) -> {
        try {
          return field.getModifiers() <= Modifier.PROTECTED;
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }).collect(Collectors.toList()));
      clazz = clazz.getSuperclass();
    }
    return fieldList;
  }
}
