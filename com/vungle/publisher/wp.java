package com.vungle.publisher;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class wp extends wc {
    protected String f11217p;
    protected C4263a f11218q;
    protected String f11219r;
    protected Integer f11220s;
    protected Integer f11221t;
    protected Integer f11222u;
    protected Integer f11223v;
    protected String f11224w;
    protected Integer f11225x;

    /* compiled from: vungle */
    public static class C4263a extends vs {
        protected Float f11247a;
        protected Integer f11248b;
        protected Boolean f11249c;
        protected Boolean f11250d;
        protected Integer f11251e;

        public /* synthetic */ Object mo6940b() throws JSONException {
            return mo6941a();
        }

        protected C4263a() {
        }

        public Float m14078c() {
            return this.f11247a;
        }

        public Boolean m14079d() {
            return this.f11249c;
        }

        public Boolean m14080f() {
            return this.f11250d;
        }

        public Integer m14081g() {
            return this.f11251e;
        }

        public Integer m14082h() {
            return this.f11248b;
        }

        public JSONObject mo6941a() throws JSONException {
            JSONObject a = super.mo6941a();
            a.putOpt("click_area", this.f11247a);
            a.putOpt("enabled", this.f11249c);
            a.putOpt("show_onclick", this.f11250d);
            a.putOpt("time_show", this.f11251e);
            a.putOpt("time_enabled", this.f11248b);
            return a;
        }
    }

    public String m14049q() {
        return this.f11217p;
    }

    public C4263a m14050r() {
        return this.f11218q;
    }

    public String m14051s() {
        return this.f11219r;
    }

    public Integer m14052t() {
        return this.f11220s;
    }

    public Integer m14053u() {
        return this.f11221t;
    }

    public Integer m14054v() {
        return this.f11222u;
    }

    public Integer m14055w() {
        return this.f11223v;
    }

    public String m14056x() {
        return this.f11224w;
    }

    public Integer m14057y() {
        return this.f11225x;
    }
}
