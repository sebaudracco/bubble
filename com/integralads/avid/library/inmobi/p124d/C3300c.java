package com.integralads.avid.library.inmobi.p124d;

import android.view.View;
import com.integralads.avid.library.inmobi.p121a.C3286a;
import com.integralads.avid.library.inmobi.p126f.C3315d;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AvidSceenProcessor */
public class C3300c implements C3297e {
    public JSONObject mo6322a(View view) {
        return C3315d.m11294a(0, 0, 0, 0);
    }

    public List<View> mo6324b(View view) {
        return C3286a.m11179a().m11184b();
    }

    public C3297e mo6325c(View view) {
        return C3299b.m11221a().m11223c();
    }

    public void mo6323a(View view, JSONObject jSONObject) {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("childViews");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                if (optJSONObject != null) {
                    int optInt = optJSONObject.optInt("x");
                    int optInt2 = optJSONObject.optInt("y");
                    int optInt3 = optJSONObject.optInt("width");
                    int optInt4 = optJSONObject.optInt("height");
                    i2 = Math.max(i2, optInt + optInt3);
                    i = Math.max(i, optInt4 + optInt2);
                }
            }
            try {
                jSONObject.put("width", i2);
                jSONObject.put("height", i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
