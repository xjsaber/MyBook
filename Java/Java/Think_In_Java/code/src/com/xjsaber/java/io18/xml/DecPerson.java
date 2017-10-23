package com.xjsaber.java.io18.xml;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;

/**
 * @author xjsaber
 */
public class DecPerson {

    public DecPerson(String fileName) throws Exception {
        Document doc = new Builder().build(fileName);
        Elements elements = doc.getRootElement().getChildElements();
        for (int i = 0; i < elements.size(); i++){
//            add
        }
    }

    public static void main(String[] args) throws Exception {
        DecPerson p = new DecPerson("People.xml");
        System.out.println(p);
    }

}
