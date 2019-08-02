package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the izvrsena_intervencija database table.
 * 
 */
@Entity
@Table(name="izvrsena_intervencija")
@NamedQuery(name="IzvrsenaIntervencija.findAll", query="SELECT i FROM IzvrsenaIntervencija i")
public class IzvrsenaIntervencija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private BigDecimal iznos;

	private BigDecimal placeno;

	private Integer popust;

	private String povrsine;

	private Integer zub;

	//bi-directional many-to-one association to Dijagnoza
	@ManyToOne
	@JoinColumn(name="dijagnoza")
	private Dijagnoza dijagnoza;

	//bi-directional many-to-one association to Materijal
	@ManyToOne
	@JoinColumn(name="materijal")
	private Materijal materijal;

	//bi-directional many-to-one association to Pacijent
	@ManyToOne
	@JoinColumn(name="pacijent")
	private Pacijent pacijent;

	//bi-directional many-to-one association to Racun
	@ManyToOne
	@JoinColumn(name="racun")
	private Racun racun;

	//bi-directional many-to-one association to RadnoMjesto
	@ManyToOne
	@JoinColumn(name="radno_mjesto")
	private RadnoMjesto radnoMjesto;

	//bi-directional many-to-one association to VrstaIntervencije
	@ManyToOne
	@JoinColumn(name="vrsta_intervencije")
	private VrstaIntervencije vrstaIntervencije;

	//bi-directional many-to-one association to Zaposleni
	@ManyToOne
	@JoinColumn(name="zaposleni")
	private Zaposleni zaposleni;

	private String napomena;
	
	public IzvrsenaIntervencija() {
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

	public BigDecimal getIznos() {
		return this.iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public BigDecimal getPlaceno() {
		return this.placeno;
	}

	public void setPlaceno(BigDecimal placeno) {
		this.placeno = placeno;
	}

	public Integer getPopust() {
		return this.popust;
	}

	public void setPopust(Integer popust) {
		this.popust = popust;
	}

	public String getPovrsine() {
		return this.povrsine;
	}

	public void setPovrsine(String povrsine) {
		this.povrsine = povrsine;
	}

	public Integer getZub() {
		return this.zub;
	}

	public void setZub(Integer zub) {
		this.zub = zub;
	}

	public Dijagnoza getDijagnoza() {
		return this.dijagnoza;
	}

	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	public Materijal getMaterijal() {
		return this.materijal;
	}

	public void setMaterijal(Materijal materijal) {
		this.materijal = materijal;
	}

	public Pacijent getPacijent() {
		return this.pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Racun getRacun() {
		return this.racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	public RadnoMjesto getRadnoMjesto() {
		return this.radnoMjesto;
	}

	public void setRadnoMjesto(RadnoMjesto radnoMjesto) {
		this.radnoMjesto = radnoMjesto;
	}

	public VrstaIntervencije getVrstaIntervencije() {
		return this.vrstaIntervencije;
	}

	public void setVrstaIntervencije(VrstaIntervencije vrstaIntervencije) {
		this.vrstaIntervencije = vrstaIntervencije;
	}

	public Zaposleni getZaposleni() {
		return this.zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public String getNapomena() {
		return this.napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

}