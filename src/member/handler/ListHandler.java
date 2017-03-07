package member.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.model.Member;
import member.model.MemberDao;
import mvc.controller.CommandHandler;

public class ListHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();
			
			List<Member> list = null;
			list = dao.selectAllMember(con);
			req.setAttribute("viewList", list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(con);
		}
		
		return "/WEB-INF/view/listView.jsp";
	}

}
