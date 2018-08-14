package com.oneaudience.sdk.p133a;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import com.oneaudience.sdk.C3843e;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.regex.Pattern;

public class C3798l extends C3784a {
    private static final String[] f9119f = new String[]{"android.permission.GET_ACCOUNTS"};

    protected C3798l(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "email", "disableEmailsCollector", true, true);
    }

    public String mo6804a() {
        String str = "";
        if (C3843e.m12285a(this.c, "android.permission.GET_ACCOUNTS")) {
            Account[] accounts = AccountManager.get(this.c).getAccounts();
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            for (Account account : accounts) {
                if (pattern.matcher(account.name).matches()) {
                    String str2 = account.name;
                    if (!str.toLowerCase().contains(str2.toLowerCase())) {
                        str = str + str2 + ",";
                    }
                }
            }
            if (str.isEmpty()) {
                return str;
            }
            CharSequence substring = str.substring(0, str.length() - 1);
            this.b.edit().putBoolean("email_setter_get_accounts", !TextUtils.isEmpty(substring)).apply();
            return substring;
        }
        C3833d.m12246a(a, "Don't have permissions to collect emails");
        return str;
    }

    public String[] mo6805b() {
        return f9119f;
    }
}
