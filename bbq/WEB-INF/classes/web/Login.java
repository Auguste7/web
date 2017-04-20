package web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import db.UseDB;
import db.User;

public class Login extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8"); //
		//
		String telephone=req.getParameter("telephone"); 
		String password=req.getParameter("password");
		String type=req.getParameter("type");
		if(telephone==null) telephone="";
		if(password==null) password="";
		if(type==null) type="";
		if(type.equals("login")){
			//
			UseDB useDB=new UseDB();
			String tem=useDB.login(telephone, password);
			if(tem.equals("wrong password")){
				//User user=new User();
				//Cookie c = new Cookie("JSESSIONID", req.getSession().getId());  
				//resp.addCookie(c);
				resp.getWriter().write("wrong password");
			}else if(tem.equals("user does not exist")){
				resp.getWriter().write("user dois not exist");
			}else{
				resp.getWriter().write(tem);//
			}
		
		}else if(type.equals("register")){
			UseDB useDB=new UseDB();
			int tem=useDB.register(telephone, password);
			if(tem==1){
				resp.getWriter().write("registed successfully");
			}else{
				resp.getWriter().write("user already exists");
			}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");//
		resp.setCharacterEncoding("UTF-8"); //
		//
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
            System.out.println("upload fail");  
            resp.getWriter().print("upload fail:"+e.getMessage());  
        }    
        doform(req,resp);
        //test(req,resp);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
//	protected void test(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
////		req.setCharacterEncoding("UTF-8");
////		resp.setHeader("Content-type", "text/html;charset=UTF-8");
////		resp.setCharacterEncoding("UTF-8"); //
//		String title=(String) URLDecoder.decode(req.getAttribute("name").toString(), "UTF-8");  // 
//		resp.getWriter().print(title+"!!!!!!!!");  
//	}
	protected void doform(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String title=(String) URLDecoder.decode(req.getAttribute("title").toString(),"UTF-8"); 
		String detail=(String) URLDecoder.decode(req.getAttribute("detail").toString(),"UTF-8");  //req.getAttribute("detail");
		//String picture=(String) URLDecoder.decode(req.getAttribute("picture").toString(),"UTF-8");//req.getAttribute("picture");
		String tag=(String) URLDecoder.decode(req.getAttribute("tag").toString(),"UTF-8");//req.getAttribute("tap");
		String public_time=(String) URLDecoder.decode(req.getAttribute("publish_time").toString(),"UTF-8");//req.getAttribute("public_time");
		String deadline_time=(String) URLDecoder.decode(req.getAttribute("deadline_time").toString(),"UTF-8");//req.getAttribute("deadline_time");
		String location=(String) URLDecoder.decode(req.getAttribute("location").toString(),"UTF-8");//req.getAttribute("location");
		String towho=(String) URLDecoder.decode(req.getAttribute("towho").toString(),"UTF-8");//req.getAttribute("towho");
		String type=(String) URLDecoder.decode(req.getAttribute("type").toString(),"UTF-8");//req.getAttribute("type");
		String reward=(String) URLDecoder.decode(req.getAttribute("reward").toString(),"UTF-8");//req.getAttribute("reward");
		String user_id=(String) URLDecoder.decode(req.getAttribute("user_id").toString(),"UTF-8");
		String path=(String) URLDecoder.decode(req.getAttribute("path").toString(),"UTF-8");//req.getAttribute("publicer");
		UseDB useDB=new UseDB();
		String tem=useDB.public_task(title,detail, path, tag, deadline_time, public_time, location, towho, type, reward, user_id);
		if(tem.equals("Successful release")){
			resp.getWriter().print(tem);
		}else{
			resp.getWriter().print("release fail");  
		}
		//resp.getWriter().print(tem);  
		
	}
}
