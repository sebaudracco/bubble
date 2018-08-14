package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class CommonUtils {
    public static <T> Collection<T> emptyIfNull(Collection<T> iterable) {
        return iterable == null ? Collections.emptyList() : iterable;
    }

    public static <T> T[] emptyIfNull(T[] array) {
        return array == null ? new Object[0] : array;
    }

    public static String convertListToSpaceDelimitedString(List<String> listOfStrings) {
        StringBuilder stringBuilder = new StringBuilder();
        if (listOfStrings != null) {
            String str = "";
            for (String str2 : listOfStrings) {
                String str22;
                if (TextUtils.isEmpty(str22)) {
                    str22 = str;
                } else {
                    stringBuilder.append(str);
                    stringBuilder.append(str22);
                    str22 = " ";
                }
                str = str22;
            }
        }
        return stringBuilder.toString();
    }
}
