package kr.co.softsoldesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.BoardInfoBean;
import kr.co.softsoldesk.dao.TopMenuDAO;
import kr.co.softsoldesk.dao.UserDAO;

@Service
public class TopMenuService {
	
	@Autowired
	private TopMenuDAO topMenuDao;
	
	
	
	//=========================================================
	
	public List<BoardInfoBean> getTopMenuList() {
		
		List<BoardInfoBean> topMenuList = topMenuDao.getTopMenuList();
		
		return topMenuList;
	}
	
}
