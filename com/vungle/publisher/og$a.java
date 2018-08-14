package com.vungle.publisher;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.vungle.publisher.image.AssetBitmapFactory.a;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class og$a {
    @Inject
    zo f3174a;
    @Inject
    qg f3175b;

    @Inject
    og$a() {
    }

    public og m4471a(Context context, boolean z) {
        og ogVar = new og(context);
        ogVar.setGravity(16);
        ImageView oaVar = new oa(context);
        this.f3174a.m4939a(oaVar, a.f);
        View textView = new TextView(context);
        textView.setText("privacy");
        textView.setTextSize(16.0f);
        textView.setTextColor(-1);
        textView.setVisibility(8);
        textView.setPadding(10, 0, 10, 0);
        if (z) {
            ogVar.addView(oaVar);
            ogVar.addView(textView);
        } else {
            ogVar.addView(textView);
            ogVar.addView(oaVar);
        }
        og.a(ogVar, this.f3175b);
        og.a(ogVar, textView);
        ogVar.setVisibility(8);
        return ogVar;
    }
}
