package com.appnext.ads.fullscreen;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Pair;
import com.appnext.ads.C0893a;
import com.appnext.base.p023b.C1042c;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.C0908i;
import com.appnext.core.C0919c;
import com.appnext.core.C0921q;
import com.appnext.core.C1104a;
import com.appnext.core.C1128g;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import org.json.JSONArray;

public class C0920b extends C0919c {
    private static C0920b dK;
    private final int dL = 30;
    private HashMap<Ad, String> dM = new HashMap();

    class C09181 implements Comparator<File> {
        final /* synthetic */ C0920b dN;

        C09181(C0920b c0920b) {
            this.dN = c0920b;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m1856a((File) obj, (File) obj2);
        }

        public int m1856a(File file, File file2) {
            return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
        }
    }

    public static synchronized C0920b ag() {
        C0920b c0920b;
        synchronized (C0920b.class) {
            if (dK == null) {
                dK = new C0920b();
            }
            c0920b = dK;
        }
        return c0920b;
    }

    private C0920b() {
    }

    protected String mo1973a(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        Object obj;
        C1128g.m2341a(ad != null ? ad.getTID() : "301", ad != null ? ad.getVID() : "2.2.5.468", ad != null ? ad.getAUID() : "700", str, ad != null ? ((Video) ad).getSessionId() : "", C0893a.cq, "sdk", "", "");
        StringBuilder append = new StringBuilder().append("https://global.appnext.com").append("/offerWallApi.aspx?ext=t&pimp=1&igroup=sdk&m=1&osid=100&auid=").append(ad != null ? ad.getAUID() : "700").append("&type=json&id=").append(str).append("&cnt=").append(30).append("&tid=").append(ad != null ? ad.getTID() : "301").append("&vid=").append(ad != null ? ad.getVID() : "2.2.5.468").append("&cat=").append(ad != null ? ad.getCategories() : "").append("&pbk=").append(ad != null ? ad.getPostback() : "").append("&vidmin=");
        if (ad == null) {
            obj = "";
        } else {
            obj = Integer.valueOf(ad.getMinVideoLength());
        }
        append = append.append(obj).append("&vidmax=");
        if (ad == null) {
            obj = "";
        } else {
            obj = Integer.valueOf(ad.getMaxVideoLength());
        }
        return append.append(obj).append("&did=").append(C1128g.m2358u(context)).append("&devn=").append(C1128g.cV()).append("&dosv=").append(VERSION.SDK_INT).append("&dct=").append(C1128g.aP(C1128g.m2361x(context))).append("&lang=").append(C1128g.cv()).append("&dcc=").append(C1128g.m2362y(context)).append("&dds=").append((int) C1128g.cX()).append("&packageId=").append(context.getPackageName()).append("&rnd=").append(new Random().nextInt()).toString();
    }

    protected boolean mo1979a(Context context, C0908i c0908i) {
        return m1895a(context, (AppnextAd) c0908i) && m1896b(context, (AppnextAd) c0908i);
    }

    protected boolean mo1980a(Ad ad) {
        return super.mo1980a(ad) && m1898h(ad);
    }

    protected boolean mo1978a(Context context, Ad ad, ArrayList<?> arrayList) {
        return m1900a(context, ad, (ArrayList) arrayList, "") != null;
    }

    protected void mo1974a(Context context, Ad ad, C1104a c1104a) throws Exception {
        m1893a(context, ad);
        AppnextAd appnextAd = null;
        try {
            appnextAd = m1909b(context, ad);
            if (appnextAd == null) {
                throw new Exception("No video ads");
            }
            m1894a(context, ad, appnextAd);
            if (ad instanceof RewardedVideo) {
                String mode = ((RewardedVideo) ad).getMode();
                if (mode.equals("default")) {
                    mode = C0940f.al().get("default_mode");
                }
                if (mode.equals("multi")) {
                    appnextAd = m1899a(context, ad, appnextAd.getBannerID());
                    if (appnextAd != null) {
                        m1894a(context, ad, appnextAd);
                    }
                }
            }
        } catch (Throwable th) {
            if (appnextAd != null) {
                mo1976a(appnextAd.getBannerID(), ad);
            }
        }
    }

