

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class balanceenquiry
 */
public class balanceenquiry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public balanceenquiry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		 String acnumber=request.getParameter("acnumber");	
		   try{
		 Class.forName("com.mysql.jdbc.Driver");
	     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
        PreparedStatement ps=conn.prepareStatement("select * from openac where idopenac=?");
	     ps.setString(1,acnumber);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
  	     String status=rs.getString(10); 
	     String balance=rs.getString(11);
	    int acbalance=Integer.parseInt(balance);
	    if(status.equals("C")){
	    	 out.print("<div style='position:absolute;top:89%;left:42%;'><h4 style=color:red;top:500px;left:700px;>" +
			 "Account is  CLOSED</h4></div>");
			RequestDispatcher rd=request.getRequestDispatcher("process.jsp");
			rd.include(request, response);
	    }
        else {  out.print("<div style='position:absolute;top:84%;left:45%;'><h2 style=color:black;font-size:20px;top:20px;left:720px;>" +
				  "Accout Balance: "+balance+"</h2></div>");
		    	  RequestDispatcher rd1=request.getRequestDispatcher("process.jsp");
		    	  rd1.include(request,response);
        }  }
		   }catch(Exception e){
			   e.printStackTrace(); 
		   }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
