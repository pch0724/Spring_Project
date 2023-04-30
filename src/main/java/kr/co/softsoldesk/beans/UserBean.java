package kr.co.softsoldesk.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {

	private int user_idx;
	
	@Size(min=2, max=4)
	@Pattern(regexp = "[가-힣]*")
	private String user_name;
	
	
	@Size(min=4, max= 20)
	@Pattern(regexp = "[a-zA-Z0-9]*") // 영문(대소문자) 또는 숫자 4~20자 
	private String user_id;
	
	@Size(min=4, max= 20)
	@Pattern(regexp = "[a-zA-Z0-9]*") // 영문(대소문자) 또는 숫자 4~20자
	private String user_pw;
	
	@Size(min=4, max= 20)
	@Pattern(regexp = "[a-zA-Z0-9]*") // 영문(대소문자) 또는 숫자 4~20자
	private String user_pw2;
	
	private boolean userIdExist;
	private boolean userLogin;
	
	public UserBean() {
		this.userIdExist=false;
		this.userLogin=false;
	}
}
