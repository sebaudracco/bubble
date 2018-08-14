package com.fyber.requesters;

public enum RequestError {
    DEVICE_NOT_SUPPORTED("Only devices running Android API level 14 and above are supported"),
    CONNECTION_ERROR("Internet connection error"),
    UNKNOWN_ERROR("An unknown error occurred"),
    SDK_NOT_STARTED("You need to start the SDK"),
    MISMATCH_CALLBACK_TYPE("You need to provide the correct callback for the requester"),
    NULL_CONTEXT_REFERENCE("The context reference cannot be null"),
    SECURITY_TOKEN_NOT_PROVIDED("The security token was not provided when starting the SDK."),
    ERROR_REQUESTING_ADS("An error happened while trying to retrieve ads"),
    UNABLE_TO_REQUEST_ADS("The SDK is unable to request right now because it is either already performing a request or showing an ad");
    
    private String f6481a;

    private RequestError(String str) {
        this.f6481a = str;
    }

    public final String getDescription() {
        return this.f6481a;
    }
}
