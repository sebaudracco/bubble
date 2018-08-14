package com.vungle.publisher;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public class wv extends wu {
    protected List<String> f11266a;
    protected List<String> f11267b;
    protected List<String> f11268c;
    protected List<String> f11269d;
    protected List<String> f11270e;
    protected List<String> f11271f;
    protected wa[] f11272g;
    protected List<String> f11273h;
    protected List<String> f11274i;
    protected List<String> f11275j;
    private Boolean f11276k;
    private String f11277l;

    public /* synthetic */ Object mo6940b() throws JSONException {
        return mo6941a();
    }

    protected wv() {
    }

    public List<String> m14101c() {
        return this.f11266a;
    }

    public List<String> m14102d() {
        return this.f11267b;
    }

    public List<String> m14103f() {
        return this.f11268c;
    }

    public List<String> m14104g() {
        return this.f11269d;
    }

    public List<String> m14105h() {
        return this.f11270e;
    }

    public List<String> m14106i() {
        return this.f11271f;
    }

    public wa[] m14107j() {
        return this.f11272g;
    }

    public List<String> m14108k() {
        return this.f11273h;
    }

    public List<String> m14109l() {
        return this.f11274i;
    }

    public List<String> m14110m() {
        return this.f11275j;
    }

    public Boolean m14111n() {
        return this.f11276k;
    }

    public String m14112o() {
        return this.f11277l;
    }

    public JSONObject mo6941a() throws JSONException {
        JSONObject a = super.mo6941a();
        a.putOpt("postroll_click", this.f11266a);
        a.putOpt("video_click", this.f11267b);
        a.putOpt("video_close", this.f11268c);
        a.putOpt("error", this.f11269d);
        a.putOpt("mute", this.f11270e);
        a.putOpt("pause", this.f11271f);
        a.putOpt("play_percentage", ra.m13857a(this.f11272g));
        a.putOpt("postroll_view", this.f11273h);
        a.putOpt("resume", this.f11274i);
        a.putOpt("unmute", this.f11275j);
        return a;
    }
}
