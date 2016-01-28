package ar.com.cuys.webapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import ar.com.cuys.webapp.annotations.UniqueUsername;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=3, message="Debe tener al menos 3 caracteres")
	@Column(unique=true)
	@UniqueUsername(message="El nombre de usuario ingresado ya existe")
	private String name;
	
	@Size(min=1, message="Dirección de email inválida")
	@Email(message="Dirección de email inválida")
	private String email;
	
	@Size(min=5, message="Debe tener al menos 5 caracteres")
	private String password;
	
	private boolean enabled;
	
	@ManyToMany
	@JoinTable
	private List<Role> roles;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<Post> posts;

	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
