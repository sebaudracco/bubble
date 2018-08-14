package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.util.DeviceUtils$ForceOrientation;
import com.mopub.mobileads.util.XmlUtils;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class VastXmlManager {
    private static final String AD = "Ad";
    private static final String CUSTOM_CLOSE_ICON = "MoPubCloseIcon";
    private static final String CUSTOM_CTA_TEXT = "MoPubCtaText";
    private static final String CUSTOM_FORCE_ORIENTATION = "MoPubForceOrientation";
    private static final String CUSTOM_SKIP_TEXT = "MoPubSkipText";
    private static final String ERROR = "Error";
    private static final int MAX_CTA_TEXT_LENGTH = 15;
    private static final int MAX_SKIP_TEXT_LENGTH = 8;
    private static final String MP_IMPRESSION_TRACKER = "MP_TRACKING_URL";
    private static final String ROOT_TAG = "MPMoVideoXMLDocRoot";
    private static final String ROOT_TAG_CLOSE = "</MPMoVideoXMLDocRoot>";
    private static final String ROOT_TAG_OPEN = "<MPMoVideoXMLDocRoot>";
    @Nullable
    private Document mVastDoc;

    VastXmlManager() {
    }

    void parseVastXml(@NonNull String xmlString) throws ParserConfigurationException, IOException, SAXException {
        Preconditions.checkNotNull(xmlString, "xmlString cannot be null");
        String documentString = ROOT_TAG_OPEN + xmlString.replaceFirst("<\\?.*\\?>", "") + ROOT_TAG_CLOSE;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setCoalescing(true);
        this.mVastDoc = documentBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(documentString)));
    }

    @NonNull
    List<VastAdXmlManager> getAdXmlManagers() {
        List<VastAdXmlManager> vastAdXmlManagers = new ArrayList();
        if (this.mVastDoc != null) {
            NodeList nodes = this.mVastDoc.getElementsByTagName(AD);
            for (int i = 0; i < nodes.getLength(); i++) {
                vastAdXmlManagers.add(new VastAdXmlManager(nodes.item(i)));
            }
        }
        return vastAdXmlManagers;
    }

    @Nullable
    VastTracker getErrorTracker() {
        if (this.mVastDoc == null) {
            return null;
        }
        String errorTracker = XmlUtils.getFirstMatchingStringData(this.mVastDoc, ERROR);
        if (TextUtils.isEmpty(errorTracker)) {
            return null;
        }
        return new VastTracker(errorTracker);
    }

    @NonNull
    List<VastTracker> getMoPubImpressionTrackers() {
        List<String> trackers = XmlUtils.getStringDataAsList(this.mVastDoc, MP_IMPRESSION_TRACKER);
        List<VastTracker> vastTrackers = new ArrayList(trackers.size());
        for (String tracker : trackers) {
            vastTrackers.add(new VastTracker(tracker));
        }
        return vastTrackers;
    }

    @Nullable
    String getCustomCtaText() {
        String customCtaText = XmlUtils.getFirstMatchingStringData(this.mVastDoc, CUSTOM_CTA_TEXT);
        return (customCtaText == null || customCtaText.length() > 15) ? null : customCtaText;
    }

    @Nullable
    String getCustomSkipText() {
        String customSkipText = XmlUtils.getFirstMatchingStringData(this.mVastDoc, CUSTOM_SKIP_TEXT);
        return (customSkipText == null || customSkipText.length() > 8) ? null : customSkipText;
    }

    @Nullable
    String getCustomCloseIconUrl() {
        return XmlUtils.getFirstMatchingStringData(this.mVastDoc, CUSTOM_CLOSE_ICON);
    }

    @NonNull
    DeviceUtils$ForceOrientation getCustomForceOrientation() {
        return DeviceUtils$ForceOrientation.getForceOrientation(XmlUtils.getFirstMatchingStringData(this.mVastDoc, CUSTOM_FORCE_ORIENTATION));
    }
}
