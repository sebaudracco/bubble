package com.altbeacon.beacon.service.p009a;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanFilter.Builder;
import android.os.ParcelUuid;
import com.altbeacon.beacon.C0819c;
import com.altbeacon.beacon.p013c.C0835d;
import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.service.scanner.ScanFilterUtils;

@TargetApi(21)
public class C0861g {

    class C0860a {
        public Long f1720a = null;
        public int f1721b;
        public byte[] f1722c;
        public byte[] f1723d;
        final /* synthetic */ C0861g f1724e;

        C0860a(C0861g c0861g) {
            this.f1724e = c0861g;
        }
    }

    public List<C0860a> m1748a(C0819c c0819c) {
        List arrayList = new ArrayList();
        for (int i : c0819c.m1578b()) {
            Long f = c0819c.m1582f();
            long longValue = c0819c.m1579c().longValue();
            int d = c0819c.m1580d();
            int e = c0819c.m1581e();
            byte[] bArr = new byte[((e + 1) - 2)];
            byte[] bArr2 = new byte[((e + 1) - 2)];
            byte[] a = C0819c.m1569a(longValue, (e - d) + 1);
            for (int i2 = 2; i2 <= e; i2++) {
                int i3 = i2 - 2;
                if (i2 < d) {
                    bArr[i3] = (byte) 0;
                    bArr2[i3] = (byte) 0;
                } else {
                    bArr[i3] = a[i2 - d];
                    bArr2[i3] = (byte) -1;
                }
            }
            C0860a c0860a = new C0860a(this);
            c0860a.f1721b = i;
            c0860a.f1722c = bArr;
            c0860a.f1723d = bArr2;
            c0860a.f1720a = f;
            arrayList.add(c0860a);
        }
        return arrayList;
    }

    public List<ScanFilter> m1749a(List<C0819c> list) {
        List<ScanFilter> arrayList = new ArrayList();
        for (C0819c a : list) {
            for (C0860a c0860a : m1748a(a)) {
                Builder builder = new Builder();
                if (c0860a.f1720a != null) {
                    String format = String.format("0000%04X-0000-1000-8000-00805f9b34fb", new Object[]{c0860a.f1720a});
                    String str = "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF";
                    ParcelUuid fromString = ParcelUuid.fromString(format);
                    ParcelUuid fromString2 = ParcelUuid.fromString(str);
                    if (C0835d.m1659a()) {
                        C0835d.m1657a(ScanFilterUtils.TAG, "making scan filter for service: " + format + " " + fromString, new Object[0]);
                        C0835d.m1657a(ScanFilterUtils.TAG, "making scan filter with service mask: " + str + " " + fromString2, new Object[0]);
                    }
                    builder.setServiceUuid(fromString, fromString2);
                } else {
                    builder.setServiceUuid(null);
                    builder.setManufacturerData(c0860a.f1721b, c0860a.f1722c, c0860a.f1723d);
                }
                ScanFilter build = builder.build();
                if (C0835d.m1659a()) {
                    C0835d.m1657a(ScanFilterUtils.TAG, "Set up a scan filter: " + build, new Object[0]);
                }
                arrayList.add(build);
            }
        }
        return arrayList;
    }
}
