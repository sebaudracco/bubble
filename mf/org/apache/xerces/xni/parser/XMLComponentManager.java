package mf.org.apache.xerces.xni.parser;

public interface XMLComponentManager {
    boolean getFeature(String str) throws XMLConfigurationException;

    Object getProperty(String str) throws XMLConfigurationException;
}
