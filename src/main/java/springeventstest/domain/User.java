package springeventstest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="mrf_user")
public class User {

	@Id
	@Column(name="ID")
	@GeneratedValue(generator="tableGenerator",strategy=GenerationType.TABLE)
	@TableGenerator(name="tableGenerator", table="mrf_table_seq", pkColumnName="seq_name", pkColumnValue="user.ID", valueColumnName="seq_value")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="AGE")
	private Integer age;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTIVE_UNTIL")
	private Date activeUntil;
	
	@Column(name="STATUS")
	private Boolean status;
	
	public User() {
		
	}
	
	public User(Long id, String name, Integer age, Date activeUntil, Boolean status) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.activeUntil = activeUntil;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}	

	public Date getActiveUntil() {
		return activeUntil;
	}

	public void setActiveUntil(Date activeUntil) {
		this.activeUntil = activeUntil;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
}
