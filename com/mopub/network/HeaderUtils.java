package com.mopub.network;

import android.support.annotation.Nullable;
import com.mopub.common.util.ResponseHeader;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class HeaderUtils {
    @Nullable
    public static String extractHeader(Map<String, String> headers, ResponseHeader responseHeader) {
        return (String) headers.get(responseHeader.getKey());
    }

    public static Integer extractIntegerHeader(Map<String, String> headers, ResponseHeader responseHeader) {
        return formatIntHeader(extractHeader((Map) headers, responseHeader));
    }

    public static boolean extractBooleanHeader(Map<String, String> headers, ResponseHeader responseHeader, boolean defaultValue) {
        return formatBooleanHeader(extractHeader((Map) headers, responseHeader), defaultValue);
    }

    public static Integer extractPercentHeader(Map<String, String> headers, ResponseHeader responseHeader) {
        return formatPercentHeader(extractHeader((Map) headers, responseHeader));
    }

    @Nullable
    public static String extractPercentHeaderString(Map<String, String> headers, ResponseHeader responseHeader) {
        Integer percentHeaderValue = extractPercentHeader(headers, responseHeader);
        return percentHeaderValue != null ? percentHeaderValue.toString() : null;
    }

    public static String extractHeader(HttpResponse response, ResponseHeader responseHeader) {
        Header header = response.getFirstHeader(responseHeader.getKey());
        return header != null ? header.getValue() : null;
    }

    public static boolean extractBooleanHeader(HttpResponse response, ResponseHeader responseHeader, boolean defaultValue) {
        return formatBooleanHeader(extractHeader(response, responseHeader), defaultValue);
    }

    public static Integer extractIntegerHeader(HttpResponse response, ResponseHeader responseHeader) {
        return formatIntHeader(extractHeader(response, responseHeader));
    }

    public static int extractIntHeader(HttpResponse response, ResponseHeader responseHeader, int defaultValue) {
        Integer headerValue = extractIntegerHeader(response, responseHeader);
        return headerValue == null ? defaultValue : headerValue.intValue();
    }

    private static boolean formatBooleanHeader(@Nullable String headerValue, boolean defaultValue) {
        return headerValue == null ? defaultValue : headerValue.equals(SchemaSymbols.ATTVAL_TRUE_1);
    }

    private static Integer formatIntHeader(String headerValue) {
        try {
            return Integer.valueOf(Integer.parseInt(headerValue));
        } catch (Exception e) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            numberFormat.setParseIntegerOnly(true);
            try {
                return Integer.valueOf(numberFormat.parse(headerValue.trim()).intValue());
            } catch (Exception e2) {
                return null;
            }
        }
    }

    @Nullable
    private static Integer formatPercentHeader(@Nullable String headerValue) {
        if (headerValue == null) {
            return null;
        }
        Integer percentValue = formatIntHeader(headerValue.replace("%", ""));
        if (percentValue == null || percentValue.intValue() < 0 || percentValue.intValue() > 100) {
            return null;
        }
        return percentValue;
    }
}
