package kr.co.softsoldesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.softsoldesk.beans.UserBean;


// 프로젝트 Bean
@Configuration
public class RootAppContext {
	//Oracle
	@Bean("loginUserBean")
	@SessionScope
	public UserBean loginUserBean() {
		
		return new UserBean();
	}

}
