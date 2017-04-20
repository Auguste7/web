package db;
import com.mysql.jdbc.PreparedStatement;
import java.sql.*;
import java.sql.ResultSet;


public class UseDB {
	User user=null;
	private String sql="";
	private String pw="";
	private String userid="";
	private String result="";
	public String login(String telephone,String password){
		sql="select password,user_id from user where telephone = ?";
		Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnDB.getConnection();
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1, telephone);
            
            ResultSet rSet = (ResultSet) pstmt.executeQuery();//
            if(rSet.next()){
            	pw=rSet.getString("password").toString();
            	userid=rSet.getString("user_id").toString();
            }
            connection.close();
            pstmt.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            ConnDB.closeConnection(connection);
        }
        if(pw.equals(password)){
        	
        	return userid;
        }else if(pw.equals("")){
        	return "user does not exist";//
        }else{//
        	return "wrong password";
        }
		
	}
	public int register(String telephone,String password){
		int result=0;
		String sql="INSERT INTO `user` (`password`, `telephone`) VALUES ('"+password+"', '"+telephone+"')";
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			result=stmt.executeUpdate(sql);
			
		}catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            ConnDB.closeConnection(connection);
        }
		if(result==1){
			return 1;//
		}else{
			return 0;//
		}
		
	}
	
	public String public_task(String title,String detail,String picture,String tag,String deadline_time,String publish_time,String location,
			String towho,String type,String reward,String user_id){
		int result=0;
		String re="";
		title=title.replaceAll("\r|\n", "");
		detail=detail.replaceAll("\r|\n", "");
		picture=picture.replaceAll("\r|\n", "");
		tag=tag.replaceAll("\r|\n", "");
		deadline_time=deadline_time.replaceAll("\r|\n", "");
		publish_time=publish_time.replaceAll("\r|\n", "");
		location=location.replaceAll("\r|\n", "");
		towho=towho.replaceAll("\r|\n", "");
		type=type.replaceAll("\r|\n", "");
		reward=reward.replaceAll("\r|\n", "");
		user_id=user_id.replaceAll("\r|\n", "");
		String sql1="INSERT INTO `task` (`title`, `detail`, `picture`, `tag`, `deadline_time`, `publish_time`, `location`, `right_towho`,`type`,`reward`,`publisher`) VALUES('";
		String sql2=title+"', '"+detail+"', '"+picture+"', '"+tag+"', '"+deadline_time+"', '"+publish_time+"', '"+location+"', '"+towho+"', '"+type+"', '"+reward+"', '"+user_id+"');";
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			result=stmt.executeUpdate(sql1+sql2);
			
		}catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            re=e.getMessage();
        }finally{
            ConnDB.closeConnection(connection);
        }
		if(result==1){
			return "successful release";
		}else{
			return re;//
		}
		
	}
	
	//////////////////////////////////////////////////////////
	//
	//
	/////////////////////////////////////////////////////////
	public String change_name(String user_id,String name){
		String re="";
		int result=0;
		String sql="update user set name='"+name+"'where user_id='"+user_id+"'";
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			result=stmt.executeUpdate(sql);
			
		}catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            re=e.getMessage();
        }finally{
            ConnDB.closeConnection(connection);
        }
		if(result==1){
			return "successfully modified";
		}else{
			return re;
		}
		
	}
	
	public String change_sign(String user_id,String sign){
		int result=0;
		String re="";
		String sql="update user set sign='"+sign+"'where user_id='"+user_id+"'";
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			result=stmt.executeUpdate(sql);
			
		}catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            re=e.getMessage();
            
        }finally{
            ConnDB.closeConnection(connection);
        }
		if(result==1){
			return "successfully modified";
		}else{
			return re;
		}
		
	}
	
	public String change_phone(String user_id,String phone){
		int result=0;
		String re="";
		String sql="update user set phone='"+phone+"'where user_id='"+user_id+"'";
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			result=stmt.executeUpdate(sql);
			
		}catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            re=e.getMessage();
        }finally{
            ConnDB.closeConnection(connection);
        }
		if(result==1){
			return "successfully modified";
		}else{
			return re;
		}
		
	}
	public String change_profile_photo(String profile_photo,String user_id){
		String re="";
		int result=0;
		//String sql="update user set profile_photo='ceshi' where user_id='1'";
		String sql="update user set profile_photo='"+profile_photo+"' where user_id='"+user_id+"'";
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			result=stmt.executeUpdate(sql);
			
		}catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            re=e.getMessage();
        }finally{
            ConnDB.closeConnection(connection);
        }
		if(result==1){
			return "successfully modified";
		}else{
			return re;
		}
		//return sql;
	}
	//////////////////////////////////////////////////////////
	//
	//
	/////////////////////////////////////////////////////////
	public String get_touxiang(String user_id){

		String sql="select profile_photo from user where user_id ="+user_id;
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
	        if(rs.next()){
	        	result=rs.getString("profile_photo").toString();
	        }
			connection.close();
			stmt.close();
		}catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            
        }finally{
            ConnDB.closeConnection(connection);
        }
		if(result.equals("")){
			return "portrait does not exist";
		}else{
			return result;
		}
		
	}
}
