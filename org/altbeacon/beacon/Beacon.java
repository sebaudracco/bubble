package org.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.altbeacon.beacon.client.BeaconDataFactory;
import org.altbeacon.beacon.client.NullBeaconDataFactory;
import org.altbeacon.beacon.distance.DistanceCalculator;
import org.altbeacon.beacon.logging.LogManager;

public class Beacon implements Parcelable, Serializable {
    @Deprecated
    public static final Creator<Beacon> CREATOR = new 1();
    private static final String TAG = "Beacon";
    private static final List<Identifier> UNMODIFIABLE_LIST_OF_IDENTIFIER = Collections.unmodifiableList(new ArrayList());
    private static final List<Long> UNMODIFIABLE_LIST_OF_LONG = Collections.unmodifiableList(new ArrayList());
    protected static BeaconDataFactory beaconDataFactory = new NullBeaconDataFactory();
    protected static DistanceCalculator sDistanceCalculator = null;
    protected static boolean sHardwareEqualityEnforced = false;
    protected int mBeaconTypeCode;
    protected String mBluetoothAddress;
    protected String mBluetoothName;
    protected List<Long> mDataFields;
    protected Double mDistance;
    protected List<Long> mExtraDataFields;
    protected List<Identifier> mIdentifiers;
    protected int mManufacturer;
    protected boolean mMultiFrameBeacon;
    private int mPacketCount;
    protected String mParserIdentifier;
    protected int mRssi;
    private int mRssiMeasurementCount;
    private Double mRunningAverageRssi;
    protected int mServiceUuid;
    protected int mTxPower;

    public static void setDistanceCalculator(DistanceCalculator dc) {
        sDistanceCalculator = dc;
    }

    public static DistanceCalculator getDistanceCalculator() {
        return sDistanceCalculator;
    }

    public static void setHardwareEqualityEnforced(boolean e) {
        sHardwareEqualityEnforced = e;
    }

    public static boolean getHardwareEqualityEnforced() {
        return sHardwareEqualityEnforced;
    }

    @Deprecated
    protected Beacon(Parcel in) {
        int i;
        boolean z = false;
        this.mRssiMeasurementCount = 0;
        this.mPacketCount = 0;
        this.mRunningAverageRssi = null;
        this.mServiceUuid = -1;
        this.mMultiFrameBeacon = false;
        int size = in.readInt();
        this.mIdentifiers = new ArrayList(size);
        for (i = 0; i < size; i++) {
            this.mIdentifiers.add(Identifier.parse(in.readString()));
        }
        this.mDistance = Double.valueOf(in.readDouble());
        this.mRssi = in.readInt();
        this.mTxPower = in.readInt();
        this.mBluetoothAddress = in.readString();
        this.mBeaconTypeCode = in.readInt();
        this.mServiceUuid = in.readInt();
        int dataSize = in.readInt();
        this.mDataFields = new ArrayList(dataSize);
        for (i = 0; i < dataSize; i++) {
            this.mDataFields.add(Long.valueOf(in.readLong()));
        }
        int extraDataSize = in.readInt();
        this.mExtraDataFields = new ArrayList(extraDataSize);
        for (i = 0; i < extraDataSize; i++) {
            this.mExtraDataFields.add(Long.valueOf(in.readLong()));
        }
        this.mManufacturer = in.readInt();
        this.mBluetoothName = in.readString();
        this.mParserIdentifier = in.readString();
        if (in.readByte() != (byte) 0) {
            z = true;
        }
        this.mMultiFrameBeacon = z;
        this.mRunningAverageRssi = (Double) in.readValue(null);
        this.mRssiMeasurementCount = in.readInt();
        this.mPacketCount = in.readInt();
    }

