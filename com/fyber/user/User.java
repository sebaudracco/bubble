package com.fyber.user;

import android.location.Location;
import android.location.LocationManager;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.appnext.base.p023b.C1048i;
import com.fyber.Fyber;
import com.fyber.mediation.admob.AdMobMediationAdapter;
import com.fyber.utils.C2656g;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public final class User extends TreeMap<String, Object> {
    private static final User f6549g = new User();
    private static final long serialVersionUID = -5963403748409731798L;
    private String f6550a;
    private boolean f6551b = false;
    private Set<String> f6552c = new HashSet();
    private Location f6553d;
    private Location f6554e;
    private Calendar f6555f;

    private User() {
        this.f6552c.add("age");
        this.f6552c.add("birthdate");
        this.f6552c.add(AdMobMediationAdapter.GENDER_KEY);
        this.f6552c.add("sexual_orientation");
        this.f6552c.add("ethnicity");
        this.f6552c.add(C1048i.ko);
        this.f6552c.add("longt");
        this.f6552c.add("marital_status");
        this.f6552c.add("children");
        this.f6552c.add("annual_household_income");
        this.f6552c.add("education");
        this.f6552c.add("zipcode");
        this.f6552c.add("interests");
        this.f6552c.add("iap");
        this.f6552c.add("iap_amount");
        this.f6552c.add("number_of_sessions");
        this.f6552c.add("ps_time");
        this.f6552c.add("last_session");
        this.f6552c.add("connection");
        this.f6552c.add("device");
        this.f6552c.add("app_version");
    }

    public static Integer getAge() {
        return (Integer) f6549g.get("age");
    }

    public static void setAge(Integer num) {
        f6549g.put("age", (Object) num);
    }

    public static Date getBirthdate() {
        return (Date) f6549g.get("birthdate");
    }

    public static void setBirthdate(Date date) {
        f6549g.put("birthdate", (Object) date);
    }

    public static UserGender getGender() {
        return (UserGender) f6549g.get(AdMobMediationAdapter.GENDER_KEY);
    }

    public static void setGender(UserGender userGender) {
        f6549g.put(AdMobMediationAdapter.GENDER_KEY, (Object) userGender);
    }

    public static UserSexualOrientation getSexualOrientation() {
        return (UserSexualOrientation) f6549g.get("sexual_orientation");
    }

    public static void setSexualOrientation(UserSexualOrientation userSexualOrientation) {
        f6549g.put("sexual_orientation", (Object) userSexualOrientation);
    }

    public static UserEthnicity getEthnicity() {
        return (UserEthnicity) f6549g.get("ethnicity");
    }

    public static void setEthnicity(UserEthnicity userEthnicity) {
        f6549g.put("ethnicity", (Object) userEthnicity);
    }

    public static Location getLocation() {
        return f6549g.f6553d;
    }

    public static void setLocation(Location location) {
        f6549g.f6553d = location;
        f6549g.m8445a(location);
    }

    public static UserMaritalStatus getMaritalStatus() {
        return (UserMaritalStatus) f6549g.get("marital_status");
    }

    public static void setMaritalStatus(UserMaritalStatus userMaritalStatus) {
        f6549g.put("marital_status", (Object) userMaritalStatus);
    }

    public static Integer getNumberOfChildrens() {
        return (Integer) f6549g.get("children");
    }

    public static void setNumberOfChildrens(Integer num) {
        f6549g.put("children", (Object) num);
    }

    public static Integer getAnnualHouseholdIncome() {
        return (Integer) f6549g.get("annual_household_income");
    }

    public static void setAnnualHouseholdIncome(Integer num) {
        f6549g.put("annual_household_income", (Object) num);
    }

    public static UserEducation getEducation() {
        return (UserEducation) f6549g.get("education");
    }

    public static void setEducation(UserEducation userEducation) {
        f6549g.put("education", (Object) userEducation);
    }

    public static String getZipcode() {
        return (String) f6549g.get("zipcode");
    }

    public static void setZipcode(String str) {
        f6549g.put("zipcode", (Object) str);
    }

    public static String[] getInterests() {
        return (String[]) f6549g.get("interests");
    }

    public static void setInterests(String[] strArr) {
        f6549g.put("interests", (Object) strArr);
    }

    public static Boolean getIap() {
        return (Boolean) f6549g.get("iap");
    }

    public static void setIap(Boolean bool) {
        f6549g.put("iap", (Object) bool);
    }

    public static Float getIapAmount() {
        return (Float) f6549g.get("iap_amount");
    }

    public static void setIapAmount(Float f) {
        f6549g.put("iap_amount", (Object) f);
    }

    public static Integer getNumberOfSessions() {
        return (Integer) f6549g.get("number_of_sessions");
    }

    public static void setNumberOfSessions(Integer num) {
        f6549g.put("number_of_sessions", (Object) num);
    }

    public static Long getPsTime() {
        return (Long) f6549g.get("ps_time");
    }

    public static void setPsTime(Long l) {
        f6549g.put("ps_time", (Object) l);
    }

    public static Long getLastSession() {
        return (Long) f6549g.get("last_session");
    }

    public static void setLastSession(Long l) {
        f6549g.put("last_session", (Object) l);
    }

    public static UserConnection getConnection() {
        return (UserConnection) f6549g.get("connection");
    }

    public static void setConnection(UserConnection userConnection) {
        f6549g.put("connection", (Object) userConnection);
    }

    public static String getDevice() {
        return (String) f6549g.get("device");
    }

    public static void setDevice(String str) {
        f6549g.put("device", (Object) str);
    }

    public static String getAppVersion() {
        return (String) f6549g.get("app_version");
    }

    public static void setAppVersion(String str) {
        f6549g.put("app_version", (Object) str);
    }

    public static void addCustomValue(String str, Object obj) {
        if (f6549g.f6552c.contains(str)) {
            FyberLogger.m8452v("User", str + " is a reserved key for this HashMap, please select another name.");
        } else {
            f6549g.put(str, obj);
        }
    }

    public static Object getCustomValue(String str) {
        return f6549g.get(str);
    }

    public static String mapToString() {
        if (f6549g.f6551b) {
            String str;
            FyberLogger.m8448d("User", "User data has changed, recreating...");
            User user = f6549g;
            C2656g a = Fyber.getConfigs().m7597a();
            if (a != null) {
                LocationManager g = a.m8504g();
                if (user.f6553d == null && g != null) {
                    Calendar instance = Calendar.getInstance();
                    if (user.f6555f == null || instance.after(user.f6555f)) {
                        for (String str2 : a.m8505h()) {
                            Location lastKnownLocation = g.getLastKnownLocation(str2);
                            if (lastKnownLocation != null) {
                                if (user.f6554e == null) {
                                    user.f6554e = lastKnownLocation;
                                }
                                if (user.f6554e != null && user.f6554e.getTime() < lastKnownLocation.getTime()) {
                                    user.f6554e = lastKnownLocation;
                                }
                            }
                        }
                        if (user.f6554e != null) {
                            Calendar instance2 = Calendar.getInstance();
                            instance2.add(5, -1);
                            if (user.f6554e.getTime() > instance2.getTimeInMillis()) {
                                user.m8445a(user.f6554e);
                                user.f6555f = instance;
                                user.f6555f.add(12, 10);
                            }
                        }
                    }
                }
            }
            Builder builder = new Builder();
            for (Entry entry : f6549g.entrySet()) {
                String format;
                str2 = (String) entry.getKey();
                Object value = entry.getValue();
                if (value instanceof Date) {
                    format = String.format(Locale.ENGLISH, "%tY/%tm/%td", new Object[]{value, value, value});
                } else if (value instanceof String[]) {
                    format = TextUtils.join(",", (String[]) value);
                } else {
                    format = value.toString();
                }
                builder.appendQueryParameter(str2, format);
            }
            f6549g.f6550a = builder.build().getEncodedQuery();
            FyberLogger.m8448d("User", "User data - " + f6549g.f6550a);
            f6549g.f6551b = false;
        }
        return f6549g.f6550a;
    }

    public final Object put(String str, Object obj) {
        if (!StringUtils.notNullNorEmpty(str) || obj == null) {
            return null;
        }
        if (!this.f6551b) {
            Object obj2 = get(str);
            boolean z = obj2 == null || !obj2.equals(obj);
            this.f6551b = z;
        }
        return super.put(str, obj);
    }

    public final Object remove(Object obj) {
        Object remove = super.remove(obj);
        this.f6551b = remove != null;
        return remove;
    }

    private void m8445a(Location location) {
        if (location != null) {
            put(C1048i.ko, Location.convert(location.getLatitude(), 0));
            put("longt", Location.convert(location.getLongitude(), 0));
            return;
        }
        remove(C1048i.ko);
        remove("longt");
    }
}
