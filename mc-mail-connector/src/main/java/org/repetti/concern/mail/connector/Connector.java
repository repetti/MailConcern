package org.repetti.concern.mail.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Date: 24/06/15
 */
public class Connector {
    private static final Logger log = LoggerFactory.getLogger(Connector.class);
    private final Store store;

    public Connector(MailProperties props) throws MessagingException {
        Session session = Session.getInstance(props.getProperties());
        store = session.getStore();
        store.connect(props.host, props.user, props.pass);

//        Folder inbox = store.getFolder("INBOX");

//        Folder[] ff = store.getUserNamespaces("tp.bot.0");
        List<Folder> folders = new ArrayList<Folder>();

//        store.

//        System.out.println("# Folders count: " + folders.length);
//        for (Folder f : folders) {
//            folderInfo(f);
//        }

        folders.add(store.getDefaultFolder());
//        folders.add(store.getFolder("INBOX"));
//        folders.add(store.getFolder("/INBOX"));
//        folders.addAll(Arrays.asList(store.getPersonalNamespaces()));
//        folders.addAll(Arrays.asList(store.getSharedNamespaces()));

        System.out.println("# Folders count: " + folders.size());
        for (Folder f : folders) {
            List<Message> a = folderInfo(f);
            for (Message m : a) {
                log.info("{}: {}", m.getSentDate(), m.getSubject());
            }
        }

    }

    private List<Message> folderInfo(Folder f) throws MessagingException {
        if (f.exists()) {
            List<Message> ret = new ArrayList<Message>();
            log.warn("Folder '{}' exists", f);
            try {
                if (!f.isOpen()) {
                    f.open(Folder.READ_WRITE);
                }
                int a = f.getMessageCount();
                ret.addAll(Arrays.asList(f.getMessages()));
                log.info("Folder '{}' has {} messages", f.getName(), a);
//                f.close(false);
            } catch (Exception e) {
                log.error("Exception for folder '{}': {}", f, e.getMessage());
            }
            Folder[] c = f.list();
            for (Folder d : c) {
                ret.addAll(folderInfo(d));
            }
            return ret;
        } else {
            log.warn("Folder {} doesnt exist", f);
            return Collections.emptyList();
        }

//        System.out.println("$ " + f.getName() + );

    }

    public void close() {
        try {
            store.close();
        } catch (MessagingException e) {
            log.warn("unable to close", e);
        }
        log.info("Connection closed");
    }
}
