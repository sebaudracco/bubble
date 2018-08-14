package com.inmobi.signals.p119a;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONObject;

/* compiled from: CellTowerInfo */
public class C3246b {
    private static final String f8165a = C3246b.class.getSimpleName();
    private String f8166b;
    private int f8167c;
    private int f8168d;

    @TargetApi(18)
    public C3246b(CellInfo cellInfo, String str, String str2, int i) {
        if (cellInfo instanceof CellInfoGsm) {
            this.f8168d = i;
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            this.f8167c = cellInfoGsm.getCellSignalStrength().getDbm();
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            this.f8166b = m10843a(str, str2, cellIdentity.getLac(), cellIdentity.getCid(), -1, Integer.MAX_VALUE);
        } else if (cellInfo instanceof CellInfoCdma) {
            this.f8168d = i;
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            this.f8167c = cellInfoCdma.getCellSignalStrength().getDbm();
            CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
            this.f8166b = m10842a(str, cellIdentity2.getSystemId(), cellIdentity2.getNetworkId(), cellIdentity2.getBasestationId());
        } else if (VERSION.SDK_INT >= 18) {
            if (cellInfo instanceof CellInfoWcdma) {
                this.f8168d = i;
                CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                this.f8167c = cellInfoWcdma.getCellSignalStrength().getDbm();
                CellIdentityWcdma cellIdentity3 = cellInfoWcdma.getCellIdentity();
                this.f8166b = m10843a(str, str2, cellIdentity3.getLac(), cellIdentity3.getCid(), cellIdentity3.getPsc(), Integer.MAX_VALUE);
            }
        } else if (cellInfo instanceof CellInfoLte) {
            this.f8168d = i;
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            this.f8167c = cellInfoLte.getCellSignalStrength().getDbm();
            CellIdentityLte cellIdentity4 = cellInfoLte.getCellIdentity();
            this.f8166b = m10843a(str, str2, cellIdentity4.getTac(), cellIdentity4.getCi(), -1, cellIdentity4.getPci());
        }
    }

    public String m10842a(String str, int i, int i2, int i3) {
        return str + "#" + i + "#" + i2 + "#" + i3;
    }

    public String m10843a(String str, String str2, int i, int i2, int i3, int i4) {
        return str + "#" + str2 + "#" + i + "#" + i2 + "#" + (i3 == -1 ? "" : Integer.valueOf(i3)) + "#" + (i4 == Integer.MAX_VALUE ? "" : Integer.valueOf(i4));
    }

    public void m10845a(int i) {
        this.f8168d = i;
    }

    public void m10846a(String str) {
        this.f8166b = str;
    }

    public void m10847b(int i) {
        this.f8167c = i;
    }

    public JSONObject m10844a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.f8166b);
            if (this.f8167c != Integer.MAX_VALUE) {
                jSONObject.put("ss", this.f8167c);
            }
            jSONObject.put("nt", this.f8168d);
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8165a, "Error while converting CellTowerInfo to string.", e);
        }
        return jSONObject;
    }
}
