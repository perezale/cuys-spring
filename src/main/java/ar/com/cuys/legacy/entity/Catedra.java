package ar.com.cuys.legacy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Catedra {

	private int id;
	private String title;
	private String url_title;
	private String ref_link;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl_title() {
		return url_title;
	}

	public void setUrl_title(String url_title) {
		this.url_title = url_title;
	}

	public String getRef_link() {
		return ref_link;
	}

	public void setRef_link(String ref_link) {
		this.ref_link = ref_link;
	}

	@Override
	public String toString() {
		return "Catedra [id=" + id + ", title=" + title + ", url_title=" + url_title + ", ref_link=" + ref_link + "]";
	}

}
