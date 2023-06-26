package com.byborgip.excercises.utils;

import org.apache.commons.lang3.StringUtils;

public class URLUtils {
    public static String normaliseHostName(String name) {
        if(StringUtils.startsWithAny(name, "http://", "http://"))
            return name;
        return  StringUtils.join("http://", name);
    }
}
