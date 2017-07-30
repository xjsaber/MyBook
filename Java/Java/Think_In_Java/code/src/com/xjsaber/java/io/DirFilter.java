package com.xjsaber.java.io;

import java.io.File;
import java.io.FilenameFilter;

public class DirFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return false;
    }
}
