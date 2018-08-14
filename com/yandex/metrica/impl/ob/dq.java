package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import com.coremedia.iso.boxes.UserBox;
import org.json.JSONException;
import org.json.JSONObject;

public class dq {
    private final JSONObject f12256a = new JSONObject();

    public void m15779a(String str) {
        m15777a(UserBox.TYPE, str);
    }

    public void m15780b(String str) {
        m15777a("device_id", str);
    }

    public void m15781c(String str) {
        m15777a("google_aid", str);
    }

    public void m15782d(String str) {
        m15777a("android_id", str);
    }

    private void m15777a(String str, String str2) {
        Object obj = (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? 1 : null;
        if (obj == null) {
            try {
                this.f12256a.put(str, str2);
            } catch (JSONException e) {
            }
        }
    }

    public String m15778a() throws JSONException {
        return this.f12256a.toString();
    }
}
