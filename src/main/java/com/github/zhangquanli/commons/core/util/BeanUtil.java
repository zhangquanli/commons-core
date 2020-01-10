package com.github.zhangquanli.commons.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 对象拷贝工具
 *
 * @author zhangquanli
 */
public final class BeanUtil {
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 将源对象中的属性拷贝到目标对象
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            String message = "对象拷贝工具>>>对象不能为空";
            RuntimeException e = new RuntimeException(message);
            logger.error(message, e);
            throw e;
        }
        // 获取对象属性信息
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(target.getClass());
        } catch (IntrospectionException e) {
            logger.error("对象拷贝工具>>>获取属性失败>>>" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
        // 遍历并拷贝属性信息
        for (PropertyDescriptor targetPd : beanInfo.getPropertyDescriptors()) {
            // class属性无需拷贝
            if ("class".equals(targetPd.getName())) {
                continue;
            }
            // 目标对象的readMethod不能为null
            Method readMethod;
            try {
                PropertyDescriptor sourcePd = new PropertyDescriptor(targetPd.getName(), source.getClass());
                readMethod = sourcePd.getReadMethod();
                if (readMethod == null) {
                    continue;
                }
            } catch (IntrospectionException e) {
                continue;
            }
            // 源对象的writeMethod不能为null
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod == null) {
                continue;
            }
            // 拷贝属性
            try {
                Object invoke = readMethod.invoke(source);
                writeMethod.invoke(target, invoke);
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error("对象拷贝工具>>>拷贝属性失败>>>" + e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 创建目标对象，再将源对象中的属性拷贝到目标对象
     *
     * @param source      源对象
     * @param targetClass 目标对象的类型
     * @param <T>         目标对象的类型
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        T target;
        try {
            target = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("对象拷贝工具>>>创建对象失败>>>" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
        copyProperties(source, target);
        return target;
    }

    private BeanUtil() {
    }
}
