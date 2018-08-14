package com.facebook.ads.internal.protocol;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.p056q.p077d.C2150a;
import org.json.JSONObject;

public final class C2105h {
    private final C2104a f4991a;
    @Nullable
    private final Long f4992b;
    @Nullable
    private final String f4993c;
    @Nullable
    private final String f4994d;

    private enum C2104a {
        f4987a,
        CREATIVE,
        NONE
    }

    public C2105h(Context context, String str, String str2, f fVar) {
        if (TextUtils.isEmpty(str)) {
            this.f4991a = C2104a.NONE;
            this.f4992b = null;
            this.f4994d = null;
            this.f4993c = null;
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (C2104a.valueOf(jSONObject.getString("type").toUpperCase())) {
                case f4987a:
                    this.f4991a = C2104a.f4987a;
                    this.f4992b = Long.valueOf(jSONObject.getString("bid_id"));
                    this.f4994d = jSONObject.getString("device_id");
                    this.f4993c = null;
                    break;
                case CREATIVE:
                    this.f4991a = C2104a.CREATIVE;
                    this.f4992b = Long.valueOf(jSONObject.getString("bid_id"));
                    this.f4994d = jSONObject.getString("device_id");
                    this.f4993c = new JSONObject(jSONObject.getString("payload")).toString();
                    break;
                default:
                    throw new C2098b(AdErrorType.BID_PAYLOAD_ERROR, "Unsupported BidPayload type " + jSONObject.getString("type"));
            }
            if (!jSONObject.getString("sdk_version").equals("4.28.1")) {
                throw new C2098b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format("Bid %d for SDK version %s being used on SDK version %s", new Object[]{this.f4992b, jSONObject.getString("sdk_version"), "4.28.1"}));
            } else if (!jSONObject.getString("resolved_placement_id").equals(str2)) {
                throw new C2098b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format("Bid %d for placement %s being used on placement %s", new Object[]{this.f4992b, jSONObject.getString("resolved_placement_id"), str2}));
            } else if (jSONObject.getInt("template") != fVar.a()) {
                throw new C2098b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format("Bid %d for template %s being used on template %s", new Object[]{this.f4992b, Integer.valueOf(jSONObject.getInt("template")), fVar}));
            }
        } catch (Throwable e) {
            C2150a.m6888a(e, context);
            throw new C2098b(AdErrorType.BID_PAYLOAD_ERROR, "Invalid BidPayload", e);
        }
    }

    public void m6758a(String str) {
        if (!this.f4994d.equals(str)) {
            throw new C2098b(AdErrorType.BID_IMPRESSION_MISMATCH, String.format("Bid %d for IDFA %s being used on IDFA %s", new Object[]{this.f4992b, this.f4994d, str}));
        }
    }

    public boolean m6759a() {
        return this.f4991a == C2104a.CREATIVE;
    }

    @Nullable
    public String m6760b() {
        return this.f4993c;
    }

    public boolean m6761c() {
        return this.f4991a != C2104a.NONE;
    }

    @Nullable
    public String m6762d() {
        return this.f4992b == null ? null : this.f4992b.toString();
    }
}
