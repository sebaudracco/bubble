package com.p000a.p001a;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.lang.reflect.Field;
import java.util.Locale;

public enum C0536c implements C0535d {
    IDENTITY {
        public String mo1832a(Field field) {
            return field.getName();
        }
    },
    UPPER_CAMEL_CASE {
        public String mo1832a(Field field) {
            return C0536c.m393a(field.getName());
        }
    },
    UPPER_CAMEL_CASE_WITH_SPACES {
        public String mo1832a(Field field) {
            return C0536c.m393a(C0536c.m394a(field.getName(), " "));
        }
    },
    LOWER_CASE_WITH_UNDERSCORES {
        public String mo1832a(Field field) {
            return C0536c.m394a(field.getName(), BridgeUtil.UNDERLINE_STR).toLowerCase(Locale.ENGLISH);
        }
    },
    LOWER_CASE_WITH_DASHES {
        public String mo1832a(Field field) {
            return C0536c.m394a(field.getName(), "-").toLowerCase(Locale.ENGLISH);
        }
    };

    private static String m392a(char c, String str, int i) {
        return i < str.length() ? c + str.substring(i) : String.valueOf(c);
    }

    static String m393a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        char charAt = str.charAt(0);
        int length = str.length();
        while (i < length - 1 && !Character.isLetter(charAt)) {
            stringBuilder.append(charAt);
            i++;
            charAt = str.charAt(i);
        }
        return !Character.isUpperCase(charAt) ? stringBuilder.append(C0536c.m392a(Character.toUpperCase(charAt), str, i + 1)).toString() : str;
    }

    static String m394a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && stringBuilder.length() != 0) {
                stringBuilder.append(str2);
            }
            stringBuilder.append(charAt);
        }
        return stringBuilder.toString();
    }
}
