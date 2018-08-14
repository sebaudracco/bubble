package com.yandex.metrica;

import android.content.ContentValues;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.yandex.metrica.impl.C4543y;
import com.yandex.metrica.impl.bc;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.utils.C4525g;
import java.util.List;
import java.util.Map;

public class CounterConfiguration implements Parcelable {
    public static final Creator<CounterConfiguration> CREATOR = new C42681();
    private ContentValues f11405a;
    private ResultReceiver f11406b;

    static class C42681 implements Creator<CounterConfiguration> {
        C42681() {
        }

        public /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new CounterConfiguration[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new CounterConfiguration(parcel);
        }
    }

    public enum C4270a {
        UNDEFINED(-1),
        FALSE(0),
        TRUE(1);
        
        public final int f11404d;

        private C4270a(int i) {
            this.f11404d = i;
        }

        public static C4270a m14230a(int i) {
            switch (i) {
                case -1:
                    return UNDEFINED;
                case 0:
                    return FALSE;
                case 1:
                    return TRUE;
                default:
                    return UNDEFINED;
            }
        }
    }

    public CounterConfiguration(CounterConfiguration other) {
        this.f11406b = null;
        this.f11405a = new ContentValues();
        this.f11405a.putAll(other.f11405a);
        this.f11406b = other.f11406b;
    }

