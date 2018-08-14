package com.bgjd.ici.p025b;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p029d.C1451c;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import org.telegram.tgnet.ConnectionsManager;

public class C1428v implements C1401f {
    private static final String f2208a = "REGRESP";
    private JSONObject f2209b;
    private JSONObject f2210c;
    private C1395h f2211d;

    public C1428v(C1395h c1395h, JSONObject jSONObject) {
        this.f2211d = c1395h;
        this.f2209b = jSONObject;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo2311a() {
        /*
        r11 = this;
        r10 = 0;
        r9 = 1;
        r0 = r11.f2209b;
        r1 = "status";
        r0 = r0.isNull(r1);
        if (r0 != 0) goto L_0x0055;
    L_0x000d:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r1 = "status";
        r8 = r0.getInt(r1);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2211d;	 Catch:{ JSONException -> 0x0068 }
        r0 = r0.getPreferences();	 Catch:{ JSONException -> 0x0068 }
        r1 = r0.edit();	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r2 = "settings";
        r0 = r0.has(r2);	 Catch:{ JSONException -> 0x0068 }
        if (r0 == 0) goto L_0x004f;
    L_0x002b:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r2 = "settings";
        r0 = r0.getString(r2);	 Catch:{ JSONException -> 0x0068 }
        r2 = r0.length();	 Catch:{ JSONException -> 0x0068 }
        r3 = 5;
        if (r2 <= r3) goto L_0x004f;
    L_0x003b:
        r2 = "settings";
        r1.putString(r2, r0);	 Catch:{ JSONException -> 0x0068 }
        r2 = new com.bgjd.ici.json.JSONObject;	 Catch:{ JSONException -> 0x0056 }
        r2.<init>(r0);	 Catch:{ JSONException -> 0x0056 }
        r11.f2210c = r2;	 Catch:{ JSONException -> 0x0056 }
        r0 = r11.f2211d;	 Catch:{ JSONException -> 0x0056 }
        r2 = r11.f2210c;	 Catch:{ JSONException -> 0x0056 }
        r0.mo2215b(r2);	 Catch:{ JSONException -> 0x0056 }
    L_0x004f:
        switch(r8) {
            case 200: goto L_0x0078;
            case 300: goto L_0x00b0;
            case 400: goto L_0x0109;
            case 401: goto L_0x010e;
            case 600: goto L_0x0119;
            case 700: goto L_0x013e;
            case 800: goto L_0x016c;
            case 801: goto L_0x018e;
            case 802: goto L_0x01b0;
            case 803: goto L_0x01d2;
            default: goto L_0x0052;
        };
    L_0x0052:
        r11.m2949a(r8);	 Catch:{ JSONException -> 0x0068 }
    L_0x0055:
        return;
    L_0x0056:
        r0 = move-exception;
        r2 = "REGRESP";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ JSONException -> 0x0068 }
        r4 = 0;
        r5 = r0.getMessage();	 Catch:{ JSONException -> 0x0068 }
        r3[r4] = r5;	 Catch:{ JSONException -> 0x0068 }
        com.bgjd.ici.p025b.C1402i.m2898a(r2, r0, r3);	 Catch:{ JSONException -> 0x0068 }
        goto L_0x004f;
    L_0x0068:
        r0 = move-exception;
        r1 = "REGRESP";
        r2 = new java.lang.String[r9];
        r3 = r0.getMessage();
        r2[r10] = r3;
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r2);
        goto L_0x0055;
    L_0x0078:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r2 = "eula";
        r2 = r0.getString(r2);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r3 = "settings";
        r3 = r0.getString(r3);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r4 = "has_shown";
        r4 = r0.getBoolean(r4);	 Catch:{ JSONException -> 0x0068 }
        r5 = 1;
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r6 = "is_agree";
        r6 = r0.getBoolean(r6);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r7 = "is_blacklisted";
        r7 = r0.getBoolean(r7);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11;
        r0.m2952a(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r11.m2951a(r1, r0);	 Catch:{ JSONException -> 0x0068 }
        goto L_0x0052;
    L_0x00b0:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r2 = "eula";
        r2 = r0.getString(r2);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r3 = "settings";
        r3 = r0.getString(r3);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r4 = "has_shown";
        r4 = r0.getBoolean(r4);	 Catch:{ JSONException -> 0x0068 }
        r5 = 1;
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r6 = "is_agree";
        r6 = r0.getBoolean(r6);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r7 = "is_blacklisted";
        r7 = r0.getBoolean(r7);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11;
        r0.m2952a(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r11.m2951a(r1, r0);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r1 = "next";
        r0 = r0.has(r1);	 Catch:{ JSONException -> 0x0068 }
        if (r0 == 0) goto L_0x0052;
    L_0x00f2:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r1 = "next";
        r0 = r0.getInt(r1);	 Catch:{ JSONException -> 0x0068 }
        r1 = r11.f2211d;	 Catch:{ JSONException -> 0x0068 }
        r2 = (long) r0;	 Catch:{ JSONException -> 0x0068 }
        r1.mo2223c(r2);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2211d;	 Catch:{ JSONException -> 0x0068 }
        r1 = 1;
        r0.mo2238g(r1);	 Catch:{ JSONException -> 0x0068 }
        goto L_0x0052;
    L_0x0109:
        r11.m2955b(r1);	 Catch:{ JSONException -> 0x0068 }
        goto L_0x0052;
    L_0x010e:
        r11.m2955b(r1);	 Catch:{ JSONException -> 0x0068 }
        r0 = r11.f2211d;	 Catch:{ JSONException -> 0x0068 }
        r1 = 1;
        r0.mo2235f(r1);	 Catch:{ JSONException -> 0x0068 }
        goto L_0x0052;
    L_0x0119:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r2 = "message";
        r0 = r0.has(r2);	 Catch:{ JSONException -> 0x0068 }
        if (r0 == 0) goto L_0x0139;
    L_0x0124:
        r0 = "REGRESP";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ JSONException -> 0x0068 }
        r3 = 0;
        r4 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r5 = "message";
        r4 = r4.getString(r5);	 Catch:{ JSONException -> 0x0068 }
        r2[r3] = r4;	 Catch:{ JSONException -> 0x0068 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r2);	 Catch:{ JSONException -> 0x0068 }
    L_0x0139:
        r11.m2950a(r1);	 Catch:{ JSONException -> 0x0068 }
        goto L_0x0052;
    L_0x013e:
        r0 = r11.f2210c;	 Catch:{ JSONException -> 0x0068 }
        if (r0 == 0) goto L_0x0052;
    L_0x0142:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r2 = "logger";
        r0 = r0.getString(r2);	 Catch:{ JSONException -> 0x0068 }
        if (r0 == 0) goto L_0x0052;
    L_0x014d:
        r2 = r0.length();	 Catch:{ JSONException -> 0x0068 }
        if (r2 <= 0) goto L_0x0052;
    L_0x0153:
        r2 = r11.f2210c;	 Catch:{ JSONException -> 0x0068 }
        r3 = "logger";
        r2.put(r3, r0);	 Catch:{ JSONException -> 0x0068 }
        r0 = "settings";
        r2 = r11.f2210c;	 Catch:{ JSONException -> 0x0068 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x0068 }
        r1.putString(r0, r2);	 Catch:{ JSONException -> 0x0068 }
        r1.commit();	 Catch:{ JSONException -> 0x0068 }
        goto L_0x0052;
    L_0x016c:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r1 = "message";
        r0 = r0.has(r1);	 Catch:{ JSONException -> 0x0068 }
        if (r0 == 0) goto L_0x0052;
    L_0x0177:
        r0 = "REGRESP";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ JSONException -> 0x0068 }
        r2 = 0;
        r3 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r4 = "message";
        r3 = r3.getString(r4);	 Catch:{ JSONException -> 0x0068 }
        r1[r2] = r3;	 Catch:{ JSONException -> 0x0068 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r1);	 Catch:{ JSONException -> 0x0068 }
        goto L_0x0052;
    L_0x018e:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r1 = "message";
        r0 = r0.has(r1);	 Catch:{ JSONException -> 0x0068 }
        if (r0 == 0) goto L_0x0052;
    L_0x0199:
        r0 = "REGRESP";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ JSONException -> 0x0068 }
        r2 = 0;
        r3 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r4 = "message";
        r3 = r3.getString(r4);	 Catch:{ JSONException -> 0x0068 }
        r1[r2] = r3;	 Catch:{ JSONException -> 0x0068 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r1);	 Catch:{ JSONException -> 0x0068 }
        goto L_0x0052;
    L_0x01b0:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r1 = "message";
        r0 = r0.has(r1);	 Catch:{ JSONException -> 0x0068 }
        if (r0 == 0) goto L_0x0052;
    L_0x01bb:
        r0 = "REGRESP";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ JSONException -> 0x0068 }
        r2 = 0;
        r3 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r4 = "message";
        r3 = r3.getString(r4);	 Catch:{ JSONException -> 0x0068 }
        r1[r2] = r3;	 Catch:{ JSONException -> 0x0068 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r1);	 Catch:{ JSONException -> 0x0068 }
        goto L_0x0052;
    L_0x01d2:
        r0 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r2 = "message";
        r0 = r0.has(r2);	 Catch:{ JSONException -> 0x0068 }
        if (r0 == 0) goto L_0x01f2;
    L_0x01dd:
        r0 = "REGRESP";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ JSONException -> 0x0068 }
        r3 = 0;
        r4 = r11.f2209b;	 Catch:{ JSONException -> 0x0068 }
        r5 = "message";
        r4 = r4.getString(r5);	 Catch:{ JSONException -> 0x0068 }
        r2[r3] = r4;	 Catch:{ JSONException -> 0x0068 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r2);	 Catch:{ JSONException -> 0x0068 }
    L_0x01f2:
        r11.m2950a(r1);	 Catch:{ JSONException -> 0x0068 }
        goto L_0x0052;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bgjd.ici.b.v.a():void");
    }

    private void m2949a(int i) {
        String z = this.f2211d.mo2295z();
        boolean z2 = false;
        boolean Q = this.f2211d.mo2190Q();
        String O = this.f2211d.mo2188O();
        if (!(!this.f2211d.mo2192S() || Q || O.equalsIgnoreCase("default"))) {
            Q = true;
        }
        try {
            C1451c.m2986a(this.f2211d.getContext());
            String a = C1451c.m2985a();
            z2 = C1451c.m2987b();
            this.f2211d.mo2216b(a);
            this.f2211d.mo2232e(z2);
            this.f2211d.mo2201a(System.currentTimeMillis() / 1000);
        } catch (Exception e) {
        }
        if (!Q && !z2 && z.equalsIgnoreCase(C1404b.aw) && i == 200) {
            C1402i.m2901b(f2208a, "Activity Started...");
            Intent intent = new Intent();
            intent.setClassName(this.f2211d.getContext(), C1404b.aR);
            intent.putExtra(C1404b.f2099C, this.f2211d.IsSandbox());
            intent.setFlags(ErrorDialogData.BINDER_CRASH);
            intent.addFlags(131072);
            intent.addFlags(ConnectionsManager.FileTypeFile);
            this.f2211d.getContext().startActivity(intent);
        } else if (z2 && z.equalsIgnoreCase(C1404b.aw) && i == 200) {
            C1402i.m2901b(f2208a, "Declining Activity...");
            m2953a(false);
        } else if (!Q || !this.f2211d.mo2192S() || z2 || i != 200) {
        } else {
            if (this.f2211d.isAccepted() && !this.f2211d.mo2226c()) {
                this.f2211d.mo2225c(true);
                this.f2211d.mo2210a(true);
                this.f2211d.mo2223c(1500);
            } else if (this.f2211d.mo2211a() && !this.f2211d.isAccepted() && !this.f2211d.mo2226c()) {
                this.f2211d.mo2210a(false);
                this.f2211d.mo2223c(1500);
            } else if (!this.f2211d.mo2211a() && !this.f2211d.isAccepted() && !this.f2211d.mo2226c() && (O.equalsIgnoreCase(C1404b.aw) || O.equalsIgnoreCase(C1404b.ax))) {
                String P = this.f2211d.mo2189P();
                if (P.length() > 10 && O.equalsIgnoreCase(C1404b.aw)) {
                    C1402i.m2901b(f2208a, "Partner Activity Started...");
                    this.f2211d.mo2225c(false);
                    this.f2211d.mo2210a(false);
                    Intent intent2 = new Intent();
                    intent2.setClassName(this.f2211d.getContext(), P);
                    intent2.putExtra(C1404b.f2099C, this.f2211d.IsSandbox());
                    intent2.setFlags(ErrorDialogData.BINDER_CRASH);
                    intent2.addFlags(131072);
                    intent2.addFlags(ConnectionsManager.FileTypeFile);
                    this.f2211d.getContext().startActivity(intent2);
                } else if (O.equalsIgnoreCase(C1404b.ax)) {
                    C1402i.m2901b(f2208a, "Dialog EULA Started...");
                    this.f2211d.mo2225c(true);
                    this.f2211d.mo2223c(1500);
                } else {
                    C1402i.m2901b(f2208a, "Missing Partner Activity...");
                }
            } else if (!this.f2211d.mo2192S() && Q && !z2 && i == 200) {
                C1402i.m2901b(f2208a, "The SDK in used is not flag as partner, using custom EULA activity may not work, please contact support for more information.");
            }
        }
    }

    private void m2954b() {
        Intent intent = new Intent();
        intent.setClassName(this.f2211d.getContext(), C1404b.aQ);
        intent.setAction(C1404b.af);
        intent.putExtra(C1404b.f2099C, this.f2211d.IsSandbox());
        this.f2211d.getContext().startService(intent);
    }

    private void m2953a(boolean z) {
        this.f2211d.mo2225c(true);
        this.f2211d.mo2210a(z);
        m2954b();
    }

    private void m2951a(Editor editor, JSONObject jSONObject) throws JSONException {
        editor.putLong("package", jSONObject.getLong("package"));
        editor.putString("browsing", jSONObject.getString("browsing"));
        editor.putInt("email", jSONObject.getInt("email"));
        editor.putInt(C1403a.f2088r, jSONObject.getInt(C1403a.f2088r));
        editor.commit();
    }

    private void m2950a(Editor editor) {
        editor.putBoolean(C1404b.f2101E, true);
        editor.putBoolean(C1404b.f2100D, true);
        editor.putBoolean(C1404b.f2103G, false);
        editor.commit();
    }

    private void m2955b(Editor editor) {
        editor.putString(C1404b.f2104H, "{}");
        editor.putBoolean(C1404b.f2102F, false);
        editor.putString(C1404b.f2107K, "{}");
        editor.putBoolean(C1404b.f2100D, false);
        editor.putBoolean(C1404b.f2103G, false);
        editor.putBoolean(C1404b.f2101E, false);
        editor.putBoolean(C1404b.aO, false);
        editor.commit();
    }

    private void m2952a(Editor editor, String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        editor.putString(C1404b.f2104H, str);
        boolean z5 = this.f2211d.mo2190Q() && this.f2211d.mo2192S();
        if (!z5) {
            editor.putBoolean(C1404b.f2102F, z);
        } else if (z) {
            editor.putBoolean(C1404b.f2102F, z);
        } else if (this.f2211d.mo2193T()) {
            editor.putBoolean(C1404b.f2102F, true);
        }
        if (!z5) {
            editor.putBoolean(C1404b.f2103G, z3);
        } else if (z3) {
            editor.putBoolean(C1404b.f2103G, z3);
        }
        editor.putString(C1404b.f2107K, str2);
        editor.putBoolean(C1404b.f2100D, z2);
        editor.putBoolean(C1404b.f2101E, z4);
    }
}
