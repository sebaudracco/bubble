package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.lang.ref.WeakReference;

public class NativeV2RecyclerViewAdapter extends Adapter<C2962a> implements aq {
    private static final String f6941a = NativeV2RecyclerViewAdapter.class.getSimpleName();
    private final NativeV2DataModel f6942b;
    private ae f6943c;
    private SparseArray<WeakReference<View>> f6944d;
    private boolean f6945e = false;

    public class C2962a extends ViewHolder {
        final /* synthetic */ NativeV2RecyclerViewAdapter f6939a;
        private ViewGroup f6940b;

        public C2962a(NativeV2RecyclerViewAdapter nativeV2RecyclerViewAdapter, View view) {
            this.f6939a = nativeV2RecyclerViewAdapter;
            super(view);
            this.f6940b = (ViewGroup) view;
        }
    }

    NativeV2RecyclerViewAdapter(@NonNull NativeV2DataModel nativeV2DataModel, @NonNull ae aeVar) {
        this.f6942b = nativeV2DataModel;
        this.f6943c = aeVar;
        this.f6944d = new SparseArray();
    }

    public C2962a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new C2962a(this, new FrameLayout(this.f6943c.m9269a()));
    }

    public void onBindViewHolder(C2962a c2962a, int i) {
        View view;
        ak b = this.f6942b.m9080b(i);
        WeakReference weakReference = (WeakReference) this.f6944d.get(i);
        if (weakReference != null) {
            view = (View) weakReference.get();
            if (view == null) {
                view = buildScrollableView(i, c2962a.f6940b, b);
            }
        } else {
            view = buildScrollableView(i, c2962a.f6940b, b);
        }
        if (view != null) {
            if (i != getItemCount() - 1) {
                c2962a.f6940b.setPadding(0, 0, 16, 0);
            }
            c2962a.f6940b.addView(view);
            this.f6944d.put(i, new WeakReference(view));
        }
    }

    public void onViewRecycled(C2962a c2962a) {
        c2962a.f6940b.removeAllViews();
        super.onViewRecycled(c2962a);
    }

    public ViewGroup buildScrollableView(int i, @NonNull ViewGroup viewGroup, @NonNull ak akVar) {
        ViewGroup a = this.f6943c.m9271a(viewGroup, akVar);
        this.f6943c.m9270a(a, viewGroup, akVar);
        a.setLayoutParams(NativeStrandViewFactory.m8952a((NativeV2Asset) akVar, viewGroup));
        return a;
    }

    public int getItemCount() {
        return this.f6942b.m9090j();
    }

    public void destroy() {
        this.f6945e = true;
    }
}
