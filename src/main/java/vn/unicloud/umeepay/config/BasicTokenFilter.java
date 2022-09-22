package vn.unicloud.umeepay.config;

import lombok.extern.log4j.Log4j2;
import vn.unicloud.umeepay.utils.CommonUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class BasicTokenFilter implements Filter {

    private final String resource;
    private final String token;

    public BasicTokenFilter(String resource, String token) {
        this.resource = resource;
        this.token = token;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String basicToken = CommonUtils.getBasicToken(request);
        if (request.getRequestURI().startsWith(resource)) {
            if (basicToken != null && basicToken.equals(token)) {
                chain.doFilter(new DeleteAuthorization(request), res);
                return;
            }
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
