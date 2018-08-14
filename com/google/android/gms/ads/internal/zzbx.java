package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.ViewSwitcher;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzald;
import com.google.android.gms.internal.ads.zzamt;
import com.google.android.gms.internal.ads.zzaqw;
import java.util.ArrayList;
import java.util.List;

public final class zzbx extends ViewSwitcher {
    private final zzald zzaed;
    @Nullable
    private final zzamt zzaee;
    private boolean zzaef = true;

    public zzbx(Context context, String str, String str2, OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        super(context);
        this.zzaed = new zzald(context);
        this.zzaed.setAdUnitId(str);
        this.zzaed.zzda(str2);
        if (context instanceof Activity) {
            this.zzaee = new zzamt((Activity) context, this, onGlobalLayoutListener, onScrollChangedListener);
        } else {
            this.zzaee = new zzamt(null, this, onGlobalLayoutListener, onScrollChangedListener);
        }
        this.zzaee.zzsc();
    }

    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.zzaee != null) {
            this.zzaee.onAttachedToWindow();
        }
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.zzaee != null) {
            this.zzaee.onDetachedFromWindow();
        }
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.zzaef) {
            this.zzaed.zze(motionEvent);
        }
        return false;
    }

    public final void removeAllViews() {
        int i;
        int i2 = 0;
        List arrayList = new ArrayList();
        for (i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt != null && (childAt instanceof zzaqw)) {
                arrayList.add((zzaqw) childAt);
            }
        }
        super.removeAllViews();
        ArrayList arrayList2 = (ArrayList) arrayList;
        i = arrayList2.size();
        while (i2 < i) {
            Object obj = arrayList2.get(i2);
            i2++;
            ((zzaqw) obj).destroy();
        }
    }

    public final zzald zzfr() {
        return this.zzaed;
    }

    public final void zzfs() {
        zzakb.v("Disable position monitoring on adFrame.");
        if (this.zzaee != null) {
            this.zzaee.zzsd();
        }
    }

    public final void zzft() {
        zzakb.v("Enable debug gesture detector on adFrame.");
        this.zzaef = true;
    }

    public final void zzfu() {
        zzakb.v("Disable debug gesture detector on adFrame.");
        this.zzaef = false;
    }
}
