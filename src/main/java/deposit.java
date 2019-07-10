

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deposit
 */
public class deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deposit() {
        super();
        // TODO Auto-generated constructor stub
    }
 protected void processRequest(HttpServletRequest request, HttpServletResponse response,String email,String name,int balance) throws MessagingException{

     String toemail=email;  
     String subject="WELCOME "+name;
     String msg="Hello, Your updated account balance: "+Integer.toString(balance); 
     final String fromemail="goyalshalini30@gmail.com";
     final String pass="24682468.";
    
     Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.user", "goyalshalini30@gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false"); 
              Session session = Session.getDefaultInstance(props, 
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromemail, pass);
                    }
                });
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                message.setText(msg);
                message.setSubject(subject);
                message.setFrom(new InternetAddress(fromemail));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toemail));
                Transport.send(message);    
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 response.setContentType("text/html");
	 PrintWriter out=response.getWriter();
	 String acnumber=request.getParameter("acnumber");
	 String amount=request.getParameter("amount");
	 
	   try{
		   Class.forName("com.mysql.jdbc.Driver");
	   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
	      PreparedStatement ps=conn.prepareStatement("select * from openac where idopenac=?");
	      ps.setString(1,acnumber);
	     ResultSet rs=ps.executeQuery();
	      if(rs.next()){
	      String status=rs.getString(10); 
	     String balance=rs.getString(11);
             String name=rs.getString(2);
             String email=rs.getString(7);
	     if(status.equals("C")){
	         out.print("<div style='position:absolute;top:93%;left:42%;'><h4 style=color:red;top:600px;left:700px;>" +
			 " Sorry Account is Closed</h4></div>");
			RequestDispatcher rd=request.getRequestDispatcher("deposit.jsp");
			rd.include(request, response);
	     }
	     else {
	     int net= Integer.parseInt(amount)+Integer.parseInt(balance);
	     PreparedStatement p=conn.prepareStatement("update openac set status=?, balance=? where idopenac=? ");
	     p.setString(1,"O");
	     p.setInt(2,net);
	     p.setString(3,acnumber);
	     p.executeUpdate();
	     
             processRequest(request, response,email,name,net);    
                            
	     out.print("<center>"+"Updated Balance:"+net);
	     RequestDispatcher rd1=request.getRequestDispatcher("process.jsp");
   	  rd1.include(request,response);
	     }
	     }
	      else {  out.print("<div style='position:absolute;top:4%;left:45%;'><h4 style=color:yellow;font-size:20px;top:20px;left:720px;>" +
			 " Register First</h4></div>");
	    	  RequestDispatcher rd1=request.getRequestDispatcher("open.jsp");
	    	  rd1.include(request,response);
	      }
	   }catch(Exception e){
		   e.printStackTrace(); 
	   }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
