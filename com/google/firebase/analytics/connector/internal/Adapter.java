package com.google.firebase.analytics.connector.internal;

import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;

public interface Adapter {
    AnalyticsConnectorListener getListener();
}
