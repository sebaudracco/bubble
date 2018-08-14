package com.vungle.publisher;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import com.vungle.publisher.image.AssetBitmapFactory;
import com.vungle.publisher.image.AssetBitmapFactory.a;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class zo {
    @Inject
    AssetBitmapFactory f3504a;

    public void m4939a(ImageView imageView, a aVar) {
        m4938a(imageView, m4937a(aVar));
    }

    public void m4938a(ImageView imageView, Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public Bitmap m4937a(a aVar) {
        return this.f3504a.m4173a(aVar);
    }

    public static String m4932a(WebView webView) {
        return webView.getSettings().getUserAgentString();
    }

    public static void m4934a(View view) {
        view.setOnTouchListener(zp.m4940a());
    }

    public static void m4936b(View view) {
        view.setOnTouchListener(null);
    }

    public static void m4933a(Context context) {
        ((Activity) context).getWindow().setFlags(16777216, 16777216);
    }
}
