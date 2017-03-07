package article.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticleContentDao;
import article.model.ArticleDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//post
		Connection con = null;
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		try {
			con = ConnectionProvider.getConnection();
			
			ArticleDao dao = ArticleDao.getInstance();
			ArticleContentDao contentDao = ArticleContentDao.getinstance();
			
			dao.deleteByNo(con, no);
			contentDao.deleteContentByNo(con, no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(con);
		}
		return "listArticle.do";
	}

}
