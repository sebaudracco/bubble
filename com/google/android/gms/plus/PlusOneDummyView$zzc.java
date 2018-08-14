package com.google.android.gms.plus;

import android.content.Context;
import android.graphics.drawable.Drawable;

class PlusOneDummyView$zzc implements PlusOneDummyView$zzd {
    private Context mContext;

    private PlusOneDummyView$zzc(Context context) {
        this.mContext = context;
    }

    public final Drawable getDrawable(int i) {
        String str;
        switch (i) {
            case 0:
                str = "ic_plusone_small_off_client";
                break;
            case 1:
                str = "ic_plusone_medium_off_client";
                break;
            case 2:
                str = "ic_plusone_tall_off_client";
                break;
            default:
                str = "ic_plusone_standard_off_client";
                break;
        }
        return this.mContext.getResources().getDrawable(this.mContext.getResources().getIdentifier(str, "drawable", this.mContext.getPackageName()));
    }

    public final boolean isValid() {
        return (this.mContext.getResources().getIdentifier("ic_plusone_small_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_medium_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_tall_off_client", "drawable", this.mContext.getPackageName()) == 0 || this.mContext.getResources().getIdentifier("ic_plusone_standard_off_client", "drawable", this.mContext.getPackageName()) == 0) ? false : true;
    }
}
