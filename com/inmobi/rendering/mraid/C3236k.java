package com.inmobi.rendering.mraid;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import com.appnext.base.p023b.C1042c;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.PlusShare.Builder;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.InMobiAdActivity.C3178a;
import com.inmobi.rendering.RenderView;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* compiled from: SystemTasksProcessor */
public class C3236k {
    private static final String f8143a = C3236k.class.getSimpleName();
    private RenderView f8144b;
    private C3235a f8145c;

    /* compiled from: SystemTasksProcessor */
    static final class C3235a extends Handler {
        private static final String f8141a = C3235a.class.getSimpleName();
        private WeakReference<RenderView> f8142b;

        public C3235a(Looper looper, RenderView renderView) {
            super(looper);
            this.f8142b = new WeakReference(renderView);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    String str = (String) message.obj;
                    String str2 = "broadcastEvent('vibrateComplete');";
                    RenderView renderView = (RenderView) this.f8142b.get();
                    if (renderView != null) {
                        renderView.m10630a(str, "broadcastEvent('vibrateComplete');");
                        return;
                    }
                    return;
                default:
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8141a, "Unknown message type. Ignoring ...");
                    return;
            }
        }
    }

    public C3236k(RenderView renderView) {
        this.f8144b = renderView;
        HandlerThread handlerThread = new HandlerThread("SystemTasksHandlerThread");
        handlerThread.start();
        this.f8145c = new C3235a(handlerThread.getLooper(), renderView);
    }

    @TargetApi(14)
    public void m10804a(String str, Context context, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        final int a = C3219a.m10742a(context);
        Calendar b = C3219a.m10746b(str3);
        if (b == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, "Invalid event start date provided. date string: " + str3);
            return;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, "Event start: " + b.get(1) + "-" + b.get(2) + "-" + b.get(5));
        Calendar b2 = C3219a.m10746b(str4);
        if (b2 == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, "Invalid event end date provided. date string: " + str4);
            return;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, "Event end: " + b2.get(1) + "-" + b2.get(2) + "-" + b2.get(5));
        Intent putExtra = new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("calendar_id", str2).putExtra("beginTime", b.getTimeInMillis()).putExtra("endTime", b2.getTimeInMillis()).putExtra("allDay", false).putExtra("title", str6).putExtra("eventLocation", str5).putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str7);
        if (str9.equals("transparent")) {
            putExtra.putExtra("availability", 1);
        } else {
            if (str9.equals("opaque")) {
                putExtra.putExtra("availability", 0);
            }
        }
        String a2 = m10799a(str10);
        if (a2.length() != 0) {
            putExtra.putExtra("rrule", a2);
        }
        final Context context2 = context;
        final String str12 = str8;
        final String str13 = str11;
        final String str14 = str3;
        final String str15 = str;
        int a3 = InMobiAdActivity.m10546a(putExtra, new C3178a(this) {
            final /* synthetic */ C3236k f8140g;

            public void mo6267a(int i, Intent intent) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3236k.f8143a, "Result code: " + i);
                if (a == C3219a.m10742a(context2)) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3236k.f8143a, "User cancelled the create calendar event");
                    return;
                }
                ContentValues contentValues = new ContentValues();
                String str = str12;
                int i2 = -1;
                switch (str.hashCode()) {
                    case -1320822226:
                        if (str.equals("tentative")) {
                            i2 = 0;
                            break;
                        }
                        break;
                    case -804109473:
                        if (str.equals("confirmed")) {
                            i2 = 1;
                            break;
                        }
                        break;
                    case 476588369:
                        if (str.equals("cancelled")) {
                            i2 = 2;
                            break;
                        }
                        break;
                }
                switch (i2) {
                    case 0:
                        contentValues.put("eventStatus", Integer.valueOf(0));
                        break;
                    case 1:
                        contentValues.put("eventStatus", Integer.valueOf(1));
                        break;
                    case 2:
                        contentValues.put("eventStatus", Integer.valueOf(2));
                        break;
                }
                ContentResolver contentResolver = context2.getContentResolver();
                contentResolver.update(ContentUris.withAppendedId(Events.CONTENT_URI, (long) C3219a.m10742a(context2)), contentValues, null, null);
                if (str13 != null && !"".equals(str13)) {
                    try {
                        if (str13.startsWith(Marker.ANY_NON_NULL_MARKER)) {
                            i2 = Integer.parseInt(str13.substring(1)) / 60000;
                        } else {
                            i2 = Integer.parseInt(str13) / 60000;
                        }
                    } catch (NumberFormatException e) {
                        Calendar b = C3219a.m10746b(str13);
                        if (b == null) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, C3236k.f8143a, "Invalid reminder date provided. date string: " + str13);
                            return;
                        }
                        i2 = ((int) (b.getTimeInMillis() - C3219a.m10746b(str14).getTimeInMillis())) / 60000;
                        if (i2 > 0) {
                            this.f8140g.f8144b.m10631a(str15, "Reminder format is incorrect. Reminder can be set only before the event starts", "createCalendarEvent");
                            return;
                        }
                    }
                    i2 = -i2;
                    contentResolver.delete(Reminders.CONTENT_URI, "event_id=?", new String[]{C3219a.m10742a(context2) + ""});
                    if (i2 < 0) {
                        this.f8140g.f8144b.m10631a(str15, "Reminder format is incorrect. Reminder can be set only before the event starts", "createCalendarEvent");
                        return;
                    }
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("event_id", Integer.valueOf(C3219a.m10742a(context2)));
                    contentValues2.put("method", Integer.valueOf(1));
                    contentValues2.put("minutes", Integer.valueOf(i2));
                    contentResolver.insert(Reminders.CONTENT_URI, contentValues2);
                }
            }
        });
        Intent intent = new Intent(context, InMobiAdActivity.class);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 103);
        intent.putExtra("id", a3);
        C3105a.m10072a(context, intent);
    }

    public void m10803a(String str, Context context, int i, String str2, String str3, String str4) {
        if (str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0 || !str3.startsWith("http") || str4 == null || str4.length() == 0 || !str4.startsWith("http") || !str4.endsWith(".jpg")) {
            this.f8144b.m10631a(str, "Attempting to share with null/empty/invalid parameters", "postToSocial");
            return;
        }
        Intent intent = null;
        String str5;
        switch (i) {
            case 1:
                str5 = "";
                break;
            case 2:
                if (m10801b()) {
                    intent = new Builder(context).setType(HTTP.PLAIN_TEXT_TYPE).setText(str2 + " " + str3 + " " + str4).setContentUrl(Uri.parse(str4)).getIntent();
                    break;
                }
                break;
            case 3:
                str5 = str2 + " " + str3 + " " + str4;
                intent = new Intent();
                intent.setType(HTTP.PLAIN_TEXT_TYPE);
                intent.setPackage("com.twitter.android");
                intent.putExtra("android.intent.extra.TEXT", str5);
                break;
            default:
                this.f8144b.m10631a(str, "Unsupported type of social network", "postToSocial");
                return;
        }
        if (intent != null) {
            try {
                C3105a.m10072a(context, intent);
                return;
            } catch (ActivityNotFoundException e) {
                m10800a(context, i, str2, str3, str4);
                return;
            }
        }
        m10800a(context, i, str2, str3, str4);
    }

    private boolean m10801b() {
        if (!C3105a.m10076a()) {
            return false;
        }
        try {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(C3105a.m10078b()) == 0) {
                return true;
            }
            return false;
        } catch (Error e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, "Google Play Services is not installed!");
            return false;
        } catch (Exception e2) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("type", "RuntimeException");
                hashMap.put("message", e2.getMessage() + "");
                C3135c.m10255a().m10280a("ads", "ExceptionCaught", hashMap);
                return false;
            } catch (Exception e22) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, "Error in submitting telemetry event : (" + e22.getMessage() + ")");
                return false;
            }
        }
    }

    private void m10800a(Context context, int i, String str, String str2, String str3) {
        String str4 = str + " " + str2 + " " + str3;
        String str5 = null;
        switch (i) {
            case 1:
                try {
                    str5 = "https://www.facebook.com/dialog/feed?app_id=181821551957328&link=" + URLEncoder.encode(str2, "UTF-8") + "&picture=" + URLEncoder.encode(str3, "UTF-8") + "&name=&description=" + URLEncoder.encode(str, "UTF-8") + "&redirect_uri=" + URLEncoder.encode(str2, "UTF-8");
                    break;
                } catch (UnsupportedEncodingException e) {
                    return;
                }
            case 2:
                str5 = "https://m.google.com/app/plus/x/?v=compose&content=" + URLEncoder.encode(str4, "UTF-8");
                break;
            case 3:
                str5 = "http://twitter.com/home?status=" + URLEncoder.encode(str4, "UTF-8");
                break;
        }
        if (str5 != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str5));
            try {
                C3105a.m10072a(context, intent);
                return;
            } catch (ActivityNotFoundException e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, e2.getMessage());
                return;
            }
        }
        Intent intent2 = new Intent();
        intent2.setType(HTTP.PLAIN_TEXT_TYPE);
        intent2.putExtra("android.intent.extra.TEXT", str4);
        try {
            C3105a.m10072a(context, intent2);
        } catch (ActivityNotFoundException e22) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, e22.getMessage());
        }
    }

    public void m10802a(Context context) {
        if (this.f8145c != null && this.f8145c.hasMessages(1)) {
            this.f8145c.removeMessages(1);
            ((Vibrator) context.getSystemService("vibrator")).cancel();
            Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, "Canceling any pending/ongoing vibrate requests");
        }
    }

    private String m10799a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str.length() == 0) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("frequency");
            if (optString == null || "".equals(optString)) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, "Error Parsing recurrence string. Frequency supplied is null");
                return "";
            } else if ("daily".equals(optString) || "weekly".equals(optString) || "monthly".equals(optString) || "yearly".equals(optString)) {
                stringBuilder.append("freq=").append(optString).append(";");
                String optString2 = jSONObject.optString(C1042c.jE);
                if (!(optString2 == null || "".equals(optString2))) {
                    stringBuilder.append("interval=").append(Integer.parseInt(optString2)).append(";");
                }
                optString2 = C3219a.m10743a(jSONObject.optString(ClientCookie.EXPIRES_ATTR));
                if (optString2 != null) {
                    stringBuilder.append("until=").append(optString2.replace(Marker.ANY_NON_NULL_MARKER, "Z+").replace("-", "Z-")).append(";");
                }
                if (optString.equals("weekly")) {
                    optString2 = C3219a.m10744a(jSONObject.optJSONArray("daysInWeek"));
                    if (optString2 != null) {
                        stringBuilder.append("byday=").append(optString2).append(";");
                    }
                }
                if (optString.equals("monthly")) {
                    optString2 = C3219a.m10745a(jSONObject.optJSONArray("daysInMonth"), -31, 31);
                    if (optString2 != null) {
                        stringBuilder.append("bymonthday=").append(optString2).append(";");
                    }
                }
                if (optString.equals("yearly")) {
                    optString2 = C3219a.m10745a(jSONObject.optJSONArray("daysInYear"), -366, 366);
                    if (optString2 != null) {
                        stringBuilder.append("byyearday=").append(optString2).append(";");
                    }
                }
                if (optString.equals("monthly")) {
                    optString2 = C3219a.m10745a(jSONObject.optJSONArray("weeksInMonth"), -4, 4);
                    if (optString2 != null) {
                        stringBuilder.append("byweekno=").append(optString2).append(";");
                    }
                }
                if (optString.equals("yearly")) {
                    String a = C3219a.m10745a(jSONObject.optJSONArray("monthsInYear"), 1, 12);
                    if (a != null) {
                        stringBuilder.append("bymonth=").append(a).append(";");
                    }
                }
                return stringBuilder.toString();
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, "Error Parsing recurrence string. Invalid Frequency supplied");
                return "";
            }
        } catch (JSONException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8143a, "Error Parsing recurrence string" + e.toString());
            return "";
        }
    }
}
