package service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import sun.misc.BASE64Encoder;
import utility.Common;

public class UserService extends Common {
	private String encryptWithMd5(String s){
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			BASE64Encoder base64Encoder = new sun.misc.BASE64Encoder();
			return base64Encoder.encode(messageDigest.digest(s.getBytes("utf-8")));
		}catch(Exception e){
			return null;
		}
	}
	public boolean register(String userid ,String password){
		
		try{
			if(userid == null || password==null||"".equals(password)
					||"".equals(userid)){
				return false;
			}
		String sql = "insert into user(uid, password) values(?,?)";
		execSQL(sql,userid,encryptWithMd5(password));
		return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean login(String userid, String password){
		
		try{
			if(userid == null || password==null||"".equals(password)
					||"".equals(userid)){
				return false;
			}
			String passwordMd5 = encryptWithMd5(password);
			execSQL("select password from user where uid = ?",userid);
			if(rs.next()){
				String pw = rs.getString("password");
				if(passwordMd5.equals(pw)){
					setProperty("login",userid);
					return true;
				}
			}
			return false;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
		
	}
	
	public User[] getUserInfoById(String id){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return null;
			}
			execSQL("select * from user where uid like ?","%"+id+"%");
			List<User> userlist = new ArrayList<User>();

			while(rs.next()){
				User user = new User();
				user.setUid(rs.getString("uid"));
				user.setPassword(rs.getString("password"));
				
				userlist.add(user);
			}
			User[] users = new User[userlist.size()];
			return userlist.toArray(users);
		}catch(Exception e){
			return null;
		}
	}
	
	public boolean updateUserInfo(User user){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			Object[] params = new Object[]{encryptWithMd5(user.getPassword()),user.getUid()};
			execSQL("update user set password = ? where uid = ?",params);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean modifyUserPassword4android(String uid,  String newpw){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			Object[] params = new Object[]{encryptWithMd5(newpw),uid};
			execSQL("update user set password = ? where uid = ?",params);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteUser(String uid){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			execSQL("delete from user where uid = ?",uid);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteAllUsers(){
		try{
			String userid = (String)getProperty("login");
			if(userid == null){
				return false;
			}
			execSQL("delete from user");
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
