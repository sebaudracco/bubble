package com.facebook.ads.internal.p069m;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.p056q.p057a.C2129r;
import com.facebook.ads.internal.p062e.C1967c;
import com.facebook.ads.internal.p062e.C1973d;
import com.facebook.ads.internal.p063f.C1981e;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.p069m.C2011b.C2010a;
import com.mopub.common.Constants;
import com.stripe.android.net.StripeApiHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C2017g implements C2010a {
    private static final String f4768a = C2017g.class.getSimpleName();
    private Context f4769b;
    private C1973d f4770c;

    @VisibleForTesting
    public C2017g(Context context, C1973d c1973d) {
        this.f4769b = context;
        this.f4770c = c1973d;
    }

    private static JSONArray m6427a(JSONArray jSONArray, JSONArray jSONArray2) {
        int i = 0;
        if (jSONArray != null) {
            i = 0 + jSONArray.length();
        }
        if (jSONArray2 != null) {
            i += jSONArray2.length();
        }
        return C2017g.m6428a(jSONArray, jSONArray2, i);
    }

    private static JSONArray m6428a(JSONArray jSONArray, JSONArray jSONArray2, int i) {
        if (jSONArray == null) {
            return jSONArray2;
        }
        if (jSONArray2 == null) {
            return jSONArray;
        }
        int length = jSONArray.length();
        int length2 = jSONArray2.length();
        JSONArray jSONArray3 = new JSONArray();
        Object obj = null;
        double d = Double.MAX_VALUE;
        double d2 = Double.MAX_VALUE;
        Object obj2 = null;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if ((i3 < length || i2 < length2) && i > 0) {
                int i4;
                JSONObject jSONObject;
                double d3;
                Object obj3;
                int i5;
                if (i3 >= length || obj != null) {
                    i4 = i3;
                } else {
                    try {
                        jSONObject = jSONArray.getJSONObject(i3);
                        d3 = jSONObject.getDouble("time");
                    } catch (JSONException e) {
                        jSONObject = null;
                        d3 = Double.MAX_VALUE;
                    }
                    d = d3;
                    i4 = i3 + 1;
                    obj = jSONObject;
                }
                if (i2 >= length2 || obj2 != null) {
                    d3 = d2;
                    obj3 = obj2;
                    i5 = i2;
                } else {
                    try {
                        jSONObject = jSONArray2.getJSONObject(i2);
                        d3 = jSONObject.getDouble("time");
                    } catch (JSONException e2) {
                        jSONObject = null;
                        d3 = Double.MAX_VALUE;
                    }
                    obj3 = jSONObject;
                    i5 = i2 + 1;
                }
                if (obj == null && obj3 == null) {
                    d2 = d3;
                    i2 = i5;
                    obj2 = obj3;
                    i3 = i4;
                } else {
                    Object obj4;
                    Object obj5;
                    double d4;
                    if (obj == null || d3 < d) {
                        jSONArray3.put(obj3);
                        d3 = Double.MAX_VALUE;
                        double d5 = d;
                        obj4 = null;
                        obj5 = obj;
                        d4 = d5;
                    } else {
                        jSONArray3.put(obj);
                        d4 = Double.MAX_VALUE;
                        obj5 = null;
                        obj4 = obj3;
                    }
                    i--;
                    d2 = d3;
                    i2 = i5;
                    i3 = i4;
                    obj2 = obj4;
                    obj = obj5;
                    d = d4;
                }
            }
        }
        if (i > 0) {
            if (obj != null) {
                jSONArray3.put(obj);
            } else if (obj2 != null) {
                jSONArray3.put(obj2);
            }
        }
        return jSONArray3;
    }

    private JSONObject m6429a(int i) {
        Cursor d;
        Cursor a;
        Cursor cursor;
        Throwable th;
        try {
            d = this.f4770c.m6225d();
            try {
                a = this.f4770c.m6217a(i);
            } catch (JSONException e) {
                cursor = null;
                a = d;
                if (a != null) {
                    a.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                a = null;
                if (d != null) {
                    d.close();
                }
                if (a != null) {
                    a.close();
                }
                throw th;
            }
            try {
                JSONArray c;
                Object obj;
                JSONArray a2;
                Object a3;
                JSONObject jSONObject;
                if (a.getCount() > 0) {
                    JSONObject a4 = m6430a(a);
                    c = m6433c(a);
                    obj = a4;
                } else {
                    c = null;
                    obj = null;
                }
                if (C2005a.m6346g(this.f4769b)) {
                    a2 = C1981e.m6247a(this.f4769b, i);
                    if (a2 != null && a2.length() > 0) {
                        a3 = C2017g.m6428a(a2, c, i);
                        if (a3 == null) {
                            jSONObject = new JSONObject();
                            if (obj != null) {
                                jSONObject.put(StripeApiHandler.TOKENS, obj);
                            }
                            jSONObject.put(Constants.VIDEO_TRACKING_EVENTS_KEY, a3);
                        } else {
                            jSONObject = null;
                        }
                        if (d != null) {
                            d.close();
                        }
                        if (a != null) {
                            return jSONObject;
                        }
                        a.close();
                        return jSONObject;
                    }
                }
                a2 = c;
                if (a3 == null) {
                    jSONObject = null;
                } else {
                    jSONObject = new JSONObject();
                    if (obj != null) {
                        jSONObject.put(StripeApiHandler.TOKENS, obj);
                    }
                    jSONObject.put(Constants.VIDEO_TRACKING_EVENTS_KEY, a3);
                }
                if (d != null) {
                    d.close();
                }
                if (a != null) {
                    return jSONObject;
                }
                a.close();
                return jSONObject;
            } catch (JSONException e2) {
                cursor = a;
                a = d;
                if (a != null) {
                    a.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (d != null) {
                    d.close();
                }
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        } catch (JSONException e3) {
            cursor = null;
            a = null;
            if (a != null) {
                a.close();
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            a = null;
            d = null;
            if (d != null) {
                d.close();
            }
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    private JSONObject m6430a(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        while (cursor.moveToNext()) {
            jSONObject.put(cursor.getString(0), cursor.getString(1));
        }
        return jSONObject;
    }

    private void m6431a(String str) {
        if (C1981e.m6256c(str)) {
            C1981e.m6250a(str);
        } else {
            this.f4770c.m6221a(str);
        }
    }

    private JSONArray m6432b(Cursor cursor) {
        JSONArray jSONArray = new JSONArray();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", cursor.getString(C1967c.f4562a.f4558a));
            jSONObject.put("token_id", cursor.getString(C1967c.f4563b.f4558a));
            jSONObject.put("type", cursor.getString(C1967c.f4565d.f4558a));
            jSONObject.put("time", C2129r.m6818a(cursor.getDouble(C1967c.f4566e.f4558a)));
            jSONObject.put("session_time", C2129r.m6818a(cursor.getDouble(C1967c.f4567f.f4558a)));
            jSONObject.put("session_id", cursor.getString(C1967c.f4568g.f4558a));
            String string = cursor.getString(C1967c.f4569h.f4558a);
            jSONObject.put("data", string != null ? new JSONObject(string) : new JSONObject());
            jSONObject.put("attempt", cursor.getString(C1967c.f4570i.f4558a));
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private JSONArray m6433c(Cursor cursor) {
        JSONArray jSONArray = new JSONArray();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", cursor.getString(2));
            jSONObject.put("token_id", cursor.getString(0));
            jSONObject.put("type", cursor.getString(4));
            jSONObject.put("time", C2129r.m6818a(cursor.getDouble(5)));
            jSONObject.put("session_time", C2129r.m6818a(cursor.getDouble(6)));
            jSONObject.put("session_id", cursor.getString(7));
            String string = cursor.getString(8);
            jSONObject.put("data", string != null ? new JSONObject(string) : new JSONObject());
            jSONObject.put("attempt", cursor.getString(9));
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private JSONObject m6434d() {
        Cursor f;
        Cursor e;
        Cursor cursor;
        Throwable th;
        try {
            f = this.f4770c.m6227f();
            try {
                e = this.f4770c.m6226e();
            } catch (JSONException e2) {
                cursor = null;
                e = f;
                if (e != null) {
                    e.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                e = null;
                if (f != null) {
                    f.close();
                }
                if (e != null) {
                    e.close();
                }
                throw th;
            }
            try {
                JSONArray jSONArray;
                Object obj;
                JSONArray a;
                Object a2;
                JSONObject jSONObject;
                if (f.getCount() <= 0 || e.getCount() <= 0) {
                    jSONArray = null;
                    obj = null;
                } else {
                    JSONObject a3 = m6430a(f);
                    jSONArray = m6432b(e);
                    obj = a3;
                }
                if (C2005a.m6346g(this.f4769b)) {
                    a = C1981e.m6246a(this.f4769b);
                    if (a != null && a.length() > 0) {
                        a2 = C2017g.m6427a(a, jSONArray);
                        if (a2 == null) {
                            jSONObject = new JSONObject();
                            if (obj != null) {
                                jSONObject.put(StripeApiHandler.TOKENS, obj);
                            }
                            jSONObject.put(Constants.VIDEO_TRACKING_EVENTS_KEY, a2);
                        } else {
                            jSONObject = null;
                        }
                        if (f != null) {
                            f.close();
                        }
                        if (e != null) {
                            return jSONObject;
                        }
                        e.close();
                        return jSONObject;
                    }
                }
                a = jSONArray;
                if (a2 == null) {
                    jSONObject = null;
                } else {
                    jSONObject = new JSONObject();
                    if (obj != null) {
                        jSONObject.put(StripeApiHandler.TOKENS, obj);
                    }
                    jSONObject.put(Constants.VIDEO_TRACKING_EVENTS_KEY, a2);
                }
                if (f != null) {
                    f.close();
                }
                if (e != null) {
                    return jSONObject;
                }
                e.close();
                return jSONObject;
            } catch (JSONException e3) {
                cursor = e;
                e = f;
                if (e != null) {
                    e.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (f != null) {
                    f.close();
                }
                if (e != null) {
                    e.close();
                }
                throw th;
            }
        } catch (JSONException e4) {
            cursor = null;
            e = null;
            if (e != null) {
                e.close();
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            e = null;
            f = null;
            if (f != null) {
                f.close();
            }
            if (e != null) {
                e.close();
            }
            throw th;
        }
    }

    public JSONObject mo3718a() {
        int j = C2005a.m6349j(this.f4769b);
        return j > 0 ? m6429a(j) : m6434d();
    }

    public boolean mo3719a(JSONArray jSONArray) {
        boolean g = C2005a.m6346g(this.f4769b);
        boolean z = true;
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("id");
                int i2 = jSONObject.getInt("code");
                if (i2 == 1) {
                    if (!this.f4770c.m6223b(string) && g) {
                        C1981e.m6254b(string);
                    }
                } else if (i2 >= 1000 && i2 < 2000) {
                    m6431a(string);
                    z = false;
                } else if (i2 >= 2000 && i2 < 3000 && !this.f4770c.m6223b(string) && g) {
                    C1981e.m6254b(string);
                }
            } catch (JSONException e) {
            }
        }
        return z;
    }

    public void mo3720b() {
        this.f4770c.m6228g();
        this.f4770c.m6222b();
        C1981e.m6255c(this.f4769b);
    }

    public void mo3721b(JSONArray jSONArray) {
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                m6431a(jSONArray.getJSONObject(i).getString("id"));
            } catch (JSONException e) {
            }
        }
    }

    public boolean mo3722c() {
        Throwable th;
        Cursor cursor;
        boolean z = true;
        int j = C2005a.m6349j(this.f4769b);
        if (j < 1) {
            return false;
        }
        Cursor cursor2 = null;
        try {
            cursor2 = this.f4770c.m6225d();
            try {
                if ((cursor2.moveToFirst() ? cursor2.getInt(0) : 0) + C1981e.m6252b(this.f4769b) <= j) {
                    z = false;
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                cursor = cursor2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = cursor2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
}
