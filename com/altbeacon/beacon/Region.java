package com.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Region implements Parcelable, Serializable {
    public static final Creator<Region> CREATOR = new C08161();
    private static final Pattern f1572d = Pattern.compile("^[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}$");
    protected final List<C0842d> f1573a;
    protected final String f1574b;
    protected final String f1575c;

    static class C08161 implements Creator<Region> {
        C08161() {
        }

        public Region m1558a(Parcel parcel) {
            return new Region(parcel);
        }

        public Region[] m1559a(int i) {
            return new Region[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1558a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1559a(i);
        }
    }

    protected Region(Parcel parcel) {
        this.f1575c = parcel.readString();
        this.f1574b = parcel.readString();
        int readInt = parcel.readInt();
        this.f1573a = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            String readString = parcel.readString();
            if (readString == null) {
                this.f1573a.add(null);
            } else {
                this.f1573a.add(C0842d.m1685a(readString));
            }
        }
    }

    public Region(String str, C0842d c0842d, C0842d c0842d2, C0842d c0842d3) {
        this.f1573a = new ArrayList(3);
        this.f1573a.add(c0842d);
        this.f1573a.add(c0842d2);
        this.f1573a.add(c0842d3);
        this.f1575c = str;
        this.f1574b = null;
        if (str == null) {
            throw new NullPointerException("uniqueId may not be null");
        }
    }

    public Region(String str, List<C0842d> list, String str2) {
        m1560a(str2);
        this.f1573a = new ArrayList(list);
        this.f1575c = str;
        this.f1574b = str2;
        if (str == null) {
            throw new NullPointerException("uniqueId may not be null");
        }
    }

    private void m1560a(String str) {
        if (str != null && !f1572d.matcher(str).matches()) {
            throw new IllegalArgumentException("Invalid mac address: '" + str + "' Must be 6 hex bytes separated by colons.");
        }
    }

    public C0842d m1561a(int i) {
        return this.f1573a.size() > i ? (C0842d) this.f1573a.get(i) : null;
    }

    public String m1562a() {
        return this.f1575c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m1563a(com.altbeacon.beacon.Beacon r6) {
        /*
        r5 = this;
        r1 = 0;
        r0 = r5.f1573a;
        r0 = r0.size();
    L_0x0007:
        r3 = r0 + -1;
        if (r3 < 0) goto L_0x0032;
    L_0x000b:
        r0 = r5.f1573a;
        r0 = r0.get(r3);
        r0 = (com.altbeacon.beacon.C0842d) r0;
        r2 = 0;
        r4 = r6.f1558c;
        r4 = r4.size();
        if (r3 >= r4) goto L_0x0020;
    L_0x001c:
        r2 = r6.m1546b(r3);
    L_0x0020:
        if (r2 != 0) goto L_0x0024;
    L_0x0022:
        if (r0 != 0) goto L_0x002e;
    L_0x0024:
        if (r2 == 0) goto L_0x0030;
    L_0x0026:
        if (r0 == 0) goto L_0x0030;
    L_0x0028:
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x0030;
    L_0x002e:
        r0 = r1;
    L_0x002f:
        return r0;
    L_0x0030:
        r0 = r3;
        goto L_0x0007;
    L_0x0032:
        r0 = r5.f1574b;
        if (r0 == 0) goto L_0x0042;
    L_0x0036:
        r0 = r5.f1574b;
        r2 = r6.f1564i;
        r0 = r0.equalsIgnoreCase(r2);
        if (r0 != 0) goto L_0x0042;
    L_0x0040:
        r0 = r1;
        goto L_0x002f;
    L_0x0042:
        r0 = 1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.altbeacon.beacon.Region.a(com.altbeacon.beacon.Beacon):boolean");
    }

    public boolean m1564a(Region region) {
        if (region.f1573a.size() != this.f1573a.size()) {
            return false;
        }
        int i = 0;
        while (i < region.f1573a.size()) {
            if (region.m1561a(i) == null && m1561a(i) != null) {
                return false;
            }
            if (region.m1561a(i) != null && m1561a(i) == null) {
                return false;
            }
            if ((region.m1561a(i) != null || m1561a(i) != null) && !region.m1561a(i).equals(m1561a(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    @Deprecated
    public Region m1565b() {
        return new Region(this.f1575c, this.f1573a, this.f1574b);
    }

    @Deprecated
    public /* synthetic */ Object clone() {
        return m1565b();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof Region ? ((Region) obj).f1575c.equals(this.f1575c) : false;
    }

    public int hashCode() {
        return this.f1575c.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (C0842d c0842d : this.f1573a) {
            if (i > 1) {
                stringBuilder.append(" ");
            }
            stringBuilder.append("id");
            stringBuilder.append(i);
            stringBuilder.append(": ");
            stringBuilder.append(c0842d == null ? "null" : c0842d.toString());
            i++;
        }
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1575c);
        parcel.writeString(this.f1574b);
        parcel.writeInt(this.f1573a.size());
        for (C0842d c0842d : this.f1573a) {
            if (c0842d != null) {
                parcel.writeString(c0842d.toString());
            } else {
                parcel.writeString(null);
            }
        }
    }
}
