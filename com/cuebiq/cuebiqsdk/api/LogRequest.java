package com.cuebiq.cuebiqsdk.api;

import com.cuebiq.cuebiqsdk.model.wrapper.LogAppBean;
import okhttp3.MediaType;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

public class LogRequest extends CuebiqRequest {
    public LogRequest(LogAppBean logAppBean, String str) {
        this.mBuilder.encodedPath(ApiConfiguration.API_LOG);
        this.mRequest = new Builder().url(this.mBuilder.build()).addHeader(CuebiqRequest.AUTH_HEADER, str).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), logAppBean.toString())).build();
    }
}
