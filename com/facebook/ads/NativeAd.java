package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import com.facebook.ads.NativeAdView.Type;
import com.facebook.ads.internal.adapters.ab;
import com.facebook.ads.internal.h.d;
import com.facebook.ads.internal.p033n.C1560e;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class NativeAd implements Ad {
    private final C1560e f2574a;

    public NativeAd(Context context, ab abVar, d dVar) {
        this.f2574a = new C1560e(context, abVar, dVar, getViewTraversalPredicate());
    }

    public NativeAd(Context context, String str) {
        this.f2574a = new C1560e(context, str, getViewTraversalPredicate());
    }

    NativeAd(NativeAd nativeAd) {
        this.f2574a = new C1560e(nativeAd.f2574a);
    }

    NativeAd(C1560e c1560e) {
        this.f2574a = c1560e;
    }

    public static void downloadAndDisplayImage(Image image, ImageView imageView) {
        C1560e.m3354a(Image.a(image), imageView);
    }

    private int getMinViewabilityPercentage() {
        return this.f2574a.m3395d();
    }

    public static C1560e.d getViewTraversalPredicate() {
        return new 2();
    }

    private void logExternalClick(String str) {
        this.f2574a.m3391b(str);
    }

    private void logExternalImpression() {
        this.f2574a.m3379F();
    }

    private void registerExternalLogReceiver(String str) {
        this.f2574a.m3387a(str);
    }

    ab m3336a() {
        return this.f2574a.m3380a();
    }

    void m3337a(MediaView mediaView) {
        if (mediaView != null) {
            this.f2574a.m3394c(true);
        }
    }

    void m3338a(Type type) {
        this.f2574a.m3386a(type.a());
    }

    void m3339a(boolean z) {
        this.f2574a.m3392b(z);
    }

    String m3340b() {
        return this.f2574a.m3414w();
    }

    String m3341c() {
        return this.f2574a.m3415x();
    }

    String m3342d() {
        return this.f2574a.m3416y();
    }

    public void destroy() {
        this.f2574a.m3393c();
    }

    VideoAutoplayBehavior m3343e() {
        return VideoAutoplayBehavior.fromInternalAutoplayBehavior(this.f2574a.m3417z());
    }

    List<NativeAd> m3344f() {
        if (this.f2574a.m3374A() == null) {
            return null;
        }
        List<NativeAd> arrayList = new ArrayList();
        for (C1560e nativeAd : this.f2574a.m3374A()) {
            arrayList.add(new NativeAd(nativeAd));
        }
        return arrayList;
    }

    @Nullable
    String m3345g() {
        return this.f2574a.m3375B();
    }

    public String getAdBody() {
        return this.f2574a.m3405n();
    }

    public String getAdCallToAction() {
        return this.f2574a.m3407p();
    }

    public Image getAdChoicesIcon() {
        return this.f2574a.m3411t() == null ? null : new Image(this.f2574a.m3411t());
    }

    public String getAdChoicesLinkUrl() {
        return this.f2574a.m3412u();
    }

    public String getAdChoicesText() {
        return this.f2574a.m3413v();
    }

    public Image getAdCoverImage() {
        return this.f2574a.m3401j() == null ? null : new Image(this.f2574a.m3401j());
    }

    public Image getAdIcon() {
        return this.f2574a.m3400i() == null ? null : new Image(this.f2574a.m3400i());
    }

    @Nullable
    public AdNetwork getAdNetwork() {
        return AdNetwork.fromInternalAdNetwork(this.f2574a.m3390b());
    }

    public String getAdRawBody() {
        return this.f2574a.m3406o();
    }

    public String getAdSocialContext() {
        return this.f2574a.m3408q();
    }

    @Deprecated
    public Rating getAdStarRating() {
        return this.f2574a.m3409r() == null ? null : new Rating(this.f2574a.m3409r());
    }

    public String getAdSubtitle() {
        return this.f2574a.m3404m();
    }

    public String getAdTitle() {
        return this.f2574a.m3403l();
    }

    public NativeAdViewAttributes getAdViewAttributes() {
        return this.f2574a.m3402k() == null ? null : new NativeAdViewAttributes(this.f2574a.m3402k());
    }

    public String getId() {
        return this.f2574a.m3410s();
    }

    public C1560e getInternalNativeAd() {
        return this.f2574a;
    }

    public String getPlacementId() {
        return this.f2574a.m3396e();
    }

    public boolean hasCallToAction() {
        return this.f2574a.m3399h();
    }

    public boolean isAdLoaded() {
        return this.f2574a.m3397f();
    }

    public boolean isNativeConfigEnabled() {
        return this.f2574a.m3398g();
    }

    public void loadAd() {
        loadAd(EnumSet.of(MediaCacheFlag.NONE));
    }

    public void loadAd(EnumSet<MediaCacheFlag> enumSet) {
        this.f2574a.m3388a(MediaCacheFlag.setToInternalSet(enumSet), null);
    }

    public void loadAdFromBid(String str) {
        loadAdFromBid(str, EnumSet.of(MediaCacheFlag.NONE));
    }

    public void loadAdFromBid(String str, EnumSet<MediaCacheFlag> enumSet) {
        this.f2574a.m3388a(MediaCacheFlag.setToInternalSet(enumSet), str);
    }

    public void onCtaBroadcast() {
        this.f2574a.m3376C();
    }

    public void registerExternalLogReceiverIfNeeded() {
        this.f2574a.m3378E();
    }

    public void registerViewForInteraction(View view) {
        this.f2574a.m3382a(view);
    }

    public void registerViewForInteraction(View view, List<View> list) {
        this.f2574a.m3383a(view, (List) list);
    }

    public void setAdListener(AdListener adListener) {
        if (adListener != null) {
            this.f2574a.m3385a(new 1(this, adListener));
        }
    }

    @Deprecated
    public void setMediaViewAutoplay(boolean z) {
        this.f2574a.m3389a(z);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.f2574a.m3381a(onTouchListener);
    }

    public void unregisterView() {
        this.f2574a.m3377D();
    }
}
