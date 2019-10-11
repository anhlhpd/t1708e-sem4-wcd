package admin;

import com.google.gson.Gson;
import entity.CrawlerSource;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class PreviewSpecialContentController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PreviewSpecialContentController.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/crawler-source/special-content.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String requestBody = StringUtil.convertInputStreamToString(req.getInputStream());
        JSONObject jsonObject = new JSONObject(requestBody);
        String url = jsonObject.getString("url");
        String titleSelector = jsonObject.getString("titleSelector");
        String contentSelector = jsonObject.getString("contentSelector");

        Document document = Jsoup.connect(url).ignoreContentType(true).get();
        String title = document.select(titleSelector).text();
        String content = document.select(contentSelector).html();
        HashMap<String, String> responseObject = new HashMap<>();
        responseObject.put("url", url);
        responseObject.put("title", title);
        responseObject.put("content", content);
        resp.getWriter().println(new Gson().toJson(responseObject));
    }
}
