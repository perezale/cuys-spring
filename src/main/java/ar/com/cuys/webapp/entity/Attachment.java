package ar.com.cuys.webapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.URL;

@Entity
public class Attachment {

	@Id
	@GeneratedValue
	private Integer id;
	
	@URL(message="URL inv�lida")
	private String link;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

}
