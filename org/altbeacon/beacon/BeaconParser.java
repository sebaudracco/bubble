package org.altbeacon.beacon;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.support.v4.view.InputDeviceCompat;
import android.util.Log;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.bluetooth.BleAdvertisement;
import org.altbeacon.bluetooth.Pdu;

public class BeaconParser implements Serializable {
    public static final String ALTBEACON_LAYOUT = "m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25";
    private static final Pattern D_PATTERN = Pattern.compile("d\\:(\\d+)\\-(\\d+)([bl]*)?");
    public static final String EDDYSTONE_TLM_LAYOUT = "x,s:0-1=feaa,m:2-2=20,d:3-3,d:4-5,d:6-7,d:8-11,d:12-15";
    public static final String EDDYSTONE_UID_LAYOUT = "s:0-1=feaa,m:2-2=00,p:3-3:-41,i:4-13,i:14-19";
    public static final String EDDYSTONE_URL_LAYOUT = "s:0-1=feaa,m:2-2=10,p:3-3:-41,i:4-21v";
    private static final char[] HEX_ARRAY = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Pattern I_PATTERN = Pattern.compile("i\\:(\\d+)\\-(\\d+)([blv]*)?");
    private static final String LITTLE_ENDIAN_SUFFIX = "l";
    private static final Pattern M_PATTERN = Pattern.compile("m\\:(\\d+)-(\\d+)\\=([0-9A-Fa-f]+)");
    private static final Pattern P_PATTERN = Pattern.compile("p\\:(\\d+)\\-(\\d+)\\:?([\\-\\d]+)?");
    private static final Pattern S_PATTERN = Pattern.compile("s\\:(\\d+)-(\\d+)\\=([0-9A-Fa-f]+)");
    private static final String TAG = "BeaconParser";
    public static final String URI_BEACON_LAYOUT = "s:0-1=fed8,m:2-2=00,p:3-3:-41,i:4-21v";
    private static final String VARIABLE_LENGTH_SUFFIX = "v";
    private static final Pattern X_PATTERN = Pattern.compile("x");
    protected List<BeaconParser> extraParsers = new ArrayList();
    protected Boolean mAllowPduOverflow = Boolean.valueOf(true);
    protected String mBeaconLayout;
    protected Integer mDBmCorrection;
    protected final List<Integer> mDataEndOffsets = new ArrayList();
    protected final List<Boolean> mDataLittleEndianFlags = new ArrayList();
    protected final List<Integer> mDataStartOffsets = new ArrayList();
    protected Boolean mExtraFrame;
    protected int[] mHardwareAssistManufacturers = new int[]{76};
    protected String mIdentifier;
    protected final List<Integer> mIdentifierEndOffsets = new ArrayList();
    protected final List<Boolean> mIdentifierLittleEndianFlags = new ArrayList();
    protected final List<Integer> mIdentifierStartOffsets = new ArrayList();
    protected final List<Boolean> mIdentifierVariableLengthFlags = new ArrayList();
    protected Integer mLayoutSize;
    private Long mMatchingBeaconTypeCode;
    protected Integer mMatchingBeaconTypeCodeEndOffset;
    protected Integer mMatchingBeaconTypeCodeStartOffset;
    protected Integer mPowerEndOffset;
    protected Integer mPowerStartOffset;
    protected Long mServiceUuid;
    protected Integer mServiceUuidEndOffset;
    protected Integer mServiceUuidStartOffset;

    public static class BeaconLayoutException extends RuntimeException {
        public BeaconLayoutException(String s) {
            super(s);
        }
    }

    public BeaconParser(String identifier) {
        this.mIdentifier = identifier;
    }

