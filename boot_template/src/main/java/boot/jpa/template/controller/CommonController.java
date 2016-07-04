package boot.jpa.template.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import boot.jpa.template.jpa.entity.UserDao;
import boot.jpa.template.jpa.repository.UserInterface;
import boot.jpa.template.mongo.dao.RequestDao;
import boot.jpa.template.mybatis.mapper.service.UserMapperService;

@RestController
public class CommonController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MongoTemplate mongo;
	
	@Autowired
	private UserInterface userInterface;
	
	@Autowired
	private UserMapperService userMapperService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getTest(@RequestParam Map<String, Object> params
			, HttpServletRequest request) {

		JsonObject result = new JsonObject();
//		String temp[] = null;
		result.addProperty("status", 200);
//		System.out.println(temp[0]);
		
		String time = userMapperService.getCurrentDateTime();
		
		System.out.println(time);
		
		return result.toString();
	}
	
	@RequestMapping(value = "/insert" ,method = RequestMethod.POST)
	public void setTest(@RequestParam Map<String, Object> params
			, HttpServletRequest request
			, UserDao userDao) {
		
		userInterface.save(userDao);
		RequestDao requestDao = new RequestDao();
		requestDao.setParameter(params.toString());
		mongo.insert(requestDao);
		
	}

}
