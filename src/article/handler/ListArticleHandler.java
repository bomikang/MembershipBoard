package article.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.model.ArticleDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;

public class ListArticleHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection con = null;
		List<Article> list = null;
		
		try{
			con = ConnectionProvider.getConnection();
			ArticleDao dao = ArticleDao.getInstance();
			list = dao.selectAll(con);
			
			req.setAttribute("viewContentList", list);
			return "/WEB-INF/view/articleList.jsp";
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con);
		}
		return "/WEB-INF/view/articleList.jsp";
	}

}
