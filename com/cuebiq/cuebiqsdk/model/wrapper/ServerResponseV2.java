package com.cuebiq.cuebiqsdk.model.wrapper;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.google.gson.JsonSyntaxException;

public class ServerResponseV2 {
    private CoverageSettings cs;
    private Settings gs;

    public static ServerResponseV2 fromJSON(String str) {
        try {
            ServerResponseV2 serverResponseV2 = (ServerResponseV2) CuebiqSDKImpl.GSON.fromJson(str, ServerResponseV2.class);
            return serverResponseV2 != null ? serverResponseV2 : new ServerResponseV2();
        } catch (JsonSyntaxException e) {
            return new ServerResponseV2();
        }
    }

    public CoverageSettings getCs() {
        return this.cs;
    }

    public Settings getGs() {
        return this.gs;
    }

    public boolean hasCoverageSettings() {
        return this.cs != null;
    }

    public boolean hasSettings() {
        return this.gs != null;
    }

    public void setCs(CoverageSettings coverageSettings) {
        this.cs = coverageSettings;
    }

    public void setGs(Settings settings) {
        this.gs = settings;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
