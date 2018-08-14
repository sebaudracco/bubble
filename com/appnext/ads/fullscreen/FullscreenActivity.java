package com.appnext.ads.fullscreen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import com.appnext.C0889R;
import com.appnext.ads.C0893a;
import com.appnext.ads.C0895b;
import com.appnext.ads.C0896c;
import com.appnext.base.p023b.C1042c;
import com.appnext.core.Ad;
import com.appnext.core.AppnextActivity;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.C0921q;
import com.appnext.core.C1123e.C0899a;
import com.appnext.core.C1128g;
import com.appnext.core.C1134l;
import com.appnext.core.C1149r;
import com.appnext.core.C1149r.C0897a;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

public class FullscreenActivity extends AppnextActivity implements C0905h, C0906i, C0907j, C0899a {
    private ArrayList<AppnextAd> aL;
    private boolean dA = true;
    private AppnextAd dB;
    private AppnextAd dC;
    C0895b dD;
    private HashMap<String, Integer> dE;
    private Video dF;
    Runnable dG = new C09023(this);
    Runnable dH = new C09034(this);
    private C0921q dz;
    private boolean finished = false;
    private Handler mHandler;
    private int state = 0;
    private int type;

    class C08981 implements C0897a {
        final /* synthetic */ FullscreenActivity dI;

        C08981(FullscreenActivity fullscreenActivity) {
            this.dI = fullscreenActivity;
        }

        public void report(String str) {
            this.dI.report(str);
        }

        public Ad ac() {
            return Video.currentAd;
        }

        public AppnextAd ad() {
            return this.dI.dC;
        }

        public C0921q ae() {
            return this.dI.getConfig();
        }
    }

    class C09002 implements C0899a {
        final /* synthetic */ FullscreenActivity dI;

        C09002(FullscreenActivity fullscreenActivity) {
            this.dI = fullscreenActivity;
        }

        public void onMarket(String str) {
        }

        public void error(String str) {
        }
    }

    class C09023 implements Runnable {
        final /* synthetic */ FullscreenActivity dI;

        class C09011 implements Runnable {
            final /* synthetic */ C09023 dJ;

            C09011(C09023 c09023) {
                this.dJ = c09023;
            }

            public void run() {
                try {
                    C1128g.m2336a("https://www.fqtag.com/pixel.cgi?org=TkBXEI5C3FBIr4zXwnmK&p=" + this.dJ.dI.placementID + "&a=" + this.dJ.dI.dB.getBannerID() + "&cmp=" + this.dJ.dI.dB.getCampaignID() + "&fmt=banner&dmn=" + this.dJ.dI.dB.getAdPackage() + "&ad=&rt=displayImg&gid=" + C1128g.m2358u(this.dJ.dI) + "&aid=&applng=" + Locale.getDefault().toString() + "&app=" + this.dJ.dI.getPackageName() + "&c1=100&c2=" + this.dJ.dI.ab().getTID() + "&c3=" + this.dJ.dI.ab().getAUID() + "&c4=" + this.dJ.dI.ab().getVID() + "&sl=1&fq=1", null);
                } catch (Throwable th) {
                    C1128g.m2351c(th);
                }
            }
        }

        C09023(FullscreenActivity fullscreenActivity) {
            this.dI = fullscreenActivity;
        }

        public void run() {
            C1128g.m2333W("impression");
            if (this.dI.userAction != null) {
                this.dI.userAction.m2398e(this.dI.dB);
            }
            if (Boolean.parseBoolean(this.dI.getConfig().get("fq_control")) && this.dI.ab() != null && this.dI.ab().fq_status) {
                new Thread(new C09011(this)).start();
            }
            this.dI.report(C0893a.cL, "S2");
        }
    }

    class C09034 implements Runnable {
        final /* synthetic */ FullscreenActivity dI;

        C09034(FullscreenActivity fullscreenActivity) {
            this.dI = fullscreenActivity;
        }

