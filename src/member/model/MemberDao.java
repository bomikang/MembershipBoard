package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import auth.model.User;
import jdbc.JdbcUtil;

public class MemberDao {
	private static final MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}
	
	/*************insert*/
	public void insert(Connection con, Member member){
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement("insert into member values(?, ?, ?, ?)");
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setTimestamp(4, new Timestamp(member.getRegDate().getTime())); //member.getRegDate().getTime() = 밀리세컨드 값
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
	/*************select member by id*/
	public Member selectById(Connection con, String id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try {
			pstmt = con.prepareStatement("select * from member where memberid = ?");
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String memberId = rs.getString("memberid");
				String name = rs.getString("name");
				String password = rs.getString("password");
				Timestamp regDate = rs.getTimestamp("regDate");
				
				member = new Member(memberId, name, password, regDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return member;
	}
	/*************select all member*/
	public List<Member> selectAllMember(Connection con){
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		List<Member> list = new ArrayList<>();
		
		try {
			pstmt = con.prepareStatement("select * from member");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String memberId = rs.getString("memberid");
				String name = rs.getString("name");
				String password = rs.getString("password");
				Timestamp regDate = rs.getTimestamp("regDate");
				
				Member member = new Member(memberId, name, password, regDate);
				
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}
	/*************************change password*/
	public void changePassword(Connection con, String newPassword, User user){
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement("update member set password=? where memberid=?");
			pstmt.setString(1, newPassword);
			pstmt.setString(2, user.getId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
}
