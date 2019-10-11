package taskqueue;

import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.gson.Gson;
import entity.Article;
import entity.CrawlerSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddQueue extends HttpServlet {

    public static CrawlerSource crawlerSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Document document = Jsoup.connect(crawlerSource.getUrl()).ignoreContentType(true).get();
        Elements elements = document.select(crawlerSource.getLinkSelector());
        for (Element el :
                elements) {
            String link = el.attr("href").trim();
            Article article = Article.Builder.anArticle().withUrl(link).withSourceId(crawlerSource.getId()).build();
            q.add(TaskOptions.Builder.withMethod(TaskOptions.Method.PULL).payload(new Gson().toJson(article)));
        }
    }
}
