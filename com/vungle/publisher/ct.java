package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.log.Logger;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;

/* compiled from: vungle */
public class ct extends dp<String> {
    public String f2781a;
    public String f2782b;
    @Inject
    C1594a f2783c;

    /* compiled from: vungle */
    public static class C1594a extends C1592a<ct, String> {
        @Inject
        Provider<ct> f2780a;

        protected /* synthetic */ Object[] mo2960b(int i) {
            return m3601c(i);
        }

        public /* synthetic */ dp[] mo2962d(int i) {
            return m3597a(i);
        }

        protected /* synthetic */ dp g_() {
            return m3589a();
        }

        @Inject
        C1594a() {
        }

        public ct m3591a(String str, String str2) {
            ct a = m3589a();
            a.t = String.class;
            a.f2781a = str2;
            a.f2782b = str;
            return a;
        }

        public List<String> m3595a(ct... ctVarArr) {
            return super.mo2968a((dp[]) ctVarArr);
        }

        protected String mo2961c() {
            return "ad_placement";
        }

        protected ct m3590a(ct ctVar, Cursor cursor, boolean z) {
            ctVar.f2782b = ce.e(cursor, "placement_reference_id");
            ctVar.f2781a = ce.e(cursor, "ad_id");
            return ctVar;
        }

        protected ct m3589a() {
            return (ct) this.f2780a.get();
        }

        public ct[] m3597a(int i) {
            return new ct[i];
        }

        protected String[] m3601c(int i) {
            return new String[i];
        }

        public List<ct> m3593a(String str) {
            return super.m3529a("ad_id = ?", new String[]{str}, null);
        }

        public List<ct> m3598b(String str) {
            return super.m3529a("placement_reference_id = ?", new String[]{str}, null);
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m3604a();
    }

    public /* synthetic */ Object c_() {
        return m3608e();
    }

    public /* synthetic */ Object d_() {
        return m3610i();
    }

    public /* synthetic */ Object e_() {
        return m3609h();
    }

    @Inject
    protected ct() {
    }

    protected StringBuilder mo2976p() {
        return super.mo2976p();
    }

    public String toString() {
        return super.toString();
    }

    protected C1594a m3604a() {
        return this.f2783c;
    }

    protected String mo2966c() {
        return "ad_placement";
    }

    public String m3608e() {
        return this.f2781a;
    }

    protected void m3606a(String str) {
        this.f2781a = str;
    }

    protected String h_() {
        return "ad_id";
    }

    protected ContentValues mo2964a(boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ad_id", this.f2781a);
        contentValues.put("placement_reference_id", this.f2782b);
        return contentValues;
    }

    protected int mo2977q() {
        String c = mo2966c();
        String str = "placement_reference_id = ? AND ad_id = ? ";
        int delete = this.v.getWritableDatabase().delete(c, "placement_reference_id = ? AND ad_id = ? ", new String[]{this.f2782b, this.f2781a});
        switch (delete) {
            case 0:
                Logger.d(Logger.DATABASE_TAG, "no " + c + " rows updated ");
                break;
            case 1:
                Logger.d(Logger.DATABASE_TAG, "delete successful " + m3575B());
                break;
            default:
                Logger.w(Logger.DATABASE_TAG, "deleted " + delete + " " + c + " records");
                break;
        }
        return delete;
    }

    public int f_() {
        String c = mo2966c();
        String str = "placement_reference_id = ? AND ad_id = ? ";
        int a = this.v.m3508a(c, mo2964a(false), "placement_reference_id = ? AND ad_id = ? ", new String[]{this.f2782b, this.f2781a}, 3);
        switch (a) {
            case 0:
                Logger.d(Logger.DATABASE_TAG, "no " + c + " rows updated ");
                break;
            case 1:
                Logger.d(Logger.DATABASE_TAG, "update successful " + m3575B());
                break;
            default:
                Logger.w(Logger.DATABASE_TAG, "updated " + a + " " + c + " records");
                break;
        }
        return a;
    }

    public String m3609h() {
        return (String) super.e_();
    }

    public String m3610i() {
        return (String) super.d_();
    }

    protected boolean i_() {
        return false;
    }
}
