package com.loopj.android.http;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

public class BlackholeHttpResponseHandler extends AsyncHttpResponseHandler {
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
    }

    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
    }

    public void onProgress(long bytesWritten, long totalSize) {
    }

    public void onCancel() {
    }

    public void onFinish() {
    }

    public void onPostProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
    }

    public void onPreProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
    }

    public void onRetry(int retryNo) {
    }

    public void onStart() {
    }

    public void onUserException(Throwable error) {
    }
}
