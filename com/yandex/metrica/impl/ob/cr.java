package com.yandex.metrica.impl.ob;

import android.net.Uri;
import android.text.TextUtils;

public class cr extends cq {
    public boolean mo7069b() {
        return true;
    }

    public cr(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if ("http".equals(parse.getScheme())) {
                str = parse.buildUpon().scheme("https").build().toString();
            }
        }
        super(str);
    }
}
