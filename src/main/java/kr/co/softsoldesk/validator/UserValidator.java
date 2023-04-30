package kr.co.softsoldesk.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.softsoldesk.beans.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserBean userBean = (UserBean) target;

		String beanName = errors.getObjectName();
		// System.out.println(beanName);

		// 모든 비번 일치성 확인 또는 수정시
		if (beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {

			if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NoEquals");
				errors.rejectValue("user_pw2", "NoEquals");
			}
			if (beanName.equals("joinUserBean")) {
				if (userBean.isUserIdExist() == false) {
					errors.rejectValue("user_id", "DontCheckUserIdExist");
				}
			}
		}
	} // validate

} // class
