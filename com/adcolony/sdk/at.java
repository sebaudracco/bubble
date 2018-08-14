package com.adcolony.sdk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.adcolony.sdk.aa.C0595a;
import com.google.android.gms.plus.PlusShare;
import com.mopub.mraid.MraidNativeCommandHandler;
import com.silvermob.sdk.Const.ClickType;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class at {

    class C06201 implements ah {
        final /* synthetic */ at f678a;

        C06201(at atVar) {
            this.f678a = atVar;
        }

        public void mo1863a(af afVar) {
            this.f678a.m808a(afVar);
        }
    }

    class C06212 implements ah {
        final /* synthetic */ at f679a;

        C06212(at atVar) {
            this.f679a = atVar;
        }

        public void mo1863a(af afVar) {
            this.f679a.m818j(afVar);
        }
    }

    class C06223 implements ah {
        final /* synthetic */ at f680a;

        C06223(at atVar) {
            this.f680a = atVar;
        }

        public void mo1863a(af afVar) {
            this.f680a.m819k(afVar);
        }
    }

    class C06234 implements ah {
        final /* synthetic */ at f681a;

        C06234(at atVar) {
            this.f681a = atVar;
        }

        public void mo1863a(af afVar) {
            this.f681a.m820l(afVar);
        }
    }

    class C06245 implements ah {
        final /* synthetic */ at f682a;

        C06245(at atVar) {
            this.f682a = atVar;
        }

        public void mo1863a(af afVar) {
            this.f682a.m805n(afVar);
        }
    }

    class C06256 implements ah {
        final /* synthetic */ at f683a;

        C06256(at atVar) {
            this.f683a = atVar;
        }

        public void mo1863a(af afVar) {
            this.f683a.m804m(afVar);
        }
    }

    class C06278 implements ah {
        final /* synthetic */ at f687a;

        C06278(at atVar) {
            this.f687a = atVar;
        }

        public void mo1863a(af afVar) {
            this.f687a.m810b(afVar);
        }
    }

    class C06289 implements ah {
        final /* synthetic */ at f688a;

        C06289(at atVar) {
            this.f688a = atVar;
        }

        public void mo1863a(af afVar) {
            this.f688a.m811c(afVar);
        }
    }

    at() {
    }

    void m806a() {
        C0594a.m609a("System.open_store", new C06201(this));
        C0594a.m609a("System.save_screenshot", new C06278(this));
        C0594a.m609a("System.telephone", new C06289(this));
        C0594a.m609a("System.sms", new ah(this) {
            final /* synthetic */ at f672a;

            {
                this.f672a = r1;
            }

            public void mo1863a(af afVar) {
                this.f672a.m812d(afVar);
            }
        });
        C0594a.m609a("System.vibrate", new ah(this) {
            final /* synthetic */ at f673a;

            {
                this.f673a = r1;
            }

            public void mo1863a(af afVar) {
                this.f673a.m813e(afVar);
            }
        });
        C0594a.m609a("System.open_browser", new ah(this) {
            final /* synthetic */ at f674a;

            {
                this.f674a = r1;
            }

            public void mo1863a(af afVar) {
                this.f674a.m814f(afVar);
            }
        });
        C0594a.m609a("System.mail", new ah(this) {
            final /* synthetic */ at f675a;

            {
                this.f675a = r1;
            }

            public void mo1863a(af afVar) {
                this.f675a.m815g(afVar);
            }
        });
        C0594a.m609a("System.launch_app", new ah(this) {
            final /* synthetic */ at f676a;

            {
                this.f676a = r1;
            }

            public void mo1863a(af afVar) {
                this.f676a.m816h(afVar);
            }
        });
        C0594a.m609a("System.create_calendar_event", new ah(this) {
            final /* synthetic */ at f677a;

            {
                this.f677a = r1;
            }

            public void mo1863a(af afVar) {
                this.f677a.m817i(afVar);
            }
        });
        C0594a.m609a("System.check_app_presence", new C06212(this));
        C0594a.m609a("System.check_social_presence", new C06223(this));
        C0594a.m609a("System.social_post", new C06234(this));
        C0594a.m609a("System.make_in_app_purchase", new C06245(this));
        C0594a.m609a("System.close", new C06256(this));
    }

    private boolean m804m(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "ad_session_id");
        Activity c = C0594a.m613c();
        if (c == null || !(c instanceof C0589b)) {
            return false;
        }
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "id", b);
        new af("AdSession.on_request_close", ((C0589b) c).f381f, a).m695b();
        return true;
    }

    private boolean m805n(af afVar) {
        JSONObject c = afVar.m698c();
        C0690d m = C0594a.m605a().m1283m();
        String b = C0802y.m1468b(c, "ad_session_id");
        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) m.m1155c().get(b);
        bc bcVar = (bc) m.m1159f().get(b);
        if ((adColonyInterstitial == null || adColonyInterstitial.getListener() == null || adColonyInterstitial.m574d() == null) && (bcVar == null || bcVar.getListener() == null || bcVar.getExpandedContainer() == null)) {
            return false;
        }
        if (bcVar == null) {
            new af("AdUnit.make_in_app_purchase", adColonyInterstitial.m574d().m1057c()).m695b();
        } else {
            new af("AdUnit.make_in_app_purchase", bcVar.getExpandedContainer().m1057c()).m695b();
        }
        m809b(C0802y.m1468b(c, "ad_session_id"));
        return true;
    }

    boolean m808a(af afVar) {
        JSONObject a = C0802y.m1453a();
        JSONObject c = afVar.m698c();
        String b = C0802y.m1468b(c, "product_id");
        if (b.equals("")) {
            b = C0802y.m1468b(c, "handle");
        }
        if (az.m877a(new Intent("android.intent.action.VIEW", Uri.parse(b)))) {
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            m807a(C0802y.m1468b(c, "ad_session_id"));
            m809b(C0802y.m1468b(c, "ad_session_id"));
            return true;
        }
        az.m882a("Unable to open.", 0);
        C0802y.m1465a(a, "success", false);
        afVar.m694a(a).m695b();
        return false;
    }

    boolean m810b(final af afVar) {
        JSONObject c;
        if (!C0594a.m614d()) {
            return false;
        }
        try {
            if (ContextCompat.checkSelfPermission(C0594a.m613c(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                m809b(C0802y.m1468b(afVar.m698c(), "ad_session_id"));
                final JSONObject a = C0802y.m1453a();
                String str = Environment.getExternalStorageDirectory().toString() + "/Pictures/AdColony_Screenshots/AdColony_Screenshot_" + System.currentTimeMillis() + ".jpg";
                View rootView = C0594a.m613c().getWindow().getDecorView().getRootView();
                rootView.setDrawingCacheEnabled(true);
                Bitmap createBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
                rootView.setDrawingCacheEnabled(false);
                try {
                    File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures");
                    File file2 = new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures/AdColony_Screenshots");
                    file.mkdirs();
                    file2.mkdirs();
                } catch (Exception e) {
                }
                try {
                    OutputStream fileOutputStream = new FileOutputStream(new File(str));
                    createBitmap.compress(CompressFormat.JPEG, 90, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    MediaScannerConnection.scanFile(C0594a.m613c(), new String[]{str}, null, new OnScanCompletedListener(this) {
                        final /* synthetic */ at f686c;

                        public void onScanCompleted(String path, Uri uri) {
                            az.m882a("Screenshot saved to Gallery!", 0);
                            C0802y.m1465a(a, "success", true);
                            afVar.m694a(a).m695b();
                        }
                    });
                    return true;
                } catch (FileNotFoundException e2) {
                    az.m882a("Error saving screenshot.", 0);
                    C0802y.m1465a(a, "success", false);
                    afVar.m694a(a).m695b();
                    return false;
                } catch (IOException e3) {
                    az.m882a("Error saving screenshot.", 0);
                    C0802y.m1465a(a, "success", false);
                    afVar.m694a(a).m695b();
                    return false;
                }
            }
            az.m882a("Error saving screenshot.", 0);
            c = afVar.m698c();
            C0802y.m1465a(c, "success", false);
            afVar.m694a(c).m695b();
            return false;
        } catch (NoClassDefFoundError e4) {
            az.m882a("Error saving screenshot.", 0);
            c = afVar.m698c();
            C0802y.m1465a(c, "success", false);
            afVar.m694a(c).m695b();
            return false;
        }
    }

    boolean m811c(af afVar) {
        JSONObject a = C0802y.m1453a();
        JSONObject c = afVar.m698c();
        if (az.m877a(new Intent("android.intent.action.DIAL").setData(Uri.parse("tel:" + C0802y.m1468b(c, "phone_number"))))) {
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            m807a(C0802y.m1468b(c, "ad_session_id"));
            m809b(C0802y.m1468b(c, "ad_session_id"));
            return true;
        }
        az.m882a("Failed to dial number.", 0);
        C0802y.m1465a(a, "success", false);
        afVar.m694a(a).m695b();
        return false;
    }

    boolean m812d(af afVar) {
        JSONObject c = afVar.m698c();
        JSONObject a = C0802y.m1453a();
        JSONArray g = C0802y.m1481g(c, "recipients");
        String str = "";
        int i = 0;
        while (i < g.length()) {
            if (i != 0) {
                str = str + ";";
            }
            String str2 = str + C0802y.m1474c(g, i);
            i++;
            str = str2;
        }
        if (az.m877a(new Intent("android.intent.action.VIEW", Uri.parse("smsto:" + str)).putExtra("sms_body", C0802y.m1468b(c, "body")))) {
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            m807a(C0802y.m1468b(c, "ad_session_id"));
            m809b(C0802y.m1468b(c, "ad_session_id"));
            return true;
        }
        az.m882a("Failed to create sms.", 0);
        C0802y.m1465a(a, "success", false);
        afVar.m694a(a).m695b();
        return false;
    }

    boolean m813e(af afVar) {
        if (!C0594a.m614d()) {
            return false;
        }
        int a = C0802y.m1449a(afVar.m698c(), "length_ms", (int) HttpStatus.SC_INTERNAL_SERVER_ERROR);
        JSONObject a2 = C0802y.m1453a();
        JSONArray A = C0594a.m605a().m1284n().m1302A();
        boolean z = false;
        for (int i = 0; i < A.length(); i++) {
            if (C0802y.m1474c(A, i).equals("android.permission.VIBRATE")) {
                z = true;
            }
        }
        if (z) {
            try {
                ((Vibrator) C0594a.m613c().getSystemService("vibrator")).vibrate((long) a);
                C0802y.m1465a(a2, "success", false);
                afVar.m694a(a2).m695b();
                return true;
            } catch (Exception e) {
                new C0595a().m622a("Vibrate command failed.").m624a(aa.f481e);
                C0802y.m1465a(a2, "success", false);
                afVar.m694a(a2).m695b();
                return false;
            }
        }
        new C0595a().m622a("No vibrate permission detected.").m624a(aa.f481e);
        C0802y.m1465a(a2, "success", false);
        afVar.m694a(a2).m695b();
        return false;
    }

    boolean m814f(af afVar) {
        JSONObject a = C0802y.m1453a();
        JSONObject c = afVar.m698c();
        String b = C0802y.m1468b(c, "url");
        if (b.startsWith(ClickType.BROWSER)) {
            b = b.replaceFirst(ClickType.BROWSER, "http");
        }
        if (b.startsWith("safari")) {
            b = b.replaceFirst("safari", "http");
        }
        if (az.m877a(new Intent("android.intent.action.VIEW", Uri.parse(b)))) {
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            m807a(C0802y.m1468b(c, "ad_session_id"));
            m809b(C0802y.m1468b(c, "ad_session_id"));
            return true;
        }
        az.m882a("Failed to launch browser.", 0);
        C0802y.m1465a(a, "success", false);
        afVar.m694a(a).m695b();
        return false;
    }

    boolean m815g(af afVar) {
        JSONObject a = C0802y.m1453a();
        JSONObject c = afVar.m698c();
        JSONArray g = C0802y.m1481g(c, "recipients");
        boolean d = C0802y.m1477d(c, "html");
        String b = C0802y.m1468b(c, "subject");
        String b2 = C0802y.m1468b(c, "body");
        String[] strArr = new String[g.length()];
        for (int i = 0; i < g.length(); i++) {
            strArr[i] = C0802y.m1474c(g, i);
        }
        Intent intent = new Intent("android.intent.action.SEND");
        if (!d) {
            intent.setType("plain/text");
        }
        intent.putExtra("android.intent.extra.SUBJECT", b).putExtra("android.intent.extra.TEXT", b2).putExtra("android.intent.extra.EMAIL", strArr);
        if (az.m877a(intent)) {
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            m807a(C0802y.m1468b(c, "ad_session_id"));
            m809b(C0802y.m1468b(c, "ad_session_id"));
            return true;
        }
        az.m882a("Failed to send email.", 0);
        C0802y.m1465a(a, "success", false);
        afVar.m694a(a).m695b();
        return false;
    }

    boolean m816h(af afVar) {
        JSONObject a = C0802y.m1453a();
        JSONObject c = afVar.m698c();
        if (C0802y.m1477d(c, "deep_link")) {
            return m808a(afVar);
        }
        if (az.m877a(C0594a.m613c().getPackageManager().getLaunchIntentForPackage(C0802y.m1468b(c, "handle")))) {
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            m807a(C0802y.m1468b(c, "ad_session_id"));
            m809b(C0802y.m1468b(c, "ad_session_id"));
            return true;
        }
        az.m882a("Failed to launch external application.", 0);
        C0802y.m1465a(a, "success", false);
        afVar.m694a(a).m695b();
        return false;
    }

    boolean m817i(af afVar) {
        JSONObject a = C0802y.m1453a();
        JSONObject c = afVar.m698c();
        String str = "";
        String str2 = "";
        JSONObject f = C0802y.m1480f(c, "params");
        JSONObject f2 = C0802y.m1480f(f, "recurrence");
        JSONArray b = C0802y.m1469b();
        JSONArray b2 = C0802y.m1469b();
        JSONArray b3 = C0802y.m1469b();
        String b4 = C0802y.m1468b(f, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
        C0802y.m1468b(f, "location");
        String b5 = C0802y.m1468b(f, "start");
        String b6 = C0802y.m1468b(f, "end");
        String b7 = C0802y.m1468b(f, "summary");
        if (f2 != null && f2.length() > 0) {
            str2 = C0802y.m1468b(f2, ClientCookie.EXPIRES_ATTR);
            str = C0802y.m1468b(f2, "frequency");
            b = C0802y.m1481g(f2, "daysInWeek");
            b2 = C0802y.m1481g(f2, "daysInMonth");
            b3 = C0802y.m1481g(f2, "daysInYear");
        }
        if (b7.equals("")) {
            b7 = b4;
        }
        Date h = az.m901h(b5);
        Date h2 = az.m901h(b6);
        Date h3 = az.m901h(str2);
        if (h == null || h2 == null) {
            az.m882a("Unable to create Calendar Event", 0);
            C0802y.m1465a(a, "success", false);
            afVar.m694a(a).m695b();
            return false;
        }
        Intent putExtra;
        long time = h.getTime();
        long time2 = h2.getTime();
        long j = 0;
        if (h3 != null) {
            j = (h3.getTime() - h.getTime()) / 1000;
        }
        if (str.equals("DAILY")) {
            j = (j / 86400) + 1;
        } else if (str.equals("WEEKLY")) {
            j = (j / 604800) + 1;
        } else if (str.equals("MONTHLY")) {
            j = (j / 2629800) + 1;
        } else if (str.equals("YEARLY")) {
            j = (j / 31557600) + 1;
        } else {
            j = 0;
        }
        if (f2 == null || f2.length() <= 0) {
            putExtra = new Intent("android.intent.action.EDIT").setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE).putExtra("title", b7).putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, b4).putExtra("beginTime", time).putExtra("endTime", time2);
        } else {
            String str3;
            str2 = "FREQ=" + str + ";COUNT=" + j;
            try {
                String str4;
                if (b.length() != 0) {
                    str4 = str2 + ";BYDAY=" + az.m873a(b);
                } else {
                    str4 = str2;
                }
                try {
                    String str5;
                    if (b2.length() != 0) {
                        str5 = str4 + ";BYMONTHDAY=" + az.m889b(b2);
                    } else {
                        str5 = str4;
                    }
                    try {
                        if (b3.length() != 0) {
                            str3 = str5 + ";BYYEARDAY=" + az.m889b(b3);
                        } else {
                            str3 = str5;
                        }
                    } catch (JSONException e) {
                        str3 = str5;
                    }
                } catch (JSONException e2) {
                    str3 = str4;
                }
            } catch (JSONException e3) {
                str3 = str2;
            }
            putExtra = new Intent("android.intent.action.EDIT").setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE).putExtra("title", b7).putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, b4).putExtra("beginTime", time).putExtra("endTime", time2).putExtra("rrule", str3);
        }
        if (az.m877a(putExtra)) {
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            m807a(C0802y.m1468b(c, "ad_session_id"));
            m809b(C0802y.m1468b(c, "ad_session_id"));
            return true;
        }
        az.m882a("Unable to create Calendar Event.", 0);
        C0802y.m1465a(a, "success", false);
        afVar.m694a(a).m695b();
        return false;
    }

    boolean m818j(af afVar) {
        JSONObject a = C0802y.m1453a();
        String b = C0802y.m1468b(afVar.m698c(), "name");
        boolean a2 = az.m881a(b);
        C0802y.m1465a(a, "success", true);
        C0802y.m1465a(a, "result", a2);
        C0802y.m1462a(a, "name", b);
        C0802y.m1462a(a, NotificationCompat.CATEGORY_SERVICE, b);
        afVar.m694a(a).m695b();
        return true;
    }

    boolean m819k(af afVar) {
        return m818j(afVar);
    }

    boolean m820l(af afVar) {
        JSONObject a = C0802y.m1453a();
        JSONObject c = afVar.m698c();
        if (az.m878a(new Intent("android.intent.action.SEND").setType(HTTP.PLAIN_TEXT_TYPE).putExtra("android.intent.extra.TEXT", C0802y.m1468b(c, "text") + " " + C0802y.m1468b(c, "url")), true)) {
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            m807a(C0802y.m1468b(c, "ad_session_id"));
            m809b(C0802y.m1468b(c, "ad_session_id"));
            return true;
        }
        az.m882a("Unable to create social post.", 0);
        C0802y.m1465a(a, "success", false);
        afVar.m694a(a).m695b();
        return false;
    }

    void m807a(String str) {
        C0690d m = C0594a.m605a().m1283m();
        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) m.m1155c().get(str);
        if (adColonyInterstitial == null || adColonyInterstitial.getListener() == null) {
            bc bcVar = (bc) m.m1159f().get(str);
            C0592e listener = bcVar != null ? bcVar.getListener() : null;
            if (bcVar != null && listener != null && (listener instanceof AdColonyNativeAdViewListener)) {
                ((AdColonyNativeAdViewListener) listener).onLeftApplication((AdColonyNativeAdView) bcVar);
                return;
            }
            return;
        }
        adColonyInterstitial.getListener().onLeftApplication(adColonyInterstitial);
    }

    void m809b(String str) {
        C0690d m = C0594a.m605a().m1283m();
        AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) m.m1155c().get(str);
        if (adColonyInterstitial == null || adColonyInterstitial.getListener() == null) {
            bc bcVar = (bc) m.m1159f().get(str);
            C0592e listener = bcVar != null ? bcVar.getListener() : null;
            if (bcVar != null && listener != null && (listener instanceof AdColonyNativeAdViewListener)) {
                ((AdColonyNativeAdViewListener) listener).onClicked((AdColonyNativeAdView) bcVar);
                return;
            }
            return;
        }
        adColonyInterstitial.getListener().onClicked(adColonyInterstitial);
    }
}
