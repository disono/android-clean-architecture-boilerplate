package disono.webmons.com.clean_architecture.util.library.Mail;

import android.app.Activity;
import android.content.Context;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/21/2016 11:19 PM
 */
public class IMAP {
    private final String TAG = "IMAP:Class";

    private Activity activity;
    private Session session;
    private Context ctx;

    public String host = null;
    public String protocol = "imaps";
    public String timeout = "5000";
    public String port = "993";

    public String username = null;
    public String password = null;

    public String folder = "INBOX";

    private Folder inbox = null;
    private Store store = null;

    public IMAP(Activity activity) {
        this.activity = activity;
        this.ctx = this.activity.getApplication();
    }

    /**
     * Fetch inbox
     *
     * @return
     */
    public ContentMessages[] fetch() {
        Message[] messages;
        ContentMessages[] contentMessages = {};
        Properties props = props();

        try {
            // create a session
            Session session = Session.getDefaultInstance(props, null);

            // connect
            this.store = session.getStore("imaps");
            this.store.connect(this.host, this.username, this.password);

            // open the inbox folder
            this.inbox = this.store.getFolder(this.folder);
            this.inbox.open(Folder.READ_WRITE);

            // get a list of javamail messages as an array of messages
            messages = this.inbox.getMessages();
            contentMessages = new ContentMessages[messages.length];

            for (int i = 0; i < messages.length; i++) {
                ContentMessages cM = new ContentMessages();
                cM.setName(getName(messages[i]));
                cM.setFrom(getFrom(messages[i]));
                cM.setBody(getBody(messages[i]));
                cM.setAttachment(getAttachment(messages[i]));
                cM.setReceivedDate(getReceivedDate(messages[i]));
                contentMessages[i] = cM;
            }

            return contentMessages;
        } catch (MessagingException | IOException e) {
            Timber.e("%s, Error: %s", TAG, e.getMessage());
        }

        return contentMessages;
    }

    /**
     * Get from/email's sender
     *
     * @param javaMailMessage
     * @return
     * @throws MessagingException
     */
    private static String getFrom(Message javaMailMessage)
            throws MessagingException {
        String from;
        Address[] froms = javaMailMessage.getFrom();

        from = (froms == null) ? null : ((InternetAddress) froms[0]).getAddress();

        return from;
    }

    /**
     * Get the sender's name/personal
     *
     * @param javaMailMessage
     * @return
     * @throws MessagingException
     */
    private static String getName(Message javaMailMessage) throws MessagingException {
        String name;
        Address[] names = javaMailMessage.getFrom();

        name = (names == null) ? null : ((InternetAddress) names[0]).getPersonal();

        return name;
    }

    /**
     * Get the content or body of the mail
     *
     * @param javaMailMessage
     * @return
     * @throws IOException
     * @throws MessagingException
     */
    private static String getBody(Message javaMailMessage) throws IOException, MessagingException {
        String body = null;
        Multipart multipart = (Multipart) javaMailMessage.getContent();

        for (int j = 0; j < multipart.getCount(); j++) {
            BodyPart bodyPart = multipart.getBodyPart(j);
            body = bodyPart.getContent().toString();
        }

        return body;
    }

    /**
     * Get the attachment
     *
     * @param javaMailMessage
     * @return
     * @throws IOException
     * @throws MessagingException
     */
    private static DataHandler getAttachment(Message javaMailMessage) throws IOException, MessagingException {
        Multipart multipart = (Multipart) javaMailMessage.getContent();

        for (int j = 0; j < multipart.getCount(); j++) {
            BodyPart bodyPart = multipart.getBodyPart(j);
            String disposition = bodyPart.getDisposition();

            // BodyPart.ATTACHMENT doesn't work for gmail
            if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
                return bodyPart.getDataHandler();
            }
        }

        return null;
    }

    /**
     * Get the received date
     *
     * @param javaMailMessage
     * @return
     * @throws MessagingException
     */
    private static Date getReceivedDate(Message javaMailMessage) throws MessagingException {
        return javaMailMessage.getReceivedDate();
    }

    /**
     * Check for attachment
     *
     * @param javaMailMessage
     * @return
     * @throws IOException
     * @throws MessagingException
     */
    private static boolean hasAttachment(Message javaMailMessage) throws IOException, MessagingException {
        Multipart multipart = (Multipart) javaMailMessage.getContent();

        for (int j = 0; j < multipart.getCount(); j++) {
            BodyPart bodyPart = multipart.getBodyPart(j);
            String disposition = bodyPart.getDisposition();

            // BodyPart.ATTACHMENT doesn't work for gmail
            if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Close the inbox folder but do not
     * Remove the messages from the server
     */
    public void close() {
        try {
            this.inbox.close(false);
            this.store.close();
        } catch (MessagingException e) {
            Timber.e("%s, Error: %s", TAG, e.getMessage());
        }
    }

    /**
     * Remove quotes
     *
     * @param stringToModify
     * @return
     */
    private static String removeQuotes(String stringToModify) {
        int indexOfFind = stringToModify.indexOf(stringToModify);
        if (indexOfFind < 0) return stringToModify;

        StringBuilder oldStringBuffer = new StringBuilder(stringToModify);
        StringBuffer newStringBuffer = new StringBuffer();

        for (int i = 0, length = oldStringBuffer.length(); i < length; i++) {
            char c = oldStringBuffer.charAt(i);
            if (c == '"' || c == '\'') {
                // do nothing
            } else {
                newStringBuffer.append(c);
            }

        }

        return new String(newStringBuffer);
    }

    /**
     * Properties
     *
     * @return
     */
    private Properties props() {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", this.protocol);
        props.setProperty("mail.imaps.port", this.port);
        props.setProperty("mail.imaps.connectiontimeout", this.timeout);
        props.setProperty("mail.imaps.timeout", this.timeout);

        return props;
    }

    /**
     * Messages
     */
    public class ContentMessages {
        private String _name = null;
        private String _from = null;
        private String _body = null;
        private DataHandler _attachment = null;
        private Date _receivedDate = null;

        public void setName(String name) {
            _name = name;
        }

        public void setFrom(String from) {
            _from = from;
        }

        public void setBody(String body) {
            _body = body;
        }

        public void setAttachment(DataHandler attachment) {
            _attachment = attachment;
        }

        public void setReceivedDate(Date receivedDate) {
            _receivedDate = receivedDate;
        }

        public String getName() {
            return _name;
        }

        public String getFrom() {
            return _from;
        }

        public String getBody() {
            return _body;
        }

        public DataHandler getAttachment() {
            return _attachment;
        }

        public Date getReceivedDate() {
            return _receivedDate;
        }
    }
}
