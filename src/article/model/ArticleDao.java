package article.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class ArticleDao {
	private static final ArticleDao instance = new ArticleDao();

	public static ArticleDao getInstance() {
		return instance;
	}
	
	private ArticleDao(){}
	
	//insert
	public int insert(Connection con, Article article) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "insert into article(writer_id, writer_name, title, regdate, moddate, read_cnt) values(?,?,?,?,?,0)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  article.getWriter().getId());
			pstmt.setString(2, article.getWriter().getName());
			pstmt.setString(3, article.getTitle());
			pstmt.setTimestamp(4, new Timestamp(article.getRegDate().getTime()));
			pstmt.setTimestamp(5, new Timestamp(article.getModifiedDate().getTime()));
			int count = pstmt.executeUpdate();
			
			//1이면 성공
			if(count > 0){ 
				stmt = con.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from article"); //마지막 번째 번호 받아오기
				if(rs.next()){
					int newNo = rs.getInt(1);
					return newNo;
				}
			}
			return -1;
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	//select all
	public List<Article> selectAll(Connection con){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Article> list = new ArrayList<>();
		
		try {
			pstmt = con.prepareStatement("select * from article");
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Writer writer = new Writer(rs.getString("writer_id"), rs.getString("writer_name"));
				Timestamp regDate = rs.getTimestamp("regdate");
				Timestamp modDate = rs.getTimestamp("modDate");
				
				Article article = new Article(rs.getInt("article_no"), writer, rs.getString("title"),
												regDate, modDate, rs.getInt("read_cnt"));
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}
	
	// (get article) select by article_no
	public Article selectByNo(Connection con, int no){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Article article = null;
		
		try {
			pstmt = con.prepareStatement("select * from article where article_no = ?");
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				Writer writer = new Writer(rs.getString("writer_id"), rs.getString("writer_name"));
				Timestamp regDate = rs.getTimestamp("regdate");
				Timestamp modDate = rs.getTimestamp("moddate");
				
				article = new Article(rs.getInt("article_no"), writer, rs.getString("title"), regDate, modDate, rs.getInt("read_cnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return article;
	}
	
	//update by article_no
	public void updateByNo(Connection con, Article article, int no){
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement("update article set title = ?, moddate = ? where article_no = ?");
			pstmt.setString(1, article.getTitle());
			pstmt.setTimestamp(2, new Timestamp(article.getModifiedDate().getTime()));
			pstmt.setInt(3, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	//delete
	public void deleteByNo(Connection con, int no){
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement("delete from article where article_no = ?");
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
}
