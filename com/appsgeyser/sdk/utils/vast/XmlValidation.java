package com.appsgeyser.sdk.utils.vast;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import mf.javax.xml.transform.Source;
import mf.javax.xml.transform.stream.StreamSource;
import mf.javax.xml.validation.SchemaFactory;
import mf.org.apache.xerces.jaxp.validation.XMLSchemaFactory;

public class XmlValidation {
    private static String TAG = "XmlTools";

    public static boolean validate(InputStream schemaStream, String xml) {
        VASTLog.m2415i(TAG, "Beginning XSD validation.");
        SchemaFactory factory = new XMLSchemaFactory();
        Source schemaSource = new StreamSource(schemaStream);
        try {
            factory.newSchema(schemaSource).newValidator().validate(new StreamSource(new ByteArrayInputStream(xml.getBytes())));
            VASTLog.m2415i(TAG, "Completed XSD validation..");
            return true;
        } catch (Exception e) {
            VASTLog.m2414e(TAG, e.getMessage(), e);
            return false;
        }
    }
}
