package com.elephant.data.p044e.p045a;

public enum C1780d {
    SHOW(1),
    CLICK(2),
    OPEN(5),
    LOGIN(9),
    DROPOUT(10),
    REGISTERED(11),
    INSTALLAPP(12),
    UPDATEAPP(13),
    UNINSTALLAPP(14);
    
    private int f3729j;

    static {
        SHOW = new C1780d("SHOW", 0, 1);
        CLICK = new C1780d("CLICK", 1, 2);
        OPEN = new C1780d("OPEN", 2, 5);
        LOGIN = new C1780d("LOGIN", 3, 9);
        DROPOUT = new C1780d("DROPOUT", 4, 10);
        REGISTERED = new C1780d("REGISTERED", 5, 11);
        INSTALLAPP = new C1780d("INSTALLAPP", 6, 12);
        UPDATEAPP = new C1780d("UPDATEAPP", 7, 13);
        UNINSTALLAPP = new C1780d("UNINSTALLAPP", 8, 14);
        C1780d[] c1780dArr = new C1780d[]{SHOW, CLICK, OPEN, LOGIN, DROPOUT, REGISTERED, INSTALLAPP, UPDATEAPP, UNINSTALLAPP};
    }

    private C1780d(int i) {
        this.f3729j = i;
    }

    public final int m5130a() {
        return this.f3729j;
    }
}
