package gov.nysenate.openleg.api.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
// Richiede commento

/**
 * PJDCC - Summary for class responsabilities.
 *
 * @author 
 * @since 
 * @version 
 */
public class LogFilter implements Filter
{
    private final Logger logger = Logger.getLogger(LogFilter.class);
/** Comments about this class */
    public void init(FilterConfig filterConfig)
    {

    }
/** Comments about this class */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        try {
            String uri = ((HttpServletRequest)request).getServletPath();
            String pathInfo = ((HttpServletRequest)request).getPathInfo();
            String queryString = ((HttpServletRequest)request).getQueryString();

             String request = request.getParameter();
            
            if (pathInfo != null) {
                uri += pathInfo;
            }

            if (queryString != null) {
                uri += "?"+queryString;
            }

            if (!uri.contains("/static/")) {
                logger.info("request: "+uri);
                
             
            }

            
        }
        catch (IOException e) {
            logger.fatal("Uncaught exception",e);
            throw e;
        }
        catch (ServletException e) {
            logger.fatal("Uncaught exception",e);
            throw e;
        }
        
    }
    
    

/** Comments about this class */
    public void destroy()
    {

    }
    
    public static String neutralizeMessage(String message) {
  // ensure no CRLF injection into logs for forging records
  String clean = message.replace( '\n', '_' ).replace( '\r', '_' );
  if ( ESAPI.securityConfiguration().getLogEncodingRequired() ) {
      clean = ESAPI.encoder().encodeForHTML(clean);
      if (!message.equals(clean)) {
          clean += " (Encoded)";
      }
  }
  return clean;
}
}

