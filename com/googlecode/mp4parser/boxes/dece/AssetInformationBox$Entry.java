package com.googlecode.mp4parser.boxes.dece;

import com.coremedia.iso.Utf8;

public class AssetInformationBox$Entry {
    public String assetId;
    public String namespace;
    public String profileLevelIdc;

    public AssetInformationBox$Entry(String namespace, String profileLevelIdc, String assetId) {
        this.namespace = namespace;
        this.profileLevelIdc = profileLevelIdc;
        this.assetId = assetId;
    }

    public String toString() {
        return "{namespace='" + this.namespace + '\'' + ", profileLevelIdc='" + this.profileLevelIdc + '\'' + ", assetId='" + this.assetId + '\'' + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssetInformationBox$Entry entry = (AssetInformationBox$Entry) o;
        if (!this.assetId.equals(entry.assetId)) {
            return false;
        }
        if (!this.namespace.equals(entry.namespace)) {
            return false;
        }
        if (this.profileLevelIdc.equals(entry.profileLevelIdc)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.namespace.hashCode() * 31) + this.profileLevelIdc.hashCode()) * 31) + this.assetId.hashCode();
    }

    public int getSize() {
        return ((Utf8.utf8StringLengthInBytes(this.namespace) + 3) + Utf8.utf8StringLengthInBytes(this.profileLevelIdc)) + Utf8.utf8StringLengthInBytes(this.assetId);
    }
}
