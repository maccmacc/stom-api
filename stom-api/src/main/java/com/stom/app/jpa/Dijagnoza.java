package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the dijagnoza database table.
 * 
 */
@Entity
@NamedQuery(name="Dijagnoza.findAll", query="SELECT d FROM Dijagnoza d")
public class Dijagnoza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String opis;

	//bi-directional many-to-one association to IzvrsenaIntervencija
	@OneToMany(mappedBy="dijagnoza")
	@JsonIgnore
	private List<IzvrsenaIntervencija> izvrsenaIntervencijas;

	//bi-directional many-to-one association to StavkaPlanaRada
	@OneToMany(mappedBy="dijagnoza")
	@JsonIgnore
	private List<StavkaPlanaRada> stavkaPlanaRadas;

	public Dijagnoza() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<IzvrsenaIntervencija> getIzvrsenaIntervencijas() {
		return this.izvrsenaIntervencijas;
	}

	public void setIzvrsenaIntervencijas(List<IzvrsenaIntervencija> izvrsenaIntervencijas) {
		this.izvrsenaIntervencijas = izvrsenaIntervencijas;
	}

	public IzvrsenaIntervencija addIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().add(izvrsenaIntervencija);
		izvrsenaIntervencija.setDijagnoza(this);

		return izvrsenaIntervencija;
	}

	public IzvrsenaIntervencija removeIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().remove(izvrsenaIntervencija);
		izvrsenaIntervencija.setDijagnoza(null);

		return izvrsenaIntervencija;
	}

	public List<StavkaPlanaRada> getStavkaPlanaRadas() {
		return this.stavkaPlanaRadas;
	}

	public void setStavkaPlanaRadas(List<StavkaPlanaRada> stavkaPlanaRadas) {
		this.stavkaPlanaRadas = stavkaPlanaRadas;
	}

	public StavkaPlanaRada addStavkaPlanaRada(StavkaPlanaRada stavkaPlanaRada) {
		getStavkaPlanaRadas().add(stavkaPlanaRada);
		stavkaPlanaRada.setDijagnoza(this);

		return stavkaPlanaRada;
	}

	public StavkaPlanaRada removeStavkaPlanaRada(StavkaPlanaRada stavkaPlanaRada) {
		getStavkaPlanaRadas().remove(stavkaPlanaRada);
		stavkaPlanaRada.setDijagnoza(null);

		return stavkaPlanaRada;
	}

}