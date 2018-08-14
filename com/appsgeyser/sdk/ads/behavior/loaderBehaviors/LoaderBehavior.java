package com.appsgeyser.sdk.ads.behavior.loaderBehaviors;

import com.appsgeyser.sdk.ads.AdsLoader;
import com.appsgeyser.sdk.ads.behavior.BehaviorAcceptor;
import com.appsgeyser.sdk.ads.behavior.BehaviorVisitor;

public abstract class LoaderBehavior implements BehaviorVisitor {
    abstract void visit(AdsLoader adsLoader);

    public void visit(BehaviorAcceptor loader) {
        if (loader instanceof AdsLoader) {
            visit((AdsLoader) loader);
        }
    }
}
