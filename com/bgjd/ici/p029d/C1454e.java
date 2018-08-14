package com.bgjd.ici.p029d;

import android.database.Cursor;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.ac;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C1454e {
    private String f2240a = "";
    private int f2241b = 0;
    private long f2242c = 0;
    private long f2243d = 0;
    private String f2244e = "";
    private int f2245f = 0;

    public C1454e(Cursor cursor) {
        try {
            this.f2245f = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            this.f2240a = cursor.getString(cursor.getColumnIndexOrThrow("url"));
            this.f2241b = cursor.getInt(cursor.getColumnIndexOrThrow(C1404b.as));
            this.f2242c = cursor.getLong(cursor.getColumnIndexOrThrow(C1404b.at));
            this.f2243d = cursor.getLong(cursor.getColumnIndexOrThrow("date"));
        } catch (Exception e) {
        }
    }

    public int m2993a() {
        return this.f2241b;
    }

    public void m2994a(String str) {
        this.f2244e = str;
    }

    public String m2995b() {
        String host;
        int i;
        String str = "";
        try {
            host = new URI(this.f2240a).getHost();
            i = 0;
        } catch (URISyntaxException e) {
            host = str;
            i = 1;
        } catch (NullPointerException e2) {
            host = str;
            i = 1;
        } catch (Exception e3) {
            host = str;
            i = 1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (i == 0) {
            Matcher matcher = Pattern.compile("\\?[^\"]*", 2).matcher(this.f2240a);
            CharSequence charSequence = null;
            while (matcher.find()) {
                charSequence = this.f2240a.substring(matcher.start(0), matcher.end(0));
            }
            if (charSequence != null) {
                str = this.f2240a.replace(charSequence, "");
            } else {
                str = this.f2240a;
            }
            if (this.f2242c == 0 && this.f2243d > 0) {
                this.f2242c = this.f2243d;
            }
            if (host == null) {
                host = "www.null-value.com";
            }
            if (str == null) {
                str = "http://www.null-value.com";
            }
            stringBuilder.append(String.format("INSERT OR IGNORE INTO %s (brw_id,browsername,name,url,bookmark,created,date,status) VALUES ", new Object[]{C1403a.f2092v}));
            stringBuilder.append(String.format("( %d,'%s','%s','%s',%d,%d,%d,0)", new Object[]{Integer.valueOf(this.f2245f), this.f2244e, ac.m2876e(host), ac.m2879f(str), Integer.valueOf(this.f2241b), Long.valueOf(this.f2242c), Long.valueOf(this.f2243d), Integer.valueOf(0)}));
        }
        return stringBuilder.toString();
    }
}
