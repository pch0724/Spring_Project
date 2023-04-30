package kr.co.softsoldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.BoardInfoBean;
import kr.co.softsoldesk.mapper.TopMenuMapper;
import kr.co.softsoldesk.mapper.UserMapper;

@Repository
public class TopMenuDAO {
	
	@Autowired
	private TopMenuMapper topMenuMapper;
	
	
	//========================================================================
	
	public List<BoardInfoBean> getTopMenuList() {
		
		List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList();
		
		return topMenuList;
	}
	
	
	
	
}
