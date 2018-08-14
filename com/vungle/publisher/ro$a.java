package com.vungle.publisher;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.vungle.publisher.image.AssetBitmapFactory.a;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.ro.b;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class ro$a {
    @Inject
    Context f3314a;
    @Inject
    mv f3315b;
    @Inject
    zo f3316c;

    @Inject
    ro$a() {
    }

    public ro m4619a() {
        ro roVar = new ro(this.f3314a, null);
        ImageView imageView = new ImageView(this.f3314a);
        ro.a(roVar, imageView);
        this.f3316c.m4939a(imageView, a.a);
        roVar.addView(imageView);
        LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        roVar.setCloseVisibility(b.c);
        return roVar;
    }

    public void m4620a(ro roVar) {
        try {
            LayoutParams layoutParams = (LayoutParams) roVar.getLayoutParams();
            int a = (int) this.f3315b.m4396a(50);
            layoutParams.width = a;
            layoutParams.height = a;
        } catch (Exception e) {
            Logger.e(Logger.AD_TAG, "could not set close region dimensions. did you add it to a view yet?");
        }
    }
}
