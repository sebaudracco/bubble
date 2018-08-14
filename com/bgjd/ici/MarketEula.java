package com.bgjd.ici;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.aa;
import com.bgjd.ici.p032h.C1512c;
import com.bgjd.ici.p032h.C1513d;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MarketEula extends Activity implements OnClickListener {
    C1393a f2031a = null;
    private boolean f2032b = false;
    private boolean f2033c = false;
    private List<String> f2034d = new ArrayList();

    public static class C1384a {
        @C1513d(id = "btnOk")
        public Button f2024a;
        @C1513d(id = "btnCancel")
        public Button f2025b;
        @C1513d(id = "webView")
        public WebView f2026c;
        @C1513d(id = "txtTitle")
        public TextView f2027d;
    }

    public static class C1385b {
        @C1513d(id = "webView")
        public WebView f2028a;
        @C1513d(id = "txtTitle")
        public TextView f2029b;
        @C1513d(id = "btnClose")
        public Button f2030c;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(16973835);
        this.f2031a = new aa(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f2032b = extras.getBoolean(C1404b.f2099C);
            this.f2033c = extras.getBoolean("reminder", false);
        }
        requestWindowFeature(1);
        JSONObject j = this.f2031a.mo2267j();
        if (j != null) {
            try {
                View view;
                JSONObject jSONObject = j.getJSONObject("layout");
                String str = C1404b.aE;
                String language = Locale.getDefault().getLanguage();
                if (language != null) {
                    language = language.toLowerCase();
                    if (language != null && language.equalsIgnoreCase("ar")) {
                        str = C1404b.aF;
                    }
                }
                try {
                    String string = j.getString(C1404b.aJ);
                    language = String.format(str, new Object[]{string, this.f2031a.mo2227d()});
                } catch (JSONException e) {
                    language = String.format(str, new Object[]{C1404b.aH, this.f2031a.mo2227d()});
                }
                View createView;
                if (this.f2033c) {
                    createView = C1512c.createView((Context) this, jSONObject, C1385b.class);
                    ((C1385b) createView.getTag()).f2030c.setOnClickListener(this);
                    ((C1385b) createView.getTag()).f2029b.setText(this.f2031a.mo2237g());
                    ((C1385b) createView.getTag()).f2028a.loadData(language, "text/html; charset=utf-8", "UTF-8");
                    ((C1385b) createView.getTag()).f2028a.setScrollbarFadingEnabled(false);
                    view = createView;
                } else {
                    createView = C1512c.createView((Context) this, jSONObject, C1384a.class);
                    ((C1384a) createView.getTag()).f2024a.setOnClickListener(this);
                    ((C1384a) createView.getTag()).f2025b.setOnClickListener(this);
                    ((C1384a) createView.getTag()).f2027d.setText(this.f2031a.mo2237g());
                    ((C1384a) createView.getTag()).f2026c.loadData(language, "text/html; charset=utf-8", "UTF-8");
                    ((C1384a) createView.getTag()).f2026c.setScrollbarFadingEnabled(false);
                    view = createView;
                }
                setContentView(view);
            } catch (JSONException e2) {
            }
        }
        getWindow().setLayout(-1, -1);
        if (m2648a()) {
            this.f2034d.clear();
            try {
                String[] strArr = getPackageManager().getPackageInfo(getPackageName(), 4096).requestedPermissions;
                if (strArr != null && strArr.length > 0) {
                    for (String str2 : strArr) {
                        if (!m2649a(str2)) {
                            this.f2034d.add(str2);
                        }
                    }
                }
            } catch (NameNotFoundException e3) {
            }
            m2646c();
        }
    }

    @SuppressLint({"NewApi"})
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onBackPressed() {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i == 3;
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    private void m2645b() {
        Intent intent = new Intent();
        intent.setClassName(this, C1404b.aQ);
        intent.setAction(C1404b.af);
        intent.putExtra(C1404b.f2099C, this.f2032b);
        startService(intent);
        finish();
    }

    private void m2644a(boolean z) {
        this.f2031a.mo2225c(true);
        this.f2031a.mo2210a(z);
        m2645b();
    }

    public boolean m2648a() {
        return VERSION.SDK_INT >= 23;
    }

    public boolean m2649a(String str) {
        boolean z = true;
        try {
            if (checkPermission(str, Process.myPid(), Process.myUid()) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            try {
                if (((Integer) Class.forName("android.support.v4.content.ContextCompat").getDeclaredMethod("checkSelfPermission", new Class[]{Context.class, String.class}).invoke(null, new Object[]{this, str})).intValue() != 0) {
                    z = false;
                }
                return z;
            } catch (Exception e2) {
                return false;
            }
        }
    }

    private void m2646c() {
        if (this.f2034d.size() > 0) {
            m2647d();
        }
    }

    private void m2647d() {
        try {
            getClass().getMethod("requestPermissions", new Class[]{String[].class, Integer.TYPE}).invoke(this, new Object[]{this.f2034d.toArray(new String[this.f2034d.size()]), Integer.valueOf(HttpStatus.SC_MULTIPLE_CHOICES)});
        } catch (Exception e) {
            try {
                Class.forName("android.support.v4.app.ActivityCompat").getDeclaredMethod("requestPermissions", new Class[]{Activity.class, String[].class, Integer.TYPE}).invoke(null, new Object[]{this, this.f2034d.toArray(new String[this.f2034d.size()]), Integer.valueOf(HttpStatus.SC_MULTIPLE_CHOICES)});
            } catch (Exception e2) {
            }
        }
    }

    public void onClick(View view) {
        switch (Integer.parseInt(view.getTag().toString())) {
            case 200:
                m2644a(true);
                return;
            case 201:
                m2644a(false);
                return;
            case HttpStatus.SC_ACCEPTED /*202*/:
                finish();
                return;
            default:
                return;
        }
    }
}
