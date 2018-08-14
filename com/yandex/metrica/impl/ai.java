package com.yandex.metrica.impl;

import android.content.Context;
import android.util.SparseArray;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.ob.dg;

public abstract class ai {

    interface C4320a {
        void mo7021a(Context context);
    }

    protected abstract int mo7022a(dg dgVar);

    abstract SparseArray<C4320a> mo7023a();

    protected abstract void mo7024a(dg dgVar, int i);

    public void m14557a(Context context) {
        dg dgVar = new dg(context);
        int a = mo7022a(dgVar);
        int b = m14559b();
        if (a < b) {
            SparseArray a2 = mo7023a();
            for (int i = a; i <= b; i++) {
                C4320a c4320a = (C4320a) a2.get(i);
                if (c4320a != null) {
                    c4320a.mo7021a(context);
                }
            }
            mo7024a(dgVar, b);
            dgVar.m15646k();
        }
    }

    int m14559b() {
        return YandexMetrica.getLibraryApiLevel();
    }
}
