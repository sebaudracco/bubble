package com.vungle.publisher;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: vungle */
public class C4257t extends o implements Parcelable {
    public static final Creator<C4257t> CREATOR = new C42561();
    static final Orientation f11073b = Orientation.matchVideo;

    /* compiled from: vungle */
    static class C42561 implements Creator<C4257t> {
        C42561() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m13919a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m13920a(i);
        }

        public C4257t m13919a(Parcel parcel) {
            return new C4257t(new o[0]).m13921a(parcel);
        }

        public C4257t[] m13920a(int i) {
            return new C4257t[i];
        }
    }

    public C4257t(o... oVarArr) {
        if (oVarArr != null) {
            for (o oVar : oVarArr) {
                if (oVar != null) {
                    this.a.putAll(oVar.a);
                }
            }
        }
    }

    public boolean isBackButtonImmediatelyEnabled() {
        return this.a.getBoolean("isBackButtonEnabled", false);
    }

    public boolean isImmersiveMode() {
        return this.a.getBoolean("isImmersiveMode", false);
    }

    public String getIncentivizedCancelDialogBodyText() {
        String string = this.a.getString("incentivizedCancelDialogBodyText");
        if (string == null) {
            return "Closing this video early will prevent you from earning your reward. Are you sure?";
        }
        return string;
    }

    public String getIncentivizedCancelDialogCloseButtonText() {
        String string = this.a.getString("incentivizedCancelDialogNegativeButtonText");
        if (zk.m14215a(string)) {
            return string;
        }
        return "Close video";
    }

    public String getIncentivizedCancelDialogKeepWatchingButtonText() {
        String string = this.a.getString("incentivizedCancelDialogPositiveButtonText");
        if (zk.m14215a(string)) {
            return string;
        }
        return "Keep watching";
    }

    public String getIncentivizedCancelDialogTitle() {
        String string = this.a.getString("incentivizedCancelDialogTitle");
        if (string == null) {
            return "Close video?";
        }
        return string;
    }

    public Orientation getOrientation() {
        Orientation orientation = (Orientation) this.a.getParcelable("orientation");
        return orientation == null ? f11073b : orientation;
    }

    public boolean isSoundEnabled() {
        return this.a.getBoolean("isSoundEnabled", true);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeBundle(this.a);
    }

    protected C4257t m13921a(Parcel parcel) {
        this.a = parcel.readBundle(C4257t.class.getClassLoader());
        return this;
    }
}
