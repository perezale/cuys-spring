package ar.com.cuys.webapp.entity;

import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.URL;
import org.ocpsoft.prettytime.PrettyTime;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private Integer id;

	private String title;

	@Column(columnDefinition = "longvarchar")
	private String description;

	@Column(name = "publish_date")
	private Date publishedDate;

	@URL(message = "URL inválida")
	private String link;

	@ManyToOne
	@JoinColumn(name = "blog_id")
	private Blog blog;

	@URL(message = "URL inválida")
	private String thumbnail;

	public Integer getId() {
		return id;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog post) {
		this.blog = post;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTimeAgo() {
		PrettyTime p = new PrettyTime(new Locale("ES"));
		return p.format(this.publishedDate);
	}

}
