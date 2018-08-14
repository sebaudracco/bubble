package com.inmobi.commons.core.utilities.uid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class ImIdShareBroadCastReceiver extends BroadcastReceiver {
    private static final String f7828a = ImIdShareBroadCastReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                if (intent.getAction().equals("com.inmobi.share.id")) {
                    C3166b c3166b = new C3166b(context);
                    String string = intent.getExtras().getString(C1404b.f2147y);
                    String string2 = intent.getExtras().getString("imid");
                    String f = c3166b.m10512f();
                    long e = c3166b.m10511e();
                    long j = intent.getExtras().getLong("imidts");
                    if (f != null && string != null) {
                        if (j < e) {
                            c3166b.m10508c(string2);
                            c3166b.m10502a(j);
                        }
                        JSONObject jSONObject = new JSONObject(f);
                        m10496a(jSONObject);
                        if (jSONObject.has(string)) {
                            jSONObject.put(string, System.currentTimeMillis());
                            c3166b.m10510d(jSONObject.toString());
                            return;
                        }
                        jSONObject.put(string, System.currentTimeMillis());
                        c3166b.m10510d(jSONObject.toString());
                        string = C3168c.m10513a().m10520a(jSONObject);
                        Intent intent2 = new Intent();
                        intent2.setAction("com.inmobi.share.id");
                        intent2.putExtra("imid", c3166b.m10509d());
                        intent2.putExtra("appendedid", string);
                        intent2.putExtra("imidts", c3166b.m10511e());
                        intent2.putExtra(C1404b.f2147y, c3166b.m10507c());
                        context.sendBroadcast(intent2);
                    }
                }
            } catch (Exception e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7828a, "SDK encountered unexpected error in ImIdShareBroadcastReceiver.onReceive method; " + e2.getMessage());
            }
        }
    }

    private void m10496a(JSONObject jSONObject) throws JSONException {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            if (System.currentTimeMillis() - jSONObject.getLong((String) keys.next()) > C3169d.f7839a) {
                keys.remove();
            }
        }
    }
}
