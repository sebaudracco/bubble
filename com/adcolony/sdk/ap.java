package com.adcolony.sdk;

import android.opengl.GLES20;
import android.util.Log;

class ap {
    an f642a;
    int f643b = GLES20.glCreateShader(35633);
    int f644c = GLES20.glCreateShader(35632);
    int f645d;
    int f646e;
    int f647f;
    int f648g;
    int f649h;
    int f650i;

    static class C0613a extends ap {
        C0613a(an anVar) {
            super(anVar, "precision mediump float;              \nuniform mat4   transform;             \nattribute vec4 position;              \nattribute lowp vec4 color;            \nvarying   lowp vec4 vertex_color;     \nvoid main()                           \n{                                     \n  gl_Position = transform * position; \n  vertex_color = color / 255.0;       \n}                                     \n", "precision mediump float;        \nvarying lowp vec4 vertex_color; \nvoid main()                     \n{                               \n  gl_FragColor = vertex_color;  \n}                               \n");
        }
    }

    static class C0614b extends ap {
        C0614b(an anVar) {
            super(anVar, "precision mediump float;              \nuniform mat4   transform;             \nattribute vec4 position;              \nattribute      vec2 uv;               \nvarying        vec2 vertex_uv;        \nvoid main()                           \n{                                     \n  gl_Position = transform * position; \n  vertex_uv = uv;                     \n}                                     \n", "precision mediump float;                       \nuniform              sampler2D texture;        \nvarying              vec2      vertex_uv;      \nvoid main()                                    \n{                                              \n  gl_FragColor = texture2D(texture,vertex_uv); \n}                                              \n");
        }
    }

    static class C0615c extends ap {
        C0615c(an anVar) {
            super(anVar, "precision mediump float;                  \nuniform mat4   transform;                 \nattribute vec4 position;                  \nattribute      vec2 uv;                   \nvarying        vec2 vertex_uv;            \nattribute lowp vec4 color;                \nvarying   lowp vec4 vertex_color;         \nvoid main()                               \n{                                         \n  gl_Position = transform * position;     \n  vertex_uv = uv;                         \n  vertex_color = color / 255.0;           \n}                                         \n", "precision mediump float;                                      \nuniform      sampler2D texture;                               \nvarying      vec2      vertex_uv;                             \nvarying lowp vec4 vertex_color;                               \nvoid main()                                                   \n{                                                             \n  vec4 texture_color = texture2D(texture,vertex_uv);          \n  gl_FragColor = vec4( texture_color.xyz + (vertex_color.xyz * texture_color.a), texture_color.a ); \n}                                                             \n");
        }
    }

    static class C0616d extends ap {
        C0616d(an anVar) {
            super(anVar, "precision mediump float;              \nuniform        mat4 transform;        \nattribute      vec4 position;         \nattribute      vec2 uv;               \nvarying        vec2 vertex_uv;        \nattribute lowp vec4 color;            \nvarying   lowp vec4 vertex_color;     \nvoid main()                           \n{                                     \n  gl_Position = transform * position; \n  vertex_uv = uv;                     \n  vertex_color = color / 255.0;       \n}                                     \n", "precision mediump float;                                      \nuniform           sampler2D texture;                          \nvarying           vec2      vertex_uv;                        \nvarying   lowp    vec4      vertex_color;                     \nvoid main()                                                   \n{                                                             \n  gl_FragColor = texture2D(texture,vertex_uv) * vertex_color; \n}                                                             \n");
        }
    }

    static class C0617e extends ap {
        C0617e(an anVar) {
            super(anVar, "precision mediump float;                        \nuniform        mat4   transform;                \nattribute      vec4 position;                   \nattribute      vec2 uv;                         \nvarying        vec2 vertex_uv;                  \nattribute lowp vec4  color;                     \nvarying   lowp vec4  vertex_color;              \nvoid main()                                     \n{                                               \n  gl_Position = transform * position;           \n  vertex_uv = uv;                               \n  vertex_color = color / 255.0;                 \n}                                               \n", "precision mediump float;                                        \nuniform      sampler2D texture;                                 \nvarying      vec2      vertex_uv;                               \nvarying lowp vec4      vertex_color;                            \nvoid main()                                                     \n{                                                               \n  lowp float texture_a = texture2D(texture,vertex_uv).a;        \n  gl_FragColor = vec4( vertex_color.xyz*texture_a, texture_a ); \n}                                                               \n");
        }
    }

