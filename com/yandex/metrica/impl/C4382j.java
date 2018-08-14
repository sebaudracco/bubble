package com.yandex.metrica.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.yandex.metrica.impl.bg.C4361a;
import com.yandex.metrica.impl.ob.du;
import com.yandex.metrica.impl.utils.C4525g;
import com.yandex.metrica.impl.utils.C4532l;
import com.yandex.metrica.impl.utils.C4533m;

public class C4382j extends ResultReceiver {
    private C4368a f11875a;

    interface C4368a {
        void mo7029a(int i, Bundle bundle);
    }

    public C4382j(Handler handler) {
        super(handler);
    }

    void m15105a(C4368a c4368a) {
        this.f11875a = c4368a;
    }

    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (this.f11875a != null) {
            this.f11875a.mo7029a(resultCode, resultData);
        }
    }

    public static void m15103a(ResultReceiver resultReceiver, ba baVar, C4361a c4361a) {
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putString("UuId", baVar.m14819b());
            bundle.putString("DeviceId", baVar.m14824c());
            bundle.putString("AdUrlGet", baVar.m14793A());
            bundle.putString("AdUrlReport", baVar.m14794B());
            bundle.putLong("ServerTimeOffset", C4533m.m16293a());
            bundle.putString("Clids", C4525g.m16271a(C4532l.m16292a(baVar.m14868y())));
            bundle.putString("CookieBrowsers", c4361a.m14924l().m15791a());
            bundle.putString("BindIdUrl", c4361a.m14925m());
            resultReceiver.send(1, bundle);
        }
    }

    public static void m15104a(ResultReceiver resultReceiver, du duVar) {
        if (resultReceiver != null) {
            resultReceiver.send(2, duVar.m15795a(new Bundle()));
        }
    }
}
