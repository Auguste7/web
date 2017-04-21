package db;

import java.sql.*;
import java.sql.ResultSet;
import com.mysql.jdbc.PreparedStatement;
import db.User;

public class UseDB {
	User user=null;
	private String sql="";
	private String pw="";
	private String userid="";
	private String name="",sign="";
	public String login(String telephone,String password){
		sql="select password,user_id,name,sign,telephone from user where telephone = ?";
		Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConnDB.getConnection();
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
           
            pstmt.setString(1, telephone);
            
            ResultSet rSet = (ResultSet) pstmt.executeQuery();
            
            if(rSet.next()){
            	pw=rSet.getString("password").toString();
            	userid=rSet.getString("user_id").toString();
            	name=rSet.getString("name").toString();
            	sign=rSet.getString("sign").toString();
            	telephone=rSet.getString("telephone").toString();
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
        	if(name.equals("")) name="null";
        	if(sign.equals("")) sign="null";
        	return userid+";"+name+";"+sign+";"+telephone;
        }else if(pw.equals("")){
        	return "The user dosen't exist";
        }else{
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
			return 1;
		}else{
			return 0;
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
			return "successful";
		}else{
			return re;
		}
		
	}
	
	//////////////////////////////////////////////////////////
	//
	//change person's info
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
			return "successful";
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
			return "successful";
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
			return "successful";
		}else{
			return re;
		}
		
	}
	public String change_profile_photo(String profile_photo,String user_id){
		String re="";
		int result=0;
		user_id=user_id.replaceAll("\r|\n", "");
		profile_photo=profile_photo.replaceAll("\r|\n", "");
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
			return "successful";
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
		String result="";
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
			return "logo.jpg";
		}else{
			return result;
		}	
	}
	public String req_all_tasks(){
		StringBuilder result = new StringBuilder();
		String sql="select title,deadline_time,location,type,publisher from task where state = 'wait for accept'";
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
	        while(rs.next()){
	        	String title = rs.getString("title"); result.append(title+",");
	        	String deadline_time = rs.getString("deadline_time"); result.append(deadline_time+",");
	        	String location = rs.getString("location"); result.append(location+",");
	        	String type = rs.getString("type"); result.append(type+",");
	        	String publisher = rs.getString("publisher");result.append(publisher+",");
	        	String sql1="select name,telephone from user where user_id="+publisher;
				Statement stmt1 = connection.createStatement();
				ResultSet rs1=stmt1.executeQuery(sql1);
				if(rs1.next()){
					String name = rs1.getString("name");result.append(name+",");
					String telephone=rs1.getString("telephone");result.append(telephone);
				}
				result.append(";");
				rs1.close();
				stmt1.close();
	        }
	        
			connection.close();
			stmt.close();
		}catch (Exception e) {
			result.append(e.getMessage());
            // TODO: handle exception
            e.printStackTrace();
            
        }finally{
            ConnDB.closeConnection(connection);
        }
		return result.toString();
		//return sql;
	}
}

