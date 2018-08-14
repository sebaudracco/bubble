package com.adcolony.sdk;

import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import com.adcolony.sdk.aa.C0595a;
import java.util.HashMap;
import org.json.JSONObject;

class aq {
    final String f654a;
    private final int f655b;
    private HashMap<Integer, Integer> f656c = new HashMap();
    private HashMap<Integer, Integer> f657d = new HashMap();
    private HashMap<Integer, Boolean> f658e = new HashMap();
    private HashMap<Integer, Integer> f659f = new HashMap();
    private HashMap<Integer, Integer> f660g = new HashMap();
    private HashMap<String, Integer> f661h = new HashMap();
    private SoundPool f662i;

    aq(final String str, final int i) {
        this.f654a = str;
        this.f655b = i;
        this.f662i = new SoundPool(50, 3, 0);
        this.f662i.setOnLoadCompleteListener(new OnLoadCompleteListener(this) {
            final /* synthetic */ aq f653c;

            public void onLoadComplete(SoundPool sp, int id, int status) {
                JSONObject a = C0802y.m1453a();
                C0802y.m1472b(a, "id", ((Integer) this.f653c.f659f.get(Integer.valueOf(id))).intValue());
                C0802y.m1462a(a, "ad_session_id", str);
                if (status == 0) {
                    new af("AudioPlayer.on_ready", i, a).m695b();
                    this.f653c.f660g.put(this.f653c.f659f.get(Integer.valueOf(id)), Integer.valueOf(id));
                    return;
                }
                new af("AudioPlayer.on_error", i, a).m695b();
            }
        });
    }

    void m775a(af afVar) {
        JSONObject c = afVar.m698c();
        int load = this.f662i.load(C0802y.m1468b(c, "filepath"), 1);
        int i = C0802y.m1477d(c, "repeats") ? -1 : 0;
        this.f659f.put(Integer.valueOf(load), Integer.valueOf(C0802y.m1473c(c, "id")));
        new C0595a().m622a("Load audio with id = ").m620a(load).m624a(aa.f480d);
        this.f657d.put(Integer.valueOf(load), Integer.valueOf(i));
        this.f658e.put(Integer.valueOf(load), Boolean.valueOf(false));
    }

    void m776b(af afVar) {
        this.f662i.unload(((Integer) this.f660g.get(Integer.valueOf(C0802y.m1473c(afVar.m698c(), "id")))).intValue());
    }

    void m777c(af afVar) {
        int intValue = ((Integer) this.f660g.get(Integer.valueOf(C0802y.m1473c(afVar.m698c(), "id")))).intValue();
        if (((Boolean) this.f658e.get(Integer.valueOf(intValue))).booleanValue()) {
            this.f662i.resume(((Integer) this.f656c.get(Integer.valueOf(intValue))).intValue());
            return;
        }
        int play = this.f662i.play(intValue, 1.0f, 1.0f, 0, ((Integer) this.f657d.get(Integer.valueOf(intValue))).intValue(), 1.0f);
        if (play != 0) {
            this.f656c.put(Integer.valueOf(intValue), Integer.valueOf(play));
            return;
        }
        JSONObject a = C0802y.m1453a();
        C0802y.m1472b(a, "id", C0802y.m1473c(afVar.m698c(), "id"));
        C0802y.m1462a(a, "ad_session_id", this.f654a);
        new af("AudioPlayer.on_error", this.f655b, a).m695b();
    }

    void m778d(af afVar) {
        int intValue = ((Integer) this.f660g.get(Integer.valueOf(C0802y.m1473c(afVar.m698c(), "id")))).intValue();
        this.f662i.pause(((Integer) this.f656c.get(Integer.valueOf(intValue))).intValue());
        this.f658e.put(Integer.valueOf(intValue), Boolean.valueOf(true));
    }

    void m779e(af afVar) {
        this.f662i.stop(((Integer) this.f656c.get(this.f660g.get(Integer.valueOf(C0802y.m1473c(afVar.m698c(), "id"))))).intValue());
    }

    SoundPool m774a() {
        return this.f662i;
    }
}
