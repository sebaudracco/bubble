package com.appsgeyser.sdk.vast.model;

import android.support.v4.app.NotificationCompat;
import com.appsgeyser.sdk.utils.vast.VASTLog;
import com.appsgeyser.sdk.utils.vast.XmlTools;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VASTModel implements Serializable {
    private static String TAG = "VASTModel";
    private static final String combinedTrackingXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/Linear/TrackingEvents/Tracking|/VASTS/VAST/Ad/InLine/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking|/VASTS/VAST/Ad/Wrapper/Creatives/Creative/Linear/TrackingEvents/Tracking|/VASTS/VAST/Ad/Wrapper/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking";
    private static final String durationXPATH = "//Duration";
    private static final String errorUrlXPATH = "//Error";
    private static final String impressionXPATH = "//Impression";
    private static final String inlineLinearTrackingXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/Linear/TrackingEvents/Tracking";
    private static final String inlineNonLinearTrackingXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking";
    private static final String mediaFileXPATH = "//MediaFile";
    private static final long serialVersionUID = 4318368258447283733L;
    private static final String videoClicksXPATH = "//VideoClicks";
    private static final String wrapperLinearTrackingXPATH = "/VASTS/VAST/Ad/Wrapper/Creatives/Creative/Linear/TrackingEvents/Tracking";
    private static final String wrapperNonLinearTrackingXPATH = "/VASTS/VAST/Ad/Wrapper/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking";
    private String pickedMediaFileURL = null;
    private transient Document vastsDocument;

    public VASTModel(Document vasts) {
        this.vastsDocument = vasts;
    }

    public Document getVastsDocument() {
        return this.vastsDocument;
    }

    public HashMap<TRACKING_EVENTS_TYPE, List<String>> getTrackingUrls() {
        VASTLog.m2412d(TAG, "getTrackingUrls");
        HashMap<TRACKING_EVENTS_TYPE, List<String>> trackings = new HashMap();
        NodeList nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(combinedTrackingXPATH, this.vastsDocument, XPathConstants.NODESET);
        if (nodes == null) {
            return trackings;
        }
        int i = 0;
        while (i < nodes.getLength()) {
            Node node = nodes.item(i);
            String eventName = node.getAttributes().getNamedItem(NotificationCompat.CATEGORY_EVENT).getNodeValue();
            try {
                TRACKING_EVENTS_TYPE key = TRACKING_EVENTS_TYPE.valueOf(eventName);
            } catch (IllegalArgumentException e) {
                VASTLog.m2417w(TAG, "Event:" + eventName + " is not valid. Skipping it.");
            }
            try {
                String trackingURL = XmlTools.getElementValue(node);
                if (trackings.containsKey(key)) {
                    ((List) trackings.get(key)).add(trackingURL);
                } else {
                    List<String> tracking = new ArrayList();
                    tracking.add(trackingURL);
                    trackings.put(key, tracking);
                }
                i++;
            } catch (Exception e2) {
                VASTLog.m2414e(TAG, e2.getMessage(), e2);
                return null;
            }
        }
        return trackings;
    }

    public List<VASTMediaFile> getMediaFiles() {
        VASTLog.m2412d(TAG, "getMediaFiles");
        ArrayList<VASTMediaFile> mediaFiles = new ArrayList();
        try {
            NodeList nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(mediaFileXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodes == null) {
                return mediaFiles;
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                String str;
                BigInteger bigInteger;
                Boolean bool;
                VASTMediaFile mediaFile = new VASTMediaFile();
                Node node = nodes.item(i);
                NamedNodeMap attributes = node.getAttributes();
                Node attributeNode = attributes.getNamedItem("apiFramework");
                if (attributeNode == null) {
                    str = null;
                } else {
                    str = attributeNode.getNodeValue();
                }
                mediaFile.setApiFramework(str);
                attributeNode = attributes.getNamedItem("bitrate");
                if (attributeNode == null) {
                    bigInteger = null;
                } else {
                    bigInteger = new BigInteger(attributeNode.getNodeValue());
                }
                mediaFile.setBitrate(bigInteger);
                attributeNode = attributes.getNamedItem("delivery");
                if (attributeNode == null) {
                    str = null;
                } else {
                    str = attributeNode.getNodeValue();
                }
                mediaFile.setDelivery(str);
                attributeNode = attributes.getNamedItem("height");
                if (attributeNode == null) {
                    bigInteger = null;
                } else {
                    bigInteger = new BigInteger(attributeNode.getNodeValue());
                }
                mediaFile.setHeight(bigInteger);
                attributeNode = attributes.getNamedItem("id");
                if (attributeNode == null) {
                    str = null;
                } else {
                    str = attributeNode.getNodeValue();
                }
                mediaFile.setId(str);
                attributeNode = attributes.getNamedItem("maintainAspectRatio");
                if (attributeNode == null) {
                    bool = null;
                } else {
                    bool = Boolean.valueOf(attributeNode.getNodeValue());
                }
                mediaFile.setMaintainAspectRatio(bool);
                attributeNode = attributes.getNamedItem("scalable");
                if (attributeNode == null) {
                    bool = null;
                } else {
                    bool = Boolean.valueOf(attributeNode.getNodeValue());
                }
                mediaFile.setScalable(bool);
                attributeNode = attributes.getNamedItem("type");
                if (attributeNode == null) {
                    str = null;
                } else {
                    str = attributeNode.getNodeValue();
                }
                mediaFile.setType(str);
                attributeNode = attributes.getNamedItem("width");
                if (attributeNode == null) {
                    bigInteger = null;
                } else {
                    bigInteger = new BigInteger(attributeNode.getNodeValue());
                }
                mediaFile.setWidth(bigInteger);
                mediaFile.setValue(XmlTools.getElementValue(node));
                mediaFiles.add(mediaFile);
            }
            return mediaFiles;
        } catch (Exception e) {
            VASTLog.m2414e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public String getDuration() {
        VASTLog.m2412d(TAG, "getDuration");
        String duration = null;
        try {
            NodeList nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(durationXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodes != null) {
                for (int i = 0; i < nodes.getLength(); i++) {
                    duration = XmlTools.getElementValue(nodes.item(i));
                }
            }
            return duration;
        } catch (Exception e) {
            VASTLog.m2414e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public VideoClicks getVideoClicks() {
        VASTLog.m2412d(TAG, "getVideoClicks");
        VideoClicks videoClicks = new VideoClicks();
        try {
            NodeList nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(videoClicksXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodes == null) {
                return videoClicks;
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                NodeList childNodes = nodes.item(i).getChildNodes();
                for (int childIndex = 0; childIndex < childNodes.getLength(); childIndex++) {
                    Node child = childNodes.item(childIndex);
                    String nodeName = child.getNodeName();
                    if (nodeName.equalsIgnoreCase("ClickTracking")) {
                        videoClicks.getClickTracking().add(XmlTools.getElementValue(child));
                    } else if (nodeName.equalsIgnoreCase("ClickThrough")) {
                        videoClicks.setClickThrough(XmlTools.getElementValue(child));
                    } else if (nodeName.equalsIgnoreCase("CustomClick")) {
                        videoClicks.getCustomClick().add(XmlTools.getElementValue(child));
                    }
                }
            }
            return videoClicks;
        } catch (Exception e) {
            VASTLog.m2414e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public List<String> getImpressions() {
        VASTLog.m2412d(TAG, "getImpressions");
        return getListFromXPath(impressionXPATH);
    }

    public List<String> getErrorUrl() {
        VASTLog.m2412d(TAG, "getErrorUrl");
        return getListFromXPath(errorUrlXPATH);
    }

    private List<String> getListFromXPath(String xPath) {
        VASTLog.m2412d(TAG, "getListFromXPath");
        ArrayList<String> list = new ArrayList();
        try {
            NodeList nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(xPath, this.vastsDocument, XPathConstants.NODESET);
            if (nodes == null) {
                return list;
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                list.add(XmlTools.getElementValue(nodes.item(i)));
            }
            return list;
        } catch (Exception e) {
            VASTLog.m2414e(TAG, e.getMessage(), e);
            return null;
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        VASTLog.m2412d(TAG, "writeObject: about to write");
        oos.defaultWriteObject();
        oos.writeObject(XmlTools.xmlDocumentToString(this.vastsDocument));
        VASTLog.m2412d(TAG, "done writing");
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        VASTLog.m2412d(TAG, "readObject: about to read");
        ois.defaultReadObject();
        String vastString = (String) ois.readObject();
        VASTLog.m2412d(TAG, "vastString data is:\n" + vastString + "\n");
        this.vastsDocument = XmlTools.stringToDocument(vastString);
        VASTLog.m2412d(TAG, "done reading");
    }

    public String getPickedMediaFileURL() {
        return this.pickedMediaFileURL;
    }

    public void setPickedMediaFileURL(String pickedMediaFileURL) {
        this.pickedMediaFileURL = pickedMediaFileURL;
    }
}
