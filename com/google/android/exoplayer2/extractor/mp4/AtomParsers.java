package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import com.coremedia.iso.boxes.MetaBox;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker.Results;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.android.exoplayer2.video.HevcConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.telegram.messenger.exoplayer2.C1681C;

final class AtomParsers {
    private static final String TAG = "AtomParsers";
    private static final int TYPE_cenc = Util.getIntegerCodeForString(C1681C.CENC_TYPE_cenc);
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_meta = Util.getIntegerCodeForString(MetaBox.TYPE);
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_text = Util.getIntegerCodeForString("text");
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");

    public static Track parseTrak(ContainerAtom trak, LeafAtom mvhd, long duration, DrmInitData drmInitData, boolean isQuickTime) throws ParserException {
        ContainerAtom mdia = trak.getContainerAtomOfType(Atom.TYPE_mdia);
        int trackType = parseHdlr(mdia.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (trackType == -1) {
            return null;
        }
        long durationUs;
        TkhdData tkhdData = parseTkhd(trak.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        if (duration == -9223372036854775807L) {
            duration = TkhdData.access$000(tkhdData);
        }
        long movieTimescale = parseMvhd(mvhd.data);
        if (duration == -9223372036854775807L) {
            durationUs = -9223372036854775807L;
        } else {
            durationUs = Util.scaleLargeTimestamp(duration, 1000000, movieTimescale);
        }
        ContainerAtom stbl = mdia.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair<Long, String> mdhdData = parseMdhd(mdia.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData stsdData = parseStsd(stbl.getLeafAtomOfType(Atom.TYPE_stsd).data, TkhdData.access$100(tkhdData), TkhdData.access$200(tkhdData), (String) mdhdData.second, drmInitData, isQuickTime);
        Pair<long[], long[]> edtsData = parseEdts(trak.getContainerAtomOfType(Atom.TYPE_edts));
        if (stsdData.format == null) {
            return null;
        }
        return new Track(TkhdData.access$100(tkhdData), trackType, ((Long) mdhdData.first).longValue(), movieTimescale, durationUs, stsdData.format, stsdData.requiredSampleTransformation, stsdData.trackEncryptionBoxes, stsdData.nalUnitLengthFieldLength, (long[]) edtsData.first, (long[]) edtsData.second);
    }

    public static TrackSampleTable parseStbl(Track track, ContainerAtom stblAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        LeafAtom stszAtom = stblAtom.getLeafAtomOfType(Atom.TYPE_stsz);
        SampleSizeBox stszSampleSizeBox;
        if (stszAtom != null) {
            stszSampleSizeBox = new StszSampleSizeBox(stszAtom);
        } else {
            LeafAtom stz2Atom = stblAtom.getLeafAtomOfType(Atom.TYPE_stz2);
            if (stz2Atom == null) {
                throw new ParserException("Track has no sample table size information");
            }
            stszSampleSizeBox = new Stz2SampleSizeBox(stz2Atom);
        }
        int sampleCount = sampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(new long[0], new int[0], 0, new long[0], new int[0]);
        }
        Object offsets;
        Object sizes;
        long[] timestamps;
        Object flags;
        int i;
        boolean chunkOffsetsAreLongs = false;
        LeafAtom chunkOffsetsAtom = stblAtom.getLeafAtomOfType(Atom.TYPE_stco);
        if (chunkOffsetsAtom == null) {
            chunkOffsetsAreLongs = true;
            chunkOffsetsAtom = stblAtom.getLeafAtomOfType(Atom.TYPE_co64);
        }
        ParsableByteArray chunkOffsets = chunkOffsetsAtom.data;
        ParsableByteArray stsc = stblAtom.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray stts = stblAtom.getLeafAtomOfType(Atom.TYPE_stts).data;
        LeafAtom stssAtom = stblAtom.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray stss = stssAtom != null ? stssAtom.data : null;
        LeafAtom cttsAtom = stblAtom.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray ctts = cttsAtom != null ? cttsAtom.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(stsc, chunkOffsets, chunkOffsetsAreLongs);
        stts.setPosition(12);
        int remainingTimestampDeltaChanges = stts.readUnsignedIntToInt() - 1;
        int remainingSamplesAtTimestampDelta = stts.readUnsignedIntToInt();
        int timestampDeltaInTimeUnits = stts.readUnsignedIntToInt();
        int remainingSamplesAtTimestampOffset = 0;
        int remainingTimestampOffsetChanges = 0;
        int timestampOffset = 0;
        if (ctts != null) {
            ctts.setPosition(12);
            remainingTimestampOffsetChanges = ctts.readUnsignedIntToInt();
        }
        int nextSynchronizationSampleIndex = -1;
        int remainingSynchronizationSamples = 0;
        if (stss != null) {
            stss.setPosition(12);
            remainingSynchronizationSamples = stss.readUnsignedIntToInt();
            if (remainingSynchronizationSamples > 0) {
                nextSynchronizationSampleIndex = stss.readUnsignedIntToInt() - 1;
            } else {
                stss = null;
            }
        }
        boolean isRechunkable = sampleSizeBox.isFixedSampleSize() && "audio/raw".equals(track.format.sampleMimeType) && remainingTimestampDeltaChanges == 0 && remainingTimestampOffsetChanges == 0 && remainingSynchronizationSamples == 0;
        int maximumSize = 0;
        long timestampTimeUnits = 0;
        if (isRechunkable) {
            long[] chunkOffsetsBytes = new long[chunkIterator.length];
            int[] chunkSampleCounts = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                chunkOffsetsBytes[chunkIterator.index] = chunkIterator.offset;
                chunkSampleCounts[chunkIterator.index] = chunkIterator.numSamples;
            }
            Results rechunkedResults = FixedSampleSizeRechunker.rechunk(sampleSizeBox.readNextSampleSize(), chunkOffsetsBytes, chunkSampleCounts, (long) timestampDeltaInTimeUnits);
            offsets = rechunkedResults.offsets;
            sizes = rechunkedResults.sizes;
            maximumSize = rechunkedResults.maximumSize;
            timestamps = rechunkedResults.timestamps;
            flags = rechunkedResults.flags;
        } else {
            offsets = new long[sampleCount];
            sizes = new int[sampleCount];
            timestamps = new long[sampleCount];
            flags = new int[sampleCount];
            long offset = 0;
            int remainingSamplesInChunk = 0;
            for (i = 0; i < sampleCount; i++) {
                while (remainingSamplesInChunk == 0) {
                    Assertions.checkState(chunkIterator.moveNext());
                    offset = chunkIterator.offset;
                    remainingSamplesInChunk = chunkIterator.numSamples;
                }
                if (ctts != null) {
                    while (remainingSamplesAtTimestampOffset == 0 && remainingTimestampOffsetChanges > 0) {
                        remainingSamplesAtTimestampOffset = ctts.readUnsignedIntToInt();
                        timestampOffset = ctts.readInt();
                        remainingTimestampOffsetChanges--;
                    }
                    remainingSamplesAtTimestampOffset--;
                }
                offsets[i] = offset;
                sizes[i] = sampleSizeBox.readNextSampleSize();
                if (sizes[i] > maximumSize) {
                    maximumSize = sizes[i];
                }
                timestamps[i] = ((long) timestampOffset) + timestampTimeUnits;
                flags[i] = stss == null ? 1 : 0;
                if (i == nextSynchronizationSampleIndex) {
                    flags[i] = 1;
                    remainingSynchronizationSamples--;
                    if (remainingSynchronizationSamples > 0) {
                        nextSynchronizationSampleIndex = stss.readUnsignedIntToInt() - 1;
                    }
                }
                timestampTimeUnits += (long) timestampDeltaInTimeUnits;
                remainingSamplesAtTimestampDelta--;
                if (remainingSamplesAtTimestampDelta == 0 && remainingTimestampDeltaChanges > 0) {
                    remainingSamplesAtTimestampDelta = stts.readUnsignedIntToInt();
                    timestampDeltaInTimeUnits = stts.readUnsignedIntToInt();
                    remainingTimestampDeltaChanges--;
                }
                offset += (long) sizes[i];
                remainingSamplesInChunk--;
            }
            Assertions.checkArgument(remainingSamplesAtTimestampOffset == 0);
            while (remainingTimestampOffsetChanges > 0) {
                Assertions.checkArgument(ctts.readUnsignedIntToInt() == 0);
                ctts.readInt();
                remainingTimestampOffsetChanges--;
            }
            if (!(remainingSynchronizationSamples == 0 && remainingSamplesAtTimestampDelta == 0 && remainingSamplesInChunk == 0 && remainingTimestampDeltaChanges == 0)) {
                Log.w(TAG, "Inconsistent stbl box for track " + track.id + ": remainingSynchronizationSamples " + remainingSynchronizationSamples + ", remainingSamplesAtTimestampDelta " + remainingSamplesAtTimestampDelta + ", remainingSamplesInChunk " + remainingSamplesInChunk + ", remainingTimestampDeltaChanges " + remainingTimestampDeltaChanges);
            }
        }
        if (track.editListDurations == null || gaplessInfoHolder.hasGaplessInfo()) {
            Util.scaleLargeTimestampsInPlace(timestamps, 1000000, track.timescale);
            return new TrackSampleTable(offsets, sizes, maximumSize, timestamps, flags);
        }
        if (track.editListDurations.length == 1 && track.type == 1 && timestamps.length >= 2) {
            long editStartTime = track.editListMediaTimes[0];
            long editEndTime = editStartTime + Util.scaleLargeTimestamp(track.editListDurations[0], track.timescale, track.movieTimescale);
            long lastSampleEndTime = timestampTimeUnits;
            if (timestamps[0] <= editStartTime && editStartTime < timestamps[1] && timestamps[timestamps.length - 1] < editEndTime && editEndTime <= lastSampleEndTime) {
                long paddingTimeUnits = lastSampleEndTime - editEndTime;
                long encoderDelay = Util.scaleLargeTimestamp(editStartTime - timestamps[0], (long) track.format.sampleRate, track.timescale);
                long encoderPadding = Util.scaleLargeTimestamp(paddingTimeUnits, (long) track.format.sampleRate, track.timescale);
                if (!(encoderDelay == 0 && encoderPadding == 0) && encoderDelay <= 2147483647L && encoderPadding <= 2147483647L) {
                    gaplessInfoHolder.encoderDelay = (int) encoderDelay;
                    gaplessInfoHolder.encoderPadding = (int) encoderPadding;
                    Util.scaleLargeTimestampsInPlace(timestamps, 1000000, track.timescale);
                    return new TrackSampleTable(offsets, sizes, maximumSize, timestamps, flags);
                }
            }
        }
        if (track.editListDurations.length == 1 && track.editListDurations[0] == 0) {
            for (i = 0; i < timestamps.length; i++) {
                timestamps[i] = Util.scaleLargeTimestamp(timestamps[i] - track.editListMediaTimes[0], 1000000, track.timescale);
            }
            return new TrackSampleTable(offsets, sizes, maximumSize, timestamps, flags);
        }
        Object editedOffsets;
        Object editedSizes;
        int editedMaximumSize;
        Object editedFlags;
        boolean omitClippedSample = track.type == 1;
        int editedSampleCount = 0;
        int nextSampleIndex = 0;
        boolean copyMetadata = false;
        for (i = 0; i < track.editListDurations.length; i++) {
            long mediaTime = track.editListMediaTimes[i];
            if (mediaTime != -1) {
                long duration = Util.scaleLargeTimestamp(track.editListDurations[i], track.timescale, track.movieTimescale);
                int startIndex = Util.binarySearchCeil(timestamps, mediaTime, true, true);
                int endIndex = Util.binarySearchCeil(timestamps, mediaTime + duration, omitClippedSample, false);
                editedSampleCount += endIndex - startIndex;
                copyMetadata |= nextSampleIndex != startIndex ? 1 : 0;
                nextSampleIndex = endIndex;
            }
        }
        copyMetadata |= editedSampleCount != sampleCount ? 1 : 0;
        if (copyMetadata) {
            editedOffsets = new long[editedSampleCount];
        } else {
            editedOffsets = offsets;
        }
        if (copyMetadata) {
            editedSizes = new int[editedSampleCount];
        } else {
            editedSizes = sizes;
        }
        if (copyMetadata) {
            editedMaximumSize = 0;
        } else {
            editedMaximumSize = maximumSize;
        }
        if (copyMetadata) {
            editedFlags = new int[editedSampleCount];
        } else {
            editedFlags = flags;
        }
        long[] editedTimestamps = new long[editedSampleCount];
        long pts = 0;
        int sampleIndex = 0;
        for (i = 0; i < track.editListDurations.length; i++) {
            mediaTime = track.editListMediaTimes[i];
            duration = track.editListDurations[i];
            if (mediaTime != -1) {
                long endMediaTime = mediaTime + Util.scaleLargeTimestamp(duration, track.timescale, track.movieTimescale);
                startIndex = Util.binarySearchCeil(timestamps, mediaTime, true, true);
                endIndex = Util.binarySearchCeil(timestamps, endMediaTime, omitClippedSample, false);
                if (copyMetadata) {
                    int count = endIndex - startIndex;
                    System.arraycopy(offsets, startIndex, editedOffsets, sampleIndex, count);
                    System.arraycopy(sizes, startIndex, editedSizes, sampleIndex, count);
                    System.arraycopy(flags, startIndex, editedFlags, sampleIndex, count);
                }
                for (int j = startIndex; j < endIndex; j++) {
                    editedTimestamps[sampleIndex] = Util.scaleLargeTimestamp(pts, 1000000, track.movieTimescale) + Util.scaleLargeTimestamp(timestamps[j] - mediaTime, 1000000, track.timescale);
                    if (copyMetadata && editedSizes[sampleIndex] > editedMaximumSize) {
                        editedMaximumSize = sizes[j];
                    }
                    sampleIndex++;
                }
            }
            pts += duration;
        }
        boolean hasSyncSample = false;
        for (i = 0; i < editedFlags.length && !hasSyncSample; i++) {
            hasSyncSample |= (editedFlags[i] & 1) != 0 ? 1 : 0;
        }
        if (hasSyncSample) {
            return new TrackSampleTable(editedOffsets, editedSizes, editedMaximumSize, editedTimestamps, editedFlags);
        }
        throw new ParserException("The edited sample sequence does not contain a sync sample.");
    }

    public static Metadata parseUdta(LeafAtom udtaAtom, boolean isQuickTime) {
        if (isQuickTime) {
            return null;
        }
        ParsableByteArray udtaData = udtaAtom.data;
        udtaData.setPosition(8);
        while (udtaData.bytesLeft() >= 8) {
            int atomPosition = udtaData.getPosition();
            int atomSize = udtaData.readInt();
            if (udtaData.readInt() == Atom.TYPE_meta) {
                udtaData.setPosition(atomPosition);
                return parseMetaAtom(udtaData, atomPosition + atomSize);
            }
            udtaData.skipBytes(atomSize - 8);
        }
        return null;
    }

    private static Metadata parseMetaAtom(ParsableByteArray meta, int limit) {
        meta.skipBytes(12);
        while (meta.getPosition() < limit) {
            int atomPosition = meta.getPosition();
            int atomSize = meta.readInt();
            if (meta.readInt() == Atom.TYPE_ilst) {
                meta.setPosition(atomPosition);
                return parseIlst(meta, atomPosition + atomSize);
            }
            meta.skipBytes(atomSize - 8);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray ilst, int limit) {
        ilst.skipBytes(8);
        List entries = new ArrayList();
        while (ilst.getPosition() < limit) {
            Entry entry = MetadataUtil.parseIlstElement(ilst);
            if (entry != null) {
                entries.add(entry);
            }
        }
        return entries.isEmpty() ? null : new Metadata(entries);
    }

    private static long parseMvhd(ParsableByteArray mvhd) {
        int i = 8;
        mvhd.setPosition(8);
        if (Atom.parseFullAtomVersion(mvhd.readInt()) != 0) {
            i = 16;
        }
        mvhd.skipBytes(i);
        return mvhd.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray tkhd) {
        long duration;
        int rotationDegrees;
        tkhd.setPosition(8);
        int version = Atom.parseFullAtomVersion(tkhd.readInt());
        tkhd.skipBytes(version == 0 ? 8 : 16);
        int trackId = tkhd.readInt();
        tkhd.skipBytes(4);
        boolean durationUnknown = true;
        int durationPosition = tkhd.getPosition();
        int durationByteCount = version == 0 ? 4 : 8;
        for (int i = 0; i < durationByteCount; i++) {
            if (tkhd.data[durationPosition + i] != (byte) -1) {
                durationUnknown = false;
                break;
            }
        }
        if (durationUnknown) {
            tkhd.skipBytes(durationByteCount);
            duration = -9223372036854775807L;
        } else {
            duration = version == 0 ? tkhd.readUnsignedInt() : tkhd.readUnsignedLongToLong();
            if (duration == 0) {
                duration = -9223372036854775807L;
            }
        }
        tkhd.skipBytes(16);
        int a00 = tkhd.readInt();
        int a01 = tkhd.readInt();
        tkhd.skipBytes(4);
        int a10 = tkhd.readInt();
        int a11 = tkhd.readInt();
        if (a00 == 0 && a01 == 65536 && a10 == (-65536) && a11 == 0) {
            rotationDegrees = 90;
        } else if (a00 == 0 && a01 == (-65536) && a10 == 65536 && a11 == 0) {
            rotationDegrees = 270;
        } else if (a00 == (-65536) && a01 == 0 && a10 == 0 && a11 == (-65536)) {
            rotationDegrees = 180;
        } else {
            rotationDegrees = 0;
        }
        return new TkhdData(trackId, duration, rotationDegrees);
    }

    private static int parseHdlr(ParsableByteArray hdlr) {
        hdlr.setPosition(16);
        int trackType = hdlr.readInt();
        if (trackType == TYPE_soun) {
            return 1;
        }
        if (trackType == TYPE_vide) {
            return 2;
        }
        if (trackType == TYPE_text || trackType == TYPE_sbtl || trackType == TYPE_subt || trackType == TYPE_clcp) {
            return 3;
        }
        if (trackType == TYPE_meta) {
            return 4;
        }
        return -1;
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray mdhd) {
        int i = 8;
        mdhd.setPosition(8);
        int version = Atom.parseFullAtomVersion(mdhd.readInt());
        mdhd.skipBytes(version == 0 ? 8 : 16);
        long timescale = mdhd.readUnsignedInt();
        if (version == 0) {
            i = 4;
        }
        mdhd.skipBytes(i);
        int languageCode = mdhd.readUnsignedShort();
        return Pair.create(Long.valueOf(timescale), "" + ((char) (((languageCode >> 10) & 31) + 96)) + ((char) (((languageCode >> 5) & 31) + 96)) + ((char) ((languageCode & 31) + 96)));
    }

    private static StsdData parseStsd(ParsableByteArray stsd, int trackId, int rotationDegrees, String language, DrmInitData drmInitData, boolean isQuickTime) throws ParserException {
        stsd.setPosition(12);
        int numberOfEntries = stsd.readInt();
        StsdData out = new StsdData(numberOfEntries);
        for (int i = 0; i < numberOfEntries; i++) {
            int childStartPosition = stsd.getPosition();
            int childAtomSize = stsd.readInt();
            Assertions.checkArgument(childAtomSize > 0, "childAtomSize should be positive");
            int childAtomType = stsd.readInt();
            if (childAtomType == Atom.TYPE_avc1 || childAtomType == Atom.TYPE_avc3 || childAtomType == Atom.TYPE_encv || childAtomType == Atom.TYPE_mp4v || childAtomType == Atom.TYPE_hvc1 || childAtomType == Atom.TYPE_hev1 || childAtomType == Atom.TYPE_s263 || childAtomType == Atom.TYPE_vp08 || childAtomType == Atom.TYPE_vp09) {
                parseVideoSampleEntry(stsd, childAtomType, childStartPosition, childAtomSize, trackId, rotationDegrees, drmInitData, out, i);
            } else if (childAtomType == Atom.TYPE_mp4a || childAtomType == Atom.TYPE_enca || childAtomType == Atom.TYPE_ac_3 || childAtomType == Atom.TYPE_ec_3 || childAtomType == Atom.TYPE_dtsc || childAtomType == Atom.TYPE_dtse || childAtomType == Atom.TYPE_dtsh || childAtomType == Atom.TYPE_dtsl || childAtomType == Atom.TYPE_samr || childAtomType == Atom.TYPE_sawb || childAtomType == Atom.TYPE_lpcm || childAtomType == Atom.TYPE_sowt || childAtomType == Atom.TYPE__mp3 || childAtomType == Atom.TYPE_alac) {
                parseAudioSampleEntry(stsd, childAtomType, childStartPosition, childAtomSize, trackId, language, isQuickTime, drmInitData, out, i);
            } else if (childAtomType == Atom.TYPE_TTML || childAtomType == Atom.TYPE_tx3g || childAtomType == Atom.TYPE_wvtt || childAtomType == Atom.TYPE_stpp || childAtomType == Atom.TYPE_c608) {
                parseTextSampleEntry(stsd, childAtomType, childStartPosition, childAtomSize, trackId, language, drmInitData, out);
            } else if (childAtomType == Atom.TYPE_camm) {
                out.format = Format.createSampleFormat(Integer.toString(trackId), "application/x-camera-motion", null, -1, drmInitData);
            }
            stsd.setPosition(childStartPosition + childAtomSize);
        }
        return out;
    }

    private static void parseTextSampleEntry(ParsableByteArray parent, int atomType, int position, int atomSize, int trackId, String language, DrmInitData drmInitData, StsdData out) throws ParserException {
        String mimeType;
        parent.setPosition((position + 8) + 8);
        List<byte[]> initializationData = null;
        long subsampleOffsetUs = Long.MAX_VALUE;
        if (atomType == Atom.TYPE_TTML) {
            mimeType = "application/ttml+xml";
        } else if (atomType == Atom.TYPE_tx3g) {
            mimeType = "application/x-quicktime-tx3g";
            int sampleDescriptionLength = (atomSize - 8) - 8;
            byte[] sampleDescriptionData = new byte[sampleDescriptionLength];
            parent.readBytes(sampleDescriptionData, 0, sampleDescriptionLength);
            initializationData = Collections.singletonList(sampleDescriptionData);
        } else if (atomType == Atom.TYPE_wvtt) {
            mimeType = "application/x-mp4-vtt";
        } else if (atomType == Atom.TYPE_stpp) {
            mimeType = "application/ttml+xml";
            subsampleOffsetUs = 0;
        } else if (atomType == Atom.TYPE_c608) {
            mimeType = "application/x-mp4-cea-608";
            out.requiredSampleTransformation = 1;
        } else {
            throw new IllegalStateException();
        }
        out.format = Format.createTextSampleFormat(Integer.toString(trackId), mimeType, null, -1, 0, language, -1, drmInitData, subsampleOffsetUs, initializationData);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parent, int atomType, int position, int size, int trackId, int rotationDegrees, DrmInitData drmInitData, StsdData out, int entryIndex) throws ParserException {
        parent.setPosition((position + 8) + 8);
        parent.skipBytes(16);
        int width = parent.readUnsignedShort();
        int height = parent.readUnsignedShort();
        boolean pixelWidthHeightRatioFromPasp = false;
        float pixelWidthHeightRatio = 1.0f;
        parent.skipBytes(50);
        int childPosition = parent.getPosition();
        if (atomType == Atom.TYPE_encv) {
            atomType = parseSampleEntryEncryptionData(parent, position, size, out, entryIndex);
            parent.setPosition(childPosition);
        }
        List<byte[]> initializationData = null;
        String mimeType = null;
        byte[] projectionData = null;
        int stereoMode = -1;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childStartPosition = parent.getPosition();
            int childAtomSize = parent.readInt();
            if (childAtomSize != 0 || parent.getPosition() - position != size) {
                Assertions.checkArgument(childAtomSize > 0, "childAtomSize should be positive");
                int childAtomType = parent.readInt();
                if (childAtomType == Atom.TYPE_avcC) {
                    Assertions.checkState(mimeType == null);
                    mimeType = "video/avc";
                    parent.setPosition(childStartPosition + 8);
                    AvcConfig avcConfig = AvcConfig.parse(parent);
                    initializationData = avcConfig.initializationData;
                    out.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
                    if (!pixelWidthHeightRatioFromPasp) {
                        pixelWidthHeightRatio = avcConfig.pixelWidthAspectRatio;
                    }
                } else if (childAtomType == Atom.TYPE_hvcC) {
                    Assertions.checkState(mimeType == null);
                    mimeType = "video/hevc";
                    parent.setPosition(childStartPosition + 8);
                    HevcConfig hevcConfig = HevcConfig.parse(parent);
                    initializationData = hevcConfig.initializationData;
                    out.nalUnitLengthFieldLength = hevcConfig.nalUnitLengthFieldLength;
                } else if (childAtomType == Atom.TYPE_vpcC) {
                    Assertions.checkState(mimeType == null);
                    mimeType = atomType == Atom.TYPE_vp08 ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
                } else if (childAtomType == Atom.TYPE_d263) {
                    Assertions.checkState(mimeType == null);
                    mimeType = "video/3gpp";
                } else if (childAtomType == Atom.TYPE_esds) {
                    Assertions.checkState(mimeType == null);
                    Pair<String, byte[]> mimeTypeAndInitializationData = parseEsdsFromParent(parent, childStartPosition);
                    mimeType = mimeTypeAndInitializationData.first;
                    initializationData = Collections.singletonList(mimeTypeAndInitializationData.second);
                } else if (childAtomType == Atom.TYPE_pasp) {
                    pixelWidthHeightRatio = parsePaspFromParent(parent, childStartPosition);
                    pixelWidthHeightRatioFromPasp = true;
                } else if (childAtomType == Atom.TYPE_sv3d) {
                    projectionData = parseProjFromParent(parent, childStartPosition, childAtomSize);
                } else if (childAtomType == Atom.TYPE_st3d) {
                    int version = parent.readUnsignedByte();
                    parent.skipBytes(3);
                    if (version == 0) {
                        switch (parent.readUnsignedByte()) {
                            case 0:
                                stereoMode = 0;
                                break;
                            case 1:
                                stereoMode = 1;
                                break;
                            case 2:
                                stereoMode = 2;
                                break;
                            case 3:
                                stereoMode = 3;
                                break;
                            default:
                                break;
                        }
                    }
                }
                childPosition += childAtomSize;
            } else if (mimeType == null) {
                out.format = Format.createVideoSampleFormat(Integer.toString(trackId), mimeType, null, -1, -1, width, height, -1.0f, initializationData, rotationDegrees, pixelWidthHeightRatio, projectionData, stereoMode, null, drmInitData);
            }
        }
        if (mimeType == null) {
            out.format = Format.createVideoSampleFormat(Integer.toString(trackId), mimeType, null, -1, -1, width, height, -1.0f, initializationData, rotationDegrees, pixelWidthHeightRatio, projectionData, stereoMode, null, drmInitData);
        }
    }

    private static Pair<long[], long[]> parseEdts(ContainerAtom edtsAtom) {
        if (edtsAtom != null) {
            LeafAtom elst = edtsAtom.getLeafAtomOfType(Atom.TYPE_elst);
            if (elst != null) {
                ParsableByteArray elstData = elst.data;
                elstData.setPosition(8);
                int version = Atom.parseFullAtomVersion(elstData.readInt());
                int entryCount = elstData.readUnsignedIntToInt();
                long[] editListDurations = new long[entryCount];
                long[] editListMediaTimes = new long[entryCount];
                for (int i = 0; i < entryCount; i++) {
                    editListDurations[i] = version == 1 ? elstData.readUnsignedLongToLong() : elstData.readUnsignedInt();
                    editListMediaTimes[i] = version == 1 ? elstData.readLong() : (long) elstData.readInt();
                    if (elstData.readShort() != 1) {
                        throw new IllegalArgumentException("Unsupported media rate.");
                    }
                    elstData.skipBytes(2);
                }
                return Pair.create(editListDurations, editListMediaTimes);
            }
        }
        return Pair.create(null, null);
    }

    private static float parsePaspFromParent(ParsableByteArray parent, int position) {
        parent.setPosition(position + 8);
        return ((float) parent.readUnsignedIntToInt()) / ((float) parent.readUnsignedIntToInt());
    }

    private static void parseAudioSampleEntry(ParsableByteArray parent, int atomType, int position, int size, int trackId, String language, boolean isQuickTime, DrmInitData drmInitData, StsdData out, int entryIndex) {
        int channelCount;
        int sampleRate;
        parent.setPosition((position + 8) + 8);
        int quickTimeSoundDescriptionVersion = 0;
        if (isQuickTime) {
            quickTimeSoundDescriptionVersion = parent.readUnsignedShort();
            parent.skipBytes(6);
        } else {
            parent.skipBytes(8);
        }
        if (quickTimeSoundDescriptionVersion == 0 || quickTimeSoundDescriptionVersion == 1) {
            channelCount = parent.readUnsignedShort();
            parent.skipBytes(6);
            sampleRate = parent.readUnsignedFixedPoint1616();
            if (quickTimeSoundDescriptionVersion == 1) {
                parent.skipBytes(16);
            }
        } else if (quickTimeSoundDescriptionVersion == 2) {
            parent.skipBytes(16);
            sampleRate = (int) Math.round(parent.readDouble());
            channelCount = parent.readUnsignedIntToInt();
            parent.skipBytes(20);
        } else {
            return;
        }
        int childPosition = parent.getPosition();
        if (atomType == Atom.TYPE_enca) {
            atomType = parseSampleEntryEncryptionData(parent, position, size, out, entryIndex);
            parent.setPosition(childPosition);
        }
        String mimeType = null;
        if (atomType == Atom.TYPE_ac_3) {
            mimeType = "audio/ac3";
        } else if (atomType == Atom.TYPE_ec_3) {
            mimeType = "audio/eac3";
        } else if (atomType == Atom.TYPE_dtsc) {
            mimeType = "audio/vnd.dts";
        } else if (atomType == Atom.TYPE_dtsh || atomType == Atom.TYPE_dtsl) {
            mimeType = "audio/vnd.dts.hd";
        } else if (atomType == Atom.TYPE_dtse) {
            mimeType = "audio/vnd.dts.hd;profile=lbr";
        } else if (atomType == Atom.TYPE_samr) {
            mimeType = "audio/3gpp";
        } else if (atomType == Atom.TYPE_sawb) {
            mimeType = "audio/amr-wb";
        } else if (atomType == Atom.TYPE_lpcm || atomType == Atom.TYPE_sowt) {
            mimeType = "audio/raw";
        } else if (atomType == Atom.TYPE__mp3) {
            mimeType = "audio/mpeg";
        } else if (atomType == Atom.TYPE_alac) {
            mimeType = "audio/alac";
        }
        byte[] initializationData = null;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            Assertions.checkArgument(childAtomSize > 0, "childAtomSize should be positive");
            int childAtomType = parent.readInt();
            if (childAtomType == Atom.TYPE_esds || (isQuickTime && childAtomType == Atom.TYPE_wave)) {
                int esdsAtomPosition;
                if (childAtomType == Atom.TYPE_esds) {
                    esdsAtomPosition = childPosition;
                } else {
                    esdsAtomPosition = findEsdsPosition(parent, childPosition, childAtomSize);
                }
                if (esdsAtomPosition != -1) {
                    Pair<String, byte[]> mimeTypeAndInitializationData = parseEsdsFromParent(parent, esdsAtomPosition);
                    mimeType = mimeTypeAndInitializationData.first;
                    initializationData = (byte[]) mimeTypeAndInitializationData.second;
                    if ("audio/mp4a-latm".equals(mimeType)) {
                        Pair<Integer, Integer> audioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(initializationData);
                        sampleRate = ((Integer) audioSpecificConfig.first).intValue();
                        channelCount = ((Integer) audioSpecificConfig.second).intValue();
                    }
                }
            } else if (childAtomType == Atom.TYPE_dac3) {
                parent.setPosition(childPosition + 8);
                out.format = Ac3Util.parseAc3AnnexFFormat(parent, Integer.toString(trackId), language, drmInitData);
            } else if (childAtomType == Atom.TYPE_dec3) {
                parent.setPosition(childPosition + 8);
                out.format = Ac3Util.parseEAc3AnnexFFormat(parent, Integer.toString(trackId), language, drmInitData);
            } else if (childAtomType == Atom.TYPE_ddts) {
                out.format = Format.createAudioSampleFormat(Integer.toString(trackId), mimeType, null, -1, -1, channelCount, sampleRate, null, drmInitData, 0, language);
            } else if (childAtomType == Atom.TYPE_alac) {
                initializationData = new byte[childAtomSize];
                parent.setPosition(childPosition);
                parent.readBytes(initializationData, 0, childAtomSize);
            }
            childPosition += childAtomSize;
        }
        if (out.format == null && mimeType != null) {
            List list;
            int pcmEncoding = "audio/raw".equals(mimeType) ? 2 : -1;
            String num = Integer.toString(trackId);
            if (initializationData == null) {
                list = null;
            } else {
                list = Collections.singletonList(initializationData);
            }
            out.format = Format.createAudioSampleFormat(num, mimeType, null, -1, -1, channelCount, sampleRate, pcmEncoding, list, drmInitData, 0, language);
        }
    }

