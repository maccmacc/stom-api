package com.stom.app.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the plan_rada database table.
 * 
 */
@Entity
@Table(name="plan_rada")
@NamedQuery(name="PlanRada.findAll", query="SELECT p FROM PlanRada p")
public class PlanRada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date datum;

	//bi-directional many-to-one association to Pacijent
	@ManyToOne
	@JoinColumn(name="pacijent")
	private Pacijent pacijent;

	//bi-directional many-to-one association to Zaposleni
	@ManyToOne
	@JoinColumn(name="zaposelni")
	private Zaposleni zaposleni;

	//bi-directional many-to-one association to StavkaPlanaRada
	@OneToMany(mappedBy="planRada")
	@JsonIgnore
	private List<StavkaPlanaRada> stavkaPlanaRadas;

	@Transient
	private String lookup;
	
	public PlanRada() {
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

	public Pacijent getPacijent() {
		return this.pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Zaposleni getZaposleni() {
		return this.zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public List<StavkaPlanaRada> getStavkaPlanaRadas() {
		return this.stavkaPlanaRadas;
	}

	public void setStavkaPlanaRadas(List<StavkaPlanaRada> stavkaPlanaRadas) {
		this.stavkaPlanaRadas = stavkaPlanaRadas;
	}

	public StavkaPlanaRada addStavkaPlanaRada(StavkaPlanaRada stavkaPlanaRada) {
		getStavkaPlanaRadas().add(stavkaPlanaRada);
		stavkaPlanaRada.setPlanRada(this);

		return stavkaPlanaRada;
	}

	public StavkaPlanaRada removeStavkaPlanaRada(StavkaPlanaRada stavkaPlanaRada) {
		getStavkaPlanaRadas().remove(stavkaPlanaRada);
		stavkaPlanaRada.setPlanRada(null);

		return stavkaPlanaRada;
	}
	
	public String getLookup() {
		String l = id.toString();
		if (datum != null)
			l = l + " - " + datum;
		if (pacijent != null)
			l = l + ", " + pacijent.getLookup();
		return l;
		
	}

}