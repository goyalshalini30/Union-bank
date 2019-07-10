

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Admin
 */
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		try{
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
				PreparedStatement ps=conn.prepareStatement("Select * from admin where username=? and password=?");
				ps.setString(1,username);
				ps.setString(2,password);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					HttpSession session=request.getSession();
					session.setAttribute("adminname",username );
					RequestDispatcher rd=request.getRequestDispatcher("empac.jsp");
					rd.forward(request, response);
				System.out.println("in if case");
				}
				else{	System.out.println("in else case");
	         out.print("<div style='position:absolute;top:89%;left:42%;'><h4 style=color:red;top:500px;left:700px;>" +
			 " Username or Password incorrect!</h4></div>");
			RequestDispatcher rd=request.getRequestDispatcher("adminlogin.jsp");
			rd.include(request, response);
			
				}
			}catch(Exception e){
				out.print(e);
			}
			
		}
		
	}


