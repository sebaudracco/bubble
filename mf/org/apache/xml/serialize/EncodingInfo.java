package mf.org.apache.xml.serialize;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;
import mf.org.apache.xerces.util.EncodingMap;

public class EncodingInfo {
    private Object[] fArgsForMethod = null;
    Object fCharToByteConverter = null;
    Object fCharsetEncoder = null;
    boolean fHaveTriedCToB = false;
    boolean fHaveTriedCharsetEncoder = false;
    String ianaName;
    String javaName;
    int lastPrintable;

    static class CharToByteConverterMethods {
        private static Method fgCanConvertMethod;
        private static boolean fgConvertersAvailable;
        private static Method fgGetConverterMethod;

        static {
            fgGetConverterMethod = null;
            fgCanConvertMethod = null;
            fgConvertersAvailable = false;
            try {
                Class clazz = Class.forName("sun.io.CharToByteConverter");
                fgGetConverterMethod = clazz.getMethod("getConverter", new Class[]{String.class});
                fgCanConvertMethod = clazz.getMethod("canConvert", new Class[]{Character.TYPE});
                fgConvertersAvailable = true;
            } catch (Exception e) {
                fgGetConverterMethod = null;
                fgCanConvertMethod = null;
                fgConvertersAvailable = false;
            }
        }

        private CharToByteConverterMethods() {
        }
    }

    static class CharsetMethods {
        private static Method fgCharsetCanEncodeMethod;
        private static Method fgCharsetEncoderCanEncodeMethod;
        private static Method fgCharsetForNameMethod;
        private static Method fgCharsetNewEncoderMethod;
        private static boolean fgNIOCharsetAvailable;

        static {
            fgCharsetForNameMethod = null;
            fgCharsetCanEncodeMethod = null;
            fgCharsetNewEncoderMethod = null;
            fgCharsetEncoderCanEncodeMethod = null;
            fgNIOCharsetAvailable = false;
            try {
                Class charsetClass = Class.forName("java.nio.charset.Charset");
                Class charsetEncoderClass = Class.forName("java.nio.charset.CharsetEncoder");
                fgCharsetForNameMethod = charsetClass.getMethod("forName", new Class[]{String.class});
                fgCharsetCanEncodeMethod = charsetClass.getMethod("canEncode", new Class[0]);
                fgCharsetNewEncoderMethod = charsetClass.getMethod("newEncoder", new Class[0]);
                fgCharsetEncoderCanEncodeMethod = charsetEncoderClass.getMethod("canEncode", new Class[]{Character.TYPE});
                fgNIOCharsetAvailable = true;
            } catch (Exception e) {
                fgCharsetForNameMethod = null;
                fgCharsetCanEncodeMethod = null;
                fgCharsetEncoderCanEncodeMethod = null;
                fgCharsetNewEncoderMethod = null;
                fgNIOCharsetAvailable = false;
            }
        }

        private CharsetMethods() {
        }
    }

    public EncodingInfo(String ianaName, String javaName, int lastPrintable) {
        this.ianaName = ianaName;
        this.javaName = EncodingMap.getIANA2JavaMapping(ianaName);
        this.lastPrintable = lastPrintable;
    }

    public String getIANAName() {
        return this.ianaName;
    }

    public Writer getWriter(OutputStream output) throws UnsupportedEncodingException {
        if (this.javaName != null) {
            return new OutputStreamWriter(output, this.javaName);
        }
        this.javaName = EncodingMap.getIANA2JavaMapping(this.ianaName);
        if (this.javaName == null) {
            return new OutputStreamWriter(output, "UTF8");
        }
        return new OutputStreamWriter(output, this.javaName);
    }

    public boolean isPrintable(char ch) {
        if (ch <= this.lastPrintable) {
            return true;
        }
        return isPrintable0(ch);
    }

    private boolean isPrintable0(char ch) {
        if (this.fCharsetEncoder == null && CharsetMethods.fgNIOCharsetAvailable && !this.fHaveTriedCharsetEncoder) {
            if (this.fArgsForMethod == null) {
                this.fArgsForMethod = new Object[1];
            }
            try {
                this.fArgsForMethod[0] = this.javaName;
                Object charset = CharsetMethods.fgCharsetForNameMethod.invoke(null, this.fArgsForMethod);
                if (((Boolean) CharsetMethods.fgCharsetCanEncodeMethod.invoke(charset, null)).booleanValue()) {
                    this.fCharsetEncoder = CharsetMethods.fgCharsetNewEncoderMethod.invoke(charset, null);
                } else {
                    this.fHaveTriedCharsetEncoder = true;
                }
            } catch (Exception e) {
                this.fHaveTriedCharsetEncoder = true;
            }
        }
        if (this.fCharsetEncoder != null) {
            try {
                this.fArgsForMethod[0] = new Character(ch);
                return ((Boolean) CharsetMethods.fgCharsetEncoderCanEncodeMethod.invoke(this.fCharsetEncoder, this.fArgsForMethod)).booleanValue();
            } catch (Exception e2) {
                this.fCharsetEncoder = null;
                this.fHaveTriedCharsetEncoder = false;
            }
        }
        if (this.fCharToByteConverter == null) {
            if (this.fHaveTriedCToB || !CharToByteConverterMethods.fgConvertersAvailable) {
                return false;
            }
            if (this.fArgsForMethod == null) {
                this.fArgsForMethod = new Object[1];
            }
            try {
                this.fArgsForMethod[0] = this.javaName;
                this.fCharToByteConverter = CharToByteConverterMethods.fgGetConverterMethod.invoke(null, this.fArgsForMethod);
            } catch (Exception e3) {
                this.fHaveTriedCToB = true;
                return false;
            }
        }
        try {
            this.fArgsForMethod[0] = new Character(ch);
            return ((Boolean) CharToByteConverterMethods.fgCanConvertMethod.invoke(this.fCharToByteConverter, this.fArgsForMethod)).booleanValue();
        } catch (Exception e4) {
            this.fCharToByteConverter = null;
            this.fHaveTriedCToB = false;
            return false;
        }
    }

    public static void testJavaEncodingName(String name) throws UnsupportedEncodingException {
        String s = new String(new byte[]{(byte) 118, (byte) 97, (byte) 108, (byte) 105, (byte) 100}, name);
    }
}
