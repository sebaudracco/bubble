package com.inmobi.ads;

import android.content.Context;
import android.widget.FrameLayout;

public abstract class NativeV2ScrollableContainer extends FrameLayout {
    private final TYPE f6946a;

    public enum TYPE {
        TYPE_PAGED,
        TYPE_FREE
    }

    interface C2963a {
        int mo6172a(int i);
    }

    abstract void mo6215a(ak akVar, aq aqVar, int i, int i2, C2963a c2963a);

    public NativeV2ScrollableContainer(Context context) {
        super(context);
        this.f6946a = TYPE.TYPE_PAGED;
    }

    public NativeV2ScrollableContainer(Context context, TYPE type) {
        super(context);
        this.f6946a = type;
    }

    public final TYPE getType() {
        return this.f6946a;
    }
}
