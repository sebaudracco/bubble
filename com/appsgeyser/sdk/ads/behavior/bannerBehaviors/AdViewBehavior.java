package com.appsgeyser.sdk.ads.behavior.bannerBehaviors;

import com.appsgeyser.sdk.ads.AdView;
import com.appsgeyser.sdk.ads.behavior.BehaviorAcceptor;
import com.appsgeyser.sdk.ads.behavior.BehaviorVisitor;

public abstract class AdViewBehavior implements BehaviorVisitor {
    abstract void visit(AdView adView);

    public void visit(BehaviorAcceptor acceptor) {
        if (acceptor instanceof AdView) {
            visit((AdView) acceptor);
        }
    }
}
