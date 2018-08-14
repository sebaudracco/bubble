package mf.org.apache.xerces.impl.dv.xs;

import java.io.UnsupportedEncodingException;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.util.URI;
import mf.org.apache.xerces.util.URI.MalformedURIException;

public class AnyURIDV extends TypeValidator {
    private static final URI BASE_URI;
    private static char[] gAfterEscaping1 = new char[128];
    private static char[] gAfterEscaping2 = new char[128];
    private static char[] gHexChs = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static boolean[] gNeedEscaping = new boolean[128];

    static {
        URI uri = null;
        try {
            uri = new URI("abc://def.ghi.jkl");
        } catch (MalformedURIException e) {
        }
        BASE_URI = uri;
        for (int i = 0; i <= 31; i++) {
            gNeedEscaping[i] = true;
            gAfterEscaping1[i] = gHexChs[i >> 4];
            gAfterEscaping2[i] = gHexChs[i & 15];
        }
        gNeedEscaping[127] = true;
        gAfterEscaping1[127] = '7';
        gAfterEscaping2[127] = 'F';
        for (char ch : new char[]{' ', '<', '>', '\"', '{', '}', '|', '\\', '^', '~', '`'}) {
            gNeedEscaping[ch] = true;
            gAfterEscaping1[ch] = gHexChs[ch >> 4];
            gAfterEscaping2[ch] = gHexChs[ch & 15];
        }
    }

    public short getAllowedFacets() {
        return (short) 2079;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            if (content.length() != 0) {
                URI uri = new URI(BASE_URI, encode(content));
            }
            return content;
        } catch (MalformedURIException e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_ANYURI});
        }
    }

    private static String encode(String anyURI) {
        int len = anyURI.length();
        StringBuffer buffer = new StringBuffer(len * 3);
        int i = 0;
        while (i < len) {
            int ch = anyURI.charAt(i);
            if (ch >= 128) {
                break;
            }
            if (gNeedEscaping[ch]) {
                buffer.append('%');
                buffer.append(gAfterEscaping1[ch]);
                buffer.append(gAfterEscaping2[ch]);
            } else {
                buffer.append((char) ch);
            }
            i++;
        }
        if (i < len) {
            try {
                for (byte b : anyURI.substring(i).getBytes("UTF-8")) {
                    if (b < (byte) 0) {
                        ch = b + 256;
                        buffer.append('%');
                        buffer.append(gHexChs[ch >> 4]);
                        buffer.append(gHexChs[ch & 15]);
                    } else if (gNeedEscaping[b]) {
                        buffer.append('%');
                        buffer.append(gAfterEscaping1[b]);
                        buffer.append(gAfterEscaping2[b]);
                    } else {
                        buffer.append((char) b);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                return anyURI;
            }
        }
        if (buffer.length() != len) {
            return buffer.toString();
        }
        return anyURI;
    }
}
