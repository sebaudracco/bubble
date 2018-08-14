package com.bgjd.ici.p030e;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Base64;
import com.bgjd.ici.p025b.C1394a;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.C1431y;

public class C1480e implements C1394a {
    private String f2380a = "";
    private String f2381b = "";
    private String f2382c = "";

    public C1480e(String str, String str2, String str3) {
        this.f2380a = str;
        this.f2381b = str2;
        this.f2382c = str3;
    }

    public void mo2329a(Context context) {
        m3081a(context, this.f2380a, this.f2381b, this.f2382c);
    }

    private void m3081a(Context context, String str, String str2, String str3) {
        try {
            if (m3082a()) {
                Notification notification;
                Class cls = Class.forName("android.app.Notification$Builder");
                Object newInstance = cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                newInstance = cls.getMethod("setContentTitle", new Class[]{CharSequence.class}).invoke(newInstance, new Object[]{str});
                newInstance = cls.getMethod("setContentText", new Class[]{CharSequence.class}).invoke(newInstance, new Object[]{str2});
                newInstance = cls.getMethod("setContentIntent", new Class[]{PendingIntent.class}).invoke(newInstance, new Object[]{PendingIntent.getActivity(context, 0, new Intent("android.intent.action.VIEW", Uri.parse(str3)), 0)});
                byte[] decode = Base64.decode(C1431y.f2222d, 0);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                newInstance = cls.getMethod("setSmallIcon", new Class[]{Integer.TYPE}).invoke(newInstance, new Object[]{Integer.valueOf(17301506)});
                if (VERSION.SDK_INT < 16) {
                    notification = (Notification) cls.getMethod("getNotification", new Class[0]).invoke(newInstance, new Object[0]);
                } else {
                    notification = (Notification) cls.getMethod(C1403a.f2088r, new Class[0]).invoke(newInstance, new Object[0]);
                }
                notification.contentView.setImageViewBitmap(16908294, decodeByteArray);
                ((NotificationManager) context.getSystemService("notification")).notify(1, notification);
                return;
            }
            Notification notification2 = (Notification) Class.forName("android.app.Notification").getConstructor(new Class[]{Integer.TYPE, CharSequence.class, Long.TYPE}).newInstance(new Object[]{Integer.valueOf(17301506), "", Long.valueOf(System.currentTimeMillis())});
            PendingIntent activity = PendingIntent.getActivity(context, 0, new Intent("android.intent.action.VIEW", Uri.parse(str3)), 0);
            notification2.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification2, new Object[]{context, str, str2, activity});
            ((NotificationManager) context.getSystemService("notification")).notify(1, notification2);
        } catch (Exception e) {
            m3083b(context, str, str2, str3);
        }
    }

    private void m3083b(Context context, String str, String str2, String str3) {
        try {
            Class cls = Class.forName("android.support.v7.app.NotificationCompat$Builder");
            Object newInstance = cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
            newInstance = cls.getMethod("setContentTitle", new Class[]{CharSequence.class}).invoke(newInstance, new Object[]{str});
            newInstance = cls.getMethod("setContentText", new Class[]{CharSequence.class}).invoke(newInstance, new Object[]{str2});
            newInstance = cls.getMethod("setContentIntent", new Class[]{PendingIntent.class}).invoke(newInstance, new Object[]{PendingIntent.getActivity(context, 0, new Intent("android.intent.action.VIEW", Uri.parse(str3)), 0)});
            byte[] decode = Base64.decode(C1431y.f2222d, 0);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
            Notification notification = (Notification) cls.getMethod(C1403a.f2088r, new Class[0]).invoke(cls.getMethod("setSmallIcon", new Class[]{Integer.TYPE}).invoke(newInstance, new Object[]{Integer.valueOf(17301506)}), new Object[0]);
            notification.contentView.setImageViewBitmap(16908294, decodeByteArray);
            ((NotificationManager) context.getSystemService("notification")).notify(1, notification);
        } catch (Exception e) {
        }
    }

    private boolean m3082a() {
        try {
            return VERSION.SDK_INT >= 11 && Class.forName("android.app.Notification$Builder") != null;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
