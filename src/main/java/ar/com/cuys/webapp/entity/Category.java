package ar.com.cuys.webapp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 1, message = "El nombre no debe ser vacio")
	private String name;

	@Size(min = 1, message = "La url no debe ser vacia")
	private String url;

	@ManyToMany(mappedBy = "categories")	
	private List<Post> posts;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", url=" + url + "]";
	}

}
