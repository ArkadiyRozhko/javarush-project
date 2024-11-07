package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector{
    private SimpleConnector simpleConnector;
    private SecurityChecker checker=new SecurityCheckerImpl();
    private String conString;

    public SecurityProxyConnector(String string) {
        conString=string;
        simpleConnector=new SimpleConnector(conString);
    }

    @Override
    public void connect() {
        if (checker.performSecurityCheck()) {
            simpleConnector.connect();
        }else
            System.out.println("connecting to "+conString+ " is not secure");

    }
}
