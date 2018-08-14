package com.google.android.gms.internal.ads;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Environment;
import com.google.android.gms.ads.internal.zzbv;

final class zzaaf implements OnClickListener {
    private final /* synthetic */ String zzbwo;
    private final /* synthetic */ String zzbwp;
    private final /* synthetic */ zzaae zzbwq;

    zzaaf(zzaae com_google_android_gms_internal_ads_zzaae, String str, String str2) {
        this.zzbwq = com_google_android_gms_internal_ads_zzaae;
        this.zzbwo = str;
        this.zzbwp = str2;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        DownloadManager downloadManager = (DownloadManager) zzaae.zza(this.zzbwq).getSystemService("download");
        try {
            String str = this.zzbwo;
            String str2 = this.zzbwp;
            Request request = new Request(Uri.parse(str));
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
            zzbv.zzem().zza(request);
            downloadManager.enqueue(request);
        } catch (IllegalStateException e) {
            this.zzbwq.zzbw("Could not store picture.");
        }
    }
}
