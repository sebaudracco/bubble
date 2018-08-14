package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.R;
import javax.annotation.Nullable;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class StringResourceValueReader {
    private final Resources zzvb;
    private final String zzvc = this.zzvb.getResourcePackageName(R.string.common_google_play_services_unknown_issue);

    public StringResourceValueReader(Context context) {
        Preconditions.checkNotNull(context);
        this.zzvb = context.getResources();
    }

    @Nullable
    public String getString(String str) {
        int identifier = this.zzvb.getIdentifier(str, SchemaSymbols.ATTVAL_STRING, this.zzvc);
        return identifier == 0 ? null : this.zzvb.getString(identifier);
    }
}
