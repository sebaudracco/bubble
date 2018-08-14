package com.inmobi.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.NativeStrandVideoView.OnQuartileCompletedListener.Quartile;
import com.inmobi.ads.NativeV2Asset.AssetActionOnFinish;
import com.inmobi.ads.ViewableAd.C2964a;
import com.inmobi.ads.ae.C2996a;
import com.inmobi.ads.ae.C2997b;
import com.inmobi.ads.ae.C2998c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: NativeV2Inflater */
public class ap extends C2964a implements C2997b {
    private static final String f7151a = ap.class.getSimpleName();
    @NonNull
    private final ae f7152b;
    @NonNull
    private final ai f7153c;
    private final C2998c f7154d = new C30091(this);
    private final C2996a f7155e = new C30102(this);
    private final ba f7156f = new C30113(this);

    /* compiled from: NativeV2Inflater */
    class C30091 implements C2998c {
        final /* synthetic */ ap f7148a;

        C30091(ap apVar) {
            this.f7148a = apVar;
        }

        public void mo6188a(int i, NativeV2Asset nativeV2Asset) {
            if (!this.f7148a.mo6200b()) {
                this.f7148a.f7153c.m9315a(i, nativeV2Asset);
            }
        }
    }

    /* compiled from: NativeV2Inflater */
    class C30102 implements C2996a {
        final /* synthetic */ ap f7149a;

        C30102(ap apVar) {
            this.f7149a = apVar;
        }

        public void mo6189a(NativeV2Asset nativeV2Asset) {
            if (!this.f7149a.mo6200b()) {
                this.f7149a.f7153c.m9318a(nativeV2Asset);
            }
        }
    }

    /* compiled from: NativeV2Inflater */
    class C30113 implements ba {
        final /* synthetic */ ap f7150a;

        C30113(ap apVar) {
            this.f7150a = apVar;
        }

        public void mo6194a(aw awVar, NativeStrandVideoView nativeStrandVideoView) {
            if (!this.f7150a.mo6200b() && (this.f7150a.f7153c instanceof au)) {
                ((au) this.f7150a.f7153c).m9422a(awVar, nativeStrandVideoView);
            }
        }

        public void mo6192a(aw awVar, int i) {
            if (!this.f7150a.mo6200b() && (this.f7150a.f7153c instanceof au)) {
                ((au) this.f7150a.f7153c).m9420a(awVar, i);
            }
        }

        public void mo6190a() {
            if (!this.f7150a.mo6200b() && (this.f7150a.f7153c instanceof au)) {
                ((au) this.f7150a.f7153c).m9435z();
            }
        }

        public void mo6191a(aw awVar) {
            if (!this.f7150a.mo6200b() && (this.f7150a.f7153c instanceof au)) {
                ((au) this.f7150a.f7153c).m9424b(awVar);
            }
        }

        public void mo6195b(aw awVar) {
            if (!this.f7150a.mo6200b() && (this.f7150a.f7153c instanceof au)) {
                ((au) this.f7150a.f7153c).m9426c(awVar);
            }
        }

        public void mo6196c(aw awVar) {
            if (!this.f7150a.mo6200b() && (this.f7150a.f7153c instanceof au)) {
                ((au) this.f7150a.f7153c).m9428d(awVar);
            }
        }

        public void mo6197d(aw awVar) {
            if (!this.f7150a.mo6200b() && (this.f7150a.f7153c instanceof au)) {
                ((au) this.f7150a.f7153c).m9431g(awVar);
            }
        }

        public void mo6193a(aw awVar, Quartile quartile) {
            if (!this.f7150a.mo6200b() && (this.f7150a.f7153c instanceof au)) {
                ((au) this.f7150a.f7153c).m9421a(awVar, quartile);
            }
        }
    }

    public /* bridge */ /* synthetic */ boolean mo6200b() {
        return super.mo6200b();
    }

    public ap(@NonNull Context context, @NonNull ai aiVar, @NonNull NativeV2DataModel nativeV2DataModel) {
        this.f7153c = aiVar;
        this.f7152b = new ae(context, nativeV2DataModel, this.f7154d, this.f7155e, this);
        this.f7152b.m9274a(this.f7156f);
    }

    public View m9393a(View view, ViewGroup viewGroup, boolean z) {
        View b;
        if (view == null) {
            if (z) {
                b = this.f7152b.m9276b(null, viewGroup);
            } else {
                b = this.f7152b.m9272a(null, viewGroup);
            }
        } else if (m9396a(view)) {
            view = (ag) view;
            if (!m9392a((ag) view)) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7151a, "Already showing same ad, ignoring inflateView");
                b = view;
            } else if (z) {
                b = this.f7152b.m9276b(view, viewGroup);
            } else {
                b = this.f7152b.m9272a((ag) view, viewGroup);
            }
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7151a, "InMobiNativeStrand.getStrandView called with Non Strand View.");
            if (z) {
                b = this.f7152b.m9276b(null, viewGroup);
            } else {
                b = this.f7152b.m9272a(null, viewGroup);
            }
        }
        b.m9287a(this.f7153c);
        return b;
    }

    private boolean m9392a(@NonNull ag agVar) {
        ai a = agVar.m9286a();
        return a == null || !this.f7153c.equals(a);
    }

    boolean m9396a(@NonNull View view) {
        return view instanceof ag;
    }

    public void mo6198a() {
        this.f7152b.m9277c();
        super.mo6198a();
    }

    public void mo6199a(at atVar) {
        if (atVar.m9018m() == AssetActionOnFinish.ASSET_ACTION_ON_FINISH_EXIT) {
            this.f7153c.m9347s();
        }
    }
}
