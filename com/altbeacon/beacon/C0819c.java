package com.altbeacon.beacon;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.support.v4.view.InputDeviceCompat;
import android.util.Log;
import com.altbeacon.beacon.p013c.C0835d;
import com.altbeacon.p010a.C0808a;
import com.altbeacon.p010a.C0812c;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.altbeacon.bluetooth.Pdu;

public class C0819c implements Serializable {
    private static final Pattern f1576A = Pattern.compile("p\\:(\\d+)\\-(\\d+)\\:?([\\-\\d]+)?");
    private static final Pattern f1577B = Pattern.compile("x");
    private static final char[] f1578C = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Pattern f1579w = Pattern.compile("i\\:(\\d+)\\-(\\d+)([blv]*)?");
    private static final Pattern f1580x = Pattern.compile("m\\:(\\d+)-(\\d+)\\=([0-9A-Fa-f]+)");
    private static final Pattern f1581y = Pattern.compile("s\\:(\\d+)-(\\d+)\\=([0-9A-Fa-f]+)");
    private static final Pattern f1582z = Pattern.compile("d\\:(\\d+)\\-(\\d+)([bl]*)?");
    private Long f1583D;
    protected String f1584a;
    protected final List<Integer> f1585b = new ArrayList();
    protected final List<Integer> f1586c = new ArrayList();
    protected final List<Boolean> f1587d = new ArrayList();
    protected final List<Integer> f1588e = new ArrayList();
    protected final List<Integer> f1589f = new ArrayList();
    protected final List<Boolean> f1590g = new ArrayList();
    protected final List<Boolean> f1591h = new ArrayList();
    protected Integer f1592i;
    protected Integer f1593j;
    protected Integer f1594k;
    protected Integer f1595l;
    protected Long f1596m;
    protected Boolean f1597n;
    protected Integer f1598o;
    protected Integer f1599p;
    protected Integer f1600q;
    protected Integer f1601r;
    protected Boolean f1602s = Boolean.valueOf(true);
    protected String f1603t;
    protected int[] f1604u = new int[]{76};
    protected List<C0819c> f1605v = new ArrayList();

    public static class C0830a extends RuntimeException {
        public C0830a(String str) {
            super(str);
        }
    }

