package ar.com.cuys.legacy.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost {

	private int entry_id;
	private String title;
	private String url_title;
	private Date entry_date;
	private String imagen_destacada;
	private String entrada;

	public int getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
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

	public Date getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public String getImagen_destacada() {
		return imagen_destacada;
	}

	public void setImagen_destacada(String imagen_destacada) {
		this.imagen_destacada = imagen_destacada;
	}
	
	public String getThumbnail(){
		return this.imagen_destacada.replace("uploads/", "uploads/_43/");
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	@Override
	public String toString() {
		return String.format(
				"BlogPost [entry_id=%s, title=%s, url_title=%s, entry_date=%s, imagen_destacada=%s]",
				entry_id, title, url_title, entry_date, imagen_destacada);
	}

}
