package com.appsgeyser.sdk.webwiewclient;

import android.app.Activity;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cz.msebera.android.httpclient.protocol.HTTP;

class SimpleWebViewClient extends WebViewClient {
    Activity activity;

    SimpleWebViewClient(Activity activity) {
        this.activity = activity;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        try {
            if (MailTo.isMailTo(url)) {
                handleMailTo(url);
                return true;
            } else if (url.startsWith("tel:")) {
                this.activity.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(url)));
                return true;
            } else if (url.startsWith("market:") || url.startsWith("geo:")) {
                this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                return true;
            } else if (url.startsWith("smsto:")) {
                handleSmsTo(url);
                return true;
            } else {
                if (!(url.startsWith("http:") || url.startsWith("https:") || url.startsWith("file:"))) {
                    Intent internetIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                    if (this.activity.getPackageManager().resolveActivity(internetIntent, 0) != null) {
                        this.activity.startActivity(internetIntent);
                        return true;
                    }
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleSmsTo(String url) {
        String[] urlParts = url.split(":");
        String phone = urlParts[1];
        String body = "";
        if (urlParts.length > 1) {
            body = urlParts[2];
        }
        Intent smsIntent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + phone));
        smsIntent.putExtra("address", phone);
        smsIntent.putExtra("sms_body", body);
        this.activity.startActivity(smsIntent);
    }

    private void handleMailTo(String url) {
        MailTo mt = MailTo.parse(url);
        if (!TextUtils.isEmpty(mt.getTo())) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(HTTP.PLAIN_TEXT_TYPE);
            intent.putExtra("android.intent.extra.EMAIL", new String[]{mt.getTo()});
            intent.putExtra("android.intent.extra.SUBJECT", mt.getSubject());
            intent.putExtra("android.intent.extra.CC", mt.getCc());
            intent.putExtra("android.intent.extra.TEXT", mt.getBody());
            this.activity.startActivity(intent);
        }
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        new SslErrorDialog(this.activity).execute(view, handler, error);
    }
}
