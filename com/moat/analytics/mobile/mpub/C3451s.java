package com.moat.analytics.mobile.mpub;

import android.media.MediaPlayer;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class C3451s extends C3426i implements NativeVideoTracker {
    private WeakReference<MediaPlayer> f8801;

    C3451s(String str) {
        super(str);
        C3412a.m11642(3, "NativeVideoTracker", this, "In initialization method.");
        if (str == null || str.isEmpty()) {
            String str2 = "PartnerCode is " + (str == null ? "null" : "empty");
            String str3 = "NativeDisplayTracker creation problem, " + str2;
            C3412a.m11642(3, "NativeVideoTracker", this, str3);
            C3412a.m11639("[ERROR] ", str3);
            this.ॱ = new C3442o(str2);
        }
        C3412a.m11639("[SUCCESS] ", "NativeVideoTracker created");
    }

    final boolean mo6520() {
        return (this.f8801 == null || this.f8801.get() == null) ? false : true;
    }

    final String m11785() {
        return "NativeVideoTracker";
    }

    public final boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        try {
            ˎ();
            ॱ();
            if (mediaPlayer == null) {
                throw new C3442o("Null player instance");
            }
            mediaPlayer.getCurrentPosition();
            this.f8801 = new WeakReference(mediaPlayer);
            return super.mo6518(map, view);
        } catch (Exception e) {
            throw new C3442o("Playback has already completed");
        } catch (Exception e2) {
            C3442o.m11756(e2);
            String ˎ = C3442o.m11755("trackVideoAd", e2);
            if (this.ˊ != null) {
                this.ˊ.onTrackingFailedToStart(ˎ);
            }
            C3412a.m11642(3, "NativeVideoTracker", this, ˎ);
            C3412a.m11639("[ERROR] ", "NativeVideoTracker " + ˎ);
            return false;
        }
    }

    final Integer mo6521() {
        return Integer.valueOf(((MediaPlayer) this.f8801.get()).getCurrentPosition());
    }

    final boolean mo6522() {
        return ((MediaPlayer) this.f8801.get()).isPlaying();
    }

    final Integer mo6523() {
        return Integer.valueOf(((MediaPlayer) this.f8801.get()).getDuration());
    }

    final Map<String, Object> mo6524() throws C3442o {
        MediaPlayer mediaPlayer = (MediaPlayer) this.f8801.get();
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        hashMap.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        hashMap.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return hashMap;
    }

    final void mo6519(List<String> list) throws C3442o {
        Object obj;
        if (this.f8801 == null || this.f8801.get() == null) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            list.add("Player is null");
        }
        super.mo6519(list);
    }
}
