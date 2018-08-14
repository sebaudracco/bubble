package com.appnext.core;

import android.os.AsyncTask;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class C0921q {
    protected static final String mu = "https://cdn.appnext.com/tools/sdk/config/2.2.5";
    protected HashMap<String, Object> mv = null;
    private ArrayList<C0909a> mw;
    private int state = 0;

    public interface C0909a {
        void mo1968a(HashMap<String, Object> hashMap);

        void error(String str);
    }

    private class C1140b extends AsyncTask<Object, Void, String> {
        final /* synthetic */ C0921q mx;

        private C1140b(C0921q c0921q) {
            this.mx = c0921q;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2381a(objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            aV((String) obj);
        }

        protected String m2381a(Object... objArr) {
            try {
                return C1128g.m2336a((String) objArr[0], (HashMap) objArr[1]);
            } catch (HttpRetryException e) {
                return "error: " + e.getReason();
            } catch (Throwable e2) {
                C1128g.m2351c(e2);
                return "error: network problem";
            } catch (Throwable th) {
                return "error: Internal error";
            }
        }

        protected void aV(String str) {
            super.onPostExecute(str);
            if (str == null) {
                this.mx.state = 0;
                this.mx.aU("unknown error");
            } else if (str.startsWith("error:")) {
                this.mx.state = 0;
                this.mx.aU(str.substring("error: ".length()));
            } else {
                try {
                    Map o = this.mx.m1922o(str);
                    if (this.mx.mv == null) {
                        this.mx.mv = o;
                    } else {
                        this.mx.mv.putAll(o);
                    }
                    this.mx.state = 2;
                    this.mx.m1917d(this.mx.mv);
                } catch (Throwable th) {
                    C1128g.m2333W(str);
                    C1128g.m2333W("error " + th.getMessage());
                    this.mx.state = 0;
                    this.mx.aU("parsing error");
                }
                C1128g.m2333W("finished loading config");
            }
        }
    }

    protected abstract HashMap<String, String> mo1982D();

    protected abstract HashMap<String, String> mo1983E();

    protected abstract String getUrl();

    public synchronized void m1920a(C0909a c0909a) {
        if (this.mw == null) {
            this.mw = new ArrayList();
        }
        if (this.state != 2) {
            if (this.state == 0) {
                this.state = 1;
                C1128g.m2333W("start loading config from " + getUrl());
                new C1140b().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{getUrl(), mo1982D()});
            }
            if (c0909a != null) {
                this.mw.add(c0909a);
            }
        } else if (c0909a != null) {
            c0909a.mo1968a(this.mv);
        }
    }

    public HashMap<String, Object> de() {
        return this.mv;
    }

    public void m1921b(String str, String str2) {
        if (this.mv == null) {
            this.mv = new HashMap();
        }
        this.mv.put(str, str2);
    }

    public boolean isLoaded() {
        return this.state == 2;
    }

    public String getValue(String str) {
        if (de() != null && de().containsKey(str)) {
            return (String) de().get(str);
        }
        return null;
    }

    public String get(String str) {
        if (de() != null) {
            if (mo1983E().containsKey(str)) {
                return get(str, (String) mo1983E().get(str));
            }
            return getValue(str);
        } else if (mo1983E().containsKey(str)) {
            return (String) mo1983E().get(str);
        } else {
            return null;
        }
    }

    public String get(String str, String str2) {
        return getValue(str) == null ? str2 : getValue(str);
    }

    protected HashMap<String, Object> m1922o(String str) throws JSONException {
        HashMap<String, Object> hashMap = new HashMap();
        JSONObject jSONObject = new JSONObject(str);
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            String string = jSONObject.getString(str2);
            try {
                JSONObject jSONObject2 = new JSONObject(string);
                Iterator keys2 = jSONObject2.keys();
                while (keys2.hasNext()) {
                    String str3 = (String) keys2.next();
                    hashMap.put(str2 + BridgeUtil.UNDERLINE_STR + str3, jSONObject2.getString(str3));
                }
            } catch (Throwable th) {
                hashMap.put(str2, string);
            }
        }
        return hashMap;
    }

    private void aU(String str) {
        synchronized ("https://cdn.appnext.com/tools/sdk/config/2.2.5") {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.mw);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C0909a c0909a = (C0909a) it.next();
                if (c0909a != null) {
                    c0909a.error(str);
                }
            }
            this.mw.clear();
        }
    }

    private void m1917d(HashMap<String, Object> hashMap) {
        synchronized ("https://cdn.appnext.com/tools/sdk/config/2.2.5") {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.mw);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((C0909a) it.next()).mo1968a(hashMap);
            }
            this.mw.clear();
        }
    }
}
