package boot.jpa.template.jpa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "tbl_user")
public class UserDao {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date regdate = new Date();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
    public String toString() {
		String result = "User Table : "
			+ "ID : " + id + " ,Name : " + name + " ,RegDate : " + regdate; 	
        return result; 
    }
	
}
