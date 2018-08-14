package com.yandex.metrica.impl;

import android.util.SparseArray;

public final class bb {
    static final C4353a f11770a = new C4353a("273", bc.m14873a());
    static final SparseArray<C4353a> f11771b;

    static final class C4353a {
        String f11768a;
        String f11769b;

        public C4353a(String str, String str2) {
            this.f11768a = str;
            this.f11769b = str2;
        }
    }

    static {
        SparseArray sparseArray = new SparseArray();
        f11771b = sparseArray;
        sparseArray.put(1, new C4353a("100", "1.00"));
        f11771b.put(2, new C4353a("110", "1.10"));
        f11771b.put(3, new C4353a("111", "1.11"));
        f11771b.put(4, new C4353a("120", "1.20"));
        f11771b.put(5, new C4353a("121", "1.21"));
        f11771b.put(6, new C4353a("122", "1.22"));
        f11771b.put(7, new C4353a("123", "1.23"));
        f11771b.put(8, new C4353a("124", "1.24"));
        f11771b.put(9, new C4353a("126", "1.26"));
        f11771b.put(10, new C4353a("127", "1.27"));
        f11771b.put(11, new C4353a("140", "1.40"));
        f11771b.put(12, new C4353a("141", "1.41"));
        f11771b.put(13, new C4353a("142", "1.42"));
        f11771b.put(14, new C4353a("150", "1.50"));
        f11771b.put(15, new C4353a("151", "1.51"));
        f11771b.put(16, new C4353a("160", "1.60"));
        f11771b.put(17, new C4353a("161", "1.61"));
        f11771b.put(18, new C4353a("162", "1.62"));
        f11771b.put(19, new C4353a("163", "1.63"));
        f11771b.put(20, new C4353a("164", "1.64"));
        f11771b.put(21, new C4353a("165", "1.65"));
        f11771b.put(22, new C4353a("166", "1.66"));
        f11771b.put(23, new C4353a("167", "1.67"));
        f11771b.put(24, new C4353a("168", "1.68"));
        f11771b.put(25, new C4353a("169", "1.69"));
        f11771b.put(26, new C4353a("170", "1.70"));
        f11771b.put(27, new C4353a("171", "1.71"));
        f11771b.put(28, new C4353a("172", "1.72"));
        f11771b.put(29, new C4353a("180", "1.80"));
        f11771b.put(30, new C4353a("181", "1.81"));
        f11771b.put(31, new C4353a("182", "1.82"));
        f11771b.put(32, new C4353a("200", "2.00"));
        f11771b.put(33, new C4353a("210", "2.10"));
        f11771b.put(34, new C4353a("211", "2.11"));
        f11771b.put(35, new C4353a("220", "2.20"));
        f11771b.put(36, new C4353a("221", "2.21"));
        f11771b.put(37, new C4353a("222", "2.22"));
        f11771b.put(38, new C4353a("223", "2.23"));
        f11771b.put(39, new C4353a("230", "2.30"));
        f11771b.put(40, new C4353a("231", "2.31"));
        f11771b.put(41, new C4353a("232", "2.32"));
        f11771b.put(42, new C4353a("233", "2.33"));
        f11771b.put(43, new C4353a("240", "2.40"));
        f11771b.put(44, new C4353a("241", "2.41"));
        f11771b.put(45, new C4353a("242", "2.42"));
        f11771b.put(46, new C4353a("243", "2.43"));
        f11771b.put(47, new C4353a("250", "2.50"));
        f11771b.put(48, new C4353a("251", "2.51"));
        f11771b.put(49, new C4353a("252", "2.52"));
        f11771b.put(50, new C4353a("260", "2.60"));
        f11771b.put(51, new C4353a("261", "2.61"));
        f11771b.put(52, new C4353a("262", "2.62"));
        f11771b.put(53, new C4353a("263", "2.63"));
        f11771b.put(54, new C4353a("264", "2.64"));
        f11771b.put(55, new C4353a("270", "2.70"));
        f11771b.put(56, new C4353a("271", "2.71"));
        f11771b.put(57, new C4353a("272", "2.72"));
        f11771b.put(58, new C4353a("273", "2.73"));
    }

    static C4353a m14870a(int i) {
        return (C4353a) f11771b.get(i);
    }
}
