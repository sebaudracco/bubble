package com.squareup.picasso;

public interface Picasso$RequestTransformer {
    public static final Picasso$RequestTransformer IDENTITY = new C39791();

    static class C39791 implements Picasso$RequestTransformer {
        C39791() {
        }

        public Request transformRequest(Request request) {
            return request;
        }
    }

    Request transformRequest(Request request);
}
