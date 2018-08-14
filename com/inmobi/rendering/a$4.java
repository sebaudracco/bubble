package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.InMobiAdActivity.C3179b;

/* compiled from: JavaScriptBridge */
class a$4 implements C3179b {
    final /* synthetic */ String f7970a;
    final /* synthetic */ String f7971b;
    final /* synthetic */ String f7972c;
    final /* synthetic */ String f7973d;
    final /* synthetic */ String f7974e;
    final /* synthetic */ String f7975f;
    final /* synthetic */ String f7976g;
    final /* synthetic */ String f7977h;
    final /* synthetic */ String f7978i;
    final /* synthetic */ String f7979j;
    final /* synthetic */ String f7980k;
    final /* synthetic */ a f7981l;

    a$4(a aVar, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.f7981l = aVar;
        this.f7970a = str;
        this.f7971b = str2;
        this.f7972c = str3;
        this.f7973d = str4;
        this.f7974e = str5;
        this.f7975f = str6;
        this.f7976g = str7;
        this.f7977h = str8;
        this.f7978i = str9;
        this.f7979j = str10;
        this.f7980k = str11;
    }

    public void mo6262a(int i, String[] strArr, int[] iArr) {
        if (iArr.length == 2 && iArr[0] == 0 && iArr[1] == 0) {
            try {
                a.a(this.f7981l).m10633a(this.f7970a, this.f7971b, this.f7972c, this.f7973d, this.f7974e, this.f7975f, this.f7976g, this.f7977h, this.f7978i, this.f7979j, this.f7980k);
                return;
            } catch (Exception e) {
                a.a(this.f7981l).m10631a(this.f7970a, "Unexpected error", "createCalendarEvent");
                Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Could not create calendar event; SDK encountered unexpected error");
                Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error in handling createCalendarEvent() request from creative; " + e.getMessage());
                return;
            }
        }
        a.a(this.f7981l).m10631a(this.f7970a, "Permission denied by user.", "createCalendarEvent");
    }
}
