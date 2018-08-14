package mf.org.apache.xerces.impl;

public class Version {
    private static final String fImmutableVersion = "@@VERSION@@";
    public static String fVersion = fImmutableVersion;

    public static String getVersion() {
        return fImmutableVersion;
    }

    public static void main(String[] argv) {
        System.out.println(fVersion);
    }
}
