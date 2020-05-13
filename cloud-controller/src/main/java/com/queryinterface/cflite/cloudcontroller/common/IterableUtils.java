package com.queryinterface.cflite.cloudcontroller.common;

import java.util.ArrayList;
import java.util.List;

public class IterableUtils {

    public static <T> List<T> toList(Iterable<T> it) {
        List<T> result = new ArrayList<>();
        it.forEach(result::add);
        return result;
    }

    public static <T> List<T> of(T... elems) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < elems.length; i++) {
            result.add(elems[i]);
        }
        return result;
    }
}
