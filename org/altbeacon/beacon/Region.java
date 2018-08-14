package org.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Region implements Parcelable, Serializable {
    public static final Creator<Region> CREATOR = new 1();
    private static final Pattern MAC_PATTERN = Pattern.compile("^[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}\\:[0-9A-Fa-f]{2}$");
    private static final String TAG = "Region";
    protected final String mBluetoothAddress;
    protected final List<Identifier> mIdentifiers;
    protected final String mUniqueId;

    public Region(String uniqueId, Identifier id1, Identifier id2, Identifier id3) {
        this.mIdentifiers = new ArrayList(3);
        this.mIdentifiers.add(id1);
        this.mIdentifiers.add(id2);
        this.mIdentifiers.add(id3);
        this.mUniqueId = uniqueId;
        this.mBluetoothAddress = null;
        if (uniqueId == null) {
            throw new NullPointerException("uniqueId may not be null");
        }
    }

    public Region(String uniqueId, List<Identifier> identifiers) {
        this(uniqueId, identifiers, null);
    }

    public Region(String uniqueId, List<Identifier> identifiers, String bluetoothAddress) {
        validateMac(bluetoothAddress);
        this.mIdentifiers = new ArrayList(identifiers);
        this.mUniqueId = uniqueId;
        this.mBluetoothAddress = bluetoothAddress;
        if (uniqueId == null) {
            throw new NullPointerException("uniqueId may not be null");
        }
    }

    public Region(String uniqueId, String bluetoothAddress) {
        validateMac(bluetoothAddress);
        this.mBluetoothAddress = bluetoothAddress;
        this.mUniqueId = uniqueId;
        this.mIdentifiers = new ArrayList();
        if (uniqueId == null) {
            throw new NullPointerException("uniqueId may not be null");
        }
    }

    public Identifier getId1() {
        return getIdentifier(0);
    }

    public Identifier getId2() {
        return getIdentifier(1);
    }

    public Identifier getId3() {
        return getIdentifier(2);
    }

    public Identifier getIdentifier(int i) {
        return this.mIdentifiers.size() > i ? (Identifier) this.mIdentifiers.get(i) : null;
    }

    public String getUniqueId() {
        return this.mUniqueId;
    }

    public String getBluetoothAddress() {
        return this.mBluetoothAddress;
    }

    public boolean matchesBeacon(Beacon beacon) {
        int i = this.mIdentifiers.size();
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            Identifier identifier = (Identifier) this.mIdentifiers.get(i);
            Identifier beaconIdentifier = null;
            if (i < beacon.mIdentifiers.size()) {
                beaconIdentifier = beacon.getIdentifier(i);
            }
            if (beaconIdentifier == null && identifier != null) {
                return false;
            }
            if (beaconIdentifier != null && identifier != null && !identifier.equals(beaconIdentifier)) {
                return false;
            }
        }
        if (this.mBluetoothAddress == null || this.mBluetoothAddress.equalsIgnoreCase(beacon.mBluetoothAddress)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.mUniqueId.hashCode();
    }

    public boolean equals(Object other) {
        if (other instanceof Region) {
            return ((Region) other).mUniqueId.equals(this.mUniqueId);
        }
        return false;
    }

    public boolean hasSameIdentifiers(Region region) {
        if (region.mIdentifiers.size() != this.mIdentifiers.size()) {
            return false;
        }
        int i = 0;
        while (i < region.mIdentifiers.size()) {
            if (region.getIdentifier(i) == null && getIdentifier(i) != null) {
                return false;
            }
            if (region.getIdentifier(i) != null && getIdentifier(i) == null) {
                return false;
            }
            if ((region.getIdentifier(i) != null || getIdentifier(i) != null) && !region.getIdentifier(i).equals(getIdentifier(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Identifier identifier : this.mIdentifiers) {
            if (i > 1) {
                sb.append(" ");
            }
            sb.append("id");
            sb.append(i);
            sb.append(": ");
            sb.append(identifier == null ? "null" : identifier.toString());
            i++;
        }
        return sb.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.mUniqueId);
        out.writeString(this.mBluetoothAddress);
        out.writeInt(this.mIdentifiers.size());
        for (Identifier identifier : this.mIdentifiers) {
            if (identifier != null) {
                out.writeString(identifier.toString());
            } else {
                out.writeString(null);
            }
        }
    }

    protected Region(Parcel in) {
        this.mUniqueId = in.readString();
        this.mBluetoothAddress = in.readString();
        int size = in.readInt();
        this.mIdentifiers = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String identifierString = in.readString();
            if (identifierString == null) {
                this.mIdentifiers.add(null);
            } else {
                this.mIdentifiers.add(Identifier.parse(identifierString));
            }
        }
    }

    private void validateMac(String mac) throws IllegalArgumentException {
        if (mac != null && !MAC_PATTERN.matcher(mac).matches()) {
            throw new IllegalArgumentException("Invalid mac address: '" + mac + "' Must be 6 hex bytes separated by colons.");
        }
    }

    @Deprecated
    public Region clone() {
        return new Region(this.mUniqueId, this.mIdentifiers, this.mBluetoothAddress);
    }
}
