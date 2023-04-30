package kr.co.softsoldesk.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.PageBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Value("${page.listcnt}")
	private int page_listcnt;
	
	@Value("${page.paginationcnt}")
	private int page_paginationcnt;
	
	
	private String saveUploadFile(MultipartFile upload_file) {
		
		String file_name =  System.currentTimeMillis() + "_" + 
							FilenameUtils.getBaseName(upload_file.getOriginalFilename()) + "." +
							FilenameUtils.getExtension(upload_file.getOriginalFilename());
		try {
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return file_name;
	}
	
	public void addContentInfo(ContentBean writeContentBean) {
		
		MultipartFile upload_file = writeContentBean.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			System.out.println(file_name);
			writeContentBean.setContent_file(file_name);
		}
		
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
		
		boardDao.addContentInfo(writeContentBean);
	}
	
	public String getBoardInfoName(int board_info_idx) {
		return boardDao.getBoardInfoName(board_info_idx);
	}
	
	public List<ContentBean> getContentList(int board_info_idx, int page){
		/*
		  1 -> 0 
		  2 -> 10 
		  3 -> 20
		 */
		
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt); // 글을 가져올 때 행의 갯수
		
		return boardDao.getContentList(board_info_idx, rowBounds);
	}
	
	public ContentBean getContentInfo(int content_idx) {
		return boardDao.getContentInfo(content_idx);
	}
	
	public void modifyContentInfo(ContentBean modifyContentBean) {
		// 첨부파일 처리
		MultipartFile upload_file = modifyContentBean.getUpload_file();
		
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			//System.out.println(file_name);
			modifyContentBean.setContent_file(file_name);
		}
		//=============================================================
		boardDao.modifyContentInfo(modifyContentBean);
		
	}
	
	public void deleteContentInfo(int content_idx) {
		boardDao.deleteContentInfo(content_idx);
	};
	
	public PageBean getContentCnt(int content_board_idx, int currentPage) {
	      //전체글의 갯수
	      int content_cnt = boardDao.getContentCnt(content_board_idx);
	      
	      PageBean pageBean = new PageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt);
	      
	      return pageBean;
	   }
	
}
