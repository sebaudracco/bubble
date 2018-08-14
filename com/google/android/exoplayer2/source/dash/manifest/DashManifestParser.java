package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTimelineElement;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.mp4parser.iso14496.part30.WebVTTSampleEntry;
import com.mp4parser.iso14496.part30.XMLSubtitleSampleEntry;
import cz.msebera.android.httpclient.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.aspectj.lang.JoinPoint;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class DashManifestParser extends DefaultHandler implements Parser<DashManifest> {
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final String TAG = "MpdParser";
    private final String contentId;
    private final XmlPullParserFactory xmlParserFactory;

    private static final class RepresentationInfo {
        public final String baseUrl;
        public final ArrayList<SchemeData> drmSchemeDatas;
        public final Format format;
        public final ArrayList<SchemeValuePair> inbandEventStreams;
        public final SegmentBase segmentBase;

        public RepresentationInfo(Format format, String baseUrl, SegmentBase segmentBase, ArrayList<SchemeData> drmSchemeDatas, ArrayList<SchemeValuePair> inbandEventStreams) {
            this.format = format;
            this.baseUrl = baseUrl;
            this.segmentBase = segmentBase;
            this.drmSchemeDatas = drmSchemeDatas;
            this.inbandEventStreams = inbandEventStreams;
        }
    }

    public DashManifestParser() {
        this(null);
    }

    public DashManifestParser(String contentId) {
        this.contentId = contentId;
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser xpp = this.xmlParserFactory.newPullParser();
            xpp.setInput(inputStream, null);
            if (xpp.next() == 2 && "MPD".equals(xpp.getName())) {
                return parseMediaPresentationDescription(xpp, uri.toString());
            }
            throw new ParserException("inputStream does not contain a valid media presentation description");
        } catch (XmlPullParserException e) {
            throw new ParserException(e);
        }
    }

    protected DashManifest parseMediaPresentationDescription(XmlPullParser xpp, String baseUrl) throws XmlPullParserException, IOException {
        boolean dynamic;
        long minUpdateTimeMs;
        long timeShiftBufferDepthMs;
        long suggestedPresentationDelayMs;
        UtcTimingElement utcTiming;
        Uri location;
        List<Period> periods;
        long nextPeriodStartMs;
        boolean seenEarlyAccessPeriod;
        boolean seenFirstBaseUrl;
        Pair<Period, Long> periodWithDurationMs;
        Period period;
        long periodDurationMs;
        long availabilityStartTime = parseDateTime(xpp, "availabilityStartTime", -9223372036854775807L);
        long durationMs = parseDuration(xpp, "mediaPresentationDuration", -9223372036854775807L);
        long minBufferTimeMs = parseDuration(xpp, "minBufferTime", -9223372036854775807L);
        String typeString = xpp.getAttributeValue(null, "type");
        if (typeString != null) {
            if (typeString.equals("dynamic")) {
                dynamic = true;
                minUpdateTimeMs = dynamic ? parseDuration(xpp, "minimumUpdatePeriod", -9223372036854775807L) : -9223372036854775807L;
                timeShiftBufferDepthMs = dynamic ? parseDuration(xpp, "timeShiftBufferDepth", -9223372036854775807L) : -9223372036854775807L;
                suggestedPresentationDelayMs = dynamic ? parseDuration(xpp, "suggestedPresentationDelay", -9223372036854775807L) : -9223372036854775807L;
                utcTiming = null;
                location = null;
                periods = new ArrayList();
                nextPeriodStartMs = dynamic ? -9223372036854775807L : 0;
                seenEarlyAccessPeriod = false;
                seenFirstBaseUrl = false;
                do {
                    xpp.next();
                    if (XmlPullParserUtil.isStartTag(xpp, "BaseURL")) {
                        if (XmlPullParserUtil.isStartTag(xpp, "UTCTiming")) {
                            if (XmlPullParserUtil.isStartTag(xpp, HttpHeaders.LOCATION)) {
                                if (XmlPullParserUtil.isStartTag(xpp, "Period") && !seenEarlyAccessPeriod) {
                                    periodWithDurationMs = parsePeriod(xpp, baseUrl, nextPeriodStartMs);
                                    period = periodWithDurationMs.first;
                                    if (period.startMs == -9223372036854775807L) {
                                        periodDurationMs = ((Long) periodWithDurationMs.second).longValue();
                                        if (periodDurationMs != -9223372036854775807L) {
                                            nextPeriodStartMs = -9223372036854775807L;
                                        } else {
                                            nextPeriodStartMs = period.startMs + periodDurationMs;
                                        }
                                        periods.add(period);
                                    } else if (dynamic) {
                                        throw new ParserException("Unable to determine start of period " + periods.size());
                                    } else {
                                        seenEarlyAccessPeriod = true;
                                    }
                                }
                            } else {
                                location = Uri.parse(xpp.nextText());
                            }
                        } else {
                            utcTiming = parseUtcTiming(xpp);
                        }
                    } else if (!seenFirstBaseUrl) {
                        baseUrl = parseBaseUrl(xpp, baseUrl);
                        seenFirstBaseUrl = true;
                    }
                } while (!XmlPullParserUtil.isEndTag(xpp, "MPD"));
                if (durationMs == -9223372036854775807L) {
                    if (nextPeriodStartMs != -9223372036854775807L) {
                        durationMs = nextPeriodStartMs;
                    } else if (!dynamic) {
                        throw new ParserException("Unable to determine duration of static manifest.");
                    }
                }
                if (periods.isEmpty()) {
                    return buildMediaPresentationDescription(availabilityStartTime, durationMs, minBufferTimeMs, dynamic, minUpdateTimeMs, timeShiftBufferDepthMs, suggestedPresentationDelayMs, utcTiming, location, periods);
                }
                throw new ParserException("No periods found.");
            }
        }
        dynamic = false;
        if (dynamic) {
        }
        if (dynamic) {
        }
        if (dynamic) {
        }
        utcTiming = null;
        location = null;
        periods = new ArrayList();
        if (dynamic) {
        }
        seenEarlyAccessPeriod = false;
        seenFirstBaseUrl = false;
        do {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp, "BaseURL")) {
                if (XmlPullParserUtil.isStartTag(xpp, "UTCTiming")) {
                    if (XmlPullParserUtil.isStartTag(xpp, HttpHeaders.LOCATION)) {
                        periodWithDurationMs = parsePeriod(xpp, baseUrl, nextPeriodStartMs);
                        period = periodWithDurationMs.first;
                        if (period.startMs == -9223372036854775807L) {
                            periodDurationMs = ((Long) periodWithDurationMs.second).longValue();
                            if (periodDurationMs != -9223372036854775807L) {
                                nextPeriodStartMs = period.startMs + periodDurationMs;
                            } else {
                                nextPeriodStartMs = -9223372036854775807L;
                            }
                            periods.add(period);
                        } else if (dynamic) {
                            throw new ParserException("Unable to determine start of period " + periods.size());
                        } else {
                            seenEarlyAccessPeriod = true;
                        }
                    } else {
                        location = Uri.parse(xpp.nextText());
                    }
                } else {
                    utcTiming = parseUtcTiming(xpp);
                }
            } else if (seenFirstBaseUrl) {
                baseUrl = parseBaseUrl(xpp, baseUrl);
                seenFirstBaseUrl = true;
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "MPD"));
        if (durationMs == -9223372036854775807L) {
            if (nextPeriodStartMs != -9223372036854775807L) {
                durationMs = nextPeriodStartMs;
            } else if (dynamic) {
                throw new ParserException("Unable to determine duration of static manifest.");
            }
        }
        if (periods.isEmpty()) {
            return buildMediaPresentationDescription(availabilityStartTime, durationMs, minBufferTimeMs, dynamic, minUpdateTimeMs, timeShiftBufferDepthMs, suggestedPresentationDelayMs, utcTiming, location, periods);
        }
        throw new ParserException("No periods found.");
    }

    protected DashManifest buildMediaPresentationDescription(long availabilityStartTime, long durationMs, long minBufferTimeMs, boolean dynamic, long minUpdateTimeMs, long timeShiftBufferDepthMs, long suggestedPresentationDelayMs, UtcTimingElement utcTiming, Uri location, List<Period> periods) {
        return new DashManifest(availabilityStartTime, durationMs, minBufferTimeMs, dynamic, minUpdateTimeMs, timeShiftBufferDepthMs, suggestedPresentationDelayMs, utcTiming, location, periods);
    }

    protected UtcTimingElement parseUtcTiming(XmlPullParser xpp) {
        return buildUtcTimingElement(xpp.getAttributeValue(null, "schemeIdUri"), xpp.getAttributeValue(null, FirebaseAnalytics$Param.VALUE));
    }

    protected UtcTimingElement buildUtcTimingElement(String schemeIdUri, String value) {
        return new UtcTimingElement(schemeIdUri, value);
    }

    protected Pair<Period, Long> parsePeriod(XmlPullParser xpp, String baseUrl, long defaultStartMs) throws XmlPullParserException, IOException {
        String id = xpp.getAttributeValue(null, "id");
        long startMs = parseDuration(xpp, "start", defaultStartMs);
        long durationMs = parseDuration(xpp, "duration", -9223372036854775807L);
        SegmentBase segmentBase = null;
        List<AdaptationSet> adaptationSets = new ArrayList();
        boolean seenFirstBaseUrl = false;
        do {
            xpp.next();
            if (!XmlPullParserUtil.isStartTag(xpp, "BaseURL")) {
                if (XmlPullParserUtil.isStartTag(xpp, "AdaptationSet")) {
                    adaptationSets.add(parseAdaptationSet(xpp, baseUrl, segmentBase));
                } else {
                    if (XmlPullParserUtil.isStartTag(xpp, "SegmentBase")) {
                        segmentBase = parseSegmentBase(xpp, null);
                    } else {
                        if (XmlPullParserUtil.isStartTag(xpp, "SegmentList")) {
                            segmentBase = parseSegmentList(xpp, null);
                        } else {
                            if (XmlPullParserUtil.isStartTag(xpp, "SegmentTemplate")) {
                                segmentBase = parseSegmentTemplate(xpp, null);
                            }
                        }
                    }
                }
            } else if (!seenFirstBaseUrl) {
                baseUrl = parseBaseUrl(xpp, baseUrl);
                seenFirstBaseUrl = true;
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "Period"));
        return Pair.create(buildPeriod(id, startMs, adaptationSets), Long.valueOf(durationMs));
    }

    protected Period buildPeriod(String id, long startMs, List<AdaptationSet> adaptationSets) {
        return new Period(id, startMs, adaptationSets);
    }

    protected AdaptationSet parseAdaptationSet(XmlPullParser xpp, String baseUrl, SegmentBase segmentBase) throws XmlPullParserException, IOException {
        int id = parseInt(xpp, "id", -1);
        int contentType = parseContentType(xpp);
        String mimeType = xpp.getAttributeValue(null, "mimeType");
        String codecs = xpp.getAttributeValue(null, "codecs");
        int width = parseInt(xpp, "width", -1);
        int height = parseInt(xpp, "height", -1);
        float frameRate = parseFrameRate(xpp, -1.0f);
        int audioChannels = -1;
        int audioSamplingRate = parseInt(xpp, "audioSamplingRate", -1);
        String language = xpp.getAttributeValue(null, "lang");
        ArrayList<SchemeData> drmSchemeDatas = new ArrayList();
        ArrayList<SchemeValuePair> inbandEventStreams = new ArrayList();
        ArrayList<SchemeValuePair> accessibilityDescriptors = new ArrayList();
        List<RepresentationInfo> representationInfos = new ArrayList();
        int selectionFlags = 0;
        boolean seenFirstBaseUrl = false;
        do {
            xpp.next();
            if (!XmlPullParserUtil.isStartTag(xpp, "BaseURL")) {
                if (XmlPullParserUtil.isStartTag(xpp, "ContentProtection")) {
                    SchemeData contentProtection = parseContentProtection(xpp);
                    if (contentProtection != null) {
                        drmSchemeDatas.add(contentProtection);
                    }
                } else {
                    if (XmlPullParserUtil.isStartTag(xpp, "ContentComponent")) {
                        language = checkLanguageConsistency(language, xpp.getAttributeValue(null, "lang"));
                        contentType = checkContentTypeConsistency(contentType, parseContentType(xpp));
                    } else {
                        if (XmlPullParserUtil.isStartTag(xpp, "Role")) {
                            selectionFlags |= parseRole(xpp);
                        } else {
                            if (XmlPullParserUtil.isStartTag(xpp, "AudioChannelConfiguration")) {
                                audioChannels = parseAudioChannelConfiguration(xpp);
                            } else {
                                if (XmlPullParserUtil.isStartTag(xpp, "Accessibility")) {
                                    accessibilityDescriptors.add(parseAccessibility(xpp));
                                } else {
                                    if (XmlPullParserUtil.isStartTag(xpp, "Representation")) {
                                        RepresentationInfo representationInfo = parseRepresentation(xpp, baseUrl, mimeType, codecs, width, height, frameRate, audioChannels, audioSamplingRate, language, selectionFlags, accessibilityDescriptors, segmentBase);
                                        contentType = checkContentTypeConsistency(contentType, getContentType(representationInfo.format));
                                        representationInfos.add(representationInfo);
                                    } else {
                                        if (XmlPullParserUtil.isStartTag(xpp, "SegmentBase")) {
                                            segmentBase = parseSegmentBase(xpp, (SingleSegmentBase) segmentBase);
                                        } else {
                                            if (XmlPullParserUtil.isStartTag(xpp, "SegmentList")) {
                                                segmentBase = parseSegmentList(xpp, (SegmentList) segmentBase);
                                            } else {
                                                if (XmlPullParserUtil.isStartTag(xpp, "SegmentTemplate")) {
                                                    segmentBase = parseSegmentTemplate(xpp, (SegmentTemplate) segmentBase);
                                                } else {
                                                    if (XmlPullParserUtil.isStartTag(xpp, "InbandEventStream")) {
                                                        inbandEventStreams.add(parseInbandEventStream(xpp));
                                                    } else if (XmlPullParserUtil.isStartTag(xpp)) {
                                                        parseAdaptationSetChild(xpp);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (!seenFirstBaseUrl) {
                baseUrl = parseBaseUrl(xpp, baseUrl);
                seenFirstBaseUrl = true;
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "AdaptationSet"));
        List<Representation> arrayList = new ArrayList(representationInfos.size());
        for (int i = 0; i < representationInfos.size(); i++) {
            arrayList.add(buildRepresentation((RepresentationInfo) representationInfos.get(i), this.contentId, drmSchemeDatas, inbandEventStreams));
        }
        return buildAdaptationSet(id, contentType, arrayList, accessibilityDescriptors);
    }

    protected AdaptationSet buildAdaptationSet(int id, int contentType, List<Representation> representations, List<SchemeValuePair> accessibilityDescriptors) {
        return new AdaptationSet(id, contentType, representations, accessibilityDescriptors);
    }

    protected int parseContentType(XmlPullParser xpp) {
        String contentType = xpp.getAttributeValue(null, "contentType");
        if (TextUtils.isEmpty(contentType)) {
            return -1;
        }
        if ("audio".equals(contentType)) {
            return 1;
        }
        if ("video".equals(contentType)) {
            return 2;
        }
        if ("text".equals(contentType)) {
            return 3;
        }
        return -1;
    }

    protected int getContentType(Format format) {
        String sampleMimeType = format.sampleMimeType;
        if (TextUtils.isEmpty(sampleMimeType)) {
            return -1;
        }
        if (MimeTypes.isVideo(sampleMimeType)) {
            return 2;
        }
        if (MimeTypes.isAudio(sampleMimeType)) {
            return 1;
        }
        if (mimeTypeIsRawText(sampleMimeType)) {
            return 3;
        }
        return -1;
    }

    protected SchemeData parseContentProtection(XmlPullParser xpp) throws XmlPullParserException, IOException {
        boolean isPlayReady = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95".equals(xpp.getAttributeValue(null, "schemeIdUri"));
        byte[] data = null;
        UUID uuid = null;
        boolean requiresSecureDecoder = false;
        do {
            xpp.next();
            if (data == null && XmlPullParserUtil.isStartTag(xpp, "cenc:pssh") && xpp.next() == 4) {
                data = Base64.decode(xpp.getText(), 0);
                uuid = PsshAtomUtil.parseUuid(data);
                if (uuid == null) {
                    Log.w(TAG, "Skipping malformed cenc:pssh data");
                    data = null;
                }
            } else if (data == null && isPlayReady && XmlPullParserUtil.isStartTag(xpp, "mspr:pro") && xpp.next() == 4) {
                data = PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(xpp.getText(), 0));
                uuid = C.PLAYREADY_UUID;
            } else if (XmlPullParserUtil.isStartTag(xpp, "widevine:license")) {
                String robustnessLevel = xpp.getAttributeValue(null, "robustness_level");
                requiresSecureDecoder = robustnessLevel != null && robustnessLevel.startsWith("HW");
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "ContentProtection"));
        if (data != null) {
            return new SchemeData(uuid, "video/mp4", data, requiresSecureDecoder);
        }
        return null;
    }

    protected SchemeValuePair parseInbandEventStream(XmlPullParser xpp) throws XmlPullParserException, IOException {
        return parseSchemeValuePair(xpp, "InbandEventStream");
    }

    protected SchemeValuePair parseAccessibility(XmlPullParser xpp) throws XmlPullParserException, IOException {
        return parseSchemeValuePair(xpp, "Accessibility");
    }

    protected int parseRole(XmlPullParser xpp) throws XmlPullParserException, IOException {
        String schemeIdUri = parseString(xpp, "schemeIdUri", null);
        String value = parseString(xpp, FirebaseAnalytics$Param.VALUE, null);
        do {
            xpp.next();
        } while (!XmlPullParserUtil.isEndTag(xpp, "Role"));
        return ("urn:mpeg:dash:role:2011".equals(schemeIdUri) && "main".equals(value)) ? 1 : 0;
    }

    protected void parseAdaptationSetChild(XmlPullParser xpp) throws XmlPullParserException, IOException {
    }

    protected RepresentationInfo parseRepresentation(XmlPullParser xpp, String baseUrl, String adaptationSetMimeType, String adaptationSetCodecs, int adaptationSetWidth, int adaptationSetHeight, float adaptationSetFrameRate, int adaptationSetAudioChannels, int adaptationSetAudioSamplingRate, String adaptationSetLanguage, int adaptationSetSelectionFlags, List<SchemeValuePair> adaptationSetAccessibilityDescriptors, SegmentBase segmentBase) throws XmlPullParserException, IOException {
        String id = xpp.getAttributeValue(null, "id");
        int bandwidth = parseInt(xpp, "bandwidth", -1);
        String mimeType = parseString(xpp, "mimeType", adaptationSetMimeType);
        String codecs = parseString(xpp, "codecs", adaptationSetCodecs);
        int width = parseInt(xpp, "width", adaptationSetWidth);
        int height = parseInt(xpp, "height", adaptationSetHeight);
        float frameRate = parseFrameRate(xpp, adaptationSetFrameRate);
        int audioChannels = adaptationSetAudioChannels;
        int audioSamplingRate = parseInt(xpp, "audioSamplingRate", adaptationSetAudioSamplingRate);
        ArrayList<SchemeData> drmSchemeDatas = new ArrayList();
        ArrayList<SchemeValuePair> inbandEventStreams = new ArrayList();
        boolean seenFirstBaseUrl = false;
        do {
            xpp.next();
            if (!XmlPullParserUtil.isStartTag(xpp, "BaseURL")) {
                if (XmlPullParserUtil.isStartTag(xpp, "AudioChannelConfiguration")) {
                    audioChannels = parseAudioChannelConfiguration(xpp);
                } else {
                    if (XmlPullParserUtil.isStartTag(xpp, "SegmentBase")) {
                        segmentBase = parseSegmentBase(xpp, (SingleSegmentBase) segmentBase);
                    } else {
                        if (XmlPullParserUtil.isStartTag(xpp, "SegmentList")) {
                            segmentBase = parseSegmentList(xpp, (SegmentList) segmentBase);
                        } else {
                            if (XmlPullParserUtil.isStartTag(xpp, "SegmentTemplate")) {
                                segmentBase = parseSegmentTemplate(xpp, (SegmentTemplate) segmentBase);
                            } else {
                                if (XmlPullParserUtil.isStartTag(xpp, "ContentProtection")) {
                                    SchemeData contentProtection = parseContentProtection(xpp);
                                    if (contentProtection != null) {
                                        drmSchemeDatas.add(contentProtection);
                                    }
                                } else {
                                    if (XmlPullParserUtil.isStartTag(xpp, "InbandEventStream")) {
                                        inbandEventStreams.add(parseInbandEventStream(xpp));
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (!seenFirstBaseUrl) {
                baseUrl = parseBaseUrl(xpp, baseUrl);
                seenFirstBaseUrl = true;
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "Representation"));
        Format format = buildFormat(id, mimeType, width, height, frameRate, audioChannels, audioSamplingRate, bandwidth, adaptationSetLanguage, adaptationSetSelectionFlags, adaptationSetAccessibilityDescriptors, codecs);
        if (segmentBase == null) {
            segmentBase = new SingleSegmentBase();
        }
        return new RepresentationInfo(format, baseUrl, segmentBase, drmSchemeDatas, inbandEventStreams);
    }

    protected Format buildFormat(String id, String containerMimeType, int width, int height, float frameRate, int audioChannels, int audioSamplingRate, int bitrate, String language, int selectionFlags, List<SchemeValuePair> accessibilityDescriptors, String codecs) {
        String sampleMimeType = getSampleMimeType(containerMimeType, codecs);
        if (sampleMimeType != null) {
            if (MimeTypes.isVideo(sampleMimeType)) {
                return Format.createVideoContainerFormat(id, containerMimeType, sampleMimeType, codecs, bitrate, width, height, frameRate, null, selectionFlags);
            }
            if (MimeTypes.isAudio(sampleMimeType)) {
                return Format.createAudioContainerFormat(id, containerMimeType, sampleMimeType, codecs, bitrate, audioChannels, audioSamplingRate, null, selectionFlags, language);
            }
            if (mimeTypeIsRawText(sampleMimeType)) {
                int accessibilityChannel;
                if ("application/cea-608".equals(sampleMimeType)) {
                    accessibilityChannel = parseCea608AccessibilityChannel(accessibilityDescriptors);
                } else if ("application/cea-708".equals(sampleMimeType)) {
                    accessibilityChannel = parseCea708AccessibilityChannel(accessibilityDescriptors);
                } else {
                    accessibilityChannel = -1;
                }
                return Format.createTextContainerFormat(id, containerMimeType, sampleMimeType, codecs, bitrate, selectionFlags, language, accessibilityChannel);
            }
        }
        return Format.createContainerFormat(id, containerMimeType, sampleMimeType, codecs, bitrate, selectionFlags, language);
    }

    protected Representation buildRepresentation(RepresentationInfo representationInfo, String contentId, ArrayList<SchemeData> extraDrmSchemeDatas, ArrayList<SchemeValuePair> extraInbandEventStreams) {
        Format format = representationInfo.format;
        ArrayList<SchemeData> drmSchemeDatas = representationInfo.drmSchemeDatas;
        drmSchemeDatas.addAll(extraDrmSchemeDatas);
        if (!drmSchemeDatas.isEmpty()) {
            format = format.copyWithDrmInitData(new DrmInitData(drmSchemeDatas));
        }
        ArrayList<SchemeValuePair> inbandEventStremas = representationInfo.inbandEventStreams;
        inbandEventStremas.addAll(extraInbandEventStreams);
        return Representation.newInstance(contentId, -1, format, representationInfo.baseUrl, representationInfo.segmentBase, inbandEventStremas);
    }

    protected SingleSegmentBase parseSegmentBase(XmlPullParser xpp, SingleSegmentBase parent) throws XmlPullParserException, IOException {
        long timescale = parseLong(xpp, "timescale", parent != null ? parent.timescale : 1);
        long presentationTimeOffset = parseLong(xpp, "presentationTimeOffset", parent != null ? parent.presentationTimeOffset : 0);
        long indexStart = parent != null ? parent.indexStart : 0;
        long indexLength = parent != null ? parent.indexLength : 0;
        String indexRangeText = xpp.getAttributeValue(null, "indexRange");
        if (indexRangeText != null) {
            String[] indexRange = indexRangeText.split("-");
            indexStart = Long.parseLong(indexRange[0]);
            indexLength = (Long.parseLong(indexRange[1]) - indexStart) + 1;
        }
        RangedUri initialization = parent != null ? parent.initialization : null;
        do {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp, "Initialization")) {
                initialization = parseInitialization(xpp);
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "SegmentBase"));
        return buildSingleSegmentBase(initialization, timescale, presentationTimeOffset, indexStart, indexLength);
    }

    protected SingleSegmentBase buildSingleSegmentBase(RangedUri initialization, long timescale, long presentationTimeOffset, long indexStart, long indexLength) {
        return new SingleSegmentBase(initialization, timescale, presentationTimeOffset, indexStart, indexLength);
    }

    protected SegmentList parseSegmentList(XmlPullParser xpp, SegmentList parent) throws XmlPullParserException, IOException {
        long timescale = parseLong(xpp, "timescale", parent != null ? parent.timescale : 1);
        long presentationTimeOffset = parseLong(xpp, "presentationTimeOffset", parent != null ? parent.presentationTimeOffset : 0);
        long duration = parseLong(xpp, "duration", parent != null ? parent.duration : -9223372036854775807L);
        int startNumber = parseInt(xpp, "startNumber", parent != null ? parent.startNumber : 1);
        RangedUri initialization = null;
        List<SegmentTimelineElement> timeline = null;
        List<RangedUri> segments = null;
        do {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp, "Initialization")) {
                initialization = parseInitialization(xpp);
            } else {
                if (XmlPullParserUtil.isStartTag(xpp, "SegmentTimeline")) {
                    timeline = parseSegmentTimeline(xpp);
                } else {
                    if (XmlPullParserUtil.isStartTag(xpp, "SegmentURL")) {
                        if (segments == null) {
                            segments = new ArrayList();
                        }
                        segments.add(parseSegmentUrl(xpp));
                    }
                }
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "SegmentList"));
        if (parent != null) {
            if (initialization == null) {
                initialization = parent.initialization;
            }
            if (timeline == null) {
                timeline = parent.segmentTimeline;
            }
            if (segments == null) {
                segments = parent.mediaSegments;
            }
        }
        return buildSegmentList(initialization, timescale, presentationTimeOffset, startNumber, duration, timeline, segments);
    }

    protected SegmentList buildSegmentList(RangedUri initialization, long timescale, long presentationTimeOffset, int startNumber, long duration, List<SegmentTimelineElement> timeline, List<RangedUri> segments) {
        return new SegmentList(initialization, timescale, presentationTimeOffset, startNumber, duration, timeline, segments);
    }

    protected SegmentTemplate parseSegmentTemplate(XmlPullParser xpp, SegmentTemplate parent) throws XmlPullParserException, IOException {
        long timescale = parseLong(xpp, "timescale", parent != null ? parent.timescale : 1);
        long presentationTimeOffset = parseLong(xpp, "presentationTimeOffset", parent != null ? parent.presentationTimeOffset : 0);
        long duration = parseLong(xpp, "duration", parent != null ? parent.duration : -9223372036854775807L);
        int startNumber = parseInt(xpp, "startNumber", parent != null ? parent.startNumber : 1);
        UrlTemplate mediaTemplate = parseUrlTemplate(xpp, "media", parent != null ? parent.mediaTemplate : null);
        UrlTemplate initializationTemplate = parseUrlTemplate(xpp, JoinPoint.INITIALIZATION, parent != null ? parent.initializationTemplate : null);
        RangedUri initialization = null;
        List<SegmentTimelineElement> timeline = null;
        do {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp, "Initialization")) {
                initialization = parseInitialization(xpp);
            } else {
                if (XmlPullParserUtil.isStartTag(xpp, "SegmentTimeline")) {
                    timeline = parseSegmentTimeline(xpp);
                }
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "SegmentTemplate"));
        if (parent != null) {
            if (initialization == null) {
                initialization = parent.initialization;
            }
            if (timeline == null) {
                timeline = parent.segmentTimeline;
            }
        }
        return buildSegmentTemplate(initialization, timescale, presentationTimeOffset, startNumber, duration, timeline, initializationTemplate, mediaTemplate);
    }

    protected SegmentTemplate buildSegmentTemplate(RangedUri initialization, long timescale, long presentationTimeOffset, int startNumber, long duration, List<SegmentTimelineElement> timeline, UrlTemplate initializationTemplate, UrlTemplate mediaTemplate) {
        return new SegmentTemplate(initialization, timescale, presentationTimeOffset, startNumber, duration, timeline, initializationTemplate, mediaTemplate);
    }

    protected List<SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xpp) throws XmlPullParserException, IOException {
        List<SegmentTimelineElement> segmentTimeline = new ArrayList();
        long elapsedTime = 0;
        do {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp, "S")) {
                elapsedTime = parseLong(xpp, "t", elapsedTime);
                long duration = parseLong(xpp, "d", -9223372036854775807L);
                int count = parseInt(xpp, "r", 0) + 1;
                for (int i = 0; i < count; i++) {
                    segmentTimeline.add(buildSegmentTimelineElement(elapsedTime, duration));
                    elapsedTime += duration;
                }
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "SegmentTimeline"));
        return segmentTimeline;
    }

    protected SegmentTimelineElement buildSegmentTimelineElement(long elapsedTime, long duration) {
        return new SegmentTimelineElement(elapsedTime, duration);
    }

    protected UrlTemplate parseUrlTemplate(XmlPullParser xpp, String name, UrlTemplate defaultValue) {
        String valueString = xpp.getAttributeValue(null, name);
        if (valueString != null) {
            return UrlTemplate.compile(valueString);
        }
        return defaultValue;
    }

    protected RangedUri parseInitialization(XmlPullParser xpp) {
        return parseRangedUrl(xpp, "sourceURL", "range");
    }

    protected RangedUri parseSegmentUrl(XmlPullParser xpp) {
        return parseRangedUrl(xpp, "media", "mediaRange");
    }

    protected RangedUri parseRangedUrl(XmlPullParser xpp, String urlAttribute, String rangeAttribute) {
        String urlText = xpp.getAttributeValue(null, urlAttribute);
        long rangeStart = 0;
        long rangeLength = -1;
        String rangeText = xpp.getAttributeValue(null, rangeAttribute);
        if (rangeText != null) {
            String[] rangeTextArray = rangeText.split("-");
            rangeStart = Long.parseLong(rangeTextArray[0]);
            if (rangeTextArray.length == 2) {
                rangeLength = (Long.parseLong(rangeTextArray[1]) - rangeStart) + 1;
            }
        }
        return buildRangedUri(urlText, rangeStart, rangeLength);
    }

    protected RangedUri buildRangedUri(String urlText, long rangeStart, long rangeLength) {
        return new RangedUri(urlText, rangeStart, rangeLength);
    }

    protected int parseAudioChannelConfiguration(XmlPullParser xpp) throws XmlPullParserException, IOException {
        int audioChannels = -1;
        if ("urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(parseString(xpp, "schemeIdUri", null))) {
            audioChannels = parseInt(xpp, FirebaseAnalytics$Param.VALUE, -1);
        }
        do {
            xpp.next();
        } while (!XmlPullParserUtil.isEndTag(xpp, "AudioChannelConfiguration"));
        return audioChannels;
    }

    private static String getSampleMimeType(String containerMimeType, String codecs) {
        if (MimeTypes.isAudio(containerMimeType)) {
            return MimeTypes.getAudioMediaMimeType(codecs);
        }
        if (MimeTypes.isVideo(containerMimeType)) {
            return MimeTypes.getVideoMediaMimeType(codecs);
        }
        if (mimeTypeIsRawText(containerMimeType)) {
            return containerMimeType;
        }
        if ("application/mp4".equals(containerMimeType)) {
            if (XMLSubtitleSampleEntry.TYPE.equals(codecs)) {
                return "application/ttml+xml";
            }
            if (WebVTTSampleEntry.TYPE.equals(codecs)) {
                return "application/x-mp4-vtt";
            }
        } else if ("application/x-rawcc".equals(containerMimeType)) {
            if (codecs != null) {
                if (codecs.contains("cea708")) {
                    return "application/cea-708";
                }
                if (codecs.contains("eia608") || codecs.contains("cea608")) {
                    return "application/cea-608";
                }
            }
            return null;
        }
        return null;
    }

    private static boolean mimeTypeIsRawText(String mimeType) {
        return MimeTypes.isText(mimeType) || "application/ttml+xml".equals(mimeType) || "application/x-mp4-vtt".equals(mimeType) || "application/cea-708".equals(mimeType) || "application/cea-608".equals(mimeType);
    }

    private static String checkLanguageConsistency(String firstLanguage, String secondLanguage) {
        if (firstLanguage == null) {
            return secondLanguage;
        }
        if (secondLanguage == null) {
            return firstLanguage;
        }
        Assertions.checkState(firstLanguage.equals(secondLanguage));
        return firstLanguage;
    }

    private static int checkContentTypeConsistency(int firstType, int secondType) {
        if (firstType == -1) {
            return secondType;
        }
        if (secondType == -1) {
            return firstType;
        }
        Assertions.checkState(firstType == secondType);
        return firstType;
    }

    protected static SchemeValuePair parseSchemeValuePair(XmlPullParser xpp, String tag) throws XmlPullParserException, IOException {
        String schemeIdUri = parseString(xpp, "schemeIdUri", null);
        String value = parseString(xpp, FirebaseAnalytics$Param.VALUE, null);
        do {
            xpp.next();
        } while (!XmlPullParserUtil.isEndTag(xpp, tag));
        return new SchemeValuePair(schemeIdUri, value);
    }

    protected static int parseCea608AccessibilityChannel(List<SchemeValuePair> accessibilityDescriptors) {
        for (int i = 0; i < accessibilityDescriptors.size(); i++) {
            SchemeValuePair descriptor = (SchemeValuePair) accessibilityDescriptors.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher accessibilityValueMatcher = CEA_608_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (accessibilityValueMatcher.matches()) {
                    return Integer.parseInt(accessibilityValueMatcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-608 channel number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static int parseCea708AccessibilityChannel(List<SchemeValuePair> accessibilityDescriptors) {
        for (int i = 0; i < accessibilityDescriptors.size(); i++) {
            SchemeValuePair descriptor = (SchemeValuePair) accessibilityDescriptors.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher accessibilityValueMatcher = CEA_708_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (accessibilityValueMatcher.matches()) {
                    return Integer.parseInt(accessibilityValueMatcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-708 service block number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static float parseFrameRate(XmlPullParser xpp, float defaultValue) {
        float frameRate = defaultValue;
        String frameRateAttribute = xpp.getAttributeValue(null, "frameRate");
        if (frameRateAttribute == null) {
            return frameRate;
        }
        Matcher frameRateMatcher = FRAME_RATE_PATTERN.matcher(frameRateAttribute);
        if (!frameRateMatcher.matches()) {
            return frameRate;
        }
        int numerator = Integer.parseInt(frameRateMatcher.group(1));
        String denominatorString = frameRateMatcher.group(2);
        if (TextUtils.isEmpty(denominatorString)) {
            return (float) numerator;
        }
        return ((float) numerator) / ((float) Integer.parseInt(denominatorString));
    }

    protected static long parseDuration(XmlPullParser xpp, String name, long defaultValue) {
        String value = xpp.getAttributeValue(null, name);
        return value == null ? defaultValue : Util.parseXsDuration(value);
    }

    protected static long parseDateTime(XmlPullParser xpp, String name, long defaultValue) throws ParserException {
        String value = xpp.getAttributeValue(null, name);
        return value == null ? defaultValue : Util.parseXsDateTime(value);
    }

    protected static String parseBaseUrl(XmlPullParser xpp, String parentBaseUrl) throws XmlPullParserException, IOException {
        xpp.next();
        return UriUtil.resolve(parentBaseUrl, xpp.getText());
    }

    protected static int parseInt(XmlPullParser xpp, String name, int defaultValue) {
        String value = xpp.getAttributeValue(null, name);
        return value == null ? defaultValue : Integer.parseInt(value);
    }

    protected static long parseLong(XmlPullParser xpp, String name, long defaultValue) {
        String value = xpp.getAttributeValue(null, name);
        return value == null ? defaultValue : Long.parseLong(value);
    }

    protected static String parseString(XmlPullParser xpp, String name, String defaultValue) {
        String value = xpp.getAttributeValue(null, name);
        return value == null ? defaultValue : value;
    }
}