    static class C0618f extends ap {
        C0618f(an anVar) {
            super(anVar, "precision mediump float;                   \nuniform mat4   transform;                  \nattribute vec4 position;                   \nattribute      vec2 uv;                    \nvarying        vec2 vertex_uv;             \nattribute lowp vec4  color;                \nvarying   lowp vec4  vertex_color;         \nvarying   lowp float vertex_inverse_a;     \nvoid main()                                \n{                                          \n  gl_Position = transform * position;      \n  vertex_uv = uv;                          \n  vertex_color = color / 255.0;            \n  vertex_inverse_a = 1.0 - vertex_color.a; \n}                                          \n", "precision mediump float;                   \nuniform      sampler2D texture;            \nvarying      vec2      vertex_uv;          \nvarying lowp vec4      vertex_color;       \nvarying lowp float vertex_inverse_a;       \nvoid main()                                \n{                                          \n  vec4 texture_color = texture2D(texture,vertex_uv);        \n  gl_FragColor = vec4( (texture_color.xyz*vertex_inverse_a)+(vertex_color.xyz*texture_color.a), texture_color.a );  \n}                                                   \n");
        }
    }

    ap(an anVar, String str, String str2) {
        m770a("Compiling " + getClass().getSimpleName() + "...");
        this.f642a = anVar;
        m770a("Vertex shader");
        GLES20.glShaderSource(this.f643b, str);
        GLES20.glCompileShader(this.f643b);
        m770a(GLES20.glGetShaderInfoLog(this.f643b));
        m770a("Pixel shader");
        GLES20.glShaderSource(this.f644c, str2);
        GLES20.glCompileShader(this.f644c);
        m770a(GLES20.glGetShaderInfoLog(this.f644c));
        m770a("vertex_shader:" + this.f643b + " pixel_shader:" + this.f644c);
        this.f645d = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.f645d, this.f643b);
        GLES20.glAttachShader(this.f645d, this.f644c);
        GLES20.glLinkProgram(this.f645d);
        this.f646e = GLES20.glGetUniformLocation(this.f645d, "transform");
        this.f647f = GLES20.glGetAttribLocation(this.f645d, "position");
        this.f648g = GLES20.glGetAttribLocation(this.f645d, "color");
        this.f649h = GLES20.glGetUniformLocation(this.f645d, "texture");
        this.f650i = GLES20.glGetAttribLocation(this.f645d, "uv");
    }

    static void m770a(String str) {
        Log.d("ADC3", str);
    }

    void m771a() {
        GLES20.glUseProgram(this.f645d);
        GLES20.glUniformMatrix4fv(this.f646e, 1, false, this.f642a.f618z.f741c, 0);
        this.f642a.f615w.rewind();
        GLES20.glVertexAttribPointer(this.f647f, 2, 5126, false, 0, this.f642a.f615w);
        GLES20.glEnableVertexAttribArray(this.f647f);
        if (this.f650i >= 0) {
            this.f642a.f616x.rewind();
            GLES20.glVertexAttribPointer(this.f650i, 2, 5126, false, 0, this.f642a.f616x);
            GLES20.glEnableVertexAttribArray(this.f650i);
        }
        if (this.f648g >= 0) {
            this.f642a.f617y.rewind();
            GLES20.glVertexAttribPointer(this.f648g, 4, 5121, false, 0, this.f642a.f617y);
            GLES20.glEnableVertexAttribArray(this.f648g);
        }
        if (this.f649h >= 0) {
            GLES20.glUniform1i(this.f649h, 0);
        }
    }
}
