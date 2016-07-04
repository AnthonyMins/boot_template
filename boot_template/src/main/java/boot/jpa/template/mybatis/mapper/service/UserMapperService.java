package boot.jpa.template.mybatis.mapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.jpa.template.mybatis.mapper.UserMapper;

@Service
public class UserMapperService {

	@Autowired
	private UserMapper userMapper;
	
	public String getCurrentDateTime(){
		return userMapper.getCurrentDateTime();
	}
}
