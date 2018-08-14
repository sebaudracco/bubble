package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bgjd.ici.p030e.C1485h.C1484a;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.p033n.C2029i;
import com.facebook.ads.internal.p070r.C2154a;
import com.silvermob.sdk.Const.BannerType;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class aa extends C1884b {
    private final ab f4166c;
    private C2029i f4167d;
    private boolean f4168e;
    private boolean f4169f;
    private boolean f4170g;
    private View f4171h;
    private List<View> f4172i;

    public aa(Context context, C1885c c1885c, C2154a c2154a, ab abVar) {
        super(context, c1885c, c2154a);
        this.f4166c = abVar;
    }

    private String m5672b(View view) {
        try {
            return m5673c(view).toString();
        } catch (JSONException e) {
            return "Json exception";
        }
    }

    private JSONObject m5673c(View view) {
        boolean z = true;
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("id", Integer.valueOf(view.getId()));
        jSONObject.putOpt(C1484a.f2398e, view.getClass());
        jSONObject.putOpt("origin", String.format("{x:%d, y:%d}", new Object[]{Integer.valueOf(view.getTop()), Integer.valueOf(view.getLeft())}));
        jSONObject.putOpt("size", String.format("{h:%d, w:%d}", new Object[]{Integer.valueOf(view.getHeight()), Integer.valueOf(view.getWidth())}));
        if (this.f4172i == null || !this.f4172i.contains(view)) {
            z = false;
        }
        jSONObject.putOpt("clickable", Boolean.valueOf(z));
        Object obj = "unknown";
        if (view instanceof Button) {
            obj = "button";
        } else if (view instanceof TextView) {
            obj = "text";
        } else if (view instanceof ImageView) {
            obj = BannerType.IMAGE;
        } else if (view instanceof MediaView) {
            obj = "mediaview";
        } else if (view instanceof ViewGroup) {
            obj = "viewgroup";
        }
        jSONObject.putOpt("type", obj);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            JSONArray jSONArray = new JSONArray();
            while (i < viewGroup.getChildCount()) {
                jSONArray.put(m5673c(viewGroup.getChildAt(i)));
                i++;
            }
            jSONObject.putOpt(SchemaSymbols.ATTVAL_LIST, jSONArray);
        }
        return jSONObject;
    }

    private String m5674d(View view) {
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            return "";
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
            createBitmap.setDensity(view.getResources().getDisplayMetrics().densityDpi);
            view.draw(new Canvas(createBitmap));
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(CompressFormat.JPEG, this.f4166c.mo3649i(), byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception e) {
            return "";
        }
    }

    public void m5675a(View view) {
        this.f4171h = view;
    }

    public void m5676a(C2029i c2029i) {
        this.f4167d = c2029i;
    }

    public void m5677a(List<View> list) {
        this.f4172i = list;
    }

    protected void mo3624a(Map<String, String> map) {
        if (this.f4166c != null) {
            if (this.a != null) {
                map.put("mil", String.valueOf(this.a.mo3726c()));
                map.put("eil", String.valueOf(this.a.mo3728d()));
                map.put("eil_source", this.a.mo3729e());
            }
            if (this.f4167d != null) {
                map.put("nti", String.valueOf(this.f4167d.m6501c()));
            }
            if (this.f4168e) {
                map.put("nhs", Boolean.TRUE.toString());
            }
            if (this.f4169f) {
                map.put("nmv", Boolean.TRUE.toString());
            }
            if (this.f4170g) {
                map.put("nmvap", Boolean.TRUE.toString());
            }
            if (this.f4171h != null && this.f4166c.mo3646f()) {
                map.put("view", m5672b(this.f4171h));
            }
            if (this.f4171h != null && this.f4166c.mo3645e()) {
                map.put("snapshot", m5674d(this.f4171h));
            }
            this.f4166c.mo3639a((Map) map);
        }
    }

    public void m5679a(boolean z) {
        this.f4168e = z;
    }

    public void m5680b(boolean z) {
        this.f4169f = z;
    }

    public void m5681c(boolean z) {
        this.f4170g = z;
    }
}
