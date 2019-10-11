package controller;

import admin.CrawlerSourceController;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import entity.Article;
import entity.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class ArticleController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ArticleController.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/article/add").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String content = req.getParameter("content");
        String strCategoryId = req.getParameter("categoryId");
        long categoryId = 0;
        try{
            categoryId = Long.parseLong(strCategoryId);
        } catch (NumberFormatException ex){
            LOGGER.warning("Can not parse categoryId");
            LOGGER.warning(ex.getMessage());
        }
        List<Ref<Category>> listCategory = new ArrayList<Ref<Category>>();

        Article article = Article.Builder.anArticle()
                .withUrl(url)
                .withTitle(title)
                .withContent(content)
                .build();
    }
}
