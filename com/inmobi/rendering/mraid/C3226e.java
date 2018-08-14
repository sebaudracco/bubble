package com.inmobi.rendering.mraid;

import android.content.Intent;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.inmobi.ads.AdContainer;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;

/* compiled from: MraidExpandProcessor */
public final class C3226e {
    private static final String f8105a = C3226e.class.getSimpleName();
    private RenderView f8106b;
    private boolean f8107c = false;
    private ViewGroup f8108d;
    private int f8109e;

    public C3226e(RenderView renderView) {
        this.f8106b = renderView;
    }

    public void m10772a(String str, String str2) {
        if (this.f8108d == null) {
            this.f8108d = (ViewGroup) this.f8106b.getParent();
            this.f8109e = this.f8108d.indexOfChild(this.f8106b);
        }
        if (this.f8106b == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8105a, "Please check if the MRAID processor was initialized correctly.");
            return;
        }
        int a;
        C3222c expandProperties = this.f8106b.getExpandProperties();
        this.f8107c = URLUtil.isValidUrl(str2);
        if (this.f8107c) {
            AdContainer renderView = new RenderView(this.f8106b.getRenderViewContext(), new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), null, this.f8106b.getImpressionId());
            renderView.m10627a(this.f8106b.getListener(), this.f8106b.getRenderingConfig(), this.f8106b.getMraidConfig());
            renderView.setOriginalRenderView(this.f8106b);
            renderView.loadUrl(str2);
            a = InMobiAdActivity.m10547a(renderView);
            if (expandProperties != null) {
                renderView.setUseCustomClose(this.f8106b.m10653h());
            }
        } else {
            m10770b();
            a = InMobiAdActivity.m10547a(this.f8106b);
        }
        Intent intent = new Intent(this.f8106b.getRenderViewContext(), InMobiAdActivity.class);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", a);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", 200);
        C3105a.m10072a(this.f8106b.getRenderViewContext(), intent);
    }

    private void m10770b() {
        View frameLayout = new FrameLayout(this.f8106b.getRenderViewContext());
        LayoutParams layoutParams = new LayoutParams(this.f8106b.getWidth(), this.f8106b.getHeight());
        frameLayout.setId(SupportMenu.USER_MASK);
        this.f8108d.addView(frameLayout, this.f8109e, layoutParams);
        this.f8108d.removeView(this.f8106b);
    }

    public void m10771a() {
        if (this.f8106b.getOriginalRenderView() == null) {
            View findViewById = this.f8108d.getRootView().findViewById(SupportMenu.USER_MASK);
            ((ViewGroup) this.f8106b.getParent()).removeView(this.f8106b);
            ((ViewGroup) findViewById.getParent()).removeView(findViewById);
            this.f8108d.addView(this.f8106b, this.f8109e, new RelativeLayout.LayoutParams(this.f8108d.getWidth(), this.f8108d.getHeight()));
            this.f8106b.m10659n();
        }
    }
}
