package cz.msebera.android.httpclient.util;

import cz.msebera.android.httpclient.Consts;
import java.io.UnsupportedEncodingException;

public final class EncodingUtils {
    public static String getString(byte[] data, int offset, int length, String charset) {
        Args.notNull(data, "Input");
        Args.notEmpty((CharSequence) charset, "Charset");
        try {
            return new String(data, offset, length, charset);
        } catch (UnsupportedEncodingException e) {
            return new String(data, offset, length);
        }
    }

    public static String getString(byte[] data, String charset) {
        Args.notNull(data, "Input");
        return getString(data, 0, data.length, charset);
    }

    public static byte[] getBytes(String data, String charset) {
        Args.notNull(data, "Input");
        Args.notEmpty((CharSequence) charset, "Charset");
        try {
            return data.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            return data.getBytes();
        }
    }

    public static byte[] getAsciiBytes(String data) {
        Args.notNull(data, "Input");
        try {
            return data.getBytes(Consts.ASCII.name());
        } catch (UnsupportedEncodingException e) {
            throw new Error("ASCII not supported");
        }
    }

    public static String getAsciiString(byte[] data, int offset, int length) {
        Args.notNull(data, "Input");
        try {
            return new String(data, offset, length, Consts.ASCII.name());
        } catch (UnsupportedEncodingException e) {
            throw new Error("ASCII not supported");
        }
    }

    public static String getAsciiString(byte[] data) {
        Args.notNull(data, "Input");
        return getAsciiString(data, 0, data.length);
    }

    private EncodingUtils() {
    }
}
