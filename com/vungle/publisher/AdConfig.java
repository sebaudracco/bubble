package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdConfig implements p {
    private final C1663o f2677a = new C1663o();

    @Inject
    public AdConfig() {
        setTransitionAnimationEnabled(true);
    }

    public C1663o getDelegateAdConfig() {
        return this.f2677a;
    }

    public boolean isBackButtonImmediatelyEnabled() {
        return this.f2677a.isBackButtonImmediatelyEnabled();
    }

    public void setBackButtonImmediatelyEnabled(boolean isBackButtonImmediatelyEnabled) {
        this.f2677a.m4454a(isBackButtonImmediatelyEnabled);
    }

    public boolean isImmersiveMode() {
        return this.f2677a.isImmersiveMode();
    }

    public void setImmersiveMode(boolean isImmersiveMode) {
        this.f2677a.m4458b(isImmersiveMode);
    }

    public String getIncentivizedCancelDialogBodyText() {
        return this.f2677a.getIncentivizedCancelDialogBodyText();
    }

    public void setIncentivizedCancelDialogBodyText(String bodyText) {
        this.f2677a.m4452a(bodyText);
    }

    public String getIncentivizedCancelDialogCloseButtonText() {
        return this.f2677a.getIncentivizedCancelDialogCloseButtonText();
    }

    public void setIncentivizedCancelDialogCloseButtonText(String closeButtonText) {
        this.f2677a.m4457b(closeButtonText);
    }

    public String getIncentivizedCancelDialogKeepWatchingButtonText() {
        return this.f2677a.getIncentivizedCancelDialogKeepWatchingButtonText();
    }

    public void setIncentivizedCancelDialogKeepWatchingButtonText(String keepWatchingButtonText) {
        this.f2677a.m4459c(keepWatchingButtonText);
    }

    public String getIncentivizedCancelDialogTitle() {
        return this.f2677a.getIncentivizedCancelDialogTitle();
    }

    public void setIncentivizedCancelDialogTitle(String title) {
        this.f2677a.m4461d(title);
    }

    public String getIncentivizedUserId() {
        return this.f2677a.getIncentivizedUserId();
    }

    public void setIncentivizedUserId(String incentivizedUserId) {
        this.f2677a.m4463e(incentivizedUserId);
    }

    public Orientation getOrientation() {
        return this.f2677a.getOrientation();
    }

    public void setOrientation(Orientation orientation) {
        this.f2677a.m4451a(orientation);
    }

    public boolean isSoundEnabled() {
        return this.f2677a.isSoundEnabled();
    }

    public void setSoundEnabled(boolean isSoundEnabled) {
        this.f2677a.m4460c(isSoundEnabled);
    }

    public boolean isTransitionAnimationEnabled() {
        return this.f2677a.isTransitionAnimationEnabled();
    }

    public void setTransitionAnimationEnabled(boolean isTransitionAnimationEnabled) {
        this.f2677a.m4462d(isTransitionAnimationEnabled);
    }

    public void setFlexViewCloseTimeInSec(int flexViewCloseTimeInSec) {
        this.f2677a.m4450a(flexViewCloseTimeInSec);
    }

    public int getFlexViewCloseTimeInSec() {
        return this.f2677a.getFlexViewCloseTimeInSec();
    }

    public void setOrdinalViewCount(int ordinalViewCount) {
        this.f2677a.m4456b(ordinalViewCount);
    }

    public int getOrdinalViewCount() {
        return this.f2677a.getOrdinalViewCount();
    }

    public int hashCode() {
        return this.f2677a.hashCode();
    }

    public boolean equals(Object obj) {
        return this.f2677a.equals(obj);
    }

    public String toString() {
        return this.f2677a.toString();
    }
}
