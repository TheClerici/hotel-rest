package com.choice;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class TomcatLauncher {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(7070);
        tomcat.addWebapp("", new File("src/main/").getAbsolutePath());
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }
}