    private static int findEsdsPosition(ParsableByteArray parent, int position, int size) {
        int childAtomPosition = parent.getPosition();
        while (childAtomPosition - position < size) {
            parent.setPosition(childAtomPosition);
            int childAtomSize = parent.readInt();
            Assertions.checkArgument(childAtomSize > 0, "childAtomSize should be positive");
            if (parent.readInt() == Atom.TYPE_esds) {
                return childAtomPosition;
            }
            childAtomPosition += childAtomSize;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parent, int position) {
        String mimeType;
        parent.setPosition((position + 8) + 4);
        parent.skipBytes(1);
        parseExpandableClassSize(parent);
        parent.skipBytes(2);
        int flags = parent.readUnsignedByte();
        if ((flags & 128) != 0) {
            parent.skipBytes(2);
        }
        if ((flags & 64) != 0) {
            parent.skipBytes(parent.readUnsignedShort());
        }
        if ((flags & 32) != 0) {
            parent.skipBytes(2);
        }
        parent.skipBytes(1);
        parseExpandableClassSize(parent);
        switch (parent.readUnsignedByte()) {
            case 32:
                mimeType = "video/mp4v-es";
                break;
            case 33:
                mimeType = "video/avc";
                break;
            case 35:
                mimeType = "video/hevc";
                break;
            case 64:
            case 102:
            case 103:
            case 104:
                mimeType = "audio/mp4a-latm";
                break;
            case 107:
                return Pair.create("audio/mpeg", null);
            case 165:
                mimeType = "audio/ac3";
                break;
            case 166:
                mimeType = "audio/eac3";
                break;
            case 169:
            case 172:
                return Pair.create("audio/vnd.dts", null);
            case 170:
            case 171:
                return Pair.create("audio/vnd.dts.hd", null);
            default:
                mimeType = null;
                break;
        }
        parent.skipBytes(12);
        parent.skipBytes(1);
        int initializationDataSize = parseExpandableClassSize(parent);
        byte[] initializationData = new byte[initializationDataSize];
        parent.readBytes(initializationData, 0, initializationDataSize);
        return Pair.create(mimeType, initializationData);
    }

    private static int parseSampleEntryEncryptionData(ParsableByteArray parent, int position, int size, StsdData out, int entryIndex) {
        int childPosition = parent.getPosition();
        while (childPosition - position < size) {
            boolean z;
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            if (childAtomSize > 0) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z, "childAtomSize should be positive");
            if (parent.readInt() == Atom.TYPE_sinf) {
                Pair<Integer, TrackEncryptionBox> result = parseSinfFromParent(parent, childPosition, childAtomSize);
                if (result != null) {
                    out.trackEncryptionBoxes[entryIndex] = (TrackEncryptionBox) result.second;
                    return ((Integer) result.first).intValue();
                }
            }
            childPosition += childAtomSize;
        }
        return 0;
    }

