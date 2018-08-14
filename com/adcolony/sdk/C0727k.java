package com.adcolony.sdk;

import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings.System;
import com.adcolony.sdk.aa.C0595a;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import org.json.JSONObject;

class C0727k extends ContentObserver {
    private AudioManager f1236a;
    private AdColonyInterstitial f1237b;

    public C0727k(Handler handler, AdColonyInterstitial adColonyInterstitial) {
        super(handler);
        if (C0594a.m614d()) {
            this.f1236a = (AudioManager) C0594a.m613c().getSystemService("audio");
            this.f1237b = adColonyInterstitial;
            C0594a.m613c().getApplicationContext().getContentResolver().registerContentObserver(System.CONTENT_URI, true, this);
        }
    }

    public boolean deliverSelfNotifications() {
        return false;
    }

    public void onChange(boolean selfChange) {
        if (this.f1236a != null && this.f1237b != null && this.f1237b.m574d() != null) {
            double streamVolume = (double) ((((float) this.f1236a.getStreamVolume(3)) / CtaButton.TEXT_SIZE_SP) * 100.0f);
            int i = (int) streamVolume;
            if (!(!this.f1237b.m577g() || this.f1237b.m578h().m1187e() == null || this.f1237b.m579i())) {
                this.f1237b.m578h().m1187e().getAvidVideoPlaybackListener().recordAdVolumeChangeEvent(Integer.valueOf(i));
                this.f1237b.m578h().m1181a("volume_change");
            }
            JSONObject a = C0802y.m1453a();
            C0802y.m1460a(a, "audio_percentage", streamVolume);
            C0802y.m1462a(a, "ad_session_id", this.f1237b.m574d().m1053b());
            C0802y.m1472b(a, "id", this.f1237b.m574d().m1060d());
            new af("AdContainer.on_audio_change", this.f1237b.m574d().m1057c(), a).m695b();
            new C0595a().m622a("Volume changed to ").m619a(streamVolume).m624a(aa.f480d);
        }
    }

    void m1197a() {
        if (C0594a.m614d()) {
            C0594a.m613c().getApplicationContext().getContentResolver().unregisterContentObserver(this);
        }
        this.f1237b = null;
        this.f1236a = null;
    }
}
