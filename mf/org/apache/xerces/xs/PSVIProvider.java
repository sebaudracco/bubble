package mf.org.apache.xerces.xs;

public interface PSVIProvider {
    AttributePSVI getAttributePSVI(int i);

    AttributePSVI getAttributePSVIByName(String str, String str2);

    ElementPSVI getElementPSVI();
}
