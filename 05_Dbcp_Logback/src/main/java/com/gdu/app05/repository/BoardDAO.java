package com.gdu.app05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.gdu.app05.domain.BoardDTO;


// @Repository 대신 AppConfig에 @Bean이 등록되어 있다.
public class BoardDAO {

	// dbcp 방식 (jdbc + DataSource)
	private Connection con;			// DB랑 연결하기위해쓰는 객체
	private PreparedStatement ps;	// 쿼리문 실행하는객체
	private ResultSet rs;			// 쿼리문 실행 결과를 담아주는 객체
	private String sql;				// sql문을 사용하기위해 쓰는 객체
	private DataSource dataSource;  // connection을 담당하는 폴이다.
	
	// BoardDAO 생성자 ( 역할 : webapp/META-INF/context.xml에 작성한 <Resource> 태그 읽기)
	public BoardDAO() {
		// JNDI 방식 : <Resource> 태그의 name 속성으로 Resource를 읽어 들이는 방식 ( context.xml에 있는 name을 말함)
		try {
			Context context = new InitialContext();	
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/GDJ61");		// java:comp/env/ 자바톰캣 사용할 때 정해진 이름 / jdbc/GDJ61 실제 리소스의 경로이름 / 리소스 읽어들이면 결과는 데이타 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// private 메소드  (BoardDAO 클래스 내부에서만 사용 )
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();  // 사용한 Connection을 dataSource에게 반납한다.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DAO 메소드 (BoardServiceImpl 클래스에서 사용하는 메소드)
	// DAO 메소드명
	// 방법1. BoardServiceImpl의 메소드와 이름을 맞춤
	// 방법2. DB 친화적으로 새 이름을 부여 (이론상 권장)
	
	// 1. 목록 ( 반환 )
	public List<BoardDTO> selectBoardList(){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = dataSource.getConnection();  // dataSource가 관리하는 Connection 8개 중 하나를 대여한다.
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				list.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	
	// 2. 상세
	public BoardDTO selectBoardByNo(int board_no) {
		BoardDTO board = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no); // sql 문에 있는 ?(변수)에 값을 채워주는 메소드
			rs = ps.executeQuery();
			if(rs.next()) {
				// null 값으로 선언만 해둔 board 이다.
				board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return board;
	}
	
	// 3. 삽입
	public int insertBoard(BoardDTO board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	
	// 4. 수정
	public int updateBoard(BoardDTO board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFIED_AT = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE BOARD_NO = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	
	
	// 5. 삭제
	public int deleteBoard(int board_no) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
}
