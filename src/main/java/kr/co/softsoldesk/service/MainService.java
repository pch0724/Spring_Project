package kr.co.softsoldesk.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.dao.BoardDao;

@Service
public class MainService {

	@Autowired
	private BoardDao boardDao;
	
	
	public List<ContentBean> getMainList(int board_info_idx){
		RowBounds rowBounds = new RowBounds(0, 5); // 글을 가져올 때 행의 갯수
		
		return boardDao.getContentList(board_info_idx, rowBounds);
	}
	
}
