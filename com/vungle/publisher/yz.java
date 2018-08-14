package com.vungle.publisher;

import java.lang.reflect.Array;

/* compiled from: vungle */
public class yz {
    public static <T> T[] m14183a(T[]... tArr) {
        Class cls = null;
        int i = 0;
        for (Object obj : tArr) {
            if (obj != null) {
                i += obj.length;
                cls = obj.getClass();
            }
        }
        if (cls == null) {
            return null;
        }
        Object[] objArr = (Object[]) Array.newInstance(cls.getComponentType(), i);
        i = 0;
        for (Object obj2 : tArr) {
            if (obj2 != null) {
                System.arraycopy(obj2, 0, objArr, i, obj2.length);
                i += obj2.length;
            }
        }
        return objArr;
    }

    public static String[] m14184a(Object[] objArr) {
        int i = 0;
        if (objArr == null) {
            return new String[0];
        }
        String[] strArr = new String[objArr.length];
        while (i < objArr.length) {
            strArr[i] = String.valueOf(objArr[i]);
            i++;
        }
        return strArr;
    }
}
