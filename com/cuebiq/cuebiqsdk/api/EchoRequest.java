package com.cuebiq.cuebiqsdk.api;

import okhttp3.Request.Builder;

public class EchoRequest extends CuebiqRequest {
    public EchoRequest(String str) {
        this.mBuilder.encodedPath(ApiConfiguration.API_ECHO);
        this.mRequest = new Builder().addHeader(CuebiqRequest.AUTH_HEADER, str).url(this.mBuilder.build()).build();
    }
}
