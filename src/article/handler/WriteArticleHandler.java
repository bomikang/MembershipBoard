package article.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.model.ArticleContent;
import article.model.ArticleContentDao;
import article.model.ArticleDao;
import article.model.Writer;
import auth.model.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;

public class WriteArticleHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")){
			return "/WEB-INF/view/articleForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")){
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			User user = (User) req.getSession(false).getAttribute("auth");
			
			Connection con = null;
			
			try{
				con = ConnectionProvider.getConnection();
				con.setAutoCommit(false); //정상적으로 진행이 되었을 때에만 계속 진행되도록
				
				Date date = new Date();
				Article article = new Article(0, new Writer(user.getId(), user.getName()), title, date, date, 0);
				
				ArticleDao articleDao = ArticleDao.getInstance();
				int number = articleDao.insert(con, article);
				
				if (number < 0) {
					throw new RuntimeException();
				}
				req.getSession().setAttribute("addBoardNo", number);
				ArticleContent articleContent = new ArticleContent(number, content);
				
				ArticleContentDao contentDao = ArticleContentDao.getinstance();
				contentDao.insert(con, articleContent);
				
				con.commit();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(con);
			}
			
			return "/WEB-INF/view/articleSuccess.jsp";
		}
		return null;
	}

}
