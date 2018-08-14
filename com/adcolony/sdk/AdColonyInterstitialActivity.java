package com.adcolony.sdk;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.adcolony.sdk.aa.C0595a;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdColonyInterstitialActivity extends C0589b {
    AdColonyInterstitial f414n;
    private C0727k f415o;

    public AdColonyInterstitialActivity() {
        this.f414n = !C0594a.m612b() ? null : C0594a.m605a().m1291u();
    }

    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ void onPause() {
        super.onPause();
    }

    public /* bridge */ /* synthetic */ void onResume() {
        super.onResume();
    }

    public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public void onCreate(Bundle bundle) {
        this.d = this.f414n == null ? 0 : this.f414n.m575e();
        super.onCreate(bundle);
        if (C0594a.m612b() && this.f414n != null) {
            if (this.f414n.m577g()) {
                this.f414n.m578h().m1180a(this.f414n.m574d());
            }
            this.f415o = new C0727k(new Handler(Looper.getMainLooper()), this.f414n);
            if (this.f414n.getListener() != null) {
                this.f414n.getListener().onOpened(this.f414n);
            }
        }
    }

    void mo1845a(af afVar) {
        super.mo1845a(afVar);
        C0690d m = C0594a.m605a().m1283m();
        C0691f c0691f = (C0691f) m.m1160g().remove(this.e);
        if (c0691f != null) {
            for (MediaPlayer mediaPlayer : c0691f.m1165c().m688c().values()) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
            }
            c0691f.m1167d().m774a().autoPause();
            c0691f.m1167d().m774a().release();
        }
        JSONObject f = C0802y.m1480f(afVar.m698c(), "v4iap");
        JSONArray g = C0802y.m1481g(f, "product_ids");
        if (!(f == null || this.f414n == null || this.f414n.getListener() == null || g.length() <= 0)) {
            this.f414n.getListener().onIAPEvent(this.f414n, C0802y.m1474c(g, 0), C0802y.m1473c(f, "engagement_type"));
        }
        m.m1147a(this.c);
        if (this.f414n != null) {
            m.m1155c().remove(this.f414n.m576f());
            if (this.f414n.m577g()) {
                this.f414n.m578h().m1179a();
            }
        }
        if (!(this.f414n == null || this.f414n.getListener() == null)) {
            this.f414n.getListener().onClosed(this.f414n);
            this.f414n.m563a(null);
            this.f414n.setListener(null);
            this.f414n = null;
        }
        if (this.f415o != null) {
            this.f415o.m1197a();
            this.f415o = null;
        }
        new C0595a().m622a("finish_ad call finished").m624a(aa.f480d);
    }

    void m580a(AdColonyInterstitial adColonyInterstitial) {
        this.f414n = adColonyInterstitial;
    }
}
