package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzac;
import java.util.ArrayList;
import java.util.List;

public class Element implements Text {
    private zzac zzct;

    Element(zzac com_google_android_gms_internal_vision_zzac) {
        this.zzct = com_google_android_gms_internal_vision_zzac;
    }

    public Rect getBoundingBox() {
        return zzc.zza((Text) this);
    }

    public List<? extends Text> getComponents() {
        return new ArrayList();
    }

    public Point[] getCornerPoints() {
        return zzc.zza(this.zzct.zzde);
    }

    public String getLanguage() {
        return this.zzct.zzcy;
    }

    public String getValue() {
        return this.zzct.zzdh;
    }
}
