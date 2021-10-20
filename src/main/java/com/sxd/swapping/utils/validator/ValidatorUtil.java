package com.sxd.swapping.utils.validator;

import com.sxd.swapping.globalException.customException.ValidateArgumentException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

public class ValidatorUtil {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new ValidateArgumentException(message);
        }
    }

    public static void notEmpty(Collection coll, String message) {
        if (CollectionUtils.isEmpty(coll)) {
            throw new ValidateArgumentException(message);
        }
    }

    public static void notEmpty(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new ValidateArgumentException(message);
        }
    }

    public static void notEmpty(Map map, String message) {
        if (map == null || map.size() <= 0) {
            throw new ValidateArgumentException(message);
        }
    }

    public static void minLength(String str, Integer length, String message) {
        if (StringUtils.length(str) < length) {
            throw new ValidateArgumentException(message);
        }
    }

    public static void maxLength(String str, Integer length, String message) {
        if (StringUtils.length(str) > length) {
            throw new ValidateArgumentException(message);
        }
    }

    public static void min(Comparable source, Comparable min, String message) {
        if (source.compareTo(min) < 0) {
            throw new ValidateArgumentException(message);
        }
    }

    public static void max(Comparable source, Comparable max, String message) {
        if (source.compareTo(max) > 0) {
            throw new ValidateArgumentException(message);
        }
    }

    public static void range(Comparable source, Comparable min, Comparable max, String message) {
        if (source.compareTo(min) < 0 || source.compareTo(max) > 0) {
            throw new ValidateArgumentException(message);
        }
    }

    public static void range(Comparable source, Comparable min, Comparable max, boolean includeEdge, String message) {
        if (includeEdge) {
            range(source, min, max, message);
        } else {
            if (source.compareTo(min) <= 0 || source.compareTo(max) >= 0) {
                throw new ValidateArgumentException(message);
            }
        }
    }

    public static void equals(Object source, Object target, String message) {
        if (!source.equals(target)) {
            throw new ValidateArgumentException(message);
        }
    }

    //满足就报错
    public static void is(Boolean flag,  String message) {
        if (flag) {
            throw new ValidateArgumentException(message);
        }
    }
}
