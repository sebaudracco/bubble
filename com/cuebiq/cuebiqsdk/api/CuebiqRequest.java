package com.cuebiq.cuebiqsdk.api;

import okhttp3.HttpUrl.Builder;
import okhttp3.Request;

public abstract class CuebiqRequest {
    public static final String AUTH_HEADER = "x-beintoo-auth";
    static final String MEDIA_TYPE_APPLICATION_JSON = "application/json; charset=utf-8";
    public static final String PACKAGE_HEADER = "x-cuebiq-package";
    final Builder mBuilder = new Builder();
    Request mRequest;

    CuebiqRequest() {
        this.mBuilder.scheme("https");
        this.mBuilder.host(ApiConfiguration.productionUrl);
    }

    public Request request() {
        return this.mRequest;
    }
}
