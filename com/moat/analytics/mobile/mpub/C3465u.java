package com.moat.analytics.mobile.mpub;

import android.graphics.Rect;
import android.location.Location;
import android.util.DisplayMetrics;
import android.view.View;
import com.mobfox.sdk.networking.RequestParams;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

final class C3465u {
    private static int f8839 = 0;
    private static int f8840 = 1;
    private Location f8841;
    private JSONObject f8842;
    private Rect f8843;
    private JSONObject f8844;
    private JSONObject f8845;
    private Rect f8846;
    private C3464c f8847 = new C3464c();
    String f8848 = "{}";
    private Map<String, Object> f8849 = new HashMap();

    static class C34611 implements Comparator<Rect> {
        C34611() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return Integer.valueOf(((Rect) obj).top).compareTo(Integer.valueOf(((Rect) obj2).top));
        }
    }

    static class C3462a {
        final Rect f8831;
        final View f8832;

        C3462a(View view, C3462a c3462a) {
            this.f8832 = view;
            if (c3462a != null) {
                int i = c3462a.f8831.left;
                i += view.getLeft();
                int top = c3462a.f8831.top + view.getTop();
                this.f8831 = new Rect(i, top, view.getWidth() + i, view.getHeight() + top);
                return;
            }
            this.f8831 = C3465u.m11818(view);
        }
    }

    static class C3463b {
        boolean f8833 = false;
        final Set<Rect> f8834 = new HashSet();
        int f8835 = 0;

        C3463b() {
        }
    }

    static class C3464c {
        double f8836 = 0.0d;
        Rect f8837 = new Rect(0, 0, 0, 0);
        double f8838 = 0.0d;

        C3464c() {
        }
    }

    C3465u() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void m11820(java.lang.String r19, android.view.View r20) {
        /*
        r18 = this;
        r11 = new java.util.HashMap;
        r11.<init>();
        r5 = "{}";
        r4 = 0;
        if (r20 == 0) goto L_0x0505;
    L_0x000b:
        r2 = 0;
    L_0x000c:
        switch(r2) {
            case 1: goto L_0x01fd;
            default: goto L_0x000f;
        };
    L_0x000f:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0340 }
        r3 = 17;
        if (r2 < r3) goto L_0x0508;
    L_0x0015:
        r2 = 1;
    L_0x0016:
        switch(r2) {
            case 1: goto L_0x01fe;
            default: goto L_0x0019;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x0019:
        r2 = r20.getContext();	 Catch:{ Exception -> 0x0340 }
        r2 = r2.getResources();	 Catch:{ Exception -> 0x0340 }
        r2 = r2.getDisplayMetrics();	 Catch:{ Exception -> 0x0340 }
        r10 = r2;
    L_0x0026:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0340 }
        r3 = 19;
        if (r2 < r3) goto L_0x0512;
    L_0x002c:
        r2 = 79;
    L_0x002e:
        switch(r2) {
            case 79: goto L_0x0250;
            default: goto L_0x0031;
        };
    L_0x0031:
        if (r20 == 0) goto L_0x051b;
    L_0x0033:
        r2 = 85;
    L_0x0035:
        switch(r2) {
            case 85: goto L_0x0269;
            default: goto L_0x0038;
        };
    L_0x0038:
        r2 = 0;
        r9 = r2;
    L_0x003a:
        if (r20 == 0) goto L_0x0523;
    L_0x003c:
        r2 = 1;
    L_0x003d:
        switch(r2) {
            case 0: goto L_0x0285;
            default: goto L_0x0040;
        };
    L_0x0040:
        r2 = f8840;
        r2 = r2 + 53;
        r3 = r2 % 128;
        f8839 = r3;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x004c;
    L_0x004c:
        r2 = r20.hasWindowFocus();	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x0526;
    L_0x0052:
        r2 = 12;
    L_0x0054:
        switch(r2) {
            case 33: goto L_0x0285;
            default: goto L_0x0057;
        };
    L_0x0057:
        r2 = f8839;
        r2 = r2 + 77;
        r3 = r2 % 128;
        f8840 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0063;
    L_0x0063:
        r2 = 1;
        r8 = r2;
    L_0x0065:
        if (r20 == 0) goto L_0x052a;
    L_0x0067:
        r2 = 15;
    L_0x0069:
        switch(r2) {
            case 15: goto L_0x0289;
            default: goto L_0x006c;
        };
    L_0x006c:
        r2 = 1;
        r7 = r2;
    L_0x006e:
        if (r20 != 0) goto L_0x0532;
    L_0x0070:
        r2 = 11;
    L_0x0072:
        switch(r2) {
            case 11: goto L_0x029a;
            default: goto L_0x0075;
        };
    L_0x0075:
        r2 = com.moat.analytics.mobile.mpub.C3465u.m11810(r20);	 Catch:{ Exception -> 0x0340 }
        r3 = r2;
    L_0x007a:
        r2 = "dr";
        r6 = r10.density;	 Catch:{ Exception -> 0x0340 }
        r6 = java.lang.Float.valueOf(r6);	 Catch:{ Exception -> 0x0340 }
        r11.put(r2, r6);	 Catch:{ Exception -> 0x0340 }
        r2 = "dv";
        r12 = com.moat.analytics.mobile.mpub.C3450r.m11783();	 Catch:{ Exception -> 0x0340 }
        r6 = java.lang.Double.valueOf(r12);	 Catch:{ Exception -> 0x0340 }
        r11.put(r2, r6);	 Catch:{ Exception -> 0x0340 }
        r2 = "adKey";
        r0 = r19;
        r11.put(r2, r0);	 Catch:{ Exception -> 0x0340 }
        r6 = "isAttached";
        if (r9 == 0) goto L_0x0536;
    L_0x00a1:
        r2 = 96;
    L_0x00a3:
        switch(r2) {
            case 96: goto L_0x029e;
            default: goto L_0x00a6;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x00a6:
        r2 = 0;
    L_0x00a7:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x0340 }
        r11.put(r6, r2);	 Catch:{ Exception -> 0x0340 }
        r6 = "inFocus";
        if (r8 == 0) goto L_0x053a;
    L_0x00b3:
        r2 = 1;
    L_0x00b4:
        switch(r2) {
            case 0: goto L_0x02a1;
            default: goto L_0x00b7;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x00b7:
        r2 = 1;
    L_0x00b8:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x0340 }
        r11.put(r6, r2);	 Catch:{ Exception -> 0x0340 }
        r6 = "isHidden";
        if (r7 == 0) goto L_0x053d;
    L_0x00c4:
        r2 = 0;
    L_0x00c5:
        switch(r2) {
            case 1: goto L_0x02a4;
            default: goto L_0x00c8;
        };
    L_0x00c8:
        r2 = f8840;
        r2 = r2 + 115;
        r12 = r2 % 128;
        f8839 = r12;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x059a;
    L_0x00d4:
        r2 = 0;
    L_0x00d5:
        switch(r2) {
            case 0: goto L_0x04d8;
            default: goto L_0x00d8;
        };
    L_0x00d8:
        r2 = 1;
    L_0x00d9:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x0340 }
        r11.put(r6, r2);	 Catch:{ Exception -> 0x0340 }
        r2 = "opacity";
        r3 = java.lang.Float.valueOf(r3);	 Catch:{ Exception -> 0x0340 }
        r11.put(r2, r3);	 Catch:{ Exception -> 0x0340 }
        r2 = r10.widthPixels;	 Catch:{ Exception -> 0x0340 }
        r3 = r10.heightPixels;	 Catch:{ Exception -> 0x0340 }
        r12 = new android.graphics.Rect;	 Catch:{ Exception -> 0x0340 }
        r6 = 0;
        r13 = 0;
        r12.<init>(r6, r13, r2, r3);	 Catch:{ Exception -> 0x0340 }
        if (r20 == 0) goto L_0x0540;
    L_0x00f7:
        r2 = 0;
    L_0x00f8:
        switch(r2) {
            case 0: goto L_0x02a7;
            default: goto L_0x00fb;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x00fb:
        r2 = new android.graphics.Rect;	 Catch:{ Exception -> 0x0340 }
        r3 = 0;
        r6 = 0;
        r13 = 0;
        r14 = 0;
        r2.<init>(r3, r6, r13, r14);	 Catch:{ Exception -> 0x0340 }
        r6 = r2;
    L_0x0105:
        r13 = new com.moat.analytics.mobile.mpub.u$c;	 Catch:{ Exception -> 0x0340 }
        r13.<init>();	 Catch:{ Exception -> 0x0340 }
        r2 = r6.width();	 Catch:{ Exception -> 0x0340 }
        r3 = r6.height();	 Catch:{ Exception -> 0x0340 }
        r3 = r3 * r2;
        if (r20 == 0) goto L_0x0543;
    L_0x0115:
        r2 = 1;
    L_0x0116:
        switch(r2) {
            case 0: goto L_0x0144;
            default: goto L_0x0119;
        };
    L_0x0119:
        r2 = f8839;
        r2 = r2 + 87;
        r14 = r2 % 128;
        f8840 = r14;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0125;
    L_0x0125:
        if (r9 == 0) goto L_0x0546;
    L_0x0127:
        r2 = 0;
    L_0x0128:
        switch(r2) {
            case 1: goto L_0x0144;
            default: goto L_0x012b;
        };
    L_0x012b:
        r2 = f8839;
        r2 = r2 + 81;
        r9 = r2 % 128;
        f8840 = r9;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0137;
    L_0x0137:
        if (r8 == 0) goto L_0x0549;
    L_0x0139:
        r2 = 68;
    L_0x013b:
        switch(r2) {
            case 9: goto L_0x0144;
            default: goto L_0x013e;
        };
    L_0x013e:
        if (r7 != 0) goto L_0x054d;
    L_0x0140:
        r2 = 0;
    L_0x0141:
        switch(r2) {
            case 0: goto L_0x02ae;
            default: goto L_0x0144;
        };
    L_0x0144:
        r0 = r18;
        r2 = r0.f8845;	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x0565;
    L_0x014a:
        r2 = 92;
    L_0x014c:
        switch(r2) {
            case 92: goto L_0x0371;
            default: goto L_0x014f;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x014f:
        r0 = r18;
        r0.f8847 = r13;	 Catch:{ Exception -> 0x0340 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r3 = r0.f8847;	 Catch:{ Exception -> 0x0340 }
        r3 = r3.f8837;	 Catch:{ Exception -> 0x0340 }
        r3 = com.moat.analytics.mobile.mpub.C3465u.m11812(r3, r10);	 Catch:{ Exception -> 0x0340 }
        r3 = com.moat.analytics.mobile.mpub.C3465u.m11817(r3);	 Catch:{ Exception -> 0x0340 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r0.f8845 = r2;	 Catch:{ Exception -> 0x0340 }
        r3 = 1;
    L_0x016b:
        r2 = "coveredPercent";
        r8 = r13.f8838;	 Catch:{ Exception -> 0x0340 }
        r4 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x0340 }
        r11.put(r2, r4);	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r2 = r0.f8844;	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x0570;
    L_0x017d:
        r2 = 65;
    L_0x017f:
        switch(r2) {
            case 65: goto L_0x03c0;
            default: goto L_0x0182;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x0182:
        r0 = r18;
        r0.f8846 = r12;	 Catch:{ Exception -> 0x0340 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0340 }
        r3 = com.moat.analytics.mobile.mpub.C3465u.m11812(r12, r10);	 Catch:{ Exception -> 0x0340 }
        r3 = com.moat.analytics.mobile.mpub.C3465u.m11817(r3);	 Catch:{ Exception -> 0x0340 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r0.f8844 = r2;	 Catch:{ Exception -> 0x0340 }
        r3 = 1;
    L_0x0198:
        r0 = r18;
        r2 = r0.f8842;	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x0574;
    L_0x019e:
        r2 = 1;
    L_0x019f:
        switch(r2) {
            case 0: goto L_0x01ac;
            default: goto L_0x01a2;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x01a2:
        r0 = r18;
        r2 = r0.f8843;	 Catch:{ Exception -> 0x0340 }
        r2 = r6.equals(r2);	 Catch:{ Exception -> 0x0340 }
        if (r2 != 0) goto L_0x01c2;
    L_0x01ac:
        r0 = r18;
        r0.f8843 = r6;	 Catch:{ Exception -> 0x0340 }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0340 }
        r3 = com.moat.analytics.mobile.mpub.C3465u.m11812(r6, r10);	 Catch:{ Exception -> 0x0340 }
        r3 = com.moat.analytics.mobile.mpub.C3465u.m11817(r3);	 Catch:{ Exception -> 0x0340 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r0.f8842 = r2;	 Catch:{ Exception -> 0x0340 }
        r3 = 1;
    L_0x01c2:
        r0 = r18;
        r2 = r0.f8849;	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x0577;
    L_0x01c8:
        r2 = 90;
    L_0x01ca:
        switch(r2) {
            case 71: goto L_0x03d8;
            default: goto L_0x01cd;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x01cd:
        r0 = r18;
        r2 = r0.f8849;	 Catch:{ Exception -> 0x0340 }
        r2 = r11.equals(r2);	 Catch:{ Exception -> 0x0340 }
        if (r2 != 0) goto L_0x057b;
    L_0x01d7:
        r2 = 0;
    L_0x01d8:
        switch(r2) {
            case 0: goto L_0x03d8;
            default: goto L_0x01db;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x01db:
        r2 = com.moat.analytics.mobile.mpub.C3441n.m11742();	 Catch:{ Exception -> 0x0340 }
        r6 = r2.m11751();	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r2 = r0.f8841;	 Catch:{ Exception -> 0x0340 }
        r2 = com.moat.analytics.mobile.mpub.C3441n.m11741(r6, r2);	 Catch:{ Exception -> 0x0340 }
        if (r2 != 0) goto L_0x057e;
    L_0x01ed:
        r2 = 11;
    L_0x01ef:
        switch(r2) {
            case 22: goto L_0x05a4;
            default: goto L_0x01f2;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x01f2:
        r2 = 1;
        r0 = r18;
        r0.f8841 = r6;	 Catch:{ Exception -> 0x0340 }
    L_0x01f7:
        if (r2 == 0) goto L_0x0582;
    L_0x01f9:
        r2 = 0;
    L_0x01fa:
        switch(r2) {
            case 0: goto L_0x03df;
            default: goto L_0x01fd;
        };
    L_0x01fd:
        return;
    L_0x01fe:
        r2 = f8840;
        r2 = r2 + 93;
        r3 = r2 % 128;
        f8839 = r3;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x020a;
    L_0x020a:
        r2 = com.moat.analytics.mobile.mpub.C3416c.f8677;	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x050b;
    L_0x020e:
        r2 = 1;
    L_0x020f:
        switch(r2) {
            case 0: goto L_0x0019;
            default: goto L_0x0212;
        };
    L_0x0212:
        r2 = f8840;
        r2 = r2 + 35;
        r3 = r2 % 128;
        f8839 = r3;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x021e;
    L_0x021e:
        r2 = com.moat.analytics.mobile.mpub.C3416c.f8677;	 Catch:{ Exception -> 0x0340 }
        r2 = r2.get();	 Catch:{ Exception -> 0x0340 }
        r2 = (android.app.Activity) r2;	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x050e;
    L_0x0228:
        r3 = 17;
    L_0x022a:
        switch(r3) {
            case 66: goto L_0x0019;
            default: goto L_0x022d;
        };
    L_0x022d:
        r3 = f8840;
        r3 = r3 + 91;
        r6 = r3 % 128;
        f8839 = r6;
        r3 = r3 % 2;
        if (r3 == 0) goto L_0x0590;
    L_0x0239:
        r3 = 1;
    L_0x023a:
        switch(r3) {
            case 1: goto L_0x04a9;
            default: goto L_0x023d;
        };
    L_0x023d:
        r3 = new android.util.DisplayMetrics;	 Catch:{ Exception -> 0x0340 }
        r3.<init>();	 Catch:{ Exception -> 0x0340 }
        r2 = r2.getWindowManager();	 Catch:{ Exception -> 0x0340 }
        r2 = r2.getDefaultDisplay();	 Catch:{ Exception -> 0x0340 }
        r2.getRealMetrics(r3);	 Catch:{ Exception -> 0x0340 }
        r10 = r3;
        goto L_0x0026;
    L_0x0250:
        if (r20 == 0) goto L_0x0515;
    L_0x0252:
        r2 = 0;
    L_0x0253:
        switch(r2) {
            case 0: goto L_0x04bc;
            default: goto L_0x0256;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x0256:
        r2 = 0;
        r9 = r2;
        goto L_0x003a;
    L_0x025a:
        r2 = r20.isAttachedToWindow();	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x0518;
    L_0x0260:
        r2 = 1;
    L_0x0261:
        switch(r2) {
            case 1: goto L_0x0265;
            default: goto L_0x0264;
        };
    L_0x0264:
        goto L_0x0256;
    L_0x0265:
        r2 = 1;
        r9 = r2;
        goto L_0x003a;
    L_0x0269:
        r2 = f8839;
        r2 = r2 + 97;
        r3 = r2 % 128;
        f8840 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0275;
    L_0x0275:
        r2 = r20.getWindowToken();	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x051f;
    L_0x027b:
        r2 = 2;
    L_0x027c:
        switch(r2) {
            case 2: goto L_0x0281;
            default: goto L_0x027f;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x027f:
        goto L_0x0038;
    L_0x0281:
        r2 = 1;
        r9 = r2;
        goto L_0x003a;
    L_0x0285:
        r2 = 0;
        r8 = r2;
        goto L_0x0065;
    L_0x0289:
        r2 = r20.isShown();	 Catch:{ Exception -> 0x0340 }
        if (r2 != 0) goto L_0x052e;
    L_0x028f:
        r2 = 63;
    L_0x0291:
        switch(r2) {
            case 98: goto L_0x0296;
            default: goto L_0x0294;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x0294:
        goto L_0x006c;
    L_0x0296:
        r2 = 0;
        r7 = r2;
        goto L_0x006e;
    L_0x029a:
        r2 = 0;
        r3 = r2;
        goto L_0x007a;
    L_0x029e:
        r2 = 1;
        goto L_0x00a7;
    L_0x02a1:
        r2 = 0;
        goto L_0x00b8;
    L_0x02a4:
        r2 = 0;
        goto L_0x00d9;
    L_0x02a7:
        r2 = com.moat.analytics.mobile.mpub.C3465u.m11818(r20);	 Catch:{ Exception -> 0x0340 }
        r6 = r2;
        goto L_0x0105;
    L_0x02ae:
        r2 = f8840;
        r2 = r2 + 35;
        r7 = r2 % 128;
        f8839 = r7;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x02ba;
    L_0x02ba:
        if (r3 <= 0) goto L_0x0550;
    L_0x02bc:
        r2 = 0;
    L_0x02bd:
        switch(r2) {
            case 1: goto L_0x0144;
            default: goto L_0x02c0;
        };
    L_0x02c0:
        r2 = f8839;
        r2 = r2 + 113;
        r7 = r2 % 128;
        f8840 = r7;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x02cc;
    L_0x02cc:
        r7 = new android.graphics.Rect;	 Catch:{ Exception -> 0x0340 }
        r2 = 0;
        r8 = 0;
        r9 = 0;
        r14 = 0;
        r7.<init>(r2, r8, r9, r14);	 Catch:{ Exception -> 0x0340 }
        r0 = r20;
        r2 = com.moat.analytics.mobile.mpub.C3465u.m11815(r0, r7);	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x0553;
    L_0x02dd:
        r2 = 3;
    L_0x02de:
        switch(r2) {
            case 3: goto L_0x02e3;
            default: goto L_0x02e1;
        };
    L_0x02e1:
        goto L_0x0144;
    L_0x02e3:
        r2 = f8840;
        r2 = r2 + 41;
        r8 = r2 % 128;
        f8839 = r8;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x02ef;
    L_0x02ef:
        r2 = r7.width();	 Catch:{ Exception -> 0x0340 }
        r8 = r7.height();	 Catch:{ Exception -> 0x0340 }
        r8 = r8 * r2;
        if (r8 >= r3) goto L_0x0557;
    L_0x02fa:
        r2 = 0;
    L_0x02fb:
        switch(r2) {
            case 0: goto L_0x034b;
            default: goto L_0x02fe;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x02fe:
        r2 = r20.getRootView();	 Catch:{ Exception -> 0x0340 }
        r2 = r2 instanceof android.view.ViewGroup;	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x055a;
    L_0x0306:
        r2 = 56;
    L_0x0308:
        switch(r2) {
            case 56: goto L_0x030d;
            default: goto L_0x030b;
        };
    L_0x030b:
        goto L_0x0144;
    L_0x030d:
        r2 = f8840;
        r2 = r2 + 49;
        r9 = r2 % 128;
        f8839 = r9;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0319;
    L_0x0319:
        r13.f8837 = r7;	 Catch:{ Exception -> 0x0340 }
        r0 = r20;
        r9 = com.moat.analytics.mobile.mpub.C3465u.m11814(r7, r0);	 Catch:{ Exception -> 0x0340 }
        r2 = r9.f8833;	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x055e;
    L_0x0325:
        r2 = 1;
    L_0x0326:
        switch(r2) {
            case 1: goto L_0x0362;
            default: goto L_0x0329;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x0329:
        r2 = r9.f8834;	 Catch:{ Exception -> 0x0340 }
        r7 = com.moat.analytics.mobile.mpub.C3465u.m11811(r7, r2);	 Catch:{ Exception -> 0x0340 }
        if (r7 <= 0) goto L_0x0561;
    L_0x0331:
        r2 = 32;
    L_0x0333:
        switch(r2) {
            case 32: goto L_0x0368;
            default: goto L_0x0336;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x0336:
        r2 = r8 - r7;
        r8 = (double) r2;	 Catch:{ Exception -> 0x0340 }
        r2 = (double) r3;	 Catch:{ Exception -> 0x0340 }
        r2 = r8 / r2;
        r13.f8836 = r2;	 Catch:{ Exception -> 0x0340 }
        goto L_0x0144;
    L_0x0340:
        r2 = move-exception;
        r3 = r5;
    L_0x0342:
        com.moat.analytics.mobile.mpub.C3442o.m11756(r2);
        r0 = r18;
        r0.f8848 = r3;
        goto L_0x01fd;
    L_0x034b:
        r2 = f8840;
        r2 = r2 + 83;
        r9 = r2 % 128;
        f8839 = r9;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0357;
    L_0x0357:
        r2 = "VisibilityInfo";
        r9 = 0;
        r14 = "Ad is clipped";
        com.moat.analytics.mobile.mpub.C3412a.m11643(r2, r9, r14);	 Catch:{ Exception -> 0x0340 }
        goto L_0x02fe;
    L_0x0362:
        r2 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r13.f8838 = r2;	 Catch:{ Exception -> 0x0340 }
        goto L_0x0144;
    L_0x0368:
        r14 = (double) r7;	 Catch:{ Exception -> 0x0340 }
        r0 = (double) r8;	 Catch:{ Exception -> 0x0340 }
        r16 = r0;
        r14 = r14 / r16;
        r13.f8838 = r14;	 Catch:{ Exception -> 0x0340 }
        goto L_0x0336;
    L_0x0371:
        r2 = f8840;
        r2 = r2 + 5;
        r3 = r2 % 128;
        f8839 = r3;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x037d;
    L_0x037d:
        r2 = r13.f8836;	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r7 = r0.f8847;	 Catch:{ Exception -> 0x0340 }
        r8 = r7.f8836;	 Catch:{ Exception -> 0x0340 }
        r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x0569;
    L_0x0389:
        r2 = 1;
    L_0x038a:
        switch(r2) {
            case 0: goto L_0x014f;
            default: goto L_0x038d;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x038d:
        r2 = r13.f8837;	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r3 = r0.f8847;	 Catch:{ Exception -> 0x0340 }
        r3 = r3.f8837;	 Catch:{ Exception -> 0x0340 }
        r2 = r2.equals(r3);	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x056c;
    L_0x039b:
        r2 = 41;
    L_0x039d:
        switch(r2) {
            case 55: goto L_0x014f;
            default: goto L_0x03a0;
        };
    L_0x03a0:
        r2 = f8839;
        r2 = r2 + 123;
        r3 = r2 % 128;
        f8840 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x059d;
    L_0x03ac:
        r2 = 41;
    L_0x03ae:
        switch(r2) {
            case 41: goto L_0x04db;
            default: goto L_0x03b1;
        };
    L_0x03b1:
        r2 = r13.f8838;	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r7 = r0.f8847;	 Catch:{ Exception -> 0x0340 }
        r8 = r7.f8838;	 Catch:{ Exception -> 0x0340 }
        r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x014f;
    L_0x03bd:
        r3 = r4;
        goto L_0x016b;
    L_0x03c0:
        r2 = f8839;
        r2 = r2 + 1;
        r4 = r2 % 128;
        f8840 = r4;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x03cc;
    L_0x03cc:
        r0 = r18;
        r2 = r0.f8846;	 Catch:{ Exception -> 0x0340 }
        r2 = r12.equals(r2);	 Catch:{ Exception -> 0x0340 }
        if (r2 != 0) goto L_0x0198;
    L_0x03d6:
        goto L_0x0182;
    L_0x03d8:
        r0 = r18;
        r0.f8849 = r11;	 Catch:{ Exception -> 0x0340 }
        r3 = 1;
        goto L_0x01db;
    L_0x03df:
        r4 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r2 = r0.f8849;	 Catch:{ Exception -> 0x0340 }
        r4.<init>(r2);	 Catch:{ Exception -> 0x0340 }
        r2 = "screen";
        r0 = r18;
        r3 = r0.f8844;	 Catch:{ Exception -> 0x0340 }
        r4.accumulate(r2, r3);	 Catch:{ Exception -> 0x0340 }
        r2 = "view";
        r0 = r18;
        r3 = r0.f8842;	 Catch:{ Exception -> 0x0340 }
        r4.accumulate(r2, r3);	 Catch:{ Exception -> 0x0340 }
        r2 = "visible";
        r0 = r18;
        r3 = r0.f8845;	 Catch:{ Exception -> 0x0340 }
        r4.accumulate(r2, r3);	 Catch:{ Exception -> 0x0340 }
        r2 = "maybe";
        r0 = r18;
        r3 = r0.f8845;	 Catch:{ Exception -> 0x0340 }
        r4.accumulate(r2, r3);	 Catch:{ Exception -> 0x0340 }
        r2 = "visiblePercent";
        r0 = r18;
        r3 = r0.f8847;	 Catch:{ Exception -> 0x0340 }
        r8 = r3.f8836;	 Catch:{ Exception -> 0x0340 }
        r3 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x0340 }
        r4.accumulate(r2, r3);	 Catch:{ Exception -> 0x0340 }
        if (r6 == 0) goto L_0x0585;
    L_0x0422:
        r2 = 0;
    L_0x0423:
        switch(r2) {
            case 0: goto L_0x04ea;
            default: goto L_0x0426;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x0426:
        r3 = r4.toString();	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r0.f8848 = r3;	 Catch:{ Exception -> 0x0430 }
        goto L_0x01fd;
    L_0x0430:
        r2 = move-exception;
        goto L_0x0342;
    L_0x0433:
        r2 = 8;
    L_0x0435:
        switch(r2) {
            case 11: goto L_0x04fa;
            default: goto L_0x0438;
        };
    L_0x0438:
        r2 = "location";
        if (r6 != 0) goto L_0x0588;
    L_0x043d:
        r3 = 50;
    L_0x043f:
        switch(r3) {
            case 85: goto L_0x045f;
            default: goto L_0x0442;
        };
    L_0x0442:
        r3 = r4;
        r6 = 0;
        r7 = r3;
        r3 = r6;
        r6 = r2;
    L_0x0447:
        if (r3 != 0) goto L_0x058c;
    L_0x0449:
        r2 = 61;
    L_0x044b:
        switch(r2) {
            case 74: goto L_0x04a3;
            default: goto L_0x044e;
        };
    L_0x044e:
        r2 = f8839;
        r2 = r2 + 71;
        r3 = r2 % 128;
        f8840 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x045a;
    L_0x045a:
        r2 = 0;
    L_0x045b:
        r7.accumulate(r6, r2);	 Catch:{ Exception -> 0x0340 }
        goto L_0x0426;
    L_0x045f:
        r3 = r2;
        r7 = r6;
        r6 = r4;
        r2 = new java.util.HashMap;	 Catch:{ Exception -> 0x0340 }
        r2.<init>();	 Catch:{ Exception -> 0x0340 }
        r8 = "latitude";
        r10 = r7.getLatitude();	 Catch:{ Exception -> 0x0340 }
        r9 = java.lang.Double.toString(r10);	 Catch:{ Exception -> 0x0340 }
        r2.put(r8, r9);	 Catch:{ Exception -> 0x0340 }
        r8 = "longitude";
        r10 = r7.getLongitude();	 Catch:{ Exception -> 0x0340 }
        r9 = java.lang.Double.toString(r10);	 Catch:{ Exception -> 0x0340 }
        r2.put(r8, r9);	 Catch:{ Exception -> 0x0340 }
        r8 = "timestamp";
        r10 = r7.getTime();	 Catch:{ Exception -> 0x0340 }
        r9 = java.lang.Long.toString(r10);	 Catch:{ Exception -> 0x0340 }
        r2.put(r8, r9);	 Catch:{ Exception -> 0x0340 }
        r8 = "horizontalAccuracy";
        r7 = r7.getAccuracy();	 Catch:{ Exception -> 0x0340 }
        r7 = java.lang.Float.toString(r7);	 Catch:{ Exception -> 0x0340 }
        r2.put(r8, r7);	 Catch:{ Exception -> 0x0340 }
        r7 = r6;
        r6 = r3;
        r3 = r2;
        goto L_0x0447;
    L_0x04a3:
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0340 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0340 }
        goto L_0x045b;
    L_0x04a9:
        r3 = new android.util.DisplayMetrics;	 Catch:{ Exception -> 0x0340 }
        r3.<init>();	 Catch:{ Exception -> 0x0340 }
        r2 = r2.getWindowManager();	 Catch:{ Exception -> 0x0340 }
        r2 = r2.getDefaultDisplay();	 Catch:{ Exception -> 0x0340 }
        r2.getRealMetrics(r3);	 Catch:{ Exception -> 0x0340 }
        r10 = r3;
        goto L_0x0026;
    L_0x04bc:
        r2 = f8839;
        r2 = r2 + 109;
        r3 = r2 % 128;
        f8840 = r3;
        r2 = r2 % 2;
        if (r2 != 0) goto L_0x0593;
    L_0x04c8:
        r2 = 2;
    L_0x04c9:
        switch(r2) {
            case 17: goto L_0x025a;
            default: goto L_0x04cc;
        };
    L_0x04cc:
        r2 = r20.isAttachedToWindow();	 Catch:{ Exception -> 0x0340 }
        if (r2 == 0) goto L_0x0597;
    L_0x04d2:
        r2 = 1;
    L_0x04d3:
        switch(r2) {
            case 0: goto L_0x0256;
            default: goto L_0x04d6;
        };	 Catch:{ Exception -> 0x0340 }
    L_0x04d6:
        goto L_0x0265;
    L_0x04d8:
        r2 = 1;
        goto L_0x00d9;
    L_0x04db:
        r2 = r13.f8838;	 Catch:{ Exception -> 0x0340 }
        r0 = r18;
        r7 = r0.f8847;	 Catch:{ Exception -> 0x0340 }
        r8 = r7.f8838;	 Catch:{ Exception -> 0x0340 }
        r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x014f;
    L_0x04e7:
        r3 = r4;
        goto L_0x016b;
    L_0x04ea:
        r2 = f8840;
        r2 = r2 + 27;
        r3 = r2 % 128;
        f8839 = r3;
        r2 = r2 % 2;
        if (r2 == 0) goto L_0x0433;
    L_0x04f6:
        r2 = 11;
        goto L_0x0435;
    L_0x04fa:
        r2 = "location";
        if (r6 != 0) goto L_0x05a1;
    L_0x04ff:
        r3 = 1;
    L_0x0500:
        switch(r3) {
            case 0: goto L_0x045f;
            default: goto L_0x0503;
        };
    L_0x0503:
        goto L_0x0442;
    L_0x0505:
        r2 = 1;
        goto L_0x000c;
    L_0x0508:
        r2 = 0;
        goto L_0x0016;
    L_0x050b:
        r2 = 0;
        goto L_0x020f;
    L_0x050e:
        r3 = 66;
        goto L_0x022a;
    L_0x0512:
        r2 = 7;
        goto L_0x002e;
    L_0x0515:
        r2 = 1;
        goto L_0x0253;
    L_0x0518:
        r2 = 0;
        goto L_0x0261;
    L_0x051b:
        r2 = 25;
        goto L_0x0035;
    L_0x051f:
        r2 = 80;
        goto L_0x027c;
    L_0x0523:
        r2 = 0;
        goto L_0x003d;
    L_0x0526:
        r2 = 33;
        goto L_0x0054;
    L_0x052a:
        r2 = 61;
        goto L_0x0069;
    L_0x052e:
        r2 = 98;
        goto L_0x0291;
    L_0x0532:
        r2 = 15;
        goto L_0x0072;
    L_0x0536:
        r2 = 86;
        goto L_0x00a3;
    L_0x053a:
        r2 = 0;
        goto L_0x00b4;
    L_0x053d:
        r2 = 1;
        goto L_0x00c5;
    L_0x0540:
        r2 = 1;
        goto L_0x00f8;
    L_0x0543:
        r2 = 0;
        goto L_0x0116;
    L_0x0546:
        r2 = 1;
        goto L_0x0128;
    L_0x0549:
        r2 = 9;
        goto L_0x013b;
    L_0x054d:
        r2 = 1;
        goto L_0x0141;
    L_0x0550:
        r2 = 1;
        goto L_0x02bd;
    L_0x0553:
        r2 = 91;
        goto L_0x02de;
    L_0x0557:
        r2 = 1;
        goto L_0x02fb;
    L_0x055a:
        r2 = 33;
        goto L_0x0308;
    L_0x055e:
        r2 = 0;
        goto L_0x0326;
    L_0x0561:
        r2 = 12;
        goto L_0x0333;
    L_0x0565:
        r2 = 21;
        goto L_0x014c;
    L_0x0569:
        r2 = 0;
        goto L_0x038a;
    L_0x056c:
        r2 = 55;
        goto L_0x039d;
    L_0x0570:
        r2 = 78;
        goto L_0x017f;
    L_0x0574:
        r2 = 0;
        goto L_0x019f;
    L_0x0577:
        r2 = 71;
        goto L_0x01ca;
    L_0x057b:
        r2 = 1;
        goto L_0x01d8;
    L_0x057e:
        r2 = 22;
        goto L_0x01ef;
    L_0x0582:
        r2 = 1;
        goto L_0x01fa;
    L_0x0585:
        r2 = 1;
        goto L_0x0423;
    L_0x0588:
        r3 = 85;
        goto L_0x043f;
    L_0x058c:
        r2 = 74;
        goto L_0x044b;
    L_0x0590:
        r3 = 0;
        goto L_0x023a;
    L_0x0593:
        r2 = 17;
        goto L_0x04c9;
    L_0x0597:
        r2 = 0;
        goto L_0x04d3;
    L_0x059a:
        r2 = 1;
        goto L_0x00d5;
    L_0x059d:
        r2 = 98;
        goto L_0x03ae;
    L_0x05a1:
        r3 = 0;
        goto L_0x0500;
    L_0x05a4:
        r2 = r3;
        goto L_0x01f7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.u.ˋ(java.lang.String, android.view.View):void");
    }

    private static float m11810(View view) {
        float alpha = view.getAlpha();
        while (true) {
            if (view != null) {
                Object obj = null;
            } else {
                int i = 1;
            }
            switch (obj) {
                case 1:
                    break;
                default:
                    if (view.getParent() != null) {
                        obj = null;
                    } else {
                        i = 1;
                    }
                    switch (obj) {
                        case 1:
                            break;
                        default:
                            switch (((double) alpha) != 0.0d ? 8 : 86) {
                                case 8:
                                    switch (view.getParent() instanceof View ? 13 : 76) {
                                        case 13:
                                            i = f8839 + 9;
                                            f8840 = i % 128;
                                            switch (i % 2 == 0 ? 1 : null) {
                                                case 1:
                                                    alpha *= ((View) view.getParent()).getAlpha();
                                                    view = (View) view.getParent();
                                                    break;
                                                default:
                                                    alpha *= ((View) view.getParent()).getAlpha();
                                                    view = (View) view.getParent();
                                                    break;
                                            }
                                        default:
                                            break;
                                    }
                                default:
                                    break;
                            }
                    }
            }
            return alpha;
        }
    }

    static Rect m11813(View view) {
        switch (view != null ? 1 : 0) {
            case 0:
                return new Rect(0, 0, 0, 0);
            default:
                int i = f8839 + 27;
                f8840 = i % 128;
                switch (i % 2 == 0 ? 99 : 3) {
                    case 99:
                        return C3465u.m11818(view);
                    default:
                        return C3465u.m11818(view);
                }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m11819(com.moat.analytics.mobile.mpub.C3465u.C3462a r10, android.graphics.Rect r11, com.moat.analytics.mobile.mpub.C3465u.C3463b r12) {
        /*
        r5 = 3;
        r6 = 0;
        r3 = 1;
        r2 = 0;
        r1 = r10.f8832;
        r0 = r1.isShown();
        if (r0 == 0) goto L_0x01e5;
    L_0x000d:
        r0 = r3;
    L_0x000e:
        switch(r0) {
            case 1: goto L_0x01a0;
            default: goto L_0x0011;
        };
    L_0x0011:
        r0 = r2;
    L_0x0012:
        if (r0 != 0) goto L_0x002a;
    L_0x0014:
        return;
    L_0x0015:
        r0 = 50;
    L_0x0017:
        switch(r0) {
            case 96: goto L_0x01b0;
            default: goto L_0x001a;
        };
    L_0x001a:
        r0 = r1.getAlpha();
        r0 = (double) r0;
        r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x01e8;
    L_0x0023:
        r0 = 72;
    L_0x0025:
        switch(r0) {
            case 82: goto L_0x0011;
            default: goto L_0x0028;
        };
    L_0x0028:
        r0 = r3;
        goto L_0x0012;
    L_0x002a:
        r0 = r10.f8832;
        r0 = r0 instanceof android.view.ViewGroup;
        if (r0 == 0) goto L_0x01ec;
    L_0x0030:
        r0 = r2;
    L_0x0031:
        switch(r0) {
            case 0: goto L_0x00ba;
            default: goto L_0x0034;
        };
    L_0x0034:
        r1 = r3;
    L_0x0035:
        if (r1 == 0) goto L_0x0204;
    L_0x0037:
        r0 = r2;
    L_0x0038:
        switch(r0) {
            case 0: goto L_0x003c;
            default: goto L_0x003b;
        };
    L_0x003b:
        goto L_0x0014;
    L_0x003c:
        r0 = f8840;
        r0 = r0 + 55;
        r1 = r0 % 128;
        f8839 = r1;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x021d;
    L_0x0048:
        r0 = r2;
    L_0x0049:
        switch(r0) {
            case 1: goto L_0x015e;
            default: goto L_0x004c;
        };
    L_0x004c:
        r0 = r10.f8831;
        r1 = r0.setIntersect(r11, r0);
        if (r1 == 0) goto L_0x0220;
    L_0x0054:
        r1 = 38;
    L_0x0056:
        switch(r1) {
            case 60: goto L_0x0014;
            default: goto L_0x0059;
        };
    L_0x0059:
        r1 = android.os.Build.VERSION.SDK_INT;
        r4 = 22;
        if (r1 < r4) goto L_0x020a;
    L_0x005f:
        r1 = r2;
    L_0x0060:
        switch(r1) {
            case 1: goto L_0x008d;
            default: goto L_0x0063;
        };
    L_0x0063:
        r1 = new android.graphics.Rect;
        r1.<init>(r2, r2, r2, r2);
        r0 = r10.f8832;
        r0 = com.moat.analytics.mobile.mpub.C3465u.m11815(r0, r1);
        if (r0 == 0) goto L_0x020d;
    L_0x0070:
        r0 = 32;
    L_0x0072:
        switch(r0) {
            case 89: goto L_0x0014;
            default: goto L_0x0075;
        };
    L_0x0075:
        r0 = f8839;
        r0 = r0 + 21;
        r4 = r0 % 128;
        f8840 = r4;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x0224;
    L_0x0081:
        r0 = r2;
    L_0x0082:
        switch(r0) {
            case 0: goto L_0x01db;
            default: goto L_0x0085;
        };
    L_0x0085:
        r0 = r10.f8831;
        r1 = r0.setIntersect(r1, r0);
        if (r1 == 0) goto L_0x0014;
    L_0x008d:
        r1 = com.moat.analytics.mobile.mpub.C3460t.m11803();
        r1 = r1.f8822;
        if (r1 == 0) goto L_0x0211;
    L_0x0095:
        r1 = 92;
    L_0x0097:
        switch(r1) {
            case 92: goto L_0x016c;
            default: goto L_0x009a;
        };
    L_0x009a:
        r1 = r12.f8834;
        r1.add(r0);
        r0 = r0.contains(r11);
        if (r0 == 0) goto L_0x0215;
    L_0x00a5:
        switch(r2) {
            case 0: goto L_0x00aa;
            default: goto L_0x00a8;
        };
    L_0x00a8:
        goto L_0x0014;
    L_0x00aa:
        r0 = f8839;
        r0 = r0 + 39;
        r1 = r0 % 128;
        f8840 = r1;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x00b6;
    L_0x00b6:
        r12.f8833 = r3;
        goto L_0x0014;
    L_0x00ba:
        r0 = f8839;
        r0 = r0 + 3;
        r1 = r0 % 128;
        f8840 = r1;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x00c6;
    L_0x00c6:
        r0 = android.view.ViewGroup.class;
        r1 = r10.f8832;
        r1 = r1.getClass();
        r1 = r1.getSuperclass();
        r1 = r0.equals(r1);
        r4 = r10.f8832;
        r0 = android.os.Build.VERSION.SDK_INT;
        r6 = 19;
        if (r0 < r6) goto L_0x01ef;
    L_0x00de:
        r0 = r2;
    L_0x00df:
        switch(r0) {
            case 0: goto L_0x01bf;
            default: goto L_0x00e2;
        };
    L_0x00e2:
        r0 = r3;
    L_0x00e3:
        if (r1 == 0) goto L_0x01f9;
    L_0x00e5:
        r1 = r3;
    L_0x00e6:
        switch(r1) {
            case 1: goto L_0x013c;
            default: goto L_0x00e9;
        };
    L_0x00e9:
        r1 = r3;
    L_0x00ea:
        r0 = r10.f8832;
        r0 = (android.view.ViewGroup) r0;
        r7 = r0.getChildCount();
        r6 = r2;
    L_0x00f3:
        if (r6 >= r7) goto L_0x0200;
    L_0x00f5:
        r4 = r5;
    L_0x00f6:
        switch(r4) {
            case 3: goto L_0x00fb;
            default: goto L_0x00f9;
        };
    L_0x00f9:
        goto L_0x0035;
    L_0x00fb:
        r4 = r12.f8835;
        r4 = r4 + 1;
        r12.f8835 = r4;
        r8 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r4 > r8) goto L_0x0014;
    L_0x0105:
        r4 = new com.moat.analytics.mobile.mpub.u$a;
        r8 = r0.getChildAt(r6);
        r4.<init>(r8, r10);
        com.moat.analytics.mobile.mpub.C3465u.m11819(r4, r11, r12);
        r4 = r12.f8833;
        if (r4 != 0) goto L_0x0014;
    L_0x0115:
        r4 = r6 + 1;
        r6 = r4;
        goto L_0x00f3;
    L_0x0119:
        r0 = 17;
    L_0x011b:
        switch(r0) {
            case 1: goto L_0x01ce;
            default: goto L_0x011e;
        };
    L_0x011e:
        r0 = r4.getBackground();
        if (r0 == 0) goto L_0x01f2;
    L_0x0124:
        r0 = r2;
    L_0x0125:
        switch(r0) {
            case 0: goto L_0x012a;
            default: goto L_0x0128;
        };
    L_0x0128:
        r0 = r3;
        goto L_0x00e3;
    L_0x012a:
        r0 = r4.getBackground();
        r0 = r0.getAlpha();
        if (r0 != 0) goto L_0x01f5;
    L_0x0134:
        r0 = 18;
    L_0x0136:
        switch(r0) {
            case 53: goto L_0x013a;
            default: goto L_0x0139;
        };
    L_0x0139:
        goto L_0x0128;
    L_0x013a:
        r0 = r2;
        goto L_0x00e3;
    L_0x013c:
        r1 = f8839;
        r1 = r1 + 95;
        r4 = r1 % 128;
        f8840 = r4;
        r1 = r1 % 2;
        if (r1 != 0) goto L_0x0148;
    L_0x0148:
        if (r0 == 0) goto L_0x01fc;
    L_0x014a:
        r0 = 13;
    L_0x014c:
        switch(r0) {
            case 13: goto L_0x0150;
            default: goto L_0x014f;
        };
    L_0x014f:
        goto L_0x00e9;
    L_0x0150:
        r0 = f8839;
        r0 = r0 + 15;
        r1 = r0 % 128;
        f8840 = r1;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x015c;
    L_0x015c:
        r1 = r2;
        goto L_0x00ea;
    L_0x015e:
        r0 = r10.f8831;
        r1 = r0.setIntersect(r11, r0);
        if (r1 == 0) goto L_0x0207;
    L_0x0166:
        r1 = r3;
    L_0x0167:
        switch(r1) {
            case 0: goto L_0x0014;
            default: goto L_0x016a;
        };
    L_0x016a:
        goto L_0x0059;
    L_0x016c:
        r1 = "VisibilityInfo";
        r4 = r10.f8832;
        r6 = java.util.Locale.ROOT;
        r7 = "Covered by %s-%s alpha=%f";
        r5 = new java.lang.Object[r5];
        r8 = r10.f8832;
        r8 = r8.getClass();
        r8 = r8.getName();
        r5[r2] = r8;
        r8 = r0.toString();
        r5[r3] = r8;
        r8 = 2;
        r9 = r10.f8832;
        r9 = r9.getAlpha();
        r9 = java.lang.Float.valueOf(r9);
        r5[r8] = r9;
        r5 = java.lang.String.format(r6, r7, r5);
        com.moat.analytics.mobile.mpub.C3412a.m11643(r1, r4, r5);
        goto L_0x009a;
    L_0x01a0:
        r0 = f8839;
        r0 = r0 + 69;
        r4 = r0 % 128;
        f8840 = r4;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x0015;
    L_0x01ac:
        r0 = 96;
        goto L_0x0017;
    L_0x01b0:
        r0 = r1.getAlpha();
        r0 = (double) r0;
        r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x0218;
    L_0x01b9:
        r0 = r2;
    L_0x01ba:
        switch(r0) {
            case 1: goto L_0x0011;
            default: goto L_0x01bd;
        };
    L_0x01bd:
        goto L_0x0028;
    L_0x01bf:
        r0 = f8840;
        r0 = r0 + 53;
        r6 = r0 % 128;
        f8839 = r6;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x0119;
    L_0x01cb:
        r0 = r3;
        goto L_0x011b;
    L_0x01ce:
        r0 = r4.getBackground();
        if (r0 == 0) goto L_0x021a;
    L_0x01d4:
        r0 = 95;
    L_0x01d6:
        switch(r0) {
            case 95: goto L_0x012a;
            default: goto L_0x01d9;
        };
    L_0x01d9:
        goto L_0x0128;
    L_0x01db:
        r0 = r10.f8831;
        r1 = r0.setIntersect(r1, r0);
        if (r1 != 0) goto L_0x008d;
    L_0x01e3:
        goto L_0x0014;
    L_0x01e5:
        r0 = r2;
        goto L_0x000e;
    L_0x01e8:
        r0 = 82;
        goto L_0x0025;
    L_0x01ec:
        r0 = r3;
        goto L_0x0031;
    L_0x01ef:
        r0 = r3;
        goto L_0x00df;
    L_0x01f2:
        r0 = r3;
        goto L_0x0125;
    L_0x01f5:
        r0 = 53;
        goto L_0x0136;
    L_0x01f9:
        r1 = r2;
        goto L_0x00e6;
    L_0x01fc:
        r0 = 37;
        goto L_0x014c;
    L_0x0200:
        r4 = 77;
        goto L_0x00f6;
    L_0x0204:
        r0 = r3;
        goto L_0x0038;
    L_0x0207:
        r1 = r2;
        goto L_0x0167;
    L_0x020a:
        r1 = r3;
        goto L_0x0060;
    L_0x020d:
        r0 = 89;
        goto L_0x0072;
    L_0x0211:
        r1 = 67;
        goto L_0x0097;
    L_0x0215:
        r2 = r3;
        goto L_0x00a5;
    L_0x0218:
        r0 = r3;
        goto L_0x01ba;
    L_0x021a:
        r0 = 85;
        goto L_0x01d6;
    L_0x021d:
        r0 = r3;
        goto L_0x0049;
    L_0x0220:
        r1 = 60;
        goto L_0x0056;
    L_0x0224:
        r0 = r3;
        goto L_0x0082;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.u.ˏ(com.moat.analytics.mobile.mpub.u$a, android.graphics.Rect, com.moat.analytics.mobile.mpub.u$b):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.VisibleForTesting
    private static com.moat.analytics.mobile.mpub.C3465u.C3463b m11814(android.graphics.Rect r12, @android.support.annotation.NonNull android.view.View r13) {
        /*
        r3 = new com.moat.analytics.mobile.mpub.u$b;
        r3.<init>();
        r8 = new java.util.ArrayDeque;	 Catch:{ Exception -> 0x0081 }
        r8.<init>();	 Catch:{ Exception -> 0x0081 }
        r0 = 0;
        r1 = r13;
        r2 = r0;
    L_0x000d:
        r0 = r1.getParent();	 Catch:{ Exception -> 0x0081 }
        if (r0 != 0) goto L_0x01a9;
    L_0x0013:
        r0 = 61;
    L_0x0015:
        switch(r0) {
            case 87: goto L_0x003b;
            default: goto L_0x0018;
        };
    L_0x0018:
        r0 = f8840;
        r0 = r0 + 5;
        r4 = r0 % 128;
        f8839 = r4;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x0024;
    L_0x0024:
        r0 = r13.getRootView();	 Catch:{ Exception -> 0x0081 }
        if (r1 != r0) goto L_0x01ad;
    L_0x002a:
        r0 = 13;
    L_0x002c:
        switch(r0) {
            case 13: goto L_0x003b;
            default: goto L_0x002f;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x002f:
        r0 = r8.isEmpty();	 Catch:{ Exception -> 0x0081 }
        if (r0 == 0) goto L_0x01b7;
    L_0x0035:
        r0 = 0;
    L_0x0036:
        switch(r0) {
            case 1: goto L_0x0087;
            default: goto L_0x0039;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x0039:
        r0 = r3;
    L_0x003a:
        return r0;
    L_0x003b:
        r2 = r2 + 1;
        r0 = 50;
        if (r2 <= r0) goto L_0x01b1;
    L_0x0041:
        r0 = 0;
    L_0x0042:
        switch(r0) {
            case 0: goto L_0x0069;
            default: goto L_0x0045;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x0045:
        r8.add(r1);	 Catch:{ Exception -> 0x0081 }
        r0 = r1.getParent();	 Catch:{ Exception -> 0x0081 }
        r0 = r0 instanceof android.view.View;	 Catch:{ Exception -> 0x0081 }
        if (r0 == 0) goto L_0x01b4;
    L_0x0050:
        r0 = 0;
    L_0x0051:
        switch(r0) {
            case 0: goto L_0x0055;
            default: goto L_0x0054;
        };
    L_0x0054:
        goto L_0x002f;
    L_0x0055:
        r0 = f8839;
        r0 = r0 + 53;
        r4 = r0 % 128;
        f8840 = r4;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x0061;
    L_0x0061:
        r0 = r1.getParent();	 Catch:{ Exception -> 0x0081 }
        r0 = (android.view.View) r0;	 Catch:{ Exception -> 0x0081 }
        r1 = r0;
        goto L_0x000d;
    L_0x0069:
        r0 = f8840;
        r0 = r0 + 91;
        r1 = r0 % 128;
        f8839 = r1;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x0075;
    L_0x0075:
        r0 = 3;
        r1 = "VisibilityInfo";
        r2 = 0;
        r4 = "Short-circuiting chain retrieval, reached max";
        com.moat.analytics.mobile.mpub.C3412a.m11642(r0, r1, r2, r4);	 Catch:{ Exception -> 0x0081 }
        goto L_0x002f;
    L_0x0081:
        r0 = move-exception;
        com.moat.analytics.mobile.mpub.C3442o.m11756(r0);
    L_0x0085:
        r0 = r3;
        goto L_0x003a;
    L_0x0087:
        r0 = "VisibilityInfo";
        r1 = "starting covering rect search";
        com.moat.analytics.mobile.mpub.C3412a.m11643(r0, r13, r1);	 Catch:{ Exception -> 0x0081 }
        r0 = 0;
        r5 = r0;
    L_0x0092:
        r0 = r8.isEmpty();	 Catch:{ Exception -> 0x0081 }
        if (r0 != 0) goto L_0x01ba;
    L_0x0098:
        r0 = 1;
    L_0x0099:
        switch(r0) {
            case 1: goto L_0x009d;
            default: goto L_0x009c;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x009c:
        goto L_0x0085;
    L_0x009d:
        r0 = r8.pollLast();	 Catch:{ Exception -> 0x0081 }
        r0 = (android.view.View) r0;	 Catch:{ Exception -> 0x0081 }
        r6 = new com.moat.analytics.mobile.mpub.u$a;	 Catch:{ Exception -> 0x0081 }
        r6.<init>(r0, r5);	 Catch:{ Exception -> 0x0081 }
        r1 = r0.getParent();	 Catch:{ Exception -> 0x0081 }
        if (r1 == 0) goto L_0x01bd;
    L_0x00ae:
        r1 = 32;
    L_0x00b0:
        switch(r1) {
            case 2: goto L_0x01eb;
            default: goto L_0x00b3;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x00b3:
        r1 = r0.getParent();	 Catch:{ Exception -> 0x0081 }
        r1 = r1 instanceof android.view.ViewGroup;	 Catch:{ Exception -> 0x0081 }
        if (r1 == 0) goto L_0x01c0;
    L_0x00bb:
        r1 = 36;
    L_0x00bd:
        switch(r1) {
            case 95: goto L_0x0194;
            default: goto L_0x00c0;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x00c0:
        r1 = r0.getParent();	 Catch:{ Exception -> 0x0081 }
        r1 = (android.view.ViewGroup) r1;	 Catch:{ Exception -> 0x0081 }
        r9 = r1.getChildCount();	 Catch:{ Exception -> 0x0081 }
        r4 = 0;
        r2 = 0;
        r7 = r2;
        r2 = r4;
    L_0x00ce:
        if (r7 >= r9) goto L_0x01c4;
    L_0x00d0:
        r4 = 87;
    L_0x00d2:
        switch(r4) {
            case 91: goto L_0x0194;
            default: goto L_0x00d5;
        };
    L_0x00d5:
        r4 = f8840;
        r4 = r4 + 33;
        r10 = r4 % 128;
        f8839 = r10;
        r4 = r4 % 2;
        if (r4 == 0) goto L_0x00e1;
    L_0x00e1:
        r4 = r3.f8835;	 Catch:{ Exception -> 0x0081 }
        r10 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r4 < r10) goto L_0x01c8;
    L_0x00e7:
        r4 = 69;
    L_0x00e9:
        switch(r4) {
            case 87: goto L_0x00f8;
            default: goto L_0x00ec;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x00ec:
        r0 = 3;
        r1 = "VisibilityInfo";
        r2 = 0;
        r4 = "Short-circuiting cover retrieval, reached max";
        com.moat.analytics.mobile.mpub.C3412a.m11642(r0, r1, r2, r4);	 Catch:{ Exception -> 0x0081 }
        goto L_0x0085;
    L_0x00f8:
        r10 = r1.getChildAt(r7);	 Catch:{ Exception -> 0x0081 }
        if (r10 != r0) goto L_0x01cc;
    L_0x00fe:
        r4 = 1;
    L_0x00ff:
        switch(r4) {
            case 1: goto L_0x0144;
            default: goto L_0x0102;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x0102:
        r4 = r3.f8835;	 Catch:{ Exception -> 0x0081 }
        r4 = r4 + 1;
        r3.f8835 = r4;	 Catch:{ Exception -> 0x0081 }
        if (r2 == 0) goto L_0x01cf;
    L_0x010a:
        r4 = 0;
    L_0x010b:
        switch(r4) {
            case 0: goto L_0x0146;
            default: goto L_0x010e;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x010e:
        r4 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0081 }
        r11 = 21;
        if (r4 < r11) goto L_0x01d7;
    L_0x0114:
        r4 = 1;
    L_0x0115:
        switch(r4) {
            case 1: goto L_0x0172;
            default: goto L_0x0118;
        };
    L_0x0118:
        r4 = 0;
    L_0x0119:
        if (r4 == 0) goto L_0x01dd;
    L_0x011b:
        r4 = 2;
    L_0x011c:
        switch(r4) {
            case 33: goto L_0x0140;
            default: goto L_0x011f;
        };
    L_0x011f:
        r4 = f8840;
        r4 = r4 + 53;
        r11 = r4 % 128;
        f8839 = r11;
        r4 = r4 % 2;
        if (r4 == 0) goto L_0x01e5;
    L_0x012b:
        r4 = 0;
    L_0x012c:
        switch(r4) {
            case 0: goto L_0x0197;
            default: goto L_0x012f;
        };
    L_0x012f:
        r4 = new com.moat.analytics.mobile.mpub.u$a;	 Catch:{ Exception -> 0x0081 }
        r4.<init>(r10, r5);	 Catch:{ Exception -> 0x0081 }
        com.moat.analytics.mobile.mpub.C3465u.m11819(r4, r12, r3);	 Catch:{ Exception -> 0x0081 }
        r4 = r3.f8833;	 Catch:{ Exception -> 0x0081 }
        if (r4 == 0) goto L_0x01e1;
    L_0x013b:
        r4 = 15;
    L_0x013d:
        switch(r4) {
            case 15: goto L_0x0191;
            default: goto L_0x0140;
        };
    L_0x0140:
        r4 = r7 + 1;
        r7 = r4;
        goto L_0x00ce;
    L_0x0144:
        r2 = 1;
        goto L_0x0140;
    L_0x0146:
        r4 = f8840;
        r4 = r4 + 59;
        r11 = r4 % 128;
        f8839 = r11;
        r4 = r4 % 2;
        if (r4 == 0) goto L_0x0152;
    L_0x0152:
        r4 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0081 }
        r11 = 21;
        if (r4 < r11) goto L_0x01d2;
    L_0x0158:
        r4 = 0;
    L_0x0159:
        switch(r4) {
            case 0: goto L_0x015e;
            default: goto L_0x015c;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x015c:
        r4 = 1;
        goto L_0x0119;
    L_0x015e:
        r4 = r10.getZ();	 Catch:{ Exception -> 0x0081 }
        r11 = r0.getZ();	 Catch:{ Exception -> 0x0081 }
        r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1));
        if (r4 < 0) goto L_0x01d4;
    L_0x016a:
        r4 = 24;
    L_0x016c:
        switch(r4) {
            case 46: goto L_0x0170;
            default: goto L_0x016f;
        };	 Catch:{ Exception -> 0x0081 }
    L_0x016f:
        goto L_0x015c;
    L_0x0170:
        r4 = 0;
        goto L_0x0119;
    L_0x0172:
        r4 = r10.getZ();	 Catch:{ Exception -> 0x0081 }
        r11 = r0.getZ();	 Catch:{ Exception -> 0x0081 }
        r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1));
        if (r4 <= 0) goto L_0x01da;
    L_0x017e:
        r4 = 55;
    L_0x0180:
        switch(r4) {
            case 80: goto L_0x0118;
            default: goto L_0x0183;
        };
    L_0x0183:
        r4 = f8840;
        r4 = r4 + 87;
        r11 = r4 % 128;
        f8839 = r11;
        r4 = r4 % 2;
        if (r4 == 0) goto L_0x018f;
    L_0x018f:
        r4 = 1;
        goto L_0x0119;
    L_0x0191:
        r0 = r3;
        goto L_0x003a;
    L_0x0194:
        r5 = r6;
        goto L_0x0092;
    L_0x0197:
        r4 = new com.moat.analytics.mobile.mpub.u$a;	 Catch:{ Exception -> 0x0081 }
        r4.<init>(r10, r5);	 Catch:{ Exception -> 0x0081 }
        com.moat.analytics.mobile.mpub.C3465u.m11819(r4, r12, r3);	 Catch:{ Exception -> 0x0081 }
        r4 = r3.f8833;	 Catch:{ Exception -> 0x0081 }
        if (r4 == 0) goto L_0x01e8;
    L_0x01a3:
        r4 = 41;
    L_0x01a5:
        switch(r4) {
            case 41: goto L_0x0191;
            default: goto L_0x01a8;
        };
    L_0x01a8:
        goto L_0x0140;
    L_0x01a9:
        r0 = 87;
        goto L_0x0015;
    L_0x01ad:
        r0 = 15;
        goto L_0x002c;
    L_0x01b1:
        r0 = 1;
        goto L_0x0042;
    L_0x01b4:
        r0 = 1;
        goto L_0x0051;
    L_0x01b7:
        r0 = 1;
        goto L_0x0036;
    L_0x01ba:
        r0 = 0;
        goto L_0x0099;
    L_0x01bd:
        r1 = 2;
        goto L_0x00b0;
    L_0x01c0:
        r1 = 95;
        goto L_0x00bd;
    L_0x01c4:
        r4 = 91;
        goto L_0x00d2;
    L_0x01c8:
        r4 = 87;
        goto L_0x00e9;
    L_0x01cc:
        r4 = 0;
        goto L_0x00ff;
    L_0x01cf:
        r4 = 1;
        goto L_0x010b;
    L_0x01d2:
        r4 = 1;
        goto L_0x0159;
    L_0x01d4:
        r4 = 46;
        goto L_0x016c;
    L_0x01d7:
        r4 = 0;
        goto L_0x0115;
    L_0x01da:
        r4 = 80;
        goto L_0x0180;
    L_0x01dd:
        r4 = 33;
        goto L_0x011c;
    L_0x01e1:
        r4 = 46;
        goto L_0x013d;
    L_0x01e5:
        r4 = 1;
        goto L_0x012c;
    L_0x01e8:
        r4 = 30;
        goto L_0x01a5;
    L_0x01eb:
        r5 = r6;
        goto L_0x0092;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.u.ˋ(android.graphics.Rect, android.view.View):com.moat.analytics.mobile.mpub.u$b");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.VisibleForTesting
    private static int m11811(android.graphics.Rect r14, java.util.Set<android.graphics.Rect> r15) {
        /*
        r6 = 1;
        r2 = 0;
        r0 = r15.isEmpty();
        if (r0 != 0) goto L_0x003d;
    L_0x0008:
        r7 = new java.util.ArrayList;
        r7.<init>();
        r7.addAll(r15);
        r0 = new com.moat.analytics.mobile.mpub.u$1;
        r0.<init>();
        java.util.Collections.sort(r7, r0);
        r8 = new java.util.ArrayList;
        r8.<init>();
        r1 = r7.iterator();
    L_0x0021:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x00eb;
    L_0x0027:
        r0 = r2;
    L_0x0028:
        switch(r0) {
            case 0: goto L_0x003f;
            default: goto L_0x002b;
        };
    L_0x002b:
        java.util.Collections.sort(r8);
        r1 = r2;
        r3 = r2;
    L_0x0030:
        r0 = r8.size();
        r0 = r0 + -1;
        if (r1 >= r0) goto L_0x00ee;
    L_0x0038:
        r0 = r6;
    L_0x0039:
        switch(r0) {
            case 1: goto L_0x0058;
            default: goto L_0x003c;
        };
    L_0x003c:
        return r3;
    L_0x003d:
        r3 = r2;
        goto L_0x003c;
    L_0x003f:
        r0 = r1.next();
        r0 = (android.graphics.Rect) r0;
        r3 = r0.left;
        r3 = java.lang.Integer.valueOf(r3);
        r8.add(r3);
        r0 = r0.right;
        r0 = java.lang.Integer.valueOf(r0);
        r8.add(r0);
        goto L_0x0021;
    L_0x0058:
        r0 = f8840;
        r0 = r0 + 121;
        r4 = r0 % 128;
        f8839 = r4;
        r0 = r0 % 2;
        if (r0 == 0) goto L_0x0064;
    L_0x0064:
        r0 = r8.get(r1);
        r0 = (java.lang.Integer) r0;
        r4 = r1 + 1;
        r4 = r8.get(r4);
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00f1;
    L_0x0076:
        r0 = 69;
    L_0x0078:
        switch(r0) {
            case 85: goto L_0x00ae;
            default: goto L_0x007b;
        };
    L_0x007b:
        r9 = new android.graphics.Rect;
        r0 = r8.get(r1);
        r0 = (java.lang.Integer) r0;
        r4 = r0.intValue();
        r5 = r14.top;
        r0 = r1 + 1;
        r0 = r8.get(r0);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r10 = r14.bottom;
        r9.<init>(r4, r5, r0, r10);
        r0 = r14.top;
        r10 = r7.iterator();
        r4 = r3;
        r3 = r0;
    L_0x00a2:
        r0 = r10.hasNext();
        if (r0 == 0) goto L_0x00f4;
    L_0x00a8:
        r0 = 80;
    L_0x00aa:
        switch(r0) {
            case 80: goto L_0x00b3;
            default: goto L_0x00ad;
        };
    L_0x00ad:
        r3 = r4;
    L_0x00ae:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0030;
    L_0x00b3:
        r0 = r10.next();
        r0 = (android.graphics.Rect) r0;
        r5 = android.graphics.Rect.intersects(r0, r9);
        if (r5 == 0) goto L_0x00f7;
    L_0x00bf:
        r5 = r2;
    L_0x00c0:
        switch(r5) {
            case 1: goto L_0x00fb;
            default: goto L_0x00c3;
        };
    L_0x00c3:
        r5 = r0.bottom;
        if (r5 <= r3) goto L_0x00f9;
    L_0x00c7:
        r5 = r6;
    L_0x00c8:
        switch(r5) {
            case 1: goto L_0x00d8;
            default: goto L_0x00cb;
        };
    L_0x00cb:
        r13 = r3;
        r3 = r4;
        r4 = r13;
    L_0x00ce:
        r0 = r0.bottom;
        r5 = r9.bottom;
        if (r0 == r5) goto L_0x00ae;
    L_0x00d4:
        r0 = r4;
    L_0x00d5:
        r4 = r3;
        r3 = r0;
        goto L_0x00a2;
    L_0x00d8:
        r5 = r9.width();
        r11 = r0.bottom;
        r12 = r0.top;
        r3 = java.lang.Math.max(r3, r12);
        r3 = r11 - r3;
        r3 = r3 * r5;
        r3 = r3 + r4;
        r4 = r0.bottom;
        goto L_0x00ce;
    L_0x00eb:
        r0 = r6;
        goto L_0x0028;
    L_0x00ee:
        r0 = r2;
        goto L_0x0039;
    L_0x00f1:
        r0 = 85;
        goto L_0x0078;
    L_0x00f4:
        r0 = 20;
        goto L_0x00aa;
    L_0x00f7:
        r5 = r6;
        goto L_0x00c0;
    L_0x00f9:
        r5 = r2;
        goto L_0x00c8;
    L_0x00fb:
        r0 = r3;
        r3 = r4;
        goto L_0x00d5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.u.ˋ(android.graphics.Rect, java.util.HashSet):int");
    }

    private static Map<String, String> m11817(Rect rect) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("x", String.valueOf(rect.left));
        hashMap.put("y", String.valueOf(rect.top));
        hashMap.put("w", String.valueOf(rect.right - rect.left));
        hashMap.put(RequestParams.f9035H, String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }

    private static Rect m11812(Rect rect, DisplayMetrics displayMetrics) {
        float f = displayMetrics.density;
        switch (f == 0.0f ? 1 : null) {
            case null:
                return new Rect(Math.round(((float) rect.left) / f), Math.round(((float) rect.top) / f), Math.round(((float) rect.right) / f), Math.round(((float) rect.bottom) / f));
            default:
                int i = f8839 + 35;
                f8840 = i % 128;
                return i % 2 == 0 ? rect : rect;
        }
    }

    private static boolean m11815(View view, Rect rect) {
        boolean z;
        if (view.getGlobalVisibleRect(rect)) {
            z = true;
        } else {
            z = false;
        }
        switch (z) {
            case true:
                int[] iArr = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
                view.getLocationInWindow(iArr);
                int[] iArr2 = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
                view.getLocationOnScreen(iArr2);
                rect.offset(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
                return true;
            default:
                return false;
        }
    }

    private static Rect m11818(View view) {
        int[] iArr = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }
}
