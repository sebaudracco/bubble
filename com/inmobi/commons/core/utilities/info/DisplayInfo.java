package com.inmobi.commons.core.utilities.info;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.util.HashMap;
import java.util.Map;

public class DisplayInfo {
    private static final String f7789a = DisplayInfo.class.getSimpleName();

    public enum ORIENTATION_VALUES {
        PORTRAIT(1),
        REVERSE_PORTRAIT(2),
        LANDSCAPE(3),
        REVERSE_LANDSCAPE(4);
        
        private int f7788a;

        private ORIENTATION_VALUES(int i) {
            this.f7788a = i;
        }

        public int getValue() {
            return this.f7788a;
        }
    }

    private static String m10424d() {
        C3161c a = m10420a();
        return a.m10439b() + "X" + a.m10438a();
    }

    public static int m10419a(int i) {
        return Math.round(m10420a().m10440c() * ((float) i));
    }

    private static String m10425e() {
        Context b = C3105a.m10078b();
        if (b == null) {
            return "0x0";
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) b.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        return i + "x" + displayMetrics.heightPixels;
    }

    public static C3161c m10420a() {
        Context b = C3105a.m10078b();
        if (b == null) {
            return new C3161c(0, 0, 2.0f);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) b.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        return new C3161c(Math.round(((float) displayMetrics.widthPixels) / f), Math.round(((float) displayMetrics.heightPixels) / f), f);
    }

    public static int m10422b(int i) {
        return Math.round(((float) i) / m10420a().m10440c());
    }

    public static int m10421b() {
        Context b = C3105a.m10078b();
        if (b == null) {
            return ORIENTATION_VALUES.PORTRAIT.getValue();
        }
        int rotation = ((WindowManager) b.getSystemService("window")).getDefaultDisplay().getRotation();
        switch (b.getResources().getConfiguration().orientation) {
            case 1:
                if (rotation == 1 || rotation == 2) {
                    return ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue();
                }
                return ORIENTATION_VALUES.PORTRAIT.getValue();
            case 2:
                if (rotation == 0 || rotation == 1) {
                    return ORIENTATION_VALUES.LANDSCAPE.getValue();
                }
                return ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue();
            default:
                return ORIENTATION_VALUES.PORTRAIT.getValue();
        }
    }

    private static float m10426f() {
        return new TextView(C3105a.m10078b()).getTextSize();
    }

    public static Map<String, String> m10423c() {
        Map<String, String> hashMap = new HashMap();
        try {
            hashMap.put("d-device-screen-density", String.valueOf(m10420a().m10440c()));
            hashMap.put("d-device-screen-size", m10424d());
            hashMap.put("d-density-dependent-screen-size", m10425e());
            hashMap.put("d-orientation", String.valueOf(m10421b()));
            hashMap.put("d-textsize", String.valueOf(m10426f()));
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7789a, "SDK encountered unexpected error in getting display info; " + e.getMessage());
        }
        return hashMap;
    }
}
