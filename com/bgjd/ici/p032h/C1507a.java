package com.bgjd.ici.p032h;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p030e.C1485h.C1484a;
import com.bgjd.ici.p032h.C1511b.C1510b;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C1507a {
    public static String applyStyleProperties(View view, List<C1511b> list) {
        String str = "";
        for (C1511b c1511b : list) {
            String valueString;
            switch (c1511b.name) {
                case ID:
                    valueString = c1511b.getValueString();
                    break;
                case BACKGROUND:
                    C1507a.applyBackground(view, c1511b);
                    valueString = str;
                    break;
                case TEXT:
                    C1507a.applyText(view, c1511b);
                    valueString = str;
                    break;
                case TEXTCOLOR:
                    C1507a.applyTextColor(view, c1511b);
                    valueString = str;
                    break;
                case TEXTSIZE:
                    C1507a.applyTextSize(view, c1511b);
                    valueString = str;
                    break;
                case TEXTSTYLE:
                    C1507a.applyTextStyle(view, c1511b);
                    valueString = str;
                    break;
                case PADDING:
                    C1507a.applyPadding(view, c1511b);
                    valueString = str;
                    break;
                case PADDING_LEFT:
                    C1507a.applyPadding(view, c1511b, 0);
                    valueString = str;
                    break;
                case PADDING_TOP:
                    C1507a.applyPadding(view, c1511b, 1);
                    valueString = str;
                    break;
                case PADDING_RIGHT:
                    C1507a.applyPadding(view, c1511b, 2);
                    valueString = str;
                    break;
                case PADDING_BOTTOM:
                    C1507a.applyPadding(view, c1511b, 3);
                    valueString = str;
                    break;
                case MINWIDTH:
                    C1507a.applyMinWidth(view, c1511b);
                    valueString = str;
                    break;
                case MINHEIGTH:
                    C1507a.applyMinHeight(view, c1511b);
                    valueString = str;
                    break;
                case ELLIPSIZE:
                    C1507a.applyEllipsize(view, c1511b);
                    valueString = str;
                    break;
                case MAXLINES:
                    C1507a.applyMaxLines(view, c1511b);
                    valueString = str;
                    break;
                case ORIENTATION:
                    C1507a.applyOrientation(view, c1511b);
                    valueString = str;
                    break;
                case SUM_WEIGHT:
                    C1507a.applyWeightSum(view, c1511b);
                    valueString = str;
                    break;
                case GRAVITY:
                    C1507a.applyGravity(view, c1511b);
                    valueString = str;
                    break;
                case SRC:
                    C1507a.applySrc(view, c1511b);
                    valueString = str;
                    break;
                case SCALETYPE:
                    C1507a.applyScaleType(view, c1511b);
                    valueString = str;
                    break;
                case ADJUSTVIEWBOUNDS:
                    C1507a.applyAdjustBounds(view, c1511b);
                    valueString = str;
                    break;
                case DRAWABLELEFT:
                    C1507a.applyCompoundDrawable(view, c1511b, 0);
                    valueString = str;
                    break;
                case DRAWABLETOP:
                    C1507a.applyCompoundDrawable(view, c1511b, 1);
                    valueString = str;
                    break;
                case DRAWABLERIGHT:
                    C1507a.applyCompoundDrawable(view, c1511b, 2);
                    valueString = str;
                    break;
                case DRAWABLEBOTTOM:
                    C1507a.applyCompoundDrawable(view, c1511b, 3);
                    valueString = str;
                    break;
                case ENABLED:
                    C1507a.applyEnabled(view, c1511b);
                    valueString = str;
                    break;
                case SELECTED:
                    C1507a.applySelected(view, c1511b);
                    valueString = str;
                    break;
                case CLICKABLE:
                    C1507a.applyClickable(view, c1511b);
                    valueString = str;
                    break;
                case SCALEX:
                    C1507a.applyScaleX(view, c1511b);
                    valueString = str;
                    break;
                case SCALEY:
                    C1507a.applyScaleY(view, c1511b);
                    valueString = str;
                    break;
                case TAG:
                    C1507a.applyTag(view, c1511b);
                    valueString = str;
                    break;
                case FUNCTION:
                    C1507a.applyFunction(view, c1511b);
                    valueString = str;
                    break;
                default:
                    valueString = str;
                    break;
            }
            str = valueString;
        }
        return str;
    }

    public static void applyLayoutProperties(View view, List<C1511b> list, ViewGroup viewGroup, HashMap<String, Integer> hashMap) {
        if (viewGroup != null) {
            LayoutParams createLayoutParams = C1507a.createLayoutParams(viewGroup);
            for (C1511b c1511b : list) {
                try {
                    switch (c1511b.name) {
                        case LAYOUT_HEIGHT:
                            createLayoutParams.height = c1511b.getValueInt();
                            break;
                        case LAYOUT_WIDTH:
                            createLayoutParams.width = c1511b.getValueInt();
                            break;
                        case LAYOUT_MARGIN:
                            if (!(createLayoutParams instanceof MarginLayoutParams)) {
                                break;
                            }
                            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) createLayoutParams;
                            int valueInt = c1511b.getValueInt();
                            marginLayoutParams.rightMargin = valueInt;
                            marginLayoutParams.leftMargin = valueInt;
                            marginLayoutParams.topMargin = valueInt;
                            marginLayoutParams.bottomMargin = valueInt;
                            break;
                        case LAYOUT_MARGINLEFT:
                            if (!(createLayoutParams instanceof MarginLayoutParams)) {
                                break;
                            }
                            ((MarginLayoutParams) createLayoutParams).leftMargin = c1511b.getValueInt();
                            break;
                        case LAYOUT_MARGINTOP:
                            if (!(createLayoutParams instanceof MarginLayoutParams)) {
                                break;
                            }
                            ((MarginLayoutParams) createLayoutParams).topMargin = c1511b.getValueInt();
                            break;
                        case LAYOUT_MARGINRIGHT:
                            if (!(createLayoutParams instanceof MarginLayoutParams)) {
                                break;
                            }
                            ((MarginLayoutParams) createLayoutParams).rightMargin = c1511b.getValueInt();
                            break;
                        case LAYOUT_MARGINBOTTOM:
                            if (!(createLayoutParams instanceof MarginLayoutParams)) {
                                break;
                            }
                            ((MarginLayoutParams) createLayoutParams).bottomMargin = c1511b.getValueInt();
                            break;
                        case LAYOUT_ABOVE:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(2, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_BELOW:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(3, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_TOLEFTOF:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(0, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_TORIGHTOF:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(1, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_TOSTARTOF:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(16, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_TOENDOF:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(17, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_ALIGNBASELINE:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(4, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_ALIGNLEFT:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(5, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_ALIGNTOP:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(6, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_ALIGNRIGHT:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(7, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_ALIGNBOTTOM:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(8, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_ALIGNSTART:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(18, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_ALIGNEND:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(19, ((Integer) hashMap.get(c1511b.getValueString())).intValue());
                            break;
                        case LAYOUT_ALIGNWITHPARENTIFMISSING:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).alignWithParent = c1511b.getValueBoolean().booleanValue();
                            break;
                        case LAYOUT_ALIGNPARENTTOP:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(10);
                            break;
                        case LAYOUT_ALIGNPARENTBOTTOM:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(12);
                            break;
                        case LAYOUT_ALIGNPARENTLEFT:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(9);
                            break;
                        case LAYOUT_ALIGNPARENTRIGHT:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(11);
                            break;
                        case LAYOUT_ALIGNPARENTSTART:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(20);
                            break;
                        case LAYOUT_ALIGNPARENTEND:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(21);
                            break;
                        case LAYOUT_CENTERHORIZONTAL:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(14);
                            break;
                        case LAYOUT_CENTERVERTICAL:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(15);
                            break;
                        case LAYOUT_CENTERINPARENT:
                            if (!(createLayoutParams instanceof RelativeLayout.LayoutParams)) {
                                break;
                            }
                            ((RelativeLayout.LayoutParams) createLayoutParams).addRule(13);
                            break;
                        case LAYOUT_GRAVITY:
                            switch (c1511b.type) {
                                case INTEGER:
                                    if (!(createLayoutParams instanceof LinearLayout.LayoutParams)) {
                                        break;
                                    }
                                    ((LinearLayout.LayoutParams) createLayoutParams).gravity = c1511b.getValueInt();
                                    break;
                                case STRING:
                                    if (!(createLayoutParams instanceof LinearLayout.LayoutParams)) {
                                        break;
                                    }
                                    ((LinearLayout.LayoutParams) createLayoutParams).gravity = ((Integer) c1511b.getValueInt(Gravity.class, c1511b.getValueString().toUpperCase())).intValue();
                                    break;
                                default:
                                    break;
                            }
                        case LAYOUT_WEIGHT:
                            switch (c1511b.type) {
                                case FLOAT:
                                    if (!(createLayoutParams instanceof LinearLayout.LayoutParams)) {
                                        break;
                                    }
                                    ((LinearLayout.LayoutParams) createLayoutParams).weight = c1511b.getValueFloat();
                                    break;
                                default:
                                    break;
                            }
                        default:
                            break;
                    }
                } catch (Exception e) {
                }
            }
            view.setLayoutParams(createLayoutParams);
        }
    }

    public static LayoutParams createLayoutParams(ViewGroup viewGroup) {
        LayoutParams layoutParams;
        if (viewGroup != null) {
            try {
                Class cls = viewGroup.getClass();
                while (!C1507a.classExists(cls.getName() + "$LayoutParams")) {
                    cls = cls.getSuperclass();
                }
                layoutParams = (LayoutParams) Class.forName(cls.getName() + "$LayoutParams").getConstructor(new Class[]{Integer.TYPE, Integer.TYPE}).newInstance(new Object[]{Integer.valueOf(-2), Integer.valueOf(-2)});
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (layoutParams != null) {
                return new LayoutParams(-2, -2);
            }
            return layoutParams;
        }
        layoutParams = null;
        if (layoutParams != null) {
            return layoutParams;
        }
        return new LayoutParams(-2, -2);
    }

    public static void applyBackground(View view, C1511b c1511b) {
        if (view != null) {
            Method method;
            switch (c1511b.type) {
                case COLOR:
                    view.setBackgroundColor(c1511b.getValueColor());
                    return;
                case REF:
                    view.setBackgroundResource(C1507a.getDrawableId(view.getContext(), c1511b.getValueString()));
                    return;
                case BASE64:
                    if (VERSION.SDK_INT >= 16) {
                        try {
                            method = view.getClass().getMethod("setBackground", new Class[]{Drawable.class});
                            if (method != null) {
                                method.invoke(view, new Object[]{c1511b.getValueBitmapDrawable()});
                                return;
                            }
                            return;
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                            return;
                        } catch (IllegalAccessException e2) {
                            e2.printStackTrace();
                            return;
                        } catch (IllegalArgumentException e3) {
                            e3.printStackTrace();
                            return;
                        } catch (InvocationTargetException e4) {
                            e4.printStackTrace();
                            return;
                        }
                    }
                    try {
                        method = view.getClass().getMethod("setBackgroundDrawable", new Class[]{Drawable.class});
                        if (method != null) {
                            method.invoke(view, new Object[]{c1511b.getValueBitmapDrawable()});
                            return;
                        }
                        return;
                    } catch (NoSuchMethodException e5) {
                        return;
                    } catch (IllegalAccessException e6) {
                        return;
                    } catch (IllegalArgumentException e7) {
                        return;
                    } catch (InvocationTargetException e8) {
                        return;
                    }
                case DRAWABLE:
                    if (VERSION.SDK_INT >= 16) {
                        try {
                            method = view.getClass().getMethod("setBackground", new Class[]{Drawable.class});
                            if (method != null) {
                                method.invoke(view, new Object[]{c1511b.getValueBitmapDrawable()});
                                return;
                            }
                            return;
                        } catch (NoSuchMethodException e9) {
                            e9.printStackTrace();
                            return;
                        } catch (IllegalAccessException e22) {
                            e22.printStackTrace();
                            return;
                        } catch (IllegalArgumentException e32) {
                            e32.printStackTrace();
                            return;
                        } catch (InvocationTargetException e42) {
                            e42.printStackTrace();
                            return;
                        }
                    }
                    try {
                        method = view.getClass().getMethod("setBackgroundDrawable", new Class[]{Drawable.class});
                        if (method != null) {
                            method.invoke(view, new Object[]{c1511b.getValueBitmapDrawable()});
                            return;
                        }
                        return;
                    } catch (NoSuchMethodException e10) {
                        return;
                    } catch (IllegalAccessException e11) {
                        return;
                    } catch (IllegalArgumentException e12) {
                        return;
                    } catch (InvocationTargetException e13) {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public static void applyPadding(View view, C1511b c1511b) {
        if (view != null) {
            switch (c1511b.type) {
                case DIMEN:
                    int valueInt = c1511b.getValueInt();
                    view.setPadding(valueInt, valueInt, valueInt, valueInt);
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyPadding(View view, C1511b c1511b, int i) {
        if (view != null) {
            switch (c1511b.type) {
                case DIMEN:
                    int[] iArr = new int[]{view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom()};
                    iArr[i] = c1511b.getValueInt();
                    view.setPadding(iArr[0], iArr[1], iArr[2], iArr[3]);
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyMinWidth(View view, C1511b c1511b) {
        if (view != null && c1511b.type == C1510b.DIMEN) {
            view.setMinimumWidth(c1511b.getValueInt());
        }
    }

    public static void applyMinHeight(View view, C1511b c1511b) {
        if (view != null && c1511b.type == C1510b.DIMEN) {
            view.setMinimumHeight(c1511b.getValueInt());
        }
    }

    public static void applyEnabled(View view, C1511b c1511b) {
        if (view != null) {
            switch (c1511b.type) {
                case BOOLEAN:
                    view.setEnabled(c1511b.getValueBoolean().booleanValue());
                    return;
                default:
                    return;
            }
        }
    }

    public static void applySelected(View view, C1511b c1511b) {
        if (view != null) {
            switch (c1511b.type) {
                case BOOLEAN:
                    view.setSelected(c1511b.getValueBoolean().booleanValue());
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyClickable(View view, C1511b c1511b) {
        if (view != null) {
            switch (c1511b.type) {
                case BOOLEAN:
                    view.setClickable(c1511b.getValueBoolean().booleanValue());
                    return;
                default:
                    return;
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void applyScaleX(View view, C1511b c1511b) {
        if (view != null) {
            switch (c1511b.type) {
                case BOOLEAN:
                    view.setScaleX(c1511b.getValueFloat());
                    return;
                default:
                    return;
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void applyScaleY(View view, C1511b c1511b) {
        if (view != null) {
            switch (c1511b.type) {
                case BOOLEAN:
                    view.setScaleY(c1511b.getValueFloat());
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyText(View view, C1511b c1511b) {
        if (view instanceof TextView) {
            switch (c1511b.type) {
                case STRING:
                    ((TextView) view).setText(c1511b.getValueString());
                    return;
                case REF:
                    ((TextView) view).setText(C1507a.getStringId(view.getContext(), c1511b.getValueString()));
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyTextColor(View view, C1511b c1511b) {
        if (view instanceof TextView) {
            switch (c1511b.type) {
                case COLOR:
                    ((TextView) view).setTextColor(c1511b.getValueColor());
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyTextSize(View view, C1511b c1511b) {
        if (view instanceof TextView) {
            switch (c1511b.type) {
                case DIMEN:
                    ((TextView) view).setTextSize(0, c1511b.getValueFloat());
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyTextStyle(View view, C1511b c1511b) {
        if (view instanceof TextView) {
            switch (c1511b.type) {
                case INTEGER:
                    ((TextView) view).setTypeface(null, c1511b.getValueInt());
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyEllipsize(View view, C1511b c1511b) {
        if (view instanceof TextView) {
            ((TextView) view).setEllipsize(TruncateAt.valueOf(c1511b.getValueString().toUpperCase().trim()));
        }
    }

    public static void applyMaxLines(View view, C1511b c1511b) {
        if (view instanceof TextView) {
            ((TextView) view).setMaxLines(c1511b.getValueInt());
        }
    }

    public static void applyGravity(View view, C1511b c1511b) {
        if (view instanceof TextView) {
            switch (c1511b.type) {
                case INTEGER:
                    ((TextView) view).setGravity(c1511b.getValueInt());
                    return;
                case STRING:
                    ((TextView) view).setGravity(((Integer) c1511b.getValueInt(Gravity.class, c1511b.getValueString().toUpperCase())).intValue());
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyCompoundDrawable(View view, C1511b c1511b, int i) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            switch (c1511b.type) {
                case REF:
                    try {
                        Method method = view.getContext().getResources().getClass().getMethod("getDrawable", new Class[]{integer.class});
                        if (method != null) {
                            method.invoke(view, new Object[]{Integer.valueOf(C1507a.getDrawableId(view.getContext(), c1511b.getValueString()))});
                            break;
                        }
                    } catch (Exception e) {
                        break;
                    }
                    break;
                case BASE64:
                    compoundDrawables[i] = c1511b.getValueBitmapDrawable();
                    break;
                case DRAWABLE:
                    compoundDrawables[i] = c1511b.getValueGradientDrawable();
                    break;
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
        }
    }

    public static void applySrc(View view, C1511b c1511b) {
        if (view instanceof ImageView) {
            switch (c1511b.type) {
                case REF:
                    ((ImageView) view).setImageResource(C1507a.getDrawableId(view.getContext(), c1511b.getValueString()));
                    return;
                case BASE64:
                    ((ImageView) view).setImageBitmap(c1511b.getValueBitmap());
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyScaleType(View view, C1511b c1511b) {
        if (view instanceof ImageView) {
            switch (c1511b.type) {
                case STRING:
                    ((ImageView) view).setScaleType(ScaleType.valueOf(c1511b.getValueString().toUpperCase()));
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyAdjustBounds(View view, C1511b c1511b) {
        if (view instanceof ImageView) {
            switch (c1511b.type) {
                case BOOLEAN:
                    ((ImageView) view).setAdjustViewBounds(c1511b.getValueBoolean().booleanValue());
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyOrientation(View view, C1511b c1511b) {
        int i = 0;
        if (view instanceof LinearLayout) {
            LinearLayout linearLayout;
            switch (c1511b.type) {
                case INTEGER:
                    linearLayout = (LinearLayout) view;
                    if (c1511b.getValueInt() != 0) {
                        i = 1;
                    }
                    linearLayout.setOrientation(i);
                    return;
                case STRING:
                    linearLayout = (LinearLayout) view;
                    if (!c1511b.getValueString().equalsIgnoreCase("HORIZONTAL")) {
                        i = 1;
                    }
                    linearLayout.setOrientation(i);
                    return;
                default:
                    return;
            }
        }
    }

    public static void applyWeightSum(View view, C1511b c1511b) {
        if ((view instanceof LinearLayout) && c1511b.type == C1510b.FLOAT) {
            ((LinearLayout) view).setWeightSum(c1511b.getValueFloat());
        }
    }

    public static void applyTag(View view, C1511b c1511b) {
        view.setTag(c1511b.getValueString());
    }

    public static void applyFunction(View view, C1511b c1511b) {
        if (c1511b.type == C1510b.JSON) {
            try {
                Class[] clsArr;
                Object[] objArr;
                JSONObject valueJSON = c1511b.getValueJSON();
                String string = valueJSON.getString("function");
                JSONArray jSONArray = valueJSON.getJSONArray("args");
                if (jSONArray == null) {
                    clsArr = new Class[0];
                    objArr = new Object[0];
                } else {
                    List arrayList = new ArrayList();
                    List arrayList2 = new ArrayList();
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        String str;
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        boolean has = jSONObject.has("primitive");
                        if (has) {
                            str = "primitive";
                        } else {
                            try {
                                str = C1484a.f2398e;
                            } catch (Exception e) {
                                clsArr = new Class[0];
                                objArr = new Object[0];
                            }
                        }
                        str = jSONObject.getString(str);
                        if (!str.contains(".")) {
                            str = "java.lang." + str;
                        }
                        Class cls = Class.forName(str);
                        if (has) {
                            arrayList.add((Class) cls.getField("TYPE").get(null));
                        } else {
                            arrayList.add(cls);
                        }
                        try {
                            arrayList2.add(C1507a.getFromJSON(jSONObject, FirebaseAnalytics$Param.VALUE, cls));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    clsArr = (Class[]) arrayList.toArray(new Class[arrayList.size()]);
                    objArr = arrayList2.toArray(new Object[arrayList2.size()]);
                }
                try {
                    view.getClass().getMethod(string, clsArr).invoke(view, objArr);
                } catch (SecurityException e3) {
                } catch (NoSuchMethodException e4) {
                    e4.printStackTrace();
                }
            } catch (Exception e22) {
                e22.printStackTrace();
            }
        }
    }

    public static int getDrawableId(Context context, String str) {
        return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
    }

    public static int getStringId(Context context, String str) {
        return context.getResources().getIdentifier(str, SchemaSymbols.ATTVAL_STRING, context.getPackageName());
    }

    public static float dpToPx(float f) {
        return TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public static float spToPx(float f) {
        return TypedValue.applyDimension(2, f, Resources.getSystem().getDisplayMetrics());
    }

    public static float pxToDp(int i) {
        return ((float) i) / Resources.getSystem().getDisplayMetrics().density;
    }

    public static float pxToSp(int i) {
        return ((float) i) / Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    public static float dpToSp(float f) {
        return (float) ((int) (C1507a.dpToPx(f) / Resources.getSystem().getDisplayMetrics().scaledDensity));
    }

    public static int deviceWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static void parseDynamicView(Object obj, View view, HashMap<String, Integer> hashMap) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(C1513d.class)) {
                Object id = ((C1513d) field.getAnnotation(C1513d.class)).id();
                if (id.equalsIgnoreCase("")) {
                    id = field.getName();
                }
                if (hashMap.containsKey(id)) {
                    try {
                        field.set(obj, view.findViewById(((Integer) hashMap.get(id)).intValue()));
                    } catch (IllegalArgumentException e) {
                    } catch (IllegalAccessException e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (field.getName().equalsIgnoreCase("ids") && field.getType() == hashMap.getClass()) {
                try {
                    field.set(obj, hashMap);
                } catch (IllegalArgumentException e3) {
                } catch (IllegalAccessException e22) {
                    e22.printStackTrace();
                }
            }
        }
    }

    private static Object getFromJSON(JSONObject jSONObject, String str, Class<?> cls) throws JSONException {
        if (cls == Integer.class || cls == Integer.TYPE) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (cls == Boolean.class || cls == Boolean.TYPE) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        if (cls == Double.class || cls == Double.TYPE) {
            return (Double) jSONObject.get(str);
        }
        if (cls == Float.class || cls == Float.TYPE) {
            return Float.valueOf(((Float) jSONObject.get(str)).floatValue());
        }
        if (cls == Long.class || cls == Long.TYPE) {
            return Long.valueOf(jSONObject.getLong(str));
        }
        if (cls == String.class) {
            return jSONObject.getString(str);
        }
        if (cls == JSONObject.class) {
            return jSONObject.getJSONObject(str);
        }
        return jSONObject.get(str);
    }

    public static boolean classExists(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
