package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> zzap;

    @KeepForSdk
    public DependencyCycleException(List<Component<?>> list) {
        String str = "Dependency cycle detected: ";
        String valueOf = String.valueOf(Arrays.toString(list.toArray()));
        super(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        this.zzap = list;
    }

    @KeepForSdk
    public List<Component<?>> getComponentsInCycle() {
        return this.zzap;
    }
}
