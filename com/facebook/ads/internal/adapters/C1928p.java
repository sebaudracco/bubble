package com.facebook.ads.internal.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Settings.System;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p055d.C1850a;
import com.facebook.ads.internal.p055d.C1960b;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.settings.C2159a.C2158a;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class C1928p extends ae {
    private final String f4419c = UUID.randomUUID().toString();
    private Context f4420d;
    private af f4421e;
    private boolean f4422f = false;
    private String f4423g;
    private String f4424h;
    private long f4425i;
    private ad f4426j;
    private ag f4427k;

    private void m5989c() {
        LocalBroadcastManager.getInstance(this.f4420d).registerReceiver(this.f4427k, this.f4427k.m5748a());
    }

    private void m5990d() {
        if (this.f4427k != null) {
            try {
                LocalBroadcastManager.getInstance(this.f4420d).unregisterReceiver(this.f4427k);
            } catch (Exception e) {
            }
        }
    }

    private String m5991e() {
        if (this.a == null) {
            return null;
        }
        String urlPrefix = AdSettings.getUrlPrefix();
        if (urlPrefix == null || urlPrefix.isEmpty()) {
            urlPrefix = "https://www.facebook.com/audience_network/server_side_reward";
        } else {
            urlPrefix = String.format("https://www.%s.facebook.com/audience_network/server_side_reward", new Object[]{urlPrefix});
        }
        Uri parse = Uri.parse(urlPrefix);
        Builder builder = new Builder();
        builder.scheme(parse.getScheme());
        builder.authority(parse.getAuthority());
        builder.path(parse.getPath());
        builder.query(parse.getQuery());
        builder.fragment(parse.getFragment());
        builder.appendQueryParameter("puid", this.a.getUserID());
        builder.appendQueryParameter("pc", this.a.getCurrency());
        builder.appendQueryParameter("ptid", this.f4419c);
        builder.appendQueryParameter(C1404b.f2147y, this.f4423g);
        return builder.build().toString();
    }

    public int mo3693a() {
        return this.f4426j == null ? -1 : this.f4426j.m5735m();
    }

    public void mo3694a(Context context, af afVar, Map<String, Object> map, final boolean z) {
        this.f4420d = context;
        this.f4421e = afVar;
        this.f4422f = false;
        this.f4424h = (String) map.get("placementId");
        this.f4425i = ((Long) map.get(AudienceNetworkActivity.REQUEST_TIME)).longValue();
        this.f4423g = this.f4424h != null ? this.f4424h.split(BridgeUtil.UNDERLINE_STR)[0] : "";
        this.f4426j = ad.m5718a((JSONObject) map.get("data"));
        if (TextUtils.isEmpty(this.f4426j.m5721a())) {
            this.f4421e.mo3607a(this, AdError.INTERNAL_ERROR);
            return;
        }
        this.f4427k = new ag(this.f4419c, this, afVar);
        m5989c();
        final C1960b c1960b = new C1960b(context);
        c1960b.m6170a(this.f4426j.m5721a());
        c1960b.m6171a(this.f4426j.m5731i(), -1, -1);
        c1960b.m6171a(this.f4426j.m5732j(), -1, -1);
        c1960b.m6171a(this.f4426j.m5731i(), -1, -1);
        for (String a : this.f4426j.m5737o()) {
            c1960b.m6171a(a, -1, -1);
        }
        c1960b.m6169a(new C1850a(this) {
            final /* synthetic */ C1928p f4418c;

            private void m5983c() {
                this.f4418c.f4422f = true;
                this.f4418c.f4421e.mo3606a(this.f4418c);
                this.f4418c.f4426j.m5724b(c1960b.m6172b(this.f4418c.f4426j.m5721a()));
            }

            public void mo3587a() {
                m5983c();
            }

            public void mo3588b() {
                if (z) {
                    this.f4418c.f4421e.mo3607a(this.f4418c, AdError.CACHE_ERROR);
                } else {
                    m5983c();
                }
            }
        });
    }

    public boolean mo3695b() {
        if (!this.f4422f) {
            return false;
        }
        String e = m5991e();
        this.f4426j.m5722a(e);
        Intent intent = new Intent(this.f4420d, AudienceNetworkActivity.class);
        intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, C2158a.REWARDED_VIDEO);
        intent.putExtra("rewardedVideoAdDataBundle", this.f4426j);
        intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.f4419c);
        intent.putExtra(AudienceNetworkActivity.REWARD_SERVER_URL, e);
        intent.putExtra("placementId", this.f4424h);
        intent.putExtra(AudienceNetworkActivity.REQUEST_TIME, this.f4425i);
        if (this.b != -1 && System.getInt(this.f4420d.getContentResolver(), "accelerometer_rotation", 0) != 1) {
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, this.b);
        } else if (!C2005a.m6350k(this.f4420d)) {
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, 6);
        }
        if (!(this.f4420d instanceof Activity)) {
            intent.setFlags(intent.getFlags() | ErrorDialogData.BINDER_CRASH);
        }
        this.f4420d.startActivity(intent);
        return true;
    }

    public void onDestroy() {
        m5990d();
    }
}
