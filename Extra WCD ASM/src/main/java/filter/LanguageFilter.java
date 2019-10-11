package filter;

import util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LanguageFilter implements Filter {

    private static String currentLanguage = Locale.getDefault().getLanguage();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String language = (String) request.getSession().getAttribute("lang");
        if (language != null && !language.equals(currentLanguage)) {
            Locale.setDefault(new Locale(language));
            currentLanguage = language;
            StringUtil.changeLanguage(request);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
