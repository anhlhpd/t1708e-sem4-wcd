package controller;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import entity.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class AccountController extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        Account account = ofy().load().type(Account.class).id(email).now();

        if(account == null){
            resp.getWriter().println("There's no account match this email.");
        }

        if(blobstoreService.getUploads(req) != null && !blobstoreService.getUploads(req).isEmpty() ){
            Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
            List<BlobKey> blobKeys = blobs.get("avatar");
            if (blobKeys != null || !blobKeys.isEmpty()) {
                account.setAvatar(blobKeys.get(0).getKeyString());
            }
        }

        ofy().save().entity(account).now();
        req.getSession().setAttribute("currentAccount", account);
    }
}
