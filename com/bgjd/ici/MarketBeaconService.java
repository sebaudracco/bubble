package com.bgjd.ici;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1407c;
import com.bgjd.ici.p025b.C1416p;
import com.bgjd.ici.p025b.aa;
import com.bgjd.ici.p026a.C1392c;
import com.bgjd.ici.plugin.C1517a;
import com.bgjd.ici.plugin.C1521e;
import org.altbeacon.beacon.BeaconConsumer;

public class MarketBeaconService extends Service implements BeaconConsumer {
    public static ClassLoader f2015a = null;
    public static boolean f2016b = false;
    private static final String f2017c = "BCNSVC";
    private C1517a f2018d;
    private C1392c f2019e;
    private IBinder f2020f = new C1383a(this);
    private C1395h f2021g = null;

    public class C1383a extends Binder {
        final /* synthetic */ MarketBeaconService f2014a;

        public C1383a(MarketBeaconService marketBeaconService) {
            this.f2014a = marketBeaconService;
        }

        public Service m2643a() {
            return this.f2014a;
        }
    }

    public void onCreate() {
        if (this.f2021g == null) {
            this.f2021g = new aa(getApplicationContext());
        }
        if (this.f2019e == null && this.f2021g.getSupportedFeatures().has(C1407c.f2164d)) {
            this.f2019e = new C1392c(this.f2021g);
            this.f2019e.m2658a(this);
        }
        if (this.f2018d == null && this.f2021g.getSupportedFeatures().has(C1407c.f2167g)) {
            this.f2018d = new C1521e(this.f2021g);
        }
        Thread.setDefaultUncaughtExceptionHandler(new C1416p(this.f2021g));
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (this.f2021g == null) {
            this.f2021g = new aa(getApplicationContext());
        }
        if (this.f2021g != null && this.f2021g.getSupportedFeatures().has(C1407c.f2167g) && intent.hasExtra("partner_name")) {
            String stringExtra = intent.getStringExtra("partner_name");
            if (stringExtra != null) {
                this.f2018d.mo2343a(stringExtra);
                if (!(f2016b || stringExtra == null)) {
                    C1402i.m2901b(f2017c, "Beacon Service Started");
                    this.f2018d.mo2342a();
                }
            }
        }
        if (this.f2019e != null && this.f2021g.getSupportedFeatures().has(C1407c.f2164d)) {
            this.f2019e.mo2169a();
        }
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return this.f2020f;
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
    }

    public void onBeaconServiceConnect() {
        if (this.f2019e != null) {
            this.f2019e.mo2171c();
        }
    }

    public void onDestroy() {
        if (this.f2018d != null) {
            this.f2018d.mo2345c();
        }
        if (this.f2019e != null) {
            this.f2019e.mo2170b();
        }
        this.f2019e = null;
        this.f2018d = null;
        super.onDestroy();
    }
}
