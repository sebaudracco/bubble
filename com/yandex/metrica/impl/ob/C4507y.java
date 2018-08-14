package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4511p;
import com.yandex.metrica.impl.C4511p.C4510a;
import java.util.LinkedList;
import java.util.List;

public class C4507y extends aa<af> {
    private final ao f12499a;
    private final ae f12500b;
    private final am f12501c;
    private final ar f12502d;
    private final at f12503e;
    private final aw f12504f;
    private final bb f12505g;
    private final au f12506h;
    private final as f12507i;
    private final av f12508j;
    private final an f12509k;
    private final aq f12510l;
    private final ad f12511m;
    private final ac f12512n;
    private final ah f12513o;
    private final aj f12514p;

    public C4507y(C4503t c4503t) {
        this.f12499a = new ao(c4503t);
        this.f12500b = new ae(c4503t);
        this.f12501c = new am(c4503t);
        this.f12502d = new ar(c4503t);
        this.f12503e = new at(c4503t);
        this.f12504f = new aw(c4503t);
        this.f12505g = new bb(c4503t);
        this.f12506h = new au(c4503t);
        this.f12507i = new as(c4503t);
        this.f12508j = new av(c4503t);
        this.f12509k = new an(c4503t);
        this.f12510l = new aq(c4503t);
        this.f12511m = new ad(c4503t);
        this.f12512n = new ac(c4503t);
        this.f12513o = new ah(c4503t);
        this.f12514p = new aj(c4503t);
    }

    C4504x<af> mo7118a(int i) {
        List linkedList = new LinkedList();
        C4510a a = C4510a.m16187a(i);
        if (C4511p.m16203b(a)) {
            linkedList.add(this.f12508j);
        }
        if (C4511p.m16199a(a)) {
            linkedList.add(this.f12503e);
        }
        switch (a) {
            case EVENT_TYPE_ACTIVATION:
                linkedList.add(this.f12513o);
                linkedList.add(this.f12504f);
                break;
            case EVENT_TYPE_START:
                linkedList.add(this.f12499a);
                break;
            case EVENT_TYPE_REGULAR:
                linkedList.add(this.f12499a);
                linkedList.add(this.f12502d);
                break;
            case EVENT_TYPE_EXCEPTION_USER:
            case EVENT_TYPE_REFERRER_DEPRECATED:
            case EVENT_TYPE_STATBOX:
            case EVENT_TYPE_CUSTOM_EVENT:
            case EVENT_TYPE_APP_OPEN:
                linkedList.add(this.f12502d);
                break;
            case EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS:
                linkedList.add(this.f12504f);
                linkedList.add(this.f12499a);
                break;
            case EVENT_TYPE_PURGE_BUFFER:
                linkedList.add(this.f12501c);
                break;
            case EVENT_TYPE_NATIVE_CRASH:
                linkedList.add(this.f12514p);
                break;
            case EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED:
            case EVENT_TYPE_EXCEPTION_UNHANDLED:
                linkedList.add(this.f12501c);
                linkedList.add(this.f12502d);
                linkedList.add(this.f12500b);
                linkedList.add(this.f12505g);
                break;
            case EVENT_TYPE_IDENTITY:
                linkedList.add(this.f12504f);
                linkedList.add(this.f12499a);
                break;
            case EVENT_TYPE_SET_USER_INFO:
                linkedList.add(this.f12506h);
                break;
            case EVENT_TYPE_REPORT_USER_INFO:
                linkedList.add(this.f12507i);
                break;
            case EVENT_TYPE_REFERRER_RECEIVED:
                linkedList.add(this.f12510l);
                break;
            case EVENT_TYPE_APP_ENVIRONMENT_UPDATED:
                linkedList.add(this.f12511m);
                break;
            case EVENT_TYPE_APP_ENVIRONMENT_CLEARED:
                linkedList.add(this.f12512n);
                break;
        }
        if (C4511p.m16207c(a)) {
            linkedList.add(this.f12509k);
        }
        return new C4505w(linkedList);
    }
}
