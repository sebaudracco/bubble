package com.fyber.user;

public enum UserEthnicity {
    asian("asian"),
    black("black"),
    hispanic("hispanic"),
    indian("indian"),
    middle_eastern("middle eastern"),
    native_american("native american"),
    pacific_islander("pacific islander"),
    white("white"),
    other("other");
    
    public final String ethnicity;

    private UserEthnicity(String str) {
        this.ethnicity = str;
    }

    public final String toString() {
        return this.ethnicity;
    }
}
