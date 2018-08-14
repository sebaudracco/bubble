package com.appsgeyser.sdk.server.network;

public abstract class OnNetworkStateChangedListener {
    private String id;

    public abstract void networkIsDown();

    public abstract void networkIsUp();

    protected OnNetworkStateChangedListener(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.id.equals(((OnNetworkStateChangedListener) o).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }
}
