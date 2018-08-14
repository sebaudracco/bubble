package com.adcolony.sdk;

import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.session.AvidAdSessionManager;
import com.integralads.avid.library.adcolony.session.AvidDisplayAdSession;
import com.integralads.avid.library.adcolony.session.AvidManagedVideoAdSession;
import com.integralads.avid.library.adcolony.session.AvidVideoAdSession;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

class C0694g {
    private AvidDisplayAdSession f972a;
    private AvidVideoAdSession f973b;
    private AvidManagedVideoAdSession f974c;
    private AdColonyCustomMessageListener f975d;
    private JSONArray f976e;
    private int f977f = -1;
    private String f978g = "";
    private int f979h;
    private boolean f980i;
    private boolean f981j;
    private boolean f982k;

    class C06921 implements Runnable {
        final /* synthetic */ C0694g f970a;

        C06921(C0694g c0694g) {
            this.f970a = c0694g;
        }

        public void run() {
            C0740l a = C0594a.m605a();
            switch (this.f970a.m1186d()) {
                case 0:
                    this.f970a.f973b = AvidAdSessionManager.startAvidVideoAdSession(C0594a.m613c(), a.m1249D());
                    this.f970a.f978g = this.f970a.f973b.getAvidAdSessionId();
                    this.f970a.m1181a("start_session");
                    return;
                case 1:
                    this.f970a.f972a = AvidAdSessionManager.startAvidDisplayAdSession(C0594a.m613c(), a.m1249D());
                    this.f970a.f978g = this.f970a.f972a.getAvidAdSessionId();
                    this.f970a.m1181a("start_session");
                    return;
                case 2:
                    this.f970a.f974c = AvidAdSessionManager.startAvidManagedVideoAdSession(C0594a.m613c(), a.m1249D());
                    this.f970a.f978g = this.f970a.f974c.getAvidAdSessionId();
                    this.f970a.m1181a("start_session");
                    for (int i = 0; i < this.f970a.f976e.length(); i++) {
                        this.f970a.f974c.injectJavaScriptResource(C0802y.m1474c(this.f970a.f976e, i));
                        this.f970a.m1181a("inject_javascript");
                    }
                    return;
                default:
                    return;
            }
        }
    }

    class C06932 implements AdColonyCustomMessageListener {
        final /* synthetic */ C0694g f971a;

        C06932(C0694g c0694g) {
            this.f971a = c0694g;
        }

        public void onAdColonyCustomMessage(AdColonyCustomMessage message) {
            JSONObject a = C0802y.m1454a(message.getMessage());
            String b = C0802y.m1468b(a, "event_type");
            boolean d = C0802y.m1477d(a, "replay");
            boolean equals = C0802y.m1468b(a, "skip_type").equals("dec");
            if (!b.equals("skip") || !equals) {
                if (!d || (!b.equals("start") && !b.equals("first_quartile") && !b.equals("midpoint") && !b.equals("third_quartile") && !b.equals("complete"))) {
                    this.f971a.m1184b(b);
                }
            }
        }
    }

    C0694g(JSONObject jSONObject) {
        this.f977f = m1178a(jSONObject);
        this.f976e = C0802y.m1481g(jSONObject, "js_resources");
    }

    int m1178a(JSONObject jSONObject) {
        if (this.f977f != -1) {
            return this.f977f;
        }
        this.f979h = C0802y.m1473c(jSONObject, "ad_unit_type");
        String b = C0802y.m1468b(jSONObject, "ad_type");
        if (this.f979h == 0) {
            return 2;
        }
        if (this.f979h != 1) {
            return b.equals("video") ? 0 : 1;
        } else {
            if (b.equals("video")) {
                return 2;
            }
            return 1;
        }
    }

    void m1180a(C0673c c0673c) {
        if (!this.f982k && this.f977f >= 0) {
            if (this.f977f == 2) {
                m1185c();
            }
            m1183b(c0673c);
            switch (this.f977f) {
                case 0:
                    this.f973b.getAvidDeferredAdSessionListener().recordReadyEvent();
                    break;
                case 1:
                    this.f972a.getAvidDeferredAdSessionListener().recordReadyEvent();
                    break;
                case 2:
                    this.f974c.getAvidDeferredAdSessionListener().recordReadyEvent();
                    break;
            }
            this.f982k = true;
            m1181a("record_ready");
        }
    }

    void m1179a() {
        switch (m1186d()) {
            case 0:
                this.f973b.endSession();
                this.f973b = null;
                m1181a("end_session");
                return;
            case 1:
                this.f972a.endSession();
                this.f972a = null;
                m1181a("end_session");
                return;
            default:
                return;
        }
    }

