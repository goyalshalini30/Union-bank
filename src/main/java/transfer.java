

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
 * Servlet implementation class transfer
 */
public class transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transfer() {
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
 
        System.out.println("in try start");
              Session session = Session.getDefaultInstance(props, 
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromemail, pass);
                    }
                });
              System.out.println("in 1"); 
              session.setDebug(true);
              System.out.println("in 2");  
              MimeMessage message = new MimeMessage(session);
              System.out.println("in 3");
              message.setText(msg);
              System.out.println("in 4");
                message.setSubject(subject);
                message.setFrom(new InternetAddress(fromemail));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toemail));
                  System.out.println("in 6");
                Transport.send(message);    
                 System.out.println("in 5");

            System.out.println("Mail sent succesfully!");  
  }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		 String source=request.getParameter("srcacnumber");
		 String destination=request.getParameter("desacnumber");
		 int amount=Integer.parseInt(request.getParameter("amount"));
		 try{ String status1=null;
		       int balance1=0;
		       String status2=null;
		       int balance2=0;
			   Class.forName("com.mysql.jdbc.Driver");
		   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
		      PreparedStatement ps1=conn.prepareStatement("select * from openac where idopenac=?");
		      ps1.setString(1,source);
		     ResultSet rs1=ps1.executeQuery();
                     
                     String email=null;
                     String name=null;
		      if(rs1.next()){
				     status1=rs1.getString(10); 
                                     email=rs1.getString(7);
                                     name=rs1.getString(2);
			         balance1=Integer.parseInt(rs1.getString(11));
			         }
		     PreparedStatement ps2=conn.prepareStatement("select  status,balance from openac where idopenac=?");
		      ps2.setString(1,destination);
		     ResultSet rs2=ps2.executeQuery();
		    System.out.println("account number exist/not");
		     if(rs2.next()){
		     status2=rs2.getString(1);
		     balance2=Integer.parseInt(rs2.getString(2));
                   
		    System.out.println("if source account closed");
		      }
		       if(status1.equals("C")){
				         out.print("<div style='position:absolute;top:93%;left:42%;'><h4 style=color:red;top:600px;left:700px;>" +
						 " Sorry Source Account is Closed</h4></div>");
						RequestDispatcher rd=request.getRequestDispatcher("transfer.jsp");
						rd.include(request, response);
						System.out.println("source account closed");
				     }
				     //if destination account is closed
		       else if(status2.equals("C")){
				         out.print("<div style='position:absolute;top:89%;left:42%;'><h4 style=color:red;top:500px;left:700px;>" +
						 " Sorry Destination Account is Closed</h4></div>");
						RequestDispatcher rd=request.getRequestDispatcher("transfer.jsp");
						rd.include(request, response);
						System.out.println("destination account closed");
				     }// f source account is new
		       else if(status1.equals("N")){
					        out.print("<center>"+"<h4 style=color:red;> Sorry zero balance in Source Account</h4>");
							RequestDispatcher rd=request.getRequestDispatcher("process.jsp");
							rd.include(request, response);
							System.out.println("source account is new");
					     }  //sufficient balance
		        else if(amount>balance1)
		                {  out.print("<center>"+"<h4 style=color:red;> Insufficient Balance in source Account</h4>");
                         RequestDispatcher rd=request.getRequestDispatcher("process.jsp");
                         rd.include(request, response);
                         }
	                 else{ int net1=balance1-amount;
		  PreparedStatement p1=conn.prepareStatement("UPDATE openac SET  balance=? WHERE idopenac=? ");
					  	     p1.setInt(1,net1);
					     p1.setString(2,source);
					     p1.executeUpdate();
					     int net2=balance2+amount;
					     PreparedStatement p2=conn.prepareStatement("UPDATE openac SET  balance=?,status=? WHERE idopenac=? ");
					  	     p2.setInt(1,net2);
					  	     p2.setString(2,"O");
					         p2.setString(3,destination);
					         p2.executeUpdate();
                                                 
          processRequest(request, response,email,name,net1);   
					         out.print("<center>"+"Updated Balance:"+net1+"</center>");
					     RequestDispatcher rd=request.getRequestDispatcher("process.jsp");
				      rd.include(request, response);
					    }
				   
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
