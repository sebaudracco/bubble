package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class C3791e extends C3784a {
    private static final String[] f9105f = new String[0];

    protected C3791e(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "browser_type_data", "disableBrowserTypeCollector", true, true);
    }

    public String mo6804a() {
        return this.c.getPackageManager().resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://")), 65536).loadLabel(this.c.getPackageManager()).toString();
    }

    public String[] mo6805b() {
        return f9105f;
    }
}
