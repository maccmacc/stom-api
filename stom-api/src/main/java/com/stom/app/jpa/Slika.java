package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the zub database table.
 * 
 */
@Entity
@NamedQuery(name="Slika.findAll", query="SELECT s FROM Slika s")
public class Slika implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String naziv;

	private String putanjaaplikacija;

	private String putanjaizvestaj;



	//bi-directional many-to-one association to Zub


	@JsonIgnore
	@OneToMany(mappedBy="slika")
	private List<Status> status12;

	public Slika() {
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPutanjaaplikacija() {
		return putanjaaplikacija;
	}

	public void setPutanjaaplikacija(String putanjaaplikacija) {
		this.putanjaaplikacija = putanjaaplikacija;
	}

	public String getPutanjaizvestaj() {
		return putanjaizvestaj;
	}

	public void setPutanjaizvestaj(String putanjaizvestaj) {
		this.putanjaizvestaj = putanjaizvestaj;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public List<Status> getStatus12(){
		return this.status12;
	}


	public void setStatus12(List<Status> status12 ){
		this.status12 = status12;
	}



	public Status addStatus12(Status s){
		getStatus12().add(s);
		s.setSlika(this);
		return s;
	}



	public Status removeStatus12(Status s){
		getStatus12().remove(s);
		s.setSlika(null);
		return s;
	}


}