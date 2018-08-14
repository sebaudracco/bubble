package com.integralads.avid.library.inmobi.p126f;

import android.view.View;
import com.integralads.avid.library.inmobi.session.internal.C3333a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

/* compiled from: AvidViewObstructionValidator */
public class C3314c {
    private boolean f8454a;

    public void m11289a() {
        this.f8454a = false;
    }

    public void m11291b() {
        this.f8454a = true;
    }

    public void m11290a(View view, JSONObject jSONObject, Collection<C3333a> collection) {
        if (this.f8454a) {
            List arrayList = new ArrayList();
            for (C3333a c3333a : collection) {
                if (c3333a.m11404g() && c3333a.m11407j().m11435b(view)) {
                    arrayList.add(c3333a.m11399c());
                }
            }
            C3315d.m11298a(jSONObject, arrayList);
        }
    }
}
