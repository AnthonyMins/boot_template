package boot.jpa.template.aop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import boot.jpa.template.util.service.EmailService;

@Aspect
@Component
public class AspectLogging {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmailService emailService;
	
	public Map<String,Object> getKeyValue(JoinPoint joinPoint){
		Map<String,Object> result = new HashMap<String,Object>();
				
		String key = null ,value = null;
		 
		Object paramValue[] = joinPoint.getArgs();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String[] paramKey = signature.getParameterNames();

		for(int i=0;i<paramKey.length;i++){
			result.put(paramKey[i], paramValue[i]);
		}
	     
		return result;

	}
	
	@Before("execution(* boot..*Controller.*(..))")
	public void logginBeforeController(JoinPoint joinPoint){

		Map<String,Object> temp = new HashMap<String,Object>();
		temp = this.getKeyValue(joinPoint);
		 
		logger.info("Method Path : " + joinPoint.getSignature() + " Start");
		logger.info("Method Parameter : " + temp.get("params").toString());
		 
	}
	 
	@Before("execution(* boot..*Service.*(..))")
	public void loggingBeforService(JoinPoint joinPoint){
		logger.info("Method Path : " + joinPoint.getSignature() + " Start");
	}

	@After("execution(* boot..*Controller.*(..))")
	public void logginAfterController(JoinPoint joinPoint){
		logger.info("Method Path : " + joinPoint.getSignature() + " End");
	}
	
	@After("execution(* boot..*Service.*(..))")
	public void loggingAfterService(JoinPoint joinPoint){
		logger.info("Method Path : " + joinPoint.getSignature() + " End");
	}
	
	@AfterThrowing(pointcut = "execution(* boot..*Controller.*(..))", throwing = "e")
	public void errorInterceptor(JoinPoint joinPoint,Exception e){
		logger.debug("Error Message Interceptor started");
		
		Map<String,Object> temp = new HashMap<String,Object>();
		temp = this.getKeyValue(joinPoint);

		emailService.sendMailInit((HttpServletRequest) temp.get("request"), e ,temp.get("params").toString());

		logger.debug("Error Message Interceptor finished.");
	}
	 
}
