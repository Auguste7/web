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

