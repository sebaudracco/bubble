package com.cuebiq.cuebiqsdk.model.processor;

import android.content.Context;
import com.cuebiq.cuebiqsdk.model.listener.ProcessorCompletedListener;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;

public abstract class AbstractProcessor {
    private final ProcessorType mType;

    AbstractProcessor(ProcessorType processorType) {
        this.mType = processorType;
    }

    public abstract void gather(Context context, Information information, ProcessorCompletedListener processorCompletedListener);

    ProcessorType getType() {
        return this.mType;
    }
}
