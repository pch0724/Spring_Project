package kr.co.softsoldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softsoldesk.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{
	
	private UserBean loginUserBean;
	
	public CheckLoginInterceptor(UserBean loginUserBean) {
		this.loginUserBean=loginUserBean;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인을 하지 않았다면
		if(loginUserBean.isUserLogin() == false) {
			// 로그인하지 않은 상태에서의 URL 호출
			String contextpath=request.getContextPath();
			response.sendRedirect(contextpath+"/user/not_login");
			// 다음 단계로 이동하지 않음
			return false;
		}
			// 로그인 되어 있는 상태
		return true;
	}
	
	

}
