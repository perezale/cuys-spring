package ar.com.cuys.webapp.entity;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.ocpsoft.prettytime.PrettyTime;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=1, message="El titulo no puede ser vacio")
	private String title;
	
	@Size(min=1, message="El mensaje no puede ser vacio")
	private String message;

	@Column(name = "publish_date")
	private Date publishedDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "post", cascade=CascadeType.REMOVE)	
	private List<Attachment> attachments;
	
	@ManyToMany
	@JoinTable	
	private List<Subject> subjects;
	
	@ManyToMany
	@JoinTable	
	private List<Category> categories;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}	

	public String getTimeAgo(){
		PrettyTime p = new PrettyTime(new Locale("ES"));		
		return p.format(this.publishedDate); 
	}
	
}
