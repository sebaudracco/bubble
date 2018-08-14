package com.appnext.core;

import java.io.Serializable;

public class C0908i implements Serializable {
    private static final long serialVersionUID = 8086013010302241826L;
    private int adID = -1;
    private String adJSON = "";
    private String placementID = "";

    protected int getAdID() {
        return this.adID;
    }

    protected void setAdID(int i) {
        this.adID = i;
    }

    protected String getAdJSON() {
        return this.adJSON;
    }

    protected void setAdJSON(String str) {
        this.adJSON = str;
    }

    protected String getPlacementID() {
        return this.placementID;
    }

    protected void setPlacementID(String str) {
        this.placementID = str;
    }
}
