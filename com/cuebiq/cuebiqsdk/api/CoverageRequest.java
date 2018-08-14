package com.cuebiq.cuebiqsdk.api;

import java.util.Map;
import okhttp3.Request.Builder;

public class CoverageRequest extends CuebiqRequest {
    public CoverageRequest(String str, String str2, Map<String, String> map) {
        this.mBuilder.encodedPath(ApiConfiguration.API_COVERAGE);
        if (map != null) {
            for (String str3 : map.keySet()) {
                this.mBuilder.addQueryParameter(str3, (String) map.get(str3));
            }
        }
        Builder url = new Builder().addHeader(CuebiqRequest.AUTH_HEADER, str).url(this.mBuilder.build());
        if (str.equals("aWildcard")) {
            url.addHeader(CuebiqRequest.PACKAGE_HEADER, str2);
        }
        this.mRequest = url.build();
    }
}
