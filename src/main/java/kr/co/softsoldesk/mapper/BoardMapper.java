package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.softsoldesk.beans.BoardInfoBean;
import kr.co.softsoldesk.beans.ContentBean;

public interface BoardMapper {

	// jdbcType=VARCHAR : NULL값을 허용하도록 처리
	// MyBatis에서는 NULL 값도 String으로 인지한다
	// statement : "select content_seq.nextval from dual";
	// keyProperty : 시퀀스 값을 어느 속성에 담을 것인지
	// before = true : 아래의 쿼리문이 실행되기 전에 실행한다는 의미
	// resultType : 결과 값의 데이터 유형
	@SelectKey(statement = "select content_seq.nextval from dual", keyProperty = "content_idx", before = true, resultType = int.class)
	@Insert("insert into content_table(content_idx, content_subject, content_text, "
			+ "content_file, content_writer_idx, content_board_idx, content_date) "
			+ "values (#{content_idx}, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, "
			+ "#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);

	@Select("select board_info_name from board_info_table where board_info_idx=#{board_info_idx}")
	String getBoardInfoName(int board_info_idx);
	
	@Select("select a1.content_idx, a1.content_subject, a2.user_name as content_writer_name, "
			+ "to_char(a1.content_date, 'yyyy-mm-dd') as content_date "
			+ "from content_table a1, user_table a2 "
			+ "where a1.content_writer_idx = a2.user_idx(+) "
			+ "and a1.content_board_idx = #{board_info_idx} "
			+ "order by a1.content_idx desc")
	List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds);
	
	@Select("select a2.user_name as content_writer_name, "
			+ "to_char(a1.content_date, 'yyyy-mm-dd') as content_date, "
			+ "a1.content_subject, a1.content_text, a1.content_file, a1.content_writer_idx "
			+ "from content_table a1, user_table a2 "
			+ "where a1.content_writer_idx = a2.user_idx(+) "
			+ "and a1.content_idx = #{content_idx}")
	ContentBean getContentInfo(int content_idx);

	@Update("update content_table set content_subject = #{content_subject}, content_text =#{content_text}, content_file = #{content_file, jdbcType = VARCHAR} "
			+ "where content_idx = #{content_idx}")
	void modifyContentInfo(ContentBean modifyContentBean);
	
	@Delete("delete from content_table where content_idx =#{content_idx}")
	void deleteContentInfo(int content_idx);
	
	// 게시물 갯수 확인
	@Select("select count(*) from content_table where content_board_idx = #{content_board_idx}")
	int getContentCnt(int content_board_idx);
	
}
