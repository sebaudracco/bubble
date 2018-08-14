package com.vungle.publisher;

import android.content.ContentValues;
import com.vungle.publisher.log.Logger;
import java.util.Arrays;

/* compiled from: vungle */
public abstract class cn extends dp<String> {
    protected static final String f9864a = ("(SELECT DISTINCT ad_id FROM ad_report WHERE status IN ('" + cz$c.f9914d + "', '" + cz$c.f9913c + "'))");
    protected static final String f9865b = ("id NOT IN " + f9864a);
    protected String f9866c;
    protected String f9867d;
    protected m f9868e;
    protected C4168c f9869f;
    protected long f9870g;
    protected long f9871h;
    protected long f9872i;
    protected String f9873j;
    protected String f9874k;
    int f9875l;
    Long f9876m;
    String f9877n;
    int f9878o;
    long f9879p;
    String f9880q;
    String f9881r;
    public String f9882s;

    /* compiled from: vungle */
    public enum C4168c {
        aware,
        failed,
        invalid,
        preparing,
        ready,
        viewed,
        deleting
    }

    protected abstract a<?, ?> m12951a();

    protected abstract boolean m12957b();

    protected final String m12958c() {
        return "ad";
    }

    protected boolean i_() {
        return false;
    }

    public String m12959e() {
        return this.f9873j;
    }

    public m a_() {
        return this.f9868e;
    }

    public C4168c m12960g() {
        return this.f9869f;
    }

    public void m12954a(String str) {
        this.f9877n = str;
        this.f9880q = null;
    }

    public String m12961h() {
        if (this.f9880q == null && this.f9877n != null) {
            this.f9880q = qr.a(new Object[]{this.f9877n, qr.c(this.f9867d)});
        }
        return this.f9880q;
    }

    public String m12962i() {
        return this.f9874k;
    }

    public int m12963j() {
        return this.f9878o;
    }

    public void m12952a(int i) {
        this.f9878o = i;
    }

    public long m12964k() {
        return this.f9872i;
    }

    public String m12965l() {
        return this.f9866c;
    }

    public String m12966m() {
        return this.f9867d;
    }

    public String m12967n() {
        return this.f9881r;
    }

    public void m12953a(C4168c c4168c) {
        Logger.v(Logger.PREPARE_TAG, "setting status from " + this.f9869f + " to " + c4168c + " for " + B());
        this.f9869f = c4168c;
        if (c4168c == C4168c.failed) {
            this.f9872i = System.currentTimeMillis();
        }
    }

    public void m12956b(C4168c c4168c) {
        m12951a().a(Arrays.asList(new cn[]{this}), c4168c);
    }

    protected ContentValues m12950a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        this.f9871h = currentTimeMillis;
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(h_(), (String) c_());
            this.f9870g = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
            contentValues.put("type", this.f9868e.toString());
        }
        contentValues.put("ad_token", this.f9866c);
        contentValues.put("ad_token_hash", this.f9867d);
        contentValues.put("advertising_app_vungle_id", this.f9873j);
        contentValues.put("campaign_id", this.f9881r);
        contentValues.put("status", this.f9869f.toString());
        contentValues.put("update_timestamp_millis", Long.valueOf(currentTimeMillis));
        contentValues.put("failed_timestamp_millis", Long.valueOf(this.f9872i));
        contentValues.put("delete_local_content_attempts", Integer.valueOf(this.f9875l));
        contentValues.put("expiration_timestamp_seconds", this.f9876m);
        contentValues.put("parent_path", this.f9877n);
        contentValues.put("prepare_retry_count", Integer.valueOf(this.f9878o));
        contentValues.put("received_timestamp_millis", Long.valueOf(this.f9879p));
        return contentValues;
    }

    protected StringBuilder j_() {
        StringBuilder j_ = super.j_();
        a(j_, "type", this.f9868e);
        return j_;
    }

    protected StringBuilder m12968p() {
        StringBuilder p = super.p();
        a(p, "ad_token", this.f9866c);
        a(p, "advertising_app_vungle_id", this.f9873j);
        a(p, "campaign_id", this.f9881r);
        a(p, "insert_timestamp_millis", Long.valueOf(this.f9870g));
        a(p, "status", this.f9869f);
        a(p, "update_timestamp_millis", Long.valueOf(this.f9871h));
        a(p, "failed_timestamp_millis", Long.valueOf(this.f9872i));
        a(p, "delete_local_content_attempts", Integer.valueOf(this.f9875l));
        a(p, "expiration_timestamp_seconds", this.f9876m);
        a(p, "parent_path", this.f9877n);
        a(p, "prepare_retry_count", Integer.valueOf(this.f9878o));
        a(p, "received_timestamp_millis", Long.valueOf(this.f9879p));
        return p;
    }

    public int m12969q() {
        int i = this.f9875l;
        this.f9875l = i + 1;
        if (m12951a().a((String) c_(), m12966m(), new C4168c[]{C4168c.ready, C4168c.preparing})) {
            return super.q();
        }
        if (m12957b()) {
            return super.q();
        }
        Logger.w(Logger.DATABASE_TAG, "unable to delete files for " + B() + " attempt " + i);
        f_();
        return 0;
    }

    public boolean equals(Object ad) {
        return (ad instanceof cn) && m12955a((cn) ad);
    }

    public boolean m12955a(cn cnVar) {
        return (cnVar == null || cnVar.u == null || !((String) cnVar.u).equals(this.u)) ? false : true;
    }

    public int hashCode() {
        return this.u == null ? super.hashCode() : ((String) this.u).hashCode();
    }

    public boolean m12970r() {
        return m12951a().a(this);
    }
}
