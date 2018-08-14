package mf.org.apache.xml.serialize;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Locale;
import mf.org.apache.xerces.util.EncodingMap;

public class Encodings {
    static final String DEFAULT_ENCODING = "UTF8";
    static final int DEFAULT_LAST_PRINTABLE = 127;
    static final String JIS_DANGER_CHARS = "\\~¢£¥¬—―‖…‾‾∥∯〜＼～￠￡￢￣";
    static final int LAST_PRINTABLE_UNICODE = 65535;
    static final String[] UNICODE_ENCODINGS = new String[]{"Unicode", "UnicodeBig", "UnicodeLittle", "GB2312", DEFAULT_ENCODING, "UTF-16"};
    static Hashtable _encodings = new Hashtable();

    static EncodingInfo getEncodingInfo(String encoding, boolean allowJavaNames) throws UnsupportedEncodingException {
        EncodingInfo eInfo;
        if (encoding == null) {
            eInfo = (EncodingInfo) _encodings.get(DEFAULT_ENCODING);
            if (eInfo != null) {
                return eInfo;
            }
            eInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(DEFAULT_ENCODING), DEFAULT_ENCODING, 65535);
            _encodings.put(DEFAULT_ENCODING, eInfo);
            return eInfo;
        }
        encoding = encoding.toUpperCase(Locale.ENGLISH);
        String jName = EncodingMap.getIANA2JavaMapping(encoding);
        int i;
        if (jName != null) {
            eInfo = (EncodingInfo) _encodings.get(jName);
            if (eInfo != null) {
                return eInfo;
            }
            i = 0;
            while (i < UNICODE_ENCODINGS.length) {
                if (UNICODE_ENCODINGS[i].equalsIgnoreCase(jName)) {
                    eInfo = new EncodingInfo(encoding, jName, 65535);
                    break;
                }
                i++;
            }
            if (i == UNICODE_ENCODINGS.length) {
                eInfo = new EncodingInfo(encoding, jName, DEFAULT_LAST_PRINTABLE);
            }
            _encodings.put(jName, eInfo);
            return eInfo;
        } else if (allowJavaNames) {
            EncodingInfo.testJavaEncodingName(encoding);
            eInfo = (EncodingInfo) _encodings.get(encoding);
            if (eInfo != null) {
                return eInfo;
            }
            i = 0;
            while (i < UNICODE_ENCODINGS.length) {
                if (UNICODE_ENCODINGS[i].equalsIgnoreCase(encoding)) {
                    eInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(encoding), encoding, 65535);
                    break;
                }
                i++;
            }
            if (i == UNICODE_ENCODINGS.length) {
                eInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(encoding), encoding, DEFAULT_LAST_PRINTABLE);
            }
            _encodings.put(encoding, eInfo);
            return eInfo;
        } else {
            throw new UnsupportedEncodingException(encoding);
        }
    }
}
