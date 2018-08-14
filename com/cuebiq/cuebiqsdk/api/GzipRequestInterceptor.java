package com.cuebiq.cuebiqsdk.api;

import com.loopj.android.http.AsyncHttpClient;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

public final class GzipRequestInterceptor implements Interceptor {
    private RequestBody gzip(final RequestBody requestBody) {
        return new RequestBody() {
            public long contentLength() {
                return -1;
            }

            public MediaType contentType() {
                return requestBody.contentType();
            }

            public void writeTo(BufferedSink bufferedSink) throws IOException {
                BufferedSink buffer = Okio.buffer(new GzipSink(bufferedSink));
                requestBody.writeTo(buffer);
                buffer.close();
            }
        };
    }

    public final Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        return (request.body() == null || request.header("Content-Encoding") != null) ? chain.proceed(request) : chain.proceed(request.newBuilder().header("Content-Encoding", AsyncHttpClient.ENCODING_GZIP).method(request.method(), gzip(request.body())).build());
    }
}
