package com.bgjd.ici.p029d;

import android.accounts.Account;
import android.accounts.AccountManager;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.C1408j.C1407c.C1406b;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.ac;

public class C1443a extends C1409k<JSONObject> {
    private static final String f2226f = "ACT";

    public /* synthetic */ Object mo2325d() {
        return m2971f();
    }

    public C1443a(C1395h c1395h) {
        super(c1395h);
        this.b = "email";
        this.e = C1406b.f2153c;
    }

    public JSONObject m2971f() {
        int i;
        Object jSONArray;
        JSONObject jSONObject;
        int i2 = 0;
        JSONArray jSONArray2 = new JSONArray();
        this.c = this.a.getEmailCount();
        boolean IsSandbox = this.a.IsSandbox();
        boolean isAccepted = this.a.isAccepted();
        C1402i.m2901b(f2226f, "Started...");
        if (ac.m2862a(this.a.getContext(), "android.permission.GET_ACCOUNTS")) {
            Account[] accounts = AccountManager.get(this.a.getContext()).getAccounts();
            if (accounts != null && accounts.length > 0) {
                int declinedTransaction;
                if (!IsSandbox && isAccepted) {
                    i = 0;
                    for (Account account : accounts) {
                        Object jSONObject2 = new JSONObject();
                        try {
                            if (account.name.matches(C1404b.f2143u)) {
                                jSONObject2.put("a", ac.m2866b(account.name));
                                jSONObject2.put("b", ac.m2866b(account.name.toUpperCase()));
                                jSONObject2.put("c", ac.m2870c(account.name));
                                jSONObject2.put("d", ac.m2870c(account.name.toUpperCase()));
                                jSONObject2.put("e", ac.m2859a(account.name));
                                jSONObject2.put("f", ac.m2859a(account.name.toUpperCase()));
                                jSONObject2.put("g", account.type);
                                jSONArray2.put(jSONObject2);
                                i++;
                            }
                        } catch (JSONException e) {
                        }
                    }
                    if (i > this.c) {
                        jSONArray = new JSONArray();
                    } else {
                        this.c = jSONArray2.length();
                        jSONArray = jSONArray2;
                    }
                    C1402i.m2901b(f2226f, "Completed Total count : " + this.c);
                    this.d = System.currentTimeMillis();
                    jSONObject = new JSONObject();
                    jSONObject.put("a", jSONArray);
                    if (!IsSandbox) {
                    }
                    jSONObject.put("b", jSONArray.length());
                    jSONObject.put("c", this.d);
                    this.a.setDeclinedTransaction("em", this.c);
                    this.a.setTransactionHistory(mo2298a(), Long.valueOf((long) mo2299b()));
                    return jSONObject;
                } else if (!(IsSandbox || isAccepted)) {
                    i = accounts.length;
                    if (i > this.c) {
                        this.c = jSONArray2.length();
                        jSONArray = jSONArray2;
                    } else {
                        jSONArray = new JSONArray();
                    }
                    C1402i.m2901b(f2226f, "Completed Total count : " + this.c);
                    this.d = System.currentTimeMillis();
                    jSONObject = new JSONObject();
                    jSONObject.put("a", jSONArray);
                    if (IsSandbox || isAccepted) {
                        jSONObject.put("b", jSONArray.length());
                    } else {
                        declinedTransaction = this.a.getDeclinedTransaction("em");
                        if (declinedTransaction > 0) {
                            if (declinedTransaction < this.c) {
                                i2 = declinedTransaction;
                            }
                            this.c = i2;
                            jSONObject.put("b", this.c);
                        }
                    }
                    jSONObject.put("c", this.d);
                    if (!(IsSandbox || isAccepted || this.c <= 0)) {
                        this.a.setDeclinedTransaction("em", this.c);
                    }
                    this.a.setTransactionHistory(mo2298a(), Long.valueOf((long) mo2299b()));
                    return jSONObject;
                }
            }
        }
        i = 0;
        if (i > this.c) {
            this.c = jSONArray2.length();
            jSONArray = jSONArray2;
        } else {
            jSONArray = new JSONArray();
        }
        C1402i.m2901b(f2226f, "Completed Total count : " + this.c);
        this.d = System.currentTimeMillis();
        jSONObject = new JSONObject();
        try {
            jSONObject.put("a", jSONArray);
            if (IsSandbox) {
            }
            jSONObject.put("b", jSONArray.length());
            jSONObject.put("c", this.d);
            this.a.setDeclinedTransaction("em", this.c);
            this.a.setTransactionHistory(mo2298a(), Long.valueOf((long) mo2299b()));
        } catch (JSONException e2) {
        }
        return jSONObject;
    }
}
