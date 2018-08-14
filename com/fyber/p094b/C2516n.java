package com.fyber.p094b;

import android.content.Context;
import android.widget.Toast;
import com.fyber.C2409a;
import com.fyber.Fyber;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.currency.VirtualCurrencyErrorResponse;
import com.fyber.currency.VirtualCurrencyErrorResponse.ErrorType;
import com.fyber.currency.VirtualCurrencyResponse;
import com.fyber.currency.p106a.C2563a;
import com.fyber.currency.p106a.C2564b;
import com.fyber.requesters.RequestError;
import com.fyber.requesters.p097a.C2588f;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.utils.C2412c;
import com.fyber.utils.C2671s;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: VirtualCurrencyNetworkOperation */
public final class C2516n extends C2506l<C2515a, Void> {
    private static final C2563a f6244b = new C2563a();
    private C2588f<VirtualCurrencyResponse, VirtualCurrencyErrorResponse> f6245e;
    private final C2623c f6246f;
    private Context f6247g;
    private boolean f6248h = true;

    /* compiled from: VirtualCurrencyNetworkOperation */
    public interface C2515a {
    }

    protected final /* synthetic */ Object mo3927a(Object obj) {
        C2515a c2515a = (C2515a) obj;
        if (c2515a instanceof VirtualCurrencyResponse) {
            int i;
            VirtualCurrencyResponse virtualCurrencyResponse = (VirtualCurrencyResponse) c2515a;
            C2564b a = C2564b.m8194a(this.f6247g);
            String a2 = a.m8196a();
            String currencyId = virtualCurrencyResponse.getCurrencyId();
            if (StringUtils.nullOrEmpty(m7999c()) && StringUtils.notNullNorEmpty(a2) && !a2.equalsIgnoreCase(currencyId)) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                a.m8198a(currencyId);
                Object b = a.m8199b(currencyId);
                Callable c2516n = new C2516n(this);
                c2516n.f6246f.m8402a("TRANSACTION_ID", b).m8402a("currency_id", m7999c()).m8420f();
                Fyber.getConfigs().m7599a(c2516n);
                i = 0;
            } else {
                i = 1;
            }
            if (i != 0) {
                a2 = C2564b.m8194a(this.f6247g).m8196a();
                String currencyId2 = virtualCurrencyResponse.getCurrencyId();
                f6244b.m8193a(new VirtualCurrencyResponse(0.0d, virtualCurrencyResponse.getLatestTransactionId(), currencyId2, virtualCurrencyResponse.getCurrencyName(), virtualCurrencyResponse.isDefault()), currencyId2, a2);
                C2564b.m8194a(this.f6247g).m8197a(virtualCurrencyResponse);
                boolean booleanValue = ((Boolean) this.f6246f.mo3970a("NOTIFY_USER_ON_REWARD")).booleanValue();
                if (virtualCurrencyResponse.getDeltaOfCoins() > 0.0d && booleanValue) {
                    a2 = virtualCurrencyResponse.getCurrencyName();
                    if (!StringUtils.notNullNorEmpty(a2)) {
                        a2 = C2671s.m8532a(UIStringIdentifier.VCS_DEFAULT_CURRENCY);
                    }
                    a2 = String.format(Locale.ENGLISH, C2671s.m8532a(UIStringIdentifier.VCS_COINS_NOTIFICATION), new Object[]{Double.valueOf(virtualCurrencyResponse.getDeltaOfCoins()), a2});
                    Fyber.getConfigs();
                    C2409a.m7595b(new C2412c(this) {
                        final /* synthetic */ C2516n f6243b;

                        public final void mo3844a() {
                            Toast.makeText(this.f6243b.f6247g, a2, 1).show();
                        }
                    });
                }
                this.f6245e.m8287c(virtualCurrencyResponse);
            }
        } else {
            VirtualCurrencyErrorResponse virtualCurrencyErrorResponse = (VirtualCurrencyErrorResponse) c2515a;
            String a3 = C2564b.m8194a(this.f6247g).m8196a();
            f6244b.m8193a(virtualCurrencyErrorResponse, StringUtils.nullOrEmpty(m7999c()) ? a3 : m7999c(), a3);
            this.f6245e.m8288d(virtualCurrencyErrorResponse);
        }
        return null;
    }

    protected final /* synthetic */ Object mo3928a(String str) {
        return C2516n.m7997b(str);
    }

    private C2516n(C2516n c2516n) {
        super(c2516n.d, c2516n.a);
        this.f6245e = c2516n.f6245e;
        this.f6247g = c2516n.f6247g;
        this.f6246f = new C2623c(c2516n.f6246f).m8417d(c2516n.f6246f.m8413b());
        this.f6248h = false;
    }

    public C2516n(C2623c c2623c, String str, Context context) {
        super(c2623c.m8419e().m8437c(), str);
        this.f6246f = c2623c;
        this.f6247g = context.getApplicationContext();
    }

    public final C2516n m8000a(C2588f c2588f) {
        this.f6245e = c2588f;
        return this;
    }

    private static C2515a m7997b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new VirtualCurrencyResponse(jSONObject.getDouble("delta_of_coins"), jSONObject.getString("latest_transaction_id"), jSONObject.getString("currency_id"), jSONObject.getString("currency_name"), jSONObject.getBoolean("is_default"));
        } catch (Exception e) {
            return new VirtualCurrencyErrorResponse(ErrorType.ERROR_INVALID_RESPONSE, null, e.getMessage());
        }
    }

    private static VirtualCurrencyErrorResponse m7998c(String str) {
        String string;
        String string2;
        ErrorType errorType;
        Exception exception;
        try {
            JSONObject jSONObject = new JSONObject(str);
            string = jSONObject.getString("code");
            try {
                string2 = jSONObject.getString("message");
                errorType = ErrorType.SERVER_RETURNED_ERROR;
            } catch (Exception e) {
                exception = e;
                FyberLogger.m8454w("VirtualCurrencyNetworkOperation", "An exception was triggered while parsing error response", exception);
                errorType = ErrorType.ERROR_OTHER;
                string2 = exception.getMessage();
                return new VirtualCurrencyErrorResponse(errorType, string, string2);
            }
        } catch (Exception e2) {
            exception = e2;
            string = null;
            FyberLogger.m8454w("VirtualCurrencyNetworkOperation", "An exception was triggered while parsing error response", exception);
            errorType = ErrorType.ERROR_OTHER;
            string2 = exception.getMessage();
            return new VirtualCurrencyErrorResponse(errorType, string, string2);
        }
        return new VirtualCurrencyErrorResponse(errorType, string, string2);
    }

    protected final boolean a_() {
        if (this.f6248h) {
            C2564b a = C2564b.m8194a(this.f6247g);
            C2515a a2 = f6244b.m8192a(m7999c(), a.m8196a());
            if (a2 == null || a2.equals(C2563a.f6435a)) {
                String str = (String) this.f6246f.mo3970a("TRANSACTION_ID");
                if (StringUtils.nullOrEmpty(str)) {
                    str = a.m8199b(m7999c());
                }
                this.d.m8539a("ltid", str);
            } else {
                if (a2 instanceof VirtualCurrencyResponse) {
                    this.f6245e.m8287c((VirtualCurrencyResponse) a2);
                } else {
                    this.f6245e.m8288d((VirtualCurrencyErrorResponse) a2);
                }
                return false;
            }
        }
        return true;
    }

    protected final String b_() {
        return "VirtualCurrencyNetworkOperation";
    }

    private String m7999c() {
        return (String) this.f6246f.mo3970a("CURRENCY_ID");
    }

    protected final /* synthetic */ Object mo3926a(int i, String str, String str2) {
        if (C2506l.m7972a(i)) {
            return C2516n.m7998c(str);
        }
        return new VirtualCurrencyErrorResponse(ErrorType.ERROR_INVALID_RESPONSE_SIGNATURE, null, "The signature received in the request did not match the expected one");
    }

    protected final /* synthetic */ Object mo3901b(IOException iOException) {
        this.f6245e.m8282a(RequestError.CONNECTION_ERROR);
        return null;
    }
}
