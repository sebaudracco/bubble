package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

public class AudioSpecificConfig$sbr_header {
    public boolean bs_alter_scale;
    public boolean bs_amp_res;
    public int bs_freq_scale;
    public boolean bs_header_extra_1;
    public boolean bs_header_extra_2;
    public boolean bs_interpol_freq;
    public int bs_limiter_bands;
    public int bs_limiter_gains;
    public int bs_noise_bands;
    public int bs_reserved;
    public boolean bs_smoothing_mode;
    public int bs_start_freq;
    public int bs_stop_freq;
    public int bs_xover_band;
    final /* synthetic */ AudioSpecificConfig this$0;

    public AudioSpecificConfig$sbr_header(AudioSpecificConfig audioSpecificConfig, BitReaderBuffer b) {
        this.this$0 = audioSpecificConfig;
        this.bs_amp_res = b.readBool();
        this.bs_start_freq = b.readBits(4);
        this.bs_stop_freq = b.readBits(4);
        this.bs_xover_band = b.readBits(3);
        this.bs_reserved = b.readBits(2);
        this.bs_header_extra_1 = b.readBool();
        this.bs_header_extra_2 = b.readBool();
        if (this.bs_header_extra_1) {
            this.bs_freq_scale = b.readBits(2);
            this.bs_alter_scale = b.readBool();
            this.bs_noise_bands = b.readBits(2);
        }
        if (this.bs_header_extra_2) {
            this.bs_limiter_bands = b.readBits(2);
            this.bs_limiter_gains = b.readBits(2);
            this.bs_interpol_freq = b.readBool();
        }
        this.bs_smoothing_mode = b.readBool();
    }
}
