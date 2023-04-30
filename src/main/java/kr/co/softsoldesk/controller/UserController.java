package kr.co.softsoldesk.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.UserService;
import kr.co.softsoldesk.validator.UserValidator;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Resource(name = "loginUserBean")
	   private UserBean loginUserBean; // session영역
	//---------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
						@RequestParam(value = "fail", defaultValue = "false") boolean fail, Model model) {
		// fail에 true면 실패했다. fail에 true면 성공 그러므로 처음에는 실패하지 않은걸로 간주
		model.addAttribute("fail", fail);
		
		return "user/login";
		
	}
	
	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, BindingResult result) {
		//model.setattribute
		if(result.hasErrors()) {
			return "user/login";
		}
		
		userService.getLoginUserInfo(tempLoginUserBean);
	
		if(loginUserBean.isUserLogin() == true) {
			return "user/login_success";
		}else {
			return "user/login_fail";
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		
		return "user/join";
	}	
	
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/join";
		}
		userService.addUserInfo(joinUserBean);
		
		return "user/join_success";
	}
	//---------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
		
		userService.getModifyUserInfo(modifyUserBean);
		
		return "user/modify";
	}
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/modify";
		}
		userService.modifyUserInfo(modifyUserBean);
		
		return "user/modify_success";
	}
	//---------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/logout")
	public String logout() {
		
		loginUserBean.setUserLogin(false);
		
		return "user/logout";
	}
	//---------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/not_login")
	public String not_login() {
		
		
		return "user/not_login";
	}
	//---------------------------------------------------------------------------------------------------------------------------
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		UserValidator validator = new UserValidator();
		
		binder.addValidators(validator);
		
	}
	
}