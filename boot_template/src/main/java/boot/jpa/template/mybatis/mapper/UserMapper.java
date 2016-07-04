package boot.jpa.template.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
	
	@Select("SELECT NOW()")
	String getCurrentDateTime();
	
}
