package org.altbeacon.beacon;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData.Builder;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.ParcelUuid;
import com.stepleaderdigital.reveal.Reveal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import org.altbeacon.beacon.logging.LogManager;

@TargetApi(21)
public class BeaconTransmitter {
    public static final int NOT_SUPPORTED_BLE = 2;
    public static final int NOT_SUPPORTED_CANNOT_GET_ADVERTISER = 4;
    public static final int NOT_SUPPORTED_CANNOT_GET_ADVERTISER_MULTIPLE_ADVERTISEMENTS = 5;
    public static final int NOT_SUPPORTED_MIN_SDK = 1;
    @Deprecated
    public static final int NOT_SUPPORTED_MULTIPLE_ADVERTISEMENTS = 3;
    public static final int SUPPORTED = 0;
    private static final String TAG = "BeaconTransmitter";
    private AdvertiseCallback mAdvertiseCallback;
    private int mAdvertiseMode = 0;
    private int mAdvertiseTxPowerLevel = 3;
    private AdvertiseCallback mAdvertisingClientCallback;
    private Beacon mBeacon;
    private BeaconParser mBeaconParser;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    private boolean mStarted;

    public BeaconTransmitter(Context context, BeaconParser parser) {
        this.mBeaconParser = parser;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Reveal.STATUS_BLUETOOTH);
        if (bluetoothManager != null) {
            this.mBluetoothAdapter = bluetoothManager.getAdapter();
            this.mBluetoothLeAdvertiser = this.mBluetoothAdapter.getBluetoothLeAdvertiser();
            LogManager.d(TAG, "new BeaconTransmitter constructed.  mbluetoothLeAdvertiser is %s", new Object[]{this.mBluetoothLeAdvertiser});
            return;
        }
        LogManager.e(TAG, "Failed to get BluetoothManager", new Object[0]);
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    public void setBeacon(Beacon beacon) {
        this.mBeacon = beacon;
    }

    public void setBeaconParser(BeaconParser beaconParser) {
        this.mBeaconParser = beaconParser;
    }

    public int getAdvertiseMode() {
        return this.mAdvertiseMode;
    }

    public void setAdvertiseMode(int mAdvertiseMode) {
        this.mAdvertiseMode = mAdvertiseMode;
    }

    public int getAdvertiseTxPowerLevel() {
        return this.mAdvertiseTxPowerLevel;
    }

    public void setAdvertiseTxPowerLevel(int mAdvertiseTxPowerLevel) {
        this.mAdvertiseTxPowerLevel = mAdvertiseTxPowerLevel;
    }

    public void startAdvertising(Beacon beacon) {
        startAdvertising(beacon, null);
    }

    public void startAdvertising(Beacon beacon, AdvertiseCallback callback) {
        this.mBeacon = beacon;
        this.mAdvertisingClientCallback = callback;
        startAdvertising();
    }

