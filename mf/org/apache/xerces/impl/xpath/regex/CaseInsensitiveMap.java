package mf.org.apache.xerces.impl.xpath.regex;

import java.lang.reflect.Array;

final class CaseInsensitiveMap {
    private static int CHUNK_MASK = (CHUNK_SIZE - 1);
    private static int CHUNK_SHIFT = 10;
    private static int CHUNK_SIZE = (1 << CHUNK_SHIFT);
    private static int INITIAL_CHUNK_COUNT = 64;
    private static int LOWER_CASE_MATCH = 1;
    private static int UPPER_CASE_MATCH = 2;
    private static int[][][] caseInsensitiveMap;

    CaseInsensitiveMap() {
    }

    static {
        buildCaseInsensitiveMap();
    }

    public static int[] get(int codePoint) {
        return codePoint < 65536 ? getMapping(codePoint) : null;
    }

    private static int[] getMapping(int codePoint) {
        return caseInsensitiveMap[codePoint >>> CHUNK_SHIFT][codePoint & CHUNK_MASK];
    }

    private static void buildCaseInsensitiveMap() {
        caseInsensitiveMap = (int[][][]) Array.newInstance(int[].class, new int[]{INITIAL_CHUNK_COUNT, CHUNK_SIZE});
        int i = 0;
        while (i < 65536) {
            int lc = Character.toLowerCase((char) i);
            int uc = Character.toUpperCase((char) i);
            if (lc != uc || lc != i) {
                int i2;
                int[] ucMap;
                int[] map = new int[2];
                int index = 0;
                if (lc != i) {
                    i2 = 0 + 1;
                    map[0] = lc;
                    index = i2 + 1;
                    map[i2] = LOWER_CASE_MATCH;
                    int[] lcMap = getMapping(lc);
                    if (lcMap != null) {
                        map = updateMap(i, map, lc, lcMap, LOWER_CASE_MATCH);
                        i2 = index;
                        if (uc != i) {
                            if (i2 == map.length) {
                                map = expandMap(map, 2);
                            }
                            index = i2 + 1;
                            map[i2] = uc;
                            i2 = index + 1;
                            map[index] = UPPER_CASE_MATCH;
                            ucMap = getMapping(uc);
                            if (ucMap != null) {
                                map = updateMap(i, map, uc, ucMap, UPPER_CASE_MATCH);
                                index = i2;
                                set(i, map);
                            }
                        }
                        index = i2;
                        set(i, map);
                    }
                }
                i2 = index;
                if (uc != i) {
                    if (i2 == map.length) {
                        map = expandMap(map, 2);
                    }
                    index = i2 + 1;
                    map[i2] = uc;
                    i2 = index + 1;
                    map[index] = UPPER_CASE_MATCH;
                    ucMap = getMapping(uc);
                    if (ucMap != null) {
                        map = updateMap(i, map, uc, ucMap, UPPER_CASE_MATCH);
                        index = i2;
                        set(i, map);
                    }
                }
                index = i2;
                set(i, map);
            }
            i++;
        }
    }

    private static int[] expandMap(int[] srcMap, int expandBy) {
        int oldLen = srcMap.length;
        int[] newMap = new int[(oldLen + expandBy)];
        System.arraycopy(srcMap, 0, newMap, 0, oldLen);
        return newMap;
    }

    private static void set(int codePoint, int[] map) {
        int offset = codePoint & CHUNK_MASK;
        caseInsensitiveMap[codePoint >>> CHUNK_SHIFT][offset] = map;
    }

    private static int[] updateMap(int codePoint, int[] codePointMap, int ciCodePoint, int[] ciCodePointMap, int matchType) {
        for (int i = 0; i < ciCodePointMap.length; i += 2) {
            int c = ciCodePointMap[i];
            int[] cMap = getMapping(c);
            if (cMap != null && contains(cMap, ciCodePoint, matchType)) {
                if (!contains(cMap, codePoint)) {
                    set(c, expandAndAdd(cMap, codePoint, matchType));
                }
                if (!contains(codePointMap, c)) {
                    codePointMap = expandAndAdd(codePointMap, c, matchType);
                }
            }
        }
        if (!contains(ciCodePointMap, codePoint)) {
            set(ciCodePoint, expandAndAdd(ciCodePointMap, codePoint, matchType));
        }
        return codePointMap;
    }

    private static boolean contains(int[] map, int codePoint) {
        for (int i = 0; i < map.length; i += 2) {
            if (map[i] == codePoint) {
                return true;
            }
        }
        return false;
    }

    private static boolean contains(int[] map, int codePoint, int matchType) {
        int i = 0;
        while (i < map.length) {
            if (map[i] == codePoint && map[i + 1] == matchType) {
                return true;
            }
            i += 2;
        }
        return false;
    }

    private static int[] expandAndAdd(int[] srcMap, int codePoint, int matchType) {
        int oldLen = srcMap.length;
        int[] newMap = new int[(oldLen + 2)];
        System.arraycopy(srcMap, 0, newMap, 0, oldLen);
        newMap[oldLen] = codePoint;
        newMap[oldLen + 1] = matchType;
        return newMap;
    }
}
