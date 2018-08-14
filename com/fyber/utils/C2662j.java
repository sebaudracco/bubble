package com.fyber.utils;

import com.fyber.exceptions.IdException;
import java.util.regex.Pattern;

/* compiled from: IdValidator */
public final class C2662j {
    private static Pattern f6623a = Pattern.compile("^[A-Z0-9_]+$");

    public static void m8518a(String str) throws IdException {
        if (StringUtils.nullOrEmpty(str)) {
            throw new IdException("An ID cannot be null or empty.");
        } else if (!f6623a.matcher(str).find()) {
            throw new IdException("An ID can only contain uppercase letters, numbers and the _ underscore symbol.");
        }
    }
}
