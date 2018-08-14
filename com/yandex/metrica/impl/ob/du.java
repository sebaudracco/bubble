package com.yandex.metrica.impl.ob;

import android.os.Bundle;

public enum du {
    f12264a(0),
    NETWORK(1),
    PARSE(2);
    
    private int f12268d;

    private du(int i) {
        this.f12268d = i;
    }

    public int m15794a() {
        return this.f12268d;
    }

    public Bundle m15795a(Bundle bundle) {
        bundle.putInt("startup_error_key_code", m15794a());
        return bundle;
    }

    public static du m15793b(Bundle bundle) {
        int i = bundle.getInt("startup_error_key_code");
        du duVar = f12264a;
        switch (i) {
            case 1:
                return NETWORK;
            case 2:
                return PARSE;
            default:
                return duVar;
        }
    }
}
