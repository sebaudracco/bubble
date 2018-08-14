package com.facebook.ads.internal.p056q.p057a;

import android.content.Context;
import android.media.AudioManager;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C2132u {
    public static float m6828a(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            int streamVolume = audioManager.getStreamVolume(3);
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            if (streamMaxVolume > 0) {
                return (((float) streamVolume) * 1.0f) / ((float) streamMaxVolume);
            }
        }
        return 0.0f;
    }

    public static void m6829a(Map<String, String> map, boolean z, boolean z2) {
        map.put("autoplay", z ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        map.put("inline", z2 ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
    }
}