        public void run() {
            C1128g.m2333W("postview");
            this.dI.mo1936a(this.dI.dB, null);
            this.dI.report(C0893a.cM, "S2");
        }
    }

    class C09045 implements Runnable {
        final /* synthetic */ FullscreenActivity dI;

        C09045(FullscreenActivity fullscreenActivity) {
            this.dI = fullscreenActivity;
        }

        public void run() {
            if (this.dI.ab() instanceof RewardedVideo) {
                RewardedServerSidePostback rewardedServerSidePostback = ((RewardedVideo) this.dI.ab()).getRewardedServerSidePostback();
                if (rewardedServerSidePostback != null) {
                    HashMap ak = rewardedServerSidePostback.ak();
                    ak.put("placementId", this.dI.placementID);
                    try {
                        C1128g.m2336a("https://admin.appnext.com/adminService.asmx/SetRewards", ak);
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.dE = (HashMap) bundle.getSerializable("templates");
            this.state = bundle.getInt("state");
        }
        if (VERSION.SDK_INT >= 17) {
            Configuration configuration = getResources().getConfiguration();
            configuration.setLayoutDirection(new Locale("en"));
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        }
        super.onCreate(bundle);
        aa();
        if (Video.currentAd == null) {
            C1128g.m2333W("null currentAd");
            onError(AppnextError.NO_ADS);
            finish();
            return;
        }
        if (Video.currentAd instanceof RewardedVideo) {
            this.dF = new RewardedVideo((Context) this, (RewardedVideo) Video.currentAd);
        } else {
            this.dF = new FullScreenVideo((Context) this, (FullScreenVideo) Video.currentAd);
        }
        String orientation = ab().getOrientation();
        boolean z = true;
        switch (orientation.hashCode()) {
            case 729267099:
                if (orientation.equals(Ad.ORIENTATION_PORTRAIT)) {
                    z = true;
                    break;
                }
                break;
            case 1430647483:
                if (orientation.equals(Ad.ORIENTATION_LANDSCAPE)) {
                    z = true;
                    break;
                }
                break;
            case 1673671211:
                if (orientation.equals(Ad.ORIENTATION_AUTO)) {
                    z = true;
                    break;
                }
                break;
            case 2129065206:
                if (orientation.equals(Ad.ORIENTATION_DEFAULT)) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
                if (getResources().getConfiguration().orientation != 2) {
                    setRequestedOrientation(7);
                    break;
                } else {
                    setRequestedOrientation(6);
                    break;
                }
            case true:
                setRequestedOrientation(6);
                break;
            case true:
                setRequestedOrientation(7);
                break;
        }
        this.mHandler = new Handler();
        this.placementID = getIntent().getExtras().getString("id");
        this.type = getIntent().getExtras().getInt("type");
        if (this.type == 1) {
            this.dz = C0922c.aj();
        } else {
            this.dz = C0940f.al();
        }
        this.dA = Boolean.parseBoolean(this.dz.get("can_close"));
        if (ab() instanceof FullScreenVideo) {
            this.dA = ((FullScreenVideo) ab()).isBackButtonCanClose();
        }
        if (bundle == null) {
            this.aL = C0920b.ag().m1912f(ab());
            if (this.aL == null) {
                onError(AppnextError.NO_ADS);
                finish();
                return;
            }
            this.dB = m1846a(this.aL, this.placementID, "");
        } else {
            this.aL = (ArrayList) bundle.getSerializable("ads");
            this.dB = (AppnextAd) bundle.getSerializable("currentAd");
        }
        if (this.dB == null) {
            onError(AppnextError.NO_ADS);
            finish();
            return;
        }
        setContentView(C0889R.layout.apnxt_video_activity);
        if (bundle == null) {
            Fragment fragment;
            Bundle bundle2 = new Bundle();
            String mode = ab() instanceof RewardedVideo ? ((RewardedVideo) ab()).getMode() : "";
            if (mode.equals("default")) {
                mode = getConfig().get("default_mode");
            }
            if (this.type == 2 && r0.equals("multi")) {
                Fragment c0939e = new C0939e();
                bundle2.putInt("time", ((RewardedVideo) ab()).getMultiTimerLength());
                report("multi");
                fragment = c0939e;
            } else {
                if (this.type == 2) {
                    report("normal");
                }
                fragment = new C0954g();
                bundle2.putBoolean("showCta", ab().isShowCta());
                this.state = 1;
            }
            fragment.setArguments(bundle2);
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.add(C0889R.id.ll, fragment, "fragment");
            beginTransaction.commit();
        } else {
            this.finished = bundle.getBoolean("finished", true);
        }
        this.lc = (RelativeLayout) findViewById(C0889R.id.ll);
        this.userAction = new C1149r(this, new C08981(this));
    }

    protected void onError(String str) {
        if (ab() != null && ab().getOnAdErrorCallback() != null) {
            ab().getOnAdErrorCallback().adError(str);
        }
    }

    protected C0921q getConfig() {
        if (this.dz == null) {
            if (this.type == 1) {
                this.dz = C0922c.aj();
            } else {
                this.dz = C0940f.al();
            }
        }
        return this.dz;
    }

    protected void mo1936a(AppnextAd appnextAd, C0899a c0899a) {
        super.mo1936a(appnextAd, new C09002(this));
    }

    private Uri m1833Y() {
        String str;
        String videoUrl = C0920b.getVideoUrl(this.dB, ab().getVideoLength());
        String N = C0920b.m1892N(videoUrl);
        if (Video.getCacheVideo()) {
            str = getFilesDir().getAbsolutePath() + C1042c.jn + C1042c.jo;
        } else {
            str = getFilesDir().getAbsolutePath() + C1042c.jn + C1042c.jo + "tmp/vid" + ab().rnd + BridgeUtil.SPLIT_MARK;
        }
        File file = new File(str + N);
        if (file.exists()) {
            Uri parse = Uri.parse(str + N);
            C1128g.m2333W("playing video " + parse.getPath());
            return parse;
        }
        parse = Uri.parse(videoUrl);
        C1128g.m2333W("playing video from web: " + videoUrl);
        C1128g.m2333W("file not found: " + file.getAbsolutePath());
        return parse;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("finished", this.finished);
        bundle.putInt("type", this.type);
        bundle.putSerializable("templates", this.dE);
        bundle.putSerializable("ads", this.aL);
        bundle.putInt("state", this.state);
        bundle.putSerializable("currentAd", this.dB);
        super.onSaveInstanceState(bundle);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.finished = bundle.getBoolean("finished", true);
        this.type = bundle.getInt("type");
        this.dE = (HashMap) bundle.getSerializable("templates");
    }

    public void onBackPressed() {
        if ((this.state == 1 && m1834Z()) || this.state == 2) {
            onClose();
            super.onBackPressed();
        }
    }

    private boolean m1834Z() {
        return (ab() instanceof FullScreenVideo) && ((FullScreenVideo) ab()).isBackButtonCanClose();
    }

    protected void onPause() {
        super.onPause();
        aa();
        this.mHandler.removeCallbacks(this.dG);
        this.mHandler.removeCallbacks(this.dH);
    }

    protected void onResume() {
        super.onResume();
        aa();
        cP();
    }

    protected void onDestroy() {
        super.onDestroy();
        aa();
        try {
            C1128g.m2348b(new File(getFilesDir().getAbsolutePath() + C1042c.jn + C1042c.jo + "tmp/vid" + ab().rnd + BridgeUtil.SPLIT_MARK));
        } catch (Throwable th) {
        }
        try {
            if (this.mHandler != null) {
                this.mHandler.removeCallbacksAndMessages(null);
            }
            this.mHandler = null;
            this.dC = null;
            this.dB = null;
        } catch (Throwable th2) {
        }
        try {
            if (this.dD != null) {
                this.dD.mo1917d(this);
            }
            this.dD = null;
        } catch (Throwable th3) {
        }
    }

    private void m1837a(AppnextAd appnextAd) {
        mo1937b(appnextAd, this);
    }

    protected void mo1937b(AppnextAd appnextAd, C0899a c0899a) {
        if (appnextAd != null) {
            this.dC = appnextAd;
            if (ab().getOnAdClickedCallback() != null) {
                ab().getOnAdClickedCallback().adClicked();
            }
            if (this.finished || !(ab() instanceof RewardedVideo)) {
                super.mo1937b(appnextAd, c0899a);
            }
        }
    }

    public void onMarket(String str) {
        cQ();
        this.finished = true;
        Collections.shuffle(this.aL, new Random(System.nanoTime()));
        this.aL.remove(this.dB);
        this.aL.add(0, this.dB);
        Fragment c0931d = new C0931d();
        this.state = 2;
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(C0889R.id.ll, c0931d, "fragment");
        beginTransaction.commit();
    }

    public void error(String str) {
        cQ();
        report(C0893a.cF);
    }

    private void onClose() {
        try {
            C0920b.ag().mo1976a(this.dB.getBannerID(), ab());
            if (!(ab() == null || ab().getOnAdClosedCallback() == null)) {
                ab().getOnAdClosedCallback().onAdClosed();
            }
            Video.currentAd = null;
        } catch (Throwable th) {
        }
    }

    public void videoStarted() {
        this.mHandler.postDelayed(this.dG, Long.parseLong(this.dz.get("postpone_impression_sec")) * 1000);
        if (Boolean.parseBoolean(this.dz.get("pview"))) {
            this.mHandler.postDelayed(this.dH, Long.parseLong(this.dz.get("postpone_vta_sec")) * 1000);
        }
        if (ab() != null && ab().getOnAdOpenedCallback() != null) {
            ab().getOnAdOpenedCallback().adOpened();
        }
    }

    public void videoEnded() {
        this.state = 2;
        this.finished = true;
        if (!(ab() == null || ab().getOnVideoEndedCallback() == null)) {
            ab().getOnVideoEndedCallback().videoEnded();
        }
        new Thread(new C09045(this)).start();
        Collections.shuffle(this.aL, new Random(System.nanoTime()));
        this.aL.remove(this.dB);
        this.aL.add(0, this.dB);
        Fragment c0931d = new C0931d();
        this.state = 2;
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(C0889R.id.ll, c0931d, "fragment");
        beginTransaction.commit();
        if (this.dC != null) {
            super.mo1937b(this.dC, this);
            report(C0893a.cV);
            return;
        }
        report(C0893a.cU);
    }

    public void videoSelected(AppnextAd appnextAd) {
        this.dB = appnextAd;
        Fragment c0954g = new C0954g();
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putBoolean("showCta", ab().isShowCta());
        c0954g.setArguments(bundle);
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(C0889R.id.ll, c0954g, "fragment");
        beginTransaction.commit();
    }

    public ArrayList<AppnextAd> getPreRollAds() {
        if (this.aL == null) {
            this.aL = C0920b.ag().m1912f(ab());
        }
        ArrayList<AppnextAd> arrayList = new ArrayList();
        AppnextAd a = m1846a(this.aL, this.placementID, "");
        arrayList.add(a);
        AppnextAd a2 = m1846a(this.aL, this.placementID, a.getBannerID());
        if (!(a2 == null || a2.getBannerID().equals(((AppnextAd) arrayList.get(0)).getBannerID()))) {
            arrayList.add(a2);
        }
        return arrayList;
    }

    public void privacyClicked() {
        try {
            String str = "android.intent.action.VIEW";
            Intent intent = new Intent(str, Uri.parse("https://www.appnext.com/privacy_policy/index.html?z=" + new Random().nextInt(10) + new FullscreenAd(this.dB).getZoneID() + new Random().nextInt(10)));
            intent.setFlags(ErrorDialogData.BINDER_CRASH);
            startActivity(intent);
        } catch (Throwable th) {
        }
    }

    public void installClicked() {
        m1837a(this.dB);
    }

    public void installClicked(AppnextAd appnextAd) {
        m1830a(this.lc, getResources().getDrawable(C0889R.drawable.apnxt_loader));
        m1837a(appnextAd);
    }

    public void closeClicked() {
        onClose();
        finish();
    }

    public Uri getSelectedVideoUri() {
        return m1833Y();
    }

    public boolean showClose() {
        return (ab() instanceof FullScreenVideo) && ((FullScreenVideo) ab()).isShowClose();
    }

    public C0921q getConfigManager() {
        return getConfig();
    }

    public int getTemplate(String str) {
        if (this.dE == null) {
            this.dE = new HashMap();
        }
        if (!this.dE.containsKey(str)) {
            int identifier = getResources().getIdentifier("apnxt_" + C0896c.m1828L(getConfig().get(str)), "layout", getPackageName());
            if (identifier == 0) {
                identifier = getResources().getIdentifier("apnxt_" + str.toLowerCase() + "t1", "layout", getPackageName());
            }
            this.dE.put(str, Integer.valueOf(identifier));
        }
        return ((Integer) this.dE.get(str)).intValue();
    }

    public boolean getMute() {
        return ab().getMute();
    }

    public void report(String str, String str2) {
        m1842d(str, getResources().getResourceEntryName(getTemplate(str2)));
    }

    public ArrayList<AppnextAd> getPostRollAds() {
        return this.aL;
    }

    public String getCtaText() {
        String buttonText = new FullscreenAd(this.dB).getButtonText();
        if (this.dB == null || !buttonText.equals("")) {
            return buttonText;
        }
        if (isInstalled()) {
            return "Open";
        }
        return "Install";
    }

    public boolean isInstalled() {
        try {
            return C1128g.m2356h(this, this.dB.getAdPackage());
        } catch (Throwable th) {
            return false;
        }
    }

    public int getCaptionTextTime() {
        return ab().getRollCaptionTime();
    }

    public long closeDelay() {
        if (ab() instanceof FullScreenVideo) {
            return ((FullScreenVideo) ab()).getCloseDelay();
        }
        return 0;
    }

    protected AppnextAd m1846a(ArrayList<AppnextAd> arrayList, String str, String str2) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd appnextAd = (AppnextAd) it.next();
            if (m1839b(appnextAd) && !m1849c(appnextAd.getBannerID(), str) && !appnextAd.getBannerID().equals(str2)) {
                return appnextAd;
            }
        }
        C1134l.db().aS(str);
        it = arrayList.iterator();
        while (it.hasNext()) {
            appnextAd = (AppnextAd) it.next();
            if (m1839b(appnextAd) && !m1849c(appnextAd.getBannerID(), str)) {
                return appnextAd;
            }
        }
        return null;
    }

    protected boolean m1849c(String str, String str2) {
        return C1134l.db().m2374r(str, str2);
    }

    private boolean m1839b(AppnextAd appnextAd) {
        return (appnextAd.getVideoUrlHigh().equals("") && appnextAd.getVideoUrlHigh30Sec().equals("")) ? false : true;
    }

    private void report(String str) {
        m1842d(str, getResources().getResourceEntryName(getTemplate("S" + (this.state + 1))));
    }

    private void m1842d(String str, String str2) {
        try {
            C1128g.m2341a(ab().getTID(), ab().getVID(), ab().getAUID(), this.placementID, ab().getSessionId(), str, str2, this.dB != null ? this.dB.getBannerID() : "", this.dB != null ? this.dB.getCampaignID() : "");
        } catch (Throwable th) {
        }
    }

    private void aa() {
    }

    private Video ab() {
        if (Video.currentAd != null) {
            return Video.currentAd;
        }
        return this.dF;
    }
}
