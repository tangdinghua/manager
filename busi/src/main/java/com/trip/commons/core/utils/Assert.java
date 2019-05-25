package com.trip.commons.core.utils;

import com.trip.commons.core.exception.ServiceException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 断言检测
 *
 * @author fqh
 * @create 2016-12-10 23:47
 */
public class Assert {

    public static void isTrue(boolean expression, String message) {
        if(!expression) {
            throw new ServiceException("7002", message);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "值应为为True");
    }

    public static void isNull(Object object, String message) {
        if(object != null) {
            throw new ServiceException("7002", message);
        }
    }

    public static void isNull(Object object) {
        isNull(object, "参数应该为空");
    }

    public static void notNull(Object object, String message) {
        if(object == null) {
            throw new ServiceException("7002", message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "值不能为空");
    }

    public static void hasLength(String text, String message) {
        if(!StringUtils.hasLength(text)) {
            throw new ServiceException("7002", message);
        }
    }

    public static void hasLength(String text) {
        hasLength(text, "值长度为0或者为空");
    }

    public static void hasText(String text, String message) {
        if(!StringUtils.hasText(text)) {
            throw new ServiceException("7002", message);
        }
    }

    public static void hasText(String text) {
        hasText(text, "值不能为空");
    }

    public static void doesNotContain(String textToSearch, String substring, String message) {
        if(StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw new ServiceException("7002", message);
        }
    }

    public static void doesNotContain(String textToSearch, String substring) {
        doesNotContain(textToSearch, substring, "值应该包含[" + substring + "]");
    }

    public static void notEmpty(Object[] array, String message) {
        if(ObjectUtils.isEmpty(array)) {
            throw new ServiceException("7002", message);
        }
    }

    public static void notEmpty(Object[] array) {
        notEmpty(array, "数组不能为空");
    }

    public static void noNullElements(Object[] array, String message) {
        if(array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if(element == null) {
                    throw new ServiceException("7002", message);
                }
            }
        }

    }

    public static void noNullElements(Object[] array) {
        noNullElements(array, "值不能包含null值");
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if(CollectionUtils.isEmpty(collection)) {
            throw new ServiceException("7002", message);
        }
    }

    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, "集合不能为空");
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if(CollectionUtils.isEmpty(map)) {
            throw new ServiceException("7002", message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, "Map不能为空");
    }

    public static void isInstanceOf(Class<?> clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type);
        if(!type.isInstance(obj)) {
            throw new ServiceException("7002", (StringUtils.hasLength(message)?message + " ":"") + "Object of class [" + (obj != null?obj.getClass().getName():"null") + "] must be an instance of " + type);
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType);
        if(subType == null || !superType.isAssignableFrom(subType)) {
            throw new ServiceException("7002", (StringUtils.hasLength(message)?message + " ":"") + subType + " is not assignable to " + superType);
        }
    }

    public static void state(boolean expression, String message) {
        if(!expression) {
            throw new IllegalStateException(message);
        }
    }

}
