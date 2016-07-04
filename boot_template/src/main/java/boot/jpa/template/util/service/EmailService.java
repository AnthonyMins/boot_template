package boot.jpa.template.util.service;

import javax.servlet.http.HttpServletRequest;

public interface EmailService {
	
	public void getTest();
	public void sendMailInit(HttpServletRequest request ,Exception e ,String params);
	public void sendMail(String to ,String subject ,String msg);
}
