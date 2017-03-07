package auth.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.model.Member;
import member.model.MemberDao;
import mvc.controller.CommandHandler;

public class ChangePasswordHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//get
		if(req.getMethod().equalsIgnoreCase("get")){
			return "/WEB-INF/view/changePasswordForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")){
			Connection con = null;
			
			try{
				con = ConnectionProvider.getConnection();
				
				User user = (User) req.getSession(false).getAttribute("auth");
				
				String newPassword = req.getParameter("newPassword");
				String password = req.getParameter("password");
				
				
				//member is not matches req.password
				MemberDao memberDao = MemberDao.getInstance();
				Member member = memberDao.selectById(con, user.getId());
				if(!member.matchPassword(password)){
					req.setAttribute("pwdNotMatch", true);
					return "/WEB-INF/view/changePasswordForm.jsp";
				}
				
				MemberDao dao = MemberDao.getInstance();
				dao.changePassword(con, newPassword, user);
				return "/WEB-INF/view/changePasswordSuccess.jsp";
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(con);
			}
		}
		return null;
	}

}
