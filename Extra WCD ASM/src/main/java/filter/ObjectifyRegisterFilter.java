package filter;

import com.googlecode.objectify.ObjectifyService;
import entity.Account;
import entity.ActiveCode;

import javax.servlet.*;
import java.io.IOException;

public class ObjectifyRegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ObjectifyService.register(ActiveCode.class);
        ObjectifyService.register(Account.class);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
