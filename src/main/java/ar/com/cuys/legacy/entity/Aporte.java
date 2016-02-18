package ar.com.cuys.legacy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aporte {

	private int entry_id;
	private String title;
	private int catedra;
	private String path;
	private List<Integer> categorias;
	
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
	public int getCatedra() {
		return catedra;
	}
	public void setCatedra(int catedra) {
		this.catedra = catedra;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<Integer> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Integer> categorias) {
		this.categorias = categorias;
	}
	@Override
	public String toString() {
		return "Aporte [entry_id=" + entry_id + ", title=" + title + ", catedra=" + catedra + ", path=" + path + ", categorias="
				+ categorias + "]";
	}
	
	
		
}
