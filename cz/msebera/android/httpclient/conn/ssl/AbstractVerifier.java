package cz.msebera.android.httpclient.conn.ssl;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.conn.util.InetAddressUtils;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.slf4j.Marker;

@Immutable
public abstract class AbstractVerifier implements X509HostnameVerifier {
    private static final String[] BAD_COUNTRY_2LDS = new String[]{"ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org"};
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    static {
        Arrays.sort(BAD_COUNTRY_2LDS);
    }

    public final void verify(String host, SSLSocket ssl) throws IOException {
        if (host == null) {
            throw new NullPointerException("host to verify is null");
        }
        SSLSession session = ssl.getSession();
        if (session == null) {
            ssl.getInputStream().available();
            session = ssl.getSession();
            if (session == null) {
                ssl.startHandshake();
                session = ssl.getSession();
            }
        }
        verify(host, session.getPeerCertificates()[0]);
    }

    public final boolean verify(String host, SSLSession session) {
        try {
            verify(host, session.getPeerCertificates()[0]);
            return true;
        } catch (SSLException e) {
            return false;
        }
    }

    public final void verify(String host, X509Certificate cert) throws SSLException {
        verify(host, getCNs(cert), getSubjectAlts(cert, host));
    }

    public final void verify(String host, String[] cns, String[] subjectAlts, boolean strictWithSubDomains) throws SSLException {
        LinkedList<String> names = new LinkedList();
        if (!(cns == null || cns.length <= 0 || cns[0] == null)) {
            names.add(cns[0]);
        }
        if (subjectAlts != null) {
            for (String subjectAlt : subjectAlts) {
                if (subjectAlt != null) {
                    names.add(subjectAlt);
                }
            }
        }
        if (names.isEmpty()) {
            throw new SSLException("Certificate for <" + host + "> doesn't contain CN or DNS subjectAlt");
        }
        StringBuilder buf = new StringBuilder();
        String hostName = normaliseIPv6Address(host.trim().toLowerCase(Locale.ENGLISH));
        boolean match = false;
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            String cn = ((String) it.next()).toLowerCase(Locale.ENGLISH);
            buf.append(" <");
            buf.append(cn);
            buf.append('>');
            if (it.hasNext()) {
                buf.append(" OR");
            }
            String[] parts = cn.split("\\.");
            boolean doWildcard = parts.length >= 3 && parts[0].endsWith(Marker.ANY_MARKER) && validCountryWildcard(cn) && !isIPAddress(host);
            if (doWildcard) {
                String firstpart = parts[0];
                if (firstpart.length() > 1) {
                    String prefix = firstpart.substring(0, firstpart.length() - 1);
                    match = hostName.startsWith(prefix) && hostName.substring(prefix.length()).endsWith(cn.substring(firstpart.length()));
                } else {
                    match = hostName.endsWith(cn.substring(1));
                }
                if (match && strictWithSubDomains) {
                    if (countDots(hostName) == countDots(cn)) {
                        match = true;
                        continue;
                    } else {
                        match = false;
                        continue;
                    }
                }
            } else {
                match = hostName.equals(normaliseIPv6Address(cn));
                continue;
            }
            if (match) {
                break;
            }
        }
        if (!match) {
            throw new SSLException("hostname in certificate didn't match: <" + host + "> !=" + buf);
        }
    }

    @Deprecated
    public static boolean acceptableCountryWildcard(String cn) {
        String[] parts = cn.split("\\.");
        if (parts.length == 3 && parts[2].length() == 2 && Arrays.binarySearch(BAD_COUNTRY_2LDS, parts[1]) >= 0) {
            return false;
        }
        return true;
    }

    boolean validCountryWildcard(String cn) {
        String[] parts = cn.split("\\.");
        if (parts.length == 3 && parts[2].length() == 2 && Arrays.binarySearch(BAD_COUNTRY_2LDS, parts[1]) >= 0) {
            return false;
        }
        return true;
    }

    public static String[] getCNs(X509Certificate cert) {
        try {
            return extractCNs(cert.getSubjectX500Principal().toString());
        } catch (SSLException e) {
            return null;
        }
    }

    static String[] extractCNs(String subjectPrincipal) throws SSLException {
        if (subjectPrincipal == null) {
            return null;
        }
        LinkedList<String> cnList = new LinkedList();
        StringTokenizer st = new StringTokenizer(subjectPrincipal, ",");
        while (st.hasMoreTokens()) {
            String tok = st.nextToken();
            int x = tok.indexOf("CN=");
            if (x >= 0) {
                cnList.add(tok.substring(x + 3));
            }
        }
        if (cnList.isEmpty()) {
            return null;
        }
        String[] cns = new String[cnList.size()];
        cnList.toArray(cns);
        return cns;
    }

    private static String[] getSubjectAlts(X509Certificate cert, String hostname) {
        int subjectType;
        if (isIPAddress(hostname)) {
            subjectType = 7;
        } else {
            subjectType = 2;
        }
        LinkedList<String> subjectAltList = new LinkedList();
        Collection<List<?>> c = null;
        try {
            c = cert.getSubjectAlternativeNames();
        } catch (CertificateParsingException e) {
        }
        if (c != null) {
            for (List<?> list : c) {
                if (((Integer) list.get(0)).intValue() == subjectType) {
                    subjectAltList.add((String) list.get(1));
                }
            }
        }
        if (subjectAltList.isEmpty()) {
            return null;
        }
        String[] subjectAlts = new String[subjectAltList.size()];
        subjectAltList.toArray(subjectAlts);
        return subjectAlts;
    }

    public static String[] getDNSSubjectAlts(X509Certificate cert) {
        return getSubjectAlts(cert, null);
    }

    public static int countDots(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                count++;
            }
        }
        return count;
    }

    private static boolean isIPAddress(String hostname) {
        return hostname != null && (InetAddressUtils.isIPv4Address(hostname) || InetAddressUtils.isIPv6Address(hostname));
    }

    private String normaliseIPv6Address(String hostname) {
        if (hostname != null && InetAddressUtils.isIPv6Address(hostname)) {
            try {
                hostname = InetAddress.getByName(hostname).getHostAddress();
            } catch (UnknownHostException uhe) {
                this.log.error("Unexpected error converting " + hostname, uhe);
            }
        }
        return hostname;
    }
}
