package com.cuebiq.cuebiqsdk.api;

import com.cuebiq.cuebiqsdk.model.wrapper.GDPRConsent;
import okhttp3.MediaType;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

public class GDPRConsentRequest extends CuebiqRequest {
    public GDPRConsentRequest(String str, GDPRConsent gDPRConsent) {
        this.mBuilder.encodedPath("/gdpr/50100/consent");
        this.mRequest = new Builder().url(this.mBuilder.build()).addHeader(CuebiqRequest.AUTH_HEADER, str).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gDPRConsent.toJSON())).build();
    }
}
