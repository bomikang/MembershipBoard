package member.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.model.Member;
import member.model.MemberDao;
import mvc.controller.CommandHandler;

public class JoinHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")){
			return "/WEB-INF/view/joinForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")){
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			
			Member member = new Member(id, name, password, new Date());
			
			Connection con = null;
			try {
				con = ConnectionProvider.getConnection();
				
				MemberDao dao = MemberDao.getInstance();
				dao.insert(con, member);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				JdbcUtil.close(con);
			}
			
			return "/WEB-INF/view/joinSuccess.jsp";
		}
		return null;
	}

}
