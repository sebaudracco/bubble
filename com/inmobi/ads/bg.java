package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.inmobi.ads.NativeV2ScrollableContainer.C2963a;
import com.inmobi.ads.NativeV2ScrollableContainer.TYPE;

@SuppressLint({"ViewConstructor"})
/* compiled from: ScrollableDeckFreeContainer */
class bg extends NativeV2ScrollableContainer {
    private static final String f7257a = bh.class.getSimpleName();
    private RecyclerView f7258b;
    private boolean f7259c = false;

    public bg(Context context, TYPE type) {
        super(context, type);
    }

    public void mo6215a(@NonNull ak akVar, aq aqVar, int i, int i2, C2963a c2963a) {
        LayoutParams layoutParams = (LayoutParams) NativeStrandViewFactory.m8952a(akVar.m9362b(0), (ViewGroup) this);
        if (VERSION.SDK_INT >= 17) {
            layoutParams.setMarginStart(20);
            layoutParams.setMarginEnd(20);
        } else {
            layoutParams.setMargins(20, 0, 20, 0);
        }
        layoutParams.gravity = i2;
        this.f7258b = new RecyclerView(getContext());
        this.f7258b.setLayoutParams(new LayoutParams(-1, -1));
        this.f7258b.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        addView(this.f7258b);
        this.f7258b.setAdapter((NativeV2RecyclerViewAdapter) aqVar);
    }
}
