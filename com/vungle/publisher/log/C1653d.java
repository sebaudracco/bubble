package com.vungle.publisher.log;

import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.zk;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class C1653d extends Formatter {
    SimpleDateFormat f3080a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    @Inject
    C1614r f3081b;

    public String format(LogRecord record) {
        String str = "";
        Object[] parameters = record.getParameters();
        if (!(parameters == null || parameters.length <= 0 || parameters[0] == null)) {
            str = (String) parameters[0];
        }
        return zk.a(";", new String[]{this.f3080a.format(new Date(record.getMillis())), TimeZone.getDefault().getID(), record.getLoggerName(), m4346a(record.getLevel()), str, this.f3081b.m3968r(), record.getMessage()}) + "\n";
    }

    private String m4346a(Level level) {
        String name = level.getName();
        Object obj = -1;
        switch (name.hashCode()) {
            case -1852393868:
                if (name.equals("SEVERE")) {
                    obj = 5;
                    break;
                }
                break;
            case 2158010:
                if (name.equals("FINE")) {
                    obj = null;
                    break;
                }
                break;
            case 2251950:
                if (name.equals("INFO")) {
                    obj = 3;
                    break;
                }
                break;
            case 66898392:
                if (name.equals("FINER")) {
                    obj = 1;
                    break;
                }
                break;
            case 1842428796:
                if (name.equals("WARNING")) {
                    obj = 4;
                    break;
                }
                break;
            case 2073850267:
                if (name.equals("FINEST")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
            case 1:
            case 2:
                return "debug";
            case 3:
                return "info";
            case 4:
                return "warn";
            case 5:
                return "error";
            default:
                return "debug";
        }
    }
}
