package util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class StringUtil {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("language");

    public static String getBundle(String key) {
        return resourceBundle.getString(key);
    }

    public static void changeLanguage(HttpServletRequest request) {
        resourceBundle = ResourceBundle.getBundle("language");
        ServletContext context = request.getSession().getServletContext();
        context.setAttribute("bundle", resourceBundle);
    }
}
