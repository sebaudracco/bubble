package com.vungle.publisher;

import com.vungle.publisher.eb.b;

/* compiled from: vungle */
abstract class eb$b$a {
    final /* synthetic */ b f9972c;

    abstract int mo6934a(eb<?, ?, ?> ebVar);

    private eb$b$a(b bVar) {
        this.f9972c = bVar;
    }

    int m13087a() {
        int i = 0;
        eb[] a = b.a(this.f9972c);
        int i2 = 0;
        while (i < a.length) {
            i2 += mo6934a(a[i]);
            i++;
        }
        return i2;
    }
}
