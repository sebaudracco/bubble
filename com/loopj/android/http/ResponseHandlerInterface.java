package com.loopj.android.http;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import java.io.IOException;
import java.net.URI;

public interface ResponseHandlerInterface {
    Header[] getRequestHeaders();

    URI getRequestURI();

    Object getTag();

    boolean getUsePoolThread();

    boolean getUseSynchronousMode();

    void onPostProcessResponse(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse);

    void onPreProcessResponse(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse);

    void sendCancelMessage();

    void sendFailureMessage(int i, Header[] headerArr, byte[] bArr, Throwable th);

    void sendFinishMessage();

    void sendProgressMessage(long j, long j2);

    void sendResponseMessage(HttpResponse httpResponse) throws IOException;

    void sendRetryMessage(int i);

    void sendStartMessage();

    void sendSuccessMessage(int i, Header[] headerArr, byte[] bArr);

    void setRequestHeaders(Header[] headerArr);

    void setRequestURI(URI uri);

    void setTag(Object obj);

    void setUsePoolThread(boolean z);

    void setUseSynchronousMode(boolean z);
}
