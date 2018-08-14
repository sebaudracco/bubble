package com.google.android.gms.plus;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class PlusOneDummyView$zzb implements PlusOneDummyView$zzd {
    private Context mContext;

    private PlusOneDummyView$zzb(Context context) {
        this.mContext = context;
    }

    public final Drawable getDrawable(int i) {
        try {
            String str;
            Resources resources = this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
            String str2 = "com.google.android.gms";
            switch (i) {
                case 0:
                    str = "ic_plusone_small";
                    break;
                case 1:
                    str = "ic_plusone_medium";
                    break;
                case 2:
                    str = "ic_plusone_tall";
                    break;
                default:
                    str = "ic_plusone_standard";
                    break;
            }
            return resources.getDrawable(resources.getIdentifier(str, "drawable", str2));
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public final boolean isValid() {
        try {
            this.mContext.createPackageContext("com.google.android.gms", 4).getResources();
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
