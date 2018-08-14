package com.cuebiq.cuebiqsdk.utils;

import com.cuebiq.cuebiqsdk.model.comparator.InformationComparator;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class InformationList extends ArrayList<Information> {
    public boolean add(Information information) {
        boolean add = super.add(information);
        Collections.sort(this, new InformationComparator());
        return add;
    }

    public boolean addAll(Collection<? extends Information> collection) {
        boolean addAll = super.addAll(collection);
        Collections.sort(this, new InformationComparator());
        return addAll;
    }

    public Information getFirst() {
        return (Information) get(0);
    }

    public Information getLast() {
        return (Information) get(size() - 1);
    }

    public void removeFirst() {
        remove(0);
        Collections.sort(this, new InformationComparator());
    }
}
