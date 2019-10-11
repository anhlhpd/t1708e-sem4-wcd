package controller;

import com.googlecode.objectify.ObjectifyService;
import entity.ActiveCode;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ConfirmEmailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/confirm-email.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String code = req.getParameter("code");
        ActiveCode activeCode = ofy().load().type(ActiveCode.class).id(code).now();
        if (activeCode.getStatus() == 1 || checkExpire(activeCode)) {
            resp.getWriter().println("Confirm email successfully!");
            resp.sendRedirect("/login");
        } else {
            resp.getWriter().println("Your active code has expired!");
        }
    }

    public boolean checkExpire(ActiveCode activeCode){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 15);
        long expireTime = activeCode.getCreatedAtMLS() + cal.getTimeInMillis();
        if (expireTime > Calendar.getInstance().getTimeInMillis()){
            return true;
        }
        return false;
    }
}
