package org.altbeacon.beacon.service.scanner;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanFilter.Builder;
import android.os.ParcelUuid;
import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.logging.LogManager;

@TargetApi(21)
public class ScanFilterUtils {
    public static final String TAG = "ScanFilterUtils";

    class ScanFilterData {
        public byte[] filter;
        public int manufacturer;
        public byte[] mask;
        public Long serviceUuid = null;

        ScanFilterData() {
        }
    }

    public List<ScanFilter> createWildcardScanFilters() {
        List<ScanFilter> scanFilters = new ArrayList();
        scanFilters.add(new Builder().build());
        return scanFilters;
    }

    public List<ScanFilterData> createScanFilterDataForBeaconParser(BeaconParser beaconParser) {
        ArrayList<ScanFilterData> scanFilters = new ArrayList();
        for (int manufacturer : beaconParser.getHardwareAssistManufacturers()) {
            Long serviceUuid = beaconParser.getServiceUuid();
            long typeCode = beaconParser.getMatchingBeaconTypeCode().longValue();
            int startOffset = beaconParser.getMatchingBeaconTypeCodeStartOffset();
            int endOffset = beaconParser.getMatchingBeaconTypeCodeEndOffset();
            byte[] filter = new byte[((endOffset + 1) - 2)];
            byte[] mask = new byte[((endOffset + 1) - 2)];
            byte[] typeCodeBytes = BeaconParser.longToByteArray(typeCode, (endOffset - startOffset) + 1);
            for (int layoutIndex = 2; layoutIndex <= endOffset; layoutIndex++) {
                int filterIndex = layoutIndex - 2;
                if (layoutIndex < startOffset) {
                    filter[filterIndex] = (byte) 0;
                    mask[filterIndex] = (byte) 0;
                } else {
                    filter[filterIndex] = typeCodeBytes[layoutIndex - startOffset];
                    mask[filterIndex] = (byte) -1;
                }
            }
            ScanFilterData sfd = new ScanFilterData();
            sfd.manufacturer = manufacturer;
            sfd.filter = filter;
            sfd.mask = mask;
            sfd.serviceUuid = serviceUuid;
            scanFilters.add(sfd);
        }
        return scanFilters;
    }

    public List<ScanFilter> createScanFiltersForBeaconParsers(List<BeaconParser> beaconParsers) {
        List<ScanFilter> scanFilters = new ArrayList();
        for (BeaconParser beaconParser : beaconParsers) {
            for (ScanFilterData sfd : createScanFilterDataForBeaconParser(beaconParser)) {
                Builder builder = new Builder();
                if (sfd.serviceUuid != null) {
                    String serviceUuidString = String.format("0000%04X-0000-1000-8000-00805f9b34fb", new Object[]{sfd.serviceUuid});
                    String serviceUuidMaskString = "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF";
                    ParcelUuid parcelUuid = ParcelUuid.fromString(serviceUuidString);
                    ParcelUuid parcelUuidMask = ParcelUuid.fromString(serviceUuidMaskString);
                    if (LogManager.isVerboseLoggingEnabled()) {
                        LogManager.m16371d(TAG, "making scan filter for service: " + serviceUuidString + " " + parcelUuid, new Object[0]);
                        LogManager.m16371d(TAG, "making scan filter with service mask: " + serviceUuidMaskString + " " + parcelUuidMask, new Object[0]);
                    }
                    builder.setServiceUuid(parcelUuid, parcelUuidMask);
                } else {
                    builder.setServiceUuid(null);
                    builder.setManufacturerData(sfd.manufacturer, sfd.filter, sfd.mask);
                }
                ScanFilter scanFilter = builder.build();
                if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.m16371d(TAG, "Set up a scan filter: " + scanFilter, new Object[0]);
                }
                scanFilters.add(scanFilter);
            }
        }
        return scanFilters;
    }
}
