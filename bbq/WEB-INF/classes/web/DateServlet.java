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

import db.UseDB;
import java.io.FileInputStream; 
import java.io.OutputStream;
import java.net.URLDecoder; 
public class DateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");//
		resp.setCharacterEncoding("UTF-8"); //
		String user_id=req.getParameter("user_id"); 
		String type=req.getParameter("type");
		UseDB useDB=new UseDB();
		if(type==null) type="";
		if(type.equals("change_name")){				
			String name=req.getParameter("name"); 
			String tem=useDB.change_name(user_id,name);
			if(tem.equals("rename successfully")){
				resp.getWriter().write(tem);
			}else {
				resp.getWriter().write("rename fail");
			}
		}
		if(type.equals("change_sign")){				//
			String sign=req.getParameter("sign"); 
			String tem=useDB.change_sign(user_id,sign);
			if(tem.equals("change successfully")){
				resp.getWriter().write(tem);
			}else {
				resp.getWriter().write("change fail");
			}
		}
		if(type.equals("change_phone")){			
			String phone=req.getParameter("phone"); 
			String tem=useDB.change_sign(user_id,phone);
			if(tem.equals("change successfully")){
				resp.getWriter().write(tem);
			}else {
				resp.getWriter().write(tem);
			}
		}
		if(type.equals("get_touxiang")){				
			OutputStream out = resp.getOutputStream();  
			String tem=useDB.get_touxiang(user_id);
			
			if(tem.equals("touxiang don't exist")){
				out.write(0);
			}else{
				String path = req.getRealPath("/upload/test/test")+tem;
				FileInputStream in = new FileInputStream(path);  
		        
		        int a;  
		        while((a = in.read())!=-1){  
		            out.write(a);  
		        }  
			}
			
		}
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");//
		resp.setCharacterEncoding("UTF-8"); //
        DiskFileItemFactory factory = new DiskFileItemFactory();    
        String path = req.getRealPath("/upload/test/test");    
        File file=new File(path);  
        if(!file.exists()){  
            file.mkdirs();  
        }  
        factory.setRepository(new File(path));   
        
        
        factory.setSizeThreshold(1024*1024) ;    
        ServletFileUpload upload = new ServletFileUpload(factory);    
        try {    
            List<FileItem> list = (List<FileItem>)upload.parseRequest(req);    
            for(FileItem item : list){    
                String name = item.getFieldName();    
                if(item.isFormField()){                       
                    String value = item.getString() ;    
                    req.setAttribute(name, value);    
                }else{    
                    String value = item.getName() ;    
                    int start = value.lastIndexOf("\\");    
                    String filename = value.substring(start+1);    
                    req.setAttribute("path", filename);    
                    item.write( new File(path,filename) );// 
                    System.out.println("upload successfully"+filename);  
                    
                }    
            }    
                
        } catch (Exception e) {    
            System.out.println("");  
            resp.getWriter().print("upload fail"+e.getMessage());  
        } 
        doform(req,resp);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	protected void doform(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String user_id=(String) URLDecoder.decode(req.getAttribute("user_id").toString(),"UTF-8");
		String path=(String) URLDecoder.decode(req.getAttribute("path").toString(),"UTF-8");//req.getAttribute("publicer");
		UseDB useDB=new UseDB();
		String tem=useDB.change_profile_photo(path, user_id);
		if(tem.equals("successfully modified")){
			resp.getWriter().print(tem);
		}else{
			resp.getWriter().print(tem);  
		}
		 
		
	}
}