    protected Beacon(Beacon otherBeacon) {
        this.mRssiMeasurementCount = 0;
        this.mPacketCount = 0;
        this.mRunningAverageRssi = null;
        this.mServiceUuid = -1;
        this.mMultiFrameBeacon = false;
        this.mIdentifiers = new ArrayList(otherBeacon.mIdentifiers);
        this.mDataFields = new ArrayList(otherBeacon.mDataFields);
        this.mExtraDataFields = new ArrayList(otherBeacon.mExtraDataFields);
        this.mDistance = otherBeacon.mDistance;
        this.mRunningAverageRssi = otherBeacon.mRunningAverageRssi;
        this.mPacketCount = otherBeacon.mPacketCount;
        this.mRssiMeasurementCount = otherBeacon.mRssiMeasurementCount;
        this.mRssi = otherBeacon.mRssi;
        this.mTxPower = otherBeacon.mTxPower;
        this.mBluetoothAddress = otherBeacon.mBluetoothAddress;
        this.mBeaconTypeCode = otherBeacon.getBeaconTypeCode();
        this.mServiceUuid = otherBeacon.getServiceUuid();
        this.mBluetoothName = otherBeacon.mBluetoothName;
        this.mParserIdentifier = otherBeacon.mParserIdentifier;
        this.mMultiFrameBeacon = otherBeacon.mMultiFrameBeacon;
        this.mManufacturer = otherBeacon.mManufacturer;
    }

    protected Beacon() {
        this.mRssiMeasurementCount = 0;
        this.mPacketCount = 0;
        this.mRunningAverageRssi = null;
        this.mServiceUuid = -1;
        this.mMultiFrameBeacon = false;
        this.mIdentifiers = new ArrayList(1);
        this.mDataFields = new ArrayList(1);
        this.mExtraDataFields = new ArrayList(1);
    }

    public void setRssiMeasurementCount(int rssiMeasurementCount) {
        this.mRssiMeasurementCount = rssiMeasurementCount;
    }

    public int getPacketCount() {
        return this.mPacketCount;
    }

    public void setPacketCount(int packetCount) {
        this.mPacketCount = packetCount;
    }

    public int getMeasurementCount() {
        return this.mRssiMeasurementCount;
    }

    public void setRunningAverageRssi(double rssi) {
        this.mRunningAverageRssi = Double.valueOf(rssi);
        this.mDistance = null;
    }

    @Deprecated
    public double getRunningAverageRssi(double rssi) {
        Double valueOf = Double.valueOf(rssi);
        this.mRunningAverageRssi = valueOf;
        return valueOf.doubleValue();
    }

    public double getRunningAverageRssi() {
        if (this.mRunningAverageRssi != null) {
            return this.mRunningAverageRssi.doubleValue();
        }
        return (double) this.mRssi;
    }

    public void setRssi(int rssi) {
        this.mRssi = rssi;
    }

    public int getManufacturer() {
        return this.mManufacturer;
    }

    public int getServiceUuid() {
        return this.mServiceUuid;
    }

    public Identifier getIdentifier(int i) {
        return (Identifier) this.mIdentifiers.get(i);
    }

    public Identifier getId1() {
        return (Identifier) this.mIdentifiers.get(0);
    }

    public Identifier getId2() {
        return (Identifier) this.mIdentifiers.get(1);
    }

    public Identifier getId3() {
        return (Identifier) this.mIdentifiers.get(2);
    }

    public List<Long> getDataFields() {
        if (this.mDataFields.getClass().isInstance(UNMODIFIABLE_LIST_OF_LONG)) {
            return this.mDataFields;
        }
        return Collections.unmodifiableList(this.mDataFields);
    }

    public List<Long> getExtraDataFields() {
        if (this.mExtraDataFields.getClass().isInstance(UNMODIFIABLE_LIST_OF_LONG)) {
            return this.mExtraDataFields;
        }
        return Collections.unmodifiableList(this.mExtraDataFields);
    }

    public void setExtraDataFields(List<Long> fields) {
        this.mExtraDataFields = fields;
    }

