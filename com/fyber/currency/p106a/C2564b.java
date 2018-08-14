package com.fyber.currency.p106a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.fyber.Fyber;
import com.fyber.currency.VirtualCurrencyResponse;
import com.fyber.p086a.C2408a;
import com.fyber.utils.StringUtils;
import com.github.lzyzsd.jsbridge.BridgeUtil;

/* compiled from: VirtualCurrencyPrefManager */
public class C2564b {
    private static C2564b f6437c;
    private final C2408a f6438a = Fyber.getConfigs().m7608i();
    private final SharedPreferences f6439b;

    public static C2564b m8194a(Context context) {
        if (f6437c == null) {
            synchronized (C2564b.class) {
                if (f6437c == null) {
                    f6437c = new C2564b(context);
                }
            }
        }
        return f6437c;
    }

    private C2564b(Context context) {
        this.f6439b = context.getSharedPreferences("FyberPreferences", 0);
    }

    private String m8195c(String str) {
        if (StringUtils.nullOrEmpty(str)) {
            str = "";
        }
        return "STATE_LATEST_CURRENCY_TRANSACTION_ID_" + this.f6438a.m7591a() + BridgeUtil.UNDERLINE_STR + this.f6438a.m7593b() + "_STATE_LATEST_TRANSACTION_CURRENCY_ID_" + str;
    }

    public final String m8196a() {
        return this.f6439b.getString("DEFAULT_CURRENCY_ID_KEY_" + this.f6438a.m7591a(), "");
    }

    public final void m8198a(String str) {
        this.f6439b.edit().putString("DEFAULT_CURRENCY_ID_KEY_" + this.f6438a.m7591a(), str).commit();
    }

    public final String m8199b(String str) {
        if (StringUtils.nullOrEmpty(str)) {
            str = m8196a();
        }
        if (StringUtils.nullOrEmpty(str)) {
            return "NO_TRANSACTION";
        }
        return this.f6439b.getString(m8195c(str), "NO_TRANSACTION");
    }

    public final void m8197a(VirtualCurrencyResponse virtualCurrencyResponse) {
        String latestTransactionId = virtualCurrencyResponse.getLatestTransactionId();
        if (StringUtils.notNullNorEmpty(latestTransactionId) && !latestTransactionId.equals("NO_TRANSACTION")) {
            Editor edit = this.f6439b.edit();
            edit.putString(m8195c(virtualCurrencyResponse.getCurrencyId()), latestTransactionId);
            edit.commit();
        }
        if (virtualCurrencyResponse.isDefault()) {
            m8198a(virtualCurrencyResponse.getCurrencyId());
        }
    }
}