    public void startAdvertising() {
        if (this.mBeacon == null) {
            throw new NullPointerException("Beacon cannot be null.  Set beacon before starting advertising");
        }
        int manufacturerCode = this.mBeacon.getManufacturer();
        int serviceUuid = -1;
        if (this.mBeaconParser.getServiceUuid() != null) {
            serviceUuid = this.mBeaconParser.getServiceUuid().intValue();
        }
        if (this.mBeaconParser == null) {
            throw new NullPointerException("You must supply a BeaconParser instance to BeaconTransmitter.");
        }
        byte[] advertisingBytes = this.mBeaconParser.getBeaconAdvertisementData(this.mBeacon);
        String byteString = "";
        for (int i = 0; i < advertisingBytes.length; i++) {
            byteString = (byteString + String.format("%02X", new Object[]{Byte.valueOf(advertisingBytes[i])})) + " ";
        }
        String str = TAG;
        String str2 = "Starting advertising with ID1: %s ID2: %s ID3: %s and data: %s of size %s";
        Object[] objArr = new Object[5];
        objArr[0] = this.mBeacon.getId1();
        objArr[1] = this.mBeacon.getIdentifiers().size() > 1 ? this.mBeacon.getId2() : "";
        objArr[2] = this.mBeacon.getIdentifiers().size() > 2 ? this.mBeacon.getId3() : "";
        objArr[3] = byteString;
        objArr[4] = Integer.valueOf(advertisingBytes.length);
        LogManager.d(str, str2, objArr);
        try {
            Builder dataBuilder = new Builder();
            if (serviceUuid > 0) {
                ParcelUuid parcelUuid = parseUuidFrom(new byte[]{(byte) (serviceUuid & 255), (byte) ((serviceUuid >> 8) & 255)});
                dataBuilder.addServiceData(parcelUuid, advertisingBytes);
                dataBuilder.addServiceUuid(parcelUuid);
                dataBuilder.setIncludeTxPowerLevel(false);
                dataBuilder.setIncludeDeviceName(false);
            } else {
                dataBuilder.addManufacturerData(manufacturerCode, advertisingBytes);
            }
            AdvertiseSettings.Builder settingsBuilder = new AdvertiseSettings.Builder();
            settingsBuilder.setAdvertiseMode(this.mAdvertiseMode);
            settingsBuilder.setTxPowerLevel(this.mAdvertiseTxPowerLevel);
            settingsBuilder.setConnectable(false);
            this.mBluetoothLeAdvertiser.startAdvertising(settingsBuilder.build(), dataBuilder.build(), getAdvertiseCallback());
            LogManager.d(TAG, "Started advertisement with callback: %s", new Object[]{getAdvertiseCallback()});
        } catch (Exception e) {
            LogManager.e(e, TAG, "Cannot start advertising due to exception", new Object[0]);
        }
    }

    public void stopAdvertising() {
        if (this.mStarted) {
            LogManager.d(TAG, "Stopping advertising with object %s", new Object[]{this.mBluetoothLeAdvertiser});
            this.mAdvertisingClientCallback = null;
            try {
                this.mBluetoothLeAdvertiser.stopAdvertising(getAdvertiseCallback());
            } catch (IllegalStateException e) {
                LogManager.w(TAG, "Bluetooth is turned off. Transmitter stop call failed.", new Object[0]);
            }
            this.mStarted = false;
            return;
        }
        LogManager.d(TAG, "Skipping stop advertising -- not started", new Object[0]);
    }

    public static int checkTransmissionSupported(Context context) {
        if (VERSION.SDK_INT < 21) {
            return 1;
        }
        if (!context.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            return 2;
        }
        try {
            if (((BluetoothManager) context.getSystemService(Reveal.STATUS_BLUETOOTH)).getAdapter().getBluetoothLeAdvertiser() != null) {
                return 0;
            }
            if (((BluetoothManager) context.getSystemService(Reveal.STATUS_BLUETOOTH)).getAdapter().isMultipleAdvertisementSupported()) {
                return 4;
            }
            return 5;
        } catch (Exception e) {
            return 4;
        }
    }

    private AdvertiseCallback getAdvertiseCallback() {
        if (this.mAdvertiseCallback == null) {
            this.mAdvertiseCallback = new 1(this);
        }
        return this.mAdvertiseCallback;
    }

    private static ParcelUuid parseUuidFrom(byte[] uuidBytes) {
        ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
        if (uuidBytes == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = uuidBytes.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        } else if (length == 16) {
            ByteBuffer buf = ByteBuffer.wrap(uuidBytes).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(buf.getLong(8), buf.getLong(0)));
        } else {
            long shortUuid;
            if (length == 2) {
                shortUuid = ((long) (uuidBytes[0] & 255)) + ((long) ((uuidBytes[1] & 255) << 8));
            } else {
                shortUuid = ((((long) (uuidBytes[0] & 255)) + ((long) ((uuidBytes[1] & 255) << 8))) + ((long) ((uuidBytes[2] & 255) << 16))) + ((long) ((uuidBytes[3] & 255) << 24));
            }
            return new ParcelUuid(new UUID(BASE_UUID.getUuid().getMostSignificantBits() + (shortUuid << 32), BASE_UUID.getUuid().getLeastSignificantBits()));
        }
    }
}
