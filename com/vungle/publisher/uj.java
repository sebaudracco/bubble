package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: vungle */
public class uj {
    private static final Pattern f3383a = Pattern.compile("\\bcharset=([\\w\\-]+)\\b");

    public String m4713a(HttpURLConnection httpURLConnection) throws IOException {
        Throwable th;
        Reader reader = null;
        try {
            int responseCode = httpURLConnection.getResponseCode();
            String a = m4712a(httpURLConnection.getContentType());
            Logger.v(Logger.NETWORK_TAG, "response character set: " + a);
            InputStream inputStream = m4711a(responseCode) ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
            if (zk.f(a)) {
                a = "ISO-8859-1";
            }
            Reader inputStreamReader = new InputStreamReader(inputStream, a);
            try {
                StringBuilder stringBuilder = new StringBuilder();
                char[] cArr = new char[8192];
                while (true) {
                    responseCode = inputStreamReader.read(cArr);
                    if (responseCode <= 0) {
                        break;
                    }
                    stringBuilder.append(cArr, 0, responseCode);
                }
                a = stringBuilder.toString();
                Logger.d(Logger.NETWORK_TAG, "response body: " + a);
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (Throwable e) {
                        Logger.e(Logger.NETWORK_TAG, "error closing input stream " + httpURLConnection.getURL(), e);
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                reader = inputStreamReader;
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Throwable e2) {
                        Logger.e(Logger.NETWORK_TAG, "error closing input stream " + httpURLConnection.getURL(), e2);
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (reader != null) {
                reader.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private boolean m4711a(int i) {
        return i / 100 <= 3;
    }

    String m4712a(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = f3383a.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
