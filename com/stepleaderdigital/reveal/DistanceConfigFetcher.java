package com.stepleaderdigital.reveal;

import com.stepleaderdigital.reveal.Reveal.RevealLogger;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DistanceConfigFetcher {
    protected Exception mException;
    protected String mResponse;
    private int mResponseCode = -1;
    private String mUrlString;
    private String mUserAgentString;

    public DistanceConfigFetcher(String urlString, String userAgentString) {
        this.mUrlString = urlString;
        this.mUserAgentString = userAgentString;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public String getResponseString() {
        return this.mResponse;
    }

    public Exception getException() {
        return this.mException;
    }

    public void request() {
        this.mResponse = null;
        String currentUrlString = this.mUrlString;
        int requestCount = 0;
        StringBuilder responseBuilder = new StringBuilder();
        HttpURLConnection conn = null;
        while (true) {
            if (requestCount != 0) {
                RevealLogger.m12431d("Following redirect from %s to %s", this.mUrlString, conn.getHeaderField(HttpHeaders.LOCATION));
                currentUrlString = conn.getHeaderField(HttpHeaders.LOCATION);
            }
            requestCount++;
            this.mResponseCode = -1;
            URL url = null;
            try {
                url = new URL(currentUrlString);
            } catch (Exception e) {
                RevealLogger.m12434e("Can't construct URL from: %s", this.mUrlString);
                this.mException = e;
            }
            if (url == null) {
                RevealLogger.m12430d("URL is null.  Cannot make request");
            } else {
                try {
                    conn = (HttpURLConnection) url.openConnection();
                    conn.addRequestProperty("User-Agent", this.mUserAgentString);
                    this.mResponseCode = conn.getResponseCode();
                    RevealLogger.m12431d("response code is %s", Integer.valueOf(conn.getResponseCode()));
                } catch (SecurityException e1) {
                    RevealLogger.m12443w(e1, "Can't reach sever.  Have you added android.permission.INTERNET to your manifest?", new Object[0]);
                    this.mException = e1;
                } catch (FileNotFoundException e2) {
                    RevealLogger.m12443w(e2, "No data exists at \"+urlString", new Object[0]);
                    this.mException = e2;
                } catch (IOException e3) {
                    RevealLogger.m12443w(e3, "Can't reach server", new Object[0]);
                    this.mException = e3;
                }
            }
            if (requestCount >= 10 || !(this.mResponseCode == HttpStatus.SC_MOVED_TEMPORARILY || this.mResponseCode == HttpStatus.SC_MOVED_PERMANENTLY || this.mResponseCode == HttpStatus.SC_SEE_OTHER)) {
            }
        }
        if (this.mException == null) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (true) {
                    String inputLine = in.readLine();
                    if (inputLine != null) {
                        responseBuilder.append(inputLine);
                    } else {
                        in.close();
                        this.mResponse = responseBuilder.toString();
                        return;
                    }
                }
            } catch (Exception e4) {
                this.mException = e4;
                RevealLogger.m12443w(e4, "error reading beacon data", new Object[0]);
            }
        }
    }
}
