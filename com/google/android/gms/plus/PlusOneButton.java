package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ViewUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.plus.internal.zzm;

@Deprecated
public final class PlusOneButton extends FrameLayout {
    @Deprecated
    public static final int ANNOTATION_BUBBLE = 1;
    @Deprecated
    public static final int ANNOTATION_INLINE = 2;
    @Deprecated
    public static final int ANNOTATION_NONE = 0;
    @Deprecated
    public static final int DEFAULT_ACTIVITY_REQUEST_CODE = -1;
    @Deprecated
    public static final int SIZE_MEDIUM = 1;
    @Deprecated
    public static final int SIZE_SMALL = 0;
    @Deprecated
    public static final int SIZE_STANDARD = 3;
    @Deprecated
    public static final int SIZE_TALL = 2;
    private int mSize;
    private View zzi;
    private int zzj;
    private String zzk;
    private int zzl;
    private OnPlusOneClickListener zzm;

    @Deprecated
    public interface OnPlusOneClickListener {
        @Deprecated
        void onPlusOneClick(Intent intent);
    }

    @Deprecated
    protected class DefaultOnPlusOneClickListener implements OnClickListener, OnPlusOneClickListener {
        private final OnPlusOneClickListener zzn;
        private final /* synthetic */ PlusOneButton zzo;

        @Deprecated
        public DefaultOnPlusOneClickListener(PlusOneButton plusOneButton, OnPlusOneClickListener onPlusOneClickListener) {
            this.zzo = plusOneButton;
            this.zzn = onPlusOneClickListener;
        }

        @Deprecated
        public void onClick(View view) {
            Intent intent = (Intent) this.zzo.zzi.getTag();
            if (this.zzn != null) {
                this.zzn.onPlusOneClick(intent);
            } else {
                onPlusOneClick(intent);
            }
        }

        @Deprecated
        public void onPlusOneClick(Intent intent) {
            Context context = this.zzo.getContext();
            if ((context instanceof Activity) && intent != null) {
                ((Activity) context).startActivityForResult(intent, this.zzo.zzl);
            }
        }
    }

    @Deprecated
    public PlusOneButton(Context context) {
        this(context, null);
    }

    @Deprecated
    public PlusOneButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSize = getSize(context, attributeSet);
        this.zzj = getAnnotation(context, attributeSet);
        this.zzl = -1;
        zza(getContext());
        if (!isInEditMode()) {
        }
    }

    @Deprecated
    protected static int getAnnotation(Context context, AttributeSet attributeSet) {
        String xmlAttributeString = ViewUtils.getXmlAttributeString("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", context, attributeSet, true, false, "PlusOneButton");
        return "INLINE".equalsIgnoreCase(xmlAttributeString) ? 2 : !"NONE".equalsIgnoreCase(xmlAttributeString) ? 1 : 0;
    }

    @Deprecated
    protected static int getSize(Context context, AttributeSet attributeSet) {
        String xmlAttributeString = ViewUtils.getXmlAttributeString("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", context, attributeSet, true, false, "PlusOneButton");
        return "SMALL".equalsIgnoreCase(xmlAttributeString) ? 0 : "MEDIUM".equalsIgnoreCase(xmlAttributeString) ? 1 : "TALL".equalsIgnoreCase(xmlAttributeString) ? 2 : 3;
    }

    private final void zza(Context context) {
        if (this.zzi != null) {
            removeView(this.zzi);
        }
        this.zzi = zzm.zza(context, this.mSize, this.zzj, this.zzk, this.zzl);
        setOnPlusOneClickListener(this.zzm);
        addView(this.zzi);
    }

    @Deprecated
    public final void initialize(String str, int i) {
        Preconditions.checkState(getContext() instanceof Activity, "To use this method, the PlusOneButton must be placed in an Activity. Use initialize(String, OnPlusOneClickListener).");
        this.zzk = str;
        this.zzl = i;
        zza(getContext());
    }

    @Deprecated
    public final void initialize(String str, OnPlusOneClickListener onPlusOneClickListener) {
        this.zzk = str;
        this.zzl = 0;
        zza(getContext());
        setOnPlusOneClickListener(onPlusOneClickListener);
    }

    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.zzi.layout(0, 0, i3 - i, i4 - i2);
    }

    protected final void onMeasure(int i, int i2) {
        View view = this.zzi;
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    @Deprecated
    @VisibleForTesting
    public final void plusOneClick() {
        this.zzi.performClick();
    }

    @Deprecated
    public final void setAnnotation(int i) {
        this.zzj = i;
        zza(getContext());
    }

    @Deprecated
    @VisibleForTesting
    public final void setIntent(Intent intent) {
        this.zzi.setTag(intent);
    }

    @Deprecated
    public final void setOnPlusOneClickListener(OnPlusOneClickListener onPlusOneClickListener) {
        this.zzm = onPlusOneClickListener;
        this.zzi.setOnClickListener(new DefaultOnPlusOneClickListener(this, onPlusOneClickListener));
    }

    @Deprecated
    public final void setSize(int i) {
        this.mSize = i;
        zza(getContext());
    }
}
