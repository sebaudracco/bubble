package com.moat.analytics.mobile.vng;

import android.media.MediaPlayer;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

class C3517u extends C3490h<MediaPlayer> implements NativeVideoTracker {
    C3517u(String str) {
        super(str);
        C3511p.m11976a(3, "NativeVideoTracker", (Object) this, "In initialization method.");
        C3511p.m11978a("[SUCCESS] ", m11997a() + " created");
    }

    String m11997a() {
        return "NativeVideoTracker";
    }

    public /* synthetic */ boolean mo6530a(Map map, Object obj, View view) {
        return trackVideoAd(map, (MediaPlayer) obj, view);
    }

    protected Map<String, Object> mo6543g() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.j.get();
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    protected Integer mo6531j() {
        return Integer.valueOf(((MediaPlayer) this.j.get()).getCurrentPosition());
    }

    protected boolean mo6544k() {
        return ((MediaPlayer) this.j.get()).isPlaying();
    }

    protected Integer mo6545l() {
        return Integer.valueOf(((MediaPlayer) this.j.get()).getDuration());
    }

    public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        if (mediaPlayer == null) {
            C3511p.m11976a(3, "NativeVideoTracker", (Object) this, "Null player instance. Not tracking.");
            C3511p.m11978a("[ERROR] ", m11997a() + " tracking didn't start, Null player instance");
            return false;
        }
        try {
            mediaPlayer.getCurrentPosition();
            return super.mo6530a(map, mediaPlayer, view);
        } catch (Exception e) {
            C3511p.m11976a(3, "NativeVideoTracker", (Object) this, "Playback has already completed. Not tracking.");
            C3511p.m11978a("[ERROR] ", m11997a() + " tracking didn't started for " + e() + ", playback has already completed");
            return false;
        }
    }
}
