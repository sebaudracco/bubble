package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.appnext.base.p019a.p022c.C1028c;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.utils.C4521e;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public final class bm {
    public static final Boolean f11945a = Boolean.valueOf(false);
    public static final int f11946b = YandexMetrica.getLibraryApiLevel();
    static final SparseArray<C4394l> f11947c;
    static final SparseArray<C4394l> f11948d;
    static final HashMap<String, String[]> f11949e;

    static abstract class C4394l {
        protected abstract void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException;

        C4394l() {
        }
    }

    private static class C4395a extends C4394l {
        private C4395a() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    interface C4396v {
        public static final String[] f11937a = new String[]{C1028c.gv, FirebaseAnalytics$Param.VALUE, "type"};
    }

    public static final class aa implements C4396v {
    }

    private static class C4397b extends C4394l {
        private C4397b() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
        }
    }

    static class C4398c extends C4394l {
        C4398c() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL(C4418y.f11941b);
            sQLiteDatabase.execSQL(C4419z.f11943b);
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    static class C4399d extends C4394l {
        C4399d() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS reports");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sessions");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
        }
    }

    static class C4400e extends C4394l {
        C4400e() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS startup (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS permissions (name TEXT PRIMARY KEY,granted INTEGER)");
        }
    }

    static class C4401f extends C4394l {
        C4401f() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device_id_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS api_level_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS startup");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS permissions");
        }
    }

    private static class C4402g extends C4394l {
        private C4402g() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS api_level_info (API_LEVEL INT )");
            ContentValues contentValues = new ContentValues();
            contentValues.put("API_LEVEL", Integer.valueOf(YandexMetrica.getLibraryApiLevel()));
            sQLiteDatabase.insert("api_level_info", "API_LEVEL", contentValues);
        }
    }

    private static class C4403h extends C4394l {
        private C4403h() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS api_level_info");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device_id_info");
        }
    }

    private static class C4404i extends C4394l {
        private C4404i() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS startup (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
        }
    }

    private static class C4405j extends C4394l {
        private C4405j() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS GeoLocationInfo");
        }
    }

    private static final class C4406k extends C4394l {
        private C4406k() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS permissions (name TEXT PRIMARY KEY,granted INTEGER)");
        }
    }

    private static class C4407m extends C4394l {
        private C4407m() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            Cursor cursor = null;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS sessions_BACKUP (");
            stringBuilder.append("id INTEGER,");
            stringBuilder.append("start_time INTEGER,");
            stringBuilder.append("connection_type INTEGER,");
            stringBuilder.append("network_type TEXT,");
            stringBuilder.append("country_code INTEGER,");
            stringBuilder.append("operator_id INTEGER,");
            stringBuilder.append("lac INTEGER,");
            stringBuilder.append("report_request_parameters TEXT );");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            CharSequence stringBuilder2 = new StringBuilder();
            stringBuilder2.append("id,");
            stringBuilder2.append("start_time,");
            stringBuilder2.append("connection_type,");
            stringBuilder2.append("network_type,");
            stringBuilder2.append("country_code,");
            stringBuilder2.append("operator_id,");
            stringBuilder2.append("lac,");
            stringBuilder2.append("report_request_parameters");
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("INSERT INTO sessions_BACKUP");
            stringBuilder3.append(" SELECT ").append(stringBuilder2);
            stringBuilder3.append(" FROM sessions;");
            sQLiteDatabase.execSQL(stringBuilder3.toString());
            sQLiteDatabase.execSQL("DROP TABLE sessions;");
            sQLiteDatabase.execSQL(C4419z.f11943b);
            try {
                cursor = sQLiteDatabase.rawQuery("SELECT * FROM sessions_BACKUP", null);
                while (cursor.moveToNext()) {
                    ContentValues contentValues = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                    List<String> arrayList = new ArrayList();
                    arrayList.add("id");
                    arrayList.add("start_time");
                    arrayList.add("report_request_parameters");
                    ContentValues contentValues2 = new ContentValues(contentValues);
                    for (Entry entry : contentValues.valueSet()) {
                        if (!arrayList.contains(entry.getKey())) {
                            contentValues2.remove((String) entry.getKey());
                        }
                    }
                    for (String remove : arrayList) {
                        contentValues.remove(remove);
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("conn_type", contentValues.getAsInteger("connection_type"));
                    jSONObject.putOpt("net_type", contentValues.get("network_type"));
                    jSONObject.putOpt("operator_id", contentValues.get("operator_id"));
                    jSONObject.putOpt("lac", contentValues.get("lac"));
                    jSONObject.putOpt("country_code", contentValues.get("country_code"));
                    contentValues2.put("network_info", jSONObject.toString());
                    sQLiteDatabase.insertOrThrow("sessions", null, contentValues2);
                }
                sQLiteDatabase.execSQL("DROP TABLE sessions_BACKUP;");
                stringBuilder = new StringBuilder();
                stringBuilder.append("ALTER TABLE reports");
                stringBuilder.append(" ADD COLUMN wifi_network_info");
                stringBuilder.append(" TEXT DEFAULT ''");
                sQLiteDatabase.execSQL(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                stringBuilder.append("ALTER TABLE reports");
                stringBuilder.append(" ADD COLUMN cell_info");
                stringBuilder.append(" TEXT DEFAULT ''");
                sQLiteDatabase.execSQL(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                stringBuilder.append("ALTER TABLE reports");
                stringBuilder.append(" ADD COLUMN location_info");
                stringBuilder.append(" TEXT DEFAULT ''");
                sQLiteDatabase.execSQL(stringBuilder.toString());
            } finally {
                bk.m14978a(cursor);
            }
        }
    }

    private static class C4408n extends C4394l {
        private C4408n() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN environment");
            stringBuilder.append(" TEXT ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN user_info");
            stringBuilder.append(" TEXT ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN session_type");
            stringBuilder.append(" INTEGER DEFAULT ").append(bl.f11933a.m15299a());
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE reports");
            stringBuilder.append(" SET session_type = ");
            stringBuilder.append(bl.BACKGROUND.m15299a());
            stringBuilder.append(" WHERE session_id");
            stringBuilder.append(" = -2");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE sessions");
            stringBuilder.append(" ADD COLUMN server_time_offset");
            stringBuilder.append(" INTEGER ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE sessions");
            stringBuilder.append(" ADD COLUMN type");
            stringBuilder.append(" INTEGER DEFAULT ").append(bl.f11933a.m15299a());
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE sessions");
            stringBuilder.append(" SET type = ");
            stringBuilder.append(bl.BACKGROUND.m15299a());
            stringBuilder.append(" WHERE id");
            stringBuilder.append(" = -2");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class C4409o extends C4394l {
        private static final String f11938a = ("CREATE TABLE IF NOT EXISTS reports (id INTEGER PRIMARY KEY,name TEXT,value TEXT,number INTEGER,type INTEGER,time INTEGER,session_id TEXT,wifi_network_info TEXT DEFAULT '',cell_info TEXT DEFAULT '',location_info TEXT DEFAULT '',error_environment TEXT,user_info TEXT,session_type INTEGER DEFAULT " + bl.f11933a.m15299a() + ",app_environment TEXT DEFAULT '{}',app_environment_revision INTEGER DEFAULT 0 )");

        private C4409o() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            Cursor cursor = null;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN app_environment");
            stringBuilder.append(" TEXT DEFAULT '{}'");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN app_environment_revision");
            stringBuilder.append(" INTEGER DEFAULT 0");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            sQLiteDatabase.execSQL("ALTER TABLE reports RENAME TO reports_backup");
            sQLiteDatabase.execSQL(f11938a);
            try {
                cursor = sQLiteDatabase.rawQuery("SELECT * FROM reports_backup", null);
                while (cursor.moveToNext()) {
                    ContentValues contentValues = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                    String asString = contentValues.getAsString("environment");
                    contentValues.remove("environment");
                    contentValues.put("error_environment", asString);
                    sQLiteDatabase.insert("reports", null, contentValues);
                }
                sQLiteDatabase.execSQL("DROP TABLE reports_backup");
            } finally {
                bk.m14978a(cursor);
            }
        }
    }

    private static class C4410p extends C4394l {
        private C4410p() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN truncated");
            stringBuilder.append(" INTEGER DEFAULT 0");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class C4411q extends C4394l {
        private C4411q() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException, JSONException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN connection_type");
            stringBuilder.append(" INTEGER DEFAULT 2");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN cellular_connection_type");
            stringBuilder.append(" TEXT ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class C4412r extends C4394l {
        private C4412r() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN custom_type");
            stringBuilder.append(" INTEGER DEFAULT 0");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class C4413s extends C4394l {
        private C4413s() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (key TEXT PRIMARY KEY,value TEXT,type INTEGER)");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE reports");
            stringBuilder.append(" ADD COLUMN wifi_access_point");
            stringBuilder.append(" TEXT ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class C4414t extends C4394l {
        private C4414t() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE sessions");
            stringBuilder.append(" ADD COLUMN wifi_network_info");
            stringBuilder.append(" TEXT DEFAULT ''");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private static class C4415u extends C4394l {
        private C4415u() {
        }

        protected void mo7056a(SQLiteDatabase sQLiteDatabase) throws SQLException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE sessions");
            stringBuilder.append(" ADD COLUMN report_request_parameters");
            stringBuilder.append(" TEXT DEFAULT ''");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    public static final class C4416w {
        public static final String[] f11939a = new String[]{"name", "granted"};
    }

    public static final class C4417x implements C4396v {
    }

    public static final class C4418y {
        public static final String[] f11940a = new String[]{"id", "number", "name", FirebaseAnalytics$Param.VALUE, "type", "time", "session_id", "wifi_network_info", "cell_info", "location_info", "error_environment", "user_info", "session_type", "app_environment", "app_environment_revision", "truncated", "connection_type", "cellular_connection_type", "custom_type", "wifi_access_point"};
        static final String f11941b = ("CREATE TABLE IF NOT EXISTS reports (id INTEGER PRIMARY KEY,name TEXT,value TEXT,number INTEGER,type INTEGER,time INTEGER,session_id TEXT,wifi_network_info TEXT DEFAULT '',cell_info TEXT DEFAULT '',location_info TEXT DEFAULT '',error_environment TEXT,user_info TEXT,session_type INTEGER DEFAULT " + bl.f11933a.m15299a() + ",app_environment TEXT DEFAULT '{}',app_environment_revision INTEGER DEFAULT 0,truncated INTEGER DEFAULT 0,connection_type INTEGER DEFAULT 2,cellular_connection_type TEXT,custom_type INTEGER DEFAULT 0, wifi_access_point TEXT )");
    }

    public static final class C4419z {
        public static final String[] f11942a = new String[]{"id", "start_time", "network_info", "report_request_parameters", "server_time_offset", "type"};
        static final String f11943b = ("CREATE TABLE IF NOT EXISTS sessions (id INTEGER,start_time INTEGER,network_info TEXT,report_request_parameters TEXT,server_time_offset INTEGER,type INTEGER DEFAULT " + bl.f11933a.m15299a() + " )");
        public static final String f11944c = String.format(Locale.US, "(select count(%s.%s) from %s where %s.%s = %s.%s) = 0 and %s NOT IN (%s)", new Object[]{"reports", "id", "reports", "reports", "session_id", "sessions", "id", "id", C4521e.m16254a(2)});
    }

    static {
        SparseArray sparseArray = new SparseArray();
        f11947c = sparseArray;
        sparseArray.put(6, new C4414t());
        f11947c.put(7, new C4415u());
        f11947c.put(14, new C4407m());
        f11947c.put(29, new C4408n());
        f11947c.put(37, new C4409o());
        f11947c.put(39, new C4410p());
        f11947c.put(45, new C4411q());
        f11947c.put(47, new C4412r());
        f11947c.put(50, new C4413s());
        sparseArray = new SparseArray();
        f11948d = sparseArray;
        sparseArray.put(12, new C4402g());
        f11948d.put(29, new C4403h());
        f11948d.put(47, new C4404i());
        f11948d.put(50, new C4405j());
        f11948d.put(55, new C4406k());
        HashMap hashMap = new HashMap();
        f11949e = hashMap;
        hashMap.put("reports", C4418y.f11940a);
        f11949e.put("sessions", C4419z.f11942a);
        f11949e.put("preferences", C4417x.a);
    }

    public static bs m15321a() {
        return new bs(new C4398c(), new C4399d(), f11947c, new bu(f11949e));
    }

    public static bs m15322b() {
        HashMap hashMap = new HashMap();
        hashMap.put("preferences", C4417x.a);
        hashMap.put("startup", aa.a);
        hashMap.put("permissions", C4416w.f11939a);
        return new bs(new C4400e(), new C4401f(), f11948d, new bu(hashMap));
    }

    public static bs m15323c() {
        HashMap hashMap = new HashMap();
        hashMap.put("preferences", C4417x.a);
        return new bs(new C4395a(), new C4397b(), new SparseArray(), new bu(hashMap));
    }
}
