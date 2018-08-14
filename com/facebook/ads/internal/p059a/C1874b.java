package com.facebook.ads.internal.p059a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.p069m.C2012c;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public class C1874b {
    private static final String f4127a = C1874b.class.getSimpleName();

    @Nullable
    public static C1873a m5632a(Context context, C2012c c2012c, String str, Uri uri, Map<String, String> map) {
        if (uri == null) {
            return null;
        }
        String authority = uri.getAuthority();
        String queryParameter = uri.getQueryParameter(BaseVideoPlayerActivity.VIDEO_URL);
        if (!TextUtils.isEmpty(uri.getQueryParameter("data"))) {
            try {
                JSONObject jSONObject = new JSONObject(uri.getQueryParameter("data"));
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    map.put(str2, jSONObject.getString(str2));
                }
            } catch (Throwable e) {
                Log.w(f4127a, "Unable to parse json data in AdActionFactory.", e);
            }
        }
        Object obj = -1;
        switch (authority.hashCode()) {
            case -1458789996:
                if (authority.equals("passthrough")) {
                    obj = 2;
                    break;
                }
                break;
            case 109770977:
                if (authority.equals("store")) {
                    obj = null;
                    break;
                }
                break;
            case 1546100943:
                if (authority.equals("open_link")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return queryParameter != null ? null : new C1878e(context, c2012c, str, uri, map);
            case 1:
                return new C1880g(context, c2012c, str, uri, map);
            case 2:
                return new C1881h(context, c2012c, str, uri, map);
            default:
                return new C1882i(context, c2012c, str, uri);
        }
    }

    public static boolean m5633a(String str) {
        return "store".equalsIgnoreCase(str) || "open_link".equalsIgnoreCase(str);
    }
}
