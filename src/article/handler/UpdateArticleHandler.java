package article.handler;

import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.model.ArticleContentDao;
import article.model.ArticleDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;

public class UpdateArticleHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//post
		if(req.getMethod().equalsIgnoreCase("post")){
			Connection con = null;
			
			try{
				con = ConnectionProvider.getConnection();
				
				//제목, 내용, 수정날짜
				int no = Integer.parseInt(req.getParameter("no"));
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				Date modifiedDate = new Date();
				
				System.out.println(title);
				System.out.println(content);
				
				ArticleDao dao = ArticleDao.getInstance();
				ArticleContentDao contentDao = ArticleContentDao.getinstance();
				Article article = dao.selectByNo(con, no);
				article.setModifiedDate(modifiedDate);
				article.setTitle(title);
				
				dao.updateByNo(con, article, no);
				contentDao.updateContentByNo(con, content, no);
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				JdbcUtil.close(con);
			}
		}
		return "listArticle.do";
	}

}
