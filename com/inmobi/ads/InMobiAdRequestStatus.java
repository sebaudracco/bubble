package com.inmobi.ads;

public final class InMobiAdRequestStatus {
    private static final String AD_ACTIVE_MESSAGE = "The Ad Request could not be submitted as the user is viewing another Ad.";
    private static final String AD_AVAILABILITY_CHANGED_MESSAGE = "An ad is no longer available. Please call load() to fetch a fresh ad.";
    private static final String AD_EARLY_REFRESH_MESSAGE = "The Ad Request cannot be done so frequently. Please wait for some time before loading another ad.";
    private static final String INTERNAL_ERROR_MESSAGE = "The InMobi SDK encountered an internal error.";
    private static final String MISSING_REQUIRED_DEPENDENCIES_MESSAGE = "The SDK rejected the ad request as one or more required dependencies could not be found. Please ensure you have included the required dependencies.";
    private static final String NETWORK_UNREACHABLE_MESSAGE = "The Internet is unreachable. Please check your Internet connection.";
    private static final String NO_FILL_MESSAGE = "Ad request successful but no ad served.";
    private static final String REQUEST_INVALID_MESSAGE = "An invalid ad request was sent and was rejected by the Ad Network. Please validate the ad request and try again";
    private static final String REQUEST_PENDING_MESSAGE = "The SDK is pending response to a previous ad request. Please wait for the previous ad request to complete before requesting for another ad.";
    private static final String REQUEST_TIMED_OUT_MESSAGE = "The Ad Request timed out waiting for a response from the network. This can be caused due to a bad network connection. Please try again after a few minutes.";
    private static final String SERVER_ERROR_MESSAGE = "The Ad Server encountered an error when processing the ad request. This may be a transient issue. Please try again in a few minutes";
    private String mMessage;
    private StatusCode mStatusCode;

    public enum StatusCode {
        NO_ERROR,
        NETWORK_UNREACHABLE,
        NO_FILL,
        REQUEST_INVALID,
        REQUEST_PENDING,
        REQUEST_TIMED_OUT,
        INTERNAL_ERROR,
        SERVER_ERROR,
        AD_ACTIVE,
        EARLY_REFRESH_REQUEST,
        AD_NO_LONGER_AVAILABLE,
        MISSING_REQUIRED_DEPENDENCIES
    }

    public InMobiAdRequestStatus(StatusCode statusCode) {
        this.mStatusCode = statusCode;
        setMessage();
    }

    public InMobiAdRequestStatus setCustomMessage(String str) {
        if (str != null) {
            this.mMessage = str;
        }
        return this;
    }

    private void setMessage() {
        switch (this.mStatusCode) {
            case INTERNAL_ERROR:
                this.mMessage = INTERNAL_ERROR_MESSAGE;
                return;
            case NETWORK_UNREACHABLE:
                this.mMessage = NETWORK_UNREACHABLE_MESSAGE;
                return;
            case REQUEST_INVALID:
                this.mMessage = REQUEST_INVALID_MESSAGE;
                return;
            case REQUEST_PENDING:
                this.mMessage = REQUEST_PENDING_MESSAGE;
                return;
            case REQUEST_TIMED_OUT:
                this.mMessage = REQUEST_TIMED_OUT_MESSAGE;
                return;
            case SERVER_ERROR:
                this.mMessage = SERVER_ERROR_MESSAGE;
                return;
            case NO_FILL:
                this.mMessage = NO_FILL_MESSAGE;
                return;
            case AD_ACTIVE:
                this.mMessage = AD_ACTIVE_MESSAGE;
                return;
            case EARLY_REFRESH_REQUEST:
                this.mMessage = AD_EARLY_REFRESH_MESSAGE;
                return;
            case AD_NO_LONGER_AVAILABLE:
                this.mMessage = AD_AVAILABILITY_CHANGED_MESSAGE;
                return;
            case MISSING_REQUIRED_DEPENDENCIES:
                this.mMessage = MISSING_REQUIRED_DEPENDENCIES_MESSAGE;
                return;
            default:
                return;
        }
    }

    public StatusCode getStatusCode() {
        return this.mStatusCode;
    }

    public String getMessage() {
        return this.mMessage;
    }
}
