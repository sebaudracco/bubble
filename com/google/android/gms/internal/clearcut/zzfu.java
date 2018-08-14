package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public class zzfu<M extends zzfu<M>> extends zzfz {
    protected zzfw zzrj;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzeo();
    }

    public void zza(zzfs com_google_android_gms_internal_clearcut_zzfs) throws IOException {
        if (this.zzrj != null) {
            for (int i = 0; i < this.zzrj.size(); i++) {
                this.zzrj.zzaq(i).zza(com_google_android_gms_internal_clearcut_zzfs);
            }
        }
    }

    protected int zzen() {
        if (this.zzrj != null) {
            for (int i = 0; i < this.zzrj.size(); i++) {
                this.zzrj.zzaq(i).zzen();
            }
        }
        return 0;
    }

    public M zzeo() throws CloneNotSupportedException {
        zzfu com_google_android_gms_internal_clearcut_zzfu = (zzfu) super.zzep();
        zzfy.zza(this, com_google_android_gms_internal_clearcut_zzfu);
        return com_google_android_gms_internal_clearcut_zzfu;
    }

    public /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzfu) clone();
    }
}
