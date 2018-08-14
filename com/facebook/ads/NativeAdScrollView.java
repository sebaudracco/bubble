package com.facebook.ads;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.facebook.ads.NativeAdView.Type;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import java.util.ArrayList;
import java.util.List;

public class NativeAdScrollView extends LinearLayout {
    public static final int DEFAULT_INSET = 20;
    public static final int DEFAULT_MAX_ADS = 10;
    private final Context f4024a;
    private final NativeAdsManager f4025b;
    private final AdViewProvider f4026c;
    private final Type f4027d;
    private final int f4028e;
    private final C1849b f4029f;
    private final NativeAdViewAttributes f4030g;

    public interface AdViewProvider {
        View createView(NativeAd nativeAd, int i);

        void destroyView(NativeAd nativeAd, View view);
    }

    private class C1848a extends PagerAdapter {
        final /* synthetic */ NativeAdScrollView f4021a;
        private List<NativeAd> f4022b = new ArrayList();

        public C1848a(NativeAdScrollView nativeAdScrollView) {
            this.f4021a = nativeAdScrollView;
        }

        public void m5495a() {
            this.f4022b.clear();
            int min = Math.min(this.f4021a.f4028e, this.f4021a.f4025b.getUniqueNativeAdCount());
            for (int i = 0; i < min; i++) {
                NativeAd nextNativeAd = this.f4021a.f4025b.nextNativeAd();
                nextNativeAd.a(true);
                this.f4022b.add(nextNativeAd);
            }
            notifyDataSetChanged();
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (i < this.f4022b.size()) {
                if (this.f4021a.f4027d != null) {
                    ((NativeAd) this.f4022b.get(i)).unregisterView();
                } else {
                    this.f4021a.f4026c.destroyView((NativeAd) this.f4022b.get(i), (View) obj);
                }
            }
            viewGroup.removeView((View) obj);
        }

        public int getCount() {
            return this.f4022b.size();
        }

        public int getItemPosition(Object obj) {
            int indexOf = this.f4022b.indexOf(obj);
            return indexOf >= 0 ? indexOf : -2;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View render = this.f4021a.f4027d != null ? NativeAdView.render(this.f4021a.f4024a, (NativeAd) this.f4022b.get(i), this.f4021a.f4027d, this.f4021a.f4030g) : this.f4021a.f4026c.createView((NativeAd) this.f4022b.get(i), i);
            viewGroup.addView(render);
            return render;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    private class C1849b extends ViewPager {
        final /* synthetic */ NativeAdScrollView f4023a;

        public C1849b(NativeAdScrollView nativeAdScrollView, Context context) {
            this.f4023a = nativeAdScrollView;
            super(context);
        }

        protected void onMeasure(int i, int i2) {
            int i3 = 0;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredHeight > i3) {
                    i3 = measuredHeight;
                }
            }
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(i3, 1073741824));
        }
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider) {
        this(context, nativeAdsManager, adViewProvider, null, null, 10);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider, int i) {
        this(context, nativeAdsManager, adViewProvider, null, null, i);
    }

    private NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, AdViewProvider adViewProvider, Type type, NativeAdViewAttributes nativeAdViewAttributes, int i) {
        super(context);
        if (!nativeAdsManager.isLoaded()) {
            throw new IllegalStateException("NativeAdsManager not loaded");
        } else if (type == null && adViewProvider == null) {
            throw new IllegalArgumentException("Must provide a NativeAdView.Type or AdViewProvider");
        } else {
            this.f4024a = context;
            this.f4025b = nativeAdsManager;
            this.f4030g = nativeAdViewAttributes;
            this.f4026c = adViewProvider;
            this.f4027d = type;
            this.f4028e = i;
            PagerAdapter c1848a = new C1848a(this);
            this.f4029f = new C1849b(this, context);
            this.f4029f.setAdapter(c1848a);
            setInset(20);
            c1848a.m5495a();
            addView(this.f4029f);
        }
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type) {
        this(context, nativeAdsManager, null, type, new NativeAdViewAttributes(), 10);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type, NativeAdViewAttributes nativeAdViewAttributes) {
        this(context, nativeAdsManager, null, type, nativeAdViewAttributes, 10);
    }

    public NativeAdScrollView(Context context, NativeAdsManager nativeAdsManager, Type type, NativeAdViewAttributes nativeAdViewAttributes, int i) {
        this(context, nativeAdsManager, null, type, nativeAdViewAttributes, i);
    }

    public void setInset(int i) {
        if (i > 0) {
            float f = C2133v.f5083b;
            int round = Math.round(((float) i) * f);
            this.f4029f.setPadding(round, 0, round, 0);
            this.f4029f.setPageMargin(Math.round(f * ((float) (i / 2))));
            this.f4029f.setClipToPadding(false);
        }
    }
}
