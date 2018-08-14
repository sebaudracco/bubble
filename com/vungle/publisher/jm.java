package com.vungle.publisher;

import android.content.ContentValues;
import com.vungle.publisher.ei.a;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
public abstract class jm<A extends cn> extends dp<Integer> implements ei<A> {
    protected String f10525o;
    protected C4238m f10526p;
    protected a f10527q;
    protected b f10528r;
    protected A f10529s;

    protected abstract cn.a<A, ?> m13502y();

    public /* synthetic */ Object c_() {
        return m13491M();
    }

    protected jm() {
    }

    protected String m13496c() {
        return "viewable";
    }

    public Integer m13491M() {
        return (Integer) this.u;
    }

    public String m13498f() {
        return this.f10525o;
    }

    public A m13492N() {
        if (this.f10529s == null) {
            this.f10529s = (cn) m13502y().a(this.f10525o, false);
        }
        return this.f10529s;
    }

    public String m13497d() {
        return m13492N().h();
    }

    public a m13499j() {
        return this.f10527q;
    }

    public void m13494a(a aVar) {
        Logger.m13644v(Logger.PREPARE_TAG, "setting " + this.f10528r + " status from " + this.f10527q + " to " + aVar + " for " + "ad_id" + ": " + this.f10525o);
        this.f10527q = aVar;
    }

    public void m13495b(a aVar) {
        Logger.m13644v(Logger.PREPARE_TAG, "updating " + this.f10528r + " status from " + this.f10527q + " to " + aVar + " for " + "ad_id" + ": " + this.f10525o);
        this.f10527q = aVar;
        f_();
    }

    public b m13500o() {
        return this.f10528r;
    }

    protected ContentValues mo6942a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(h_(), (Integer) this.u);
            contentValues.put("ad_id", this.f10525o);
            contentValues.put("type", this.f10528r.toString());
            contentValues.put("ad_type", this.f10526p.toString());
        }
        contentValues.put("status", this.f10527q.toString());
        return contentValues;
    }

    protected StringBuilder mo6943p() {
        StringBuilder p = super.p();
        a(p, "ad_id", this.f10525o);
        a(p, "status", this.f10527q);
        a(p, "type", this.f10528r);
        return p;
    }

    protected String m13490C() {
        return String.valueOf(m13500o());
    }
}
