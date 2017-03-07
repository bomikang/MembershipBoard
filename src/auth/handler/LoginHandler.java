package auth.handler;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.model.Member;
import member.model.MemberDao;
import mvc.controller.CommandHandler;

public class LoginHandler implements CommandHandler {
	private final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return FORM_VIEW;
		}else if(req.getMethod().equalsIgnoreCase("post")){
			String id = req.getParameter("id");
			String password = req.getParameter("password");
			
			Connection con = null;
			Member member = null;
			MemberDao dao = MemberDao.getInstance();
			
			try{
				con = ConnectionProvider.getConnection();
				member = dao.selectById(con, id);
				
				//member is not exist
				if(member == null){
					req.setAttribute("notJoin", true);
					return FORM_VIEW;
				}
				//member is not matches req.password
				if(!member.matchPassword(password)){
					req.setAttribute("idOrPwdNotMatch", true);
					return FORM_VIEW;
				}
				
				//session에 id 세팅
				User user = new  User(id, member.getName());
				req.getSession().setAttribute("auth", user);
				return "index.jsp"; //이건 index페이지라서 webcontent안에 맹금
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				JdbcUtil.close(con);
			}
		}
		return null;
	}

}
