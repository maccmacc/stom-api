package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the zaposleni database table.
 * 
 */
@Entity
@NamedQuery(name="Zaposleni.findAll", query="SELECT z FROM Zaposleni z")
public class Zaposleni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String adresa;

	private String ime;

	private String kontakt;

	private String prezime;
	
	private String username;
	
	private String password;

	//bi-directional many-to-one association to IzvrsenaIntervencija
	@OneToMany(mappedBy="zaposleni")
	@JsonIgnore
	private List<IzvrsenaIntervencija> izvrsenaIntervencijas;

	//bi-directional many-to-one association to PlanRada
	@OneToMany(mappedBy="zaposleni")
	@JsonIgnore
	private List<PlanRada> planRadas;

	//bi-directional many-to-one association to Termin
	@OneToMany(mappedBy="zaposleni")
	@JsonIgnore
	private List<Termin> termins;

	//bi-directional many-to-one association to Struka
	@ManyToOne
	@JoinColumn(name="struka")
	private Struka struka;
	
	@Transient
	private String lookup;

	public Zaposleni() {
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

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<IzvrsenaIntervencija> getIzvrsenaIntervencijas() {
		return this.izvrsenaIntervencijas;
	}

	public void setIzvrsenaIntervencijas(List<IzvrsenaIntervencija> izvrsenaIntervencijas) {
		this.izvrsenaIntervencijas = izvrsenaIntervencijas;
	}

	public IzvrsenaIntervencija addIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().add(izvrsenaIntervencija);
		izvrsenaIntervencija.setZaposleni(this);

		return izvrsenaIntervencija;
	}

	public IzvrsenaIntervencija removeIzvrsenaIntervencija(IzvrsenaIntervencija izvrsenaIntervencija) {
		getIzvrsenaIntervencijas().remove(izvrsenaIntervencija);
		izvrsenaIntervencija.setZaposleni(null);

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
		planRada.setZaposleni(this);

		return planRada;
	}

	public PlanRada removePlanRada(PlanRada planRada) {
		getPlanRadas().remove(planRada);
		planRada.setZaposleni(null);

		return planRada;
	}

	public List<Termin> getTermins() {
		return this.termins;
	}

	public void setTermins(List<Termin> termins) {
		this.termins = termins;
	}

	public Termin addTermin(Termin termin) {
		getTermins().add(termin);
		termin.setZaposleni(this);

		return termin;
	}

	public Termin removeTermin(Termin termin) {
		getTermins().remove(termin);
		termin.setZaposleni(null);

		return termin;
	}

	public Struka getStruka() {
		return this.struka;
	}

	public void setStruka(Struka struka) {
		this.struka = struka;
	}
	
	public String getLookup() {
		String s = ime + " " + prezime;
		if (struka != null)
			s = s + ", " + struka.getNaziv();
		return s;
	}
}