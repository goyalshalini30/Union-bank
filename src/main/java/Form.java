

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Form extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Form() {
        // TODO Auto-generated constructor stub
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
   PrintWriter write=response.getWriter();
   String name=request.getParameter("name");
   String gender=request.getParameter("gender");
   String address=request.getParameter("address");
   String country=request.getParameter("country");
   String mob=request.getParameter("mob");
   String email=request.getParameter("email");
   String dob=request.getParameter("dob");
   String password=request.getParameter("password");
   try{ 
	   Class.forName("com.mysql.jdbc.Driver");
	   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
      PreparedStatement ps=conn.prepareStatement("insert into signup(name,gender,address,country,mobile,email,dob,password)"+"values(?,?,?,?,?,?,?,?)");
      ps.setString(1,name );
		ps.setString(2,gender);
		ps.setString(3,address);
		ps.setString(4,country);
		ps.setString(5,mob);
		ps.setString(6,email);
		ps.setString(7,dob);
		ps.setString(8,password);
		int i = ps.executeUpdate();
		if(i>0){
			write.print("<center><h1>Succesfully Registered</h1></center>"+name);
		}
	}catch(Exception e1){
		e1.printStackTrace();
		
	}     
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
