package com.google.android.gms.common.server;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.server.BaseApi.BaseApiaryOptions;

public final class BaseApi$BaseApiaryOptions$Collector {
    private boolean zzvx;
    private boolean zzvy;
    private int zzvz;
    private StringBuilder zzwa = new StringBuilder();
    private final /* synthetic */ BaseApiaryOptions zzwb;

    public BaseApi$BaseApiaryOptions$Collector(BaseApiaryOptions baseApiaryOptions) {
        this.zzwb = baseApiaryOptions;
    }

    private final void append(String str) {
        if (this.zzvx) {
            this.zzvx = false;
            this.zzwa.append(",");
        } else if (this.zzvy) {
            this.zzvy = false;
            this.zzwa.append(BridgeUtil.SPLIT_MARK);
        }
        this.zzwa.append(str);
    }

    public final void addPiece(String str) {
        append(str);
        this.zzvy = true;
    }

    public final void beginSubCollection(String str) {
        append(str);
        this.zzwa.append("(");
        this.zzvz++;
    }

    public final void endSubCollection() {
        this.zzwa.append(")");
        this.zzvz--;
        if (this.zzvz == 0) {
            this.zzwb.addField(this.zzwa.toString());
            this.zzwa.setLength(0);
            this.zzvx = false;
            this.zzvy = false;
            return;
        }
        this.zzvx = true;
    }

    public final void finishPiece(String str) {
        append(str);
        if (this.zzvz == 0) {
            this.zzwb.addField(this.zzwa.toString());
            this.zzwa.setLength(0);
            return;
        }
        this.zzvx = true;
    }
}
