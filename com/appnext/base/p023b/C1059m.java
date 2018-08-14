package com.appnext.base.p023b;

import java.util.Calendar;
import java.util.Date;

public class C1059m {
    public static Date m2189d(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.getTime();
    }
}
