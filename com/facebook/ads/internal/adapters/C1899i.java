package com.facebook.ads.internal.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p033n.C2026f;
import com.facebook.ads.internal.p056q.p057a.C2118i;
import com.facebook.ads.internal.view.C2193c;
import com.facebook.ads.internal.view.C2395u;
import com.facebook.ads.internal.view.hscroll.C2355b;
import com.facebook.ads.internal.view.p034b.C1897e;
import com.facebook.ads.internal.view.p034b.C2186d;
import java.util.List;

public class C1899i extends Adapter<C2193c> {
    private static final int f4261a = Color.argb(51, 0, 0, 0);
    private final List<e> f4262b;
    private final int f4263c;
    private final int f4264d;

    public C1899i(C2355b c2355b, List<e> list) {
        float f = c2355b.getContext().getResources().getDisplayMetrics().density;
        this.f4262b = list;
        this.f4263c = Math.round(f * 1.0f);
        this.f4264d = c2355b.getChildSpacing();
    }

    public C2193c m5826a(ViewGroup viewGroup, int i) {
        View c2395u = new C2395u(viewGroup.getContext());
        c2395u.setScaleType(ScaleType.CENTER_CROP);
        C2118i.m6796a(c2395u, C2118i.INTERNAL_AD_MEDIA);
        return new C2193c(c2395u);
    }

    public void m5827a(final C2193c c2193c, int i) {
        LayoutParams marginLayoutParams = new MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(i == 0 ? this.f4264d * 2 : this.f4264d, 0, i >= this.f4262b.size() + -1 ? this.f4264d * 2 : this.f4264d, 0);
        c2193c.f5325a.setBackgroundColor(0);
        c2193c.f5325a.setImageDrawable(null);
        c2193c.f5325a.setLayoutParams(marginLayoutParams);
        c2193c.f5325a.setPadding(this.f4263c, this.f4263c, this.f4263c, this.f4263c);
        e eVar = (e) this.f4262b.get(i);
        eVar.a(c2193c.f5325a);
        C2026f j = eVar.j();
        if (j != null) {
            C2186d a = new C2186d(c2193c.f5325a).m7000a();
            a.m7002a(new C1897e(this) {
                final /* synthetic */ C1899i f4260b;

                public void mo3668a() {
                    c2193c.f5325a.setBackgroundColor(C1899i.f4261a);
                }
            });
            a.m7004a(j.m6473a());
        }
    }

    public int getItemCount() {
        return this.f4262b.size();
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m5827a((C2193c) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m5826a(viewGroup, i);
    }
}
