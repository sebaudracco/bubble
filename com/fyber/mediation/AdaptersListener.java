package com.fyber.mediation;

import java.util.Map;
import java.util.Set;

public interface AdaptersListener {
    void startedAdapters(Set<String> set, Map<String, Map<String, Object>> map);
}
