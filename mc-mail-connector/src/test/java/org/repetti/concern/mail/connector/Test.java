package org.repetti.concern.mail.connector;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;

/**
 * Date: 24/06/15
 */
public class Test {
    public static void main(String[] args) throws MessagingException, IOException {
//        MailProperties props = new MailProperties(
//                "imap.server",
//                "name",
//                "pass",
//                "imaps",
//                ""
//        );
        MailProperties props = MailProperties.loadFromFile(
//                new File("mc-mail-connector/src/test/resources/01.secret"));
                new File("mc-mail-connector/src/test/resources/02.secret"));
        Connector c = new Connector(props);
    }
}
