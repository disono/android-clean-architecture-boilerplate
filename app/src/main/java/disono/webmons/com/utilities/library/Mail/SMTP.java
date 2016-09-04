package disono.webmons.com.utilities.library.Mail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/21/2016 12:55 PM
 */
public class SMTP extends AsyncTask<Void, Void, Void> {
    private final String TAG = "SMTP:Class";

    private Activity activity;
    private Session session;
    private Context ctx;
    private ProgressDialog progressDialog;

    // host information
    public String host = null;
    public String port = "465";
    public String factory = "javax.net.ssl.SSLSocketFactory";
    public String fallback = "false";
    public String auth = "true";

    // information to send email
    public String email = null;
    public String emailMultiple = null;
    public String cc = null;
    public String ccMultiple = null;
    public String bcc = null;
    public String bccMultiple = null;
    public String subject = null;
    public String message = null;
    public String attachment = null;

    // username and password
    public String username = null;
    public String password = null;

    public SMTP(Activity activity) {
        this.activity = activity;
        this.ctx = this.activity.getApplication();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // showing progress dialog while sending email
        progressDialog = ProgressDialog.show(this.activity,
                "Sending message", "Please wait...", false, false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // dismissing the progress dialog
        progressDialog.dismiss();

        // showing a success message
        Toast.makeText(ctx, "Message Sent", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // creating properties
        Properties props = new Properties();

        // configuring properties
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);
        props.put("mail.smtp.socketFactory.port", this.port);
        props.put("mail.smtp.socketFactory.class", this.factory);
        props.put("mail.smtp.socketFactory.fallback", this.fallback);
        props.put("mail.smtp.auth", this.auth);

        // creating a new session
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            // setting sender address
            mm.setFrom(new InternetAddress(username));

            // adding receiver single or multiple
            if (this.emailMultiple != null) {
                mm.addRecipients(Message.RecipientType.TO, InternetAddress.parse(this.cleanEmail(this.emailMultiple)));
            } else {
                mm.addRecipient(Message.RecipientType.TO, new InternetAddress(this.email));
            }

            // adding cc single or multiple
            if (this.ccMultiple != null) {
                mm.addRecipients(Message.RecipientType.CC, InternetAddress.parse(this.cleanEmail(this.ccMultiple)));
            } else if (this.cc != null) {
                mm.addRecipient(Message.RecipientType.CC, new InternetAddress(this.cc));
            }

            // adding bcc single or multiple
            if (this.bccMultiple != null) {
                mm.addRecipients(Message.RecipientType.CC, InternetAddress.parse(this.cleanEmail(this.bccMultiple)));
            } else if (this.bcc != null) {
                mm.addRecipient(Message.RecipientType.CC, new InternetAddress(this.bcc));
            }

            // adding subject
            mm.setSubject(this.subject);
            // attachment if available
            if (this.attachment != null) {
                BodyPart messageBodyPart = new MimeBodyPart();
                // message body
                messageBodyPart.setText(this.message);
                // create a multi part message
                Multipart multiPart = new MimeMultipart();
                // set text message part
                multiPart.addBodyPart(messageBodyPart);

                // attachment
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(this.attachment);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(this.attachment);
                multiPart.addBodyPart(messageBodyPart);

                // send the complete message parts
                mm.setContent(multiPart);
            } else {
                BodyPart messageBodyPart = new MimeBodyPart();
                // adding message
                messageBodyPart.setText(this.message);
                // create a multi part message
                Multipart multiPart = new MimeMultipart();
                multiPart.addBodyPart(messageBodyPart);

                mm.setHeader("X-Priority", "1");
                // put parts in message
                mm.setContent(multiPart);
            }

            // sending email
            Transport.send(mm);
        } catch (MessagingException e) {
            Timber.e("%s, Error: %s", TAG, e.getMessage());
        }

        return null;
    }

    /**
     * Make sure all email is clean
     *
     * @param emails
     * @return
     */
    private String cleanEmail(String emails) {
        // remove all spaces
        emails = emails.replaceAll("\\s+", "");

        return emails;
    }
}
