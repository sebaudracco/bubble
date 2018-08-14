package com.vungle.publisher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vungle.publisher.bw.b;
import com.vungle.publisher.env.C1613o;
import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.hk.C1630a;
import com.vungle.publisher.log.C1654g;
import com.vungle.publisher.log.Logger;
import dagger.Lazy;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

@Singleton
/* compiled from: vungle */
public class vc extends tq {
    @Inject
    qg f3389a;
    @Inject
    protected bw f3390b;
    @Inject
    Lazy<yk> f3391c;
    @Inject
    C1613o f3392d;
    @Inject
    zf f3393e;
    @Inject
    wm$a f3394f;
    @Inject
    Lazy<C1614r> f3395g;
    @Inject
    String f3396h;
    @Inject
    C1654g f3397i;
    @Inject
    yd f3398j;
    @Inject
    C1630a f3399k;
    private PublishSubject<Boolean> f3400l = PublishSubject.create();

    @Inject
    vc() {
    }

    public void m4727a(cz<?, ?, ?, ?> czVar) {
        qw.m4582a((cz) czVar).retryWhen(this.f3398j.m4898a("config failure retry")).subscribe(vd.m4743a(this, czVar), vi.m4748a(this));
    }

    /* synthetic */ void m4728a(cz czVar, JsonObject jsonObject) {
        this.f3397i.f3085c.info("report ad successfully sent");
        ((yk) this.f3391c.get()).m4912a((Integer) czVar.c_());
        czVar.m3655i().r();
    }

    /* synthetic */ void m4742d(Throwable th) {
        this.f3397i.f3085c.severe("error sending report ad: " + th.getMessage());
    }

    public void m4730a(String str, JsonObject jsonObject) {
        qw.m4588b(jsonObject).subscribe(vj.m4749a(this, str), vk.m4750a(this, str));
    }

    /* synthetic */ void m4736b(String str, JsonObject jsonObject) {
        File file = new File(str);
        if (file != null && file.exists()) {
            file.delete();
        }
        this.f3397i.f3085c.info("logs successfully reported");
    }

    /* synthetic */ void m4732a(String str, Throwable th) {
        File file = new File(str);
        if (file != null && file.exists()) {
            file.delete();
        }
        this.f3397i.f3085c.severe("error while reporting logs: " + th.getMessage());
    }

    public void m4725a() {
        this.f3400l.flatMap(vl.m4751a(this, zk.f(this.f3396h) ? "https://ads.api.vungle.com/config" : this.f3396h)).subscribe(vm.m4752a(this), vn.m4753a(this));
        this.f3400l.onNext(Boolean.valueOf(true));
    }

    /* synthetic */ Observable m4724a(String str, Boolean bool) {
        return qw.m4584a(str, this.f3392d.m3934d()).retryWhen(this.f3398j.m4898a("config failure retry"));
    }

