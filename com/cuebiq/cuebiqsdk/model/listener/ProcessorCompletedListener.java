package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.processor.ProcessorType;

public interface ProcessorCompletedListener {
    void onProcessorCompleted(ProcessorType processorType);
}
