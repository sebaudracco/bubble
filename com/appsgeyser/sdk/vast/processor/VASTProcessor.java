package com.appsgeyser.sdk.vast.processor;

import com.appsgeyser.sdk.utils.vast.VASTLog;
import com.appsgeyser.sdk.utils.vast.XmlTools;
import com.appsgeyser.sdk.utils.vast.XmlValidation;
import com.appsgeyser.sdk.vast.model.VASTModel;
import com.appsgeyser.sdk.vast.model.VAST_DOC_ELEMENTS;
import com.bgjd.ici.p025b.C1408j.C1403a;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public final class VASTProcessor {
    private static final boolean IS_VALIDATION_ON = false;
    private static final int MAX_VAST_LEVELS = 5;
    private static final String TAG = "VASTProcessor";
    private VASTMediaPicker mediaPicker;
    private StringBuilder mergedVastDocs = new StringBuilder(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    private VASTModel vastModel;

    public VASTProcessor(VASTMediaPicker mediaPicker) {
        this.mediaPicker = mediaPicker;
    }

    public VASTModel getModel() {
        return this.vastModel;
    }

    public int process(String xmlData) {
        VASTLog.m2412d(TAG, C1403a.f2089s);
        this.vastModel = null;
        try {
            InputStream is = new ByteArrayInputStream(xmlData.getBytes(Charset.defaultCharset().name()));
            int error = processUri(is, 0);
            try {
                is.close();
            } catch (IOException e) {
            }
            InputStream inputStream;
            if (error != 0) {
                inputStream = is;
                return error;
            }
            Document mainDoc = wrapMergedVastDocWithVasts();
            this.vastModel = new VASTModel(mainDoc);
            if (mainDoc == null) {
                inputStream = is;
                return 3;
            } else if (VASTModelPostValidator.validate(this.vastModel, this.mediaPicker)) {
                return 0;
            } else {
                inputStream = is;
                return 5;
            }
        } catch (UnsupportedEncodingException e2) {
            VASTLog.m2414e(TAG, e2.getMessage(), e2);
            return 3;
        }
    }

    private Document wrapMergedVastDocWithVasts() {
        VASTLog.m2412d(TAG, "wrapmergedVastDocWithVasts");
        this.mergedVastDocs.insert(0, "<VASTS>");
        this.mergedVastDocs.append("</VASTS>");
        String merged = this.mergedVastDocs.toString();
        VASTLog.m2416v(TAG, "Merged VAST doc:\n" + merged);
        return XmlTools.stringToDocument(merged);
    }

    private int processUri(InputStream is, int depth) {
        VASTLog.m2412d(TAG, "processUri");
        if (depth >= 5) {
            VASTLog.m2413e(TAG, "VAST wrapping exceeded max limit of 5.");
            return 6;
        }
        Document doc = createDoc(is);
        if (doc == null) {
            return 3;
        }
        merge(doc);
        NodeList uriToNextDoc = doc.getElementsByTagName(VAST_DOC_ELEMENTS.vastAdTagURI.getValue());
        if (uriToNextDoc == null || uriToNextDoc.getLength() == 0) {
            return 0;
        }
        VASTLog.m2412d(TAG, "Doc is a wrapper. ");
        String nextUri = XmlTools.getElementValue(uriToNextDoc.item(0));
        VASTLog.m2412d(TAG, "Wrapper URL: " + nextUri);
        try {
            InputStream nextInputStream = new URL(nextUri).openStream();
            int error = processUri(nextInputStream, depth + 1);
            try {
                nextInputStream.close();
                return error;
            } catch (IOException e) {
                return error;
            }
        } catch (Exception e2) {
            VASTLog.m2414e(TAG, e2.getMessage(), e2);
            return 2;
        }
    }

    private Document createDoc(InputStream is) {
        VASTLog.m2412d(TAG, "About to create doc from InputStream");
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            doc.getDocumentElement().normalize();
            VASTLog.m2412d(TAG, "Doc successfully created.");
            return doc;
        } catch (Exception e) {
            VASTLog.m2414e(TAG, e.getMessage(), e);
            return null;
        }
    }

    private void merge(Document newDoc) {
        VASTLog.m2412d(TAG, "About to merge doc into main doc.");
        this.mergedVastDocs.append(XmlTools.xmlDocumentToString(newDoc.getElementsByTagName("VAST").item(0)));
        VASTLog.m2412d(TAG, "Merge successful.");
    }

    private boolean validateAgainstSchema(Document doc) {
        VASTLog.m2412d(TAG, "About to validate doc against schema.");
        InputStream stream = VASTProcessor.class.getResourceAsStream("assets/vast_2_0_1_schema.xsd");
        boolean isValid = XmlValidation.validate(stream, XmlTools.xmlDocumentToString(doc));
        try {
            stream.close();
        } catch (IOException e) {
        }
        return isValid;
    }
}
