package com.fyber.utils.testsuite;

import com.fyber.utils.testsuite.IntegrationAnalyzer.FailReason;

public interface IntegrationAnalysisListener {
    void onAnalysisFailed(FailReason failReason);

    void onAnalysisSucceeded(IntegrationReport integrationReport);
}
