package com.integralads.avid.library.inmobi.p127g;

import com.integralads.avid.library.inmobi.p122b.C3288a;
import com.integralads.avid.library.inmobi.session.internal.C3333a;
import com.integralads.avid.library.inmobi.session.internal.p129a.C3331a;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AvidVideoPlaybackListenerImpl */
public class C3319b extends C3288a implements C3318a {
    public C3319b(C3333a c3333a, C3331a c3331a) {
        super(c3333a, c3331a);
    }

    public void b_() {
        m11336a("AdImpression", null);
    }

    public void c_() {
        m11336a("AdStarted", null);
    }

    public void d_() {
        m11336a("AdLoaded", null);
    }

    public void e_() {
        m11336a("AdVideoStart", null);
    }

    public void mo6338e() {
        m11336a("AdStopped", null);
    }

    public void mo6340f() {
        m11336a("AdVideoComplete", null);
    }

    public void mo6341g() {
        m11336a("AdClickThru", null);
    }

    public void mo6342h() {
        m11336a("AdVideoFirstQuartile", null);
    }

    public void mo6343i() {
        m11336a("AdVideoMidpoint", null);
    }

    public void mo6344j() {
        m11336a("AdVideoThirdQuartile", null);
    }

    public void mo6345k() {
        m11336a("AdPaused", null);
    }

    public void mo6346l() {
        m11336a("AdPlaying", null);
    }

    public void mo6347m() {
        m11336a("AdExpandedChange", null);
    }

    public void mo6348n() {
        m11336a("AdUserMinimize", null);
    }

    public void mo6349o() {
        m11336a("AdUserClose", null);
    }

    public void mo6350p() {
        m11336a("AdSkipped", null);
    }

    public void mo6333a(Integer num) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("volume", num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m11336a("AdVolumeChange", jSONObject);
    }

    public void mo6351q() {
        m11336a("AdEnteredFullscreen", null);
    }

    public void mo6352r() {
        m11336a("AdExitedFullscreen", null);
    }

    public void mo6334a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m11336a("AdError", jSONObject);
    }

    private void m11336a(String str, JSONObject jSONObject) {
        m11195d();
        m11337s();
        m11194c().m11382a(str, jSONObject);
    }

    private void m11337s() {
        if (!m11193b().m11405h()) {
            throw new IllegalStateException("The AVID ad session is not ready. Please ensure you have called recordReadyEvent for the deferred AVID ad session before recording any video event.");
        }
    }
}
