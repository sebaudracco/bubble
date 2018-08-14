package com.fyber.user;

public enum UserConnection {
    wifi("wifi"),
    three_g("3g");
    
    public final String connection;

    private UserConnection(String str) {
        this.connection = str;
    }

    public final String toString() {
        return this.connection;
    }
}
