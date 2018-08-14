package com.integralads.avid.library.adcolony.processing;

public class AvidProcessorFactory {
    private AvidSceenProcessor f8332a = new AvidSceenProcessor(this.f8333b);
    private AvidViewProcessor f8333b = new AvidViewProcessor();

    public IAvidNodeProcessor getRootProcessor() {
        return this.f8332a;
    }
}
