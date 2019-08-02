package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the radno_mjesto database table.
 * 
 */
@Entity
@Table(name="radno_mjesto")
@NamedQuery(name="RadnoMjesto.findAll", query="SELECT r FROM RadnoMjesto r")
public class RadnoMjesto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String naziv;

	//bi-directional many-to-one association to IzvrsenaIntervencija
	@OneToMany(mappedBy="radnoMjesto")
	@JsonIgnore
	private List<IzvrsenaIntervencija> izvrsenaIntervencijas;

	//bi-directional many-to-one association to Ordinacija
	@ManyToOne
	@JoinColumn(name="ordinacija")
	private Ordinacija ordinacija;

	//bi-directional many-to-one association to Termin
	@OneToMany(mappedBy="radnoMjesto")
	@JsonIgnore
	private List<Termin> termins;

	public RadnoMjesto() {
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

	public List<IzvrsenaIntervencija> getIzvrsenaIntervencijas() {
		return this.izvrsenaIntervencijas;
	}

	public void setIzvrsenaIntervencijas(List<IzvrsenaIntervencija> izvrsenaIntervencijas) {
		this.izvrsenaIntervencijas = izvrsenaIntervencijas;
	}

	public IzvrsenaIntervencija addIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().add(izvrsenaIntervencija);
		izvrsenaIntervencija.setRadnoMjesto(this);

		return izvrsenaIntervencija;
	}

	public IzvrsenaIntervencija removeIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().remove(izvrsenaIntervencija);
		izvrsenaIntervencija.setRadnoMjesto(null);

		return izvrsenaIntervencija;
	}

	public Ordinacija getOrdinacija() {
		return this.ordinacija;
	}

	public void setOrdinacija(Ordinacija ordinacija) {
		this.ordinacija = ordinacija;
	}

	public List<Termin> getTermins() {
		return this.termins;
	}

	public void setTermins(List<Termin> termins) {
		this.termins = termins;
	}

	public Termin addTermin(Termin termin) {
		getTermins().add(termin);
		termin.setRadnoMjesto(this);

		return termin;
	}

	public Termin removeTermin(Termin termin) {
		getTermins().remove(termin);
		termin.setRadnoMjesto(null);

		return termin;
	}
}