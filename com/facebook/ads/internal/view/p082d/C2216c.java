package com.facebook.ads.internal.view.p082d;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.List;

public class C2216c extends Adapter<C2218e> {
    private final List<String> f5403a;
    private final int f5404b;

    C2216c(List<String> list, int i) {
        this.f5403a = list;
        this.f5404b = i;
    }

    public C2218e m7072a(ViewGroup viewGroup, int i) {
        return new C2218e(new C2217d(viewGroup.getContext()));
    }

    public void m7073a(C2218e c2218e, int i) {
        String str = (String) this.f5403a.get(i);
        LayoutParams marginLayoutParams = new MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(i == 0 ? this.f5404b * 4 : this.f5404b, 0, i >= getItemCount() + -1 ? this.f5404b * 4 : this.f5404b, 0);
        c2218e.m7075a().setLayoutParams(marginLayoutParams);
        c2218e.m7075a().m7074a(str);
    }

    public int getItemCount() {
        return this.f5403a.size();
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m7073a((C2218e) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m7072a(viewGroup, i);
    }
}
