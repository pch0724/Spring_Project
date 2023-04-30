package kr.co.softsoldesk.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.mapper.UserMapper;

@Repository
public class UserDAO {
	
	@Autowired
	private UserMapper userMapper; 
	//============================================================
	// 아이디 중복체크
	public String checkUserIdExist(String User_id) {
		return userMapper.checkUserIdExist(User_id);
	}
	// 회원가입
	public void addUserInfo(UserBean joinUserBean) {
		userMapper.addUserInfo(joinUserBean);
	}
	// 로그인
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
		return userMapper.getLoginUserInfo(tempLoginUserBean);
	}
	// 사용자 정보수정하기위해 값 가져오기
	public UserBean getModifyUserInfo(int user_idx) {
		return userMapper.getModifyUserInfo(user_idx);
	}
	
	public void modifyUserInfo(UserBean modifyUserBean) {
		userMapper.modifyUserInfo(modifyUserBean);
	}
}
