package com.xjsaber.java.io18;

import java.io.File;
import java.io.FilenameFilter;

public class DirFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return false;
    }
}
