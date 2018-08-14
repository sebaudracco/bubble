package mf.org.w3c.dom.html;

public interface HTMLOptionElement extends HTMLElement {
    boolean getDefaultSelected();

    boolean getDisabled();

    HTMLFormElement getForm();

    int getIndex();

    String getLabel();

    boolean getSelected();

    String getText();

    String getValue();

    void setDefaultSelected(boolean z);

    void setDisabled(boolean z);

    void setIndex(int i);

    void setLabel(String str);

    void setValue(String str);
}
