package com.google.android.gms.internal.measurement;

import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

final class zzdy {
    private final zzgl zzacw;
    private String zzadl;
    private String zzadm;
    private String zzadn;
    private String zzado;
    private long zzadp;
    private long zzadq;
    private long zzadr;
    private long zzads;
    private String zzadt;
    private long zzadu;
    private long zzadv;
    private boolean zzadw;
    private long zzadx;
    private boolean zzady;
    private boolean zzadz;
    private long zzaea;
    private long zzaeb;
    private long zzaec;
    private long zzaed;
    private long zzaee;
    private long zzaef;
    private String zzaeg;
    private boolean zzaeh;
    private long zzaei;
    private long zzaej;
    private String zzth;
    private final String zzti;

    @WorkerThread
    zzdy(zzgl com_google_android_gms_internal_measurement_zzgl, String str) {
        Preconditions.checkNotNull(com_google_android_gms_internal_measurement_zzgl);
        Preconditions.checkNotEmpty(str);
        this.zzacw = com_google_android_gms_internal_measurement_zzgl;
        this.zzti = str;
        this.zzacw.zzab();
    }

    @WorkerThread
    public final String getAppInstanceId() {
        this.zzacw.zzab();
        return this.zzadl;
    }

    @WorkerThread
    public final String getGmpAppId() {
        this.zzacw.zzab();
        return this.zzadm;
    }

    @WorkerThread
    public final boolean isMeasurementEnabled() {
        this.zzacw.zzab();
        return this.zzadw;
    }

    @WorkerThread
    public final void setAppVersion(String str) {
        this.zzacw.zzab();
        this.zzaeh = (!zzka.zzs(this.zzth, str) ? 1 : 0) | this.zzaeh;
        this.zzth = str;
    }

