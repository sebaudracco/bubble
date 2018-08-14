package com.integralads.avid.library.inmobi.p124d;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.integralads.avid.library.inmobi.p126f.C3315d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: AvidViewProcessor */
public class C3301d implements C3297e {
    public JSONObject mo6322a(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return C3315d.m11294a(iArr[0], iArr[1], width, height);
    }

    public List<View> mo6324b(View view) {
        return view instanceof ViewGroup ? m11230a((ViewGroup) view) : new ArrayList();
    }

    public C3297e mo6325c(View view) {
        return this;
    }

    public void mo6323a(View view, JSONObject jSONObject) {
    }

    private List<View> m11230a(ViewGroup viewGroup) {
        List arrayList = new ArrayList();
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (m11235d(childAt)) {
                int a = m11229a(childAt, arrayList);
                if (a < 0) {
                    arrayList.add(childAt);
                } else {
                    arrayList.add(a, childAt);
                }
            }
        }
        return arrayList;
    }

    private int m11229a(View view, List<View> list) {
        if (VERSION.SDK_INT < 21) {
            return -1;
        }
        int i;
        float z = view.getZ();
        int size = list.size();
        for (int i2 = size - 1; i2 >= 0; i2--) {
            if (z >= ((View) list.get(i2)).getZ()) {
                i = i2 + 1;
                break;
            }
        }
        i = 0;
        if (i == size) {
            i = -1;
        }
        return i;
    }

    boolean m11235d(View view) {
        if (view.getVisibility() != 0) {
            return false;
        }
        if (VERSION.SDK_INT < 11 || ((double) view.getAlpha()) > 0.0d) {
            return true;
        }
        return false;
    }
}
