package com.yandex.metrica.impl.ob;

import android.content.Context;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.be.C4359a;
import com.yandex.metrica.impl.bi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ck {
    private ci f12113a;

    class C44281 extends HashMap<String, Object> {
        final /* synthetic */ StringBuilder f12108a;

        C44281(StringBuilder stringBuilder) {
            this.f12108a = stringBuilder;
            put("data", this.f12108a.toString());
        }
    }

    class C44292 extends HashMap<String, Object> {
        final /* synthetic */ StringBuilder f12109a;

        C44292(StringBuilder stringBuilder) {
            this.f12109a = stringBuilder;
            put("error", this.f12109a.toString());
        }
    }

    class C44313 extends HashMap<String, Object> {
        final /* synthetic */ String f12111a;
        final /* synthetic */ int f12112b;

        class C44301 extends HashMap<String, Object> {
            final /* synthetic */ C44313 f12110a;

            C44301(C44313 c44313) {
                this.f12110a = c44313;
                put("candidates_count", Integer.valueOf(this.f12110a.f12112b));
            }
        }

        C44313(String str, int i) {
            this.f12111a = str;
            this.f12112b = i;
            put(this.f12111a, new C44301(this));
        }
    }

    public ck(ci ciVar) {
        this.f12113a = ciVar;
    }

    public String m15593a(Context context) {
        return m15595b(context);
    }

    String m15595b(Context context) {
        C4359a c4359a;
        String str;
        cj cjVar = new cj(context);
        List<C4359a> c = m15596c(context);
        List<ce> arrayList = new ArrayList(c.size());
        LinkedList linkedList = new LinkedList();
        for (C4359a c4359a2 : c) {
            if (be.m14886a(c4359a2.f11777d) < 29) {
                linkedList.add(c4359a2);
            } else {
                Object obj;
                ch a;
                if (this.f12113a.m15583e()) {
                    str = c4359a2.f11777d.applicationInfo.packageName;
                    a = this.f12113a.m15574a(context, str);
                    ch b = this.f12113a.m15576b(context, str);
                    if (a == null && b == null) {
                        obj = null;
                    } else {
                        cg cgVar = new cg(c4359a2, b, a);
                    }
                } else {
                    a = this.f12113a.m15574a(context, c4359a2.f11777d.applicationInfo.packageName);
                    if (a == null) {
                        obj = null;
                    } else if (bi.m14957a(a.m15560c())) {
                        obj = null;
                    } else {
                        ce ceVar = new ce(c4359a2, a);
                    }
                }
                if (obj != null) {
                    arrayList.add(obj);
                }
            }
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            c4359a2 = (C4359a) it.next();
            String f = this.f12113a.m15585f(context, c4359a2.f11777d.packageName);
            if (!bi.m14957a(f)) {
                arrayList.add(new ce(c4359a2, new ch(f, null, -1)));
            }
        }
        str = "";
        if (arrayList.isEmpty()) {
            return str;
        }
        Map hashMap = new HashMap();
        for (ce ceVar2 : arrayList) {
            String a2 = ceVar2.mo7062a();
            cf cfVar = (cf) hashMap.get(a2);
            if (cfVar == null) {
                cfVar = new cf(a2, cjVar);
                hashMap.put(a2, cfVar);
            }
            cfVar.m15546a(ceVar2);
        }
        if (hashMap.size() == 1) {
            Iterator it2 = hashMap.values().iterator();
            if (it2.hasNext()) {
                return ((cf) it2.next()).m15549c();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Smth wrong when iterate through initial candidates list").append('\n');
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", new C44292(stringBuilder));
            return str;
        }
        m15592c(context, arrayList);
        return m15588a(context, hashMap);
    }

    private String m15588a(Context context, Map<String, cf> map) {
        m15590a(context, "method_carriers_count", map.size());
        List arrayList = new ArrayList();
        int i = 0;
        for (cf cfVar : map.values()) {
            int b = cfVar.m15548b();
            if (b > i) {
                arrayList.clear();
                arrayList.add(cfVar);
                i = b;
            } else if (b == i) {
                arrayList.add(cfVar);
            }
        }
        if (arrayList.size() == 1) {
            return ((cf) arrayList.get(0)).m15549c();
        }
        String a;
        if (((cf) arrayList.get(0)).m15548b() == 1) {
            a = m15587a(context, (ArrayList) arrayList);
        } else {
            a = null;
        }
        if (a != null) {
            return a;
        }
        List a2 = m15589a(arrayList);
        if (a2 == null) {
            return m15594a(context, arrayList);
        }
        return m15594a(context, a2);
    }

    private static List<cf> m15589a(List<cf> list) {
        List<cf> arrayList = new ArrayList();
        for (cf cfVar : list) {
            if (!cfVar.m15547a()) {
                arrayList.add(cfVar);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    private static String m15587a(Context context, ArrayList<cf> arrayList) {
        String packageName = context.getPackageName();
        Iterator it = arrayList.iterator();
        cf cfVar = null;
        cf cfVar2 = null;
        while (it.hasNext()) {
            cf cfVar3 = (cf) it.next();
            if (packageName.equals(((ce) cfVar3.m15550d().get(0)).mo7064c().f11778e)) {
                cfVar = cfVar3;
            } else {
                cfVar2 = cfVar3;
            }
        }
        if (cfVar == null) {
            return null;
        }
        if (!cfVar2.m15547a()) {
            return cfVar2.m15549c();
        }
        if (cfVar.m15547a()) {
            return cfVar2.m15549c();
        }
        return cfVar.m15549c();
    }

    String m15594a(Context context, List<cf> list) {
        m15590a(context, "method_first_installed", list.size());
        List arrayList = new ArrayList();
        Long valueOf = Long.valueOf(Long.MAX_VALUE);
        Long l = valueOf;
        for (cf cfVar : list) {
            Long e = cfVar.m15551e();
            int compareTo = e.compareTo(l);
            if (compareTo < 0) {
                arrayList.clear();
                arrayList.add(cfVar);
                l = e;
            } else if (compareTo == 0) {
                arrayList.add(cfVar);
            }
        }
        if (arrayList.size() == 1) {
            return ((cf) arrayList.get(0)).m15549c();
        }
        return m15591b(context, arrayList);
    }

    private static String m15591b(Context context, List<cf> list) {
        m15590a(context, "method_device_id_comparing", list.size());
        String str = "";
        for (cf cfVar : list) {
            String c;
            if (cfVar.m15549c().compareTo(str) > 0) {
                c = cfVar.m15549c();
            } else {
                c = str;
            }
            str = c;
        }
        return str;
    }

    private static void m15592c(Context context, List<ce> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ce ceVar : list) {
            stringBuilder.append(ceVar.mo7064c().f11777d.packageName);
            stringBuilder.append(" ");
            stringBuilder.append(ceVar.toString());
            stringBuilder.append('\n');
        }
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", new C44281(stringBuilder));
    }

    List<C4359a> m15596c(Context context) {
        return be.m14891b(context);
    }

    private static void m15590a(Context context, String str, int i) {
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", new C44313(str, i));
    }
}
