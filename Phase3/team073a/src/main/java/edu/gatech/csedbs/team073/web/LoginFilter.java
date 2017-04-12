package edu.gatech.csedbs.team073.web;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.lang3.StringUtils;

import edu.gatech.csedbs.team073.model.User;
/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
		dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
					, 
		urlPatterns = { 
				"/LoginFilter", 
				"/"
		})
public class LoginFilter implements Filter {

	private static final String USER_DATA = "user";	
	protected FilterConfig config;
	protected ServletContext context;
	
	
	protected String contextPath = null;
	protected String indexPage = "/loginForm";
	protected String nextPage = null;	
	

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String logoutAction = (String) request.getParameter("action");
		HttpSession session = request.getSession(false);			
		contextPath = request.getContextPath();
		if (null != contextPath) {
			nextPage = contextPath;	
		}
		
		//context.log(request.getRequestedSessionId());
		
		if ((!StringUtils.isBlank(request.getRequestedSessionId())) && (!request.isRequestedSessionIdValid())
		        && (!StringUtils.containsIgnoreCase(request.getRequestURI(),"/public/"))
		        && (!StringUtils.containsIgnoreCase(request.getRequestURI(),"login"))) {
		
		    if (null != contextPath) {
				nextPage = contextPath + "/loginForm";
			} else {
				nextPage = "/loginForm";
			}
			
			//context.log("*** User not authenticated redirecting to SessionTimeout.jsp");		
			response.sendRedirect(response.encodeRedirectURL(nextPage));
			return;
		}
		
		if (session != null) {
		    
		    User user = (User) session.getAttribute(USER_DATA);
			
			
			
			if (StringUtils.equalsIgnoreCase("LOGOUT",logoutAction)) {
			    if (null != contextPath) {
			        nextPage = contextPath + "/loginForm?action=logout";				        
			    } else {
			        nextPage = "/loginForm?action=logout";
			    }
				//request.getRequestDispatcher().forward(req,resp);
				response.sendRedirect(response.encodeRedirectURL(nextPage));
				return;
			}
					
			if ((null == user) && (!StringUtils.containsIgnoreCase(request.getRequestURI(),"login"))
			        && (!StringUtils.containsIgnoreCase(request.getRequestURI(),"changePassword"))
			        && (!StringUtils.containsIgnoreCase(request.getRequestURI(),"contact"))
			        && (!StringUtils.containsIgnoreCase(request.getRequestURI(),"SystemUnavailable"))) {
			    nextPage = nextPage + "/loginForm";
				//context.log(" User not found redirecting to login.jsp " + nextPage + " " + request.getRequestURI());				
				response.sendRedirect(response.encodeRedirectURL(nextPage));
				return;
				
				 
			}
						
			if (null != user) {
				if(user.getSiteId() > 0) {
					chain.doFilter(req, resp);
					return;
				} else {
					if ((user.getSiteId() == 0) &&  StringUtils.containsIgnoreCase(request.getRequestURI(),"login")) {
						chain.doFilter(req, resp);
						return;
					}
					if (null != contextPath) {
						nextPage = contextPath + "/loginForm";
					} else {
						nextPage = "/loginForm";
					}
					
					//context.log("*** User not authenticated redirecting to LoginForm.jsp");		
					response.sendRedirect(response.encodeRedirectURL(nextPage));
									
				}
			}
			

		} else {
		    //context.log(request.getRequestURI());
		    if (!StringUtils.containsIgnoreCase(request.getRequestURI(),"login") && 
		            !StringUtils.containsIgnoreCase(request.getRequestURI(),"contact") &&
		            !StringUtils.containsIgnoreCase(request.getRequestURI(),"SessionTimeout") &&
		            !StringUtils.containsOnly(request.getRequestURI(),contextPath)) {
		        nextPage = nextPage + "/loginForm";
		        //context.log("**** " + request.getRequestURI());
		        //context.log(" User not logged on redirecting to LoginForm.jsp " + nextPage + " " + request.getRequestURI());				
		        response.sendRedirect(response.encodeRedirectURL(nextPage));
		        return;			        
		    } 
		    
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig; // In case it is needed by subclass.
		//context = config.getServletContext();	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
