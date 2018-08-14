package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.NativeAd;
import com.facebook.ads.internal.adapters.ah;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.settings.C2159a.C2158a;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.C2323c;
import com.facebook.ads.internal.view.p053e.p054b.C1841k;
import com.facebook.ads.internal.view.p053e.p054b.C1842i;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.UUID;

public class C2380n extends C2249b {
    private final String f5894b = UUID.randomUUID().toString();
    private final C1841k f5895c = new C23771(this);
    private final C1842i f5896d = new C23782(this);
    private final C1844c f5897e = new C23793(this);
    private final ah f5898f;
    private C2012c f5899g;
    @Nullable
    private C2323c f5900h;
    @Nullable
    private String f5901i;
    @Nullable
    private Uri f5902j;
    @Nullable
    private String f5903k;
    @Nullable
    private String f5904l;
    @Nullable
    private String f5905m;
    @Nullable
    private C1838o f5906n;
    @Nullable
    private NativeAd f5907o;

    class C23771 extends C1841k {
        final /* synthetic */ C2380n f5891a;

        C23771(C2380n c2380n) {
            this.f5891a = c2380n;
        }

        public void m7514a(C2234j c2234j) {
            if (this.f5891a.f5906n != null) {
                this.f5891a.f5906n.mo3574c();
            }
        }
    }

    class C23782 extends C1842i {
        final /* synthetic */ C2380n f5892a;

        C23782(C2380n c2380n) {
            this.f5892a = c2380n;
        }

        public void m7516a(C2233h c2233h) {
            if (this.f5892a.f5906n != null) {
                this.f5892a.f5906n.mo3573b();
            }
        }
    }

    class C23793 extends C1844c {
        final /* synthetic */ C2380n f5893a;

        C23793(C2380n c2380n) {
            this.f5893a = c2380n;
        }

        public void m7518a(C2229b c2229b) {
            if (this.f5893a.f5906n != null) {
                this.f5893a.f5906n.mo3579h();
            }
        }
    }

    public C2380n(Context context) {
        super(context);
        this.f5898f = new ah(this, context);
        m7521m();
    }

    public C2380n(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5898f = new ah(this, context);
        m7521m();
    }

    public C2380n(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5898f = new ah(this, context);
        m7521m();
    }

    @TargetApi(21)
    public C2380n(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f5898f = new ah(this, context);
        m7521m();
    }

    private void m7520a(Intent intent) {
        if (this.f5901i == null || this.f5900h == null) {
            throw new IllegalStateException("Must setVideoReportUri first.");
        } else if (this.f5902j == null && this.f5904l == null) {
            throw new IllegalStateException("Must setVideoURI or setVideoMPD first.");
        } else {
            intent.putExtra("useNativeCtaButton", this.f5905m);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, C2158a.FULL_SCREEN_VIDEO);
            intent.putExtra(AudienceNetworkActivity.VIDEO_URL, this.f5902j.toString());
            intent.putExtra(AudienceNetworkActivity.CLIENT_TOKEN, this.f5903k == null ? "" : this.f5903k);
            intent.putExtra(AudienceNetworkActivity.VIDEO_MPD, this.f5904l);
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, 13);
            intent.putExtra(AudienceNetworkActivity.VIDEO_SEEK_TIME, getCurrentPosition());
            intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.f5894b);
            intent.putExtra(AudienceNetworkActivity.VIDEO_LOGGER, this.f5900h.mo3678g());
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
        }
    }

    private void m7521m() {
        getEventBus().m6328a(this.f5895c, this.f5896d, this.f5897e);
    }

    public void mo3827a() {
        Context context = getContext();
        Intent intent = new Intent(context, AudienceNetworkActivity.class);
        m7520a(intent);
        try {
            m7107a(false);
            setVisibility(8);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            try {
                intent.setClass(context, InterstitialAdActivity.class);
                context.startActivity(intent);
            } catch (Throwable e2) {
                C1999b.m6321a(C1998a.m6318a(e2, "Error occurred while loading fullscreen video activity."));
            }
        } catch (Throwable e22) {
            C1999b.m6321a(C1998a.m6318a(e22, "Error occurred while loading fullscreen video activity."));
        }
    }

    public void m7523a(@Nullable String str, @Nullable String str2) {
        if (this.f5900h != null) {
            this.f5900h.m7341a();
        }
        this.f5903k = str2;
        this.f5901i = str;
        C2323c c2323c = (str == null || str2 == null) ? null : new C2323c(getContext(), this.f5899g, this, str2);
        this.f5900h = c2323c;
    }

    public void m7524b() {
        if (this.f5907o != null) {
            this.f5907o.onCtaBroadcast();
        }
    }

    @Nullable
    public C1838o getListener() {
        return this.f5906n;
    }

    public String getUniqueId() {
        return this.f5894b;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f5898f.m5749a();
    }

    protected void onDetachedFromWindow() {
        this.f5898f.m5750b();
        super.onDetachedFromWindow();
    }

    public void setAdEventManager(C2012c c2012c) {
        this.f5899g = c2012c;
    }

    public void setEnableBackgroundVideo(boolean z) {
        this.a.setBackgroundPlaybackEnabled(z);
    }

    public void setListener(@Nullable C1838o c1838o) {
        this.f5906n = c1838o;
    }

    public void setNativeAd(@Nullable NativeAd nativeAd) {
        this.f5907o = nativeAd;
    }

    public void setVideoCTA(@Nullable String str) {
        this.f5905m = str;
    }

    public void setVideoMPD(@Nullable String str) {
        if (str == null || this.f5900h != null) {
            this.f5904l = str;
            super.setVideoMPD(str);
            return;
        }
        throw new IllegalStateException("Must setVideoReportUri first.");
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null || this.f5900h != null) {
            this.f5902j = uri;
            super.setVideoURI(uri);
            return;
        }
        throw new IllegalStateException("Must setVideoReportUri first.");
    }
}
