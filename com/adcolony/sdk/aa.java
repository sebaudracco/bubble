package com.adcolony.sdk;

class aa {
    static aa f477a = new aa(3, false);
    static aa f478b = new aa(3, true);
    static aa f479c = new aa(2, false);
    static aa f480d = new aa(2, true);
    static aa f481e = new aa(1, false);
    static aa f482f = new aa(1, true);
    static aa f483g = new aa(0, false);
    static aa f484h = new aa(0, true);
    private int f485i;
    private boolean f486j;

    static class C0595a {
        StringBuilder f476a = new StringBuilder();

        C0595a() {
        }

        C0595a m618a(char c) {
            if (c != '\n') {
                this.f476a.append(c);
            }
            return this;
        }

        C0595a m622a(String str) {
            this.f476a.append(str);
            return this;
        }

        C0595a m621a(Object obj) {
            if (obj != null) {
                this.f476a.append(obj.toString());
            } else {
                this.f476a.append("null");
            }
            return this;
        }

        C0595a m619a(double d) {
            az.m875a(d, 2, this.f476a);
            return this;
        }

        C0595a m620a(int i) {
            this.f476a.append(i);
            return this;
        }

        C0595a m623a(boolean z) {
            this.f476a.append(z);
            return this;
        }

        void m624a(aa aaVar) {
            aaVar.m626a(this.f476a.toString());
        }
    }

    aa(int i, boolean z) {
        this.f485i = i;
        this.f486j = z;
    }

    private void m626a(String str) {
        ac.m656a(this.f485i, str, this.f486j);
    }
}
