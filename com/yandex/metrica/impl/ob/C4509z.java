package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4511p.C4510a;
import java.util.LinkedList;

public class C4509z extends aa<af> {
    private final bb f12516a;
    private final ba f12517b;
    private final az f12518c;
    private final ap f12519d;
    private final ax f12520e;
    private final ak f12521f;

    public C4509z(C4503t c4503t) {
        this.f12516a = new bb(c4503t);
        this.f12517b = new ba(c4503t);
        this.f12518c = new az(c4503t);
        this.f12519d = new ap(c4503t);
        this.f12520e = new ax(c4503t);
        this.f12521f = new ak(c4503t);
    }

    C4504x<af> mo7118a(int i) {
        LinkedList linkedList = new LinkedList();
        switch (C4510a.m16187a(i)) {
            case EVENT_TYPE_ACTIVITY_START_DEPRECATED:
                linkedList.add(this.f12520e);
                break;
            case EVENT_TYPE_START:
                linkedList.add(this.f12520e);
                linkedList.add(this.f12519d);
                break;
            case EVENT_TYPE_SESSION_START_MANUALLY:
                linkedList.add(this.f12516a);
                linkedList.add(this.f12517b);
                linkedList.add(this.f12518c);
                break;
            case EVENT_TYPE_INIT:
            case EVENT_TYPE_INIT_BACKGROUND:
                linkedList.add(this.f12519d);
                break;
            case EVENT_TYPE_ACTIVITY_END:
                linkedList.add(this.f12521f);
                break;
        }
        return new C4505w(linkedList);
    }
}
