package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.dp.C1592a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;

/* compiled from: vungle */
public class hk extends dp<String> {
    public boolean f2986a;
    public boolean f2987b;
    @Inject
    C1630a f2988c;

    /* compiled from: vungle */
    public static class C1630a extends C1592a<hk, String> {
        @Inject
        Provider<hk> f2985a;

        public /* bridge */ /* synthetic */ List mo2959a(String str, String[] strArr) {
            return super.mo2959a(str, strArr);
        }

        protected /* synthetic */ Object[] mo2960b(int i) {
            return m4097c(i);
        }

        public /* synthetic */ dp[] mo2962d(int i) {
            return m4093a(i);
        }

        protected /* synthetic */ dp g_() {
            return m4088a();
        }

        @Inject
        C1630a() {
        }

        public hk[] m4099d(List<s> list) {
            hk[] a = m4093a(list.size());
            int i = 0;
            for (s sVar : list) {
                a[i] = m4088a();
                a[i].t = String.class;
                a[i].mo2969a((Object) sVar.a);
                a[i].f2986a = sVar.b;
                a[i].f2987b = sVar.c;
                i++;
            }
            return a;
        }

        public List<String> m4092a(hk... hkVarArr) {
            return super.mo2968a((dp[]) hkVarArr);
        }

        public int m4094b(hk... hkVarArr) {
            int i = 0;
            for (hk e_ : hkVarArr) {
                if (((String) e_.e_()) != null) {
                    i++;
                }
            }
            return i;
        }

        protected String mo2961c() {
            return "placement";
        }

        protected hk m4089a(hk hkVar, Cursor cursor, boolean z) {
            hkVar.mo2969a((Object) ce.e(cursor, "id"));
            hkVar.f2986a = ce.a(cursor, "is_auto_cached").booleanValue();
            hkVar.f2987b = ce.a(cursor, "is_incentivized").booleanValue();
            return hkVar;
        }

        protected hk m4088a() {
            return (hk) this.f2985a.get();
        }

        public hk[] m4093a(int i) {
            return new hk[i];
        }

        protected String[] m4097c(int i) {
            return new String[i];
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m4101a();
    }

    @Inject
    protected hk() {
    }

    protected StringBuilder mo2976p() {
        return super.mo2976p();
    }

    public String toString() {
        return super.toString();
    }

    protected C1630a m4101a() {
        return this.f2988c;
    }

    protected String mo2966c() {
        return "placement";
    }

    protected ContentValues mo2964a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(h_(), (String) c_());
        }
        contentValues.put("is_auto_cached", Boolean.valueOf(this.f2986a));
        contentValues.put("is_incentivized", Boolean.valueOf(this.f2987b));
        return contentValues;
    }

    protected boolean i_() {
        return false;
    }
}
