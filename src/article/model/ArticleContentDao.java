package article.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class ArticleContentDao {
	private static final ArticleContentDao instance = new ArticleContentDao();

	public static ArticleContentDao getinstance() {
		return instance;
	}

	private ArticleContentDao(){}
	
	//insert
	public int insert(Connection con, ArticleContent content){
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = con.prepareStatement("insert into article_content(article_no, content) values(?,?)");
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
		return res;
	}
	
	// get content (select by no
	public ArticleContent getContentByNo(Connection con, int no){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleContent content = null;
		
		try {
			pstmt = con.prepareStatement("select * from article_content where article_no = ?");
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				content = new ArticleContent(rs.getInt("article_no"), rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return content;
	}
	
	//update content by no
	public void updateContentByNo(Connection con, String content, int no){
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement("update article_content set content = ? where article_no = ?");
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	//delete content
	public void deleteContentByNo(Connection con, int no){
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement("delete from article_content where article_no = ?");
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
}
