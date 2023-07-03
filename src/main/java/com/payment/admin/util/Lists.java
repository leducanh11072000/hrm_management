package com.payment.admin.util;

import java.util.ArrayList;
import java.util.List;

public class Lists {

    public static <V> List<V> newArrayList(V... vs) {
        List<V> list = new ArrayList<V>();
        for (V v : vs) {
            list.add(v);
        }
        return list;
    }
}
