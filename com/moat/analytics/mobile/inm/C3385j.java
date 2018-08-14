package com.moat.analytics.mobile.inm;

import com.moat.analytics.mobile.inm.base.exception.C3376a;

class C3385j implements Runnable {
    final /* synthetic */ C3373i f8597a;

    C3385j(C3373i c3373i) {
        this.f8597a = c3373i;
    }

    public void run() {
        try {
            if (this.f8597a.f.get() == null || this.f8597a.m11498e()) {
                this.f8597a.m11496c();
            } else if (Boolean.valueOf(this.f8597a.m11504i()).booleanValue()) {
                this.f8597a.d.postDelayed(this, 200);
            } else {
                this.f8597a.m11496c();
            }
        } catch (Exception e) {
            this.f8597a.m11496c();
            C3376a.m11557a(e);
        }
    }
}
