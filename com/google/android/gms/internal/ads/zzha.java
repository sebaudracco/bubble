package com.google.android.gms.internal.ads;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@zzadh
public final class zzha {
    private final int zzaiz;
    private final zzgq zzajb;
    private String zzajj;
    private String zzajk;
    private final boolean zzajl = false;
    private final int zzajm;
    private final int zzajn;

    public zzha(int i, int i2, int i3) {
        this.zzaiz = i;
        if (i2 > 64 || i2 < 0) {
            this.zzajm = 64;
        } else {
            this.zzajm = i2;
        }
        if (i3 <= 0) {
            this.zzajn = 1;
        } else {
            this.zzajn = i3;
        }
        this.zzajb = new zzgz(this.zzajm);
    }

    public final String zza(ArrayList<String> arrayList, ArrayList<zzgp> arrayList2) {
        Collections.sort(arrayList2, new zzhb(this));
        HashSet hashSet = new HashSet();
        for (int i = 0; i < arrayList2.size(); i++) {
            boolean z;
            String[] split = Normalizer.normalize((CharSequence) arrayList.get(((zzgp) arrayList2.get(i)).zzhf()), Form.NFKC).toLowerCase(Locale.US).split("\n");
            if (split.length != 0) {
                for (String str : split) {
                    int i2;
                    String stringBuilder;
                    String[] zzb;
                    Object obj;
                    int i3;
                    boolean z2;
                    String valueOf;
                    String valueOf2;
                    if (str.indexOf("'") != -1) {
                        StringBuilder stringBuilder2 = new StringBuilder(str);
                        i2 = 1;
                        boolean z3 = false;
                        while (i2 + 2 <= stringBuilder2.length()) {
                            if (stringBuilder2.charAt(i2) == '\'') {
                                if (stringBuilder2.charAt(i2 - 1) == ' ' || !((stringBuilder2.charAt(i2 + 1) == 's' || stringBuilder2.charAt(i2 + 1) == 'S') && (i2 + 2 == stringBuilder2.length() || stringBuilder2.charAt(i2 + 2) == ' '))) {
                                    stringBuilder2.setCharAt(i2, ' ');
                                } else {
                                    stringBuilder2.insert(i2, ' ');
                                    i2 += 2;
                                }
                                z3 = true;
                            }
                            i2++;
                        }
                        stringBuilder = z3 ? stringBuilder2.toString() : null;
                        if (stringBuilder != null) {
                            this.zzajk = stringBuilder;
                            zzb = zzgu.zzb(stringBuilder, true);
                            if (zzb.length >= this.zzajn) {
                                for (i2 = 0; i2 < zzb.length; i2++) {
                                    obj = "";
                                    for (i3 = 0; i3 < this.zzajn; i3++) {
                                        if (i2 + i3 >= zzb.length) {
                                            z2 = false;
                                            break;
                                        }
                                        if (i3 > 0) {
                                            obj = String.valueOf(obj).concat(" ");
                                        }
                                        valueOf = String.valueOf(obj);
                                        valueOf2 = String.valueOf(zzb[i2 + i3]);
                                        obj = valueOf2.length() == 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                                    }
                                    z2 = true;
                                    if (!z2) {
                                        break;
                                    }
                                    hashSet.add(obj);
                                    if (hashSet.size() >= this.zzaiz) {
                                        z = false;
                                        break;
                                    }
                                }
                                if (hashSet.size() >= this.zzaiz) {
                                    z = false;
                                    break;
                                }
                            }
                        }
                    }
                    stringBuilder = str;
                    zzb = zzgu.zzb(stringBuilder, true);
                    if (zzb.length >= this.zzajn) {
                        while (i2 < zzb.length) {
                            obj = "";
                            for (i3 = 0; i3 < this.zzajn; i3++) {
                                if (i2 + i3 >= zzb.length) {
                                    z2 = false;
                                    break;
                                }
                                if (i3 > 0) {
                                    obj = String.valueOf(obj).concat(" ");
                                }
                                valueOf = String.valueOf(obj);
                                valueOf2 = String.valueOf(zzb[i2 + i3]);
                                if (valueOf2.length() == 0) {
                                }
                            }
                            z2 = true;
                            if (!z2) {
                                hashSet.add(obj);
                                if (hashSet.size() >= this.zzaiz) {
                                    z = false;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        if (hashSet.size() >= this.zzaiz) {
                            z = false;
                            break;
                        }
                    }
                }
            }
            z = true;
            if (!z) {
                break;
            }
        }
        zzgt com_google_android_gms_internal_ads_zzgt = new zzgt();
        this.zzajj = "";
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            try {
                com_google_android_gms_internal_ads_zzgt.write(this.zzajb.zzx((String) it.next()));
            } catch (Throwable e) {
                zzane.zzb("Error while writing hash to byteStream", e);
            }
        }
        return com_google_android_gms_internal_ads_zzgt.toString();
    }
}
