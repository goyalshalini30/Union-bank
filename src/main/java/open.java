
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "open", urlPatterns = {"/open"})
public class open extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public open() {
        super();
        // TODO Auto-generated constructor stub
    }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response,String email,String name) throws MessagingException{

     String toemail=email;  
     String subject="WELCOME "+name;
     String msg="Hello, Thank you so much for allowing us to help you with your recent account opening. We are committed to providing our customers with the highest level of service and the most innovative banking products possible"; 
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
     	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
		
        response.setContentType("text/html;charset=UTF-8");
		   PrintWriter write=response.getWriter();
		   String name=request.getParameter("name");
		   String gender=request.getParameter("gender");
		   String address=request.getParameter("address");
		   String country=request.getParameter("country");
		   String mob=request.getParameter("mob");
		   String email=request.getParameter("email");
		   String dob=request.getParameter("dob");
		   String password=request.getParameter("password");
		   String status="N";
		   String balance="0";	
			   try{ 
			   Class.forName("com.mysql.jdbc.Driver");
			   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
		      PreparedStatement ps=conn.prepareStatement("insert into openac(name,gender,address,country,mobile,email,dob,password,status,balance)"+"values(?,?,?,?,?,?,?,?,?,?)");
		      ps.setString(1,name );
				ps.setString(2,gender);
				ps.setString(3,address);
				ps.setString(4,country);
				ps.setString(5,mob);
				ps.setString(6,email);
				ps.setString(7,dob);
				ps.setString(8,password);
				ps.setString(9,status);
				ps.setString(10,balance);
				
                                int i = ps.executeUpdate();
				
                                   processRequest(request, response,email,name);    
                                if(i>0){
					RequestDispatcher rd=request.getRequestDispatcher("Front.jsp");
					rd.forward(request, response);
				}
			}catch(Exception e1){
				e1.printStackTrace();  
			}
      
        }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
