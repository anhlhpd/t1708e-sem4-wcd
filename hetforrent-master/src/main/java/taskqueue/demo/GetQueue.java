package taskqueue.demo;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;
import com.google.gson.Gson;
import entity.Article;
import entity.CrawlerSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class GetQueue extends HttpServlet {

    private static Queue q = QueueFactory.getQueue("demo-queue");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskHandle> tasks = q.leaseTasks(10, TimeUnit.SECONDS, 1);
        if (tasks.size() > 0) {
            TaskHandle taskHandle = tasks.get(0);
            String articleObjectJson = new String(taskHandle.getPayload());
            Article article = new Gson().fromJson(articleObjectJson, Article.class);
            CrawlerSource crawlerSource = ofy().load().type(CrawlerSource.class).id(article.getSourceId()).now();
            if (crawlerSource == null) {
                return;
            }
            Document document = Jsoup.connect(article.getUrl()).ignoreContentType(true).get();
            String title = document.select(crawlerSource.getTitleSelector()).text();
            String description = document.select(crawlerSource.getDescriptionSelector()).text();
            String content = document.select(crawlerSource.getContentSelector()).text();
            String author = document.select(crawlerSource.getAuthorSelector()).text();
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(content);
            article.setAuthor(author);
            ofy().save().entity(article).now();
        }
    }
}
