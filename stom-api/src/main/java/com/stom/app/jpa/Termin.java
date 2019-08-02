package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the termin database table.
 * 
 */
@Entity
@NamedQuery(name="Termin.findAll", query="SELECT t FROM Termin t")
public class Termin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String napomena;

	private Time pocetak;

	private Time zavrsetak;

	@Temporal(TemporalType.DATE)
	private Date datum;

	//bi-directional many-to-one association to Pacijent
	@ManyToOne
	@JoinColumn(name="pacijent")
	private Pacijent pacijent;

	//bi-directional many-to-one association to RadnoMjesto
	@ManyToOne
	@JoinColumn(name="radno_mjesto")
	private RadnoMjesto radnoMjesto;

	//bi-directional many-to-one association to Zaposleni
	@ManyToOne
	@JoinColumn(name="zaposleni")
	private Zaposleni zaposleni;

	public Termin() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNapomena() {
		return this.napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public Time getPocetak() {
		return this.pocetak;
	}

	public void setPocetak(Time pocetak) {
		this.pocetak = pocetak;
	}

	public Time getZavrsetak() {
		return this.zavrsetak;
	}

	public void setZavrsetak(Time zavrsetak) {
		this.zavrsetak = zavrsetak;
	}

	public Pacijent getPacijent() {
		return this.pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public RadnoMjesto getRadnoMjesto() {
		return this.radnoMjesto;
	}

	public void setRadnoMjesto(RadnoMjesto radnoMjesto) {
		this.radnoMjesto = radnoMjesto;
	}

	public Zaposleni getZaposleni() {
		return this.zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}
}