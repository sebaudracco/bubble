package com.appnext.base.receivers.imp;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import com.appnext.base.C1061b;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.receivers.C1078c;
import java.io.File;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class caact implements C1078c {
    private static final String CAMERA = "camera";
    private static final String hS = "[^a-zA-Z]+";
    private static final String hT = "dcim";
    private static Uri hU = Media.INTERNAL_CONTENT_URI;
    private static Uri hV = Media.EXTERNAL_CONTENT_URI;
    public static final String hl = caact.class.getSimpleName();
    private Handler hW;
    private CaactContentObserver hX;
    private CaactContentObserver hY;
    private HandlerThread hZ;

    private class CaactContentObserver extends ContentObserver {
        private Uri ia;
        final /* synthetic */ caact ib;

        private CaactContentObserver(caact com_appnext_base_receivers_imp_caact, Handler handler, Uri uri) {
            this.ib = com_appnext_base_receivers_imp_caact;
            super(handler);
            this.ia = uri;
        }

        public void onChange(boolean z) {
            super.onChange(z);
            try {
                if (this.ib.hasPermission() && m2255a(this.ia)) {
                    bW();
                }
            } catch (Throwable th) {
            }
        }

        private void bW() {
            C1057k.m2175d(caact.hl, SchemaSymbols.ATTVAL_TRUE, C1041a.Boolean);
        }

        private boolean m2255a(Uri uri) {
            Uri uri2 = uri;
            Cursor query = C1043d.getContext().getContentResolver().query(uri2, new String[]{"_data", "datetaken"}, null, null, "datetaken DESC");
            if (query != null && query.moveToNext()) {
                long j = query.getLong(query.getColumnIndexOrThrow("datetaken"));
                String string = query.getString(query.getColumnIndexOrThrow("_data"));
                query.close();
                String toLowerCase = string.replaceAll(caact.hS, "").toLowerCase();
                if (!TextUtils.isEmpty(string) && (toLowerCase.contains(caact.CAMERA) || toLowerCase.contains(caact.hT))) {
                    if (!new File(string).exists()) {
                        return false;
                    }
                    if (System.currentTimeMillis() - j > 40000 || C1048i.cy().getString(caact.hl + uri, "").equals(string)) {
                        return false;
                    }
                    C1048i.cy().putString(caact.hl + uri, string);
                    return true;
                }
            }
            return false;
        }
    }

    public caact() {
        try {
            if (hasPermission()) {
                this.hZ = new HandlerThread(hl + "HandlerThread");
                this.hZ.start();
                this.hW = new Handler(this.hZ.getLooper());
                this.hY = new CaactContentObserver(this.hW, hV);
                this.hX = new CaactContentObserver(this.hW, hU);
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    public boolean hasPermission() {
        if (VERSION.SDK_INT >= 16) {
            return C1045f.m2133g(C1043d.getContext().getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE");
        }
        return C1045f.m2133g(C1043d.getContext().getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public boolean register() {
        try {
            C1043d.getContext().getContentResolver().registerContentObserver(hU, true, this.hX);
            C1043d.getContext().getContentResolver().registerContentObserver(hV, true, this.hY);
        } catch (Throwable th) {
        }
        return true;
    }

    public void unregister() {
        try {
            if (this.hX != null) {
                C1043d.getContext().getContentResolver().unregisterContentObserver(this.hX);
            }
            if (this.hY != null) {
                C1043d.getContext().getContentResolver().unregisterContentObserver(this.hY);
            }
            if (this.hW != null) {
                this.hW.removeCallbacks(null);
                this.hW = null;
            }
            if (this.hZ != null) {
                this.hZ.quit();
                this.hZ = null;
            }
        } catch (Throwable th) {
        }
    }
}
