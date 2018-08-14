package mf.org.apache.wml;

public interface WMLTemplateElement extends WMLElement {
    String getOnEnterBackward();

    String getOnEnterForward();

    String getOnTimer();

    void setOnEnterBackward(String str);

    void setOnEnterForward(String str);

    void setOnTimer(String str);
}
