package com.yandex.metrica.impl.ob;

public enum bl {
    f11933a(0),
    BACKGROUND(1);
    
    private final int f11936c;

    private bl(int i) {
        this.f11936c = i;
    }

    public int m15299a() {
        return this.f11936c;
    }

    public static bl m15298a(Integer num) {
        bl blVar = f11933a;
        if (num == null) {
            return blVar;
        }
        switch (num.intValue()) {
            case 0:
                return f11933a;
            case 1:
                return BACKGROUND;
            default:
                return blVar;
        }
    }
}
