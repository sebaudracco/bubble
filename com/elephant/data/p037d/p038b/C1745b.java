package com.elephant.data.p037d.p038b;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.elephant.data.p048g.C1814b;

public final class C1745b extends ContentObserver {
    private Context f3605a;

    static {
        String str = C1814b.go;
    }

    public C1745b(Context context, Handler handler) {
        super(handler);
        this.f3605a = context;
    }

    public final void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        C1748e.m5034a(this.f3605a).m5046a(uri);
    }
}
