package com.cuebiq.cuebiqsdk.model.wrapper;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;

public class Event {
    private String eInfo1;
    private String eInfo2;
    private String eInfo3;
    private String eInfo4;
    private String name;

    public final class Builder {
        private String eInfo1;
        private String eInfo2;
        private String eInfo3;
        private String eInfo4;
        private String name;

        public final Event build() {
            Event event = new Event();
            event.eInfo1 = this.eInfo1;
            event.eInfo2 = this.eInfo2;
            event.eInfo4 = this.eInfo4;
            event.name = this.name;
            event.eInfo3 = this.eInfo3;
            return event;
        }

        public final Builder setEventInfo1(String str) {
            this.eInfo1 = str;
            return this;
        }

        public final Builder setEventInfo2(String str) {
            this.eInfo2 = str;
            return this;
        }

        public final Builder setEventInfo3(String str) {
            this.eInfo3 = str;
            return this;
        }

        public final Builder setEventInfo4(String str) {
            this.eInfo4 = str;
            return this;
        }

        public final Builder setName(String str) {
            this.name = str;
            return this;
        }
    }

    public String getName() {
        return this.name;
    }

    public String geteInfo1() {
        return this.eInfo1;
    }

    public String geteInfo2() {
        return this.eInfo2;
    }

    public String geteInfo3() {
        return this.eInfo3;
    }

    public String geteInfo4() {
        return this.eInfo4;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void seteInfo1(String str) {
        this.eInfo1 = str;
    }

    public void seteInfo2(String str) {
        this.eInfo2 = str;
    }

    public void seteInfo3(String str) {
        this.eInfo3 = str;
    }

    public void seteInfo4(String str) {
        this.eInfo4 = str;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