    private void m1894a(Context context, Ad ad, AppnextAd appnextAd) throws Exception {
        String str;
        if (!appnextAd.getImageURL().equals("")) {
            C1128g.aO(appnextAd.getImageURL());
        }
        if (!appnextAd.getWideImageURL().equals("")) {
            C1128g.aO(appnextAd.getWideImageURL());
        }
        String videoUrl = C0920b.getVideoUrl(appnextAd, ((Video) ad).getVideoLength());
        String N = C0920b.m1892N(videoUrl);
        if (Video.getCacheVideo()) {
            str = context.getFilesDir().getAbsolutePath() + C1042c.jn + C1042c.jo;
        } else {
            str = context.getFilesDir().getAbsolutePath() + C1042c.jn + C1042c.jo + "tmp/vid" + ((Video) ad).rnd + BridgeUtil.SPLIT_MARK;
        }
        File file = new File(str + N);
        if (file.exists()) {
            file.setLastModified(System.currentTimeMillis());
            C1128g.m2333W(file.getPath() + " exists");
            this.dM.put(ad, file.getAbsolutePath());
        } else if (!Video.isStreamingModeEnabled()) {
            new File(str).mkdirs();
            URL url = new URL(videoUrl);
            url.openConnection().connect();
            InputStream bufferedInputStream = new BufferedInputStream(url.openStream(), 1024);
            OutputStream fileOutputStream = new FileOutputStream(str + N + C1042c.jp);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    C1128g.m2333W("downloaded " + str + N);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    bufferedInputStream.close();
                    File file2 = new File(str + N + C1042c.jp);
                    file2.renameTo(new File(str + N));
                    file2.delete();
                    this.dM.put(ad, file.getAbsolutePath());
                    return;
                }
            }
        }
    }

    private void m1893a(Context context, Ad ad) {
        int i = 0;
        try {
            File[] listFiles = new File(context.getFilesDir().getAbsolutePath() + C1042c.jn + C1042c.jo).listFiles();
            Arrays.sort(listFiles, new C09181(this));
            r1 = Video.getCacheVideo() ? ad instanceof FullScreenVideo ? Integer.parseInt(C0922c.aj().get("num_saved_videos")) - 1 : Integer.parseInt(C0940f.al().get("num_saved_videos")) - 1 : 0;
            if (listFiles.length > r1) {
                while (i < listFiles.length - r1) {
                    listFiles[i].delete();
                    i++;
                }
            }
        } catch (Throwable th) {
        }
    }

    protected void mo1975a(Ad ad, String str, String str2) {
        if (ad != null) {
            C1128g.m2341a(ad.getTID(), ad.getVID(), ad.getAUID(), str2, str, C0893a.cp, "sdk", "", "");
        } else {
            C1128g.m2341a("300", "2.2.5.468", "700", str2, str, C0893a.cp, "sdk", "", "");
        }
        C1128g.m2333W("error " + str);
    }

    protected <T> void mo1977a(String str, Ad ad, T t) {
        C1128g.m2341a(ad.getTID(), ad.getVID(), ad.getAUID(), str, ((Video) ad).getSessionId(), C0893a.co, "sdk", "", "");
    }

    protected static String getVideoUrl(AppnextAd appnextAd, String str) {
        String videoUrlHigh30Sec;
        if (str.equals("30")) {
            videoUrlHigh30Sec = appnextAd.getVideoUrlHigh30Sec();
            if (videoUrlHigh30Sec.equals("")) {
                videoUrlHigh30Sec = appnextAd.getVideoUrlHigh();
            }
        } else {
            videoUrlHigh30Sec = appnextAd.getVideoUrlHigh();
            if (videoUrlHigh30Sec.equals("")) {
                videoUrlHigh30Sec = appnextAd.getVideoUrlHigh30Sec();
            }
        }
        C1128g.m2333W("returning video url for: " + appnextAd.getBannerID() + " with len: " + str + " url: " + videoUrlHigh30Sec);
        return videoUrlHigh30Sec;
    }

    protected void mo1976a(String str, Ad ad) {
        super.mo1976a(str, ad);
        if (this.dM.containsKey(ad)) {
            this.dM.remove(ad);
        }
    }

    protected boolean m1913g(Ad ad) {
        try {
            if (m1880b(ad) && (m1890j(ad).cK().longValue() + m1889i(ad)) + 300000 >= System.currentTimeMillis() && m1898h(ad)) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            C1128g.m2351c(th);
            return false;
        }
    }

    protected C0921q mo1981d(Ad ad) {
        return ad instanceof RewardedVideo ? C0940f.al() : C0922c.aj();
    }

    protected boolean m1911e(Ad ad) {
        try {
            return mo1980a(ad) && m1898h(ad);
        } catch (Throwable th) {
            C1128g.m2351c(th);
            return false;
        }
    }

    private boolean m1898h(Ad ad) {
        if (Video.isStreamingModeEnabled()) {
            return true;
        }
        if (this.dM.containsKey(ad)) {
            return new File((String) this.dM.get(ad)).exists();
        }
        return false;
    }

    private boolean m1897b(AppnextAd appnextAd) {
        return (appnextAd.getVideoUrlHigh().equals("") && appnextAd.getVideoUrlHigh30Sec().equals("")) ? false : true;
    }

    protected AppnextAd m1909b(Context context, Ad ad) {
        return m1899a(context, ad, "");
    }

    protected AppnextAd m1899a(Context context, Ad ad, String str) {
        if (m1890j(ad) == null) {
            return null;
        }
        ArrayList cL = m1890j(ad).cL();
        if (cL != null) {
            return m1900a(context, ad, cL, str);
        }
        return null;
    }

    protected AppnextAd m1900a(Context context, Ad ad, ArrayList<AppnextAd> arrayList, String str) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd appnextAd = (AppnextAd) it.next();
            if (m1897b(appnextAd) && !m1883c(appnextAd.getBannerID(), ad.getPlacementID()) && !appnextAd.getBannerID().equals(str)) {
                return appnextAd;
            }
        }
        return null;
    }

    protected ArrayList<AppnextAd> m1912f(Ad ad) {
        return m1890j(ad).cL();
    }

    private boolean m1895a(Context context, AppnextAd appnextAd) {
        FullscreenAd fullscreenAd = new FullscreenAd(appnextAd);
        if (fullscreenAd.getCampaignGoal().equals("new") && C1128g.m2356h(context, fullscreenAd.getAdPackage())) {
            return false;
        }
        if (!fullscreenAd.getCampaignGoal().equals("existing") || C1128g.m2356h(context, fullscreenAd.getAdPackage())) {
            return true;
        }
        return false;
    }

    private boolean m1896b(Context context, AppnextAd appnextAd) {
        FullscreenAd fullscreenAd = new FullscreenAd(appnextAd);
        if (fullscreenAd.getCptList().equals("") || fullscreenAd.getCptList().equals("[]")) {
            return true;
        }
        try {
            JSONArray jSONArray = new JSONArray(fullscreenAd.getCptList());
            for (int i = 0; i < jSONArray.length(); i++) {
                if (C1128g.m2356h(context, jSONArray.getString(i))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            C1128g.m2351c(e);
            return true;
        }
    }

    protected static String m1892N(String str) {
        String substring = str.substring(str.lastIndexOf(BridgeUtil.SPLIT_MARK) + 1);
        if (substring.contains("?")) {
            substring = substring.substring(0, substring.indexOf("?"));
        }
        try {
            String queryParameter = Uri.parse(str).getQueryParameter("rnd");
            if (!(queryParameter == null || queryParameter.equals(""))) {
                substring = substring.substring(0, substring.lastIndexOf(46)) + BridgeUtil.UNDERLINE_STR + queryParameter + substring.substring(substring.lastIndexOf(46));
            }
        } catch (Throwable th) {
        }
        return substring;
    }
}
