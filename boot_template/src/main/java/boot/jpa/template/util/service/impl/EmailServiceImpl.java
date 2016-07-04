package boot.jpa.template.util.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import boot.jpa.template.util.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	private JavaMailSenderImpl mailSender;	 

	public void getTest(){
		System.out.println("### Test Method ###");
	}
	
	public void sendMailInit(HttpServletRequest request ,Exception e ,String params){
		
		String msg = null ,url = null;
		
		url = request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
		
		msg = "Url : " + url + "<br/><br/>";
		msg += "Parameter : " + params +"<br/><br/>";
		
		StringBuilder sb = new StringBuilder();
	    for (StackTraceElement element : e.getStackTrace()) {
	        sb.append(element.toString());
	        sb.append("<br/>");
	    }
	    
	    msg += "Error Massege : " + sb.toString();
		
		this.sendMail("anthony@13mile.co.kr", url + " Exception Error", msg);
	}
	
	public void sendMail(String to ,String subject ,String msg){
		try {
			MimeMessage message = mailSender.createMimeMessage();
			message.setHeader("Content-Type", "text/plain; charset=UTF-8");

			MimeMessageHelper helper;
			
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("anthony@13mile.co.kr");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg, true);
			
			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
