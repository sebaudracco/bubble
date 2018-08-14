package com.inmobi.ads;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import com.inmobi.ads.AdUnit.C2905b;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.Map;

class InMobiInterstitial$2 implements C2905b {
    final /* synthetic */ InMobiInterstitial f6756a;

    InMobiInterstitial$2(InMobiInterstitial inMobiInterstitial) {
        this.f6756a = inMobiInterstitial;
    }

    public void mo6122a(AdUnit adUnit, boolean z) {
        InMobiInterstitial.access$300(this.f6756a).a();
        if (z) {
            Message obtain = Message.obtain();
            obtain.what = 11;
            Bundle bundle = new Bundle();
            bundle.putBoolean("available", true);
            obtain.setData(bundle);
            InMobiInterstitial.access$300(this.f6756a).sendMessage(obtain);
            obtain = Message.obtain();
            obtain.what = 1;
            InMobiInterstitial.access$300(this.f6756a).sendMessage(obtain);
            return;
        }
        obtain = Message.obtain();
        obtain.what = 11;
        bundle = new Bundle();
        bundle.putBoolean("available", false);
        obtain.setData(bundle);
        InMobiInterstitial.access$300(this.f6756a).sendMessage(obtain);
    }

    public void mo6119a() {
        InMobiInterstitial.access$300(this.f6756a).sendEmptyMessage(1);
    }

    public void mo6120a(AdUnit adUnit) {
        InMobiInterstitial.access$300(this.f6756a).sendEmptyMessage(12);
    }

    public void mo6121a(AdUnit adUnit, InMobiAdRequestStatus inMobiAdRequestStatus) {
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = inMobiAdRequestStatus;
        InMobiInterstitial.access$300(this.f6756a).sendMessage(obtain);
        obtain = Message.obtain();
        obtain.what = 10;
        obtain.obj = inMobiAdRequestStatus;
        InMobiInterstitial.access$300(this.f6756a).sendMessage(obtain);
        if (inMobiAdRequestStatus.getStatusCode() == StatusCode.EARLY_REFRESH_REQUEST || inMobiAdRequestStatus.getStatusCode() == StatusCode.REQUEST_PENDING || inMobiAdRequestStatus.getStatusCode() == StatusCode.AD_ACTIVE) {
            Logger.m10359a(InternalLogLevel.INTERNAL, InMobiInterstitial.access$200(), " No need to fire TRC for " + inMobiAdRequestStatus.getStatusCode());
        } else {
            InMobiInterstitial.access$400(this.f6756a, "onAdLoadFailed");
        }
    }

    public void mo6124b() {
        InMobiInterstitial.access$300(this.f6756a).a();
        InMobiInterstitial.access$300(this.f6756a).b();
        InMobiInterstitial.access$300(this.f6756a).sendEmptyMessage(14);
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = new InMobiAdRequestStatus(StatusCode.AD_NO_LONGER_AVAILABLE);
        InMobiInterstitial.access$300(this.f6756a).sendMessage(obtain);
        InMobiInterstitial.access$400(this.f6756a, "onAdShowFailed");
    }

    public void mo6126c() {
        InMobiInterstitial.access$300(this.f6756a).sendEmptyMessage(15);
    }

    public void mo6127d() {
        InMobiInterstitial.access$300(this.f6756a).sendEmptyMessage(3);
        InMobiInterstitial.access$300(this.f6756a).sendEmptyMessage(16);
        InMobiInterstitial.access$400(this.f6756a, "onAdDisplayed");
    }

    public void mo6128e() {
        InMobiInterstitial.access$300(this.f6756a).sendEmptyMessage(4);
        InMobiInterstitial.access$300(this.f6756a).sendEmptyMessage(19);
        C3063j.m9855a().m9876b(InMobiInterstitial.access$500(this.f6756a));
    }

    public void mo6123a(@NonNull Map<Object, Object> map) {
        Message obtain = Message.obtain();
        obtain.what = 5;
        obtain.obj = map;
        InMobiInterstitial.access$300(this.f6756a).sendMessage(obtain);
        obtain = Message.obtain();
        obtain.what = 18;
        obtain.obj = map;
        InMobiInterstitial.access$300(this.f6756a).sendMessage(obtain);
    }

    public void mo6129f() {
        InMobiInterstitial.access$300(this.f6756a).sendEmptyMessage(20);
        InMobiInterstitial.access$300(this.f6756a).sendEmptyMessage(6);
    }

    public void mo6125b(@NonNull Map<Object, Object> map) {
        Message obtain = Message.obtain();
        obtain.what = 13;
        obtain.obj = map;
        InMobiInterstitial.access$300(this.f6756a).sendMessage(obtain);
        obtain = Message.obtain();
        obtain.what = 7;
        obtain.obj = map;
        InMobiInterstitial.access$300(this.f6756a).sendMessage(obtain);
    }

    public void mo6130g() {
    }
}
