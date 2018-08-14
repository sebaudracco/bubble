package com.fyber.ads.videos.p093a;

import android.text.TextUtils;
import com.appnext.base.p023b.C1042c;
import com.fyber.utils.StringUtils;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JavascriptProtocolHelper */
public final class C2447a {
    static String m7769a(String... strArr) {
        return C2447a.m7768a(false, strArr);
    }

    static String m7768a(boolean z, String... strArr) {
        int i = z ? 3 : 2;
        int length = strArr.length;
        if (length % i != 0) {
            return String.format("tracking_params:{%s}", new Object[]{""});
        }
        Iterable arrayList = new ArrayList(length / i);
        for (int i2 = 0; i2 < length; i2 += i) {
            String str = strArr[i2];
            String str2 = strArr[i2 + 1];
            if (z || !StringUtils.nullOrEmpty(str2)) {
                String str3 = str2;
            } else {
                Object obj = "";
            }
            str2 = z ? strArr[i2 + 2] : "'";
            if (StringUtils.notNullNorEmpty(str)) {
                arrayList.add(String.format("%1$s:%3$s%2$s%3$s", new Object[]{str, obj, str2}));
            }
        }
        return String.format("tracking_params:{%s}", new Object[]{TextUtils.join(", ", arrayList)});
    }

    static String m7767a(String str, String str2, String str3, String str4, JSONObject jSONObject, Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("cid", str3).put(C1042c.jL, str4).put("data", jSONObject).put("params", new JSONObject(map));
        } catch (JSONException e) {
        }
        stringBuilder.append(BridgeUtil.JAVASCRIPT_STR).append(str).append(".trigger(\"").append(str2).append("\",").append(jSONObject2.toString()).append(")");
        return stringBuilder.toString();
    }
}
