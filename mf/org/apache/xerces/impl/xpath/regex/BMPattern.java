package mf.org.apache.xerces.impl.xpath.regex;

import java.text.CharacterIterator;

public class BMPattern {
    final boolean ignoreCase;
    final char[] pattern;
    final int[] shiftTable;

    public BMPattern(String pat, boolean ignoreCase) {
        this(pat, 256, ignoreCase);
    }

    public BMPattern(String pat, int tableSize, boolean ignoreCase) {
        int i;
        this.pattern = pat.toCharArray();
        this.shiftTable = new int[tableSize];
        this.ignoreCase = ignoreCase;
        int length = this.pattern.length;
        for (i = 0; i < this.shiftTable.length; i++) {
            this.shiftTable[i] = length;
        }
        for (i = 0; i < length; i++) {
            char ch = this.pattern[i];
            int diff = (length - i) - 1;
            int index = ch % this.shiftTable.length;
            if (diff < this.shiftTable[index]) {
                this.shiftTable[index] = diff;
            }
            if (this.ignoreCase) {
                ch = Character.toUpperCase(ch);
                index = ch % this.shiftTable.length;
                if (diff < this.shiftTable[index]) {
                    this.shiftTable[index] = diff;
                }
                index = Character.toLowerCase(ch) % this.shiftTable.length;
                if (diff < this.shiftTable[index]) {
                    this.shiftTable[index] = diff;
                }
            }
        }
    }

    public int matches(CharacterIterator iterator, int start, int limit) {
        if (this.ignoreCase) {
            return matchesIgnoreCase(iterator, start, limit);
        }
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch;
            int pindex = plength;
            int nindex = index + 1;
            do {
                index--;
                ch = iterator.setIndex(index);
                pindex--;
                if (ch != this.pattern[pindex]) {
                    break;
                } else if (pindex == 0) {
                    return index;
                }
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length] + 1;
            if (index < nindex) {
                index = nindex;
            }
        }
        return -1;
    }

    public int matches(String str, int start, int limit) {
        if (this.ignoreCase) {
            return matchesIgnoreCase(str, start, limit);
        }
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch;
            int pindex = plength;
            int nindex = index + 1;
            do {
                index--;
                ch = str.charAt(index);
                pindex--;
                if (ch != this.pattern[pindex]) {
                    break;
                } else if (pindex == 0) {
                    return index;
                }
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length] + 1;
            if (index < nindex) {
                index = nindex;
            }
        }
        return -1;
    }

    public int matches(char[] chars, int start, int limit) {
        if (this.ignoreCase) {
            return matchesIgnoreCase(chars, start, limit);
        }
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch;
            int pindex = plength;
            int nindex = index + 1;
            do {
                index--;
                ch = chars[index];
                pindex--;
                if (ch != this.pattern[pindex]) {
                    break;
                } else if (pindex == 0) {
                    return index;
                }
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length] + 1;
            if (index < nindex) {
                index = nindex;
            }
        }
        return -1;
    }

    int matchesIgnoreCase(CharacterIterator iterator, int start, int limit) {
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch;
            int pindex = plength;
            int nindex = index + 1;
            do {
                index--;
                ch = iterator.setIndex(index);
                char ch1 = ch;
                pindex--;
                char ch2 = this.pattern[pindex];
                if (ch1 != ch2) {
                    ch1 = Character.toUpperCase(ch1);
                    ch2 = Character.toUpperCase(ch2);
                    if (!(ch1 == ch2 || Character.toLowerCase(ch1) == Character.toLowerCase(ch2))) {
                        break;
                    }
                }
                if (pindex == 0) {
                    return index;
                }
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length] + 1;
            if (index < nindex) {
                index = nindex;
            }
        }
        return -1;
    }

    int matchesIgnoreCase(String text, int start, int limit) {
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch;
            int pindex = plength;
            int nindex = index + 1;
            do {
                index--;
                ch = text.charAt(index);
                char ch1 = ch;
                pindex--;
                char ch2 = this.pattern[pindex];
                if (ch1 != ch2) {
                    ch1 = Character.toUpperCase(ch1);
                    ch2 = Character.toUpperCase(ch2);
                    if (!(ch1 == ch2 || Character.toLowerCase(ch1) == Character.toLowerCase(ch2))) {
                        break;
                    }
                }
                if (pindex == 0) {
                    return index;
                }
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length] + 1;
            if (index < nindex) {
                index = nindex;
            }
        }
        return -1;
    }

    int matchesIgnoreCase(char[] chars, int start, int limit) {
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch;
            int pindex = plength;
            int nindex = index + 1;
            do {
                index--;
                ch = chars[index];
                char ch1 = ch;
                pindex--;
                char ch2 = this.pattern[pindex];
                if (ch1 != ch2) {
                    ch1 = Character.toUpperCase(ch1);
                    ch2 = Character.toUpperCase(ch2);
                    if (!(ch1 == ch2 || Character.toLowerCase(ch1) == Character.toLowerCase(ch2))) {
                        break;
                    }
                }
                if (pindex == 0) {
                    return index;
                }
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length] + 1;
            if (index < nindex) {
                index = nindex;
            }
        }
        return -1;
    }
}