    /* synthetic */ void m4739c(JsonObject jsonObject) {
        this.f3397i.f3085c.info("request config successful");
        List arrayList = new ArrayList();
        Iterator it = jsonObject.getAsJsonArray("placements").iterator();
        while (it.hasNext()) {
            JsonElement jsonElement = (JsonElement) it.next();
            s sVar = new s();
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            sVar.b = asJsonObject.get("is_auto_cached").getAsBoolean();
            sVar.c = asJsonObject.get("is_incentivized").getAsBoolean();
            sVar.a = asJsonObject.get("reference_id").getAsString();
            arrayList.add(sVar);
        }
        this.f3392d.m3919a(arrayList);
        hk[] d = this.f3399k.m4099d(arrayList);
        int b = this.f3399k.m4094b(d);
        if (b < d.length) {
            this.f3397i.f3085c.warning("Not all Placement were inserted or Updated. Expected: " + d.length + " Actual: " + b);
        }
        if (jsonObject.has("will_play_ad")) {
            asJsonObject = jsonObject.getAsJsonObject("will_play_ad");
            if (asJsonObject.has("enabled")) {
                this.f3392d.m3920a(asJsonObject.get("enabled").getAsBoolean());
                this.f3392d.m3916a(asJsonObject.get("request_timeout").getAsInt());
            }
        }
        if (jsonObject.has("config")) {
            asJsonObject = jsonObject.getAsJsonObject("config");
            if (asJsonObject.has("refresh_time")) {
                Observable.timer(asJsonObject.get("refresh_time").getAsLong(), TimeUnit.MILLISECONDS, Schedulers.io()).take(1).subscribe(vh.m4747a(this));
            }
        }
        if (jsonObject.has("playback")) {
            asJsonObject = jsonObject.getAsJsonObject("playback");
            if (asJsonObject.has("buffer_timeout")) {
                this.f3392d.m3924b(asJsonObject.get("buffer_timeout").getAsInt());
            } else {
                Logger.w(Logger.PROTOCOL_TAG, "null playback buffer timeout millis");
            }
        } else {
            Logger.w(Logger.PROTOCOL_TAG, "null playback buffer timeout millis");
        }
        if (jsonObject.has("viewability")) {
            asJsonObject = jsonObject.getAsJsonObject("viewability");
            if (asJsonObject.has("moat")) {
                this.f3392d.m3917a(Boolean.valueOf(asJsonObject.get("moat").getAsBoolean()));
            }
        }
        if (this.f3392d.m3937g() && !this.f3392d.m3938h()) {
            this.f3393e.m4923a();
        }
        if (jsonObject.has("logging")) {
            asJsonObject = jsonObject.getAsJsonObject("logging");
            if (asJsonObject.has("enabled")) {
                this.f3392d.m3925b(Boolean.valueOf(asJsonObject.get("enabled").getAsBoolean()));
            }
            this.f3397i.f3085c.info("setting logger status to " + this.f3392d.m3939i());
            this.f3397i.m4350a(this.f3392d.m3939i());
        }
        if (jsonObject.has("exception_reporting")) {
            this.f3392d.m3927b(jsonObject.get("exception_reporting").getAsBoolean());
        }
        if (jsonObject.has("vduid")) {
            this.f3392d.m3918a(jsonObject.get("vduid").getAsString());
        }
        this.f3389a.m4568a(new td(true, null));
    }

    /* synthetic */ void m4729a(Long l) {
        this.f3400l.onNext(Boolean.valueOf(true));
    }

    /* synthetic */ void m4740c(Throwable th) {
        this.f3397i.f3085c.severe("error sending request config: " + th.getMessage());
    }

    public void m4731a(String str, String str2) {
        qw.m4585a(str, false, str2).subscribe(vo.m4754a(this), vp.m4755a(this));
    }

    /* synthetic */ void m4735b(JsonObject jsonObject) {
        try {
            this.f3389a.m4568a(new au(this.f3394f.mo3020d(new JSONObject(jsonObject.toString())), null));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* synthetic */ void m4737b(Throwable th) {
        this.f3397i.f3085c.severe("error requesting streaming ad: " + th.getMessage());
        this.f3389a.m4568a(new ak());
    }

    public void m4734b() {
        qw.m4587b().retry(5).subscribe(ve.m4744a(this), vf.m4745a(this));
    }

    /* synthetic */ void m4726a(JsonObject jsonObject) {
        this.f3397i.f3085c.info("sending track install successful");
        ((C1614r) this.f3395g.get()).m3948b(true);
    }

    /* synthetic */ void m4733a(Throwable th) {
        this.f3397i.f3085c.severe("error sending track install: " + th.getMessage());
    }

    public void m4738c() {
        this.f3390b.m3474a(vg.m4746a(this), b.k);
    }

    /* synthetic */ void m4741d() {
        try {
            qw.m4590c();
        } catch (Exception e) {
            this.f3397i.f3085c.severe("error sending unfilled ad: " + e.getMessage());
        }
    }
}
