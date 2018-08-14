package com.cuebiq.cuebiqsdk.model.config;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import java.util.Arrays;

public class Settings {
    public static final String ACCURACY = "network";
    public static final int ANALYTICS_APP_OPEN_COUNTER = 30;
    public static final int ANALYTICS_COVERAGE_CHECKER_COUNTER = 1440;
    public static final long A_DEFAULT_MIN_ACQUISITION_RATE = 20;
    public static final int BATTERY_LEVEL_THRESHOLD = 10;
    public static final long DEFAULT_FLUSH_AFTER_TIME_ELAPSED = 60;
    public static final int DEFAULT_GEO_ENABLED = 1;
    public static final long DEFAULT_MAX_ACQUISITION_RATE = 30;
    public static final int DEFAULT_MAX_BATCH_SIZE = 100;
    public static final long DEFAULT_MIN_ACQUISITION_RATE = 1;
    public static final int DEFAULT_MIN_ANDROID_VERSION = 14;
    public static final int DEFAULT_MIN_BATCH_SIZE = 10;
    public static final int DEFAULT_MIN_VERSION_TO_LOG = 10122;
    public static final int INSTALLED_BLUETOOTH_THRESHOLD = 10;
    public static final long IP_ADDRESS_API_DIFF = 10;
    public static final int IP_ADDRESS_API_ENABLE = 1;
    public static final int LOCATION_REQUEST_FASTEST = 4;
    public static final int LOCATION_REQUEST_INTERVAL = 10;
    public static final int LOCATION_REQUEST_MAX_WAIT = 60;
    public static final float LOCATION_REQUEST_SMALLEST_DISPLACEMENT = 10.0f;
    public static final int MAX_DYNAMIC_ACQUISITION = 600;
    public static final int OFFSET_DYNAMIC_ACQUISITION = -10;
    public static final int SCHEDULER_DWELLING_DISTANCE_THRESHOLD = 50;
    public static final int SCHEDULER_ENABLED = 1;
    public static final int SCHEDULER_HIGHWAY_DISTANCE_THRESHOLD = 200;
    public static final SchedulerSleepTime SCHEDULER_SLEEP_TIME = new SchedulerSleepTime(Arrays.asList(new Integer[]{Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(30)}));
    public static final int SCHEDULER_TEMPORARY_BUFFER_SIZE = 5;
    public static final int SCHEDULER_TIME_THRESHOLD = 5;
    public static final int SLOPE_DYNAMIC_ACQUISITION = 30;
    public static final int THRESHOLD_METERS = 30;
    public static final int TRACK_ALL_STATISTICS_ENABLE = 1;
    public static final int TRACK_LOCATION_ON_OFF_ENABLE = 1;
    public static final int TRACK_WHEN_LOCATION_OFF_AND_WIFI_ON_ENABLE = 1;
    public static final int VERSION = 40;
    public static final int WIFI_ENABLED = 0;
    public static final int WIFI_SCANNING_THRESHOLD = 20;
    public String acc = "network";
    public int alve = DEFAULT_MIN_VERSION_TO_LOG;
    public long aminar = 20;
    public int amvs = 14;
    public int anao = 30;
    public int ancc = ANALYTICS_COVERAGE_CHECKER_COUNTER;
    public int btlt = 10;
    public int ciaa = 1;
    public int defgeoen = 1;
    public int ibtr = 10;
    public long ipad = 10;
    public int lrf = 4;
    public int lri = 10;
    public int lrmw = 60;
    public float lrsd = LOCATION_REQUEST_SMALLEST_DISPLACEMENT;
    public long maxa = 30;
    public int maxb = 100;
    public long maxst = 60;
    public int mdyna = MAX_DYNAMIC_ACQUISITION;
    public long mina = 1;
    public int minb = 10;
    public int offda = -10;
    public int scen = 1;
    public int sddt = 50;
    public int shdt = 200;
    public int slopeda = 30;
    public SchedulerSleepTime sst = SCHEDULER_SLEEP_TIME;
    public int stbs = 5;
    public int stt = 5;
    public int tase = 1;
    public int tloo = 1;
    public int tlowo = 1;
    public int tr = 30;
    public Integer f2536v = Integer.valueOf(40);
    public int wfe = 0;
    public int wst = 20;

    public static Settings fromJSON(String str) {
        return str == null ? new Settings() : str.equals("") ? new Settings() : (Settings) CuebiqSDKImpl.GSON.fromJson(str, Settings.class);
    }

    public String getAcc() {
        return this.acc;
    }

