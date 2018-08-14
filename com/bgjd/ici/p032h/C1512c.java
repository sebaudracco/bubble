package com.bgjd.ici.p032h;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.wBubble_7453037.R;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class C1512c {
    static int INTERNAL_TAG_ID = R.animator.design_appbar_state_list_animator;
    static int mCurrentId = 13;

    public static View createView(Context context, JSONObject jSONObject, Class<?> cls) {
        return C1512c.createView(context, jSONObject, null, cls);
    }

    public static View createView(Context context, JSONObject jSONObject, ViewGroup viewGroup, Class<?> cls) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        View createViewInternal = C1512c.createViewInternal(context, jSONObject, viewGroup, hashMap);
        if (createViewInternal == null) {
            return null;
        }
        if (createViewInternal.getTag(INTERNAL_TAG_ID) != null) {
            C1507a.applyLayoutProperties(createViewInternal, (List) createViewInternal.getTag(INTERNAL_TAG_ID), viewGroup, hashMap);
        }
        createViewInternal.setTag(INTERNAL_TAG_ID, null);
        if (cls != null) {
            try {
                Object newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                C1507a.parseDynamicView(newInstance, createViewInternal, hashMap);
                createViewInternal.setTag(newInstance);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
        return createViewInternal;
    }

    public static View createView(Context context, JSONObject jSONObject, ViewGroup viewGroup) {
        return C1512c.createView(context, jSONObject, viewGroup, null);
    }

    public static View createView(Context context, JSONObject jSONObject) {
        return C1512c.createView(context, jSONObject, null, null);
    }

    private static View createViewInternal(Context context, JSONObject jSONObject, ViewGroup viewGroup, HashMap<String, Integer> hashMap) {
        View view;
        try {
            String string = jSONObject.getString("widget");
            if (!string.contains(".")) {
                string = "android.widget." + string;
            }
            view = (View) Class.forName(string).getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
        } catch (JSONException e) {
            e.printStackTrace();
            view = null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            view = null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            view = null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            view = null;
        } catch (InstantiationException e5) {
            e5.printStackTrace();
            view = null;
        } catch (IllegalAccessException e6) {
            e6.printStackTrace();
            view = null;
        }
        if (view == null) {
            return null;
        }
        try {
            view.setLayoutParams(C1507a.createLayoutParams(viewGroup));
            List arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("properties");
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    C1511b c1511b = new C1511b(jSONArray.getJSONObject(i));
                    if (c1511b.isValid()) {
                        arrayList.add(c1511b);
                    }
                }
            }
            view.setTag(INTERNAL_TAG_ID, arrayList);
            CharSequence applyStyleProperties = C1507a.applyStyleProperties(view, arrayList);
            if (!TextUtils.isEmpty(applyStyleProperties)) {
                hashMap.put(applyStyleProperties, Integer.valueOf(mCurrentId));
                view.setId(mCurrentId);
                mCurrentId++;
            }
            if (!(view instanceof ViewGroup)) {
                return view;
            }
            ViewGroup viewGroup2 = (ViewGroup) view;
            if (!jSONObject.has("views")) {
                return view;
            }
            List<View> arrayList2 = new ArrayList();
            JSONArray jSONArray2 = jSONObject.getJSONArray("views");
            if (jSONArray2 != null) {
                int length = jSONArray2.length();
                for (int i2 = 0; i2 < length; i2++) {
                    View createViewInternal = C1512c.createViewInternal(context, jSONArray2.getJSONObject(i2), viewGroup, hashMap);
                    if (createViewInternal != null) {
                        arrayList2.add(createViewInternal);
                        viewGroup2.addView(createViewInternal);
                    }
                }
            }
            for (View view2 : arrayList2) {
                C1507a.applyLayoutProperties(view2, (List) view2.getTag(INTERNAL_TAG_ID), viewGroup2, hashMap);
                view2.setTag(INTERNAL_TAG_ID, null);
            }
            return view;
        } catch (JSONException e7) {
            e7.printStackTrace();
            return view;
        }
    }
}
