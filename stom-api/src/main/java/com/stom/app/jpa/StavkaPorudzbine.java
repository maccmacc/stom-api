package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stavka_porudzbine database table.
 * 
 */
@Entity
@Table(name="stavka_porudzbine")
@NamedQuery(name="StavkaPorudzbine.findAll", query="SELECT s FROM StavkaPorudzbine s")
public class StavkaPorudzbine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private BigDecimal cena;

	@Column(name="jedinica_mere")
	private String jedinicaMere;

	private BigDecimal kolicina;

	@Column(name="redni_broj")
	private Integer redniBroj;

	//bi-directional many-to-one association to Artikl
	@ManyToOne
	@JoinColumn(name="artikl")
	private Artikl artikl;

	//bi-directional many-to-one association to Porudzbina
	@ManyToOne
	@JoinColumn(name="porudzbina")
	private Porudzbina porudzbina;
	
	@Transient
	private BigDecimal ukupno;

	public StavkaPorudzbine() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCena() {
		return this.cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public String getJedinicaMere() {
		return this.jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public BigDecimal getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(BigDecimal kolicina) {
		this.kolicina = kolicina;
	}

	public Integer getRedniBroj() {
		return this.redniBroj;
	}

	public void setRedniBroj(Integer redniBroj) {
		this.redniBroj = redniBroj;
	}

	public Artikl getArtikl() {
		return this.artikl;
	}

	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}

	public Porudzbina getPorudzbina() {
		return this.porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}
	
	public BigDecimal getUkupno(){		
		return new BigDecimal (this.cena.doubleValue() * this.kolicina.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

}