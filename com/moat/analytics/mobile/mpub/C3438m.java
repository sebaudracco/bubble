package com.moat.analytics.mobile.mpub;

import com.moat.analytics.mobile.mpub.base.functional.Optional;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

final class C3438m {

    static class C34372 extends Thread {
        private /* synthetic */ String f8757;

        C34372(String str) {
            this.f8757 = str;
        }

        public final void run() {
            try {
                C3438m.m11734(this.f8757);
            } catch (Exception e) {
            }
        }
    }

    C3438m() {
    }

    private static String m11733(InputStream inputStream) throws IOException {
        char[] cArr = new char[256];
        StringBuilder stringBuilder = new StringBuilder();
        Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int i = 0;
        do {
            int read = inputStreamReader.read(cArr, 0, 256);
            if (read <= 0) {
                break;
            }
            i += read;
            stringBuilder.append(cArr, 0, read);
        } while (i < 1024);
        return stringBuilder.toString();
    }

    static Optional<String> m11734(String str) {
        InputStream inputStream = null;
        Optional<String> of;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() >= HttpStatus.SC_BAD_REQUEST) {
                return Optional.empty();
            }
            inputStream = httpURLConnection.getInputStream();
            of = Optional.of(C3438m.m11733(inputStream));
            if (inputStream == null) {
                return of;
            }
            try {
                inputStream.close();
                return of;
            } catch (IOException e) {
                return of;
            }
        } catch (IOException e2) {
            of = Optional.empty();
            if (inputStream == null) {
                return of;
            }
            try {
                inputStream.close();
                return of;
            } catch (IOException e3) {
                return of;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
        }
    }
}
