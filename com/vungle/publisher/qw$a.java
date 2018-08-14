package com.vungle.publisher;

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

/* compiled from: vungle */
final class qw$a implements Interceptor {
    final /* synthetic */ qw f10951a;

    private qw$a(qw qwVar) {
        this.f10951a = qwVar;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.body() == null || request.header("Content-Encoding") != null) {
            return chain.proceed(request);
        }
        return chain.proceed(request.newBuilder().header("Content-Encoding", AsyncHttpClient.ENCODING_GZIP).method(request.method(), m13853a(request.body())).build());
    }

    private RequestBody m13853a(final RequestBody requestBody) {
        return new RequestBody(this) {
            final /* synthetic */ qw$a f10950b;

            public MediaType contentType() {
                return requestBody.contentType();
            }

            public long contentLength() {
                return -1;
            }

            public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink buffer = Okio.buffer(new GzipSink(sink));
                requestBody.writeTo(buffer);
                buffer.close();
            }
        };
    }
}