    public int getAlve() {
        return this.alve;
    }

    public long getAminar() {
        return this.aminar;
    }

    public int getAmvs() {
        return this.amvs;
    }

    public int getAnao() {
        return this.anao;
    }

    public int getAncc() {
        return this.ancc;
    }

    public int getBtlt() {
        return this.btlt;
    }

    public int getCiaa() {
        return this.ciaa;
    }

    public int getDefgeoen() {
        return this.defgeoen;
    }

    public int getIbtr() {
        return this.ibtr;
    }

    public long getIpad() {
        return this.ipad;
    }

    public int getLrf() {
        return this.lrf;
    }

    public int getLri() {
        return this.lri;
    }

    public int getLrmw() {
        return this.lrmw;
    }

    public float getLrsd() {
        return this.lrsd;
    }

    public long getMaxa() {
        return this.maxa;
    }

    public int getMaxb() {
        return this.maxb;
    }

    public long getMaxst() {
        return this.maxst;
    }

    public int getMdyna() {
        return this.mdyna;
    }

    public long getMina() {
        return this.mina;
    }

    public int getMinb() {
        return this.minb;
    }

    public int getOffda() {
        return this.offda;
    }

    public int getScen() {
        return this.scen;
    }

    public int getSddt() {
        return this.sddt;
    }

    public int getShdt() {
        return this.shdt;
    }

    public int getSlopeda() {
        return this.slopeda;
    }

    public SchedulerSleepTime getSst() {
        return this.sst;
    }

    public int getStbs() {
        return this.stbs;
    }

    public int getStt() {
        return this.stt;
    }

    public int getTase() {
        return this.tase;
    }

    public int getTloo() {
        return this.tloo;
    }

    public int getTlowo() {
        return this.tlowo;
    }

    public int getTr() {
        return this.tr;
    }

    public Integer getV() {
        return this.f2536v;
    }

    public int getWfe() {
        return this.wfe;
    }

    public int getWst() {
        return this.wst;
    }

    public void setAcc(String str) {
        this.acc = str;
    }

    public void setAlve(int i) {
        this.alve = i;
    }

    public void setAminar(long j) {
        this.aminar = j;
    }

    public void setAmvs(int i) {
        this.amvs = i;
    }

    public void setAnao(int i) {
        this.anao = i;
    }

    public void setAncc(int i) {
        this.ancc = i;
    }

    public void setBtlt(int i) {
        this.btlt = i;
    }

    public void setCiaa(int i) {
        this.ciaa = i;
    }

    public void setDefgeoen(int i) {
        this.defgeoen = i;
    }

    public void setIbtr(int i) {
        this.ibtr = i;
    }

    public void setIpad(long j) {
        this.ipad = j;
    }

    public void setLrf(int i) {
        this.lrf = i;
    }

    public void setLri(int i) {
        this.lri = i;
    }

    public void setLrmw(int i) {
        this.lrmw = i;
    }

    public void setLrsd(float f) {
        this.lrsd = f;
    }

    public void setMaxa(long j) {
        this.maxa = j;
    }

    public void setMaxb(int i) {
        this.maxb = i;
    }

    public void setMaxst(long j) {
        this.maxst = j;
    }

    public void setMdyna(int i) {
        this.mdyna = i;
    }

    public void setMina(long j) {
        this.mina = j;
    }

    public void setMinb(int i) {
        this.minb = i;
    }

    public void setOffda(int i) {
        this.offda = i;
    }

    public void setScen(int i) {
        this.scen = i;
    }

    public void setSddt(int i) {
        this.sddt = i;
    }

    public void setShdt(int i) {
        this.shdt = i;
    }

    public void setSlopeda(int i) {
        this.slopeda = i;
    }

    public void setSst(SchedulerSleepTime schedulerSleepTime) {
        this.sst = schedulerSleepTime;
    }

    public void setStbs(int i) {
        this.stbs = i;
    }

    public void setStt(int i) {
        this.stt = i;
    }

    public void setTase(int i) {
        this.tase = i;
    }

    public void setTloo(int i) {
        this.tloo = i;
    }

    public void setTlowo(int i) {
        this.tlowo = i;
    }

    public void setTr(int i) {
        this.tr = i;
    }

    public void setV(Integer num) {
        this.f2536v = num;
    }

    public void setWfe(int i) {
        this.wfe = i;
    }

    public void setWst(int i) {
        this.wst = i;
    }

    public String toJSON() {
        return CuebiqSDKImpl.GSON.toJson(this);
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson(this);
    }
}
