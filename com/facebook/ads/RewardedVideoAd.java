package com.facebook.ads;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1830a;
import com.facebook.ads.internal.adapters.ae;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2097a;
import com.facebook.ads.internal.protocol.C2101d;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.f;

public class RewardedVideoAd implements Ad {
    public static final int UNSET_VIDEO_DURATION = -1;
    private static final String f4052a = RewardedVideoAd.class.getSimpleName();
    private final Context f4053b;
    private final String f4054c;
    private DisplayAdController f4055d;
    private boolean f4056e = false;
    private RewardedVideoAdListener f4057f;
    private RewardData f4058g;
    private int f4059h = -1;

    class C18551 extends C1830a {
        final /* synthetic */ RewardedVideoAd f4051a;

        C18551(RewardedVideoAd rewardedVideoAd) {
            this.f4051a = rewardedVideoAd;
        }

        public void mo3551a() {
            if (this.f4051a.f4057f != null) {
                this.f4051a.f4057f.onAdClicked(this.f4051a);
            }
        }

        public void mo3553a(AdAdapter adAdapter) {
            ae aeVar = (ae) adAdapter;
            if (this.f4051a.f4058g != null) {
                aeVar.m5746a(this.f4051a.f4058g);
            }
            this.f4051a.f4059h = aeVar.mo3693a();
            this.f4051a.f4056e = true;
            if (this.f4051a.f4057f != null) {
                this.f4051a.f4057f.onAdLoaded(this.f4051a);
            }
        }

        public void mo3554a(C2097a c2097a) {
            if (this.f4051a.f4057f != null) {
                this.f4051a.f4057f.onError(this.f4051a, AdError.getAdErrorFromWrapper(c2097a));
            }
        }

        public void mo3555b() {
            if (this.f4051a.f4057f != null) {
                this.f4051a.f4057f.onLoggingImpression(this.f4051a);
            }
        }

        public void mo3591f() {
            this.f4051a.f4057f.onRewardedVideoCompleted();
        }

        public void mo3592g() {
            if (this.f4051a.f4057f != null) {
                this.f4051a.f4057f.onRewardedVideoClosed();
            }
        }

        public void mo3593h() {
            if (this.f4051a.f4057f instanceof S2SRewardedVideoAdListener) {
                ((S2SRewardedVideoAdListener) this.f4051a.f4057f).onRewardServerFailed();
            }
        }

        public void mo3594i() {
            if (this.f4051a.f4057f instanceof S2SRewardedVideoAdListener) {
                ((S2SRewardedVideoAdListener) this.f4051a.f4057f).onRewardServerSuccess();
            }
        }
    }

    public RewardedVideoAd(Context context, String str) {
        this.f4053b = context;
        this.f4054c = str;
    }

    private void m5528a(String str, boolean z) {
        try {
            m5532b(str, z);
        } catch (Throwable e) {
            Log.e(f4052a, "Error loading rewarded video ad", e);
            if (this.f4057f != null) {
                this.f4057f.onError(this, AdError.INTERNAL_ERROR);
            }
        }
    }

    private final void m5529a(boolean z) {
        if (this.f4055d != null) {
            this.f4055d.m5618b(z);
            this.f4055d = null;
        }
    }

    private void m5532b(String str, boolean z) {
        m5529a(false);
        this.f4056e = false;
        this.f4055d = new DisplayAdController(this.f4053b, this.f4054c, f.k, AdPlacementType.REWARDED_VIDEO, e.b, C2101d.ADS, 1, true);
        this.f4055d.m5615a(z);
        this.f4055d.m5611a(new C18551(this));
        this.f4055d.m5614a(str);
    }

    public void destroy() {
        m5529a(true);
    }

    public String getPlacementId() {
        return this.f4054c;
    }

    public int getVideoDuration() {
        return this.f4059h;
    }

    public boolean isAdLoaded() {
        return this.f4056e;
    }

    public void loadAd() {
        m5528a(null, false);
    }

    public void loadAd(boolean z) {
        m5528a(null, z);
    }

    public void loadAdFromBid(String str) {
        m5528a(str, false);
    }

    public void loadAdFromBid(String str, boolean z) {
        m5528a(str, z);
    }

    public void setAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.f4057f = rewardedVideoAdListener;
    }

    public void setRewardData(RewardData rewardData) {
        this.f4058g = rewardData;
        if (this.f4056e) {
            this.f4055d.m5610a(rewardData);
        }
    }

    public boolean show() {
        return show(-1);
    }

    public boolean show(int i) {
        if (this.f4056e) {
            this.f4055d.m5609a(i);
            this.f4055d.m5616b();
            this.f4056e = false;
            return true;
        } else if (this.f4057f == null) {
            return false;
        } else {
            this.f4057f.onError(this, AdError.INTERNAL_ERROR);
            return false;
        }
    }
}
