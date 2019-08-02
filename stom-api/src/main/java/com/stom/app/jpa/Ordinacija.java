package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the ordinacija database table.
 * 
 */
@Entity
@NamedQuery(name="Ordinacija.findAll", query="SELECT o FROM Ordinacija o")
public class Ordinacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String adresa;

	private String naziv;

	//bi-directional many-to-one association to RadnoMjesto
	@OneToMany(mappedBy="ordinacija")
	@JsonIgnore
	private List<RadnoMjesto> radnoMjestos;

	public Ordinacija() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<RadnoMjesto> getRadnoMjestos() {
		return this.radnoMjestos;
	}

	public void setRadnoMjestos(List<RadnoMjesto> radnoMjestos) {
		this.radnoMjestos = radnoMjestos;
	}

	public RadnoMjesto addRadnoMjesto(RadnoMjesto radnoMjesto) {
		getRadnoMjestos().add(radnoMjesto);
		radnoMjesto.setOrdinacija(this);

		return radnoMjesto;
	}

	public RadnoMjesto removeRadnoMjesto(RadnoMjesto radnoMjesto) {
		getRadnoMjestos().remove(radnoMjesto);
		radnoMjesto.setOrdinacija(null);

		return radnoMjesto;
	}

}