    public List<Identifier> getIdentifiers() {
        if (this.mIdentifiers.getClass().isInstance(UNMODIFIABLE_LIST_OF_IDENTIFIER)) {
            return this.mIdentifiers;
        }
        return Collections.unmodifiableList(this.mIdentifiers);
    }

    public double getDistance() {
        if (this.mDistance == null) {
            double bestRssiAvailable = (double) this.mRssi;
            if (this.mRunningAverageRssi != null) {
                bestRssiAvailable = this.mRunningAverageRssi.doubleValue();
            } else {
                LogManager.d(TAG, "Not using running average RSSI because it is null", new Object[0]);
            }
            this.mDistance = calculateDistance(this.mTxPower, bestRssiAvailable);
        }
        return this.mDistance.doubleValue();
    }

    public int getRssi() {
        return this.mRssi;
    }

    public int getTxPower() {
        return this.mTxPower;
    }

    public int getBeaconTypeCode() {
        return this.mBeaconTypeCode;
    }

    public String getBluetoothAddress() {
        return this.mBluetoothAddress;
    }

    public String getBluetoothName() {
        return this.mBluetoothName;
    }

    public String getParserIdentifier() {
        return this.mParserIdentifier;
    }

    public boolean isMultiFrameBeacon() {
        return this.mMultiFrameBeacon;
    }

    public int hashCode() {
        StringBuilder sb = toStringBuilder();
        if (sHardwareEqualityEnforced) {
            sb.append(this.mBluetoothAddress);
        }
        return sb.toString().hashCode();
    }

    public boolean equals(Object that) {
        if (!(that instanceof Beacon)) {
            return false;
        }
        Beacon thatBeacon = (Beacon) that;
        if (this.mIdentifiers.equals(thatBeacon.mIdentifiers)) {
            return sHardwareEqualityEnforced ? getBluetoothAddress().equals(thatBeacon.getBluetoothAddress()) : true;
        } else {
            return false;
        }
    }

    public void requestData(BeaconDataNotifier notifier) {
        beaconDataFactory.requestBeaconData(this, notifier);
    }

    public String toString() {
        return toStringBuilder().toString();
    }

    private StringBuilder toStringBuilder() {
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
        if (this.mParserIdentifier != null) {
            sb.append(" type " + this.mParserIdentifier);
        }
        return sb;
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mIdentifiers.size());
        for (Identifier identifier : this.mIdentifiers) {
            out.writeString(identifier == null ? null : identifier.toString());
        }
        out.writeDouble(getDistance());
        out.writeInt(this.mRssi);
        out.writeInt(this.mTxPower);
        out.writeString(this.mBluetoothAddress);
        out.writeInt(this.mBeaconTypeCode);
        out.writeInt(this.mServiceUuid);
        out.writeInt(this.mDataFields.size());
        for (Long dataField : this.mDataFields) {
            out.writeLong(dataField.longValue());
        }
        out.writeInt(this.mExtraDataFields.size());
        for (Long dataField2 : this.mExtraDataFields) {
            out.writeLong(dataField2.longValue());
        }
        out.writeInt(this.mManufacturer);
        out.writeString(this.mBluetoothName);
        out.writeString(this.mParserIdentifier);
        out.writeByte((byte) (this.mMultiFrameBeacon ? 1 : 0));
        out.writeValue(this.mRunningAverageRssi);
        out.writeInt(this.mRssiMeasurementCount);
        out.writeInt(this.mPacketCount);
    }

    public boolean isExtraBeaconData() {
        return this.mIdentifiers.size() == 0 && this.mDataFields.size() != 0;
    }

    protected static Double calculateDistance(int txPower, double bestRssiAvailable) {
        if (getDistanceCalculator() != null) {
            return Double.valueOf(getDistanceCalculator().calculateDistance(txPower, bestRssiAvailable));
        }
        LogManager.e(TAG, "Distance calculator not set.  Distance will bet set to -1", new Object[0]);
        return Double.valueOf(-1.0d);
    }
}
