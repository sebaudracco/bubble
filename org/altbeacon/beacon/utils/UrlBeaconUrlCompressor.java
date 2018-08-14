package org.altbeacon.beacon.utils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlBeaconUrlCompressor {
    private static final byte EDDYSTONE_URL_BIZ = (byte) 12;
    private static final byte EDDYSTONE_URL_BIZ_SLASH = (byte) 5;
    private static final byte EDDYSTONE_URL_COM = (byte) 7;
    private static final byte EDDYSTONE_URL_COM_SLASH = (byte) 0;
    private static final byte EDDYSTONE_URL_EDU = (byte) 9;
    private static final byte EDDYSTONE_URL_EDU_SLASH = (byte) 2;
    private static final int EDDYSTONE_URL_FQDN_GROUP = 3;
    private static final byte EDDYSTONE_URL_GOV = (byte) 13;
    private static final byte EDDYSTONE_URL_GOV_SLASH = (byte) 6;
    private static final byte EDDYSTONE_URL_INFO = (byte) 11;
    private static final byte EDDYSTONE_URL_INFO_SLASH = (byte) 4;
    private static final byte EDDYSTONE_URL_NET = (byte) 10;
    private static final byte EDDYSTONE_URL_NET_SLASH = (byte) 3;
    private static final byte EDDYSTONE_URL_ORG = (byte) 8;
    private static final byte EDDYSTONE_URL_ORG_SLASH = (byte) 1;
    private static final int EDDYSTONE_URL_PATH_GROUP = 5;
    private static final int EDDYSTONE_URL_PROTOCOL_GROUP = 1;
    private static final byte EDDYSTONE_URL_PROTOCOL_HTTP = (byte) 2;
    private static final byte EDDYSTONE_URL_PROTOCOL_HTTPS = (byte) 3;
    private static final byte EDDYSTONE_URL_PROTOCOL_HTTPS_WWW = (byte) 1;
    private static final byte EDDYSTONE_URL_PROTOCOL_HTTP_WWW = (byte) 0;
    private static final String EDDYSTONE_URL_REGEX = "^((?i)http|https):\\/\\/((?i)www\\.)?((?:[0-9a-zA-Z_-]+\\.?)+)(/?)([./0-9a-zA-Z_-]*)";
    private static final int EDDYSTONE_URL_SLASH_GROUP = 4;
    private static final int EDDYSTONE_URL_WWW_GROUP = 2;
    private static final byte TLD_NOT_ENCODABLE = (byte) -1;
    private static final String URL_HOST_WWW = "www.";
    private static final String URL_PROTOCOL_HTTP = "http";
    private static final String URL_PROTOCOL_HTTPS_COLON_SLASH_SLASH = "https://";
    private static final String URL_PROTOCOL_HTTPS_WWW_DOT = "https://www.";
    private static final String URL_PROTOCOL_HTTP_COLON_SLASH_SLASH = "http://";
    private static final String URL_PROTOCOL_HTTP_WWW_DOT = "http://www.";
    private static final String URL_TLD_DOT_BIZ = ".biz";
    private static final String URL_TLD_DOT_BIZ_SLASH = ".biz/";
    private static final String URL_TLD_DOT_COM = ".com";
    private static final String URL_TLD_DOT_COM_SLASH = ".com/";
    private static final String URL_TLD_DOT_EDU = ".edu";
    private static final String URL_TLD_DOT_EDU_SLASH = ".edu/";
    private static final String URL_TLD_DOT_GOV = ".gov";
    private static final String URL_TLD_DOT_GOV_SLASH = ".gov/";
    private static final String URL_TLD_DOT_INFO = ".info";
    private static final String URL_TLD_DOT_INFO_SLASH = ".info/";
    private static final String URL_TLD_DOT_NET = ".net";
    private static final String URL_TLD_DOT_NET_SLASH = ".net/";
    private static final String URL_TLD_DOT_ORG = ".org";
    private static final String URL_TLD_DOT_ORG_SLASH = ".org/";
    private static List<TLDMapEntry> tldMap = new ArrayList();

    private static class TLDMapEntry {
        public final byte encodedByte;
        public final String tld;

        public TLDMapEntry(String topLevelDomain, byte encodedTLDByte) {
            this.tld = topLevelDomain;
            this.encodedByte = encodedTLDByte;
        }
    }

    static {
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_COM_SLASH, (byte) 0));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_ORG_SLASH, (byte) 1));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_EDU_SLASH, (byte) 2));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_NET_SLASH, (byte) 3));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_INFO_SLASH, EDDYSTONE_URL_INFO_SLASH));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_BIZ_SLASH, EDDYSTONE_URL_BIZ_SLASH));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_GOV_SLASH, EDDYSTONE_URL_GOV_SLASH));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_COM, EDDYSTONE_URL_COM));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_ORG, EDDYSTONE_URL_ORG));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_EDU, EDDYSTONE_URL_EDU));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_NET, EDDYSTONE_URL_NET));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_INFO, EDDYSTONE_URL_INFO));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_BIZ, EDDYSTONE_URL_BIZ));
        tldMap.add(new TLDMapEntry(URL_TLD_DOT_GOV, EDDYSTONE_URL_GOV));
    }

    private static byte encodedByteForTopLevelDomain(String tld) {
        byte encodedByte = (byte) -1;
        boolean tldFound = false;
        Iterator<TLDMapEntry> iterator = tldMap.iterator();
        while (!tldFound && iterator.hasNext()) {
            TLDMapEntry entry = (TLDMapEntry) iterator.next();
            tldFound = entry.tld.equalsIgnoreCase(tld);
            if (tldFound) {
                encodedByte = entry.encodedByte;
            }
        }
        return encodedByte;
    }

    private static String topLevelDomainForByte(Byte encodedByte) {
        String tld = null;
        boolean tldFound = false;
        Iterator<TLDMapEntry> iterator = tldMap.iterator();
        while (!tldFound && iterator.hasNext()) {
            TLDMapEntry entry = (TLDMapEntry) iterator.next();
            tldFound = entry.encodedByte == encodedByte.byteValue();
            if (tldFound) {
                tld = entry.tld;
            }
        }
        return tld;
    }

    public static byte[] compress(String urlString) throws MalformedURLException {
        if (urlString != null) {
            byte[] byteBuffer = new byte[urlString.length()];
            Arrays.fill(byteBuffer, (byte) 0);
            Matcher urlMatcher = Pattern.compile(EDDYSTONE_URL_REGEX).matcher(urlString);
            if (urlMatcher.matches()) {
                String slash;
                boolean haswww = urlMatcher.group(2) != null;
                if (urlMatcher.group(1).toLowerCase().equalsIgnoreCase("http")) {
                    byteBuffer[0] = haswww ? (byte) 0 : (byte) 2;
                } else {
                    byteBuffer[0] = haswww ? (byte) 1 : (byte) 3;
                }
                int byteBufferIndex = 0 + 1;
                String[] domains = new String(urlMatcher.group(3).getBytes()).toLowerCase().split(Pattern.quote("."));
                boolean consumedSlash = false;
                if (domains != null) {
                    int writableDomainsCount;
                    Object periodBytes = new byte[]{(byte) 46};
                    if (domains.length == 1) {
                        writableDomainsCount = 1;
                    } else {
                        writableDomainsCount = domains.length - 1;
                    }
                    int domainIndex = 0;
                    int byteBufferIndex2 = byteBufferIndex;
                    while (domainIndex < writableDomainsCount) {
                        if (domainIndex > 0) {
                            System.arraycopy(periodBytes, 0, byteBuffer, byteBufferIndex2, periodBytes.length);
                            byteBufferIndex = byteBufferIndex2 + periodBytes.length;
                        } else {
                            byteBufferIndex = byteBufferIndex2;
                        }
                        byte[] domainBytes = domains[domainIndex].getBytes();
                        int domainLength = domainBytes.length;
                        System.arraycopy(domainBytes, 0, byteBuffer, byteBufferIndex, domainLength);
                        domainIndex++;
                        byteBufferIndex2 = byteBufferIndex + domainLength;
                    }
                    if (domains.length > 1) {
                        String tld = "." + domains[domains.length - 1];
                        slash = urlMatcher.group(4);
                        byte encodedTLDByte = encodedByteForTopLevelDomain(slash == null ? tld : tld + slash);
                        if (encodedTLDByte != (byte) -1) {
                            byteBufferIndex = byteBufferIndex2 + 1;
                            byteBuffer[byteBufferIndex2] = encodedTLDByte;
                            consumedSlash = slash != null;
                        } else {
                            Object tldBytes = tld.getBytes();
                            int tldLength = tldBytes.length;
                            System.arraycopy(tldBytes, 0, byteBuffer, byteBufferIndex2, tldLength);
                            byteBufferIndex = byteBufferIndex2 + tldLength;
                        }
                    } else {
                        byteBufferIndex = byteBufferIndex2;
                    }
                }
                if (!consumedSlash) {
                    slash = urlMatcher.group(4);
                    if (slash != null) {
                        int slashLength = slash.length();
                        System.arraycopy(slash.getBytes(), 0, byteBuffer, byteBufferIndex, slashLength);
                        byteBufferIndex += slashLength;
                    }
                }
                String path = urlMatcher.group(5);
                if (path != null) {
                    int pathLength = path.length();
                    System.arraycopy(path.getBytes(), 0, byteBuffer, byteBufferIndex, pathLength);
                    byteBufferIndex += pathLength;
                }
                byte[] compressedBytes = new byte[byteBufferIndex];
                System.arraycopy(byteBuffer, 0, compressedBytes, 0, compressedBytes.length);
                return compressedBytes;
            }
            throw new MalformedURLException();
        }
        throw new MalformedURLException();
    }

    public static String uncompress(byte[] compressedURL) {
        StringBuffer url = new StringBuffer();
        switch (compressedURL[0] & 15) {
            case 0:
                url.append(URL_PROTOCOL_HTTP_WWW_DOT);
                break;
            case 1:
                url.append(URL_PROTOCOL_HTTPS_WWW_DOT);
                break;
            case 2:
                url.append(URL_PROTOCOL_HTTP_COLON_SLASH_SLASH);
                break;
            case 3:
                url.append(URL_PROTOCOL_HTTPS_COLON_SLASH_SLASH);
                break;
        }
        byte lastByte = (byte) -1;
        for (int i = 1; i < compressedURL.length; i++) {
            byte b = compressedURL[i];
            if (lastByte == (byte) 0 && b == (byte) 0) {
                return url.toString();
            }
            lastByte = b;
            String tld = topLevelDomainForByte(Byte.valueOf(b));
            if (tld != null) {
                url.append(tld);
            } else {
                url.append((char) b);
            }
        }
        return url.toString();
    }
}
