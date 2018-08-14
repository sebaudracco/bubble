package mf.org.w3c.dom.svg;

public interface SVGTests {
    SVGStringList getRequiredExtensions();

    SVGStringList getRequiredFeatures();

    SVGStringList getSystemLanguage();

    boolean hasExtension(String str);
}
