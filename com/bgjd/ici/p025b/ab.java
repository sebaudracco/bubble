package com.bgjd.ici.p025b;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.plugin.C1520d;
import com.bgjd.ici.plugin.C1532j;

public class ab extends C1397l {
    private static final String f2057b = "3RDPTY";

    public ab(C1395h c1395h) {
        super(c1395h);
    }

    public ab() {
        super(null);
    }

    public JSONObject mo2296a() {
        Intent E = this.a.mo2177E();
        if (E != null) {
            Bundle extras = E.getExtras();
            if (!(extras == null || extras.isEmpty())) {
                String string = extras.getString("partner_id");
                if (string != null && string.length() > 0) {
                    try {
                        C1520d b = C1532j.m3310b();
                        if (b.mo2360b(string)) {
                            Object a = b.mo2357a(string);
                            a.getClass().getMethod("handleEvent", new Class[]{Context.class, Intent.class}).invoke(a, new Object[]{this.a.getContext(), this.a.mo2177E()});
                        }
                    } catch (Throwable e) {
                        C1402i.m2898a(f2057b, e, e.getMessage());
                    } catch (Throwable e2) {
                        C1402i.m2898a(f2057b, e2, e2.getMessage());
                    } catch (Throwable e22) {
                        C1402i.m2898a(f2057b, e22, e22.getMessage());
                    } catch (Throwable e222) {
                        C1402i.m2898a(f2057b, e222, e222.getMessage());
                    } catch (Throwable e2222) {
                        C1402i.m2898a(f2057b, e2222, e2222.getMessage());
                    }
                }
            }
        }
        return new JSONObject();
    }

    public String mo2297b() {
        return "3rdparty";
    }
}
