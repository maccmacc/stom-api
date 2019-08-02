package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the racun database table.
 * 
 */
@Entity
@NamedQuery(name="Racun.findAll", query="SELECT r FROM Racun r")
public class Racun implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private BigDecimal ukupno;

	//bi-directional many-to-one association to IzvrsenaIntervencija
	@OneToMany(mappedBy="racun")
	@JsonIgnore
	private List<IzvrsenaIntervencija> izvrsenaIntervencijas;

	//bi-directional many-to-one association to Pacijent
	@ManyToOne
	@JoinColumn(name="pacijent")
	private Pacijent pacijent;

	@Transient
	private String lookup;
	
	public Racun() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public BigDecimal getUkupno() {
		return this.ukupno;
	}

	public void setUkupno(BigDecimal ukupno) {
		this.ukupno = ukupno;
	}

	public List<IzvrsenaIntervencija> getIzvrsenaIntervencijas() {
		return this.izvrsenaIntervencijas;
	}

	public void setIzvrsenaIntervencijas(List<IzvrsenaIntervencija> izvrsenaIntervencijas) {
		this.izvrsenaIntervencijas = izvrsenaIntervencijas;
	}

	public IzvrsenaIntervencija addIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().add(izvrsenaIntervencija);
		izvrsenaIntervencija.setRacun(this);

		return izvrsenaIntervencija;
	}

	public IzvrsenaIntervencija removeIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().remove(izvrsenaIntervencija);
		izvrsenaIntervencija.setRacun(null);

		return izvrsenaIntervencija;
	}

	public Pacijent getPacijent() {
		return this.pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public String getLookup() {
		String rez = String.valueOf(id);
		if (this.datum != null)
			rez = rez + ", " + this.datum;
		if (this.pacijent != null)
			rez = rez + ", " + this.pacijent.getLookup();
		return rez;
	}
}