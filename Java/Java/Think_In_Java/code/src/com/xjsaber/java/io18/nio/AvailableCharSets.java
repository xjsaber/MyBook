package com.xjsaber.java.io18.nio;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * @author xjsaber
 */
public class AvailableCharSets {

    public static void main(String[] args){
        SortedMap<String, Charset> charSets = Charset.availableCharsets();
        for (String csName : charSets.keySet()) {
            System.out.print(csName);
            Iterator aliases = charSets.get(csName).aliases().iterator();
            if (aliases.hasNext()) {
                System.out.print(": ");
            }
            while (aliases.hasNext()) {
                System.out.print(aliases.next());
                ;
                if (aliases.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
        }
    }
}
