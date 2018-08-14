package com.moat.analytics.mobile.inm;

import android.media.MediaPlayer;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

class ag extends C3373i<MediaPlayer> implements NativeVideoTracker {
    public ag(String str, C3371a c3371a, ao aoVar) {
        super(str, c3371a, aoVar);
    }

    protected Map<String, Object> mo6474a() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.f.get();
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    public /* synthetic */ boolean mo6475a(Map map, Object obj, View view) {
        return trackVideoAd(map, (MediaPlayer) obj, view);
    }

    public void changeTargetView(View view) {
        super.changeTargetView(view);
    }

    protected Integer mo6477f() {
        return Integer.valueOf(((MediaPlayer) this.f.get()).getCurrentPosition());
    }

    protected boolean mo6478g() {
        return ((MediaPlayer) this.f.get()).isPlaying();
    }

    protected Integer mo6479h() {
        return Integer.valueOf(((MediaPlayer) this.f.get()).getDuration());
    }

    public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        if (mediaPlayer == null) {
            m11492a("Null player instance. Not tracking.");
        }
        try {
            mediaPlayer.getCurrentPosition();
            return super.mo6475a(map, mediaPlayer, view);
        } catch (IllegalStateException e) {
            m11492a("Playback has already completed. Not tracking.");
            return false;
        }
    }
}
