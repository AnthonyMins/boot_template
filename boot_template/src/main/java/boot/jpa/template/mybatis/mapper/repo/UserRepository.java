package boot.jpa.template.mybatis.mapper.repo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public String getCurrentDateTime(){
		return sqlSession.selectOne("sample.getCurrentDateTime");
	}
	
}
