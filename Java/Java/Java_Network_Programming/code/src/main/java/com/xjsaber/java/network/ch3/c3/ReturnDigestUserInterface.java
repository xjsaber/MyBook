package com.xjsaber.java.network.ch3.c3;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjsaber on 2017/3/21.
 *
 */
public class ReturnDigestUserInterface {

   public static void main(String[] args){
       List<String> list = new ArrayList<>();
       list.add("txt1.txt");
       list.add("txt2.txt");
       list.add("txt3.txt");
       ReturnDigest[] digests = new ReturnDigest[list.size()];

       for (int i = 0; i < list.size(); i++){
           digests[i] = new ReturnDigest(list.get(i));
           digests[i].start();
       }
       for (int i = 0; i < list.size(); i++){
            //现在显示结果
            StringBuffer result = new StringBuffer(list.get(i));
            result.append(": ");
            byte[] digest = digests[i].getDigest();
            result.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(result);
       }
   }
}
