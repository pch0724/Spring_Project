package kr.co.softsoldesk.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.dao.UserDAO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean; // loginUserBean은 session 영역

	public boolean checkUserIdExist(String user_id) {
		String user_name = userDao.checkUserIdExist(user_id);

		if (user_name == null) {
			return true;
		} else {
			return false;
		}
	}

	// 회원가입
	public void addUserInfo(UserBean joinUserBean) {
		userDao.addUserInfo(joinUserBean);
	}

	// 로그인
	public void getLoginUserInfo(UserBean tempLoginUserBean) {

		UserBean tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);

		// 가져온 값이 없을시 예외처리
		if (tempLoginUserBean2 != null) {
			// 자체가 로그인 성공
			// request영역(tempLoginUserBean2) = session영역은(loginUserBean)
			loginUserBean.setUser_idx(tempLoginUserBean2.getUser_idx());
			loginUserBean.setUser_name(tempLoginUserBean2.getUser_name());
			loginUserBean.setUserLogin(true); // 로그인성공
		}

	}
	
	// modifyUserBean : modify.jsp 에서 주입받은 정보를 객체에 담아서 가져옴
	public void getModifyUserInfo(UserBean modifyUserBean) {
		UserBean tempModifyUserBean=userDao.getModifyUserInfo(loginUserBean.getUser_idx());
		
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		// update시 필요
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
		
	}
	public void modifyUserInfo(UserBean modifyUserBean) {
		
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
		
		userDao.modifyUserInfo(modifyUserBean);
	}
}
