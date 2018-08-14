package com.yandex.metrica.impl.ob;

import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;

public final class dz {
    private Integer f12295a;
    private final Integer f12296b;
    private final Integer f12297c;
    private final Integer f12298d;
    private final Integer f12299e;
    private final String f12300f;
    private final String f12301g;
    private final boolean f12302h;
    private final int f12303i;
    private final Integer f12304j;

    static abstract class C4456b {
        static final Integer f12290a = Integer.valueOf(Integer.MAX_VALUE);
        static final Integer f12291b = Integer.valueOf(Integer.MAX_VALUE);
        static final Integer f12292c = Integer.valueOf(Integer.MAX_VALUE);
        static final Integer f12293d = Integer.valueOf(Integer.MAX_VALUE);
        static final Integer f12294e = Integer.valueOf(Integer.MAX_VALUE);

        abstract dz mo7083a(CellInfo cellInfo);

        C4456b() {
        }

        protected dz m15838a(Integer num, Integer num2, CellSignalStrength cellSignalStrength, Integer num3, Integer num4, boolean z, int i, Integer num5) {
            Integer num6 = null;
            if (num != null) {
                if (num == f12290a) {
                    num = null;
                }
                num6 = num;
            }
            Integer num7 = null;
            if (num2 != null) {
                if (num2 == f12291b) {
                    num2 = null;
                }
                num7 = num2;
            }
            Integer valueOf = cellSignalStrength != null ? Integer.valueOf(cellSignalStrength.getDbm()) : null;
            Integer num8 = null;
            if (num4 != null) {
                if (num4 == f12292c) {
                    num4 = null;
                }
                num8 = num4;
            }
            Integer num9 = null;
            if (num3 != null) {
                if (num3 == f12293d) {
                    num3 = null;
                }
                num9 = num3;
            }
            Integer num10 = (num5 == null || num5 == f12294e) ? null : num5;
            return new dz(num8, num9, num7, num6, null, null, valueOf, z, i, num10);
        }
    }

    static class C4457a extends C4456b {
        C4457a() {
        }

        dz mo7083a(CellInfo cellInfo) {
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            return m15838a(null, null, cellInfoCdma.getCellSignalStrength(), null, null, cellInfoCdma.isRegistered(), 2, null);
        }
    }

    static class C4458c extends C4456b {
        C4458c() {
        }

        dz mo7083a(CellInfo cellInfo) {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            return m15838a(Integer.valueOf(cellIdentity.getCid()), Integer.valueOf(cellIdentity.getLac()), cellInfoGsm.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoGsm.isRegistered(), 1, null);
        }
    }

    static class C4459d extends C4456b {
        C4459d() {
        }

        dz mo7083a(CellInfo cellInfo) {
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
            return m15838a(Integer.valueOf(cellIdentity.getCi()), Integer.valueOf(cellIdentity.getTac()), cellInfoLte.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoLte.isRegistered(), 4, Integer.valueOf(cellIdentity.getPci()));
        }
    }

    static class C4460e extends C4456b {
        C4460e() {
        }

        dz mo7083a(CellInfo cellInfo) {
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
            CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
            return m15838a(Integer.valueOf(cellIdentity.getCid()), Integer.valueOf(cellIdentity.getLac()), cellInfoWcdma.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoWcdma.isRegistered(), 3, Integer.valueOf(cellIdentity.getPsc()));
        }
    }

    public dz(Integer num, Integer num2, Integer num3, Integer num4, String str, String str2, Integer num5, boolean z, int i, Integer num6) {
        this.f12296b = num;
        this.f12297c = num2;
        this.f12298d = num3;
        this.f12299e = num4;
        this.f12300f = str;
        this.f12301g = str2;
        this.f12295a = num5;
        this.f12302h = z;
        this.f12303i = i;
        this.f12304j = num6;
    }

    public Integer m15843a() {
        return this.f12295a;
    }

    public Integer m15845b() {
        return this.f12296b;
    }

    public Integer m15846c() {
        return this.f12297c;
    }

    public Integer m15847d() {
        return this.f12298d;
    }

    public Integer m15848e() {
        return this.f12299e;
    }

    public String m15849f() {
        return this.f12300f;
    }

    public String m15850g() {
        return this.f12301g;
    }

    public boolean m15851h() {
        return this.f12302h;
    }

    public void m15844a(Integer num) {
        this.f12295a = num;
    }

    public int m15852i() {
        return this.f12303i;
    }

    public Integer m15853j() {
        return this.f12304j;
    }

    public String toString() {
        return "CellDescription{mSignalStrength=" + this.f12295a + ", mMobileCountryCode=" + this.f12296b + ", mMobileNetworkCode=" + this.f12297c + ", mLocationAreaCode=" + this.f12298d + ", mCellId=" + this.f12299e + ", mOperatorName='" + this.f12300f + '\'' + ", mNetworkType='" + this.f12301g + '\'' + ", mConnected=" + this.f12302h + ", mCellType=" + this.f12303i + ", mPci=" + this.f12304j + '}';
    }
}
