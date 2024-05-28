package com.javarush.task.task36.task3605;


import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet treeSet=new TreeSet();
        try (FileReader inputStream=new FileReader(args[0])){
            int i;
            while ((i=inputStream.read())!=-1){
                if ((i >=97&&i<=122)||(i>=65&&i<=90)) {
                    char ch=Character.toLowerCase((char) i);
                    treeSet.add(ch);
                }
            }
            Iterator iterator=treeSet.iterator();
            if (treeSet.size()<=5) {
                while (iterator.hasNext()) {
                    System.out.print(iterator.next());
                }
            }else {
                for (int j = 0; iterator.hasNext()&&j<5; j++) {
                    System.out.print(iterator.next());
                }
            }
        }

    }
}
