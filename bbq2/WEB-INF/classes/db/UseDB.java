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
	public String login(String telephone,String password,String Longitude,String Latitude){
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
        	int result1=0;
        	
        	String sql1="update user set latitude='"+Latitude+"',longitude='"+Longitude+"' where user_id='"+userid+"'";
    		Connection connection1 = null;
    		try{
    			connection1 = ConnDB.getConnection();
    			Statement stmt1 = connection1.createStatement();
    			result1=stmt1.executeUpdate(sql1);
    			
    		}catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }finally{
                ConnDB.closeConnection(connection);
            }
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
	
	public String publish_task(String title,String detail,String picture,String tag,String deadline_time,String publish_time,String location,
			String towho,String type,String reward,String user_id,String latitude,String longitude){
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
		latitude=latitude.replaceAll("\r|\n", "");
		longitude=longitude.replaceAll("\r|\n", "");
		String sql1="INSERT INTO `task` (`title`, `detail`, `picture`, `tag`, `deadline_time`, `publish_time`, `location`, `right_towho`,`type`,`reward`,`publisher`,`latitude`,`longitude`) VALUES('";
		String sql2=title+"', '"+detail+"', '"+picture+"', '"+tag+"', '"+deadline_time+"', '"+publish_time+"', '"+location+"', '"+towho+"', '"+type+"', '"+reward+"', '"+user_id+"', '"+latitude+"', '"+longitude+"');";
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
		//String sql="select task_id,title,deadline_time,location,type,publisher from task where state = 'wait for accept'";
		String sql="select task_id,title,deadline_time,location,type,publisher from task where state = 'wait for accept' order by task_id desc";
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
	        //判断结果集是否有效
	        while(rs.next()){
	        	String task_id=rs.getString("task_id");
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
					String telephone=rs1.getString("telephone");result.append(telephone+",");
				}
				result.append(task_id);
				result.append(";");
				rs1.close();
				stmt1.close();
	        }
	       		rs.close(); 
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

	public String req_one_task(String task_id){
		StringBuilder result = new StringBuilder();
		String sql="select title,detail,picture,tag,deadline_time,location,type,reward,publisher,receiver,state from task where task_id ='"+task_id+"'";
		Connection connection = null;
		String receiver="";
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
	        //判断结果集是否有效
	        if(rs.next()){
	        	String title = rs.getString("title"); result.append(title+",");
	        	String detail = rs.getString("detail"); result.append(detail+",");
	        	String picture = rs.getString("picture"); result.append(picture+",");
	        	String tag = rs.getString("tag"); result.append(tag+",");
	        	String deadline_time = rs.getString("deadline_time"); result.append(deadline_time+",");
	        	String location = rs.getString("location"); result.append(location+",");
	        	String type = rs.getString("type"); result.append(type+",");
	        	String reward = rs.getString("reward"); result.append(reward+",");
	        	String publisher = rs.getString("publisher");result.append(publisher+",");
	        	String state = rs.getString("state");
	        	if(state.equals("wait for accept")){
	        	//	result.append("No"+",");
	        	}else{
	        		receiver = rs.getString("receiver");
	        	}
	        	//取publisher
	        	String sql1="select name,telephone from user where user_id="+publisher;
				Statement stmt1 = connection.createStatement();
				ResultSet rs1=stmt1.executeQuery(sql1);
				if(rs1.next()){
					String name = rs1.getString("name");result.append(name+",");
					String telephone=rs1.getString("telephone");result.append(telephone+",");
				}
				rs1.close();
				stmt1.close();
				//取receiver
				if(state.equals("wait for accept")){
					result.append("Noname"+",");result.append("Nophone");
				}else{
					String sql2="select name,telephone from user where user_id="+receiver;
					Statement stmt2 = connection.createStatement();
					ResultSet rs2=stmt2.executeQuery(sql2);
					if(rs2.next()){
						String name = rs2.getString("name");result.append(name+",");
						String telephone=rs2.getString("telephone");result.append(telephone+",");
					}
					rs2.close();
					stmt2.close();
				}
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
	}	
		
	public String accept_task(String receiver,String task_id){
		int result=0;
		String re="";
	//	String sql="update task set receiver='"+receiver+"'where task_id='"+task_id+"'";
	//	String sql="update task set receiver='"+receiver+",state='wait for complete'"+"' where task_id='"+task_id+"'";
		String sql="update task set receiver='"+receiver+"',state='wait for complete' where task_id='"+task_id+"'";
	
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
	
	public String make_friend(String user_id1,String user_id2){
		int result=0;
		String re="";
		user_id1=user_id1.replaceAll("\r|\n", "");
		user_id2=user_id2.replaceAll("\r|\n", "");
		String tem="ask";
		String sql1="INSERT INTO `relationship` (`user1`, `user2`, `state`) VALUES('";
		String sql2=user_id1+"', '"+user_id2+"', '"+tem+"');";
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
	
	public String req_my_task(String toget,String user_id){
		StringBuilder result = new StringBuilder();
		toget=toget.replaceAll("\r|\n", "");
		String sql="";
		
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			if(toget.equals("mypublic")){
				//sql="select task_id,title,deadline_time,location,type,state from task where publisher='"+user_id+"'";
				sql="select task_id,title,deadline_time,location,type,state from task where publisher='"+user_id+"' order by task_id desc";
				ResultSet rs=stmt.executeQuery(sql);
		        //判断结果集是否有效
		        while(rs.next()){
		        	String task_id=rs.getString("task_id");
		        	String title = rs.getString("title"); result.append(title+",");
		        	String deadline_time = rs.getString("deadline_time"); result.append(deadline_time+",");
		        	String location = rs.getString("location"); result.append(location+",");
		        	String type = rs.getString("type"); result.append(type+",");
		        	String state = rs.getString("state");result.append(state+",");
		        	String sql1="select name,telephone from user where user_id="+user_id;
					Statement stmt1 = connection.createStatement();
					ResultSet rs1=stmt1.executeQuery(sql1);
					if(rs1.next()){
						String name = rs1.getString("name");result.append(name+",");
						String telephone=rs1.getString("telephone");result.append(telephone+",");
					}
					result.append(task_id);
					result.append(";");
					rs1.close();
					stmt1.close();
		        }
			}else if(toget.equals("myreceive")){
				//sql="select task_id,title,deadline_time,location,type,state,publisher from task where receiver='"+user_id+"'";
				sql="select task_id,title,deadline_time,location,type,state,publisher from task where receiver='"+user_id+"' order by task_id desc";
				ResultSet rs=stmt.executeQuery(sql);
		        //判断结果集是否有效
		        while(rs.next()){
		        	String task_id=rs.getString("task_id");
		        	String title = rs.getString("title"); result.append(title+",");
		        	String deadline_time = rs.getString("deadline_time"); result.append(deadline_time+",");
		        	String location = rs.getString("location"); result.append(location+",");
		        	String type = rs.getString("type"); result.append(type+",");
		        	String state = rs.getString("state");result.append(state+",");
		        	String publisher = rs.getString("publisher");result.append(publisher+",");
		        	String sql1="select name,telephone from user where user_id="+publisher;
					Statement stmt1 = connection.createStatement();
					ResultSet rs1=stmt1.executeQuery(sql1);
					if(rs1.next()){
						String name = rs1.getString("name");result.append(name+",");
						String telephone=rs1.getString("telephone");result.append(telephone+",");
					}
					result.append(task_id);
					result.append(";");
					rs1.close();
					stmt1.close();
		        }
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
	public String my_friends(String user_id){
		StringBuilder result = new StringBuilder();
		String sql="select user1,user2 from relationship where (user1 ='"+user_id+"'or user2='"+user_id+"') and state='friends'";
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
	        //判断结果集是否有效
	        while(rs.next()){
	        	String friends_id1 = rs.getString("user1"); 
	        	if(!friends_id1.equals(user_id)){
	        		result.append(friends_id1+",");
		        	String sql1="select name from user where user_id="+friends_id1;
					Statement stmt1 = connection.createStatement();
					ResultSet rs1=stmt1.executeQuery(sql1);
					if(rs1.next()){
						String name = rs1.getString("name");
						result.append(name+";");
					}
					rs1.close();
					stmt1.close();
	        	}
	        	String friends_id2 = rs.getString("user2"); 
	        	if(!friends_id2.equals(user_id)){
	        		result.append(friends_id2+",");
		        	String sql1="select name from user where user_id="+friends_id2;
					Statement stmt1 = connection.createStatement();
					ResultSet rs1=stmt1.executeQuery(sql1);
					if(rs1.next()){
						String name = rs1.getString("name");
						result.append(name+";");
					}
					rs1.close();
					stmt1.close();
	        	}
	        	
	        }
	        //String sql1="select user1 from relationship where user2="+user_id;
		String sql1="select user1 from relationship where user2="+user_id+" and state='ask'";
			Statement stmt1 = connection.createStatement();
			ResultSet rs1=stmt1.executeQuery(sql1);
			result.append("new_friend_num"+",");
			String tem="No";
			if(rs1.next()){
				tem = "Yes";
			}
			result.append(tem);
			rs1.close();
			stmt1.close();
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
	}
	

	public String my_ask_friends(String user_id){
		StringBuilder result = new StringBuilder();
		
		//String sql="select user1,state from relationship where user2='"+user_id+"'";//and (state='ask' or state='wait')
		String sql="select user1,state from relationship where user2='"+user_id+"' order by relationship_id desc";
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
	        //判断结果集是否有效
	        while(rs.next()){
	        	String user1 = rs.getString("user1"); result.append(user1+",");
	        	String state=rs.getString("state");
	        	
		        String sql1="select name from user where user_id="+user1;
				Statement stmt1 = connection.createStatement();
				ResultSet rs1=stmt1.executeQuery(sql1);
				if(rs1.next()){
					String name = rs1.getString("name");
					result.append(name+",");
				}
				rs1.close();
				stmt1.close();
				result.append(state+";");
	        }
	        String sql2="update relationship set state='wait' where state='ask' and user2='"+user_id+"'";
			Statement stmt2 = connection.createStatement();
			int result2=stmt2.executeUpdate(sql2);
			stmt2.close();
		}catch (Exception e) {
			result.append(e.getMessage());
            // TODO: handle exception
            e.printStackTrace();
            
        }finally{
            ConnDB.closeConnection(connection);
        }
		return result.toString();
	}	
	
	public String accept_friend(String user_id1,String user_id2){
		int result=0;
		String re="";
		user_id1=user_id1.replaceAll("\r|\n", "");
		user_id2=user_id2.replaceAll("\r|\n", "");
		String tem="ask";
		
		String sql1="update relationship set state='friends' where user1='"+user_id1+"' and user2='"+user_id2+"'";
		
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			result=stmt.executeUpdate(sql1);
			
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

	public String finish_task(String task_id){
		int result=0;
		String re="";
		String sql="update task set state='done' where task_id='"+task_id+"'";
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

	public String people_around(String user_id){
		StringBuilder result = new StringBuilder();
		
		String sql="select latitude,longitude from user where user_id='"+user_id+"'";//and (state='ask' or state='wait')
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt = connection.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
	        //判断结果集是否有效
	        if(rs.next()){
	        	String latitude = rs.getString("latitude"); 
	        	String longitude=rs.getString("longitude");
	        	
		        String sql1="select user_id,name from user as A where 6378.137*acos(cos(A.latitude)*cos("+latitude+")*cos(A.longitude-"+longitude+")+sin(A.latitude)*sin("+latitude+"))*1000*0.01745<=5000 and A.user_id != '" + user_id + "'";
				Statement stmt1 = connection.createStatement();
				ResultSet rs1=stmt1.executeQuery(sql1);
				while(rs1.next()){
					String userid=rs1.getString("user_id");result.append(userid+",");
					String name = rs1.getString("name");result.append(name+";");
				}
				rs1.close();
				stmt1.close();
				
	        }
	       
		}catch (Exception e) {
			result.append(e.getMessage());
            // TODO: handle exception
            e.printStackTrace();
            
        }finally{
            ConnDB.closeConnection(connection);
        }
		return result.toString();
	}
	public String search_tasks(String tag,String type_sp,String distance,String user_id){
		StringBuilder result = new StringBuilder();
		if(distance.equals("1")) distance="1000";
		else if(distance.equals("2")) distance="2000";
		else if(distance.equals("5")) distance="5000";
		else if(distance.equals("nolimit")) distance="6378000";
		
		Connection connection = null;
		try{
			connection = ConnDB.getConnection();
			Statement stmt0 = connection.createStatement();
			//从视图query_v中得到所需要的信息
			String sql0="drop view if exists query_v;";
			stmt0.executeUpdate(sql0);
			sql0="create view query_v(task_id,title,deadline_time,location,publisher,task_latitude,task_longitude,state,type,tag,user_id,user_latitude,user_longitude) "
						+"as select task_id,title,deadline_time,location,publisher,task.latitude,task.longitude,state,type,tag,user_id,user.latitude,user.longitude "
						+"from user,task "
						+"where user_id = "+user_id+" and state!='done'";
			int cnt=stmt0.executeUpdate(sql0);
			sql0="drop view if exists query_task;";
			stmt0.executeUpdate(sql0);
			sql0="create view query_task(task_id,title,deadline_time,location,publisher,state,type,tag) "
					+"as select task_id,title,deadline_time,location,publisher,state,type,tag "
					+"from query_v "
					+"where 6378.137*acos(cos(task_latitude)*cos(user_latitude)*cos(task_longitude-user_longitude)+sin(task_latitude)*sin(user_latitude))*1000*0.01745<="+distance;
			 cnt=stmt0.executeUpdate(sql0);
		}
		catch (Exception e) {
			result.append(e.getMessage());
            // TODO: handle exception
            e.printStackTrace();
            
        }
		
		//sql0="select task_id,title,deadline_time,location,type,publisher,state,type,tag from query_v where 6378.137*acos(cos(task_latitude)*cos(user_latitude)*cos(task_longitude-user_longitude)+sin(task_latitude)*sin(user_latitude))*1000*0.01745<="+distance;
		String sql="";
		if(type_sp.equals("nopay")){
			type_sp="无偿";
			
			if(tag.equals("all")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and type='"+type_sp+"' order by task_id desc";
			}else if(tag.equals("express")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='快递' and type='"+type_sp+"' order by task_id desc";
			}else if(tag.equals("pet")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='寄养' and type='"+type_sp+"' order by task_id desc";
			}else if(tag.equals("buy")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='代购' and type='"+type_sp+"' order by task_id desc";
			}else if(tag.equals("other")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='其他' and type='"+type_sp+"' order by task_id desc";
			}
			
		}else if(type_sp.equals("pay")){//if(type.equals("赏金任务"))
			type_sp="有偿";
			
			if(tag.equals("all")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and type='"+type_sp+"' order by task_id desc";
			}else if(tag.equals("express")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='快递' and type='"+type_sp+"' order by task_id desc";
			}else if(tag.equals("pet")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='寄养' and type='"+type_sp+"' order by task_id desc";
			}else if(tag.equals("buy")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='代购' and type='"+type_sp+"' order by task_id desc";
			}else if(tag.equals("other")){
				sql="select task_id,title,deadline_time,location,type,publisher from task where state = 'wait for accept' and tag='其他' and type='"+type_sp+"' order by task_id desc";
			}
			
		}else{
			if(tag.equals("all")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' order by task_id desc";
			}else if(tag.equals("express")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='快递' order by task_id desc";
			}else if(tag.equals("pet")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='寄养' order by task_id desc";
			}else if(tag.equals("buy")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='代购' order by task_id desc";
			}else if(tag.equals("other")){
				sql="select task_id,title,deadline_time,location,type,publisher from query_task where state = 'wait for accept' and tag='其他' order by task_id desc";
			}
			
		}
		
		
		try{
			
			Statement stmt = connection.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
	        //判断结果集是否有效
	        while(rs.next()){
	        	String task_id=rs.getString("task_id");
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
					String telephone=rs1.getString("telephone");result.append(telephone+",");
				}
				result.append(task_id);
				result.append(";");
				rs1.close();
				stmt1.close();
	        }
	        rs.close();
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

