package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

public class AudioSpecificConfig$ELDSpecificConfig {
    private static final int ELDEXT_TERM = 0;
    public boolean aacScalefactorDataResilienceFlag;
    public boolean aacSectionDataResilienceFlag;
    public boolean aacSpectralDataResilienceFlag;
    public boolean frameLengthFlag;
    public boolean ldSbrCrcFlag;
    public boolean ldSbrPresentFlag;
    public boolean ldSbrSamplingRate;
    final /* synthetic */ AudioSpecificConfig this$0;

    public AudioSpecificConfig$ELDSpecificConfig(AudioSpecificConfig audioSpecificConfig, int channelConfiguration, BitReaderBuffer bitReaderBuffer) {
        this.this$0 = audioSpecificConfig;
        this.frameLengthFlag = bitReaderBuffer.readBool();
        this.aacSectionDataResilienceFlag = bitReaderBuffer.readBool();
        this.aacScalefactorDataResilienceFlag = bitReaderBuffer.readBool();
        this.aacSpectralDataResilienceFlag = bitReaderBuffer.readBool();
        this.ldSbrPresentFlag = bitReaderBuffer.readBool();
        if (this.ldSbrPresentFlag) {
            this.ldSbrSamplingRate = bitReaderBuffer.readBool();
            this.ldSbrCrcFlag = bitReaderBuffer.readBool();
            ld_sbr_header(channelConfiguration, bitReaderBuffer);
        }
        while (bitReaderBuffer.readBits(4) != 0) {
            int eldExtLen = bitReaderBuffer.readBits(4);
            int len = eldExtLen;
            int eldExtLenAdd = 0;
            if (eldExtLen == 15) {
                eldExtLenAdd = bitReaderBuffer.readBits(8);
                len += eldExtLenAdd;
            }
            if (eldExtLenAdd == 255) {
                len += bitReaderBuffer.readBits(16);
            }
            for (int cnt = 0; cnt < len; cnt++) {
                bitReaderBuffer.readBits(8);
            }
        }
    }

    public void ld_sbr_header(int channelConfiguration, BitReaderBuffer bitReaderBuffer) {
        int numSbrHeader;
        switch (channelConfiguration) {
            case 1:
            case 2:
                numSbrHeader = 1;
                break;
            case 3:
                numSbrHeader = 2;
                break;
            case 4:
            case 5:
            case 6:
                numSbrHeader = 3;
                break;
            case 7:
                numSbrHeader = 4;
                break;
            default:
                numSbrHeader = 0;
                break;
        }
        for (int el = 0; el < numSbrHeader; el++) {
            AudioSpecificConfig$sbr_header audioSpecificConfig$sbr_header = new AudioSpecificConfig$sbr_header(this.this$0, bitReaderBuffer);
        }
    }
}
