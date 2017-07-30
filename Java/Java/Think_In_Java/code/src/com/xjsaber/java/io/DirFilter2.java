package com.xjsaber.java.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirFilter2 {

    /**
     *
     * @param regex 传向filter()的参数必须是final的。这在匿名内部类中是必需的，这样才能够使用来自该类范围之外的对象。
     * @return
     */
    private static FilenameFilter filter(final String regex) {
        // Creation of anonymous inner class
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        }; // End of anonymous inner class
    }
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        }
        else {
            list = path.list(filter(args[0]));
        }
        assert list != null;
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}
