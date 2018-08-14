package com.bgjd.ici.p032h;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Base64;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C1511b {
    public C1509a name;
    public C1510b type;
    private Object value;

    public enum C1509a {
        NO_VALID,
        ID,
        LAYOUT_WIDTH,
        LAYOUT_HEIGHT,
        PADDING_LEFT,
        PADDING_RIGHT,
        PADDING_TOP,
        PADDING_BOTTOM,
        PADDING,
        LAYOUT_MARGINLEFT,
        LAYOUT_MARGINRIGHT,
        LAYOUT_MARGINTOP,
        LAYOUT_MARGINBOTTOM,
        LAYOUT_MARGIN,
        BACKGROUND,
        ENABLED,
        SELECTED,
        CLICKABLE,
        SCALEX,
        SCALEY,
        MINWIDTH,
        MINHEIGTH,
        TEXT,
        TEXTCOLOR,
        TEXTSIZE,
        TEXTSTYLE,
        ELLIPSIZE,
        MAXLINES,
        GRAVITY,
        DRAWABLETOP,
        DRAWABLEBOTTOM,
        DRAWABLELEFT,
        DRAWABLERIGHT,
        SRC,
        SCALETYPE,
        ADJUSTVIEWBOUNDS,
        LAYOUT_ABOVE,
        LAYOUT_ALIGNBASELINE,
        LAYOUT_ALIGNBOTTOM,
        LAYOUT_ALIGNEND,
        LAYOUT_ALIGNLEFT,
        LAYOUT_ALIGNPARENTBOTTOM,
        LAYOUT_ALIGNPARENTEND,
        LAYOUT_ALIGNPARENTLEFT,
        LAYOUT_ALIGNPARENTRIGHT,
        LAYOUT_ALIGNPARENTSTART,
        LAYOUT_ALIGNPARENTTOP,
        LAYOUT_ALIGNRIGHT,
        LAYOUT_ALIGNSTART,
        LAYOUT_ALIGNTOP,
        LAYOUT_ALIGNWITHPARENTIFMISSING,
        LAYOUT_BELOW,
        LAYOUT_CENTERHORIZONTAL,
        LAYOUT_CENTERINPARENT,
        LAYOUT_CENTERVERTICAL,
        LAYOUT_TOENDOF,
        LAYOUT_TOLEFTOF,
        LAYOUT_TORIGHTOF,
        LAYOUT_TOSTARTOF,
        LAYOUT_GRAVITY,
        LAYOUT_WEIGHT,
        SUM_WEIGHT,
        ORIENTATION,
        TAG,
        FUNCTION
    }

    public enum C1510b {
        NO_VALID,
        STRING,
        DIMEN,
        INTEGER,
        FLOAT,
        COLOR,
        REF,
        BOOLEAN,
        BASE64,
        DRAWABLE,
        JSON
    }

    private Object convertValue(Object obj) {
        String str = null;
        boolean z = true;
        int i = 0;
        if (obj == null) {
            return str;
        }
        switch (this.type) {
            case INTEGER:
                return Integer.valueOf(Integer.parseInt(obj.toString()));
            case FLOAT:
                return Float.valueOf(Float.parseFloat(obj.toString()));
            case DIMEN:
                return Float.valueOf(convertDimenToPixel(obj.toString()));
            case COLOR:
                return Integer.valueOf(convertColor(obj.toString()));
            case BOOLEAN:
                str = obj.toString();
                if (str.equalsIgnoreCase("t")) {
                    return Boolean.valueOf(true);
                }
                if (str.equalsIgnoreCase("f")) {
                    return Boolean.valueOf(i);
                }
                if (str.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
                    return Boolean.valueOf(true);
                }
                if (str.equalsIgnoreCase(SchemaSymbols.ATTVAL_FALSE)) {
                    return Boolean.valueOf(i);
                }
                if (Integer.parseInt(str) != 1) {
                    z = i;
                }
                return Boolean.valueOf(z);
            case BASE64:
                try {
                    return BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode(obj.toString(), 0)));
                } catch (Exception e) {
                    return str;
                }
            case DRAWABLE:
                JSONObject jSONObject = (JSONObject) obj;
                GradientDrawable gradientDrawable = new GradientDrawable();
                if (jSONObject != null) {
                    int i2;
                    try {
                        gradientDrawable.setColor(convertColor(jSONObject.getString("COLOR")));
                    } catch (JSONException e2) {
                    }
                    if (jSONObject.has("CORNER")) {
                        String string;
                        try {
                            string = jSONObject.getString("CORNER");
                        } catch (JSONException e3) {
                            string = str;
                        }
                        if (!TextUtils.isEmpty(string)) {
                            if (string.contains("|")) {
                                float[] fArr = new float[8];
                                Arrays.fill(fArr, 0.0f);
                                String[] split = string.split("\\|");
                                int min = Math.min(split.length, fArr.length);
                                for (i2 = i; i2 < min; i2++) {
                                    try {
                                        fArr[i2] = convertDimenToPixel(split[i2]);
                                    } catch (Exception e4) {
                                        fArr[i2] = 0.0f;
                                    }
                                }
                                gradientDrawable.setCornerRadii(fArr);
                            } else {
                                try {
                                    gradientDrawable.setCornerRadius(convertDimenToPixel(string));
                                } catch (Exception e5) {
                                    gradientDrawable.setCornerRadius(0.0f);
                                }
                            }
                        }
                    }
                    i2 = ViewCompat.MEASURED_SIZE_MASK;
                    if (jSONObject.has("STROKECOLOR")) {
                        try {
                            i2 = convertColor(jSONObject.getString("STROKECOLOR"));
                        } catch (JSONException e6) {
                        }
                    }
                    if (jSONObject.has("STROKESIZE")) {
                        try {
                            i = (int) convertDimenToPixel(jSONObject.getString("STROKESIZE"));
                        } catch (JSONException e7) {
                        }
                    }
                    gradientDrawable.setStroke(i, i2);
                }
                return gradientDrawable;
            default:
                return obj;
        }
    }

    public C1511b(JSONObject jSONObject) {
        try {
            this.name = C1509a.valueOf(jSONObject.getString("name").toUpperCase().trim());
        } catch (Exception e) {
            this.name = C1509a.NO_VALID;
        }
        try {
            this.type = C1510b.valueOf(jSONObject.getString("type").toUpperCase().trim());
        } catch (Exception e2) {
            this.type = C1510b.NO_VALID;
        }
        try {
            this.value = convertValue(jSONObject.get(FirebaseAnalytics$Param.VALUE));
        } catch (Exception e3) {
        }
    }

    public boolean isValid() {
        return this.value != null;
    }

    public Object getValueInt(Class<?> cls, String str) {
        try {
            Field field = cls.getField(str);
            if (field != null) {
                return field.get(cls);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        }
        return null;
    }

    public int getValueColor() {
        if (this.type == C1510b.COLOR) {
            return ((Integer) Integer.class.cast(this.value)).intValue();
        }
        return -1;
    }

    public String getValueString() {
        return (String) String.class.cast(this.value);
    }

    public int getValueInt() {
        if (this.value instanceof Integer) {
            return ((Integer) Integer.class.cast(this.value)).intValue();
        }
        if (this.value instanceof Float) {
            return (int) getValueFloat();
        }
        return ((Integer) this.value).intValue();
    }

    public float getValueFloat() {
        return ((Float) Float.class.cast(this.value)).floatValue();
    }

    public Boolean getValueBoolean() {
        return (Boolean) Boolean.class.cast(this.value);
    }

    public Bitmap getValueBitmap() {
        return (Bitmap) this.value;
    }

    public Drawable getValueBitmapDrawable() {
        return new BitmapDrawable(Resources.getSystem(), getValueBitmap());
    }

    public Drawable getValueGradientDrawable() {
        return (Drawable) this.value;
    }

    public JSONObject getValueJSON() {
        return (JSONObject) JSONObject.class.cast(this.value);
    }

    int convertColor(String str) {
        if (str.startsWith("0x")) {
            return (int) Long.parseLong(str.substring(2), 16);
        }
        return Color.parseColor(str);
    }

    float convertDimenToPixel(String str) {
        if (str.endsWith("dp")) {
            return C1507a.dpToPx(Float.parseFloat(str.substring(0, str.length() - 2)));
        }
        if (str.endsWith("sp")) {
            return C1507a.spToPx(Float.parseFloat(str.substring(0, str.length() - 2)));
        }
        if (str.endsWith("px")) {
            return (float) Integer.parseInt(str.substring(0, str.length() - 2));
        }
        if (str.endsWith("%")) {
            return (float) ((int) ((Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f) * ((float) C1507a.deviceWidth())));
        }
        if (str.equalsIgnoreCase("match_parent")) {
            return -1.0f;
        }
        if (str.equalsIgnoreCase("wrap_content")) {
            return -2.0f;
        }
        return (float) Integer.parseInt(str);
    }
}