    private static Pair<Integer, TrackEncryptionBox> parseSinfFromParent(ParsableByteArray parent, int position, int size) {
        boolean z = true;
        int childPosition = position + 8;
        boolean isCencScheme = false;
        TrackEncryptionBox trackEncryptionBox = null;
        Integer dataFormat = null;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            int childAtomType = parent.readInt();
            if (childAtomType == Atom.TYPE_frma) {
                dataFormat = Integer.valueOf(parent.readInt());
            } else if (childAtomType == Atom.TYPE_schm) {
                parent.skipBytes(4);
                isCencScheme = parent.readInt() == TYPE_cenc;
            } else if (childAtomType == Atom.TYPE_schi) {
                trackEncryptionBox = parseSchiFromParent(parent, childPosition, childAtomSize);
            }
            childPosition += childAtomSize;
        }
        if (!isCencScheme) {
            return null;
        }
        boolean z2;
        if (dataFormat != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        Assertions.checkArgument(z2, "frma atom is mandatory");
        if (trackEncryptionBox == null) {
            z = false;
        }
        Assertions.checkArgument(z, "schi->tenc atom is mandatory");
        return Pair.create(dataFormat, trackEncryptionBox);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parent, int position, int size) {
        boolean defaultIsEncrypted = true;
        int childPosition = position + 8;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            if (parent.readInt() == Atom.TYPE_tenc) {
                parent.skipBytes(6);
                if (parent.readUnsignedByte() != 1) {
                    defaultIsEncrypted = false;
                }
                int defaultInitVectorSize = parent.readUnsignedByte();
                byte[] defaultKeyId = new byte[16];
                parent.readBytes(defaultKeyId, 0, defaultKeyId.length);
                return new TrackEncryptionBox(defaultIsEncrypted, defaultInitVectorSize, defaultKeyId);
            }
            childPosition += childAtomSize;
        }
        return null;
    }

    private static byte[] parseProjFromParent(ParsableByteArray parent, int position, int size) {
        int childPosition = position + 8;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            if (parent.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parent.data, childPosition, childPosition + childAtomSize);
            }
            childPosition += childAtomSize;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray data) {
        int currentByte = data.readUnsignedByte();
        int size = currentByte & 127;
        while ((currentByte & 128) == 128) {
            currentByte = data.readUnsignedByte();
            size = (size << 7) | (currentByte & 127);
        }
        return size;
    }

    private AtomParsers() {
    }
}
