package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.bj;
import com.inmobi.commons.a.b;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.core.utilities.info.DisplayInfo.ORIENTATION_VALUES;
import com.inmobi.rendering.RenderView.RenderViewState;
import com.inmobi.rendering.a.1;
import com.inmobi.rendering.a.10;
import com.inmobi.rendering.a.11;
import com.inmobi.rendering.a.12;
import com.inmobi.rendering.a.13;
import com.inmobi.rendering.a.14;
import com.inmobi.rendering.a.2;
import com.inmobi.rendering.a.3;
import com.inmobi.rendering.a.4;
import com.inmobi.rendering.a.5;
import com.inmobi.rendering.a.6;
import com.inmobi.rendering.a.7;
import com.inmobi.rendering.a.8;
import com.inmobi.rendering.a.9;
import com.inmobi.rendering.a.a;
import com.inmobi.rendering.a.c;
import com.inmobi.rendering.mraid.i;
import com.inmobi.rendering.mraid.j;
import cz.msebera.android.httpclient.HttpVersion;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JavaScriptBridge */
public class C1582a {
    static final String[] f2647a = new String[]{"tel", "sms", "calendar", "storePicture", "inlineVideo"};
    private static final String f2648b = C1582a.class.getSimpleName();
    private RenderView f2649c;
    private RenderingProperties f2650d;
    private i f2651e;
    private DownloadManager f2652f;
    private BroadcastReceiver f2653g;

    public C1582a(RenderView renderView, RenderingProperties renderingProperties) {
        this.f2649c = renderView;
        this.f2650d = renderingProperties;
    }

