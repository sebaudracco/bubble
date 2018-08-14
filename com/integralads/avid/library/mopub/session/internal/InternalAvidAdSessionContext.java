package com.integralads.avid.library.mopub.session.internal;

import android.content.Context;
import com.integralads.avid.library.mopub.AvidContext;
import com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext;
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
    private ExternalAvidAdSessionContext avidAdSessionContext;
    private String avidAdSessionId;
    private String avidAdSessionType;
    private String mediaType;

    public InternalAvidAdSessionContext(Context context, String avidAdSessionId, String avidAdSessionType, String mediaType, ExternalAvidAdSessionContext avidAdSessionContext) {
        AvidContext.getInstance().init(context);
        this.avidAdSessionId = avidAdSessionId;
        this.avidAdSessionContext = avidAdSessionContext;
        this.avidAdSessionType = avidAdSessionType;
        this.mediaType = mediaType;
    }

    public String getAvidAdSessionId() {
        return this.avidAdSessionId;
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.avidAdSessionContext;
    }

    public void setAvidAdSessionContext(ExternalAvidAdSessionContext avidAdSessionContext) {
        this.avidAdSessionContext = avidAdSessionContext;
    }

    public JSONObject getFullContext() {
        JSONObject fullContext = new JSONObject();
        try {
            fullContext.put("avidAdSessionId", this.avidAdSessionId);
            fullContext.put("bundleIdentifier", AvidContext.getInstance().getBundleId());
            fullContext.put("partner", AvidContext.getInstance().getPartnerName());
            fullContext.put("partnerVersion", this.avidAdSessionContext.getPartnerVersion());
            fullContext.put("avidLibraryVersion", AvidContext.getInstance().getAvidVersion());
            fullContext.put("avidAdSessionType", this.avidAdSessionType);
            fullContext.put("mediaType", this.mediaType);
            fullContext.put("isDeferred", this.avidAdSessionContext.isDeferred());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fullContext;
    }

    public JSONObject getStubContext() {
        JSONObject stubContext = getFullContext();
        try {
            stubContext.put("avidApiLevel", "2");
            stubContext.put("mode", "stub");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stubContext;
    }
}
