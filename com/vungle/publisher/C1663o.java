package com.vungle.publisher;

import android.os.Bundle;
import javax.inject.Inject;

/* compiled from: vungle */
public class C1663o implements p {
    protected Bundle f3171a = new Bundle();

    @Inject
    public C1663o() {
        m4462d(true);
    }

    public boolean isBackButtonImmediatelyEnabled() {
        return this.f3171a.getBoolean("isBackButtonEnabled");
    }

    public void m4454a(boolean z) {
        this.f3171a.putBoolean("isBackButtonEnabled", z);
    }

    public boolean isImmersiveMode() {
        return this.f3171a.getBoolean("isImmersiveMode", true);
    }

    public void m4458b(boolean z) {
        this.f3171a.putBoolean("isImmersiveMode", z);
    }

    public String getIncentivizedCancelDialogBodyText() {
        return this.f3171a.getString("incentivizedCancelDialogBodyText");
    }

    public void m4452a(String str) {
        this.f3171a.putString("incentivizedCancelDialogBodyText", str);
    }

    public String getIncentivizedCancelDialogCloseButtonText() {
        return this.f3171a.getString("incentivizedCancelDialogNegativeButtonText");
    }

    public void m4457b(String str) {
        this.f3171a.putString("incentivizedCancelDialogNegativeButtonText", str);
    }

    public String getIncentivizedCancelDialogKeepWatchingButtonText() {
        return this.f3171a.getString("incentivizedCancelDialogPositiveButtonText");
    }

    public void m4459c(String str) {
        this.f3171a.putString("incentivizedCancelDialogPositiveButtonText", str);
    }

    public String getIncentivizedCancelDialogTitle() {
        return this.f3171a.getString("incentivizedCancelDialogTitle");
    }

    public void m4461d(String str) {
        this.f3171a.putString("incentivizedCancelDialogTitle", str);
    }

    public void m4463e(String str) {
        this.f3171a.putString("incentivizedUserId", str);
    }

    public String getIncentivizedUserId() {
        return this.f3171a.getString("incentivizedUserId");
    }

    public Orientation getOrientation() {
        return (Orientation) this.f3171a.getParcelable("orientation");
    }

    public void m4451a(Orientation orientation) {
        this.f3171a.putParcelable("orientation", orientation);
    }

    public boolean isSoundEnabled() {
        return this.f3171a.getBoolean("isSoundEnabled");
    }

    public void m4460c(boolean z) {
        this.f3171a.putBoolean("isSoundEnabled", z);
    }

    public boolean isTransitionAnimationEnabled() {
        return this.f3171a.getBoolean("isTransitionAnimationEnabled");
    }

    public void m4462d(boolean z) {
        this.f3171a.putBoolean("isTransitionAnimationEnabled", z);
    }

    public void m4450a(int i) {
        this.f3171a.putInt("flexViewCloseTimerInSecKey", i);
    }

    public int getFlexViewCloseTimeInSec() {
        return this.f3171a.getInt("flexViewCloseTimerInSecKey", 0);
    }

    public void m4456b(int i) {
        this.f3171a.putInt("ordinalViewCount", i);
    }

    public int getOrdinalViewCount() {
        return this.f3171a.getInt("ordinalViewCount", 0);
    }

    public int hashCode() {
        return this.f3171a.hashCode();
    }

    public boolean equals(Object object) {
        return object != null && (object instanceof C1663o) && m4455a((C1663o) object);
    }

    public boolean m4455a(C1663o c1663o) {
        return c1663o != null && c1663o.f3171a.equals(this.f3171a);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(123);
        m4453a(stringBuilder, this.f3171a);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    protected void m4453a(StringBuilder stringBuilder, Bundle bundle) {
        Object obj = 1;
        for (String str : bundle.keySet()) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(", ");
            }
            stringBuilder.append(str).append(" = ").append(bundle.get(str));
        }
    }
}
