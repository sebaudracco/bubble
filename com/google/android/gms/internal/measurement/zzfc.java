package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzfc extends zzhh {
    private final zzfd zzaig = new zzfd(this, getContext(), "google_app_measurement_local.db");
    private boolean zzaih;

    zzfc(zzgl com_google_android_gms_internal_measurement_zzgl) {
        super(com_google_android_gms_internal_measurement_zzgl);
    }

    @WorkerThread
    @VisibleForTesting
    private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (this.zzaih) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzaig.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzaih = true;
        return null;
    }

    @WorkerThread
    private final boolean zza(int i, byte[] bArr) {
        zzab();
        if (this.zzaih) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("entry", bArr);
        int i2 = 0;
        int i3 = 5;
        while (i2 < 5) {
            SQLiteDatabase sQLiteDatabase = null;
            Cursor cursor = null;
            try {
                sQLiteDatabase = getWritableDatabase();
                if (sQLiteDatabase == null) {
                    this.zzaih = true;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return false;
                }
                sQLiteDatabase.beginTransaction();
                long j = 0;
                cursor = sQLiteDatabase.rawQuery("select count(1) from messages", null);
                if (cursor != null && cursor.moveToFirst()) {
                    j = cursor.getLong(0);
                }
                if (j >= 100000) {
                    zzge().zzim().log("Data loss, local db full");
                    j = (100000 - j) + 1;
                    long delete = (long) sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(j)});
                    if (delete != j) {
                        zzge().zzim().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j), Long.valueOf(delete), Long.valueOf(j - delete));
                    }
                }
                sQLiteDatabase.insertOrThrow("messages", null, contentValues);
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return true;
            } catch (SQLiteFullException e) {
                zzge().zzim().zzg("Error writing entry to local database", e);
                this.zzaih = true;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i2++;
            } catch (SQLiteDatabaseLockedException e2) {
                SystemClock.sleep((long) i3);
                i3 += 20;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i2++;
            } catch (SQLiteException e3) {
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                }
                zzge().zzim().zzg("Error writing entry to local database", e3);
                this.zzaih = true;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i2++;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            }
        }
        zzge().zzip().log("Failed to write entry to local database");
        return false;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final void resetAnalyticsData() {
        zzab();
        try {
            int delete = getWritableDatabase().delete("messages", null, null) + 0;
            if (delete > 0) {
                zzge().zzit().zzg("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzge().zzim().zzg("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zza(zzeu com_google_android_gms_internal_measurement_zzeu) {
        Parcel obtain = Parcel.obtain();
        com_google_android_gms_internal_measurement_zzeu.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzge().zzip().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzjx com_google_android_gms_internal_measurement_zzjx) {
        Parcel obtain = Parcel.obtain();
        com_google_android_gms_internal_measurement_zzjx.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzge().zzip().log("User property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final boolean zzc(zzed com_google_android_gms_internal_measurement_zzed) {
        zzgb();
        byte[] zza = zzka.zza((Parcelable) com_google_android_gms_internal_measurement_zzed);
        if (zza.length <= 131072) {
            return zza(2, zza);
        }
        zzge().zzip().log("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzfr() {
        super.zzfr();
    }

    public final /* bridge */ /* synthetic */ void zzfs() {
        super.zzfs();
    }

    public final /* bridge */ /* synthetic */ zzdu zzft() {
        return super.zzft();
    }

    public final /* bridge */ /* synthetic */ zzhk zzfu() {
        return super.zzfu();
    }

    public final /* bridge */ /* synthetic */ zzfb zzfv() {
        return super.zzfv();
    }

    public final /* bridge */ /* synthetic */ zzeo zzfw() {
        return super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzii zzfx() {
        return super.zzfx();
    }

    public final /* bridge */ /* synthetic */ zzif zzfy() {
        return super.zzfy();
    }

    public final /* bridge */ /* synthetic */ zzfc zzfz() {
        return super.zzfz();
    }

    public final /* bridge */ /* synthetic */ zzfe zzga() {
        return super.zzga();
    }

    public final /* bridge */ /* synthetic */ zzka zzgb() {
        return super.zzgb();
    }

    public final /* bridge */ /* synthetic */ zzjh zzgc() {
        return super.zzgc();
    }

    public final /* bridge */ /* synthetic */ zzgg zzgd() {
        return super.zzgd();
    }

    public final /* bridge */ /* synthetic */ zzfg zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfr zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzef zzgg() {
        return super.zzgg();
    }

    protected final boolean zzhf() {
        return false;
    }

    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zzp(int r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:154:0x013d
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.modifyBlocksTree(BlockProcessor.java:248)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r14 = this;
        r14.zzab();
        r0 = r14.zzaih;
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        r0 = 0;
    L_0x0008:
        return r0;
    L_0x0009:
        r10 = new java.util.ArrayList;
        r10.<init>();
        r0 = r14.getContext();
        r1 = "google_app_measurement_local.db";
        r0 = r0.getDatabasePath(r1);
        r0 = r0.exists();
        if (r0 != 0) goto L_0x0021;
    L_0x001f:
        r0 = r10;
        goto L_0x0008;
    L_0x0021:
        r9 = 5;
        r0 = 0;
        r12 = r0;
    L_0x0024:
        r0 = 5;
        if (r12 >= r0) goto L_0x01ec;
    L_0x0027:
        r1 = 0;
        r11 = 0;
        r0 = r14.getWritableDatabase();	 Catch:{ SQLiteFullException -> 0x0225, SQLiteDatabaseLockedException -> 0x021c, SQLiteException -> 0x0211, all -> 0x01fd }
        if (r0 != 0) goto L_0x0039;
    L_0x002f:
        r1 = 1;
        r14.zzaih = r1;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        if (r0 == 0) goto L_0x0037;
    L_0x0034:
        r0.close();
    L_0x0037:
        r0 = 0;
        goto L_0x0008;
    L_0x0039:
        r0.beginTransaction();	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r1 = "messages";	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r2 = 3;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r3 = 0;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r4 = "rowid";	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r2[r3] = r4;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r3 = 1;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r4 = "type";	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r2[r3] = r4;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r3 = 2;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r4 = "entry";	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r2[r3] = r4;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r3 = 0;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r4 = 0;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r5 = 0;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r6 = 0;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r7 = "rowid asc";	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r8 = 100;	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r8 = java.lang.Integer.toString(r8);	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteFullException -> 0x022a, SQLiteDatabaseLockedException -> 0x0220, SQLiteException -> 0x0215, all -> 0x0201 }
        r4 = -1;
    L_0x0067:
        r1 = r2.moveToNext();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        if (r1 == 0) goto L_0x01b1;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x006d:
        r1 = 0;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r4 = r2.getLong(r1);	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r1 = 1;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r1 = r2.getInt(r1);	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r3 = 2;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r6 = r2.getBlob(r3);	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        if (r1 != 0) goto L_0x011a;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x007e:
        r3 = android.os.Parcel.obtain();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r1 = 0;
        r7 = r6.length;	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r3.unmarshall(r6, r1, r7);	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r1 = 0;	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r3.setDataPosition(r1);	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r1 = com.google.android.gms.internal.measurement.zzeu.CREATOR;	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r1 = r1.createFromParcel(r3);	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r1 = (com.google.android.gms.internal.measurement.zzeu) r1;	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r3.recycle();
        if (r1 == 0) goto L_0x0067;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x0098:
        r10.add(r1);	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        goto L_0x0067;
    L_0x009c:
        r1 = move-exception;
        r13 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r13;
    L_0x00a1:
        r3 = r14.zzge();	 Catch:{ all -> 0x0208 }
        r3 = r3.zzim();	 Catch:{ all -> 0x0208 }
        r4 = "Error reading entries from local database";	 Catch:{ all -> 0x0208 }
        r3.zzg(r4, r0);	 Catch:{ all -> 0x0208 }
        r0 = 1;	 Catch:{ all -> 0x0208 }
        r14.zzaih = r0;	 Catch:{ all -> 0x0208 }
        if (r1 == 0) goto L_0x00b7;
    L_0x00b4:
        r1.close();
    L_0x00b7:
        if (r2 == 0) goto L_0x0230;
    L_0x00b9:
        r2.close();
        r0 = r9;
    L_0x00bd:
        r1 = r12 + 1;
        r12 = r1;
        r9 = r0;
        goto L_0x0024;
    L_0x00c3:
        r1 = move-exception;
        r1 = r14.zzge();	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r1 = r1.zzim();	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r6 = "Failed to load event from local database";	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r1.log(r6);	 Catch:{ SafeParcelReader$ParseException -> 0x00c3, all -> 0x00e9 }
        r3.recycle();
        goto L_0x0067;
    L_0x00d6:
        r1 = move-exception;
        r1 = r0;
    L_0x00d8:
        r4 = (long) r9;
        android.os.SystemClock.sleep(r4);	 Catch:{ all -> 0x020e }
        r0 = r9 + 20;
        if (r2 == 0) goto L_0x00e3;
    L_0x00e0:
        r2.close();
    L_0x00e3:
        if (r1 == 0) goto L_0x00bd;
    L_0x00e5:
        r1.close();
        goto L_0x00bd;
    L_0x00e9:
        r1 = move-exception;
        r3.recycle();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        throw r1;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x00ee:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
    L_0x00f2:
        if (r1 == 0) goto L_0x00fd;
    L_0x00f4:
        r3 = r1.inTransaction();	 Catch:{ all -> 0x020e }
        if (r3 == 0) goto L_0x00fd;	 Catch:{ all -> 0x020e }
    L_0x00fa:
        r1.endTransaction();	 Catch:{ all -> 0x020e }
    L_0x00fd:
        r3 = r14.zzge();	 Catch:{ all -> 0x020e }
        r3 = r3.zzim();	 Catch:{ all -> 0x020e }
        r4 = "Error reading entries from local database";	 Catch:{ all -> 0x020e }
        r3.zzg(r4, r0);	 Catch:{ all -> 0x020e }
        r0 = 1;	 Catch:{ all -> 0x020e }
        r14.zzaih = r0;	 Catch:{ all -> 0x020e }
        if (r2 == 0) goto L_0x0113;
    L_0x0110:
        r2.close();
    L_0x0113:
        if (r1 == 0) goto L_0x0230;
    L_0x0115:
        r1.close();
        r0 = r9;
        goto L_0x00bd;
    L_0x011a:
        r3 = 1;
        if (r1 != r3) goto L_0x0165;
    L_0x011d:
        r7 = android.os.Parcel.obtain();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r3 = 0;
        r1 = 0;
        r8 = r6.length;	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r7.unmarshall(r6, r1, r8);	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r1 = 0;	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r7.setDataPosition(r1);	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r1 = com.google.android.gms.internal.measurement.zzjx.CREATOR;	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r1 = r1.createFromParcel(r7);	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r1 = (com.google.android.gms.internal.measurement.zzjx) r1;	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r7.recycle();
    L_0x0136:
        if (r1 == 0) goto L_0x0067;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x0138:
        r10.add(r1);	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        goto L_0x0067;
    L_0x013d:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
    L_0x0141:
        if (r2 == 0) goto L_0x0146;
    L_0x0143:
        r2.close();
    L_0x0146:
        if (r1 == 0) goto L_0x014b;
    L_0x0148:
        r1.close();
    L_0x014b:
        throw r0;
    L_0x014c:
        r1 = move-exception;
        r1 = r14.zzge();	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r1 = r1.zzim();	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r6 = "Failed to load user property from local database";	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r1.log(r6);	 Catch:{ SafeParcelReader$ParseException -> 0x014c, all -> 0x0160 }
        r7.recycle();
        r1 = r3;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        goto L_0x0136;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x0160:
        r1 = move-exception;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r7.recycle();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        throw r1;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x0165:
        r3 = 2;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        if (r1 != r3) goto L_0x01a1;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x0168:
        r7 = android.os.Parcel.obtain();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r3 = 0;
        r1 = 0;
        r8 = r6.length;	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r7.unmarshall(r6, r1, r8);	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r1 = 0;	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r7.setDataPosition(r1);	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r1 = com.google.android.gms.internal.measurement.zzed.CREATOR;	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r1 = r1.createFromParcel(r7);	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r1 = (com.google.android.gms.internal.measurement.zzed) r1;	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r7.recycle();
    L_0x0181:
        if (r1 == 0) goto L_0x0067;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x0183:
        r10.add(r1);	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        goto L_0x0067;
    L_0x0188:
        r1 = move-exception;
        r1 = r14.zzge();	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r1 = r1.zzim();	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r6 = "Failed to load user property from local database";	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r1.log(r6);	 Catch:{ SafeParcelReader$ParseException -> 0x0188, all -> 0x019c }
        r7.recycle();
        r1 = r3;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        goto L_0x0181;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x019c:
        r1 = move-exception;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r7.recycle();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        throw r1;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x01a1:
        r1 = r14.zzge();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r1 = r1.zzim();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r3 = "Unknown record type in local database";	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r1.log(r3);	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        goto L_0x0067;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x01b1:
        r1 = "messages";	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r3 = "rowid <= ?";	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r6 = 1;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r6 = new java.lang.String[r6];	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r7 = 0;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r4 = java.lang.Long.toString(r4);	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r6[r7] = r4;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r1 = r0.delete(r1, r3, r6);	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r3 = r10.size();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        if (r1 >= r3) goto L_0x01d9;	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x01cb:
        r1 = r14.zzge();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r1 = r1.zzim();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r3 = "Fewer entries removed from local database than expected";	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r1.log(r3);	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
    L_0x01d9:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        r0.endTransaction();	 Catch:{ SQLiteFullException -> 0x009c, SQLiteDatabaseLockedException -> 0x00d6, SQLiteException -> 0x00ee, all -> 0x013d }
        if (r2 == 0) goto L_0x01e4;
    L_0x01e1:
        r2.close();
    L_0x01e4:
        if (r0 == 0) goto L_0x01e9;
    L_0x01e6:
        r0.close();
    L_0x01e9:
        r0 = r10;
        goto L_0x0008;
    L_0x01ec:
        r0 = r14.zzge();
        r0 = r0.zzip();
        r1 = "Failed to read events from database in reasonable time";
        r0.log(r1);
        r0 = 0;
        goto L_0x0008;
    L_0x01fd:
        r0 = move-exception;
        r2 = r11;
        goto L_0x0141;
    L_0x0201:
        r1 = move-exception;
        r2 = r11;
        r13 = r1;
        r1 = r0;
        r0 = r13;
        goto L_0x0141;
    L_0x0208:
        r0 = move-exception;
        r13 = r1;
        r1 = r2;
        r2 = r13;
        goto L_0x0141;
    L_0x020e:
        r0 = move-exception;
        goto L_0x0141;
    L_0x0211:
        r0 = move-exception;
        r2 = r11;
        goto L_0x00f2;
    L_0x0215:
        r1 = move-exception;
        r2 = r11;
        r13 = r1;
        r1 = r0;
        r0 = r13;
        goto L_0x00f2;
    L_0x021c:
        r0 = move-exception;
        r2 = r11;
        goto L_0x00d8;
    L_0x0220:
        r1 = move-exception;
        r2 = r11;
        r1 = r0;
        goto L_0x00d8;
    L_0x0225:
        r0 = move-exception;
        r2 = r1;
        r1 = r11;
        goto L_0x00a1;
    L_0x022a:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r11;
        goto L_0x00a1;
    L_0x0230:
        r0 = r9;
        goto L_0x00bd;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfc.zzp(int):java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>");
    }
}
