/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.security;

import edu.hdu.lab.datasource.DynamicDataSourceHolder;
import edu.hdu.lab.security.exceptions.AuthorizationException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 拦截器
 * @author justin
 */
public class SecurityInterceptor
    implements HandlerInterceptor{
    
	private List<String> excludedUrls;

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}    

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//1.判断是否包含 配置文件中的URI后缀
		String requestUri = request.getRequestURI();
		for (String url : excludedUrls) {
			if (requestUri.endsWith(url)) {
				return true;
			}
		}
        
		// intercept
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			throw new AuthorizationException("Unauthorized Access Denied");
		} else {
//            if (session.getAttribute("place") != null)
//				DynamicDataSourceHolder.setDataSource((String) session.getAttribute("place"));
			return true;
		}        
    }

    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
    }

    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
    }
    
}
