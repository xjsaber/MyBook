package com.xjsaber.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class Directory {

    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(
                        new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }

    /**
     * a two-tuple for returning a pair of objects
     */
    public static class TreeInfo implements Iterable<File> {
        List<File> files = new ArrayList<>();
        List<File> dirs = new ArrayList<>();

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }
        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }
        public String toString() {
            return "dirs:" + PPrint.pformat(dirs) + "files:" + PPrint.pformat(files);
        }
    }

    public static TreeInfo walk(String start, String regex) {
        return recurseDirs(new File(start), regex);
    }
    public static TreeInfo walk(File start, String regex) {
        return recurseDirs(start, regex);
    }
    public static TreeInfo walk(File start) { // everything
        return recurseDirs(start, ".*");
    }
    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }
    /**
     * 遍历dir
     * @param startDir 遍历开始的开始目录
     * @param regex 正则关键字
     * @return
     */
    private static TreeInfo recurseDirs(File startDir, String regex){
        TreeInfo result = new TreeInfo();
        for (File item : startDir.listFiles()) {
            if (item.isDirectory()) {
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            } else { // Regular file
                if (item.getName().matches(regex)) {
                    result.files.add(item);
                }
            }
        }
        return result;
    }
}