    public BeaconParser setBeaconLayout(String beaconLayout) {
        String hexString;
        this.mBeaconLayout = beaconLayout;
        Log.d(TAG, "Parsing beacon layout: " + beaconLayout);
        String[] terms = beaconLayout.split(",");
        this.mExtraFrame = Boolean.valueOf(false);
        int length = terms.length;
        int i = 0;
        while (i < length) {
            String term = terms[i];
            boolean found = false;
            Matcher matcher = I_PATTERN.matcher(term);
            while (matcher.find()) {
                found = true;
                try {
                    int startOffset = Integer.parseInt(matcher.group(1));
                    int endOffset = Integer.parseInt(matcher.group(2));
                    this.mIdentifierLittleEndianFlags.add(Boolean.valueOf(matcher.group(3).contains(LITTLE_ENDIAN_SUFFIX)));
                    this.mIdentifierVariableLengthFlags.add(Boolean.valueOf(matcher.group(3).contains("v")));
                    this.mIdentifierStartOffsets.add(Integer.valueOf(startOffset));
                    this.mIdentifierEndOffsets.add(Integer.valueOf(endOffset));
                } catch (NumberFormatException e) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + term);
                }
            }
            matcher = D_PATTERN.matcher(term);
            while (matcher.find()) {
                found = true;
                try {
                    startOffset = Integer.parseInt(matcher.group(1));
                    endOffset = Integer.parseInt(matcher.group(2));
                    this.mDataLittleEndianFlags.add(Boolean.valueOf(matcher.group(3).contains(LITTLE_ENDIAN_SUFFIX)));
                    this.mDataStartOffsets.add(Integer.valueOf(startOffset));
                    this.mDataEndOffsets.add(Integer.valueOf(endOffset));
                } catch (NumberFormatException e2) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + term);
                }
            }
            matcher = P_PATTERN.matcher(term);
            while (matcher.find()) {
                found = true;
                try {
                    startOffset = Integer.parseInt(matcher.group(1));
                    endOffset = Integer.parseInt(matcher.group(2));
                    int dBmCorrection = 0;
                    if (matcher.group(3) != null) {
                        dBmCorrection = Integer.parseInt(matcher.group(3));
                    }
                    this.mDBmCorrection = Integer.valueOf(dBmCorrection);
                    this.mPowerStartOffset = Integer.valueOf(startOffset);
                    this.mPowerEndOffset = Integer.valueOf(endOffset);
                } catch (NumberFormatException e3) {
                    throw new BeaconLayoutException("Cannot parse integer power byte offset in term: " + term);
                }
            }
            matcher = M_PATTERN.matcher(term);
            while (matcher.find()) {
                found = true;
                try {
                    startOffset = Integer.parseInt(matcher.group(1));
                    endOffset = Integer.parseInt(matcher.group(2));
                    this.mMatchingBeaconTypeCodeStartOffset = Integer.valueOf(startOffset);
                    this.mMatchingBeaconTypeCodeEndOffset = Integer.valueOf(endOffset);
                    hexString = matcher.group(3);
                    try {
                        this.mMatchingBeaconTypeCode = Long.decode("0x" + hexString);
                    } catch (NumberFormatException e4) {
                        throw new BeaconLayoutException("Cannot parse beacon type code: " + hexString + " in term: " + term);
                    }
                } catch (NumberFormatException e5) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + term);
                }
            }
            matcher = S_PATTERN.matcher(term);
            while (matcher.find()) {
                found = true;
                try {
                    startOffset = Integer.parseInt(matcher.group(1));
                    endOffset = Integer.parseInt(matcher.group(2));
                    this.mServiceUuidStartOffset = Integer.valueOf(startOffset);
                    this.mServiceUuidEndOffset = Integer.valueOf(endOffset);
                    hexString = matcher.group(3);
                    try {
                        this.mServiceUuid = Long.decode("0x" + hexString);
                    } catch (NumberFormatException e6) {
                        throw new BeaconLayoutException("Cannot parse serviceUuid: " + hexString + " in term: " + term);
                    }
                } catch (NumberFormatException e7) {
                    throw new BeaconLayoutException("Cannot parse integer byte offset in term: " + term);
                }
            }
            matcher = X_PATTERN.matcher(term);
            while (matcher.find()) {
                found = true;
                this.mExtraFrame = Boolean.valueOf(true);
            }
            if (found) {
                i++;
            } else {
                LogManager.m16371d(TAG, "cannot parse term %s", term);
                throw new BeaconLayoutException("Cannot parse beacon layout term: " + term);
            }
        }
        if (!this.mExtraFrame.booleanValue()) {
            if (this.mIdentifierStartOffsets.size() == 0 || this.mIdentifierEndOffsets.size() == 0) {
                throw new BeaconLayoutException("You must supply at least one identifier offset with a prefix of 'i'");
            } else if (this.mPowerStartOffset == null || this.mPowerEndOffset == null) {
                throw new BeaconLayoutException("You must supply a power byte offset with a prefix of 'p'");
            }
        }
        if (this.mMatchingBeaconTypeCodeStartOffset == null || this.mMatchingBeaconTypeCodeEndOffset == null) {
            throw new BeaconLayoutException("You must supply a matching beacon type expression with a prefix of 'm'");
        }
        this.mLayoutSize = Integer.valueOf(calculateLayoutSize());
        return this;
    }

    public boolean addExtraDataParser(BeaconParser extraDataParser) {
        return extraDataParser != null && extraDataParser.mExtraFrame.booleanValue() && this.extraParsers.add(extraDataParser);
    }

    public List<BeaconParser> getExtraDataParsers() {
        return new ArrayList(this.extraParsers);
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public int[] getHardwareAssistManufacturers() {
        return this.mHardwareAssistManufacturers;
    }

    public void setHardwareAssistManufacturerCodes(int[] manufacturers) {
        this.mHardwareAssistManufacturers = manufacturers;
    }

    public void setAllowPduOverflow(Boolean enabled) {
        this.mAllowPduOverflow = enabled;
    }

    public Long getMatchingBeaconTypeCode() {
        return this.mMatchingBeaconTypeCode;
    }

    public int getMatchingBeaconTypeCodeStartOffset() {
        return this.mMatchingBeaconTypeCodeStartOffset.intValue();
    }

    public int getMatchingBeaconTypeCodeEndOffset() {
        return this.mMatchingBeaconTypeCodeEndOffset.intValue();
    }

    public Long getServiceUuid() {
        return this.mServiceUuid;
    }

    public int getMServiceUuidStartOffset() {
        return this.mServiceUuidStartOffset.intValue();
    }

    public int getServiceUuidEndOffset() {
        return this.mServiceUuidEndOffset.intValue();
    }

    public Beacon fromScanData(byte[] scanData, int rssi, BluetoothDevice device) {
        return fromScanData(scanData, rssi, device, new Beacon());
    }

    protected Beacon fromScanData(byte[] bytesToProcess, int rssi, BluetoothDevice device, Beacon beacon) {
        byte[] serviceUuidBytes;
        byte[] typeCodeBytes;
        int i;
        int txPower;
        String macAddress;
        String name;
        boolean z;
        BleAdvertisement advert = new BleAdvertisement(bytesToProcess);
        boolean parseFailed = false;
        Pdu pduToParse = null;
        int startByte = 0;
        ArrayList<Identifier> identifiers = new ArrayList();
        ArrayList<Long> dataFields = new ArrayList();
        for (Pdu pdu : advert.getPdus()) {
            boolean patternFound;
            int endIndex;
            if (pdu.getType() == (byte) 22 || pdu.getType() == (byte) -1) {
                pduToParse = pdu;
                if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.m16371d(TAG, "Processing pdu type %02X: %s with startIndex: %d, endIndex: %d", Byte.valueOf(pdu.getType()), bytesToHex(bytesToProcess), Integer.valueOf(pdu.getStartIndex()), Integer.valueOf(pdu.getEndIndex()));
                }
                if (pduToParse != null) {
                    if (LogManager.isVerboseLoggingEnabled()) {
                        LogManager.m16371d(TAG, "No PDUs to process in this packet.", new Object[0]);
                    }
                    parseFailed = true;
                } else {
                    serviceUuidBytes = null;
                    typeCodeBytes = longToByteArray(getMatchingBeaconTypeCode().longValue(), (this.mMatchingBeaconTypeCodeEndOffset.intValue() - this.mMatchingBeaconTypeCodeStartOffset.intValue()) + 1);
                    if (getServiceUuid() != null) {
                        serviceUuidBytes = longToByteArray(getServiceUuid().longValue(), (this.mServiceUuidEndOffset.intValue() - this.mServiceUuidStartOffset.intValue()) + 1, false);
                    }
                    startByte = pduToParse.getStartIndex();
                    patternFound = false;
                    if (getServiceUuid() != null) {
                        if (byteArraysMatch(bytesToProcess, this.mMatchingBeaconTypeCodeStartOffset.intValue() + startByte, typeCodeBytes)) {
                            patternFound = true;
                        }
                    } else if (byteArraysMatch(bytesToProcess, this.mServiceUuidStartOffset.intValue() + startByte, serviceUuidBytes) && byteArraysMatch(bytesToProcess, this.mMatchingBeaconTypeCodeStartOffset.intValue() + startByte, typeCodeBytes)) {
                        patternFound = true;
                    }
                    if (!patternFound) {
                        if (getServiceUuid() != null) {
                            if (LogManager.isVerboseLoggingEnabled()) {
                                LogManager.m16371d(TAG, "This is not a matching Beacon advertisement. (Was expecting %s.  The bytes I see are: %s", byteArrayToString(typeCodeBytes), bytesToHex(bytesToProcess));
                            }
                        } else if (LogManager.isVerboseLoggingEnabled()) {
                            LogManager.m16371d(TAG, "This is not a matching Beacon advertisement. Was expecting %s at offset %d and %s at offset %d.  The bytes I see are: %s", byteArrayToString(serviceUuidBytes), Integer.valueOf(this.mServiceUuidStartOffset.intValue() + startByte), byteArrayToString(typeCodeBytes), Integer.valueOf(this.mMatchingBeaconTypeCodeStartOffset.intValue() + startByte), bytesToHex(bytesToProcess));
                        }
                        parseFailed = true;
                        beacon = null;
                    } else if (LogManager.isVerboseLoggingEnabled()) {
                        LogManager.m16371d(TAG, "This is a recognized beacon advertisement -- %s seen", byteArrayToString(typeCodeBytes));
                        LogManager.m16371d(TAG, "Bytes are: %s", bytesToHex(bytesToProcess));
                    }
                    if (patternFound) {
                        if (bytesToProcess.length <= this.mLayoutSize.intValue() + startByte && this.mAllowPduOverflow.booleanValue()) {
                            if (LogManager.isVerboseLoggingEnabled()) {
                                LogManager.m16371d(TAG, "Expanding buffer because it is too short to parse: " + bytesToProcess.length + ", needed: " + (this.mLayoutSize.intValue() + startByte), new Object[0]);
                            }
                            bytesToProcess = ensureMaxSize(bytesToProcess, this.mLayoutSize.intValue() + startByte);
                        }
                        i = 0;
                        while (i < this.mIdentifierEndOffsets.size()) {
                            endIndex = ((Integer) this.mIdentifierEndOffsets.get(i)).intValue() + startByte;
                            if (endIndex <= pduToParse.getEndIndex() && ((Boolean) this.mIdentifierVariableLengthFlags.get(i)).booleanValue()) {
                                if (LogManager.isVerboseLoggingEnabled()) {
                                    LogManager.m16371d(TAG, "Need to truncate identifier by " + (endIndex - pduToParse.getEndIndex()), new Object[0]);
                                }
                                identifiers.add(Identifier.fromBytes(bytesToProcess, ((Integer) this.mIdentifierStartOffsets.get(i)).intValue() + startByte, pduToParse.getEndIndex() + 1, ((Boolean) this.mIdentifierLittleEndianFlags.get(i)).booleanValue()));
                            } else if (endIndex > pduToParse.getEndIndex() || this.mAllowPduOverflow.booleanValue()) {
                                identifiers.add(Identifier.fromBytes(bytesToProcess, ((Integer) this.mIdentifierStartOffsets.get(i)).intValue() + startByte, endIndex + 1, ((Boolean) this.mIdentifierLittleEndianFlags.get(i)).booleanValue()));
                            } else {
                                parseFailed = true;
                                if (LogManager.isVerboseLoggingEnabled()) {
                                    LogManager.m16371d(TAG, "Cannot parse identifier " + i + " because PDU is too short.  endIndex: " + endIndex + " PDU endIndex: " + pduToParse.getEndIndex(), new Object[0]);
                                }
                            }
                            i++;
                        }
                        for (i = 0; i < this.mDataEndOffsets.size(); i++) {
                            endIndex = ((Integer) this.mDataEndOffsets.get(i)).intValue() + startByte;
                            if (endIndex > pduToParse.getEndIndex() || this.mAllowPduOverflow.booleanValue()) {
                                dataFields.add(Long.decode(byteArrayToFormattedString(bytesToProcess, ((Integer) this.mDataStartOffsets.get(i)).intValue() + startByte, endIndex, ((Boolean) this.mDataLittleEndianFlags.get(i)).booleanValue())));
                            } else {
                                if (LogManager.isVerboseLoggingEnabled()) {
                                    LogManager.m16371d(TAG, "Cannot parse data field " + i + " because PDU is too short.  endIndex: " + endIndex + " PDU endIndex: " + pduToParse.getEndIndex() + ".  Setting value to 0", new Object[0]);
                                }
                                dataFields.add(new Long(0));
                            }
                        }
                        if (this.mPowerStartOffset != null) {
                            endIndex = this.mPowerEndOffset.intValue() + startByte;
                            try {
                                if (endIndex > pduToParse.getEndIndex() || this.mAllowPduOverflow.booleanValue()) {
                                    txPower = Integer.parseInt(byteArrayToFormattedString(bytesToProcess, this.mPowerStartOffset.intValue() + startByte, this.mPowerEndOffset.intValue() + startByte, false)) + this.mDBmCorrection.intValue();
                                    if (txPower > 127) {
                                        txPower += InputDeviceCompat.SOURCE_ANY;
                                    }
                                    beacon.mTxPower = txPower;
                                } else {
                                    parseFailed = true;
                                    if (LogManager.isVerboseLoggingEnabled()) {
                                        LogManager.m16371d(TAG, "Cannot parse power field because PDU is too short.  endIndex: " + endIndex + " PDU endIndex: " + pduToParse.getEndIndex(), new Object[0]);
                                    }
                                }
                            } catch (NumberFormatException e) {
                            } catch (NullPointerException e2) {
                            }
                        }
                    }
                }
                if (parseFailed) {
                    return null;
                }
                int beaconTypeCode = Integer.parseInt(byteArrayToFormattedString(bytesToProcess, this.mMatchingBeaconTypeCodeStartOffset.intValue() + startByte, this.mMatchingBeaconTypeCodeEndOffset.intValue() + startByte, false));
                int manufacturer = Integer.parseInt(byteArrayToFormattedString(bytesToProcess, startByte, startByte + 1, true));
                macAddress = null;
                name = null;
                if (device != null) {
                    macAddress = device.getAddress();
                    name = device.getName();
                }
                beacon.mIdentifiers = identifiers;
                beacon.mDataFields = dataFields;
                beacon.mRssi = rssi;
                beacon.mBeaconTypeCode = beaconTypeCode;
                if (this.mServiceUuid == null) {
                    beacon.mServiceUuid = (int) this.mServiceUuid.longValue();
                } else {
                    beacon.mServiceUuid = -1;
                }
                beacon.mBluetoothAddress = macAddress;
                beacon.mBluetoothName = name;
                beacon.mManufacturer = manufacturer;
                beacon.mParserIdentifier = this.mIdentifier;
                if (this.extraParsers.size() <= 0 || this.mExtraFrame.booleanValue()) {
                    z = true;
                } else {
                    z = false;
                }
                beacon.mMultiFrameBeacon = z;
                return beacon;
            } else if (LogManager.isVerboseLoggingEnabled()) {
                LogManager.m16371d(TAG, "Ignoring pdu type %02X", Byte.valueOf(pdu.getType()));
            }
        }
        if (pduToParse != null) {
            serviceUuidBytes = null;
            typeCodeBytes = longToByteArray(getMatchingBeaconTypeCode().longValue(), (this.mMatchingBeaconTypeCodeEndOffset.intValue() - this.mMatchingBeaconTypeCodeStartOffset.intValue()) + 1);
            if (getServiceUuid() != null) {
                serviceUuidBytes = longToByteArray(getServiceUuid().longValue(), (this.mServiceUuidEndOffset.intValue() - this.mServiceUuidStartOffset.intValue()) + 1, false);
            }
            startByte = pduToParse.getStartIndex();
            patternFound = false;
            if (getServiceUuid() != null) {
                patternFound = true;
            } else if (byteArraysMatch(bytesToProcess, this.mMatchingBeaconTypeCodeStartOffset.intValue() + startByte, typeCodeBytes)) {
                patternFound = true;
            }
            if (!patternFound) {
                if (getServiceUuid() != null) {
                    if (LogManager.isVerboseLoggingEnabled()) {
                        LogManager.m16371d(TAG, "This is not a matching Beacon advertisement. Was expecting %s at offset %d and %s at offset %d.  The bytes I see are: %s", byteArrayToString(serviceUuidBytes), Integer.valueOf(this.mServiceUuidStartOffset.intValue() + startByte), byteArrayToString(typeCodeBytes), Integer.valueOf(this.mMatchingBeaconTypeCodeStartOffset.intValue() + startByte), bytesToHex(bytesToProcess));
                    }
                } else if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.m16371d(TAG, "This is not a matching Beacon advertisement. (Was expecting %s.  The bytes I see are: %s", byteArrayToString(typeCodeBytes), bytesToHex(bytesToProcess));
                }
                parseFailed = true;
                beacon = null;
            } else if (LogManager.isVerboseLoggingEnabled()) {
                LogManager.m16371d(TAG, "This is a recognized beacon advertisement -- %s seen", byteArrayToString(typeCodeBytes));
                LogManager.m16371d(TAG, "Bytes are: %s", bytesToHex(bytesToProcess));
            }
            if (patternFound) {
                if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.m16371d(TAG, "Expanding buffer because it is too short to parse: " + bytesToProcess.length + ", needed: " + (this.mLayoutSize.intValue() + startByte), new Object[0]);
                }
                bytesToProcess = ensureMaxSize(bytesToProcess, this.mLayoutSize.intValue() + startByte);
                i = 0;
                while (i < this.mIdentifierEndOffsets.size()) {
                    endIndex = ((Integer) this.mIdentifierEndOffsets.get(i)).intValue() + startByte;
                    if (endIndex <= pduToParse.getEndIndex()) {
                    }
                    if (endIndex > pduToParse.getEndIndex()) {
                    }
                    identifiers.add(Identifier.fromBytes(bytesToProcess, ((Integer) this.mIdentifierStartOffsets.get(i)).intValue() + startByte, endIndex + 1, ((Boolean) this.mIdentifierLittleEndianFlags.get(i)).booleanValue()));
                    i++;
                }
                while (i < this.mDataEndOffsets.size()) {
                    endIndex = ((Integer) this.mDataEndOffsets.get(i)).intValue() + startByte;
                    if (endIndex > pduToParse.getEndIndex()) {
                    }
                    dataFields.add(Long.decode(byteArrayToFormattedString(bytesToProcess, ((Integer) this.mDataStartOffsets.get(i)).intValue() + startByte, endIndex, ((Boolean) this.mDataLittleEndianFlags.get(i)).booleanValue())));
                }
                if (this.mPowerStartOffset != null) {
                    endIndex = this.mPowerEndOffset.intValue() + startByte;
                    if (endIndex > pduToParse.getEndIndex()) {
                    }
                    txPower = Integer.parseInt(byteArrayToFormattedString(bytesToProcess, this.mPowerStartOffset.intValue() + startByte, this.mPowerEndOffset.intValue() + startByte, false)) + this.mDBmCorrection.intValue();
                    if (txPower > 127) {
                        txPower += InputDeviceCompat.SOURCE_ANY;
                    }
                    beacon.mTxPower = txPower;
                }
            }
        } else {
            if (LogManager.isVerboseLoggingEnabled()) {
                LogManager.m16371d(TAG, "No PDUs to process in this packet.", new Object[0]);
            }
            parseFailed = true;
        }
        if (parseFailed) {
            return null;
        }
        int beaconTypeCode2 = Integer.parseInt(byteArrayToFormattedString(bytesToProcess, this.mMatchingBeaconTypeCodeStartOffset.intValue() + startByte, this.mMatchingBeaconTypeCodeEndOffset.intValue() + startByte, false));
        int manufacturer2 = Integer.parseInt(byteArrayToFormattedString(bytesToProcess, startByte, startByte + 1, true));
        macAddress = null;
        name = null;
        if (device != null) {
            macAddress = device.getAddress();
            name = device.getName();
        }
        beacon.mIdentifiers = identifiers;
        beacon.mDataFields = dataFields;
        beacon.mRssi = rssi;
        beacon.mBeaconTypeCode = beaconTypeCode2;
        if (this.mServiceUuid == null) {
            beacon.mServiceUuid = -1;
        } else {
            beacon.mServiceUuid = (int) this.mServiceUuid.longValue();
        }
        beacon.mBluetoothAddress = macAddress;
        beacon.mBluetoothName = name;
        beacon.mManufacturer = manufacturer2;
        beacon.mParserIdentifier = this.mIdentifier;
        if (this.extraParsers.size() <= 0) {
        }
        z = true;
        beacon.mMultiFrameBeacon = z;
        return beacon;
    }

    @TargetApi(9)
    public byte[] getBeaconAdvertisementData(Beacon beacon) {
        if (beacon.getIdentifiers().size() != getIdentifierCount()) {
            throw new IllegalArgumentException("Beacon has " + beacon.getIdentifiers().size() + " identifiers but format requires " + getIdentifierCount());
        }
        int index;
        int lastIndex = -1;
        if (this.mMatchingBeaconTypeCodeEndOffset != null && this.mMatchingBeaconTypeCodeEndOffset.intValue() > -1) {
            lastIndex = this.mMatchingBeaconTypeCodeEndOffset.intValue();
        }
        if (this.mPowerEndOffset != null && this.mPowerEndOffset.intValue() > lastIndex) {
            lastIndex = this.mPowerEndOffset.intValue();
        }
        int identifierNum = 0;
        while (identifierNum < this.mIdentifierEndOffsets.size()) {
            if (this.mIdentifierEndOffsets.get(identifierNum) != null && ((Integer) this.mIdentifierEndOffsets.get(identifierNum)).intValue() > lastIndex) {
                lastIndex = ((Integer) this.mIdentifierEndOffsets.get(identifierNum)).intValue();
            }
            identifierNum++;
        }
        identifierNum = 0;
        while (identifierNum < this.mDataEndOffsets.size()) {
            if (this.mDataEndOffsets.get(identifierNum) != null && ((Integer) this.mDataEndOffsets.get(identifierNum)).intValue() > lastIndex) {
                lastIndex = ((Integer) this.mDataEndOffsets.get(identifierNum)).intValue();
            }
            identifierNum++;
        }
        int adjustedIdentifiersLength = 0;
        for (identifierNum = 0; identifierNum < this.mIdentifierStartOffsets.size(); identifierNum++) {
            if (((Boolean) this.mIdentifierVariableLengthFlags.get(identifierNum)).booleanValue()) {
                adjustedIdentifiersLength = (adjustedIdentifiersLength + beacon.getIdentifier(identifierNum).getByteCount()) - ((((Integer) this.mIdentifierEndOffsets.get(identifierNum)).intValue() - ((Integer) this.mIdentifierStartOffsets.get(identifierNum)).intValue()) + 1);
            }
        }
        byte[] advertisingBytes = new byte[(((lastIndex + adjustedIdentifiersLength) + 1) - 2)];
        long beaconTypeCode = getMatchingBeaconTypeCode().longValue();
        for (index = this.mMatchingBeaconTypeCodeStartOffset.intValue(); index <= this.mMatchingBeaconTypeCodeEndOffset.intValue(); index++) {
            advertisingBytes[index - 2] = (byte) ((int) ((getMatchingBeaconTypeCode().longValue() >> ((this.mMatchingBeaconTypeCodeEndOffset.intValue() - index) * 8)) & 255));
        }
        for (identifierNum = 0; identifierNum < this.mIdentifierStartOffsets.size(); identifierNum++) {
            byte[] identifierBytes = beacon.getIdentifier(identifierNum).toByteArrayOfSpecifiedEndianness(!((Boolean) this.mIdentifierLittleEndianFlags.get(identifierNum)).booleanValue());
            if (identifierBytes.length < getIdentifierByteCount(identifierNum)) {
                if (!((Boolean) this.mIdentifierVariableLengthFlags.get(identifierNum)).booleanValue()) {
                    if (((Boolean) this.mIdentifierLittleEndianFlags.get(identifierNum)).booleanValue()) {
                        identifierBytes = Arrays.copyOf(identifierBytes, getIdentifierByteCount(identifierNum));
                    } else {
                        Object newIdentifierBytes = new byte[getIdentifierByteCount(identifierNum)];
                        System.arraycopy(identifierBytes, 0, newIdentifierBytes, getIdentifierByteCount(identifierNum) - identifierBytes.length, identifierBytes.length);
                        Object identifierBytes2 = newIdentifierBytes;
                    }
                }
                LogManager.m16371d(TAG, "Expanded identifier because it is too short.  It is now: " + byteArrayToString(identifierBytes), new Object[0]);
            } else {
                if (identifierBytes.length > getIdentifierByteCount(identifierNum)) {
                    if (((Boolean) this.mIdentifierLittleEndianFlags.get(identifierNum)).booleanValue()) {
                        identifierBytes = Arrays.copyOfRange(identifierBytes, getIdentifierByteCount(identifierNum) - identifierBytes.length, getIdentifierByteCount(identifierNum));
                    } else {
                        identifierBytes = Arrays.copyOf(identifierBytes, getIdentifierByteCount(identifierNum));
                    }
                    LogManager.m16371d(TAG, "Truncated identifier because it is too long.  It is now: " + byteArrayToString(identifierBytes), new Object[0]);
                } else {
                    LogManager.m16371d(TAG, "Identifier size is just right: " + byteArrayToString(identifierBytes), new Object[0]);
                }
            }
            for (index = ((Integer) this.mIdentifierStartOffsets.get(identifierNum)).intValue(); index <= (((Integer) this.mIdentifierStartOffsets.get(identifierNum)).intValue() + identifierBytes.length) - 1; index++) {
                advertisingBytes[index - 2] = identifierBytes[index - ((Integer) this.mIdentifierStartOffsets.get(identifierNum)).intValue()];
            }
        }
        if (!(this.mPowerStartOffset == null || this.mPowerEndOffset == null)) {
            for (index = this.mPowerStartOffset.intValue(); index <= this.mPowerEndOffset.intValue(); index++) {
                advertisingBytes[index - 2] = (byte) ((beacon.getTxPower() >> ((index - this.mPowerStartOffset.intValue()) * 8)) & 255);
            }
        }
        for (int dataFieldNum = 0; dataFieldNum < this.mDataStartOffsets.size(); dataFieldNum++) {
            long dataField = ((Long) beacon.getDataFields().get(dataFieldNum)).longValue();
            int dataFieldLength = ((Integer) this.mDataEndOffsets.get(dataFieldNum)).intValue() - ((Integer) this.mDataStartOffsets.get(dataFieldNum)).intValue();
            for (index = 0; index <= dataFieldLength; index++) {
                int endianCorrectedIndex = index;
                if (!((Boolean) this.mDataLittleEndianFlags.get(dataFieldNum)).booleanValue()) {
                    endianCorrectedIndex = dataFieldLength - index;
                }
                advertisingBytes[(((Integer) this.mDataStartOffsets.get(dataFieldNum)).intValue() - 2) + endianCorrectedIndex] = (byte) ((int) ((dataField >> (index * 8)) & 255));
            }
        }
        return advertisingBytes;
    }

    public BeaconParser setMatchingBeaconTypeCode(Long typeCode) {
        this.mMatchingBeaconTypeCode = typeCode;
        return this;
    }

    public int getIdentifierByteCount(int identifierNum) {
        return (((Integer) this.mIdentifierEndOffsets.get(identifierNum)).intValue() - ((Integer) this.mIdentifierStartOffsets.get(identifierNum)).intValue()) + 1;
    }

    public int getIdentifierCount() {
        return this.mIdentifierStartOffsets.size();
    }

    public int getDataFieldCount() {
        return this.mDataStartOffsets.size();
    }

    public String getLayout() {
        return this.mBeaconLayout;
    }

    public int getPowerCorrection() {
        return this.mDBmCorrection.intValue();
    }

    protected static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 2)];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[(j * 2) + 1] = HEX_ARRAY[v & 15];
        }
        return new String(hexChars);
    }

    public static byte[] longToByteArray(long longValue, int length) {
        return longToByteArray(longValue, length, true);
    }

    public static byte[] longToByteArray(long longValue, int length, boolean bigEndian) {
        byte[] array = new byte[length];
        for (int i = 0; i < length; i++) {
            int adjustedI = bigEndian ? i : (length - i) - 1;
            array[i] = (byte) ((int) ((longValue & (255 << (((length - adjustedI) - 1) * 8))) >> ((int) ((long) (((length - adjustedI) - 1) * 8)))));
        }
        return array;
    }

    private int calculateLayoutSize() {
        int endOffset;
        int lastEndOffset = 0;
        if (this.mIdentifierEndOffsets != null) {
            for (Integer intValue : this.mIdentifierEndOffsets) {
                endOffset = intValue.intValue();
                if (endOffset > lastEndOffset) {
                    lastEndOffset = endOffset;
                }
            }
        }
        if (this.mDataEndOffsets != null) {
            for (Integer intValue2 : this.mDataEndOffsets) {
                endOffset = intValue2.intValue();
                if (endOffset > lastEndOffset) {
                    lastEndOffset = endOffset;
                }
            }
        }
        if (this.mPowerEndOffset != null && this.mPowerEndOffset.intValue() > lastEndOffset) {
            lastEndOffset = this.mPowerEndOffset.intValue();
        }
        if (this.mServiceUuidEndOffset != null && this.mServiceUuidEndOffset.intValue() > lastEndOffset) {
            lastEndOffset = this.mServiceUuidEndOffset.intValue();
        }
        return lastEndOffset + 1;
    }

    private boolean byteArraysMatch(byte[] source, int offset, byte[] expected) {
        int length = expected.length;
        if (source.length - offset < length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (source[offset + i] != expected[i]) {
                return false;
            }
        }
        return true;
    }

    private String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(bytes[i])}));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    private String byteArrayToFormattedString(byte[] byteBuffer, int startIndex, int endIndex, boolean littleEndian) {
        int i;
        byte[] bytes = new byte[((endIndex - startIndex) + 1)];
        if (littleEndian) {
            for (i = 0; i <= endIndex - startIndex; i++) {
                bytes[i] = byteBuffer[((bytes.length + startIndex) - 1) - i];
            }
        } else {
            for (i = 0; i <= endIndex - startIndex; i++) {
                bytes[i] = byteBuffer[startIndex + i];
            }
        }
        if ((endIndex - startIndex) + 1 < 5) {
            long number = 0;
            for (i = 0; i < bytes.length; i++) {
                number += ((long) (bytes[(bytes.length - i) - 1] & 255)) * ((long) Math.pow(256.0d, ((double) i) * MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE));
            }
            return Long.toString(number);
        }
        String hexString = bytesToHex(bytes);
        if (bytes.length != 16) {
            return "0x" + hexString;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(hexString.substring(0, 8));
        sb.append("-");
        sb.append(hexString.substring(8, 12));
        sb.append("-");
        sb.append(hexString.substring(12, 16));
        sb.append("-");
        sb.append(hexString.substring(16, 20));
        sb.append("-");
        sb.append(hexString.substring(20, 32));
        return sb.toString();
    }

    @TargetApi(9)
    private byte[] ensureMaxSize(byte[] array, int requiredLength) {
        return array.length >= requiredLength ? array : Arrays.copyOf(array, requiredLength);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.mMatchingBeaconTypeCode, this.mIdentifierStartOffsets, this.mIdentifierEndOffsets, this.mIdentifierLittleEndianFlags, this.mDataStartOffsets, this.mDataEndOffsets, this.mDataLittleEndianFlags, this.mIdentifierVariableLengthFlags, this.mMatchingBeaconTypeCodeStartOffset, this.mMatchingBeaconTypeCodeEndOffset, this.mServiceUuidStartOffset, this.mServiceUuidEndOffset, this.mServiceUuid, this.mExtraFrame, this.mPowerStartOffset, this.mPowerEndOffset, this.mDBmCorrection, this.mLayoutSize, this.mAllowPduOverflow, this.mIdentifier, this.mHardwareAssistManufacturers, this.extraParsers});
    }

    public boolean equals(Object o) {
        try {
            BeaconParser that = (BeaconParser) o;
            if (that.mBeaconLayout != null && that.mBeaconLayout.equals(this.mBeaconLayout) && that.mIdentifier != null && that.mIdentifier.equals(this.mIdentifier)) {
                return true;
            }
        } catch (ClassCastException e) {
        }
        return false;
    }
}
