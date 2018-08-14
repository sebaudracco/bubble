package com.vungle.publisher;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.vungle.publisher.log.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: vungle */
public final class qr {
    static final Pattern f10948a = Pattern.compile("[|\\\\?*<\":>'&\\[\\]]");

    public static String m13842a(Object... objArr) {
        if (objArr == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Object obj = objArr[i];
            if (obj == null) {
                throw new IllegalArgumentException("null path element at index " + i);
            }
            stringBuilder.append(obj);
            int length2 = stringBuilder.length();
            if (length2 > 0 && i + 1 < length && stringBuilder.charAt(length2 - 1) != File.separatorChar) {
                stringBuilder.append(File.separatorChar);
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public static boolean m13845a(String str) {
        return m13843a(new File(str));
    }

    public static boolean m13843a(File file) {
        boolean b = m13846b(file);
        if (b) {
            Logger.m13635d(Logger.FILE_TAG, "successfully deleted: " + file);
        } else {
            Logger.m13647w(Logger.FILE_TAG, "could not delete: " + file);
        }
        return b;
    }

    static boolean m13846b(File file) {
        Deque arrayDeque = new ArrayDeque();
        arrayDeque.push(file);
        boolean z = true;
        for (File file2 = (File) arrayDeque.peek(); file2 != null; file2 = (File) arrayDeque.peek()) {
            boolean z2;
            if (file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (Object push : listFiles) {
                        arrayDeque.push(push);
                    }
                    z2 = z;
                    z = z2;
                }
            }
            arrayDeque.pop();
            z = z && (!file2.exists() || file2.delete());
            z2 = z;
            z = z2;
        }
        return z;
    }

    public static boolean m13849c(File file) {
        if (file == null) {
            Logger.m13647w(Logger.FILE_TAG, "null directory path");
            return false;
        } else if (file.mkdirs()) {
            Logger.m13635d(Logger.FILE_TAG, "created directory: " + file);
            return true;
        } else if (file.isDirectory()) {
            Logger.m13644v(Logger.FILE_TAG, "directory exists: " + file);
            return true;
        } else {
            Logger.m13635d(Logger.FILE_TAG, "unable to create directory: " + file);
            return false;
        }
    }

    public static boolean m13850d(File file) {
        File parentFile = file == null ? null : file.getParentFile();
        if (!m13849c(parentFile)) {
            return false;
        }
        boolean canWrite = parentFile.canWrite();
        if (canWrite) {
            Logger.m13644v(Logger.FILE_TAG, "directory is writeable: " + parentFile);
            return canWrite;
        }
        Logger.m13635d(Logger.FILE_TAG, "directory not writeable: " + parentFile);
        return canWrite;
    }

    public static boolean m13847b(String str) {
        return !f10948a.matcher(str).find();
    }

    public static String m13848c(String str) {
        Matcher matcher = f10948a.matcher(str);
        if (!matcher.find()) {
            return str;
        }
        String replaceAll = matcher.replaceAll(BridgeUtil.UNDERLINE_STR);
        Logger.m13641i(Logger.FILE_TAG, "Unsafe character(s) found / replaced in filepath: " + str + " --> " + replaceAll);
        return replaceAll;
    }

    public static boolean m13844a(File file, File file2) throws IOException {
        String canonicalPath = file2.getCanonicalPath();
        String canonicalPath2 = file.getCanonicalPath();
        return (canonicalPath == null || canonicalPath.equals(canonicalPath2) || !canonicalPath.startsWith(canonicalPath2)) ? false : true;
    }
}
