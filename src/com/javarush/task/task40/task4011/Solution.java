package com.javarush.task.task40.task4011;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/* 
Свойства URL
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        decodeURLString("htps://www.amrood.com/index.htm?language=en#j2se");
    }

    public static void decodeURLString(String s) throws MalformedURLException {
        try {
            URL url=new URL(s);
            System.out.printf("- protocol %s \n",url.getProtocol());
            System.out.printf("- authority %s \n",url.getAuthority());
            System.out.printf("- file %s \n", url.getFile());
            System.out.printf("- host %s \n", url.getHost());
            System.out.printf("- path %s \n", url.getPath());
            System.out.printf("- port %s \n", url.getPort());
            System.out.printf("- default port %s \n",url.getDefaultPort());
            System.out.printf("- query %s \n",url.getQuery());
            System.out.printf("- ref %s \n",url.getRef());
        } catch (MalformedURLException e) {
            System.out.printf("Parameter %s is not a valid URL.",s);
        }
    }
}

