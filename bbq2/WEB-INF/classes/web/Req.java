package web;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import db.User;
import db.UseDB;
import java.io.FileInputStream; 
import java.io.OutputStream;
import java.net.URLDecoder; 

public class Req extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8"); 

		String type=req.getParameter("type");
		if(type==null) type="";
		if(type.equals("all_tasks")){
			
			UseDB useDB=new UseDB();
			String tem=useDB.req_all_tasks();
			resp.getWriter().write(tem);  
			//System.out.println("successful："+tem);
		}
		if(type.equals("one_task")){
			String task_id=req.getParameter("task_id");
			UseDB useDB=new UseDB();
			String tem=useDB.req_one_task(task_id);
			resp.getWriter().write(tem);
			//System.out.println("successful："+tem);
		}
		if(type.equals("accept_task")){
			String receiver=req.getParameter("user_id");
			String task_id=req.getParameter("task_id");
			UseDB useDB=new UseDB();
			String tem=useDB.accept_task(receiver,task_id);
			resp.getWriter().write(tem);
			//System.out.println("successful："+tem);
		}
		if(type.equals("make_friend")){
			String user_id1=req.getParameter("user_id1");
			String user_id2=req.getParameter("user_id2");
			UseDB useDB=new UseDB();
			String tem=useDB.make_friend(user_id1,user_id2);
			resp.getWriter().write(tem);
			//System.out.println("successful："+tem);
		}
		if(type.equals("mytasks")){
			String toget=req.getParameter("toget");
			String user_id=req.getParameter("user_id");
			UseDB useDB=new UseDB();
			String tem=useDB.req_my_task(toget,user_id);
			resp.getWriter().write(tem);  
			//System.out.println("successful："+tem);
		}
		if(type.equals("my_friends")){
			String user_id=req.getParameter("user_id");
			UseDB useDB=new UseDB();
			String tem=useDB.my_friends(user_id);
			resp.getWriter().write(tem);  
		}
		if(type.equals("my_ask_friends")){
			String user_id=req.getParameter("user_id");
			UseDB useDB=new UseDB();
			String tem=useDB.my_ask_friends(user_id);
			resp.getWriter().write(tem);  
		}
		if(type.equals("accept_friend")){
			String user_id1=req.getParameter("user1");
			String user_id2=req.getParameter("user2");
			UseDB useDB=new UseDB();
			String tem=useDB.accept_friend(user_id1,user_id2);
			resp.getWriter().write(tem);
			//System.out.println("successful："+tem);
		}
		if(type.equals("finish_task")){
			String task_id=req.getParameter("task_id");
			UseDB useDB=new UseDB();
			String tem=useDB.finish_task(task_id);
			resp.getWriter().write(tem);
		}
		if(type.equals("people_around")){
			String user_id=req.getParameter("user_id");
			UseDB useDB=new UseDB();
			String tem=useDB.people_around(user_id);
			resp.getWriter().write(tem);  
		}
		if(type.equals("search_tasks")){
			String user_id=req.getParameter("user_id");
			String tag=req.getParameter("tag");
			String distance_sp=req.getParameter("distance");
			String type_sp=req.getParameter("type_sp");
			UseDB useDB=new UseDB();
			
			String tem=useDB.search_tasks(tag,type_sp,distance_sp,user_id);
			resp.getWriter().write(tem);  
			//System.out.println("successful："+tem);
		}	
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8"); 
		
		
		BufferedReader b =  req.getReader();  
	    String line;  
	    StringBuilder s = new StringBuilder();  
	    while((line = b.readLine())!=null){  
	        s.append(line);  
	    }  
	    resp.getWriter().write(s.toString());  
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}

