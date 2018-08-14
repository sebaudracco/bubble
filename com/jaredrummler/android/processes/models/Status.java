package com.jaredrummler.android.processes.models;

import java.io.IOException;

public final class Status extends ProcFile {
    public static Status get(int pid) throws IOException {
        return new Status(String.format("/proc/%d/status", new Object[]{Integer.valueOf(pid)}));
    }

    private Status(String path) throws IOException {
        super(path);
    }

    public String getValue(String fieldName) {
        for (String line : this.content.split("\n")) {
            if (line.startsWith(fieldName + ":")) {
                return line.split(fieldName + ":")[1].trim();
            }
        }
        return null;
    }

    public int getUid() {
        try {
            return Integer.parseInt(getValue("Uid").split("\\s+")[0]);
        } catch (Exception e) {
            return -1;
        }
    }

    public int getGid() {
        try {
            return Integer.parseInt(getValue("Gid").split("\\s+")[0]);
        } catch (Exception e) {
            return -1;
        }
    }
}
