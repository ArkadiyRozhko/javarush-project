package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.com/social.html"));
    }

    public static void getSite(URL url) {

        try {
            String host= url.getHost();
            Socket socket=new Socket(host,80);

            OutputStream outputStream=socket.getOutputStream();
            PrintWriter writer=new PrintWriter(outputStream,true);
            String path=url.getPath();
            writer.println("GET "+path+"HTTP/1.1"+"\r\n"+"Host: "+host+"\n");
            writer.println();
            writer.flush();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}