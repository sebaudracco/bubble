package com.cuebiq.cuebiqsdk.api;

public class ApiConfiguration {
    public static final String API_COVERAGE = "/bea/50100/coverage";
    public static final String API_ECHO = "/bea/echo";
    static final String API_GDPR_CONSENT = "/gdpr/50100/consent";
    public static final String API_LOG = "/log/bea/50100";
    public static final String API_POST = "/bea/50100";
    public static final String SCHEME = "https";
    public static String apiBaseUrl = productionUrl;
    public static final String productionUrl = "in.cuebiq.com";
    public static Environment workingEnvironment = Environment.PRODUCTION;

    public static void setEnvironment(Environment environment) {
        if (environment == Environment.PRODUCTION) {
            apiBaseUrl = productionUrl;
        }
        workingEnvironment = environment;
    }
}
