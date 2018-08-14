package com.appsgeyser.sdk.vast.processor;

import com.appsgeyser.sdk.vast.model.VASTMediaFile;
import java.util.List;

public interface VASTMediaPicker {
    VASTMediaFile pickVideo(List<VASTMediaFile> list);
}
