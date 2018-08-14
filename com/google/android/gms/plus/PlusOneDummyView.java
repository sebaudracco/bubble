package com.google.android.gms.plus;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

@Deprecated
public class PlusOneDummyView extends FrameLayout {
    @Deprecated
    public static final String TAG = "PlusOneDummyView";

    @Deprecated
    public PlusOneDummyView(Context context, int i) {
        int i2;
        int i3 = 24;
        super(context);
        View button = new Button(context);
        button.setEnabled(false);
        zzd com_google_android_gms_plus_PlusOneDummyView_zzb = new zzb(getContext(), null);
        if (!com_google_android_gms_plus_PlusOneDummyView_zzb.isValid()) {
            com_google_android_gms_plus_PlusOneDummyView_zzb = new zzc(getContext(), null);
        }
        if (!com_google_android_gms_plus_PlusOneDummyView_zzb.isValid()) {
            com_google_android_gms_plus_PlusOneDummyView_zzb = new zza(getContext(), null);
        }
        button.setBackgroundDrawable(com_google_android_gms_plus_PlusOneDummyView_zzb.getDrawable(i));
        Point point = new Point();
        switch (i) {
            case 0:
                i2 = 14;
                break;
            case 1:
                i3 = 32;
                i2 = 20;
                break;
            case 2:
                i3 = 50;
                i2 = 20;
                break;
            default:
                i3 = 38;
                i2 = 24;
                break;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float applyDimension = TypedValue.applyDimension(1, (float) i3, displayMetrics);
        float applyDimension2 = TypedValue.applyDimension(1, (float) i2, displayMetrics);
        point.x = (int) (((double) applyDimension) + 0.5d);
        point.y = (int) (((double) applyDimension2) + 0.5d);
        addView(button, new LayoutParams(point.x, point.y, 17));
    }
}
