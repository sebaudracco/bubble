package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzge {
    private final Object mLock = new Object();
    private final int zzagx;
    private final int zzagy;
    private final int zzagz;
    private final zzgr zzaha;
    private final zzha zzahb;
    private ArrayList<String> zzahc = new ArrayList();
    private ArrayList<String> zzahd = new ArrayList();
    private ArrayList<zzgp> zzahe = new ArrayList();
    private int zzahf = 0;
    private int zzahg = 0;
    private int zzahh = 0;
    private int zzahi;
    private String zzahj = "";
    private String zzahk = "";
    private String zzahl = "";

    public zzge(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.zzagx = i;
        this.zzagy = i2;
        this.zzagz = i3;
        this.zzaha = new zzgr(i4);
        this.zzahb = new zzha(i5, i6, i7);
    }

    private static String zza(ArrayList<String> arrayList, int i) {
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            i2++;
            stringBuilder.append((String) obj);
            stringBuilder.append(' ');
            if (stringBuilder.length() > 100) {
                break;
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String stringBuilder2 = stringBuilder.toString();
        return stringBuilder2.length() >= 100 ? stringBuilder2.substring(0, 100) : stringBuilder2;
    }

    private final void zzc(@Nullable String str, boolean z, float f, float f2, float f3, float f4) {
        if (str != null && str.length() >= this.zzagz) {
            synchronized (this.mLock) {
                this.zzahc.add(str);
                this.zzahf += str.length();
                if (z) {
                    this.zzahd.add(str);
                    this.zzahe.add(new zzgp(f, f2, f3, f4, this.zzahd.size() - 1));
                }
            }
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzge)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        zzge com_google_android_gms_internal_ads_zzge = (zzge) obj;
        return com_google_android_gms_internal_ads_zzge.zzahj != null && com_google_android_gms_internal_ads_zzge.zzahj.equals(this.zzahj);
    }

    public final int getScore() {
        return this.zzahi;
    }

    public final String getSignature() {
        return this.zzahj;
    }

    public final int hashCode() {
        return this.zzahj.hashCode();
    }

    public final String toString() {
        int i = this.zzahg;
        int i2 = this.zzahi;
        int i3 = this.zzahf;
        String zza = zza(this.zzahc, 100);
        String zza2 = zza(this.zzahd, 100);
        String str = this.zzahj;
        String str2 = this.zzahk;
        String str3 = this.zzahl;
        return new StringBuilder(((((String.valueOf(zza).length() + 165) + String.valueOf(zza2).length()) + String.valueOf(str).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()).append("ActivityContent fetchId: ").append(i).append(" score:").append(i2).append(" total_length:").append(i3).append("\n text: ").append(zza).append("\n viewableText").append(zza2).append("\n signture: ").append(str).append("\n viewableSignture: ").append(str2).append("\n viewableSignatureForVertical: ").append(str3).toString();
    }

    public final void zza(String str, boolean z, float f, float f2, float f3, float f4) {
        zzc(str, z, f, f2, f3, f4);
        synchronized (this.mLock) {
            if (this.zzahh < 0) {
                zzane.zzck("ActivityContent: negative number of WebViews.");
            }
            zzgt();
        }
    }

    public final void zzb(String str, boolean z, float f, float f2, float f3, float f4) {
        zzc(str, z, f, f2, f3, f4);
    }

    public final boolean zzgn() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzahh == 0;
        }
        return z;
    }

    public final String zzgo() {
        return this.zzahk;
    }

    public final String zzgp() {
        return this.zzahl;
    }

    public final void zzgq() {
        synchronized (this.mLock) {
            this.zzahi -= 100;
        }
    }

    public final void zzgr() {
        synchronized (this.mLock) {
            this.zzahh--;
        }
    }

    public final void zzgs() {
        synchronized (this.mLock) {
            this.zzahh++;
        }
    }

    public final void zzgt() {
        synchronized (this.mLock) {
            int i = (this.zzahf * this.zzagx) + (this.zzahg * this.zzagy);
            if (i > this.zzahi) {
                this.zzahi = i;
                if (((Boolean) zzkb.zzik().zzd(zznk.zzawq)).booleanValue() && !zzbv.zzeo().zzqh().zzqu()) {
                    this.zzahj = this.zzaha.zza(this.zzahc);
                    this.zzahk = this.zzaha.zza(this.zzahd);
                }
                if (((Boolean) zzkb.zzik().zzd(zznk.zzaws)).booleanValue() && !zzbv.zzeo().zzqh().zzqw()) {
                    this.zzahl = this.zzahb.zza(this.zzahd, this.zzahe);
                }
            }
        }
    }

    @VisibleForTesting
    final int zzgu() {
        return this.zzahf;
    }

    public final void zzo(int i) {
        this.zzahg = i;
    }
}
