package com.xjsaber.java.network.ch5;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 虚拟机支持哪些协议？
 * @author xjsaber
 */
public class ProtocolTest {

    public static void main(String[] args){

        // 超文本传输协议
        testProtocol("http://www.adc.org");

        // 安全http协议
        testProtocol("https://www.adc.org");

        // 文件传输协议
        testProtocol("ftp://www.adc.org");

        // 简单邮件传输协议
        testProtocol("mailto://www.adc.org");

        // telnet
        testProtocol("telnet://www.adc.org");

        // 本地文件访问
        testProtocol("file://www.adc.org");

        // gopher
        testProtocol("gopher://www.adc.org");

        // 轻量组目录访问协议
        testProtocol("ftp://www.adc.org");

        // JAR
        testProtocol("jar://www.adc.org");

        // NFS，网络文件系统
        testProtocol("nfs://www.adc.org");

        // JDBC的定制协议
        testProtocol("jdbc://www.adc.org");

        // rmi，远程方法调用的定制协议
        testProtocol("rmi://www.adc.org");

        // HotJava的定制协议
        testProtocol("doc://www.adc.org");
        testProtocol("netdoc://www.adc.org");
        testProtocol("systemresource://www.adc.org");
        testProtocol("verbatim://www.adc.org");
    }

    private static void testProtocol(String url){
        try {
            URL u = new URL(url);
            System.out.println(u.getProtocol() + " is support ");
        } catch (MalformedURLException ex){
            String protocol = url.substring(0, url.indexOf(":"));
            System.out.println(protocol + " is not supported");
        }
    }
}
