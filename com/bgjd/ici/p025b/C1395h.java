package com.bgjd.ici.p025b;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.bgjd.ici.C1393a;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONObject;
import java.util.List;
import java.util.Map;

public interface C1395h extends C1393a {
    int mo2173A();

    boolean mo2174B();

    String mo2175C();

    Map<String, Long> mo2176D();

    Intent mo2177E();

    JSONArray mo2178F();

    C1394a mo2179G();

    String mo2180H();

    String mo2181I();

    boolean IsSandbox();

    JSONArray mo2183J();

    void mo2184K();

    void mo2185L();

    void mo2186M();

    int mo2187N();

    String mo2188O();

    String mo2189P();

    boolean mo2190Q();

    JSONObject mo2191R();

    boolean mo2192S();

    boolean mo2193T();

    boolean mo2194U();

    boolean mo2195V();

    JSONObject mo2196W();

    String mo2197X();

    JSONObject mo2198Y();

    String mo2199Z();

    void mo2200a(int i);

    void mo2201a(long j);

    void mo2202a(Intent intent);

    void mo2203a(JSONObject jSONObject);

    void mo2204a(String str);

    void mo2205a(String str, int i);

    void mo2206a(String str, long j);

    void mo2207a(String str, Object obj);

    void mo2208a(String str, String str2);

    void mo2209a(String str, Throwable th);

    String aa();

    void mo2213b(int i);

    void mo2214b(long j);

    void mo2215b(JSONObject jSONObject);

    void mo2216b(String str);

    void mo2217b(String str, int i);

    void mo2218b(String str, long j);

    void mo2219b(String str, String str2);

    String mo2222c(String str);

    void mo2223c(long j);

    void mo2224c(String str, String str2);

    void mo2228d(String str);

    void mo2229d(boolean z);

    void mo2231e(String str);

    void mo2232e(boolean z);

    long mo2233f(String str);

    void mo2235f(boolean z);

    int mo2236g(String str);

    void mo2238g(boolean z);

    String getBrowserCacheTable();

    JSONObject getBrowserDate();

    int getBrowsingLimit();

    Context getContext();

    String getDatabasePath();

    String[] getDatabaseTableNames();

    int getDatabaseVersion();

    String[] getDatabseCreateTables();

    int getDeclinedTransaction(String str);

    String getDetectedBeaconCacheTable();

    int getEmailCount();

    int getExtensionId();

    String getInstalledAppCacheTable();

    float getLatitude();

    float getLongitude();

    long getPackageDate();

    String getPackageFilter();

    int getPackageMaxId();

    String getPartnerLogsTable();

    SharedPreferences getPreferences();

    String getProcessCacheTable();

    JSONObject getSupportedFeatures();

    String getSystemFilter();

    long mo2263h(String str);

    String mo2265i(String str);

    boolean isAccepted();

    String mo2268j(String str);

    List<C1426t> mo2269k();

    void mo2270k(String str);

    String mo2271l();

    void mo2272l(String str);

    int mo2273m();

    void mo2274m(String str);

    int mo2275n(String str);

    String mo2276n();

    String mo2277o();

    void mo2278o(String str);

    int mo2279p();

    void mo2280p(String str);

    JSONArray mo2281q();

    void mo2282q(String str);

    String mo2283r();

    boolean mo2284s();

    void setDeclinedTransaction(String str, int i);

    void setLocation(float f, float f2);

    void setPackageMaxId(int i);

    void setTransactionHistory(String str, Long l);

    long mo2289t();

    boolean mo2290u();

    boolean mo2291v();

    String mo2292w();

    String mo2293x();

    String[] mo2294y();

    String mo2295z();
}
