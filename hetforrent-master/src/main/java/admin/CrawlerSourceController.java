package admin;

import entity.CrawlerSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class CrawlerSourceController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CrawlerSourceController.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/crawler-source/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        String linkSelector = req.getParameter("linkSelector");
        String strLinkLimit = req.getParameter("linkLimit");
        int linkLimit = 10;
        try {
            linkLimit = Integer.parseInt(strLinkLimit);
        } catch (NumberFormatException e) {

        }
        String titleSelector = req.getParameter("titleSelector");
        String descriptionSelector = req.getParameter("descriptionSelector");
        String contentSelector = req.getParameter("contentSelector");
        String authorSelector = req.getParameter("authorSelector");
        // validate first.
        CrawlerSource crawlerSource = CrawlerSource.Builder.aCrawlerSource()
                .withUrl(url)
                .withLinkSelector(linkSelector)
                .withLinkLimit(linkLimit)
                .withTitleSelector(titleSelector)
                .withDescriptionSelector(descriptionSelector)
                .withContentSelector(contentSelector)
                .withAuthorSelector(authorSelector)
                .build();
        if (crawlerSource.isValid()) {
            ofy().save().entity(crawlerSource).now();
        }
        resp.sendRedirect("/admin/crawler-source/list");
    }
}
