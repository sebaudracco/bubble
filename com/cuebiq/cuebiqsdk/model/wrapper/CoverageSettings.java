package com.cuebiq.cuebiqsdk.model.wrapper;

public class CoverageSettings {
    private String country;
    private Boolean countryOpen;
    private Integer f3507d;
    private Boolean isGDPRCountry;

    public String getCountry() {
        return this.country;
    }

    public Integer getD() {
        return this.f3507d;
    }

    public boolean hasCountryResponse() {
        return this.countryOpen != null;
    }

    public boolean isCountryOpen() {
        return this.countryOpen.booleanValue();
    }

    public boolean isGDPRCountry() {
        return this.isGDPRCountry.booleanValue();
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setCountryOpen(boolean z) {
        this.countryOpen = Boolean.valueOf(z);
    }

    public void setD(Integer num) {
        this.f3507d = num;
    }

    public void setIsGDPRCountry(boolean z) {
        this.isGDPRCountry = Boolean.valueOf(z);
    }

    public String toString() {
        return "CoverageSettings{d=" + this.f3507d + ", isGDPRCountry=" + this.isGDPRCountry + ", country='" + this.country + '\'' + ", countryOpen=" + this.countryOpen + '}';
    }
}
