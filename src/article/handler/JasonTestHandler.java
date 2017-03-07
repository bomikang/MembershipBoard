package article.handler;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import article.model.Article;
import article.model.ArticleDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;

public class JasonTestHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("post")) {
			int articleNo = Integer.parseInt(req.getParameter("no"));
			
			Connection con = null;
			
			try {
				con = ConnectionProvider.getConnection();
				
				ArticleDao dao = ArticleDao.getInstance();
				Article article = dao.selectByNo(con, articleNo);
				
				//밑 두 줄만 있으면 json형태로 바꿔줌
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(article); //Map과 ArrayList도 json형태로 바꿀 수 있음
				
				res.setContentType("application/json;charset=utf-8");
				PrintWriter pw = res.getWriter();
				pw.print(json);
				pw.flush();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				JdbcUtil.close(con);
			}
		}
		return null;
	}

}
