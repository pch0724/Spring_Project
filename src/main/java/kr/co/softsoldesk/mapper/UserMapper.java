package kr.co.softsoldesk.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.softsoldesk.beans.UserBean;

public interface UserMapper {
	
	@Select("select user_name "
			+ "from user_table "
			+ "where user_id=#{user_id}")
	String checkUserIdExist(String user_id);	
	
	@Insert("insert into user_table(user_idx, user_name, user_id, user_pw) "
			+ "values(user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	void addUserInfo(UserBean joinUserBean);
	
	@Select("select user_idx, user_name "
			+ "from user_table "
			+ "where user_id=#{user_id} and user_pw=#{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean); // tempLoginUserBean은 request영역

	@Select("select user_id, user_name "
			+ "from user_table "
			+ "where user_idx=#{user_idx}")
	UserBean getModifyUserInfo(int user_idx);
	// 넣을떄 void
	@Update("update user_table "
			+ "set user_pw=#{user_pw} "
			+ "where user_idx=#{user_idx}")
	void modifyUserInfo(UserBean modifyUserbean);
}
