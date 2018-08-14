package com.oneaudience.sdk.p135c.p137b;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class C3827d {
    private static final SimpleDateFormat f9189a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String m12235a(long j) {
        return f9189a.format(new Date(j));
    }

    public static String m12236b(long j) {
        long toHours = TimeUnit.MILLISECONDS.toHours(j);
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(j - TimeUnit.HOURS.toMillis(toHours));
        long toMillis = TimeUnit.MILLISECONDS.toMillis(((j - TimeUnit.HOURS.toMillis(toHours)) - TimeUnit.MINUTES.toMillis(toMinutes)) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds((j - TimeUnit.HOURS.toMillis(toHours)) - TimeUnit.MINUTES.toMillis(toMinutes))));
        return String.format("%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(toHours), Long.valueOf(toMinutes), Long.valueOf(r4), Long.valueOf(toMillis)});
    }
}
