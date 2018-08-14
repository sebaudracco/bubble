package com.appnext.base.receivers.imp;

import android.os.Handler;
import android.os.HandlerThread;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.receivers.C1078c;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;

public class glod implements C1078c {
    private static final String KEY = glod.class.getSimpleName();
    private static final int SECOND = 1000;
    private static final String ic = "threshold";
    private static final String is = "last_playing_detected";
    private static final String it = "load_file";
    private static final int iu = 25;
    private static final int iv = 30000;
    private static final int iw = 5;
    private static final String ix = "scan";
    private static final String iy = "sample";
    private HandlerThread hZ;
    private int iA;
    private List<Float> iB;
    private String[] iC = new String[]{"/sys/devices/platform/gpusysfs/gpu_busy", "/sys/class/kgsl/kgsl-3d0/gpubusy", "/sys/module/ged/parameters/gpu_loading"};
    private PLAYING_STATE iD = PLAYING_STATE.NoPlaying;
    private Timer iE;
    private int im;
    private int iz;
    private Handler mHandler;

    private enum PLAYING_STATE {
        Playing,
        NoPlaying
    }

    public boolean hasPermission() {
        return true;
    }

    public boolean register() {
        C1021c ab = C1017a.aM().aR().ab(KEY);
        if (ab == null) {
            return false;
        }
        this.hZ = new HandlerThread("glodThread");
        this.hZ.start();
        this.mHandler = new Handler(this.hZ.getLooper());
        try {
            this.im = ab.bh().getInt(ic);
        } catch (JSONException e) {
            this.im = 25;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return false;
        }
        try {
            this.iA = ab.bh().getInt("scan");
        } catch (JSONException e2) {
            this.iA = 30000;
        }
        try {
            this.iz = ab.bh().getInt(iy);
        } catch (JSONException e3) {
            this.iz = 5;
        }
        String string = C1048i.cy().getString(it, null);
        if (string == null) {
            string = bY();
            if (string == null) {
                return false;
            }
            C1048i.cy().putString(it, string);
        }
        final String str = string;
        this.iB = new ArrayList();
        ca();
        this.iE = new Timer();
        this.iE.schedule(new TimerTask(this) {
            final /* synthetic */ glod iG;

            class C10851 implements Runnable {
                final /* synthetic */ C10861 iH;

                C10851(C10861 c10861) {
                    this.iH = c10861;
                }

                public void run() {
                    try {
                        this.iH.iG.iB.add(Float.valueOf(this.iH.iG.ap(str)));
                        int size = this.iH.iG.iB.size();
                        while (this.iH.iG.iB.size() > this.iH.iG.iz) {
                            this.iH.iG.iB.remove(0);
                        }
                        if (size >= this.iH.iG.iz) {
                            double c = this.iH.iG.bX();
                            if (c <= ((double) this.iH.iG.im)) {
                                this.iH.iG.iD = PLAYING_STATE.NoPlaying;
                            } else if (this.iH.iG.iD == PLAYING_STATE.NoPlaying) {
                                C1048i.cy().putLong(glod.is, System.currentTimeMillis());
                                this.iH.iG.m2272g((int) c);
                                this.iH.iG.iD = PLAYING_STATE.Playing;
                            }
                        }
                    } catch (Throwable th) {
                        C1061b.m2191a(th);
                    }
                }
            }

            public void run() {
                this.iG.mHandler.post(new C10851(this));
            }
        }, 0, (long) (this.iA / this.iz));
        return true;
    }

    public void unregister() {
        try {
            if (this.iE != null) {
                this.iE.cancel();
            }
            if (this.mHandler != null) {
                this.mHandler.removeCallbacksAndMessages(null);
            }
            if (this.hZ != null) {
                this.hZ.quit();
            }
            bZ();
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    private double bX() {
        try {
            if (this.iB == null || this.iB.size() == 0) {
                return 0.0d;
            }
            ArrayList arrayList = new ArrayList(this.iB);
            ListIterator listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                if (((Float) listIterator.next()) == null) {
                    listIterator.set(Float.valueOf(0.0f));
                }
            }
            Collections.sort(arrayList);
            if (arrayList.size() % 2 != 0) {
                return (double) ((Float) arrayList.get(arrayList.size() / 2)).floatValue();
            }
            return (((double) ((Float) arrayList.get((arrayList.size() / 2) - 1)).floatValue()) + ((double) ((Float) arrayList.get(arrayList.size() / 2)).floatValue())) / 2.0d;
        } catch (Throwable th) {
            return 0.0d;
        }
    }

    private void m2272g(int i) {
        C1057k.m2175d(KEY, String.valueOf(i), C1041a.Integer);
    }

    private String bY() {
        if (this.iC == null) {
            return null;
        }
        try {
            for (String str : this.iC) {
                if (new File(str).exists()) {
                    return str;
                }
            }
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    private float ap(String str) {
        try {
            InputStream fileInputStream = new FileInputStream(new File(str));
            if (fileInputStream == null) {
                return 0.0f;
            }
            Reader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str2 = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str2 = str2 + readLine;
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            String[] split = str2.trim().replaceAll("\\s+", " ").split(" ");
            int[] iArr = new int[split.length];
            if (iArr.length == 0) {
                return 0.0f;
            }
            for (int i = 0; i < split.length; i++) {
                iArr[i] = Integer.parseInt(split[i].trim());
            }
            if (iArr.length <= 1) {
                return (float) iArr[0];
            }
            if (iArr[0] == 0 || iArr[1] == 0) {
                return 0.0f;
            }
            return (((float) iArr[0]) / ((float) iArr[1])) * 100.0f;
        } catch (Exception e) {
            return 0.0f;
        }
    }

    private void bZ() {
        if (this.iB != null && this.iB.size() > 0) {
            C1017a.aM().aP().m2093a(new C1020b(KEY, this.iB.toString(), C1041a.Integer.getType()));
        }
    }

    private void ca() {
        try {
            List ad = C1017a.aM().aP().ad(KEY);
            if (ad != null && ad.size() > 0) {
                C1020b c1020b = (C1020b) ad.get(0);
                if (System.currentTimeMillis() - c1020b.aZ().getTime() < 10000) {
                    String replaceAll = c1020b.aY().replaceAll("[^0-9,.]", "");
                    if (replaceAll != null && replaceAll.length() > 1) {
                        List asList = Arrays.asList(replaceAll.split("\\s*,\\s*"));
                        if (asList != null) {
                            int size = asList.size();
                            for (int i = 0; i < size; i++) {
                                this.iB.add(Float.valueOf((String) asList.get(i)));
                            }
                        }
                        if (this.iB.size() >= this.iz && bX() > ((double) this.im)) {
                            this.iD = PLAYING_STATE.Playing;
                        }
                    }
                }
                if (this.iD == PLAYING_STATE.Playing && C1057k.aF(KEY) < C1048i.cy().getLong(is, 0)) {
                    m2272g((int) bX());
                }
            }
        } catch (Throwable th) {
        }
        C1017a.aM().aP().ac(KEY);
    }
}