    protected static String m1566a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = f1578C[i2 >>> 4];
            cArr[(i * 2) + 1] = f1578C[i2 & 15];
        }
        return new String(cArr);
    }

    private String m1567a(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        byte[] bArr2 = new byte[((i2 - i) + 1)];
        if (z) {
            for (i3 = 0; i3 <= i2 - i; i3++) {
                bArr2[i3] = bArr[((bArr2.length + i) - 1) - i3];
            }
        } else {
            for (i3 = 0; i3 <= i2 - i; i3++) {
                bArr2[i3] = bArr[i + i3];
            }
        }
        if ((i2 - i) + 1 < 5) {
            long j = 0;
            for (i3 = 0; i3 < bArr2.length; i3++) {
                j += ((long) (bArr2[(bArr2.length - i3) - 1] & 255)) * ((long) Math.pow(256.0d, ((double) i3) * MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE));
            }
            return Long.toString(j);
        }
        String a = C0819c.m1566a(bArr2);
        if (bArr2.length != 16) {
            return "0x" + a;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a.substring(0, 8));
        stringBuilder.append("-");
        stringBuilder.append(a.substring(8, 12));
        stringBuilder.append("-");
        stringBuilder.append(a.substring(12, 16));
        stringBuilder.append("-");
        stringBuilder.append(a.substring(16, 20));
        stringBuilder.append("-");
        stringBuilder.append(a.substring(20, 32));
        return stringBuilder.toString();
    }

    private boolean m1568a(byte[] bArr, int i, byte[] bArr2) {
        int length = bArr2.length;
        if (bArr.length - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (bArr[i + i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] m1569a(long j, int i) {
        return C0819c.m1570a(j, i, true);
    }

    public static byte[] m1570a(long j, int i, boolean z) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = z ? i2 : (i - i2) - 1;
            bArr[i2] = (byte) ((int) (((255 << (((i - i3) - 1) * 8)) & j) >> ((int) ((long) (((i - i3) - 1) * 8)))));
        }
        return bArr;
    }

    @TargetApi(9)
    private byte[] m1571a(byte[] bArr, int i) {
        return bArr.length >= i ? bArr : Arrays.copyOf(bArr, i);
    }

    private String m1572b(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    private int m1573g() {
        int i;
        int intValue;
        if (this.f1586c != null) {
            i = 0;
            for (Integer intValue2 : this.f1586c) {
                intValue = intValue2.intValue();
                if (intValue <= i) {
                    intValue = i;
                }
                i = intValue;
            }
        } else {
            i = 0;
        }
        if (this.f1589f != null) {
            for (Integer intValue22 : this.f1589f) {
                intValue = intValue22.intValue();
                if (intValue > i) {
                    i = intValue;
                }
            }
        }
        if (this.f1599p != null && this.f1599p.intValue() > r1) {
            i = this.f1599p.intValue();
        }
        if (this.f1595l != null && this.f1595l.intValue() > r1) {
            i = this.f1595l.intValue();
        }
        return i + 1;
    }

    public Beacon mo1871a(byte[] bArr, int i, BluetoothDevice bluetoothDevice) {
        return m1575a(bArr, i, bluetoothDevice, new Beacon());
    }

    protected Beacon m1575a(byte[] bArr, int i, BluetoothDevice bluetoothDevice, Beacon beacon) {
        C0812c c0812c;
        Object obj;
        Object obj2;
        String str;
        String str2;
        C0808a c0808a = new C0808a(bArr);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (C0812c c0812c2 : c0808a.m1491a()) {
            int i2;
            Beacon beacon2;
            byte[] bArr2;
            byte[] a;
            int c;
            int i3;
            Object obj3;
            int i4;
            if (c0812c2.m1512a() == Pdu.GATT_SERVICE_UUID_PDU_TYPE || c0812c2.m1512a() == (byte) -1) {
                if (C0835d.m1659a()) {
                    C0835d.m1657a("BeaconParser", "Processing pdu type %02X: %s with startIndex: %d, endIndex: %d", Byte.valueOf(c0812c2.m1512a()), C0819c.m1566a(bArr), Integer.valueOf(c0812c2.m1514c()), Integer.valueOf(c0812c2.m1515d()));
                    c0812c = c0812c2;
                } else {
                    c0812c = c0812c2;
                }
                if (c0812c != null) {
                    if (C0835d.m1659a()) {
                        C0835d.m1657a("BeaconParser", "No PDUs to process in this packet.", new Object[0]);
                    }
                    i2 = 0;
                    obj = 1;
                    beacon2 = beacon;
                } else {
                    bArr2 = null;
                    a = C0819c.m1569a(m1579c().longValue(), (this.f1593j.intValue() - this.f1592i.intValue()) + 1);
                    if (m1582f() != null) {
                        bArr2 = C0819c.m1570a(m1582f().longValue(), (this.f1595l.intValue() - this.f1594k.intValue()) + 1, false);
                    }
                    c = c0812c.m1514c();
                    if (m1582f() != null) {
                        if (m1568a(bArr, this.f1592i.intValue() + c, a)) {
                            obj2 = 1;
                        }
                        obj2 = null;
                    } else {
                        if (m1568a(bArr, this.f1594k.intValue() + c, bArr2) && m1568a(bArr, this.f1592i.intValue() + c, a)) {
                            i3 = 1;
                        }
                        obj2 = null;
                    }
                    if (obj2 != null) {
                        if (m1582f() != null) {
                            if (C0835d.m1659a()) {
                                C0835d.m1657a("BeaconParser", "This is not a matching Beacon advertisement. (Was expecting %s.  The bytes I see are: %s", m1572b(a), C0819c.m1566a(bArr));
                            }
                        } else if (C0835d.m1659a()) {
                            C0835d.m1657a("BeaconParser", "This is not a matching Beacon advertisement. Was expecting %s at offset %d and %s at offset %d.  The bytes I see are: %s", m1572b(bArr2), Integer.valueOf(this.f1594k.intValue() + c), m1572b(a), Integer.valueOf(this.f1592i.intValue() + c), C0819c.m1566a(bArr));
                        }
                        obj = 1;
                        beacon = null;
                    } else {
                        if (C0835d.m1659a()) {
                            C0835d.m1657a("BeaconParser", "This is a recognized beacon advertisement -- %s seen", m1572b(a));
                            C0835d.m1657a("BeaconParser", "Bytes are: %s", C0819c.m1566a(bArr));
                        }
                        obj = null;
                    }
                    if (obj2 == null) {
                        if (bArr.length <= this.f1601r.intValue() + c && this.f1602s.booleanValue()) {
                            if (C0835d.m1659a()) {
                                C0835d.m1657a("BeaconParser", "Expanding buffer because it is too short to parse: " + bArr.length + ", needed: " + (this.f1601r.intValue() + c), new Object[0]);
                            }
                            bArr = m1571a(bArr, this.f1601r.intValue() + c);
                        }
                        obj3 = obj;
                        i4 = 0;
                        while (i4 < this.f1586c.size()) {
                            i3 = ((Integer) this.f1586c.get(i4)).intValue() + c;
                            if (i3 <= c0812c.m1515d() && ((Boolean) this.f1591h.get(i4)).booleanValue()) {
                                if (C0835d.m1659a()) {
                                    C0835d.m1657a("BeaconParser", "Need to truncate identifier by " + (i3 - c0812c.m1515d()), new Object[0]);
                                }
                                arrayList.add(C0842d.m1687a(bArr, ((Integer) this.f1585b.get(i4)).intValue() + c, c0812c.m1515d() + 1, ((Boolean) this.f1587d.get(i4)).booleanValue()));
                            } else if (i3 > c0812c.m1515d() || this.f1602s.booleanValue()) {
                                arrayList.add(C0842d.m1687a(bArr, ((Integer) this.f1585b.get(i4)).intValue() + c, i3 + 1, ((Boolean) this.f1587d.get(i4)).booleanValue()));
                            } else {
                                obj3 = 1;
                                if (C0835d.m1659a()) {
                                    C0835d.m1657a("BeaconParser", "Cannot parse identifier " + i4 + " because PDU is too short.  endIndex: " + i3 + " PDU endIndex: " + c0812c.m1515d(), new Object[0]);
                                }
                            }
                            i4++;
                        }
                        for (i4 = 0; i4 < this.f1589f.size(); i4++) {
                            i3 = ((Integer) this.f1589f.get(i4)).intValue() + c;
                            if (i3 > c0812c.m1515d() || this.f1602s.booleanValue()) {
                                arrayList2.add(Long.decode(m1567a(bArr, ((Integer) this.f1588e.get(i4)).intValue() + c, i3, ((Boolean) this.f1590g.get(i4)).booleanValue())));
                            } else {
                                if (C0835d.m1659a()) {
                                    C0835d.m1657a("BeaconParser", "Cannot parse data field " + i4 + " because PDU is too short.  endIndex: " + i3 + " PDU endIndex: " + c0812c.m1515d() + ".  Setting value to 0", new Object[0]);
                                }
                                arrayList2.add(new Long(0));
                            }
                        }
                        if (this.f1598o == null) {
                            i2 = this.f1599p.intValue() + c;
                            try {
                                if (i2 > c0812c.m1515d() || this.f1602s.booleanValue()) {
                                    i2 = Integer.parseInt(m1567a(bArr, this.f1598o.intValue() + c, this.f1599p.intValue() + c, false)) + this.f1600q.intValue();
                                    if (i2 > 127) {
                                        i2 += InputDeviceCompat.SOURCE_ANY;
                                    }
                                    beacon.f1563h = i2;
                                } else {
                                    obj3 = 1;
                                    if (C0835d.m1659a()) {
                                        C0835d.m1657a("BeaconParser", "Cannot parse power field because PDU is too short.  endIndex: " + i2 + " PDU endIndex: " + c0812c.m1515d(), new Object[0]);
                                    }
                                }
                                i2 = c;
                                obj = obj3;
                                beacon2 = beacon;
                            } catch (NumberFormatException e) {
                                i2 = c;
                                obj = obj3;
                                beacon2 = beacon;
                            } catch (NullPointerException e2) {
                                i2 = c;
                                obj = obj3;
                                beacon2 = beacon;
                            }
                        } else {
                            i2 = c;
                            obj = obj3;
                            beacon2 = beacon;
                        }
                    } else {
                        i2 = c;
                        beacon2 = beacon;
                    }
                }
                if (obj != null) {
                    return null;
                }
                c = Integer.parseInt(m1567a(bArr, this.f1592i.intValue() + i2, this.f1593j.intValue() + i2, false));
                i3 = Integer.parseInt(m1567a(bArr, i2, i2 + 1, true));
                str = null;
                str2 = null;
                if (bluetoothDevice != null) {
                    str = bluetoothDevice.getAddress();
                    str2 = bluetoothDevice.getName();
                }
                beacon2.f1558c = arrayList;
                beacon2.f1559d = arrayList2;
                beacon2.f1562g = i;
                beacon2.f1565k = c;
                if (this.f1596m == null) {
                    beacon2.f1567m = (int) this.f1596m.longValue();
                } else {
                    beacon2.f1567m = -1;
                }
                beacon2.f1564i = str;
                beacon2.f1568n = str2;
                beacon2.f1566l = i3;
                beacon2.f1569o = this.f1603t;
                boolean z = this.f1605v.size() <= 0 || this.f1597n.booleanValue();
                beacon2.f1570p = z;
                return beacon2;
            } else if (C0835d.m1659a()) {
                C0835d.m1657a("BeaconParser", "Ignoring pdu type %02X", Byte.valueOf(c0812c2.m1512a()));
            }
        }
        c0812c = null;
        if (c0812c != null) {
            bArr2 = null;
            a = C0819c.m1569a(m1579c().longValue(), (this.f1593j.intValue() - this.f1592i.intValue()) + 1);
            if (m1582f() != null) {
                bArr2 = C0819c.m1570a(m1582f().longValue(), (this.f1595l.intValue() - this.f1594k.intValue()) + 1, false);
            }
            c = c0812c.m1514c();
            if (m1582f() != null) {
                i3 = 1;
            } else {
                if (m1568a(bArr, this.f1592i.intValue() + c, a)) {
                    obj2 = 1;
                }
                obj2 = null;
            }
            if (obj2 != null) {
                if (C0835d.m1659a()) {
                    C0835d.m1657a("BeaconParser", "This is a recognized beacon advertisement -- %s seen", m1572b(a));
                    C0835d.m1657a("BeaconParser", "Bytes are: %s", C0819c.m1566a(bArr));
                }
                obj = null;
            } else {
                if (m1582f() != null) {
                    if (C0835d.m1659a()) {
                        C0835d.m1657a("BeaconParser", "This is not a matching Beacon advertisement. Was expecting %s at offset %d and %s at offset %d.  The bytes I see are: %s", m1572b(bArr2), Integer.valueOf(this.f1594k.intValue() + c), m1572b(a), Integer.valueOf(this.f1592i.intValue() + c), C0819c.m1566a(bArr));
                    }
                } else if (C0835d.m1659a()) {
                    C0835d.m1657a("BeaconParser", "This is not a matching Beacon advertisement. (Was expecting %s.  The bytes I see are: %s", m1572b(a), C0819c.m1566a(bArr));
                }
                obj = 1;
                beacon = null;
            }
            if (obj2 == null) {
                i2 = c;
                beacon2 = beacon;
            } else {
                if (C0835d.m1659a()) {
                    C0835d.m1657a("BeaconParser", "Expanding buffer because it is too short to parse: " + bArr.length + ", needed: " + (this.f1601r.intValue() + c), new Object[0]);
                }
                bArr = m1571a(bArr, this.f1601r.intValue() + c);
                obj3 = obj;
                i4 = 0;
                while (i4 < this.f1586c.size()) {
                    i3 = ((Integer) this.f1586c.get(i4)).intValue() + c;
                    if (i3 <= c0812c.m1515d()) {
                    }
                    if (i3 > c0812c.m1515d()) {
                    }
                    arrayList.add(C0842d.m1687a(bArr, ((Integer) this.f1585b.get(i4)).intValue() + c, i3 + 1, ((Boolean) this.f1587d.get(i4)).booleanValue()));
                    i4++;
                }
                while (i4 < this.f1589f.size()) {
                    i3 = ((Integer) this.f1589f.get(i4)).intValue() + c;
                    if (i3 > c0812c.m1515d()) {
                    }
                    arrayList2.add(Long.decode(m1567a(bArr, ((Integer) this.f1588e.get(i4)).intValue() + c, i3, ((Boolean) this.f1590g.get(i4)).booleanValue())));
                }
                if (this.f1598o == null) {
                    i2 = c;
                    obj = obj3;
                    beacon2 = beacon;
                } else {
                    i2 = this.f1599p.intValue() + c;
                    if (i2 > c0812c.m1515d()) {
                    }
                    i2 = Integer.parseInt(m1567a(bArr, this.f1598o.intValue() + c, this.f1599p.intValue() + c, false)) + this.f1600q.intValue();
                    if (i2 > 127) {
                        i2 += InputDeviceCompat.SOURCE_ANY;
                    }
                    beacon.f1563h = i2;
                    i2 = c;
                    obj = obj3;
                    beacon2 = beacon;
                }
            }
        } else {
            if (C0835d.m1659a()) {
                C0835d.m1657a("BeaconParser", "No PDUs to process in this packet.", new Object[0]);
            }
            i2 = 0;
            obj = 1;
            beacon2 = beacon;
        }
        if (obj != null) {
            return null;
        }
        c = Integer.parseInt(m1567a(bArr, this.f1592i.intValue() + i2, this.f1593j.intValue() + i2, false));
        i3 = Integer.parseInt(m1567a(bArr, i2, i2 + 1, true));
        str = null;
        str2 = null;
        if (bluetoothDevice != null) {
            str = bluetoothDevice.getAddress();
            str2 = bluetoothDevice.getName();
        }
        beacon2.f1558c = arrayList;
        beacon2.f1559d = arrayList2;
        beacon2.f1562g = i;
        beacon2.f1565k = c;
        if (this.f1596m == null) {
            beacon2.f1567m = -1;
        } else {
            beacon2.f1567m = (int) this.f1596m.longValue();
        }
        beacon2.f1564i = str;
        beacon2.f1568n = str2;
        beacon2.f1566l = i3;
        beacon2.f1569o = this.f1603t;
        if (this.f1605v.size() <= 0) {
        }
        beacon2.f1570p = z;
        return beacon2;
    }

    public C0819c m1576a(String str) {
        String group;
        this.f1584a = str;
        Log.d("BeaconParser", "Parsing beacon layout: " + str);
        String[] split = str.split(",");
        this.f1597n = Boolean.valueOf(false);
        int length = split.length;
        int i = 0;
        while (i < length) {
            Object obj = split[i];
            Matcher matcher = f1579w.matcher(obj);
            boolean z = false;
            while (matcher.find()) {
                try {
                    int parseInt = Integer.parseInt(matcher.group(1));
                    int parseInt2 = Integer.parseInt(matcher.group(2));
                    this.f1587d.add(Boolean.valueOf(matcher.group(3).contains("l")));
                    this.f1591h.add(Boolean.valueOf(matcher.group(3).contains("v")));
                    this.f1585b.add(Integer.valueOf(parseInt));
                    this.f1586c.add(Integer.valueOf(parseInt2));
                    z = true;
                } catch (NumberFormatException e) {
                    throw new C0830a("Cannot parse integer byte offset in term: " + obj);
                }
            }
            matcher = f1582z.matcher(obj);
            while (matcher.find()) {
                try {
                    parseInt = Integer.parseInt(matcher.group(1));
                    parseInt2 = Integer.parseInt(matcher.group(2));
                    this.f1590g.add(Boolean.valueOf(matcher.group(3).contains("l")));
                    this.f1588e.add(Integer.valueOf(parseInt));
                    this.f1589f.add(Integer.valueOf(parseInt2));
                    z = true;
                } catch (NumberFormatException e2) {
                    throw new C0830a("Cannot parse integer byte offset in term: " + obj);
                }
            }
            matcher = f1576A.matcher(obj);
            while (matcher.find()) {
                try {
                    parseInt2 = Integer.parseInt(matcher.group(1));
                    int parseInt3 = Integer.parseInt(matcher.group(2));
                    this.f1600q = Integer.valueOf(matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0);
                    this.f1598o = Integer.valueOf(parseInt2);
                    this.f1599p = Integer.valueOf(parseInt3);
                    z = true;
                } catch (NumberFormatException e3) {
                    throw new C0830a("Cannot parse integer power byte offset in term: " + obj);
                }
            }
            matcher = f1580x.matcher(obj);
            while (matcher.find()) {
                try {
                    parseInt = Integer.parseInt(matcher.group(1));
                    parseInt2 = Integer.parseInt(matcher.group(2));
                    this.f1592i = Integer.valueOf(parseInt);
                    this.f1593j = Integer.valueOf(parseInt2);
                    group = matcher.group(3);
                    try {
                        this.f1583D = Long.decode("0x" + group);
                        z = true;
                    } catch (NumberFormatException e4) {
                        throw new C0830a("Cannot parse beacon type code: " + group + " in term: " + obj);
                    }
                } catch (NumberFormatException e5) {
                    throw new C0830a("Cannot parse integer byte offset in term: " + obj);
                }
            }
            matcher = f1581y.matcher(obj);
            while (matcher.find()) {
                try {
                    parseInt = Integer.parseInt(matcher.group(1));
                    parseInt2 = Integer.parseInt(matcher.group(2));
                    this.f1594k = Integer.valueOf(parseInt);
                    this.f1595l = Integer.valueOf(parseInt2);
                    group = matcher.group(3);
                    try {
                        this.f1596m = Long.decode("0x" + group);
                        z = true;
                    } catch (NumberFormatException e6) {
                        throw new C0830a("Cannot parse serviceUuid: " + group + " in term: " + obj);
                    }
                } catch (NumberFormatException e7) {
                    throw new C0830a("Cannot parse integer byte offset in term: " + obj);
                }
            }
            matcher = f1577B.matcher(obj);
            while (matcher.find()) {
                this.f1597n = Boolean.valueOf(true);
                z = true;
            }
            if (z) {
                i++;
            } else {
                C0835d.m1657a("BeaconParser", "cannot parse term %s", obj);
                throw new C0830a("Cannot parse beacon layout term: " + obj);
            }
        }
        if (!this.f1597n.booleanValue()) {
            if (this.f1585b.size() == 0 || this.f1586c.size() == 0) {
                throw new C0830a("You must supply at least one identifier offset with a prefix of 'i'");
            } else if (this.f1598o == null || this.f1599p == null) {
                throw new C0830a("You must supply a power byte offset with a prefix of 'p'");
            }
        }
        if (this.f1592i == null || this.f1593j == null) {
            throw new C0830a("You must supply a matching beacon type expression with a prefix of 'm'");
        }
        this.f1601r = Integer.valueOf(m1573g());
        return this;
    }

    public List<C0819c> m1577a() {
        return new ArrayList(this.f1605v);
    }

    public int[] m1578b() {
        return this.f1604u;
    }

    public Long m1579c() {
        return this.f1583D;
    }

    public int m1580d() {
        return this.f1592i.intValue();
    }

    public int m1581e() {
        return this.f1593j.intValue();
    }

    public boolean equals(Object obj) {
        try {
            C0819c c0819c = (C0819c) obj;
            if (c0819c.f1584a != null && c0819c.f1584a.equals(this.f1584a) && c0819c.f1603t != null && c0819c.f1603t.equals(this.f1603t)) {
                return true;
            }
        } catch (ClassCastException e) {
        }
        return false;
    }

    public Long m1582f() {
        return this.f1596m;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f1583D, this.f1585b, this.f1586c, this.f1587d, this.f1588e, this.f1589f, this.f1590g, this.f1591h, this.f1592i, this.f1593j, this.f1594k, this.f1595l, this.f1596m, this.f1597n, this.f1598o, this.f1599p, this.f1600q, this.f1601r, this.f1602s, this.f1603t, this.f1604u, this.f1605v});
    }
}
