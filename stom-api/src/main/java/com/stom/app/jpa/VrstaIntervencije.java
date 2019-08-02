package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the vrsta_intervencije database table.
 * 
 */
@Entity
@Table(name="vrsta_intervencije")
@NamedQuery(name="VrstaIntervencije.findAll", query="SELECT v FROM VrstaIntervencije v")
public class VrstaIntervencije implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private BigDecimal iznos;

	private String opis;

	//bi-directional many-to-one association to IzvrsenaIntervencija
	@OneToMany(mappedBy="vrstaIntervencije")
	@JsonIgnore
	private List<IzvrsenaIntervencija> izvrsenaIntervencijas;

	//bi-directional many-to-one association to StavkaPlanaRada
	@OneToMany(mappedBy="vrstaIntervencije")
	@JsonIgnore
	private List<StavkaPlanaRada> stavkaPlanaRadas;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status_zuba")
	private Status status;

	public VrstaIntervencije() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getIznos() {
		return this.iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
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
		izvrsenaIntervencija.setVrstaIntervencije(this);

		return izvrsenaIntervencija;
	}

	public IzvrsenaIntervencija removeIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().remove(izvrsenaIntervencija);
		izvrsenaIntervencija.setVrstaIntervencije(null);

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
		stavkaPlanaRada.setVrstaIntervencije(this);

		return stavkaPlanaRada;
	}

	public StavkaPlanaRada removeStavkaPlanaRada(StavkaPlanaRada stavkaPlanaRada) {
		getStavkaPlanaRadas().remove(stavkaPlanaRada);
		stavkaPlanaRada.setVrstaIntervencije(null);

		return stavkaPlanaRada;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}