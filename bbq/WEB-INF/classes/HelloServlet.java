import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
   
public class HelloServlet extends HttpServlet
  {
      public void doGet(HttpServletRequest request,
          HttpServletResponse response)
          throws ServletException, IOException
      {
          response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          out.println("<html><head><title>head</title></head><body><h1>Hello Servlet~~~~~~~~~~~~~~~~</h1></body></html>");
      }
  }
