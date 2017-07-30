package com.xjsaber.utils;

import java.io.File;

public class DirectoryDemo {

    public static void main(String[] args) {
        // All directories
        PPrint.pprint(Directory.walk(".").dirs);
        // All files beginning with "T"
        for(File file : Directory.walk(".", "T.*").dirs){
            System.out.println(file);
        }
        System.out.println("---------------------------------");
        // All java files beginning with 'T'
        for (File file : Directory.walk(".", "T.*\\.java"))
            System.out.println(file);
        System.out.println("---------------------------");
        // Class files containing "Z" or "z"
        for (File file : Directory.walk(".", ".*[Zz].*\\.class"))
            System.out.println(file);
    }
}
