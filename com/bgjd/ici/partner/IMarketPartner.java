package com.bgjd.ici.partner;

import com.bgjd.ici.p025b.C1408j.C1404b;

public interface IMarketPartner {

    public enum EULA {
        DEFAULT("default"),
        DIALOG(C1404b.ax),
        ACTIVITY(C1404b.aw);
        
        private String value;

        private EULA(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return getValue();
        }
    }

    boolean isBlacklisted();

    boolean isEulaAccepted();

    boolean isEulaShown();

    boolean isMaxDeclineReached();

    void onEulaAccepted();

    void onEulaDeclined();

    void start();
}
