package com.queryinterface.cflite.cloudcontroller.common;

import java.util.ArrayList;
import java.util.List;

public class IterableUtils {

    public static <T> List<T> toList(Iterable<T> it) {
        List<T> result = new ArrayList<>();
        it.forEach(result::add);
        return result;
    }
}
