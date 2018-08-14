package com.appnext.base.operations.imp;

import android.os.Build.VERSION;
import android.os.Bundle;
import com.appnext.base.C1033a;
import com.appnext.base.C1061b;
import com.appnext.base.operations.C1067e;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p019a.p022c.C1028c;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.p023b.C1058l;
import com.appnext.core.C1128g;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class cdm extends C1067e {
    private final String TAG = "cdm";
    private final String ha = "[ { \"status\": \"on\", \"sample\":\"1\", \"sample_type\":\"hour\", \"cycle\":\"1\", \"cycle_type\":\"interval\", \"exact\":\"false\", \"key\":\"cdm\" } ]";

    public cdm(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
        C1048i.cy().init(C1043d.getContext());
    }

    public boolean hasPermission() {
        return true;
    }

    protected List<C1020b> getData() {
        if (!C1057k.m2183q(C1043d.getContext())) {
            String a;
            try {
                a = C1128g.m2336a("http://cdn.appnext.com/tools/services/4.6.7/config.json?packageId=" + C1043d.getContext().getPackageName() + "&dosv=" + VERSION.SDK_INT + "&lvid=" + "4.6.8", null);
            } catch (Throwable e) {
                int responseCode = e.responseCode();
                if (responseCode == HttpStatus.SC_BAD_REQUEST || responseCode == HttpStatus.SC_UNAUTHORIZED || responseCode == 402 || responseCode == HttpStatus.SC_FORBIDDEN || responseCode == 404 || responseCode == 405 || responseCode == HttpStatus.SC_INTERNAL_SERVER_ERROR || responseCode == HttpStatus.SC_NOT_IMPLEMENTED || responseCode == HttpStatus.SC_SERVICE_UNAVAILABLE) {
                    C1061b.m2191a(e);
                }
                a = "[ { \"status\": \"on\", \"sample\":\"1\", \"sample_type\":\"hour\", \"cycle\":\"1\", \"cycle_type\":\"interval\", \"exact\":\"false\", \"key\":\"cdm\" } ]";
            } catch (Throwable e2) {
                C1058l.m2187n("cdm", e2.toString());
                a = "[ { \"status\": \"on\", \"sample\":\"1\", \"sample_type\":\"hour\", \"cycle\":\"1\", \"cycle_type\":\"interval\", \"exact\":\"false\", \"key\":\"cdm\" } ]";
            }
            try {
                List bm = C1017a.aM().aR().bm();
                if (bm != null) {
                    C1033a.aI().m2113a(bm);
                }
                aj(a);
                C1033a.aI().aJ();
            } catch (Throwable e22) {
                C1061b.m2191a(e22);
            }
        }
        return null;
    }

    protected boolean bz() {
        return false;
    }

    private void aj(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String optString = jSONObject.optString(C1028c.gv);
                C1058l.m2184k(" *** new *** ", optString);
                jSONObject.put(C1042c.jO, optString);
            }
            C1017a.aM().aR().delete();
            C1017a.aM().aR().m2101b(jSONArray);
        } catch (Throwable th) {
            C1058l.m2187n("cdm", th.getMessage());
        }
    }
}