    void m1182b() {
        if (this.f977f >= 0 && C0594a.m614d()) {
            az.m880a(new C06921(this));
        }
    }

    void m1181a(String str) {
        JSONObject a = C0802y.m1453a();
        JSONObject a2 = C0802y.m1453a();
        C0802y.m1472b(a2, "session_type", this.f977f);
        C0802y.m1462a(a2, "session_id", this.f978g);
        C0802y.m1462a(a2, NotificationCompat.CATEGORY_EVENT, str);
        C0802y.m1462a(a, "type", "ias_hook");
        C0802y.m1462a(a, "message", a2.toString());
        new af("CustomMessage.controller_send", 0, a).m695b();
    }

    void m1183b(C0673c c0673c) {
        m1181a("register_ad_view");
        View view = (WebView) C0594a.m605a().m1294x().get(Integer.valueOf(c0673c.m1057c()));
        if (view == null && !c0673c.m1068h().isEmpty()) {
            view = (WebView) ((Entry) c0673c.m1068h().entrySet().iterator().next()).getValue();
        }
        if (this.f973b != null && view != null) {
            this.f973b.registerAdView(view, C0594a.m613c());
        } else if (this.f972a != null && view != null) {
            this.f972a.registerAdView(view, C0594a.m613c());
            if (this.f979h == 1 && c0673c != null) {
                JSONObject a = C0802y.m1453a();
                C0802y.m1462a(a, "id", this.f972a.getAvidAdSessionId());
                new af("AdSession.send_avid_id", c0673c.m1057c(), a).m695b();
            }
        } else if (this.f974c != null) {
            this.f974c.registerAdView(c0673c, C0594a.m613c());
            c0673c.m1049a(this.f974c);
            m1181a("register_obstructions");
        }
    }