    public CounterConfiguration() {
        this.f11406b = null;
        this.f11405a = new ContentValues();
        this.f11405a.put("CFG_DISPATCH_PERIOD", Integer.valueOf(90));
        this.f11405a.put("CFG_MAX_REPORTS_COUNT", Integer.valueOf(7));
        this.f11405a.put("CFG_SESSION_TIMEOUT", Integer.valueOf(10));
        this.f11405a.put("CFG_REPORTS", Boolean.valueOf(true));
        this.f11405a.put("CFG_REPORTS_CRASHES", Boolean.valueOf(true));
        this.f11405a.put("CFG_REPORTS_NATIVE_CRASHES", Boolean.valueOf(true));
        this.f11405a.put("CFG_REPORT_LOCATION", Boolean.valueOf(true));
        this.f11405a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf(C4270a.FALSE.f11404d));
        this.f11405a.putNull("CFG_HOST_URL");
        this.f11405a.putNull("CFG_CUSTOM_HOSTS");
        this.f11405a.putNull("CFG_MANUAL_LOCATION");
        this.f11405a.putNull("CFG_APP_VERSION");
        this.f11405a.putNull("CFG_APP_VERSION_CODE");
        this.f11405a.putNull("CFG_API_KEY");
        this.f11405a.putNull("CFG_PACKAGE_NAME");
        this.f11405a.putNull("CFG_UUID");
        this.f11405a.putNull("CFG_DEVICE_ID");
        this.f11405a.putNull("CFG_DEVICE_SIZE_TYPE");
        this.f11405a.putNull("CFG_CLIDS");
        this.f11405a.putNull("CFG_IS_FIRST_ACTIVATION_AS_UPDATE");
        this.f11405a.put("CFG_MAIN_REPORTER", Boolean.valueOf(true));
        this.f11405a.put("CFG_IS_LOG_ENABLED", Boolean.valueOf(false));
        this.f11405a.put("CFG_APP_FRAMEWORK", bc.m14877c());
    }

    public void m14247a(C4295e c4295e) {
        boolean z;
        boolean z2 = false;
        if (c4295e.getSessionTimeout() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14257c(c4295e.getSessionTimeout().intValue());
        }
        if (c4295e.getLocation() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14241a(c4295e.getLocation());
        }
        if (c4295e.isTrackLocationEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14259c(c4295e.isTrackLocationEnabled().booleanValue());
        }
        if (c4295e.isCollectInstalledApps() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14263d(c4295e.isCollectInstalledApps().booleanValue());
        }
        if (c4295e.isReportCrashEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14251a(c4295e.isReportCrashEnabled().booleanValue());
        }
        if (c4295e.isReportNativeCrashEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14255b(c4295e.isReportNativeCrashEnabled().booleanValue());
        }
        if (c4295e.m14415e() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14246a(c4295e.m14415e());
        }
        if (c4295e.m14419i() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14240a(c4295e.m14419i().intValue());
        }
        if (c4295e.m14418h() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14253b(c4295e.m14418h().intValue());
        }
        if (!bi.m14957a(c4295e.getAppVersion())) {
            m14271g(c4295e.getAppVersion());
        }
        if (c4295e.m14414d() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14261d(c4295e.m14414d().intValue());
        }
        if (c4295e.m14413c() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14249a(c4295e.m14413c());
        }
        if (c4295e.m14421k() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14269f(c4295e.m14421k().booleanValue());
        }
        if (c4295e.m14422l() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14272g(c4295e.m14422l().booleanValue());
        }
        if (c4295e.m14416f() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m14250a(c4295e.m14416f());
        }
        if (c4295e.m14417g() != null) {
            z2 = true;
        }
        if (z2) {
            m14274h(c4295e.m14417g());
        }
        if (c4295e.isFirstActivationAsUpdate()) {
            this.f11405a.put("CFG_IS_FIRST_ACTIVATION_AS_UPDATE", Boolean.valueOf(true));
        }
    }

    public void m14244a(ResultReceiver resultReceiver) {
        this.f11406b = resultReceiver;
    }

    public ResultReceiver m14239a() {
        return this.f11406b;
    }

    public void m14240a(int i) {
        this.f11405a.put("CFG_DISPATCH_PERIOD", Integer.valueOf(i));
    }

    public int m14252b() {
        return this.f11405a.getAsInteger("CFG_DISPATCH_PERIOD").intValue();
    }

    public void m14253b(int i) {
        ContentValues contentValues = this.f11405a;
        String str = "CFG_MAX_REPORTS_COUNT";
        if (i <= 0) {
            i = Integer.MAX_VALUE;
        }
        contentValues.put(str, Integer.valueOf(i));
    }

    public int m14256c() {
        return this.f11405a.getAsInteger("CFG_MAX_REPORTS_COUNT").intValue();
    }

    public void m14257c(int i) {
        this.f11405a.put("CFG_SESSION_TIMEOUT", Integer.valueOf(Math.max(10, i)));
    }

    public int m14260d() {
        return this.f11405a.getAsInteger("CFG_SESSION_TIMEOUT").intValue();
    }

    public void m14246a(C4275a c4275a) {
        this.f11405a.put("CFG_DEVICE_SIZE_TYPE", c4275a == null ? null : c4275a.m14313a());
    }

    public C4275a m14264e() {
        return C4275a.m14312a(this.f11405a.getAsString("CFG_DEVICE_SIZE_TYPE"));
    }

    public void m14248a(String str) {
        bk.m14982a(str);
        this.f11405a.put("CFG_API_KEY", str);
    }

    public void m14254b(String str) {
        this.f11405a.put("CFG_API_KEY", str);
    }

    public void m14258c(String str) {
        this.f11405a.put("CFG_PACKAGE_NAME", str);
    }

    public String m14267f() {
        return this.f11405a.getAsString("CFG_PACKAGE_NAME");
    }

    public void m14262d(String str) {
        this.f11405a.put("CFG_UUID", str);
    }

    public String m14270g() {
        return this.f11405a.getAsString("CFG_UUID");
    }

    public void m14265e(String str) {
        this.f11405a.put("CFG_DEVICE_ID", str);
    }

    public String m14273h() {
        return this.f11405a.getAsString("CFG_DEVICE_ID");
    }

    public void m14268f(String str) {
        this.f11405a.put("CFG_POSSIBLE_DEVICE_ID", str);
    }

    public String m14276i() {
        return this.f11405a.getAsString("CFG_POSSIBLE_DEVICE_ID");
    }

    public String m14277j() {
        return this.f11405a.getAsString("CFG_API_KEY");
    }

    public void m14251a(boolean z) {
        this.f11405a.put("CFG_REPORTS_CRASHES", Boolean.valueOf(z));
    }

    public boolean m14278k() {
        return this.f11405a.getAsBoolean("CFG_REPORTS_CRASHES").booleanValue();
    }

    public void m14255b(boolean z) {
        this.f11405a.put("CFG_REPORTS_NATIVE_CRASHES", Boolean.valueOf(z));
    }

    public boolean m14279l() {
        return this.f11405a.getAsBoolean("CFG_REPORTS_NATIVE_CRASHES").booleanValue();
    }

    public void m14259c(boolean z) {
        this.f11405a.put("CFG_REPORT_LOCATION", Boolean.valueOf(z));
    }

    public boolean m14280m() {
        return this.f11405a.getAsBoolean("CFG_REPORT_LOCATION").booleanValue();
    }

    public void m14249a(List<String> list) {
        this.f11405a.put("CFG_CUSTOM_HOSTS", C4525g.m16270a((List) list));
    }

    public List<String> m14281n() {
        Object asString = this.f11405a.getAsString("CFG_CUSTOM_HOSTS");
        return TextUtils.isEmpty(asString) ? null : C4525g.m16274b(asString);
    }

    public void m14271g(String str) {
        this.f11405a.put("CFG_APP_VERSION", str);
    }

    public String m14282o() {
        return this.f11405a.getAsString("CFG_APP_VERSION");
    }

    public void m14261d(int i) {
        this.f11405a.put("CFG_APP_VERSION_CODE", String.valueOf(i));
    }

    public String m14283p() {
        return this.f11405a.getAsString("CFG_APP_VERSION_CODE");
    }

    public void m14263d(boolean z) {
        this.f11405a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf(z ? C4270a.TRUE.f11404d : C4270a.FALSE.f11404d));
    }

    public boolean m14284q() {
        switch (m14285r()) {
            case TRUE:
                return true;
            default:
                return false;
        }
    }

    public C4270a m14285r() {
        return m14231a(this.f11405a.get("CFG_COLLECT_INSTALLED_APPS"));
    }

    public void m14241a(Location location) {
        this.f11405a.put("CFG_MANUAL_LOCATION", C4543y.m16308b(location));
    }

    public void m14266e(boolean z) {
        this.f11405a.put("CFG_IS_LOG_ENABLED", Boolean.valueOf(z));
    }

    public boolean m14286s() {
        if (this.f11405a.containsKey("CFG_IS_LOG_ENABLED")) {
            return this.f11405a.getAsBoolean("CFG_IS_LOG_ENABLED").booleanValue();
        }
        return false;
    }

    public Location m14287t() {
        Location a = C4543y.m16304a(this.f11405a.getAsByteArray("CFG_MANUAL_LOCATION"));
        if (a != null || !m14234B()) {
            return a;
        }
        Double z = m14293z();
        Double A = m14233A();
        a = new Location("NONE");
        a.setLatitude(z.doubleValue());
        a.setLongitude(A.doubleValue());
        a.setTime(System.currentTimeMillis());
        return a;
    }

    public void m14250a(Map<String, String> map) {
        this.f11405a.put("CFG_CLIDS", C4525g.m16271a((Map) map));
    }

    public Map<String, String> m14288u() {
        return C4525g.m16272a(this.f11405a.getAsString("CFG_CLIDS"));
    }

    public String m14289v() {
        return this.f11405a.getAsString("CFG_DISTRIBUTION_REFERRER");
    }

    public void m14274h(String str) {
        this.f11405a.put("CFG_DISTRIBUTION_REFERRER", str);
    }

    public boolean m14290w() {
        Boolean asBoolean = this.f11405a.getAsBoolean("CFG_AUTO_PRELOAD_INFO_DETECTION");
        return asBoolean != null ? asBoolean.booleanValue() : false;
    }

    public boolean m14291x() {
        Boolean asBoolean = this.f11405a.getAsBoolean("CFG_PERMISSIONS_COLLECTING");
        return asBoolean == null ? true : asBoolean.booleanValue();
    }

    public void m14269f(boolean z) {
        this.f11405a.put("CFG_AUTO_PRELOAD_INFO_DETECTION", Boolean.valueOf(z));
    }

    public void m14272g(boolean z) {
        this.f11405a.put("CFG_PERMISSIONS_COLLECTING", Boolean.valueOf(z));
    }

    public boolean m14292y() {
        if (this.f11405a.get("CFG_IS_FIRST_ACTIVATION_AS_UPDATE") != null) {
            return this.f11405a.getAsBoolean("CFG_IS_FIRST_ACTIVATION_AS_UPDATE").booleanValue();
        }
        return false;
    }

    Double m14293z() {
        return this.f11405a.getAsDouble("CFG_LOCATION_LATITUDE");
    }

    Double m14233A() {
        return this.f11405a.getAsDouble("CFG_LOCATION_LONGITUDE");
    }

    boolean m14234B() {
        int i;
        int i2 = 1;
        if (this.f11405a.getAsDouble("CFG_LOCATION_LONGITUDE") != null) {
            i = 1;
        } else {
            i = 0;
        }
        if (this.f11405a.getAsDouble("CFG_LOCATION_LATITUDE") == null) {
            i2 = 0;
        }
        return i & i2;
    }

    public CounterConfiguration(Parcel srcObj) {
        this.f11406b = null;
        m14243a(srcObj);
    }

    public int describeContents() {
        return 0;
    }

    public void m14243a(Parcel parcel) {
        this.f11405a = (ContentValues) parcel.readParcelable(ContentValues.class.getClass().getClassLoader());
        this.f11406b = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClass().getClassLoader());
    }

    public void writeToParcel(Parcel destObj, int flags) {
        destObj.writeParcelable(this.f11405a, 0);
        ResultReceiver resultReceiver = this.f11406b;
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        resultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        destObj.writeParcelable(resultReceiver, 0);
    }

    public void m14275h(boolean z) {
        this.f11405a.put("CFG_MAIN_REPORTER", Boolean.valueOf(z));
    }

    public boolean m14235C() {
        Boolean asBoolean = this.f11405a.getAsBoolean("CFG_MAIN_REPORTER");
        return asBoolean != null ? asBoolean.booleanValue() : true;
    }

    public boolean m14236D() {
        return bk.m14992b(m14277j());
    }

    public String m14237E() {
        return this.f11405a.getAsString("CFG_APP_FRAMEWORK");
    }

    public Bundle m14238F() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("COUNTER_CFG_OBJ", this);
        return bundle;
    }

    public void m14245a(CounterConfiguration counterConfiguration) {
        if (this.f11405a.containsKey("CFG_DISPATCH_PERIOD")) {
            this.f11405a.put("CFG_DISPATCH_PERIOD", counterConfiguration.f11405a.getAsInteger("CFG_DISPATCH_PERIOD"));
        }
        if (this.f11405a.containsKey("CFG_SESSION_TIMEOUT")) {
            this.f11405a.put("CFG_SESSION_TIMEOUT", counterConfiguration.f11405a.getAsInteger("CFG_SESSION_TIMEOUT"));
        }
        if (this.f11405a.containsKey("CFG_MAX_REPORTS_COUNT")) {
            this.f11405a.put("CFG_MAX_REPORTS_COUNT", counterConfiguration.f11405a.getAsInteger("CFG_MAX_REPORTS_COUNT"));
        }
        if (this.f11405a.containsKey("CFG_REPORTS_CRASHES")) {
            this.f11405a.put("CFG_REPORTS_CRASHES", counterConfiguration.f11405a.getAsBoolean("CFG_REPORTS_CRASHES"));
        }
        if (this.f11405a.containsKey("CFG_REPORTS_NATIVE_CRASHES")) {
            this.f11405a.put("CFG_REPORTS_NATIVE_CRASHES", counterConfiguration.f11405a.getAsBoolean("CFG_REPORTS_NATIVE_CRASHES"));
        }
        if (this.f11405a.containsKey("CFG_REPORT_LOCATION")) {
            this.f11405a.put("CFG_REPORT_LOCATION", counterConfiguration.f11405a.getAsBoolean("CFG_REPORT_LOCATION"));
        }
        if (this.f11405a.containsKey("CFG_MANUAL_LOCATION")) {
            this.f11405a.put("CFG_MANUAL_LOCATION", counterConfiguration.f11405a.getAsByteArray("CFG_MANUAL_LOCATION"));
        }
        if (this.f11405a.containsKey("CFG_COLLECT_INSTALLED_APPS")) {
            this.f11405a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf(m14231a(counterConfiguration.f11405a.get("CFG_COLLECT_INSTALLED_APPS")).f11404d));
        }
        if (this.f11405a.containsKey("CFG_DEVICE_SIZE_TYPE")) {
            this.f11405a.put("CFG_DEVICE_SIZE_TYPE", counterConfiguration.f11405a.getAsString("CFG_DEVICE_SIZE_TYPE"));
        }
        if (this.f11405a.containsKey("CFG_IS_LOG_ENABLED")) {
            this.f11405a.put("CFG_IS_LOG_ENABLED", counterConfiguration.f11405a.getAsBoolean("CFG_IS_LOG_ENABLED"));
        }
        if (this.f11405a.containsKey("CFG_CLIDS")) {
            this.f11405a.put("CFG_CLIDS", counterConfiguration.f11405a.getAsString("CFG_CLIDS"));
        }
        if (this.f11405a.containsKey("CFG_AUTO_PRELOAD_INFO_DETECTION")) {
            this.f11405a.put("CFG_AUTO_PRELOAD_INFO_DETECTION", counterConfiguration.f11405a.getAsBoolean("CFG_AUTO_PRELOAD_INFO_DETECTION"));
        }
        if (this.f11405a.containsKey("CFG_PERMISSIONS_COLLECTING")) {
            this.f11405a.put("CFG_PERMISSIONS_COLLECTING", counterConfiguration.f11405a.getAsBoolean("CFG_PERMISSIONS_COLLECTING"));
        }
    }

    public void m14242a(Bundle bundle) {
        if (bundle != null) {
            if (bundle.getInt("CFG_DISPATCH_PERIOD") != 0) {
                m14240a(bundle.getInt("CFG_DISPATCH_PERIOD"));
            }
            if (bundle.getInt("CFG_SESSION_TIMEOUT") != 0) {
                m14257c(bundle.getInt("CFG_SESSION_TIMEOUT"));
            }
            if (bundle.getInt("CFG_MAX_REPORTS_COUNT") != 0) {
                m14253b(bundle.getInt("CFG_MAX_REPORTS_COUNT"));
            }
            if (bundle.getString("CFG_API_KEY") != null && !"-1".equals(bundle.getString("CFG_API_KEY"))) {
                m14248a(bundle.getString("CFG_API_KEY"));
            }
        }
    }

    private static C4270a m14231a(Object obj) {
        if (obj != null) {
            if (obj instanceof Integer) {
                return C4270a.m14230a(((Integer) obj).intValue());
            }
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue() ? C4270a.TRUE : C4270a.FALSE;
            }
        }
        return C4270a.UNDEFINED;
    }

    public static CounterConfiguration m14232b(Bundle bundle) {
        CounterConfiguration counterConfiguration;
        if (bundle != null) {
            try {
                counterConfiguration = (CounterConfiguration) bundle.getParcelable("COUNTER_CFG_OBJ");
            } catch (Throwable th) {
                return null;
            }
        }
        counterConfiguration = null;
        if (counterConfiguration == null) {
            counterConfiguration = new CounterConfiguration();
        }
        counterConfiguration.m14242a(bundle);
        return counterConfiguration;
    }
}
