package com.fyber.utils;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.p089c.p090d.C2543d;
import com.fyber.p089c.p090d.C2543d.C2434b;
import com.fyber.p089c.p101a.C2523a;

/* compiled from: AbstractClickthroughBrowserListener */
public abstract class C2435a implements C2434b {
    protected abstract String mo3879a();

    protected abstract void mo3880a(C2543d c2543d, C2523a c2523a, Uri uri);

    public final boolean mo3878a(final C2543d c2543d, C2523a c2523a, String str) {
        if (C2682v.m8582a(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        if (C2663k.m8519a(c2543d.getContext(), "android.intent.action.VIEW", parse)) {
            mo3880a(c2543d, c2523a, parse);
        } else {
            m7718a(c2543d.getContext(), UIStringIdentifier.RV_REDIRECT_ERROR, "Keep watching", new OnClickListener(this) {
                final /* synthetic */ C2435a f6572b;

                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    c2543d.m8100c();
                }
            }, "Close Video", new OnClickListener(this) {
                final /* synthetic */ C2435a f6574b;

                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    c2543d.m8097a("redirect_fail", "Redirection Error");
                }
            });
        }
        return true;
    }

    protected final void m7718a(Context context, UIStringIdentifier uIStringIdentifier, String str, OnClickListener onClickListener, String str2, OnClickListener onClickListener2) {
        CharSequence a = C2671s.m8532a(UIStringIdentifier.RV_REDIRECT_DIALOG_TITLE);
        Object a2 = C2671s.m8532a(uIStringIdentifier);
        try {
            new Builder(context).setTitle(a).setCancelable(false).setMessage(a2).setPositiveButton(str, onClickListener).setNegativeButton(str2, onClickListener2).create().show();
        } catch (Exception e) {
            FyberLogger.m8450e(mo3879a(), "Couldn't show error dialog. Not displayed error message is: " + a2, e);
        }
    }
}
