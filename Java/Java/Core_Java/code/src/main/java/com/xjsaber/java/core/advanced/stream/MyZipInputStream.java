package com.xjsaber.java.core.advanced.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MyZipInputStream {

    public static void main(String[] args){
        String zipName = "";
        try {
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipName));
            ZipEntry zipEntry;
            while ((zipEntry = zin.getNextEntry()) != null){
                zin.closeEntry();
            }
            zin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
