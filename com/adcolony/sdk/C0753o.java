package com.adcolony.sdk;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import com.adcolony.sdk.aa.C0595a;
import com.appsgeyser.sdk.configuration.Constants.BannerLoadTags;
import org.json.JSONObject;

class C0753o {
    static AlertDialog f1349a;
    private af f1350b;
    private AlertDialog f1351c;
    private boolean f1352d;

    class C07481 implements ah {
        final /* synthetic */ C0753o f1340a;

        C07481(C0753o c0753o) {
            this.f1340a = c0753o;
        }

        public void mo1863a(af afVar) {
            if (!C0594a.m614d()) {
                new C0595a().m622a("Null Activity reference, can't build AlertDialog.").m624a(aa.f483g);
            } else if (C0802y.m1477d(afVar.m698c(), BannerLoadTags.ON_RESUME)) {
                this.f1340a.f1350b = afVar;
            } else {
                this.f1340a.m1342a(afVar);
            }
        }
    }

    C0753o() {
        C0594a.m609a("Alert.show", new C07481(this));
    }

    void m1342a(final af afVar) {
        if (C0594a.m614d()) {
            final Builder builder = C0594a.m605a().m1284n().m1328s() >= 21 ? new Builder(C0594a.m613c(), 16974374) : new Builder(C0594a.m613c(), 16974126);
            JSONObject c = afVar.m698c();
            CharSequence b = C0802y.m1468b(c, "message");
            CharSequence b2 = C0802y.m1468b(c, "title");
            CharSequence b3 = C0802y.m1468b(c, "positive");
            CharSequence b4 = C0802y.m1468b(c, "negative");
            builder.setMessage(b);
            builder.setTitle(b2);
            builder.setPositiveButton(b3, new OnClickListener(this) {
                final /* synthetic */ C0753o f1342b;

                public void onClick(DialogInterface dialog, int which) {
                    this.f1342b.f1351c = null;
                    dialog.dismiss();
                    JSONObject a = C0802y.m1453a();
                    C0802y.m1465a(a, "positive", true);
                    this.f1342b.f1352d = false;
                    afVar.m694a(a).m695b();
                }
            });
            if (!b4.equals("")) {
                builder.setNegativeButton(b4, new OnClickListener(this) {
                    final /* synthetic */ C0753o f1344b;

                    public void onClick(DialogInterface dialog, int which) {
                        this.f1344b.f1351c = null;
                        dialog.dismiss();
                        JSONObject a = C0802y.m1453a();
                        C0802y.m1465a(a, "positive", false);
                        this.f1344b.f1352d = false;
                        afVar.m694a(a).m695b();
                    }
                });
            }
            builder.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ C0753o f1346b;

                public void onCancel(DialogInterface dialog) {
                    this.f1346b.f1351c = null;
                    this.f1346b.f1352d = false;
                    JSONObject a = C0802y.m1453a();
                    C0802y.m1465a(a, "positive", false);
                    afVar.m694a(a).m695b();
                }
            });
            az.m880a(new Runnable(this) {
                final /* synthetic */ C0753o f1348b;

                public void run() {
                    this.f1348b.f1352d = true;
                    this.f1348b.f1351c = builder.show();
                }
            });
        }
    }

    void m1340a() {
        if (this.f1350b != null) {
            m1342a(this.f1350b);
            this.f1350b = null;
        }
    }

    AlertDialog m1343b() {
        return this.f1351c;
    }

    void m1341a(AlertDialog alertDialog) {
        this.f1351c = alertDialog;
    }

    boolean m1344c() {
        return this.f1352d;
    }
}
