package com.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.altbeacon.beacon.p011a.C0817a;
import com.altbeacon.beacon.p011a.C0818b;
import com.altbeacon.beacon.p012b.C0822c;
import com.altbeacon.beacon.p013c.C0835d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Beacon implements Parcelable, Serializable {
    @Deprecated
    public static final Creator<Beacon> CREATOR = new C08151();
    protected static boolean f1553a = false;
    protected static C0822c f1554b = null;
    protected static C0817a f1555j = new C0818b();
    private static final List<Long> f1556q = Collections.unmodifiableList(new ArrayList());
    private static final List<C0842d> f1557r = Collections.unmodifiableList(new ArrayList());
    protected List<C0842d> f1558c;
    protected List<Long> f1559d;
    protected List<Long> f1560e;
    protected Double f1561f;
    protected int f1562g;
    protected int f1563h;
    protected String f1564i;
    protected int f1565k;
    protected int f1566l;
    protected int f1567m;
    protected String f1568n;
    protected String f1569o;
    protected boolean f1570p;
    private Double f1571s;

    static class C08151 implements Creator<Beacon> {
        C08151() {
        }

        public Beacon m1556a(Parcel parcel) {
            return new Beacon(parcel);
        }

        public Beacon[] m1557a(int i) {
            return new Beacon[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1556a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1557a(i);
        }
    }

    protected Beacon() {
        this.f1571s = null;
        this.f1567m = -1;
        this.f1570p = false;
        this.f1558c = new ArrayList(1);
        this.f1559d = new ArrayList(1);
        this.f1560e = new ArrayList(1);
    }

    @Deprecated
    protected Beacon(Parcel parcel) {
        int i;
        boolean z = false;
        this.f1571s = null;
        this.f1567m = -1;
        this.f1570p = false;
        int readInt = parcel.readInt();
        this.f1558c = new ArrayList(readInt);
        for (i = 0; i < readInt; i++) {
            this.f1558c.add(C0842d.m1685a(parcel.readString()));
        }
        this.f1561f = Double.valueOf(parcel.readDouble());
        this.f1562g = parcel.readInt();
        this.f1563h = parcel.readInt();
        this.f1564i = parcel.readString();
        this.f1565k = parcel.readInt();
        this.f1567m = parcel.readInt();
        readInt = parcel.readInt();
        this.f1559d = new ArrayList(readInt);
        for (i = 0; i < readInt; i++) {
            this.f1559d.add(Long.valueOf(parcel.readLong()));
        }
        readInt = parcel.readInt();
        this.f1560e = new ArrayList(readInt);
        for (i = 0; i < readInt; i++) {
            this.f1560e.add(Long.valueOf(parcel.readLong()));
        }
        this.f1566l = parcel.readInt();
        this.f1568n = parcel.readString();
        this.f1569o = parcel.readString();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        }
        this.f1570p = z;
    }

    public static C0822c m1538a() {
        return f1554b;
    }

    protected static Double m1539a(int i, double d) {
        if (m1538a() != null) {
            return Double.valueOf(m1538a().mo1872a(i, d));
        }
        C0835d.m1663d("Beacon", "Distance calculator not set.  Distance will bet set to -1", new Object[0]);
        return Double.valueOf(-1.0d);
    }

    public static void m1540a(C0822c c0822c) {
        f1554b = c0822c;
    }

    private StringBuilder m1541l() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (C0842d c0842d : this.f1558c) {
            if (i > 1) {
                stringBuilder.append(" ");
            }
            stringBuilder.append("id");
            stringBuilder.append(i);
            stringBuilder.append(": ");
            stringBuilder.append(c0842d == null ? "null" : c0842d.toString());
            i++;
        }
        if (this.f1569o != null) {
            stringBuilder.append(" type " + this.f1569o);
        }
        return stringBuilder;
    }

    public void m1542a(double d) {
        this.f1571s = Double.valueOf(d);
        this.f1561f = null;
    }

    public void m1543a(int i) {
        this.f1562g = i;
    }

    public void m1544a(List<Long> list) {
        this.f1560e = list;
    }

    public int m1545b() {
        return this.f1567m;
    }

    public C0842d m1546b(int i) {
        return (C0842d) this.f1558c.get(i);
    }

    public C0842d m1547c() {
        return (C0842d) this.f1558c.get(0);
    }

    public List<Long> m1548d() {
        return this.f1559d.getClass().isInstance(f1556q) ? this.f1559d : Collections.unmodifiableList(this.f1559d);
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    public List<Long> m1549e() {
        return this.f1560e.getClass().isInstance(f1556q) ? this.f1560e : Collections.unmodifiableList(this.f1560e);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Beacon)) {
            return false;
        }
        Beacon beacon = (Beacon) obj;
        return this.f1558c.equals(beacon.f1558c) ? f1553a ? m1553i().equals(beacon.m1553i()) : true : false;
    }

    public double m1550f() {
        if (this.f1561f == null) {
            double d = (double) this.f1562g;
            if (this.f1571s != null) {
                d = this.f1571s.doubleValue();
            } else {
                C0835d.m1657a("Beacon", "Not using running average RSSI because it is null", new Object[0]);
            }
            this.f1561f = m1539a(this.f1563h, d);
        }
        return this.f1561f.doubleValue();
    }

    public int m1551g() {
        return this.f1562g;
    }

    public int m1552h() {
        return this.f1565k;
    }

    public int hashCode() {
        StringBuilder l = m1541l();
        if (f1553a) {
            l.append(this.f1564i);
        }
        return l.toString().hashCode();
    }

    public String m1553i() {
        return this.f1564i;
    }

    public boolean m1554j() {
        return this.f1570p;
    }

    public boolean m1555k() {
        return this.f1558c.size() == 0 && this.f1559d.size() != 0;
    }

    public String toString() {
        return m1541l().toString();
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f1558c.size());
        for (C0842d c0842d : this.f1558c) {
            parcel.writeString(c0842d == null ? null : c0842d.toString());
        }
        parcel.writeDouble(m1550f());
        parcel.writeInt(this.f1562g);
        parcel.writeInt(this.f1563h);
        parcel.writeString(this.f1564i);
        parcel.writeInt(this.f1565k);
        parcel.writeInt(this.f1567m);
        parcel.writeInt(this.f1559d.size());
        for (Long longValue : this.f1559d) {
            parcel.writeLong(longValue.longValue());
        }
        parcel.writeInt(this.f1560e.size());
        for (Long longValue2 : this.f1560e) {
            parcel.writeLong(longValue2.longValue());
        }
        parcel.writeInt(this.f1566l);
        parcel.writeString(this.f1568n);
        parcel.writeString(this.f1569o);
        parcel.writeByte((byte) (this.f1570p ? 1 : 0));
    }
}