    @WorkerThread
    public final void setMeasurementEnabled(boolean z) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzadw != z ? 1 : 0) | this.zzaeh;
        this.zzadw = z;
    }

    @WorkerThread
    public final void zzaa(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzadx != j ? 1 : 0) | this.zzaeh;
        this.zzadx = j;
    }

    @WorkerThread
    public final String zzag() {
        this.zzacw.zzab();
        return this.zzth;
    }

    @WorkerThread
    public final String zzah() {
        this.zzacw.zzab();
        return this.zzti;
    }

    @WorkerThread
    public final void zzal(String str) {
        this.zzacw.zzab();
        this.zzaeh = (!zzka.zzs(this.zzadl, str) ? 1 : 0) | this.zzaeh;
        this.zzadl = str;
    }

    @WorkerThread
    public final void zzam(String str) {
        this.zzacw.zzab();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzaeh = (!zzka.zzs(this.zzadm, str) ? 1 : 0) | this.zzaeh;
        this.zzadm = str;
    }

    @WorkerThread
    public final void zzan(String str) {
        this.zzacw.zzab();
        this.zzaeh = (!zzka.zzs(this.zzadn, str) ? 1 : 0) | this.zzaeh;
        this.zzadn = str;
    }

    @WorkerThread
    public final void zzao(String str) {
        this.zzacw.zzab();
        this.zzaeh = (!zzka.zzs(this.zzado, str) ? 1 : 0) | this.zzaeh;
        this.zzado = str;
    }

    @WorkerThread
    public final void zzap(String str) {
        this.zzacw.zzab();
        this.zzaeh = (!zzka.zzs(this.zzadt, str) ? 1 : 0) | this.zzaeh;
        this.zzadt = str;
    }

    @WorkerThread
    public final void zzaq(String str) {
        this.zzacw.zzab();
        this.zzaeh = (!zzka.zzs(this.zzaeg, str) ? 1 : 0) | this.zzaeh;
        this.zzaeg = str;
    }

    @WorkerThread
    public final void zzd(boolean z) {
        this.zzacw.zzab();
        this.zzaeh = this.zzady != z;
        this.zzady = z;
    }

    @WorkerThread
    public final void zze(boolean z) {
        this.zzacw.zzab();
        this.zzaeh = this.zzadz != z;
        this.zzadz = z;
    }

    @WorkerThread
    public final void zzgh() {
        this.zzacw.zzab();
        this.zzaeh = false;
    }

    @WorkerThread
    public final String zzgi() {
        this.zzacw.zzab();
        return this.zzadn;
    }

    @WorkerThread
    public final String zzgj() {
        this.zzacw.zzab();
        return this.zzado;
    }

    @WorkerThread
    public final long zzgk() {
        this.zzacw.zzab();
        return this.zzadq;
    }

    @WorkerThread
    public final long zzgl() {
        this.zzacw.zzab();
        return this.zzadr;
    }

    @WorkerThread
    public final long zzgm() {
        this.zzacw.zzab();
        return this.zzads;
    }

    @WorkerThread
    public final String zzgn() {
        this.zzacw.zzab();
        return this.zzadt;
    }

    @WorkerThread
    public final long zzgo() {
        this.zzacw.zzab();
        return this.zzadu;
    }

    @WorkerThread
    public final long zzgp() {
        this.zzacw.zzab();
        return this.zzadv;
    }

    @WorkerThread
    public final long zzgq() {
        this.zzacw.zzab();
        return this.zzadp;
    }

    @WorkerThread
    public final long zzgr() {
        this.zzacw.zzab();
        return this.zzaei;
    }

    @WorkerThread
    public final long zzgs() {
        this.zzacw.zzab();
        return this.zzaej;
    }

    @WorkerThread
    public final void zzgt() {
        this.zzacw.zzab();
        long j = this.zzadp + 1;
        if (j > 2147483647L) {
            this.zzacw.zzge().zzip().zzg("Bundle index overflow. appId", zzfg.zzbm(this.zzti));
            j = 0;
        }
        this.zzaeh = true;
        this.zzadp = j;
    }

    @WorkerThread
    public final long zzgu() {
        this.zzacw.zzab();
        return this.zzaea;
    }

    @WorkerThread
    public final long zzgv() {
        this.zzacw.zzab();
        return this.zzaeb;
    }

    @WorkerThread
    public final long zzgw() {
        this.zzacw.zzab();
        return this.zzaec;
    }

    @WorkerThread
    public final long zzgx() {
        this.zzacw.zzab();
        return this.zzaed;
    }

    @WorkerThread
    public final long zzgy() {
        this.zzacw.zzab();
        return this.zzaef;
    }

    @WorkerThread
    public final long zzgz() {
        this.zzacw.zzab();
        return this.zzaee;
    }

    @WorkerThread
    public final String zzha() {
        this.zzacw.zzab();
        return this.zzaeg;
    }

    @WorkerThread
    public final String zzhb() {
        this.zzacw.zzab();
        String str = this.zzaeg;
        zzaq(null);
        return str;
    }

    @WorkerThread
    public final long zzhc() {
        this.zzacw.zzab();
        return this.zzadx;
    }

    @WorkerThread
    public final boolean zzhd() {
        this.zzacw.zzab();
        return this.zzady;
    }

    @WorkerThread
    public final boolean zzhe() {
        this.zzacw.zzab();
        return this.zzadz;
    }

    @WorkerThread
    public final void zzm(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzadq != j ? 1 : 0) | this.zzaeh;
        this.zzadq = j;
    }

    @WorkerThread
    public final void zzn(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzadr != j ? 1 : 0) | this.zzaeh;
        this.zzadr = j;
    }

    @WorkerThread
    public final void zzo(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzads != j ? 1 : 0) | this.zzaeh;
        this.zzads = j;
    }

    @WorkerThread
    public final void zzp(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzadu != j ? 1 : 0) | this.zzaeh;
        this.zzadu = j;
    }

    @WorkerThread
    public final void zzq(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzadv != j ? 1 : 0) | this.zzaeh;
        this.zzadv = j;
    }

    @WorkerThread
    public final void zzr(long j) {
        int i = 1;
        Preconditions.checkArgument(j >= 0);
        this.zzacw.zzab();
        boolean z = this.zzaeh;
        if (this.zzadp == j) {
            i = 0;
        }
        this.zzaeh = z | i;
        this.zzadp = j;
    }

    @WorkerThread
    public final void zzs(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzaei != j ? 1 : 0) | this.zzaeh;
        this.zzaei = j;
    }

    @WorkerThread
    public final void zzt(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzaej != j ? 1 : 0) | this.zzaeh;
        this.zzaej = j;
    }

    @WorkerThread
    public final void zzu(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzaea != j ? 1 : 0) | this.zzaeh;
        this.zzaea = j;
    }

    @WorkerThread
    public final void zzv(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzaeb != j ? 1 : 0) | this.zzaeh;
        this.zzaeb = j;
    }

    @WorkerThread
    public final void zzw(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzaec != j ? 1 : 0) | this.zzaeh;
        this.zzaec = j;
    }

    @WorkerThread
    public final void zzx(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzaed != j ? 1 : 0) | this.zzaeh;
        this.zzaed = j;
    }

    @WorkerThread
    public final void zzy(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzaef != j ? 1 : 0) | this.zzaeh;
        this.zzaef = j;
    }

    @WorkerThread
    public final void zzz(long j) {
        this.zzacw.zzab();
        this.zzaeh = (this.zzaee != j ? 1 : 0) | this.zzaeh;
        this.zzaee = j;
    }
}
