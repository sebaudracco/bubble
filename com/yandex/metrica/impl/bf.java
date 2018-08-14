package com.yandex.metrica.impl;

import android.os.Bundle;
import android.os.Parcelable;
import com.yandex.metrica.CounterConfiguration;

public class bf extends aw {
    private final String f11779e;

    public bf(String str) {
        this.f11779e = str;
    }

    Bundle mo7025c() {
        Bundle c = super.mo7025c();
        Parcelable counterConfiguration = new CounterConfiguration(m14714b());
        counterConfiguration.m14254b(this.f11779e);
        c.putParcelable("COUNTER_MIGRATION_CFG_OBJ", counterConfiguration);
        return c;
    }
}
