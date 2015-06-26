package org.repetti.concern.mail.connector;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Date: 24/06/15
 */
public class MailProperties {
    private final Properties props = new Properties();

    public final String host;
    public final String user;
    public final String pass;
    public final String protocolStore;
    public final String protocolTransport;
    public final String debug;

    //TODO? create builder
    private MailProperties(String host, String user, String pass, String protocolStore, String protocolTransport, String debug) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.protocolStore = protocolStore;
        this.protocolTransport = protocolTransport;
        this.debug = debug;
    }

    public static MailProperties loadFromFile(File file) throws IOException {
        Properties props = new Properties();
        props.load(new FileReader(file));
        return new MailProperties(
                props.getProperty("mail.host"),
                props.getProperty("mail.user"),
                props.getProperty("mail.pass"),
                props.getProperty("mail.store.protocol"),
                props.getProperty("mail.transport.protocol"),
                props.getProperty("mail.debug"));
    }

    public Properties getProperties() {
        Properties ret = new Properties();
//        ret.setProperty("mail.imap.host", host);
        ret.setProperty("mail.store.protocol", protocolStore);
        ret.setProperty("mail.debug", String.valueOf(debug));
        return ret;
    }
}