    void m1185c() {
        this.f975d = new C06932(this);
        AdColony.addCustomMessageListener(this.f975d, "ias_ad_event");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m1184b(java.lang.String r6) {
        /*
        r5 = this;
        r2 = 1;
        r0 = 0;
        r1 = com.adcolony.sdk.C0594a.m614d();
        if (r1 == 0) goto L_0x000c;
    L_0x0008:
        r1 = r5.f974c;
        if (r1 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r1 = r5.f974c;
        r3 = r1.getAvidVideoPlaybackListener();
        r1 = -1;
        r4 = r6.hashCode();	 Catch:{ IllegalStateException -> 0x0033 }
        switch(r4) {
            case -1941887438: goto L_0x005b;
            case -1638835128: goto L_0x0066;
            case -1367724422: goto L_0x00b6;
            case -934426579: goto L_0x00ea;
            case -651914917: goto L_0x0071;
            case -599445191: goto L_0x007c;
            case -567202649: goto L_0x0087;
            case -342650039: goto L_0x00c3;
            case 3532159: goto L_0x00a9;
            case 106440182: goto L_0x00dd;
            case 109757538: goto L_0x0051;
            case 583742045: goto L_0x0092;
            case 823102269: goto L_0x009d;
            case 1648173410: goto L_0x00d0;
            default: goto L_0x001b;
        };	 Catch:{ IllegalStateException -> 0x0033 }
    L_0x001b:
        r0 = r1;
    L_0x001c:
        switch(r0) {
            case 0: goto L_0x0020;
            case 1: goto L_0x00f7;
            case 2: goto L_0x00ff;
            case 3: goto L_0x0107;
            case 4: goto L_0x010f;
            case 5: goto L_0x0117;
            case 6: goto L_0x0136;
            case 7: goto L_0x0136;
            case 8: goto L_0x0155;
            case 9: goto L_0x0155;
            case 10: goto L_0x0174;
            case 11: goto L_0x0181;
            case 12: goto L_0x018f;
            case 13: goto L_0x01a8;
            default: goto L_0x001f;
        };	 Catch:{ IllegalStateException -> 0x0033 }
    L_0x001f:
        goto L_0x000c;
    L_0x0020:
        r3.recordAdLoadedEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r3.recordAdStartedEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r3.recordAdPlayingEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r3.recordAdImpressionEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r3.recordAdVideoStartEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r5.m1181a(r6);	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x0033:
        r0 = move-exception;
        r0 = new com.adcolony.sdk.aa$a;
        r0.<init>();
        r1 = "Recording IAS event for ";
        r0 = r0.m622a(r1);
        r0 = r0.m622a(r6);
        r1 = " caused IllegalStateException.";
        r0 = r0.m622a(r1);
        r1 = com.adcolony.sdk.aa.f482f;
        r0.m624a(r1);
        goto L_0x000c;
    L_0x0051:
        r2 = "start";
        r2 = r6.equals(r2);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r2 == 0) goto L_0x001b;
    L_0x005a:
        goto L_0x001c;
    L_0x005b:
        r0 = "first_quartile";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x0064:
        r0 = r2;
        goto L_0x001c;
    L_0x0066:
        r0 = "midpoint";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x006f:
        r0 = 2;
        goto L_0x001c;
    L_0x0071:
        r0 = "third_quartile";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x007a:
        r0 = 3;
        goto L_0x001c;
    L_0x007c:
        r0 = "complete";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x0085:
        r0 = 4;
        goto L_0x001c;
    L_0x0087:
        r0 = "continue";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x0090:
        r0 = 5;
        goto L_0x001c;
    L_0x0092:
        r0 = "in_video_engagement";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x009b:
        r0 = 6;
        goto L_0x001c;
    L_0x009d:
        r0 = "html5_interaction";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x00a6:
        r0 = 7;
        goto L_0x001c;
    L_0x00a9:
        r0 = "skip";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x00b2:
        r0 = 8;
        goto L_0x001c;
    L_0x00b6:
        r0 = "cancel";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x00bf:
        r0 = 9;
        goto L_0x001c;
    L_0x00c3:
        r0 = "sound_mute";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x00cc:
        r0 = 10;
        goto L_0x001c;
    L_0x00d0:
        r0 = "sound_unmute";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x00d9:
        r0 = 11;
        goto L_0x001c;
    L_0x00dd:
        r0 = "pause";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x00e6:
        r0 = 12;
        goto L_0x001c;
    L_0x00ea:
        r0 = "resume";
        r0 = r6.equals(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x001b;
    L_0x00f3:
        r0 = 13;
        goto L_0x001c;
    L_0x00f7:
        r3.recordAdVideoFirstQuartileEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r5.m1181a(r6);	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x00ff:
        r3.recordAdVideoMidpointEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r5.m1181a(r6);	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x0107:
        r3.recordAdVideoThirdQuartileEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r5.m1181a(r6);	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x010f:
        r3.recordAdCompleteEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r5.m1181a(r6);	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x0117:
        r3.recordAdUserCloseEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r3.recordAdStoppedEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = "ias_ad_event";
        com.adcolony.sdk.AdColony.removeCustomMessageListener(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = r5.f974c;	 Catch:{ IllegalStateException -> 0x0033 }
        r0.endSession();	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = "end_session";
        r5.m1181a(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = 0;
        r5.f974c = r0;	 Catch:{ IllegalStateException -> 0x0033 }
        r5.m1181a(r6);	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x0136:
        r3.recordAdClickThruEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r5.m1181a(r6);	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = r5.f981j;	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x000c;
    L_0x0140:
        r0 = r5.f980i;	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 != 0) goto L_0x000c;
    L_0x0144:
        r3.recordAdPausedEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = "pause";
        r5.m1181a(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = 1;
        r5.f980i = r0;	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = 0;
        r5.f981j = r0;	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x0155:
        r3.recordAdSkippedEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r3.recordAdStoppedEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = "ias_ad_event";
        com.adcolony.sdk.AdColony.removeCustomMessageListener(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = r5.f974c;	 Catch:{ IllegalStateException -> 0x0033 }
        r0.endSession();	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = "end_session";
        r5.m1181a(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = 0;
        r5.f974c = r0;	 Catch:{ IllegalStateException -> 0x0033 }
        r5.m1181a(r6);	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x0174:
        r0 = 0;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r3.recordAdVolumeChangeEvent(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r5.m1181a(r6);	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x0181:
        r0 = 100;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r3.recordAdVolumeChangeEvent(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r5.m1181a(r6);	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x018f:
        r0 = r5.f980i;	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 != 0) goto L_0x000c;
    L_0x0193:
        r0 = r5.f981j;	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 != 0) goto L_0x000c;
    L_0x0197:
        r3.recordAdPausedEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = "pause";
        r5.m1181a(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = 1;
        r5.f980i = r0;	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = 0;
        r5.f981j = r0;	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
    L_0x01a8:
        r0 = r5.f980i;	 Catch:{ IllegalStateException -> 0x0033 }
        if (r0 == 0) goto L_0x000c;
    L_0x01ac:
        r3.recordAdPlayingEvent();	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = "resume";
        r5.m1181a(r0);	 Catch:{ IllegalStateException -> 0x0033 }
        r0 = 0;
        r5.f980i = r0;	 Catch:{ IllegalStateException -> 0x0033 }
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adcolony.sdk.g.b(java.lang.String):void");
    }

    int m1186d() {
        return this.f977f;
    }

    AvidManagedVideoAdSession m1187e() {
        return this.f974c;
    }

    void m1188f() {
        this.f981j = true;
    }
}
