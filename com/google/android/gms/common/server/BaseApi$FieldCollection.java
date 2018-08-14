package com.google.android.gms.common.server;

public class BaseApi$FieldCollection<Parent> {
    private final BaseApi$BaseApiaryOptions$Collector zzvw;
    private final Parent zzwc;

    protected BaseApi$FieldCollection(Parent parent, BaseApi$BaseApiaryOptions$Collector baseApi$BaseApiaryOptions$Collector) {
        Object obj;
        if (parent == null) {
            obj = this;
        }
        this.zzwc = obj;
        this.zzvw = baseApi$BaseApiaryOptions$Collector;
    }

    protected BaseApi$BaseApiaryOptions$Collector getCollector() {
        return this.zzvw;
    }

    protected Parent getParent() {
        return this.zzwc;
    }
}
