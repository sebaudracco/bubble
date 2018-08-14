package com.fyber.currency.p106a;

import com.fyber.currency.VirtualCurrencyErrorResponse;
import com.fyber.currency.VirtualCurrencyErrorResponse.ErrorType;
import com.fyber.p094b.C2516n.C2515a;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* compiled from: VirtualCurrencyCache */
public final class C2563a {
    public static final VirtualCurrencyErrorResponse f6435a = new VirtualCurrencyErrorResponse(ErrorType.ERROR_OTHER, "", "Unknown error");
    private Map<String, C2562a> f6436b = new HashMap();

    /* compiled from: VirtualCurrencyCache */
    private class C2562a {
        final /* synthetic */ C2563a f6432a;
        private Calendar f6433b;
        private C2515a f6434c;

        private C2562a(C2563a c2563a) {
            this.f6432a = c2563a;
        }
    }

    public final C2515a m8192a(String str, String str2) {
        Calendar instance = Calendar.getInstance();
        C2562a c2562a = (C2562a) this.f6436b.get(C2563a.m8191b(str, str2));
        if (c2562a == null) {
            c2562a = new C2562a();
            c2562a.f6433b = instance;
            this.f6436b.put(C2563a.m8191b(str, str2), c2562a);
        }
        if (instance.before(c2562a.f6433b)) {
            c2562a = (C2562a) this.f6436b.get(C2563a.m8191b(str, str2));
            if (c2562a == null) {
                c2562a = new C2562a();
                c2562a.f6433b = Calendar.getInstance();
                this.f6436b.put(C2563a.m8191b(str, str2), c2562a);
            }
            C2515a b = c2562a.f6434c;
            if (b == null) {
                return f6435a;
            }
            FyberLogger.m8448d("VCSCache", "The VCS was queried less than 15s ago.Replying with cached response");
            return b;
        }
        instance.add(13, 15);
        c2562a = (C2562a) this.f6436b.get(C2563a.m8191b(str, str2));
        if (c2562a == null) {
            c2562a = new C2562a();
            this.f6436b.put(C2563a.m8191b(str, str2), c2562a);
        }
        c2562a.f6433b = instance;
        return null;
    }

    public final void m8193a(C2515a c2515a, String str, String str2) {
        C2562a c2562a = (C2562a) this.f6436b.get(C2563a.m8191b(str, str2));
        if (c2562a == null) {
            c2562a = new C2562a();
            this.f6436b.put(C2563a.m8191b(str, str2), c2562a);
        }
        c2562a.f6434c = c2515a;
    }

    private static String m8191b(String str, String str2) {
        return StringUtils.notNullNorEmpty(str) ? str : str2;
    }
}
