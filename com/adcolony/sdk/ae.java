package com.adcolony.sdk;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

class ae {
    final String f520a;
    private final int f521b;
    private HashMap<Integer, MediaPlayer> f522c = new HashMap();
    private HashMap<Integer, C0606a> f523d = new HashMap();
    private HashMap<Integer, Boolean> f524e = new HashMap();
    private HashMap<Integer, Boolean> f525f = new HashMap();
    private ArrayList<MediaPlayer> f526g = new ArrayList();

    private class C0606a implements OnErrorListener, OnPreparedListener {
        int f517a;
        boolean f518b;
        final /* synthetic */ ae f519c;

        C0606a(ae aeVar, int i, boolean z) {
            this.f519c = aeVar;
            this.f517a = i;
            this.f518b = z;
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            JSONObject a = C0802y.m1453a();
            C0802y.m1472b(a, "id", this.f517a);
            C0802y.m1462a(a, "ad_session_id", this.f519c.f520a);
            new af("AudioPlayer.on_error", this.f519c.f521b, a).m695b();
            return true;
        }

        public void onPrepared(MediaPlayer mp) {
            mp.setLooping(this.f518b);
            this.f519c.f524e.put(Integer.valueOf(this.f517a), Boolean.valueOf(true));
            JSONObject a = C0802y.m1453a();
            C0802y.m1472b(a, "id", this.f517a);
            C0802y.m1462a(a, "ad_session_id", this.f519c.f520a);
            new af("AudioPlayer.on_ready", this.f519c.f521b, a).m695b();
        }
    }

    ae(String str, int i) {
        this.f520a = str;
        this.f521b = i;
    }

    void m685a(af afVar) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        JSONObject c = afVar.m698c();
        int c2 = C0802y.m1473c(c, "id");
        Object c0606a = new C0606a(this, c2, C0802y.m1477d(c, "repeats"));
        this.f522c.put(Integer.valueOf(c2), mediaPlayer);
        this.f523d.put(Integer.valueOf(c2), c0606a);
        this.f524e.put(Integer.valueOf(c2), Boolean.valueOf(false));
        this.f525f.put(Integer.valueOf(c2), Boolean.valueOf(false));
        mediaPlayer.setOnErrorListener(c0606a);
        mediaPlayer.setOnPreparedListener(c0606a);
        try {
            mediaPlayer.setDataSource(C0802y.m1468b(c, "filepath"));
        } catch (Exception e) {
            c = C0802y.m1453a();
            C0802y.m1472b(c, "id", c2);
            C0802y.m1462a(c, "ad_session_id", this.f520a);
            new af("AudioPlayer.on_error", this.f521b, c).m695b();
        }
        mediaPlayer.prepareAsync();
    }

    void m684a() {
        this.f526g.clear();
        for (MediaPlayer mediaPlayer : this.f522c.values()) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                this.f526g.add(mediaPlayer);
            }
        }
    }

    void m686b() {
        Iterator it = this.f526g.iterator();
        while (it.hasNext()) {
            ((MediaPlayer) it.next()).start();
        }
        this.f526g.clear();
    }

    void m687b(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        if (((Boolean) this.f525f.get(Integer.valueOf(c))).booleanValue()) {
            ((MediaPlayer) this.f522c.get(Integer.valueOf(c))).pause();
        }
    }

    void m689c(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        if (((Boolean) this.f524e.get(Integer.valueOf(c))).booleanValue()) {
            ((MediaPlayer) this.f522c.get(Integer.valueOf(c))).start();
            this.f525f.put(Integer.valueOf(c), Boolean.valueOf(true));
        }
    }

    void m690d(af afVar) {
        ((MediaPlayer) this.f522c.remove(Integer.valueOf(C0802y.m1473c(afVar.m698c(), "id")))).release();
    }

    void m691e(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        if (((Boolean) this.f525f.get(Integer.valueOf(c))).booleanValue()) {
            MediaPlayer mediaPlayer = (MediaPlayer) this.f522c.get(Integer.valueOf(c));
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
    }

    HashMap<Integer, MediaPlayer> m688c() {
        return this.f522c;
    }
}
