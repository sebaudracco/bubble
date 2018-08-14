package com.cuebiq.cuebiqsdk.api;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private final String mAuthKey;

    public AuthInterceptor(String str) {
        this.mAuthKey = str;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        return chain.proceed(request.newBuilder().addHeader(CuebiqRequest.AUTH_HEADER, this.mAuthKey).method(request.method(), request.body()).build());
    }
}
