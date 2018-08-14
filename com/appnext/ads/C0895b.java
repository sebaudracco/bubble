package com.appnext.ads;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import com.appnext.core.AdsService;
import com.appnext.core.C0894p;

public class C0895b extends C0894p {
    String bj;
    String bk;
    String bl;
    String bm;
    String bn;
    ResultReceiver bo;
    String dq;
    String dr;
    String guid;

    public C0895b(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ResultReceiver resultReceiver) {
        this.bj = str;
        this.bk = str2;
        this.guid = str3;
        this.bl = str4;
        this.bm = str5;
        this.bo = resultReceiver;
        this.bn = str6;
        this.dq = str7;
        this.dr = str8;
    }

    public void m1826a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ResultReceiver resultReceiver) {
        this.bj = str;
        this.bk = str2;
        this.guid = str3;
        this.bl = str4;
        this.bm = str5;
        this.bo = resultReceiver;
        this.bn = str6;
        this.dq = str7;
        this.dr = str8;
    }

    protected void mo1916a(Intent intent) {
        intent.putExtra("added_info", AdsService.ADD_PACK);
        intent.putExtra("package", this.bj);
        intent.putExtra("link", this.bk);
        intent.putExtra("guid", this.guid);
        intent.putExtra("bannerid", this.bl);
        intent.putExtra("placementid", this.bm);
        intent.putExtra("receiver", this.bo);
        intent.putExtra("vid", this.bn);
        intent.putExtra("tid", this.dq);
        intent.putExtra("auid", this.dr);
    }

    public void mo1917d(Context context) {
        super.mo1917d(context);
        this.bo = null;
    }
}
