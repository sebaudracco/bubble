package com.vungle.publisher.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Base64;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AssetBitmapFactory {
    @Inject
    Context f3013a;

    @Inject
    AssetBitmapFactory() {
    }

    public Bitmap m4173a(a aVar) {
        byte[] decode = Base64.decode(aVar.a(), 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length, m4174a(this.f3013a));
    }

    Options m4174a(Context context) {
        Options options = new Options();
        options.inDensity = 330;
        options.inTargetDensity = (int) (context.getResources().getDisplayMetrics().density * ((float) options.inDensity));
        return options;
    }
}
