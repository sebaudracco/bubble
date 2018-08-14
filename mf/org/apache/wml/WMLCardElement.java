package mf.org.apache.wml;

public interface WMLCardElement extends WMLElement {
    boolean getNewContext();

    String getOnEnterBackward();

    String getOnEnterForward();

    String getOnTimer();

    boolean getOrdered();

    String getTitle();

    String getXmlLang();

    void setNewContext(boolean z);

    void setOnEnterBackward(String str);

    void setOnEnterForward(String str);

    void setOnTimer(String str);

    void setOrdered(boolean z);

    void setTitle(String str);

    void setXmlLang(String str);
}
