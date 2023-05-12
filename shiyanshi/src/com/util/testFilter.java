// 
// 
// 

package com.util;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;

public class testFilter extends HttpServlet implements Filter
{
    private FilterConfig filterConfig;
    
    public void init(final FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) {
        try {
            ((HttpServletResponse)response).sendRedirect("http://localhost:808/tbm/index.jsp");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void destroy() {
    }
}
