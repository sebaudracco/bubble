package com.inmobi.ads;

import android.graphics.Point;
import android.support.annotation.NonNull;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import java.util.Locale;

public class NativeStrandAssetStyle {
    protected C2933b f6774a;
    protected C2932a f6775b;
    protected ContentMode f6776c;
    protected float f6777d;
    protected String f6778e;
    protected String f6779f;
    private Point f6780g;
    private Point f6781h;

    public enum ContentMode {
        CONTENT_MODE_UNSPECIFIED("unspecified"),
        CONTENT_MODE_FILL("fill"),
        CONTENT_MODE_ASPECT_FIT("aspectFit"),
        CONTENT_MODE_ASPECT_FILL("aspectFill");
        
        private final String f6765a;

        private ContentMode(String str) {
            this.f6765a = str;
        }

        public boolean isEqual(String str) {
            return str != null && this.f6765a.equalsIgnoreCase(str);
        }
    }

    enum C2932a {
        BORDER_CORNER_STYLE_CURVED("curved"),
        BORDER_CORNER_STYLE_STRAIGHT("straight");
        
        private final String f6769c;

        private C2932a(String str) {
            this.f6769c = str;
        }
    }

    enum C2933b {
        BORDER_STROKE_STYLE_NONE("none"),
        BORDER_STROKE_STYLE_LINE("line");
        
        private final String f6773c;

        private C2933b(String str) {
            this.f6773c = str;
        }
    }

    NativeStrandAssetStyle() {
        this.f6780g = new Point(0, 0);
        this.f6781h = new Point(0, 0);
        this.f6774a = C2933b.BORDER_STROKE_STYLE_NONE;
        this.f6775b = C2932a.BORDER_CORNER_STYLE_STRAIGHT;
        this.f6777d = Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT;
        this.f6778e = "#ff000000";
        this.f6779f = "#00000000";
        this.f6776c = ContentMode.CONTENT_MODE_FILL;
    }

    public NativeStrandAssetStyle(int i, int i2, int i3, int i4, @NonNull C2933b c2933b, @NonNull C2932a c2932a, @NonNull String str, @NonNull String str2) {
        this(i, i2, i3, i4, ContentMode.CONTENT_MODE_FILL, c2933b, c2932a, str, str2);
    }

    public NativeStrandAssetStyle(int i, int i2, int i3, int i4, ContentMode contentMode, @NonNull C2933b c2933b, @NonNull C2932a c2932a, @NonNull String str, @NonNull String str2) {
        this.f6780g = new Point(i3, i4);
        this.f6781h = new Point(i, i2);
        this.f6774a = c2933b;
        this.f6775b = c2932a;
        this.f6777d = Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT;
        this.f6776c = contentMode;
        if (str.length() == 0) {
            str = "#ff000000";
        }
        this.f6778e = str;
        if (str2.length() == 0) {
            str2 = "#00000000";
        }
        this.f6779f = str2;
    }

    public Point m8849a() {
        return this.f6780g;
    }

    public Point m8850b() {
        return this.f6781h;
    }

    public C2933b m8851c() {
        return this.f6774a;
    }

    public C2932a m8852d() {
        return this.f6775b;
    }

    public float m8853e() {
        return this.f6777d;
    }

    public String m8854f() {
        return this.f6778e.toLowerCase(Locale.US);
    }

    public String mo6187g() {
        return this.f6779f.toLowerCase(Locale.US);
    }

    public ContentMode m8856h() {
        return this.f6776c;
    }
}
