package com.inmobi.rendering;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.InMobiAdActivity.C3179b;

/* compiled from: JavaScriptBridge */
class a$2 implements C3179b {
    final /* synthetic */ String f7965a;
    final /* synthetic */ String f7966b;
    final /* synthetic */ a f7967c;

    a$2(a aVar, String str, String str2) {
        this.f7967c = aVar;
        this.f7965a = str;
        this.f7966b = str2;
    }

    public void mo6262a(int i, String[] strArr, int[] iArr) {
        if (iArr.length == 1 && iArr[0] == 0) {
            try {
                a.a(this.f7967c, this.f7965a, this.f7966b);
                return;
            } catch (Exception e) {
                a.a(this.f7967c).m10631a(this.f7965a, "Unexpected error", "storePicture");
                Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Failed to store picture to gallery; SDK encountered an unexpected error");
                Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error in handling storePicture() request from creative; " + e.getMessage());
                return;
            }
        }
        a.a(this.f7967c).m10631a(this.f7965a, "Permission denied by user.", "storePicture");
    }
}
