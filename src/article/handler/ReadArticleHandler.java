package article.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.model.ArticleContent;
import article.model.ArticleContentDao;
import article.model.ArticleDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;

public class ReadArticleHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection con = null;
		int no = Integer.parseInt(req.getParameter("no"));
		
		try{
			con = ConnectionProvider.getConnection();
			
			ArticleDao articleDao = ArticleDao.getInstance();
			ArticleContentDao contentDao = ArticleContentDao.getinstance();
			
			Article article = articleDao.selectByNo(con, no); //제목, 작성자
			ArticleContent content = contentDao.getContentByNo(con, no); //내용
			
			req.setAttribute("subjectAndWriter", article);
			req.setAttribute("boardContent", content);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con);
		}
		
		//get
		if(req.getMethod().equalsIgnoreCase("get")){
			return "/WEB-INF/view/boardContentView.jsp";
		}
		//post
		else if(req.getMethod().equalsIgnoreCase("post")){
			return "/WEB-INF/view/boardUpdate.jsp";
		}
		
		return null;
	}

}
