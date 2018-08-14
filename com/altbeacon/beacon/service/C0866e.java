package com.altbeacon.beacon.service;

import android.content.Context;
import com.altbeacon.beacon.Beacon;
import com.altbeacon.beacon.Region;
import com.altbeacon.beacon.p013c.C0835d;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class C0866e {
    private static volatile C0866e f1733a;
    private static final String f1734b = C0866e.class.getSimpleName();
    private static final Object f1735f = new Object();
    private Map<Region, C0870i> f1736c;
    private Context f1737d;
    private boolean f1738e = true;

    public C0866e(Context context) {
        this.f1737d = context;
    }

    public static C0866e m1763a(Context context) {
        C0866e c0866e = f1733a;
        if (c0866e == null) {
            synchronized (f1735f) {
                c0866e = f1733a;
                if (c0866e == null) {
                    c0866e = new C0866e(context.getApplicationContext());
                    f1733a = c0866e;
                }
            }
        }
        return c0866e;
    }

    private C0870i m1764a(Region region, C0862a c0862a) {
        if (m1766g().containsKey(region)) {
            for (Region region2 : m1766g().keySet()) {
                if (region2.equals(region)) {
                    if (region2.m1564a(region)) {
                        return (C0870i) m1766g().get(region2);
                    }
                    C0835d.m1657a(f1734b, "Replacing region with unique identifier " + region.m1562a(), new Object[0]);
                    C0835d.m1657a(f1734b, "Old definition: " + region2, new Object[0]);
                    C0835d.m1657a(f1734b, "New definition: " + region, new Object[0]);
                    C0835d.m1657a(f1734b, "clearing state", new Object[0]);
                    m1766g().remove(region);
                }
            }
        }
        C0870i c0870i = new C0870i(c0862a);
        m1766g().put(region, c0870i);
        return c0870i;
    }

    private List<Region> m1765b(Beacon beacon) {
        List<Region> arrayList = new ArrayList();
        for (Region region : m1769a()) {
            if (region.m1563a(beacon)) {
                arrayList.add(region);
            } else {
                C0835d.m1657a(f1734b, "This region (%s) does not match beacon: %s", region, beacon);
            }
        }
        return arrayList;
    }

    private Map<Region, C0870i> m1766g() {
        if (this.f1736c == null) {
            m1767h();
        }
        return this.f1736c;
    }

    private void m1767h() {
        long currentTimeMillis = System.currentTimeMillis() - m1777e();
        this.f1736c = new HashMap();
        if (!this.f1738e) {
            C0835d.m1657a(f1734b, "Not restoring monitoring state because persistence is disabled", new Object[0]);
        } else if (currentTimeMillis > 900000) {
            C0835d.m1657a(f1734b, "Not restoring monitoring state because it was recorded too many milliseconds ago: " + currentTimeMillis, new Object[0]);
        } else {
            m1778f();
            C0835d.m1657a(f1734b, "Done restoring monitoring status", new Object[0]);
        }
    }

    public synchronized C0870i m1768a(Region region) {
        return (C0870i) m1766g().get(region);
    }

    public synchronized Set<Region> m1769a() {
        return m1766g().keySet();
    }

    protected void m1770a(long j) {
        this.f1737d.getFileStreamPath("com.altbeacon.beacon.service.monitoring_status_state").setLastModified(j);
    }

    public synchronized void m1771a(Beacon beacon) {
        Object obj = null;
        for (Region region : m1765b(beacon)) {
            C0870i c0870i = (C0870i) m1766g().get(region);
            if (c0870i != null && c0870i.m1795b()) {
                obj = 1;
                c0870i.m1794a().m1751a(this.f1737d, "monitoringData", new C0865d(c0870i.m1798e(), region).m1762c());
            }
            obj = obj;
        }
        if (obj != null) {
            m1776d();
        } else {
            m1770a(System.currentTimeMillis());
        }
    }

    public void m1772a(Region region, Integer num) {
        C0870i c0870i = (C0870i) m1766g().get(region);
        if (c0870i == null) {
            c0870i = m1774b(region);
        }
        if (num != null) {
            if (num.intValue() == 0) {
                c0870i.m1796c();
            }
            if (num.intValue() == 1) {
                c0870i.m1795b();
            }
        }
    }

    public synchronized int m1773b() {
        return m1769a().size();
    }

    public C0870i m1774b(Region region) {
        return m1764a(region, new C0862a(null));
    }

    public synchronized void m1775c() {
        Object obj = null;
        synchronized (this) {
            for (Region region : m1769a()) {
                Object obj2;
                C0870i a = m1768a(region);
                if (a.m1797d()) {
                    C0835d.m1657a(f1734b, "found a monitor that expired: %s", region);
                    a.m1794a().m1751a(this.f1737d, "monitoringData", new C0865d(a.m1798e(), region).m1762c());
                    obj2 = 1;
                } else {
                    obj2 = obj;
                }
                obj = obj2;
            }
            if (obj != null) {
                m1776d();
            } else {
                m1770a(System.currentTimeMillis());
            }
        }
    }

    protected void m1776d() {
        ObjectOutputStream objectOutputStream;
        IOException e;
        OutputStream outputStream;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        if (this.f1738e) {
            C0835d.m1657a(f1734b, "saveMonitoringStatusIfOn()", new Object[0]);
            if (m1766g().size() > 50) {
                C0835d.m1662c(f1734b, "Too many regions being monitored.  Will not persist region state", new Object[0]);
                this.f1737d.deleteFile("com.altbeacon.beacon.service.monitoring_status_state");
                return;
            }
            try {
                OutputStream openFileOutput = this.f1737d.openFileOutput("com.altbeacon.beacon.service.monitoring_status_state", 0);
                try {
                    objectOutputStream = new ObjectOutputStream(openFileOutput);
                    try {
                        objectOutputStream.writeObject(m1766g());
                        if (openFileOutput != null) {
                            try {
                                openFileOutput.close();
                            } catch (IOException e2) {
                            }
                        }
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                    } catch (IOException e4) {
                        e = e4;
                        outputStream = openFileOutput;
                        try {
                            C0835d.m1663d(f1734b, "Error while saving monitored region states to file. %s ", e.getMessage());
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e5) {
                                }
                            }
                            if (objectOutputStream == null) {
                                try {
                                    objectOutputStream.close();
                                } catch (IOException e6) {
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e7) {
                                }
                            }
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.close();
                                } catch (IOException e8) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        outputStream = openFileOutput;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (objectOutputStream != null) {
                            objectOutputStream.close();
                        }
                        throw th;
                    }
                } catch (IOException e9) {
                    e = e9;
                    objectOutputStream = null;
                    outputStream = openFileOutput;
                    C0835d.m1663d(f1734b, "Error while saving monitored region states to file. %s ", e.getMessage());
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (objectOutputStream == null) {
                        objectOutputStream.close();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    objectOutputStream = null;
                    outputStream = openFileOutput;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e10) {
                e = e10;
                objectOutputStream = null;
                C0835d.m1663d(f1734b, "Error while saving monitored region states to file. %s ", e.getMessage());
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream == null) {
                    objectOutputStream.close();
                }
            } catch (Throwable th5) {
                th = th5;
                objectOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                throw th;
            }
        }
    }

    protected long m1777e() {
        return this.f1737d.getFileStreamPath("com.altbeacon.beacon.service.monitoring_status_state").lastModified();
    }

    protected void m1778f() {
        FileInputStream openFileInput;
        ObjectInputStream objectInputStream;
        Exception e;
        FileInputStream fileInputStream;
        Throwable th;
        ObjectInputStream objectInputStream2 = null;
        try {
            openFileInput = this.f1737d.openFileInput("com.altbeacon.beacon.service.monitoring_status_state");
            try {
                objectInputStream = new ObjectInputStream(openFileInput);
                try {
                    Map map = (Map) objectInputStream.readObject();
                    C0835d.m1657a(f1734b, "Restored region monitoring state for " + map.size() + " regions.", new Object[0]);
                    for (Region region : map.keySet()) {
                        C0835d.m1657a(f1734b, "Region  " + region + " uniqueId: " + region.m1562a() + " state: " + map.get(region), new Object[0]);
                    }
                    for (C0870i c0870i : map.values()) {
                        if (c0870i.m1798e()) {
                            c0870i.m1795b();
                        }
                    }
                    this.f1736c.putAll(map);
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e2) {
                        }
                    }
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (IOException e4) {
                    e = e4;
                    objectInputStream2 = objectInputStream;
                    fileInputStream = openFileInput;
                    openFileInput = fileInputStream;
                    objectInputStream = objectInputStream2;
                    try {
                        if (e instanceof InvalidClassException) {
                            C0835d.m1657a(f1734b, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
                        } else {
                            C0835d.m1663d(f1734b, "Deserialization exception, message: %s", e.getMessage());
                        }
                        if (openFileInput != null) {
                            try {
                                openFileInput.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e6) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (openFileInput != null) {
                            try {
                                openFileInput.close();
                            } catch (IOException e7) {
                            }
                        }
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e8) {
                            }
                        }
                        throw th;
                    }
                } catch (ClassNotFoundException e9) {
                    e = e9;
                    if (e instanceof InvalidClassException) {
                        C0835d.m1663d(f1734b, "Deserialization exception, message: %s", e.getMessage());
                    } else {
                        C0835d.m1657a(f1734b, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
                    }
                    if (openFileInput != null) {
                        openFileInput.close();
                    }
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                } catch (ClassCastException e10) {
                    e = e10;
                    if (e instanceof InvalidClassException) {
                        C0835d.m1657a(f1734b, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
                    } else {
                        C0835d.m1663d(f1734b, "Deserialization exception, message: %s", e.getMessage());
                    }
                    if (openFileInput != null) {
                        openFileInput.close();
                    }
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                }
            } catch (IOException e11) {
                e = e11;
                fileInputStream = openFileInput;
                openFileInput = fileInputStream;
                objectInputStream = objectInputStream2;
                if (e instanceof InvalidClassException) {
                    C0835d.m1663d(f1734b, "Deserialization exception, message: %s", e.getMessage());
                } else {
                    C0835d.m1657a(f1734b, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (ClassNotFoundException e12) {
                e = e12;
                objectInputStream = null;
                if (e instanceof InvalidClassException) {
                    C0835d.m1657a(f1734b, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
                } else {
                    C0835d.m1663d(f1734b, "Deserialization exception, message: %s", e.getMessage());
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (ClassCastException e13) {
                e = e13;
                objectInputStream = null;
                if (e instanceof InvalidClassException) {
                    C0835d.m1663d(f1734b, "Deserialization exception, message: %s", e.getMessage());
                } else {
                    C0835d.m1657a(f1734b, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (Throwable th3) {
                th = th3;
                objectInputStream = null;
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
        } catch (IOException e14) {
            e = e14;
            fileInputStream = null;
            openFileInput = fileInputStream;
            objectInputStream = objectInputStream2;
            if (e instanceof InvalidClassException) {
                C0835d.m1657a(f1734b, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
            } else {
                C0835d.m1663d(f1734b, "Deserialization exception, message: %s", e.getMessage());
            }
            if (openFileInput != null) {
                openFileInput.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        } catch (ClassNotFoundException e15) {
            e = e15;
            objectInputStream = null;
            openFileInput = null;
            if (e instanceof InvalidClassException) {
                C0835d.m1663d(f1734b, "Deserialization exception, message: %s", e.getMessage());
            } else {
                C0835d.m1657a(f1734b, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
            }
            if (openFileInput != null) {
                openFileInput.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        } catch (ClassCastException e16) {
            e = e16;
            objectInputStream = null;
            openFileInput = null;
            if (e instanceof InvalidClassException) {
                C0835d.m1657a(f1734b, "Serialized Monitoring State has wrong class. Just ignoring saved state...", new Object[0]);
            } else {
                C0835d.m1663d(f1734b, "Deserialization exception, message: %s", e.getMessage());
            }
            if (openFileInput != null) {
                openFileInput.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        } catch (Throwable th4) {
            th = th4;
            objectInputStream = null;
            openFileInput = null;
            if (openFileInput != null) {
                openFileInput.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            throw th;
        }
    }
}
