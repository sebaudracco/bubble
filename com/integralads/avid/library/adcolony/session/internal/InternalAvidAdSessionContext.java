package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import com.integralads.avid.library.adcolony.AvidContext;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import org.json.JSONException;
import org.json.JSONObject;

public class InternalAvidAdSessionContext {
    public static final String AVID_API_LEVEL = "2";
    public static final String AVID_STUB_MODE = "stub";
    public static final String CONTEXT_AVID_AD_SESSION_ID = "avidAdSessionId";
    public static final String CONTEXT_AVID_AD_SESSION_TYPE = "avidAdSessionType";
    public static final String CONTEXT_AVID_API_LEVEL = "avidApiLevel";
    public static final String CONTEXT_AVID_LIBRARY_VERSION = "avidLibraryVersion";
    public static final String CONTEXT_BUNDLE_IDENTIFIER = "bundleIdentifier";
    public static final String CONTEXT_IS_DEFERRED = "isDeferred";
    public static final String CONTEXT_MEDIA_TYPE = "mediaType";
    public static final String CONTEXT_MODE = "mode";
    public static final String CONTEXT_PARTNER = "partner";
    public static final String CONTEXT_PARTNER_VERSION = "partnerVersion";
    private String f8359a;
    private ExternalAvidAdSessionContext f8360b;
    private String f8361c;
    private String f8362d;

    public InternalAvidAdSessionContext(Context context, String avidAdSessionId, String avidAdSessionType, String mediaType, ExternalAvidAdSessionContext avidAdSessionContext) {
        AvidContext.getInstance().init(context);
        this.f8359a = avidAdSessionId;
        this.f8360b = avidAdSessionContext;
        this.f8361c = avidAdSessionType;
        this.f8362d = mediaType;
    }

    public String getAvidAdSessionId() {
        return this.f8359a;
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.f8360b;
    }

    public void setAvidAdSessionContext(ExternalAvidAdSessionContext avidAdSessionContext) {
        this.f8360b = avidAdSessionContext;
    }

    public JSONObject getFullContext() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("avidAdSessionId", this.f8359a);
            jSONObject.put("bundleIdentifier", AvidContext.getInstance().getBundleId());
            jSONObject.put("partner", AvidContext.getInstance().getPartnerName());
            jSONObject.put("partnerVersion", this.f8360b.getPartnerVersion());
            jSONObject.put("avidLibraryVersion", AvidContext.getInstance().getAvidVersion());
            jSONObject.put("avidAdSessionType", this.f8361c);
            jSONObject.put("mediaType", this.f8362d);
            jSONObject.put("isDeferred", this.f8360b.isDeferred());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public JSONObject getStubContext() {
        JSONObject fullContext = getFullContext();
        try {
            fullContext.put("avidApiLevel", "2");
            fullContext.put("mode", "stub");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fullContext;
    }
}
