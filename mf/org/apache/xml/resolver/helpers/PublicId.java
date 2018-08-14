package mf.org.apache.xml.resolver.helpers;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import org.slf4j.Marker;

public abstract class PublicId {
    protected PublicId() {
    }

    public static String normalize(String publicId) {
        String normal = publicId.replace('\t', ' ').replace('\r', ' ').replace('\n', ' ').trim();
        while (true) {
            int pos = normal.indexOf("  ");
            if (pos < 0) {
                return normal;
            }
            normal = normal.substring(0, pos) + normal.substring(pos + 1);
        }
    }

    public static String encodeURN(String publicId) {
        return "urn:publicid:" + stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(normalize(publicId), "%", "%25"), ";", "%3B"), "'", "%27"), "?", "%3F"), "#", "%23"), Marker.ANY_NON_NULL_MARKER, "%2B"), " ", Marker.ANY_NON_NULL_MARKER), "::", ";"), ":", "%3A"), "//", ":"), BridgeUtil.SPLIT_MARK, "%2F");
    }

    public static String decodeURN(String urn) {
        String publicId = "";
        if (urn.startsWith("urn:publicid:")) {
            return stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(stringReplace(urn.substring(13), "%2F", BridgeUtil.SPLIT_MARK), ":", "//"), "%3A", ":"), ";", "::"), Marker.ANY_NON_NULL_MARKER, " "), "%2B", Marker.ANY_NON_NULL_MARKER), "%23", "#"), "%3F", "?"), "%27", "'"), "%3B", ";"), "%25", "%");
        }
        return urn;
    }

    private static String stringReplace(String str, String oldStr, String newStr) {
        String result = "";
        int pos = str.indexOf(oldStr);
        while (pos >= 0) {
            result = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(result)).append(str.substring(0, pos)).toString())).append(newStr).toString();
            str = str.substring(pos + 1);
            pos = str.indexOf(oldStr);
        }
        return new StringBuilder(String.valueOf(result)).append(str).toString();
    }
}
