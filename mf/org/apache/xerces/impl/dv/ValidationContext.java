package mf.org.apache.xerces.impl.dv;

import java.util.Locale;

public interface ValidationContext {
    void addId(String str);

    void addIdRef(String str);

    Locale getLocale();

    String getSymbol(String str);

    String getURI(String str);

    boolean isEntityDeclared(String str);

    boolean isEntityUnparsed(String str);

    boolean isIdDeclared(String str);

    boolean needExtraChecking();

    boolean needFacetChecking();

    boolean needToNormalize();

    boolean useNamespaces();
}
