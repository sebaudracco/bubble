package com.facebook.ads.internal.view;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.facebook.ads.internal.adapters.C1900j;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p056q.p057a.C2130s;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.p070r.C2154a;
import com.facebook.ads.internal.p070r.C2154a.C2025a;
import com.facebook.ads.internal.view.C1923a.C1832a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C2360i extends Adapter<C2359b> {
    private final C2012c f5821a;
    private final C2130s f5822b;
    private final C1900j f5823c;
    @Nullable
    private C1832a f5824d;
    private int f5825e;
    private int f5826f;
    private String f5827g;
    private boolean f5828h;
    private int f5829i;
    private List<C2358a> f5830j;

    public static class C2358a {
        public String f5811a;
        public String f5812b;
        public String f5813c;
        public String f5814d;
        public String f5815e;
        private final int f5816f;
        private final int f5817g;
        @Nullable
        private C2154a f5818h;
        private boolean f5819i = false;

        public C2358a(int i, int i2, String str, String str2, String str3, String str4, String str5) {
            this.f5816f = i;
            this.f5817g = i2;
            this.f5815e = str;
            this.f5811a = str2;
            this.f5812b = str3;
            this.f5813c = str4;
            this.f5814d = str5;
        }

        private void m7458a(final C2012c c2012c, final C2130s c2130s, final String str, C2349h c2349h) {
            if (!this.f5819i) {
                if (this.f5818h != null) {
                    this.f5818h.m6920b();
                    this.f5818h = null;
                }
                this.f5818h = new C2154a(c2349h, 10, new C2025a(this) {
                    final /* synthetic */ C2358a f5810d;

                    public void mo3725a() {
                        if (!TextUtils.isEmpty(str)) {
                            Map a = this.f5810d.m7461a();
                            if (this.f5810d.f5818h != null) {
                                this.f5810d.f5818h.m6919a(a);
                            }
                            a.put("touch", C2119j.m6798a(c2130s.m6826e()));
                            c2012c.mo3709a(str, a);
                        }
                        this.f5810d.f5819i = true;
                    }
                });
                this.f5818h.m6918a(100);
                this.f5818h.m6921b(100);
                this.f5818h.m6917a();
            }
        }

        public Map<String, String> m7461a() {
            Map<String, String> hashMap = new HashMap();
            hashMap.put("cardind", this.f5816f + "");
            hashMap.put("cardcnt", this.f5817g + "");
            return hashMap;
        }
    }

    public static class C2359b extends ViewHolder {
        public C2349h f5820a;

        public C2359b(C2349h c2349h) {
            super(c2349h);
            this.f5820a = c2349h;
        }
    }

    public C2360i(List<C2358a> list, C2012c c2012c, C2130s c2130s, C1832a c1832a, C1900j c1900j, String str, int i, int i2, int i3, boolean z) {
        this.f5821a = c2012c;
        this.f5822b = c2130s;
        this.f5824d = c1832a;
        this.f5830j = list;
        this.f5826f = i;
        this.f5823c = c1900j;
        this.f5828h = z;
        this.f5827g = str;
        this.f5825e = i3;
        this.f5829i = i2;
    }

    public C2359b m7462a(ViewGroup viewGroup, int i) {
        return new C2359b(new C2349h(viewGroup.getContext(), this.f5823c, this.f5828h, this.f5821a, this.f5824d, this.f5827g));
    }

    public void m7463a(C2359b c2359b, int i) {
        LayoutParams marginLayoutParams = new MarginLayoutParams(this.f5826f, -2);
        marginLayoutParams.setMargins(i == 0 ? this.f5825e : this.f5829i, 0, i >= this.f5830j.size() + -1 ? this.f5825e : this.f5829i, 0);
        C2358a c2358a = (C2358a) this.f5830j.get(i);
        c2359b.f5820a.setImageUrl(c2358a.f5815e);
        c2359b.f5820a.setLayoutParams(marginLayoutParams);
        c2359b.f5820a.m7432a(c2358a.f5811a, c2358a.f5812b);
        c2359b.f5820a.m7433a(c2358a.f5813c, c2358a.f5814d, c2358a.m7461a());
        c2358a.m7458a(this.f5821a, this.f5822b, this.f5827g, c2359b.f5820a);
    }

    public int getItemCount() {
        return this.f5830j.size();
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m7463a((C2359b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m7462a(viewGroup, i);
    }
}
