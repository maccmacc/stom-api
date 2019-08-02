package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the struka database table.
 * 
 */
@Entity
@NamedQuery(name="Struka.findAll", query="SELECT s FROM Struka s")
public class Struka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String naziv;

	private String stepen;

	//bi-directional many-to-one association to Zaposleni
	@OneToMany(mappedBy="struka")
	@JsonIgnore
	private List<Zaposleni> zaposlenis;

	public Struka() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getStepen() {
		return this.stepen;
	}

	public void setStepen(String stepen) {
		this.stepen = stepen;
	}

	public List<Zaposleni> getZaposlenis() {
		return this.zaposlenis;
	}

	public void setZaposlenis(List<Zaposleni> zaposlenis) {
		this.zaposlenis = zaposlenis;
	}

	public Zaposleni addZaposleni(Zaposleni zaposleni) {
		getZaposlenis().add(zaposleni);
		zaposleni.setStruka(this);

		return zaposleni;
	}

	public Zaposleni removeZaposleni(Zaposleni zaposleni) {
		getZaposlenis().remove(zaposleni);
		zaposleni.setStruka(null);

		return zaposleni;
	}

}