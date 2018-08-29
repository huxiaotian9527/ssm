package com.hu.ssm.util;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 常用工具类
 */
public class CommonUtil {

    /**
     * 把一个Object对象转换成Map对象
     */
    public static Map obj2Map(Object obj) {
        Map map = new HashMap();
        Class clazz = obj.getClass();
        List<Field> fieldList = findAllFieldsOfSelfAndSuperClass(clazz);
        Field field = null;
        try {
            for (int i = 0; i < fieldList.size(); i++) {
                field = fieldList.get(i);
                Object value = getProperty(obj, field.getName());
                if(null == value){  //值为null，转成""
                    map.put(field.getName(),"");
                }else {
                    map.put(field.getName(),getProperty(obj, field.getName()));
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取一个类和其父类的所有属性
     */
    private static List<Field> findAllFieldsOfSelfAndSuperClass(Class clazz) {
        Field[] fields = null;
        List fieldList = new ArrayList();
        while (true) {
            if (clazz == null) {
                break;
            } else {
                fields = clazz.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    //过滤掉 @Ignore 的字段
                    Ignore ignore = fields[i].getAnnotation(Ignore.class);
                    if(ignore==null){
                        fieldList.add(fields[i]);
                    }
                }
                clazz = clazz.getSuperclass();
            }
        }
        return fieldList;
    }

    /**
     * 通过get方法获取到对应的值
     */
    private static Object getProperty(Object obj, String propertyName) {
        Class clazz = obj.getClass();// 获取对象的类型
        String method = "get"+propertyName.substring(0, 1).toUpperCase()+ propertyName.substring(1);
        Method getMethod = null;
        //如果本类找不到对应的get方法，尝试从父类中获取get方法
        while (true) {
            if (clazz == null) {
                break;
            } else {
                try {
                    getMethod = clazz.getDeclaredMethod(method);
                } catch (Exception e) {
                    //do nothing
                }
                if(getMethod!=null){
                    break;
                }
                clazz = clazz.getSuperclass();
            }
        }
        Object value = null;
        try {
            value = getMethod.invoke(obj, new Object[]{});// 调用方法获取方法的返回值
        } catch (Exception e) {
            //do nothing
        }
        return value;// 返回值
    }
}