package kr.co.softsoldesk.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.co.softsoldesk.beans.BoardInfoBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor{
	                                       // autowired 안먹음
	private TopMenuService topMenuService; 
	private UserBean loginUserBean;
	
	public TopMenuInterceptor(TopMenuService topMenuService, UserBean loginUserBean) {
		this.topMenuService = topMenuService;
		this.loginUserBean = loginUserBean;
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		return true;
	}
	
	
}
