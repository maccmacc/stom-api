package com.stom.app.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * The persistent class for the pacijent database table.
 * 
 */
@Entity
@NamedQuery(name="Pacijent.findAll", query="SELECT p FROM Pacijent p")
public class Pacijent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String adresa;

	@Temporal(TemporalType.DATE)
	@Column(name="datum_rodjenja")
	private Date datumRodjenja;

	private String ime;

	private String kontakt;

	private String napomena;

	private String prezime;

	@Transient
	private String lookup;
	//bi-directional many-to-one association to IzvrsenaIntervencija
	@OneToMany(mappedBy="pacijent")
	@JsonIgnore
	private List<IzvrsenaIntervencija> izvrsenaIntervencijas;

	//bi-directional many-to-one association to PlanRada
	@OneToMany(mappedBy="pacijent")
	@JsonIgnore
	private List<PlanRada> planRadas;

	//bi-directional many-to-one association to Racun
	@OneToMany(mappedBy="pacijent")
	@JsonIgnore
	private List<Racun> racuns;

	//bi-directional many-to-one association to Snimak
	@OneToMany(mappedBy="pacijent")
	@JsonIgnore
	private List<Snimak> snimaks;

	//bi-directional many-to-one association to Termin
	@OneToMany(mappedBy="pacijent")
	@JsonIgnore
	private List<Termin> termins;

	//bi-directional many-to-one association to Zub
	@OneToMany(mappedBy="pacijent")
	@JsonIgnore
	private List<Zub> zubs;
	
	private String email;
	
	@Temporal(TemporalType.DATE)
	@Column(name="datum_upisa")
	private Date datumUpisa;

	public Pacijent() {
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

	public Date getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getKontakt() {
		return this.kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public String getNapomena() {
		return this.napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<IzvrsenaIntervencija> getIzvrsenaIntervencijas() {
		return this.izvrsenaIntervencijas;
	}

	public void setIzvrsenaIntervencijas(List<IzvrsenaIntervencija> izvrsenaIntervencijas) {
		this.izvrsenaIntervencijas = izvrsenaIntervencijas;
	}

	public IzvrsenaIntervencija addIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().add(izvrsenaIntervencija);
		izvrsenaIntervencija.setPacijent(this);

		return izvrsenaIntervencija;
	}

	public IzvrsenaIntervencija removeIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().remove(izvrsenaIntervencija);
		izvrsenaIntervencija.setPacijent(null);

		return izvrsenaIntervencija;
	}

	public List<PlanRada> getPlanRadas() {
		return this.planRadas;
	}

	public void setPlanRadas(List<PlanRada> planRadas) {
		this.planRadas = planRadas;
	}

	public PlanRada addPlanRada(PlanRada planRada) {
		getPlanRadas().add(planRada);
		planRada.setPacijent(this);

		return planRada;
	}

	public PlanRada removePlanRada(PlanRada planRada) {
		getPlanRadas().remove(planRada);
		planRada.setPacijent(null);

		return planRada;
	}

	public List<Racun> getRacuns() {
		return this.racuns;
	}

	public void setRacuns(List<Racun> racuns) {
		this.racuns = racuns;
	}

	public Racun addRacun(Racun racun) {
		getRacuns().add(racun);
		racun.setPacijent(this);

		return racun;
	}

	public Racun removeRacun(Racun racun) {
		getRacuns().remove(racun);
		racun.setPacijent(null);

		return racun;
	}

	public List<Snimak> getSnimaks() {
		return this.snimaks;
	}

	public void setSnimaks(List<Snimak> snimaks) {
		this.snimaks = snimaks;
	}

	public Snimak addSnimak(Snimak snimak) {
		getSnimaks().add(snimak);
		snimak.setPacijent(this);

		return snimak;
	}

	public Snimak removeSnimak(Snimak snimak) {
		getSnimaks().remove(snimak);
		snimak.setPacijent(null);

		return snimak;
	}

	public List<Termin> getTermins() {
		return this.termins;
	}

	public void setTermins(List<Termin> termins) {
		this.termins = termins;
	}

	public Termin addTermin(Termin termin) {
		getTermins().add(termin);
		termin.setPacijent(this);

		return termin;
	}

	public Termin removeTermin(Termin termin) {
		getTermins().remove(termin);
		termin.setPacijent(null);

		return termin;
	}

	public List<Zub> getZubs() {
		return this.zubs;
	}

	public void setZubs(List<Zub> zubs) {
		this.zubs = zubs;
	}

	public Zub addZub(Zub zub) {
		getZubs().add(zub);
		zub.setPacijent(this);

		return zub;
	}

	public Zub removeZub(Zub zub) {
		getZubs().remove(zub);
		zub.setPacijent(null);

		return zub;
	}

	public String getLookup() {
		return ime + " " + prezime;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDatumUpisa() {
		return this.datumUpisa;
	}

	public void setDatumUpisa(Date datumUpisa) {
		this.datumUpisa = datumUpisa;
	}	
}