package com.facebook.ads.internal.p060b;

import android.os.Bundle;
import android.view.View;
import com.facebook.ads.internal.p056q.p057a.C1912o;
import com.facebook.ads.internal.p070r.C2154a;
import java.util.ArrayList;
import java.util.List;

public final class C1942a implements C1912o<Bundle> {
    private final View f4490a;
    private final List<C1945d> f4491b;
    private C1944c f4492c;

    public C1942a(View view, List<C1909b> list) {
        this.f4490a = view;
        this.f4491b = new ArrayList(list.size());
        for (C1909b c1945d : list) {
            this.f4491b.add(new C1945d(c1945d));
        }
        this.f4492c = new C1944c();
    }

    public C1942a(View view, List<C1909b> list, Bundle bundle) {
        this.f4490a = view;
        this.f4491b = new ArrayList(list.size());
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("TESTS");
        for (int i = 0; i < list.size(); i++) {
            this.f4491b.add(new C1945d((C1909b) list.get(i), (Bundle) parcelableArrayList.get(i)));
        }
        this.f4492c = (C1944c) bundle.getSerializable("STATISTICS");
    }

    public void m6125a() {
        this.f4492c.m6137a();
    }

    public void m6126a(double d, double d2) {
        if (d2 >= 0.0d) {
            this.f4492c.m6140b(d, d2);
        }
        double c = (double) C2154a.m6899a(this.f4490a, 0).m6925c();
        this.f4492c.m6138a(d, c);
        for (C1945d a : this.f4491b) {
            a.m6145a(d, c);
        }
    }

    public C1944c m6127b() {
        return this.f4492c;
    }

    public Bundle mo3678g() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("STATISTICS", this.f4492c);
        ArrayList arrayList = new ArrayList(this.f4491b.size());
        for (C1945d g : this.f4491b) {
            arrayList.add(g.mo3678g());
        }
        bundle.putParcelableArrayList("TESTS", arrayList);
        return bundle;
    }
}
