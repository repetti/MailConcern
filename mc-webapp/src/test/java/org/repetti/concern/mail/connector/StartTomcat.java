package org.repetti.concern.mail.connector;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * Created on 12/07/15.
 */
public class StartTomcat {
    public static void main(String... args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        final String baseDir = "mc-webapp/src/main/webapp";
        try {
            tomcat.addWebapp(null, "", baseDir);
//            tomcat.addWebapp(null, "/manager", baseDir + "/manager");
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        } finally {
            try {
                tomcat.stop();
            } catch (Exception ignore) {
            }
        }
        System.exit(1);
    }
}
