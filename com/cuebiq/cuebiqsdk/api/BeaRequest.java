package com.cuebiq.cuebiqsdk.api;

import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import okhttp3.MediaType;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

public class BeaRequest extends CuebiqRequest {
    public BeaRequest(TrackRequest trackRequest, String str) {
        this.mBuilder.encodedPath(ApiConfiguration.API_POST);
        this.mRequest = new Builder().url(this.mBuilder.build()).addHeader(CuebiqRequest.AUTH_HEADER, str).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), trackRequest.toString())).build();
    }
}
