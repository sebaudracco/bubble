package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class C2403x extends View {
    private C2024w f5980a;

    public C2403x(Context context, C2024w c2024w) {
        super(context);
        this.f5980a = c2024w;
        setLayoutParams(new LayoutParams(0, 0));
    }

    public void onWindowVisibilityChanged(int i) {
        if (this.f5980a != null) {
            this.f5980a.mo3724a(i);
        }
    }
}