    @JavascriptInterface
    public void open(String str, String str2) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else {
            new Handler(this.f2649c.getRenderViewContext().getMainLooper()).post(new 1(this, str, str2));
        }
    }

    @JavascriptInterface
    public void openEmbedded(String str, String str2) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else {
            new Handler(this.f2649c.getRenderViewContext().getMainLooper()).post(new 7(this, str, str2));
        }
    }

    @JavascriptInterface
    public void ping(String str, String str2, boolean z) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
            this.f2649c.a(str, "Invalid URL:" + str2, "ping");
        } else {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "JavaScript called ping() URL: >>> " + str2 + " <<<");
            try {
                c.a().a(str2, z);
            } catch (Exception e) {
                this.f2649c.a(str, "Unexpected error", "ping");
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to fire ping; SDK encountered unexpected error");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling ping() request from creative; " + e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void pingInWebView(String str, String str2, boolean z) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
            this.f2649c.a(str, "Invalid URL:" + str2, "pingInWebView");
        } else {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "JavaScript called pingInWebView() URL: >>> " + str2 + " <<<");
            try {
                c.a().b(str2, z);
            } catch (Exception e) {
                this.f2649c.a(str, "Unexpected error", "pingInWebView");
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to fire ping; SDK encountered unexpected error");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling pingInWebView() request from creative; " + e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void log(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "Log called. Message:" + str2);
    }

    @JavascriptInterface
    public String getPlatformVersion(String str) {
        String num = Integer.toString(VERSION.SDK_INT);
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "getPlatformVersion. Version:" + num);
        return num;
    }

    @JavascriptInterface
    public String getPlatform(String str) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "getPlatform. Platform:" + b.e());
        return b.e();
    }

    @JavascriptInterface
    public void fireAdReady(String str) {
        try {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "fireAdReady called.");
            this.f2649c.getListener().a(this.f2649c);
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "fireAdReady");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling fireAdReady() signal from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public void fireAdFailed(String str) {
        try {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "fireAdFailed called.");
            this.f2649c.getListener().b(this.f2649c);
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "fireAdFailed");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling fireAdFailed() signal from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public String getDefaultPosition(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return new JSONObject().toString();
        }
        synchronized (this.f2649c.getDefaultPositionMonitor()) {
            this.f2649c.setDefaultPositionLock();
            new Handler(this.f2649c.getRenderViewContext().getMainLooper()).post(new 8(this));
            while (this.f2649c.e()) {
                try {
                    this.f2649c.getDefaultPositionMonitor().wait();
                } catch (InterruptedException e) {
                }
            }
        }
        return this.f2649c.getDefaultPosition();
    }

    @JavascriptInterface
    public String getCurrentPosition(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return "";
        }
        synchronized (this.f2649c.getCurrentPositionMonitor()) {
            this.f2649c.setCurrentPositionLock();
            new Handler(this.f2649c.getRenderViewContext().getMainLooper()).post(new 9(this));
            while (this.f2649c.f()) {
                try {
                    this.f2649c.getCurrentPositionMonitor().wait();
                } catch (InterruptedException e) {
                }
            }
        }
        return this.f2649c.getCurrentPosition();
    }

    @JavascriptInterface
    public void setExpandProperties(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "setExpandProperties called. Params:" + str2);
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else if (this.f2649c.getState() == RenderViewState.EXPANDED) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "setExpandProperties can't be called on an already expanded ad.");
        } else {
            try {
                this.f2649c.setExpandProperties(com.inmobi.rendering.mraid.c.a(str2, this.f2649c.getExpandProperties(), this.f2649c.getOrientationProperties()));
            } catch (Exception e) {
                this.f2649c.a(str, "Unexpected error", "setExpandProperties");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in setExpandProperties(); " + e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public String getExpandProperties(String str) {
        if (this.f2649c != null) {
            return this.f2649c.getExpandProperties().c();
        }
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        return "";
    }

    @JavascriptInterface
    public void expand(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "expand called. Url:" + str2);
        if (this.f2650d.a() == PlacementType.PLACEMENT_TYPE_FULLSCREEN) {
            return;
        }
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else if (!this.f2649c.d()) {
            this.f2649c.a(str, "Creative is not visible. Ignoring request.", "expand");
        } else if (str2 == null || str2.length() == 0 || str2.startsWith("http")) {
            new Handler(this.f2649c.getRenderViewContext().getMainLooper()).post(new 10(this, str, str2));
        } else {
            this.f2649c.a(str, "Invalid URL", "expand");
        }
    }

    @JavascriptInterface
    public String getVersion(String str) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "getVersion called. Version:" + b.d());
        return b.d();
    }

    @JavascriptInterface
    public void setResizeProperties(String str, String str2) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "setResizeProperties called. Properties:" + str2);
        j a = j.a(str2, this.f2649c.getResizeProperties());
        if (a == null) {
            this.f2649c.a(str, "setResizeProperties", "All mandatory fields are not present");
        }
        this.f2649c.setResizeProperties(a);
    }

    @JavascriptInterface
    public String getResizeProperties(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return "";
        }
        j resizeProperties = this.f2649c.getResizeProperties();
        return resizeProperties == null ? "" : resizeProperties.a();
    }

    @JavascriptInterface
    public void resize(String str) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "resize called");
        if (this.f2650d.a() == PlacementType.PLACEMENT_TYPE_FULLSCREEN) {
            return;
        }
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else if (this.f2649c.d()) {
            new Handler(this.f2649c.getRenderViewContext().getMainLooper()).post(new 11(this, str));
        } else {
            this.f2649c.a(str, "Creative is not visible. Ignoring request.", "resize");
        }
    }

    @JavascriptInterface
    public void setOrientationProperties(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "setOrientationProperties called: " + str2);
        this.f2651e = i.a(str2, this.f2649c.getOrientationProperties());
        this.f2649c.setOrientationProperties(this.f2651e);
    }

    @JavascriptInterface
    public String getOrientationProperties(String str) {
        String a = this.f2651e.a();
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "getOrientationProperties called: " + a);
        return a;
    }

    @JavascriptInterface
    public void onOrientationChange(String str) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, ">>> onOrientationChange() >>> This API is deprecated!");
    }

    @JavascriptInterface
    public boolean isViewable(String str) {
        if (this.f2649c != null) {
            return this.f2649c.d();
        }
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        return false;
    }

    @JavascriptInterface
    public void useCustomClose(String str, boolean z) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "useCustomClose called:" + z);
        new Handler(this.f2649c.getRenderViewContext().getMainLooper()).post(new 12(this, z, str));
    }

    @JavascriptInterface
    public void playVideo(String str, String str2) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else if (str2 == null || str2.trim().length() == 0 || !str2.startsWith("http") || !(str2.endsWith("mp4") || str2.endsWith("avi") || str2.endsWith("m4v"))) {
            this.f2649c.a(str, "Null or empty or invalid media playback URL supplied", "playVideo");
        } else {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "JavaScript called: playVideo (" + str2 + ")");
            new Handler(this.f2649c.getRenderViewContext().getMainLooper()).post(new 13(this, str, str2));
        }
    }

    @JavascriptInterface
    public String getState(String str) {
        String toLowerCase = this.f2649c.getState().toString().toLowerCase(Locale.ENGLISH);
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "getState called:" + toLowerCase);
        return toLowerCase;
    }

    @JavascriptInterface
    public String getScreenSize(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", DisplayInfo.a().b());
            jSONObject.put("height", DisplayInfo.a().a());
        } catch (JSONException e) {
        } catch (Exception e2) {
            this.f2649c.a(str, "Unexpected error", "getScreenSize");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error while getting screen dimensions; " + e2.getMessage());
        }
        String jSONObject2 = jSONObject.toString();
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "getScreenSize called:" + jSONObject2);
        return jSONObject2;
    }

    @JavascriptInterface
    public String getMaxSize(String str) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "getMaxSize called");
        JSONObject jSONObject = new JSONObject();
        try {
            int i;
            Activity fullScreenActivity = this.f2649c.getFullScreenActivity();
            if (fullScreenActivity == null) {
                if (!(this.f2649c.getRenderViewContext() instanceof Activity)) {
                    return getScreenSize(str);
                }
                fullScreenActivity = (Activity) this.f2649c.getRenderViewContext();
            }
            FrameLayout frameLayout = (FrameLayout) fullScreenActivity.findViewById(16908290);
            int b = DisplayInfo.b(frameLayout.getWidth());
            int b2 = DisplayInfo.b(frameLayout.getHeight());
            if (this.f2649c.getFullScreenActivity() == null || !(b == 0 || b2 == 0)) {
                i = b2;
                b2 = b;
            } else {
                a aVar = new a(frameLayout);
                frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(aVar);
                synchronized (a.a(aVar)) {
                    try {
                        a.a(aVar).wait();
                    } catch (InterruptedException e) {
                    }
                    b2 = a.b(aVar);
                    i = a.c(aVar);
                }
            }
            try {
                jSONObject.put("width", b2);
                jSONObject.put("height", i);
            } catch (Throwable e2) {
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "Error while creating max size Json.", e2);
            }
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "getMaxSize called:" + jSONObject.toString());
        } catch (Exception e3) {
            this.f2649c.a(str, "Unexpected error", "getMaxSize");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling getMaxSize() request from creative; " + e3.getMessage());
        }
        return jSONObject.toString();
    }

    @JavascriptInterface
    public void close(String str) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "close called");
        new Handler(this.f2649c.getRenderViewContext().getMainLooper()).post(new 14(this, str));
    }

    @JavascriptInterface
    public String getPlacementType(String str) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "getPlacementType called");
        if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.f2650d.a()) {
            return "interstitial";
        }
        return "inline";
    }

    @JavascriptInterface
    @SuppressLint({"NewApi"})
    public void storePicture(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "storePicture called with URL: " + str2);
        if (!this.f2649c.f("storePicture")) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "storePicture called despite the fact that it is not supported");
        } else if (str2 == null || str2.length() == 0) {
            this.f2649c.a(str, "Null or empty URL supplied", "storePicture");
        } else if (str2.startsWith("http") || str2.startsWith(HttpVersion.HTTP)) {
            Context b = com.inmobi.commons.a.a.b();
            if (b == null) {
                return;
            }
            if (VERSION.SDK_INT < 23 || b.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                try {
                    m3434a(str, str2);
                    return;
                } catch (Exception e) {
                    this.f2649c.a(str, "Unexpected error", "storePicture");
                    Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to store picture to gallery; SDK encountered an unexpected error");
                    Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling storePicture() request from creative; " + e.getMessage());
                    return;
                }
            }
            InMobiAdActivity.a(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, new 2(this, str, str2));
        } else {
            this.f2649c.a(str, "Invalid URL scheme - only HTTP(S) is supported", "storePicture");
        }
    }

    private void m3434a(String str, String str2) {
        Context b = com.inmobi.commons.a.a.b();
        if (b != null) {
            if (this.f2652f == null) {
                this.f2652f = (DownloadManager) b.getSystemService("download");
            }
            try {
                Uri parse = Uri.parse(str2);
                Request request = new Request(parse);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, parse.getLastPathSegment());
                registerBroadcastListener(str);
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "Download enqueued with ID: " + this.f2652f.enqueue(request));
            } catch (ParseException e) {
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "Invalid URL provided to storePicture " + e.getMessage());
                this.f2649c.a(str, "Invalid URL", "storePicture");
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void registerBroadcastListener(String str) {
        Context b = com.inmobi.commons.a.a.b();
        if (b != null && this.f2653g == null) {
            this.f2653g = new 3(this, str);
            b.registerReceiver(this.f2653g, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        }
    }

    @SuppressLint({"NewApi"})
    public void unRegisterBroadcastListener() {
        Context b = com.inmobi.commons.a.a.b();
        if (b != null && this.f2653g != null) {
            b.unregisterReceiver(this.f2653g);
            this.f2653g = null;
        }
    }

    @JavascriptInterface
    @TargetApi(23)
    public void createCalendarEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else if (!this.f2649c.f("calendar")) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "createCalendarEvent called even when it is not supported");
        } else if (str3 == null || str3.trim().length() == 0 || str4 == null || str4.trim().length() == 0) {
            this.f2649c.a(str, "Mandatory parameter(s) start and/or end date not supplied", "createCalendarEvent");
        } else {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "createCalendarEvent called with parameters: \nevent ID: " + str2 + "; startDate: " + str3 + "; endDate: " + str4 + "; location: " + str5 + "; description: " + str6 + "; summary: " + str7 + "; status: " + str8 + "; transparency: " + str9 + "; recurrence: " + str10 + "; reminder: " + str11);
            Context b = com.inmobi.commons.a.a.b();
            if (b == null) {
                return;
            }
            if (VERSION.SDK_INT < 23 || (b.checkSelfPermission("android.permission.WRITE_CALENDAR") == 0 && b.checkSelfPermission("android.permission.READ_CALENDAR") == 0)) {
                try {
                    this.f2649c.a(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
                    return;
                } catch (Exception e) {
                    this.f2649c.a(str, "Unexpected error", "createCalendarEvent");
                    Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not create calendar event; SDK encountered unexpected error");
                    Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling createCalendarEvent() request from creative; " + e.getMessage());
                    return;
                }
            }
            InMobiAdActivity.a(new String[]{"android.permission.WRITE_CALENDAR", "android.permission.READ_CALENDAR"}, new 4(this, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11));
        }
    }

    @JavascriptInterface
    public void postToSocial(String str, int i, String str2, String str3, String str4) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "postToSocial called with parameters: socialType: " + i + "; text: " + str2 + "; link: " + str3 + "; image URL: " + str4);
        try {
            this.f2649c.a(str, i, str2, str3, str4);
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "postToSocial");
            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not post to social network; SDK encountered an unexpected error");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered an unexpected error in handling the postToSocial() request from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public String getSdkVersion(String str) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "getSdkVersion called. Version:" + b.c());
        return b.c();
    }

    @JavascriptInterface
    public String supports(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "Checking support for: " + str2);
        if (Arrays.asList(f2647a).contains(str2) || this.f2649c.f(str2)) {
            return String.valueOf(this.f2649c.f(str2));
        }
        return SchemaSymbols.ATTVAL_FALSE;
    }

    @JavascriptInterface
    public void openExternal(String str, String str2, @Nullable String str3) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "openExternal called with url: " + str2);
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        Object obj = (str2 == null || !str2.startsWith("http") || URLUtil.isValidUrl(str2)) ? null : 1;
        if (obj == null) {
            try {
                this.f2649c.a("openExternal", str, str2, str3);
            } catch (Exception e) {
                this.f2649c.a(str, "Unexpected error", "openExternal");
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not open URL; SDK encountered an unexpected error");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling openExternal() request from creative; " + e.getMessage());
            }
        } else if (str3 == null || !str3.startsWith("http") || URLUtil.isValidUrl(str3)) {
            try {
                this.f2649c.a("openExternal", str, str3, null);
            } catch (Exception e2) {
                this.f2649c.a(str, "Unexpected error", "openExternal");
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not open URL; SDK encountered an unexpected error");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling openExternal() request from creative; " + e2.getMessage());
            }
        } else {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "openExternal called with invalid url (" + str2 + ")");
            this.f2649c.a(str, "Invalid URL", "openExternal");
        }
    }

    @JavascriptInterface
    public void asyncPing(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "asyncPing called: " + str2);
        if (URLUtil.isValidUrl(str2)) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("command", "ping");
                hashMap.put("scheme", bj.a(str));
                this.f2649c.a("ads", "CreativeInvokedAction", hashMap);
                NetworkRequest networkRequest = new NetworkRequest(RequestType.GET, str2, false, null);
                networkRequest.a(false);
                new com.inmobi.commons.core.network.a(networkRequest, new 5(this, networkRequest, SystemClock.elapsedRealtime())).a();
                return;
            } catch (Exception e) {
                this.f2649c.a(str, "Unexpected error", "asyncPing");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered internal error in handling asyncPing() request from creative; " + e.getMessage());
                return;
            }
        }
        this.f2649c.a(str, "Invalid url", "asyncPing");
    }

    @JavascriptInterface
    public void showAlert(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "showAlert: " + str2);
    }

    @JavascriptInterface
    public void disableCloseRegion(String str, boolean z) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else {
            new Handler(this.f2649c.getRenderViewContext().getMainLooper()).post(new 6(this, z, str));
        }
    }

    @JavascriptInterface
    public void onUserInteraction(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "onUserInteraction called. Params:" + str2);
        if (str2 == null) {
            try {
                this.f2649c.getListener().b(this.f2649c, new HashMap());
                return;
            } catch (Exception e) {
                this.f2649c.a(str, "Unexpected error", "onUserInteraction");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling onUserInteraction() signal from creative; " + e.getMessage());
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            HashMap hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                hashMap.put(str3, jSONObject.get(str3));
            }
            try {
                this.f2649c.getListener().b(this.f2649c, hashMap);
            } catch (Exception e2) {
                this.f2649c.a(str, "Unexpected error", "onUserInteraction");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling onUserInteraction() signal from creative; " + e2.getMessage());
            }
        } catch (JSONException e3) {
            try {
                this.f2649c.getListener().b(this.f2649c, new HashMap());
            } catch (Exception e22) {
                this.f2649c.a(str, "Unexpected error", "onUserInteraction");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling onUserInteraction() signal from creative; " + e22.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void incentCompleted(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "incentCompleted called. IncentData:" + str2);
        if (str2 == null) {
            try {
                this.f2649c.getListener().a(this.f2649c, new HashMap());
                return;
            } catch (Exception e) {
                this.f2649c.a(str, "Unexpected error", "incentCompleted");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling onUserInteraction() signal from creative; " + e.getMessage());
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            HashMap hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                hashMap.put(str3, jSONObject.get(str3));
            }
            try {
                this.f2649c.getListener().a(this.f2649c, hashMap);
            } catch (Exception e2) {
                this.f2649c.a(str, "Unexpected error", "incentCompleted");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling onUserInteraction() signal from creative; " + e2.getMessage());
            }
        } catch (JSONException e3) {
            try {
                this.f2649c.getListener().a(this.f2649c, new HashMap());
            } catch (Exception e22) {
                this.f2649c.a(str, "Unexpected error", "incentCompleted");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling onUserInteraction() signal from creative; " + e22.getMessage());
            }
        }
    }

    @JavascriptInterface
    public String getOrientation(String str) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "getOrientation called");
        int b = DisplayInfo.b();
        if (b == ORIENTATION_VALUES.PORTRAIT.getValue()) {
            return SchemaSymbols.ATTVAL_FALSE_0;
        }
        if (b == ORIENTATION_VALUES.LANDSCAPE.getValue()) {
            return "90";
        }
        if (b == ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue()) {
            return "180";
        }
        if (b == ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue()) {
            return "270";
        }
        return "-1";
    }

    @JavascriptInterface
    public void saveContent(String str, String str2, String str3) {
        if (str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "saveContent called with invalid parameters");
            JSONObject jSONObject = new JSONObject();
            try {
                String str4 = "url";
                if (str3 == null) {
                    str3 = "";
                }
                jSONObject.put(str4, str3);
                jSONObject.put("reason", 1);
            } catch (JSONException e) {
            }
            String replace = jSONObject.toString().replace("\"", "\\\"");
            StringBuilder append = new StringBuilder().append("sendSaveContentResult(\"saveContent_");
            if (str2 == null) {
                str2 = "";
            }
            this.f2649c.a(str, append.append(str2).append("\", 'failed', \"").append(replace).append("\");").toString());
            return;
        }
        try {
            this.f2649c.c(str, str2, str3);
        } catch (Exception e2) {
            this.f2649c.a(str, "Unexpected error", "saveContent");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling saveContent() request from creative; " + e2.getMessage());
        }
    }

    @JavascriptInterface
    public void cancelSaveContent(String str, String str2) {
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "cancelSaveContent called. mediaId:" + str2);
        try {
            this.f2649c.e(str2);
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "cancelSaveContent");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling cancelSaveContent() request from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public String isDeviceMuted(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return SchemaSymbols.ATTVAL_FALSE;
        }
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "JavaScript called: isDeviceMuted()");
        boolean z = false;
        try {
            z = this.f2649c.getMediaProcessor().b();
        } catch (Exception e) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in checking if device is muted; " + e.getMessage());
        }
        return String.valueOf(z);
    }

    @JavascriptInterface
    public String isHeadphonePlugged(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return SchemaSymbols.ATTVAL_FALSE;
        }
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "JavaScript called: isHeadphonePlugged()");
        boolean z = false;
        try {
            z = this.f2649c.getMediaProcessor().f();
        } catch (Exception e) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in checking if headphones are plugged-in; " + e.getMessage());
        }
        return String.valueOf(z);
    }

    @JavascriptInterface
    public void registerDeviceMuteEventListener(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        try {
            this.f2649c.getMediaProcessor().a(str);
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "registerDeviceMuteEventListener");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling registerDeviceMuteEventListener() request from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public void unregisterDeviceMuteEventListener(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "Unregister device mute event listener ...");
        try {
            this.f2649c.getMediaProcessor().c();
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "unRegisterDeviceMuteEventListener");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling unregisterDeviceMuteEventListener() request from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public void registerDeviceVolumeChangeEventListener(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        try {
            this.f2649c.getMediaProcessor().b(str);
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "registerDeviceVolumeChangeEventListener");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling registerDeviceVolumeChangeEventListener() request from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public void unregisterDeviceVolumeChangeEventListener(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "Unregister device volume change listener ...");
        try {
            this.f2649c.getMediaProcessor().d();
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "unregisterDeviceVolumeChangeEventListener");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling unregisterDeviceVolumeChangeEventListener() request from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public int getDeviceVolume(String str) {
        int i = -1;
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else {
            try {
                i = this.f2649c.getMediaProcessor().e();
            } catch (Exception e) {
                this.f2649c.a(str, "Unexpected error", "getDeviceVolume");
                Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling getDeviceVolume() request from creative; " + e.getMessage());
            }
        }
        return i;
    }

    @JavascriptInterface
    public void registerHeadphonePluggedEventListener(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        try {
            this.f2649c.getMediaProcessor().c(str);
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "registerHeadphonePluggedEventListener");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling registerHeadphonePluggedEventListener() request from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public void unregisterHeadphonePluggedEventListener(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "Unregister headphone plugged event listener ...");
        try {
            this.f2649c.getMediaProcessor().g();
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "unregisterHeadphonePluggedEventListener");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling unregisterHeadphonePluggedEventListener() request from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public void disableBackButton(String str, boolean z) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        } else {
            this.f2649c.setDisableBackButton(z);
        }
    }

    @JavascriptInterface
    public boolean isBackButtonDisabled(String str) {
        if (this.f2649c != null) {
            return this.f2649c.j();
        }
        Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
        return false;
    }

    @JavascriptInterface
    public void registerBackButtonPressedEventListener(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        try {
            this.f2649c.c(str);
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "registerBackButtonPressedEventListener");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling registerBackButtonPressedEventListener() request from creative; " + e.getMessage());
        }
    }

    @JavascriptInterface
    public void unregisterBackButtonPressedEventListener(String str) {
        if (this.f2649c == null) {
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "Found a null instance of render view!");
            return;
        }
        try {
            this.f2649c.k();
        } catch (Exception e) {
            this.f2649c.a(str, "Unexpected error", "unregisterBackButtonPressedEventListener");
            Logger.a(InternalLogLevel.INTERNAL, f2648b, "SDK encountered unexpected error in handling unregisterBackButtonPressedEventListener() request from creative; " + e.getMessage());
        }
    }
}
