package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the materijal database table.
 * 
 */
@Entity
@NamedQuery(name="Materijal.findAll", query="SELECT m FROM Materijal m")
public class Materijal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String naziv;

	private String proizvodjac;

	//bi-directional many-to-one association to IzvrsenaIntervencija
	@OneToMany(mappedBy="materijal")
	@JsonIgnore
	private List<IzvrsenaIntervencija> izvrsenaIntervencijas;

	public Materijal() {
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

	public String getProizvodjac() {
		return this.proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public List<IzvrsenaIntervencija> getIzvrsenaIntervencijas() {
		return this.izvrsenaIntervencijas;
	}

	public void setIzvrsenaIntervencijas(List<IzvrsenaIntervencija> izvrsenaIntervencijas) {
		this.izvrsenaIntervencijas = izvrsenaIntervencijas;
	}

	public IzvrsenaIntervencija addIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().add(izvrsenaIntervencija);
		izvrsenaIntervencija.setMaterijal(this);

		return izvrsenaIntervencija;
	}

	public IzvrsenaIntervencija removeIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().remove(izvrsenaIntervencija);
		izvrsenaIntervencija.setMaterijal(null);

		return izvrsenaIntervencija;
	}

}