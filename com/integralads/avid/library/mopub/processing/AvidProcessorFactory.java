package com.integralads.avid.library.mopub.processing;

public class AvidProcessorFactory {
    private AvidSceenProcessor screenProcessor = new AvidSceenProcessor(this.viewProcessor);
    private AvidViewProcessor viewProcessor = new AvidViewProcessor();

    public IAvidNodeProcessor getRootProcessor() {
        return this.screenProcessor;
    }
}
