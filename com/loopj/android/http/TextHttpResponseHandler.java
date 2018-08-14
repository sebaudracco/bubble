package com.loopj.android.http;

import cz.msebera.android.httpclient.Header;
import java.io.UnsupportedEncodingException;

public abstract class TextHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "TextHttpRH";

    public abstract void onFailure(int i, Header[] headerArr, String str, Throwable th);

    public abstract void onSuccess(int i, Header[] headerArr, String str);

    public TextHttpResponseHandler() {
        this("UTF-8");
    }

    public TextHttpResponseHandler(String encoding) {
        setCharset(encoding);
    }

    public static String getResponseString(byte[] stringBytes, String charset) {
        String toReturn = stringBytes == null ? null : new String(stringBytes, charset);
        if (toReturn == null) {
            return toReturn;
        }
        try {
            if (toReturn.startsWith(AsyncHttpResponseHandler.UTF8_BOM)) {
                return toReturn.substring(1);
            }
            return toReturn;
        } catch (UnsupportedEncodingException e) {
            AsyncHttpClient.log.mo6453e(LOG_TAG, "Encoding response into string failed", e);
            return null;
        }
    }

    public void onSuccess(int statusCode, Header[] headers, byte[] responseBytes) {
        onSuccess(statusCode, headers, getResponseString(responseBytes, getCharset()));
    }

    public void onFailure(int statusCode, Header[] headers, byte[] responseBytes, Throwable throwable) {
        onFailure(statusCode, headers, getResponseString(responseBytes, getCharset()), throwable);
    }
}
