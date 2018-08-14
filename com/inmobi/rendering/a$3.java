package com.inmobi.rendering;

import android.app.DownloadManager.Query;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: JavaScriptBridge */
class a$3 extends BroadcastReceiver {
    final /* synthetic */ String f7968a;
    final /* synthetic */ a f7969b;

    a$3(a aVar, String str) {
        this.f7969b = aVar;
        this.f7968a = str;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
                    long longExtra = intent.getLongExtra("extra_download_id", 0);
                    Query query = new Query();
                    query.setFilterById(new long[]{longExtra});
                    Cursor query2 = a.b(this.f7969b).query(query);
                    if (query2.moveToFirst()) {
                        int columnIndex = query2.getColumnIndex("status");
                        if (16 == query2.getInt(columnIndex)) {
                            a.a(this.f7969b).m10631a(this.f7968a, "File failed to download", "storePicture");
                        } else if (8 == query2.getInt(columnIndex)) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "Download completed");
                        } else if (1 == query2.getInt(columnIndex)) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "Download queued");
                        } else if (2 == query2.getInt(columnIndex)) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "Download ongoing");
                        }
                    }
                    query2.close();
                }
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, a.a(), "SDK encountered unexpected error while processing android.intent.action.DOWNLOAD_COMPLETE intent; " + e.getMessage());
            }
        }
    }
}
