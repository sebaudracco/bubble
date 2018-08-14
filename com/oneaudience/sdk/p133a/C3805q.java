package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build.VERSION;
import android.view.Display;
import com.oneaudience.sdk.model.TvInput;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.ArrayList;

public class C3805q extends C3784a {
    private final String f9133f = "Built-in Screen";
    private long f9134g;

    protected C3805q(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "tv_input_data", "disableTVInputCollector", false, false);
    }

    public String mo6804a() {
        if (VERSION.SDK_INT < 17) {
            return "";
        }
        this.f9134g = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (Display display : ((DisplayManager) this.c.getSystemService("display")).getDisplays()) {
            if (!display.getName().equals("Built-in Screen")) {
                arrayList.add(display.getName());
            }
        }
        C3833d.m12246a(a, "Saved displayed list size: " + arrayList.size());
        return arrayList.isEmpty() ? "" : m12083a((Object) new TvInput(this.f9134g, arrayList));
    }

    public String[] mo6805b() {
        return new String[0];
    }
}
