package ar.com.cuys.webapp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity
public class Subject {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 1, message = "El titulo no debe ser vacio")
	private String title;

	@Size(min = 1, message = "La url no debe ser vacia")
	private String urlTitle;

	private String referenceLink;
	
	@ManyToMany(mappedBy = "subjects")	
	private List<Post> posts;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReferenceLink() {
		return referenceLink;
	}

	public void setReferenceLink(String referenceLink) {
		this.referenceLink = referenceLink;
	}

}
