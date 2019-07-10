

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
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String email=request.getParameter("username");
		String password=request.getParameter("password");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			PreparedStatement ps=conn.prepareStatement("Select * from signup where email=? and password=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				//out.print("<b>welcome to your account</b>");
				HttpSession session=request.getSession();
				session.setAttribute("username",email);
				RequestDispatcher rd=request.getRequestDispatcher("process.jsp");
				rd.forward(request, response);
			}
			else{
         out.print("<div style='position:absolute;top:93%;left:42%;'><h4 style=color:red;top:600px;left:700px;>" +
		 " Username or Password incorrect!</h4></div>");
		RequestDispatcher rd=request.getRequestDispatcher("emplogin.jsp");
		rd.include(request, response);
		
			}
		}catch(Exception e){
			out.print(e);
		}
		
	}

}
