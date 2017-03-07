package auth.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class LogOutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//getSession(true) : 세션이 이미 있는지 확인하여 이미 있다면 그 세션을 반환시키고, 없으면 새로운 세션을 생성한다.
		//getSession(false): 세션이 있다면 그 세션을 리턴하지만, 세션이 존재하지 않으면 null을 리턴
		HttpSession session = req.getSession(false);
		
		if(session != null){ //세션이 존재한다면
			session.invalidate(); //session삭제 *invalidate : 무효화하다
		}
		return "index.jsp";
	}
}
