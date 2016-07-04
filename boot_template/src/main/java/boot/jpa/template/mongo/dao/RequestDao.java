package boot.jpa.template.mongo.dao;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "request")
public class RequestDao {

	@Id
	private String id;
	private String parameter;
	@Temporal(TemporalType.TIMESTAMP)
    private Date regdate = new Date();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
    public String toString() {
		String result = "Request Collection : "
			+ "ID : " + id + " ,Parameter : " + parameter + " ,RegDate : " + regdate; 	
        return result; 
    }
	
}
