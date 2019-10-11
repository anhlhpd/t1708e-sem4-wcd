package controller;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileServicePb;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import entity.Account;
import entity.ActiveCode;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class RegisterController extends HttpServlet {
    private static final String NUMBERCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
    private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getSimpleName());
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Xử lý upload ảnh
        String avatar = "";
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("avatar");
        if (blobKeys == null || blobKeys.isEmpty()) {
            avatar = blobKeys.get(0).getKeyString();
        }

        Account account = Account.Builder.anAccount()
                .withEmail(email).withAvatar(avatar)
                .withPassword(password)
                .build();
        ofy().save().entity(account).now();

        // Tạo active code
        ActiveCode activeCode =  ActiveCode.Builder.anActiveCode()
                .withCode(generateActiveCode())
                .withCreatedAtMLS(Calendar.getInstance().getTimeInMillis())
                .withAccount(Ref.create(Key.create(Account.class, email)))
                .build();
        ofy().save().entity(activeCode).now();


        // Gửi mail
        Properties properties = new Properties();
//        Session session = Session.getInstance(properties,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("lphuonganh99@gmail.com", "ssuwzwqivxkhctrt");
//                    }
//                });
        Session session = Session.getDefaultInstance(properties, null);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lphuonganh99@gmail.com", "lphuonganh99@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, "Mr. User"));
            message.setSubject("Confirm email");
            message.setText("Please fill in the form with the active code: ");
            // Send message
            Transport.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            LOGGER.log(Level.SEVERE, "Error: ", e);
        }
        resp.sendRedirect("/confirm-email");
    }

    public static String generateActiveCode() {
        StringBuilder code = new StringBuilder();
        Random rnd = new Random();
        while (code.length() < 7) { // length of the random string.
            int index = (int) (rnd.nextFloat() * NUMBERCHARS.length());
            code.append(NUMBERCHARS.charAt(index));
        }
        String codeStr = code.toString();
        return codeStr.toLowerCase();
    }
}
