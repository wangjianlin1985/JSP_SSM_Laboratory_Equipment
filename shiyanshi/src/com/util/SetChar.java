// 
// 
// 

package com.util;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;

public class SetChar extends HttpServlet implements Filter
{
    private FilterConfig filterConfig;
    
    public void init(final FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) {
        try {
            ((HttpServletResponse)response).setHeader("Pragma", "No-cache");
            ((HttpServletResponse)response).setHeader("Cache-Control", "no-cache");
            ((HttpServletResponse)response).setHeader("Expires", "0");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            filterChain.doFilter(request, response);
        }
        catch (ServletException sx) {
            this.filterConfig.getServletContext().log(sx.getMessage());
        }
        catch (IOException iox) {
            this.filterConfig.getServletContext().log(iox.getMessage());
        }
    }
    
    public void destroy() {
    }
}
